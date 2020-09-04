package components;

import javax.swing.table.DefaultTableModel;

public class StudyTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 1L;
	
	public StudyTableModel() {
//		column headings
		addColumn("");//0 // ID // hidden column
		addColumn("Selection");//1
		addColumn("Name");//2
		addColumn("Creation date");//3
		addColumn("Favorite");//4
		addColumn("");//5 // offline or online // hidden column
		addColumn("");//6
		addColumn("");//7
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		if(column==0||column==2||column==3)
			return false;
		return true;
	}
	 
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0:
	            return String.class;
	        case 1:
	            return Boolean.class;// show JCheckBox
	        case 2:
	            return String.class;
	        case 3:
	            return String.class;
	        case 4:
	            return Boolean.class;// show JCheckBox
	        case 5:
	            return String.class;
	        default:
	            return String.class;
		}
	}
}
