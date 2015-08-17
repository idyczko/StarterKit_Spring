package pl.spring.demo.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.impl.LibraryServiceImpl;

public class LibraryRepositoryMockTest {

	@Mock
	private LibraryRepository libraryRepository;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldCallDeleteFromRepository() {
		// when given
		libraryRepository.delete(1L);
		// then
		Mockito.verify(libraryRepository).delete(Mockito.anyLong());
	}

}
