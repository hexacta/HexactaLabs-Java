package ar.com.hexacta.tpl.presentation;

import java.io.Serializable;

import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class JavaBaseProjectSession extends WebSession {
    private static final long serialVersionUID = 1L;

    public JavaBaseProjectSession(final Request request) {
        super(request);
        this.injectDependencies();
    }

    public void add(final String key, final Serializable value) {
        this.setAttribute(key, value);
    }

    public void remove(final String key) {
        this.removeAttribute(key);
    }

    public Object get(final String key) {
        return this.getAttribute(key);
    }

    private void injectDependencies() {
        Injector.get().inject(this);
    }

    public static JavaBaseProjectSession getSession() {
        return (JavaBaseProjectSession) Session.get();
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        // boolean authenticated = false;
        // try {
        // Authentication authentication =
        // authenticationManager.authenticate(new
        // UsernamePasswordAuthenticationToken(username, password));
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        // authenticated = authentication.isAuthenticated();
        // } catch (AuthenticationException e) {
        // authenticated = false;
        // }
        // return authenticated;
        return true;
    }

}
