package entities;

import java.sql.Date;

public class Exercise {
	private int exerciseID;
	private boolean exerciseSelection;
	private String exerciseText;
	private Date exerciseDate;
	private boolean exerciseFavorite;
	
	public int getExerciseID() {
		return exerciseID;
	}
	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}
	public boolean isExerciseSelection() {
		return exerciseSelection;
	}
	public void setExerciseSelection(boolean exerciseSelection) {
		this.exerciseSelection = exerciseSelection;
	}
	public String getExerciseText() {
		return exerciseText;
	}
	public void setExerciseText(String exerciseText) {
		this.exerciseText = exerciseText;
	}
	public Date getExerciseDate() {
		return exerciseDate;
	}
	public void setExerciseDate(Date exerciseDate) {
		this.exerciseDate = exerciseDate;
	}
	public boolean isExerciseFavorite() {
		return exerciseFavorite;
	}
	public void setExerciseFavorite(boolean exerciseFavorite) {
		this.exerciseFavorite = exerciseFavorite;
	}
}
