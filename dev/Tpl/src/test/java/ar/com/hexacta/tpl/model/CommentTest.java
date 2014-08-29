package ar.com.hexacta.tpl.model;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
import ar.com.hexacta.tpl.persistence.dao.CommentDAO;

public class CommentTest {
	
	private String testUser;
	private String testBody;
	private Book testBook;
	private CommentDAO dao;
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	@Before
	public void setup(){
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
        
        dao = (CommentDAO) applicationContext.getBean(CommentDAO.class);
        txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		testUser = "test@mail.com";
        testBody = "Test comment body";
        testBook = new Book("Test Book");
	}
	
	@After
	public void tearDown(){
		applicationContext.close();
		dao = null;
	}
	
    @Test
    public void testCommentCreation() {
        Comment comment = new Comment();

        Assert.assertNotNull(comment);
    }

    @Test
    public void testParamCommentCreation() {

        Comment comment = new Comment(testBook, testUser, testBody);

        Assert.assertTrue(comment.getUser() == testUser);
        Assert.assertTrue(comment.getBody() == testBody);
        Assert.assertTrue(comment.getBook() == testBook);
        
    }
    
    @Test
    @Transactional(readOnly = true)
    public void testCommentDAOEmpty(){
    	Assert.assertTrue(dao.findAll().isEmpty());
    }

    
    @Test
    @Transactional
    public void testCommentDAOSaveOne() {
    	
    	TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
            	Comment comment = new Comment(testBook, testUser, testBody);
            	dao.save(comment);
            	List <Comment> testList = dao.findAll();
            	Assert.assertTrue(testList.size() == 1);
            	Assert.assertTrue(testList.contains(comment));
            	Assert.assertNotNull(dao.findById(1));
            }
        });
    }
    
    
    @Test
    @Transactional
    public void testCommentDAOSaveMany() {
    	Comment comment1 = new Comment(new Book(), "user", "the body");
    	Comment comment2 = new Comment(new Book(), "user", "body");
    	Comment comment3 = new Comment(new Book(), "user", "other body");
    	dao.save(comment1);
    	dao.save(comment2);
    	dao.save(comment3);
    	List <Comment> testList = dao.findAll(); 
    	Assert.assertTrue(testList.size() == 3);
    	Assert.assertTrue(testList.contains(comment1) && testList.contains(comment2) && testList.contains(comment3));
    }
    
    @Test
    @Transactional
    public void testCommentDAOSaveManyOfSameBook() {
    	Comment comment1 = new Comment(testBook, testUser, testBody);
    	Comment comment2 = new Comment(testBook, "user", "body");
    	Comment comment3 = new Comment(testBook, "user", "other body");
    	dao.save(comment1);
    	dao.save(comment2);
    	dao.save(comment3);
    	List <Comment> testList = dao.findByBookId(new Long(1)); 
    	Assert.assertTrue(testList.size() == 3);
    	Assert.assertTrue(testList.contains(comment1) && testList.contains(comment2) && testList.contains(comment3));
    }
    
    @Test
    @Transactional
    public void testCommentDAOSaveManyOfDifferentBooks() {
    	Comment comment1 = new Comment(testBook, testUser, testBody);
    	Comment comment2 = new Comment(testBook, "user", "body");
    	Comment comment3 = new Comment(new Book(), "user", "other body");
    	dao.save(comment1);
    	dao.save(comment2);
    	dao.save(comment3);
    	List <Comment> testList = dao.findByBookId(new Long (1)); 
    	Assert.assertTrue(testList.size() == 2);
    	Assert.assertTrue(testList.contains(comment1) && testList.contains(comment2) && !testList.contains(comment3));
    }
    
    @Test
    @Transactional
    public void testCommentDAOSaveAndUpdate(){
    	Comment comment = new Comment(testBook, testUser, "initial body");
    	dao.save(comment);
    	Long id = new Long(1);
    	comment = dao.findById(id);
    	comment.setBody("updated body");
    	dao.update(comment);
    	comment = dao.findById(id);
    	Assert.assertTrue(comment.getBody().equals("updated body"));    	
    }
    
    @Test
    @Transactional
    public void testCommentDAOSaveAndDelete(){
    	Comment comment = new Comment(testBook, testUser, "initial body");
    	dao.save(comment);
    	Long id = new Long(1);
    	dao.deleteById(id);
    	comment = dao.findById(id);
    	Assert.assertNull(comment);    
    }
    
    @Test
    @Transactional 
    public void testCommentDAOSaveManyAndDeleteOne(){
    	Comment comment = new Comment(testBook, testUser, "initial body");
    	Comment comment2 = new Comment(testBook, "user", "body");
    	Comment comment3 = new Comment(new Book(), "user", "other body");
    	dao.save(comment);
    	dao.save(comment2);
    	dao.save(comment3);
    	Long id = new Long(1);
    	dao.deleteById(id);
    	comment = dao.findById(id);
    	Assert.assertNull(comment);
    	Assert.assertTrue(dao.findAll().size() == 2);
    }
    
    
}
