package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Loan;

public interface LoanRepository extends Repository<Loan>{
    List<Loan> findByBookId(Long bookId);

    List<Loan> findByUser(String user);
}
