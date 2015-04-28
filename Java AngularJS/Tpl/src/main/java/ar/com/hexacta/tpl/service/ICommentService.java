package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.Comment;

public interface ICommentService {

	List<Comment> findAllComments();

	Comment findComment(Long commentId);

	void createComment(final Comment comment);

	void updateComment(final Comment comment);

	void deleteCommentById(Long commentId);

	List<Comment> findCommentsByBookId(Long bookId);

}
