package factory;

import controller.MySQLCategoryDAO;
import controller.MySQLExerciseDAO;
import controller.MySQLTopicDAO;
import interfaces.CategoryDAO;
import interfaces.ExerciseDAO;
import interfaces.TopicDAO;

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

}
