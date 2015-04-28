package ar.com.hexacta.tpl.presentation.book;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.service.IBooksService;

public class BooksListPanel extends Panel {

    private static final long serialVersionUID = 4249034740540883117L;

    @SpringBean(name = "service.books")
    private IBooksService itemsService;

    private IDataProvider<Book> dataProvider;

    public BooksListPanel(final String id) {
        super(id);
        this.setOutputMarkupId(true);
        this.initializeDataProvider();
        this.loadData();
    }

    private void initializeDataProvider() {
        List<Book> items = itemsService.findAllBooks();
        dataProvider = new ListDataProvider<Book>(items);
    }

    private void loadData() {
        DataView<Book> itemsDataView = new BooksDataView("books", dataProvider);
        itemsDataView.setItemsPerPage(50);
        this.add(itemsDataView);
    }

}
