package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.persistence.repository.LoanRepository;

@Repository
public class LoanDAO extends AbstractDAO<Loan> implements LoanRepository {

    @Override
    public void deleteById(final Long loanId) {
        super.deleteById(loanId);
    }

    @Override
    public Loan findById(final Long loanId) {
        Criteria criteria = this.getSession().createCriteria(Loan.class);
        criteria.add(Restrictions.like("id", loanId));
        return (Loan) criteria.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Loan> findByBookId(final Long bookId) {
        Criteria criteria = this.getSession().createCriteria(Loan.class);
        criteria.add(Restrictions.ilike("book", bookId));
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Loan> findByUser(final String user) {
        Criteria criteria = this.getSession().createCriteria(Loan.class);
        criteria.add(Restrictions.ilike("user", user));
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Loan> findAll() {
        Criteria criteria = this.getSession().createCriteria(Loan.class);
        return criteria.list();
    }

}
