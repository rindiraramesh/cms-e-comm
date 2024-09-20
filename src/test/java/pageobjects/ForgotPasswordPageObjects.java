package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.WaitingMethods;

public class ForgotPasswordPageObjects extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(ForgotPasswordPageObjects.class);

	@FindBy(xpath = "//span[contains(text(),'Forgot Password ?')]")
	private WebElement forgotPasswordLink;

	@FindBy(xpath = "//input[@id='username']")
	private WebElement userNameTextbox;

	@FindBy(xpath = "//input[@id='emailAddress']")
	private WebElement emailAddressTextBox;

	@FindBy(id = "submitBtn")
	private WebElement getNewPasswordButton;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a")
	private WebElement homeBreadCrumbLinkLocator;

	@FindBy(xpath = "//div[@class='cimm_mainContentEnclosure']/descendant::strong[contains(text(),'email')]")
	private WebElement forgotYourPasswordInstructions;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMessageLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement successMessageLocator;

	@FindBy(xpath = "//h1")
	private WebElement forgotPasswordPageName;

	@Step("Verify page name")
	public ForgotPasswordPageObjects verifyPageName() {
		waiting.waitForVisibilityOfElement(forgotPasswordPageName, 15);
		Assert.assertTrue(forgotPasswordPageName.isDisplayed(), "Not landed to forgot password page");
		log.info("Forget password page name is verified.");
		return this;
	}

	@Step("Verify retrieve password page")
	public ForgotPasswordPageObjects verifyForgotPasswordPage() {

		SoftAssert soft = new SoftAssert();

		waiting.waitForVisibilityOfElement(forgotPasswordPageName, 10);
		soft.assertEquals(forgotPasswordPageName.getText().trim().toUpperCase(), "FORGOT PASSWORD",
				"Page name is not right. I am getting " + loginPopUp().pageName.getText().trim());

		log.info("Forgot password page name has been verified" + " with [Actual] and [Expected] value as " + "["
				+ forgotPasswordPageName.getText().trim().toUpperCase() + "] and " + "[" + "FORGOT PASSWORD" + "]");
		soft.assertTrue(emailAddressTextBox.isDisplayed(), "email address text box is not displayed");

		log.info("email address text box is displayed");
		soft.assertTrue(getNewPasswordButton.isDisplayed(), "retrieve password button is not displayed");

		log.info("retrieve password button is not displayed");
		soft.assertEquals(getNewPasswordButton.getAttribute("value"), "Get New Password");

		log.info("Attribute of get New password button has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + getNewPasswordButton.getAttribute("value") + "] and " + "[" + "Get New Password" + "]");
		soft.assertEquals(forgotYourPasswordInstructions.getText().trim(), data.getForgotYourPasswordInstructions());

		log.info("forgotYour Password Instructions has been verified" + " with [Actual] and [Expected] value as " + "["
				+ forgotYourPasswordInstructions.getText().trim() + "] and " + "["
				+ data.getForgotYourPasswordInstructions() + "]");
		soft.assertAll();

		log.info("Retrieve password page is verified");
		return this;
	}

	@Step("Enter username as {0}")
	public ForgotPasswordPageObjects enterUserName(String userName) {
		waiting.waitForVisibilityOfElement(userNameTextbox, 4);
		userNameTextbox.click();
		userNameTextbox.sendKeys(userName);
		log.info("User Name is entered :" + userName);
		return this;
	}

	@Step("Enter email id as {0}")
	public ForgotPasswordPageObjects enterEmailId(String emailId) {
		waiting.waitForVisibilityOfElement(emailAddressTextBox, 10);
		emailAddressTextBox.clear();
		emailAddressTextBox.sendKeys(emailId);
		log.info("Email id is enter :" + emailId);
		return this;
	}

	@Step("Click on get new password button")
	public ForgotPasswordPageObjects clickOnGetNewPassword() throws InterruptedException {
		waiting.waitForVisibilityOfElement(getNewPasswordButton, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getNewPasswordButton);
		log.info("clicked on get new password button");
		return this;
	}

	@Step("Verify error message as:{0}")
	public ForgotPasswordPageObjects verifyErrorMessage(String expectedErrorMessage) {
		waiting.waitForVisibilityOfElement(errorMessageLocator, 15);
		Assert.assertEquals(errorMessageLocator.getText().replace("×", "").trim(), expectedErrorMessage);
		log.info("Error Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ errorMessageLocator.getText().replace("×", "").trim() + "] and " + "[" + expectedErrorMessage + "]");
		return this;
	}

	@Step("Verify success message as:{0}")
	public ForgotPasswordPageObjects verifySuccessMessage(String emailid, String expectedMessage) {
		waiting.waitForVisibilityOfElement(successMessageLocator, 5);
		Assert.assertEquals(successMessageLocator.getText().trim().replace("×", "").replace("\n", ""),
				expectedMessage.trim() + " " + emailid, "Success Message is wrong");

		log.info("Success Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ successMessageLocator.getText().trim().replace("×", "").replace("\n", "") + "] and " + "["
				+ expectedMessage.trim() + " " + emailid + "]");

		return this;
	}

	@Step("Verify invalid account error message as:{0}")
	public ForgotPasswordPageObjects verifyErrorMessage_InvalidAccount(String expectedErrorMessage)
			throws InterruptedException {
		waiting.waitForVisibilityOfElement(errorMessageLocator, 10);
		String actual = errorMessageLocator.getText().replace("\n", "").replace("×", "").toLowerCase().trim();

		String expected = expectedErrorMessage.replace("\n", "").toLowerCase().trim();

		Assert.assertEquals(actual, expected, "Alert message mismatch");

		log.info("Error Message For Invalid Account has been verified" + " with [Actual] and [Expected] value as " + "["
				+ actual + "] and " + "[" + expected + "]");

		return this;
	}

	@Step("Verify bread crumb")
	public ForgotPasswordPageObjects verifyBreadCrumbsOfForgotPage(String breadCrumb) {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);

		Assert.assertTrue(productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.trim()
				.equalsIgnoreCase(breadCrumb.trim()), "Invalid Breadcrumb");

		log.info("Breadcrumb of the forgot page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1).getText().trim()
				+ "] and " + "[" + breadCrumb.trim() + "]");
		return this;

	}

	@Step("Click on Home link bread crumb")
	public HomePageObjects clickOnHomeLinkBreadCrumb() throws InterruptedException {
		waiting.waitForVisibilityOfElement(homeBreadCrumbLinkLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", homeBreadCrumbLinkLocator);
		log.info("clicked On Home Link BreadCrumb");
		return homePage();

	}
}
