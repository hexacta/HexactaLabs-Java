package ar.com.hexacta.tpl.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;

@Service
public class DataInitServiceImpl implements InitializingBean {

	@Autowired
    private DataInitRepository repository;

    public void setRepository(final DataInitRepository repository) {
        this.repository = repository;
    }

    public DataInitRepository getRepository() {
        return repository;
    }

	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {
        this.getRepository().initializeData();
	}

}
