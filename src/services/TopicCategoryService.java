package services;

import entities.TopicCategory;
import factory.DAOFactory;
import interfaces.TopicCategoryDAO;

public class TopicCategoryService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	TopicCategoryDAO topicCategoryDAO = daoFactory.getTopicCategoryDAO();

	public int insertTopicCategory (TopicCategory bean) {
		return topicCategoryDAO.insertTopicCategory(bean);
	}

}
