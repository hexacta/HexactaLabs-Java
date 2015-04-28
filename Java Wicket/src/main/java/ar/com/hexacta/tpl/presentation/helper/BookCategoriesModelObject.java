package ar.com.hexacta.tpl.presentation.helper;

import java.io.Serializable;

public class BookCategoriesModelObject implements Serializable {

    private static final long serialVersionUID = -3105888683210535871L;

    private String bookCategories = "";

    public String getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(final String bookCategories) {
        this.bookCategories = bookCategories;
    }

    public void addCategory(final String category) {

        if (this.getBookCategories().equals("")) {
            this.setBookCategories(category);
        } else {
            this.setBookCategories(this.getBookCategories() + ", " + category);
        }
    }

}
