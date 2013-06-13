package ejb;

import java.util.List;
import entities.Category;

public interface CategoryService extends GenericCRUDService<Category> {
	 public List<Category> getCategories();
	 public Category getCategoryById(int id);
}
