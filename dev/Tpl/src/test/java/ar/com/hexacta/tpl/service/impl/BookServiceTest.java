package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.dao.BookDAO;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	private Book book;

	@InjectMocks
	private BooksServiceImpl service = new BooksServiceImpl();

	@Mock
	private BookDAO dao;

	@Before
	public void prepare() {

		book = new Book();
		book.setName("name");
		book.setDescription("desc");
		book.setCountry("EEUU");
		book.setIsbn("1345");
		book.setPublisher("publisher");
		book.setId(1L);

		List<Book> todosLosUsuarios = new ArrayList<Book>();
		todosLosUsuarios.add(book);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

		// getUsuario
		when(dao.findById(anyInt())).thenReturn(book);

	}

	@Test
	public void testFindAll() {
		List<Book> todosLosUsuarios = service.findAllBooks();

		Assert.assertNotNull(todosLosUsuarios);
		Assert.assertTrue(todosLosUsuarios.size() > 0);
		Assert.assertEquals(todosLosUsuarios.get(0).getDescription(), "desc");

	}

	@Test
	public void testFindById() {
		Book ubook = service.findBook(1L);

		Assert.assertNotNull(ubook);
		Assert.assertEquals(ubook.getDescription(), "desc");
		Assert.assertEquals(ubook.getPublisher(), "publisher");
		Assert.assertEquals(ubook.getName(), "name");
		Assert.assertEquals(ubook.getIsbn(), "1345");
		Assert.assertEquals(ubook.getCountry(), "EEUU");
		Assert.assertTrue(ubook.getId().equals(1L));

	}

	@Test
	public void testCreateBook() {
		Book ubook = new Book("aName", "aDescription", "aPublisher",
				"aCountry", "12345", null, null);
		service.createBook(ubook);
		verify(dao, atLeastOnce()).save(ubook);

	}

	@Test
	public void testDeleteBookById() {

		service.deleteBookById(1L);
		verify(dao, atLeastOnce()).deleteById(1L);

	}

	@Test
	public void testDeleteBook() {

		service.deleteBook(book);
		verify(dao, atLeastOnce()).delete(book);
	}

	@Test
	public void testUpdateBook() {

		service.updateBook(book);
		verify(dao, atLeastOnce()).save(book);
	}
}
