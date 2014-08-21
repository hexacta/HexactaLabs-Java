package ar.com.hexacta.tpl.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.hexacta.tpl.model.BookCategory;
import ar.com.hexacta.tpl.service.IBookCategoriesService;

public class CategoryWS {

	public CategoryWS(){}
	
	@Autowired
	private IBookCategoriesService bookCategoriesService;
	
	@GET
    @Path("/")
    @Produces("application/json")
    public List<BookCategory> findAllBookCategories() {
        return bookCategoriesService.findAllCategories();
    }
}
