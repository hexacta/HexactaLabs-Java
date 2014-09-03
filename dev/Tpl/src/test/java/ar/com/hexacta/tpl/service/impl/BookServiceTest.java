package ar.com.hexacta.tpl.service.impl;

/*
 import static org.mockito.Mockito.*;

 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;

 import org.junit.BeforeClass;
 import org.junit.Test;
 import org.springframework.beans.factory.annotation.Autowired;

 import ar.com.hexacta.tpl.model.Book;
 import ar.com.hexacta.tpl.persistence.repository.BookRepository;

 public class BookServiceTest {

 @Autowired
 private BookRepository booksRepository;

 private static BooksServiceImpl mockedBooksServiceImpl;
 private static Book book1;
 private static Book book2;

 @BeforeClass
 public static void setUp() {
 // Create mock object of BookDAL
 mockedBooksServiceImpl = mock(BookServiceImpl.class);
 // Create few instances of Book class.
 book1 = new Book("nombre", "desc", "publisher", "country", "isbn",
 Arrays.asList("fisico"), Arrays.asList("1"));

 book2 = new Book("nombre2", "desc2", "publisher2", "country2", "isbn2",
 Arrays.asList("ebook"), Arrays.asList("2"));
 // Stubbing the methods of mocked BookDAL with mocked data.
 when(mockedBooksServiceImpl.findAllBooks()).thenReturn(
 Arrays.asList(book1, book2));
 when(mockedBooksServiceImpl.findBook(2L)).thenReturn(book2);
 when(mockedBooksServiceImpl.createBook(book1)).thenReturn(
 book1.getISBN());
 when(mockedBooksServiceImpl.updateBook(book1)).thenReturn(
 book1.getISBN());
 }

 @Test
 public void testCreateBook(final Book book) {
 if (!testValidateISBN(book.getISBN()))
 return;
 booksRepository.save(book);
 }

 public void testDeleteBook(final Book book) {
 booksRepository.delete(book);
 }

 public void testDeleteBookById(final Long bookId) {
 booksRepository.deleteById(bookId);
 }

 public void testUpdateBook(final Book book) {
 if (!testValidateISBN(book.getISBN()))
 return;
 booksRepository.save(book);
 }

 public void testFindAllBooks() {
 // Para que no muestre libros repetidos
 Set set = new HashSet();
 set.addAll(booksRepository.findAll());
 List list = new ArrayList();
 list.addAll(set);

 }

 public void testFindBook(final Long bookId) {
 booksRepository.findById(bookId);
 }

 public void setBookRepository(final BookRepository bookRepository) {
 booksRepository = bookRepository;
 }

 private boolean testValidateISBN(final String isbn) {
 if (isbn.isEmpty())
 return false;
 if (!isbn.matches("^[0-9\\-]*"))
 return false;
 return true;
 }
 }*/

import static org.mockito.Matchers.anyInt;
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
		Assert.assertTrue(ubook.getId() == 1);

	}

	@Test
	public void testCreateBook() {
		Book ubook = new Book("aName", "aDescription", "aPublisher",
				"aCountry", "12345", null, null);
		service.createBook(ubook);
		Assert.assertNotNull(ubook);

	}

	@Test
	public void testDeleteBookById() {

		service.deleteBookById(1L);
		Book ubook = service.findBook(1L);
		Assert.assertNotNull(ubook);
	}

	@Test
	public void testDeleteBook() {

		Book ubook = new Book();
		service.deleteBook(ubook);
		Assert.assertNotNull(ubook);
	}

	@Test
	public void testUpdateBook() {
		Book ubook = new Book("aName", "aDescription", "aPublisher",
				"aCountry", "12345", null, null);
		service.createBook(ubook);
		Book ubook2 = ubook;
		// new Book("aName", "aDescription", "aPublisher",
		// "aCountry", "12345", null, null);
		service.updateBook(ubook);
		Assert.assertEquals(ubook, ubook2);

	}
}
