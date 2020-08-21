package guis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.Document;

import arrays.ArrayOffCategories;
import components.ButtonEnablement;
import components.TextFieldLimit;
import controller.MySQLUpdateClass;
import entities.Category;

public class Create extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private String title;
	private JTable table;
	private JTextField txtName;
	private JButton btnCreate;
	private JButton btnCancel;
	private JCheckBox chckbxAddToFavorities;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Create dialog = new Create(null, null, 100);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Create(String title, JTable table, int maxValue) {
		this.title = title;
		this.table = table;
		setModal(true);
		setTitle("New "+title);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		/*** Name text box ***/
		txtName = new JTextField();
		txtName.setBounds(10, 51, 363, 20);
		getContentPane().add(txtName);
		
		// limit field to x characters
		txtName.setDocument(new TextFieldLimit(maxValue));
		
		/*** Create button ***/
		btnCreate = new JButton("Create");
		btnCreate.setEnabled(false);
		btnCreate.addActionListener(this);
		btnCreate.setBounds(182, 163, 89, 23);
		getContentPane().add(btnCreate);
		
		// declare a button model
		ButtonModel model = btnCreate.getModel();
				
		// enable button when you start typing correctly
		ButtonEnablement butEna = new ButtonEnablement(model, maxValue);
		Document doc1=txtName.getDocument();
		butEna.addDocument(doc1);
		
		/*** Cancel button ***/
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(284, 163, 89, 23);
		getContentPane().add(btnCancel);
		
		chckbxAddToFavorities = new JCheckBox("Add to Favorities");
		chckbxAddToFavorities.setBounds(6, 78, 194, 23);
		getContentPane().add(chckbxAddToFavorities);
		
		/*** Name label ***/
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 26, 48, 14);
		getContentPane().add(lblName);
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnCreate) {
			actionPerformedBtnCreate(e);
		}		
	}

	private void actionPerformedBtnCreate(ActionEvent e) {
		if(title.equals("category")) {
			aOffCat=new ArrayOffCategories();
			if(aOffCat.getSize()<10) {
				Category c=new Category(aOffCat.generateID(), false, getTxtName(), new Date(), getChckbx());
				if(validateCategoryName()) {
					aOffCat.addCategory(c);
					aOffCat.saveOffCategories();
					refreshTable(aOffCat);
					ArrayOffCategories.clearTxtSelectedCategories();
					ArrayOffCategories.showSelectedCategories();
					dispose();
				}
				else
					showMessage("This category already exists");
			}
			else
				showMessage("You cannot create more than 10 categories");
		}
	}

	private void actionPerformedBtnCancel(ActionEvent e) {
		dispose();		
	}
	
}
