package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

@Service
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<BookEntity>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> foundBooks = new ArrayList<>();
		title = title.toLowerCase();
		for (BookEntity book : ALL_BOOKS) {
			String booksTitle = book.getTitle();
			while (true) {
				if (booksTitle.toLowerCase().startsWith(title)) {
					foundBooks.add(book);
					break;
				}
				if (booksTitle.indexOf(" ") < 0) {
					break;
				}
				booksTitle = booksTitle.substring(booksTitle.indexOf(" ") + 1, booksTitle.length());
			}
		}
		return foundBooks;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> foundBooks = new ArrayList<>();
		author = author.toLowerCase();
		String[] authorTab = author.split(" ");
		for (BookEntity book : ALL_BOOKS) {
			List<AuthorTo> authorsList = book.getAuthors();
			outerloop: for (AuthorTo authorTo : authorsList) {
				for (String auth : authorTab) {
					if (authorTo.getFirstName().toLowerCase().startsWith(auth)
							|| authorTo.getLastName().toLowerCase().startsWith(auth)) {
						foundBooks.add(book);
						break outerloop;
					}
				}
			}
		}
		return foundBooks;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	public long getNewId() {
		return sequence.nextValue(ALL_BOOKS);
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", Arrays.asList(new AuthorTo(1L, "William Shakespear"))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", Arrays.asList(new AuthorTo(2L, "Hanna Ożogowska"))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", Arrays.asList(new AuthorTo(3L, "Jan Parandowski"))));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", Arrays.asList(new AuthorTo(4L, "Edmund Niziurski"))));
		ALL_BOOKS.add(
				new BookEntity(5L, "Pan Samochodzik i Fantomas", Arrays.asList(new AuthorTo(5L, "Zbigniew Nienacki"))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", Arrays.asList(new AuthorTo(6L, "Aleksander Fredro"))));
	}
}
