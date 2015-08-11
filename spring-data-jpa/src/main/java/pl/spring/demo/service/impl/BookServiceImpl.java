package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public List<BookTo> findAllBooks() {
		return BookMapper.map2To(bookRepository.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return BookMapper.map2To(bookRepository.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return BookMapper.map2To(bookRepository.findBookByAuthor(author));
	}

	@Override
	@Transactional(readOnly = false)
	public BookTo saveBook(BookTo book) {
		if (!book.getLibraryId().equals(null)) {
			LibraryEntity libraryEntity = libraryRepository.getOne(book.getLibraryId());
			if (!libraryEntity.equals(null)) {
				BookEntity entity = BookMapper.map(book);
				entity.setLibrary(libraryEntity);
				entity = bookRepository.save(entity);
				return BookMapper.map(entity);
			}
		}
		return null;
	}

	public LibraryRepository getLibraryRepository() {
		return libraryRepository;
	}

	public BookRepository getBookRepository() {
		return bookRepository;
	}
}
