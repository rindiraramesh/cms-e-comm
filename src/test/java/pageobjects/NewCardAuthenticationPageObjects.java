package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.WaitingMethods;

public class NewCardAuthenticationPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(NewCardAuthenticationPageObjects.class);

	@FindBy(xpath = "//input[@id='cardNumber']")
	private WebElement cardNumberTextboxLocator;

	@FindBy(id = "ddlExpirationMonth")
	private WebElement expirationMonthDropdownLocator;

	@FindBy(id = "submit")
	private WebElement addToCardButtonLocator;

	@FindBy(id = "ddlExpirationYear")
	private WebElement expirationYearDropdownLocator;

	@FindBy(xpath = "//div[@id='cardStatus']/center")
	private WebElement successMessageLocator;

	@FindBy(xpath = "//span[@class='error']")
	private WebElement errorMessageLocator;

	@Step("Verify New Card Authentication Page Title:{0}")
	public NewCardAuthenticationPageObjects verifyNewCardAuthenticationPageTitle(String productName) {
		log = Logger.getLogger("verifyNewCardAuthenticationPageTitle");
		String newCardAuthenticationPageTitle = getDriver().getTitle().trim();
		Assert.assertEquals(newCardAuthenticationPageTitle, "New Card Authentication" + " - " + productName,
				"Title is wrong");
		log.info("Title of the New Card Authentication page has been verified"
				+ " with [Actual] and [Expected] value as " + "[" + newCardAuthenticationPageTitle + "] and " + "["
				+ "New Card Authentication" + " - " + productName + "]");

		return this;
	}

	@Step("Enter Card Number :{0}")
	public NewCardAuthenticationPageObjects enterCardNumber(String cardNumber) throws InterruptedException {
		log = Logger.getLogger("enterNickName");
		waiting.waitForVisibilityOfElement(cardNumberTextboxLocator, 10);
		cardNumberTextboxLocator.clear();
		cardNumberTextboxLocator.sendKeys(cardNumber);
		Thread.sleep(1200);
		log.info("Entered Card Number : " + cardNumber);
		return this;
	}

	@Step("Select Month To Create Credit Card:{0}")
	public NewCardAuthenticationPageObjects selectExpirationMonth(String month) throws InterruptedException {
		log = Logger.getLogger("selectExpirationMonth");
		waiting.waitForVisibilityOfElement(expirationMonthDropdownLocator, 10);
		Select sel = new Select(expirationMonthDropdownLocator);
		sel.selectByVisibleText(month);
		Thread.sleep(1200);
		log.info("Select Month To Create Credit Card: " + month);
		return this;
	}

	@Step("Select Year To Create Credit Card:{0}")
	public NewCardAuthenticationPageObjects selectExpirationYear(String year) throws InterruptedException {
		log = Logger.getLogger("selectExpirationYear");
		waiting.waitForVisibilityOfElement(expirationYearDropdownLocator, 10);
		Select sel = new Select(expirationYearDropdownLocator);
		sel.selectByVisibleText(year);
		Thread.sleep(1200);
		log.info("Select Year To Create Credit Card: " + year);
		return this;
	}

	@Step("Switch Inside Frame.")
	public NewCardAuthenticationPageObjects switchInsideFrame() throws InterruptedException {
		log = Logger.getLogger("switchInsideFrame");
		getDriver().switchTo().frame("frameCont");
		Thread.sleep(1200);
		log.info("Switched Inside Frame.");
		return this;
	}

	@Step("Click On Add To Card Button.")
	public NewCardAuthenticationPageObjects clickOnAddToCardButton() throws InterruptedException {
		log = Logger.getLogger("clickOnAddToCardButton");
		waiting.waitForVisibilityOfElement(addToCardButtonLocator, 10);
		addToCardButtonLocator.click();
		Thread.sleep(2400);
		log.info("Clicked On Add To Card Button.");
		return this;
	}

	@Step("Verify Newly Created Credit Card Message :{0}")
	public NewCardAuthenticationPageObjects verifyNewlyAddedCreditCardSuccessMessage(String successMessage) {
		log = Logger.getLogger("verifyNewlyAddedCreditCardSuccessMessage");
		waiting.waitForVisibilityOfElement(successMessageLocator, 10);
		Assert.assertEquals(successMessageLocator.getText().trim().toLowerCase(), successMessage.trim().toLowerCase(),
				"Success Message Of Newly Created Credit Card is not :" + successMessage.trim().toLowerCase()
						+ ". It is :" + successMessageLocator.getText().trim().toLowerCase());
		log.info("Newly Created Credit Card Sucess Message has been verified"
				+ " with [Actual] and [Expected] value as " + "[" + successMessageLocator.getText().trim() + "] and "
				+ "[" + successMessage.trim().toLowerCase() + "]");
		return this;
	}

	@Step("Switch Back to main page.")
	public NewCardAuthenticationPageObjects swichBackToMainWindow() throws InterruptedException {
		log = Logger.getLogger("swichBackToMainWindow");
		getDriver().switchTo().defaultContent();
		Thread.sleep(1200);
		log.info("Switch Back to main page.");
		return this;

	}

	@Step("Verify Error Message for invalid credit card : {0}")
	public NewCardAuthenticationPageObjects verifyErrorMessageForInvalidCreditCard(String errorMessage) {
		log = Logger.getLogger("verifyErrorMessageForInvalidCreditCard");
		waiting.waitForVisibilityOfElement(errorMessageLocator, 10);
		Assert.assertEquals(errorMessageLocator.getText().trim().replace("- ", "").toLowerCase(),
				errorMessage.trim().toLowerCase(),
				"Error Message for invalid credit card is not : " + errorMessage.trim().toLowerCase() + ". It is : "
						+ errorMessageLocator.getText().trim().replace("-", "").toLowerCase());
		logger.info(
				"Error Message For Invalid Credit Card has been verified" + " with [Actual] and [Expected] value as "
						+ "[" + errorMessageLocator.getText().trim().replace("- ", "").toLowerCase() + "] and " + "["
						+ errorMessage.trim().toLowerCase() + "]");
		return this;
	}

}
