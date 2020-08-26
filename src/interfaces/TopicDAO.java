package interfaces;

import java.util.ArrayList;

import entities.Topic;

public interface TopicDAO {
	//public int insertTopic (Topic t);
	ArrayList<Topic> readTopics();
}
