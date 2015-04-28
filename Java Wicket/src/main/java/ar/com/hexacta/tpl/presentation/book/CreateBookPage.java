package ar.com.hexacta.tpl.presentation.book;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.presentation.BasePage;

public class CreateBookPage extends BasePage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String BOOK_DEFAULT_NAME = "";

    private Form<?> form;

    private FeedbackPanel feedbackPanel;

    private Book newBook;

    public CreateBookPage() {
        super();
        newBook = new Book(BOOK_DEFAULT_NAME);
        this.initialize();
    }

    private void initialize() {
        this.createForm();
        this.createFeedbackPanel();
        this.createCreateBookPanel();

    }

    @SuppressWarnings("rawtypes")
    private void createForm() {
        form = new Form("createBookForm");
        form.setOutputMarkupId(true);
        this.add(form);
    }

    private void createCreateBookPanel() {
        CreateBookPanel createBookPanel = new CreateBookPanel("createBookPanel", newBook, feedbackPanel);
        form.add(createBookPanel);
    }

    private void createFeedbackPanel() {
        feedbackPanel = new FeedbackPanel("feedbackPanel");
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
    }

}
