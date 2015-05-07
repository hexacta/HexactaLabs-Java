/**
 * 
 */
package ar.com.hexacta.tpl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author clopez
 * 
 */
@Entity
@Table(name = "loans")
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    private User user;

    @ManyToOne
    private BookCopy book;

    @Column(name = "FROM_DATE")
    private Date fromDate;

    @Column(name = "TO_DATE")
    private Date toDate;

    protected Loan() {
        super();
    }

    public Loan(final User user, final BookCopy book, final Date fromDate, final Date toDate) {
        super();
        this.user = user;
        this.book = book;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public User getUser() {
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

}
