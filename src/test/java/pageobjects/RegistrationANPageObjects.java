package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class RegistrationANPageObjects extends PageInitializer {

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(RegistrationANPageObjects.class);

	@FindBy(xpath = "//h1")
	private WebElement pageName;

	@FindBy(id = "accountNo1B")
	private WebElement accountNumberLocator;

	@FindBy(id = "firstName1B")
	private WebElement firstNameLocator;

	@FindBy(id = "lastName1B")
	private WebElement lastNameLocator;

	@FindBy(id = "emailAddress1B")
	private WebElement emailAddressLocator;

	@FindBy(id = "newPassword1B")
	private WebElement passwordLocator;

	@FindBy(id = "newPasswordConfirm1B")
	private WebElement passwordConfirmationLocator;

	@FindBy(xpath = "//input[@id='RegisterWSA']")
	private WebElement iAcceptLocator;

	@FindBy(xpath = "//form[contains(@action,'OnAccountReg')]/descendant::div[@class='center']")
	private WebElement agreementLocator;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMsgLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement registrationSuccessMessageLocator;

	@Step("Verify page name")
	public RegistrationANPageObjects verifyPageName() {
		waiting.waitForVisibilityOfElement(pageName, 15);
		Assert.assertTrue(pageName.isDisplayed(), "Not landed to Registration page");
		log.info("Verified registration page name");
		return this;
	}

	@Step("Verify new retail customer tab")
	public RegistrationANPageObjects verifyOnAccountRegistrationTab() {
		softAssert.assertTrue(accountNumberLocator.isDisplayed(), "account number locator is not displayed.");
		softAssert.assertTrue(lastNameLocator.isDisplayed(), "last name is not displayed.");
		softAssert.assertTrue(firstNameLocator.isDisplayed(), "company name is not displayed.");
		softAssert.assertTrue(emailAddressLocator.isDisplayed(), "email address is not displayed.");
		softAssert.assertTrue(passwordLocator.isDisplayed(), "password is not displayed.");
		softAssert.assertTrue(passwordConfirmationLocator.isDisplayed(), "confirm password is not displayed.");
		softAssert.assertAll();
		log.info("Verified On Account Registration Tab");
		return this;
	}

	@Step("Enter account number as:: {0}")
	public RegistrationANPageObjects enterAccountNumber(String accNumber) {
		waiting.waitForVisibilityOfElement(accountNumberLocator, 8);
		accountNumberLocator.sendKeys(accNumber);
		log.info("Entered Account Number :" + accNumber);
		return this;
	}

	@Step("Click I Accept")
	public RegistrationANPageObjects clickOnIAccept() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", iAcceptLocator);
		log.info("Clicked on I Accept Button");
		return this;
	}

	@Step("Enter email Id : {0}")
	public RegistrationANPageObjects enterEmailId(String emailId) {
		emailAddressLocator.sendKeys(emailId);
		log.info("Entered Email Id :" + emailId);
		return this;
	}

	@Step("Enter first name : {0}")
	public RegistrationANPageObjects enterFirstName(String firstName) {
		firstNameLocator.sendKeys(firstName);
		log.info("Entered First Name :" + firstName);
		return this;
	}

	@Step("Enter last name : {0}")
	public RegistrationANPageObjects enterLastName(String lastName) {
		lastNameLocator.sendKeys(lastName);
		log.info("Entered Last Name :" + lastName);
		return this;
	}

	@Step("Enter password : {0}")
	public RegistrationANPageObjects enterPassword(String password) {
		passwordLocator.sendKeys(password);
		log.info("Entered Password :" + password);
		return this;
	}

	@Step("Enter confirm password : {0}")
	public RegistrationANPageObjects enterConfirmPassword(String confirmPassword) {
		passwordConfirmationLocator.sendKeys(confirmPassword);
		log.info("Entered Confirm password :" + confirmPassword);
		return this;
	}

	@Step("Verify success message")
	public RegistrationANPageObjects verifySuccessMsg() throws InterruptedException {
		Thread.sleep(1500);
		utilityMethods.scrollThePageToTop();
		waiting.waitForVisibilityOfElement(registrationSuccessMessageLocator, 15);
		Assert.assertTrue(registrationSuccessMessageLocator.isDisplayed(), "Registration Unsuccessful");
		log.info("Verified success message");
		return this;
	}

	@Step("Verify Expected error message to be : {0}")
	public RegistrationANPageObjects verifyErrorMsg(String expectedErrorMsg) throws Exception {
		Thread.sleep(1200);
		utilityMethods.scrollThePageToTop();
		Assert.assertEquals(errorMsgLocator.getText().trim().replace("×", "").replace("\n", "").toLowerCase(),
				expectedErrorMsg.trim().replace("\n", "").toLowerCase());
		log.info("Verified Error message :" + expectedErrorMsg);
		return this;
	}
}
