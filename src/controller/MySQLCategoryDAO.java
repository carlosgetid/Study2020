package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbConnection.MySQLConnect;
import entities.Category;
import interfaces.CategoryDAO;

public class MySQLCategoryDAO implements CategoryDAO{

	@Override
	public ArrayList<Category> listAllCategories() {
		ArrayList<Category> list = new ArrayList<Category>();
		Category bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLConnect.getConnection();
			String sql = "SELECT category_ID, category_Name, category_Date, category_Favorite FROM tb_category";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Category();
				bean.setCategoryID(rs.getInt(1));
				bean.setCategoryName(rs.getString(2));
				bean.setCategoryDatetime(rs.getTimestamp(3));
				bean.setCategoryFavorite(rs.getBoolean(4));
				
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
}
