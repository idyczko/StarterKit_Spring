package pl.spring.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;

	@Test
	public void testShouldFindLibraryByName() {
		// given
		String libraryName = "bibl";
		// when
		List<LibraryEntity> libraryEntities = libraryRepository.findLibraryByName(libraryName);
		// then
		assertNotNull(libraryEntities);
		assertEquals("Biblioteka Miejska", libraryEntities.get(0).getName());
	}

	@Test
	public void testShouldDeleteBooksWithItsLibrary() {
		// given
		BookEntity bookEntity = new BookEntity(null, "Książka 1");
		bookEntity.setLibrary(libraryRepository.getOne(2L));
		Long booksCount = bookRepository.count();
		// when
		bookRepository.save(bookEntity);
		libraryRepository.delete(2L);
		// then
		assertEquals(null, libraryRepository.findOne(2L));
		assertEquals(bookRepository.count(), booksCount.longValue());
	}

}