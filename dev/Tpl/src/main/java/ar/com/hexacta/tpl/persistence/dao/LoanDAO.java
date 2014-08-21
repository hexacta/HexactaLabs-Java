package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
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
    @SuppressWarnings("unchecked")
    public List<Loan> findByBookId(final Long bookId) {
        DetachedCriteria criteria = this.createCriteria();
        criteria.add(Restrictions.ilike("book", bookId));
        return (List<Loan>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Loan> findByUser(final String user) {
        DetachedCriteria criteria = this.createCriteria();
        criteria.add(Restrictions.ilike("user", user));
        return (List<Loan>) this.getHibernateTemplate().findByCriteria(criteria);
    }
}