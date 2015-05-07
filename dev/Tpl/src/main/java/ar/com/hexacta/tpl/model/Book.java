package ar.com.hexacta.tpl.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private static final long serialVersionUID = 604529088687479075L;
    @Id
    @Column(name = "BOOK_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Version
    @Column(name = "VERSION")
    private Long version;
    
    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PUBLISHER")
    private String publisher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BookCategory> bookCategories = new HashSet<BookCategory>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BookCopy> bookCopies = new HashSet<BookCopy>(0);

    // Hibernate needs
    public Book() {
        super();
    }

    public Book(final String name) {
        super();
        this.title = name;
    }

    public Book(final String aName, final String aDescription, final String aPublisher,
            final Set<BookCategory> bookCategories, final Set<BookCopy> aBookCopies) {
        super();
        title = aName;
        description = aDescription;
        publisher = aPublisher;
        this.bookCategories = bookCategories;
        bookCopies = aBookCopies;

    }

    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public String getDescription() {
        return description;
    }

    public BookCopy getFreeBookCopy() {
        for (BookCopy bookCopy : bookCopies) {
            if (bookCopy.getState().equals(BookCopy.STATE_FREE)) {
                return bookCopy;
            }
        }
        return null;
    }

    public String getName() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setBookCategories(final Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.title = name;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
