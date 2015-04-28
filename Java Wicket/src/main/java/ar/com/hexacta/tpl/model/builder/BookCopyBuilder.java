package ar.com.hexacta.tpl.model.builder;

import ar.com.hexacta.tpl.model.BookCopy;

/**
 * TODO: description
 */
public class BookCopyBuilder {

    private String code = "defaultCode";

    private String bookRate = BookCopy.BOOK_RATE_GOOD;

    private String state = BookCopy.STATE_FREE;

    public BookCopy build() {
        return new BookCopy(code, bookRate, state);
    }

    public BookCopyBuilder withCode(final String aCode) {
        code = aCode;
        return this;
    }

    public BookCopyBuilder withState(final String aState) {
        state = aState;
        return this;
    }

}
