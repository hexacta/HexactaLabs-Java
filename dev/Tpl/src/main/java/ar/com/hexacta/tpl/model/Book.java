package ar.com.hexacta.tpl.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "BOOKS")
public class Book implements Serializable {
	private static final long serialVersionUID = 604529088687479075L;

	@Id
	@Column(name = "BOOK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	@Column(name = "VERSION")
	private Long version;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PUBLISHER")
	private String publisher;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "ENABLED")
	private Boolean enabled;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BookCategory> bookCategories;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<BookCopy> bookCopies;

	public Book() {
		super();
		enabled = Boolean.TRUE;
	}

	public Book(final String name) {
		this();
		this.name = name;
	}

	public Book(final String aName, final String aDescription,
			final String aPublisher, final String aCountry, final String isbn,
			final Set<BookCategory> bookCategories,
			final Set<BookCopy> bookCopies, final Set<Comment> bookComments) {
		this(aName);
		description = aDescription;
		publisher = aPublisher;
		country = aCountry;
		this.bookCategories = bookCategories;
		this.bookCopies = bookCopies;
		this.isbn = isbn;
	}

	public Set<BookCategory> getBookCategories() {
		return bookCategories;
	}

	public String getDescription() {
		return description;
	}

	@JsonIgnore
	public Set<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public String getName() {
		return name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setBookCategories(final Set<BookCategory> bookCategories) {
		this.bookCategories = bookCategories;
	}

	public void setBookCopies(final Set<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPublisher(final String publisher) {
		this.publisher = publisher;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setEnabled(final Boolean enable) {
		enabled = enable;
	}

	@JsonIgnore
	public boolean getEnabled() {
		return enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(final String isbn) {
		this.isbn = isbn;
	}

}