package pl.spring.demo.mapper;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {

	public static BookTo map(BookEntity bookEntity) {
		if (bookEntity != null) {
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), convertSetToString(bookEntity.getAuthors()));
		}
		return null;
	}

	private static String convertSetToString(Set<AuthorEntity> authors) {
		String authorsInString = "";
		for(AuthorEntity author: authors){
			authorsInString+=author.getFirstName();
			authorsInString+=" ";
			authorsInString+=author.getLastName();
			authorsInString+=" ";
		}
		return authorsInString;
	}

	public static BookEntity map(BookTo bookTo) {
		if (bookTo != null) {
			return new BookEntity(bookTo.getId(), bookTo.getTitle());
		}
		return null;
	}

    public static List<BookTo> map2To(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }
}
