/**
 * 
 */
package ar.com.hexacta.tpl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author clopez
 * 
 */
@Entity
@Table(name = "book_copies")
public class BookCopy implements Serializable {

    public static final String BOOK_RATE_BAD = "Bad";

    public static final String BOOK_RATE_NORMAL = "Normal";

    public static final String BOOK_RATE_GOOD = "Good";

    public static final String BOOK_RATE_VERY_GOOD = "Very good";

    public static final String STATE_LOANED = "Loaned";

    public static final String STATE_FREE = "Free";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @ManyToOne(optional = true)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "BOOK_RATE", nullable = false)
    private String bookRate;

    @Column(name = "STATE")
    private String state;

    public BookCopy() {

    }

    public BookCopy(final String bookRate, final String state) {
        super();
        this.bookRate = bookRate;
        this.state = state;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setBookRate(String bookRate) {
		this.bookRate = bookRate;
	}

	public void setState(String state) {
		this.state = state;
	}
}
