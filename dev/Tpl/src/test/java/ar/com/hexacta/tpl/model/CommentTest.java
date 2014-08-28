package ar.com.hexacta.tpl.model;

import java.util.List;

import org.hibernate.FlushMode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.dao.CommentDAO;

public class CommentTest {
	
	private String testUser;
	private String testBody;
	private Book testBook;
	private CommentDAO dao;
	private AbstractApplicationContext applicationContext; 
	
	@Before
	public void setup(){
		System.out.println("HOLA");
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
        
        dao = (CommentDAO) applicationContext.getBean(CommentDAO.class);
		testUser = "test@mail.com";
        testBody = "Test comment body";
        testBook = new Book("Test Book");
	}
	
	@After
	public void tearDown(){
		applicationContext.close();
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
    	Comment comment = new Comment(testBook, testUser, testBody);
    	dao.save(comment);
    	List <Comment> testList = dao.findAll();
    	Assert.assertTrue(testList.size() == 1);
    	Assert.assertTrue(testList.contains(comment));
    	Assert.assertTrue(dao.findById(1) != null);
    }
    
    
    @Test
    @Transactional
    public void testCommentDAOSaveMany() {
    	Comment comment1 = new Comment(testBook, testUser, testBody);
    	Comment comment2 = new Comment(testBook, "user", "body");
    	Comment comment3 = new Comment(testBook, "user", "other body");
    	dao.save(comment1);
    	dao.save(comment2);
    	dao.save(comment3);
    	List <Comment> testList = dao.findAll(); 
    	Assert.assertTrue(testList.size() == 3);
    	Assert.assertTrue(testList.contains(comment1) && testList.contains(comment2) && testList.contains(comment3));
    }
    
    
}
