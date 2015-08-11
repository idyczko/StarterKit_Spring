package pl.spring.demo.service;

import pl.spring.demo.exception.NoLibraryFoundException;

public interface LibraryService {
	public Long deleteLibrary(Long id) throws NoLibraryFoundException;
}
