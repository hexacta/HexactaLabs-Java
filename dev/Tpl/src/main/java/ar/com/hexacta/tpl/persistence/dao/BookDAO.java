package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.repository.BookRepository;

@Repository
public class BookDAO extends AbstractDAO<Book> implements BookRepository {

	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		Criteria criteria = this.getSession().createCriteria(Book.class);
		criteria.add(Restrictions.eq("enabled", true));
		return criteria.list();
	}

	@Override
	public Book findById(final Long bookId) {
		Criteria criteria = this.getSession().createCriteria(Book.class);
		criteria.add(Restrictions.like("id", bookId));
		criteria.add(Restrictions.eq("enabled", true));
		return (Book) criteria.uniqueResult();
	}

	@Override
	public void deleteById(final Long bookId) {
		super.deleteById(bookId);
	}

}
