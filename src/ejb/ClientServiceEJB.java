package ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import ejb.GenericCRUDServiceEJB;
import entities.Client;
@Stateless
@Local(ClientService.class)
public class ClientServiceEJB extends GenericCRUDServiceEJB<Client> implements ClientService{
 
  public Client login(String login, String password) {
    Client client = null;
    try {
      client = (Client) em.createQuery("select c from Client c where c.login=:login and c.password=:password")
          .setParameter("login", login).setParameter("password", password).getSingleResult();
    } catch (NoResultException e) {
    	System.out.println(e.getMessage());
    }
    catch (Exception e) {
    	System.out.println(e.getMessage());
    }
    
    return client;
  }
  
  public Client register(String login, String password) {
	    Client client = new Client();
	    client.setLogin(login);
	    client.setPassword(password);
	    try {
	      em.persist(client);
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    return client;
  }
  
}
