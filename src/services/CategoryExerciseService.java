package services;

import entities.CategoryExercise;
import entities.TopicCategory;
import factory.DAOFactory;
import interfaces.CategoryExerciseDAO;

public class CategoryExerciseService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	CategoryExerciseDAO categoryExerciseDAO = daoFactory.getCategoryExerciseDAO();
	
	public int insertCategoryExercise(CategoryExercise bean) {
		return categoryExerciseDAO.insertCategoryExercise(bean);
	}
}
