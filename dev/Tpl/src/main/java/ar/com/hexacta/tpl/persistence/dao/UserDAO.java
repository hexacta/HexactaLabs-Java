package ar.com.hexacta.tpl.persistence.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;

@Repository
public class UserDAO extends AbstractDAO<User> implements UserRepository{

	@Override
	public void deleteById(Long userId) {
		super.deleteById(userId);
	}

	@Override
	public User findById(Long userId) {
		Criteria criteria = this.getSession().createCriteria(User.class);
		criteria.add(Restrictions.like("id", userId));
		return (User)criteria.uniqueResult();
	}

	public User findByUser(String username) {
		Criteria criteria = this.getSession().createCriteria(User.class);
		criteria.add(Restrictions.ilike("username", username));
		return(User)criteria.uniqueResult();
	}

	
}
