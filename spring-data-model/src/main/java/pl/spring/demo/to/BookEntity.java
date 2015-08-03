package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.List;

public class BookEntity implements IdAware {
	private Long id;
	private String title;
	private List<AuthorTo> authors = new ArrayList<AuthorTo>();

	public BookEntity() {
	}

	public BookEntity(Long id, String title, List<AuthorTo> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}
}
