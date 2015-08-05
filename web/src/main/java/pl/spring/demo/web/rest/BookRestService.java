package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class BookRestService {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
	public List<BookTo> findBooksByTitle(@RequestParam("titlePrefix") String titlePrefix) {
		return bookService.findBooksByTitle(titlePrefix);
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public BookTo saveBook(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

	@RequestMapping(value = "/book-by-id", method = RequestMethod.POST)
	public ModelAndView deleteBookById(@RequestParam("id") Long id, Map<String, Object> params) {
		params.put("deletedBook", bookService.deleteBookById(id));
		return new ModelAndView("bookDeleted", params);
	}
}
