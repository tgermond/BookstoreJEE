package controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import ejb.CategoryService;
import entities.Category;


@Named
@SessionScoped
public class CategoryController implements Serializable {

	private static Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@EJB
	private CategoryService categoryService;
	
		
	public List<Category> getList(){
		return categoryService.findAll();
	}
	
	public List<Category> getCategories(){
		return categoryService.getCategories();
	}
	
	public Category getCategoryById(int id){
		return categoryService.getCategoryById(id);
	}
}
