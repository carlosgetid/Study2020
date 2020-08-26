package components;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entities.Category;
import services.CategoryService;

public class TableListener implements TableModelListener{

	private JTable table;
	CategoryService categoryService = new CategoryService();
	
	public TableListener(JTable table) {
		this.table = table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if(table.getSelectedRows().length != 0) {
			int id = (int) table.getValueAt(table.getSelectedRow(), 0);
			String name = (String) table.getValueAt(table.getSelectedRow(), 2);
			boolean favorite = (boolean) table.getValueAt(table.getSelectedRow(), 4);
		
			Category bean = new Category();
			bean.setCategoryID(id);
			bean.setCategoryName(name);
			bean.setCategoryFavorite(favorite);
			
//			call service
			categoryService.updateCategory(bean);
		}
	}
}
