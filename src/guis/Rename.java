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
import services.CategoryService;

import java.awt.event.ActionEvent;

public class Rename extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JButton btnSave;
	private JButton btnCancel;
	private JTable table;
	CategoryService categoryService = new CategoryService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Rename dialog = new Rename(null, 100);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Rename(JTable table, int maxValue) {
		this.table=table;
		setModal(true);
		setTitle("Rename "+table.getName());
		setBounds(100, 100, 399, 162);
		getContentPane().setLayout(null);
		
		/*** Name text box ***/
		txtName = new JTextField();
		txtName.setBounds(10, 51, 363, 20);
		getContentPane().add(txtName);
		
		// limit field to x characters
		txtName.setDocument(new TextFieldLimit(maxValue));
		
		// get selected category name
		Object x = table.getValueAt(table.getSelectedRow(), 2);
		txtName.setText((String) x);
		
		/*** Save button ***/
		btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(this);
		btnSave.setBounds(182, 82, 89, 23);
		getContentPane().add(btnSave);
		
		ButtonModel model = btnSave.getModel();
		
		// enable button when you start typing correctly
		ButtonEnablement butEna = new ButtonEnablement(model,maxValue);
		Document doc1 = txtName.getDocument();
		butEna.addDocument(doc1);
		
		/*** Cancel button ***/
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(284, 82, 89, 23);
		getContentPane().add(btnCancel);
		
		/*** Name label ***/
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
		
		Category bean = new Category();
		bean.setCategoryID(id);
		bean.setCategoryName(name);
		bean.setCategoryFavorite(favorite);
		
//		call service
		categoryService.updateCategory(bean);
		
		table.setValueAt(name, table.getSelectedRow(), 2);
		
		dispose();
	}
}
