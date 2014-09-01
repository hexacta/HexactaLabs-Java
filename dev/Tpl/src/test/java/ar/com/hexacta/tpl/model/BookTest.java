package ar.com.hexacta.tpl.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ar.com.hexacta.tpl.persistence.dao.BookCategoryDAO;
import ar.com.hexacta.tpl.persistence.dao.BookDAO;

public class BookTest {
	private static final String NAME = "Test Book";
	private static final String DESCRIPTION = "Test Book Description";
	private static final String PUBLISHER = "TEST PUBLISHER";
	private static final String COUNTRY = "BOOKLAND";
	private static final String ISBN = "123456789";
	private static HashSet<BookCategory> CATEGORIES;
	private static final HashSet<BookCopy> COPIES = new HashSet<BookCopy> ();
	private static final BookCategory CATEGORY = new BookCategory();
	
	
	private Book testBook;
	private BookDAO dao;
	private BookCategoryDAO categoryDao;
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	@Before
	public void setUp(){
		CATEGORIES = new HashSet<BookCategory> ();
		testBook = new Book(NAME, DESCRIPTION, PUBLISHER, COUNTRY, ISBN, CATEGORIES, COPIES);
		CATEGORIES.add(CATEGORY);
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		categoryDao = (BookCategoryDAO) applicationContext.getBean(BookCategoryDAO.class);
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				categoryDao.save(CATEGORY);
			}
		});
			
		dao = (BookDAO) applicationContext.getBean(BookDAO.class);
		
	}
	
	@After
	public void tearDown(){
		applicationContext.close();
		
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testBookCreation(){
		assertNotNull(testBook);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testParameterCretion(){
		assertTrue(testBook.getName().equals(NAME));
		assertTrue(testBook.getDescription().equals(DESCRIPTION));
		assertTrue(testBook.getPublisher().equals(PUBLISHER));
		assertTrue(testBook.getCountry().equals(COUNTRY));
		assertTrue(testBook.getIsbn().equals(ISBN));
		assertTrue(testBook.getBookCategories() == CATEGORIES);
	}
	
	@Test
    @Transactional(readOnly = true)
    public void testBookDAOEmpty(){
    	assertTrue(dao.findAll().isEmpty());
    }

	@Test
    @Transactional
    public void testBookDAOSaveOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBook);
				List <Book> results = dao.findAll();
				assertFalse(results.isEmpty());
				assertTrue(results.size() == 1);
				assertTrue(results.contains(testBook));
				dao.delete(testBook);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookDAOSaveMany(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Book book1 = new Book();
				Book book2 = new Book();
				Book book3 = new Book();
				dao.save(book1);
				dao.save(book2);
				dao.save(book3);
				List <Book> results = dao.findAll();
				assertTrue(results.size() == 3);
				assertTrue(results.contains(book1) && results.contains(book2) && results.contains(book3));
				dao.delete(book1);
				dao.delete(book2);
				dao.delete(book3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookDAOSaveAndSearch(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBook);
				Book searchedBook = dao.findById(testBook.getId());
				assertTrue(testBook == searchedBook);
				dao.delete(testBook);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookDAOSaveAndDelete(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBook);
				dao.delete(testBook);
				assertTrue(dao.findAll().isEmpty());
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookDAOSaveManyAndDeleteOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Book otherBook = new Book();
				dao.save(testBook);
				dao.save(otherBook);
				dao.delete(testBook);
				assertNull(dao.findById(testBook.getId()));
				assertFalse(dao.findAll().isEmpty());
				assertTrue(dao.findAll().contains(otherBook));
				dao.delete(otherBook);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookDAOSaveAndUpdate(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBook);
				Book searchedBook = dao.findById(testBook.getId());
				searchedBook.setDescription("other description");
				dao.update(searchedBook);
				Book updatedBook = dao.findById(testBook.getId());
				assertTrue(updatedBook.getDescription().equals("other description"));
				dao.delete(testBook);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookDAOWithCategoryDAO(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBook);
				BookCategory newCategory = new BookCategory("other category", "other description");
				categoryDao.save(newCategory);
				testBook.getBookCategories().add(newCategory);
				dao.update(testBook);
				assertTrue(dao.findById(testBook.getId()).getBookCategories().size() == 2);
				dao.delete(testBook);
			}
		});
		
	}
	
}
