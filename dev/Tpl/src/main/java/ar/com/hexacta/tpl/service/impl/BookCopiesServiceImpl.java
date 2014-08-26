package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.service.IBookCopiesService;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;

@Service
public class BookCopiesServiceImpl implements IBookCopiesService{
	
	@Autowired
	private BookCopyRepository bookCopyRepository;
	
	 public void setBookCopyRepository(final BookCopyRepository bookCopyRepository) {
	        this.bookCopyRepository = bookCopyRepository;
	    }

	@Override
	@Transactional(readOnly = true)
	public List<BookCopy> findAllCopies() {
		return bookCopyRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public BookCopy findFreeCopyByBook(long bookId) {
		return bookCopyRepository.findFreeCopy(bookId);
	}
}
