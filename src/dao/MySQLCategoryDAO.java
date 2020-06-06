package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectToDB.MySQLConnect;
import entities.Category;
import intefaces.CategoryDAO;

public class MySQLCategoryDAO implements CategoryDAO{

	@Override
	public ArrayList<Category> readCategories() {
		ArrayList<Category> list = new ArrayList<Category>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySQLConnect.getConnection();
			String sql="select * from tb_category";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			Category c;
			while(rs.next()) {
				c=new Category();
				c.setCategoryID(rs.getInt(1));
				c.setCategoryName(rs.getString(2));
				c.setCategoryDatetime(rs.getTimestamp(3));
				c.setCategoryFavorite(rs.getBoolean(4));
				list.add(c);
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
