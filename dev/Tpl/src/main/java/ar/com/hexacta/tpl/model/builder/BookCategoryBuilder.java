package ar.com.hexacta.tpl.model.builder;

import ar.com.hexacta.tpl.model.BookCategory;

public class BookCategoryBuilder {

    private String name = "defaultName";

    private String description = "defaultDescription";
    
    public BookCategoryBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public BookCategoryBuilder withDescription(final String aDescription) {
        description = aDescription;
        return this;
    }
    
    public BookCategory buid() {
        return new BookCategory(name, description);
    }
}
