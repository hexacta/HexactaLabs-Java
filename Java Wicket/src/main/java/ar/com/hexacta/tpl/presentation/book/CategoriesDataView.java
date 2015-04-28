package ar.com.hexacta.tpl.presentation.book;

import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.com.hexacta.tpl.model.BookCategory;

public class CategoriesDataView extends DataView<BookCategory> {

    private static final long serialVersionUID = 3900932271570057555L;

    private final Set<BookCategory> selectedBookCategories;

    private final Component[] componentsToBeRenderedPostSelection;

    public CategoriesDataView(final String id, final IDataProvider<BookCategory> dataProvider,
            final Set<BookCategory> selectedBookCategories, final Component... componentsToBeRenderedPostSelection) {
        super(id, dataProvider);
        this.selectedBookCategories = selectedBookCategories;
        this.componentsToBeRenderedPostSelection = componentsToBeRenderedPostSelection;
    }

    private void addNameColumn(final Item<BookCategory> item) {
        BookCategory bookCategory = item.getModelObject();
        item.add(new Label("name", new PropertyModel<BookCategory>(bookCategory, "name")));
    }

    private void addSelectionColumn(final Item<BookCategory> item) {
        AjaxCheckBox checkBox = new AjaxCheckBox("selectCheckBox", new Model<Boolean>()) {

            private static final long serialVersionUID = 5492585806677348555L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                boolean selected = this.getModel().getObject();
                if (selected) {
                    selectedBookCategories.add(item.getModelObject());
                } else {
                    selectedBookCategories.remove(item.getModelObject());
                }
                // Update
                for (Component component : componentsToBeRenderedPostSelection) {
                    target.add(component);
                }
            }

        };

        checkBox.setDefaultModelObject(selectedBookCategories.contains(item.getModelObject()));

        item.add(checkBox);
    }

    @Override
    protected void populateItem(final Item<BookCategory> item) {
        this.addSelectionColumn(item);
        this.addNameColumn(item);
    }

}
