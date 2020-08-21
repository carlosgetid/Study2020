package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbConnection.MySQLConnect;
import entities.Topic;
import interfaces.TopicDAO;

public class MySQLTopicDAO implements TopicDAO{

	@Override
	public ArrayList<Topic> readTopics() {
		ArrayList<Topic> list = new ArrayList<Topic>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySQLConnect.getConnection();
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
		
		return list;
	}
	
//	public void showFields(Object o) {
//		Class<?> clazz = o.getClass();
//		
//		for(Field field:clazz.getDeclaredFields()) {
//			System.out.println(field.toGenericString());
//		}
//	}
//	
}
