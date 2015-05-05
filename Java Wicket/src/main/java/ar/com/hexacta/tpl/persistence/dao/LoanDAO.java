package ar.com.hexacta.tpl.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.persistence.repository.LoanRepository;

@Repository
public class LoanDAO extends AbstractDAO<Loan> implements LoanRepository {
	@Override
	public void deleteById(final Serializable loanId) {
		super.deleteById(loanId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Loan> findByBookId(final Long bookCopyId) {
		DetachedCriteria criteria = this.createCriteria();
		criteria.add(Restrictions.eq("bookCopy.id", bookCopyId));
		return (List<Loan>) this.getHibernateTemplate()
				.findByCriteria(criteria);
	}
}
