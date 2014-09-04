package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.persistence.dao.BookCategoryDAO;

@RunWith(MockitoJUnitRunner.class)
public class BookCategoriesServiceTest {

	private BookCategory bookCat;

	@InjectMocks
	private BookCategoriesServiceImpl service = new BookCategoriesServiceImpl();

	@Mock
	private BookCategoryDAO dao;

	@Before
	public void prepare() {

		bookCat = new BookCategory();
		bookCat.setName("name");
		bookCat.setDescription("desc");
		bookCat.setId(1L);

		List<BookCategory> todosLosUsuarios = new ArrayList<BookCategory>();
		todosLosUsuarios.add(bookCat);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

	}

	@Test
	public void testFindAll() {
		List<BookCategory> todosLosUsuarios = service.findAllCategories();

		Assert.assertNotNull(todosLosUsuarios);
		Assert.assertTrue(todosLosUsuarios.size() > 0);

		Assert.assertEquals(todosLosUsuarios.get(0).getDescription(), "desc");

	}
}
