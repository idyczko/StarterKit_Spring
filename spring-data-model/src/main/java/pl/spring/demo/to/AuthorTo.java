package pl.spring.demo.to;

public class AuthorTo implements IdAware {
	private Long id;
	private String firstName;
	private String lastName;

	public AuthorTo() {

	}

	public AuthorTo(Long id, String author) {
		String[] authorTab;
		this.id = id;
		try {
			authorTab = author.split(" ");
		} catch (NullPointerException e) {
			this.firstName = this.lastName = author;
			return;
		}
		this.firstName = authorTab[0];
		this.lastName = authorTab[authorTab.length - 1];
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
