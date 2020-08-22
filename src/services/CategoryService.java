package services;

import java.util.ArrayList;

import entities.Category;
import factory.DAOFactory;
import interfaces.CategoryDAO;

public class CategoryService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
	
	public int insertCategory(Category bean) {
		return categoryDAO.insertCategory(bean);
	}
	public ArrayList<Category> listAllCategories(){
		return categoryDAO.listAllCategories();
	}
}
