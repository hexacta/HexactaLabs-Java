package ar.com.hexacta.tpl.persistence.dao;

import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.persistence.repository.BookCategoryRepository;

@Repository
public class BookCategoryDAO extends AbstractDAO<BookCategory> implements
		BookCategoryRepository {

}
