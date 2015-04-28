/**
 * 
 */
package ar.com.hexacta.tpl.model;

/**
 * @author clopez
 * 
 */
public class BookCopy extends Entity {

    public static final String BOOK_RATE_BAD = "Bad";

    public static final String BOOK_RATE_NORMAL = "Normal";

    public static final String BOOK_RATE_GOOD = "Good";

    public static final String BOOK_RATE_VERY_GOOD = "Very good";

    public static final String STATE_LOANED = "Loaned";

    public static final String STATE_FREE = "Free";

    private static final long serialVersionUID = 1L;

    private String code = "";

    private String bookRate;

    private String state;

    public BookCopy() {

    }

    public BookCopy(final String code, final String bookRate, final String state) {
        super();
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
}
