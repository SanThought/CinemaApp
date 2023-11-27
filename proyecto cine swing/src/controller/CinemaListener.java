package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import models.Chair;
import persistence.FileOperationsLanguages;
import utilities.Constants;
import view.swing.Actions;
import view.swing.JFrameCinema;

public class CinemaListener implements ActionListener {
	private JFrameCinema jFrameCinema;
	private Properties propertiesLaguages;
	private static final String FILE_DIR = "resources/chairStates";

	public CinemaListener() {
		readLenguage();
		try {
			showFrame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case BUTTON_EXIT:
			System.exit(0);
			break;
		case BUTTON_ENTER:
			jFrameCinema.drawChairs(readStates());
			break;
		case BUTTON_INFO:
			jFrameCinema.showInfo();
			break;
		case BUTTON_lANGUAGE:
			jFrameCinema.changeLanguage();
			break;
		case BUTTON_CLOSE:
			jFrameCinema.closeChangeLanguage();
			break;
		case BUTTON_ENGLISH:
			changeToEnglish();
			jFrameCinema.closeChangeLanguage();
			break;
		case BUTTON_SPANISH:
			changeToEspanish();
			jFrameCinema.closeChangeLanguage();
			break;
		case BUTTON_BACK:
			jFrameCinema.openMainFrame();
			break;
		case BUTTON_SAVE:
			saveInfo();
			jFrameCinema.addDialog();
			break;
		default:
			break;
		}
	}

	private Chair[][] readStates() {
		Chair[][] chairs = new Chair[Constants.CHAIR_NUMBER_X][Constants.CHAIR_NUMBER_Y];
		try {
			FileReader fileReader = new FileReader(FILE_DIR);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			for (int i = 0; i < chairs.length; i++) {
				String states = bufferedReader.readLine();
				String[] split = states.split(" ");
				for (int j = 0; j < chairs[i].length; j++) {
					if (split[j].equals("1")) {
						chairs[i][j] = new Chair(true);
					} else {
						chairs[i][j] = new Chair(false);
					}
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chairs;
	}

	private void saveInfo() {
		Chair[][] chairs = jFrameCinema.getChairs();
		try {
			FileWriter fileWriter = new FileWriter(FILE_DIR);
			fileWriter.write(chairFormat(chairs));
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void changeToEspanish() {
		FileOperationsLanguages.changeTheCurrentLanguage("spanish");
		readLenguage();
		jFrameCinema.setProperties(propertiesLaguages);
	}

	private void changeToEnglish() {
		FileOperationsLanguages.changeTheCurrentLanguage("english");
		readLenguage();
		jFrameCinema.setProperties(propertiesLaguages);
	}

	public void showFrame() throws IOException {
		if (jFrameCinema == null)
			this.jFrameCinema = new JFrameCinema(this, propertiesLaguages);
		jFrameCinema.setVisible(true);
	}

	private String chairFormat(Chair[][] chairs) {
		String states = "";
		for (int i = 0; i < chairs.length; i++) {
			for (int j = 0; j < chairs[i].length; j++) {
				if (chairs[i][j].isTaken()) {
					states += "1 ";
				} else {
					states += "0 ";
				}
			}
			if (i != chairs.length - 1) {
				states += "\n";
			}

		}
		return states;
	}

	private Properties readLenguage() {
		String LastLanguageUsed = FileOperationsLanguages.readLastLanguageUsed();
		String urlLanguage = "resources/" + LastLanguageUsed + ".properties";
		propertiesLaguages = FileOperationsLanguages.readLanguageSelected(urlLanguage);
		return propertiesLaguages;
	}
}
