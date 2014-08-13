package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.User;

public interface IUsersService {
	List<User> findAllUsers();

	User findUser(Long userId);
	
	boolean createUser(final User user);

	boolean updateUser(final User user);

	void deleteUser(final User user);

	void deleteUserById(Long userId);

}
