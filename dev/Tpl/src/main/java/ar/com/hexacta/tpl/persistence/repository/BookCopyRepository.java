package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.BookCopy;

public interface BookCopyRepository {
	
	void save(final BookCopy bookCopy);
	
	void delete (final BookCopy bookCopy);
	
	List<BookCopy> findByBookId(final Long bookId);
	
	BookCopy findFreeBookCopy(final Long bookId);

	List<BookCopy> findAll();

	BookCopy findById(long bookCopyId);

	void update(final BookCopy bookCopy);

}
