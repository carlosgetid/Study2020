package guis;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import entities.Category;
import services.CategoryService;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Create extends JDialog implements ActionListener {
	private JTextField txtName;
	private JButton btnCreate;
	private JButton btnCancel;
	private CategoryService categoryService = new CategoryService();
	private JCheckBox checkFavorities;
	

	public static void main(String[] args) {
		try {
			Create dialog = new Create();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Create() {
		setBounds(100, 100, 399, 236);
		getContentPane().setLayout(null);
		{
			txtName = new JTextField();
			txtName.setBounds(10, 51, 363, 20);
			getContentPane().add(txtName);
		}
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(this);
		btnCreate.setBounds(182, 163, 89, 23);
		getContentPane().add(btnCreate);
		
		checkFavorities = new JCheckBox("Add to Favorities");
		checkFavorities.setBounds(6, 78, 194, 23);
		getContentPane().add(checkFavorities);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(284, 163, 89, 23);
		getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 26, 48, 14);
		getContentPane().add(lblName);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnCreate) {
			actionPerformedBtnCreate(e);
		}
	}
	protected void actionPerformedBtnCreate(ActionEvent e) {
		Category bean = new Category();
		bean.setCategoryName(txtName.getText());
		bean.setCategoryFavorite(checkFavorities.isSelected());
		int output = categoryService.insertCategory(bean);
		if(output != -1)
			showMessage("Category created");
		else
			showMessage("Error");
	}
	private void showMessage(String string) {
		JOptionPane.showMessageDialog(this, string);		
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		dispose();
	}
}
