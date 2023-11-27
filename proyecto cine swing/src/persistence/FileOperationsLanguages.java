package persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileOperationsLanguages {
	private static Properties p;

	public static String readLastLanguageUsed() {
		p = new Properties();
		try {
			p.load(new FileReader("resources/lastLanguage.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String languageSeleted = p.getProperty("language");
		return languageSeleted;
	}

	public static Properties readLanguageSelected(String urlLanguage) {
		Properties propertiesLaguages = new Properties();
		try {
			propertiesLaguages.load(new FileReader(urlLanguage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propertiesLaguages;
	}

	public static void changeTheCurrentLanguage(String es_o_en) {
		p = new Properties();
		try {
			p.load(new FileReader("resources/lastLanguage.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setProperty("language", es_o_en);
		try {
			p.store(new FileWriter("resources/lastLanguage.properties"), "semodifico el archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
