package pl.spring.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.spring.demo.to.BookTo;

import java.awt.print.Book;
import java.util.Map;

@Controller
public class SimpleController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Map<String, Object> parameters) {
        parameters.put("book", new BookTo(1L, "First Book Title", null));
        parameters.put("booksCount", 1);
        return "home";
    }
}

