package ar.com.hexacta.tpl.model;

import static org.junit.Assert.*;

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

import ar.com.hexacta.tpl.persistence.dao.UserDAO;

public class UserTest {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email@email.com";
	
	private User testUser;
	private UserDAO dao;
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	public UserTest(){
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		dao = (UserDAO) applicationContext.getBean(UserDAO.class);
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				List<User> users = dao.findAll();
				for (User user : users){
					dao.delete(user);
				}
			}
		});
	}
	
	@Before
	public void setUp(){
		testUser = new User(USERNAME, PASSWORD, EMAIL);
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		dao = (UserDAO) applicationContext.getBean(UserDAO.class);
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		
	}
	
	@After
	public void tearDown(){
		applicationContext.close();
		
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testUserCreation(){
		assertNotNull(testUser);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testParameterCretion(){
		assertTrue(testUser.getUsername().equals(USERNAME));
		assertTrue(testUser.getPassword().equals(PASSWORD));
		assertTrue(testUser.getEmail().equals(EMAIL));
	}
	
	@Test
    @Transactional(readOnly = true)
    public void testUserDAOEmpty(){
    	assertTrue(dao.findAll().isEmpty());
    }

	@Test
    @Transactional
    public void testUserDAOSaveOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testUser);
				List <User> results = dao.findAll();
				assertFalse(results.isEmpty());
				assertTrue(results.size() == 1);
				assertTrue(results.contains(testUser));
				dao.delete(testUser);
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveMany(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				User user1 = new User();
				User user2 = new User();
				User user3 = new User();
				user1.setEnabled(true);
				user2.setEnabled(true);
				user3.setEnabled(true);
				dao.save(user1);
				dao.save(user2);
				dao.save(user3);
				List <User> results = dao.findAll();
				assertTrue(results.size() == 3);
				assertTrue(results.contains(user1) && results.contains(user2) && results.contains(user3));
				dao.delete(user1);
				dao.delete(user2);
				dao.delete(user3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveAndSearch(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testUser);
				User searchedUser = dao.findByUser(testUser.getUsername());
				assertTrue(testUser.equals(searchedUser));
				searchedUser = dao.findById(testUser.getId());
				assertTrue(testUser.equals(searchedUser));
				dao.delete(testUser);
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveAndDelete(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testUser);
				dao.deleteById(testUser.getId());
				assertTrue(dao.findAll().isEmpty());
				assertNull(dao.findById(testUser.getId()));
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveManyAndDeleteOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				User otherUser = new User();
				otherUser.setEnabled(true);
				dao.save(testUser);
				dao.save(otherUser);
				dao.delete(testUser);
				assertNull(dao.findByUser(testUser.getUsername()));
				assertFalse(dao.findAll().isEmpty());
				assertTrue(dao.findAll().contains(otherUser));
				dao.delete(otherUser);
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveDisabled(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				User otherUser = new User();
				otherUser.setEnabled(false);
				dao.save(otherUser);
				assertTrue(dao.findAll().isEmpty());
				dao.delete(otherUser);
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveAndUpdate(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testUser);
				User searchedUser = dao.findByUser(testUser.getUsername());
				searchedUser.setPassword("password2");
				dao.update(searchedUser);
				User updatedUser = dao.findByUser(testUser.getUsername());
				assertTrue(updatedUser.getPassword().equals("password2"));
				dao.delete(testUser);
			}
		});
	}
	
	@Test
    @Transactional
    public void testUserDAOSaveAndUpdateUsername(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testUser);
				User searchedUser = dao.findByUser(testUser.getUsername());
				searchedUser.setUsername("username2");
				dao.update(searchedUser);
				User updatedUser = dao.findByUser("username");
				assertNull(updatedUser);
				updatedUser = dao.findByUser("username2");
				assertNotNull(updatedUser);
				dao.delete(testUser);
			}
		});
	}
}
