package ar.com.hexacta.tpl.persistence.repository;

import ar.com.hexacta.tpl.model.User;

public interface UserRepository {

    void save(final User user);

}
