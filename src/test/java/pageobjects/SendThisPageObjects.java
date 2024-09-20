package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class SendThisPageObjects extends PageInitializer {

	Actions action = new Actions(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	@FindBy(xpath = "//div[@id='notificationDiv']")
	private WebElement errorMsgLocator;

	@FindBy(id = "successMsg")
	private WebElement successMsgLocator;

	@FindBy(id = "toName")
	private WebElement friendNameLocator;

	@FindBy(id = "toEmail")
	private WebElement friendEmailAddressLocator;

	@FindBy(id = "fromName")
	private WebElement fromNameLocator;

	@FindBy(id = "fromEmail")
	private WebElement fromEmailAddressLocator;

	@FindBy(id = "mailSubject")
	private WebElement subjectLocator;

	@FindBy(id = "sendBtn")
	private WebElement sendButtonLocator;

	@FindBy(xpath = "//h1")
	private WebElement pageNameshareThisLinkLocator;

	@FindBy(xpath = "//form[@id='sendPageForm']//div[@class='form-group']")
	private List<WebElement> mandatoryFieldsOfShareThisPageLocator;

	@Step("Enter friend name as {0}")
	public SendThisPageObjects enterFriendName(String friendName) {

		logger = Logger.getLogger("enterFriendName");

		friendNameLocator.clear();
		friendNameLocator.sendKeys(friendName);

		logger.info("entered friend name as:" + friendName);
		return this;
	}

	@Step("Enter friend Email Address as {0}")
	public SendThisPageObjects enterFriendEmailAddress(String friendEmailAddress) {

		logger = Logger.getLogger("enterFriendEmailAddress");
		friendEmailAddressLocator.clear();
		friendEmailAddressLocator.sendKeys(friendEmailAddress);
		logger.info("entered friend email as:" + friendEmailAddress);
		return this;
	}

	@Step("Enter from name as {0}")
	public SendThisPageObjects enterFromName(String fromName) {

		logger = Logger.getLogger("enterFromName");
		fromNameLocator.clear();
		fromNameLocator.sendKeys(fromName);

		logger.info("enterd from name as:" + fromName);
		return this;
	}

	@Step("Enter from Email Address {0}")
	public SendThisPageObjects enterFromEmailAddress(String fromEmailAddress) {

		logger = Logger.getLogger("enterFromEmailAddress");

		fromEmailAddressLocator.clear();
		fromEmailAddressLocator.sendKeys(fromEmailAddress);

		logger.info("entered from Email Address :" + fromEmailAddress);
		return this;
	}

	@Step("Enter Subject as {0}")
	public SendThisPageObjects enterSubject(String subject) {

		logger = Logger.getLogger("enterSubject");
		subjectLocator.clear();
		subjectLocator.sendKeys(subject);

		logger.info("entered Subject as : " + subject);
		return this;
	}

	@Step("Click on send")
	public SendThisPageObjects clickOnSend() throws InterruptedException {

		logger = Logger.getLogger("clickOnSend");
		sendButtonLocator.click();

		Thread.sleep(1500);

		logger.info("Clicked on send button");
		return this;
	}

	@Step("Verify if error msg is {0}")
	public SendThisPageObjects verifyErrorMessage(String errorMsg) {

		logger = Logger.getLogger("verifyErrorMessage");

		Assert.assertEquals(errorMsgLocator.getText().replace("\n", "").replace("×", "").trim(), errorMsg.trim());

		logger.info("Error Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ errorMsgLocator.getText().replace("\n", "").replace("×", "").trim() + "] and " + "[" + errorMsg.trim()
				+ "]");
		return this;
	}

	@Step("Verify if msg is {0}")
	public SendThisPageObjects verifySuccessMessage(String successMessageForItemShare) {

		return this;
	}

	@Step("Verify breadcrumb of Send This Page as {0}")
	public SendThisPageObjects verifySendThisPageBreadCrumb(String sendThisPage) {

		logger = Logger.getLogger("verifySendThisPageBreadCrumb");

		Assert.assertTrue(productsPage().homeIconInBreadcrumbLocator.isDisplayed(),
				"Home icon is not displayed in the breadcrumb.");

		logger.info("Home icon is displayed in the breadcrumb.");

		Assert.assertEquals(productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.replace("|", "")
				.trim(), sendThisPage);

		logger.info(
				"Breadcrumb of the Send This page has been verified" + " with [Actual] and [Expected] value as " + "["
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.replace("|", "")
								.trim()
						+ "] and " + "[" + sendThisPage + "]");

		return this;

	}

	@Step("Verify send This Page Name")
	public SendThisPageObjects verifyPageNameOfSendThisPage() {
		waiting.waitForVisibilityOfElement(pageNameshareThisLinkLocator, 10);
		logger = Logger.getLogger("verifyPageNameOfSendThisPage");

		Assert.assertTrue(pageNameshareThisLinkLocator.isDisplayed(),
				"Page Name is not displayed in the Send This Page");

		logger.info("Page Name is displayed in the Send This Page");

		return this;

	}

	@Step("Verify Mandatory Fields Of Send This Page: {0}")
	public SendThisPageObjects verifyMandatoryFieldOfSendThisPage(String mandatoryFieldsOfSendThisPage) {
		waiting.waitForVisibilityOfElements(mandatoryFieldsOfShareThisPageLocator, 10);

		logger = Logger.getLogger("verifyMandatoryFieldOfSendThisPage");

		for (int i = 0; i < mandatoryFieldsOfShareThisPageLocator.size(); i++) {

			String[] expectedMandatoryFields = mandatoryFieldsOfSendThisPage.split(",");

			Assert.assertEquals(mandatoryFieldsOfShareThisPageLocator.get(i).getText().trim(),
					expectedMandatoryFields[i].trim());

			logger.info("Mandatory Fields Of Send This  has been verified" + " with [Actual] and [Expected] value as "
					+ "[" + mandatoryFieldsOfShareThisPageLocator.get(i).getText().trim() + "] and " + "["
					+ expectedMandatoryFields[i].trim() + "]");

		}
		return this;
	}

	@Step("Verify Item Part Number in Send This Page: {0}")
	public SendThisPageObjects verifyItemsInSendThisPage(String partNumber) {
		logger = Logger.getLogger("verifyItemsInSendThisPage");

		By itemInSendThisPage = By.xpath(
				"//div[contains(@class,'cartProdDescription')]//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]");
		testUtilityMethods.scrollThePageToBottom();

		//testUtilityMethods.moveToElement(itemInSendThisPage);

		Assert.assertTrue(testUtilityMethods.isElementDisplayed(itemInSendThisPage),
				"Item added is not present in Send This Page");

		logger.info("Item added is present in Send This Page :" + partNumber);

		return this;

	}

	@Step("Verify Title of Send This Page as : {0}")
	public SendThisPageObjects verifyTitleOfSendThisPage(String productName) throws InterruptedException {

		logger = Logger.getLogger("verifyHomePageTitle");

		Thread.sleep(1500);
		String sendThisPageTitle = getDriver().getTitle();

		Assert.assertEquals(sendThisPageTitle, "Send this" + " | " + productName, "Title is wrong");

		logger.info("Title of the home page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ sendThisPageTitle + "] and " + "[" + "Send this" + " | " + productName + "]");

		return this;

	}

}
