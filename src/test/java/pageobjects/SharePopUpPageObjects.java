package pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class SharePopUpPageObjects extends PageInitializer {

	public TestDataPropertyFile data = new TestDataPropertyFile();

	Logger log = Logger.getLogger(SharePopUpPageObjects.class);

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utilities = new UtilityMethods(getDriver());

	/*
	 * @FindBy(xpath = "//div[contains(@class,'popupHeader')]/h3") private
	 * WebElement shareCartHeadingLocator;
	 */

	@FindBy(xpath = "//div[@id='sharePop']//h4")
	private WebElement shareCartHeadingLocator;

	/*
	 * @FindBy(id = "popkeyword") private WebElement userNameOrEmailTextBoxLocator;
	 */

	@FindBy(xpath = "//form[@id='popShareForm']//input[@id='popkeyword']")
	private WebElement userNameOrEmailTextBoxLocator;

	@FindBy(id = "popLoginBtn")
	private WebElement searchButtonLocator;

	@FindBy(xpath = "//form[@id='popShareForm']/p")
	private WebElement instructionsButtonLocator;

	/*
	 * @FindBy(xpath =
	 * "//div[contains(@id,'sharePopDiv1')]/descendant::a[@class='closeBtn']")
	 * private WebElement closeButtonLocator;
	 */

	@FindBy(xpath = "//div[@id='sharePop']//button[@class='close']")
	private WebElement closeButtonLocator;

	@FindBy(xpath = "//div[@id='pLoginErr1']/span")
	private WebElement errorMsgLocator;

	/*
	 * @FindBy(xpath = "//input[@value='Reset']") private WebElement
	 * resetButtonLocator;
	 */

	@FindBy(xpath = "//button[contains(text(),'Reset')]")
	private WebElement resetButtonLocator;

	/*
	 * @FindBy(xpath = "//input[@value='Share']") private WebElement
	 * shareButtonLocator;
	 */

	@FindBy(xpath = "//button[@id='share']")
	private WebElement shareButtonLocator;

	@FindBy(xpath = "//div[@id='innerContent']")
	private WebElement userNotFoundLocator;

	@FindBy(xpath = "//div[@id='innerContent']/descendant::a[@class='closeBtn']")
	private WebElement closeButtonUserNotFoundLocator;

	@FindBy(xpath = "//form[@id='popShareForm']//label")
	private WebElement userNameOrEmailLabelLocator;

	@FindBy(xpath = "//form[@id='performshare']//tr/th")
	private List<WebElement> shareCartFieldsLocator;

	@FindBy(xpath = "//div[contains(text(),'Saved Cart Shared Successfully.')]")
	private WebElement shareSucessMessageLocator;

	@Step("Verify Share pop up page")
	public SharePopUpPageObjects verifySharePopUp(String sharePopUpHeading, String userNameTextBoxLabel) {

		SoftAssert soft = new SoftAssert();

		waiting.waitForVisibilityOfElement(userNameOrEmailTextBoxLocator, 10);

		/*
		 * soft.assertTrue(userNameOrEmailTextBoxLocator.isDisplayed(),
		 * "user Name or Email text box is not displayed.");
		 */

		soft.assertTrue(utilities.isElementDisplayed(userNameOrEmailTextBoxLocator),
				"user Name or Email text box is not displayed.");

		waiting.waitForVisibilityOfElement(searchButtonLocator, 10);

		/*
		 * soft.assertTrue(searchButtonLocator.isDisplayed(),
		 * "Search Button is not displayed.");
		 */

		soft.assertTrue(utilities.isElementDisplayed(searchButtonLocator), "Search Button is not displayed.");

		soft.assertTrue(shareCartHeadingLocator.getText().contains(sharePopUpHeading),
				"Share Pop Up heading is not displayed.");

		/*
		 * soft.assertTrue(closeButtonLocator.isDisplayed(),
		 * "Close Button is not displayed.");
		 */

		soft.assertTrue(utilities.isElementDisplayed(closeButtonLocator), "Close Button is not displayed.");

		soft.assertEquals(userNameOrEmailLabelLocator.getText().trim(), userNameTextBoxLabel.trim(),
				"user name or email label is not : " + userNameTextBoxLabel.trim() + ". It is :"
						+ userNameOrEmailLabelLocator.getText().trim());

		soft.assertAll();
		log.info("Share cart page is verified");
		return this;
	}

	@Step("Click on close button")
	public SharePopUpPageObjects clickOnCloseButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(closeButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", closeButtonLocator);
		Thread.sleep(1600);
		log.info("Clicked on close button");
		return this;
	}

	@Step("Click on search button")
	public SharePopUpPageObjects clickOnSearchButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(searchButtonLocator, 10);
		searchButtonLocator.click();
		Thread.sleep(1500);
		log.info("Click on Search button");
		return this;
	}

	@Step("Verify error message:{0}")
	public SharePopUpPageObjects verifyErrorMsg(String errorMsg) {
		Assert.assertEquals(errorMsgLocator.getText().trim(), errorMsg);
		log.info("Error message " + errorMsg + " is verified");
		return this;
	}

	@Step("Enter User Name Or Email:{0}")
	public SharePopUpPageObjects enterUserNameOrEmail(String validKeywordForShareCart) {
		waiting.waitForVisibilityOfElement(userNameOrEmailTextBoxLocator, 10);
		userNameOrEmailTextBoxLocator.sendKeys(validKeywordForShareCart);
		log.info("Email is entered :" + validKeywordForShareCart);
		return this;
	}

	@Step("Verify search result as {0}")
	public SharePopUpPageObjects verifySearchResult(String validKeywordForShareCart) {
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(getDriver().findElement(By.xpath("//td[contains(text(),'" + validKeywordForShareCart + "')]"))
				.isDisplayed(), "Search result is not getting displayed");
		log.info("Search result is verified :" + validKeywordForShareCart);
		return this;
	}

	@Step("Click on the specific checkbox of share cart pop up")
	public SharePopUpPageObjects clickOnTheSpecificCheckbox(String validKeywordForShareCart,
			int numberOfCheckboxesToClick) throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> chboxes = getDriver().findElements(By.xpath(
				"//td[contains(text(),'" + validKeywordForShareCart + "')]/following-sibling::td/descendant::label"));
		for (int i = 0; i < numberOfCheckboxesToClick; i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", chboxes.get(i));
		}
		Thread.sleep(1000);
		log.info("Selected :" + validKeywordForShareCart);
		return this;
	}

	@Step("Verify whether checkboxes are checked")
	public SharePopUpPageObjects verifyWhetherCheckboxesAreClicked() {
		Assert.assertEquals(
				((JavascriptExecutor) getDriver()).executeScript("return $('#sharedUserIdCheck').attr('checked');"),
				"checked", "checkboxes are not checked");
		return this;
	}

	@Step("Click on reset button")
	public SharePopUpPageObjects clickOnResetButton() throws InterruptedException {
		resetButtonLocator.click();
		Thread.sleep(1200);
		log.info("Reset button is clicked");
		return this;

	}

	@Step("Verify whether checkboxes are unchecked")
	public SharePopUpPageObjects verifyWhetherCheckboxesAreNotClicked() throws InterruptedException {
		Thread.sleep(1700);
		Assert.assertEquals(
				((JavascriptExecutor) getDriver()).executeScript("return $('#sharedUserIdCheck').attr('checked');"),
				null, "checkboxes are still checked");
		return this;
	}

	@Step("Click on share button")
	public SharePopUpPageObjects clickOnShareButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(shareButtonLocator, 5);
		shareButtonLocator.click();
		Thread.sleep(1200);
		log.info("Share button is clicked");
		return this;
	}

	@Step("Verify no search results")
	public SharePopUpPageObjects verifyNoSearchResults(String noSearchResultsMessage) {
		waiting.waitForVisibilityOfElement(userNotFoundLocator, 5);
		Assert.assertEquals(userNotFoundLocator.getText().trim(), noSearchResultsMessage,
				"User not found message is not displayed.");
		log.info("No result message is verified");
		return this;
	}

	@Step("Click on close button")
	public SharePopUpPageObjects clickOnCloseButtonForUserNotFound() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", closeButtonUserNotFoundLocator);
		log.info("Close button clicked");
		return this;
	}

	@Step("Verify share cart fields :{0}")
	public SharePopUpPageObjects verifyShareCartFields(String shareCartFields) {
		waiting.waitForVisibilityOfElements(shareCartFieldsLocator, 10);

		String[] expectedShareCartFields = shareCartFields.split(",");

		for (int i = 0; i < shareCartFieldsLocator.size(); i++) {

			Assert.assertEquals(shareCartFieldsLocator.get(i).getText().trim().toLowerCase(),
					expectedShareCartFields[i].trim().toLowerCase(),
					"Share Cart fields are not " + expectedShareCartFields[i].trim().toLowerCase() + ".It is :"
							+ shareCartFieldsLocator.get(i).getText().trim().toLowerCase());
			log.info("Verified share cart field :" + expectedShareCartFields[i]);
		}
		return this;
	}

	@Step("Verify Reset button")
	public SharePopUpPageObjects verifResetButton() {

		waiting.waitForVisibilityOfElement(resetButtonLocator, 10);
		Assert.assertTrue(resetButtonLocator.isDisplayed(), "Reset Button is not displayed");
		log.info("Reset button verified");
		return this;
	}

	@Step("Verify Share button")
	public SharePopUpPageObjects verifyShareButton() {
		waiting.waitForVisibilityOfElement(shareButtonLocator, 10);
		Assert.assertTrue(shareButtonLocator.isDisplayed(), "Share Button is not displayed");
		log.info("Share button verified");
		return this;

	}

	@Step("Verify Cancel button functionality")
	public SharePopUpPageObjects verifyCancleButtonFunctionality() throws InterruptedException {
		Thread.sleep(1200);
		Assert.assertTrue(
				utilities.isElementNotDisplayed(By.xpath(
						"//h4[contains(text(),'Share Within Your Company')]/ancestor::div[@class='modal-content']")),
				"Share Within Your Company pop up is still displaying.");
		log.info("Share within your company popup is verified");
		return this;

	}

	@Step("Verify Success Message")
	public SharePopUpPageObjects verifySavedCartSharedSucessMessage() {
		waiting.waitForVisibilityOfElement(shareSucessMessageLocator, 10);
		Assert.assertTrue(shareSucessMessageLocator.isDisplayed(),
				"Saved Cart shared success message is not displayed.");
		log.info("Saved Cart shared success message is verified");
		return this;
	}

}
