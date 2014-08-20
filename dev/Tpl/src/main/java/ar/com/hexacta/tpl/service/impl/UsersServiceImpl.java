package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.IUsersService;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;

@Service
public class UsersServiceImpl implements IUsersService {
	private static int MIN_LENGTH_USERNAME = 3;
	private static int MIN_LENGTH_PASSWORD = 6;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findUser(Long userId) {
		return userRepository.findById(userId);
	}
	
	@Override
	@Transactional
	public boolean createUser(User user) {
		if (validateUser(user)){
			userRepository.save(user);
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateUser(User user) {
		if (validateUser(user)){
			userRepository.update(user);
			return true;
		}else{
			return false;
		}
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

	
	private boolean validateUsername(String username, Long id){
		if (username.length() < MIN_LENGTH_USERNAME){
			return false;
		}
		boolean alphanumeric = username.matches("^[a-zA-Z0-9\\.]*$");
		boolean mailformat = username.toUpperCase().matches("^[A-Z][A-Z0-9_]*\\@[A-Z0-9_]*\\.[A-Z]{2,3}(\\.[A-Z]{2})?$");
		if(!mailformat && !alphanumeric){
			return false;
		}
		User alreadyExists = userRepository.findByUser(username);
		if (alreadyExists != null){
			User user = userRepository.findById(id);
			if (user == null){ //The user is being created, so the username must be the same to another existing username 
				return false;
			}
			if (!user.getUsername().equals(username)){ //the username is being modified, and its the same to another existing username
				return false;
			}
		}
		//TODO: ver si hay mas validaciones para hacer
		return true;
	}
	
	private boolean validatePassword(String password){
		if (password.length() < MIN_LENGTH_PASSWORD){
			return false;
		}
		if (password.contains(":")){
			return false;
		}
		//TODO: ver si hay mas validaciones para hacer
		return true;
	}
	
	private boolean validateUser(User user){
		return (validateUsername(user.getUsername(), user.getId()) && validatePassword(user.getPassword()));
	}
}
