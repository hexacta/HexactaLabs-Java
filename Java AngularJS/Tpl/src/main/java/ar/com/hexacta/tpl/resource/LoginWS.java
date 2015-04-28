package ar.com.hexacta.tpl.resource;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.ILoginService;

@Service
public class LoginWS {
	private static final Logger LOG = LogManager.getLogger(LoginWS.class.getName());
	private static final int HTTP_ERROR_UNAUTHORIZED = 401;
    @Autowired
    private ILoginService loginService;

    public LoginWS() {
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response validateUser(@HeaderParam("Authorization") String auth) {
        String basic = "Basic ";
        auth = auth.substring(basic.length());
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = decoder.decodeBuffer(auth);
            String decoded = new String(decodedBytes);
            String username = decoded.substring(0, decoded.indexOf(':'));
            String password = decoded.substring(decoded.indexOf(':') + 1);

            User user = loginService.findUserByUsername(username);
            if (user == null){
            	LOG.error("Se intento loggear con usuario inexistente.");
                return Response.status(HTTP_ERROR_UNAUTHORIZED).build();
            }
            if (!user.getPassword().equals(password)){
            	LOG.error("Se intento loggear con contraseña incorrecta.");
                return Response.status(HTTP_ERROR_UNAUTHORIZED).build();
            }
            if (user.getEnabled()){
                return Response.ok(user).build();
            }else{
            	LOG.error("Se intento loggear con usuario inhabilitado o eliminado.");
                return Response.status(HTTP_ERROR_UNAUTHORIZED).build();
            }
        } catch (IOException e) {
            return Response.status(HTTP_ERROR_UNAUTHORIZED).build();
        }
    }
}
