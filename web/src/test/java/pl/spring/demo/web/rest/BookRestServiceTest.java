package pl.spring.demo.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParser;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class BookRestServiceTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(bookService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testShouldCallBookService() throws Exception {
		// given
		final String bookTitle = "testTitle";
		AuthorTo author1 = new AuthorTo(1L, "Author1", "Author1");
		AuthorTo author2 = new AuthorTo(2L, "Author2", "Author2");
		final BookTo bookTo1 = new BookTo(1L, bookTitle, new HashSet<AuthorTo>(Arrays.asList(author1)));
		final BookTo bookTo2 = new BookTo(2L, bookTitle, new HashSet<AuthorTo>(Arrays.asList(author2)));

		Mockito.when(bookService.findBooksByTitle(bookTitle)).thenReturn(Arrays.asList(bookTo1, bookTo2));

		// when
		ResultActions response = this.mockMvc.perform(get("/books/books-by-title?titlePrefix=" + bookTitle)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		// then
		Mockito.verify(bookService).findBooksByTitle(bookTitle);

		response.andExpect(status().isOk())

		.andExpect(jsonPath("[0].id").value(bookTo1.getId().intValue()))
				.andExpect(jsonPath("[0].title").value(bookTo1.getTitle()))
				.andExpect(jsonPath("[0].authors[0].id").value(author1.getId().intValue()))
				.andExpect(jsonPath("[0].authors[0].firstName").value(author1.getFirstName()))
				.andExpect(jsonPath("[0].authors[0].lastName").value(author1.getLastName()))

		.andExpect(jsonPath("[1].id").value(bookTo2.getId().intValue()))
				.andExpect(jsonPath("[1].title").value(bookTo2.getTitle()))
				.andExpect(jsonPath("[1].authors[0].id").value(author2.getId().intValue()))
				.andExpect(jsonPath("[1].authors[0].firstName").value(author2.getFirstName()))
				.andExpect(jsonPath("[1].authors[0].lastName").value(author2.getLastName()));
	}

	@Test
	public void testShouldSaveBook() throws Exception {
		// given
		File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/bookToSave.json");
		String json = FileUtils.readFileToString(file);
		// when
		ResultActions response = this.mockMvc.perform(post("/books/book").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json.getBytes()));
		// then
		response.andExpect(status().isOk());
	}
}
