package ejb;

import ejb.GenericCRUDServiceEJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import entities.Order;

@Stateless
@Local(OrderService.class)
public class OrderServiceEJB extends GenericCRUDServiceEJB<Order> implements OrderService{

}


