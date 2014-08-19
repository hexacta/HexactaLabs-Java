package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;

@Repository
public class CommentDAO extends AbstractDAO<Comment> implements
		CommentRepository {

	// TODO Remove prints!!

	@Override
	public Comment findById(final Long commentId) {
		Criteria criteria = this.getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.like("id", commentId));
		return (Comment) criteria.uniqueResult();
	}

	@Override
	public void deleteById(final Long commentId) {
		System.out.println("CommentDAO.deleteById( " + commentId + " )...");
		super.deleteById(commentId);
		System.out.println("<< OK >>");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findByBookId(final Long bookId) {
		/*System.out.println("CommentDAO.findByBookId( " + bookId + " )...");
		System.out.println("<< Unsupported Operation >>");
		throw new UnsupportedOperationException();*/
		Criteria criteria = this.getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.like("bookId", bookId));
		return (List<Comment>) criteria.list();
	}

}
