package factory;

import controller.MySQLCategoryDAO;
import interfaces.CategoryDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CategoryDAO getCategoryDAO() {
		return new MySQLCategoryDAO();
	}

}
