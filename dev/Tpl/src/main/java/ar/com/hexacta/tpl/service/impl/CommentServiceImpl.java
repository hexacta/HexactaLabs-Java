package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;
import ar.com.hexacta.tpl.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	CommentRepository commentRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findAllComments() {
		return commentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Comment findComment(final Long commentId) {
		return commentRepository.findById(commentId);
	}

	@Override
	@Transactional
	public void createComment(final Comment comment) {
		commentRepository.save(comment);

	}

	@Override
	@Transactional
	public void updateComment(final Comment comment) {
		commentRepository.update(comment);

	}

	@Override
	@Transactional
	public void deleteCommentById(final Long commentId) {
		commentRepository.deleteById(commentId);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findCommentsByBookId(final Long bookId) {
		return commentRepository.findByBookId(bookId);
	}
}
