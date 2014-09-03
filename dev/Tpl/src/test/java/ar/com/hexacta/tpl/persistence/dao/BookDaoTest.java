package ar.com.hexacta.tpl.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.dao.BookDAO;

public class BookDaoTest {

	@Autowired
	private BookDAO bookDao;

	@Test
	public void testFindById() {
		Book book = bookDao.findById(1l);

		assertEquals("El principito", book.getName());
		assertEquals(
				"Best-seller del escritor frances Antoine de Saint-Exupery.",
				book.getDescription());
		assertEquals("Editorial Planeta", book.getPublisher());
		return;
	}

	@Test
	public void testFindAll() {
		Book book = new Book();
		book.setPublisher("Editorial Planeta");
		book.setName("El principito");
		book.setDescription("Best-seller del escritor frances Antoine de Saint-Exupery.");
		bookDao.save(book);

		List<Book> books = bookDao.findAll();
		assertEquals(2, books.size());
		Book newBook = books.get(0);

		assertEquals("El principito", newBook.getName());
		assertEquals("Editorial Planeta", newBook.getPublisher());
		assertEquals(
				"Best-seller del escritor frances Antoine de Saint-Exupery.",
				newBook.getDescription());
		return;
	}

}
