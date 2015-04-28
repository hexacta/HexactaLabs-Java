package ar.com.hexacta.tpl.presentation.book;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.presentation.BasePage;

public class LendBookPage extends BasePage {

    private static final long serialVersionUID = 1L;

    private Form<?> form;

    private FeedbackPanel feedbackPanel;

    private Book book;

    public LendBookPage(final Book book) {
        super();
        this.book = book;
        this.initialize();
    }

    private void initialize() {
        this.createForm();
        this.createFeedbackPanel();
        this.createLendBookPanel();
    }

    @SuppressWarnings("rawtypes")
    private void createForm() {
        form = new Form("lendBookForm");
        form.setOutputMarkupId(true);
        this.add(form);
    }

    private void createFeedbackPanel() {
        feedbackPanel = new FeedbackPanel("feedbackPanel");
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
    }

    private void createLendBookPanel() {
        LendBookPanel lendBookPanel = new LendBookPanel("lendBookPanel", book, feedbackPanel);
        form.add(lendBookPanel);
    }

}
