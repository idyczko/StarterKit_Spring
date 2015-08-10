package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entity.LibraryEntity;

public interface LibraryDao extends Dao<LibraryEntity, Long> {
	List<LibraryEntity> findLibraryByName(String prefix);
}
