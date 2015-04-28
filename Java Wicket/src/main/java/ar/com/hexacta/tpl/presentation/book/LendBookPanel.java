package ar.com.hexacta.tpl.presentation.book;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.presentation.HomePage;

public class LendBookPanel extends Panel {

    private static final long serialVersionUID = 1943808705985585489L;

    private FeedbackPanel feedbackPanel;

    private Form<?> form;

    @SuppressWarnings("unused")
    private IModel<Book> model;

    private User selected = new User("default", "default");

    public LendBookPanel(final String id, final Book book, final FeedbackPanel feedbackPanel) {

        super(id);

        model = new CompoundPropertyModel<Book>(book);
        this.feedbackPanel = feedbackPanel;
        this.initialize();
    }

    private void initialize() {

        this.createForm();
        this.createDropDownBox();
        this.createLendBookButton();
        this.createCancelButton();
    }

    @SuppressWarnings("rawtypes")
    private void createForm() {
        form = new Form("bookLendingForm");
        this.add(form);
    }

    private void createDropDownBox() {

        List<User> usersList = this.getMockedUsers();

        DropDownChoice<User> usersDropDown = new DropDownChoice<User>("usersdropdown", new PropertyModel<User>(this,
                "selected"), usersList, new ChoiceRenderer<User>("username", "email"));
        form.add(usersDropDown);
    }

    private void createCancelButton() {

        AjaxButton cancelButton = new AjaxButton("cancelButton") {

            private static final long serialVersionUID = -3512655638452784649L;

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> aForm) {
                LendBookPanel.this.navigateToBooksPage();
            }

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> aForm) {
                target.add(form);
                LendBookPanel.this.navigateToBooksPage();
            }
        };
        cancelButton.setOutputMarkupId(true);
        form.add(cancelButton);
    }

    private void createLendBookButton() {

        AjaxButton lendBookButton = new AjaxButton("lendBookButton") {

            private static final long serialVersionUID = -3157928465788766942L;

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> aForm) {

                feedbackPanel.setOutputMarkupId(true);
                target.add(feedbackPanel);
            }

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> aForm) {
                target.add(form);
                User selectedUser = selected;
                LendBookPanel.this.navigateToBooksPage();
            }

        };
        lendBookButton.setOutputMarkupId(true);
        form.add(lendBookButton);
    }

    private void navigateToBooksPage() {
        this.setResponsePage(HomePage.class);
    }

    private List<User> getMockedUsers() {

        List<User> list = new ArrayList<User>();

        User user1 = new User("user1", "user1");
        User user2 = new User("user2", "user2");

        list.add(user1);
        list.add(user2);
        return list;
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(final User selected) {
        this.selected = selected;
    }

}
