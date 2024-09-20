package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class RegistrationCAPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utility = new UtilityMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(RegistrationCAPageObjects.class);

	@FindBy(xpath = "//a[contains(@href,'newRetailCustomer')]")
	private WebElement newRetailCustomerTabLocator;

	@FindBy(xpath = "//a[@href='#newCommertialCustomer']")
	private WebElement newCommercialCustomerTabLocator;

	@FindBy(xpath = "//a[contains(@href,'firstTimeOrder')]")
	private WebElement firstTimeOrderingTabLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumbsLocator;

	@FindBy(xpath = "//h1")
	private WebElement registrationPageNameLocator;

	@FindBy(xpath = "//input[@id='firstName2A']")
	private WebElement firstNameLocator;

	@FindBy(xpath = "//input[@id='lastName2A']")
	private WebElement lastNameLocator;

	@FindBy(xpath = "//input[@id='companyName2A']")
	private WebElement companyNameLocator;

	@FindBy(xpath = "//input[@id='emailAddress2A']")
	private WebElement emailAddressLocator;

	@FindBy(xpath = "//input[@id='password2A']")
	private WebElement passwordLocator;

	@FindBy(xpath = "//input[@id='confirmPassword2A']")
	private WebElement confirmPasswordLocator;

	@FindBy(xpath = "//input[@id='billingAddress2A']")
	private WebElement address1Locator;

	@FindBy(xpath = "//input[@id='suiteNo2A']")
	private WebElement address2Locator;

	@FindBy(xpath = "//input[@id='cityName2A']")
	private WebElement cityLocator;

	@FindBy(xpath = "//select[@id='stateName2A']")
	private WebElement stateDropdownLocator;

	@FindBy(xpath = "//div[@id='countrySelect_chosen']/a")
	private WebElement countryLocator;

	@FindBy(xpath = "//input[@id='phoneNo2A']")
	private WebElement phoneNumberLocator;

	@FindBy(xpath = "//input[@id='zipCode2A']")
	private WebElement zipCodeLocator;

	@FindBy(xpath = "//form[@id='form2A']//input[@value='I Accept']")
	private WebElement iAcceptLocator;

	@FindBy(xpath = "//form[contains(@action,'CommercialReg')]/descendant::div[@class='center']")
	private WebElement agreementLocator;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMsgLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement registrationSuccessMessageLocator;

	@Step("Enter email Id : {0}")
	public RegistrationCAPageObjects enterEmailId(String emailId) {
		emailAddressLocator.sendKeys(emailId);
		log.info("Entered Email Id :" + emailId);
		return this;
	}

	@Step("Enter first name : {0}")
	public RegistrationCAPageObjects enterFirstName(String firstName) {
		waiting.waitForVisibilityOfElement(firstNameLocator, 8);
		firstNameLocator.sendKeys(firstName);
		log.info("Entered First Name :" + firstName);
		return this;
	}

	@Step("Enter last name : {0}")
	public RegistrationCAPageObjects enterLastName(String lastName) {
		lastNameLocator.sendKeys(lastName);
		log.info("Entered Last Name :" + lastName);
		return this;
	}

	@Step("Enter company name : {0}")
	public RegistrationCAPageObjects enterCompanyName(String companyName) {
		companyNameLocator.sendKeys(companyName);
		log.info("Entered Company Name :" + companyName);
		return this;
	}

	@Step("Enter password : {0}")
	public RegistrationCAPageObjects enterPassword(String password) {
		passwordLocator.sendKeys(password);
		log.info("Entered Password :" + password);
		return this;
	}

	@Step("Enter confirm password : {0}")
	public RegistrationCAPageObjects enterConfirmPassword(String confirmPassword) {
		confirmPasswordLocator.sendKeys(confirmPassword);
		log.info("Entered Confirm Password :" + confirmPassword);
		return this;
	}

	@Step("Enter address1 : {0}")
	public RegistrationCAPageObjects enterAddress1(String address1) {
		address1Locator.sendKeys(address1);
		log.info("Entered Address1 :" + address1);
		return this;
	}

	@Step("Enter address2 : {0}")
	public RegistrationCAPageObjects enterAddress2(String address2) {
		address2Locator.sendKeys(address2);
		log.info("Entered Address2 :" + address2);
		return this;
	}

	@Step("Enter city : {0}")
	public RegistrationCAPageObjects enterCity(String city) {
		cityLocator.sendKeys(city);
		log.info("Entered City :" + city);
		return this;
	}

	@Step("Enter Zip Code : {0}")
	public RegistrationCAPageObjects enterZipCode(String zipCode) {
		zipCodeLocator.sendKeys(zipCode);
		log.info("Entered Zip Code :" + zipCode);
		return this;
	}

	@Step("Enter Phone Number : {0}")
	public RegistrationCAPageObjects enterPhoneNumber(String phoneNumber) {
		phoneNumberLocator.sendKeys(phoneNumber);
		log.info("Entered Phone number :" + phoneNumber);
		return this;
	}

	@Step("Enter State : {0}")
	public RegistrationCAPageObjects chooseState(String state) throws Exception {
		Thread.sleep(3000);
		if (state.equals("")) {

		} else {

			/*
			 * stateDropdown.click();
			 * waiting.waitForVisibilityOfElements(stateDrodpdownOptions, 3); for
			 * (WebElement stateOption : stateDrodpdownOptions) { if (stateOption .getText()
			 * .trim() .equals(state)) { stateOption.click(); break; }
			 */
		}
		return this;
	}

	@Step("Select State : {0}")
	public RegistrationCAPageObjects selectState(String state) {
		waiting.waitForVisibilityOfElement(stateDropdownLocator, 8);
		Select select = new Select(stateDropdownLocator);
		select.selectByVisibleText(state.trim());
		log.info("Selected state :" + state);
		return this;
	}

	@Step("Click I Accept")
	public RegistrationCAPageObjects clickOnIAccept() {
		waiting.waitForVisibilityOfElement(iAcceptLocator, 5);
		iAcceptLocator.click();
		log.info("Clicked on I Accept Button");
		return this;
	}

	@Step("Verify Expected error message to be : {0}")
	public RegistrationCAPageObjects verifyErrorMsg(String expectedErrorMsg) throws Exception {
		Thread.sleep(1200);
		utility.scrollThePageToTop();
		Assert.assertEquals(errorMsgLocator.getText().trim().replace("×", "").replace("\n", "").toLowerCase(),
				expectedErrorMsg.trim().replace("\n", "").toLowerCase());
		log.info("Verified error message :" + expectedErrorMsg);
		return this;
	}

	@Step("Verify success message")
	public RegistrationCAPageObjects verifySuccessMsg() throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElement(registrationSuccessMessageLocator, 15);
		utility.scrollThePageToTop();
		Assert.assertTrue(registrationSuccessMessageLocator.isDisplayed(), "Registration Unsuccessful");
		log.info("Verified success message");
		return this;
	}

	public RegistrationCAPageObjects verifySuccessMsg(String emailId, String successMsg) throws InterruptedException {
		Thread.sleep(3000);
		waiting.waitForVisibilityOfElement(registrationSuccessMessageLocator, 10);
		Assert.assertTrue(registrationSuccessMessageLocator.isDisplayed(),
				"Retail Registration success Message is not displayed.");
		Assert.assertEquals(registrationSuccessMessageLocator.getText().replace("\n", "").trim(),
				emailId + " " + successMsg);
		log.info("Verified success message :" + successMsg);
		return this;

	}

	public RegistrationCAPageObjects enterEmailIdForPositiveFlow(String emailId) {
		emailAddressLocator.sendKeys(emailId);
		log.info("Verified Email id :" + emailId);
		return this;
	}

	@Step("Verify registration breadcrump is : {0}")
	public RegistrationCAPageObjects verifyBreadCrumb(String registrationBreadCrumb) {
		Assert.assertEquals(breadCrumbsLocator.get(breadCrumbsLocator.size() - 1).getText().replace("|", "").trim(),
				registrationBreadCrumb);
		log.info("Verified Breadcrumb");
		return this;
	}

	@Step("Verify registration page name is : {0}")
	public RegistrationCAPageObjects verifyRegistrationPageName(String registrationBreadCrumb) {
		Assert.assertEquals(registrationPageNameLocator.getText().trim().toLowerCase(),
				registrationBreadCrumb.toLowerCase(), "Getting " + registrationPageNameLocator.getText().trim()
						+ " But expecting " + registrationBreadCrumb + ".");
		log.info("Verified Page name");
		return this;
	}

	@Step("Click on new commercial account tab")
	public RegistrationCAPageObjects clickOnNewCommercialAccount() throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitTillPageLoads();
		newCommercialCustomerTabLocator.click();
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", newCommercialCustomerTabLocator);
		log.info("Clicked on New Commercial Account Tab");
		return this;
	}

	@Step("Verify commercial user registration tab")
	public RegistrationCAPageObjects verifyCommercialUserRegistrationTab() throws InterruptedException {
		waiting.waitTillPageLoads();
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElement(firstNameLocator, 8);
		softAssert.assertTrue(firstNameLocator.isDisplayed(), "first name is not displayed.");
		softAssert.assertTrue(lastNameLocator.isDisplayed(), "last name is not displayed.");
		softAssert.assertTrue(companyNameLocator.isDisplayed(), "company name is not displayed.");
		softAssert.assertTrue(emailAddressLocator.isDisplayed(), "email address is not displayed.");
		softAssert.assertTrue(passwordLocator.isDisplayed(), "password is not displayed.");
		softAssert.assertTrue(confirmPasswordLocator.isDisplayed(), "confirm password is not displayed.");
		softAssert.assertTrue(address1Locator.isDisplayed(), "address1 is not displayed.");
		softAssert.assertTrue(address2Locator.isDisplayed(), "address2 is not displayed.");
		softAssert.assertTrue(cityLocator.isDisplayed(), "city is not displayed.");
		softAssert.assertTrue(phoneNumberLocator.isDisplayed(), "phone number is not displayed.");
		softAssert.assertTrue(zipCodeLocator.isDisplayed(), "zipcode is not displayed.");
		softAssert.assertTrue(agreementLocator.isDisplayed(), "agreement is not displayed.");
		softAssert.assertTrue(iAcceptLocator.isDisplayed(), "I Accept button is not displayed.");
		softAssert.assertAll();
		log.info("Verified Commercial User Registration Tab");
		return this;
	}
}
