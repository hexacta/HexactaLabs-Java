package ar.com.hexacta.tpl.presentation.book;

import java.util.Iterator;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.access.AccessDeniedException;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.presentation.HomePage;
import ar.com.hexacta.tpl.presentation.helper.BookCategoriesModelObject;
import ar.com.hexacta.tpl.service.IBooksService;

public class BooksDataView extends DataView<Book> {

    private static final long serialVersionUID = -1407856626588256002L;

    @SpringBean(name = "service.books")
    private IBooksService booksService;

    public BooksDataView(final String id, final IDataProvider<Book> dataProvider) {
        super(id, dataProvider);
    }

    @Override
    protected void populateItem(final Item<Book> item) {
        this.addNameColumn(item);
        this.addDescriptionColumn(item);
        this.addPublisherColumn(item);
        this.addCategoriesColumn(item);
        this.addEditButton(item);
        this.addDeleteButton(item);
        this.addLendButton(item);

    }

    private void addNameColumn(final Item<Book> item) {
        Book book = item.getModelObject();
        item.add(new Label("name", new PropertyModel<Book>(book, "name")));
    }

    private void addDescriptionColumn(final Item<Book> item) {
        Book book = item.getModelObject();
        item.add(new Label("description", new PropertyModel<Book>(book, "description")));
    }

    private void addPublisherColumn(final Item<Book> item) {
        Book book = item.getModelObject();
        item.add(new Label("publisher", new PropertyModel<Book>(book, "publisher")));
    }

    private void addCategoriesColumn(final Item<Book> item) {
        Book book = item.getModelObject();
        Set<BookCategory> bookCategories = book.getBookCategories();
        BookCategoriesModelObject modelObject = new BookCategoriesModelObject();
        Iterator<BookCategory> iter = bookCategories.iterator();
        while (iter.hasNext()) {
            BookCategory category = iter.next();
            modelObject.addCategory(category.getName());
        }
        item.add(new Label("categories", new PropertyModel<BookCategory>(modelObject, "bookCategories")));
    }

    private void addEditButton(final Item<Book> item) {
        item.add(new AjaxButton("editBook") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                this.setResponsePage(new EditBookPage(item.getModelObject()));
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                // TODO Manejar Errores
            }
        });
    }

    private void addDeleteButton(final Item<Book> item) {
        // this.deleteBookConfirmationPopin.
        item.add(new AjaxButton("delete") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                try {

                    booksService.deleteBook(item.getModelObject());
                } catch (AccessDeniedException e) {
                    this.error("Access denied");
                }
                BooksDataView.this.navigateToHomePage();
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                // TODO Manejar Errores
            }
        });
    }

    private void addLendButton(final Item<Book> item) {
        item.add(new AjaxButton("lendBook") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                this.setResponsePage(new LendBookPage(item.getModelObject()));
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                // TODO Manejar Errores
            }
        });
    }

    private void navigateToHomePage() {
        this.setResponsePage(HomePage.class);
    }

}
