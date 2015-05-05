package ar.com.hexacta.tpl.persistence.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T> {
	public void save(final T entity);

	public void delete(final T entity);

	public void update(final T entity);

	public void saveOrUpdate(final T entity);

	public T findById(final Serializable id);

	public List<T> findAll();

	public void deleteById(final Serializable id);
}
