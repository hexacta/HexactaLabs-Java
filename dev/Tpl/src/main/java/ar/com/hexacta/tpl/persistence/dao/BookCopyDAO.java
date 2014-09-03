package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;



import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;

@Repository
public class BookCopyDAO extends AbstractDAO<BookCopy> implements BookCopyRepository {

	@Override
	@SuppressWarnings("unchecked")
	public BookCopy findFreeCopy(final Long bookId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("book.id", bookId));
		criteria.add(Restrictions.like("state", "Free"));
		List<BookCopy> result = (List<BookCopy>) this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size()==0){
			return null;
		}else{
			return result.get(0);
		}
	}

}
