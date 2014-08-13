package ar.com.hexacta.tpl.resource;


import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.ILoginService;

@Service
public class LoginWS {
	
	@Autowired
	private ILoginService loginService;
	
	public LoginWS(){}

	@GET
	@Path("/")
	@Produces("application/json")
	public Response validateUser(@HeaderParam("Authorization") String auth){
		String basic = "Basic ";
		auth = auth.substring(basic.length());
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] decodedBytes = decoder.decodeBuffer(auth);
			String decoded = new String (decodedBytes);
			String username = decoded.substring(0, decoded.indexOf(':'));
			String password = decoded.substring (decoded.indexOf(':') + 1);
			
			User user = loginService.findUserByUsername(username);
			if (user == null){ //there's no user with such username
				return Response.serverError().build();
			}
			if (!user.getPassword().equals(password)){//the password is incorrect for that username
				return Response.serverError().build();
			}
			
			if (user.isEnabled()){
				return Response.ok(user).build();
			}else{
				return Response.serverError().build();
			}
			
		} catch (IOException e) {
			return Response.status(401).build();
		}
		
	}
	
}
