package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbConnection.MySQLConnection;
import entities.Category;
import entities.Exercise;
import interfaces.ExerciseDAO;

public class MySQLExerciseDAO implements ExerciseDAO {

	@Override
	public int insertExercise(Exercise bean) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "insert into tb_exercise (exercise_Text, exercise_Favorite) values (?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getExerciseText());
			pstm.setBoolean(2, bean.isExerciseFavorite());
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

	@Override
	public ArrayList<Exercise> listAllExercises() {
		ArrayList<Exercise> list = new ArrayList<Exercise>();
		Exercise bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "select exercise_ID, exercise_Text, exercise_Date, exercise_Favorite from tb_exercise";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Exercise();
				bean.setExerciseID(rs.getInt(1));
				bean.setExerciseText(rs.getString(2));
				bean.setExerciseDatetime(rs.getTimestamp(3));
				bean.setExerciseFavorite(rs.getBoolean(4));
				
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null)cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int updateExercise(Exercise bean) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "update tb_exercise set exercise_Text = ?, exercise_Favorite = ? where exercise_ID = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getExerciseText());
			pstm.setBoolean(2, bean.isExerciseFavorite());
			pstm.setInt(3, bean.getExerciseID());
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

	@Override
	public int deleteExercise(int id) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "delete from tb_exercise where exercise_ID=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
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

	@Override
	public int getNextAutoIncrementID() {
		int id = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'tb_exercise'";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			if(rs.next())
				id = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return id;
	}

}
