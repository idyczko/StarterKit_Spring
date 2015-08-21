package pl.spring.demo.web.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;

@RestController
@RequestMapping(value = "/authors")
public class AuthorRestService {
	@Autowired
	private AuthorService authorService;

	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public Set<AuthorTo> findAllAuthors() {
		return authorService.findAllAuthors();
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable("id") long id) {
		authorService.deleteAuthor(id);
	}
}
