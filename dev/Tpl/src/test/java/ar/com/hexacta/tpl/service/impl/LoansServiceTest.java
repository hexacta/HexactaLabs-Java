package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.model.Loan;
import ar.com.hexacta.tpl.model.User;
import ar.com.hexacta.tpl.persistence.dao.LoanDAO;

@RunWith(MockitoJUnitRunner.class)
public class LoansServiceTest {

	private Loan loan;

	@InjectMocks
	private LoansServiceImpl service = new LoansServiceImpl();

	@Mock
	private LoanDAO dao;

	@Before
	public void prepare() {

		loan = new Loan();
		loan.setBook(new BookCopy());
		loan.setUser(new User());
		Calendar c = new GregorianCalendar(2013, 1, 1);
		Date d1 = c.getTime();
		loan.setFromDate(d1);
		c.add(Calendar.DATE, 1);
		Date d2 = c.getTime();
		loan.setToDate(d2);
		loan.setId(1L);

		List<Loan> todosLosUsuarios = new ArrayList<Loan>();
		todosLosUsuarios.add(loan);

		// getTodosLosUsuarios
		when(dao.findAll()).thenReturn(todosLosUsuarios);

		// getUsuario
		when(dao.findById(anyInt())).thenReturn(loan);

	}

	@Test
	public void testFindAll() {
		List<Loan> todosLosUsuarios = service.findAllLoans();

		Assert.assertNotNull(todosLosUsuarios);
		Assert.assertTrue(todosLosUsuarios.size() > 0);

		Assert.assertTrue(todosLosUsuarios.get(0).getId() == 1L);

	}

	@Test
	public void testFindById() {
		Loan uloan = service.findLoan(1L);

		Assert.assertNotNull(uloan);
		Calendar c = new GregorianCalendar(2013, 1, 1);
		Date d1 = c.getTime();
		Assert.assertEquals(uloan.getFromDate(), d1);
		c.add(Calendar.DATE, 1);
		Date d2 = c.getTime();
		Assert.assertEquals(uloan.getToDate(), d2);
		Assert.assertTrue(uloan.getId() == 1);

	}

	@Test
	public void testCreateLoan() {
		Calendar c = new GregorianCalendar(2013, 1, 1);
		Date d1 = c.getTime();
		c.add(Calendar.DATE, 1);
		Date d2 = c.getTime();
		Loan uloan = new Loan(new User(), new BookCopy(), d1, d2);
		service.createLoan(uloan);
		Assert.assertNotNull(uloan);

	}

	@Test
	public void testDeleteLoanById() {

		service.deleteLoanById(1L);
		Loan uloan = service.findLoan(1L);
		Assert.assertNotNull(uloan);
	}

	@Test
	public void testUpdateLoan() {
		Calendar c = new GregorianCalendar(2013, 1, 1);
		Date d1 = c.getTime();
		c.add(Calendar.DATE, 1);
		Date d2 = c.getTime();
		Loan uloan = new Loan(new User(), new BookCopy(), d1, d2);
		service.createLoan(uloan);
		Loan uloan2 = uloan;
		service.updateLoan(uloan2);
		Assert.assertEquals(uloan, uloan2);

	}
}
