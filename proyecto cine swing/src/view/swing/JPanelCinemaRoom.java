package view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.CinemaListener;
import models.Chair;

@SuppressWarnings("serial")
public class JPanelCinemaRoom extends JPanel {

	private JPanelCinemaChairs jPanelCinemaChairs;
	private JPanel jPanelScreen;
	private JPanel jpanelLetters;
	private JPanel jPanelOptions;
	private JLabel jLabel;
	private ActionListener listener;

	public JPanelCinemaRoom(JFrameCinema jFrameCinema, CinemaListener cinemaListener) throws IOException {
		this.jPanelCinemaChairs = new JPanelCinemaChairs(jFrameCinema);
		this.jPanelScreen = new JPanel();
		this.jpanelLetters = new JPanel();
		this.jPanelOptions = new JPanel();
		this.jLabel = new JLabel("<html><p align = 'center'>pantalla");
		this.listener = cinemaListener;
		init();
	}

	private void init() {
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout(20, 50));
		addScreen();
		addLetters();
		addOptions();
		this.revalidate();
		this.add(jPanelCinemaChairs, BorderLayout.CENTER);
	}

	private void addOptions() {
		JButton jButton = new JButton("volver");
		JButton jButtonSave = new JButton("guardar");
		jButton.setActionCommand(Actions.BUTTON_BACK.name());
		jButton.addActionListener(listener);
		jButtonSave.setActionCommand(Actions.BUTTON_SAVE.name());
		jButtonSave.addActionListener(listener);
		jPanelOptions.add(jButton);
		jPanelOptions.add(jButtonSave);
		this.add(jPanelOptions, BorderLayout.SOUTH);
	}

	private void addLetters() {
		GridLayout gridLayout = new GridLayout(10, 1);
		JLabel[] letters = { new JLabel("A"), new JLabel("B"), new JLabel("C"), new JLabel("D"), new JLabel("E"),
				new JLabel("F"), new JLabel("G"), new JLabel("H"), new JLabel("I"), new JLabel("J"), };
		jpanelLetters.setBackground(Color.LIGHT_GRAY);
		jpanelLetters.setLayout(gridLayout);
		for (int i = 0; i < letters.length; i++) {
			jpanelLetters.add(letters[i]);
		}
		this.add(jpanelLetters, BorderLayout.WEST);
	}

	public void addScreen() {
		jPanelScreen.setBackground(Color.LIGHT_GRAY);
		jPanelScreen.setLayout(new GridLayout(2, 3));
		jPanelScreen.add(new JPanelRectangle(Color.LIGHT_GRAY));
		jPanelScreen.add(jLabel);
		jPanelScreen.add(new JPanelRectangle(Color.LIGHT_GRAY));
		jPanelScreen.add(new JPanelRectangle(Color.LIGHT_GRAY));
		jPanelScreen.add(new JPanelRectangle(Color.BLACK));
		jPanelScreen.add(new JPanelRectangle(Color.LIGHT_GRAY));
		this.add(jPanelScreen, BorderLayout.NORTH);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		jLabel.setFont(new Font("arial", Font.BOLD, 20));
	}

	public void changeFocus() {
		jPanelCinemaChairs.setFocusable(true);
		jPanelCinemaChairs.requestFocus(true);
	}
	
	public void setchairs(Chair[][] chairs) {
		jPanelCinemaChairs.setChairs(chairs);
	}

	public Chair[][] getChairs() {
		return jPanelCinemaChairs.getChairs();
	}

	public void addDialog() {
		JDialog jDialog = new JDialog();
		jDialog.setLayout(new GridLayout(1, 1));
		jDialog.add(new JLabel("<html><p align = 'center'>guardado con exito"));
		jDialog.setBounds(500, 500, 300, 200);
		jDialog.setVisible(true);
		changeFocus();
	}
}
