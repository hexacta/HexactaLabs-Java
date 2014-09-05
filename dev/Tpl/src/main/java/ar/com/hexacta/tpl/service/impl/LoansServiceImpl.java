package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.persistence.repository.LoanRepository;
import ar.com.hexacta.tpl.service.ILoansService;

@Service
public class LoansServiceImpl implements ILoansService {

	@Autowired
	LoanRepository loanRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Loan> findAllLoans() {
		return loanRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Loan findLoan(final Long loanId) {
		return loanRepository.findById(loanId);
	}

	@Override
	@Transactional
	public void createLoan(final Loan loan) {
		loanRepository.save(loan);

	}

	@Override
	@Transactional
	public void updateLoan(final Loan loan) {
		loanRepository.update(loan);

	}

	@Override
	@Transactional
	public void deleteLoanById(final Long loanId) {
		loanRepository.deleteById(loanId);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Loan> findLoansByBookId(final Long bookCopyId) {
		return loanRepository.findByBookId(bookCopyId);
	}
}
