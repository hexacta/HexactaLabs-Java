package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Loan;

public interface LoanRepositoy {
	void save(final Loan loan);

	void update(final Loan loan);

	void delete(final Loan loan);

	void deleteById(Long loanId);

	List<Loan> findAll();

	Loan findById(Long loanId);

	Loan findByCopyId(Long bookCopyId);
	
	List<Loan> findByUserId(Long userId);


}
