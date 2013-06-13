package ejb;
import java.io.IOException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import entities.Book;

@Stateless
@Local(BookService.class)
public class BookServiceEJB extends GenericCRUDServiceEJB<Book> implements BookService{
	public Book getBookById(int id){
		Book book = null;
		try {
			book = (Book)em.createQuery("select b from Book b WHERE b.id=:id").setParameter("id",id).getSingleResult();
		}
		catch (NoResultException e) {
			    System.out.println(e.getMessage());
		}
		catch (Exception e) {
			    System.out.println(e.getMessage());
		}
		return book;
	}
	
	public void getPhoto(int id, HttpServletResponse response){
		Book book  = getBookById(id);
		response.setContentType("image/jpeg");
		try {
			ServletOutputStream os = response.getOutputStream();
			os.write(book.getPhoto());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
