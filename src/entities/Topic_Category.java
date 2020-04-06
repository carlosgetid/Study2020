package entities;

public class Topic_Category {
	private int topicID, categoryID;
	
	public Topic_Category(int topicID, int categoryID) {
		this.topicID = topicID;
		this.categoryID = categoryID;
	}
	
	public int getTopicID() {
		return topicID;
	}
	
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
}
