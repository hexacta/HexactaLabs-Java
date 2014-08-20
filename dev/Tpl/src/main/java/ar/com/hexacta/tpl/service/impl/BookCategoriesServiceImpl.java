package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.persistence.repository.BookCategoryRepository;
import ar.com.hexacta.tpl.service.IBookCategoriesService;

@Service
public class BookCategoriesServiceImpl implements IBookCategoriesService {
	
	@Autowired
    private BookCategoryRepository categoriesRepository;

    public void setCategoriesRepository(final BookCategoryRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCategory> findAllCategories() {
        return categoriesRepository.findAll();
    }

}
