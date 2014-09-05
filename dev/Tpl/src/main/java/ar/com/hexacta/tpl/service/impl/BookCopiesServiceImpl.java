package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;
import ar.com.hexacta.tpl.service.IBookCopiesService;

@Service
public class BookCopiesServiceImpl implements IBookCopiesService {

	@Autowired
	private BookCopyRepository bookCopyRepository;

	public void setBookCopyRepository(
			final BookCopyRepository bookCopyRepository) {
		this.bookCopyRepository = bookCopyRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookCopy> findAllCopies() {
		return bookCopyRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public BookCopy findCopy(final Long copyId) {
		return bookCopyRepository.findById(copyId);
	}

	@Override
	@Transactional(readOnly = true)
	public BookCopy findFreeCopyByBook(final long bookId) {
		return bookCopyRepository.findFreeCopy(bookId);
	}

	@Override
	@Transactional
	public void updateCopy(final BookCopy copy) {
		bookCopyRepository.saveOrUpdate(copy);
	}

}
