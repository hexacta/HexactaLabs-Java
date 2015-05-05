package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.Loan;

public interface ILoansService {

	List<Loan> findAllLoans();

	Loan findLoan(Long loanId);

	void createLoan(final Loan loan);

	void updateLoan(final Loan loan);

	void deleteLoanById(Long loanId);

	List<Loan> findLoansByBookId(Long bookCopyId);

}
