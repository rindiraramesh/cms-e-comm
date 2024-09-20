package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ApprovalCartListPageObjects extends PageInitializer {

	Logger logger = Logger.getLogger(ApprovalCartListPageObjects.class);

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	@FindBy(xpath = "//h1[@class='cimm_page-title']")
	private WebElement approvalCartListPageLocator;

	@FindBy(xpath = "//input[@name='idList']/..")
	private List<WebElement> quantityCheckboxLocatorInApprovalcartListPage;

	@FindBy(xpath = "//input[@title='Quantity']")
	private List<WebElement> quantityTextBoxLocator;

	@FindBy(xpath = "//input[@id='reasonid']")
	private WebElement reasonTextboxLocator;

	@FindBy(xpath = "//input[@id='popLoginBtn']")
	private WebElement submitButtonLocator;

	@FindBy(xpath = "//button[@id='approveCart']")
	private WebElement approveCartButtonLocator;

	@FindBy(xpath = "//button[@id='rejectCart']")
	private WebElement rejectCartButtonLocator;

	@FindBy(xpath = "//input[@id='chkSelectall']/..")
	private WebElement selectAllCheckboxLocator;

	@FindBy(xpath = "//button[@id='updateSelectedItems']")
	private WebElement updateSelectedItemsButtonLocator;

	@Step("Verify Approval Cart List page with below fields: {0}")
	public ApprovalCartListPageObjects verifyApprovalCartListPage(String productName) {

		logger = Logger.getLogger("verifyApprovalCartListPage");

		waiting.waitForVisibilityOfElement(approvalCartListPageLocator, 10);

		String ApprovalCartListPageTitle = getDriver().getTitle().trim();

		Assert.assertEquals(ApprovalCartListPageTitle, "Approval Cart List" + " | " + productName, "Title is wrong");

		logger.info("Approval Cart List Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ ApprovalCartListPageTitle + "] and " + "[" + "Approval Cart List" + " | " + productName + "]");

		Assert.assertTrue(approvalCartListPageLocator.isDisplayed(), "Approval Cart List Page is not displayed");

		logger.info("Approval Cart List Page is displayed");

		return this;

	}

	@Step("Verify display of user mail id for approval the cart items : {0}")
	public ApprovalCartListPageObjects verifyDisplayOfGeneralUserMailIdWithDateAndTime(
			String userNameForWhichCartIsShared) {
		logger = Logger.getLogger("verifyDisplayOfGeneralUserMailIdWithDateAndTime");
		List<WebElement> generalUserEmailIDs = getDriver()
				.findElements(By.xpath("//a[contains(text(),'" + userNameForWhichCartIsShared.trim() + "')]"));

		Assert.assertTrue(generalUserEmailIDs.get(0).isDisplayed(), "Approval item mail is not displayed");

		logger.info("user mail id for approval the cart items has been verified : " + userNameForWhichCartIsShared);

		return this;

	}

	@Step("Click on the latest approval mail :{0}")
	public ApprovalCartListPageObjects clickOnLatestGeneralUserAccountLinkWhileSubmmitedCartForApproval(
			String userNameForWhichCartIsShared) throws InterruptedException {
		logger = Logger.getLogger("clickOnLatestGeneralUserAccountLinkWhileSubmmitedCartForApproval");
		List<WebElement> generalUserEmailIDs = getDriver()
				.findElements(By.xpath("//a[contains(text(),'" + userNameForWhichCartIsShared.trim() + "')]"));

		generalUserEmailIDs.get(0).click();
		Thread.sleep(1200);

		logger.info("clicked on the latest approval mail : " + userNameForWhichCartIsShared);
		return this;

	}

	@Step("Verify display of Reject Cart Button")
	public ApprovalCartListPageObjects verifyRejectCartButtonInApprovalCartPage() {
		waiting.waitForVisibilityOfElement(rejectCartButtonLocator, 10);

		Assert.assertTrue(rejectCartButtonLocator.isDisplayed(), "Reject cart Button is not displayed");

		logger.info("Reject cart Button is displayed.");
		return this;

	}

	@Step("Click on reject cart button")
	public ApprovalCartListPageObjects clickOnRejectCartButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnRejectCartButton");
		waiting.waitForVisibilityOfElement(rejectCartButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rejectCartButtonLocator);
		Thread.sleep(1200);

		logger.info("clicked on reject cart button");
		return this;

	}

	@Step("Verify display of Approval Cart Button")
	public ApprovalCartListPageObjects verifyApproveCartButtonInApprovalCartPage() {
		logger = Logger.getLogger("verifyApproveCartButtonInApprovalCartPage");
		waiting.waitForVisibilityOfElement(approveCartButtonLocator, 10);
		Assert.assertTrue(approveCartButtonLocator.isDisplayed(), "Approval cart Button is not displayed");
		logger.info("Approval cart Button is displayed.");
		return this;

	}

	@Step("Click on Approval Cart Button")
	public ApprovalCartListPageObjects clickOnApproveCartButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnApproveCartButton");
		waiting.waitForVisibilityOfElement(approveCartButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", approveCartButtonLocator);
		Thread.sleep(1200);
		logger.info("click on Approval Cart Button");
		return this;

	}

	@Step("Click on Select ALL")
	public ApprovalCartListPageObjects clickOnSelectAllCheckbox() throws InterruptedException {

		waiting.waitForVisibilityOfElement(selectAllCheckboxLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", selectAllCheckboxLocator);
		Thread.sleep(1200);
		return this;

	}

	@Step("Verify Update functionality Of items")
	public ApprovalCartListPageObjects verifyUpdateSelectedItemsButtonInApprovalCartPage() {
		logger = Logger.getLogger("verifyUpdateSelectedItemsButtonInApprovalCartPage");
		waiting.waitForVisibilityOfElement(updateSelectedItemsButtonLocator, 10);

		Assert.assertTrue(updateSelectedItemsButtonLocator.isDisplayed(), "Number of selected item is not updated");

		logger.info("Quantity of items has been updated.");
		return this;

	}

	@Step("Click on updated selected item button")
	public ApprovalCartListPageObjects clickOnUpdateSelectedItemsButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnUpdateSelectedItemsButton");
		waiting.waitForVisibilityOfElement(updateSelectedItemsButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", updateSelectedItemsButtonLocator);

		Thread.sleep(1200);
		logger.info("clicked on updated selected item button.");

		return this;

	}

	@Step("Enter quantity for specific items:{0},{1}")
	public ApprovalCartListPageObjects enterQuantity(String quantity, int numberOfCheckboxesToClick)
			throws InterruptedException {
		logger = Logger.getLogger("enterQuantity");
		waiting.waitForVisibilityOfElements(quantityTextBoxLocator, 10);
		quantityTextBoxLocator.get(numberOfCheckboxesToClick - 1).clear();
		quantityTextBoxLocator.get(numberOfCheckboxesToClick - 1).sendKeys(quantity);
		Thread.sleep(1200);
		logger.info("entered quantity for specific items: " + quantity + numberOfCheckboxesToClick);
		return this;
	}

	@Step("Click on specific checkbox to update quantity :{0}")
	public ApprovalCartListPageObjects clickOnSpecificCheckbox(int specificQuantityCheckbox)
			throws InterruptedException {
		logger = Logger.getLogger("clickOnSpecificCheckbox");

		waiting.waitForVisibilityOfElements(quantityCheckboxLocatorInApprovalcartListPage, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				quantityCheckboxLocatorInApprovalcartListPage.get(specificQuantityCheckbox - 1));
		Thread.sleep(1200);

		logger.info("click on specific checkbox to update quantity : " + specificQuantityCheckbox);

		return this;

	}

	@Step("Verify the functionality of Updated Selected Item Button :{0),{1}")
	public ApprovalCartListPageObjects verifyFunctionalityOfUpdateSelectedItemsButton(String quantity,
			int numberOfCheckboxesToClick) {
		logger = Logger.getLogger("verifyFunctionalityOfUpdateSelectedItemsButton");
		waiting.waitForVisibilityOfElements(quantityTextBoxLocator, 10);
		String quantityValue = quantityTextBoxLocator.get(numberOfCheckboxesToClick - 1).getAttribute("value");
		Assert.assertEquals(quantityValue.trim(), quantity.trim());
		logger.info(" functionality of Updated Selected Item Button has been verified"
				+ " with [Actual] and [Expected] value as " + "[" + quantityValue.trim() + "] and " + "["
				+ quantity.trim() + "]");
		return this;

	}

	@Step("Enter reason to reject cart:{0}")
	public ApprovalCartListPageObjects enterReasonToRejectCart(String reason) throws InterruptedException {
		logger = Logger.getLogger("enterReasonToRejectCart");
		waiting.waitForVisibilityOfElement(reasonTextboxLocator, 10);
		reasonTextboxLocator.sendKeys(reason);
		Thread.sleep(1200);
		logger.info("entered reason to reject cart: " + reason);
		return this;

	}

	@Step("Click on Submit Button of Rejected Cart Pop Up")
	public ApprovalCartListPageObjects clickOnSubmitButtonOfRejectedCartPopUp() throws InterruptedException {
		logger = Logger.getLogger("clickOnSubmitButtonOfRejectedCartPopUp");
		waiting.waitForVisibilityOfElement(submitButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", submitButtonLocator);

		Thread.sleep(1400);
		logger.info("clicked on Submit Button of Rejected Cart Pop Up");
		return this;

	}

}
