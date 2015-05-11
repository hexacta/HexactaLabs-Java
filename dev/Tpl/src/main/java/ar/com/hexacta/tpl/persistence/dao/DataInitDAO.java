package ar.com.hexacta.tpl.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.model.builder.BookBuilder;
import ar.com.hexacta.tpl.model.builder.BookCategoryBuilder;
import ar.com.hexacta.tpl.model.builder.BookCopyBuilder;
import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;
@Repository
public class DataInitDAO implements DataInitRepository {
	@Autowired
    private UserDAO userDAO;
	
	@Autowired
    private BookDAO bookDAO;

	@Autowired
    private BookCategoryDAO bookCategoryDAO;

	@Autowired
    private GenericDAO genericDAO;

    private void createData() {

        // Users
        User user = new User("Peter", "Parker");
        userDAO.saveOrUpdate(user);
        User admin = new User("Tony", "Stark");
        userDAO.saveOrUpdate(admin);

        // Categorias
        BookCategory eBookCategory = new BookCategoryBuilder().withName("ebook")
                .withDescription("Libro en formato electronico").buid();
        bookCategoryDAO.saveOrUpdate(eBookCategory);

        BookCategory physicalCategory = new BookCategoryBuilder().withName("fisico")
                .withDescription("Libro en formato fisico").buid();
        bookCategoryDAO.saveOrUpdate(physicalCategory);

        // Copias
        BookCopy bookCopy1 = new BookCopyBuilder().withState(BookCopy.STATE_FREE).build();
        BookCopy bookCopy2 = new BookCopyBuilder().withState(BookCopy.STATE_LOANED).build();
        BookCopy bookCopy3 = new BookCopyBuilder().build();
        BookCopy bookCopy4 = new BookCopyBuilder().build();

        // Libros
        Book book1 = new BookBuilder().withName("El principito")
                .withDescription("Best-seller del escritor frances Antoine de Saint-Exupery.")
                .withPublisher("Editorial Planeta").withCategory(physicalCategory).withBookCopy(bookCopy1, bookCopy2)
                .build();
        bookDAO.saveOrUpdate(book1);

        Book book2 = new BookBuilder().withName("El codigo Da Vinci")
                .withDescription("Novela de misterio del escritor Dan Brown.").withPublisher("Editorial Estrada")
                .withCategory(physicalCategory).withBookCopy(bookCopy3).build();
        bookDAO.saveOrUpdate(book2);

        Book book3 = new BookBuilder().withName("El Hobbit").withDescription("Novela fantastica de J. R. R. Tolkien.")
                .withPublisher("Editorial Atlantida").withCategory(eBookCategory).withBookCopy(bookCopy4).build();
        bookDAO.saveOrUpdate(book3);
    }

    @Override
    @Transactional
    public boolean initializeData() {
        boolean success = true;
        try {
            this.createData();
        } catch (Exception e) {
        	e.printStackTrace();
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

    public void setGenericDAO(final GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
