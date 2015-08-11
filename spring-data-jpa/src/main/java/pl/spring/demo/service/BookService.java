package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.to.BookTo;

public interface BookService {

	List<BookTo> findAllBooks();

	List<BookTo> findBooksByTitle(String title);

	List<BookTo> findBooksByAuthor(String author);

	BookTo saveBook(BookTo book);

	LibraryRepository getLibraryRepository();

	BookRepository getBookRepository();
}
