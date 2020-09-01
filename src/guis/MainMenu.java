package guis;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import components.ButtonEditor;
import components.ButtonRenderer;
import components.TopicTableModel;
import controller.MySQLTopicDAO;
import entities.Topic;

import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class MainMenu extends JFrame implements ItemListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TopicTableModel tblModel;
	MySQLTopicDAO msTopDAO;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");
	
	private JPanel contentPane;
	private JScrollPane spTopicInfo;
	private JTextArea txtTopicInfo;
	private JScrollPane spTopics;
	private JTable tblTopics;
	private JButton btnSelect;
	private JButton btnNewTopic;
	private JComboBox<String> cboTopicGroup;
	private JTextField txtSearchTopic;
	private int topicGroupID;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Main menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 492);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*** Tabbed pane ***/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 527, 431);
		contentPane.add(tabbedPane);
		
		
		/*** Topic panel ***/
		JPanel pnlTopics = new JPanel();
		tabbedPane.addTab("Topics", null, pnlTopics, null);
		pnlTopics.setLayout(null);
			
			/*** Info text box ***/
			spTopicInfo = new JScrollPane();
			spTopicInfo.setBounds(10, 237, 499, 121);
			pnlTopics.add(spTopicInfo);
			
				txtTopicInfo = new JTextArea();
				spTopicInfo.setViewportView(txtTopicInfo);
				txtTopicInfo.setEditable(false);
			
			/*** Topic table ***/
			spTopics = new JScrollPane();
			spTopics.setBounds(10, 42, 499, 153);
			pnlTopics.add(spTopics);
				
				tblModel = new TopicTableModel();
				
				// column headings
				tblModel.addColumn("");//0 // ID // hidden column
				tblModel.addColumn("Selection");//1
				tblModel.addColumn("Name");//2
				tblModel.addColumn("Creation date");//3
				tblModel.addColumn("Favorite");//4
				tblModel.addColumn("");//5 // offline or online // hidden column
				tblModel.addColumn("");//6
				tblModel.addColumn("");//7
								
				tblTopics = new JTable();
				
				tblTopics.setModel(tblModel);
				
				// column widths
				tblTopics.getColumnModel().getColumn(0).setMinWidth(0);
				tblTopics.getColumnModel().getColumn(0).setMaxWidth(0);
				tblTopics.getColumnModel().getColumn(0).setWidth(0);
				tblTopics.getColumnModel().getColumn(5).setMinWidth(0);
				tblTopics.getColumnModel().getColumn(5).setMaxWidth(0);
				tblTopics.getColumnModel().getColumn(5).setWidth(0);
				
				// set buttons Rename and Delete on table
				tblTopics.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer(6));
				tblTopics.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), tblTopics, 6));
				tblTopics.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer(7));
				tblTopics.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox(), tblTopics, 7));
				
				showTableContent();
				
				filterTopics("true", 4);
				
				spTopics.setViewportView(tblTopics);
			
			/*** Select button ***/
			btnSelect = new JButton("Select");
			btnSelect.addActionListener(this);
			btnSelect.setBounds(395, 369, 114, 23);
			pnlTopics.add(btnSelect);
			
			/*** New Topic button ***/
			btnNewTopic = new JButton("New topic");
			btnNewTopic.addActionListener(this);
			btnNewTopic.setBounds(395, 194, 114, 23);
			pnlTopics.add(btnNewTopic);
			
			/*** Groups of topics in combo box ***/
			cboTopicGroup = new JComboBox<String>();
			cboTopicGroup.setModel(new DefaultComboBoxModel<String>(new String[] {"Favorites", "My topics", "Online topics"}));
			cboTopicGroup.addItemListener(this);
			cboTopicGroup.setBounds(10, 10, 96, 22);
			pnlTopics.add(cboTopicGroup);
			
			/*** Search text box ***/
			txtSearchTopic = new JTextField();
			txtSearchTopic.setBounds(413, 11, 96, 20);
			pnlTopics.add(txtSearchTopic);
			
		JPanel pnlCategories = new JPanel();
		tabbedPane.addTab("New tab", null, pnlCategories, null);
		
		JPanel pnlExercises = new JPanel();
		tabbedPane.addTab("New tab", null, pnlExercises, null);
	}
	private void filterTopics(String input, int columnIndex) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(tblModel);
		tblTopics.setRowSorter(trs);
		
		trs.setRowFilter(RowFilter.regexFilter(input, columnIndex));
	}

	private void showTableContent() {
		// clear table content
		tblModel.setRowCount(0);
		
		msTopDAO = new MySQLTopicDAO();
		
		for(Topic t:msTopDAO.readTopics()) {
			Object row[] = {
					t.getTopicID(),
					t.isTopicSelected(),
					t.getTopicName(),
					sdf.format(t.getTopicDatetime()),
					t.isTopicFavorite(),
					"offline"
			};
			tblModel.addRow(row);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboTopicGroup) {
			itemStateChangedCboTopicGroup(e);
		}
	}
	protected void itemStateChangedCboTopicGroup(ItemEvent e) {
		topicGroupID = cboTopicGroup.getSelectedIndex();
		if(topicGroupID == 1)
			filterTopics("offline", 5);// column index == 5 is hidden
		else if(topicGroupID == 2)
			filterTopics("online", 5);// column index == 5 is hidden
		else
			filterTopics("true", 4);// favorite column
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewTopic) {
			actionPerformedBtnNewTopic(e);
		}
		if (e.getSource() == btnSelect) {
			actionPerformedBtnSelect(e);
		}
	}
	protected void actionPerformedBtnSelect(ActionEvent e) {
		
	}
	protected void actionPerformedBtnNewTopic(ActionEvent e) {
		NewTopic gui = new NewTopic();
		gui.setVisible(true);
	}
}
