package ar.com.hexacta.tpl.model.builder;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCopy;

/**
 * TODO: description
 */
public class BookCopyBuilder {

    private String code = "defaultCode";

    private String bookRate = BookCopy.BOOK_RATE_GOOD;

    private String state = BookCopy.STATE_FREE;

    private boolean enabled = true;
    
    private Book book = new Book();
    		
    public BookCopy build() {
        return new BookCopy(code, bookRate, state, enabled, book);
    }

    public BookCopyBuilder withCode(final String aCode) {
        code = aCode;
        return this;
    }

    public BookCopyBuilder withState(final String aState) {
        state = aState;
        return this;
    }

    public BookCopyBuilder isEnabled(final boolean aEnabled) {
        enabled = aEnabled;
        return this;
    }

}
