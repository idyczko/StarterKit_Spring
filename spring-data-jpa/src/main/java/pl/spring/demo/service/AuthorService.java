package pl.spring.demo.service;

import java.util.Set;

import pl.spring.demo.to.AuthorTo;

public interface AuthorService {
	
	Set<AuthorTo> findAllAuthors();
    void deleteAuthor(long id);
}
