package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class AdvanceSearchPageObjects extends PageInitializer {

	Logger log = Logger.getLogger(AdvanceSearchPageObjects.class);

	WaitingMethods waiting = new WaitingMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	UtilityMethods utils = new UtilityMethods(getDriver());

	@FindBy(xpath = "//a[@href='/advancedSearch']")
	private WebElement advanceSearchLink;

	@FindBy(xpath = "//h1[@class='cimm_pageTitle']")
	private WebElement pageNameLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadcrumbsLocator;

	@FindBy(xpath = "//input[@id='searchCustomerPartNumber']/..")
	private WebElement cpnLocator;

	@FindBy(xpath = "//input[@id='advSearchUpc']/..")
	private WebElement upcLocator;

	@FindBy(xpath = "//input[@id='glantzCode']/..")
	private WebElement mpnLocator;

	@FindBy(xpath = "//input[@id='description']/..")
	private WebElement descriptionLocator;

	@FindBy(xpath = "//input[@id='allWords']/..")
	private WebElement desc_AllOfThese;

	@FindBy(xpath = "//input[@id='exactWordingPhrase']/..")
	private WebElement desc_ExactWording;

	@FindBy(xpath = "//input[@id='oneOrMore']/..")
	private WebElement desc_OneOrMore;

	@FindBy(xpath = "//input[@id='txtAdvSearch']")
	private WebElement searchTextboxLocator;

	@FindBy(xpath = "//button[@id='advSrchBtn']")
	private WebElement searchButtonLocator;

	@Step("Click on advance search link")
	public AdvanceSearchPageObjects clickOnAdvanceSearchLink() {
		waiting.waitForVisibilityOfElement(advanceSearchLink, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", advanceSearchLink);
		log.info("Clicked on advance search link");
		return this;
	}

	@Step("Select CPN")
	public AdvanceSearchPageObjects selectCPN() {
		waiting.waitForVisibilityOfElement(cpnLocator, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", cpnLocator);
		log.info("Selected CPN");
		return this;
	}

	@Step("Select UPC")
	public AdvanceSearchPageObjects selectUPC() {
		waiting.waitForVisibilityOfElement(upcLocator, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", upcLocator);
		log.info("Selected UPC");
		return this;
	}

	@Step("Select MPN")
	public AdvanceSearchPageObjects selectMPN() {
		waiting.waitForVisibilityOfElement(mpnLocator, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", mpnLocator);
		log.info("Selected MPN");
		return this;
	}

	@Step("Select Description")
	public AdvanceSearchPageObjects selectDescription() {
		waiting.waitForVisibilityOfElement(descriptionLocator, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", descriptionLocator);
		log.info("Selected Description");
		return this;
	}

	@Step("Select All of these option")
	public AdvanceSearchPageObjects selectDescription_AllWords() {
		waiting.waitForVisibilityOfElement(desc_AllOfThese, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", desc_AllOfThese);
		log.info("Select All of these option");
		return this;
	}

	@Step("Select option")
	public AdvanceSearchPageObjects selectDescription_ExactWords() {
		waiting.waitForVisibilityOfElement(desc_ExactWording, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", desc_ExactWording);
		log.info("Selected option :" + desc_ExactWording);
		return this;
	}

	@Step("Select one or more option")
	public AdvanceSearchPageObjects selectDescription_OneOrMore() {
		waiting.waitForVisibilityOfElement(desc_OneOrMore, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", desc_OneOrMore);
		log.info("Select one or more option:" + desc_OneOrMore);
		return this;
	}

	@Step("Enter advance search text: {0}")
	public AdvanceSearchPageObjects enterSearchText(String searchText) {
		waiting.waitForVisibilityOfElement(searchTextboxLocator, 8);
		searchTextboxLocator.sendKeys(searchText.trim());
		log.info("Entered advance search text :" + searchText);
		return this;
	}

	@Step("Click on search button")
	public AdvanceSearchPageObjects clickOnSearchButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(searchButtonLocator, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", searchButtonLocator);
		Thread.sleep(1000);
		log.info("Clicked on search button");
		return this;
	}

	@Step("Verify advance search page")
	public AdvanceSearchPageObjects verifyAdvanceSearchPage(String productName, String breadcrumb) {
		waiting.waitForVisibilityOfElement(cpnLocator, 8);
		softAssert.assertEquals(getDriver().getTitle().trim(), "Advanced Search" + " | " + productName, "Title is wrong");
		softAssert.assertEquals(breadcrumbsLocator.get(breadcrumbsLocator.size() - 1).getText().trim(),
				breadcrumb.trim());
		softAssert.assertTrue(utils.isElementDisplayed(cpnLocator), "CPN selector is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(upcLocator), "UPC selector is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(mpnLocator), "MPN selector is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(descriptionLocator), "Description selector is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(searchTextboxLocator), "Search text box is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(searchButtonLocator), "Search button is not displayed");
		softAssert.assertAll();
		log.info("Verified advance search page");
		return this;
	}

	@Step("Verify whether alert message is {0}")
	public AdvanceSearchPageObjects verifyAlertMessage(String alertText) throws Exception {
		Thread.sleep(800);
		WebElement alert = getDriver()
				.findElement(By.xpath("//div[normalize-space(text())='" + alertText.trim() + "']"));
		Assert.assertTrue(utils.isElementDisplayed(alert), "Alert pop up not displayed");
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//button[@data-bb-handler='ok']")).click();
		log.info("Verified alert message :" + alertText);
		return this;
	}
}
