package ar.com.hexacta.tpl.presentation;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 1129872141829429463L;

    public BasePage() {
        super();
        this.iniciarControles();

    }

    private void iniciarControles() {
        this.addLinkToBooksPage();

    }

    private void addLinkToBooksPage() {
        BookmarkablePageLink<HomePage> linkToBooks = new BookmarkablePageLink<HomePage>("linkToBooksPage",
                HomePage.class);
        this.add(linkToBooks);
    }

}
