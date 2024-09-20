package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.MainOperations;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class UserInformationPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());
	
	MainOperations mo = new MainOperations();

	/*@FindBy(xpath = "//h1[@class='cimm_pageTitle']")
	private WebElement userInformationPageNameLocator;*/

	@FindBy(xpath = "//h1[@class='cimm_page-title']")
	private WebElement userInformationPageNameLocator;

	@FindBy(xpath = "//form[@action='editBillAddress.action']//td/a[normalize-space(text())='Edit']")
	private WebElement editButtonUnderBillingAddressLocator;
	
	@FindBy(xpath = "//a[contains(text(),'Add Shipping Address')]")
	private WebElement addShippingAddressButtonLocator;

	@FindBy(xpath = "//h2[normalize-space(text())='Shipping Address']/following-sibling::div//tbody//a[text()='Edit']")
	private List<WebElement> editButtonUnderShippingAddressLocator;

	@Step("Verify User Information Page Title as : {0}")
	public UserInformationPageObjects verifyUserInformationPageTitle(String productName) {
		logger = Logger.getLogger("verifyUserInformationPageTitle");
		Assert.assertEquals(getDriver().getTitle().trim(), productName.trim(), "Title is wrong");
		logger.info("Title of the User Information Page has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + getDriver().getTitle().trim() + "] and " + "[" + productName.trim() + "]");
		return this;
	}

	@Step("Verify User Information Page Name as : {0}")
	public UserInformationPageObjects verifyUserInformationPageName(String userInformationPageName) {
		logger = Logger.getLogger("verifyUserInformationPageName");
		waiting.waitForVisibilityOfElement(userInformationPageNameLocator, 10);
		Assert.assertEquals(userInformationPageNameLocator.getText().trim().toUpperCase(),
				userInformationPageName.toUpperCase(),
				"User Information Page Name is not :" + userInformationPageName.toUpperCase() + ". It is : "
						+ userInformationPageNameLocator.getText().trim().toUpperCase());
		logger.info("User Information Page Name has been verified" + " with [Actual] and [Expected] value as " + "["
				+ userInformationPageNameLocator.getText().trim().toUpperCase() + "] and " + "["
				+ userInformationPageName.toUpperCase() + "]");
		return this;
	}

	@Step("Click On Edit Button Of Billing Address Under User Information Page")
	public UserInformationPageObjects clickOnEditButtonUnderBillingAddress() throws InterruptedException {
		logger = Logger.getLogger("clickOnEditButtonUnderBillingAddress");
		waiting.waitForVisibilityOfElement(editButtonUnderBillingAddressLocator, 10);
		editButtonUnderBillingAddressLocator.click();
		Thread.sleep(1200);
		logger.info("Clicked On Edit Button Of Billing Address Under User Information Page.");
		return this;
	}

	@Step("Click On Edit Button Of Shipping Address Under User Information Page")
	public UserInformationPageObjects clickOnSpecificEditButtonUnderShippingAddress(int specificEditButton)
			throws InterruptedException {
		logger = Logger.getLogger("clickOnSpecificEditButtonUnderShippingAddress");
		Thread.sleep(3500);
		waiting.waitForVisibilityOfElements(editButtonUnderShippingAddressLocator, 10);
		editButtonUnderShippingAddressLocator.get(specificEditButton - 1).click();
		Thread.sleep(1200);
		logger.info("Clicked On Specific Edit Button Of Shipping Address Under User Information Page : "
				+ specificEditButton);
		return this;

	}

	@Step("Verify Whether Billing Address Updated :{0}")
	public UserInformationPageObjects verifyWhetherBillingAddressUpdated(String address1) {
		logger = Logger.getLogger("verifyWhetherBillingAddressUpdated");
		By actualAddress1 = By.xpath("//form[@method='get']//td[@data-th='Address']/span[normalize-space(text())='"
				+ address1.trim() + "']");
		Assert.assertTrue(utilityMethods.isElementDisplayed(actualAddress1),
				"Address1 Of Billing Address is not updated.");
		logger.info("Billing Address has been updated : " + address1);
		return this;

	}

	@Step("Verify Whether Shipping Address Updated :{0}")
	public UserInformationPageObjects verifyWhetherShippingAddressUpdated(String address1) {
		logger = Logger.getLogger("verifyWhetherShippingAddressUpdated");
		By actualAddress1OfShippingAddress = By.xpath(
				"//h2[normalize-space(text())='Shipping Address']/following-sibling::div//span[normalize-space(text())='"
						+ address1.trim() + "']");
		Assert.assertTrue(utilityMethods.isElementDisplayed(actualAddress1OfShippingAddress),
				"Address1 Of Shipping Address is not updated.");
		logger.info("Shipping Address has been updated : " + address1);
		return this;
	}

	@Step("Click On Add Shipping Address Button")
	public UserInformationPageObjects clickOnAddShippingAddressButton()
			throws InterruptedException {
		mo.waitForVisibilityOfElement(addShippingAddressButtonLocator, 5);
		mo.clickByJavaScript(addShippingAddressButtonLocator);
		return this;

	}


}
