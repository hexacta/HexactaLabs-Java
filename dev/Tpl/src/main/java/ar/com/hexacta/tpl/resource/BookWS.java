package ar.com.hexacta.tpl.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.service.IBooksService;
import ar.com.hexacta.tpl.service.IDataInitService;
import ar.com.hexacta.tpl.service.impl.BooksServiceImpl;

//Import log4j classes.
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class BookWS {

	private static final int HTTP_OK_CREATED = 201;
	private static final int HTTP_OK = 200;
	private static final int HTTP_DELETE = 204;

	private static final Logger LOG = LogManager.getLogger(BookWS.class.getName()); 
	
	public BookWS() {
	}

	@Autowired
	private IDataInitService initService;

	@Autowired
	private IBooksService bookService;

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Book> findAllBooks() {
		return bookService.findAllBooks();
	}

	@GET
	@Path("/{bookId}")
	@Produces("application/json")
	public Book findBook(@PathParam("bookId") final String bookId) {
		return bookService.findBook(new Long(bookId));
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createBook(@Multipart(value = "book2", type = "application/json") final String jsonBook) {
		try {
			bookService.createBook(parseBook(jsonBook));
		} catch (Exception e) {
			LOG.error("Error al crear el libro.");
			return Response.serverError().build();
		}
		return Response.status(HTTP_OK_CREATED).build();
	}

	@PUT
	@Path("/{bookId}")
	@Consumes("application/json")
	public Response updateBook(@PathParam("bookId") final String bookId,
			final String jsonBook) {
		try {
			Book book = parseBook(jsonBook);
			book.setId(new Long(bookId));

			Book persistedBook = bookService.findBook(new Long(bookId));
			if (persistedBook.getEnabled()) {
				return makeUpdate(book, HTTP_OK);
			} else {
				LOG.error("Se quiso acceder a un libro inhabilitado - que ya eliminado logicamente.");
				return Response.serverError().build();
			}

		} catch (Exception e) {
			LOG.error("Se quiso acceder a un libro que no existe.");
			return Response.serverError().build();
		}
	}

	@DELETE
	@Path("/{bookId}")
	public Response deleteBook(@PathParam("bookId") final String bookId) {
		try{
			Book book = bookService.findBook(new Long(bookId));
			book.setEnabled(false);
			return makeUpdate(book, HTTP_DELETE);
		}catch(Exception e){
			LOG.error("Se quiso eliminar un libro que no existe.");
			return Response.serverError().build();
		}
	}

	private Response makeUpdate(Book book, int responseCode) {
		bookService.updateBook(book);
		return Response.status(responseCode).build();
	}

	private Book parseBook(final String jsonBook) throws Exception{
		Book newBook = new Book();
		ObjectMapper mapper = new ObjectMapper();
		newBook = mapper.readValue(jsonBook, Book.class);
		return newBook;
	}

	public IBooksService getBookService() {
		return bookService;
	}

	public void setBookService(final BooksServiceImpl bookService) {
		this.bookService = bookService;
	}

}
