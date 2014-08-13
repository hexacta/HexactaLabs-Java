package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.User;

public interface UserRepository {
	void save(final User user);

	void update(final User user);

	void delete(final User user);

	void deleteById(Long userId);

	List<User> findAll();

	User findById(Long userId);

	User findByUser(String username);
}
