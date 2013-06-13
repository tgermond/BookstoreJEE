package entitymanager;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Client;

public class CreateTables {
	public static void main(String[] args) {
	    Map<String, String> properties = new HashMap<String, String>();
	    properties.put("eclipselink.ddl-generation", "drop-and-create-tables");
	    
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JEE6-JDBC", properties);
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Client client = new Client();
	    client.setLogin("galland");
	    client.setPassword("dauphine");
	    em.persist(client);
	    em.getTransaction().commit();
	    em.close();
	  }
}