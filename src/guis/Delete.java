package guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;

import components.StudyTableModel;
import services.CategoryService;
import services.ExerciseService;

public class Delete extends JDialog implements ActionListener {

	private static final long serialVersionUID = -8889868309940547562L;
	private JTable table ;
	private JButton btnYes;
	private JLabel lblName;
	private JButton btnCancel;
	private StudyTableModel tableModel;
	CategoryService categoryService = new CategoryService();
	ExerciseService exerciseService = new ExerciseService();
	private int rowIndex;
	private int tableID;
	
	
	public static void main(String[] args) {
		try {
			Delete dialog = new Delete(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Delete(StudyTableModel tableModel,JTable table) {
		this.table = table;
		this.tableModel = tableModel;
		
		rowIndex = table.getSelectedRow();
		
		setModal(true);
		setTitle("Delete "+table.getName());
		setBounds(100, 100, 399, 162);
		getContentPane().setLayout(null);
		
		tableID = table.getName().equals("category") ? 1 : 2;
		
//		yes button
		btnYes = new JButton("Yes");
		btnYes.addActionListener(this);
		btnYes.setBounds(182, 82, 89, 23);
		getContentPane().add(btnYes);
		
		lblName = new JLabel();
		lblName.setBounds(10, 49, 363, 14);
		getContentPane().add(lblName);
		
//		get name of selected category/exercise
		Object x=table.getValueAt(table.getSelectedRow(), 2);
		lblName.setText((String) x);
		
//		cancel button
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(284, 82, 89, 23);
		getContentPane().add(btnCancel);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete this "+table.getName()+"?");
		lblAreYouSure.setBounds(10, 11, 363, 14);
		getContentPane().add(lblAreYouSure);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnYes) {
			actionPerformedBtnYes(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
	}

	private void actionPerformedBtnCancel(ActionEvent e) {
		dispose();
	}

	private void actionPerformedBtnYes(ActionEvent e) {
		int id = (int) table.getValueAt(rowIndex, 0);
		if(tableID == 1)
			categoryService.deleteCategory(id);
		else
			exerciseService.deleteExercise(id);
		
		int modelIndex = table.convertRowIndexToModel(rowIndex);
		
		table.getSelectionModel().clearSelection();
		tableModel.removeRow(modelIndex);
		
		dispose();
	}

}
