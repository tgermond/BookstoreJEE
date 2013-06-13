package ejb;

import javax.servlet.http.HttpServletResponse;

import entities.Book;

public interface BookService extends GenericCRUDService<Book>{
	public Book getBookById(int id);
	public void getPhoto(int id, HttpServletResponse response);
}


