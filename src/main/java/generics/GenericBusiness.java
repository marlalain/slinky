package generics;

import exceptions.GenericException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GenericBusiness<T, S extends Serializable> {
	protected final IGenericRepository<T, S> repository;

	public GenericBusiness(IGenericRepository<T, S> repository) {
		this.repository = repository;
	}

	public void persist(T entity) {
		this.repository.persist(entity);
	}

	public void update(T entity) {
		this.repository.update(entity);
	}

	public void update(T entity, String... fields) throws NoSuchFieldException, GenericException {
		this.repository.update(entity, fields);
	}

	public void update(T entity, List<Field> fieldsToUpdate) throws NoSuchFieldException, GenericException {
		this.repository.update(entity, fieldsToUpdate);
	}

	public void updateNonNullFields(T entity) {
		this.repository.update(entity);
	}

	public void deleteById(S id) {
		this.repository.deleteById(id);
	}

	public void delete(T entity) {
		this.repository.delete(entity);
	}

	public void deleteAllById(Iterable<? extends S> s) {
		this.repository.deleteAllById(s);
	}

	public void deleteAll(Iterable<? extends T> entities) {
		this.repository.deleteAll(entities);
	}

	public void deleteAll() {
		this.repository.deleteAll();
	}

	public T persistOrUpdate(T entity) throws NoSuchFieldException, GenericException {
		return this.repository.persistOrUpdate(entity);
	}

	public List<T> persistOrUpdate(List<T> entities) {
		return this.repository.persistOrUpdate(entities);
	}

	public T persisOrUpdateNonNullFields(T entity) throws NoSuchFieldException, GenericException {
		return this.repository.persisOrUpdateNonNullFields(entity);
	}

	public List<T> persistOrUpdateNonNullFields(List<T> entities) {
		return this.repository.persistOrUpdateNonNullFields(entities);
	}

	public List<T> findAll() {
		return this.repository.findAll();
	}

	public List<T> findAll(Sort sort) {
		return this.repository.findAll(sort);
	}

	public Page<T> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public <ID extends T> List<ID> findAll(Example<ID> example) {
		return this.repository.findAll(example);
	}

	public <ID extends T> List<ID> findAll(Example<ID> example, Sort sort) {
		return this.repository.findAll(example, sort);
	}

	public <ID extends T> Page<ID> findAll(Example<ID> example, Pageable pageable) {
		return this.repository.findAll(example, pageable);
	}

	public List<T> findAllById(Iterable<S> s) {
		return this.repository.findAllById(s);
	}

	public long count() {
		return this.repository.count();
	}

	public <ID extends T> long count(Example<ID> example) {
		return this.repository.count(example);
	}

	public <ID extends T> ID save(ID entity) {
		return this.repository.save(entity);
	}

	public <ID extends T> List<ID> saveAll(Iterable<ID> entities) {
		return this.repository.saveAll(entities);
	}

	public Optional<T> findById(S s) {
		return this.repository.findById(s);
	}

	public boolean existsById(S s) {
		return this.repository.existsById(s);
	}

	public void flush() {
		this.repository.flush();
	}

	public <ID extends T> ID saveAndFlush(ID entity) {
		return this.repository.saveAndFlush(entity);
	}

	public <ID extends T> List<ID> saveAllAndFlush(Iterable<ID> entities) {
		return this.repository.saveAllAndFlush(entities);
	}

	public void deleteAllInBatch(Iterable<T> entities) {
		this.repository.deleteAllInBatch(entities);
	}

	public void deleteAllInBatch() {
		this.repository.deleteAllInBatch();
	}

	public void deleteAllByIdInBatch(Iterable<S> s) {
		this.repository.deleteAllByIdInBatch(s);
	}

	@Deprecated
	public T getOne(S s) {
		return this.repository.getOne(s);
	}

	public T getById(S s) {
		return this.repository.getById(s);
	}

	public <ID extends T> Optional<ID> findOne(Example<ID> example) {
		return this.repository.findOne(example);
	}

	public <ID extends T> boolean exists(Example<ID> example) {
		return this.repository.exists(example);
	}

	public <ID extends T, R> R findBy(Example<ID> example, Function<FluentQuery.FetchableFluentQuery<ID>, R> queryFunction) {
		return this.repository.findBy(example, queryFunction);
	}
}
