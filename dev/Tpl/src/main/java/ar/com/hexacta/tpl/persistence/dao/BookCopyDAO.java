package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;

public class BookCopyDAO extends AbstractDAO<BookCopy> implements BookCopyRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<BookCopy> findByBookId(Long bookId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("book_id", bookId));
		return (List<BookCopy>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BookCopy findFreeBookCopy(Long BookId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("Free", true));
		List<BookCopy> result = (List<BookCopy>) this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size() == 0){
			return null;
		}else{
			return result.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BookCopy findById(long bookCopyId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("id", bookCopyId));
		List<BookCopy> result = (List<BookCopy>) this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size() == 0){
			return null;
		}
		else{
			return result.get(0);
		}
	}

}
