package interfaces;

import java.util.ArrayList;

import entities.Topic;

public interface TopicDAO {
	public int insertTopic (Topic bean);
	ArrayList<Topic> readTopics();
	public int getNextAutoIncrementID();
}
