package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

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
public class UserServiceTest {

	private User user;

	@InjectMocks
	private UsersServiceImpl service = new UsersServiceImpl();

	@Mock
	private UserDAO dao;

	@Before
	public void prepare() {

		user = new User();
		user.setUsername("username");
		user.setPassword("Password");
		user.setEmail("Email");
		user.setId(1L);

		List<User> todosLosUsuarios = new ArrayList<User>();
		todosLosUsuarios.add(user);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

		// getUsuario
		when(dao.findById(anyInt())).thenReturn(user);

	}

	@Test
	public void testFindAll() {
		List<User> todosLosUsuarios = service.findAllUsers();

		Assert.assertNotNull(todosLosUsuarios);
		Assert.assertTrue(todosLosUsuarios.size() > 0);
		Assert.assertEquals(todosLosUsuarios.get(0).getEmail(), "Email");

	}

	@Test
	public void testFindById() {
		User uuser = service.findUser(1L);

		Assert.assertNotNull(uuser);
		Assert.assertEquals(uuser.getUsername(), "username");
		Assert.assertEquals(uuser.getPassword(), "Password");
		Assert.assertEquals(uuser.getEmail(), "Email");
		Assert.assertTrue(uuser.getId().equals(1L));

	}

	@Test
	public void testCreateUser() {
		User uuser = new User("aUsername", "aPassword", "aEmail");
		service.createUser(uuser);
		verify(dao, atLeastOnce()).save(uuser);

	}

	@Test
	public void testDeleteUserById() {

		service.deleteUserById(1L);
		verify(dao, atLeastOnce()).deleteById(1L);
	}

	@Test
	public void testDeleteUser() {

		service.deleteUser(user);
		verify(dao, atLeastOnce()).delete(user);
	}

	@Test
	public void testUpdateUser() {

		service.updateUser(user);
		verify(dao, atLeastOnce()).update(user);
	}
}