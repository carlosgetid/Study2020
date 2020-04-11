package components;

import javax.swing.table.DefaultTableModel;

public class TopicTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean isCellEditable(int row, int column) {
		if(column==1||column==2)
			return false;
		return true;
	}
	 
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
	        case 0:
	            return Boolean.class;//show JCheckBox
	        case 1:
	            return String.class;
	        case 2:
	            return String.class;
	        case 3:
	            return Boolean.class;//show JCheckBox
	        case 4:
	            return String.class;
	        default:
	            return String.class;
		}
	}
}
