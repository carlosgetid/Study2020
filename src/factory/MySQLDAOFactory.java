package factory;

import controller.MySQLCategoryDAO;
import controller.MySQLCategoryExerciseDAO;
import controller.MySQLExerciseDAO;
import controller.MySQLTopicCategoryDAO;
import controller.MySQLTopicDAO;
import controller.MySQLTopicExerciseDAO;
import interfaces.CategoryDAO;
import interfaces.CategoryExerciseDAO;
import interfaces.ExerciseDAO;
import interfaces.TopicCategoryDAO;
import interfaces.TopicDAO;
import interfaces.TopicExerciseDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CategoryDAO getCategoryDAO() {
		return new MySQLCategoryDAO();
	}

	@Override
	public ExerciseDAO getExerciseDAO() {
		return new MySQLExerciseDAO();
	}

	@Override
	public TopicDAO getTopicDAO() {
		return new MySQLTopicDAO();
	}

	@Override
	public TopicCategoryDAO getTopicCategoryDAO() {
		return new MySQLTopicCategoryDAO();
	}

	@Override
	public TopicExerciseDAO getTopicExerciseDAO() {
		return new MySQLTopicExerciseDAO();
	}

	@Override
	public CategoryExerciseDAO getCategoryExerciseDAO() {
		return new MySQLCategoryExerciseDAO();
	}

}
