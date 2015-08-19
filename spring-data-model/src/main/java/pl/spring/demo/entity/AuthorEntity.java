package pl.spring.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;  
    @Column(nullable=false, length=50)
	private String firstName;
    @Column(nullable=false, length=50)
	private String lastName;
    
    //for hibernate
    protected AuthorEntity() {

    }

    public AuthorEntity(Long id, String firstName, String lastName) {
        this(firstName, lastName);
        this.id = id;
    }

    public AuthorEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
	
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
