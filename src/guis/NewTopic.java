package guis;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import components.ButtonEditor;
import components.ButtonRenderer;
import components.CategoryTableListener;
import components.ExerciseTableListener;
import components.TopicTableModel;
import controller.MySQLCategoryDAO;
import entities.Category;
import entities.Exercise;
import services.CategoryService;
import services.ExerciseService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class NewTopic extends JFrame implements ActionListener, ItemListener, KeyListener {

	private static final long serialVersionUID = -9139193141198867504L;
	private TopicTableModel tblModel;
	private TopicTableModel exerciseTableModel;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");
	
	private JPanel contentPane;
	private JTextField txtTopicName;
	private JTextField txtSearchCategory;
	private JTextField txtSearchExercise;
	private JTable tblCategories;
	private JTable tblExercises;
	private JButton btnAdd;
	private JComboBox<String> cboCategoryGroup;
	private JComboBox<String> cboExerciseGroup;
	private JButton btnNewCategory;
	private JButton btnNewExercise;
	private JScrollPane spSelectedCategories;
	private JTextArea txtSelectedCategories;
	private JScrollPane spSelectedExercises;
	private JTextArea txtSelectedExercises;
	private int categoryGroupID;
	private int exerciseGroupID;
	
	
	CategoryTableListener tableListener;
	ExerciseTableListener exerciseTableListener;
	
	private CategoryService categoryService = new CategoryService();
	private ExerciseService exerciseService = new ExerciseService();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewTopic frame = new NewTopic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewTopic() {
		setTitle("New topic");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 696);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		text box name of topic
		txtTopicName = new JTextField();
		txtTopicName.setBounds(84, 30, 96, 20);
		contentPane.add(txtTopicName);
		
//		text box to search categories
		txtSearchCategory = new JTextField();
		txtSearchCategory.addKeyListener(this);
		txtSearchCategory.setBounds(483, 83, 96, 20);
		contentPane.add(txtSearchCategory);
		
//		category table
		JScrollPane spCategories = new JScrollPane();
		spCategories.setBounds(28, 119, 552, 115);
		contentPane.add(spCategories);
		
			tblModel = new TopicTableModel();
		
			tblCategories = new JTable();
			
			tblCategories.setModel(tblModel);
			
//			hidden columns
			tblCategories.getColumnModel().getColumn(0).setMinWidth(0);
			tblCategories.getColumnModel().getColumn(0).setMaxWidth(0);
			tblCategories.getColumnModel().getColumn(0).setWidth(0);
			tblCategories.getColumnModel().getColumn(5).setMinWidth(0);
			tblCategories.getColumnModel().getColumn(5).setMaxWidth(0);
			tblCategories.getColumnModel().getColumn(5).setWidth(0);
			
			// set buttons Rename and Delete on table
			tblCategories.setName("category");			
			tblCategories.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer(6));
			tblCategories.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), tblCategories, 6, tblModel));
			tblCategories.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer(7));
			tblCategories.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox(), tblCategories, 7, tblModel));
			
			loadCategories();
			
//			only show favorite categories
			filterCategories("true", 4);
			
			spCategories.setViewportView(tblCategories);
		
//		text box to search exercises
		txtSearchExercise = new JTextField();
		txtSearchExercise.addKeyListener(this);
		txtSearchExercise.setBounds(483, 362, 96, 20);
		contentPane.add(txtSearchExercise);
		
//		exercise table
		JScrollPane spExercises = new JScrollPane();
		spExercises.setBounds(28, 405, 552, 115);
		contentPane.add(spExercises);
		
			exerciseTableModel = new TopicTableModel();
			
			tblExercises = new JTable();
			
			tblExercises.setModel(exerciseTableModel);
			
//			hidden columns
			tblExercises.getColumnModel().getColumn(0).setMinWidth(0);
			tblExercises.getColumnModel().getColumn(0).setMaxWidth(0);
			tblExercises.getColumnModel().getColumn(0).setWidth(0);
			tblExercises.getColumnModel().getColumn(5).setMinWidth(0);
			tblExercises.getColumnModel().getColumn(5).setMaxWidth(0);
			tblExercises.getColumnModel().getColumn(5).setWidth(0);
			
			loadExercises();
			
//			only show favorite exercises
			filterExercises("true", 4);
			
			spExercises.setViewportView(tblExercises);
			
//		add topic button
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(490, 30, 89, 23);
		contentPane.add(btnAdd);
		
//		Groups of categories in combo box 
		cboCategoryGroup = new JComboBox<String>();
		cboCategoryGroup.setModel(new DefaultComboBoxModel<String>(new String[] {"Favorites", "My categories", "Online categories"}));
		cboCategoryGroup.addItemListener(this);
		cboCategoryGroup.setBounds(84, 82, 96, 22);
		contentPane.add(cboCategoryGroup);
		
//		Groups of exercises in combo box 
		cboExerciseGroup = new JComboBox<String>();
		cboExerciseGroup.setModel(new DefaultComboBoxModel<String>(new String[] {"Favorites", "My exercises", "Online exercises"}));
		cboExerciseGroup.addItemListener(this);
		cboExerciseGroup.setBounds(84, 361, 96, 22);
		contentPane.add(cboExerciseGroup);
		
//		new category button
		btnNewCategory = new JButton("New category");
		btnNewCategory.addActionListener(this);
		btnNewCategory.setBounds(425, 233, 154, 23);
		contentPane.add(btnNewCategory);
		
