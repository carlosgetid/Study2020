package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbConnection.MySQLConnection;
import entities.Topic;
import interfaces.TopicDAO;

public class MySQLTopicDAO implements TopicDAO {

	@Override
	public int insertTopic(Topic bean) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "insert into tb_topic (topic_Name, topic_Favorite) values (?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getTopicName());
			pstm.setBoolean(2, bean.isTopicFavorite());
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
	public ArrayList<Topic> readTopics() {
	ArrayList<Topic> list = new ArrayList<Topic>();
	Connection cn=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	try {
		cn=MySQLConnection.getConnection();
		String sql="select * from tb_topic";
		pstm=cn.prepareStatement(sql);
		rs=pstm.executeQuery();
		Topic t;
		while(rs.next()) {
			t=new Topic();
			t.setTopicID(rs.getInt(1));
			t.setTopicName(rs.getString(2));
			t.setTopicDatetime(rs.getTimestamp(3));
			t.setTopicFavorite(rs.getBoolean(4));
			list.add(t);
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
	
	return list;}

	@Override
	public int getNextAutoIncrementID() {
		int id = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'tb_topic'";
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
