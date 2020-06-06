package entities;

import java.sql.Timestamp;

public class Category {
	private int categoryID;
	private boolean categorySelected;
	private String categoryName;
	private Timestamp categoryDatetime;
	private boolean categoryFavorite;
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public boolean isCategorySelected() {
		return categorySelected;
	}
	public void setCategorySelected(boolean categorySelected) {
		this.categorySelected = categorySelected;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Timestamp getCategoryDatetime() {
		return categoryDatetime;
	}
	public void setCategoryDatetime(Timestamp categoryDatetime) {
		this.categoryDatetime = categoryDatetime;
	}
	public boolean isCategoryFavorite() {
		return categoryFavorite;
	}
	public void setCategoryFavorite(boolean categoryFavorite) {
		this.categoryFavorite = categoryFavorite;
	}
	
}
