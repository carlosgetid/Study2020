package entities;

import java.sql.Date;

public class Category {
	private int categoryID;
	private boolean categorySelected;
	private String categoryName;
	private Date categoryDate;
	private boolean categoryFavorite;
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public boolean isCategorySelection() {
		return categorySelected;
	}
	public void setCategorySelection(boolean categorySelection) {
		this.categorySelected = categorySelection;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Date getCategoryDate() {
		return categoryDate;
	}
	public void setCategoryDate(Date categoryDate) {
		this.categoryDate = categoryDate;
	}
	public boolean isCategoryFavorite() {
		return categoryFavorite;
	}
	public void setCategoryFavorite(boolean categoryFavorite) {
		this.categoryFavorite = categoryFavorite;
	}
}
