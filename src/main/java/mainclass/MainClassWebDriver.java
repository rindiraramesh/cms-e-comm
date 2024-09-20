package mainclass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.qameta.allure.Attachment;
import utility.MSExcelMethods;
import utility.WaitingMethods;

public class MainClassWebDriver {

	private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

	public Logger logger = Logger.getLogger(MainClassWebDriver.class);

	MSExcelMethods msExcelMethods = new MSExcelMethods();

	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeClass(alwaysRun = true)
	@Parameters(value = { "URL", "Browser", "RemoteUrl", "RunHeadless" })
	public void initialSetup(String url, String browser, String remoteUrl, String runHeadless)
			throws IOException, InterruptedException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		if (runHeadless.equalsIgnoreCase("true") & browser.equalsIgnoreCase("chrome")) {
			final ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("--window-size=1920,1080");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.addArguments("--proxy-server='direct://'");
			chromeOptions.addArguments("--proxy-bypass-list=*");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("disable-blink-features=AutomationControlled");
			chromeOptions.addArguments("--aggressive-cache-discard");
			chromeOptions.addArguments("--disable-cache");
			chromeOptions.addArguments("--disable-application-cache");
			chromeOptions.addArguments("--disable-offline-load-stale-cache");
			chromeOptions.addArguments("--disk-cache-size=0");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--dns-prefetch-disable");
			chromeOptions.addArguments("--no-proxy-server");
			chromeOptions.addArguments("--log-level=3");
			chromeOptions.addArguments("--silent");
			chromeOptions.addArguments("--disable-browser-side-navigation");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			chromeOptions.setProxy(null);
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			logger.info("Running in Chrome, headless mode");
		}

		if (runHeadless.equalsIgnoreCase("true") & browser.equalsIgnoreCase("firefox")) {
			final FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			firefoxOptions.addArguments("--headless");
			firefoxOptions.addArguments("--window-size=1920,1080");
			/*firefoxOptions.addArguments("--disable-gpu");
			firefoxOptions.addArguments("--disable-extensions");
			firefoxOptions.addArguments("--start-maximized");
			firefoxOptions.addArguments("--ignore-certificate-errors");
			firefoxOptions.addArguments("disable-blink-features=AutomationControlled");
			firefoxOptions.addArguments("--aggressive-cache-discard");
			firefoxOptions.addArguments("--disable-cache");
			firefoxOptions.addArguments("--no-proxy-server");
			firefoxOptions.addArguments("--silent");
			firefoxOptions.setProxy(null);*/
			desiredCapabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, "true");
			desiredCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
			desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
			logger.info("Running in firefox, headless mode");
		}

		if (runHeadless.equalsIgnoreCase("false") & browser.equalsIgnoreCase("firefox")) {
			final FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--window-size=1920x1080");
			desiredCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
			desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
		}
		
		/*if (runHeadless.equalsIgnoreCase("false") & browser.equalsIgnoreCase("edge")) {
			//EdgeOptions edgeOptions = new EdgeOptions();
			//DesiredCapabilities desiredCapabilities = DesiredCapabilities.edge();
			desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			desiredCapabilities.setCapability(InternetExplorerDriver.
			  INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		}*/
		
		desiredCapabilities.merge(desiredCapabilities);
		desiredCapabilities.setBrowserName(browser);
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setPlatform(Platform.ANY);

		try {
			driver.set(new RemoteWebDriver(new URL(remoteUrl), desiredCapabilities));
		} catch (Exception e) {
			driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities));
		}
		logger.info("Launched " + browser);
		getDriver().get(url);
		logger.info("Launched the site :" + url);
		new WaitingMethods(getDriver()).waitTillPageLoads();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().navigate().refresh();
		getDriver().manage().window().maximize();
		
		try {
			getDriver().findElement(By.xpath("//button[normalize-space(text())='Accept' and @ data-tid='banner-accept']")).click();
			}catch(Exception e) {
			logger.info("Cookie banner not displayed!");
			}

	}
	/*
	 * public void uncheckInstoke() { getDriver().findElement(By.
	 * xpath("//button[normalize-space(text())='Accept' and @ data-tid='banner-accept']"
	 * )).click();
	 * 
	 * }
	 */
	


	public void afterMethod(ITestResult iTestResult) throws IOException {
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(iTestResult.getName(), getDriver());

		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		getDriver().quit();
		logger.info("browser closed ");
	}

	@Attachment(value = "Failure at:{0}", type = "image/png")
	public byte[] captureScreenshot(String name, WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public byte[] getFileInBytes(String fileName) throws Exception {
		File file = new File(fileName);
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}

	@Attachment(value = "CSV File:{0}", type = "text/csv", fileExtension = ".csv")
	public byte[] attachCSVFile(String fileName, String filePath) throws Exception {
		return getFileInBytes(filePath);
	}

	@Attachment(value = "XML File:{0}", type = "text/xml")
	public byte[] attachXMLFile(String fileName, String filePath) throws Exception {
		return getFileInBytes(filePath);
	}

	@Attachment(value = "Excel File:{0}", type = "text/xlsx", fileExtension = ".xlsx")
	public byte[] attachXLSXFile(String fileName, String filePath) throws Exception {
		return getFileInBytes(filePath);
	}

	@Attachment(value = "Text File:{0}", type = "text/plain")
	public byte[] attachTextFile(String fileName, String filePath) throws Exception {
		return getFileInBytes(filePath);
	}

	@Attachment(value = "JSON attachment", type = "text/json")
	public byte[] attachJSONFile(String fileName, String filePath) throws Exception {
		return getFileInBytes(filePath);
	}

}
