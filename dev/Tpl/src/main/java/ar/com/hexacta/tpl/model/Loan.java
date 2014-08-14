/**
 *
 */
package ar.com.hexacta.tpl.model;

import java.io.Serializable;
import java.util.Date;

// @Entity
// @Table(name = "LOANS")
public class Loan extends Entidad implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * @Id
     * 
     * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
     * 
     * @Version private Long version;
     */
    // @Column(name = "USER")
    private String user;

    // @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    // @Column(name = "BOOK")
    private BookCopy book;

    // @Column(name = "FROM_DATE")
    private Date fromDate;

    // @Column(name = "TO_DATE")
    private Date toDate;

    public Loan() {
        super();
    }

    public Loan(final String user, final BookCopy book, final Date fromDate, final Date toDate) {
        super();
        this.user = user;
        this.book = book;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getUser() {
        return user;
    }

    public BookCopy getBook() {
        return book;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
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
