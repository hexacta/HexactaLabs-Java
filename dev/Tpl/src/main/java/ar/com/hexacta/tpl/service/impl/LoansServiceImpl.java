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
    LoanRepository LoanRepository;

    @Override
    public List<Loan> findAllLoans() {
        return LoanRepository.findAll();
    }

    @Override
    public Loan findLoan(final Long loanId) {
        return LoanRepository.findById(loanId);
    }

    @Override
    @Transactional
    public void createLoan(final Loan loan) {
        LoanRepository.save(loan);

    }

    @Override
    @Transactional
    public void updateLoan(final Loan loan) {
        LoanRepository.update(loan);

    }

    @Override
    @Transactional
    public void deleteLoanById(final Long loanId) {
        LoanRepository.deleteById(loanId);

    }

    @Override
    public List<Loan> findLoansByBookId(final Long bookId) {
        return LoanRepository.findByBookId(bookId);
    }

    @Override
    public List<Loan> findLoansByUser(final String user) {
        return LoanRepository.findByUser(user);
    }
}
