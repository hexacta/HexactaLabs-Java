package ar.com.hexacta.tpl.model;

import java.util.HashSet;
import java.util.Set;

public class Book extends Entity {

    private static final long serialVersionUID = 604529088687479075L;

    private String name;

    private String description;

    private String publisher;

    private Set<BookCategory> bookCategories = new HashSet<BookCategory>(0);

    private Set<BookCopy> bookCopies = new HashSet<BookCopy>(0);

    // Hibernate needs
    public Book() {
        super();
    }

    public Book(final String name) {
        super();
        this.name = name;
    }

    public Book(final String aName, final String aDescription, final String aPublisher,
            final Set<BookCategory> bookCategories, final Set<BookCopy> aBookCopies) {
        super();
        name = aName;
        description = aDescription;
        publisher = aPublisher;
        this.bookCategories = bookCategories;
        bookCopies = aBookCopies;

    }

    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public String getDescription() {
        return description;
    }

    public BookCopy getFreeBookCopy() {
        for (BookCopy bookCopy : bookCopies) {
            if (bookCopy.getState().equals(BookCopy.STATE_FREE)) {
                return bookCopy;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setBookCategories(final Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }
}
