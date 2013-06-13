package ejb;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Category;

@Stateless
@Local(CategoryService.class)
public class CategoryServiceEJB extends GenericCRUDServiceEJB<Category> implements CategoryService{

	public List<Category> getCategories(){
		Query query = em.createQuery("select c from Category c");
		List<Category> categories = query.getResultList();
		return categories;		
	}
	
	public Category getCategoryById(int id){
		Category category = null;
		try {
			category = (Category)em.createQuery("select c from Category c WHERE c.id=:id").setParameter("id",id).getSingleResult();
		}
		catch (NoResultException e) {
			    System.out.println(e.getMessage());
		}
		catch (Exception e) {
			    System.out.println(e.getMessage());
		}
		return category;
	}
}
