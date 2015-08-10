package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(Map<String, Object> params) {
		final List<BookTo> allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookList";
	}

	@RequestMapping(value = "/books_tab", method = RequestMethod.GET)
	public String bookTab(Map<String, Object> params) {
		final List<BookTo> allBooks = bookService.findAllBooks();
		params.put("books", allBooks);
		return "bookTab";
	}

	@RequestMapping(value = "/delete-by-id", method = RequestMethod.POST)
	public String deleteBookById(@RequestParam("id") Long id, Map<String, Object> params) {
		params.put("deletedBook", bookService.deleteBookById(id));
		return "bookDeleted";
	}

	@RequestMapping(value = "/newbook", method = RequestMethod.POST)
	public String saveNewBook(@RequestParam("title") String title, @RequestParam("authors") String authors,
			Map<String, Object> params) {
		if (title.isEmpty() || authors.isEmpty()) {
			return "illegalArgument";
		}
		BookTo bookTo = new BookTo(null, title, authors);
		params.put("insertedBook", bookService.saveBook(bookTo));
		return "bookInserted";
	}
}
