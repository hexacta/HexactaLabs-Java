package ar.com.hexacta.tpl.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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
import ar.com.hexacta.tpl.persistence.dao.LoanDAO;
import ar.com.hexacta.tpl.persistence.dao.UserDAO;

public class LoanTest {
	
	private static Book testBook = new Book();
	private static BookCopy testCopy = new BookCopy();
	private static User testUser = new User("user", "password");
	private Date initDate = new Date();
	private Date endDate = new Date();
	private Loan testLoan;
	private LoanDAO dao;
	private BookDAO bookDao;
	private BookCopyDAO copyDao;
	private UserDAO userDao;
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	
	@Before
	public void setUp(){
		testCopy.setBook(testBook);
		testCopy.setBookRate(BookCopy.BOOK_RATE_GOOD);
		testLoan = new Loan (testUser, testCopy, initDate, endDate);
		
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		dao = (LoanDAO) applicationContext.getBean(LoanDAO.class);
		bookDao = (BookDAO) applicationContext.getBean(BookDAO.class);
		copyDao = (BookCopyDAO) applicationContext.getBean(BookCopyDAO.class);
		userDao = (UserDAO) applicationContext.getBean(UserDAO.class);
		
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				bookDao.saveOrUpdate(testBook);
				copyDao.saveOrUpdate(testCopy);
				userDao.saveOrUpdate(testUser);
			}
		});
			
		
		
	}
	
	@After
	public void tearDown(){
		applicationContext.close();
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testLoanCreation(){
		assertNotNull(testLoan);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testParameterCretion(){
		assertTrue(testLoan.getBook() == testCopy);
		assertTrue(testLoan.getFromDate() == initDate);
		assertTrue(testLoan.getToDate() == endDate);
		assertTrue(testLoan.getUser() == testUser);
	}
	
	@Test
    @Transactional(readOnly = true)
    public void testLoanDAOEmpty(){
    	assertTrue(dao.findAll().isEmpty());
    }

	@Test
    @Transactional
    public void testLoanDAOSaveOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testLoan);
				List <Loan> results = dao.findAll();
				assertFalse(results.isEmpty());
				assertTrue(results.size() == 1);
				assertTrue(results.contains(testLoan));
				dao.delete(testLoan);
			}
		});
	}
	
	@Test
    @Transactional
    public void testLoanDAOSaveMany(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Loan loan1 = new Loan();
				Loan loan2 = new Loan();
				Loan loan3 = new Loan();
				dao.save(loan1);
				dao.save(loan2);
				dao.save(loan3);
				List <Loan> results = dao.findAll();
				assertTrue(results.size() == 3);
				assertTrue(results.contains(loan1) && results.contains(loan2) && results.contains(loan3));
				dao.delete(loan1);
				dao.delete(loan2);
				dao.delete(loan3);
				copyDao.delete(testCopy);
			}
		});
	}
	
	@Test
    @Transactional
    public void testLoanDAOSaveAndSearch(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testLoan);
				Loan searchedLoan = dao.findById(testLoan.getId());
				assertTrue(testLoan == searchedLoan);
				dao.delete(testLoan);
			}
		});
	}
	
	@Test
    @Transactional
    public void testLoanDAOSaveAndDelete(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testLoan);
				dao.delete(testLoan);
				assertTrue(dao.findAll().isEmpty());
			}
		});
	}
	
	@Test
    @Transactional
    public void testLoanDAOSaveManyAndDeleteOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Loan otherLoan = new Loan();
				dao.save(testLoan);
				dao.save(otherLoan);
				dao.delete(testLoan);
				assertNull(dao.findById(testLoan.getId()));
				assertFalse(dao.findAll().isEmpty());
				assertTrue(dao.findAll().contains(otherLoan));
				dao.delete(otherLoan);
			}
		});
	}
	
	@Test
    @Transactional
    public void testLoanDAOSaveAndUpdate(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testLoan);
				Date otherDate = new Date(50000);
				Loan searchedLoan = dao.findById(testLoan.getId());
				searchedLoan.setToDate(otherDate);
				dao.update(searchedLoan);
				Loan updatedLoan = dao.findById(testLoan.getId());
				assertTrue(updatedLoan.getToDate() == otherDate);
				dao.delete(testLoan);
			}
		});
	}
	/*
	@Test
    @Transactional
    public void testLoanDAOFindByUser(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testLoan);
				List<Loan> results = dao.findByUser(testUser.getUsername());
				assertFalse(results.isEmpty());
				assertTrue(results.contains(testLoan));
			}
		});
	}

	@Test
    @Transactional
    public void testLoanDAOFindByBook(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testLoan);
				List<Loan> results = dao.findByBookId(testBook.getId());
				assertFalse(results.isEmpty());
				assertTrue(results.contains(testLoan));
			}
		});
	}*/
}
