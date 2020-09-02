package components;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entities.Category;
import services.CategoryService;

public class TableListener implements TableModelListener{

	private JTable table;
	private TopicTableModel tableModel;
	CategoryService categoryService = new CategoryService();
	
	public TableListener(TopicTableModel tableModel, JTable table) {
		this.tableModel = tableModel;
		this.table = table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if(table.getSelectedRows().length != 0) {
//			better handle it by tableModel instead of table
			int id = (int) tableModel.getValueAt(table.getSelectedRow(), 0);
			String name = (String) tableModel.getValueAt(table.getSelectedRow(), 2);
			boolean favorite = (boolean) tableModel.getValueAt(table.getSelectedRow(), 4);
		
			Category bean = new Category();
			bean.setCategoryID(id);
			bean.setCategoryName(name);
			bean.setCategoryFavorite(favorite);
			
//			call service
			categoryService.updateCategory(bean);
		}
	}
}
