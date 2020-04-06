package entities;

import java.sql.Date;

public class Topic {
	private int topicID;
	private String topicName;
	private Date topicDate;
	private boolean topicFavorite;
	public Topic(int topicID, String topicName, Date topicDate, boolean topicFavorite) {
		this.topicID = topicID;
		this.topicName = topicName;
		this.topicDate = topicDate;
		this.topicFavorite = topicFavorite;
	}
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Date getTopicDate() {
		return topicDate;
	}
	public void setTopicDate(Date topicDate) {
		this.topicDate = topicDate;
	}
	public boolean isTopicFavorite() {
		return topicFavorite;
	}
	public void setTopicFavorite(boolean topicFavorite) {
		this.topicFavorite = topicFavorite;
	}
}
