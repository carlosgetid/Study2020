package intefaces;

import java.util.ArrayList;

import entities.Category;

public interface CategoryDAO {
	ArrayList<Category> readCategories();
}
