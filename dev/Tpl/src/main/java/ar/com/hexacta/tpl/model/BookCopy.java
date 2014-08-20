/**
 *
 */
package ar.com.hexacta.tpl.model;

import java.io.Serializable;

// @Entity
public class BookCopy extends Entidad implements Serializable {

    /*
     * @Id
     *
     * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
     *
     * @Version private Long version;
     */
    public static final String BOOK_RATE_BAD = "Bad";

    public static final String BOOK_RATE_NORMAL = "Normal";

    public static final String BOOK_RATE_GOOD = "Good";

    public static final String BOOK_RATE_VERY_GOOD = "Very good";

    public static final String STATE_LOANED = "Loaned";

    public static final String STATE_FREE = "Free";

    private static final long serialVersionUID = 1L;

    // @Column(name = "CODE")
    private String code = "";

    // @Column(name = "BOOK_RATE", unique=true, nullable=false)
    private String bookRate;

    // @Column(name = "STATE")
    private String state;

    public BookCopy() {
    	super();
    }

    public BookCopy(final String code, final String bookRate, final String state) {
        this();
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }
    /*
     * public Long getId() { return id; }
     *
     * public void setId(final Long id) { this.id = id; }
     *
     * public Long getVersion() { return version; }
     *
     * public void setVersion(final Long version) { this.version = version; }
     */
}
