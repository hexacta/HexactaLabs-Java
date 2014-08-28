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

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.service.ICommentService;

@Service
public class CommentWS {

	public static int HTTP_OK_CREATED = 201;

	public CommentWS() {
	}

	@Autowired
	private ICommentService commentService;

	@GET
	@Path("/")
	@Produces("application/json")
	public List<Comment> findAllComments() {
		return commentService.findAllComments();
	}

	@GET
	@Path("/{commentId}")
	@Produces("application/json")
	public Comment findComment(@PathParam("commentId") final String commentId) {
		return commentService.findComment(new Long(commentId));
	}

	@GET
	@Path("/byBook/{bookId}")
	@Produces("application/json")
	public List<Comment> findCommentsByBookId(
			@PathParam("bookId") final String bookId) {
		return commentService.findCommentsByBookId(new Long(bookId));
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createComment(
			@Multipart(value = "newComment", type = "application/json") final String jsonComment) {
		try {

			commentService.createComment(parseComment(jsonComment));

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.status(HTTP_OK_CREATED).build();

	}

	@PUT
	@Path("/{commentId}")
	@Consumes("application/json")
	public Response updateComment(
			@PathParam("commentId") final String commentId,
			final String jsonComment) {
		try {
			Comment comment = parseComment(jsonComment);
			comment.setId(new Long(commentId));
			commentService.updateComment(comment);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("commentId") final String commentId) {
		commentService.deleteCommentById(new Long(commentId));
	}

	private Comment parseComment(final String jsonComment)
			throws JsonParseException, JsonMappingException, IOException {
		Comment newComment = new Comment();
		ObjectMapper mapper = new ObjectMapper();
		newComment = mapper.readValue(jsonComment, Comment.class);
		return newComment;
	}

	public ICommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(final ICommentService commentService) {
		this.commentService = commentService;
	}

}
