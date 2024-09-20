package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.WaitingMethods;

public class LoginPageObjects extends PageInitializer {
	public TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(LoginPageObjects.class);

	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	private WebElement errorMsgLocator;

	@FindBy(id = "mainUserName")
	private WebElement userNameLocator;

	@FindBy(xpath = "//div[@class='cimm_loginSubmit']//input[@value='Login']")
	private WebElement loginButtonLocatorForAddNewCreditCardModule;

	@FindBy(xpath = "//form[@id='mainLoginForm']/descendant::label[contains(text(),'Username or Email')]")
	private WebElement emailLabelLocator;

	@FindBy(id = "mainPassword")
	private WebElement passwordLocator;


	@FindBy(xpath = "//form[@id='mainLoginForm']/descendant::label[contains(text(),'Password')]")
	private WebElement passwordLabelLocator;

	@FindBy(xpath = "//form[@id='mainLoginForm']//input[@id='rememberMe']/..")
	private WebElement rememberMeLocator;

	@FindBy(xpath = "//form[@id='mainLoginForm']//button[text()='Sign In']")
	private WebElement loginButtonLocator;

	@FindBy(xpath = "//h1[text()='Login']")
	private WebElement loginPageName;

	@FindBy(xpath = "//form[@id='mainLoginForm']/descendant::p")
	private WebElement welcomelocator;

	@FindBy(xpath = "//form[@id='mainLoginForm']//a[contains(text(),'Forgot your password?')]")
	private WebElement forgotYourPassword;

	@FindBy(xpath = "//div[@class='cimm_siteWidth']/h3")
	private WebElement contactUsLocator;

	@FindBy(xpath = "//a[@data-target='loginDropDown']")
	private WebElement loginLinkLocator;

	@FindBy(xpath = "//input[@id='popUserName']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='popPassword']")
	private WebElement password;

	@FindBy(xpath = "//form[@id='popLoginForm']//button[normalize-space(text())='Sign In']")
	private WebElement loginButton;

	@Step("Verify error message is {0}")
	public LoginPageObjects assertForErrorMessages(String expectedMsg) {
		waiting.waitForVisibilityOfElement(errorMsgLocator, 6);
		Assert.assertEquals(errorMsgLocator.getText().trim(), expectedMsg,
				errorMsgLocator.getText().trim() + " is displayed");
		return this;
	}

	@Step("Enter user name as : {0}")
	public LoginPageObjects enterUserName(String userName) {
		userNameLocator.sendKeys(userName);
		log.info("Entered Username: " + userName);
		return this;
	}

	@Step("Enter password as : {0}")
	public LoginPageObjects enterPassword(String password) {
		passwordLocator.sendKeys(password);
		log.info("Entered Password :" + password);
		return this;
	}
	
	


	@Step("Click on remember me checkbox")
	public LoginPageObjects clickOnRememberMe() {
		rememberMeLocator.click();
		log.info("Clicked on remember me checkbox");
		return this;
	}

	@Step("Click on login link")
	public LoginPageObjects clickOnLoginButton() {
		loginButtonLocator.click();
		log.info("Clicked on login button");
		return this;
	}

	@Step("Click on login link for Add New Credit Card Module.")
	public LoginPageObjects clickOnLoginButtonForAddNewCreditCard() throws InterruptedException {
		waiting.waitForVisibilityOfElement(loginButtonLocatorForAddNewCreditCardModule, 10);
		loginButtonLocatorForAddNewCreditCardModule.click();
		Thread.sleep(1200);
		return this;
	}

	@Step("Enter user name : {0}")
	public LoginPageObjects enterUsernameRegression(String userName) {
		waiting.waitForVisibilityOfElement(this.userNameLocator, 6);
		this.userNameLocator.click();
		this.userNameLocator.clear();
		this.userNameLocator.sendKeys(userName);
		log.info("Entered username :" + userName);
		return this;

	}

	@Step("Enter password : {0}")
	public LoginPageObjects enterPasswordRegression(String password) {
		waiting.waitForVisibilityOfElement(this.passwordLocator, 3);
		this.passwordLocator.click();
		this.passwordLocator.clear();
		this.passwordLocator.sendKeys(password);
		log.info("Entered Password :" + password);
		return this;

	}

