package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.dao.UserDAO;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	private User login;

	@InjectMocks
	private LoginServiceImpl service = new LoginServiceImpl();

	@Mock
	private UserDAO dao;

	@Before
	public void prepare() {

		login = new User();
		login.setUsername("Username");
		login.setPassword("Password");
		login.setEmail("Email");
		login.setId(1L);

		List<User> todosLosUsuarios = new ArrayList<User>();
		todosLosUsuarios.add(login);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

		// getUsuario
		when(dao.findById(anyInt())).thenReturn(login);

	}

	@Test
	public void testFindUserByUsername() {
		User ulogin = service.findUserByUsername("Username");

		Assert.assertNotNull(ulogin);
		Assert.assertEquals(ulogin.getUsername(), "Username");
		Assert.assertEquals(ulogin.getPassword(), "Password");
		Assert.assertEquals(ulogin.getEmail(), "Email");
		Assert.assertTrue(ulogin.getId() == 1);
	}
}