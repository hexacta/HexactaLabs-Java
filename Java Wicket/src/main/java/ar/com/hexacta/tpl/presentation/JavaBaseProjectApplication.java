package ar.com.hexacta.tpl.presentation;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class JavaBaseProjectApplication extends WebApplication implements ApplicationContextAware {
    private static final long serialVersionUID = -7489854575515312542L;

    private transient ApplicationContext context;

    private boolean initialized = false;

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        if (!this.isInitialized()) {
            super.init();
            this.setListeners();
            this.setInitialized(true);

        }
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        return new JavaBaseProjectSession(request);
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(final boolean isInitialized) {
        initialized = isInitialized;
    }

    private void setListeners() {
        this.getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, this.getApplicationContext(), true));
    }

}
