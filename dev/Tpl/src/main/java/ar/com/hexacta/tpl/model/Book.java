package ar.com.hexacta.tpl.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    private static final long serialVersionUID = 604529088687479075L;

    @Id
    @Column(name = "BOOK_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private BookGenre genre;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BookCategory> bookCategories;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BookCopy> bookCopies;

    // Needed for "optimistic model" in Oracle databases
    @Version
    @Column(name = "VERSION")
    private Long version;

    public Book() {
        super();
        enabled = Boolean.TRUE;
    }

    public Book(final String aTitle) {
        this();
        title = aTitle;
    }

    public Book(final String aTitle, final String aDescription, final String aPublisher, final BookGenre aBookGenre,
            final Set<BookCategory> bookCategories, final Set<BookCopy> bookCopies) {
        this(aTitle);
        description = aDescription;
        publisher = aPublisher;
        genre = aBookGenre;
        this.bookCategories = bookCategories;
        this.bookCopies = bookCopies;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String aTitle) {
        title = aTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(final BookGenre genre) {
        this.genre = genre;
    }

    @JsonIgnore
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enable) {
        enabled = enable;
    }

    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(final Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    @JsonIgnore
    public Set<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(final Set<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }
}