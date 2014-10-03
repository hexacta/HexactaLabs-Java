package ar.com.hexacta.tpl.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;

@Repository
public class CommentDAO extends AbstractDAO<Comment> implements CommentRepository {

    @Override
    @SuppressWarnings("unchecked")
    public Comment findById(final Serializable commentId) {
        DetachedCriteria criteria = createCriteria();
        criteria.add(Restrictions.eq("id", commentId));
        List<Comment> result = (List<Comment>) getHibernateTemplate().findByCriteria(criteria);
        if (result.size() == 0)
            return null;
        else
            return result.get(0);
    }

    @Override
    public void deleteById(final Serializable commentId) {
        super.deleteById(commentId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Comment> findByBookId(final Long bookId) {
        DetachedCriteria criteria = createCriteria();
        criteria.add(Restrictions.eq("book.id", bookId));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return (List<Comment>) getHibernateTemplate().findByCriteria(criteria);
    }

}
