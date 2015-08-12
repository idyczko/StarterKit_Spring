package pl.spring.demo.dao.impl;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;
import pl.spring.demo.entity.QAuthorEntity;
import pl.spring.demo.entity.QBookEntity;
import pl.spring.demo.entity.QLibraryEntity;

import javax.persistence.FetchType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

@Repository
public class BookDaoImpl extends AbstractDao<BookEntity, Long> implements BookDao {
	private BooleanBuilder builder;

    @Override
    public List<BookEntity> findBookByTitle(String title) {
        TypedQuery<BookEntity> query = entityManager.createQuery(
                "select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')", BookEntity.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

	@Override
	public List<BookEntity> findBooksByBookSearchCriteria(BookSearchCriteria searchCriteria) {
		//BookEntity bookEntity;
		//LibraryEntity libraryEntity;
		QBookEntity bookEntity = QBookEntity.bookEntity;
		BooleanExpression correctTitle= bookEntity.title.toLowerCase().startsWith(searchCriteria.getBookTitle().toLowerCase());
		JPQLQuery<BookEntity> query= new JPAQuery<BookEntity>(entityManager).from(bookEntity);
		return query.where(correctTitle).fetch();
	}

}
