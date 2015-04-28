package ar.com.hexacta.tpl.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractDAO<T> extends HibernateDaoSupport {

    private static final String UNCHECKED = "unchecked";

    private final transient Class<T> persistentClass;

    @SuppressWarnings(UNCHECKED)
    protected AbstractDAO() {
        super();
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.persistentClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    public void save(final T entity) {
        this.saveOrUpdate(entity);
    }

    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public void saveOrUpdate(final T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.getPersistentClass(), id);
    }

    @SuppressWarnings(UNCHECKED)
    public List<T> findAll() {
        return this.getHibernateTemplate().find("from " + this.getPersistentClass().getName() + " o");
    }

    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    public void flushAndClear() {
        this.getHibernateTemplate().flush();
        this.getHibernateTemplate().clear();
    }

    protected Criteria createCriteria() {
        return this.getSession().createCriteria(this.getPersistentClass());
    }

}
