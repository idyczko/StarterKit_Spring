package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceTest {
	
	@Autowired
	private BookService bookService;

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo bookTo = new BookTo(null, "Title", "Authors", "Library", 1L);
		Long bookCount = bookService.getBookRepository().count();
		// when
		bookTo = bookService.saveBook(bookTo);
		// then
		assertNotNull(bookTo);
		assertEquals(bookCount + 1, bookService.getBookRepository().count());
	}

	@Test
	public void testCallfindBooksByBookSearchCriteriaRepositoryMethod() {
		// given
		BookTo bookTo = new BookTo(null, "Title", "Authors", "Library", 1L);
		Long bookCount = bookService.getBookRepository().count();
		// when
		bookTo = bookService.saveBook(bookTo);
		// then
		assertNotNull(bookTo);
		assertEquals(bookCount + 1, bookService.getBookRepository().count());
	}

}
