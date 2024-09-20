package pageobjects;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class MyProductGroupsPageObjects extends PageInitializer {

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();

	Actions action = new Actions(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(MyProductGroupsPageObjects.class);

	@FindBy(css = ".cimm_page-title")
	public WebElement pageName;

	@FindBy(xpath = "//form[@id='productGroupForm']/descendant::table")
	public WebElement myProductGroupCartSection;

	@FindBy(id = "groupName")
	private WebElement groupNamePageTitle;

	@FindBy(xpath = "//button[contains(@onclick,'deleteSavedProductGroup')]")
	private WebElement deleteGroupButtonLocator;

	@FindBy(xpath = "//li[contains(text(),'No Product')]")
	private WebElement noProductGroupsAvailableText;

	@FindBy(xpath = "//div[@class='cimm_listEnclosure']/descendant::a")
	private List<WebElement> listOfMyProductGroupsLocator;

	@FindBy(xpath = "//button[@onclick='editGroup();']")
	private WebElement editGroupNameButtonLocator;

	@FindBy(xpath = "//button[@data-id='bulkActionSelect']")
	private WebElement bulkOptionsLocator;

	@FindBy(xpath = "//input[@id='searchKey']")
	private WebElement searchTextboxLocator;

	@FindBy(xpath = "//button[@id='searchBtn']")
	private WebElement serachGoButtonLocator;

	@FindBy(xpath = "//button[@id='clearsearchBtn']")
	private WebElement clearSearchButtonLocator;

	@FindBy(xpath = "//h2[text()='My Product Groups']")
	private WebElement myProductGroupBreadcrumbLocator;

	@FindBy(xpath = "//div[@id='sortByBrand_chosen']/descendant::li")
	private List<WebElement> sortByOptionsTextLocator;

	@FindBy(xpath = "//button[@data-id='sortByBrand']/following-sibling::div[contains(@class,'dropdown-menu')]")
	private WebElement sortByDropdownBlockLocator;

	@FindBy(xpath = "//button[@data-id='sortByBrand']/span[contains(@class,'filter-option')]")
	private WebElement sortByDropdownSelectedValueLocator;

	@FindBy(xpath = "//button[@data-id='resultPage']")
	private WebElement resultsPerPageLocator;

	@FindBy(xpath = "//table[@id='groupCart']//th")
	public List<WebElement> tableHeaderLocator;

	@FindBy(xpath = "//button[@data-id='views']")
	private WebElement changeViewLocator;

	@FindBy(xpath = "//td[@data-th='Select']/label")
	private List<WebElement> checkboxesLocator;

	@FindBy(xpath = "//dl[@id='bulkOptions']/dd/descendant::li/descendant::span")
	private List<WebElement> bulkOptionsValuesLocator;

	@FindBy(xpath = "//div[contains(@class,'mainContentEnclosure')]/descendant::p")
	private WebElement noSaveGroupAvailableTextLocator;

	@FindBy(id = "editedName")
	private WebElement editGroupNameTextboxLocator;

	@FindBy(id = "groupNameSaveBtn")
	private WebElement groupNameSaveButton;

	@FindBy(xpath = "//td[@data-th='Ext Price']/span")
	private WebElement extensionPriceLocator;

	@FindBy(xpath = "//td[@data-th='Ext Price']/span")
	private List<WebElement> extensionPriceInPGLocator;

	@FindBy(xpath = "//div[@class='cimm_tableDescSection']/descendant::a")
	private List<WebElement> productNamesLocator;

	@FindBy(name = "shoppingCartQty")
	private List<WebElement> shoppingCartQuantityLocator;

	@FindBy(xpath = "//table/descendant::span[contains(text(),'Select All')]")
	private WebElement selectAllTextLocator;

	@FindBy(xpath = "//table/descendant::input[contains(@id,'chkSelectall')]")
	private WebElement selectAllCheckboxLocator;

	@FindBy(xpath = "//button[@data-id='sortByBrand']")
	private WebElement sortByDropdownLocator;

	@FindBy(xpath = "//p[contains(text(),'No items present')]")
	private WebElement noItemsPresentInTheGroupLocator;

	@FindBy(xpath = "//form[@id='productGroupForm']/descendant::input[@class='quantity']")
	private List<WebElement> quantityFieldLocator;

	@FindBy(xpath = "//p[contains(text(),'Added Successfully.')]")
	private List<WebElement> addedSuccessfullyMsgLocator;

	//@FindBy(xpath = "//form[@id='productGroupForm']//p[@itemprop='sku']/strong[normalize-space(text())='Part#:']/following-sibling::span")
	@FindBy(xpath = "//strong[normalize-space(text())='Part#:']/following-sibling::span[text()]")
	private List<WebElement> partNumberValueLocator;

	//@FindBy(xpath = "//form[@id='productGroupForm']//p[@itemprop='mpn']/strong[contains(text(),'MPN:')]/following-sibling::span")
	@FindBy(xpath = "//strong[contains(text(),'MPN:')]/following-sibling::span[text()]")
	private List<WebElement> manufacturerPartNumberLocator;

	@FindBy(xpath = "//button[@data-id='views']")
	private WebElement chooseViewLocator;

	@FindBy(xpath = "//li[contains(@style,'list-item')]")
	private List<WebElement> shortDescriptionLocator;

	@FindBy(xpath = "//div[@class='cartTotalCount pull-right']/descendant::a[@class='closeBtn']")
	private WebElement closeButtonLocatorInProductGroupPageMyCartPopUp;

	@FindBy(xpath = "//p[@class='alreadyExist']")
	private List<WebElement> itemAlreadyExistsMessageLocator;

	@FindBy(xpath = "//input[@value='Combine']")
	private List<WebElement> combineButtonInCartPopUpLocator;

	@FindBy(xpath = "//input[@value='Seperate']")
	private List<WebElement> seperateButtonInCartPopUpLocator;

	@FindBy(xpath = "//div[contains(@class,'popCheckout')]/descendant::a[text()='Checkout']")
	private WebElement checkoutButtonInMyCartLocator;

	@FindBy(xpath = "//div[@class='mulAddtoCartStatus']/descendant::input[@value='Cancel']")
	private List<WebElement> cancelButtonInCartPopUpLocator;

	@FindBy(xpath = "//td[contains(text(),'No results found')]")
	private WebElement noResultsLocator;

	@FindBy(xpath = "//td[@data-th='Item Description']")
	private List<WebElement> itemsInPG;

	@FindBy(xpath = "//input[@id='editedName']")
	private WebElement editCartNameTextBoxLocator;

	@FindBy(xpath = "//input[@value='Save']")
	private WebElement editCartNameSaveButtonLocator;

	@FindBy(xpath = "//button[@onclick='cancelEditGroup();']")
	private WebElement editCartNameCancelButtonLocator;

	@FindBy(xpath = "//h3[@id='addToCartHeaderContent']")
	private WebElement productGroupPopUpLocator;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement serachTextBoxLocator;

	@FindBy(xpath = "//div[contains(text(),'No Product')]")
	private WebElement noPGAvailableLocator;

	@Step("Click on {0} group")
	public MyProductGroupsPageObjects clickOnTheGroupCreated(String myProductGroupName) throws InterruptedException {
		Thread.sleep(1000);
		WebElement myProductGroup = getDriver()
				.findElement(By.xpath("//*[@id='groupCart']//a[contains(text(),'" + myProductGroupName + "')]"));
		myProductGroup.click();
		log.info("Clicked on My Product Group : " + myProductGroupName);
		return this;
	}

	@Step("Verify whether breadcrumb is {0} ")
	public MyProductGroupsPageObjects verifyBreadCrumb(String myProductGroupName) throws Exception {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);
		String lastBreadCrump = productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.trim();
		Assert.assertEquals(myProductGroupName, lastBreadCrump.replace("/", "").trim(),
				"item name and the last breadcrump is not the same. Item name is : " + myProductGroupName
						+ " and the last breadcrump is : " + lastBreadCrump);
		log.info("Verified breadcrumb");
		return this;

	}

	@Step("Verify price precision in My Product Group page")
	public MyProductGroupsPageObjects verifyPricePrecisionInMyProductGroupPage(String pnNumber, String pricePrecision) {

		WebElement specificItemPrice = getDriver().findElement(By.xpath("//span[contains(text(),'" + pnNumber
				+ "')]/ancestor::td/following-sibling::td[contains(@data-th,'Your')]/span"));

		Assert.assertEquals(specificItemPrice.getText().replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1].length(), Integer.parseInt(pricePrecision),
				"Price precision in not matched in My Product Group page");

		log.info("Verify Price precision in My Product Group page");
		return this;
	}

	@Step("Verify whether page name {0}")
	public MyProductGroupsPageObjects verifyPageName(String myProductGroupName) {
		Assert.assertEquals(groupNamePageTitle.getText().trim(), myProductGroupName.toUpperCase());
		log.info("My Product Group Page Name has been verified" + " with [Actual] and [Expected] value as " + "["
				+ groupNamePageTitle.getText().trim() + "] and " + "[" + myProductGroupName.toUpperCase() + "]");
		return this;
	}

	@Step("Click on delete")
	public MyProductGroupsPageObjects clickOnDelete() {
		deleteGroupButtonLocator.click();
		log.info("Clicked on delete button");
		return this;
	}

	@Step("Verify whether alert text is {0}")
	public MyProductGroupsPageObjects verifyAlertText(String partNumber) throws Exception {
		waiting.waitForAlertToBePresent(5);
		String expText = "Min Order Quantity is 10. For PN: " + partNumber
				+ ". To Continue with Min Order Qty click \"Ok\".To cancel this item click \"Cancel\"";
		Assert.assertTrue(utilityMethods.verifyAlertText(expText), "Alert text is invalid.");
		log.info("verifyAlertText-completed");
		return this;
	}

	@Step("Verify No Product Group Available text is displayed")
	public MyProductGroupsPageObjects verifyNoProductGroupAvailableText() {
		Assert.assertTrue(noProductGroupsAvailableText.isDisplayed());
		log.info("verifyNoProductGroupAvailableText-completed");
		return this;

	}

	@Step("Verify whether {0} group is deleted")
	public MyProductGroupsPageObjects verifyWhetherGroupIsDeleted(String myProductGroupName) {
		By myProductGroup = By.xpath("//a[contains()(text(),'" + myProductGroupName + "')]");
		Assert.assertTrue(utilityMethods.isElementNotDisplayed(myProductGroup), "group is not deleted.");
		log.info("verifyWhetherGroupIsDeleted-completed");
		return this;
	}

	@Step("Verify deleted Product group :{0}")
	public boolean assertGroupdIsDeleted(String myProductGroupName) {
		try {
			String productGroup = "//a[text()='" + myProductGroupName + "')]";
			waiting.waitForVisibilityOfElement(getDriver().findElement(By.xpath(productGroup)), 10);
			WebElement myProductGroup = getDriver().findElement(By.xpath(productGroup));
			Assert.assertTrue(myProductGroup.isDisplayed(), "group is not deleted yet.");
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	@Step("Verify whether breadcrumb is {0} ")
	public MyProductGroupsPageObjects verifyBreadCrumbOfMyProductGroupLandingPage(String myProductGroupsPageName) {
		Assert.assertTrue(productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.replace("/", "")
				.trim()
				.equalsIgnoreCase(myProductGroupsPageName));
		log.info("verifyBreadCrumbOfMyProductGroupLandingPage-completed");
		return this;
	}

	@Step("Verify whether page name is {0}")
	public MyProductGroupsPageObjects verifyPageNameOfMyProductGroupLandingPage(String myProductGroupsPageName) {
		Assert.assertTrue(pageName.getText().trim().equalsIgnoreCase(myProductGroupsPageName), "Page name is "
				+ pageName.getText().trim() + " but expecting the page name to be " + myProductGroupsPageName + ".");
		log.info("verifyPageNameOfMyProductGroupLandingPage-completed");
		return this;
	}

	@Step("Verify page title is {0}")
	public MyProductGroupsPageObjects verifyPageTitle(String myProductGroupLandingPageTitle, String productName) throws InterruptedException {
		waiting.waitTillPageLoads();
		Thread.sleep(2500);
		Assert.assertEquals(getDriver().getTitle().trim(), myProductGroupLandingPageTitle + " | " + productName);
		log.info("My Product Group Page Title  has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + myProductGroupLandingPageTitle + " | " + productName
				+ "]");
		return this;
	}

	@Step("Verify my product group page after clicking on the product")
	public MyProductGroupsPageObjects verifyMyProductGroupPageAfterClickingOnTheProduct() throws InterruptedException {
		Assert.assertTrue(changeViewLocator.isDisplayed(), "Change view dropdown is not displayed.");
		Assert.assertTrue(sortByDropdownLocator.isDisplayed(), "Sort By options is not displayed.");
		Assert.assertTrue(resultsPerPageLocator.isDisplayed(), "Results Per page is not displayed.");
		Assert.assertTrue(myProductGroupCartSection.isDisplayed(), "My Product Group Cart Section is not displayed.");
		Assert.assertTrue(clearSearchButtonLocator.isDisplayed(), "Clear search is not displayed.");
		Assert.assertTrue(serachGoButtonLocator.isDisplayed(), "Search Go button is not displayed.");
		Assert.assertTrue(searchTextboxLocator.isDisplayed(), "Search textbox is not displayed.");
		Assert.assertTrue(bulkOptionsLocator.isDisplayed(), "Bulk options is not displayed.");
		Assert.assertTrue(editGroupNameButtonLocator.isDisplayed(), "Edit group name button is not displayed.");
		Assert.assertTrue(deleteGroupButtonLocator.isDisplayed(), "Delete group name button is not displayed.");
		log.info("verifyMyProductGroupPageAfterClickingOnTheProduct-completed");
		return this;
	}

	@Step("Verify my product group page after clicking on the product")
	public MyProductGroupsPageObjects verifyMyProductGroupFields() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(changeViewLocator.isDisplayed(), "Change view dropdown is not displayed.");
		softAssert.assertTrue(sortByDropdownLocator.isDisplayed(), "Sort By options is not displayed.");
		softAssert.assertTrue(resultsPerPageLocator.isDisplayed(), "Results Per page is not displayed.");
		softAssert.assertTrue(myProductGroupCartSection.isDisplayed(),
				"My Product Group Cart Section is not displayed.");
		softAssert.assertTrue(clearSearchButtonLocator.isDisplayed(), "Clear search is not displayed.");
		softAssert.assertTrue(serachGoButtonLocator.isDisplayed(), "Search Go button is not displayed.");
		softAssert.assertTrue(searchTextboxLocator.isDisplayed(), "Search textbox is not displayed.");
		softAssert.assertTrue(bulkOptionsLocator.isDisplayed(), "Bulk options is not displayed.");
		softAssert.assertTrue(editGroupNameButtonLocator.isDisplayed(), "Edit group name button is not displayed.");
		softAssert.assertTrue(deleteGroupButtonLocator.isDisplayed(), "Delete group name button is not displayed.");
		softAssert.assertAll();
		log.info("verifyMyProductGroupFields-completed");
		return this;
	}

	@Step("Click on the {0}st/nd/rd checkbox")
	public MyProductGroupsPageObjects clickOnSpecificCheckbox(String partNumber){
		WebElement specificCheckboxByPN = getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Select']/label"));
		specificCheckboxByPN.click();
		log.info("clickOnSpecificCheckbox-completed");
		return this;
	}

	@Step("Select {0} from bulk actions dropdown")
	public MyProductGroupsPageObjects selectBulkActionsDropdown(String bulkOption) throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", bulkOptionsLocator);
		switch (bulkOption) {
		case "Delete Selected Items":
			getDriver().findElement(By.xpath("//span[text()='Delete Selected Items']")).click();
			break;
		case "Update Selected Items":
			getDriver().findElement(By.xpath("//span[text()='Update Selected Items']")).click();
			break;
		case "Add Selected Items to Cart":
			getDriver().findElement(By.xpath("//span[text()='Add Selected Items to Cart']")).click();
			break;
		default:
			throw new Exception("invalid input");
		}
		log.info("selectBulkActionsDropdown-completed");
		return this;
	}

	@Step("Verify Deleted My Product Group")
	public boolean assertDeleteOfMyProductGroup() {
		try {
			if (myProductGroupCartSection.isDisplayed()) {
				return false;
			}
		} catch (NoSuchElementException e) {
			return true;
		}
		return false;
	}

	@Step("Click on edit button")
	public MyProductGroupsPageObjects clickOnEditButton() {
		waiting.waitForVisibilityOfElement(editGroupNameButtonLocator, 5);
		editGroupNameButtonLocator.click();
		log.info("Clicked on edit button");
		return this;
	}

	@Step("Edit group name as {0}")
	public MyProductGroupsPageObjects enterEditGroupName(String groupName) {
		waiting.waitForVisibilityOfElement(editGroupNameTextboxLocator, 5);
		editGroupNameTextboxLocator.clear();
		editGroupNameTextboxLocator.sendKeys(groupName);
		log.info("Entered updated product group name");
		return this;
	}

	@Step("Edit group name as {0}")
	public MyProductGroupsPageObjects enterNameForEditPGNameScenarios(String flag) {
		waiting.waitForVisibilityOfElement(editGroupNameTextboxLocator, 5);
		if (!flag.equals("No Change"))
			editGroupNameTextboxLocator.clear();
		return this;
	}

	@Step("Click on save")
	public MyProductGroupsPageObjects clickOnSave() {
		waiting.waitForVisibilityOfElement(groupNameSaveButton, 5);
		groupNameSaveButton.click();
		log.info("Clicked on Save button");
		return this;
	}

	@Step("Get extension price")
	public Number getExtensionPrice() throws Exception {
		waiting.waitForVisibilityOfElement(extensionPriceLocator, 5);
		Number price = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(extensionPriceLocator.getText().replace("\n", "").replace(" ", "").trim());
		return price;
	}

	@Step("Get extension price :{0}")
	public float getExtensionPriceOfSpecificItem(int specificItem) throws Exception {
		waiting.waitForVisibilityOfElements(extensionPriceInPGLocator, 5);
		return Float.parseFloat(extensionPriceInPGLocator.get(specificItem - 1).getText().replace("$", "").trim());
	}

	@Step("Verify update of extension price")
	public MyProductGroupsPageObjects verifyExtPrice(String quantity, Number currentExtnPrice) throws ParseException {
		Number afterUpdateExtensionPrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(extensionPriceLocator.getText().replace("\n", "").replace(" ", "").trim());
		int quantityValue = Integer.parseInt(quantity);
		Assert.assertTrue(checkForExtnPrice(currentExtnPrice, afterUpdateExtensionPrice, quantityValue),
				"extension price is not getting updated.");
		log.info("verifyExtPrice-completed");
		return this;
	}

	@Step("Check for Extn price")
	private boolean checkForExtnPrice(Number previousPrice, Number afterPrice, int quantityValue) {
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		String previous = oneDigit.format(previousPrice.doubleValue() * quantityValue);
		String after = oneDigit.format(afterPrice.doubleValue());
		return previous.equals(after);
	}

	@Step("Get quantity")
	public String getQuantity() {

		return shoppingCartQuantityLocator.get(0).getAttribute("value").trim();
	}

	public String getSpecificProductGroupName(int specificGroupName) {
		return listOfMyProductGroupsLocator.get(specificGroupName - 1).getText().trim();
	}

	@Step("Verify sort by options")
	public MyProductGroupsPageObjects verifySortByDrodown(String[] expectedSortByDropdownOptions)
			throws InterruptedException {
		Thread.sleep(3500);
		sortByDropdownLocator.click();
		for (int i = 0; i < sortByOptionsTextLocator.size(); i++) {
			Assert.assertEquals(sortByOptionsTextLocator.get(i).getText().trim(), expectedSortByDropdownOptions[i]);
		}
		log.info("verifySortByDrodown-completed");
		return this;
	}

	@Step("Click On Cancel Icon of Edit Product GroupName")
	public MyProductGroupsPageObjects clickOnCancelButtonOfEditPGName() throws Exception {
		waiting.waitForVisibilityOfElement(editCartNameCancelButtonLocator, 15);
		editCartNameCancelButtonLocator.click();
		log.info("Click on Cancel button when group name is in edit mode");
		return this;
	}

	@Step("Verify Product Group Name Elements are not displayed when Cancel Icon is clicked")
	public MyProductGroupsPageObjects verifyWhetherEditCartNameElementsAreNotDisplayedAfterCancel() throws Exception {
		Assert.assertFalse(editCartNameTextBoxLocator.isDisplayed(), "enterEditCartName is displayed.");
		Assert.assertFalse(editCartNameSaveButtonLocator.isDisplayed(), "editCartNameSaveIcon is displayed.");
		Assert.assertFalse(editCartNameCancelButtonLocator.isDisplayed(), "editCartNameCancelIcon is displayed.");
		log.info("verifyWhetherEditCartNameElementsAreNotDisplayedAfterCancel-completed");
		return this;
	}

	@Step("Click on select all checkbox")
	public MyProductGroupsPageObjects clickOnSelectAllCheckbox() {
		selectAllTextLocator.click();
		log.info("Clicked on Select All checkbox");
		return this;
	}

	@Step("Verify no product group")
	public boolean assertNoItemsInGroupMessage() {
		try {
			if (noItemsPresentInTheGroupLocator.isDisplayed()) {
				return true;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}

	@Step("Verify no items in group message.")
	public MyProductGroupsPageObjects verifyNoItemsInGroupMessage() throws Exception {
		Thread.sleep(1500);
		Assert.assertTrue(assertNoItemsInGroupMessage(), "No items in group message is not displayed.");
		log.info("verifyNoItemsInGroupMessage-completed");
		return this;
	}

	@Step("Verify landing page :{0}")
	public MyProductGroupsPageObjects verifyMyProductGroupsLandingPageName(String expectedPageName) {
		Assert.assertEquals(pageName.getText().trim(), expectedPageName, "My Product Groups Page Name is wrong");
		log.info("verifyMyProductGroupsLandingPageName-completed");
		return this;
	}

	@Step("Verify bread crumb")
	public MyProductGroupsPageObjects verifyMyProductGroupsLandingPageBreadcrumb(String expectedBreadcrumb) {
		Assert.assertTrue(myProductGroupBreadcrumbLocator.isDisplayed(),
				"My Product Groups Landing Page Breadcrumb is wrong");
		log.info("verifyMyProductGroupsLandingPageBreadcrumb-completed");
		return this;
	}

	@Step("Enter {0} quantity")
	public MyProductGroupsPageObjects enterQuantityForSpecificItem(String quantity, int specificItemsQty)
			throws InterruptedException {
		waiting.waitForVisibilityOfElements(quantityFieldLocator, 5);
		quantityFieldLocator.get(specificItemsQty - 1).click();
		quantityFieldLocator.get(specificItemsQty - 1).clear();
		quantityFieldLocator.get(specificItemsQty - 1).sendKeys(quantity);
		log.info("Entered quantity of specific item");
		return this;
	}

	@Step("Enter quantity for Item:{0}")
	public MyProductGroupsPageObjects enterQuantityForSpecificItem(String quantity, String partNumber)
			throws InterruptedException {
		WebElement qtyOfItem = getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Qty']/input"));
		qtyOfItem.clear();
		qtyOfItem.sendKeys(quantity);
		log.info("Entered quantity for item with part number:" + partNumber);
		return this;
	}

	@Step("Enter quantity for multiple items")
	public MyProductGroupsPageObjects enterQuantitiesForMultipleItems(String quantities) {
		for (int i = 0; i < quantityFieldLocator.size(); i++) {
			quantityFieldLocator.get(i).click();
			quantityFieldLocator.get(i).clear();
			quantityFieldLocator.get(i).sendKeys(quantities);
		}
		log.info("Entered quantity for multiple items");
		return this;
	}

	@Step("Verify quantity for multiple items")
	public MyProductGroupsPageObjects verifyQuantitiesInForMultipleItems(String quantity) throws InterruptedException {
		waiting.waitForVisibilityOfElements(quantityFieldLocator, 5);
		for (int i = 0; i < quantityFieldLocator.size(); i++) {
			Assert.assertEquals(quantityFieldLocator.get(i).getAttribute("value"), quantity,
					"Quantity is not getting updated. The updated quantity is "
							+ quantityFieldLocator.get(i).getAttribute("value"));
		}
		log.info("verifyQuantitiesInForMultipleItems-completed");
		return this;
	}

	@Step("Verify added succeddfully message : {0}")
	public MyProductGroupsPageObjects verifyNumberAddedSuccessfullyMsg(int expectedNumberOfAddedSuccessfullyMessages) {
		waiting.waitForVisibilityOfElements(addedSuccessfullyMsgLocator, 5);
		Assert.assertEquals(addedSuccessfullyMsgLocator.size(), expectedNumberOfAddedSuccessfullyMessages,
				"Added successfully message is not displayed.");
		log.info("verifyNumberAddedSuccessfullyMsg-completed");
		return this;
	}

	@Step("Enter Part Number {0}")
	public MyProductGroupsPageObjects enterSearchText(String itemToBeSearched) {
		waiting.waitForVisibilityOfElement(searchTextboxLocator, 5);
		searchTextboxLocator.sendKeys(itemToBeSearched);
		log.info("Entered search text");
		return this;
	}

	@Step("Click on Search button")
	public MyProductGroupsPageObjects clickOnSearchButton() {
		waiting.waitForVisibilityOfElement(serachGoButtonLocator, 5);
		serachGoButtonLocator.click();
		log.info("Clicked on Search button");
		return this;
	}

	@Step("Verify Part Number {0}")
	public MyProductGroupsPageObjects verifyPartNumberAfterSearch(String partNumber) {
		WebElement pn = getDriver().findElement(By.xpath("//b[contains(text(),'Part#:')]/ancestor::p[contains(text(),'"
				+ partNumber.trim()
				+ "')]/ancestor::td[@data-th='Item Description']//following-sibling::td[@data-th='Select']/label"));
		Assert.assertTrue(pn.isDisplayed(), "Searched item not found");
		log.info("verifyPartNumberAfterSearch-completed");
		return this;
	}

	@Step("Select View:{0}")
	public MyProductGroupsPageObjects selectView(String viewType) throws InterruptedException {
		waiting.waitForVisibilityOfElement(chooseViewLocator, 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", chooseViewLocator);
		// chooseViewLocator.click();
		Thread.sleep(1200);
		switch (viewType) {
		case "Expand View":
			WebElement expand = getDriver().findElement(By.xpath("//span[contains(text(),'Expand View')]"));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", expand);
			log.info("Select - Expand View");
			break;

		case "Collapse View":
			WebElement collapse = getDriver().findElement(By.xpath("//span[contains(text(),'Collapse View')]"));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", collapse);
			log.info("Select - Collapse View");
			break;
		}
		return this;
	}

	@Step("Verify collapse view")
	public MyProductGroupsPageObjects verifyCollapseView() {
		try {
			for (int i = 0; i < itemsInPG.size(); i++) {
				Assert.assertFalse(utilityMethods.isElementDisplayed(shortDescriptionLocator.get(i)),
						"Not in Collapse View");
			}
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		log.info("Verified collapse view");
		return this;
	}

	@Step("Verify expand view")
	public MyProductGroupsPageObjects verifyExpandView() {
		for (int i = 0; i < itemsInPG.size(); i++) {
			Assert.assertTrue(utilityMethods.isElementDisplayed(shortDescriptionLocator.get(i)), "Not in Expand View");
		}
		log.info("Verified expand view");
		return this;
	}

	@Step("Click on the {0}st/nd/rd checkbox")
	public MyProductGroupsPageObjects clickOnSpecificCheckbox(int specificCheckbox) throws InterruptedException {
		waiting.waitForVisibilityOfElements(checkboxesLocator, 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				checkboxesLocator.get(specificCheckbox - 1));
		log.info("Clicked on specific checkbox");
		return this;
	}

	@Step("Click on Close button")
	public MyProductGroupsPageObjects clickOnCloseButtonInProductGroupPageMyCartPopUp() throws InterruptedException {
		Thread.sleep(3000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				closeButtonLocatorInProductGroupPageMyCartPopUp);
		log.info("Clicked on close button");
		return this;
	}

	@Step("Verify Message :{0}")
	public MyProductGroupsPageObjects verifyItemAlreadyExistsMessageInMyCartPopUpInMyProductGroup(
			int specificItemAlreadyExistsMessage) {
		waiting.waitForVisibilityOfElements(itemAlreadyExistsMessageLocator, 5);
		Assert.assertTrue(itemAlreadyExistsMessageLocator.get(specificItemAlreadyExistsMessage - 1).isDisplayed(),
				"Item already exists message is not displayed");
		log.info("verifyItemAlreadyExistsMessageInMyCartPopUpInMyProductGroup-completed");
		return this;

	}

	@Step("Verify the button :{0}")
	public MyProductGroupsPageObjects verifyDisplayOfCombineSeperateButtonInMyCartPopUpInMyProductGroup(
			int specificCombineButton) {
		Assert.assertTrue(combineButtonInCartPopUpLocator.get(specificCombineButton - 1).isDisplayed(),
				"Combine button is not displayed");
		Assert.assertTrue(seperateButtonInCartPopUpLocator.get(specificCombineButton - 1).isDisplayed(),
				"Seperate button is not displayed");
		log.info("verifyDisplayOfCombineSeperateButtonInMyCartPopUpInMyProductGroup-completed");
		return this;
	}

	@Step("Click on Seperate buttib {0}")
	public MyProductGroupsPageObjects clickOnSpecificSeperateButton(int specificSperateButton) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				seperateButtonInCartPopUpLocator.get(specificSperateButton - 1));
		log.info("Clicked on Seperate button");
		return this;
	}

	@Step("Click on Checkout button in My Cart pop up")
	public MyCartPageObjects clickOnCheckoutButtonInMyCartPopUpInMyProductGroup() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", checkoutButtonInMyCartLocator);
		log.info("clickOnCheckoutButtonInMyCartPopUpInMyProductGroup-completed");
		return myCartPage();
	}

	@Step("Click on cancel button :{0}")
	public MyProductGroupsPageObjects clickOnSpecificCancelButton(int specificCancelButton) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				cancelButtonInCartPopUpLocator.get(specificCancelButton - 1));
		log.info("Clicked on Cancel button");
		return this;

	}

	@Step("Click on Specific combine button:{0}")
	public MyProductGroupsPageObjects clickOnSpecificCombineButton(int specificCombineButton) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				combineButtonInCartPopUpLocator.get(specificCombineButton - 1));
		log.info("Clicked on Combine button");
		return this;
	}

	@Step("Verify product group page")
	public MyProductGroupsPageObjects verifyProductGroupPage(String productGroupName) throws Exception {
		verifyBreadCrumb(productGroupName);
		verifyPageName(productGroupName);
		return this;
	}

	@Step("Verify item with part number:{0} is present in product group")
	public MyProductGroupsPageObjects verifyItemsInProductGroup(String partNumber) throws Exception {
		Thread.sleep(800);
		By itemInPG = By.xpath("//strong[contains(text(),'Part#')]/following-sibling::span[contains(text(),'"
				+ partNumber.trim() + "')]");
		Assert.assertTrue(utilityMethods.isElementDisplayed(itemInPG), "Item added is not present in product group");
		log.info("verifyItemsInProductGroup-completed");
		return this;
	}

	@Step("Verify items added to product group")
	public MyProductGroupsPageObjects verifyItemsInProductGroup(String[] partNumbers) throws Exception {
		Thread.sleep(400);
		for (int i = 0; i < partNumbers.length; i++) {
			By itemInPG = By.xpath("//strong[contains(text(),'Part#')]/following-sibling::span[contains(text(),'"
					+ partNumbers[i].trim() + "')]");
			Assert.assertTrue(getDriver().findElement(itemInPG).isDisplayed(),
					"Item added is not present in product group");
		}
		log.info("verifyItemsInProductGroup-completed");
		return this;
	}

	@Step("Verify items added to product group")
	public MyProductGroupsPageObjects verifyItemsInProductGroup(List<String> partNumbers) throws Exception {
		Thread.sleep(400);
		for (int i = 0; i < partNumbers.size(); i++) {
			By itemInPG = By.xpath("//strong[contains(text(),'Part#')]/following-sibling::span[contains(text(),'"
					+ partNumbers.get(i).trim() + "')]");
			Assert.assertTrue(getDriver().findElement(itemInPG).isDisplayed(),
					"Item added is not present in product group");
		}
		log.info("verifyItemsInProductGroup-completed");
		return this;
	}

	@Step("Verify item with part number:{0} is deleted from product group")
	public MyProductGroupsPageObjects verifyItemIsDeletedFromPG(String partNumber) {
		By itemInPG = By.xpath(
				"//strong[contains(text(),'Part#')]/following-sibling::span[contains(text(),'" + partNumber + "')]");
		Assert.assertTrue(utilityMethods.isElementNotDisplayed(itemInPG), "Item is not deleted from product group");
		log.info("verifyItemIsDeletedFromPG-completed");
		return this;
	}

	@Step("Verify quantity of an item in product group")
	public MyProductGroupsPageObjects verifyQuantityOfItemInPG(String quantity, int specificItemQty) {
		Assert.assertEquals(quantityFieldLocator.get(specificItemQty - 1).getAttribute("value"), quantity);
		log.info("verifyQuantityOfItemInPG-completed");
		return this;
	}

	@Step("Verify updated extn. price in product group")
	public MyProductGroupsPageObjects verifyUpdatedExtPrice(float updatedExtnPrice, int specificItem) {
		float actualExtPrice = Float
				.parseFloat(extensionPriceInPGLocator.get(specificItem - 1).getText().replace("$", "").trim());
		Assert.assertEquals(actualExtPrice, updatedExtnPrice, "Price mismatch");
		log.info("verifyUpdatedExtPrice-completed");
		return this;
	}

	@Step("Verify updated extn. price in product group")
	public MyProductGroupsPageObjects verifyUpdatedExtPrice(String updatedExtPrice, String partNumber)
			throws InterruptedException {
		Thread.sleep(1000);
		String actualExtPrice = getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Ext Price']/span"))
				.getText()
				.trim()
				.replace("$", "");
		Assert.assertEquals(Math.round(Float.parseFloat(actualExtPrice)), Math.round(Float.parseFloat(updatedExtPrice)),
				"Ext. Price not updated after quantity is updated");
		log.info("verifyUpdatedExtPrice-completed");
		return this;
	}

	@Step("Verify alert text of bulk options")
	public MyProductGroupsPageObjects verifyAlertTextsForBulkOptions(String[] alertTexts, String[] bulkOptions)
			throws Exception {
		for (int i = 0; i < bulkOptions.length; i++) {
			selectBulkActionsDropdown(bulkOptions[i].trim());
			verifyAlertText(alertTexts[i].trim());
		}
		log.info("verifyAlertTextsForBulkOptions-completed");
		return this;
	}

	@Step("Verify error message")
	public MyProductGroupsPageObjects verifyErrorMsg(String searchText, String errorMsg) throws Exception {
		try {
			if (searchText.equals(""))
				verifyAlertText(errorMsg.trim());
			else
				Assert.assertTrue(noResultsLocator.isDisplayed(), "Searched Item found");
			return this;
		} catch (UnhandledAlertException e) {
			// utils.alertAccept();
			throw new Exception("Unhandled alert");
		}
	}

	@Step("Verify error message")
	public MyProductGroupsPageObjects verifyErrorMsg() throws Exception {
		Assert.assertTrue(noResultsLocator.isDisplayed(), "Searched Item found");
		log.info("Verified error message");
		return this;
	}

	@Step("Delete product group {0}")
	public MyProductGroupsPageObjects deleteProductGroup(String productGroupName) throws Exception {
		Thread.sleep(1500);
		WebElement deleteIcon = getDriver().findElement(By.xpath("//a[contains(text(),'" +productGroupName+ "')]/ancestor::td[@data-th='Group Name']/following-sibling::td[@data-th='Action']//button[@data-original-title='Delete Group']"));
		deleteIcon.click();
		log.info("Deleted product group:" + productGroupName);
		return this;
	}

	@Step("Verify quantity of specific item :{1}")
	public MyProductGroupsPageObjects verifyQuantityOfSpecificItemInPG(int quantity, String partNumber) {
		waiting.waitForVisibilityOfElement(pageName, 5);
		String itemQty = getDriver().findElement(By.xpath(
				"//td[@data-th='Item Description']//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber.trim() + "')]/ancestor::td/following-sibling::td[@data-th='Qty']/input"))
				.getAttribute("value");
		Assert.assertTrue(Integer.parseInt(itemQty) >= quantity,
				"Quantity not incremented after adding same item multiple times");
		log.info("verifyQuantityOfSpecificItemInPG-completed");
		return this;
	}

	@Step("Verify updated group name :{0}")
	public MyProductGroupsPageObjects verifyUpdatedGroupName(String editPGName) {
		waiting.waitForVisibilityOfElement(pageName, 8);
		Assert.assertEquals(pageName.getText().toLowerCase().trim(), editPGName.toLowerCase().trim(),
				"Group name is not updated");
		log.info("verifyUpdatedGroupName-completed");
		return this;
	}

	@Step("Click on Clear Search Button")
	public MyProductGroupsPageObjects clickOnClearSearchButton() {
		waiting.waitForVisibilityOfElement(clearSearchButtonLocator, 5);
		clearSearchButtonLocator.click();
		log.info("Click on clear search button");
		return this;
	}

	@Step("Click on Sort By Dropdown")
	public MyProductGroupsPageObjects clickOnSortByDropdown() {
		waiting.waitForVisibilityOfElement(sortByDropdownLocator, 5);
		sortByDropdownLocator.click();
		log.info("Clicked on SortBy dropdown");
		return this;
	}

	@Step("Verify sort by option :{2}")
	public MyProductGroupsPageObjects verifySortByOption(List<String> manufacturerPN, List<String> partNumber,
			String sortOption) throws Exception {
		waiting.waitForVisibilityOfElements(partNumberValueLocator, 10);
		List<String> pn = partNumberValueLocator.stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> mpn = manufacturerPartNumberLocator.stream().map(WebElement::getText).collect(Collectors.toList());
		WebElement option = getDriver().findElement(By.xpath("//span[contains(text(),'" + sortOption.trim() + "')]"));
		option.click();
		switch (sortOption) {
		case "Part#(ASC)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByDropdownLocator);
			waiting.waitForVisibilityOfElement(sortByDropdownBlockLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Thread.sleep(1000);
			Collections.sort(partNumber);
			Collections.sort(pn);
			Assert.assertEquals(partNumber, pn);
			break;
		case "Part#(Desc)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByDropdownLocator);
			waiting.waitForVisibilityOfElement(sortByDropdownBlockLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Thread.sleep(1000);
			Collections.sort(partNumber, Collections.reverseOrder());
			Collections.sort(pn, Collections.reverseOrder());
			Assert.assertEquals(partNumber, pn);
			break;
		case "MPN(ASC)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByDropdownLocator);
			waiting.waitForVisibilityOfElement(sortByDropdownBlockLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Thread.sleep(1000);
			Collections.sort(manufacturerPN);
			Collections.sort(mpn);
			Assert.assertEquals(manufacturerPN, mpn);
			break;
		case "MPN(Desc)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByDropdownLocator);
			waiting.waitForVisibilityOfElement(sortByDropdownBlockLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Thread.sleep(1000);
			Collections.sort(manufacturerPN, Collections.reverseOrder());
			Collections.sort(mpn, Collections.reverseOrder());
			Assert.assertEquals(manufacturerPN, mpn);
			break;
		}
		log.info("Verified sort by functionality :" + sortOption);
		return this;
	}

	@Step("Select Sort by Option :{0}")
	public MyProductGroupsPageObjects selectSortByOption(String sortByOption) {
		waiting.waitForVisibilityOfElement(sortByDropdownBlockLocator, 10);
		WebElement option = getDriver().findElement(By.xpath("//span[contains(text(),'" + sortByOption.trim() + "')]"));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
		log.info("Selected Sort By option as:" + sortByOption);
		return this;
	}

	@Step("Get the PN of items")
	public List<String> getPartNumbersOfItemsInPG() {
		waiting.waitForVisibilityOfElements(partNumberValueLocator, 8);
		List<String> pn = partNumberValueLocator.stream().map(WebElement::getText).collect(Collectors.toList());
		return pn;
	}

	@Step("Verify display of My Product Group Pop Up")
	public MyProductGroupsPageObjects verifyDisplayOfProductGroupPopUp() {
		waiting.waitForVisibilityOfElement(productGroupPopUpLocator, 10);
		Assert.assertTrue(productGroupPopUpLocator.isDisplayed());
		log.info("Verified Display Of Product Group PopUp");
		return this;
	}

	@Step("Verify Bulk option functionality :{0}")
	public MyProductGroupsPageObjects verifyBulkOptionFunctionality(String bulkOption) throws InterruptedException {
		waiting.waitForVisibilityOfElement(bulkOptionsLocator, 10);
		bulkOptionsLocator.click();
		Thread.sleep(500);
		switch (bulkOption) {
		case "Delete Selected Items":
			break;
		case "Update Selected Items":
			getDriver().findElement(By.xpath("//span[text()='" + bulkOption + "']")).click();
			break;
		case "Add Selected Items to Cart":
			break;
		}
		log.info("Verified Bulk option functionality");
		return this;
	}

	@Step("Click on close button in my product group popup of Product list Or Grid Page")
	public MyProductGroupsPageObjects clickOnCloseButtonOfProductGroupPopUp() throws InterruptedException {
		myCartPage().clickOnCloseButtonInMyCartPopUpOfProductListGridPage();
		return this;
	}

	@Step("Get price of Part Number :{0}")
	public String getPriceOfSpecificItem(String partNumber) {
		return getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Your Price']/span"))
				.getText()
				.trim()
				.replace("$", "");
	}

	@Step("Search for product group :{0}")
	public MyProductGroupsPageObjects searchProductGroup(String productGroupName) {
		try {
			waiting.waitForVisibilityOfElement(serachTextBoxLocator, 5);
			serachTextBoxLocator.clear();
			serachTextBoxLocator.sendKeys(productGroupName);
		} catch (TimeoutException | NoSuchElementException e) {
			Assert.assertTrue(utilityMethods.isElementDisplayed(noPGAvailableLocator));
		}
		return this;
	}

	@Step("Verify Table Header Of My Product Group Table :{0}")
	public MyProductGroupsPageObjects verifyTableHeaderOfMyProductGroupLandingPage(String tableHeaderOfMyProductGroup) {
		try {
			waiting.waitForVisibilityOfElements(tableHeaderLocator, 10);
			String[] expectedTableHeaderOfMyProductGroup = tableHeaderOfMyProductGroup.split(",");
			for (int i = 0; i < tableHeaderLocator.size(); i++) {

				Assert.assertEquals(tableHeaderLocator.get(i).getText().trim(),
						expectedTableHeaderOfMyProductGroup[i].trim(),

						"Table Header Of My Product Gropu is not : " + expectedTableHeaderOfMyProductGroup[i].trim()
								+ ". It is :" + tableHeaderLocator.get(i).getText().trim());
			}
		} catch (TimeoutException e) {
			Assert.assertTrue(false, "Table Header Is not Available For Logged In User.");
		}
		log.info("Verified Table header of product group");
		return this;
	}

}