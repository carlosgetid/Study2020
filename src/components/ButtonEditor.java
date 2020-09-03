package components;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import guis.Delete;
import guis.Rename;

public class ButtonEditor extends DefaultCellEditor{
	
	protected JButton button;
	private String label;
	private boolean isPushed;
	private JTable table;
 	private int columnIndex;
 	private TopicTableModel tableModel;
	
	public ButtonEditor(JCheckBox checkBox, JTable table, int columnIndex, TopicTableModel tableModel) {
		super(checkBox);
		this.table = table;
		this.columnIndex = columnIndex;
		this.tableModel = tableModel;
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getCellEditorValue();
			}
		});

		String url = (columnIndex == 6) ? "/rename.png" : "/delete.png";
		// after press button
		Image img = new ImageIcon(this.getClass().getResource(url)).getImage();
		button.setIcon(new ImageIcon(img));
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if(isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		}
		else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null ) ? "" :value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}
	
	@Override
	public Object getCellEditorValue() {
		if(isPushed) {
			if(columnIndex == 6) {
				Rename gui = new Rename(table, 50);
				gui.setVisible(true);				
			}
			else {
				Delete gui = new Delete(tableModel, table);
				gui.setVisible(true);
			}
		}
		isPushed = false;
		return label;
	}
	
	@Override
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
}
}
