package ar.com.hexacta.tpl.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;

@Service
public class DataInitServiceImpl{

	@Autowired
    private DataInitRepository repository;

	@PostConstruct
	@Transactional(readOnly = false)
	public void init() throws Exception {
        this.repository.initializeData();
	}

	public void setRepository(final DataInitRepository repository) {
        this.repository = repository;
    }
}
