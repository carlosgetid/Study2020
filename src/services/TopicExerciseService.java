package services;

import entities.TopicExercise;
import factory.DAOFactory;
import interfaces.TopicExerciseDAO;

public class TopicExerciseService {
	DAOFactory daoFactory = DAOFactory.getDaoFactory(1);
	TopicExerciseDAO topicExerciseDAO = daoFactory.getTopicExerciseDAO();

	public int insertTopicExercise (TopicExercise bean) {
		return topicExerciseDAO.insertTopicExercise(bean);
	}
}
