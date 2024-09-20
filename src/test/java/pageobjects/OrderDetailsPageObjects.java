package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.WaitingMethods;

public class OrderDetailsPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	/*@FindBy(xpath = "//h2[@class='cimm_pageTitle']")
	private WebElement pageNameLocator;
	*/

	@FindBy(xpath = "//h2[@class='cimm_page-title']")
	private WebElement pageNameLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a")
	private List<WebElement> breadCrumbLocators;

	@FindBy(xpath = "//h3[@class='cimm_subheading']")
	private WebElement orderStatusLocator;

	@FindBy(xpath = "//div[@id='printOrderInvoices']//h4")
	private List<WebElement> orderDetailBillingShippingAddressLocators;

	@FindBy(xpath = "//form[@id='reOrderForm']//th")
	private List<WebElement> reOderItemDescriptionHeaderLocators;

	@FindBy(xpath = "//form[@id='reOrderForm']//li/span[1]")
	private List<WebElement> priceHeaderLocators;

	@FindBy(xpath = "//input[@id='reorderBtn']")
	private WebElement reOrderButtonLocator;

	@FindBy(xpath = "//li/a[@href='Dashboard']")
	private WebElement myAccountLinkLocator;

	@FindBy(xpath = "//form[@id='reOrderForm']//span[normalize-space(text())='Select All']/..")
	private WebElement selectAllCheckBoxLocator;

	@Step("Verify Order Details Page Name:{0}")
	public OrderDetailsPageObjects verifyOrderDetailPageName(String orderDetailPageName) {
		logger = Logger.getLogger("verifyOrderDetailPageName");
		Assert.assertEquals(pageNameLocator.getText().trim().toUpperCase(), orderDetailPageName.trim().toUpperCase(),
				"Order Details Page Name is not :" + orderDetailPageName.trim().toUpperCase() + ". It Is :"
						+ pageNameLocator.getText().trim().toUpperCase());
		logger.info("Order Detail Page Name has been verified" + " with [Actual] and [Expected] value as " + "["
				+ pageNameLocator.getText().trim().toUpperCase() + "] and " + "["
				+ orderDetailPageName.trim().toUpperCase() + "]");
		return this;
	}

	@Step("Verify Order Details Bread Crumb:{0}")
	public OrderDetailsPageObjects verifyOrderDetailBreadCrumbForOpenOrderAndCompletedOrder(
			String orderDetailBreadCrumb) {
		logger = Logger.getLogger("verifyOrderDetailBreadCrumbForOpenOrder");
		waiting.waitForVisibilityOfElements(breadCrumbLocators, 10);
		String lastBreadCrumb = breadCrumbLocators.get(breadCrumbLocators.size() - 1).getText().trim();
		Assert.assertEquals(lastBreadCrumb.trim().toUpperCase(), orderDetailBreadCrumb.toUpperCase(),
				"last breadcrump of order detail page when clicked on view order button under open orders is not the same. It is : "
						+ orderDetailBreadCrumb.toUpperCase() + " and the last breadcrump is : "
						+ lastBreadCrumb.trim().toUpperCase());
		logger.info(
				"last breadcrump of order detail page when clicked on view order button under open orders has been verified"
						+ " with [Actual] and [Expected] value as " + "[" + lastBreadCrumb.trim().toUpperCase()
						+ "] and " + "[" + orderDetailBreadCrumb.toUpperCase() + "]");
		return this;
	}

	@Step("Verify Order Details Page :{0},{1},{2}")
	public OrderDetailsPageObjects verifyOrderDetailPage(String orderDetailHeader, String reOderItemDescriptionHeader,
			String priceHeader) {
		SoftAssert softAssert = new SoftAssert();
		waiting.waitForVisibilityOfElement(orderStatusLocator, 10);
		String[] expectedOrderDetailHeader = orderDetailHeader.split(",");
		String[] expectedreOderItemDescriptionHeader = reOderItemDescriptionHeader.split(",");
		String[] expectedrePriceHeader = priceHeader.split(",");
		softAssert.assertTrue(orderStatusLocator.isDisplayed(), "Order Status is not displayed in order detail page.");

		for (int i = 0; i < orderDetailBillingShippingAddressLocators.size(); i++) {

			softAssert.assertEquals(orderDetailBillingShippingAddressLocators.get(i).getText().toLowerCase().trim(),
					expectedOrderDetailHeader[i].toLowerCase().trim(),
					"Order Detail,Billing Address and Shipping Address are not similar to :"
							+ expectedOrderDetailHeader[i].toLowerCase().trim() + ". It is : "
							+ orderDetailBillingShippingAddressLocators.get(i).getText().toLowerCase().trim());
		}
		for (int i = 0; i < reOderItemDescriptionHeaderLocators.size(); i++) {

			softAssert.assertEquals(reOderItemDescriptionHeaderLocators.get(i).getText().toLowerCase().trim(),
					expectedreOderItemDescriptionHeader[i].toLowerCase().trim(),
					"Item's Description is not :" + expectedreOderItemDescriptionHeader[i].toLowerCase().trim()
							+ ". It is : " + reOderItemDescriptionHeaderLocators.get(i).getText().toLowerCase().trim());
		}
		for (int i = 0; i < priceHeaderLocators.size(); i++) {

			softAssert.assertEquals(priceHeaderLocators.get(i).getText().toLowerCase().replace(":", "").trim(),
					expectedrePriceHeader[i].toLowerCase().trim(),
					"Price Header is not : " + expectedrePriceHeader[i].toLowerCase().trim() + ". It is : "
							+ priceHeaderLocators.get(i).getText().toLowerCase().replace(":", "").trim());
		}
		softAssert.assertAll();
		return this;
	}

	@Step("Click On Reorder Button.")
	public OrderDetailsPageObjects clickOnReorderButton() {
		waiting.waitForVisibilityOfElement(reOrderButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", reOrderButtonLocator);
		return this;

	}

	@Step("Click On Select All Checkbox.")
	public OrderDetailsPageObjects clickonSelectAllCheckbox() throws InterruptedException {
		waiting.waitForVisibilityOfElement(selectAllCheckBoxLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", selectAllCheckBoxLocator);
		Thread.sleep(1200);
		return this;
	}

	@Step("Click on My Account Breadcrumb link.")
	public OrderDetailsPageObjects clickOnMyAccountInBreadCrumb() throws InterruptedException {
		waiting.waitForVisibilityOfElement(myAccountLinkLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myAccountLinkLocator);
		Thread.sleep(1200);
		return this;

	}

}
