package view.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Chair;
import utilities.Constants;

@SuppressWarnings("serial")
public class JPanelCinemaChairs extends JPanel implements KeyListener {

	private Chair[][] chairs;
	private int fila;
	private int columna;
	private int chairSizeX;
	private int chairSizeY;
	private JFrame jFrame;

	public JPanelCinemaChairs(JFrameCinema jFrameCinema) throws IOException {
		this.chairs = new Chair[Constants.CHAIR_NUMBER_X][Constants.CHAIR_NUMBER_Y];
		this.setBackground(Color.LIGHT_GRAY);
		for (int i = 0; i < chairs.length; i++) {
			for (int j = 0; j < chairs[i].length; j++) {
				this.chairs[i][j] = new Chair(false);
			}
		}
		this.addKeyListener(this);
		this.jFrame = jFrameCinema;
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		this.chairSizeX = (int) (jFrame.getWidth() * 0.043);
		this.chairSizeY = (int) (jFrame.getHeight() * 0.048);
		for (int i = 0; i < chairs.length; i++) {
			for (int j = 0; j < chairs[i].length; j++) {
				g.setColor(columna == j && fila == i ? Color.RED : Color.BLACK);
				g.drawRect((chairSizeX + chairSizeX / 2) * j, (chairSizeY + chairSizeY / 2) * i, chairSizeX,
						chairSizeY);
				if (chairs[i][j].isTaken()) {
					g.fillRect((chairSizeX + chairSizeX / 2) * j, (chairSizeY + chairSizeY / 2) * i, chairSizeX,
							chairSizeY);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			columna -= 1;
		} else if (e.getKeyCode() == 38) {
			fila -= 1;
		} else if (e.getKeyCode() == 39) {
			columna += 1;
		} else if (e.getKeyCode() == 40) {
			fila += 1;
		}
		if (e.getKeyCode() == 10) {
			chairs[fila][columna].setTaken(!chairs[fila][columna].isTaken());
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public Chair[][] getChairs() {
		return chairs;
	}

	public void setChairs(Chair[][] chairs2) {
		this.chairs = chairs2;
	}
}
