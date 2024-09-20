package pageobjects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class MyAccountsPageObjects extends PageInitializer {
	public TestDataPropertyFile data = new TestDataPropertyFile();

	SoftAssert softAssert = new SoftAssert();

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	@FindBy(xpath = "//h1[text()='My Account']")
	private WebElement myAccountPageName;

	@FindBy(xpath = "//h1[contains(@class,'pageTitle')]")
	private WebElement landingPageName;

	@FindBy(xpath = "//ul[@class='breadcrumb']/descendant::li[contains(text(),'My Account')]")
	private WebElement myAccountBreadcrumb;

	@FindBy(xpath = "//h3[@data-hashid='#Address']")
	private WebElement addressTab;

	@FindBy(xpath = "//input[@value='Upload']")
	public WebElement uploadButtonLocator;

	@FindBy(xpath = "//table[@id='DataTables_Table_1']/thead//th")
	private List<WebElement> shipAddressListHeaderLocators;

	@FindBy(xpath = "//div[contains(@class,'userInfo')]//strong")
	private List<WebElement> addressSectionInProfileLabelTabLocator;

	@FindBy(xpath = "//h3[@data-hashid='#profile']")
	private WebElement profileTab;

	@FindBy(xpath = "//h3[@data-hashid='#groups']")
	private WebElement groupsTab;

	@FindBy(xpath = "//div[@id='openOrderTable']/descendant::td/a")
	private List<WebElement> viewOrderButtonsLocatorOfOpenOrder;

	@FindBy(xpath = "//h3[@data-hashid='#order']")
	private WebElement ordersTab;

	@FindBy(xpath = "//label[text()='Recently Ordered Items']")
	private WebElement recentlyOrderedItemsHeading;

	@FindBy(id = "recentorders")
	private WebElement recentlyOrderedItemsList;

	@FindBy(id = "profile")
	private WebElement profileSection;

	@FindBy(xpath = "//div[@id='profile']/descendant::a[@href='/EditContactInfo']")
	private WebElement editContactInfoLink;

	@FindBy(xpath = "//input[@id='profileImage']")
	private WebElement profileUploadLinkLocator;

	@FindBy(xpath = "//h3[contains(text(),'Billing Address')]/descendant::a")
	private WebElement editBillingAddressLocator;

	@FindBy(xpath = "//h4//span[@id='divLocal']")
	private WebElement lastLoginDateTimeYearLocator;

	@FindBy(xpath = "//a[@data-original-title='Refresh Shipping Information']")
	private WebElement refreshShippingIconLocator;

	@FindBy(xpath = "//td[normalize-space(text())='No matching records found']")
	private WebElement invalidSearchLocator;

	@FindBy(xpath = "//input[@value='Clear']")
	public WebElement clearButtonLocator;

	@FindBy(xpath = "//div[@id='groups']//a[@href='/SavedGroups/Products']")
	public WebElement myProductGroupViewMoreLocator;

	@FindBy(xpath = "//div[@id='groups']//a[@href='/SavedGroups/Cart']")
	public WebElement savedCartViewMoreLocator;

	@FindBy(xpath = "//div[@class='profileImage']/descendant::img[@id='profilePicture']")
	private WebElement profilePicLocator;

	@FindBy(xpath = "//div[@id='orderHistory']//td/a[@title='View Order']")
	private List<WebElement> viewOrderButtonsLocatorOfCompletedOrder;

	@FindBy(xpath = "//div[@id='openOrderTable']//td[@data-th='Order#']//a")
	private List<WebElement> orderNumberLocator;

	@FindBy(name = "shipFirstName")

	private WebElement addNewShippingAddressFirstNameLocator;

	@FindBy(name = "shipLastName")
	private WebElement addNewshippingAddressLastNameLocator;

	@FindBy(name = "address1")
	private WebElement addNewShippingAddressAddress1Locator;

	@FindBy(name = "address2")
	private WebElement addNewShippingAddressAddress2Locator;

	@FindBy(name = "city")
	private WebElement addNewShippingAddressCityLocator;

	@FindBy(id = "stateSelectShip")
	private WebElement addNewShippingAddressSelectStateLocator;

	@FindBy(id = "zip")
	private WebElement addNewShippingAddressZipCodeLocator;

	// Ujwal
	@FindBy(xpath = "//*[@id='Address']/div[1]/div[2]/p/span[1]")
	private WebElement shippingAddressNameLocator;

	@FindBy(css = "#Address>div:first-child>div:last-child>p>span:nth-child(3)")
	private WebElement shippingAddressLane1Locator;

	@FindBy(css = "#Address>div:first-child>div:last-child>p>span:nth-child(5)")
	private WebElement shippingAddressLane2Locator;

	@FindBy(css = "#Address>div:first-child>div:last-child>p>span:nth-child(7)")
	private WebElement shippingAddressStateLocator;

	@FindBy(css = "#Address>div:first-child>div:last-child>p>span:nth-child(9)")
	private WebElement shippingAddressZipLocator;

	/*
	 * @FindBy(css = ".fa.fa-lg.fa-refresh") private WebElement
	 * refreshShippingInformationButtonLocator;
	 */

	@FindBy(xpath = "//a[@data-original-title='Refresh Shipping Information']")
	private WebElement refreshShippingInformationButtonLocator;

	@FindBy(xpath = "//div[contains(text(),'Thank you')]")
	private WebElement msgPopupLocator;

	@FindBy(css = ".bootbox-body")
	private WebElement successPopupLocator;

	@FindBy(xpath = "//button[text()='OK']")
	private WebElement okButtonLocator;

	// @FindBy(xpath = "(//input[@type='search'])[2]")
	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchFieldLocator;

	// Ujwal
	@FindBy(xpath = "//h3[normalize-space(text())='Shipping Address']")
	private WebElement shippingAddressEditIconLocator;

	@FindBy(xpath = "//h3[normalize-space(text())='Billing Address']")
	private WebElement billingAddressEditIconLocator;

	/*
	 * @FindBy(xpath = "//form[@method='get']//a[normalize-space(text())='Edit']")
	 * private WebElement billingAddressEditIconLocator;
	 */

	@FindBy(name = "shipPhone")
	private WebElement addNewShippingAddressPhoneLocator;

	@FindBy(id = "emailAddress")
	private WebElement addNewShippingAddressEmailAddressLocator;

	@FindBy(name = "shipToId")
	private WebElement addNewShippingAddressShipToLocator;

	@FindBy(xpath = "//button[contains(text(),'Add Address')]")
	private WebElement addNewShippingAddressFormButtonLocator;

	@FindBy(xpath = "//a[contains(text(),'Add Shipping Address')]")
	private WebElement addNewShippingAddressButtonLocator;

	@FindBy(xpath = "//h3[text()='My Saved Carts']/ancestor::div[@class='accountDash cimm_halfBlockColumns']/descendant::a[contains(text(),'View More')]")
	private WebElement viewMoreLocator;

	@FindBy(xpath = "//h3[contains(text(),'user contact')]")
	private WebElement userContactLocator;

	@FindBy(xpath = "//div[@class='userInfo']/p/span")
	private WebElement addressSectionInProfileTabLocator;

	@FindBy(xpath = "//div[@id='profile']//h4")
	private WebElement lastLoginLocator;

	@FindBy(xpath = "//div[contains(@class,'cimm_headerRight')]/descendant::ul[@class='cimm_myAccountMenu']/li/a")
	private List<WebElement> myAcountDropdownLinksLocator;

	@FindBy(xpath = "//div[@id='Address']/descendant::h3[contains(text(),'Billing Address')]/following-sibling::p")
	private WebElement billingAddressLocator;

	/*
	 * @FindBy(xpath =
	 * "//div[@id='Address']/descendant::h3[contains(text(),'Billing Address1')]/following-sibling::p"
	 * ) private WebElement billingAddressLocator;
	 */

	@FindBy(xpath = "//div[@id='Address']/descendant::h3[contains(text(),'Shipping Address')]/following-sibling::p")
	private WebElement shippingAddressLocator;

	@FindBy(id = "profile")
	private WebElement profileSectionLocator;

	@FindBy(xpath = "//th[normalize-space(text())='Address']/ancestor::thead/following-sibling::tbody/tr/td[2]")
	private List<WebElement> addressNameLocator;

	@FindBy(xpath = "//div[@id='ship_table_length']/label/select")
	private WebElement showDropdownLocator;

	// @FindBy(xpath = "(//input[@placeholder='Search Ship Address'])[2]")
	@FindBy(xpath = "//input[@placeholder='Search Ship Address']")
	private WebElement searchShipAddressIcon;

	@FindBy(xpath = "//table[@id='ship_table']/tbody/tr[@role='row']")
	private WebElement shipAddressListDetails;

	@FindBy(xpath = "//div[@id='ship_table_paginate']/ul[@class='pagination']")
	private WebElement paginationLocator;

	/*
	 * @FindBy(xpath =
	 * "//div[@id='DataTables_Table_0_paginate']/ul[@class='pagination1']") private
	 * WebElement paginationLocator;
	 */

	@FindBy(xpath = "//td[text()='Unilog']")
	private WebElement companyNameLocator;

	@FindBy(xpath = "//td[text()='No matching records found']")
	private WebElement noMatchingRecordLocator;

	@FindBy(xpath = "//div[@id='ship_table_length']/label/select")
	private WebElement itemsPerPageLocator;

	@FindBy(xpath = "//select[@name='ship_table_length']/option")
	private List<WebElement> itemPerPageOptionsLocator;

	@FindBy(xpath = "//table[@id='ship_table']/tbody/tr[@role='row']")
	private List<WebElement> verifyItemsPerPage;

	@FindBy(xpath = "//div[@id='openOrderTable']//a[normalize-space(text())='View All']")
	private WebElement viewAllUnderOpenOrdersLocator;

	@FindBy(xpath = "//div[contains(@id,'orderHistory')]//following::a[contains(.,'View All')]")
	private WebElement viewAllUnderCompletedOrdersLocator;

	@FindBy(xpath = "//div[@id='openOrderTable']/descendant::th")
	private List<WebElement> openOrderHeadersLocator;

	@FindBy(xpath = "//div[@id='orderHistory']/descendant::th")
	private List<WebElement> completedOrderHeadersLocator;

	@Step("Verify my accounts page")
	public MyAccountsPageObjects verifyMyAccountPage() {
		logger = Logger.getLogger("verifyMyAccountPage");
		softAssert.assertTrue(myAccountPageName.isDisplayed(), "My Account page name is not displayed.");
		logger.info("My Account page name is displayed.");
		softAssert.assertTrue(myAccountBreadcrumb.isDisplayed(), "My Account breadcrumb name is not displayed.");
		logger.info("My Account breadcrumb name is displayed.");
		softAssert.assertTrue(profileTab.getAttribute("class").contains("active"), "Profile tab is not enabled first.");
		logger.info("Profile tab is enabled first.");
		softAssert.assertTrue(addressTab.getAttribute("class").equals(""),
				"Address tab is not enabled first.Address tab is " + addressTab.getAttribute("class"));
		logger.info("Address tab is enabled first.");
		softAssert.assertTrue(groupsTab.getAttribute("class").equals(""), "Groups tab is not enabled first.");
		logger.info("Group tab is enabled first.");
		softAssert.assertTrue(ordersTab.getAttribute("class").equals(""), "Orders tab is not enabled first.");
		logger.info("Orders tab is enabled first.");
		softAssert.assertTrue(profileSection.isDisplayed(), "Profile Section is not displayed.");
		logger.info("Profile Section is displayed.");
		softAssert.assertAll();
		return this;
	}

	@Step("Click on edit contact info")
	public EditContactInfoPageObjects clickOnEditContactInfo() throws InterruptedException {
		logger = Logger.getLogger("clickOnEditContactInfo");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", editContactInfoLink);
		Thread.sleep(1200);
		logger.info("clicked on edit contact info link.");
		return editContactInfoPage();
	}

	@Step("Upload file whose file path is {0}")
	public MyAccountsPageObjects uploadFile(String filepath) {
		logger = Logger.getLogger("uploadFile");
		File file = new File(filepath);
		profileUploadLinkLocator.sendKeys(file.getAbsolutePath());
		logger.info("Uploaded file.");
		return this;
	}

	@Step("Click on address tab.")
	public MyAccountsPageObjects clickOnAddressTab() throws Exception {
		logger = Logger.getLogger("clickOnAddressTab");
		Thread.sleep(3000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addressTab);
		logger.info("clicked on address tab.");
		return this;
	}

	@Step("Click on edit billing address")
	public MyAccountsPageObjects clickOnEditBillAddress() {
		waiting.waitForVisibilityOfElement(editBillingAddressLocator, 10);
		editBillingAddressLocator.click();
		return this;
	}

	@Step("Click on add new shipping address")
	public MyAccountsPageObjects clickOnAddNewShippingAddress() {
		addNewShippingAddressButtonLocator.click();
		return this;
	}

	@Step("Click on add address button")
	public MyAccountsPageObjects clickOnAddAddressButton() {
		addNewShippingAddressFormButtonLocator.click();
		return this;
	}

	@Step("Enter first name {0}")
	public MyAccountsPageObjects enterFirstName(char generateCharacters) {
		addNewShippingAddressFirstNameLocator.sendKeys(Character.toString(generateCharacters) + "Hemanth");
		return this;
	}

	@Step("Enter last name {0}")
	public MyAccountsPageObjects enterLastName(char generateCharacters) {
		addNewshippingAddressLastNameLocator.sendKeys(Character.toString(generateCharacters) + "Sridhar");
		return this;
	}

	@Step("Enter address1")
	public MyAccountsPageObjects enterAddress1(char generateCharacters) {
		addNewShippingAddressAddress1Locator.sendKeys(Character.toString(generateCharacters) + "Address1");
		return this;
	}

	@Step("Enter city")
	public MyAccountsPageObjects enterCity(char generateCharacters) {
		addNewShippingAddressCityLocator.sendKeys(Character.toString(generateCharacters) + "Kent");
		return this;
	}

	@Step("Select state {0}")
	public MyAccountsPageObjects selectState(String stateName) {
		Select select = new Select(addNewShippingAddressSelectStateLocator);
		select.selectByVisibleText(stateName);
		return this;
	}

	@Step("Enter zip code {0}")
	public MyAccountsPageObjects enterZipCode(int generateEightRandomNumbers) {
		addNewShippingAddressZipCodeLocator.sendKeys(Integer.toString(generateEightRandomNumbers));
		return this;
	}

	@Step("Enter phone number {0}")
	public MyAccountsPageObjects enterPhoneNumber(int tenRandomNumbers) {
		addNewShippingAddressPhoneLocator.sendKeys("1234567890");
		return this;
	}

	@Step("Enter email Address {0}")
	public MyAccountsPageObjects enterEmailAddress(char generateCharacters) {
		addNewShippingAddressEmailAddressLocator.sendKeys(Character.toString(generateCharacters) + "hemanth@gmail.com");
		return this;
	}

	@Step("Enter ship to id {0}")
	public MyAccountsPageObjects enterShipToID(int generateEightRandomNumbers) {
		addNewShippingAddressShipToLocator.sendKeys(Integer.toString(generateEightRandomNumbers));
		return this;
	}

	@Step("Click on groups tab")
	public MyAccountsPageObjects clickOnMyProductGroupsTab() throws Exception {
		logger = Logger.getLogger("clickOnMyProductGroupsTab");
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", groupsTab);
		logger.info("clicked on groups tab.");
		return this;
	}

	@Step("Verify page name is:{0}")
	public MyAccountsPageObjects verifyPageName(String myAccountBreadCrumb) {
		Assert.assertTrue(myAccountPageName.getText().trim().equalsIgnoreCase(myAccountBreadCrumb),
				"Page name is not " + myAccountBreadCrumb + " It is " + myAccountPageName.getText().trim());
		return this;
	}

	@Step("Click on view more icon")
	public MyAccountsPageObjects clickOnViewMore() throws Exception {
		Thread.sleep(4000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", viewMoreLocator);
		return this;
	}

	@Step("Verify page name")
	public MyAccountsPageObjects verifyPageName() {
		Assert.assertTrue(myAccountPageName.isDisplayed(), "My Account page name is not displayed.");
		return this;
	}

	public MyAccountsPageObjects verifyWhetherCreatedCartIsDisplayedUnderSaveCartSectionInGroupsTab(
			String saveCartName) {
		waiting.waitForVisibilityOfElement(By
				.xpath("//h3[text()='My Saved Carts']/following-sibling::ul/descendant::a[text()[normalize-space() = '"
						+ saveCartName + "']]"),
				6);
		Assert.assertTrue(
				getDriver().findElement(By.xpath(
						"//h3[text()='My Saved Carts']/following-sibling::ul/descendant::a[text()[normalize-space() = '"
								+ saveCartName + "']]"))
						.isDisplayed(),
				"Created cart is not getting displayed under groups tab.");
		return this;
	}

	@Step("Verify profile tab details")
	public MyAccountsPageObjects verifyProfileTab(String addressSectionLabels) {
		logger = Logger.getLogger("verifyProfileTab");
		waiting.waitForVisibilityOfElement(profileSectionLocator, 10);
		softAssert.assertTrue(profileSectionLocator.isDisplayed(), "Profile Section is not displayed");
		logger.info("Profile Section is displayed");
		softAssert.assertTrue(profileTab.getAttribute("class").contains("active"), "Profile tab is not enabled first.");
		logger.info("Profile tab is enabled first.");
		softAssert.assertTrue(userContactLocator.isDisplayed(), "User Contact is not displayed.");
		logger.info("User Contact is displayed.");
		softAssert.assertTrue(editContactInfoLink.isDisplayed(), "Edit contact link is not displayed.");
		logger.info("Edit contact link is displayed.");
		softAssert.assertTrue(profilePicLocator.isDisplayed(), "Profile picture is not displayed.");
		logger.info("Profile picture is displayed.");
		softAssert.assertTrue(uploadButtonLocator.isDisplayed(), "Upload button is not displayed.");
		logger.info("Upload button is displayed.");
		softAssert.assertTrue(lastLoginLocator.isDisplayed(), "Last Login is not displayed.");
		logger.info("Last Login is displayed.");
		waiting.waitForVisibilityOfElements(addressSectionInProfileLabelTabLocator, 10);

		String[] expectedAddressSectionLabels = addressSectionLabels.split(",");

		for (int i = 0; i < addressSectionInProfileLabelTabLocator.size(); i++) {
			softAssert.assertEquals(
					addressSectionInProfileLabelTabLocator.get(i).getText().replace(":", "").toLowerCase().trim(),
					expectedAddressSectionLabels[i].toLowerCase().trim(),
					"Address Section of Profile Tab is not :" + expectedAddressSectionLabels[i].toLowerCase().trim()
							+ ". It is :"
							+ addressSectionInProfileLabelTabLocator.get(i)
									.getText()
									.replace(":", "")
									.toLowerCase()
									.trim());
			logger.info("Address Section of Profile Tab has been verified" + " with [Actual] and [Expected] value as "
					+ "["
					+ addressSectionInProfileLabelTabLocator.get(i).getText().replace(":", "").toLowerCase().trim()
					+ "] and " + "[" + expectedAddressSectionLabels[i].toLowerCase().trim() + "]");
		}

		softAssert.assertAll();

		return this;
	}

	@Step("Verify address page")
	public MyAccountsPageObjects verifyAddressPage(String shipAddressListHeader) throws InterruptedException {
		Thread.sleep(1300);
		logger = Logger.getLogger("verifyAddressPage");
		softAssert.assertTrue(addressTab.getAttribute("class").contains("active"), "Address tab is not enabled.");
		logger.info("Address tab is enabled.");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(billingAddressLocator),
				"Billing Address is not displayed.");
		logger.info("Billing Address is displayed.");
		waiting.waitForVisibilityOfElement(shippingAddressLocator, 5);
		softAssert.assertTrue(utilityMethods.isElementDisplayed(shippingAddressLocator),
				"Shipping Address is not displayed.");
		logger.info("Shipping Address is displayed.");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(showDropdownLocator),
				"Show Dropdown is not displayed.");
		logger.info("Show Dropdown is displayed.");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(searchShipAddressIcon),
				"Search Ship Address is not displayed.");
		logger.info("Search Ship Address is displayed.");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(shipAddressListDetails),
				"Ship Address List Details is not displayed.");
		logger.info("Ship Address List Details is displayed.");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(paginationLocator), "Pagination is not displayed.");
		logger.info("Pagination is displayed.");
		String[] expectedShipAddressListHeader = shipAddressListHeader.split(",");
		for (int i = 0; i < shipAddressListHeaderLocators.size(); i++) {
			waiting.waitForVisibilityOfElements(shipAddressListHeaderLocators, 10);
			softAssert.assertEquals(shipAddressListHeaderLocators.get(i).getText(),
					expectedShipAddressListHeader[i].trim(),
					"Ship Address List Header are not : " + expectedShipAddressListHeader[i].trim() + ". It is :"
							+ shipAddressListHeaderLocators.get(i).getText());
			logger.info("Ship Address List Header has been verified" + " with [Actual] and [Expected] value as " + "["
					+ shipAddressListHeaderLocators.get(i).getText() + "] and " + "["
					+ expectedShipAddressListHeader[i].trim() + "]");
		}
		softAssert.assertAll();
		return this;
	}

	@Step("Verify My Product Group Tab.")
	public MyAccountsPageObjects verifyMyProductGroupsTab() {
		logger = Logger.getLogger("verifyMyProductGroupsTab");
		Assert.assertTrue(groupsTab.getAttribute("class").contains("active"), "My Product Group tab is not enabled.");
		logger.info("My Product Group tab is enabled.");
		return this;
	}

	@Step("Click On Product Group Under My Account Tab.")
	public MyAccountsPageObjects clickProductGroupUnderMyAccountTab(String myProductGroupName)
			throws InterruptedException {
		try {
			Thread.sleep(1000);
			WebElement myProductGroup = getDriver().findElement(By.xpath(
					"//h3[normalize-space(text())='My Product Group']/following-sibling::div//a[normalize-space(text())='"
							+ myProductGroupName + "']"));
			myProductGroup.click();
		} catch (NoSuchElementException e) {
			clickOnViewMoreUnderMyProductGroup();
			productGroupsPage().searchProductGroup(data.getProductGroupName())
					.clickOnTheGroupCreated(data.getProductGroupName());
		}
		return this;
	}

	@Step("Click On Product Group Under My Account Tab.")
	public MyAccountsPageObjects clickOnSaveCartUnderMyAccountTab(String saveCartNAme) throws Exception {
		try {
			Thread.sleep(1000);
			WebElement saveCart = getDriver().findElement(By.xpath(
					"//h3[normalize-space(text())='My Saved Carts']/following-sibling::div//a[normalize-space(text())='"
							+ saveCartNAme + "']"));
			saveCart.click();
		} catch (NoSuchElementException e) {
			clickOnViewMoreUnderMySavedCart();
			saveCartPage().searchSaveCart(data.getSaveCartName().trim())
					.clickOnTheCreatedSaveCart(data.getSaveCartName().trim());
		}
		return this;
	}

	@Step("Click on Edit Icon Of Billing Address")
	public MyAccountsPageObjects clickOnBillingAddressEditIcon() throws InterruptedException {
		logger = Logger.getLogger("clickOnBillingAddressEditIcon");
		Thread.sleep(1200);
		billingAddressEditIconLocator.click();
		logger.info("clicked on Edit Icon Of Billing Address.");
		return this;
	}

	@Step("Click on Edit Icon Of Billing Address")
	public MyAccountsPageObjects clickOnShippingAddressEditIcon() throws InterruptedException {
		logger = Logger.getLogger("clickOnShippingAddressEditIcon");
		Thread.sleep(1200);
		shippingAddressEditIconLocator.click();
		logger.info("clicked on Edit Icon Of Shipping Address.");
		return this;
	}

	@Step("Search text:{0}")
	public MyAccountsPageObjects sendTextToSearchFunction(String text) throws InterruptedException {
		logger = Logger.getLogger("sendTextToSearchFunction");
		waiting.waitForVisibilityOfElement(searchShipAddressIcon, 3);
		searchShipAddressIcon.clear();
		searchShipAddressIcon.sendKeys(text);
		searchShipAddressIcon.sendKeys(Keys.ENTER);
		Thread.sleep(1200);

		logger.info("Search Ship Address : " + text);
		return this;
	}

	@Step("Verify search result is:{0}")
	public void verifyValidSearchResult(String companyNameForRegistration) {
		Assert.assertEquals(companyNameForRegistration, companyNameLocator.getText());
	}

	@Step("Verify invalid search result")
	public void verifyInValidSearchResult(String invalidSearchMsg) {
		Assert.assertEquals(invalidSearchMsg, noMatchingRecordLocator.getText());
	}

	@Step("Click On Item Per Page Locator")
	public MyAccountsPageObjects clickOnItemPerPage() throws Exception {
		logger = Logger.getLogger("clickOnItemPerPage");
		Thread.sleep(4500);
		itemsPerPageLocator.click();
		logger.info("clicked On Item Per Page.");
		return this;
	}

	@Step("Click On Item Per Page Options in  dropdown")
	public MyAccountsPageObjects clickOnItemPerPageOption(String option) throws Exception {
		for (int i = 0; i < itemPerPageOptionsLocator.size(); i++) {
			if (itemPerPageOptionsLocator.get(i).getText().trim().equals(option)) {
				itemPerPageOptionsLocator.get(i).click();
				break;
			}
		}
		return this;
	}

	@Step("Verify display of items per page")
	public MyAccountsPageObjects verifyDisplayOfItems(int items) throws Exception {
		for (int i = 0; i <= verifyItemsPerPage.size(); i++) {
			if (verifyItemsPerPage.size() <= items) {
				Assert.assertTrue(true);
				break;
			}
		}
		return this;
	}

	@Step("Verify display of product group and view mode option")
	public MyAccountsPageObjects verifyDisplayOfMyProductGroupAndViewMore() {
		waiting.waitForVisibilityOfElement(myProductGroupViewMoreLocator, 4);
		Assert.assertTrue(myProductGroupViewMoreLocator.isDisplayed(),
				"my product group view more option is not displayed.");
		return this;
	}

	@Step("Verify display of save cart and view mode option")
	public MyAccountsPageObjects verifyDisplayOfMySavedCartAndViewMore() {
		Assert.assertTrue(savedCartViewMoreLocator.isDisplayed(), "save cart view more option is not displayed.");
		return this;
	}

	@Step("Click on orders tab")
	public MyAccountsPageObjects clickOnOrdersTab() throws Exception {
		logger = Logger.getLogger("clickOnOrdersTab");
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", ordersTab);
		logger.info("clicked on orders tab.");
		return this;
	}

	@Step("Verify orders tab content")
	public MyAccountsPageObjects verifyOrdersTabPageContent(String[] orderPageOpenOrdersHeaders,
			String[] orderPageCompletedOrdersHeaders) throws Exception {
		logger = Logger.getLogger("verifyOrdersTabPageContent");

		waiting.waitForVisibilityOfElements(openOrderHeadersLocator, 4);

		for (int i = 0; i < openOrderHeadersLocator.size() - 1; i++) {
			Assert.assertEquals(openOrderHeadersLocator.get(i).getText().trim(), orderPageOpenOrdersHeaders[i],
					"Table header of open order is wrong");
			logger.info("Table header of open order has been verified" + " with [Actual] and [Expected] value as " + "["
					+ openOrderHeadersLocator.get(i).getText().trim() + "] and " + "["
					+ orderPageOpenOrdersHeaders[i].trim() + "]");
		}

		for (int i = 0; i < completedOrderHeadersLocator.size() - 1; i++) {

			Assert.assertEquals(completedOrderHeadersLocator.get(i).getText().trim(),
					orderPageCompletedOrdersHeaders[i], "Table header of completed order is wrong");
			logger.info("Table header of Completed order has been verified" + " with [Actual] and [Expected] value as "
					+ "[" + completedOrderHeadersLocator.get(i).getText().trim() + "] and " + "["
					+ orderPageCompletedOrdersHeaders[i].trim() + "]");
		}

		return this;
	}

	@Step("Click on view all under comlpleted orders")
	public MyAccountsPageObjects clickOnViewAllInCompletedOrders() {
		waiting.waitForVisibilityOfElement(viewAllUnderCompletedOrdersLocator, 3);
		viewAllUnderCompletedOrdersLocator.click();
		return this;
	}

	public MyAccountsPageObjects verifyRequiredLastLoginDateFormat(String expectedDate) {
		waiting.waitForVisibilityOfElement(lastLoginLocator, 10);
		int i = lastLoginLocator.getText().trim().lastIndexOf(" ");
		String[] a = { lastLoginLocator.getText().trim().substring(0, i),
				lastLoginLocator.getText().trim().substring(i) };
		Assert.assertEquals(a[0], expectedDate, "Required date is not in MMMDDYYY format.");
		return this;
	}

	@Step("Verify page name  is {0} ")
	public MyAccountsPageObjects verifyPageNameMyAccountLandingPageName(String saveCartBreadcrumb) {
		waiting.waitForVisibilityOfElement(landingPageName, 5);
		Assert.assertTrue(landingPageName.getText().trim().equalsIgnoreCase(saveCartBreadcrumb),
				"Page name is not " + saveCartBreadcrumb);
		return this;
	}

	@Step("Verify shipping address details ")
	public MyAccountsPageObjects verifyShippingAddressDetails() {
		logger = Logger.getLogger("verifyChangedShippingAddressFieldsInMyAccountShippingAddresssection");
		waiting.waitForVisibilityOfElement(shippingAddressLane1Locator, 50);
		String[] ChangedShippingAddressData = data.ChangedShippingAddressData().split(">");
		softAssert.assertEquals(shippingAddressNameLocator.getText().trim(), ChangedShippingAddressData[0]);
		logger.info("Name field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressNameLocator.getText().trim().replace("×", "").replace("\n", "") + "] and " + "["
				+ ChangedShippingAddressData[0] + "]");
		softAssert.assertEquals(shippingAddressLane1Locator.getText().trim(), ChangedShippingAddressData[1]);
		logger.info("Address1  field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressLane1Locator.getText().trim().replace("×", "").replace("\n", "") + "] and " + "["
				+ ChangedShippingAddressData[1] + "]");
		softAssert.assertEquals(shippingAddressLane2Locator.getText().trim(), ChangedShippingAddressData[2]);
		logger.info("Address2  field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressLane2Locator.getText().trim().replace("×", "").replace("\n", "") + "] and " + "["
				+ ChangedShippingAddressData[2] + "]");
		softAssert.assertEquals(shippingAddressStateLocator.getText().trim(), ChangedShippingAddressData[3]);
		logger.info("City field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressStateLocator.getText().trim().trim().replace("×", "").replace("\n", "") + "] and "
				+ "[" + ChangedShippingAddressData[3] + "]");
		softAssert.assertEquals(shippingAddressZipLocator.getText(), ChangedShippingAddressData[4]);
		logger.info("Zip/Postal Code field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressZipLocator.getText().trim().replace("×", "").replace("\n", "") + "] and " + "["
				+ ChangedShippingAddressData[4] + "]");
		softAssert.assertAll();
		return this;
	}

	@Step("Search for address in Shipping address list ")
	public List<String> getAllShipAddressList() throws Exception {
		Thread.sleep(3000);
		ArrayList<String> AllShippingAddress = new ArrayList<String>();
		String row_start = "//*[@id='DataTables_Table_0']/tbody/tr[";
		String row_end = "]/td[1]";
		int x = 1;
		int rownum = 0;

		while (utilityMethods.isElementDisplayed((row_start + x + row_end))) {
			x++;
			rownum++;
		}

		String xp_start = "//*[@id='DataTables_Table_0']/tbody/tr[";
		String xp_mid = "]/td[";
		String xp_end = "]";

		for (int rows = 1; rows <= rownum; rows++) {

			for (int cols = 2; cols <= 5; cols++) {

				String address = (getDriver().findElement(By.xpath(xp_start + rows + xp_mid + cols + xp_end))
						.getText());
				AllShippingAddress.add(address);
			}

		}
		return AllShippingAddress;
	}

	@Step("Verify all Shipping Address list in my account")
	public MyAccountsPageObjects verifyAllShippingAddressWithAllShipAddressListInMyAccount() throws Throwable {
		logger = Logger.getLogger("verifyAllShippingAddressWithAllShipAddressListInMyAccount");
		List<String> allexpectedAddress = MyAccountMenuDropdown().clickOnChangeShippingAddressLink()
				.getAllShippigAddressSelectShipping();
		List<String> allActualAddressFromMyAccount = SelectShippingToProceed().selectingShippingAddress1()
				.verifyWelcomeMsgAfterLogin()
				.MyAccountMenuDropdown()
				.clickOnMyAccountLink()
				.clickOnAddressTab()
				.getAllShipAddressList();
		logger.info("Expected Shipping Address displayed :" + allexpectedAddress);
		logger.info("Actual Shipping Address displayed :" + allActualAddressFromMyAccount);
		Assert.assertTrue(allexpectedAddress.containsAll(allActualAddressFromMyAccount),
				"Shipping address list is different in My Account page");
		logger.info("All Shipping Address with All shipping address list verification completed");
		return this;
	}

	@Step("Clear the search field")
	public MyAccountsPageObjects clearSearchField() {
		logger = Logger.getLogger("clearSearchField");
		searchFieldLocator.clear();
		logger.info("Search field is cleared");
		return this;
	}

	@Step("Search for required shipping address")
	public List<String> getSearchedAddess(String searchData) throws Exception {
		logger = Logger.getLogger("getSearchedAddess");
		waiting.waitForVisibilityOfElement(searchFieldLocator, 10);
		searchFieldLocator.sendKeys(searchData);
		List<String> address = getAllShipAddressList();
		logger.info("searched shipping address is completed for :" + searchData);
		searchFieldLocator.clear();
		searchFieldLocator.click();
		return address;
	}

	@Step("Verify searched address with in all shipping address")
	public MyAccountsPageObjects verifySearchAddressWithAllShippingAddress() throws Exception {
		logger = Logger.getLogger("verifySearchAddressWithAllShippingAddress");
		SoftAssert softAssert = new SoftAssert();
		String[] searchAddress = data.getSearchShippingAddress().split(">");
		for (int j = 0; j < searchAddress.length; j++) {
			String searchedAddress = getSearchedAddess(searchAddress[j]).toString();
			for (int i = 0; i < getAllShipAddressList().size(); i++) {
				softAssert.assertTrue(searchedAddress.contains(getAllShipAddressList().get(i).toString()));
			}
		}
		softAssert.assertAll();
		logger.info("searched shipping address is verified with all shipping address completed");
		return this;
	}

	@Step("Click on Refresh Shipping Information link")
	public MyAccountsPageObjects clickOnRefreshShippingInformation() throws InterruptedException {
		logger = Logger.getLogger("clickOnRefreshShippingInformation");

		waiting.waitForElementToBeClickable(refreshShippingInformationButtonLocator, 10);
		refreshShippingInformationButtonLocator.click();
		Thread.sleep(800);
		logger.info("clicked on Refresh Shipping Information link");
		return this;
	}

	@Step("Verify success message")
	public MyAccountsPageObjects verifyPopupOnclickOnRefreshShippingInformation() throws Exception {
		logger = Logger.getLogger("VerifyPopupOnclickOnRefreshShippingInformation");
		String expected = "Thank you for your patience while your Ship To/Jobs list is being refreshed.";

		Thread.sleep(3000);
		waiting.waitForVisibilityOfElement(msgPopupLocator, 10);
		utilityMethods.moveToElement(msgPopupLocator);

		Assert.assertEquals(msgPopupLocator.getText().trim(), expected);
		logger.info("Alert text is verified after clicked on Refresh Shipping Information link");
		return this;
	}

	@Step("Verify Success message")
	public MyAccountsPageObjects verifySuccessMessageAfterclickOnRefreshShippingInformation() throws Exception {
		logger = Logger.getLogger("VerifyPopupOnclickOnRefreshShippingInformation");
		waiting.waitForVisibilityOfElement(successPopupLocator, 10);
		Assert.assertEquals(successPopupLocator.getText().trim(),
				"Thank you for your patience while your Ship To/Jobs list is being refreshed.",
				"Success message is not displayed");
		logger.info("verified Success message after clicked on Refresh Shipping Information link");
		return this;
	}

	@Step("Accept alert after clicked on Refresh Shipping Information link")
	public MyAccountsPageObjects acceptAlertAfterclickOnRefreshShippingInformation() {
		logger = Logger.getLogger("VerifyPopupOnclickOnRefreshShippingInformation");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", okButtonLocator);
		logger.info("clicked on OK button");
		return this;
	}

	@Step("Verify Last Login Time :{0}")
	public MyAccountsPageObjects verifyOfLastLogin(String date) {
		logger = Logger.getLogger("verifyOfLastLogin");
		waiting.waitForVisibilityOfElement(lastLoginLocator, 10);
		Assert.assertTrue(lastLoginDateTimeYearLocator.getText().trim().contains(date),
				"Last Login Detail is not similar to current date");
		logger.info("Last Login Detail is similar to current date");
		return this;
	}

	@Step("Click On Refresh Shipping Information Icon")
	public MyAccountsPageObjects clickOnRefreshShippingInformationIcon() throws InterruptedException {
		logger = Logger.getLogger("clickOnRefreshShippingInformationIcon");
		waiting.waitForVisibilityOfElement(refreshShippingIconLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", refreshShippingIconLocator);
		Thread.sleep(1500);
		logger.info("Clicked On Refresh Shipping Information Icon.");
		return this;
	}

	@Step("Get address ")
	public String getAddressName(int specificAddress) {
		waiting.waitForVisibilityOfElements(addressNameLocator, 10);
		String addressName = addressNameLocator.get(specificAddress - 1).getText().trim();
		return addressName;

	}

	@Step("Verify Valid Search Result Of Shipping Address:{0}")
	public MyAccountsPageObjects verifyValidSearchResultOfShippingAddress(String addressName) {
		logger = Logger.getLogger("verifyValidSearchResultOfShippingAddress");
		By validAddress = By.xpath("//td[contains(text(),'" + addressName.substring(0, 2).trim() + "')]");
		Assert.assertTrue(utilityMethods.isElementDisplayed(validAddress),
				"Search Result For Valid Data is not working.");
		logger.info("Valid Search Result Of Shipping Address has been verified : " + addressName);
		return this;

	}

	@Step("Verify Invalid Search Result Of Shipping Address.")
	public MyAccountsPageObjects verifyInValidSearchResultOfShippingAddress() {
		logger = Logger.getLogger("verifyInValidSearchResultOfShippingAddress");
		waiting.waitForVisibilityOfElement(invalidSearchLocator, 10);
		Assert.assertTrue(invalidSearchLocator.isDisplayed(), "Invalid Search Error Message Is not displayed.");
		logger.info("Invalid Search Result Of Shipping Address has been verified.");
		return this;

	}

	@Step("Click on specific View Order Button Under Open Order :{0}")
	public MyAccountsPageObjects clickOnViewOrderButtonOfOpenOrder(int specificViewOrderButton)
			throws InterruptedException {
		logger = Logger.getLogger("clickOnViewOrderButtonOfOpenOrder");
		try {
			waiting.waitForVisibilityOfElements(viewOrderButtonsLocatorOfOpenOrder, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					viewOrderButtonsLocatorOfOpenOrder.get(specificViewOrderButton - 1));
			Thread.sleep(1200);
			logger.info("Clicked on specific View Order Button Under Open Order : " + specificViewOrderButton);
		} catch (Exception e) {
			Assert.assertTrue(false, "View Order Button is not available under Open Orders,Plesae verify it manually.");

			logger.info("View Order Button is not available under Open Orders.");
		}
		return this;

	}

	@Step("Click on specific View Order Button Under Completed Order :{0}")
	public MyAccountsPageObjects clickOnViewOrderButtonOfCompletedOrder(int specificViewOrderButton)
			throws InterruptedException {
		try {

			logger = Logger.getLogger("clickOnViewOrderButtonOfCompletedOrder");

			waiting.waitForVisibilityOfElements(viewOrderButtonsLocatorOfCompletedOrder, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					viewOrderButtonsLocatorOfCompletedOrder.get(specificViewOrderButton - 1));
			Thread.sleep(1200);

			logger.info("Clicked on specific View Order Button Under Completed Order : " + specificViewOrderButton);

		} catch (TimeoutException e) {
			Assert.assertTrue(false,
					"View Order Button is not available under completed order,Plesae verify it manually.");
		}

		return this;

	}

	@Step("Click on specific Order Number Under Open Orders:{0}")
	public MyAccountsPageObjects clickOnSpecificOrderNumber(int specificOrderNumber) throws InterruptedException {

		try {
			logger = Logger.getLogger("clickOnSpecificOrderNumber");
			waiting.waitForVisibilityOfElements(orderNumberLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					orderNumberLocator.get(specificOrderNumber - 1));
			Thread.sleep(1200);
			logger.info("Clicked on specific Order Number Under Open Orders: " + specificOrderNumber);
		} catch (TimeoutException e) {
			Assert.assertTrue(false, "Order Number is not available under open order,Plesae verify it manually.");
		}
		return this;
	}

	@Step("Click on View All Button  Under Open Orders")
	public MyAccountsPageObjects clickOnViewAllButtonOfOpenOrders() throws InterruptedException {

		try {
			logger = Logger.getLogger("clickOnViewAllButtonOfOpenOrders");
			waiting.waitForVisibilityOfElement(viewAllUnderOpenOrdersLocator, 10);
			viewAllUnderOpenOrdersLocator.click();
			Thread.sleep(1200);
			logger.info("Click on View All Button  Under Open Orders");
		}

		catch (TimeoutException e) {
			Assert.assertTrue(false, "View All Button is not available under open order,Plesae verify it manually.");
		}
		return this;

	}

	@Step("Click on View All Button  Under Completed Orders")
	public MyAccountsPageObjects clickOnViewAllButtonOfCompletedOrders() throws InterruptedException {

		try {
			logger = Logger.getLogger("clickOnViewAllButtonOfCompletedOrders");
			waiting.waitForVisibilityOfElement(viewAllUnderCompletedOrdersLocator, 10);
			viewAllUnderCompletedOrdersLocator.click();
			logger.info("Click on View All Button  Under Completed Orders.");
			Thread.sleep(1200);
		} catch (TimeoutException e) {
			Assert.assertTrue(false,
					"View All Button is not available under Completed order,Plesae verify it manually.");
		}
		return this;

	}

	@Step("Click on View More Button Under My product Group.")
	public MyAccountsPageObjects clickOnViewMoreUnderMyProductGroup() throws InterruptedException {
		try {
			logger = Logger.getLogger("clickOnViewMoreUnderMyProductGroup");

			waiting.waitForVisibilityOfElement(myProductGroupViewMoreLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myProductGroupViewMoreLocator);
			Thread.sleep(1200);
			logger.info("clicked on View More Button Under My product Group.");
		} catch (TimeoutException e) {

			Assert.assertFalse(true,
					"View More Option is not available under my product group,please test this feature manually.");
		}
		return this;
	}

	@Step("Click on View More Button Under Save Cart.")
	public MyAccountsPageObjects clickOnViewMoreUnderMySavedCart() throws InterruptedException {
		try {

			logger = Logger.getLogger("clickOnViewMoreUnderMySavedCart");
			waiting.waitForVisibilityOfElement(savedCartViewMoreLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", savedCartViewMoreLocator);
			Thread.sleep(1200);
			logger.info("clicked on View More Button Under Save Cart.");
		} catch (TimeoutException e) {

			Assert.assertFalse(true,
					"View More Option is not available under save cart,please test this feature manually.");
		}
		return this;
	}

	@Step("Click on upload button.")
	public MyAccountsPageObjects clickOnUpload() {
		logger = Logger.getLogger("clickOnUpload");
		uploadButtonLocator.click();
		logger.info("clicked on Upload Button.");
		return this;
	}

	@Step("Verify file upload")
	public MyAccountsPageObjects verifyFileUpload(String profilePicture) throws Exception {
		logger = Logger.getLogger("verifyFileUpload");
		Thread.sleep(3000);
		waiting.waitForVisibilityOfElement(profilePicLocator, 70);
		Assert.assertTrue(profilePicLocator.getAttribute("src").trim().contains(
				profilePicture.substring(profilePicture.lastIndexOf("/") + 1)));
		logger.info("File Uploaded.");
		return this;
	}

	@Step("Verify Clear Button Functionality.")
	public MyAccountsPageObjects verifyClearButtonFunctionality() {
		logger = Logger.getLogger("verifyClearButtonFunctionality");
		waiting.waitForVisibilityOfElement(profilePicLocator, 70);
		Assert.assertTrue(profilePicLocator.getAttribute("src").trim().contains("default-profile.png"),
				"clear button is not working properly.");
		logger.info("Clear Button is working as expected.");
		return this;
	}

	@Step("Click on clear button.")
	public MyAccountsPageObjects clickOnClearButton() throws InterruptedException {
		logger = Logger.getLogger("clickOnClearButton");
		clearButtonLocator.click();
		Thread.sleep(1500);
		logger.info("Clicked on Clear button.");
		return this;

	}

}
