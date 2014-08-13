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

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.IUsersService;

@Service
public class UserWS {
	
	@Autowired
	private IUsersService userService;
	
	public UserWS(){
		
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
	public User findComment(@PathParam("userId") final String userId) {
		return userService.findUser(new Long(userId));
	}
	

	@POST
	@Path("/")
	@Consumes("application/json")
	public Response createUser(	@Multipart(value = "newUser", type = "application/json") final String jsonUser) {
		try {
			boolean validation = userService.createUser(parseUser(jsonUser));
			if (validation){
				return Response.ok().build();
			}else{
				return Response.serverError().build();
			}
				
		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/{userId}")
	@Consumes("application/json")
	public Response updateUser(	@PathParam("userId") final String userId, final String jsonUser) {
		try {
			User user = parseUser(jsonUser);
			user.setId(new Long(userId));
			
			if (userService.findUser(new Long(userId)).isEnabled()){
				return makeUpdate(user);
			}else{
				return Response.serverError().build();
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.serverError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	private Response makeUpdate(User user){
		boolean validation = userService.updateUser(user);
		
		if (validation){
			return Response.ok().build();
		}else{
			return Response.serverError().build();
		}

	}
	
	@DELETE
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") final String userId) {
		//userService.deleteUserById(new Long(userId));
		User user = userService.findUser(new Long(userId));
		user.Disable();
		return makeUpdate(user);
	}
		
	
	private User parseUser(final String jsonUser) throws JsonParseException, JsonMappingException, IOException {
		User newUser = new User();
		ObjectMapper mapper = new ObjectMapper();
		newUser = mapper.readValue(jsonUser, User.class);
		newUser.Enable();
		return newUser;
	}
	
	public IUsersService getUsersService() {
		return userService;
	}

	public void setUserService(final IUsersService userService) {
		this.userService = userService;
	}
}
