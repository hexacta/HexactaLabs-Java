package ar.com.hexacta.tpl.presentation;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.com.hexacta.tpl.presentation.book.BooksListPanel;
import ar.com.hexacta.tpl.presentation.book.CreateBookPage;

public class HomePage extends BasePage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Form<?> form;

    public HomePage() {
        super();
        new DataInitPage();
        this.initialize();

    }

    private void initialize() {
        this.createForm();
        this.createFeedbackPanel();
        this.createBooksListPanel();
        this.createCreateBookButton();
    }

    @SuppressWarnings("rawtypes")
    private void createForm() {
        form = new Form("booksForm");
        form.setOutputMarkupId(true);
        this.add(form);
    }

    private void createFeedbackPanel() {
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
        form.add(feedbackPanel);
    }

    private void createBooksListPanel() {
        BooksListPanel booksListPanel = new BooksListPanel("booksList");
        form.add(booksListPanel);
    }

    private void createCreateBookButton() {

        AjaxButton createBookButton = new AjaxButton("createBookButton") {

            private static final long serialVersionUID = -6636436275592635172L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> aForm) {
                target.add(form);
                HomePage.this.navigateToCreateBookPage();
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> aForm) {
                // TODO Manejar Errores
            }

        };
        createBookButton.setOutputMarkupId(true);
        form.add(createBookButton);
    }

    private void navigateToCreateBookPage() {
        this.setResponsePage(CreateBookPage.class);
    }

}
