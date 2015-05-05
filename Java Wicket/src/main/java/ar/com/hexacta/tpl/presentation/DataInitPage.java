package ar.com.hexacta.tpl.presentation;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.com.hexacta.tpl.service.IDataInitService;

/**
 * This is a test/demo feature page.
 * 
 */

public class DataInitPage extends BasePage {

    private static final long serialVersionUID = 1L;

    private static Boolean initialized = false;

    @SpringBean(name = "service.dataInit")
    private IDataInitService dataInitService;

    public DataInitPage() {
        super();
        synchronized (initialized) {
            if (!initialized) {
                try{
                	dataInitService.init();
                	initialized = true;
                } catch (Exception ex){
                	initialized = false;
                }
            }
        }
    }

}
