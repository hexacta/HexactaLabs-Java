package ar.com.hexacta.tpl.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;
import ar.com.hexacta.tpl.service.IDataInitService;

@Service
public class DataInitServiceImpl implements IDataInitService, InitializingBean {

    @Autowired
    private DataInitRepository repository;

    @Autowired
    @Qualifier("transactionManager")
    private PlatformTransactionManager txManager;

    @Transactional
    public void init() throws Exception {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(final TransactionStatus status) {
                repository.initializeData();
            }
        });
    }

    public void setRepository(final DataInitRepository repository) {
        this.repository = repository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
