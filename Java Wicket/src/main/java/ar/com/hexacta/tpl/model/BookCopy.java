package ar.com.hexacta.tpl.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "book_copies")
public class BookCopy implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String BOOK_RATE_BAD = "Bad";

	public static final String BOOK_RATE_NORMAL = "Normal";

	public static final String BOOK_RATE_GOOD = "Good";

	public static final String BOOK_RATE_VERY_GOOD = "Very good";

	public static final String STATE_LOANED = "Loaned";

	public static final String STATE_FREE = "Free";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	@Column(name = "VERSION")
	private Long version;

	@ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Book.class)
	private Book book;

	@Column(name = "BOOK_RATE", nullable = false)
	private String bookRate;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ENABLED")
	private boolean enabled;

	public BookCopy() {
		super();
	}

	public BookCopy(final Book book, final String bookRate, final String state) {
		super();
		this.book = book;
		this.bookRate = bookRate;
		this.state = state;
		enabled = true;
		this.book = book;
	}

	public BookCopy(final Book book, final String bookRate, final String state,
			final boolean enabled) {
		this(book, bookRate, state);
		this.enabled = enabled;
	}

	public void changeToFree() {
		state = STATE_FREE;
	}

	public void changeToLoaned() {
		state = STATE_LOANED;
	}

	public String getBookRate() {
		return bookRate;
	}

	public String getState() {
		return state;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(final Book book) {
		this.book = book;
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

	public void setBookRate(final String bookRate) {
		this.bookRate = bookRate;
	}
}
