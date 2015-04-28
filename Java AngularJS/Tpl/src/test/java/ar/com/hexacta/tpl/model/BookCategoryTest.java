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

import ar.com.hexacta.tpl.persistence.dao.BookCategoryDAO;

public class BookCategoryTest {
	private static final String CATEGORY_DESCRIPTION = "a description";
	private static final String CATEGORY_NAME = "a name";
	
	private BookCategory testBookCategory;
	private BookCategoryDAO dao;
	private AbstractApplicationContext applicationContext; 
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager txManager;
	
	@Before
	public void setup(){
		testBookCategory = new BookCategory(CATEGORY_NAME, CATEGORY_DESCRIPTION);
		applicationContext = new ClassPathXmlApplicationContext("spring/spring-persistence-test.xml");
		dao = (BookCategoryDAO) applicationContext.getBean(BookCategoryDAO.class);
		txManager = (PlatformTransactionManager) applicationContext.getBean(PlatformTransactionManager.class);
		
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				List<BookCategory> categories = dao.findAll();
				for (BookCategory category : categories){
					dao.delete(category);
				}
			}
		});
	}
	
	@After
	public void tearDown(){
		applicationContext.close();
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testCategoryCreation(){
		assertNotNull(testBookCategory);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testParameterCretion(){
		assertTrue(testBookCategory.getName().equals(CATEGORY_NAME));
		assertTrue(testBookCategory.getDescription().equals(CATEGORY_DESCRIPTION));
	}
	
	@Test
    @Transactional(readOnly = true)
    public void testBookCategoryDAOEmpty(){
    	assertTrue(dao.findAll().isEmpty());
    }
	
	@Test
    @Transactional
    public void testBookCategoryDAOSaveOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBookCategory);
				List <BookCategory> results = dao.findAll();
				assertFalse(results.isEmpty());
				assertTrue(results.size() == 1);
				assertTrue(results.contains(testBookCategory));
				dao.delete(testBookCategory);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCategoryDAOSaveMany(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCategory category1 = new BookCategory();
				BookCategory category2 = new BookCategory();
				BookCategory category3 = new BookCategory();
				dao.save(category1);
				dao.save(category2);
				dao.save(category3);
				List <BookCategory> results = dao.findAll();
				assertTrue(results.size() == 3);
				assertTrue(results.contains(category1) && results.contains(category2) && results.contains(category3));
				dao.delete(category1);
				dao.delete(category2);
				dao.delete(category3);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCategoryDAOSaveAndSearch(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBookCategory);
				BookCategory searchedUser = dao.findById(testBookCategory.getId());
				assertTrue(testBookCategory.equals(searchedUser));
				dao.delete(testBookCategory);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCategoryDAOSaveAndDelete(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBookCategory);
				dao.delete(testBookCategory);
				assertTrue(dao.findAll().isEmpty());
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCategoryDAOSaveManyAndDeleteOne(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				BookCategory otherUser = new BookCategory();
				dao.save(testBookCategory);
				dao.save(otherUser);
				dao.delete(testBookCategory);
				assertNull(dao.findById(testBookCategory.getId()));
				assertFalse(dao.findAll().isEmpty());
				assertTrue(dao.findAll().contains(otherUser));
				dao.delete(otherUser);
			}
		});
	}
	
	@Test
    @Transactional
    public void testBookCategoryDAOSaveAndUpdate(){
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				dao.save(testBookCategory);
				BookCategory searchedUser = dao.findById(testBookCategory.getId());
				searchedUser.setName("another name");
				dao.update(searchedUser);
				BookCategory updatedCategory = dao.findById(testBookCategory.getId());
				assertTrue(updatedCategory.getName().equals("another name"));
				dao.delete(testBookCategory);
			}
		});
	}
	
}
