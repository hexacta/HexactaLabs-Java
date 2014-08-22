package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.repository.BookRepository;

@Repository
public class BookDAO extends AbstractDAO<Book> implements BookRepository {

<<<<<<< HEAD
    @Override
    @SuppressWarnings("unchecked")
    public List<Book> findAll() {
        DetachedCriteria criteria = createCriteria();
        criteria.add(Restrictions.eq("enabled", true));
        return (List<Book>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Book findById(final Long bookId) {
        DetachedCriteria criteria = createCriteria();
        criteria.add(Restrictions.like("id", bookId));
        criteria.add(Restrictions.eq("enabled", true));
        List<Book> result = (List<Book>) getHibernateTemplate().findByCriteria(criteria);
        if (result.size() == 0)
            return null;
        else
            return result.get(0);
    }

    @Override
    public void deleteById(final Long bookId) {
        super.deleteById(bookId);
    }
=======
	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("enabled", true));
		return (List<Book>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Book findById(final Long bookId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.like("id", bookId));
		criteria.add(Restrictions.eq("enabled", true));
		List<Book> result = (List<Book>) this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size() == 0){
			return null;
		}
		else{
			return result.get(0);
		}
	}

	@Override
	public void deleteById(final Long bookId) {
		super.deleteById(bookId);
	}
>>>>>>> ee4c7d4f195a05e7e64b4422afee0982dba1b329

}
