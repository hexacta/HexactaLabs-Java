package ar.com.hexacta.tpl.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.service.IBookCopiesService;


@Service
public class BookCopyWS {
	
	public BookCopyWS(){	
	}
	
	@Autowired
	private IBookCopiesService bookCopyService;
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<BookCopy> findAllBookCopies(){
		return bookCopyService.findAllCopies();
	}
	
	@GET
	@Path("/byBook/{bookId}")
	@Produces("application/json")
	public BookCopy findFreeBookCopy(@PathParam("bookId") final String bookId){
		return bookCopyService.findFreeCopyByBookId(new Long(bookId));
		
	}
	
	@PUT
	@Path("/{copyId}")
	@Consumes("application/json")
	public Response updateBookCopy(
			@PathParam("copyId") final String copyId,
			final String jsonBookCopy) {
		try {
			BookCopy copy = parseBookCopy(jsonBookCopy);
			copy.setId(new Long(copyId));
			copy.changeToLoaned();
			bookCopyService.updateCopy(copy);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	private BookCopy parseBookCopy(final String jsonBookCopy) 
		throws JsonParseException, JsonMappingException, IOException{
			BookCopy newCopy = new BookCopy();
			ObjectMapper mapper = new ObjectMapper();
			newCopy = mapper.readValue(jsonBookCopy, BookCopy.class);
			return newCopy;
		}

	public IBookCopiesService getBookCopiesService() {
		return bookCopyService;
	}

	public void setBookCopiesService(final IBookCopiesService bookCopyService) {
		this.bookCopyService = bookCopyService;
	}
	

}
