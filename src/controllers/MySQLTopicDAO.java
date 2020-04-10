package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectToDB.MySQLConnect;
import entities.Topic;
import intefaces.topicDAO;

public class MySQLTopicDAO implements topicDAO{

	@Override
	public ArrayList<Topic> readTopics() {
		ArrayList<Topic> list = new ArrayList<Topic>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySQLConnect.getConnection();
			String sql="select topic_Name, topic_Date, topic_Favorite from tb_topic";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			Topic t;
			while(rs.next()) {
				t=new Topic();
				t.setTopicName(rs.getString(1));
				t.setTopicDate(rs.getDate(2));
				t.setTopicFavorite(rs.getBoolean(3));
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
		return list;
	}
	
}
