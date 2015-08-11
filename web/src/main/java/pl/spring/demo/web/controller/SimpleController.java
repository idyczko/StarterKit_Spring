package pl.spring.demo.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.spring.demo.to.BookTo;

@Controller
public class SimpleController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Map<String, Object> parameters) {
        parameters.put("book", new BookTo(1L, "First Book Title", null));
        parameters.put("booksCount", 1);
        return "home";
    }
}

