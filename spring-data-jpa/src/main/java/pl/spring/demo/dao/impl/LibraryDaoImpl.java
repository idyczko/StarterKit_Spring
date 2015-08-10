package pl.spring.demo.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import pl.spring.demo.dao.LibraryDao;
import pl.spring.demo.entity.LibraryEntity;

public class LibraryDaoImpl  extends AbstractDao<LibraryEntity, Long> implements LibraryDao {

	@Override
	public List<LibraryEntity> findLibraryByName(String prefix) {
        TypedQuery<LibraryEntity> query = entityManager.createQuery(
                "select library from LibraryEntity library where upper(library.name) like concat(upper(:prefix), '%')", LibraryEntity.class);
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }

}
