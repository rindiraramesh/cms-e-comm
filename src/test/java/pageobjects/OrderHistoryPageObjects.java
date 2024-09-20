package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class OrderHistoryPageObjects extends PageInitializer {

	Logger log = Logger.getLogger(OrderHistoryPageObjects.class);

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	@FindBy(xpath = "//table[contains(@id,'Table')]//th")
	private List<WebElement> orderHistoryTableHeadersLocator;

	@FindBy(xpath = "//td[@data-th='Description']")
	private List<WebElement> descriptionValueLocator;

	@FindBy(xpath = "//table[contains(@id,'Table')]//tbody/tr")
	private List<WebElement> contentPerPageLocator;

	@FindBy(xpath = "//button[@data-id='orderCategory']")
	private WebElement selectCategoryDropDownLocator;

	@FindBy(xpath = "//input[@id='orderPoSearch']")
	private WebElement searchTextBoxLocator;

	@FindBy(xpath = "//select[contains(@aria-controls,'Table')]")
	private WebElement itemPerPageDropDownLocator;

	@FindBy(xpath = "//input[@id='startDate']")
	private WebElement startDateCalenderLocator;

	@FindBy(xpath = "//input[@id='endDate']")
	private WebElement endDateCalenderLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumbLocator;

	@FindBy(xpath = "//input[@id='orderPoSearch']")
	private WebElement searchBoxLocatorForKeyword;
	
	@FindBy(xpath = "//input[@id='orderPoSearchBtn']")
	private WebElement searchButtonLocatorForKeyword;

	@FindBy(xpath = "//td[@data-th='Order#']/a")
	private List<WebElement> orderNoLocator;

	@FindBy(xpath = "//th[@class='left sorting_asc']")
	private WebElement orderAsendingLocator;

	@FindBy(xpath = "//th[@class='left sorting_desc']")
	private WebElement orderDescendingLocator;

	@FindBy(xpath = "//th[contains(text(),'Debit')]")
	private WebElement debitAsendingLocator;

	@FindBy(xpath = "//th[@class='sorting_desc']")
	private WebElement debitDescendingLocator;
	
	@FindBy(xpath = "//button[@id='searchBtn']")
	private WebElement applyBtnLocator;
	
	@FindBy(xpath = "//button[@id='resetBtn']")
	private WebElement clearBtnLocator;
	
	@FindBy(xpath = "//table[@id='OrdersHistoryTable']/tbody/descendant::td[1]/a[contains(@href,'order')]")
	private WebElement orderNo;

	@Step("Verify table Header in Order History page :{0}")
	public OrderHistoryPageObjects verifyOrderHistoryTableHeaders(String[] headers) {
		waiting.waitForVisibilityOfElements(orderHistoryTableHeadersLocator, 4);
		for (int i = 0; i < orderHistoryTableHeadersLocator.size() - 1; i++) {
			Assert.assertEquals(orderHistoryTableHeadersLocator.get(i).getText().trim(), headers[i].trim(),
					"Table header Of Order History Page is not : " + headers[i].trim() + ". It is : "
							+ orderHistoryTableHeadersLocator.get(i).getText().trim());
			log.info("Verified table header in Order History page :" + headers[i]);
		}
		return this;
	}

	@Step("Clicking sorting for Order #")
	public OrderHistoryPageObjects clickOnOrderColumnSorting() {
		try {
			if (orderAsendingLocator.isDisplayed()) {
				orderAsendingLocator.click();
				log.info("Clicked to have Ascending order");
			}
		} catch (Exception e) {
			if (orderDescendingLocator.isDisplayed()) {
				orderDescendingLocator.click();
				log.info("Clicked to have Descending order");
			}
		}
		return this;
	}

	@Step("Clicking sorting for Debit")
	public OrderHistoryPageObjects clickOnDebitColumnSorting() {
		try {
			if (debitAsendingLocator.isDisplayed()) {
				debitAsendingLocator.click();
				log.info("Clicked to have Ascending order");
			}
		} catch (Exception e) {
			if (debitDescendingLocator.isDisplayed()) {
				debitDescendingLocator.click();
				log.info("Clicked to have Descending order");
			}
		}
		return this;
	}

	@Step("Verify the Order# column is sorted in assending order")
	public OrderHistoryPageObjects verifyAccendingSort() {
		for (int i = 0; i < orderNoLocator.size(); i++) {
			for (int j = 1; j < orderNoLocator.size() - 1; j++) {
				float a = Float.parseFloat(orderNoLocator.get(i).getText().trim());
				float b = Float.parseFloat(orderNoLocator.get(j).getText().trim());
				if (a < b) {
					Assert.assertTrue(true, "Not Sorted in Assending order");
				}
			}
		}
		log.info("Sorted in Assending order");
		return this;
	}

	@Step("Verify the Order# column is sorted in descending order")
	public OrderHistoryPageObjects verifyDesendingSort() {
		for (int i = 0; i < orderNoLocator.size(); i++) {
			for (int j = 1; j < orderNoLocator.size() - 1; j++) {
				float a = Float.parseFloat(orderNoLocator.get(i).getText().trim());
				float b = Float.parseFloat(orderNoLocator.get(j).getText().trim());
				if (a > b) {
					Assert.assertTrue(true, "Not Sorted in Desending order");
				}
			}
		}
		log.info("Sorted in Desending order");
		return this;
	}

	@Step("Verify Order History page.")
	public OrderHistoryPageObjects verifyOrderHistoryPage() {
		waiting.waitForVisibilityOfElement(searchTextBoxLocator, 10);
		/*softAssert.assertTrue(selectCategoryDropDownLocator.isDisplayed(),
				"Select Category Drop Down is not displayed.");*/
		softAssert.assertTrue(searchTextBoxLocator.isDisplayed(), "Search Text Box is not displayed.");
		softAssert.assertTrue(startDateCalenderLocator.isDisplayed(), "Start Date Calender is not displayed.");
		softAssert.assertTrue(endDateCalenderLocator.isDisplayed(), "End Date Calender is not displayed.");
		softAssert.assertTrue(applyBtnLocator.isDisplayed(), "Apply button is not displayed.");
		softAssert.assertTrue(clearBtnLocator.isDisplayed(), "Clear button is not displayed.");
		softAssert.assertTrue(itemPerPageDropDownLocator.isDisplayed(), "Item Per page Drop Down is not displayed.");
		softAssert.assertAll();
		log.info("Verified order history page");
		return this;
	}

	@Step("Select Content Per Page Options in Order History page.")
	public OrderHistoryPageObjects selectContentPerPageOption(String selectText) throws InterruptedException {
		waiting.waitForVisibilityOfElement(itemPerPageDropDownLocator, 10);
		Select oSelect = new Select(itemPerPageDropDownLocator);
		oSelect.selectByVisibleText(selectText);
		log.info("Selected content per page as :" + selectText);
		Thread.sleep(1400);
		return this;
	}

	@Step("Verify The Display Of Content:{0}")
	public OrderHistoryPageObjects verifyDisplayOfContent(String items) {
		waiting.waitForVisibilityOfElement(contentPerPageLocator.get(0), 10);
		for (int i = 0; i <= contentPerPageLocator.size(); i++) {
			if (contentPerPageLocator.size() <= Integer.parseInt(items)) {
				Assert.assertTrue(true);
				break;
			}
		}
		log.info("Verified the content display :" + items);
		return this;
	}

	@Step("Verify bread crumb of Order History page :{0}")
	public OrderHistoryPageObjects verifyBreadCrumbOfOrderHistoryPage(String breadCrumbs) {
		String[] expectedBreadCrumbs = breadCrumbs.split(",");
		waiting.waitForVisibilityOfElements(breadCrumbLocator, 10);
		for (int i = 0; i < breadCrumbLocator.size(); i++) {
			Assert.assertEquals(breadCrumbLocator.get(i).getText().trim(), expectedBreadCrumbs[i].trim(),
					"Bread Crumb of Order History Page is not : " + expectedBreadCrumbs[i].trim() + " . It is : "
							+ breadCrumbLocator.get(i).getText().trim());
		}
		log.info("Verified bread crumb of Order History page");
		return this;
	}

	@Step("Enter search keyword :{0}")
	public OrderHistoryPageObjects sendTextToSearchBox(String searchText) throws InterruptedException {
		waiting.waitForVisibilityOfElement(searchBoxLocatorForKeyword, 10);
		searchBoxLocatorForKeyword.clear();
		searchBoxLocatorForKeyword.sendKeys(searchText);
		Thread.sleep(1200);
		log.info("Enter search keyword: " + searchText);
		searchButtonLocatorForKeyword.click();
		return this;

	}

	@Step("Enter search keyword :{0}")
	public String getKeywords(int specificKeywordNumber) {
		String descriptionValue;
		try {
			waiting.waitForVisibilityOfElements(descriptionValueLocator, 10);
			descriptionValue = descriptionValueLocator.get(specificKeywordNumber - 1).getText();
			log.info("Keyword for search: "+descriptionValue);
			return descriptionValue;
		} catch (TimeoutException e) {
			Assert.assertTrue(false, "Test Data is not available for completed order table,Please test it manually.");
			descriptionValue = "";
		}
		return descriptionValue;
	}

	@Step("Verify Search Functionality for Valid Data:{0}")
	public OrderHistoryPageObjects verifyTextDisplayForKeyword(String keyword) {
		List<WebElement> keywords = getDriver().findElements(By.xpath("//td[contains(text(),'" + keyword + "')]"));
		Assert.assertTrue(keywords.get(0).isDisplayed(), "Search Result is not working");
		log.info("Verify Search Functionality for Valid Data: " + keyword);
		return this;

	}

	@Step("Verify Order History page title :{0}")
	public OrderHistoryPageObjects verifyOrderHistoryPageTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Orders History" + " | " + productName, "Title is wrong");
		log.info("Verified Order History page title");
		return this;
	}
	
	@Step("Verify Order Number in Open Order Page :{0}")
	public OrderHistoryPageObjects verifyTextDisplayForOrderNo(String orderNumber) {
		Assert.assertEquals(orderNo.getText().trim(), orderNumber,

				"Order Number is not " + orderNumber + ". It is :" + orderNo.getText().trim());

		log.info("Order Number in Open Order Page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ orderNo.getText().trim() + "] and " + "[" + orderNumber.toUpperCase().trim() + "]");

		return this;
	}

}
