package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.BookCategory;

public interface BookCategoryRepository {

    void save(final BookCategory bookCategory);

    void delete(final BookCategory bookCategory);

    List<BookCategory> findAll();

}
