package entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Exercise {
	private int exerciseID;
	private boolean exerciseSelected;
	private String exerciseText;
	private Timestamp exerciseDatetime;
	private boolean exerciseFavorite;
	public int getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}
	public boolean isExerciseSelected() {
		return exerciseSelected;
	}
	public void setExerciseSelected(boolean exerciseSelected) {
		this.exerciseSelected = exerciseSelected;
	}
	public String getExerciseText() {
		return exerciseText;
	}
	public void setExerciseText(String exerciseText) {
		this.exerciseText = exerciseText;
	}
	public Timestamp getExerciseDatetime() {
		return exerciseDatetime;
	}
	public void setExerciseDatetime(Timestamp exerciseDatetime) {
		this.exerciseDatetime = exerciseDatetime;
	}
	public boolean isExerciseFavorite() {
		return exerciseFavorite;
	}
	public void setExerciseFavorite(boolean exerciseFavorite) {
		this.exerciseFavorite = exerciseFavorite;
	}
	
}
