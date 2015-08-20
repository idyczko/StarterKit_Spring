package pl.spring.demo.mapper;

import java.util.HashSet;
import java.util.Set;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.to.AuthorTo;

public class AuthorMapper {

	private static AuthorTo map(AuthorEntity author) {
		return new AuthorTo(author.getId(), author.getFirstName(), author.getLastName());
	}

	private static AuthorEntity map(AuthorTo author) {
		return new AuthorEntity(author.getId(), author.getFirstName(), author.getLastName());
	}

	public static Set<AuthorTo> map2To(Set<AuthorEntity> authors) {
		Set<AuthorTo> authorsTo = new HashSet<AuthorTo>();
		for (AuthorEntity author : authors) {
			authorsTo.add(map(author));
		}
		return authorsTo;
	}

	public static Set<AuthorEntity> map2Entity(Set<AuthorTo> authors) {
		Set<AuthorEntity> authorsEntity = new HashSet<AuthorEntity>();
		for (AuthorTo author : authors) {
			authorsEntity.add(map(author));
		}
		return authorsEntity;
	}

}
