package ar.com.hexacta.tpl.model;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.DateTime;

import ar.com.hexacta.tpl.model.exceptions.MaximumLoansException;
import ar.com.hexacta.tpl.model.exceptions.NoBookCopyException;

public class User extends Entity {
    private static final long serialVersionUID = 1588265571172283477L;

    private String username;

    private String email;

    private Collection<Loan> currentLoans = new ArrayList<Loan>();

    public User() {
        super();
    }

    public User(final String aUserName, final String aPassword) {
        this();
        username = aUserName;
        email = aPassword;
    }

    private void checkMaximumLoans() {
        if (currentLoans.size() == 2) {
            throw new MaximumLoansException();
        }

    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Loan loan(final Book book) {
        this.checkMaximumLoans();

        BookCopy copy = book.getFreeBookCopy();
        if (copy == null) {
            throw new NoBookCopyException(book);
        }

        DateTime now = new DateTime();
        Loan loan = new Loan(this, copy, now.toDate(), now.plusDays(15).toDate());
        copy.changeToLoaned();
        currentLoans.add(loan);
        return loan;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setUsername(final String name) {
        username = name;
    }

}
