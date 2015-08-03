package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.to.AuthorTo;

@Component
public class BookMapper {
	private final Set<AuthorTo> ALL_AUTHORS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookTo map(BookEntity book) {
		String authors = "";
		for (AuthorTo author : book.getAuthors()) {
			authors += author.getFirstName() + " " + author.getLastName() + ", ";
		}
		return new BookTo(book.getId(), book.getTitle(), authors);
	}

	public BookEntity map(BookTo book) {
		String[] authorsTab;
		List<AuthorTo> authorList = new ArrayList<AuthorTo>();
		try {
			authorsTab = book.getAuthors().split(",");
		} catch (NullPointerException e) {
			return new BookEntity(book.getId(), book.getTitle(), authorList);
		}
		for (String author : authorsTab) {
			authorList
					.add(new AuthorTo(sequence.nextValue(ALL_AUTHORS), author));
			ALL_AUTHORS.add(authorList.get(0));
		}
		return new BookEntity(book.getId(), book.getTitle(), authorList);
	}

	public List<BookTo> map(List<BookEntity> bookSet) {
		List<BookTo> bookList = new ArrayList<BookTo>();
		for (BookEntity book : bookSet) {
			bookList.add(map(book));
		}
		return bookList;
	}

}
