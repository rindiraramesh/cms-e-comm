package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class AccountFinancialInfoPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(AccountFinancialInfoPageObjects.class);

	@FindBy(xpath = "//h6[text()='Account Inquiry']")
	private WebElement accountFinancialInfoPageName;

	@FindBy(xpath = "//h6[text()='OPEN BALANCES']")
	private WebElement openBalanceTabLocator;

	@FindBy(xpath = "//h6[text()='OPEN BALANCES']/following-sibling::div//th")
	private List<WebElement> openBalanceTableHeaderLocator;

	@FindBy(xpath = "//h6[text()='ACCOUNT BALANCE']")
	private WebElement accountBalanceTabLocator;

	@FindBy(xpath = "//h6[text()='ACCOUNT BALANCE']/following-sibling::div//th")
	private List<WebElement> accountBalanceTableHeaderLocator;

	@Step("Verify Account Financial Info Page Name")
	public AccountFinancialInfoPageObjects verifyAccountFinancialInfoPageName() {
		log = Logger.getLogger("verifyOpenOrdersPageName");
		Assert.assertTrue(accountFinancialInfoPageName.isDisplayed(),
				"Account Financial Info Page Name is not displayed.");
		log.info("Account Financial Info Page Name is displayed.");
		return this;
	}

	@Step("Verify Account Financial Info Page Title as : {0}")
	public AccountFinancialInfoPageObjects verifyAccountFinancialInfoPageTitle(String productName)
			throws InterruptedException {
		log = Logger.getLogger("verifyAccountFinancialInfoPageTitle");
		Thread.sleep(1500);
		String AccountFinancialInfoPageTitle = getDriver().getTitle();
		Assert.assertEquals(AccountFinancialInfoPageTitle, "AccountInquiry" + " | " + productName, "Title is wrong");
		log.info("Title of the Account Financial Info page has been verified"
				+ " with [Actual] and [Expected] value as " + "[" + AccountFinancialInfoPageTitle + "] and " + "["
				+ "AccountInquiry" + " | " + productName + "]");
		return this;
	}

	@Step("Verify Account Balance Table Header : {0}")
	public AccountFinancialInfoPageObjects verifyAccountBalanceTabAndTableHeader(String accountBalanceTableHeader) {
		log = Logger.getLogger("verifyAccountBalanceTabAndTableHeader");
		waiting.waitForVisibilityOfElement(accountBalanceTabLocator, 10);
		Assert.assertTrue(accountBalanceTabLocator.isDisplayed(), "Account Balance Tab is not displayed.");
		log.info("Account Balance Tab not displayed.");
		String[] expectedAccountBalanceTableHeader = accountBalanceTableHeader.split(",");
		for (int i = 0; i < accountBalanceTableHeaderLocator.size(); i++) {
			Assert.assertEquals(accountBalanceTableHeaderLocator.get(i).getText().trim().replace(":", ""),
					expectedAccountBalanceTableHeader[i].trim(),
					"Account Balance Table Header  is not :" + expectedAccountBalanceTableHeader[i].trim() + ". It is :"
							+ accountBalanceTableHeaderLocator.get(i).getText().trim().replace(":", ""));
			log.info("Account Balance Table Header  has been verified" + " with [Actual] and [Expected] value as " + "["
					+ accountBalanceTableHeaderLocator.get(i).getText().trim().replace(":", "") + "] and " + "["
					+ expectedAccountBalanceTableHeader[i].trim() + "]");
		}
		return this;

	}

	@Step("Verify Open Balance Table Header : {0}")
	public AccountFinancialInfoPageObjects verifyOpenBalanceTabAndTableHeader(String openBalanceTableHeader) {
		log = Logger.getLogger("verifyOpenBalanceTabAndTableHeader");
		waiting.waitForVisibilityOfElement(openBalanceTabLocator, 10);

		Assert.assertTrue(openBalanceTabLocator.isDisplayed(), "Open Balance Tab is not displayed.");
		log.info("Open Balance Tab not displayed.");

		String[] expectedOpenBalanceTableHeader = openBalanceTableHeader.split(",");

		for (int i = 0; i < openBalanceTableHeaderLocator.size(); i++) {
			System.out.println(
					" verifyOpenBalanceTabAndTableHeader :===> " + openBalanceTableHeaderLocator.get(i).getText());

			Assert.assertEquals(openBalanceTableHeaderLocator.get(i).getText().trim().replace(":", ""),
					expectedOpenBalanceTableHeader[i].trim(),
					"Open Balance Table Header  is not :" + expectedOpenBalanceTableHeader[i].trim() + ". It is :"
							+ openBalanceTableHeaderLocator.get(i).getText().trim().replace(":", ""));

			log.info("Open Balance Table Header  has been verified" + " with [Actual] and [Expected] value as " + "["
					+ openBalanceTableHeaderLocator.get(i).getText().trim().replace(":", "") + "] and " + "["
					+ expectedOpenBalanceTableHeader[i].trim() + "]");
		}
		return this;
	}

}
