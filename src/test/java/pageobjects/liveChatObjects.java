package pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class liveChatObjects extends PageInitializer {

	Logger log = Logger.getLogger(liveChatObjects.class);

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods util = new UtilityMethods(getDriver());

	SoftAssert soft = new SoftAssert();

	String mainWindow;

	@FindBy(css = "#footerMenu4>li:nth-child(3)>a")
	private WebElement liveChatLocator;

	@FindBy(xpath = "//div[@id='LP_TextFieldQuestion_1']//input")
	private WebElement fromNameInputLocator;

	@FindBy(xpath = "//div[@id='LP_TextFieldQuestion_2']//input")
	private WebElement customerNumberInputLocator;

	@FindBy(xpath = "//div[@id='LP_TextFieldQuestion_3']//input")
	private WebElement emailInputLocator;

	@FindBy(xpath = "//div[@id='LP_TextFieldQuestion_4']//input")
	private WebElement phoneNumberInputLocator;

	@FindBy(xpath = "//div[@id='LP_TextFieldQuestion_5']//input")
	private WebElement subjectInputLocator;

	@FindBy(xpath = "//div[@id='LP_TextAreaQuestion_1']//div/textarea")
	private WebElement messageInputLocator;

	@FindBy(xpath = "//button[@data-lp-point='submit_button']")
	private WebElement submitButtonLocator;

	@FindBy(xpath = "//button[@data-lp-point='cancel_button']")
	private WebElement cancelButtonLocator;

	@FindBy(xpath = "//div[@data-lp-point=\"errorText\"]")
	private List<WebElement> errorMessageLocator;

	@FindBy(xpath = "//div[@class='lp_lpview_title lp_title']")
	private WebElement thankYouLocator;

	@FindBy(xpath = "//img[contains(@id,'LPMimage')]")
	private WebElement leaveMessageLocator;

	@Step("Click on live chat")
	public liveChatObjects clickOnLiveChatLink() {
		liveChatLocator.click();
		log.info("Clicked on Live chat link");
		return this;
	}

	@Step("Click on leave message")
	public liveChatObjects clickOnLeaveMessageLink() {
		leaveMessageLocator.click();
		log.info("Clicked on leave message link");
		return this;
	}

	@Step("Click on live chat")
	public liveChatObjects focusOnContactUsWindow() {
		Set<String> allTabs;
		Iterator<String> itr;

		allTabs = getDriver().getWindowHandles();
		itr = allTabs.iterator();
		mainWindow = itr.next();
		String contactUs = itr.next();
		getDriver().switchTo().window(contactUs);
		log.info("Focus is on Contact Us");
		return this;
	}

	@Step("verifies the social media section")
	public liveChatObjects verifyContactUsFields() throws Throwable {

		soft.assertTrue(util.isElementDisplayed(fromNameInputLocator), "From Name input is not displayed.");
		log.info("From Name input is verified.");

		soft.assertTrue(util.isElementDisplayed(customerNumberInputLocator), "Customer Number input is not displayed.");
		log.info("Customer Number input is verified.");

		soft.assertTrue(util.isElementDisplayed(emailInputLocator), "Email input is not displayed.");
		log.info("Email input is verified.");

		soft.assertTrue(util.isElementDisplayed(phoneNumberInputLocator), "phone number input is not displayed.");
		log.info("Phone number input is verified.");

		soft.assertTrue(util.isElementDisplayed(subjectInputLocator), "Subject input is not displayed.");
		log.info("Subject input is verified.");

		soft.assertTrue(util.isElementDisplayed(messageInputLocator), "Message field is not displayed.");
		log.info("Message field is verified.");

		soft.assertTrue(util.isElementDisplayed(submitButtonLocator), "Submit btn is not displayed.");
		log.info("Submit Btn is verified.");

		soft.assertTrue(util.isElementDisplayed(cancelButtonLocator), "Cancel Btn is not displayed.");
		log.info("Cancel Btn is verified.");

		soft.assertAll();
		getDriver().close();
		getDriver().switchTo().window(mainWindow);
		return null;
	}

	@Step("Click on Submit Button")
	public liveChatObjects clickOnSubmitButtonContactUsWindow() {
		submitButtonLocator.click();
		log.info("Clicked on Submit button");
		return this;
	}

	@Step("Click on Cancel Button")
	public liveChatObjects clickOnCancelButtonContactUsWindow() {
		cancelButtonLocator.click();
		log.info("Clicked on Cancel button");
		return this;
	}

	@Step("Verify Error message")
	public liveChatObjects verifyErrorMessageInContactUsWindow(String errorMsg) {
		for (WebElement error : errorMessageLocator) {
			assertEquals(error.getText().trim(), errorMsg, "Error message is different");

		}

		log.info("Error message is verified");
		getDriver().close();
		getDriver().switchTo().window(mainWindow);
		return this;
	}

	@Step("Enter From Name : {0}")
	public liveChatObjects enterFromName(String fromName) {
		fromNameInputLocator.sendKeys(fromName);
		log.info("Enter From Name");
		return this;
	}

	@Step("Enter Customer Number : {0}")
	public liveChatObjects enterCustomerNumber(String CustomerNumber) {
		customerNumberInputLocator.sendKeys(CustomerNumber);
		log.info("Enter Customer Number");
		return this;
	}

	@Step("Enter Email: {0}")
	public liveChatObjects enterEmail(String email) {
		emailInputLocator.sendKeys(email);
		log.info("Enter email");
		return this;
	}

	@Step("Enter message: {0}")
	public liveChatObjects enterMessage(String message) {
		messageInputLocator.sendKeys(message);
		log.info("Enter message");
		return this;
	}

	@Step("Verify thank you message")
	public liveChatObjects verifyThankyouMsg() {
		Assert.assertTrue(util.isElementDisplayed(thankYouLocator), "Thank you message is not displayed");
		log.info("Thank you message is displayed");
		getDriver().close();
		getDriver().switchTo().window(mainWindow);
		return this;
	}

}
