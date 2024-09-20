package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.WaitingMethods;

public class CommonPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	@FindBy(xpath = "//div[@class='modal-content']/descendant::div[@class='bootbox-body']")
	private WebElement alertTextLocator;

	@FindBy(xpath = "//div[@class='modal-content']/descendant::button[@data-bb-handler='cancel']")
	private WebElement alertPopUpCancleButtonLocator;

	@FindBy(xpath = "//div[@id='popup_message']")
	private WebElement alertTextLocatorForAddNewCreditCard;

	@FindBy(xpath = "//input[@id='popup_ok']")
	private WebElement alertPopUpOkButtonLocatorForCreditCard;

	@FindBy(xpath = "//button[contains(@data-bb-handler,'confirm') or  contains(@data-bb-handler,'ok')]")
	private WebElement alertPopUpOkButtonLocator;

	@Step("Verify Alert text Message : {0}")
	public CommonPageObjects verifyAlertTextPopUp(String alertTextOfDeleteSaveCart) throws InterruptedException {
		logger = Logger.getLogger("verifyAlertTextPopUp");
		Thread.sleep(5000);
		Assert.assertEquals(alertTextLocator.getText().trim().toLowerCase().replace("\n", ""),alertTextOfDeleteSaveCart.trim().toLowerCase(), "Alert text is invalid.");
		logger.info("Alert Message has been verified" + " with [Actual] and [Expected] value as " + "["+ alertTextLocator.getText().trim().toLowerCase() + "] and " + "["+ alertTextOfDeleteSaveCart.trim().toLowerCase() + "]");
		return this;
	}

	@Step("Verify Alert text Message : {0}")
	public CommonPageObjects verifyAlertTextPopUpForAddNewCreditCard(String alertTextOfDeleteSaveCart)
			throws InterruptedException {
		logger = Logger.getLogger("verifyAlertTextPopUp");
		Thread.sleep(2000);
		Assert.assertEquals(alertTextLocatorForAddNewCreditCard.getText().trim().toLowerCase().replace("\n", ""),
				alertTextOfDeleteSaveCart.trim().toLowerCase(), "Alert text is invalid.");
		logger.info("Alert Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ alertTextLocator.getText().trim().toLowerCase() + "] and " + "["
				+ alertTextOfDeleteSaveCart.trim().toLowerCase() + "]");
		return this;
	}

	@Step("verify Alert text Message : {0}")
	public CommonPageObjects verifyAlertPopUpContainsText(String particularText) throws InterruptedException {
		logger = Logger.getLogger("verifyAlertTextPopUp");
		Thread.sleep(1200);

		Assert.assertTrue(alertTextLocator.getText().trim().toLowerCase().contains(particularText.trim().toLowerCase()),
				"Alert text is invalid.");

		return this;
	}

	@Step("Click on OK button in Alert Pop Up")
	public CommonPageObjects clickOnOkButtonInAlertPopUp() throws InterruptedException {
		// waiting.waitForVisibilityOfElement(alertPopUpOkButtonLocator, 2);
		logger = Logger.getLogger("clickOnOkButtonInAlertPopUp");
		Thread.sleep(2000);
		alertPopUpOkButtonLocator.click();
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", alertPopUpOkButtonLocator);
		Thread.sleep(1700);
		logger.info("Clicked on OK button in Alert Pop Up");

		return this;
	}

	@Step("Click on OK button in Alert Pop Up")
	public CommonPageObjects clickOnOkButtonInAlertPopUpForAddNewCreditCard() throws InterruptedException {
		waiting.waitForVisibilityOfElement(alertPopUpOkButtonLocatorForCreditCard, 5);
		logger = Logger.getLogger("alertPopUpOkButtonLocatorForCreditCard");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				alertPopUpOkButtonLocatorForCreditCard);
		Thread.sleep(1800);
		logger.info("Clicked on OK button in Alert Pop Up");
		return this;
	}

	@Step("verify display of Ok Button In Alert Pop Up")
	public CommonPageObjects verifyOkButtonInAlertPopUp() throws InterruptedException {

		waiting.waitForVisibilityOfElement(alertPopUpOkButtonLocator, 10);

		logger = Logger.getLogger("verifyOkButtonInAlertPopUp");

		Assert.assertTrue(alertPopUpOkButtonLocator.isDisplayed(), "Ok Button is not displayed");
		Thread.sleep(1200);
		logger.info("Ok Button is displayed");

		return this;
	}

	@Step("Click on cancle button in Alert Pop Up")
	public CommonPageObjects clickOnCancelButtonInAlertPopUp() throws InterruptedException {
		waiting.waitForVisibilityOfElement(alertPopUpCancleButtonLocator, 10);
		logger = Logger.getLogger("clickOnCancelButtonInAlertPopUp");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", alertPopUpCancleButtonLocator);
		Thread.sleep(1500);
		logger.info("Clicked on cancle button in Alert Pop Up");

		return this;
	}

	@Step("verify display of Cancle Button In Alert Pop Up")
	public CommonPageObjects verifyCancelButtonInAlertPopUp() throws InterruptedException {
		waiting.waitForVisibilityOfElement(alertPopUpCancleButtonLocator, 10);

		logger = Logger.getLogger("verifyCancleButtonInAlertPopUp");

		Assert.assertTrue(alertPopUpCancleButtonLocator.isDisplayed(), "Cancle Button is not displayed");
		Thread.sleep(1200);
		logger.info("Cancel Button is displayed");

		return this;
	}

	@Step("verify Alert text Message : {0}")
	public CommonPageObjects verifyJavaScriptTextPopUp(String partNumber) throws InterruptedException {

		logger = Logger.getLogger("verifyAlertTextPopUp");
		Thread.sleep(2000);

		waiting.waitForAlertToBePresent(5);
		String expText = "Min Order Quantity is 10. For Part#: " + partNumber
				+ ". To Continue with Min Order Qty click \"Ok\".To cancel this item click \"Cancel\"";

		Alert alert_popup = getDriver().switchTo().alert();
		String error_msg = alert_popup.getText().trim();
		alert_popup.accept();

		Assert.assertEquals(error_msg.toLowerCase(), expText.trim().toLowerCase(), "Alert text is invalid.");

		logger.info("Alert Message has been verified" + " with [Actual] and [Expected] value as " + "["
				+ error_msg.toLowerCase() + "] and " + "[" + expText.trim().toLowerCase() + "]");

		return this;
	}

	@Step("Alert accept : {0}")
	public CommonPageObjects alertJavaAccept() throws InterruptedException {

		logger = Logger.getLogger("verifyAlertTextPopUp");
		Thread.sleep(2000);

		Alert alert_popup = getDriver().switchTo().alert();
		alert_popup.accept();
		return this;
	}

}