	@Step("Enter login pop up")
	public LoginPageObjects verifyLoginPage() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(loginPageName.isDisplayed(), "Login page name is not displayed");
		Assert.assertTrue(userNameLocator.isDisplayed(), "user Name text box is not displayed");
		Assert.assertTrue(passwordLocator.isDisplayed(), "password textbox is not displayed");
		Assert.assertTrue(rememberMeLocator.isDisplayed(), "Remember me checkbox is not displayed");
		Assert.assertTrue(forgotYourPassword.isDisplayed(), "forgot your password link is not displayed");
		Assert.assertTrue(loginButtonLocator.isDisplayed(), "login button is not displayed");
		log.info("verifyLoginPage-completed");
		return this;
	}

	@Step("Verify default tab focus")
	public LoginPageObjects verifyDefaultTabFocus(String htmlAttribute, String userNameId) throws Exception {
		waiting.waitForVisibilityOfElement(userNameLocator, 10);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute(htmlAttribute).trim(), userNameId);
		log.info("verifyDefaultTabFocus-completed");
		return this;
	}

	@Step("Verify tab focus from top to bottom")
	public LoginPageObjects verifyTabFocusTopToBottom(String userNameAttribute, String passwordAttribute,
			String forgotYourPasswordAttribute, String rememberMeAttribute, String loginButtonAttribute)
			throws Exception {
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameAttribute);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), passwordAttribute);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		/*
		 * Assert.assertEquals(getDriver().switchTo().activeElement().
		 * getAttribute("name"), rememberMeAttribute, "Remember Me name is not " +
		 * rememberMeAttribute + " .It is " +
		 * getDriver().switchTo().activeElement().getText().trim());
		 * getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		 */
		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordAttribute);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("value"), loginButtonAttribute);
		log.info("verifyTabFocusTopToBottom-completed");
		return this;
	}

	@Step("Verify tab focus from bottom to top")
	public LoginPageObjects verifyTabFocusBottomToTop(String userNameAttribute, String passwordAttribute,
			String forgotYourPasswordAttribute, String rememberMeAttribute, String loginButtonAttribute) {
		waiting.waitForVisibilityOfElement(loginPageName, 3);
		String tabBehind = Keys.chord(Keys.SHIFT, Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("value"), loginButtonAttribute);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordAttribute);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		/*
		 * Assert.assertEquals(getDriver().switchTo().activeElement().
		 * getAttribute("name"), rememberMeAttribute, "Remember Me name is not " +
		 * rememberMeAttribute + " .It is " +
		 * getDriver().switchTo().activeElement().getText().trim());
		 * getDriver().switchTo().activeElement().sendKeys(tabBehind);
		 */
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), passwordAttribute);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameAttribute);
		log.info("verifyTabFocusBottomToTop-completed");
		return this;
	}

	@Step("Click on Tab")
	public LoginPageObjects hitTab(int numberOfTimesToTab) {
		for (int j = 0; j < numberOfTimesToTab; j++) {
			getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		}
		log.info("Clicked on Tab Key");
		return this;
	}

	@Step("Hit enter")
	public LoginPageObjects hitEnter() {
		passwordLocator.sendKeys(Keys.ENTER);
		log.info("Click on Enter key");
		return this;
	}

	@Step("Verify auto fill of username and password")
	public LoginPageObjects verifyAutofillOfUserNameAndPassword() {
		waiting.waitForVisibilityOfElement(userNameLocator, 10);
		Assert.assertEquals(userNameLocator.getAttribute("value"), data.getUserName());
		Assert.assertEquals(passwordLocator.getAttribute("value"), data.getPassword());
		log.info("verifyAutofillOfUserNameAndPassword-completed");
		return this;
	}

	@Step("Verify login page functionality")
	public LoginPageObjects loginMethod(String enterUserName, String enterPassword) throws InterruptedException {
		waiting.waitForVisibilityOfElement(loginLinkLocator, 10);
		loginLinkLocator.click();
		log.info("clicked on login link");

		waiting.waitForVisibilityOfElement(userName, 10);
		userName.click();
		userName.clear();
		userName.sendKeys(enterUserName);
		log.info("Entered user name :" + enterUserName);

		waiting.waitForVisibilityOfElement(password, 10);
		password.click();
		password.clear();
		password.sendKeys(enterPassword);
		log.info("Entered Password:" + enterPassword);

		waiting.waitForVisibilityOfElement(loginButton, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", loginButton);
		log.info("clicked on login button");
		Thread.sleep(3000);
		return this;
	}

}
