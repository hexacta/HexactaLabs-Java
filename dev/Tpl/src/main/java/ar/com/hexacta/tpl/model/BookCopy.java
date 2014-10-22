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

    @ManyToOne(optional = true)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Column(name = "CODE")
    private String code = "";

    @Column(name = "BOOK_RATE", nullable = false)
    private String bookRate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ENABLED")
    private boolean enabled;

    public BookCopy() {
        super();
    }

    public BookCopy(final String code, final String bookRate, final String state, final Book book) {
        super();
        this.code = code;
        this.bookRate = bookRate;
        this.state = state;
        enabled = true;
        this.book = book;
    }

    public BookCopy(final String code, final String bookRate, final String state, final boolean enabled, final Book book) {
        this(code, bookRate, state, book);
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

    public String getCode() {
        return code;
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

    public void setCode(final String code) {
        this.code = code;
    }

    public void setBookRate(final String bookRate) {
        this.bookRate = bookRate;
    }
}
