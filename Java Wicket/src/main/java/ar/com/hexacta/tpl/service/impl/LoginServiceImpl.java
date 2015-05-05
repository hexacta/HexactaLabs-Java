package ar.com.hexacta.tpl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;
import ar.com.hexacta.tpl.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		return userRepository.findByUser(username);
	}
}
