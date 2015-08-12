package pl.spring.demo.criteria;

import org.springframework.stereotype.Component;

@Component
public class BookSearchCriteria {
private String bookTitle;
private String bookAuthor;
private String libraryName;


public String getBookTitle() {
	return bookTitle;
}
public void setBookTitle(String bookTitle) {
	this.bookTitle = bookTitle;
}
public String getBookAuthor() {
	return bookAuthor;
}
public void setBookAuthor(String bookAuthor) {
	this.bookAuthor = bookAuthor;
}
public String getLibraryName() {
	return libraryName;
}
public void setLibraryName(String libraryName) {
	this.libraryName = libraryName;
}



}
