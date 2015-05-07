package ar.com.hexacta.tpl.persistence.dao;

import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.repository.UserRepository;
@Repository
public class UserDAO extends AbstractDAO<User> implements UserRepository {

}
