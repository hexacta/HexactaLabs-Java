package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;

@Repository
public class BookCopyDAO extends AbstractDAO<BookCopy> implements BookCopyRepository {

	@SuppressWarnings("unchecked")
	@Override
	public BookCopy findFreeCopy(final long bookId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("book.id", bookId));
		criteria.add(Restrictions.eq("free", true));
		List<BookCopy> result = (List<BookCopy>) this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size()==0){
			return null;
		}else{
			return result.get(0);
		}
		
	
	}


}
