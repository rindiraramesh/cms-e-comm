package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class SelectShippingToProceedObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods util = new UtilityMethods(getDriver());

	public TestDataPropertyFile data = new TestDataPropertyFile();

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchFieldLocator;

	@FindBy(xpath = "//a[text()='Previous']")
	private WebElement previousButtonLocator;

	@FindBy(xpath = "//div[@id='example_paginate']/ul/li[3]/a")
	private WebElement nextButtonLocator;

	@FindBy(xpath = "//a[contains(text(),'Refresh')]")
	private WebElement refreshShippingInformationButtonLocator;

	@FindBy(xpath = "//select[@name='example_length']")
	private WebElement showEntriesDropdownSelector;

	@FindBy(xpath = "//div[@id='example_paginate']/ul/li[2]/a[text()='2']")
	private WebElement paginationButtonLocator;

	@FindBy(xpath = "//*[@id='example']/tbody/tr[1]/td[1]/label/span")
	private WebElement selectAddress1;

	@FindBy(xpath = "//*[@id='example']/tbody/tr[2]/td[1]/label/span")
	private WebElement selectAddress2;

	@FindBy(id = "selectButton")
	private WebElement useSelectedAddressButton;

	@FindBy(xpath = "//div[contains(text(),'Thank you')]")
	private WebElement msgPopupLocator;

	@FindBy(css = ".bootbox-body")
	private WebElement successPopupLocator;

	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okButtonLocator;

	@Step("Selected 1st shipping address")
	public HomePageObjects selectingShippingAddress1() {
		waiting.waitForVisibilityOfElement(selectAddress1, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", selectAddress1);
		logger.info("clicked on UseSelectedAddress button");
		return homePage();
	}

	@Step("Selected 2nd shipping address")
	public HomePageObjects selectingShippingAddress2() throws InterruptedException {
		waiting.waitForVisibilityOfElement(selectAddress2, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", selectAddress2);
		Thread.sleep(600);
		logger.info("clicked on UseSelectedAddress button");
		return homePage();
	}

	@Step("Click on Refresh Shipping Information link")
	public SelectShippingToProceedObjects clickOnRefreshShippingInformation() {

		waiting.waitForElementToBeClickable(refreshShippingInformationButtonLocator, 5);
		refreshShippingInformationButtonLocator.click();
		logger.info("clicked on Refresh Shipping Information link");
		return this;
	}

	@Step("Get all shipping address")
	public List<String> getAllShippigAddressSelectShipping() {
		logger = Logger.getLogger("getAllShippigAddressSelectShipping");
		waiting.waitForVisibilityOfElement(selectAddress1, 10);
		logger = Logger.getLogger("clickshippingaddress1Link");
		ArrayList<String> AllShippingAddress = new ArrayList<String>();

		String row_start = "//*[@id='example']/tbody/tr[";
		String row_end = "]/td[1]/label/span";
		int x = 1;
		int rownum = 0;
		while (util.isElementDisplayed((row_start + x + row_end))) {
			x++;
			rownum++;
		}
		String xp_start = "//*[@id='example']/tbody/tr[";
		String xp_mid = "]/td[";
		String xp_end = "]";
		for (int rows = 1; rows <= rownum; rows++) {
			for (int cols = 2; cols <= 5; cols++) {
				String address = (getDriver().findElement(By.xpath(xp_start + rows + xp_mid + cols + xp_end))
						.getText());
				AllShippingAddress.add(address);
			}
		}
		// logger.info("search for all shipping address is completed");
		return AllShippingAddress;
	}

	@Step("Search for required shipping address")
	public List<String> getSearchedAddess(String searchData) {
		logger = Logger.getLogger("getSearchedAddess");
		waiting.waitForVisibilityOfElement(searchFieldLocator, 500);
		searchFieldLocator.sendKeys(searchData);
		List<String> address = getAllShippigAddressSelectShipping();
		logger.info("searched shipping address is completed for :" + searchData);
		searchFieldLocator.clear();
		return address;
	}

	@Step("Verify searched shipping address with in all shipping address")
	public SelectShippingToProceedObjects verifySearchAddressWithAllShippingAddress() throws Exception {
		logger = Logger.getLogger("verifySearchAddressWithAllShippingAddress");
		SoftAssert softAssert = new SoftAssert();
		String[] searchAddress = data.getSearchShippingAddress().split(">");

		for (int j = 0; j < searchAddress.length; j++) {

			List<String> searchedAddress1 = getSearchedAddess(searchAddress[j]);

			softAssert.assertTrue(getAllShippigAddressSelectShipping().containsAll(searchedAddress1),
					"Searched Address is not found :" + searchAddress[j]);

			/*
			 * String searchedAddress = getSearchedAddess(searchAddress[j]).toString();
			 * 
			 * for (int i = 0; i < getAllShippigAddressSelectShipping().size(); i++) { //
			 * System.out.println(searchedAddress.contains(
			 * getAllShippigAddressSelectShipping().get(i).toString()));
			 * softAssert.assertTrue(searchedAddress.contains(
			 * getAllShippigAddressSelectShipping().get(i).toString()));
			 */
		}
		softAssert.assertAll();
		logger.info("searched shipping address is verified with all shipping address completed");
		return this;
	}

	@Step("Clear the search field")
	public SelectShippingToProceedObjects clearSearchField() {
		logger = Logger.getLogger("clearSearchField");
		searchFieldLocator.clear();
		logger.info("Search field is cleared");
		return this;
	}

	@Step("Verify Success message")
	public SelectShippingToProceedObjects verifyPopupOnclickOnRefreshShippingInformation() throws Exception {
		// now
		logger = Logger.getLogger("VerifyPopupOnclickOnRefreshShippingInformation");
		String expected = "Thank you for your patience while your Ship To/Jobs list is being refreshed.";

		Thread.sleep(3000);
		waiting.waitForVisibilityOfElement(msgPopupLocator, 10);
		util.moveToElement(msgPopupLocator);

		Assert.assertEquals(msgPopupLocator.getText().trim(), expected);
		logger.info("Alert text is verified after clicked on Refresh Shipping Information link");
		return this;
	}

	@Step("Verify Success message")
	public SelectShippingToProceedObjects verifySuccessMessageAfterclickOnRefreshShippingInformation()
			throws Exception {
		logger = Logger.getLogger("VerifyPopupOnclickOnRefreshShippingInformation");
		waiting.waitForVisibilityOfElement(successPopupLocator, 10);
		// Thread.sleep(2500);
		Assert.assertEquals(successPopupLocator.getText().trim(),
				"Thank you for your patience while your Ship To/Jobs list is being refreshed.",
				"Success message is not displayed");
		logger.info("verified Success message after clicked on Refresh Shipping Information link");
		return this;
	}

	@Step("Accept alert after clicked on Refresh Shipping Information link")
	public SelectShippingToProceedObjects acceptAlertAfterclickOnRefreshShippingInformation() {
		logger = Logger.getLogger("VerifyPopupOnclickOnRefreshShippingInformation");
		okButtonLocator.click();
		logger.info("clicked on OK button");
		return this;
	}

	@Step("Click on pagination ")
	public SelectShippingToProceedObjects clickOnPagination() {
		logger = Logger.getLogger("clickOnPagination");
		util.moveToElement(paginationButtonLocator);
		paginationButtonLocator.click();
		logger.info("clicked on 2nd page");
		return this;
	}

}
