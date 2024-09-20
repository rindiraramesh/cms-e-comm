package testmethods;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;
import utility.MSExcelMethods;

public class SampleTest extends PageInitializer {

	TestDataPropertyFile testdata = new TestDataPropertyFile();

	MSExcelMethods msExcelMethods = new MSExcelMethods();

	LoginModuleTest loginModule = new LoginModuleTest();

	String filePath = "src/test/resources/TestDataFiles/TestData.xlsx";

	Logger logger;

	@Test
	public void testing1() throws InterruptedException {
		System.out.println("testing1 executed");
		// extentTest = extentReports.createTest("testing1");
		/*
		 * logger = Logger.getLogger("testing1"); logger.info("Hello");
		 */
		testPage().assertTitle("Google");
	}

	@Test
	public void testing2() throws InterruptedException {
		// extentTest = extentReports.createTest("testing2");
		System.out.println("testing2 executed");
		testPage().assertTitle("Google");
	}

	@Test
	public void testing3() throws InterruptedException {
		System.out.println("testing3 executed");
		// extentTest = extentReports.createTest("testing3");
		testPage().assertTitle("asdf");
	}

	@Test
	public void testing4() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing5() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing6() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing7() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing8() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing9() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing10() throws InterruptedException {
		testPage().assertTitle("Google");
	}

	@Test
	public void testing11() throws InterruptedException {
		/*
		 * testPage() .assertTitle("Google");
		 */
		int j = 0;
		System.out.println("Post1=" + j++);
		System.out.println("Post2=" + j++);
		System.out.println("Post3=" + j++);

		int i = 0;
		System.out.println("Pre1=" + ++i);
		System.out.println("Pre2=" + ++i);
		System.out.println("Pre3=" + ++i);

	}

	@Feature("TestDataFromExcel")
	@Test(enabled = true, dataProvider = "ReadDataFromExcel", dataProviderClass = TestNGDataProvider.class)
	public void testDataFromExcel(String uName, String pwd, String cmt) throws InterruptedException, IOException {

		System.out.println(uName + "/" + pwd + "/" + cmt + "\n");

		/*
		 * String result[][] =
		 * msExcelMethods.readDataFromExcel("src/test/resources/TestData.xlsx",
		 * "testDataFromExcel"); for (int i = 0; i < result.length; i++) { for (int j =
		 * 0; j < result[0].length; j++) { System.out.print(result[i][j] + " "); }
		 * System.out.println(); }
		 */
	}

	@Test
	public void navigateCategory() {
		testPage().navigateCategories();
	}

	@Test
	public void clickOnProductsLink() throws InterruptedException {
		homePage().clickOnProductsLink();
	}

	@FindBy(xpath = "//td[@data-th='Ext Price']/strong")
	private WebElement extPrice;

	@Test
	public void trail() throws ParseException {

		Scanner scanner = new Scanner(System.in);
		double payment = scanner.nextDouble();
		scanner.close();

		NumberFormat usFormat = NumberFormat.getCurrencyInstance(Locale.US);

		String us = usFormat.format(payment);
		System.out.println("US: " + us);

	}

	@Test
	public void selectDate() throws Exception {
		loginModule.loginAsASuperUser();
		homePage().clickOnMyAccountMenuDropdown().testPage().navigateToRFQ().clickOnDate().selectDate("20",
				"Jull 2019");
		Thread.sleep(2500);
	}

	@Test
	public void hashExample() throws Exception {
		// String[][] testdata = msExcelMethods.readDataFromExcel(filePath,
		// "hashExample");
		HashMap<String, String> map = new HashMap<>();
		// Map m = Collections.synchronizedMap(map);
		/*
		 * for (String[] data : testdata) { map.put(data[0], data[1]); }
		 */
		System.out.println("Value=" + map.get("Method1"));
		System.out.println(map);
		map.put("Method5", "TC05");
		map.put("Method6", "TC06");
		System.out.println(map);
		System.out.println("Clear Map");
		map.clear();
		System.out.println(map);
		map.put("Method5", "TC05");
		map.put("Method6", "TC06");
		System.out.println(map);
		System.out.println("Invalid Key" + map.get("adsf"));
	}

	@Test
	public void testWriteToPropertiesFile() {
		new utilities.TestDataPropertyFile().updatePrerequisitesConfigFile("username", "usr@lv.com");
		new utilities.TestDataPropertyFile().updatePrerequisitesConfigFile("password",
				"test~!#@$%^&*()_+={[}]:;\"'<>./");

	}

}
