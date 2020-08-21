package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dbConnection.MySQLConnect;

public class MySQLUpdateClass {
	public void updateClass (String name, int ID) {;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnect.getConnection();
			String sql = "update tb_topic set topic_Name = "+"\""+name+"\""+"where topic_ID = "+ID;
			pstm = cn.prepareStatement(sql);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
