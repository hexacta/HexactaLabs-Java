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

import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.service.ILoansService;

@Service
public class LoansWS {

    public LoansWS() {

    }

    @Autowired
    private ILoansService loanService;

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
        return loanService.findLoan(new Long(loanId));
    }

    @GET
    @Path("/byBook/{bookId}")
    @Produces("application/json")
    public List<Loan> findLoansByBookId(@PathParam("bookId") final String bookId) {
        return loanService.findLoansByBookId(new Long(bookId));
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public Response createLoan(@Multipart(value = "newLoan", type = "application/json") final String jsonLoan) {
        try {
            loanService.createLoan(parseLoan(jsonLoan));
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{loanId}")
    public void deleteLoan(@PathParam("loanId") final String loanId) {
        loanService.deleteLoanById(new Long(loanId));
    }

    private Loan parseLoan(final String jsonLoan) throws JsonParseException, JsonMappingException, IOException {
        Loan newLoan = new Loan();
        ObjectMapper mapper = new ObjectMapper();
        newLoan = mapper.readValue(jsonLoan, Loan.class);
        return newLoan;
    }

    public ILoansService getLoanService() {
        return loanService;
    }

    public void setLoanService(final ILoansService loanService) {
        this.loanService = loanService;
    }

}
