package ar.com.hexacta.tpl.service.impl;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.BookCopy;
import ar.com.hexacta.tpl.persistence.dao.BookCopyDAO;

@RunWith(MockitoJUnitRunner.class)
public class BookCopiesServiceTest {

    private BookCopy bookcopy;

    @InjectMocks
    private BookCopiesServiceImpl service = new BookCopiesServiceImpl();

    @Mock
    private BookCopyDAO dao;

    @Before
    public void prepare() {

        bookcopy = new BookCopy();
        Book book = new Book();
        book.setId(2L);
        bookcopy.setBook(book);
        bookcopy.setBookRate("Good");
        bookcopy.changeToFree();
        bookcopy.setId(1L);

        List<BookCopy> todosLosUsuarios = new ArrayList<BookCopy>();
        todosLosUsuarios.add(bookcopy);

        // getTodosLosUsuarios
        when(dao.findAll()).thenReturn(todosLosUsuarios);

        // getUsuario
        when(dao.findFreeCopy(anyLong())).thenReturn(bookcopy);

        when(dao.findById(anyLong())).thenReturn(bookcopy);
    }

    @Test
    public void testFindAll() {
        List<BookCopy> todosLosUsuarios = service.findAllCopies();

        Assert.assertNotNull(todosLosUsuarios);
        Assert.assertTrue(todosLosUsuarios.size() > 0);
    }

    @Test
    public void testFindFreeCopyById() {
        BookCopy ubookcopy = service.findFreeCopyByBook(2L);

        Assert.assertNotNull(ubookcopy);
        Assert.assertEquals(ubookcopy.getState(), "Free");
        Assert.assertEquals(ubookcopy.getBookRate(), "Good");
        Assert.assertTrue(ubookcopy.getBook().getId().equals(2L));
        Assert.assertTrue(ubookcopy.getId().equals(1L));
    }

    @Test
    public void testFindCopyById() {
        BookCopy ubookcopy = service.findCopy(1L);
        verify(dao, atLeastOnce()).findById(1L);
        Assert.assertNotNull(ubookcopy);
        Assert.assertEquals(ubookcopy.getState(), "Free");
        Assert.assertEquals(ubookcopy.getBookRate(), "Good");
        Assert.assertTrue(ubookcopy.getId().equals(1L));
    }

    @Test
    public void testUpdateCopy() {
        service.updateCopy(bookcopy);
        verify(dao, atLeastOnce()).saveOrUpdate(bookcopy);

    }
}