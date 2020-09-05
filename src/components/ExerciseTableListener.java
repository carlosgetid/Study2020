package components;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entities.Exercise;
import services.ExerciseService;

public class ExerciseTableListener implements TableModelListener{

	private JTable table;
	private StudyTableModel tableModel;
	ExerciseService exerciseService = new ExerciseService();
	private JTextArea jTextArea;
	private ArrayList<Integer> arrayList;
	
	public ExerciseTableListener(StudyTableModel tableModel, JTable table, JTextArea jTextArea, ArrayList<Integer> arrayList) {
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
				String text = (String) table.getValueAt(table.getSelectedRow(), 2);
				boolean favorite = (boolean) table.getValueAt(table.getSelectedRow(), 4);
				
				Exercise bean = new Exercise();
				bean.setExerciseID(id);
				bean.setExerciseText(text);
				bean.setExerciseFavorite(favorite);
				
				exerciseService.updateExercise(bean);
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
