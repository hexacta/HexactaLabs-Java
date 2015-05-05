package ar.com.hexacta.tpl.persistence.repository;

import ar.com.hexacta.tpl.model.BookCopy;

public interface BookCopyRepository extends Repository<BookCopy> {
	BookCopy findFreeCopy(Long bookId);
}
