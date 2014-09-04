package ar.com.hexacta.tpl.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

import ar.com.hexacta.tpl.persistence.dao.BookCopyDAO;
import ar.com.hexacta.tpl.persistence.dao.BookDAO;

public class BookCopyTest {
	
	private Book testBook;
	private BookCopy testCopy;
	private BookCopyDAO dao;
	private BookDAO bookDao;
	
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	public BookCopyTest(){
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		dao = (BookCopyDAO) applicationContext.getBean(BookCopyDAO.class);
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				List<BookCopy> copies = dao.findAll();
				for (BookCopy copy : copies){
					dao.delete(copy);
				}
			}
		});
	}
	
	@Before
	public void setUp(){
		testBook = new Book();
		testCopy = new BookCopy("code", BookCopy.BOOK_RATE_NORMAL, BookCopy.STATE_FREE, testBook);
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		dao = (BookCopyDAO) applicationContext.getBean(BookCopyDAO.class);
		bookDao = (BookDAO) applicationContext.getBean(BookDAO.class);
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				bookDao.save(testBook);
			}
		});
	}
	
	
	
	@After
	public void tearDown(){
		applicationContext.close();
		
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testBookCopyCreation(){
		assertNotNull(testCopy);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testParameterCretion(){
		assertTrue(testCopy.getBook().equals(testBook));
		assertTrue(testCopy.getState().equals(BookCopy.STATE_FREE));
		assertTrue(testCopy.getBookRate().equals(BookCopy.BOOK_RATE_NORMAL));
	}
	
	@Test
    @Transactional(readOnly = true)
    public void testBookCopyDAOEmpty(){
    	assertTrue(dao.findAll().isEmpty());
    }

	@Test
    @Transactional
    public void testBookCopyDAOSaveOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testCopy);
				List <BookCopy> results = dao.findAll();
				assertFalse(results.isEmpty());
				assertTrue(results.size() == 1);
				assertTrue(results.contains(testCopy));
				dao.delete(testCopy);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOSaveMany(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy copy1 = new BookCopy();
				BookCopy copy2 = new BookCopy();
				BookCopy copy3 = new BookCopy();
				copy1.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy2.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy3.setBookRate(BookCopy.BOOK_RATE_GOOD);
				dao.save(copy1);
				dao.save(copy2);
				dao.save(copy3);
				List <BookCopy> results = dao.findAll();
				assertTrue(results.size() == 3);
				assertTrue(results.contains(copy1) && results.contains(copy2) && results.contains(copy3));
				dao.delete(copy1);
				dao.delete(copy2);
				dao.delete(copy3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOSaveAndSearch(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testCopy);
				BookCopy searchedCopy = dao.findById(testCopy.getId());
				assertTrue(testCopy.equals(searchedCopy));
				dao.delete(testCopy);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOSaveAndDelete(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testCopy);
				dao.delete(testCopy);
				assertTrue(dao.findAll().isEmpty());
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOSaveManyAndDeleteOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy otherCopy = new BookCopy();
				otherCopy.setBookRate(BookCopy.BOOK_RATE_GOOD);
				dao.save(testCopy);
				dao.save(otherCopy);
				dao.delete(testCopy);
				assertNull(dao.findById(testCopy.getId()));
				assertFalse(dao.findAll().isEmpty());
				assertTrue(dao.findAll().contains(otherCopy));
				dao.delete(otherCopy);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOSaveAndUpdate(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testCopy);
				BookCopy searchedCopy = dao.findById(testCopy.getId());
				searchedCopy.setBookRate(BookCopy.BOOK_RATE_BAD);
				dao.update(searchedCopy);
				BookCopy updatedCopy = dao.findById(testCopy.getId());
				assertTrue(updatedCopy.getBookRate().equals(BookCopy.BOOK_RATE_BAD));
				dao.delete(testCopy);
			}
		});
	}
		
	@Test
    @Transactional
    public void testBookCopyDAOFreeByBookWhenOnlyOneFree(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy copy1 = new BookCopy();
				BookCopy copy2 = new BookCopy();
				BookCopy copy3 = new BookCopy();
				copy1.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy2.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy3.setBookRate(BookCopy.BOOK_RATE_GOOD);
				Book book1 = new Book();
				Book book2 = new Book();
				copy1.setBook(book1);
				copy2.setBook(book1);
				copy3.setBook(book2);
				copy1.changeToFree();
				copy2.changeToLoaned();
				copy3.changeToFree();
				
				
				bookDao.save(book1);
				bookDao.save(book2);
				dao.save(copy1);
				dao.save(copy2);
				dao.save(copy3);
				
				BookCopy searchBookCopy1 = dao.findFreeCopy(book1.getId());
				assertNotNull(searchBookCopy1);
				assertTrue(searchBookCopy1.equals(copy1) );
				
				BookCopy searchBookCopy2 = dao.findFreeCopy(book2.getId());
				assertNotNull(searchBookCopy2);
				assertTrue(searchBookCopy2.equals(copy3));
				
				dao.delete(copy1);
				dao.delete(copy2);
				dao.delete(copy3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOFreeByBookWhenManyFree(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy copy1 = new BookCopy();
				BookCopy copy2 = new BookCopy();
				BookCopy copy3 = new BookCopy();
				BookCopy copy4 = new BookCopy();
				copy1.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy2.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy3.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy4.setBookRate(BookCopy.BOOK_RATE_GOOD);
				Book book1 = new Book();
				copy1.setBook(book1);
				copy2.setBook(book1);
				copy3.setBook(book1);
				copy4.setBook(book1);
				copy1.changeToFree();
				copy2.changeToFree();
				copy3.changeToFree();
				copy4.changeToLoaned();
				
				bookDao.save(book1);
				dao.save(copy1);
				dao.save(copy2);
				dao.save(copy3);
				dao.save(copy4);
				
				BookCopy searchBookCopy = dao.findFreeCopy(book1.getId());
				assertNotNull(searchBookCopy);
				assertTrue(searchBookCopy.equals(copy1) || searchBookCopy.equals(copy2) || searchBookCopy.equals(copy3));
				
				dao.delete(copy1);
				dao.delete(copy2);
				dao.delete(copy3);
				dao.delete(copy4);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOFreeByBookWhenAllLoaned(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy copy1 = new BookCopy();
				BookCopy copy2 = new BookCopy();
				BookCopy copy3 = new BookCopy();
				copy1.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy2.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy3.setBookRate(BookCopy.BOOK_RATE_GOOD);
				Book book1 = new Book();
				copy1.setBook(book1);
				copy2.setBook(book1);
				copy3.setBook(book1);
				copy1.changeToLoaned();
				copy2.changeToLoaned();
				copy3.changeToLoaned();
				
				bookDao.save(book1);
				dao.save(copy1);
				dao.save(copy2);
				dao.save(copy3);
				
				BookCopy searchBookCopy = dao.findFreeCopy(book1.getId());
				assertNull(searchBookCopy);
				
				dao.delete(copy1);
				dao.delete(copy2);
				dao.delete(copy3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOFreeByBookWhenNoCopyForBook(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy copy1 = new BookCopy();
				BookCopy copy2 = new BookCopy();
				BookCopy copy3 = new BookCopy();
				copy1.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy2.setBookRate(BookCopy.BOOK_RATE_GOOD);
				copy3.setBookRate(BookCopy.BOOK_RATE_GOOD);
				Book book1 = new Book();
				Book book2 = new Book();
				Book book3 = new Book();
				copy1.setBook(book1);
				copy2.setBook(book1);
				copy3.setBook(book2);
				copy1.changeToFree();
				copy2.changeToFree();
				copy3.changeToFree();
				
				bookDao.save(book1);
				bookDao.save(book2);
				bookDao.save(book3);
				dao.save(copy1);
				dao.save(copy2);
				dao.save(copy3);
				
				BookCopy searchBookCopy = dao.findFreeCopy(book3.getId());
				assertNull(searchBookCopy);
				
				dao.delete(copy1);
				dao.delete(copy2);
				dao.delete(copy3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCopyDAOFreeByBookChangeState(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCopy copy = new BookCopy();
				copy.setBookRate(BookCopy.BOOK_RATE_GOOD);
				Book book = new Book();
				copy.setBook(book);
				copy.changeToFree();
				
				bookDao.save(book);
				dao.save(copy);
				
				BookCopy searchBookCopy = dao.findFreeCopy(book.getId());
				assertTrue(searchBookCopy.equals(copy));
				copy.changeToLoaned();
				dao.update(copy);
				assertNull(dao.findFreeCopy(book.getId()));
				copy.changeToFree();
				dao.update(copy);
				assertNotNull(dao.findFreeCopy(book.getId()));
				
				dao.delete(copy);
			}
		});
	}
}
