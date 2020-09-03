package factory;

import interfaces.CategoryDAO;
import interfaces.ExerciseDAO;

public abstract class DAOFactory {
	public static final int mysql = 1;
	public static final int oracle = 2;
	public static final int sqlserver = 3;
	
	public abstract CategoryDAO getCategoryDAO();
	public abstract ExerciseDAO getExerciseDAO();
	
	public static DAOFactory getDaoFactory(int whichFactory) {
		switch (whichFactory) {
		case mysql:
			return new MySQLDAOFactory();
		case oracle:
//			return new OracleDAOFactory();
		case sqlserver:
//			return new SQLServerAOFactory();
		default:
			return null;
		}
	}
}
