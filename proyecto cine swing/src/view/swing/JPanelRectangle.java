package view.swing;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanelRectangle extends JPanel {

	public JPanelRectangle(Color color) {
		super();
		init(color);
	}

	private void init(Color color) {
		this.setBackground(color);
	}
}
