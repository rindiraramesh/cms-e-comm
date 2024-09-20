package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class LocationsPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(PageHeaderObjects.class);

	UtilityMethods util = new UtilityMethods(getDriver());

	SoftAssert soft = new SoftAssert();

	@FindBy(xpath = "//div[@id='normalHead']//a[normalize-space(text())='LOCATIONS']")
	private WebElement LocationsLinkLocator;

	@FindBy(xpath = "//div[@id='normalHead']//a[normalize-space(text())='LOCATIONS']")
	private WebElement LocationsHeadingLocator;

	@FindBy(xpath = "//div[@id='normalHead']//a[normalize-space(text())='LOCATIONS']")
	private WebElement LocationsBreadcrumbLocator;

	@FindBy(xpath = "//input[@id='selectedZipCode']")
	private WebElement enterZipCodeLocator;

	@FindBy(xpath = "//select[@id='selectedMiles']")
	private WebElement selectDropdownValue;

	@FindBy(xpath = "//button[@data-id='selectedMiles']")
	private WebElement withinDropdownLocator;

	@FindBy(xpath = "//span[@class='productTitle pull-left']")
	private WebElement productTitle;

	@FindBy(xpath = "//h4[@class='searchHeading']")
	private WebElement searchYourNearestLocation;

	@FindBy(xpath = "//a[@id='findNowButton']")
	private WebElement findNowLinkLocator;

	@FindBy(xpath = "//input[@id='searchTextField']")
	private WebElement enterLocation;

	@FindBy(xpath = "//input[@id='directions-to']")
	private WebElement productTitleLocator;

	@FindBy(xpath = "//input[@id='getDiections']")
	private WebElement getDirectionsButton;

	@FindBy(xpath = "//a[@id='close-directions']")
	private WebElement closeButtonLocator;

	@FindBy(xpath = "//div[@id='viweAll']")
	private WebElement allLocationsLocator;

	@Step("Verify Locations Page Title and BreadCrumb")
	public LocationsPageObjects verifyLocationsTitleAndBreadCrumbs(String productName) {
		soft.assertEquals(getDriver().getTitle().trim(), "Locations | " + productName);
		soft.assertTrue(LocationsHeadingLocator.isDisplayed(), "Locations Heading is not displayed.");
		soft.assertTrue(LocationsBreadcrumbLocator.isDisplayed(), "Locations Breadcrumb is not displayed.");
		soft.assertAll();
		return this;
	}

	@Step("Click on Locations link")
	public LocationsPageObjects clickLocationsLink() throws InterruptedException {
		Thread.sleep(1600);
		waiting.waitForVisibilityOfElement(LocationsLinkLocator, 20);
		LocationsLinkLocator.click();
		log.info("clicked on location link");
		return this;
	}

	@Step("Verify all links in location page")
	public LocationsPageObjects verifyLocationsPageLinks() throws InterruptedException {
		Thread.sleep(1200);
		soft.assertTrue(util.isElementDisplayed(enterZipCodeLocator), "Enter zipcode textbox is available");
		log.info("Enter Zipcode element presence tested");

		soft.assertTrue(searchYourNearestLocation.isDisplayed(), "Serach your nearest location heading available");
		soft.assertTrue(util.isElementDisplayed(withinDropdownLocator));
		log.info("With in dropdown element presence tested");

		soft.assertAll();
		return this;
	}

	@Step("Click on Find Now link")
	public LocationsPageObjects clickFindNow() {
		waiting.waitForVisibilityOfElement(findNowLinkLocator, 8);
		findNowLinkLocator.click();
		log.info("clicked on Find Now link");
		return this;
	}

	@Step("Select value from within dropdowns")
	public LocationsPageObjects selectWithinDropdownValue(String specificValue) {
		waiting.waitForElementToBeClickable(withinDropdownLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", withinDropdownLocator);
		// withinDropdownLocator.click();
		log.info("clicked on within dropdown");
		Select select = new Select(selectDropdownValue);
		select.selectByVisibleText(specificValue);
		return this;
	}

	@Step("Enter zipcode in Locations Page")
	public LocationsPageObjects enterZipCodeInLocationsPage(String zipcode) {
		waiting.waitForElementToBeClickable(enterZipCodeLocator, 8);
		enterZipCodeLocator.clear();
		enterZipCodeLocator.sendKeys(zipcode);
		log.info("Enter zipcode in Locations Page :" + zipcode);
		return this;
	}

	@Step("Click on Product Title in Locations Page")
	public LocationsPageObjects clickOnProductTitle(String productTitleName) throws InterruptedException {
		waiting.waitForVisibilityOfElement(productTitle, 10);
		getDriver().findElement(By.xpath("//span[normalize-space(text())='" + productTitleName.trim() + "']")).click();
		soft.assertTrue(
				util.isElementDisplayed(
						getDriver().findElement(By.xpath("//strong[text()='" + productTitleName + "']"))),
				"Product Title details is displayed below map");
		log.info("Selected Product Title details displayed below map");
		Thread.sleep(2000);
		/*
		 * soft.assertTrue(util.isElementDisplayed(allLocationsLocator),
		 * "ALL LOCATIONS link is displayed");
		 * log.info("ALL LOACTIONS link displayed on the page ");
		 * allLocationsLocator.click();
		 */
		soft.assertAll();
		return this;
	}

	@Step("Verify Map and Driving Directions functionality")
	public LocationsPageObjects verifyMapAndDrivingDirections(String productTitle, String locationName)
			throws InterruptedException {
		getDriver().findElement(By.xpath("//li[contains(@class,'directionBtn')]//a[@id='" + productTitle + "']"))
				.click();
		log.info("clicked on location link");
		waiting.waitForElementToBeClickable(enterLocation, 8);
		enterLocation.clear();
		enterLocation.sendKeys(locationName);
		soft.assertTrue(util.isElementDisplayed(productTitleLocator), "product Title is displayed");
		getDirectionsButton.click();
		log.info("clicked on Get Directions button");
		Thread.sleep(2000);
		soft.assertTrue(
				util.isElementDisplayed(
						getDriver().findElement(By.xpath("//td[contains(text(),'" + locationName + "')]"))),
				"Events Link is not displayed");
		log.info("location name below map is verified");
		Thread.sleep(250);
		closeButtonLocator.click();
		util.scrollThePageToTop();
		soft.assertAll();
		return this;
	}
}
