package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.WaitingMethods;

public class CompletedOrdersPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	@FindBy(xpath = "//h1[text()='Completed Orders']")
	private WebElement completedOrdersPageName;

	@Step("verify completed orders page name")
	public CompletedOrdersPageObjects verifyCompletedOrdersPagename() {
		logger = Logger.getLogger("verifyCompletedOrdersPagename");
		waiting.waitForVisibilityOfElement(completedOrdersPageName, 10);
		Assert.assertTrue(completedOrdersPageName.isDisplayed(), "Completed Order Page Name is not displayed.");
		logger.info("Completed Order Page Name is displayed.");
		return this;
	}

	@Step("verify page title of completed orders :{0}")
	public CompletedOrdersPageObjects verifyCompleteOrdersTitle(String productName) throws InterruptedException {

		logger = Logger.getLogger("verifyCompleteOrdersTitle");
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Completed Orders" + " | " + productName, "Title is wrong");

		logger.info("Page Title Of Completed Order  has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Completed Orders" + " | " + productName + "]");
		return this;
	}

}
