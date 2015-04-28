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

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.IUsersService;

@Service
public class UserWS {
	private static final Logger LOG = LogManager.getLogger(UserWS.class.getName());
	private static final int HTTP_OK_CREATED = 201;
	private static final int HTTP_OK = 200;
	private static final int HTTP_DELETE = 204;
	
	@Autowired
	private IUsersService userService;

	public UserWS() {

	}

	@GET
	@Path("/")
	@Produces("application/json")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

	@GET
	@Path("/{userId}")
	@Produces("application/json")
	public User findUser(@PathParam("userId") final String userId) {
		try{
			return userService.findUser(new Long(userId));
		}catch(Exception e){
			LOG.error("Se intento buscar un usuario que no existe.");
			return null;
		}
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createUser(
			@Multipart(value = "newUser", type = "application/json") final String jsonUser) {
		try {
			boolean validation = userService.createUser(parseUser(jsonUser));
			if (validation){
				return Response.status(HTTP_OK_CREATED).build();
			}else{
				LOG.error("El usuario que quiere crearse no cumple las condiciones (ya existe nombre de usuario, o no cumple con las cantidades).");
				return Response.serverError().build();
			}
		} catch (Exception e) {
			LOG.error("Error al intentar crear el usuario.");
			return Response.serverError().build();
		}
	}

	@PUT
	@Path("/{userId}")
	@Consumes("application/json")
	public Response updateUser(@PathParam("userId") final String userId,
			final String jsonUser) {
		try {
			User user = parseUser(jsonUser);
			user.setId(new Long(userId));

			if (userService.findUser(new Long(userId)).getEnabled()){
				return makeUpdate(user, HTTP_OK);
			}else{
				LOG.error("Se intento modificar un usuario que no esta habilitado - con baja logica.");
				return Response.serverError().build();
			}
		} catch (Exception e) {
			LOG.error("Se intento modificar un usuario que no existe.");
			return Response.serverError().build();
		}
	}

	private Response makeUpdate(final User user, final int responseCode) {
		boolean validation = userService.updateUser(user);

		if (validation)
			return Response.status(responseCode).build();
		else
			return Response.serverError().build();

	}

	@DELETE
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") final String userId) {
		// userService.deleteUserById(new Long(userId));
		User user = userService.findUser(new Long(userId));
		user.setEnabled(false);
		return makeUpdate(user, HTTP_DELETE);
	}

	private User parseUser(final String jsonUser) throws JsonParseException,
			JsonMappingException, IOException {
		User newUser = new User();
		ObjectMapper mapper = new ObjectMapper();
		newUser = mapper.readValue(jsonUser, User.class);
		newUser.setEnabled(true);
		return newUser;
	}

	public IUsersService getUsersService() {
		return userService;
	}

	public void setUserService(final IUsersService userService) {
		this.userService = userService;
	}

}
