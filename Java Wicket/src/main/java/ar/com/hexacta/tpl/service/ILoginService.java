package ar.com.hexacta.tpl.service;

import ar.com.hexacta.tpl.model.User;

public interface ILoginService {
	public User findUserByUsername(String username);
}
