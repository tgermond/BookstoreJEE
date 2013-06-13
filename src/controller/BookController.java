package controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.BookService;
import entities.Book;

@Named
@SessionScoped
public class BookController implements Serializable {
	
	private static Logger log = LoggerFactory.getLogger(BookController.class);
	
	@EJB
	private BookService bookService;
	
	public Book getBookById(int id){
		return bookService.getBookById(id);
	}
	
	public void getPhoto(int id){
	//	return bookService.getPhoto(id);
	}
}



	
		
	
	
	
