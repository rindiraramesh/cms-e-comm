package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;

public class PropertyFileMethods {

	public Properties properties = new Properties();

	public String readTestDataFromPropertyFile(String filePath, String key) {
		try {
			FileReader fileReader = new FileReader(filePath);
			properties.load(fileReader);
			String value = properties.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeDataToPropertyFile(String filePathWrite, String key, String value) {
		try {

			FileInputStream in = new FileInputStream(filePathWrite);
			properties.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(filePathWrite);
			properties.setProperty(key, value);
			properties.store(out, null);
			out.close();
			/*
			 * PropertiesConfiguration conf = new
			 * PropertiesConfiguration("propFile.properties"); //FileOutputStream fileWriter
			 * = new FileOutputStream(filePath); // FileWriter fileWriter = new
			 * FileWriter(filePath); properties.setProperty(key, value); conf.
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}