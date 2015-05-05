package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.BookCopy;

public interface IBookCopiesService {

	List<BookCopy> findAllCopies();

	BookCopy findFreeCopyByBook(long bookId);

	void updateCopy(final BookCopy copy);

	BookCopy findCopy(Long copyId);
}
