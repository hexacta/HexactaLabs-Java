package ar.com.hexacta.tpl.persistence.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.model.builder.BookBuilder;
import ar.com.hexacta.tpl.model.builder.BookCategoryBuilder;
import ar.com.hexacta.tpl.model.builder.BookCopyBuilder;
import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;

@Repository
public class DataInitDAO implements DataInitRepository {
	private static final Logger LOG = Logger.getLogger(DataInitDAO.class);


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

		// Comentarios
		/*Comment comment1 = new Comment("El principito", "yo@mail.com",
				"El mejor libro sobre dinosaurios!");
		commentDAO.save(comment1);
		LOG.info("Created comment " + comment1.getId());
		Comment comment2 = new Comment("Godzila", "sdlkfj@gmail.com",
				"No me gusto que no mencionen Hello Kitty");
		commentDAO.save(comment2);
		LOG.info("Created comment " + comment2.getId());
		Comment comment3 = new Comment("Jesus Reloaded", "religioso@islam.com",
				"Por Al-lah!");
		commentDAO.save(comment3);
		LOG.info("Created comment " + comment3.getId());
		Comment comment4 = new Comment("Book Not Found", "User Not Found",
				"Me hubiese gustado que el libro tuviese algo escrito");
		commentDAO.save(comment4);
		LOG.info("Created comment " + comment4.getId());*/

		// TESTING Comentarios
		/*LOG.info("\n <<<<<<<<<<<<< Retrieving Comments... >>>>>>>>>>>>>>>>>");

		Comment commentTest = commentDAO.findById(comment1.getId());
		LOG.info("Comment " + commentTest.getId() + "\n" + ">>Book: "
				+ commentTest.getBook() + "\n" + ">>User: "
				+ commentTest.getUser() + "\n" + ">>Body: "
				+ commentTest.getBody());
		commentTest = commentDAO.findById(comment2.getId());
		LOG.info("Comment " + commentTest.getId() + "\n" + ">>Book: "
				+ commentTest.getBook() + "\n" + ">>User: "
				+ commentTest.getUser() + "\n" + ">>Body: "
				+ commentTest.getBody());
		commentTest = commentDAO.findById(comment3.getId());
		LOG.info("Comment " + commentTest.getId() + "\n" + ">>Book: "
				+ commentTest.getBook() + "\n" + ">>User: "
				+ commentTest.getUser() + "\n" + ">>Body: "
				+ commentTest.getBody());
		commentTest = commentDAO.findById(comment4.getId());
		LOG.info("Comment " + commentTest.getId() + "\n" + ">>Book: "
				+ commentTest.getBook() + "\n" + ">>User: "
				+ commentTest.getUser() + "\n" + ">>Body: "
				+ commentTest.getBody());

		LOG.info("\n <<<<<<<<<<<<< END OF COMMENTS >>>>>>>>>>>>>>>>>");
*/
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

		// Libros
		Book book1 = new BookBuilder()
				.withName("El principito")
				.withDescription(
						"Best-seller del escritor frances Antoine de Saint-Exupery.")
				.withPublisher("Editorial Planeta")
				.withCategory(physicalCategory)
				.withBookCopy(bookCopy1, bookCopy2).build();
		bookDAO.saveOrUpdate(book1);
		LOG.info("Created book " + book1.getId());
		Book book2 = new BookBuilder().withName("El codigo Da Vinci")
				.withDescription("Novela de misterio del escritor Dan Brown.")
				.withPublisher("Editorial Estrada")
				.withCategory(physicalCategory).withBookCopy(bookCopy3).build();
		bookDAO.saveOrUpdate(book2);
		LOG.info("Created book " + book2.getId());

		Book book3 = new BookBuilder().withName("El Hobbit")
				.withDescription("Novela fantastica de J. R. R. Tolkien.")
				.withPublisher("Editorial Atlantida")
				.withCategory(eBookCategory).withBookCopy(bookCopy4).build();
		bookDAO.saveOrUpdate(book3);
		LOG.info("Created book " + book3.getId());

		// Prestamos
		
		
		//Users
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
