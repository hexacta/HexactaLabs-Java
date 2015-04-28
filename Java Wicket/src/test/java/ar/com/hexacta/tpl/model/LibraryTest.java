package ar.com.hexacta.tpl.model;

import junit.framework.Assert;

import org.junit.Test;

import ar.com.hexacta.tpl.model.builder.BookBuilder;
import ar.com.hexacta.tpl.model.builder.BookCopyBuilder;
import ar.com.hexacta.tpl.model.builder.UserBuilder;
import ar.com.hexacta.tpl.model.exceptions.MaximumLoansException;
import ar.com.hexacta.tpl.model.exceptions.NoBookCopyException;

/**
 * TODO: description
 */
public class LibraryTest {

    @Test
    public void loanBookToUser() {
        // Fixture
        User user = new UserBuilder().build();
        BookCopy copy = new BookCopyBuilder().withState(BookCopy.STATE_FREE).build();
        Book book = new BookBuilder().withBookCopy(copy).build();
        Library library = new Library();

        // Exercise
        Loan loan = library.loan(book, user);

        // Assert
        Assert.assertEquals(copy, loan.getBook());
    }

    public void loanBookToUserWith2TotalCopies() {
        // Fixture
        User user = new UserBuilder().build();
        BookCopy copyLoaned = new BookCopyBuilder().withState(BookCopy.STATE_LOANED).build();
        BookCopy copyFree = new BookCopyBuilder().withState(BookCopy.STATE_FREE).build();
        Book book = new BookBuilder().withBookCopy(copyLoaned, copyFree).build();
        Library library = new Library();
        // Exercise
        Loan loan = library.loan(book, user);

        // Assert
        Assert.assertEquals(copyFree, loan.getBook());
    }

    @Test(expected = NoBookCopyException.class)
    public void noLoanBookToUserBecauseMaximounUserLoans() {
        // Fixture
        User user = new UserBuilder().build();
        BookCopy copy = new BookCopyBuilder().withState(BookCopy.STATE_LOANED).build();
        Book book = new BookBuilder().withBookCopy(copy).build();
        Library library = new Library();

        // Exercise
        library.loan(book, user);
        Assert.fail();

    }

    @Test(expected = MaximumLoansException.class)
    public void noLoanBookToUserNoCopiesAvailable() {
        // Fixture
        User user = new UserBuilder().build();
        BookCopy copy1 = new BookCopyBuilder().withCode("").withState(BookCopy.STATE_FREE).build();
        BookCopy copy2 = new BookCopyBuilder().withState(BookCopy.STATE_LOANED).build();
        BookCopy copy3 = new BookCopyBuilder().withState(BookCopy.STATE_FREE).build();
        Book book = new BookBuilder().withBookCopy(copy1, copy2, copy3).build();
        Library library = new Library();
        library.loan(book, user);
        library.loan(book, user);

        // Exercise
        library.loan(book, user);
        Assert.fail();

    }

}
