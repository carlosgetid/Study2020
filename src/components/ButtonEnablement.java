package components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class ButtonEnablement implements DocumentListener{
	
	private ButtonModel buttonModel;
	private List<Document> documents = new ArrayList<Document>();
	private int maxValue;
	
	
	public ButtonEnablement(ButtonModel buttonModel, int maxValue) {
		this.buttonModel = buttonModel;
		this.maxValue = maxValue;
	}
	
	public void addDocument(Document doc) {
		doc.addDocumentListener(this);
		documents.add(doc);
		documentChange();
	}

	private void documentChange() {
		boolean buttonEneable = false;
		Document doc = documents.get(0);
		if(doc.getLength() > 0) {
			try {
				// allow [¿?!¡()' Spanish characters Numbers][¿?¡!()',.- spaces Spanish characters Numbers]||[spaces]+[¿?!¡()' Spanish Numbers]+[¿?¡!()',.- spaces Spanish characters Numbers]
				if(doc.getText(0, doc.getLength()).matches("[\\¿?¡!()'\\p{L}0-9][\\¿?¡!()',.\\-\\s\\p{L}0-9]{0,"+maxValue+"}||[\\s]+[\\¿?¡!()'\\p{L}0-9]+[\\¿?¡!()',.\\-\\s\\p{L}0-9]{0,"+maxValue+"}"))
					buttonEneable = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		buttonModel.setEnabled(buttonEneable);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		try {
			documentChange();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		try {
			documentChange();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		try {
			documentChange();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
