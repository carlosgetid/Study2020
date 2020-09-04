package guis;

import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.Document;

import components.ButtonEnablement;
import components.TextFieldLimit;
import entities.Category;
import entities.Exercise;
import services.CategoryService;
import services.ExerciseService;

import java.awt.event.ActionEvent;

public class Rename extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JButton btnSave;
	private JButton btnCancel;
	private JTable table;
	CategoryService categoryService = new CategoryService();
	ExerciseService exerciseService = new ExerciseService();
	private int tableID;

	public static void main(String[] args) {
		try {
			Rename dialog = new Rename(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Rename(JTable table) {
		this.table=table;
		setModal(true);
		setTitle("Rename "+table.getName());
		setBounds(100, 100, 399, 162);
		getContentPane().setLayout(null);
		
		int maxValue = 0;
		
		if(table.getName().equals("category")) {
			tableID = 1;
			maxValue = 50;
		}
		else {
			tableID = 2;
			maxValue = 200;
		}
		
//		text box for name
		txtName = new JTextField();
		txtName.setBounds(10, 51, 363, 20);
		getContentPane().add(txtName);
		
//		limit characters in field
		txtName.setDocument(new TextFieldLimit(maxValue));
		
//		get name of selected category
		Object x = table.getValueAt(table.getSelectedRow(), 2);
		txtName.setText((String) x);
		
//		save button
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(this);
		btnSave.setBounds(182, 82, 89, 23);
		getContentPane().add(btnSave);
		
//		enable button when you start typing correctly
		ButtonModel model = btnSave.getModel();
		ButtonEnablement buttonEnablement = new ButtonEnablement(model, maxValue);
		Document document = txtName.getDocument();
		buttonEnablement.addDocument(document);
		
//		button cancel
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(284, 82, 89, 23);
		getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 26, 48, 14);
		getContentPane().add(lblName);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnSave) {
			actionPerformedBtnSave(e);
		}
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		dispose();
	}

	protected void actionPerformedBtnSave(ActionEvent e) {
		int id = (int) table.getValueAt(table.getSelectedRow(), 0);
		String name = txtName.getText();
		boolean favorite = (boolean) table.getValueAt(table.getSelectedRow(), 4);
		
		if(tableID == 1) {
			Category bean = new Category();
			bean.setCategoryID(id);
			bean.setCategoryName(name);
			bean.setCategoryFavorite(favorite);
			
			categoryService.updateCategory(bean);
		}
		else {
			Exercise bean = new Exercise();
			bean.setExerciseID(id);
			bean.setExerciseText(name);
			bean.setExerciseFavorite(favorite);
			
			exerciseService.updateExercise(bean);
		}
		
		table.setValueAt(name, table.getSelectedRow(), 2);
		
		dispose();
	}
}
