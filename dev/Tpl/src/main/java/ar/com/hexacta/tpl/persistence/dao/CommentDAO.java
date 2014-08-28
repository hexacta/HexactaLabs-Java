package ar.com.hexacta.tpl.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;

@Repository
public class CommentDAO extends AbstractDAO<Comment> implements
		CommentRepository {

	@Override
	@SuppressWarnings("unchecked")
	public Comment findById(final Serializable commentId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("id", commentId));
		List<Comment> result = (List<Comment>) this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size() == 0){
			return null;
		}
		else{
			return result.get(0);
		}
	}

	@Override
	public void deleteById(final Serializable commentId) {
		System.out.println("CommentDAO.deleteById( " + commentId + " )...");
		super.deleteById(commentId);
		System.out.println("<< OK >>");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findByBookId(final Long bookId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("book.id", bookId));
		return (List<Comment>) this.getHibernateTemplate().findByCriteria(criteria);
	}

}
