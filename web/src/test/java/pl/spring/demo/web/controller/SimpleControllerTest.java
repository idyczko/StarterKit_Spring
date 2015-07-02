package pl.spring.demo.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.spring.demo.to.BookTo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class SimpleControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(new SimpleController())
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testHomePage() throws Exception {
        // given when
        ResultActions resultActions = mockMvc.perform(get("/home"));
        // then
        resultActions
                .andExpect(view().name("home"))
                .andExpect(model().attribute("booksCount", 1))
                .andExpect(model().attribute("book", new ArgumentMatcher<Object>() {
                    @Override
                    public boolean matches(Object argument) {
                        return "First Book Title".equals(((BookTo) argument).getTitle());
                    }
                }));
    }
}
