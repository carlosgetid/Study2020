package guis;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import components.TopicTableModel;
import entities.Category;
import services.CategoryService;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Create extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3642710321787124367L;
	private JTextField txtName;
	private JButton btnCreate;
	private JButton btnCancel;
	private CategoryService categoryService = new CategoryService();
	private JCheckBox checkFavorities;
	public TopicTableModel tblModel;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");

	public static void main(String[] args) {
		try {
			Create dialog = new Create(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Create(TopicTableModel tblModel) {
		this.tblModel = tblModel;
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
		String name = txtName.getText();
		Boolean favorite = checkFavorities.isSelected();
		
		Category bean = new Category();
		bean.setCategoryName(name);
		bean.setCategoryFavorite(favorite);
		
//		call service
		int output = categoryService.insertCategory(bean);
		
		if(output != -1) {
			int ID;
			int rowCount = tblModel.getRowCount();
			
//			get the last category ID used and plus 1
			if(rowCount != 0)
				ID = (int) tblModel.getValueAt(rowCount-1, 0) + 1;
			else
				ID = 2001;
			
			Object row[] = {
					ID,
					false,
					name,
					sdf.format(new Date()),
					favorite,
					"offline"
					};
			tblModel.addRow(row);

			showMessage("Category created successfully");
		}
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
