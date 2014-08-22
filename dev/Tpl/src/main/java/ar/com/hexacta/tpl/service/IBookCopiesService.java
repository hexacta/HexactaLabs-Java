package ar.com.hexacta.tpl.service;

import java.util.List;
import ar.com.hexacta.tpl.model.BookCopy;


public interface IBookCopiesService {
	
	List <BookCopy> findAllCopies();
	
	BookCopy findCopyById(final long BookCopyId);
	
	void createCopy(final BookCopy bookCopy);
	
	void deleteCopy(final BookCopy bookCopy);
	
	void updateCopy(final BookCopy bookCopy);
	
	List<BookCopy> findByBookId (final long bookId);
	
	BookCopy findFreeCopyByBookId (final long bookId);

}
