package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.model.User;

public interface ILoansService {
	List<Loan> findAllLoans();
	
	Loan findLoan(Long loanId);
	
	boolean createLoan(final Loan loan, User user, BookCopy bookCopy);
	
	boolean updateLoan(final Loan loan);
	
	void deleteLoanById(Long loanId);
	
	List<Loan> findLoanByUserId (Long userId);
	
	Loan findLoanByBookCopyId (Long bookCopyId);

	boolean createLoan(Loan parseLoan);

}
