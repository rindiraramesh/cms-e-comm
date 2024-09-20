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
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class UserManagementPageObjects extends PageInitializer {

	Logger logger = Logger.getLogger(UserManagementPageObjects.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	@FindBy(xpath = "//span[normalize-space(text())='Add New User']/..")
	private WebElement addNewUserButtonLocator;

	@FindBy(xpath = "//input[@id='contactAddress1']")
	private WebElement address1TextBoxLocator;

	@FindBy(xpath = "//button[@data-id='roleAssign']")
	private WebElement roleAssignmentDropDownLocator;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	private WebElement confirmPasswordTextboxLocator;

	@FindBy(xpath = "//table[@id='ManagePurchaseAgent']//th")
	private List<WebElement> userManagementHeaderLocator;

	@FindBy(xpath = "//input[@id='contactPhone']")
	private WebElement phoneTextBoxLocator;

	@FindBy(xpath = "//input[@id='zip']")
	private WebElement zipCodeTextBoxLocator;

	@FindBy(xpath = "//input[@id='contactFax']")
	private WebElement faxNumberTextBoxLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement successMessageLocator;

	@FindBy(xpath = "//form[@id='newPurchaseAgentForm']//div[@class='form-group']/label")
	private List<WebElement> addNewUserFieldsLocator;

	@FindBy(xpath = "//button[normalize-space(text())='Submit']")
	private WebElement submitButtonLocator;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordTextboxLocator;

	@FindBy(xpath = "//input[@id='contactemailAddress']")
	private WebElement emailTextboxLocator;

	@FindBy(xpath = "//input[@id='contactFirstName']")
	private WebElement firstNameTextboxLocator;

	@FindBy(xpath = "//input[@id='contactLastName']")
	private WebElement lastNameTextboxLocator;

	@FindBy(xpath = "//input[@id='getentityAddress']/..")
	private WebElement useEntityAddressCheckboxLocator;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMessageLocator;

	@FindBy(xpath = "//input[@placeholder='Search User']")
	private WebElement searchTexbLocator;

	@FindBy(xpath = "//td[normalize-space(text())='No matching records found']")
	private WebElement errroMessageLocatorForInvalidSearch;

	@FindBy(xpath = "//span[contains(text(),'Disable')]")
	private List<WebElement> disabelButtonLocator;

	@FindBy(xpath = "//span[contains(text(),'Enable')]")
	private List<WebElement> enableButtonLocator;

	@FindBy(xpath = "//a[normalize-space(text())='Back to Users List']")
	private WebElement backToUserListButtonLocator;

	@FindBy(xpath = "//div[@id='editUserDetailsBlock']//a[normalize-space(text())='Back to Users List']")
	private WebElement backToUserListButtonLocatorAfterClickOnEditButton;

	@FindBy(xpath = "//th[normalize-space(text())='Action']/ancestor::thead/following-sibling::tbody//a[@data-original-title='Edit']")
	private List<WebElement> editButtonLocators;

	@FindBy(xpath = "//th[normalize-space(text())='Action']/ancestor::thead/following-sibling::tbody//span[normalize-space(text())='Disable']")
	private List<WebElement> disableButtonLocators;

	@FindBy(xpath = "//button[@data-id='userRole']")
	private WebElement userPrivilegesButtonLocator;

	@FindBy(xpath = "//input[@name='shipIdList']")
	private List<WebElement> shipToAddressCheckboxLocator;

	@FindBy(xpath = "//input[@name='userToAssign']/..")
	private List<WebElement> purchaseAgentListAssignedLocator;

	@FindBy(id = "updateBtn")
	private WebElement updateButtonLocator;

	@FindBy(xpath = "//td[@data-th='Agent Status']//strong")
	private List<WebElement> agentStatusLocator;

	@FindBy(xpath = "//h4[text()='User Privileges']/..")
	private WebElement userPrivilegesSectionLocator;

	@FindBy(xpath = "//h4[text()='User Information']/..")
	private WebElement userInformationLocator;

	@FindBy(xpath = "//input[@id='contactCity']")
	private WebElement cityTextBoxLocator;

	@FindBy(xpath = "//input[@id='contactAddress2']")
	private WebElement address2TextBoxLocator;

	@FindBy(xpath = "//button[@data-id='stateSelect']")
	private WebElement stateDropDownLocator;

	@FindBy(xpath = ".//*[@id='notificationDiv']/div")
	private WebElement successMessageLoctorAfterAssigningRoleLocator;

	@FindBy(xpath = "//h4[text()='Assign Purchasing Agent Ship To/Jobs List']")
	private WebElement purchasingAgentShipToLocator;

	@Step("Verify User Management page with below fields: {0}")
	public UserManagementPageObjects verifyUserManagementPage(String userManagementHeader) {
		logger = Logger.getLogger("verifyUserManagementPage");
		SoftAssert soft = new SoftAssert();
		waiting.waitForVisibilityOfElements(userManagementHeaderLocator, 10);
		String[] expectedUserManagementHeader = userManagementHeader.split(",");
		for (int i = 0; i < userManagementHeaderLocator.size(); i++) {
			soft.assertEquals(userManagementHeaderLocator.get(i).getText().toLowerCase().trim(),
					expectedUserManagementHeader[i].toLowerCase().trim(),
					"User Management page fields are not" + expectedUserManagementHeader[i].toLowerCase().trim()
							+ ". It is :" + userManagementHeaderLocator.get(i).getText().toLowerCase().trim());
			logger.info("User Management page fields has been verified" + " with [Actual] and [Expected] value as "
					+ "[" + userManagementHeaderLocator.get(i).getText().trim() + "] and " + "["
					+ expectedUserManagementHeader[i].trim() + "]");
		}
		soft.assertTrue(addNewUserButtonLocator.isDisplayed(), "Add New User Button Is not displayed");
		logger.info("Add New User Button is displayed.");
		soft.assertAll();
		return this;
	}

	@Step("Verify function Of Back To User List Button")
	public UserManagementPageObjects verifyBackToUserListButtonFunctionality(String userManagementHeader) {
		logger = Logger.getLogger("verifyBackToUserListButtonFunctionality");
		verifyUserManagementPage(userManagementHeader);
		return this;

	}

	@Step("Click On Add New User Button")
	public UserManagementPageObjects clickOnAddNewUserButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnAddNewUserButton");
		waiting.waitForVisibilityOfElement(addNewUserButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addNewUserButtonLocator);
		logger.info("Clicked On Add New User Button.");
		Thread.sleep(1000);
		return this;
	}

	@Step("Verify Add New User Fields with : {0}")
	public UserManagementPageObjects verifyAddNewUserFields(String addNewUserFields) {
		logger = Logger.getLogger("verifyAddNewUserFields");
		SoftAssert soft = new SoftAssert();
		waiting.waitForVisibilityOfElements(addNewUserFieldsLocator, 10);
		String[] expectedAddNewUserFields = addNewUserFields.split(",");
		for (int i = 0; i < addNewUserFieldsLocator.size(); i++) {
			soft.assertEquals(addNewUserFieldsLocator.get(i).getText().trim(), expectedAddNewUserFields[i].trim(),
					"Mandatory And Non-Mandatory Fields of Add New User are not :" + expectedAddNewUserFields[i].trim()
							+ ". It is :" + addNewUserFieldsLocator.get(i).getText().trim());
			logger.info("Mandatory And Non-Mandatory Fields of Add New User has been verified"
					+ " with [Actual] and [Expected] value as " + "[" + addNewUserFieldsLocator.get(i).getText().trim()
					+ "] and " + "[" + expectedAddNewUserFields[i].trim() + "]");
		}
		soft.assertTrue(submitButtonLocator.isDisplayed(), "Submit Button is not displayed");
		logger.info("Submit Button is displayed");
		soft.assertTrue(backToUserListButtonLocator.isDisplayed(), "Back To user List Button is not displayed");
		logger.info("Back To user List Button is displayed");
		soft.assertAll();
		return this;

	}

	@Step("Enter Email to create New user :{0}")
	public UserManagementPageObjects enterEmailToCreateNewUser(String emailId) {
		logger = Logger.getLogger("enterEmailToCreateNewUser");
		waiting.waitForVisibilityOfElement(emailTextboxLocator, 10);
		emailTextboxLocator.clear();
		emailTextboxLocator.sendKeys(emailId);
		logger.info("Email to create New user has been entered as : " + emailId);
		return this;

	}

	@Step("Enter First Name to create New user :{0}")
	public UserManagementPageObjects enterFirstName(String firstname) {
		logger = Logger.getLogger("enterFirstName");
		waiting.waitForVisibilityOfElement(firstNameTextboxLocator, 10);
		firstNameTextboxLocator.clear();
		firstNameTextboxLocator.sendKeys(firstname);
		logger.info("First Name to create New user has been entered as : " + firstname);
		return this;

	}

	@Step("Enter Last Name to create New user :{0}")
	public UserManagementPageObjects enterLastName(String lastName) {
		logger = Logger.getLogger("enterLastName");
		waiting.waitForVisibilityOfElement(lastNameTextboxLocator, 10);
		lastNameTextboxLocator.clear();
		lastNameTextboxLocator.sendKeys(lastName);
		logger.info("Last Name to create New user has been entered as : " + lastName);
		return this;

	}

	@Step("Enter Password to create New user :{0}")
	public UserManagementPageObjects enterPassword(String password) {
		logger = Logger.getLogger("enterPassword");
		waiting.waitForVisibilityOfElement(passwordTextboxLocator, 10);
		passwordTextboxLocator.clear();
		passwordTextboxLocator.sendKeys(password);
		logger.info("Password to create New user has been entered as : " + password);
		return this;

	}

	@Step("Enter Password to validate:{0}")
	public UserManagementPageObjects enterConfirmPassword(String password) {
		logger = Logger.getLogger("enterConfirmPassword");
		waiting.waitForVisibilityOfElement(confirmPasswordTextboxLocator, 10);
		confirmPasswordTextboxLocator.clear();
		confirmPasswordTextboxLocator.sendKeys(password);
		logger.info("Enter Password to validate : " + password);
		return this;

	}

	@Step("Clicked On Role Assignment Drop Down")
	public UserManagementPageObjects clickOnRoleAssignmentDropDown() throws InterruptedException {
		logger = Logger.getLogger("clickOnRoleAssignmentDropDown");
		waiting.waitForVisibilityOfElement(roleAssignmentDropDownLocator, 10);
		roleAssignmentDropDownLocator.click();
		Thread.sleep(1200);
		logger.info("Clicked On Role Assignment Drop Down.");
		return this;

	}

	@Step("Select Role of the user {0}")
	public UserManagementPageObjects selectRoleOfTheUser(String userRole) throws InterruptedException {
		logger = Logger.getLogger("selectRoleOfTheUser");
		WebElement role = getDriver()
				.findElement(By.xpath("//div[@class='dropdown-menu open']//span[text()='" + userRole.trim() + "']/.."));
		role.click();
		Thread.sleep(1200);

		logger.info("select Role of the user :  " + userRole);

		return this;

	}

	@Step("Enter the Address1 to create New User:{0}")
	public UserManagementPageObjects enterAddress1(String address1) {
		logger = Logger.getLogger("enterAddress1");
		waiting.waitForVisibilityOfElement(address1TextBoxLocator, 10);
		address1TextBoxLocator.clear();
		address1TextBoxLocator.sendKeys(address1);
		logger.info("Address1 to create New user has been entered as : " + address1);
		return this;

	}

	@Step("Enter the Address2 to create New User:{0}")
	public UserManagementPageObjects enterAddress2(String address2) {
		logger = Logger.getLogger("enterAddress2");
		waiting.waitForVisibilityOfElement(address2TextBoxLocator, 10);
		address2TextBoxLocator.clear();
		address2TextBoxLocator.sendKeys(address2);
		logger.info("Address2 to create New user has been entered as : " + address2);
		return this;

	}

	@Step("Enter the city to create New User:{0}")
	public UserManagementPageObjects enterCity(String city) {
		logger = Logger.getLogger("enterCity");
		waiting.waitForVisibilityOfElement(cityTextBoxLocator, 10);
		cityTextBoxLocator.clear();
		cityTextBoxLocator.sendKeys(city);
		logger.info("City to create New user has been entered as : " + city);
		return this;

	}

	@Step("Click on state drop down")
	public UserManagementPageObjects clickOnStateDropDown() throws InterruptedException {

		logger = Logger.getLogger("clickOnStateDropDown");

		waiting.waitForVisibilityOfElement(stateDropDownLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", stateDropDownLocator);
		Thread.sleep(1200);

		logger.info("Click on state drop down.");
		return this;

	}

	@Step("Select state {0}")
	public UserManagementPageObjects selectState(String state) throws InterruptedException {
		logger = Logger.getLogger("selectState");
		WebElement state1 = getDriver().findElement(By.xpath("//span[text()='" + state + "']/.."));
		state1.click();
		Thread.sleep(1200);
		logger.info("Select state : " + state);
		return this;

	}

	@Step("Enter the Zip Code to create New User:{0}")
	public UserManagementPageObjects enterZipCode(String zipCode) {
		logger = Logger.getLogger("enterZipCode");
		waiting.waitForVisibilityOfElement(zipCodeTextBoxLocator, 10);
		zipCodeTextBoxLocator.clear();
		zipCodeTextBoxLocator.sendKeys(zipCode);
		logger.info("Zip Code to create New user has been entered as : " + zipCode);
		return this;

	}

	@Step("Enter the Phone Number to create New User:{0}")
	public UserManagementPageObjects enterPhoneNumber(String phoneNumber) {
		logger = Logger.getLogger("enterPhoneNumber");
		waiting.waitForVisibilityOfElement(phoneTextBoxLocator, 10);
		phoneTextBoxLocator.clear();
		phoneTextBoxLocator.sendKeys(phoneNumber);

		logger.info("Phone Number to create New user has been entered as : " + phoneNumber);
		return this;

	}

	@Step("Enter the Fax Number to create New User:{0}")
	public UserManagementPageObjects enterFaxNumber(String faxNumber) {

		logger = Logger.getLogger("enterFaxNumber");

		waiting.waitForVisibilityOfElement(faxNumberTextBoxLocator, 10);
		faxNumberTextBoxLocator.clear();
		faxNumberTextBoxLocator.sendKeys(faxNumber);

		logger.info("Fax Number to create New user has been entered as : " + faxNumber);
		return this;

	}

	@Step("Get the agent name")
	public String getAgentName(String userName) {

		WebElement ele = getDriver()
				.findElement(By.xpath("//td[text()='" + userName.trim() + "']/preceding-sibling::td"));

		waiting.waitForVisibilityOfElement(ele, 10);

		return ele.getText();

	}

	@Step("Click on submit button")
	public UserManagementPageObjects clickOnSubmitButton() throws InterruptedException {

		logger = Logger.getLogger("clickOnSubmitButton");
		waiting.waitForVisibilityOfElement(submitButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", submitButtonLocator);
		Thread.sleep(1400);

		logger.info("Clicked on submit button");
		return this;

	}

	@Step("Verify Success Message For Creating New User : {0}")
	public UserManagementPageObjects verifyUserCreatedSuccessMessage(String emailId) throws Throwable {
		Thread.sleep(5000);
		logger = Logger.getLogger("verifyUserCreatedSuccessMessage");
		waiting.waitForVisibilityOfElement(successMessageLocator, 10);

		Assert.assertEquals(successMessageLocator.getText().trim(), emailId + " User is created successfully",
				"Add New User Sucess Message is  not " + emailId + " User is created successfully" + ". It is :"
						+ successMessageLocator.getText().trim());

		logger.info("Add New User Sucess Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ successMessageLocator.getText().trim() + "] and " + "[" + emailId + "]");
		return this;

	}

	@Step("Click On Back To Users List Button")
	public UserManagementPageObjects clickOnBackToUserListButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnBackToUserListButton");
		waiting.waitForVisibilityOfElement(backToUserListButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", backToUserListButtonLocator);
		Thread.sleep(1200);

		logger.info("Clicked On Back To Users List Button");
		return this;

	}

	@Step("Verify Display of Back To User List Button")
	public UserManagementPageObjects verifyBackToUserListButton() {
		logger = Logger.getLogger("verifyBackToUserListButton");

		waiting.waitForVisibilityOfElement(backToUserListButtonLocatorAfterClickOnEditButton, 10);
		Assert.assertTrue(backToUserListButtonLocatorAfterClickOnEditButton.isDisplayed(),
				"Back To User List Button is not displayed");

		logger.info("Back To User List Button is displayed");

		return this;
	}

	@Step("Click on Back To User List Button ")
	public UserManagementPageObjects clickOnBackToUserListButtonAfterClickingOnEditUserButton()
			throws InterruptedException {
		logger = Logger.getLogger("clickOnBackToUserListButtonAfterClickingOnEditUserButton");
		waiting.waitForVisibilityOfElement(backToUserListButtonLocatorAfterClickOnEditButton, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				backToUserListButtonLocatorAfterClickOnEditButton);
		Thread.sleep(1500);
		logger.info("Clicked on Back To User List Button.");
		return this;

	}

	@Step("Verify Error Message If Mandatory Fields Leave Blank:{0}")
	public UserManagementPageObjects verifyErrorMessageIfMandatoryFieldsLeaveBlankInUserManagementPage(
			String errorMessage) {
		logger = Logger.getLogger("verifyErrorMessageIfMandatoryFieldsLeaveBlankInUserManagementPage");
		waiting.waitForVisibilityOfElement(errorMessageLocator, 10);
		Assert.assertEquals(errorMessageLocator.getText().replace("\n", "").replace("×", "").trim(),
				errorMessage.trim(), "Error Message if mandatory fields leave blank is not:" + errorMessage.trim()
						+ ".It is:" + errorMessageLocator.getText().replace("\n", "").replace("×", "").trim());
		logger.info("Error Message has been verified when mandatory fields leave blank "
				+ " with [Actual] and [Expected] value as " + "["
				+ errorMessageLocator.getText().replace("\n", "").replace("×", "").trim() + "] and " + "["
				+ errorMessage.trim() + "]");
		return this;
	}

	@Step("Click On Use Entity Address Checkbox")
	public UserManagementPageObjects clickOnUseEntityAddress() throws InterruptedException {
		logger = Logger.getLogger("clickOnUseEntityAddress");
		waiting.waitForVisibilityOfElement(useEntityAddressCheckboxLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", useEntityAddressCheckboxLocator);
		Thread.sleep(1400);

		logger.info("Clicked On Use Entity Address Checkbox");
		return this;

	}

	@Step("Enter User Name In Search Text Box:{0}")
	public UserManagementPageObjects enterUserNameToSearch(String userNameForWhichCartIsShared)
			throws InterruptedException {
		logger = Logger.getLogger("enterUserNameToSearch");

		waiting.waitForVisibilityOfElement(searchTexbLocator, 10);
		searchTexbLocator.clear();
		searchTexbLocator.sendKeys(userNameForWhichCartIsShared);
		Thread.sleep(1200);

		logger.info("Enter User Name In Search Text Box: " + userNameForWhichCartIsShared);
		return this;
	}

	@Step("Verify valid Search Result:{0}")
	public UserManagementPageObjects verifyValidsearchResult(String userNameForWhichCartIsShared) {
		logger = Logger.getLogger("verifyValidsearchResult");
		WebElement userName = getDriver()
				.findElement(By.xpath("//td[text()='" + userNameForWhichCartIsShared.trim() + "']"));

		Assert.assertTrue(userName.isDisplayed(), "Valid User Name Search result is not displayed");

		logger.info("Valid User Name Search result has been displayed : " + userNameForWhichCartIsShared);
		return this;

	}

	@Step("Verify Error Message For Invalid Search Result")
	public UserManagementPageObjects verifyErrorMessageForInvalidSearchOfUser() {
		logger = Logger.getLogger("verifyErrorMessageForInvalidSearchOfUser");
		waiting.waitForVisibilityOfElement(errroMessageLocatorForInvalidSearch, 10);

		Assert.assertTrue(errroMessageLocatorForInvalidSearch.isDisplayed(),
				"Error Message is not displayed for invalid user name");

		logger.info("Error Message has been  displayed for invalid user name.");
		return this;

	}

	@Step("Click On Specific Disable Button:{0}")
	public UserManagementPageObjects clickOnSpecificDisableButton(int specificButton) throws InterruptedException {
		logger = Logger.getLogger("clickOnSpecificDisableButton");
		waiting.waitForVisibilityOfElements(disabelButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				disabelButtonLocator.get(specificButton - 1));
		Thread.sleep(1400);
		logger.info("Clicked On Specific Disable Button: " + specificButton);
		return this;

	}

	@Step("Click On Specific Enabled Button:{0}")
	public UserManagementPageObjects clickOnSpecificEnabledButton(int specificButton) throws InterruptedException {
		logger = Logger.getLogger("clickOnSpecificEnabledButton");

		waiting.waitForVisibilityOfElements(enableButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				enableButtonLocator.get(specificButton - 1));
		Thread.sleep(1400);

		logger.info("Clicked On Specific Enabled Button: " + specificButton);
		return this;

	}

	@Step("Verify Specific Agent Status After clicking On Cancel Button of Pop Up display once Clicked on Disable Button")
	public UserManagementPageObjects verifyAgentStatusAfterClickOnCancelButtonOfDisableButtonAlertPopUp(
			int specificAgent) {
		logger = Logger.getLogger("verifyAgentStatusAfterClickOnCancelButtonOfDisableButtonAlertPopUp");
		waiting.waitForVisibilityOfElements(agentStatusLocator, 10);

		Assert.assertEquals(agentStatusLocator.get(specificAgent - 1).getText(), "Active",

				"Agent Status is not:" + "Active" + ".It is :" + agentStatusLocator.get(specificAgent - 1).getText());

		logger.info(
				"Verified Specific Agent Status After clicking On Cancel Button of Pop Up display once Clicked on Disable Button");
		return this;

	}

	@Step("Verify Specific Agent Status After clicking On Ok Button of Pop Up display once Clicked on Disable Button")
	public UserManagementPageObjects verifyAgentStatusAfterClickOnOkButtonOfDisableButtonAlertPopUp(int specificAgent) {
		logger = Logger.getLogger("verifyAgentStatusAfterClickOnOkButtonOfDisableButtonAlertPopUp");
		waiting.waitForVisibilityOfElements(agentStatusLocator, 10);
		Assert.assertEquals(agentStatusLocator.get(specificAgent - 1).getText(), "In active", "Agent Status is not:"
				+ "In active" + ".It is :" + agentStatusLocator.get(specificAgent - 1).getText());
		logger.info(
				"Verified Specific Agent Status After clicking On Ok Button of Pop Up display once Clicked on Disable Button");
		return this;

	}

	@Step("Verify Specific Agent Status After clicking On Cancel Button of Pop Up display once Clicked on Enabled Button")
	public UserManagementPageObjects verifyAgentStatusAfterClickOnCancelButtonOfEnabledButtonAlertPopUp(
			int specificAgent) {
		logger = Logger.getLogger("verifyAgentStatusAfterClickOnCancelButtonOfEnabledButtonAlertPopUp");
		waiting.waitForVisibilityOfElements(agentStatusLocator, 10);
		Assert.assertEquals(agentStatusLocator.get(specificAgent - 1).getText(), "In active", "Agent Status is not:"
				+ "In active" + ".It is :" + agentStatusLocator.get(specificAgent - 1).getText());
		logger.info(
				"Verified Specific Agent Status After clicking On Cancel Button of Pop Up display once Clicked on Enabled Button");
		return this;

	}

	@Step("Verify Specific Agent Status After clicking On Ok Button of Pop Up display once Clicked on Enabled Button")
	public UserManagementPageObjects verifyAgentStatusAfterClickOnOKButtonOfEnabledButtonAlertPopUp(int specificAgent) {

		logger = Logger.getLogger("verifyAgentStatusAfterClickOnOKButtonOfEnabledButtonAlertPopUp");

		waiting.waitForVisibilityOfElements(agentStatusLocator, 10);
		Assert.assertEquals(agentStatusLocator.get(specificAgent - 1).getText(), "Active",
				"Agent Status is not:" + "Active" + ".It is :" + agentStatusLocator.get(specificAgent - 1).getText());

		logger.info(
				"Verified Specific Agent Status After clicking On Ok Button of Pop Up display once Clicked on Enabled Button.");

		return this;

	}

	@Step("Click On Edit Button of respective Agent Name :{0}")
	public UserManagementPageObjects clickOnSpecificEditButtonOfAgnet(String userNameForWhichCartIsShared)
			throws InterruptedException {
		logger = Logger.getLogger("clickOnSpecificEditButtonOfAgnet");
		Thread.sleep(1000);
		WebElement editButton = getDriver().findElement(By.xpath("//td[contains(text(),'"
				+ userNameForWhichCartIsShared.trim() + "')]/following-sibling::td//a[@data-original-title='Edit']"));
		editButton.click();

		logger.info("Clicked On Edit Button of respective Agent Name : " + userNameForWhichCartIsShared);
		return this;
	}

	@Step("Verify Display of User Information Section")
	public UserManagementPageObjects verifyUserInformationSectionWhenClickedOnEditButton() {
		logger = Logger.getLogger("verifyUserInformationSectionWhenClickedOnEditButton");
		waiting.waitForVisibilityOfElement(userInformationLocator, 10);

		Assert.assertTrue(userInformationLocator.isDisplayed(), "User Information section is not displayed");
		logger.info("User Information section is displayed");
		return this;

	}

	@Step("Verify Display of User Privileges Section")
	public UserManagementPageObjects verifyUserPrivilegesSectionWhenClickedOnEditButton() {
		logger = Logger.getLogger("verifyUserPrivilegesSectionWhenClickedOnEditButton");
		waiting.waitForVisibilityOfElement(userPrivilegesSectionLocator, 10);

		Assert.assertTrue(userPrivilegesSectionLocator.isDisplayed(), "User Privilege section is not displayed");
		logger.info("User Privilege section is displayed.");
		return this;

	}

	@Step("Verify Display of Assign Purchasing Agent Ship To/Jobs List Section")
	public UserManagementPageObjects verifyPurchasingAgentShipTo() {
		logger = Logger.getLogger("verifyPurchasingAgentShipTo");
		waiting.waitForVisibilityOfElement(purchasingAgentShipToLocator, 10);

		Assert.assertTrue(purchasingAgentShipToLocator.isDisplayed(),
				"Assign Purchasing Agent Ship To/Jobs List Section is not displayed.");

		logger.info("Assign Purchasing Agent Ship To/Jobs List Section is displayed.");
		return this;

	}

	@Step("Click on User privileges button")
	public UserManagementPageObjects clickOnUserPrivilegesButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(userPrivilegesButtonLocator, 10);
		userPrivilegesButtonLocator.click();
		logger.info("Clicked on User privileges button");
		Thread.sleep(1200);
		return this;

	}

	@Step("Select Role of the user:{0}")
	public UserManagementPageObjects selectRoleOfTheUserWhenClickedOnEditButton(String role) throws Exception {
		logger = Logger.getLogger("selectRoleOfTheUserWhenClickedOnEditButton");
		waiting.waitForVisibilityOfElement(userPrivilegesButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", userPrivilegesButtonLocator);

		Thread.sleep(1200);
		switch (role.trim()) {

		case "Super User":

			getDriver().findElement(By.xpath("//form[@id='manageForm']//a/span[text()='Super User']")).click();
			Thread.sleep(1500);

			break;

		case "Authorized Purchase Agent":

			getDriver().findElement(By.xpath("//form[@id='manageForm']//a/span[text()='Authorized Purchasing Agent']"))
					.click();
			Thread.sleep(1500);
			break;

		case "General User":

			getDriver().findElement(By.xpath("//form[@id='manageForm']//a/span[text()='General User']")).click();
			Thread.sleep(1500);
			break;

		default:
			throw new Exception("invalid input");
		}
		logger.info("Selected Role of the user: " + role);
		return this;

	}

	@Step("Select Specific Ship To Address Checkbox:{0},{1}")
	public UserManagementPageObjects selectSpecificShipToAddressCheckbox(int specificCheckbox, int flag)
			throws InterruptedException {

		logger = Logger.getLogger("selectSpecificShipToAddressCheckbox");
		if (flag == 0)
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					shipToAddressCheckboxLocator.get(specificCheckbox - 1));

		logger.info("Selected Specific Ship To Address Checkbox");
		return this;
	}

	@Step("Click on Specific Radio Button Of Purchase Agent List :{0}")
	public UserManagementPageObjects clickOnSpecificPurchaseAgentListRadioButton(int specificRadioButton) {

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				purchaseAgentListAssignedLocator.get(specificRadioButton - 1));
		logger.info("Clicked on Specific Radio Button Of Purchase Agent List");
		return this;
	}

	@Step("Click on Specific Radio Button Of Purchase Agent List :{0}")
	public UserManagementPageObjects clickOnSpecificPurchaseAgentListRadioButton(String agentName) {

		/*
		 * ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		 * purchaseAgentListAssignedLocator.get(specificRadioButton - 1));
		 */

		WebElement ele = getDriver().findElement(By.xpath("//span[text()='" + agentName.trim() + "']/.."));
		waiting.waitForVisibilityOfElement(ele, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ele);

		logger.info("Clicked on Specific Radio Button Of Purchase Agent List" + agentName);
		return this;
	}

	@Step("Click On Update Button")
	public UserManagementPageObjects clickOnUpdateButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnUpdateButton");
		waiting.waitForVisibilityOfElement(updateButtonLocator, 10);
		updateButtonLocator.click();
		Thread.sleep(2200);
		logger.info("Clicked On Update Button.");
		return this;

	}

	@Step("Verify role:{1} assigned to user: {0}")
	public UserManagementPageObjects verifyRoleAssignedToUser(String username, String role) {

		Assert.assertTrue(utilityMethods.isElementDisplayed(getDriver().findElement(By.xpath(
				"//td[text()='" + username.trim() + "']/following-sibling::td/p[text()='" + role.trim() + "']"))));

		logger.info("Verify role " + role + " assigned to user " + username);
		return this;

	}

	@Step("Verify Role Assigned Message:{0}")
	public UserManagementPageObjects verifyRoleAssignedMessage(String role) {
		logger = Logger.getLogger("verifyRoleAssignedMessage");
		Assert.assertEquals(successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", ""),
				"User assigned as Ecomm Customer " + role + ".Shipping address assigned.",
				"Role Assigned Sucess Message is  not " + "User assigned as Ecomm Customer " + role
						+ ".Shipping address assigned." + ". It is :"
						+ successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", ""));
		logger.info("Verified Role Assigned Message: " + role);

		return this;

	}

	@Step("Verify Role Assigned Message for General user Role:{0}")
	public UserManagementPageObjects verifyRoleAssignedMessageForGeneralUser(String role) {

		Assert.assertEquals(successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", ""),
				"User assigned as Ecomm Customer " + role + ".Purchase Agent Assigned" + ".Shipping address assigned.",

				"Role Assigned Sucess Message for General User Role is  not " + "User assigned as Ecomm Customer "
						+ role + ".Purchase Agent Assigned" + ".Shipping address assigned." + ". It is :"
						+ successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", ""));
		logger.info("Verified Role Assigned Message for General user Role");
		return this;

	}

	@Step("Verify Error Message Without Updating The Role:{0}")
	public UserManagementPageObjects verifyErrorMessageWithoutUpdatingRole(String errorMessage) {

		logger = Logger.getLogger("verifyErrorMessageWithoutUpdatingRole");

		Assert.assertEquals(successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", ""),
				errorMessage.trim(), "Error Message Withou Updating the role is not :" + errorMessage.trim() + ".It Is"
						+ successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", ""));

		logger.info("Error Message Without Updating The Role has been verified"
				+ " with [Actual] and [Expected] value as " + "["
				+ successMessageLoctorAfterAssigningRoleLocator.getText().replace("\n", "").replace("×", "") + "] and "
				+ "[" + errorMessage.trim() + "]");
		return this;

	}

	@Step("Verfiy User Management Page Bread Crumb:{0}")
	public UserManagementPageObjects verifyUserManagementPageBreadCrumb(String userManagementPageTitle) {
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.trim()
						.equalsIgnoreCase(userManagementPageTitle.trim()),
				"Breadcrumb is not " + userManagementPageTitle + ". It is :"
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.trim());
		logger.info("Verfiy User Management Page Bread Crumb");
		return this;

	}

	@Step("Verfiy User Management Page Title:{0}")
	public UserManagementPageObjects verifyUserManagementPageTitle(String userManagementPageTitle, String productName) {
		Assert.assertEquals(getDriver().getTitle().trim(), userManagementPageTitle + " | " + productName);
		logger.info("Verfied User Management Page Title");
		return this;
	}

}
