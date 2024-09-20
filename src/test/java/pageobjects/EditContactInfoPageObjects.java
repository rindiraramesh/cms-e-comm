package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.MainOperations;
import utility.WaitingMethods;

public class EditContactInfoPageObjects extends PageInitializer {
	@FindBy(xpath = "//ul[contains(@class,'breadcrumbs')]/descendant::a[text()[normalize-space()='My Account']]")
	public WebElement myAccountInBreadcrumbLocator;

	TestDataPropertyFile data = new TestDataPropertyFile();

	Actions action = new Actions(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());
	
	MainOperations mo = new MainOperations();

	@FindBy(id = "firstName")
	private WebElement firstName;

	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "address1")
	private WebElement address1;

	@FindBy(id = "address2")
	private WebElement address2;

	@FindBy(xpath = "//input[@id='city']")
	private WebElement city;

	@FindBy(id = "billPhone")
	private WebElement phoneNumber;

	@FindBy(id = "phoneNo")
	private WebElement phoneNumberLocatorInShippingAddressPage;

	@FindBy(id = "countrySelectShip")
	private WebElement country;

	@FindBy(id = "stateSelectShip")
	private WebElement state;

	@FindBy(xpath = "//div[@id='successBlock']/a[@href='/Address']")
	private WebElement backToAddressPageButtonLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement billingAddressSuccessMessageLoctor;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMessageLocatorWhenMandatoryFieldBlank;

	@FindBy(id = "zip")
	private WebElement zip;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(xpath = "//span[contains(text(),'Use Entity Address')]")
	private WebElement useEntityAddress;

	@FindBy(id = "updateBtn")
	private WebElement updateButton;

	@FindBy(xpath = "//span[@id='message']")
	private WebElement confirmationMessageLocator;

	@FindBy(id = "errorMsg")
	private WebElement errorMessageLocator;

	/*
	 * @FindBy(xpath = "//div[@id='stateSelectShip_chosen']/a") private WebElement
	 * selectStateAfterPluginLoadLocator;
	 */

	@FindBy(xpath = "//button[@data-id='stateSelectShip']")
	private WebElement selectStateAfterPluginLoadLocator;

	@FindBy(xpath = "//div[@id='stateSelectShip_chosen']/descendant::li")
	private List<WebElement> stateResultsLocator;

	@FindBy(xpath = "//div[@id='countrySelectShip_chosen']/descendant::a")
	private WebElement countryAfterPluginLoadLocator;

	@FindBy(xpath = "//div[@id='countrySelectShip_chosen']/descendant::input")
	private WebElement countryInputTextLocator;

	@FindBy(xpath = "//div[@id='countrySelectShip_chosen']/descendant::li")
	private List<WebElement> countryResultsLocator;

	@FindBy(xpath = "//label[@for='getentityAddress']/input[@id='getentityAddress' and @type='checkbox']")
	private WebElement useEntityAddressCheckboxLocator;

	@FindBy(xpath = "//div[@id='stateSelectShip_chosen']/descendant::span")
	private WebElement defaultValueOfSelectDropdownLocator;

	/*
	 * @FindBy(xpath = "//h1[@class='cimm_pageTitle']") private WebElement
	 * editContactInfoPageName;
	 */

	/*
	 * @FindBy(xpath = "//h1[@class='cimm_page-title']") private WebElement
	 * editContactInfoPageName;
	 */

	@FindBy(xpath = "//h1")
	private WebElement editContactInfoPageName;

	@FindBy(xpath = "//button[@data-id='stateSelectShip']")
	private WebElement stateDropdownLocator;

	@FindBy(xpath = "//div[@id='stateSelectShip_chosen']/descendant::input")
	private WebElement stateInputTextLocator;

	@FindBy(id = "email")
	private WebElement emailLocatorInShippingAddressPage;

	@FindBy(id = "address2")
	private WebElement address2Locator;

	@FindBy(id = "customerName")
	private WebElement customerNameLocator;
	
	@FindBy(id = "shipFirstName")
	private WebElement firstName_SA;

	@FindBy(id = "shipLastName")
	private WebElement lastName_SA;

	@FindBy(id = "address1")
	private WebElement address1_SA;

	@FindBy(id = "address2")
	private WebElement address2_SA;

	@FindBy(xpath = "//input[@id='city']")
	private WebElement city_SA;

	@FindBy(id = "shipPhone")
	private WebElement phoneNumber_SA;

	@FindBy(xpath = "//button[@title='Select State']")
	private WebElement state_SA;
	
	@FindBy(id = "EmailAddress")
	private WebElement email_SA;
	
	@FindBy(id = "Zipcode")
	private WebElement zipcode_SA;

	@Step("Verify edit contact info page title contains {0}")
	public EditContactInfoPageObjects verifyEditContactInfoPageTitle(String productName) {
		logger = Logger.getLogger("verifyEditContactInfoPageTitle");
		Assert.assertEquals(getDriver().getTitle().trim(), productName, "Title is wrong");
		logger.info("Title of the Edit Contact page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + productName + "]");
		return this;
	}

	@Step("Verify edit contact info page name contains {0}")
	public EditContactInfoPageObjects verifyEditContactInfoPageName(String editContactInfoPageName1) {
		logger = Logger.getLogger("verifyEditContactInfoPageName");
		Assert.assertEquals(editContactInfoPageName.getText().trim().toUpperCase(),
				editContactInfoPageName1.toUpperCase(),
				"Edit Contact Page Name is not: " + editContactInfoPageName1.toUpperCase() + " .It is : "
						+ editContactInfoPageName.getText().trim().toUpperCase());
		logger.info("Edit Contact Page Name has been verified" + " with [Actual] and [Expected] value as " + "["
				+ editContactInfoPageName.getText().trim().toUpperCase() + "] and " + "["
				+ editContactInfoPageName1.toUpperCase() + "]");
		return this;
	}

	@Step("Verify Billing Address page name contains {0}")
	public EditContactInfoPageObjects verifyBillingAddressPageName() {
		logger = Logger.getLogger("verifyBillingAddressPageName");
		Assert.assertEquals(editContactInfoPageName.getText().trim(), "BILLING ADDRESS");
		logger.info("Billing Address page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ editContactInfoPageName.getText().trim() + "] and " + "[" + "BILLING ADDRESS" + "]");
		return this;

	}

	@Step("Verify edit contact info breadcrumb contains {0}")
	public EditContactInfoPageObjects verifyEditContactInfoBreadcrumb(String editContactInfoPageName) {
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.replace("/", "")
						.trim()
						.equalsIgnoreCase(editContactInfoPageName),
				"Breadcrump is not " + editContactInfoPageName + ". It is "
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim()
						+ ".");
		return this;
	}

	@Step("Verify entity address checkbox")
	public EditContactInfoPageObjects verifyDisplayOfEntityAddressCheckbox() {
		Assert.assertTrue(useEntityAddress.isDisplayed(), "Use entity checkbox is not displayed.");
		return this;
	}

	@Step("Verify update button")
	public EditContactInfoPageObjects verifyDisplayOfUpdateButton() {
		Assert.assertTrue(updateButton.isDisplayed(), "Update button is not displayed");
		return this;
	}

	@Step("Get First Name")
	public String getFirstName() {

		return firstName.getAttribute("value").trim();
	}

	@Step("Get Last name")
	public String getLastName() {

		return lastName.getAttribute("value").trim();
	}

	@Step("Get phone number")
	public String getPhoneNumber() {

		return phoneNumber.getAttribute("value").trim();
	}

	@Step("Get Email Address")
	public String getEmailAddress() {

		return email.getAttribute("value").trim();
	}

	@Step("Get contact details")
	public String[] getContactDetails() {
		String[] str = { getFirstName(), getLastName(), getPhoneNumber(), getEmailAddress() };
		return str;
	}

	@Step("Verify display of my account link in breadcrumb")
	public EditContactInfoPageObjects verifyDisplayOfMyAccountLinkInBreadCrumb(String myAccountBreadcrumb) {
		Assert.assertTrue(myAccountInBreadcrumbLocator.isDisplayed(), "My Account in Breadcrumb is not displayed.");
		return this;
	}

	@Step("Enter first name:{0}")
	public EditContactInfoPageObjects enterFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		return this;
	}

	@Step("Enter Customer name:{0}")
	public EditContactInfoPageObjects enterCustomerName(String customerName) {
		logger = Logger.getLogger("enterCustomerName");
		waiting.waitForVisibilityOfElement(customerNameLocator, 10);
		customerNameLocator.clear();
		customerNameLocator.sendKeys(customerName);
		logger.info("Entered Customer name: " + customerName);
		return this;
	}

	@Step("Enter last name:{0}")
	public EditContactInfoPageObjects enterLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
		return this;
	}

	@Step("Enter address1:{0}")
	public EditContactInfoPageObjects enterAddress1(String address1) {
		logger = Logger.getLogger("enterAddress1");
		waiting.waitForVisibilityOfElement(this.address1, 10);
		this.address1.clear();
		this.address1.sendKeys(address1);
		logger.info("Entered address1: " + address1);
		return this;
	}

	@Step("Enter Address2 :{0}")
	public EditContactInfoPageObjects enterAddress2(String address2) {
		logger = Logger.getLogger("enterAddress2");
		waiting.waitForVisibilityOfElement(address2Locator, 10);
		address2Locator.clear();
		address2Locator.sendKeys(address2);
		logger.info("Enter Address2 : " + address2);
		return this;

	}

	@Step("Enter city:{0}")
	public EditContactInfoPageObjects enterCity(String city) {
		logger = Logger.getLogger("enterCity");
		this.city.clear();
		this.city.sendKeys(city);
		logger.info("Enter City : " + city);
		return this;
	}

	@Step("Enter zip code:{0}")
	public EditContactInfoPageObjects enterZipCode(String zipcode) {
		logger = Logger.getLogger("enterZipCode");
		waiting.waitForVisibilityOfElement(zip, 10);
		zip.clear();
		zip.sendKeys(zipcode);
		logger.info("Enter zip code: " + zipcode);
		return this;
	}

	@Step("Enter phone number:{0}")
	public EditContactInfoPageObjects enterPhoneNumber(String phoneNumber) {
		logger = Logger.getLogger("enterPhoneNumber");
		waiting.waitForVisibilityOfElement(this.phoneNumber, 10);
		this.phoneNumber.clear();
		this.phoneNumber.sendKeys(phoneNumber);
		logger.info("Enter phone number: " + phoneNumber);
		return this;
	}

	@Step("Enter Phone Number in Shipping Address Page :{0}")
	public EditContactInfoPageObjects enterPhoneNumberInShippingAddressPage(String phoneNumber) {
		logger = Logger.getLogger("enterPhoneNumberInShippingAddressPage");
		waiting.waitForVisibilityOfElement(phoneNumberLocatorInShippingAddressPage, 10);
		phoneNumberLocatorInShippingAddressPage.clear();
		phoneNumberLocatorInShippingAddressPage.sendKeys(phoneNumber);
		logger.info("Entered Phone Number in Shipping Address Page : " + phoneNumber);
		return this;
	}

	@Step("Enter Email in Shipping Address Page :{0}")
	public EditContactInfoPageObjects enterEmailInShippingAddressPage(String email) {
		logger = Logger.getLogger("enterEmailInShippingAddressPage");
		waiting.waitForVisibilityOfElement(emailLocatorInShippingAddressPage, 10);
		emailLocatorInShippingAddressPage.clear();
		emailLocatorInShippingAddressPage.sendKeys(email);
		logger.info("Entered Email in Shipping Address Page : " + email);

		return this;
	}

	@Step("Click on update button")
	public EditContactInfoPageObjects clickOnUpdateButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnUpdateButton");
		waiting.waitForVisibilityOfElement(updateButton, 10);
		updateButton.click();
		Thread.sleep(1200);
		logger.info("Clicked on update button");
		return this;
	}

	@Step("Verify success message:{0}")
	public EditContactInfoPageObjects verifySuccessMessage(String expectedSuccessMessage) {
		waiting.waitForVisibilityOfElement(confirmationMessageLocator, 3);
		Assert.assertEquals(confirmationMessageLocator.getText().trim(), expectedSuccessMessage,
				"Contact info is not updated successfully");
		return this;
	}

	@Step("Verify updated phone number is:{0}")
	public EditContactInfoPageObjects verifyUpdationOfPhoneNumber(String phoneNumber) {
		Assert.assertEquals(this.phoneNumber.getAttribute("value").trim(), phoneNumber, "Phone number is not updated.");
		return this;
	}

	@Step("Verify error message is:{0}")
	public EditContactInfoPageObjects verifyErrorMessage(String expectedErrorMessage) {
		waiting.waitForVisibilityOfElement(errorMessageLocator, 5);
		Assert.assertEquals(errorMessageLocator.getText().replace("\n", "").trim(), expectedErrorMessage);
		return this;
	}

	@Step("Select state:{0}")
	public EditContactInfoPageObjects selectState(String state) throws InterruptedException {

		waiting.waitForVisibilityOfElement(selectStateAfterPluginLoadLocator, 10);
		// selectStateAfterPluginLoadLocator.click();

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", selectStateAfterPluginLoadLocator);

		Thread.sleep(1200);
		/*
		 * getDriver().findElement(By.xpath(
		 * "//div[@id='stateSelectShip_chosen']/descendant::li[text()='" + state +
		 * "']")) .click();
		 */

		getDriver().findElement(By.xpath("//span[normalize-space(text())='" + state + "']/..")).click();
		return this;
	}

	@Step("Click on country")
	public EditContactInfoPageObjects clickOnCountry() throws InterruptedException {
		Thread.sleep(3000);
		countryAfterPluginLoadLocator.click();
		return this;
	}

	@Step("Select country as:{0}")
	public EditContactInfoPageObjects enterCountry(String country) throws Exception {
		Thread.sleep(1500);
		countryInputTextLocator.sendKeys(country);
		return this;
	}

	@Step("Verify {0} is displayed in dropdown")
	public EditContactInfoPageObjects verifyCountryIsDisplayedInTheCountryDropdown(String country)
			throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(countryResultsLocator.get(0).getText().trim(), country);
		return this;
	}

	@Step("Click on state")
	public EditContactInfoPageObjects clickOnState() throws InterruptedException {
		Thread.sleep(3000);
		selectStateAfterPluginLoadLocator.click();
		return this;
	}

	@Step("Enter state:{0}")
	public EditContactInfoPageObjects enterState(String state) throws InterruptedException {
		Thread.sleep(1500);
		stateInputTextLocator.sendKeys(state);
		return this;
	}

	@Step("Enter state:{0}")
	public EditContactInfoPageObjects enterStateInBillingAddressPage(String state) throws InterruptedException {
		logger = Logger.getLogger("enterStateInBillingAddressPage");
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", stateDropdownLocator);
		Thread.sleep(1500);
		WebElement stateName = getDriver()
				.findElement(By.xpath("//span[normalize-space(text())='" + state.trim() + "']/.."));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", stateName);
		logger.info("Enter state: " + state);
		return this;
	}

	@Step("Verify {0} is displayed in dropdown")
	public EditContactInfoPageObjects verifyStateIsDisplayedInTheDropdown(String country) throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(stateResultsLocator.get(0).getText().trim(), country);
		return this;
	}

	@Step("Click on Use Entity Address")
	public EditContactInfoPageObjects clickOnUseEntityAddress() throws InterruptedException {
		Thread.sleep(2500);
		useEntityAddressCheckboxLocator.click();
		return this;
	}

	@Step("Verify Use Entity Address is Not selected")
	public EditContactInfoPageObjects verifyWhetherUseEntityAddressIsNotSelected() {
		Assert.assertEquals(
				((JavascriptExecutor) getDriver()).executeScript("return $('#getentityAddress').attr('checked');"),
				null, "remember me checkbox is not checked");
		return this;
	}

	@Step("Verify for empty fields of address,city,state and phone number")
	public EditContactInfoPageObjects verifyWhetherAddress1CityStatePhoneNumberIsEmpty() throws InterruptedException {
		Assert.assertEquals(address1.getAttribute("value").trim(), "", "Address1 is not empty");
		Assert.assertEquals(city.getAttribute("value").trim(), "", "City is not empty");
		Assert.assertEquals(phoneNumber.getAttribute("value").trim(), "", "Phone Number is not empty");
		Thread.sleep(1500);
		Assert.assertEquals(defaultValueOfSelectDropdownLocator.getText().trim(), "Select State",
				"Dropdown default value did not set to Select State.");
		return this;
	}

	@Step("Verify address,city,state and phone number are not empty")
	public EditContactInfoPageObjects verifyWhetherAddress1CityStatePhoneNumberIsNotEmpty()
			throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertNotEquals(address1.getAttribute("value").trim(), "", "Address1 is empty");
		Assert.assertNotEquals(city.getAttribute("value").trim(), "", "City is empty");
		Assert.assertNotEquals(defaultValueOfSelectDropdownLocator.getText().trim(), "Select State",
				"Dropdown default value did not set to Select State.");
		Assert.assertNotEquals(phoneNumber.getAttribute("value").trim(), "", "Phone Number is empty");
		return this;
	}

	@Step("Verify Shipping Address And Billing Address Updation Success Message :{0}")
	public EditContactInfoPageObjects verifyBillingAddressAndShippingAddressUpdationSuccessMessage(
			String successMessage) {
		logger = Logger.getLogger("verifyBillingAddressAndShippingAddressUpdationSuccessMessage");
		waiting.waitForVisibilityOfElement(billingAddressSuccessMessageLoctor, 10);
		Assert.assertEquals(billingAddressSuccessMessageLoctor.getText().trim(), successMessage.trim(),
				"Billing Address And Shipping Address Updation Message is not :" + successMessage.trim() + ". It is : "
						+ billingAddressSuccessMessageLoctor.getText().trim());
		logger.info("Billing Address And Shipping Address Updation Message has been verified"
				+ " with [Actual] and [Expected] value as " + "[" + billingAddressSuccessMessageLoctor.getText().trim()
				+ "] and " + "[" + successMessage.trim() + "]");
		return this;

	}

	@Step("Click On Back To Address Page Button")
	public EditContactInfoPageObjects clickOnBackToAddressPageButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnBackToAddressPageButton");
		waiting.waitForVisibilityOfElement(backToAddressPageButtonLocator, 10);
		backToAddressPageButtonLocator.click();
		Thread.sleep(1200);
		logger.info("Clicked On Back To Address Page Button.");
		return this;

	}

	@Step("Verify Error Message When Mandatory Fields Leave Blanks:{0}")
	public EditContactInfoPageObjects verifyErrorMessageWhenMandatoryFieldBlank(String errorMessage) {
		logger = Logger.getLogger("verifyErrorMessageWhenMandatoryFieldBlank");
		waiting.waitForVisibilityOfElement(errorMessageLocatorWhenMandatoryFieldBlank, 10);
		Assert.assertEquals(
				errorMessageLocatorWhenMandatoryFieldBlank.getText().trim().replace("×", "").replace("\n", ""),
				errorMessage.trim(),
				"Error Message is not :" + errorMessage.trim() + ". It is :"
						+ errorMessageLocatorWhenMandatoryFieldBlank.getText().trim().replace("×", "").replace("\n", "")
						+ "When Mandatory Fields Leave Blank.");
		logger.info("Error Message When Mandatory Fields Leave Blanks has been verified"
				+ " with [Actual] and [Expected] value as " + "["
				+ errorMessageLocatorWhenMandatoryFieldBlank.getText().trim().replace("×", "").replace("\n", "")
				+ "] and " + "[" + errorMessage.trim() + "]");
		return this;
	}

	public EditContactInfoPageObjects enterFirstName_AddNewShippingAddress(String firstName) {
		this.firstName_SA.clear();
		this.firstName_SA.sendKeys(firstName);
		return this;
	}


	@Step("Enter last name:{0}")
	public EditContactInfoPageObjects enterLastName_AddNewShippingAddress(String lastName) {
		this.lastName_SA.clear();
		this.lastName_SA.sendKeys(lastName);
		return this;
	}

	@Step("Enter address1:{0}")
	public EditContactInfoPageObjects enterAddress1_AddNewShippingAddress(String address1) {
		logger = Logger.getLogger("enterAddress1");
		this.address1_SA.clear();
		this.address1_SA.sendKeys(address1);
		logger.info("Entered address1: " + address1);
		return this;
	}

	@Step("Enter Address2 :{0}")
	public EditContactInfoPageObjects enterAddress2_AddNewShippingAddress(String address2) {
		logger = Logger.getLogger("enterAddress2");
		address2_SA.clear();
		address2_SA.sendKeys(address2);
		logger.info("Enter Address2 : " + address2);
		return this;

	}

	@Step("Enter city:{0}")
	public EditContactInfoPageObjects enterCity_AddNewShippingAddress(String city) {
		logger = Logger.getLogger("enterCity");
		city_SA.clear();
		city_SA.sendKeys(city);
		logger.info("Enter City : " + city);
		return this;
	}
	
	@Step("Enter state:{0}")
	public EditContactInfoPageObjects enterState_AddNewShippingAddress(String state) throws InterruptedException {
		Thread.sleep(2500);
		mo.clickByJavaScript(state_SA);
		Thread.sleep(1500);
		 WebElement selectState = getDriver().findElement(By.xpath("//span[contains(text(),'"+state+"')]"));
		 mo.clickByJavaScript(selectState);
		return this;
	}

	@Step("Enter zip code:{0}")
	public EditContactInfoPageObjects enterZipCode_AddNewShippingAddress(String zipcode) {
		logger = Logger.getLogger("enterZipCode");
		zipcode_SA.clear();
		zipcode_SA.sendKeys(zipcode);
		logger.info("Enter zip code: " + zipcode);
		return this;
	}

	@Step("Enter phone number:{0}")
	public EditContactInfoPageObjects enterPhoneNumber_AddNewShippingAddress(String phoneNumber) {
		logger = Logger.getLogger("enterPhoneNumber");
		phoneNumber_SA.clear();
		phoneNumber_SA.sendKeys(phoneNumber);
		logger.info("Enter phone number: " + phoneNumber);
		return this;
	}
	
	@Step("Enter phone number:{0}")
	public EditContactInfoPageObjects enterEmail_AddNewShippingAddress(String email) {
		logger = Logger.getLogger("enterPhoneNumber");
		email_SA.clear();	
		email_SA.sendKeys(email);
		logger.info("Enter phone number: " + email);
		return this;
	}


}
