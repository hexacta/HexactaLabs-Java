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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.service.IBookCopiesService;
import ar.com.hexacta.tpl.service.ILoansService;

@Service
public class LoansWS {

	private static final int HTTP_OK = 200;
	private static final Logger LOG = LogManager.getLogger(LoansWS.class.getName());
    public LoansWS() {
    }
    
    @Autowired
    private ILoansService loanService;

    @Autowired
    private IBookCopiesService copyService;

    @GET
    @Path("/")
    @Produces("application/json")
    public List<Loan> findAllLoans() {
        return loanService.findAllLoans();
    }

    @GET
    @Path("/{loanId}")
    @Produces("application/json")
    public Loan findLoan(@PathParam("loanId") final String loanId) {
    	try{
    		return loanService.findLoan(new Long(loanId));
    	}catch(Exception e){
    		LOG.error("Se busca un prestamo que no existe.");
    		return null;
    	}
    }

    @GET
    @Path("/byBook/{bookId}")
    @Produces("application/json")
    public List<Loan> findLoansByBookId(@PathParam("bookCopyId") final String bookCopyId) {
    	try{
    		return loanService.findLoansByBookId(new Long(bookCopyId));
    	}catch(Exception e){
    		LOG.error("Se busca prestamos de un libro que no existe.");
    		return null;
    	}
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public Response createLoan(@Multipart(value = "newLoan", type = "application/json") final String jsonLoan) {
        try {
            Loan newLoan = parseLoan(jsonLoan);

            BookCopy copy = newLoan.getBookCopy();
            copy = copyService.findCopy(copy.getId());
            copy.changeToLoaned();
            makeUpdate(copy, HTTP_OK);

            loanService.createLoan(newLoan);
        } catch (Exception e) {
            LOG.error("No se pudo crear el prestamo para la copia y usuario indicados.");
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{loanId}")
    @Consumes("application/json")
    public Response updateLoan(@PathParam("loanId") final String loanId, final String jsonLoan) {
        try {
            Loan loan = parseLoan(jsonLoan);
            loan.setId(new Long(loanId));
            loanService.updateLoan(loan);
        } catch (Exception e) {
            LOG.error("Se trata de actualizar prestamo que no existe.");
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{loanId}")
    public void deleteLoan(@PathParam("loanId") final String loanId) {
    	try{
    		loanService.deleteLoanById(new Long(loanId));
    	}catch(Exception e){
    		LOG.error("Se intenta eliminar prestamo que no existe.");
    	}
    }

    private Loan parseLoan(final String jsonLoan) throws JsonParseException, JsonMappingException, IOException {
        Loan newLoan = new Loan();
        ObjectMapper mapper = new ObjectMapper();
        newLoan = mapper.readValue(jsonLoan, Loan.class);
        return newLoan;
    }

    private Response makeUpdate(final BookCopy copy, final int responseCode) {
        copyService.updateCopy(copy);
        return Response.status(responseCode).build();
    }

    public ILoansService getLoanService() {
        return loanService;
    }

    public void setLoanService(final ILoansService loanService) {
        this.loanService = loanService;
    }

    public IBookCopiesService getBookCopyService() {
        return copyService;
    }

    public void setBookCopiesService(final IBookCopiesService copyService) {
        this.copyService = copyService;
    }

}
