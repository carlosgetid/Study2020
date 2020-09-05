package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dbConnection.MySQLConnection;
import entities.TopicExercise;
import interfaces.TopicExerciseDAO;

public class MySQLTopicExerciseDAO implements TopicExerciseDAO {

	@Override
	public int insertTopicExercise(TopicExercise bean) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "insert into tb_topic_exercise (topic_ID, exercise_ID) values (?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, bean.getTopicID());
			pstm.setInt(2, bean.getExerciseID());
			output = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm != null) pstm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return output;
	}

}
