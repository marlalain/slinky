package generics;

import exceptions.GenericException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

@NoRepositoryBean
@SuppressWarnings({"unused"})
public interface IGenericRepository<T, S extends Serializable> extends JpaRepository<T, S> {
	void persist(T entity);

	void update(T entity);

	void update(T entity, String... fields) throws NoSuchFieldException, GenericException;

	void update(T entity, List<Field> fieldsToUpdate) throws NoSuchFieldException, GenericException;

	void updateNonNullFields(T entity) throws NoSuchFieldException, GenericException;

	void deleteById(S id);

	T persistOrUpdate(T entity) throws NoSuchFieldException, GenericException;

	T persisOrUpdateNonNullFields(T entity) throws NoSuchFieldException, GenericException;

	List<T> persistOrUpdate(List<T> entities);

	List<T> persistOrUpdateNonNullFields(List<T> entities);
}
