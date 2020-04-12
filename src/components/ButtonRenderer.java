package components;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton renderButton;

	public ButtonRenderer(int columnIndex) {
		setOpaque(true);
		renderButton = new JButton();
		
		String url = (columnIndex == 5) ? "/rename.png" : "/delete.png";
		// before press button
		Image img = new ImageIcon(this.getClass().getResource(url)).getImage();
		renderButton.setIcon(new ImageIcon(img));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if(isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		}
		else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText(value == null ? "" : value.toString());
		return renderButton;
	}
	
	
}
