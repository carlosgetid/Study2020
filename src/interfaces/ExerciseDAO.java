package interfaces;

import java.util.ArrayList;

import entities.Exercise;

public interface ExerciseDAO {
	public int insertExercise(Exercise bean);
	public ArrayList<Exercise> listAllExercises();
	public int updateExercise(Exercise bean);
	public int deleteExercise(int id);
	public int getNextAutoIncrementID();
}
