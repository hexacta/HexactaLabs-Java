package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.service.IBookCopiesService;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;

@Service
public class BookCopiesServiceImpl implements IBookCopiesService{
	

	BookCopyRepository bookCopyRepository;

	@Override
	@Transactional(readOnly = true)
	public List<BookCopy> findAllCopies() {
		return bookCopyRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public BookCopy findCopyById(final long BookCopyId) {
		return bookCopyRepository.findById(BookCopyId);
	}

	@Override
	@Transactional
	public void createCopy(final BookCopy bookCopy) {
		bookCopyRepository.save(bookCopy);
	}

	@Override
	@Transactional
	public void deleteCopy(final BookCopy bookCopy) {
		bookCopyRepository.delete(bookCopy);
	}

	@Override
	@Transactional
	public void updateCopy(final BookCopy bookCopy) {
		bookCopyRepository.update(bookCopy);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookCopy> findByBookId(long bookId) {
		return bookCopyRepository.findByBookId(bookId);
	}

	@Override
	@Transactional(readOnly = true)
	public BookCopy findFreeCopyByBookId(long bookId) {
		return bookCopyRepository.findFreeBookCopy(bookId);
	}

}
