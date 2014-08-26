package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.BookCopy;

public interface BookCopyRepository {
	
	void save(final BookCopy bookCopy);
	
	void delete (final BookCopy bookCopy);

	List<BookCopy> findAll();

	BookCopy findFreeCopy(Long bookId);

}
