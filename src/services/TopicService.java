package services;

import entities.Topic;
import factory.DAOFactory;
import interfaces.TopicDAO;

public class TopicService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	TopicDAO topicDAO = daoFactory.getTopicDAO();

	public int insertTopic (Topic bean) {
		return topicDAO.insertTopic(bean);
	}
	
	public int getNextAutoIncrementID() {
		return topicDAO.getNextAutoIncrementID();
	}
}
