package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Book;

public interface BookRepository {

    void save(final Book book);

    void delete(final Book book);

    List<Book> findAll();

}
