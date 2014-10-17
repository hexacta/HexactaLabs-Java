package ar.com.hexacta.tpl.model.builder;

import java.util.HashSet;
import java.util.Set;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.BookGenre;

public class BookBuilder {

    private String title = "defaultTitle";

    private String description = "defaultDescription";

    private String publisher = "defaultPublisher";

    private BookGenre genre = BookGenre.HUMOR;

    private Set<BookCategory> bookCategories = new HashSet<BookCategory>(0);

    private Set<BookCopy> bookCopies = new HashSet<BookCopy>(0);

    public Book build() {
        return new Book(title, description, publisher, genre, bookCategories, bookCopies);
    }

    public BookBuilder withBookCopy(final BookCopy... aCopy) {
        for (BookCopy bookCopy : aCopy) {
            if (bookCopy.getState().equals(BookCopy.STATE_FREE)) {
                bookCopies.add(bookCopy);
            }
        }
        return this;
    }

    public Set<BookCopy> getFreeBookCopies() {
        for (BookCopy bookCopy : bookCopies) {
            if (bookCopy.getState().equals(BookCopy.STATE_FREE)) {
                bookCopies.add(bookCopy);
            }
        }
        return bookCopies;
    }

    public BookBuilder withCategory(final BookCategory aCategory) {
        bookCategories.add(aCategory);
        return this;
    }

    public BookBuilder withDescription(final String aDescription) {
        description = aDescription;
        return this;
    }

    public BookBuilder withTitle(final String aTitle) {
        title = aTitle;
        return this;
    }

    public BookBuilder withPublisher(final String aPublisher) {
        publisher = aPublisher;
        return this;
    }

    public BookBuilder withGenre(final BookGenre aGenre) {
        genre = aGenre;
        return this;
    }

}
