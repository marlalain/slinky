package generics;

import exceptions.GenericException;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.server.ServerErrorException;
import utils.ReflectionUtil;
import utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@SuppressWarnings({"unused"})
public class GenericRepository<T, S extends Serializable> extends SimpleJpaRepository<T, S> implements IGenericRepository<T, S> {

	private final CriteriaBuilder cb;
	private EntityManager em;

	public GenericRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.cb = em.getCriteriaBuilder();
	}

	public GenericRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.cb = em.getCriteriaBuilder();
	}

	@Override
	@Transactional
	public void persist(T entity) {
		this.em.persist(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		persist(entity);
	}

	@Override
	@Transactional
	@SuppressWarnings({"unchecked"})
	public void update(T entity, List<Field> fieldsToUpdate) throws NoSuchFieldException, GenericException {
		Field idField = ReflectionUtil.getIdField(entity);

		if (isNull(idField)) throw new NoSuchFieldException("Could not find field annotated with @Id");

		Object id = ReflectionUtil.getValueByField(idField, entity);

		CriteriaUpdate<T> criteria = (CriteriaUpdate<T>) cb.createCriteriaUpdate(entity.getClass());
		Root<T> root = criteria.from((Class<T>) entity.getClass());

		fieldsToUpdate.parallelStream().forEach(field -> {
			if (ReflectionUtil.isEntity(field) && ReflectionUtil.isFieldNull(field, entity)) {
				criteria.set(root.<Long>get(field.getName()), cb.nullLiteral(Long.class));
			} else if (ReflectionUtil.isEmbeddable(field) && ReflectionUtil.isFieldNotNull(field, entity)) {
				try {
					handleEmbeddedAttribute(entity, criteria, root, field);
				} catch (GenericException e) {
					throw new RuntimeException(e);
				}
			} else {
				try {
					criteria.set(field.getName(), ReflectionUtil.getValueByField(field, entity));
				} catch (GenericException e) {
					throw new RuntimeException(e);
				}
			}
		});

		criteria.where(cb.equal(root.get(idField.getName()), id));

		Query query = em.createQuery(criteria);

		query.executeUpdate();
	}

	@Override
	@Transactional
	public void update(T entity, String... fields) throws NoSuchFieldException, GenericException {
		List<Field> fieldsToUpdate = new ArrayList<>();

		StringUtils.toList(fields).forEach(field -> {
			try {
				fieldsToUpdate.add(entity.getClass().getDeclaredField(String.valueOf(field)));
			} catch (NoSuchFieldException | SecurityException e) {
				throw new ServerErrorException("Invalid specified field" + e.getMessage(), e.getCause());
			}
		});

		update(entity, fieldsToUpdate);
	}

	private void handleEmbeddedAttribute(T entity, CriteriaUpdate<T> criteria, Root<T> root, Field field) throws GenericException {
		List<Field> embeddedFields = Arrays.asList(field.getType().getDeclaredFields());

		if (!embeddedFields.isEmpty()) {
			AtomicReference<Object> value = new AtomicReference<>();
			Object embedded = ReflectionUtil.getValueByField(field, entity);
			embeddedFields.parallelStream().forEach(embeddedField -> {
				try {
					value.set(ReflectionUtil.getValueByField(embeddedField, embedded));
					if (nonNull(value.get()) && ReflectionUtil.isFieldUpdatable(embeddedField, embedded)) {
						criteria.set(root.get(field.getName()).get(embeddedField.getName()), value);
					}
				} catch (GenericException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}

	@Override
	@Transactional
	public void updateNonNullFields(T entity) throws NoSuchFieldException, GenericException {
		List<Field> updatableFields = getUpdatableFields(entity);
		update(entity, updatableFields);
	}

	private List<Field> getUpdatableFields(T entity) {
		List<Field> fields = FieldUtils.getAllFieldsList(entity.getClass());

		return fields.parallelStream()
			.filter(field -> ReflectionUtil.isFieldUpdatable(field, entity))
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public T persistOrUpdate(T entity) throws NoSuchFieldException, GenericException {
		Object id = getId(entity);

		if (isNull(id)) this.em.persist(entity);
		else update(entity);

		return entity;
	}

	@Override
	@Transactional
	public T persisOrUpdateNonNullFields(T entity) throws NoSuchFieldException, GenericException {
		List<Field> fieldsToUpdate = getUpdatableFields(entity);

		update(entity, fieldsToUpdate);

		return entity;
	}

	private List<T> persistOrUpdate(List<T> entities, boolean updateNonNullFields) {
		List<T> result = new ArrayList<>();

		if (isNull(entities)) return result;

		entities.parallelStream().forEach(entity -> {
			try {
				if (updateNonNullFields) {
					result.add(persisOrUpdateNonNullFields(entity));
				} else {
					result.add(persistOrUpdate(entity));
				}
			} catch (NoSuchFieldException | GenericException e) {
				throw new RuntimeException(e.getMessage(), e.getCause());
			}
		});

		return result;
	}

	@Override
	@Transactional
	public List<T> persistOrUpdate(List<T> entities) {
		return persistOrUpdate(entities, false);
	}

	@Override
	@Transactional
	public List<T> persistOrUpdateNonNullFields(List<T> entities) {
		return persistOrUpdate(entities, true);
	}

	private Session getSession() {
		return this.em.unwrap(Session.class);
	}

	private Object getId(T entity) throws NoSuchFieldException, GenericException {
		Field idField = ReflectionUtil.getIdField(entity);

		if (isNull(idField)) throw new NoSuchFieldException("Could not find field annotated with @Id");

		return ReflectionUtil.getValueByField(idField, entity);
	}
}
