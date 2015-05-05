package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Comment;

public interface CommentRepository extends Repository<Comment> {
	List<Comment> findByBookId(Long bookId);
}
