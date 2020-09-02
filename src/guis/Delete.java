package guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;

import components.TopicTableModel;
import services.CategoryService;

public class Delete extends JDialog implements ActionListener {

	private static final long serialVersionUID = -8889868309940547562L;
	private JTable table ;
	private JButton btnYes;
	private JLabel lblName;
	private JButton btnCancel;
	private TopicTableModel tableModel;
	CategoryService categoryService = new CategoryService();

	public static void main(String[] args) {
		try {
			Delete dialog = new Delete(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Delete(TopicTableModel tableModel,JTable table) {
		this.table = table;
		this.tableModel = tableModel;
		setModal(true);
		setTitle("Delete "+table.getName());
		setBounds(100, 100, 399, 162);
		getContentPane().setLayout(null);
		
		/*** Yes button ***/
		btnYes = new JButton("Yes");
		btnYes.addActionListener(this);
		btnYes.setBounds(182, 82, 89, 23);
		getContentPane().add(btnYes);
		
		lblName = new JLabel();
		lblName.setBounds(10, 49, 363, 14);
		getContentPane().add(lblName);
		
//		get selected category name
		Object x=table.getValueAt(table.getSelectedRow(), 2);
		lblName.setText((String) x);
		
		/*** Cancel button ***/
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(284, 82, 89, 23);
		getContentPane().add(btnCancel);
		
		/*** Question label ***/
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
		int rowIndex = table.getSelectedRow();
		int id = (int) table.getValueAt(rowIndex, 0);
		categoryService.deleteCategory(id);
		
		table.getSelectionModel().clearSelection();
		
		tableModel.removeRow(rowIndex);
		
		dispose();
	}

}
