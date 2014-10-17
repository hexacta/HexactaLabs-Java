package ar.com.hexacta.tpl.persistence.dao;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.BookGenre;
import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.model.builder.BookBuilder;
import ar.com.hexacta.tpl.model.builder.BookCategoryBuilder;
import ar.com.hexacta.tpl.model.builder.BookCopyBuilder;
import ar.com.hexacta.tpl.persistence.repository.BookCategoryRepository;
import ar.com.hexacta.tpl.persistence.repository.BookCopyRepository;
import ar.com.hexacta.tpl.persistence.repository.BookRepository;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;
import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;
import ar.com.hexacta.tpl.persistence.repository.LoanRepository;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;

@Repository
public class DataInitDAO implements DataInitRepository {

    private static final Logger LOG = LogManager.getLogger(DataInitDAO.class.getName());

    @Autowired
    private BookRepository bookDAO;

    @Autowired
    private CommentRepository commentDAO;

    @Autowired
    private BookCategoryRepository bookCategoryDAO;

    @Autowired
    private BookCopyRepository bookCopyDAO;

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private LoanRepository loanDAO;

    private void createData() {
        // Categorias
        BookCategory eBookCategory = new BookCategoryBuilder().withName("ebook")
                .withDescription("Libro en formato electronico").build();
        bookCategoryDAO.saveOrUpdate(eBookCategory);

        BookCategory physicalCategory = new BookCategoryBuilder().withName("fisico")
                .withDescription("Libro en formato fisico").build();
        bookCategoryDAO.saveOrUpdate(physicalCategory);

        // Copias
        BookCopy bookCopy1 = new BookCopyBuilder().withCode("1").withState(BookCopy.STATE_FREE).build();
        bookCopyDAO.saveOrUpdate(bookCopy1);
        BookCopy bookCopy2 = new BookCopyBuilder().withCode("2").withState(BookCopy.STATE_LOANED).build();
        bookCopyDAO.saveOrUpdate(bookCopy2);
        BookCopy bookCopy3 = new BookCopyBuilder().withCode("3").build();
        bookCopyDAO.saveOrUpdate(bookCopy3);
        BookCopy bookCopy4 = new BookCopyBuilder().withCode("4").build();
        bookCopyDAO.saveOrUpdate(bookCopy4);
        BookCopy bookCopy5 = new BookCopyBuilder().withCode("5").build();
        bookCopyDAO.saveOrUpdate(bookCopy5);

        // Libros

        Book book1 = new BookBuilder().withTitle("El principito")
                .withDescription("Best-seller del escritor frances Antoine de Saint-Exupery.")
                .withPublisher("Editorial Planeta").withCategory(physicalCategory).withGenre(BookGenre.FANTASY)
                .withBookCopy(bookCopy1, bookCopy2).build();
        bookDAO.saveOrUpdate(book1);
        LOG.info("Created book " + book1.getId());
        Book book2 = new BookBuilder().withTitle("El codigo Da Vinci")
                .withDescription("Novela de misterio del escritor Dan Brown.").withPublisher("Editorial Estrada")
                .withGenre(BookGenre.MYSTERY).withCategory(physicalCategory).withBookCopy(bookCopy3).build();
        bookDAO.saveOrUpdate(book2);
        LOG.info("Created book " + book2.getId());

        Book book3 = new BookBuilder().withTitle("El Hobbit").withDescription("Novela fantastica de J. R. R. Tolkien.")
                .withPublisher("Editorial Atlantida").withGenre(BookGenre.FABLE).withCategory(eBookCategory)
                .withBookCopy(bookCopy4).build();
        bookDAO.saveOrUpdate(book3);
        LOG.info("Created book " + book3.getId());

        Book book4 = new BookBuilder().withTitle("Ender's Game").withDescription("Novela de ciencia ficción de Scott")
                .withPublisher("Editorial pepin").withCategory(physicalCategory).withBookCopy(bookCopy5).build();
        bookDAO.saveOrUpdate(book4);
        LOG.info("Created book " + book4.getId());

        // Users
        User userAdmin = new User("admin", "admin", "admin@hexacta.com");
        userDAO.save(userAdmin);

        User user2 = new User("edu", "malvino", "emalvino@hexacta.com", false);
        userDAO.save(user2);

        Loan loan = new Loan(userAdmin, bookCopy1, new Date(), new Date());
        loanDAO.saveOrUpdate(loan);
        bookDAO.saveOrUpdate(book1);
    }

    @Override
    public boolean initializeData() {
        boolean success = true;
        try {
            createData();
        } catch (Exception e) {
            LOG.error("Error al inicializar los datos.");
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
}