package pl.spring.demo.mapper;

import java.util.HashSet;
import java.util.Set;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.to.AuthorTo;

public class AuthorMapper {
	public static Set<AuthorTo> map(Set<AuthorEntity> authors) {
		Set<AuthorTo> authorsTo = new HashSet<AuthorTo>();
		for(AuthorEntity author: authors){
			authorsTo.add(map(author));
		}
		return authorsTo;
	}

	private static AuthorTo map(AuthorEntity author) {
		return new AuthorTo(author.getId(), author.getFirstName(), author.getLastName());
	}
}
