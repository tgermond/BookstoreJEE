/**
 * 
 */
package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author dga
 *
 */
@Entity()
@NamedQueries({
@NamedQuery(name = "Client.findAll", query = "Select c From Client c"),
@NamedQuery(name = "Client.findLikeOnLogin", query = "Select c From Client c where c.login like :like")
})
public class Client extends Persistent {
  private String login;
  private String password;
  private byte[] avatar;
  private String civilite;
  private String nom;
  private String prenom;

  private String email;
  private String adresse;
  private String ville;
  private int zipcode;
  private List<Order> commandes = new ArrayList<Order>();
  
  public boolean equals(Object other){
    if(other != null && other instanceof Client)
        return getLogin().equals(((Client)other).getLogin());
    return false;
  }
  
  public int hashCode(){
    return getLogin().hashCode();
  }

  @Column(nullable = false, unique=true)
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Lob
  @Column(length = 100000)
  public byte[] getAvatar() {
    return this.avatar;
  }
  
  public void setAvatar(byte[] avatar) {
    this.avatar = avatar;
  }


  @OneToMany(mappedBy="client",cascade ={CascadeType.PERSIST, CascadeType.REMOVE})
  public List<Order> getCommandes() {
    return commandes;
  }
  public void setCommandes(List<Order> commandes) {
    this.commandes = commandes;
  }
  public void setCivilite(String civ) {
	  this.civilite = civ;
  } 
  public void setNom(String nom) {
	this.nom = nom;
	} 
	
  public void setPrenom(String prenom) {
	this.prenom = prenom;
	}
  
  public void setEmail(String email) {
	this.email = email;
	}
	
  public void setAdresse(String adresse) {
	this.adresse = adresse;
	}  
	
  public void setVille(String ville) {
	this.ville = ville;
  }  
	
  public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
  }
  
  @Column
  public String getNom() {
	return this.nom;
  }  
  @Column
  public String getCivilite() {
	return this.civilite;
  }
  @Column
  public String getPrenom() {
	return this.prenom;
  }
  
  @Column 
  public String getEmail() {
	return this.email;
  }	
  @Column
  public String getAdresse() {
	return this.adresse;
  }
  @Column
  public String getVille() {
	return this.ville;
  }
  @Column
  public int getZipcode() {
	return this.zipcode;
  }  
}
