package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

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
	@Mock
	private BookMapper bookMapper;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo bookTo = new BookTo(null, "title", "author");
		BookEntity bookEntity = new BookEntity(null, "title", Arrays.asList(new AuthorTo(null, "author")));
		BookEntity bookEntity_2 = new BookEntity(1L, "title", Arrays.asList(new AuthorTo(null, "author")));
		Mockito.when(bookMapper.map(bookTo)).thenReturn(bookEntity);
		Mockito.when(bookMapper.map(bookEntity_2)).thenReturn(new BookTo(1L, "title", "author"));
		Mockito.when(bookDao.save(bookEntity)).thenReturn(bookEntity_2);
		// when
		BookTo result = bookService.saveBook(bookTo);
		// then
		Mockito.verify(bookDao).save(bookEntity);
		assertEquals(1L, result.getId().longValue());
	}
}
