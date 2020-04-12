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
import controllers.MySQLTopicDAO;
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
	MySQLTopicDAO msTopDAO;
	TopicTableModel tblModel;
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy HH:mm a");
	
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 527, 431);
		contentPane.add(tabbedPane);
		
		JPanel pnlTopics = new JPanel();
		tabbedPane.addTab("Topics", null, pnlTopics, null);
		pnlTopics.setLayout(null);
		
			spTopicInfo = new JScrollPane();
			spTopicInfo.setBounds(10, 237, 499, 121);
			pnlTopics.add(spTopicInfo);
			
				txtTopicInfo = new JTextArea();
				spTopicInfo.setViewportView(txtTopicInfo);
				txtTopicInfo.setEditable(false);
				
			spTopics = new JScrollPane();
			spTopics.setBounds(10, 42, 499, 153);
			pnlTopics.add(spTopics);
				
				tblModel = new TopicTableModel();
				
				//Table header
				tblModel.addColumn("Selection");//0
				tblModel.addColumn("Name");//1
				tblModel.addColumn("Creation date");//2
				tblModel.addColumn("Favorite");//3
				tblModel.addColumn("");//4 //offline or online //hidden column
				tblModel.addColumn("Button");//5
				tblModel.addColumn("");//6
								
				tblTopics = new JTable();
				
				tblTopics.setModel(tblModel);
				
				//Column width
				tblTopics.getColumnModel().getColumn(4).setMinWidth(0);
				tblTopics.getColumnModel().getColumn(4).setMaxWidth(0);
				tblTopics.getColumnModel().getColumn(4).setWidth(0);
				
				tblTopics.getColumn("Button").setCellRenderer(new ButtonRenderer());
				tblTopics.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
				showTableContent();
				
				filterTopics("true", 3);
				
				spTopics.setViewportView(tblTopics);
			
			btnSelect = new JButton("Select");
			btnSelect.addActionListener(this);
			btnSelect.setBounds(395, 369, 114, 23);
			pnlTopics.add(btnSelect);
			
			btnNewTopic = new JButton("New topic");
			btnNewTopic.addActionListener(this);
			btnNewTopic.setBounds(395, 194, 114, 23);
			pnlTopics.add(btnNewTopic);
			
			cboTopicGroup = new JComboBox<String>();
			cboTopicGroup.setModel(new DefaultComboBoxModel<String>(new String[] {"Favorites", "My topics", "Online topics"}));
			cboTopicGroup.addItemListener(this);
			cboTopicGroup.setBounds(10, 10, 96, 22);
			pnlTopics.add(cboTopicGroup);
			
			txtSearchTopic = new JTextField();
			txtSearchTopic.setBounds(413, 11, 96, 20);
			pnlTopics.add(txtSearchTopic);
			
		JPanel pnlCategories = new JPanel();
		tabbedPane.addTab("New tab", null, pnlCategories, null);
		
		JPanel pnlExercises = new JPanel();
		tabbedPane.addTab("New tab", null, pnlExercises, null);
	}
	private void filterTopics(String string, int columnIndex) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(tblModel);
		tblTopics.setRowSorter(trs);
		
		trs.setRowFilter(RowFilter.regexFilter(string, columnIndex));
	}

	private void showTableContent() {
		//clear table content
		tblModel.setRowCount(0);
		
		msTopDAO=new MySQLTopicDAO();
		
		for(Topic t:msTopDAO.readTopics()) {
			Object row[] = {
					t.isTopicSelected(),t.getTopicName(),sdf.format(t.getTopicDatetime()),t.isTopicFavorite(),"offline"
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
			filterTopics("offline", 4);//column index==4 is hidden
		else if(topicGroupID == 2)
			filterTopics("online", 4);//column index==4 is hidden
		else
			filterTopics("true", 3);//favorite column
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
	}
}
