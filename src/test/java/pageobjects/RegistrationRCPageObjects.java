package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class RegistrationRCPageObjects extends PageInitializer {

	UtilityMethods utility = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(RegistrationRCPageObjects.class);

	@FindBy(xpath = "//a[contains(@href,'newRetailCustomer')]")
	private WebElement retailUserTab;

	@FindBy(xpath = "//input[@id='firstName2B']")
	private WebElement firstNameLocator;

	@FindBy(xpath = "//input[@id='lastName2B']")
	private WebElement lastNameLocator;

	@FindBy(xpath = "//input[@id='emailAddress2B']")
	private WebElement emailAddressLocator;

	@FindBy(xpath = "//input[@id='password2B']")
	private WebElement passwordLocator;

	@FindBy(xpath = "//input[@id='confirmPassword2B']")
	private WebElement confirmPasswordLocator;

	@FindBy(xpath = "//input[@id='billingAddress2B']")
	private WebElement address1Locator;

	@FindBy(xpath = "//input[@id='suiteNo2B']")
	private WebElement address2Locator;

	@FindBy(xpath = "//input[@id='cityName2B']")
	private WebElement cityLocator;

	@FindBy(xpath = "//select[@id='stateName2B']")
	private WebElement stateDropdownLocator;

	@FindBy(xpath = "//div[@id='countrySelect_chosen']/a")
	private WebElement countryLocator;

	@FindBy(xpath = "//input[@id='phoneNo2B']")
	private WebElement phoneNumberLocator;

	@FindBy(xpath = "//input[@id='zipCode2B']")
	private WebElement zipCodeLocator;

	@FindBy(xpath = "//form[contains(@action,'RetailReg')]/descendant::div[@class='center']")
	private WebElement agreementLocator;

	@FindBy(xpath = "//form[contains(@action,'RetailReg')]/descendant::input[@value='I Accept']")
	private WebElement iAcceptLocator;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMsgLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement registrationSuccessMessageLocator;

	@FindBy(xpath = "//input[@id='companyName2B']")
	private WebElement companyNameLocator;

	@Step("Click on retail user tab")
	public RegistrationRCPageObjects clickOnRetailUserTab() throws InterruptedException {
		// waiting.waitForVisibilityOfElement(retailUserTab, 8);
		Thread.sleep(1000);
		retailUserTab.click();
		// ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		// retailUserTab);
		log.info("Clicked on Retail User tab");
		return this;
	}

	@Step("Verify new retail customer tab")
	public RegistrationRCPageObjects verifyNewRetailCustomerTab() throws Exception {
		waiting.waitForVisibilityOfElement(firstNameLocator, 15);
		softAssert.assertTrue(firstNameLocator.isDisplayed(), "first name is not displayed.");
		softAssert.assertTrue(lastNameLocator.isDisplayed(), "last name is not displayed.");
		softAssert.assertTrue(emailAddressLocator.isDisplayed(), "email address is not displayed.");
		softAssert.assertTrue(passwordLocator.isDisplayed(), "password is not displayed.");
		softAssert.assertTrue(confirmPasswordLocator.isDisplayed(), "confirm password is not displayed.");
		softAssert.assertTrue(address1Locator.isDisplayed(), "address1 is not displayed.");
		softAssert.assertTrue(address2Locator.isDisplayed(), "address2 is not displayed.");
		softAssert.assertTrue(cityLocator.isDisplayed(), "city is not displayed.");
		softAssert.assertTrue(phoneNumberLocator.isDisplayed(), "phone number is not displayed.");
		softAssert.assertTrue(zipCodeLocator.isDisplayed(), "zipcode is not displayed.");
		softAssert.assertTrue(agreementLocator.isDisplayed(), "agreement is not displayed.");
		softAssert.assertTrue(iAcceptLocator.isDisplayed(), "agreement is not displayed.");
		softAssert.assertAll();
		log.info("Verified New Retail Customer Tab");
		return this;
	}

	@Step("Enter email Id : {0}")
	public RegistrationRCPageObjects enterEmailId(String emailId) throws InterruptedException {
		emailAddressLocator.sendKeys(emailId);
		log.info("Entered Email Id :" + emailId);
		return this;
	}

	@Step("Enter first name : {0}")
	public RegistrationRCPageObjects enterFirstName(String firstName) {
		waiting.waitForVisibilityOfElement(firstNameLocator, 6);
		firstNameLocator.sendKeys(firstName);
		log.info("Entered First Name :" + firstName);
		return this;
	}

	@Step("Enter last name : {0}")
	public RegistrationRCPageObjects enterLastName(String lastName) {
		lastNameLocator.sendKeys(lastName);
		log.info("Entered Last Name :" + lastName);
		return this;
	}

	@Step("Enter company name : {0}")
	public RegistrationRCPageObjects enterCompanyName(String companyName) {
		companyNameLocator.sendKeys(companyName);
		log.info("Entered Company Name :" + companyName);
		return this;
	}

	@Step("Enter password : {0}")
	public RegistrationRCPageObjects enterPassword(String password) {
		passwordLocator.sendKeys(password);
		log.info("Entered Password :" + password);
		return this;
	}

	@Step("Enter confirm password : {0}")
	public RegistrationRCPageObjects enterConfirmPassword(String confirmPassword) {
		confirmPasswordLocator.sendKeys(confirmPassword);
		log.info("Entered Confirm Password :" + confirmPassword);
		return this;
	}

	@Step("Enter address1 : {0}")
	public RegistrationRCPageObjects enterAddress1(String address1) {
		address1Locator.sendKeys(address1);
		log.info("Entered Address1 :" + address1);
		return this;
	}

	@Step("Enter address2 : {0}")
	public RegistrationRCPageObjects enterAddress2(String address2) {
		address2Locator.sendKeys(address2);
		log.info("Entered Address2 :" + address2);
		return this;
	}

	@Step("Enter city : {0}")
	public RegistrationRCPageObjects enterCity(String city) {
		cityLocator.sendKeys(city);
		log.info("Entered City :" + city);
		return this;
	}

	@Step("Enter Zip Code : {0}")
	public RegistrationRCPageObjects enterZipCode(String zipCode) {
		zipCodeLocator.sendKeys(zipCode);
		log.info("Entered Zip Code :" + zipCode);
		return this;
	}

	@Step("Enter Phone Number : {0}")
	public RegistrationRCPageObjects enterPhoneNumber(String phoneNumber) {
		phoneNumberLocator.sendKeys(phoneNumber);
		log.info("Entered Phone Number :" + phoneNumber);
		return this;
	}

	@Step("Select State : {0}")
	public RegistrationRCPageObjects selectState(String state) {
		waiting.waitForVisibilityOfElement(stateDropdownLocator, 8);
		Select select = new Select(stateDropdownLocator);
		select.selectByVisibleText(state.trim());
		log.info("Selected State :" + state);
		return this;
	}

	@Step("Click I Accept")
	public RegistrationRCPageObjects clickOnIAccept() {
		iAcceptLocator.click();
		log.info("Clicked on I Accept button");
		return this;
	}

	@Step("Verify success message")
	public RegistrationRCPageObjects verifySuccessMsg() throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElement(registrationSuccessMessageLocator, 15);
		utility.scrollThePageToTop();
		Assert.assertTrue(registrationSuccessMessageLocator.isDisplayed(), "Registration Unsuccessful");
		log.info("Verified success message");
		return this;
	}

	@Step("Verify Expected error message to be : {0}")
	public RegistrationRCPageObjects verifyErrorMsg(String expectedErrorMsg) throws Exception {
		Thread.sleep(1200);
		utility.scrollThePageToTop();
		Assert.assertEquals(errorMsgLocator.getText().trim().replace("×", "").replace("\n", "").toLowerCase(),
				expectedErrorMsg.trim().replace("\n", "").toLowerCase());
		log.info("Verified error message :" + expectedErrorMsg);
		return this;
	}

}