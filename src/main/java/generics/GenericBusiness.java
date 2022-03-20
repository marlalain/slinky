package generics;

import exceptions.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
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

public class GenericBusiness<T, S extends Serializable> implements IGenericRepository<T, S> {

	@Autowired
	private GenericRepository<T, S> repository;

	@Override
	public void persist(T entity) {
		this.repository.persist(entity);
	}

	@Override
	public void update(T entity) {
		this.repository.update(entity);
	}

	@Override
	public void update(T entity, String... fields) throws NoSuchFieldException, GenericException {
		this.repository.update(entity, fields);
	}

	@Override
	public void update(T entity, List<Field> fieldsToUpdate) throws NoSuchFieldException, GenericException {
		this.repository.update(entity, fieldsToUpdate);
	}

	@Override
	public void updateNonNullFields(T entity) {
		this.repository.update(entity);
	}

	@Override
	public void deleteById(S id) {
		this.repository.deleteById(id);
	}

	@Override
	public void delete(T entity) {
		this.repository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends S> s) {
		this.repository.deleteAllById(s);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		this.repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		this.repository.deleteAll();
	}

	@Override
	public T persistOrUpdate(T entity) throws NoSuchFieldException, GenericException {
		return this.repository.persistOrUpdate(entity);
	}

	@Override
	public List<T> persistOrUpdate(List<T> entities) {
		return this.repository.persistOrUpdate(entities);
	}

	@Override
	public T persisOrUpdateNonNullFields(T entity) throws NoSuchFieldException, GenericException {
		return this.repository.persisOrUpdateNonNullFields(entity);
	}

	@Override
	public List<T> persistOrUpdateNonNullFields(List<T> entities) {
		return this.repository.persistOrUpdateNonNullFields(entities);
	}

	@Override
	public List<T> findAll() {
		return this.repository.findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return this.repository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	@Override
	public List<T> findAllById(Iterable<S> s) {
		return this.repository.findAllById(s);
	}

	@Override
	public long count() {
		return this.repository.count();
	}

	@Override
	public <ID extends T> ID save(ID entity) {
		return this.repository.save(entity);
	}

	@Override
	public <ID extends T> List<ID> saveAll(Iterable<ID> entities) {
		return this.repository.saveAll(entities);
	}

	@Override
	public Optional<T> findById(S s) {
		return this.repository.findById(s);
	}

	@Override
	public boolean existsById(S s) {
		return this.repository.existsById(s);
	}

	@Override
	public void flush() {
		this.repository.flush();
	}

	@Override
	public <ID extends T> ID saveAndFlush(ID entity) {
		return this.repository.saveAndFlush(entity);
	}

	@Override
	public <ID extends T> List<ID> saveAllAndFlush(Iterable<ID> entities) {
		return this.repository.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<T> entities) {
		this.repository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<S> s) {
		this.repository.deleteAllByIdInBatch(s);
	}

	@Override
	public void deleteAllInBatch() {
		this.repository.deleteAllInBatch();
	}

	@Override
	@Deprecated
	public T getOne(S s) {
		return this.repository.getOne(s);
	}

	@Override
	public T getById(S s) {
		return this.repository.getById(s);
	}

	@Override
	public <ID extends T> Optional<ID> findOne(Example<ID> example) {
		return this.repository.findOne(example);
	}

	@Override
	public <ID extends T> List<ID> findAll(Example<ID> example) {
		return this.repository.findAll(example);
	}

	@Override
	public <ID extends T> List<ID> findAll(Example<ID> example, Sort sort) {
		return this.repository.findAll(example, sort);
	}

	@Override
	public <ID extends T> Page<ID> findAll(Example<ID> example, Pageable pageable) {
		return this.repository.findAll(example, pageable);
	}

	@Override
	public <ID extends T> long count(Example<ID> example) {
		return this.repository.count(example);
	}

	@Override
	public <ID extends T> boolean exists(Example<ID> example) {
		return this.repository.exists(example);
	}

	@Override
	public <ID extends T, R> R findBy(Example<ID> example, Function<FluentQuery.FetchableFluentQuery<ID>, R> queryFunction) {
		return this.repository.findBy(example, queryFunction);
	}
}
