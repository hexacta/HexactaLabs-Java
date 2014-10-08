package ar.com.hexacta.tpl.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.service.IBookCopiesService;

public class CopyWS {
	private static final Logger logger = LogManager.getLogger(CopyWS.class.getName());
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
		try{
			return bookCopyService.findFreeCopyByBook(new Long(bookId));
		}catch(Exception e){
			logger.error("Se buscan copias de un libro que no existe.");
			return null;
		}
	}

}
