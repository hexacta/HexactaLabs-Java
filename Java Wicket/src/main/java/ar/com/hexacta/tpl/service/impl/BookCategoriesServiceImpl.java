package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.persistence.repository.BookCategoryRepository;
import ar.com.hexacta.tpl.service.IBookCategoriesService;

public class BookCategoriesServiceImpl implements IBookCategoriesService {

    private BookCategoryRepository categoriesRepository;

    public void setCategoriesRepository(final BookCategoryRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    @Transactional
    public List<BookCategory> findAllCategories() {
        return categoriesRepository.findAll();
    }

}
