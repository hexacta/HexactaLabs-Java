package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.BookCategory;

public interface IBookCategoriesService {

    List<BookCategory> findAllCategories();

}
