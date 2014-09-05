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

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.dao.CommentDAO;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

	private Comment comment;

	@InjectMocks
	private CommentServiceImpl service = new CommentServiceImpl();

	@Mock
	private CommentDAO dao;

	@Before
	public void prepare() {

		comment = new Comment();
		comment.setBook(new Book());
		comment.setUser("User");
		comment.setBody("Body");
		comment.setId(1L);

		List<Comment> todosLosUsuarios = new ArrayList<Comment>();
		todosLosUsuarios.add(comment);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

		// getUsuario
		when(dao.findById(anyInt())).thenReturn(comment);

	}

	@Test
	public void testFindAll() {
		List<Comment> todosLosUsuarios = service.findAllComments();

		Assert.assertNotNull(todosLosUsuarios);
		Assert.assertTrue(todosLosUsuarios.size() > 0);

		Assert.assertEquals(todosLosUsuarios.get(0).getUser(), "User");

	}

	@Test
	public void testFindById() {
		Comment ucomment = service.findComment(1L);

		Assert.assertNotNull(ucomment);
		Assert.assertEquals(ucomment.getUser(), "User");
		Assert.assertEquals(ucomment.getBody(), "Body");
		Assert.assertTrue(ucomment.getId().equals(1L));

	}

	@Test
	public void testCreateComment() {
		Comment ucomment = new Comment(new Book(), "aUser", "aBody");
		service.createComment(ucomment);
		verify(dao, atLeastOnce()).save(ucomment);

	}

	@Test
	public void testDeleteCommentById() {

		service.deleteCommentById(1L);
		verify(dao, atLeastOnce()).deleteById(1L);

	}

	@Test
	public void testUpdateComment() {

		service.updateComment(comment);
		verify(dao, atLeastOnce()).update(comment);

	}
}