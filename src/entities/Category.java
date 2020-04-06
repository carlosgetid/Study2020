package entities;

import java.sql.Date;

public class Category {
	private int categoryID;
	private boolean categorySelection;
	private String categoryName;
	private Date categoryDate;
	private boolean categoryFavorite;
	public Category(int categoryID, boolean categorySelection, String categoryName, Date categoryDate,
			boolean categoryFavorite) {
		this.categoryID = categoryID;
		this.categorySelection = categorySelection;
		this.categoryName = categoryName;
		this.categoryDate = categoryDate;
		this.categoryFavorite = categoryFavorite;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public boolean isCategorySelection() {
		return categorySelection;
	}
	public void setCategorySelection(boolean categorySelection) {
		this.categorySelection = categorySelection;
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