//		new exercise button
		btnNewExercise = new JButton("New exercise");
		btnNewExercise.addActionListener(this);
		btnNewExercise.setBounds(425, 503, 154, 23);
		contentPane.add(btnNewExercise);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(28, 33, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(28, 85, 89, 14);
		contentPane.add(lblCategory);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(425, 86, 48, 14);
		contentPane.add(lblSearch);
		
		lblSearch = new JLabel("Search");
		lblSearch.setBounds(425, 365, 48, 14);
		contentPane.add(lblSearch);
		
		JLabel lblExercises = new JLabel("Exercises");
		lblExercises.setBounds(28, 365, 48, 14);
		contentPane.add(lblExercises);
		
//		text box for selected categories
		spSelectedCategories = new JScrollPane();
		spSelectedCategories.setBounds(28, 267, 550, 77);
		contentPane.add(spSelectedCategories);
		
			txtSelectedCategories = new JTextArea();
			txtSelectedCategories.setWrapStyleWord(true);
			txtSelectedCategories.setLineWrap(true);
			txtSelectedCategories.setTabSize(2);
			spSelectedCategories.setViewportView(txtSelectedCategories);
			txtSelectedCategories.setEditable(false);
		
//			read changes by checkboxes
			tableListener = new CategoryTableListener(tblModel, tblCategories, txtSelectedCategories);
			tblModel.addTableModelListener(tableListener);
			
//		text box for selected exercises
		spSelectedExercises = new JScrollPane();
		spSelectedExercises.setBounds(28, 544, 550, 77);
		contentPane.add(spSelectedExercises);
		
			txtSelectedExercises = new JTextArea();
			txtSelectedExercises.setWrapStyleWord(true);
			txtSelectedExercises.setLineWrap(true);
			txtSelectedExercises.setTabSize(2);
			spSelectedExercises.setViewportView(txtSelectedExercises);
			txtSelectedExercises.setEditable(false);
			
//			read changes by checkboxes
			exerciseTableListener = new ExerciseTableListener(exerciseTableModel, tblExercises, txtSelectedExercises);
			exerciseTableModel.addTableModelListener(exerciseTableListener);
	}
	
	private void filterExercises(String input, int columnIndex) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(exerciseTableModel);
		tblExercises.setRowSorter(trs);
		
		trs.setRowFilter(RowFilter.regexFilter(input, columnIndex));
	}

	private void loadExercises() {
		ArrayList<Exercise> list = exerciseService.listAllExercises();
		
//		read each category and put its values in a array
		for(Exercise bean:list) {
			Object row[] = {
					bean.getExerciseID(),
					bean.isExerciseSelected(),
					bean.getExerciseText(),
					sdf.format(bean.getExerciseDatetime()),
					bean.isExerciseFavorite(),
					"offline"
			};
			
			exerciseTableModel.addRow(row);
		}
	}

	private void filterCategories(String input, int columnIndex) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(tblModel);
		tblCategories.setRowSorter(trs);
		
		trs.setRowFilter(RowFilter.regexFilter(input, columnIndex));
	}
	
	
	private void loadCategories() {
		ArrayList<Category> list = categoryService.listAllCategories();
		
//		read each category and put its values in a array
		for(Category bean:list) {
			Object row[] = {
					bean.getCategoryID(),
					bean.isCategorySelected(),
					bean.getCategoryName(),
					sdf.format(bean.getCategoryDatetime()),
					bean.isCategoryFavorite(),
					"offline"
			};
			
			tblModel.addRow(row);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewExercise) {
			actionPerformedBtnNewExercise(e);
		}
		if (e.getSource() == btnNewCategory) {
			actionPerformedBtnNewCategory(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
	}

	protected void actionPerformedBtnNewCategory(ActionEvent e) {
		Create gui = new Create(tblModel, 50);
		gui.setVisible(true);
	}

	protected void actionPerformedBtnNewExercise(ActionEvent e) {
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboExerciseGroup) {
			itemStateChangedCboExerciseGroup(e);
		}
		if (e.getSource() == cboCategoryGroup) {
			itemStateChangedCboCategoryGroup(e);
		}
	}
	
	protected void itemStateChangedCboCategoryGroup(ItemEvent e) {
		categoryGroupID = cboCategoryGroup.getSelectedIndex();
		if(categoryGroupID == 1)
			filterCategories("offline", 5);// column index == 5 is hidden
		else if(categoryGroupID == 2)
			filterCategories("online", 5);// column index == 5 is hidden
		else
			filterCategories("true", 4);// favorite column
	}

	protected void itemStateChangedCboExerciseGroup(ItemEvent e) {
		exerciseGroupID = cboExerciseGroup.getSelectedIndex();
		if(exerciseGroupID == 1)
			filterExercises("offline", 5);// column index == 5 is hidden
		else if(categoryGroupID == 2)
			filterExercises("online", 5);// column index == 5 is hidden
		else
			filterExercises("true", 4);// favorite column
	
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtSearchCategory) {
			keyReleasedTxtSearchCategory(e);
		}
		if (e.getSource() == txtSearchExercise) {
			keyReleasedTxtSearchExercise(e);
		}
	}
	private void keyReleasedTxtSearchExercise(KeyEvent e) {
		String input = txtSearchExercise.getText();
		filterExercises(input, 2);
	}

	public void keyTyped(KeyEvent e) {
	}
	protected void keyReleasedTxtSearchCategory(KeyEvent e) {
		String input = txtSearchCategory.getText();
		filterCategories(input, 2);
	}
}
