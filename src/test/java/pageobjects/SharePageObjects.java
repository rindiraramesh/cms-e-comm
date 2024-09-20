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

public class SharePageObjects extends PageInitializer {
	Actions action = new Actions(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(SharePageObjects.class);

	@FindBy(xpath = "//div[@id='notificationDiv']")
	private WebElement errorMsgLocator;

	@FindBy(id = "successMsg")
	private WebElement successMsgLocator;

	@FindBy(id = "toNames")
	private WebElement friendNameLocator;

	@FindBy(id = "toEmails")
	private WebElement friendEmailAddressLocator;

	@FindBy(id = "fromNames")
	private WebElement fromNameLocator;

	@FindBy(id = "fromEmails")
	private WebElement fromEmailAddressLocator;

	@FindBy(id = "mailSubjects")
	private WebElement subjectLocator;

	@FindBy(id = "sendBtn")
	private WebElement sendButtonLocator;

	@FindBy(xpath = "//h1[@class='cimm_pageTitle']")
	private WebElement pageNameshareThisLinkLocator;

	@FindBy(xpath = "//div[@id='SendItem']//div[@class='form-group']")
	private List<WebElement> mandatoryFieldsOfShareThisPageLocator;

	@FindBy(xpath = "//input[@id='jcaptchas']")
	private WebElement capthaTextBoxLocator;

	@Step("Enter friend name as {0}")
	public SharePageObjects enterFriendName(String friendName) {

		friendNameLocator.clear();
		friendNameLocator.sendKeys(friendName);
		log.info("Entered friend name as:" + friendName);
		return this;
	}

	@Step("Enter friend Email Address as {0}")
	public SharePageObjects enterFriendEmailAddress(String friendEmailAddress) {
		friendEmailAddressLocator.clear();
		friendEmailAddressLocator.sendKeys(friendEmailAddress);
		log.info("Entered friend email as:" + friendEmailAddress);
		return this;
	}

	@Step("Enter from name as {0}")
	public SharePageObjects enterFromName(String fromName) {
		fromNameLocator.clear();
		fromNameLocator.sendKeys(fromName);

		log.info("Entered from name as:" + fromName);
		return this;
	}

	@Step("Enter from Email Address {0}")
	public SharePageObjects enterFromEmailAddress(String fromEmailAddress) {
		fromEmailAddressLocator.clear();
		fromEmailAddressLocator.sendKeys(fromEmailAddress);

		log.info("Entered from Email Address :" + fromEmailAddress);
		return this;
	}

	@Step("Enter Subject as {0}")
	public SharePageObjects enterSubject(String subject) {
		subjectLocator.clear();
		subjectLocator.sendKeys(subject);

		log.info("Entered Subject as : " + subject);
		return this;
	}

	@Step("Click on send")
	public SharePageObjects clickOnSend() throws InterruptedException {
		waiting.waitForVisibilityOfElement(sendButtonLocator, 10);
		sendButtonLocator.click();
		Thread.sleep(1800);
		log.info("Clicked on send button");
		return this;
	}

	@Step("Verify if error msg is {0}")
	public SharePageObjects verifyErrorMessage(String errorMsg) {

		Assert.assertEquals(errorMsgLocator.getText().replace("\n", "").replace("×", "").trim(), errorMsg.trim(),

				"Error Message without entering of the mandatory fields is not : " + errorMsg.trim() + ". It is : "
						+ errorMsgLocator.getText().replace("\n", "").replace("×", "").trim());

		log.info("Error Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ errorMsgLocator.getText().replace("\n", "").replace("×", "").trim() + "] and " + "[" + errorMsg.trim()
				+ "]");
		return this;
	}

	@Step("Verify Bread Crumb Of Share This Page:{0}")
	public SharePageObjects verifyShareThisPageBreadCrumb(String shareThisPageBreadCrumb) {
		Assert.assertEquals(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1).getText().trim(),
				shareThisPageBreadCrumb.trim(),
				"BreadCrumb of Share This Page is not : " + shareThisPageBreadCrumb.trim() + ". It is : "
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.trim());
		log.info("Bread CrumbOf Share This Page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1).getText().trim()
				+ "] and " + "[" + shareThisPageBreadCrumb.trim() + "]");
		return this;
	}

	@Step("Verify Mandatory Fields Of Share This Page :{0}")
	public SharePageObjects verifyMandatoryFieldOfShareThisPage(String mandatoryFieldsOfShareThisPage) {
		waiting.waitForVisibilityOfElements(mandatoryFieldsOfShareThisPageLocator, 10);
		for (int i = 0; i < mandatoryFieldsOfShareThisPageLocator.size(); i++) {
			String[] expectedMandatoryFields = mandatoryFieldsOfShareThisPage.split(",");
			Assert.assertEquals(
					mandatoryFieldsOfShareThisPageLocator.get(i).getText().replace("*", "").replace(":", "").trim(),
					expectedMandatoryFields[i].trim(),
					"Mandatory Fields of Share this page are not :" + expectedMandatoryFields[i].trim() + ". It is : "
							+ mandatoryFieldsOfShareThisPageLocator.get(i)
									.getText()
									.replace("*", "")
									.replace(":", "")
									.trim());
			log.info("Mandatory Fields Of Share This Page has been verified" + " with [Actual] and [Expected] value as "
					+ "["
					+ mandatoryFieldsOfShareThisPageLocator.get(i).getText().replace("*", "").replace(":", "").trim()
					+ "] and " + "[" + expectedMandatoryFields[i].trim() + "]");

		}
		return this;
	}

	@Step("Verify Item In Share This Page:{0}")
	public SharePageObjects verifyItemsInShareThisPage(String partNumber) {

		By itemInShareThisPage = By
				.xpath("//strong[normalize-space(text())='Part#:']/following-sibling::span[contains(text(),'"
						+ partNumber.trim() + "')]");
		Assert.assertTrue(testUtilityMethods.isElementDisplayed(itemInShareThisPage),
				"Item added is not present in Share This Page");
		log.info("Item added is present in Share This Page : " + partNumber);
		return this;
	}

	@Step("Verify Captha Textbox")
	public SharePageObjects verifyTextBoxToEnterCaptha() {
		waiting.waitForVisibilityOfElement(capthaTextBoxLocator, 10);
		Assert.assertTrue(capthaTextBoxLocator.isDisplayed(), "Captha text Box is not displayed.");
		log.info("Captha text Box is displayed.");
		return this;

	}

}