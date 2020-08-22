package interfaces;

import java.util.ArrayList;

import entities.Category;

public interface CategoryDAO {
	public int insertCategory(Category bean);
	public ArrayList<Category> listAllCategories();
	
}
