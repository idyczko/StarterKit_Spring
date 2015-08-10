package pl.spring.demo.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "LIBRARIES")
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name= "NAME",nullable = false, length = 50)
    private String name;
    
    @OneToMany(mappedBy="library", cascade=CascadeType.REMOVE)
    private Collection<BookEntity> books;

}