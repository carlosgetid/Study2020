package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbConnection.MySQLConnection;
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
			cn = MySQLConnection.getConnection();
			String sql = "select category_ID, category_Name, category_Date, category_Favorite from tb_category";
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

	@Override
	public int insertCategory(Category bean) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "insert into tb_category (category_Name, category_Favorite) values (?, ?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getCategoryName());
			pstm.setBoolean(2, bean.isCategoryFavorite());
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
	public int updateCategory(Category bean) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "update tb_category set category_Name = ?, category_Favorite = ? where category_ID = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getCategoryName());
			pstm.setBoolean(2, bean.isCategoryFavorite());
			pstm.setInt(3, bean.getCategoryID());
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
	public int deleteCategory(int id) {
		int output = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLConnection.getConnection();
			String sql = "delete from tb_category where category_ID=?";
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
			String sql = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'tb_category'";
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
