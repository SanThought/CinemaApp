package view.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.CinemaListener;

@SuppressWarnings("serial")
public class JPanelCinemaPrincipal extends JPanel {

	private JFrame jFrame;
	private BufferedImage bufferedImage;
	private InputStream inputStream;
	private JButton jButtonExit;
	private JButton jButtonEnter;
	private JButton jButtonLanguage;
	private JButton jButtonInfo;
	private JLabel jLabel;
	private JPanel jPanel;
	private JDialog jDialogLanguage;
	private JDialog jDialogInfo;
	private ActionListener listener;
	private Properties propertiesLaguages;

	public JPanelCinemaPrincipal(JFrame jFrame, CinemaListener cinemaListener, Properties propertiesLaguages)
			throws IOException {
		this.listener = cinemaListener;
		this.propertiesLaguages = propertiesLaguages;
		this.inputStream = this.getClass().getResourceAsStream("Cinema.jpg");
		this.bufferedImage = ImageIO.read(inputStream);
		this.jFrame = jFrame;
		this.jButtonExit = new JButton(propertiesLaguages.getProperty("exit"));
		this.jButtonEnter = new JButton(propertiesLaguages.getProperty("enter"));
		this.jButtonLanguage = new JButton(propertiesLaguages.getProperty("language"));
		this.jButtonInfo = new JButton(propertiesLaguages.getProperty("about"));
		this.jPanel = new JPanel();
		this.jLabel = new JLabel("<html><p align = 'center'>" + propertiesLaguages.getProperty("welcome"));
		init();
	}

	private void init() {
		this.setLayout(null);
		addButtons();
		jPanel.setBackground(new Color(0, 0, 0, 1));
		this.add(jLabel);
		this.add(jPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bufferedImage, 0, 0, jFrame.getWidth(), jFrame.getHeight(), this);
		jLabel.setFont(new Font("Vineta BT", Font.BOLD, (int) (jFrame.getWidth() * 0.033)));
		jLabel.setBounds(jFrame.getWidth() / 3, (int) (jFrame.getHeight() * 0.1), (int) (jFrame.getWidth() * 0.4),
				(int) (jFrame.getHeight() * 0.2));
		jPanel.setBounds((int) (jFrame.getWidth() / 2.3), (int) (jFrame.getHeight() / 2.3),
				(int) (jFrame.getWidth() * 0.15), (int) (jFrame.getHeight() * 0.45));
	}

	private void addButtons() {
		GridLayout gridLayout = new GridLayout(4, 1);
		gridLayout.setVgap(20);
		jButtonExit.setActionCommand(Actions.BUTTON_EXIT.name());
		jButtonEnter.setActionCommand(Actions.BUTTON_ENTER.name());
		jButtonLanguage.setActionCommand(Actions.BUTTON_lANGUAGE.name());
		jButtonInfo.setActionCommand(Actions.BUTTON_INFO.name());
		jButtonExit.addActionListener(listener);
		jButtonEnter.addActionListener(listener);
		jButtonLanguage.addActionListener(listener);
		jButtonInfo.addActionListener(listener);
		jPanel.setLayout(gridLayout);
		jPanel.add(jButtonEnter);
		jPanel.add(jButtonLanguage);
		jPanel.add(jButtonInfo);
		jPanel.add(jButtonExit);
	}

	public void showButtonLanguage(JFrame frame) {
		this.jDialogLanguage = new JDialog(frame);
		jDialogLanguage.setUndecorated(true);
		jDialogLanguage.setBounds(400, 400, 400, 80);
		jDialogLanguage.setLayout(new GridLayout(1, 3));
		JButton jButtonSpanish = new JButton(propertiesLaguages.getProperty("spanish"));
		JButton jButtonEnglish = new JButton(propertiesLaguages.getProperty("english"));
		JButton jButtonClose = new JButton(propertiesLaguages.getProperty("exit"));
		jButtonSpanish.setActionCommand(Actions.BUTTON_SPANISH.name());
		jButtonEnglish.setActionCommand(Actions.BUTTON_ENGLISH.name());
		jButtonClose.setActionCommand(Actions.BUTTON_CLOSE.name());
		jButtonSpanish.addActionListener(listener);
		jButtonEnglish.addActionListener(listener);
		jButtonClose.addActionListener(listener);
		jDialogLanguage.add(jButtonSpanish);
		jDialogLanguage.add(jButtonEnglish);
		jDialogLanguage.add(jButtonClose);
		jDialogLanguage.repaint();
		jDialogLanguage.setVisible(true);
	}

	public void changeButtonsLanguage() {
		jButtonExit.setText(propertiesLaguages.getProperty("exit"));
		jButtonEnter.setText(propertiesLaguages.getProperty("enter"));
		jButtonLanguage.setText(propertiesLaguages.getProperty("language"));
		jButtonInfo.setText(propertiesLaguages.getProperty("about"));
		jLabel.setText("<html><p align = 'center'>" + propertiesLaguages.getProperty("welcome"));
	}

	public void closeButtonLanguage() {
		jDialogLanguage.dispose();
	}

	public void setProperties(Properties propertiesLaguages) {
		this.propertiesLaguages = propertiesLaguages;
	}

	public void showInfo(JFrame jFrame) {
		this.jDialogInfo = new JDialog(jFrame);
		jDialogInfo.setBounds(600, 100, 400, 200);
		jDialogInfo.add(new JLabel("<html><p align = 'center'>" + propertiesLaguages.getProperty("info")));
		jDialogInfo.setVisible(true);
	}
}