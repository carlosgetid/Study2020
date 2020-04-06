package entities;

public class Topic_Exercise {
	private int topicID,exerciseID;

	public Topic_Exercise(int topicID, int exerciseID) {
		this.topicID = topicID;
		this.exerciseID = exerciseID;
	}

	public int getTopicID() {
		return topicID;
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}
	
}
