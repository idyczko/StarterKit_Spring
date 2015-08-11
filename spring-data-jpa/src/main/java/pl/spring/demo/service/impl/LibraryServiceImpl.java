package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.exception.NoLibraryFoundException;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.LibraryService;

@Service
@Transactional(readOnly = true)
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	LibraryRepository libraryRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Long deleteLibrary(Long id) throws IllegalArgumentException, NoLibraryFoundException{
		if(id.equals(null)){
			throw new IllegalArgumentException();
		}
		if(libraryRepository.getOne(id).equals(null)){
			throw new NoLibraryFoundException();
		}
		libraryRepository.delete(id);
		return id;
	}
}
