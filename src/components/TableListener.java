package components;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entities.Category;
import services.CategoryService;

public class TableListener implements TableModelListener{

	private JTable table;
	private TopicTableModel tableModel;
	CategoryService categoryService = new CategoryService();
	private JTextArea jTextArea;
	
	public TableListener(TopicTableModel tableModel, JTable table, JTextArea jTextArea) {
		this.tableModel = tableModel;
		this.table = table;
		this.jTextArea = jTextArea;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
//		at least one row is selected
		if(table.getSelectedRows().length != 0) {
			if(table.getSelectedColumn()==4) {

//				better handle it by tableModel instead of table
				int id = (int) tableModel.getValueAt(table.getSelectedRow(), 0);
				String name = (String) tableModel.getValueAt(table.getSelectedRow(), 2);
				boolean favorite = (boolean) tableModel.getValueAt(table.getSelectedRow(), 4);
			
				Category bean = new Category();
				bean.setCategoryID(id);
				bean.setCategoryName(name);
				bean.setCategoryFavorite(favorite);
				
//				call service
				categoryService.updateCategory(bean);
			
			}
		}
		
		if(table.getSelectedColumn()==1) {
			jTextArea.setText("");
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				if(tableModel.getValueAt(i, 1).equals(true)) 
					jTextArea.append(tableModel.getValueAt(i, 2)+ "\t");
			}
		}
	}
}
