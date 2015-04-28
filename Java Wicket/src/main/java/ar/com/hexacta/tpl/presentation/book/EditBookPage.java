package ar.com.hexacta.tpl.presentation.book;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.presentation.BasePage;

public class EditBookPage extends BasePage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Form<?> form;

    private FeedbackPanel feedbackPanel;

    private Book book;

    public EditBookPage(final Book book) {
        super();
        this.book = book;
        this.initialize();
    }

    private void initialize() {
        this.createForm();
        this.createFeedbackPanel();
        this.createEditBookPanel();
    }

    @SuppressWarnings("rawtypes")
    private void createForm() {
        form = new Form("editBookForm");
        form.setOutputMarkupId(true);
        this.add(form);
    }

    private void createFeedbackPanel() {
        feedbackPanel = new FeedbackPanel("feedbackPanel");
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
    }

    private void createEditBookPanel() {
        EditBookPanel editBookPanel = new EditBookPanel("editBookPanel", book, feedbackPanel);
        form.add(editBookPanel);
    }

}
