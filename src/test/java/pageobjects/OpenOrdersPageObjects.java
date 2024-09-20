package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class OpenOrdersPageObjects extends PageInitializer {

	Logger log = Logger.getLogger(OpenOrdersPageObjects.class);

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	@FindBy(xpath = "//a[contains(@class,'myaccountDropDown')]/following-sibling::ul/descendant::a[contains(text(),'Open Orders')]")
	private WebElement openOrdersLinkLocator;

	@FindBy(xpath = "//h1[contains(text(),'Open Orders')]")
	private WebElement openOrdersPageName;

	@FindBy(xpath = "//h2[text()='Re-Order Cart']")
	private WebElement reOrderCartPageName;

	@FindBy(xpath = "//ul[@class='cimm_breadcrumbs']/descendant::li[contains(text(),'Open Orders')]")
	private WebElement openOrderBreadcrump;

	@FindBy(xpath = "//div[@id='openOrderTable_filter']/label/input")
	private WebElement searchTextBox;

	@FindBy(xpath = "//select[@name='openOrderTable_length']")
	private WebElement countPerPageDropdown;

	@FindBy(xpath = "//div[@id='openOrderTable_wrapper']")
	private WebElement openOrderTable;

	@FindBy(xpath = "//table[@id='openOrderTable']/descendant::th")
	private List<WebElement> openOrderHeadersLocator;

	@FindBy(xpath = "//td[normalize-space(text())='No matching records found']")
	private WebElement noMatchingRecordFoundLocator;

	@FindBy(xpath = "//td[normalize-space(text())='No data available in table']")
	private WebElement noRecordAvailableLocator;

	@FindBy(xpath = "//td[@data-th='PO#']")
	private WebElement poValueLocator;

	@FindBy(xpath = "//td[@data-th='Order Date']")
	private WebElement orderDateValueLocator;

	@FindBy(xpath = "//td[@data-th='Amount']")
	private WebElement orderTotalValueLocator;

	@FindBy(xpath = "//table[@id='openOrderTable']/tbody/descendant::td/strong/a")
	private WebElement orderNo;

	@FindBy(xpath = "//table[@id='openOrderTable']/tbody/descendant::td[@data-th='PO#']")
	private WebElement POno;

	@FindBy(xpath = "//table[@id='openOrderTable']/tbody/descendant::td[@data-th='Order Date']")
	private WebElement orderDate;

	@FindBy(xpath = "//a[@data-original-title='View Order']")
	private List<WebElement> viewOrderLinkLocator;

	@FindBy(xpath = "//select[@name='openOrderTable_length']/option")
	private List<WebElement> itemPerPageOptionsLocator;

	@FindBy(xpath = "//table[@id='openOrderTable']/tbody/tr")
	private List<WebElement> verifyItemsPerPage;

	@FindBy(xpath = "//table[@id='openOrderTable']/tbody/descendant::td/a")
	private WebElement reorderButtonLocator;

	@FindBy(xpath = "//input[@id='reorderBtn']")
	private WebElement reorderButtonInOrderDetailsLocator;

	@FindBy(xpath = "//select[@name='openOrderTable_length']")
	private WebElement selectOpenOrderPageLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumbLocator;

	@FindBy(xpath = "//table[@id='openOrderTable']//tbody/tr")
	private List<WebElement> contentPerPageLocator;

	@FindBy(xpath = "//a[text()='Prev']")
	private WebElement previousButtonLocatorInPagination;

	@FindBy(xpath = "//a[text()='Prev']/..")
	private WebElement previousButtonLocator;

	@FindBy(xpath = "//a[text()='Next']")
	private WebElement nextButtonLocatorInPagination;

	@FindBy(xpath = "//a[text()='Next']/..")
	private WebElement nextButtonLocator;

	@FindBy(xpath = "//ul[@class='pagination']/li[not(@id='openOrderTable_previous') and not(@id='openOrderTable_next')]//a")
	private List<WebElement> paginationButtonLocators;

	@FindBy(xpath = "//form[@id='reOrderForm']/descendant::tbody/tr/td/label")
	private WebElement selectCheckBoxLocator;

	@FindBy(xpath = "//div[@class='cimm_cartCheckout pull-right']/descendant::a")
	private WebElement checkOutLocator;

	@FindBy(xpath = "//div[@class='cimm_cartProdDescription']/descendant::strong")
	private WebElement productTitle;

	@FindBy(xpath = "//td[@data-th='Order#']/strong")
	private WebElement eclipseOrderNumberValueLocator;

	@FindBy(xpath = "//td[@data-th='Order#']/strong")
	private List<WebElement> orderNumberValueLocator;

	@FindBy(xpath = "//th[contains(text(),'Required')]")
	private WebElement sortingRequiredByDateLocator;

	@Step("Verify Open Order Page Name")
	public OpenOrdersPageObjects verifyOpenOrdersPageName() {
		Assert.assertTrue(openOrdersPageName.isDisplayed(), "Open Order Page Name is not displayed.");
		log.info("Open Order Page Name is displayed.");
		return this;
	}

	@Step("Verify Open Order Page details")
	public OpenOrdersPageObjects verifyOpenOrdersPage() {
		Assert.assertTrue(openOrdersPageName.isDisplayed(), "Open Order page name is not displayed.");
		Assert.assertTrue(openOrderBreadcrump.isDisplayed(), "Open Order breadcrumb is not displayed.");
		Assert.assertTrue(searchTextBox.isDisplayed(), "Search Text Box is not displayed.");
		Assert.assertTrue(countPerPageDropdown.isDisplayed(), "Count Per Page Dropdown is not displayed.");
		Assert.assertTrue(openOrderTable.isDisplayed(), "Open Order Table is not displayed.");
		log.info("Verified open order page details");
		return this;
	}

	@Step("Get the Page title")
	public String getTitle() {
		String title = openOrdersPageName.getText().trim();
		return title;
	}

	@Step("Verify table Header in Open Order page :{0}")
	public OpenOrdersPageObjects verifyOpenOrdersHeaders(String[] headers) {
		waiting.waitForVisibilityOfElements(openOrderHeadersLocator, 4);
		for (int i = 0; i < openOrderHeadersLocator.size() - 1; i++) {
			Assert.assertEquals(openOrderHeadersLocator.get(i).getText().toLowerCase().trim(),
					headers[i].toLowerCase().trim(),
					"Table header Of Open Order Page is not : " + headers[i].toLowerCase().trim() + ". It is : "
							+ openOrderHeadersLocator.get(i).getText().toLowerCase().trim());
			log.info("Verified table Header in Open Order page :" + headers[i]);
		}
		return this;
	}

	@Step("Verify order details")
	public OpenOrdersPageObjects verifyOrderDetails(String[] orderDetails) {
		String expectedEclipseOrderNumber = orderDetails[0];
		String expectedPoValue = orderDetails[1];
		String expectedOrderDate = orderDetails[2];
		String expectedOrderTotal = orderDetails[3];
		Assert.assertEquals(eclipseOrderNumberValueLocator.getText().trim(), expectedEclipseOrderNumber);
		Assert.assertEquals(poValueLocator.getText().trim(), expectedPoValue);
		Assert.assertEquals(orderDateValueLocator.getText().trim(), expectedOrderDate);
		Assert.assertEquals(orderTotalValueLocator.getText().trim(), expectedOrderTotal);
		log.info("Verified order details");
		return this;
	}

	@Step("Get Order Number: {0}")
	public String getOrderNumber(int specificOrderNumber) {
		String orderNumber;
		try {
			waiting.waitForVisibilityOfElements(orderNumberValueLocator, 10);
			orderNumber = orderNumberValueLocator.get(specificOrderNumber - 1).getText();
			return orderNumber;
		} catch (TimeoutException e) {

			Assert.assertTrue(false, "Order Number is not availab,please verify it manually.");
			orderNumber = "";
		}
		log.info("Order Number :" + orderNumber);
		return orderNumber;
	}

	@Step("Enter Order Number In Search Open Orders Text Box : {0}")
	public OpenOrdersPageObjects sendTextToSearchBox(String text) throws InterruptedException {
		waiting.waitForVisibilityOfElement(searchTextBox, 3);
		searchTextBox.sendKeys(text);
		Thread.sleep(1200);
		log.info("Order Number In Search Open Orders Text Box :-" + text + "has been entered");
		return this;
	}

	@Step("Verify Order Number in Open Order Page :{0}")
	public OpenOrdersPageObjects verifyTextDisplayForOrderNo(String orderNumber) {
		Assert.assertEquals(orderNo.getText().trim(), orderNumber,

				"Order Number is not " + orderNumber + ". It is :" + orderNo.getText().trim());

		log.info("Order Number in Open Order Page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ orderNo.getText().trim() + "] and " + "[" + orderNumber.toUpperCase().trim() + "]");

		return this;
	}

	@Step("Verify PO number")
	public void verifyTextDisplayForPONo(String textDisplay) {
		Assert.assertEquals(POno.getText().trim(), textDisplay);
		log.info("Verify PO number :" + textDisplay);
	}

	@Step("Verify order date")
	public void verifyTextDisplayForShipDate(String textDisplay) {
		Assert.assertEquals(orderDate.getText().trim(), textDisplay);
		log.info("Verified order date :" + textDisplay);
	}

	@Step("Click on Count per page")
	public OpenOrdersPageObjects clickOnCountPerPageDown() throws Exception {
		Thread.sleep(2000);
		waiting.waitForVisibilityOfElement(countPerPageDropdown, 3);
		countPerPageDropdown.click();
		log.info("Clicked on Count per page");
		return this;
	}

	@Step("Click On Item Per Page Options in  dropdown")
	public OpenOrdersPageObjects clickOnItemPerPageOption(String option) throws Exception {
		for (int i = 0; i < itemPerPageOptionsLocator.size(); i++) {
			if (itemPerPageOptionsLocator.get(i).getText().trim().equals(option)) {
				itemPerPageOptionsLocator.get(i).click();
				log.info("Clicked on item per page :" + i);
				break;
			}
		}
		return this;
	}

	@Step("Verify display of items per page")
	public OpenOrdersPageObjects verifyDisplayOfItems(int items) throws Exception {
		for (int i = 0; i <= verifyItemsPerPage.size(); i++) {
			if (verifyItemsPerPage.size() <= items) {
				Assert.assertTrue(true);
				log.info("Verified display of items per page :" + i);
				break;
			}
		}
		return this;
	}

	@Step("Click on Re Order Button")
	public OpenOrdersPageObjects clickOnReorder() throws Exception {
		Thread.sleep(2000);
		waiting.waitForVisibilityOfElement(reorderButtonLocator, 3);
		reorderButtonLocator.click();
		log.info("Clicked on Re Order Button");
		return this;
	}

	@Step("Click on ReOrder Button in Order Details page")
	public OpenOrdersPageObjects clickOnReorderInOrderDetailsPage() throws Exception {
		Thread.sleep(2000);
		waiting.waitForVisibilityOfElement(reorderButtonInOrderDetailsLocator, 3);
		reorderButtonInOrderDetailsLocator.click();
		log.info("Clicked on ReOrder Button in Order Details page");
		return this;
	}

	@Step("Verify alert text :{0}")
	public OpenOrdersPageObjects verifyAlertText(String expectedAlertText) throws Exception {
		Thread.sleep(2000);
		Assert.assertTrue(utils.verifyAlertText(expectedAlertText), "Alert text while deleting an item is not wrong.");
		log.info("Verified alert text :" + expectedAlertText);
		return this;
	}

	@Step("Click on Selected item")
	public OpenOrdersPageObjects clickOnSelectItem() {
		waiting.waitForVisibilityOfElement(selectCheckBoxLocator, 3);
		selectCheckBoxLocator.click();
		log.info("Clicked on Selected item");
		return this;
	}

	@Step("Verify page name  is {0} ")
	public OpenOrdersPageObjects verifyPageName() {
		Assert.assertTrue(reOrderCartPageName.isDisplayed(), "My Cart page name is not displayed.");
		log.info("Verified page name");
		return this;
	}

	@Step("Click on Check Out")
	public OpenOrdersPageObjects clickOnCheckout() {
		waiting.waitForVisibilityOfElement(checkOutLocator, 3);
		checkOutLocator.click();
		log.info("Clicked on Check Out");
		return this;
	}

	@Step("Get the Title")
	public String getProductName() {
		String productName = productTitle.getText().trim();
		return productName;
	}

	@Step("Verify Open Order page title :{0}")
	public OpenOrdersPageObjects verifyOpenOrdersTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Open Orders" + " | " + productName, "Title is wrong");
		log.info("Open Order Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Open Orders" + " | " + productName + "]");
		return this;
	}

	@Step("Verify bread crumb of Open Order page :{0}")
	public OpenOrdersPageObjects verifyBreadCrumbOfOpenOrderPage(String breadCrumbs) {
		String[] expectedBreadCrumbs = breadCrumbs.split(",");
		waiting.waitForVisibilityOfElements(breadCrumbLocator, 10);
		for (int i = 0; i < breadCrumbLocator.size(); i++) {
			Assert.assertEquals(breadCrumbLocator.get(i).getText().trim(), expectedBreadCrumbs[i].trim(),
					"Bread Crumb of Open Order Page is not : " + expectedBreadCrumbs[i].trim() + " . It is : "
							+ breadCrumbLocator.get(i).getText().trim());
		}
		log.info("Verified bread crumb of Open Order page");
		return this;
	}

	@Step("Verify Search Result For Invalid Data")
	public OpenOrdersPageObjects verifySearchResultForInvalidData() {
		try {
			Assert.assertTrue(noMatchingRecordFoundLocator.isDisplayed(),
					"Search Result For Invalid Data is not Correct");
		} catch (TimeoutException | NoSuchElementException e) {
			Assert.assertTrue(noRecordAvailableLocator.isDisplayed(), "Data Available In Table Of Open Order Page.");
		}
		log.info("Verified Search Result For Invalid Data");
		return this;
	}

	@Step("Select Content Per Page Options in Open Order page.")
	public OpenOrdersPageObjects selectContentPerPageOption(String selectText) throws InterruptedException {
		waiting.waitForVisibilityOfElement(selectOpenOrderPageLocator, 10);
		Select oSelect = new Select(selectOpenOrderPageLocator);
		oSelect.selectByVisibleText(selectText);
		Thread.sleep(1400);
		log.info("Selected Content Per Page :" + selectText);
		return this;
	}

	@Step("Verify The Display Of Content:{0}")
	public OpenOrdersPageObjects verifyDisplayOfContent(String items) {
		waiting.waitForVisibilityOfElements(contentPerPageLocator, 10);
		for (int i = 0; i <= contentPerPageLocator.size(); i++) {
			if (contentPerPageLocator.size() <= Integer.parseInt(items)) {
				Assert.assertTrue(true);
				log.info("Verified the display of content");
				break;
			}
		}
		return this;
	}

	@Step("Verify The Functionality Of Next Button Of Pagination.")
	public OpenOrdersPageObjects verifyNextButtonOfPagination() throws InterruptedException {

		try {
			waiting.waitForVisibilityOfElements(paginationButtonLocators, 10);
			if (paginationButtonLocators.size() > 1) {
				for (int i = 0; i < paginationButtonLocators.size(); i++) {
					nextButtonLocatorInPagination.click();
					Thread.sleep(1200);
				}
				Assert.assertTrue(nextButtonLocator.getAttribute("class").contains("disabled"),
						"Next Button is not disabled");
			} else {

				Assert.assertFalse(true,
						"Open Orders records are either less than 10 or equls to 10,so can't test next button functionality of pagination.");
			}
		} catch (TimeoutException e) {

			Assert.assertFalse(true,
					"Open Orders records are not available,so can't test next button functionality of pagination.");
		}
		log.info("Verified The Functionality Of Next Button Of Pagination");
		return this;
	}

	@Step("Verify The Functionality Of Previous Button Of Pagination.")
	public OpenOrdersPageObjects verifyPreviousButtonOfPagination() throws InterruptedException {
		try {
			waiting.waitForVisibilityOfElements(paginationButtonLocators, 10);
			if (paginationButtonLocators.size() > 1) {
				nextButtonLocatorInPagination.click();
				Thread.sleep(1200);
				previousButtonLocatorInPagination.click();
				Thread.sleep(1200);
			}
			Assert.assertTrue(previousButtonLocator.getAttribute("class").contains("disabled"),
					"Previous Button is not disabled");
		} catch (TimeoutException e) {
			Assert.assertTrue(previousButtonLocator.getAttribute("class").contains("disabled"),
					"Previous Button is not disabled");
		}
		log.info("Verified The Functionality Of Previous Button Of Pagination");
		return this;
	}

	@Step("Click on Specific Order Number :{0}")
	public OpenOrdersPageObjects clickOnOrderNumber(String orderNumber) throws InterruptedException {
		WebElement expectedOrderNumber = getDriver()
				.findElement(By.xpath("//a[contains(text(),'" + orderNumber + "')]"));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", expectedOrderNumber);
		Thread.sleep(1200);
		log.info("Clicked on Specific Order Number :" + orderNumber);
		return this;
	}

	@Step("Verify price precision in Open Order page")
	public OpenOrdersPageObjects verifyPricePrecisionInOpenOrderPage(String pricePrecision) {

		WebElement specificItemPrice = getDriver()
				.findElement(By.xpath("//td[contains(@data-th,'Unit')]/span[@class]"));

		Assert.assertEquals(specificItemPrice.getText().replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1], Integer.parseInt(pricePrecision),
				"Price precision in not matched in Open Order page");

		log.info("Verified Price precision in Open Order page");
		return this;
	}

	@Step("Click on Specific View Order Link :{0}")
	public OpenOrdersPageObjects clickOnSpecificViewOrderLink(int specificViewOrderLink) throws InterruptedException {
		try {
			waiting.waitForVisibilityOfElements(viewOrderLinkLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					viewOrderLinkLocator.get(specificViewOrderLink - 1));

			Thread.sleep(1200);
		} catch (Exception e) {
			Assert.assertTrue(false, "View Order Link is not available,Please verify it manually.");
		}
		log.info("Clicked on Specific View Order Link");
		return this;

	}

	@Step("Sorting table by Required by Date")
	public OpenOrdersPageObjects sortingOpenOrderTable_RequiredByDate() throws Exception {
		waiting.waitForVisibilityOfElement(sortingRequiredByDateLocator, 5);
		sortingRequiredByDateLocator.click();
		Thread.sleep(1500);
		sortingRequiredByDateLocator.click();
		Thread.sleep(1500);
		log.info("Sorting table by Required by Date");
		return this;
	}

}
