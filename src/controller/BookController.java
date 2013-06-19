package controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.LoggedIn;

import ejb.BookService;
import entities.Book;
import entities.Category;

@Named
@SessionScoped
public class BookController implements Serializable {
	
	private static Logger log = LoggerFactory.getLogger(BookController.class);
	
	@EJB
	private BookService bookService;
	
	private Book currentBook;
	
	@Produces @LoggedIn @Named  
	public Book getCurrentBook() {
		return currentBook;
	}
	
	
	public Book getBookById(Long id){
		return bookService.find(id);
	}
	
	public void getPhoto(int id){
	//	return bookService.getPhoto(id);
	}
	
	public String displayBook(Long id){
		currentBook = bookService.find(id);
		return "book";
	}
	
}



	
		
	
	
	
