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
import utility.WaitingMethods;

public class LoginPopUpPageObjects extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(LoginPopUpPageObjects.class);

	@FindBy(xpath = "//form[@id='modalLoginForm']")
	public WebElement loginPopup;

	@FindBy(xpath = "//form[@id='modalLoginForm']//h4")
	public WebElement pageName;

	@FindBy(xpath = "//form[@id='modalLoginForm']//div[contains(@class,'pLoginErr')]")
	private WebElement errorMsgLocator;

	@FindBy(xpath = "//input[@id='modalRememberMe']/..")
	private WebElement rememberMeCheckbox;

	@FindBy(xpath = "//form[@id='modalLoginForm']//button[normalize-space(text())='Sign In']")
	private WebElement loginButton;

	@FindBy(xpath = "//input[@id='modalUserName']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='modalPassword']")
	private WebElement password;

	@FindBy(xpath = "//form[@id='modalLoginForm']/descendant::a[text()='Forgot your password?']")
	private WebElement forgotYourPassword;

	@FindBy(xpath = "//form[@id='modalLoginForm']//a[normalize-space(text())='Sign Up']")
	private WebElement signUp;

	@FindBy(xpath = "//form[@id='modalLoginForm']//button[@data-dismiss='modal']")
	private WebElement closeButton;

	@FindBy(xpath = "//form[@id='modalLoginForm']//span[text()='Remember me']")
	private WebElement rememberMeText;

	@Step("click on remember me checkbox")
	public LoginPopUpPageObjects clickOnRememberMe() {
		waiting.waitForVisibilityOfElement(rememberMeCheckbox, 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rememberMeCheckbox);
		return this;
	}

	@Step("click on close button")
	public LoginPopUpPageObjects clickCloseButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(closeButton, 20);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", closeButton);
		Thread.sleep(1200);
		return this;
	}

	@Step("click on forgot your password link")
	public ForgotPasswordPageObjects clickOnForgotYourPassword() {
		waiting.waitForVisibilityOfElement(forgotYourPassword, 20);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", forgotYourPassword);
		return forgotPasswordPage();
	}

	@Step("click on login button")
	public LoginPopUpPageObjects clickOnLoginButton() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", loginButton);
		return this;
	}

	@Step("enter user name")
	public LoginPopUpPageObjects enterUserName() throws Exception {
		waiting.waitForVisibilityOfElement(userName, 15);
		userName.clear();
		userName.sendKeys(data.getUserName());
		log.info("Entered user name :" + data.getUserName());
		return this;
	}

	@Step("enter password")
	public LoginPopUpPageObjects enterPassword() throws Exception {
		waiting.waitForVisibilityOfElement(password, 10);
		password.clear();
		password.sendKeys(data.getPassword());
		log.info("Entered Password:" + data.getPassword());
		return this;
	}

	@Step("enter user name {0}")
	public LoginPopUpPageObjects enterUserName(String uName) {
		waiting.waitForVisibilityOfElement(userName, 20);
		userName.clear();
		userName.sendKeys(uName);
		log.info("Entered Username:" + uName);
		return this;

	}

	@Step("enter password {0}")
	public LoginPopUpPageObjects enterPassword(String pwd) {
		waiting.waitForVisibilityOfElement(password, 20);
		password.clear();
		password.sendKeys(pwd);
		log.info("Entered Password:" + pwd);
		return this;

	}

	public LoginPopUpPageObjects hitEnter() {
		password.sendKeys(Keys.ENTER);
		log.info("Enter Key pressed");
		return this;
	}

	@Step("enter login pop up")
	public LoginPopUpPageObjects verifyLoginPopUp() {
		SoftAssert softAssert = new SoftAssert();
		waiting.waitForVisibilityOfElement(loginPopup, 15);
		softAssert.assertTrue(pageName.isDisplayed(), "Login page name is not displayed");
		softAssert.assertTrue(rememberMeText.isDisplayed(), "Remember me checkbox is not displayed");
		softAssert.assertTrue(loginButton.isDisplayed(), "login button is not displayed");
		softAssert.assertTrue(password.isDisplayed(), "password textbox is not displayed");
		softAssert.assertTrue(forgotYourPassword.isDisplayed(), "forgot your password link is not displayed");
		softAssert.assertTrue(signUp.isDisplayed(), "Sign up link is not displayed");
		softAssert.assertTrue(closeButton.isDisplayed(), "close button is not displayed");
		softAssert.assertAll();
		log.info("verifyLoginPopUp-completed");
		return this;
	}

	@Step("verify auto fill of user name and password")
	public LoginPopUpPageObjects verifyAutofillOfUserNameAndPassword() throws Exception {
		waiting.waitForVisibilityOfElement(userName, 15);
		Assert.assertEquals(userName.getAttribute("value"), data.getUserName());
		Assert.assertEquals(password.getAttribute("value"), data.getPassword());
		log.info("verifyAutofillOfUserNameAndPassword-completed");
		return this;
	}

	@Step("verify whether username and password textboxes are empty")
	public LoginPopUpPageObjects verifyEmptyUserNameAndPasswordTextbox() {
		waiting.waitForVisibilityOfElement(userName, 20);
		Assert.assertEquals(userName.getAttribute("value"), "");
		Assert.assertEquals(password.getAttribute("value"), "");
		log.info("verifyEmptyUserNameAndPasswordTextbox-completed");
		return this;
	}

	@Step("verify whether remember me checkbox is selected")
	public LoginPopUpPageObjects verifyRememberMeCheckBoxSelected() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(
				((JavascriptExecutor) getDriver()).executeScript("return $('#rememberMe').attr('checked');"), "checked",
				"remember me checkbox is not checked");
		log.info("verifyRememberMeCheckBoxSelected-completed");
		return this;
	}

	@Step("verify whether remember me checkbox is not selected.")
	public LoginPopUpPageObjects verifyRememberMeCheckboxNotSelected() throws InterruptedException {
		Thread.sleep(1500);
		Assert.assertEquals(((JavascriptExecutor) getDriver()).executeScript("return $('#rememberMe').attr('checked')",
				rememberMeCheckbox), null, "remember me checkbox is selected");
		log.info("verifyRememberMeCheckboxNotSelected-completed");
		return this;
	}

	public LoginPopUpPageObjects verifyDefaultTabFocus(String userNameId) {
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameId);
		log.info("verifyDefaultTabFocus-completed");
		return this;
	}

	@Step("verify tab focus from top to bottom")
	public LoginPopUpPageObjects verifyTabFocusTopToBottom(String userNameId, String passwordId,
			String forgotYourPasswordId, String rememberMeClassName, String loginButtonId) throws Exception {
		Thread.sleep(2000);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), passwordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Assert.assertTrue(getDriver().switchTo().activeElement().getText().trim().contains(rememberMeClassName),
				"Remember Me class name is not " + rememberMeClassName + " .It is "
						+ getDriver().switchTo().activeElement().getText().trim());
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), loginButtonId);
		log.info("verifyTabFocusTopToBottom-completed");
		return this;
	}

	@Step("verify tab focus from bottom to top")
	public LoginPopUpPageObjects verifyTabFocusBottomToTop(String userNameHTMLId, String passwordHTMLId,
			String forgotYourPasswordHTMLText, String rememberMeHTMLClassName, String loginButtonHTMLId)
			throws Exception {

		String tabBehind = Keys.chord(Keys.SHIFT, Keys.TAB);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), loginButtonHTMLId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		Assert.assertEquals(getDriver().switchTo().activeElement().getText().trim(), forgotYourPasswordHTMLText);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		Assert.assertTrue(getDriver().switchTo().activeElement().getText().trim().contains(rememberMeHTMLClassName),
				"Remember Me class name is not " + rememberMeHTMLClassName + " .It is "
						+ getDriver().switchTo().activeElement().getAttribute("class"));
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), passwordHTMLId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		Assert.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), userNameHTMLId);
		log.info("verifyTabFocusBottomToTop-completed");
		return this;
	}

	public LoginPopUpPageObjects hitTabTopToBottom(int numberOfTimesToTab) {
		for (int j = 0; j < numberOfTimesToTab; j++) {
			getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		}
		log.info("hitTabTopToBottom-completed");
		return this;
	}

	@Step("verify error message is {0}")
	public LoginPopUpPageObjects verifyErrorMessages(String expectedMsg) throws InterruptedException {
		waiting.waitForVisibilityOfElement(errorMsgLocator, 20);
		Assert.assertEquals(errorMsgLocator.getText().trim(), expectedMsg, "Invalid error message");
		log.info("verifyErrorMessages-completed");
		return this;
	}

	@Step("Click on sign up link")
	public RegistrationANPageObjects clickOnSignUpLink() {
		waiting.waitForVisibilityOfElement(signUp, 15);
		signUp.click();
		log.info("Clicked on Register Here link");
		return onAccountRegistrationPage();
	}
}
