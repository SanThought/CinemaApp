package view.swing;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import controller.CinemaListener;
import models.Chair;

public class JFrameCinema extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanelCinemaPrincipal jPanelCinemaPrincipal;
	private JPanelCinemaRoom jPanelCinemaRoom;

	public JFrameCinema(CinemaListener cinemaListener, Properties propertiesLaguages) throws IOException {
		super(propertiesLaguages.get("title").toString());
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(639, 469));
		this.jPanelCinemaPrincipal = new JPanelCinemaPrincipal(this, cinemaListener, propertiesLaguages);
		this.jPanelCinemaRoom = new JPanelCinemaRoom(this, cinemaListener);
		init();
	}

	private void init() {
		this.add(jPanelCinemaPrincipal);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void openMainFrame() {
		this.remove(jPanelCinemaRoom);
		this.add(jPanelCinemaPrincipal);
		this.repaint();
		this.revalidate();
	}

	public void drawChairs(Chair[][] chairs) {
		this.remove(jPanelCinemaPrincipal);
		this.add(jPanelCinemaRoom);
		jPanelCinemaRoom.changeFocus();
		jPanelCinemaRoom.setchairs(chairs);
		this.repaint();
		this.revalidate();
	}

	public void changeLanguage() {
		jPanelCinemaPrincipal.showButtonLanguage(this);
	}

	public void closeChangeLanguage() {
		jPanelCinemaPrincipal.closeButtonLanguage();
		changeButtonsLanguage();
	}

	public void changeButtonsLanguage() {
		jPanelCinemaPrincipal.changeButtonsLanguage();
	}

	public void setProperties(Properties propertiesLaguages) {
		jPanelCinemaPrincipal.setProperties(propertiesLaguages);
	}

	public void showInfo() {
		jPanelCinemaPrincipal.showInfo(this);
	}

	public Chair[][] getChairs() {
		return jPanelCinemaRoom.getChairs();
	}

	public void addDialog() {
		jPanelCinemaRoom.addDialog();
	}
}
