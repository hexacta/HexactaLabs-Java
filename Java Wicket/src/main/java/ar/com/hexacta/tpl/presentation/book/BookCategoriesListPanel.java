package ar.com.hexacta.tpl.presentation.book;

import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import ar.com.hexacta.tpl.model.BookCategory;

public class BookCategoriesListPanel extends Panel {

    private static final long serialVersionUID = -2507328260564718363L;

    private IDataProvider<BookCategory> categoriesDataProvider;

    private Set<BookCategory> selectedCategories = new HashSet<BookCategory>();

    public BookCategoriesListPanel(final String id, final IDataProvider<BookCategory> categoriesDataProvider) {
        super(id);
        this.categoriesDataProvider = categoriesDataProvider;
        this.loadData();
    }

    public BookCategoriesListPanel(final String id, final IDataProvider<BookCategory> categoriesDataProvider,
            final Set<BookCategory> selectedCategories) {
        super(id);
        this.categoriesDataProvider = categoriesDataProvider;
        this.selectedCategories = selectedCategories;
        this.loadData();
    }

    public Set<BookCategory> getSelectedCategories() {
        return selectedCategories;
    }

    private void loadData() {
        DataView<BookCategory> categoriesDataView = new CategoriesDataView("categories", categoriesDataProvider,
                selectedCategories, new Component[] {});
        categoriesDataView.setItemsPerPage(50);
        this.add(categoriesDataView);
    }

}
