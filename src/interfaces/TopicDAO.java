package interfaces;

import java.util.ArrayList;

import entities.Topic;

public interface TopicDAO {
	public int insertTopic (Topic bean);
	public ArrayList<Topic> listAllTopics();
	public int getNextAutoIncrementID();
}
