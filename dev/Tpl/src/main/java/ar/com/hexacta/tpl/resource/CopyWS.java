package ar.com.hexacta.tpl.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.service.IBookCopiesService;

public class CopyWS {
	
	public CopyWS(){}
	
	@Autowired
	private IBookCopiesService bookCopyService;
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<BookCopy> findAllBookCopies(){
		return bookCopyService.findAllCopies();
	}
	
	@GET
	@Path("/book/{bookId}")
	@Produces("application/json")
	public BookCopy findFreeBookCopyByBook(@PathParam("bookId") final String bookId){
		return bookCopyService.findFreeCopyByBook(new Long(bookId));
	}

}
