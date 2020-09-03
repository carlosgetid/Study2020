package services;

import java.util.ArrayList;

import entities.Exercise;
import factory.DAOFactory;
import interfaces.ExerciseDAO;

public class ExerciseService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	ExerciseDAO exerciseDAO = daoFactory.getExerciseDAO();

	public int insertExercise(Exercise bean) {
		return exerciseDAO.insertExercise(bean);
	}
	public ArrayList<Exercise> listAllExercises(){
		return exerciseDAO.listAllExercises();
	}
	public int updateExercise(Exercise bean) {
		return exerciseDAO.updateExercise(bean);
	}
	public int deleteExercise(int id) {
		return exerciseDAO.deleteExercise(id);
	}
	public int getNextAutoIncrementID() {
		return exerciseDAO.getNextAutoIncrementID();
	}
}
