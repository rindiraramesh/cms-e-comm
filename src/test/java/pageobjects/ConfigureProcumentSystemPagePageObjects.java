package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ConfigureProcumentSystemPagePageObjects extends PageInitializer {

	public TestDataPropertyFile data = new TestDataPropertyFile();

	SoftAssert softAssert = new SoftAssert();

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(RequestForQuotePageObjects.class);

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	public List<WebElement> breadCrumbs;

	@Step("Verify Configure Procurement System page title")
	public ConfigureProcumentSystemPagePageObjects verifyConfigureProcurementSystemTitle(String pageTitle) {
		Assert.assertEquals(getDriver().getTitle(), pageTitle, "Title of the page didnt matched :");
		log.info("Configure Procurement System Title of the page is verified ");
		return this;
	}

	@Step("Verify Configure Procurement System info breadcrumb contains {0}")
	public ConfigureProcumentSystemPagePageObjects verifyConfigureProcurementSystemBreadcrumb(String bredCrums) {
		Assert.assertTrue(
				breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("/", "").trim().equalsIgnoreCase(bredCrums),
				"Breadcrump is not " + bredCrums + ". It is "
						+ breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("/", "").trim() + ".");
		return this;
	}
}
