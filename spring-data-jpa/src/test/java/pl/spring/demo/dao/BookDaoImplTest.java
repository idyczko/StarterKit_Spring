package pl.spring.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = bookDao.findOne(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("Pierwsza książka", bookEntity.getTitle());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "p";
        // when
        List<BookEntity> booksEntity = bookDao.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
    
    @Test
    public void testShouldFindBooksByBookSearchCriteria_TitleOnly() {
    	// given
    	final String bookTitle = "pierw";
    	BookSearchCriteria searchCriteria=new BookSearchCriteria();
    	searchCriteria.setBookTitle(bookTitle);
    	// when
    	List<BookEntity> booksEntity = bookDao.findBooksByBookSearchCriteria(searchCriteria);
    	// then
    	assertNotNull(booksEntity);
    	assertFalse(booksEntity.isEmpty());
    	assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
   
    @Test
    public void testShouldFindBooksByBookSearchCriteria_AuthorOnly() {
    	// given
    	final String bookAuthor = "Ja";
    	BookSearchCriteria searchCriteria=new BookSearchCriteria();
    	searchCriteria.setBookAuthor(bookAuthor);
    	// when
    	List<BookEntity> booksEntity = bookDao.findBooksByBookSearchCriteria(searchCriteria);
    	// then
    	assertNotNull(booksEntity);
    	assertFalse(booksEntity.isEmpty());
    	assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
  
    @Test
    public void testShouldFindBooksByBookSearchCriteria_LibraryOnly() {
    	// given
    	final String bookLibrary = "Bibl";
    	BookSearchCriteria searchCriteria=new BookSearchCriteria();
    	searchCriteria.setLibraryName(bookLibrary);
    	// when
    	List<BookEntity> booksEntity = bookDao.findBooksByBookSearchCriteria(searchCriteria);
    	// then
    	assertNotNull(booksEntity);
    	assertFalse(booksEntity.isEmpty());
    	assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
   
    @Test
    public void testShouldFindBooksByBookSearchCriteria_TitleAndAuthor() {
    	// given
    	final String bookTitle = "pierw";
    	final String bookAuthor = "jA";
    	BookSearchCriteria searchCriteria=new BookSearchCriteria();
    	searchCriteria.setBookAuthor(bookAuthor);
    	searchCriteria.setBookTitle(bookTitle);
    	// when
    	List<BookEntity> booksEntity = bookDao.findBooksByBookSearchCriteria(searchCriteria);
    	// then
    	assertNotNull(booksEntity);
    	assertFalse(booksEntity.isEmpty());
    	assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
   
    @Test
    public void testShouldFindBooksByWholeBookSearchCriteria() {
    	// given
    	final String bookTitle = "pierw";
    	final String bookAuthor = "jA";
    	final String bookLibrary = "Bibl";
    	BookSearchCriteria searchCriteria=new BookSearchCriteria();
    	searchCriteria.setBookAuthor(bookAuthor);
    	searchCriteria.setBookTitle(bookTitle);
    	searchCriteria.setLibraryName(bookLibrary);
    	// when
    	List<BookEntity> booksEntity = bookDao.findBooksByBookSearchCriteria(searchCriteria);
    	// then
    	assertNotNull(booksEntity);
    	assertFalse(booksEntity.isEmpty());
    	assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
  
    @Test
    public void testShouldFindAllBooksByEmptyBookSearchCriteria() {
    	// given
    	BookSearchCriteria searchCriteria=new BookSearchCriteria();
    	long size= bookDao.count();
    	// when
    	List<BookEntity> booksEntity = bookDao.findBooksByBookSearchCriteria(searchCriteria);
    	// then
    	assertNotNull(booksEntity);
    	assertFalse(booksEntity.isEmpty());
    	assertEquals(size, booksEntity.size());
    }
}
