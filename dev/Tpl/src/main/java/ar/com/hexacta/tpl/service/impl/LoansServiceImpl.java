package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.service.ILoansService;
import ar.com.hexacta.tpl.persistence.repository.LoanRepositoy;

@Service
public class LoansServiceImpl implements ILoansService{
	
	LoanRepositoy loanRepository;
	
	@Override
	public List<Loan> findAllLoans() {
		return loanRepository.findAll();
	}

	@Override
	public Loan findLoan(Long loanId) {
		return loanRepository.findById(loanId);
	}

	@Override
	public boolean createLoan(Loan loan, User user, BookCopy bookCopy) {
		if(validateLoan(user, bookCopy)){
		loanRepository.save(loan);
		return true;
		}else
			return false;
	}

	

	@Override
	public boolean updateLoan(Loan loan) {
		return false;	
	}

	@Override
	public void deleteLoanById(Long loanId) {
		loanRepository.deleteById(loanId);
	}

	@Override
	public List<Loan> findLoanByUserId(Long userId) {
		return loanRepository.findByUserId(userId);
	}

	@Override
	public Loan findLoanByBookCopyId(Long bookCopyId) {
		return loanRepository.findByCopyId(bookCopyId);
	}
	
	
	private boolean validateUser(User user){
		return user.isEnabled();
	}
	
	private boolean validateLoan(User user, BookCopy bookCopy) {	
		return validateUser(user)&&availableCopy(bookCopy);
	}
	
	private boolean availableCopy(BookCopy bookCopy) {
		return bookCopy.getState().equals("Loaned");
	}

}
