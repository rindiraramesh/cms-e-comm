package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;
import utility.UtilityMethods;

public class LocationsModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(LocationsModuleTest.class);

	LoginModuleTest login = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	SoftAssert soft = new SoftAssert();

	UtilityMethods util = new UtilityMethods(getDriver());

	@Feature("Locations")
	@Test(groups = { "Locations", "regression",
			 "Smoke Test" }, dataProvider = "Locations", dataProviderClass = TestNGDataProvider.class)

	@Description("Verify search your nearest locations functionality by entering zipcode and miles")
	public void verifyNearestLocations(ITestContext context,String productName, String zipcode, String specificValue) throws Exception {
		try {
			log.info("-------------------------verifyNearestLocations started--------------------");
			locationsPage().clickLocationsLink()
					.verifyLocationsPageLinks()
					.verifyLocationsTitleAndBreadCrumbs(context.getCurrentXmlTest().getParameter("Company").trim())
					.enterZipCodeInLocationsPage(zipcode)
					.selectWithinDropdownValue(specificValue)
					.clickFindNow();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyNearestLocations failed :" + t.getMessage());
			Assert.fail("verifyNearestLocations failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------------verifyNearestLocations completed--------------------");

	}

	@Feature("Locations")
	@Test(enabled = true, groups = { "Locations",
			"regression" }, dataProvider = "Locations", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify nearest locations by clicking Product Title")
	public void verifyLocationsByProductTitle(String productTitleName) throws Exception {
		log.info("-------------------------verifyLocationsByProductTitle started--------------------");

		try {
			locationsPage().clickLocationsLink().clickOnProductTitle(productTitleName);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLocationsByProductTitle failed :" + t.getMessage());
			Assert.fail("verifyLocationsByProductTitle failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------------verifyLocationsByProductTitle Completed--------------------");

	}

	// Google api is not working in bond,this test should be tested specific to
	// project
	@Feature("Locations")
	@Test(enabled = false, groups = { "Locations", "regression",
			"Smoke Test" }, dataProvider = "Locations", dataProviderClass = TestNGDataProvider.class)
	@Description("verifying  Map and Driving Directions functionality")
	public void verifyMapDrivingDirections(String productTitleName, String productTitle, String locationName)
			throws Exception {
		log.info("-------------------------verifyMapDrivingDirections started--------------------");

		try {
			locationsPage().clickLocationsLink().clickOnProductTitle(productTitleName).verifyMapAndDrivingDirections(
					productTitle, locationName);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyMapDrivingDirections failed :" + t.getMessage());
			Assert.fail("verifyMapDrivingDirections failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------------verifyMapDrivingDirections completed--------------------");

	}

	@Feature("Locations")
	@Test(enabled = true, groups = { "Locations",
			"regression" }, dataProvider = "Locations", dataProviderClass = TestNGDataProvider.class)
	@Description("Verification of error message when user clicks on Find now link without entering zip cod")
	public void verifyLocationNegativeScenario(String zipcode) throws InterruptedException {
		log.info("-------------------------verifyLocationNegativeScenario started--------------------");

		try {
			locationsPage().clickLocationsLink().enterZipCodeInLocationsPage(zipcode).clickFindNow();
			commonOperations().verifyAlertTextPopUp("Please enter Zip Code to find location.")
					.clickOnOkButtonInAlertPopUp();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLocationNegativeScenario failed :" + t.getMessage());
			Assert.fail("verifyLocationNegativeScenario failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------------verifyLocationNegativeScenario completed--------------------");

	}
}
