package services;

import java.util.ArrayList;

import entities.Topic;
import factory.DAOFactory;
import interfaces.TopicDAO;

public class TopicService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	TopicDAO topicDAO = daoFactory.getTopicDAO();

	public int insertTopic (Topic bean) {
		return topicDAO.insertTopic(bean);
	}
	
	public ArrayList<Topic> listAllTopics(){
		return topicDAO.listAllTopics();
	}
	
	public int updateTopic(Topic bean) {
		return topicDAO.updateTopic(bean);
	}
	
	public int getNextAutoIncrementID() {
		return topicDAO.getNextAutoIncrementID();
	}
}
