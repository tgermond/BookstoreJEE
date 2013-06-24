package controller;

//import java.io.ByteArrayInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.LoggedIn;
import ejb.ClientService;
import entities.Book;
import entities.Client;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ClientController implements Serializable {

	private static Logger log = LoggerFactory.getLogger(ClientController.class);
		
	@EJB 
	private ClientService clientService;
	
	@Inject
	private LoginForm loginForm;

	@Inject
	private RegisterForm registerForm;
	
	@Inject
	private MessageBean messageBean;
	
	private Client currentClient;
	
	@Produces @LoggedIn @Named  
	public Client getCurrentClient() {
		return currentClient;
	}

	public String doLogin() {
		currentClient = clientService.login(loginForm.getLogin(), loginForm.getPassword());
		if(currentClient == null){
			messageBean.addMessage("clientNotFound");
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
	
	public String register() {
		currentClient = clientService.register(registerForm.getLogin(), registerForm.getPassword());
		if(currentClient == null){
			return null;
		}
		return "welcome";
	}
	
	public String updateProfile(){		
		currentClient.setLogin(currentClient.getLogin());
		currentClient.setPassword(currentClient.getPassword());
		currentClient.setNom(currentClient.getNom());
		currentClient.setPrenom(currentClient.getPrenom());
		currentClient.setEmail(currentClient.getEmail());
		currentClient.setAdresse(currentClient.getAdresse());
		currentClient.setVille(currentClient.getVille());
		currentClient.setZipcode(currentClient.getZipcode());
		
		clientService.update(currentClient);
		if(currentClient == null){
			return null;
		}
		return "showProfile";
	}
	
	public String goToMyProfile(){
		return "showProfile";
	}
	
	public String goToLogin(){
		return "login";
	}
	
	public String seeMyOrders(){
		return "mesCommandes";
	}
	
	public void uploadPhoto(FileUploadEvent event) {   
		currentClient.setAvatar(event.getFile().getContents());	
		System.out.println("FIN SET PHOTO");
	}

	public StreamedContent getPhoto() {
		System.out.println("Show photo");
	   
        
		InputStream input = null;
		if(currentClient!=null)
		{
			byte[] byt = currentClient.getAvatar();
			if(byt!=null && byt.length>0)
				input = new ByteArrayInputStream(byt);
			else
			{
				File f = new File("img/15x15.png");
			    byte[] phot = new byte[(int)(f.length())];
			    FileInputStream fi = null;
				try {
					fi = new FileInputStream(f);
					fi.read(phot);
					input = new ByteArrayInputStream(phot);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}			
    	return new DefaultStreamedContent(input, "image/png");
  
        
	}
}
