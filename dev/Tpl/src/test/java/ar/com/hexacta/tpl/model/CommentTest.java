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

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.dao.BookDAO;
import ar.com.hexacta.tpl.persistence.dao.CommentDAO;

public class CommentTest {
	
	private String testUser;
	private String testBody;
	private Book testBook;
	private Comment testComment;
	private CommentDAO dao;
	private BookDAO bookDao;
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	@Before
	public void setup(){
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
        	
        dao = (CommentDAO) applicationContext.getBean(CommentDAO.class);
        bookDao = (BookDAO) applicationContext.getBean(BookDAO.class);
        txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
        
		testUser = "test@mail.com";
        testBody = "Test comment body";
        testBook = new Book("Test Book");
        testComment = new Comment(testBook, testUser, testBody);
        
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
	public void testCommentCreation(){
		assertNotNull(testComment);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testParameterCretion(){
		assertTrue(testComment.getBook() == testBook);
		assertTrue(testComment.getBody().equals(testBody));
		assertTrue(testComment.getUser().equals(testUser));
	}
	
	@Test
    @Transactional(readOnly = true)
    public void testCommentDAOEmpty(){
    	assertTrue(dao.findAll().isEmpty());
    }

	@Test
    @Transactional
    public void testCommentDAOSaveOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testComment);
				List <Comment> results = dao.findAll();
				assertFalse(results.isEmpty());
				assertTrue(results.size() == 1);
				assertTrue(results.contains(testComment));
				dao.delete(testComment);
			}
		});
	}
	
	@Test
    @Transactional
    public void testCommentDAOSaveMany(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Comment comment1 = new Comment();
				Comment comment2 = new Comment();
				Comment comment3 = new Comment();
				dao.save(comment1);
				dao.save(comment2);
				dao.save(comment3);
				List <Comment> results = dao.findAll();
				assertTrue(results.size() == 3);
				assertTrue(results.contains(comment1) && results.contains(comment2) && results.contains(comment3));
				dao.delete(comment1);
				dao.delete(comment2);
				dao.delete(comment3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testCommentDAOSaveAndSearch(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testComment);
				Comment searchedComment = dao.findById(testComment.getId());
				assertTrue(testComment == searchedComment);
				dao.delete(testComment);
			}
		});
	}
	
	@Test
    @Transactional
    public void testCommentDAOSaveAndDelete(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testComment);
				dao.delete(testComment);
				assertTrue(dao.findAll().isEmpty());
			}
		});
	}
	
	@Test
    @Transactional
    public void testCommentDAOSaveManyAndDeleteOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Comment otherComment = new Comment();
				dao.save(testComment);
				dao.save(otherComment);
				dao.delete(testComment);
				assertNull(dao.findById(testComment.getId()));
				assertFalse(dao.findAll().isEmpty());
				assertTrue(dao.findAll().contains(otherComment));
				dao.delete(otherComment);
			}
		});
	}
	
	@Test
    @Transactional
    public void testCommentDAOSaveAndUpdate(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testComment);
				Comment searchedComment = dao.findById(testComment.getId());
				searchedComment.setBody("new body");
				dao.update(searchedComment);
				Comment updatedComment = dao.findById(testComment.getId());
				assertTrue(updatedComment.getBody().equals("new body"));
				System.out.println(updatedComment.getBody());
				dao.delete(testComment);
			}
		});
	}
		
	@Test
    @Transactional
    public void testCommentDAOSaveAndUpdateBook(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testComment);
				Comment searchedComment = dao.findByBookId(testBook.getId()).get(0);
				Book newBook = new Book();
				bookDao.save(newBook);
				
				searchedComment.setBook(newBook);
				dao.update(searchedComment);
				assertTrue(dao.findByBookId(testBook.getId()).isEmpty());
				Comment updatedComment = dao.findByBookId(newBook.getId()).get(0);
				assertNotNull(updatedComment);
				dao.delete(testComment);
			}
		});
	}
	
	@Test
    @Transactional
    public void testCommentDAOSearchByBook(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Comment comment1 = new Comment();
				Comment comment2 = new Comment();
				Comment comment3 = new Comment();
				Book book1 = new Book();
				Book book2 = new Book();
				comment1.setBook(book1);
				comment2.setBook(book1);
				comment3.setBook(book2);
				
				bookDao.save(book1);
				bookDao.save(book2);
				dao.save(comment1);
				dao.save(comment2);
				dao.save(comment3);
				
				List<Comment> searchBook1 = dao.findByBookId(book1.getId());
				assertTrue(searchBook1.size() == 2);
				assertTrue(searchBook1.contains(comment1) && searchBook1.contains(comment2));
				
				List<Comment> searchBook2 = dao.findByBookId(book2.getId());
				assertTrue(searchBook2.size() == 1);
				assertTrue(searchBook2.contains(comment3));
				
				dao.delete(comment1);
				dao.delete(comment2);
				dao.delete(comment3);
			}
		});
	}
    
}
