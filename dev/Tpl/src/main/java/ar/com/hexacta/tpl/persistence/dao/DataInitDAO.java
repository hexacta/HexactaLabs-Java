package ar.com.hexacta.tpl.persistence.dao;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.model.builder.BookBuilder;
import ar.com.hexacta.tpl.model.builder.BookCategoryBuilder;
import ar.com.hexacta.tpl.model.builder.BookCopyBuilder;
import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;

@Repository
public class DataInitDAO implements DataInitRepository {

	private static final Logger LOG = Logger.getLogger(DataInitDAO.class
			.getName());

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private BookCategoryDAO bookCategoryDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GenericDAO genericDAO;

	@Transactional
	private void createData() {
		// Categorias
		BookCategory eBookCategory = new BookCategoryBuilder()
				.withName("ebook")
				.withDescription("Libro en formato electronico").buid();
		bookCategoryDAO.saveOrUpdate(eBookCategory);

		BookCategory physicalCategory = new BookCategoryBuilder()
				.withName("fisico").withDescription("Libro en formato fisico")
				.buid();
		bookCategoryDAO.saveOrUpdate(physicalCategory);

		// Copias
		BookCopy bookCopy1 = new BookCopyBuilder().withCode("1")
				.withState(BookCopy.STATE_FREE).build();
		BookCopy bookCopy2 = new BookCopyBuilder().withCode("2")
				.withState(BookCopy.STATE_LOANED).build();
		BookCopy bookCopy3 = new BookCopyBuilder().withCode("3").build();
		BookCopy bookCopy4 = new BookCopyBuilder().withCode("4").build();
		BookCopy bookCopy5 = new BookCopyBuilder().withCode("5").build();

		// Libros
		Book book1 = new BookBuilder()
				.withName("El principito")
				.withDescription(
						"Best-seller del escritor frances Antoine de Saint-Exupery.")
				.withPublisher("Editorial Planeta")
				.withCategory(physicalCategory).withCountry("EEUU")
				.withISBN("978-0-152-16415-7")
				.withBookCopy(bookCopy1, bookCopy2).build();
		bookDAO.saveOrUpdate(book1);
		LOG.info("Created book " + book1.getId());
		Book book2 = new BookBuilder().withName("El codigo Da Vinci")
				.withDescription("Novela de misterio del escritor Dan Brown.")

				.withPublisher("Editorial Estrada").withCountry("EEUU")
				.withISBN("84-95618-60-5").withCategory(physicalCategory)
				.withBookCopy(bookCopy3).build();

		bookDAO.saveOrUpdate(book2);
		LOG.info("Created book " + book2.getId());

		Book book3 = new BookBuilder().withName("El Hobbit")
				.withDescription("Novela fantastica de J. R. R. Tolkien.")
				.withPublisher("Editorial Atlantida")
				.withCountry("United Kingdom").withISBN("84-450-7037-1")
				.withCategory(eBookCategory).withBookCopy(bookCopy4).build();
		bookDAO.saveOrUpdate(book3);
		LOG.info("Created book " + book3.getId());

		Book book4 = new BookBuilder().withName("Ender's Game")
				.withDescription("Novela de ciencia ficción de Scott")
				.withPublisher("Editorial pepin")
				.withCategory(physicalCategory).withBookCopy(bookCopy5).build();
		bookDAO.saveOrUpdate(book4);
		LOG.info("Created book " + book4.getId());

		// Users
		User userAdmin = new User("admin", "admin", "admin@hexacta.com");
		userDAO.save(userAdmin);

		User user2 = new User("edu", "malvino", "emalvino@hexacta.com", false);
		userDAO.save(user2);

		Loan loan = new Loan(userAdmin, bookCopy1, new Date(), new Date());
		genericDAO.saveOrUpdate(loan);
		genericDAO.saveOrUpdate(book1);
	}

	@Override
	public boolean initializeData() {
		boolean success = true;
		try {
			createData();
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	public void setBookCategoryDAO(final BookCategoryDAO bookCategoryDAO) {
		this.bookCategoryDAO = bookCategoryDAO;
	}

	public void setBookDAO(final BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public void setCommentDAO(final CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}
}