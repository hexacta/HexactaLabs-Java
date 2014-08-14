package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Loan;

public interface LoanRepository {

    void save(final Loan loan);

    void update(final Loan loan);

    void delete(final Loan loan);

    void deleteById(Long loanId);

    List<Loan> findAll();

    Loan findById(Long loanId);

    List<Loan> findByBookId(Long bookId);

    List<Loan> findByUser(String user);

}
