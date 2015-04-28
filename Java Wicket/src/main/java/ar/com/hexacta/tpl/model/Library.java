package ar.com.hexacta.tpl.model;

/**
 * TODO: description
 */
public class Library extends Entity {

    private static final long serialVersionUID = 1L;

    public Loan loan(final Book book, final User user) {
        return user.loan(book);
    }

}
