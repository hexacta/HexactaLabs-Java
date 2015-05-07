package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.repository.BookRepository;
import ar.com.hexacta.tpl.service.IBooksService;
@Service
public class BooksServiceImpl implements IBooksService {
	@Autowired
    private BookRepository booksRepository;

    @Override
    @Transactional
    public void createBook(final Book book) {
        // TODO: Add validation logic
        booksRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(final Book book) {
        booksRepository.delete(book);

    }

    @Override
    @Transactional
    public void updateBook(final Book book) {
        // TODO: Add validation logic
        booksRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return booksRepository.findAll();
    }

    public BookRepository getBookRepository() {
        return booksRepository;
    }

    public void setBookRepository(final BookRepository bookRepository) {
        booksRepository = bookRepository;
    }

}
