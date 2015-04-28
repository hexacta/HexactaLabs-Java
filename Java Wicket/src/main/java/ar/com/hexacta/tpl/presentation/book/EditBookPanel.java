package ar.com.hexacta.tpl.presentation.book;

import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.presentation.HomePage;
import ar.com.hexacta.tpl.service.IBookCategoriesService;
import ar.com.hexacta.tpl.service.IBooksService;

public class EditBookPanel extends Panel {

    private static final long serialVersionUID = 5258005475318339820L;

    @SpringBean(name = "service.books")
    private IBooksService booksService;

    @SpringBean(name = "service.categories")
    private IBookCategoriesService categoriesService;

    private FeedbackPanel feedbackPanel;

    private IDataProvider<BookCategory> dataProvider;

    private Form<?> form;

    private BookCategoriesListPanel bookCategoriesListPanel;

    private IModel<Book> model;

    public EditBookPanel(final String id, final Book book, final FeedbackPanel feedbackPanel) {
        super(id);

        model = new CompoundPropertyModel<Book>(book);
        this.setDefaultModel(model);

        this.feedbackPanel = feedbackPanel;

        this.initializeDataProvider();
        this.initialize();
    }

    private void initialize() {
        this.createForm();
        this.createBookDataEditionPanel();
        this.initializeCategoriesList();

        this.createSaveBookButton();
        this.createCancelButton();
    }

    @SuppressWarnings("rawtypes")
    private void createForm() {
        form = new Form("bookDataEditionForm");
        this.add(form);
    }

    private void createBookDataEditionPanel() {
        BookDataEditionPanel bookDataEditionPanel = new BookDataEditionPanel("bookDataEditionPanel", model.getObject());
        form.add(bookDataEditionPanel);
    }

    private void initializeCategoriesList() {
        bookCategoriesListPanel = new BookCategoriesListPanel("bookCategoriesListPanel", dataProvider, model
                .getObject().getBookCategories());
        form.add(bookCategoriesListPanel);
    }

    private void createSaveBookButton() {

        AjaxButton saveBookButton = new AjaxButton("saveBookButton") {

            private static final long serialVersionUID = -3157928465788766942L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> aForm) {
                target.add(form);

                try {
                    booksService.updateBook(EditBookPanel.this.getModifiedBook());

                } catch (Exception e) {
                    target.add(feedbackPanel);
                }

                EditBookPanel.this.navigateToBooksPage();

            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> aForm) {

                feedbackPanel.setOutputMarkupId(true);
                target.add(feedbackPanel);

            }

        };
        saveBookButton.setOutputMarkupId(true);
        form.add(saveBookButton);
    }

    private void createCancelButton() {

        AjaxButton cancelButton = new AjaxButton("cancelButton") {

            private static final long serialVersionUID = -3512655638452784649L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> aForm) {
                target.add(form);
                EditBookPanel.this.navigateToBooksPage();
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> aForm) {
                EditBookPanel.this.navigateToBooksPage();
            }
        };
        cancelButton.setOutputMarkupId(true);
        form.add(cancelButton);
    }

    private Book getModifiedBook() {
        final Book modifiedBook = model.getObject();

        final Set<BookCategory> categoriasAsociadas = bookCategoriesListPanel.getSelectedCategories();
        modifiedBook.setBookCategories(categoriasAsociadas);

        return modifiedBook;
    }

    private void navigateToBooksPage() {
        this.setResponsePage(HomePage.class);
    }

    private void initializeDataProvider() {
        List<BookCategory> categorias = categoriesService.findAllCategories();
        dataProvider = new ListDataProvider<BookCategory>(categorias);
    }

}
