package ejb;

import entities.Client;

public interface ClientService extends GenericCRUDService<Client>{
  public Client login(String login, String password);
  public Client register(String login, String password);
}
