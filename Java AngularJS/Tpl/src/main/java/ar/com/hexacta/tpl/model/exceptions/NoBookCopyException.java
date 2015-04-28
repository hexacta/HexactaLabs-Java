package ar.com.hexacta.tpl.model.exceptions;

import ar.com.hexacta.tpl.model.Book;

/**
 * Exception thrown then there's no book copy 
 */
public class NoBookCopyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Book book;

    public NoBookCopyException(final Book aBook) {
        book = aBook;
    }

    @Override
    public String getMessage() {
        return book.toString() + " ---- " + super.getMessage();
    }

}
