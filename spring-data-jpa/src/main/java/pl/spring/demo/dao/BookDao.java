package pl.spring.demo.dao;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends Dao<BookEntity, Long> {

    List<BookEntity> findBookByTitle(String title);

	List<BookEntity> findBooksByBookSearchCriteria(BookSearchCriteria searchCriteria);
}
