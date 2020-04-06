package entities;

public class Category_Exercise {
	private int categoryID,exerciseID;

	public Category_Exercise(int categoryID, int exerciseID) {
		this.categoryID = categoryID;
		this.exerciseID = exerciseID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}
	
}
