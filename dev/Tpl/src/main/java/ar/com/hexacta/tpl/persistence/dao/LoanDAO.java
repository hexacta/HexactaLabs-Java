package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.persistence.repository.LoanRepositoy;

import org.springframework.stereotype.Repository;

@Repository
public class LoanDAO extends AbstractDAO<Loan> implements LoanRepositoy{

	@Override
	public void deleteById(Long loanId) {
		super.deleteById(loanId);	
	}

	@Override
	public Loan findById(final Long loanId) {
		Criteria criteria = this.getSession().createCriteria(Loan.class);
		criteria.add(Restrictions.like("id", loanId));
		return (Loan) criteria.uniqueResult();
	}

	@Override
	public Loan findByCopyId(Long bookCopyId) {
		Criteria criteria = this.getSession().createCriteria(Loan.class);
		criteria.add(Restrictions.like("copy_id", bookCopyId));
		return (Loan) criteria.uniqueResult();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Loan> findByUserId(Long userId) {
		Criteria criteria = this.getSession().createCriteria(Loan.class);
		criteria.add(Restrictions.like("user_id", userId));
		return criteria.list();
	}
	

}
