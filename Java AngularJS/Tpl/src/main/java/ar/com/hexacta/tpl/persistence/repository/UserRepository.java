package ar.com.hexacta.tpl.persistence.repository;

import ar.com.hexacta.tpl.model.User;

public interface UserRepository extends Repository<User>{
	User findByUser(String username);
}
