package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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
		BookCopy book = new BookCopy();
		book.setId(2L);
		loan.setBook(book);
		loan.setUser(new User());
		Calendar c = new GregorianCalendar(2013, 1, 1);
		Date d1 = c.getTime();
		loan.setFromDate(d1);
		c.add(Calendar.DATE, 1);
		Date d2 = c.getTime();
		loan.setToDate(d2);
		loan.setId(1L);

		List<Loan> todosLosPrestamos = new ArrayList<Loan>();
		todosLosPrestamos.add(loan);

		// getTodosLosPrestamos
		when(dao.findAll()).thenReturn(todosLosPrestamos);

		// getPrestamo
		when(dao.findById(anyInt())).thenReturn(loan);

		when(dao.findByBookId(anyLong())).thenReturn(todosLosPrestamos);
	}

	@Test
	public void testFindAll() {
		List<Loan> todosLosPrestamos = service.findAllLoans();

		Assert.assertNotNull(todosLosPrestamos);
		Assert.assertTrue(todosLosPrestamos.size() > 0);

		Assert.assertTrue(todosLosPrestamos.get(0).getId().equals(1L));

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
		Assert.assertTrue(uloan.getId().equals(1L));

	}

	@Test
	public void testCreateLoan() {
		Calendar c = new GregorianCalendar(2013, 1, 1);
		Date d1 = c.getTime();
		c.add(Calendar.DATE, 1);
		Date d2 = c.getTime();
		Loan uloan = new Loan(new User(), new BookCopy(), d1, d2);
		service.createLoan(uloan);
		verify(dao, atLeastOnce()).save(uloan);

	}

	@Test
	public void testDeleteLoanById() {

		service.deleteLoanById(1L);
		verify(dao, atLeastOnce()).deleteById(1L);
	}

	@Test
	public void testUpdateLoan() {

		service.updateLoan(loan);
		verify(dao, atLeastOnce()).update(loan);

	}

	@Test
	public void testFindLoansByBookId() {
		List<Loan> todosLosPrestamos = service.findLoansByBookId(2L);

		Assert.assertNotNull(todosLosPrestamos);
		Assert.assertTrue(todosLosPrestamos.size() > 0);

		Assert.assertTrue(todosLosPrestamos.get(0).getBook().getId().equals(2L));

	}
}
