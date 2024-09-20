package utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVFileOperations {

	/* This method reads all data form CSV file by skipping the number of lines from top. */
	public List<String[]> readDataFromCSV(String filePath, int skipLines) throws IOException {

		// Create an object of file reader class with CSV file as a parameter.
		FileReader filereader = new FileReader(filePath);

		// create csvReader object and skip first Line
		CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(skipLines).build();

		List<String[]> allData = csvReader.readAll();
		return allData;
	}

	/* This method reads all data form CSV file. */
	public List<String[]> readDataFromCSV(String filePath) throws IOException {

		// Create an object of file reader class with CSV file as a parameter.
		FileReader filereader = new FileReader(filePath);

		// create csvReader object
		CSVReader csvReader = new CSVReaderBuilder(filereader).build();

		List<String[]> allData = csvReader.readAll();
		return allData;
	}

	public int getRowCount(String filePath) throws IOException {
		// Create an object of file reader class with CSV file as a parameter.
		FileReader filereader = new FileReader(filePath);

		// create csvReader object
		CSVReader csvReader = new CSVReaderBuilder(filereader).build();

		List<String[]> allData = csvReader.readAll();
		return allData.size();
	}

	public int getColumnCount(String filePath) throws IOException {
		// Create an object of file reader class with CSV file as a parameter.
		FileReader filereader = new FileReader(filePath);

		// create csvReader object
		CSVReader csvReader = new CSVReaderBuilder(filereader).build();

		List<String[]> allData = csvReader.readAll();
		return allData.get(0).length;
	}

}
