package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.anyLong;
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

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.persistence.dao.BookCopyDAO;

@RunWith(MockitoJUnitRunner.class)
public class BookCopiesServiceTest {

	private BookCopy bookcopy;

	@InjectMocks
	private BookCopiesServiceImpl service = new BookCopiesServiceImpl();

	@Mock
	private BookCopyDAO dao;

	@Before
	public void prepare() {

		bookcopy = new BookCopy();
		bookcopy.setBook(new Book());
		bookcopy.setCode("Code");
		bookcopy.setBookRate("Good");
		bookcopy.changeToFree();
		bookcopy.setId(1L);

		List<BookCopy> todosLosUsuarios = new ArrayList<BookCopy>();
		todosLosUsuarios.add(bookcopy);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

		// getUsuario
		when(dao.findFreeCopy(anyLong())).thenReturn(bookcopy);

	}

	@Test
	public void testFindAll() {
		List<BookCopy> todosLosUsuarios = service.findAllCopies();

		Assert.assertNotNull(todosLosUsuarios);
		Assert.assertTrue(todosLosUsuarios.size() > 0);
		Assert.assertEquals(todosLosUsuarios.get(0).getCode(), "Code");

	}

	@Test
	public void testFindFreeCopyById() {
		BookCopy ubookcopy = service.findFreeCopyByBook(1L);

		Assert.assertNotNull(ubookcopy);
		Assert.assertEquals(ubookcopy.getCode(), "Code");
		Assert.assertEquals(ubookcopy.getState(), "Free");
		Assert.assertEquals(ubookcopy.getBookRate(), "Good");
		Assert.assertTrue(ubookcopy.getId() == 1);
	}
}