package entities;

import java.sql.Timestamp;

public class Topic {
	private int topicID;
	private boolean topicSelected;
	private String topicName;
	private Timestamp topicDatetime;
	private boolean topicFavorite;
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public boolean isTopicSelected() {
		return topicSelected;
	}
	public void setTopicSelected(boolean topicSelected) {
		this.topicSelected = topicSelected;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Timestamp getTopicDatetime() {
		return topicDatetime;
	}
	public void setTopicDatetime(Timestamp topicDatetime) {
		this.topicDatetime = topicDatetime;
	}
	public boolean isTopicFavorite() {
		return topicFavorite;
	}
	public void setTopicFavorite(boolean topicFavorite) {
		this.topicFavorite = topicFavorite;
	}
	
}
