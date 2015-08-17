package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.repository.CustomBookRepository;

public class BookRepositoryImpl implements CustomBookRepository {
	@PersistenceContext(name = "hsql")
	private EntityManager entityManager;

	@Override
	public List<BookEntity> findBooksByBookSearchCriteria(BookSearchCriteria searchCriteria) {
		BooleanBuilder criteria = new BooleanBuilder();
		QBookEntity bookEntity = QBookEntity.bookEntity;
		JPQLQuery<BookEntity> query = new JPAQuery<BookEntity>(entityManager).from(bookEntity);
		if (!searchCriteria.equals(null)) {
			if (searchCriteria.getBookTitle() != null) {
				String title = searchCriteria.getBookTitle();
				criteria.and(bookEntity.title.toLowerCase().startsWith(title));
			}
			if (searchCriteria.getBookAuthor() != null) {
				String author = searchCriteria.getBookAuthor();
				criteria.and(bookEntity.authors.any().firstName.startsWithIgnoreCase(author)
						.or(bookEntity.authors.any().lastName.startsWithIgnoreCase(author)));
			}
			if (searchCriteria.getLibraryName() != null) {
				String library = searchCriteria.getLibraryName();
				criteria.and(bookEntity.library.name.startsWithIgnoreCase(library));
			}
		}
		return query.where(criteria).fetch();
	}

}
