package interfaces;

import java.util.ArrayList;

import entities.Topic;

public interface topicDAO {
	//public int insertTopic (Topic t);
	ArrayList<Topic> readTopics();
}
