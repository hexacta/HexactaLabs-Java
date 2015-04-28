package ar.com.hexacta.tpl.presentation.book;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import ar.com.hexacta.tpl.model.Book;

public class BookDataEditionPanel extends Panel {

    private static final long serialVersionUID = -5592488496096097948L;

    public BookDataEditionPanel(final String id, final Book book) {
        super(id, new CompoundPropertyModel<Book>(book));
        this.initialize();
    }

    private void initialize() {
        this.addName();
        this.addDescription();
        this.addPublisher();
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

}
