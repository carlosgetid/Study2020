package components;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entities.Category;
import services.CategoryService;

public class CategoryTableListener implements TableModelListener{

	private JTable table;
	private StudyTableModel tableModel;
	CategoryService categoryService = new CategoryService();
	private JTextArea jTextArea;
	private ArrayList<Integer> arrayList;
	
	public CategoryTableListener(StudyTableModel tableModel, JTable table, JTextArea jTextArea, ArrayList<Integer> arrayList) {
		this.tableModel = tableModel;
		this.table = table;
		this.jTextArea = jTextArea;
		this.arrayList = arrayList;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
//		at least one row is selected
		if(table.getSelectedRows().length != 0) {
			if(table.getSelectedColumn()==4) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				String name = (String) table.getValueAt(table.getSelectedRow(), 2);
				boolean favorite = (boolean) table.getValueAt(table.getSelectedRow(), 4);
				
				Category bean = new Category();
				bean.setCategoryID(id);
				bean.setCategoryName(name);
				bean.setCategoryFavorite(favorite);
				
				categoryService.updateCategory(bean);
			}
		}
		
		if(table.getSelectedColumn()==1) {
			jTextArea.setText("");
			arrayList.clear();
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				if(tableModel.getValueAt(i, 1).equals(true)) {
					jTextArea.append(tableModel.getValueAt(i, 2)+ "\t");
					arrayList.add((Integer) tableModel.getValueAt(i, 0));
				}
			}
		}
	}
}
