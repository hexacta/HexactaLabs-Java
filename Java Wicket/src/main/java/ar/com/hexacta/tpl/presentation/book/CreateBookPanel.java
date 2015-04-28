package ar.com.hexacta.tpl.presentation.book;

import java.util.List;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.presentation.HomePage;
import ar.com.hexacta.tpl.service.IBookCategoriesService;
import ar.com.hexacta.tpl.service.IBooksService;

public class CreateBookPanel extends Panel {

    private static final long serialVersionUID = 3474118583191544003L;

    @SpringBean(name = "service.books")
    private IBooksService booksService;

    @SpringBean(name = "service.categories")
    private IBookCategoriesService categoriesService;

    private FeedbackPanel feedbackPanel;

    private BookCategoriesListPanel categoriesListPanel;

    private IModel<Book> model;

    public CreateBookPanel(final String id, final Book book, final FeedbackPanel feedbackPanel) {
        super(id);

        model = new CompoundPropertyModel<Book>(book);
        this.setDefaultModel(model);

        this.feedbackPanel = feedbackPanel;
        this.feedbackPanel.setOutputMarkupId(true);
        this.initialize();
    }

    private void initialize() {
        this.addName();
        this.addDescription();
        this.addPublisher();

        this.createCategoriesListPanel();

        this.createSaveBookButton();
        this.createCancelButton();
    }

    private void addName() {
        TextField<String> nameTextField = new TextField<String>("name");
        nameTextField.setLabel(new Model<String>("Titulo"));
        nameTextField.setRequired(true);
        this.add(nameTextField);
    }

    private void addDescription() {
        TextField<String> descriptionTextField = new TextField<String>("description");
        descriptionTextField.setLabel(new Model<String>("Descripcion"));
        descriptionTextField.setRequired(true);
        this.add(descriptionTextField);
    }

    private void addPublisher() {
        TextField<String> publisherTextField = new TextField<String>("publisher");
        publisherTextField.setLabel(new Model<String>("Editorial"));
        publisherTextField.setRequired(true);
        this.add(publisherTextField);
    }

    private void createCategoriesListPanel() {
        categoriesListPanel = new BookCategoriesListPanel("bookCategoriesList", this.obtainCategoriesDataProvider());
        this.add(categoriesListPanel);
    }

    private IDataProvider<BookCategory> obtainCategoriesDataProvider() {
        final List<BookCategory> categories = categoriesService.findAllCategories();
        return new ListDataProvider<BookCategory>(categories);
    }

    private void createSaveBookButton() {

        AjaxButton saveBookButton = new AjaxButton("saveBookButton") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                target.add(form);

                try {
                    booksService.createBook(CreateBookPanel.this.getBookToBeCreated());

                } catch (Exception e) {
                    feedbackPanel.setOutputMarkupId(true);
                    target.add(feedbackPanel);
                }
                CreateBookPanel.this.navigateToBooksPage();
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.add(feedbackPanel);
            }

        };
        saveBookButton.setOutputMarkupId(true);
        this.add(saveBookButton);
    }

    private void createCancelButton() {
        AjaxButton cancelButton = new AjaxButton("cancelButton") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                target.add(form);
                CreateBookPanel.this.cancelCreateBook();
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.add(form);
                CreateBookPanel.this.cancelCreateBook();
            }
        };
        cancelButton.setOutputMarkupId(true);
        this.add(cancelButton);
    }

    private void navigateToBooksPage() {
        this.setResponsePage(HomePage.class);
    }

    private void cancelCreateBook() {
        this.navigateToBooksPage();
    }

    Book getBookToBeCreated() {
        final Book newBook = model.getObject();

        final Set<BookCategory> bookCategories = categoriesListPanel.getSelectedCategories();
        newBook.setBookCategories(bookCategories);

        return newBook;
    }

}
