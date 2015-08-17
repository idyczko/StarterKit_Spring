package pl.spring.demo.repository;

import java.util.List;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

public interface CustomBookRepository {

	public List<BookEntity> findBooksByBookSearchCriteria(BookSearchCriteria searchCriteria);
}
