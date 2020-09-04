package guis;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.text.Document;

import components.ButtonEnablement;
import components.TextFieldLimit;
import components.StudyTableModel;
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
	
	private static final long serialVersionUID = -3642710321787124367L;
	private JTextField txtName;
	private JButton btnCreate;
	private JButton btnCancel;
	private CategoryService categoryService = new CategoryService();
	private JCheckBox checkFavorities;
	private StudyTableModel tblModel;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");
	private int id;
	private int maxValue;
	
	public static void main(String[] args) {
		try {
			Create dialog = new Create(null, 100);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Create(StudyTableModel tblModel, int maxValue) {
		this.tblModel = tblModel;
		this.maxValue = maxValue;
		if(maxValue == 50) {
			setTitle("Create category");
			id = categoryService.getNextAutoIncrementID();
		}
		else
			setTitle("Create exercise");
		
		setModal(true);
		setBounds(100, 100, 399, 236);
		getContentPane().setLayout(null);
		
//		name text field
		txtName = new JTextField();
		txtName.setBounds(10, 51, 363, 20);
		getContentPane().add(txtName);
		
//		limit characters in field
		txtName.setDocument(new TextFieldLimit(maxValue));
		
//		create button
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(this);
		btnCreate.setBounds(182, 163, 89, 23);
		getContentPane().add(btnCreate);
				
//		enable button when you start typing correctly
		ButtonModel model = btnCreate.getModel();
		ButtonEnablement buttonEnablement = new ButtonEnablement(model, maxValue);
		Document document = txtName.getDocument();
		buttonEnablement.addDocument(document);
		
//		favorite check box
		checkFavorities = new JCheckBox("Add to Favorities");
		checkFavorities.setBounds(6, 78, 194, 23);
		getContentPane().add(checkFavorities);
		
//		cancel button
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
//		create category
		if(maxValue == 50) {
			String name = txtName.getText();
			Boolean favorite = checkFavorities.isSelected();
			
			Category bean = new Category();
			bean.setCategoryName(name);
			bean.setCategoryFavorite(favorite);
			
			int output = categoryService.insertCategory(bean);
			
//			add category in table
			if(output != -1) {
				Object row[] = {
						id,
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
			dispose();
		}
	}
	
	private void showMessage(String string) {
		JOptionPane.showMessageDialog(this, string);		
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		dispose();
	}
}
