package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ejb.ClientService;
import entities.Client;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ClientController implements Serializable {

	private static Logger log = LoggerFactory.getLogger(ClientController.class);
	
	@EJB
	private ClientService clientService;
	
	@Inject
	private LoginForm loginForm;

	private Client currentClient;
	
	@Produces @Named
	public Client getCurrentClient() {
		return currentClient;
	}

	public String doLogin() {
		currentClient = clientService.login(loginForm.getLogin(), loginForm.getPassword());
		
		if(currentClient == null){
			return null;
		}
		
		return "welcome";
	}

	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		currentClient = null;
		return "login";
	}
}
