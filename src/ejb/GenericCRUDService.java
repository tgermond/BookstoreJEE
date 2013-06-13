package ejb;

import java.util.List;
import java.util.Map;

public interface GenericCRUDService<T> {
  public  void create(T t);
  public   T find(Object id);
  public   T update(T t);
  public void delete(Object id);
  public List findWithNamedQuery(String queryName);
  public List findWithNamedQuery(String queryName, Map<String,Object> parameters);
  public List findAll();
}
