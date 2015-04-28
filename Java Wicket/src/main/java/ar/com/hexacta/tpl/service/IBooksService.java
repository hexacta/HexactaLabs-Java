package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.Book;

public interface IBooksService {

    List<Book> findAllBooks();

    void createBook(final Book book);

    void updateBook(final Book book);

    void deleteBook(final Book book);

}
