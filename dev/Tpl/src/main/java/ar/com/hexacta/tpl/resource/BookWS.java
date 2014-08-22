package ar.com.hexacta.tpl.resource;

import java.io.IOException;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.service.IBooksService;
import ar.com.hexacta.tpl.service.impl.BooksServiceImpl;

@Service
public class BookWS {
	
	public static int HTTP_OK_CREATED = 201;
	public static int HTTP_OK = 200;
	public static int HTTP_DELETE = 204;

    public BookWS() {
    }

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
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.status(HTTP_OK_CREATED).build();
    }

    @PUT
    @Path("/{bookId}")
    @Consumes("application/json")
    public Response updateBook(@PathParam("bookId") final String bookId, final String jsonBook) {
        try {
            Book book = parseBook(jsonBook);
            book.setId(new Long(bookId));
            
<<<<<<< HEAD
            if (bookService.findBook(new Long(bookId)).getEnabled()){
            	return makeUpdate(book);
=======
            Book persistedBook = bookService.findBook(new Long(bookId)); 
            if (persistedBook.getEnabled()){
            	return makeUpdate(book, HTTP_OK);
>>>>>>> ee4c7d4f195a05e7e64b4422afee0982dba1b329
            }else{
            	return Response.serverError().build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{bookId}")
    public Response deleteBook(@PathParam("bookId") final String bookId) {
        Book book = bookService.findBook(new Long(bookId));
        book.setEnabled(false);
<<<<<<< HEAD
        return makeUpdate(book);
    }

	private Response makeUpdate(Book book){
		bookService.updateBook(book);
		return Response.ok().build();
=======
        return makeUpdate(book, HTTP_DELETE);
    }

	private Response makeUpdate(Book book, int responseCode){
		bookService.updateBook(book);
		return Response.status(responseCode).build();
>>>>>>> ee4c7d4f195a05e7e64b4422afee0982dba1b329
	}
    
    private Book parseBook(final String jsonBook) throws JsonParseException, JsonMappingException, IOException {
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
