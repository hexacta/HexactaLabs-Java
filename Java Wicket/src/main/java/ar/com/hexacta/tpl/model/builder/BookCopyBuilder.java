package ar.com.hexacta.tpl.model.builder;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCopy;

public class BookCopyBuilder {

	private Book book = new Book();

	private String bookRate = BookCopy.BOOK_RATE_GOOD;

	private String state = BookCopy.STATE_FREE;

	private boolean enabled = true;

	public BookCopy build() {
		return new BookCopy(book, bookRate, state, enabled);
	}

	public BookCopyBuilder withBook(final Book aBook) {
		book = aBook;
		return this;
	}

	public BookCopyBuilder withState(final String aState) {
		state = aState;
		return this;
	}

	public BookCopyBuilder withBookRate(final String aBookRate) {
		bookRate = aBookRate;
		return this;
	}

	public BookCopyBuilder isEnabled(final boolean aEnabled) {
		enabled = aEnabled;
		return this;
	}

}
