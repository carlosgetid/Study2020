package factory;

import controller.MySQLCategoryDAO;
import controller.MySQLExerciseDAO;
import interfaces.CategoryDAO;
import interfaces.ExerciseDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CategoryDAO getCategoryDAO() {
		return new MySQLCategoryDAO();
	}

	@Override
	public ExerciseDAO getExerciseDAO() {
		return new MySQLExerciseDAO();
	}

}
