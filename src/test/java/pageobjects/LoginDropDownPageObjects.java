package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class LoginDropDownPageObjects extends PageInitializer {

	public TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utility = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(LoginDropDownPageObjects.class);

	@FindBy(xpath = "//input[@id='popUserName']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='popPassword']")
	private WebElement password;

	@FindBy(xpath = "//form[@id='popLoginForm']//button[normalize-space(text())='Sign In']")
	private WebElement loginButton;

	@FindBy(xpath = "//form[@id='popLoginForm']//button[normalize-space(text())='Login']")
	private WebElement loginButtonForAccountFinancialInfo;

	@FindBy(xpath = "//form[@id='popLoginForm']/following-sibling::div/a[@href='/Register']")
	private WebElement registerHereLink;

	@FindBy(xpath = "//input[@id='rememberMeDrop']/following-sibling::span")
	private WebElement rememberMeText;

	@FindBy(xpath = "//input[@id='rememberMeDrop']/..")
	private WebElement rememberMeCheckbox;

	@FindBy(xpath = "//div[@class='loginDropdown']")
	private WebElement loginDropdownSectionLocator;

	@FindBy(className = "forgotPassword")
	private WebElement forgotYourPassword;

	@FindBy(xpath = "//form[@id='popLoginForm']//div[contains(@class,'pLoginErr')]")
	private WebElement errorMsgLocator;

	@Step("Enter username")
	public LoginDropDownPageObjects enterUserName() throws Exception {
		waiting.waitForVisibilityOfElement(userName, 10);
		userName.click();
		userName.clear();
		userName.sendKeys(data.getUserName());
		log.info("Entered user name :" + data.getUserName());
		return this;
	}

	@Step("Enter password")
	public LoginDropDownPageObjects enterPassword() throws Exception {
		waiting.waitForVisibilityOfElement(password, 10);
		password.click();
		password.clear();
		password.sendKeys(data.getPassword());
		Thread.sleep(1200);
		log.info("Entered Password:" + data.getPassword());
		return this;
	}

	@Step("Enter user name as : {0}")
	public LoginDropDownPageObjects enterUserName(String uName) {
		waiting.waitForVisibilityOfElement(userName, 20);
		userName.clear();
		userName.sendKeys(uName);
		log.info("Entered Username :" + uName);
		return this;
	}

	@Step("Enter password as : {0}")
	public LoginDropDownPageObjects enterPassword(String pwd) {
		waiting.waitForVisibilityOfElement(password, 20);
		password.clear();
		password.sendKeys(pwd);
		log.info("Entered Password :" + pwd);
		return this;

	}

	@Step("Click on login button")
	public LoginDropDownPageObjects clickOnLoginButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(loginButton, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", loginButton);
		log.info("clicked on login button");
		return this;
	}

	@Step("Click on login button")
	public LoginDropDownPageObjects clickOnLoginButtonForAccountFinancialInfoModule() throws InterruptedException {
		waiting.waitForVisibilityOfElement(loginButtonForAccountFinancialInfo, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", loginButtonForAccountFinancialInfo);
		log.info("clicked on login button. ");
		return this;
	}

	@Step("Verify login dropdown")
	public LoginDropDownPageObjects verifyLoginDropDown() {
		SoftAssert softAssert = new SoftAssert();
		waiting.waitForVisibilityOfElement(userName, 3);

		softAssert.assertTrue(userName.isDisplayed(), "user name locator is not displayed");
		log.info("user name locator is displayed");

		softAssert.assertTrue(loginButton.isDisplayed(), "login button is not displayed");
		log.info("login button is displayed");

		softAssert.assertTrue(password.isDisplayed(), "password textbox is not displayed");
		log.info("password textbox is displayed");

		softAssert.assertTrue(forgotYourPassword.isDisplayed(), "forgot your password link is not displayed");
		log.info("forgot your password link is displayed");

		softAssert.assertTrue(registerHereLink.isDisplayed(), "Sign up link is not displayed");
		log.info("Sign up link is displayed");

		softAssert.assertTrue(rememberMeText.isDisplayed(), "remember text is not displayed");
		log.info("remember text is displayed");

		softAssert.assertAll();

		return this;
	}

	@Step("Verify default tab focus to be username")
	public LoginDropDownPageObjects verifyDefaultTabFocus(String htmlAttribute, String userNameId) {
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute(htmlAttribute), userNameId);
		log.info("verifyDefaultTabFocus-completed");
		return this;
	}

	@Step("Click on remember me checkbox")
	public LoginDropDownPageObjects clickOnRememberMe() {
		waiting.waitForVisibilityOfElement(rememberMeCheckbox, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rememberMeCheckbox);
		log.info("Clicked on remember me checkbox");
		return this;
	}

	@Step("Verify auto fill of user name and password")
	public LoginDropDownPageObjects verifyAutofillOfUserNameAndPassword(String uName, String pwd) throws Exception {
		waiting.waitForVisibilityOfElement(userName, 20);
		Assert.assertEquals(userName.getAttribute("value"), uName);
		Assert.assertEquals(password.getAttribute("value"), pwd);
		log.info("verifyAutofillOfUserNameAndPassword-completed");
		return this;
	}

	@Step("Verify whether username and password textboxes are empty")
	public LoginDropDownPageObjects verifyEmptyUserNameAndPasswordTextbox() {
		waiting.waitForVisibilityOfElement(userName, 20);
		Assert.assertEquals(userName.getAttribute("value"), "");
		Assert.assertEquals(password.getAttribute("value"), "");
		log.info("verifyEmptyUserNameAndPasswordTextbox-completed");
		return this;
	}

	@Step("Click on remember me link")
	public LoginDropDownPageObjects clickOnRememberText() {
		rememberMeText.click();
		log.info("Clicked on remember me checkbox");
		return this;
	}

	@Step("Verify whether remember me checkbox is selected")
	public LoginDropDownPageObjects verifyRememberMeCheckBoxSelected() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(
				((JavascriptExecutor) getDriver()).executeScript("return $('#rememberMe').attr('checked');"), "checked",
				"remember me checkbox is not checked");
		log.info("verifyRememberMeCheckBoxSelected-completed");
		return this;
	}

	@Step("Verify whether remember me checkbox is not selected.")
	public LoginDropDownPageObjects verifyRememberMeCheckboxNotSelected() throws InterruptedException {
		Thread.sleep(1500);
		Assert.assertEquals(((JavascriptExecutor) getDriver()).executeScript("return $('#rememberMe').attr('checked')",
				rememberMeCheckbox), null, "remember me checkbox is selected");
		return this;
	}

	@Step("Verify tab focus from top to bottom")
	public LoginDropDownPageObjects verifyTabFocusTopToBottom(String userNameId, String passwordId,
			String goButtonClassName, String forgotYourPasswordHtmlText, String rememberMeAttributeName,
			String registerHereHTMLText) {
		waiting.waitForVisibilityOfElement(loginDropdownSectionLocator, 3);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("name"), userNameId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("name"), passwordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertTrue(getDriver().switchTo().activeElement().getAttribute("name").equals(rememberMeAttributeName),
				"Remember Me name is not " + rememberMeAttributeName + " .It is "
						+ getDriver().switchTo().activeElement().getText().trim());
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("class").trim(), goButtonClassName);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordHtmlText);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), registerHereHTMLText);

		log.info("tab focus from top to bottom");
		return this;
	}

	@Step("Verify tab focus from bottom to top")
	public LoginDropDownPageObjects verifyTabFocusBottomToTop(String userNameId, String passwordId,
			String goButtonClassName, String forgotYourPasswordHtmlText, String rememberMeAttributeName,
			String registerHereHTMLText) {
		waiting.waitForVisibilityOfElement(loginDropdownSectionLocator, 3);
		String tabBehind = Keys.chord(Keys.SHIFT, Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), registerHereHTMLText);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordHtmlText);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("class").trim(), goButtonClassName);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertTrue(getDriver().switchTo().activeElement().getAttribute("name").equals(rememberMeAttributeName),
				"Remember Me name is not " + rememberMeAttributeName + " .It is "
						+ getDriver().switchTo().activeElement().getText().trim());
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("name"), passwordId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("name"), userNameId);

		log.info("tab focus from bottom to top-completed");
		return this;
	}

	@Step("Tab from top to bottom")
	public LoginDropDownPageObjects hitTabTopToBottom(int numberOfTimesTabHasToBeHit) {
		for (int i = 0; i < numberOfTimesTabHasToBeHit; i++) {
			getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		}
		return this;
	}

	@Step("Enter Key pressed")
	public LoginDropDownPageObjects hitEnter() {
		password.sendKeys(Keys.ENTER);
		log.info("Enter Key Pressed");
		return this;
	}

	@Step("Click on register link")
	public RegistrationCAPageObjects clickOnRegisterHere() throws InterruptedException {
		waiting.waitForVisibilityOfElement(registerHereLink, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", registerHereLink);
		Thread.sleep(1500);
		log.info("Clicked on Register Here link");
		return commnercialRegistrationPage();
	}

	@Step("Verify tab focus from top to bottom")
	public LoginDropDownPageObjects verifyTabFocusTopToBottom(String userNameId, String passwordId,
			String rememberMeAttributeName, String forgotYourPasswordHtmlText, String loginButtonID) {
		waiting.waitForVisibilityOfElement(loginDropdownSectionLocator, 3);
		userName.click();
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), passwordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		/*
		 * Assert.assertTrue(getDriver().switchTo().activeElement().getAttribute
		 * ("name").equals(rememberMeAttributeName), "Remember Me name is not " +
		 * rememberMeAttributeName + " .It is " +
		 * getDriver().switchTo().activeElement().getText().trim());
		 */
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordHtmlText);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), loginButtonID);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);

		log.info("verifyTabFocusTopToBottom-completed");
		return this;
	}

	@Step("Verify tab focus from bottom to top")
	public LoginDropDownPageObjects verifyTabFocusBottomToTop(String userNameId, String passwordId,
			String rememberMeAttributeName, String forgotYourPasswordHtmlText, String loginButtonID) {
		waiting.waitForVisibilityOfElement(loginDropdownSectionLocator, 3);

		String tabBehind = Keys.chord(Keys.SHIFT, Keys.TAB);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), loginButtonID);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordHtmlText);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		/*
		 * Assert.assertTrue(getDriver().switchTo().activeElement().getAttribute
		 * ("name").equals(rememberMeAttributeName), "Remember Me name is not " +
		 * rememberMeAttributeName + " .It is " +
		 * getDriver().switchTo().activeElement().getText().trim());
		 */
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), passwordId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);

		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameId);
		log.info("tab focus from bottom to top-completed");
		return this;
	}

	@Step("Verify error message is {0}")
	public LoginDropDownPageObjects verifyErrorMessages(String expectedMsg) throws InterruptedException {
		waiting.waitForVisibilityOfElement(errorMsgLocator, 10);
		Assert.assertEquals(errorMsgLocator.getText().trim(), expectedMsg, "Invalid error message");
		log.info("verifyErrorMessages-completed");
		return this;
	}

	@Step("Click on forgot your password link")
	public ForgotPasswordPageObjects clickOnForgotYourPassword() throws InterruptedException {
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", forgotYourPassword);
		Thread.sleep(3500);
		log.info("clicked on forgot your password link");
		return forgotPasswordPage();
	}

	@Step("Click on sign up link")
	public RegistrationANPageObjects clickOnSignUpLink() {
		waiting.waitForVisibilityOfElement(registerHereLink, 15);
		registerHereLink.click();
		log.info("Clicked on Register Here link");
		return onAccountRegistrationPage();
	}

}