package guis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame implements ItemListener, ActionListener {

	private JPanel contentPane;
	private JScrollPane spTopicInfo;
	private JTextArea txtTopicInfo;
	private JScrollPane spTopics;
	private JTable tblTopics;
	private JButton btnSelect;
	private JButton btnNewTopic;
	private JComboBox cboTopicGroup;
	private JTextField txtSearchTopic;
	
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
			
				tblTopics = new JTable();
				spTopics.setViewportView(tblTopics);
			
			btnSelect = new JButton("Select");
			btnSelect.addActionListener(this);
			btnSelect.setBounds(395, 369, 114, 23);
			pnlTopics.add(btnSelect);
			
			btnNewTopic = new JButton("New topic");
			btnNewTopic.addActionListener(this);
			btnNewTopic.setBounds(395, 194, 114, 23);
			pnlTopics.add(btnNewTopic);
			
			cboTopicGroup = new JComboBox();
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
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboTopicGroup) {
			itemStateChangedCboTopicGroup(e);
		}
	}
	protected void itemStateChangedCboTopicGroup(ItemEvent e) {
		
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
