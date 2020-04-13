package components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextFieldLimit extends PlainDocument{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxValue;
	
	public TextFieldLimit(int maxValue) {
		super();
		this.maxValue=maxValue;
	}
	
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null)
			return;
		if((getLength()+str.length()) <= maxValue)
			super.insertString(offs, str, a);
	}
}
