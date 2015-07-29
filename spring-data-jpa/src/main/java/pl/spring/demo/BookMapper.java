package pl.spring.demo;

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

	static BookTo map(BookEntity book) {
		BookTo book_2 = new BookTo();
		return null;
	}

	static BookEntity map(BookTo book) {
		String[] authors = book.getAuthors().split(" ");
		List<AuthorTo> authorList = new ArrayList<AuthorTo>();
		for (int i = 0; i < authorList.size(); i += 2) {
			authorList.add(new AuthorTo(sequence.nextValue(ALL_AUTHORS), authors[i], authors[i + 1]));
		}
	}

}
