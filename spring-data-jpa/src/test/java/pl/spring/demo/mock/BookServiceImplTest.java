package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;

    @Before
    public void setUpt() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author");
        Mockito.when(bookDao.save(book)).thenReturn(new BookTo(1L, "title", "author"));
        // when
        BookTo result = bookService.saveBook(book);
        // then
        Mockito.verify(bookDao).save(book);
        assertEquals(1L, result.getId().longValue());
    }
}
