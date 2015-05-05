package ar.com.hexacta.tpl.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;

@Repository
public class UserDAO extends AbstractDAO<User> implements UserRepository {

	@Override
	public void deleteById(Serializable userId) {
		super.deleteById(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findById(Serializable userId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("id", userId));
		List<User> result = (List<User>) this.getHibernateTemplate()
				.findByCriteria(criteria);
		if (result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUser(String username) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.ilike("username", username));
		List<User> result = (List<User>) this.getHibernateTemplate()
				.findByCriteria(criteria);
		if (result.size() == 0) {
			return null;
		} else {
			return result.get(0);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		DetachedCriteria criteria = createCriteria();
		criteria.add(Restrictions.eq("enabled", true));
		return (List<User>) getHibernateTemplate().findByCriteria(criteria);
	}

}
