package controller;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.BookService;
import ejb.OrderService;
import entities.Client;
import entities.Order;
import entities.Book;

@Named
@SessionScoped
public class PanierController implements Serializable {

	private static Logger log = LoggerFactory.getLogger(PanierController.class);
	
	@EJB
	private BookService bookService;
	
	@Inject
	private MessageBean messageBean;
	
	@EJB
	private OrderService orderService;
	
	private  transient @Inject Order currentOrder;
	
	private Book book;
		
	public Order getCurrentOrder() {
		return currentOrder;
	}
	
	public Book getBook(String idComponent){
		Book book = new Book();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Map component = facesContext.getExternalContext().getRequestParameterMap();
		String bookId = (String)component.get(idComponent);
		Long id = Long.parseLong(bookId);
		book = bookService.find(id);	
		return book;
	}
		
	public String addBook(){
		book= getBook("idAddBook");
		currentOrder.addOne(book);
		return "panier";
	}
	
	public String addOne(Long id){
		book= bookService.find(id);
		currentOrder.addOne(book);
		return "panier";
	}
	
	public String removeOne(Long id){
		book= bookService.find(id);
		currentOrder.removeOne(book);
		return "panier";
	}
	
	public String deleteAll(){
		currentOrder.removeAll();
		return "panier";
	}
	
	public BigDecimal getTotal(){
		return currentOrder.getTotal();
	}
	
	public String goToPanier(){
		return "panier";
	}
	
	public String recap(){
		return "recapitulatif";
	}
	
	public String validate(Client client){
		if(client == null){
			return "login";
		}
		else if(client.getNom()== null && client.getAdresse()== null && client.getZipcode()== 0 && client.getVille()==null){
			messageBean.addMessage("coordonneesManquantes");
			return "showProfile";
		}
		
		{
			currentOrder.setClient(client);
			currentOrder.setDate(new Date());
			orderService.create(currentOrder);
			currentOrder.removeAll();
			return "mesCommandes";
		}
	}
}
