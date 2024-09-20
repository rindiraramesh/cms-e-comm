package pageobjects;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.MainOperations;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ProductsListPageObjects extends PageInitializer {

	SoftAssert softAssertion = new SoftAssert();

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Actions action = new Actions(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	MainOperations mainOperations = new MainOperations();

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(ProductsListPageObjects.class);

	@FindBy(id = "box_Category")
	private WebElement categorySearchTextbox;

	@FindBy(xpath = "//div[@id='col_Category']//button[contains(text(),'Filter')]")
	private WebElement categoryFilterButtonLocator;

	@FindBy(css = "input[value='Search Brands']")
	private WebElement brandSearchTextbox;

	@FindBy(xpath = "//div[@id='col_Brands']//button[contains(text(),'Filter')]")
	private WebElement brandFilterButtonLocator;
	
	@FindBy(xpath = "//div[@id='col_Manufacturer']//button[contains(text(),'Filter')]")
	private WebElement manufacturerFilterButtonLocator;

	@FindBy(xpath = "//div[@class='cimm_leftnav']")
	public WebElement filterSectionLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'cart')]")
	private List<WebElement> addToCartButton;

	@FindBy(xpath = "//h4[contains(text(),'Brands')]/following-sibling::span")
	public WebElement filterBrandsDropdownToggleButtonLocator;

	@FindBy(xpath = "//div[@data-target='#col_Category']")
	public WebElement filterCategoryHeadingLocator;

	@FindBy(xpath = "//div[@data-target='#col_Brands']")
	public WebElement filterBrandsHeadingLocator;

	@FindBy(xpath = "//div[@data-target='#col_Manufacturer']")
	public WebElement filterManufacturerHeadingLocator;

	@FindBy(xpath = "//p[@class='cimm_productDetailBrand']")
	private WebElement productDetailsBrandHeading;

	@FindBy(xpath = "//ul[@class='listGridContainer']/li")
	private List<WebElement> listOfProductsLocator;

	@FindBy(xpath = "//a[@id='listView']")
	private WebElement changeViewButtonLocatorListView;

	@FindBy(xpath = "//a[@id='gridView']")
	private WebElement changeViewButtonLocatorGridView;

	@FindBy(xpath = "//div[@class='searchResults']/p")
	private WebElement searchResultsHeaderLocator;

	@FindBy(xpath = "//a[contains(text(),'Advanced Search')]")
	private WebElement advancedSearchLinkLocator;

	@FindBy(xpath = "//input[@id='keyWordTxt' or @id='narrowText']")
	private WebElement filterSearchTextboxLocator;

	@FindBy(xpath = "//button[@id='searchWithInBtn' or @id='nSearchBtn']")
	private WebElement filterSearchButtonLocator;

	@FindBy(xpath = "//ul[@class='chosen-results']/li")
	private List<WebElement> sortByDropdownOptionsLocator;

	@FindBy(css = "select[id='resultPerPage']>option")
	private List<WebElement> resultsPerPageDropdownOptionsLocator;

	@FindBy(xpath = "//span[@class='cimm_compareControls']//a[@onclick='compareItems();']")
	private WebElement compareLinkLocator;

	@FindBy(xpath = "//a[@data-function='productGroupDropDown']")
	private List<WebElement> myProductGroupsLocator;

	@FindBy(css = ".productGroupBtn.dropdown>a>span")
	private List<WebElement> myProductGroupsLocatorBeforeLogin;

	@FindBy(xpath = "//a[text()='Add To Cart']/ancestor::li/descendant::ul/descendant::h2/a")
	private List<WebElement> items;

	@FindBy(xpath = "//ul[@class='listGridContainer']/descendant::h2")
	private List<WebElement> allProductsLocator;

	@FindBy(xpath = "//button[@data-id='sortBy']")
	private WebElement sortByDropdownLocator;

	@FindBy(xpath = "//a[@data-function='productGroupDropDown']/following-sibling::ul//input")
	public WebElement myProductGroupTextbox;

	@FindBy(xpath = "//div[@id='popSelector']/descendant::a")
	private WebElement productGroupCreationMsg;

	@FindBy(xpath = "//div[@id='drpdwnDiv']/descendant::a")
	private List<WebElement> myProductGroupOptionsLocator;

	@FindBy(xpath = "//div[contains(@class,'List')]/descendant::button")
	private List<WebElement> moreChoicesButtonLocator;

	@FindBy(xpath = "//div[contains(@class,'Grid')]/a")
	private List<WebElement> moreChoicesButtonLocator_GridView;

	@FindBy(xpath = "//input[@name='compareId']/..")
	private List<WebElement> compareCheckboxesUnderMoreChoicesLocator;

	@FindBy(xpath = "//td[contains(@class,'details-control')]/descendant::span[not(contains(@class,'imgForSend')) and not(contains(@id,'quantityBreakPricingDetails'))]")
	private List<WebElement> itemIdsUnderMyChoicesLocator;

	@FindBy(xpath = "//span[contains(@class,'compareControls')]/descendant::a[contains(@onclick,'clearCookie')]")
	private WebElement clearCompareButtonLocator;

	@FindBy(xpath = "//table[@id='childItemTable']//td[@class='tabelImage']//label")
	public List<WebElement> addToCompareCheckboxesUnderViewMoreChoicesLocatorInProductVersion3;

	@FindBy(xpath = "//td[@title='Compare']/label")
	public List<WebElement> addToCompareCheckboxesUnderViewMoreChoicesLocator;

	@FindBy(xpath = "//table[@id='childItemTable']//a[contains(text(),'Add To Cart')]")
	public List<WebElement> addToCartButtonAtItemLevelUnderViewMoreChoicesLocator;

	@FindBy(xpath = "//table[@id='childItemTable']//a[contains(text(),'Add To Product')]")
	public List<WebElement> addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocator;

	@FindBy(xpath = "//div[contains(@class,'dropdown groupList')]//a[@data-target='#loginModal']")
	public List<WebElement> addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocatorBeforeLogin;

	@FindBy(xpath = "//td[@title='Item Information']/em")
	public List<WebElement> itemInformationLinkUnderViewMoreChoicesLocator;

	@FindBy(xpath = "//td[@title='Add to Cart / Product group']/label")
	public List<WebElement> selectItemCheckboxLocatorsInProductMode;

	@FindBy(xpath = "//a[contains(text(),'Add To Product')]/following-sibling::ul/li/a")
	public List<WebElement> createdProductGroupLocator;

	@FindBy(xpath = "//div[@class='productdetail_list']//span[@id='compareSpan']")
	public WebElement compareItemsCount;

	@FindBy(xpath = "//a[contains(text(),'Add To Product')]/following-sibling::ul/li/input[@placeholder='Enter New Group Name']")
	public WebElement textBoxLocatorToEnterNewgroupName;

	@FindBy(xpath = "//div[@id='multiTab']//a")
	public List<WebElement> itemInformationTabLocatorUnderViewMoreChoices;

	@FindBy(xpath = "//li[@id='sitePartNo']/b/following-sibling::span")
	private WebElement partNumberValueInProductModeLocator;

	@FindBy(xpath = "//li[@id='upcNo']/b/following-sibling::span")
	private WebElement upcValueInProductModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'UPC')]")
	private List<WebElement> upcLabelInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'UPC')]/following-sibling::span")
	private List<WebElement> upcValueInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Part#:')]")
	private List<WebElement> partNumberLabelInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'MPN:')]")
	private List<WebElement> manufacturerPartNumberLabelInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'MPN:')]/following-sibling::span")
	private List<WebElement> manufacturerPartNumberValueInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'availLabel')]")
	private List<WebElement> availabilityLabelInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'availLabel')]/following-sibling::span")
	private List<WebElement> availabilityValueInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Min')]")
	private List<WebElement> minOrderQtyLabelInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Min')]/following-sibling::span")
	private List<WebElement> minOrderQtyValueInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Qty')]")
	private List<WebElement> qtyIntervalLabelInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Interval')]/following-sibling::span")
	private List<WebElement> qtyIntervalValueInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'Image')]")
	private List<WebElement> itemImageInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'Title')]")
	private List<WebElement> itemTitleInSKUModeLocator;

	@FindBy(xpath = "//p[@itemprop='description']")
	private List<WebElement> itemShortDescriptionInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Manu')]")
	private List<WebElement> manufacturerNameInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Manu')]/following-sibling::span")
	private List<WebElement> manufacturerValueInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Brand')]")
	private List<WebElement> brandNameInSKUModeLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Brand')]/following-sibling::span")
	private List<WebElement> brandValueInSKUModeLocator;

	@FindBy(xpath = "//button[@data-id='bulkAction']//span[text()='Bulk Options']")
	public WebElement bulkOptionLocator;

	@FindBy(xpath = "//span[contains(text(),'Add Items to Cart')]/..")
	public WebElement addItemToCartLocator;

	@FindBy(xpath = "//strong[contains(text(),'Availability:')]/following-sibling::span[1]")
	private List<WebElement> itemAvailabilityLocatorBeforeLogin;

	@FindBy(xpath = "//div[@class='bulkActionBtn']//span[contains(text(),'Add Items to Cart')]")
	private WebElement bulkOptionAddToCart;

	@FindBy(xpath = "//div[@class='bulkActionBtn']//span[contains(text(),'Add Items to Group')]")
	public WebElement bulkOptionAddToProductGroup;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Select')]/..")
	private List<WebElement> selectItemCheckboxLocators;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(text(),'Select')]/..")
	private List<WebElement> addToProductGropLocators;

	@FindBy(xpath = "//table[@id='childItemTable']//th")
	public List<WebElement> itemFieldsLocators;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'quantity')]")
	private List<WebElement> qtyTextBoxesLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//span[contains(@id,'partNumber')]")
	private List<WebElement> partNumberValueInPLPLocator;

	@FindBy(xpath = "//a[contains(@class,'btns-disable')]/ancestor::li//span[contains(@id,'partNumber')]")
	private List<WebElement> partNumberValueOfCallForPriceItem;

	@FindBy(xpath = "//td[@class='tabelImage']/descendant::span")
	private List<WebElement> partNumbersAboveLocators;

	@FindBy(xpath = "//div[@id='resultPerPage_chosen']/a")
	private WebElement showResultsDropdownLocator;

	@FindBy(xpath = "//ul[@id='productListBlock']//strong[contains(text(),'MPN:')]/following-sibling::span")
	private List<WebElement> mpnValueInSKUModeLocator;

	//@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'listGridItemDesc')]")
	@FindBy(xpath = "//*[@class='listGridContainer']/descendant::p[contains(@class,'listGridItemDesc')][1]")
	private List<WebElement> shortDescriptionLocator;
	
	@FindBy(xpath = "//*[contains(@id,'Block')]//p[contains(@itemprop,'desc')]")
	private List<WebElement> shortDescriptionPDLocator;

	@FindBy(xpath = "//td[@title='Item Information']/descendant::i")
	private List<WebElement> expandMoreChoicesInProductModeLocator;

	@FindBy(xpath = "//li[@id='sitePartNo']/descendant::span")
	private WebElement partNumberValueOfItemInProductModeLocator;

	@FindBy(xpath = "//select[@id='resultPerPage']")
	private WebElement itemsPerPageLocator;

	@FindBy(xpath = "//div[@class='cimm_filterAttributes']")
	private WebElement filterPanelLocator;

	@FindBy(xpath = "//input[@class='selectedItems']")
	private List<WebElement> selectCheckboxLocator;

	@FindBy(xpath = "//dl[@id='bulkAction']")
	private WebElement addItemsOptionsLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumbs;

	@FindBy(xpath = "//strong[contains(text(),'Brand:')]/following-sibling::span")
	private List<WebElement> brandValueLocator;

	@FindBy(xpath = "//strong[contains(text(),'Qty. Interval:')]/following-sibling::span")
	private List<WebElement> quantityIntervalValueLocator;

	@FindBy(xpath = "//div[@class='itemDetailInfoList']/a[contains(@onclick,'ProductMode.submitProductModeFilterForm')]")
	private List<WebElement> toggleButtonToViewProductModeItemInformationLocator;

	/*
	 * @FindBy(xpath = "//table[@id='childItemTable']//tr/td[@class='tabelImage']")
	 * private List<WebElement> childItemsLocator;
	 */

	@FindBy(xpath = "//table[@id='childItemTable']//tr/td[@class='tabelImage']//a")
	private List<WebElement> childItemsLocator;

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@name,'compare')]/..")
	private List<WebElement> addToCompareCheckboxesInSKUModeLocator;

	@FindBy(xpath = "//span[text()='Call for Price']/ancestor::li//input[@onclick='compareItemList(this);']")
	private List<WebElement> addToCompareCheckboxCallForPrice;

	// category Filter Priya

	@FindBy(xpath = "//*[contains(@id,'Block')]//*[contains(@class,'price')]/span")
	private List<WebElement> itemsPriceLocator;

	@FindBy(xpath = "//strong[contains(text(),'Availa')]/following-sibling::span[1]")
	private List<WebElement> itemsAvailabilityLocator;

	@FindBy(xpath = "//div[contains(text(),'Category')]/following-sibling::div//ul/li/label")
	private List<WebElement> categoryFilterLocator;

	@FindBy(xpath = "//div[contains(text(),'Brands')]/following-sibling::div//li")
	private List<WebElement> brandsFilterLocator;

	@FindBy(xpath = "//div[contains(text(),'Manufacturer')]/following-sibling::div//li")
	private List<WebElement> manufacturerFilterLocator;

	@FindBy(xpath = "//div[contains(text(),'Brands')]/following-sibling::div//li//label")
	private List<WebElement> brandsFilterCheckboxLocator;

	@FindBy(xpath = "//div[contains(text(),'Manufacturer')]/following-sibling::div//li//label")
	private List<WebElement> manufacturerFilterCheckboxLocator;

	@FindBy(xpath = "//div[@class='searchResults']/p")
	public WebElement searchResult;

	@FindBy(xpath = "//div[@class='cimm_filter-block-title cleatBtn']//a")
	public WebElement clearAllLinkLocator;

	@FindBy(xpath = "//div[@id='mulitFilterTrail']//li")
	public WebElement clearAllSectionLocator;

	@FindBy(xpath = "//a[@id='clickforAllBranch']")
	private List<WebElement> branchAvailabilityLinkLocator;

	@FindBy(xpath = "//div[@id='modalBodyContent']")
	private WebElement branchAvailabilityPopUpLocator;

	@FindBy(xpath = "//h4[@class='modal-title']")
	private WebElement branchAvailabilityPopUpNameLocator;

	@FindBy(xpath = "//th[text()='Branch Name']")
	private WebElement branchAvailabilityHeaderLocator;

	@FindBy(xpath = "//th[text()='Available']")
	private WebElement branchAvailableHeaderLocator;

	@FindBy(xpath = "//div[@class='pagebarUTH']/*[self::span | self::a[contains(@title,'Go to page')]]")
	private List<WebElement> paginationLocator;

	@FindBy(xpath = "//div[@class='pagebarUTH']/span[@class='this-page']")
	private WebElement currentPageLocator;

	@FindBy(xpath = "(//div[@class='pagebarUTH']/a[contains(text(),'Next')])[1]")
	private WebElement nextPageLocator;

	@FindBy(xpath = "(//div[@class='pagebarUTH']/a[contains(text(),'Previous')])[1]")
	private WebElement previousPageLocator;

	@FindBy(xpath = "//div/a[contains(@onclick,'changemode')]")
	private List<WebElement> listViewTooltip;

	@FindBy(xpath = "//li[@class='gridView']")
	private List<WebElement> checkForGridViewLocator;

	@FindBy(xpath = "//a[@id='listView']")
	private WebElement gridIconLocator;

	public ProductsListPageObjects paging(String pageNumber) {
		waiting.waitForVisibilityOfElement(
				By.xpath("//div[@class='pagebarUTH']//a[@title='Go to page " + pageNumber + "']"), 10);
		getDriver().findElement(By.xpath("//div[@class='pagebarUTH']//a[@title='Go to page " + pageNumber + "']"))
				.click();
		return this;
	}

	@Step("Verify header contains {0}")
	public ProductsListPageObjects verifyHeader(String searchText) {
		String productsHeader = "//b[contains(text(),'" + searchText + "')]";
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement productHeaderLocator = getDriver().findElement(By.xpath(productsHeader));
		waiting.waitForVisibilityOfElement(productHeaderLocator, 15);
		Assert.assertEquals(productHeaderLocator.getText().trim().toLowerCase(), searchText.toLowerCase(),
				"products page search header is not displayed");
		return this;

	}

	@Step("Verify whether products are displayed in the product list page")
	public ProductsListPageObjects verifyListOfProducts() {
		waiting.waitForVisibilityOfElements(listOfProductsLocator, 15);
		for (int i = 0; i < listOfProductsLocator.size(); i++) {
			Assert.assertTrue(listOfProductsLocator.get(i).isDisplayed(), "products are not displayed");
		}
		return this;
	}

	@Step("Verify alert message is {0}")
	public ProductsListPageObjects verifyAlertMessageForComparingMoreThan3Items(
			String expectedAlertMessageForComaringMoreThan3Items) throws Exception {

		return this;

	}

	@Step("Verify whether filter section is displayed.")
	public ProductsListPageObjects verifyFilterSection() {
		Assert.assertTrue(filterSectionLocator.isDisplayed(), "filter section is not displayed.");
		return this;
	}

	@Step("Verify whether compare link locator is displayed.")
	public ProductsListPageObjects verifyCompareLinkLocator() {
		Assert.assertTrue(compareLinkLocator.isDisplayed());
		return this;
	}

	@Step("Click on {0}st/2nd/rd My Product group button")
	public ProductsListPageObjects clickOnSpecificMyProductGroupButton(int specificProductGroup) throws Exception {
		waiting.waitForVisibilityOfElements(myProductGroupsLocator, 18);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				myProductGroupsLocator.get(specificProductGroup - 1));
		// myProductGroupsLocator.get(specificProductGroup - 1).click();
		return this;
	}

	@Step("Click on {0}st/2nd/rd My Product group button")
	public ProductsListPageObjects clickOnSpecificMyProductGroupButtonBeforeLogin(int specificProductGroup)
			throws Exception {
		waiting.waitForVisibilityOfElements(myProductGroupsLocatorBeforeLogin, 15);
		myProductGroupsLocatorBeforeLogin.get(specificProductGroup - 1).click();
		return this;
	}

	@Step("Verify sort by dropdown is displayed.")
	public ProductsListPageObjects verifySortByDropdown(String[] expectedSortByOptions) throws Exception {
		waiting.waitForVisibilityOfElement(sortByDropdownLocator, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByDropdownLocator);
		Thread.sleep(1000);
		for (int i = 0; i < expectedSortByOptions.length; i++) {
			By opt = By.xpath("//span[contains(text(),'" + expectedSortByOptions[i] + "')]");
			Assert.assertTrue(utils.isElementDisplayed(opt),
					expectedSortByOptions[i] + ":SortBy option is not displayed");
		}
		log.info("Verified sort by dropdown values");
		return this;
	}

	@Step("Verify results per page drop down is displayed.")
	public ProductsListPageObjects verifyResultsPerPageDropdown() {
		return this;
	}

	@Step("Click on {0} st/nd/rd item")
	public ProductsListPageObjects clickOnSpecificItem(int specificItemNumber) {
		items.get(specificItemNumber - 1).click();
		return this;
	}

	@Step("Enter {0} in narrow filter textbox")
	public ProductsListPageObjects enterSearchTextInNarrowFilterTextbox(String searchText) throws InterruptedException {
		waiting.waitForVisibilityOfElement(filterSearchTextboxLocator, 10);
		filterSearchTextboxLocator.sendKeys(searchText);
		return this;
	}

	@Step("Click on narrow search button")
	public ProductsListPageObjects clickOnNarrowSearchOrFilterSearchButton() throws InterruptedException {
		Thread.sleep(800);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", filterSearchButtonLocator);
		return this;
	}

	@Step("Enter group name {0}")
	public ProductsListPageObjects enterGroupName(String myProductGroupName) throws Exception {
		waiting.waitForVisibilityOfElement(myProductGroupTextbox, 6);
		myProductGroupTextbox.sendKeys(myProductGroupName);
		return this;

	}

	@Step("Hit enter")
	public ProductsListPageObjects hitEnter() throws InterruptedException {
		myProductGroupTextbox.sendKeys(Keys.ENTER);
		Thread.sleep(1200);
		return this;
	}

	@Step("Verify My Product Group creation success message contains {0}")
	public ProductsListPageObjects verifyMyProductCreationSuccessMsg(String myProductGroupName, int specificItem) {
		waiting.waitForVisibilityOfElement(productGroupCreationMsg, 5);
		Assert.assertEquals(productGroupCreationMsg.getText().trim(),
				items.get(specificItem - 1).getText().trim() + " Added To Group - " + myProductGroupName);
		return this;
	}

	@Step("Click on the success message")
	public MyProductGroupsPageObjects clickOnSuccessMessage() {
		productGroupCreationMsg.click();
		return productGroupsPage();
	}

	@Step("hover over a sku product")
	public ProductsListPageObjects hoverOverSpecificProduct(int specificAddToCart) throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-2000)", "");
		Actions action = new Actions(getDriver());
		action.moveToElement(items.get(specificAddToCart - 1)).build().perform();
		Thread.sleep(1500);
		return this;
	}

	@Step("Verify alert text is:{0}")
	public ProductsListPageObjects verifyAlertMessage(String expectedAlertMessageForBlankData) throws Exception {

		return this;
	}

	@Step("Choose {0} group name from dropdown list")
	public ProductsListPageObjects chooseGroupNameFromTheDropdownList(String myProductGroupName) {
		waiting.waitForVisibilityOfElements(myProductGroupOptionsLocator, 5);
		for (WebElement everyProductGroup : myProductGroupOptionsLocator) {
			if (everyProductGroup.getText().trim().equals(myProductGroupName)) {
				everyProductGroup.click();
				break;
			}
		}
		return this;
	}

	@Step("Click on compare link")
	public ProductsListPageObjects clickOnCompareLink() throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", compareLinkLocator);

		return this;
	}

	@Step("Click on {0} st/nd/rd More Choices link")
	public ProductsListPageObjects clickOnSpecificMoreChoices(int specificMoreChoices) throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				moreChoicesButtonLocator.get(specificMoreChoices - 1));
		return this;
	}

	@Step("Click on {0} Compare checkboxes under More Choices ")
	public ProductsListPageObjects clickOnCompareCheckboxesUnderMoreChoices(int numberOfCheckboxesToBeClicked)
			throws Exception {
		getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		for (int i = 0; i <= numberOfCheckboxesToBeClicked - 1; i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					compareCheckboxesUnderMoreChoicesLocator.get(i));
		}

		return this;
	}

	public String[] getItemIds(int numberOfItems) {

		String itemIds[] = new String[numberOfItems];
		for (int i = 0; i <= numberOfItems - 1; i++) {
			itemIds[i] = itemIdsUnderMyChoicesLocator.get(i).getText().trim();

		}
		return itemIds;
	}

	@Step("Verify compare text")
	public ProductsListPageObjects verifyCompareText(int expectedItemsInCompareLink) {

		Assert.assertEquals(Integer.parseInt(compareLinkLocator.getText()
				.trim()
				.replace("Compare", "")
				.replace("Items", "")
				.replace("(", "")
				.replace(")", "")
				.trim()), expectedItemsInCompareLink);
		return this;
	}

	@Step("Click on clear compare button")
	public ProductsListPageObjects clickOnClearCompareButton() throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", clearCompareButtonLocator);
		Thread.sleep(600);
		return this;
	}

	@Step("Verify clear compare link contains {0}")
	public ProductsListPageObjects verifyClearFunctionality(int expectedItemsInCompareLink) {
		Assert.assertEquals(
				Integer.parseInt(compareLinkLocator.getText().replace("Compare", "").replace("Item(s)", "").trim()),
				expectedItemsInCompareLink);
		return this;
	}

	public String getItemId(int specificItemId) {

		String partNumber = itemIdsUnderMyChoicesLocator.get(specificItemId - 1).getText().trim();
		return partNumber;
	}

	@Step("Click on {0} st/nd/rd page")
	public ProductsListPageObjects clickOnSpecificPage(int specficPage) {
		try {
			WebElement specificPageLocator = getDriver()
					.findElement(By.xpath("//a[@title='Go to page " + specficPage + "']"));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", specificPageLocator);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Step("Click on {0} st/nd/rd/th compare checkboxes under product mode items")
	public ProductsListPageObjects clickOnSpecificComapreCheckboxUnderViewMoreChoices(
			int specificCheckboxUnderProductMode) throws InterruptedException {
		waiting.waitForVisibilityOfElements(addToCompareCheckboxesUnderViewMoreChoicesLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCompareCheckboxesUnderViewMoreChoicesLocator.get(specificCheckboxUnderProductMode - 1));

		Thread.sleep(1400);
		return this;
	}

	@Step("Click on specific compare checkbox")
	public ProductsListPageObjects clickOnSpecificComapreCheckboxUnderViewMoreChoicesInProductVersion3Page(
			int specificCheckboxUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(addToCompareCheckboxesUnderViewMoreChoicesLocatorInProductVersion3, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCompareCheckboxesUnderViewMoreChoicesLocatorInProductVersion3
						.get(specificCheckboxUnderProductMode - 1));

		Thread.sleep(1400);
		return this;
	}

	@Step("Click on Add to cart button at item level")
	public ProductsListPageObjects clickOnAddToCartButtonAtItemLevel(int specificAddToCArtButtonUnderProductMode)
			throws InterruptedException {
		waiting.waitForVisibilityOfElements(addToCartButtonAtItemLevelUnderViewMoreChoicesLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCartButtonAtItemLevelUnderViewMoreChoicesLocator.get(specificAddToCArtButtonUnderProductMode - 1));

		Thread.sleep(1400);
		return this;
	}

	@Step("Click on Add to Product group button at item level")
	public ProductsListPageObjects clickOnAddToProductGroupButtonAtItemLevel(
			int specificAddToProductGrouptButtonUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocator
						.get(specificAddToProductGrouptButtonUnderProductMode - 1));

		Thread.sleep(1400);
		return this;
	}

	@Step("Click on Add to Product group button before login")
	public ProductsListPageObjects clickOnAddToProductGroupButtonAtItemLevelBeforeLogin(
			int specificAddToProductGrouptButtonUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocatorBeforeLogin,
				10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocatorBeforeLogin
						.get(specificAddToProductGrouptButtonUnderProductMode - 1));

		Thread.sleep(1400);

		return this;

	}

	@Step("Click on'Item info' link under product mode items {0}")
	public ProductsListPageObjects clickOnSpecificItemInformationLinkUnderViewMoreChoices(
			int specificItemInformationLink) throws InterruptedException {

		waiting.waitForVisibilityOfElements(itemInformationLinkUnderViewMoreChoicesLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				itemInformationLinkUnderViewMoreChoicesLocator.get(specificItemInformationLink - 1));

		Thread.sleep(1800);

		return this;

	}

	@Step("Verify  of 'Item information' link {0} of product mode items")
	public ProductsListPageObjects verifyItemInformationTabUnderViewMoreChoices(
			String itemInformationTabUnderViewMore) {

		String[] expectedItemInformationTabUnderViewMore = itemInformationTabUnderViewMore.split(",");

		waiting.waitForVisibilityOfElements(itemInformationTabLocatorUnderViewMoreChoices, 10);

		for (int i = 0; i < itemInformationTabLocatorUnderViewMoreChoices.size(); i++) {

			Assert.assertEquals(itemInformationTabLocatorUnderViewMoreChoices.get(i).getText().trim().toLowerCase(),
					expectedItemInformationTabUnderViewMore[i].trim().toLowerCase());
		}
		return this;

	}

	@Step("Verify brand name or MPN in product list page")
	public boolean assertForBrandNameOrMPNInProductListPage(String searchKeyword) {
		waiting.waitForVisibilityOfElements(listOfProductsLocator, 3);
		for (int i = 0; i < listOfProductsLocator.size(); i++) {

			if (listOfProductsLocator.get(i).getText().toLowerCase().trim().contains(
					searchKeyword.toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}

	@Step("Click on show results dropdown")
	public ProductsListPageObjects clickOnShowResultsDropdown() {
		showResultsDropdownLocator.click();
		return this;
	}

	@Step("Select result:{0}")
	public ProductsListPageObjects selectShowResults(int showItemsPerPage) throws Exception {
		String showItemsPerPageString = Integer.toString(showItemsPerPage);
		Thread.sleep(1500);
		switch (showItemsPerPageString) {
		case "12":
			getDriver().findElement(By.xpath("//ul/li[contains(text(),'12')]")).click();
			break;
		case "24":
			getDriver().findElement(By.xpath("//ul/li[contains(text(),'24')]")).click();
			break;
		case "48":
			getDriver().findElement(By.xpath("//ul/li[contains(text(),'48')]")).click();
			break;
		case "60":
			getDriver().findElement(By.xpath("//ul/li[contains(text(),'60')]")).click();
			break;
		default:
			throw new Exception("Invalid input");
		}
		return this;
	}

	@Step("Verify PN in SKU mode")
	public boolean assertPartNumberInSKUModeInProductListPage(String partNumber) {
		for (int i = 0; i < partNumberValueInPLPLocator.size(); i++) {
			if (partNumberValueInPLPLocator.get(i).getText().trim().equalsIgnoreCase(partNumber)) {
				return true;
			}
		}
		return false;
	}

	@Step("Verify part number:{0} in product list page")
	public ProductsListPageObjects verifyPartNumberInProductListPage(String partNumber) throws InterruptedException {
		try {
			if ((assertPartNumberInSKUModeInProductListPage(partNumber))) {
				Assert.assertTrue(assertPartNumberInSKUModeInProductListPage(partNumber),
						"Part Number is not displayed in product list page.");
			} else {
				Assert.assertTrue(verifyPartNumberInProductMode(partNumber),
						"Part number is not displayed in product list page.");
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(verifyPartNumberInProductMode(partNumber),
					"Part number is not displayed in product list page.");
		}
		return this;
	}

	@Step("Verify PN in product mode")
	private boolean verifyPartNumberInProductMode(String partNumber) throws InterruptedException {
		for (int i = 0; i < moreChoicesButtonLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", moreChoicesButtonLocator.get(i));
			Thread.sleep(3000);
			for (int j = 0; j < expandMoreChoicesInProductModeLocator.size(); j++) {
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
						expandMoreChoicesInProductModeLocator.get(j));
				Thread.sleep(2000);
				if (partNumberValueOfItemInProductModeLocator.getText().trim().equals(partNumber.trim())) {
					return true;
				}
			}
		}
		return false;
	}

	@Step("Verify UPC:{0} in product list page")
	public ProductsListPageObjects verifyUPCInProductListPage(String upc) throws InterruptedException {
		try {
			if ((assertUPCInSKUModeInProductListPage(upc))) {
				Assert.assertTrue(assertUPCInSKUModeInProductListPage(upc),
						"UPC Number is not displayed in product list page.");
			}
			Assert.assertTrue(verifyUPCInProductMode(upc), "UPC is not displayed in product list page.");
		} catch (NoSuchElementException e) {
			Assert.assertTrue(verifyUPCInProductMode(upc), "UPC is not displayed in product list page.");
		}
		return this;
	}

	@Step("Verify UPC in product mode")
	private boolean verifyUPCInProductMode(String upc) throws InterruptedException {
		for (int i = 0; i < moreChoicesButtonLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", moreChoicesButtonLocator.get(i));
			Thread.sleep(5000);
			for (int j = 0; j < expandMoreChoicesInProductModeLocator.size(); j++) {
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
						expandMoreChoicesInProductModeLocator.get(j));
				Thread.sleep(1500);
				if (upcValueInProductModeLocator.getText().trim().equals(upc)) {
					return true;
				}

			}
		}
		return false;
	}

	private boolean assertUPCInSKUModeInProductListPage(String upc) {
		for (int i = 0; i < upcValueInSKUModeLocator.size(); i++) {
			if (upcValueInSKUModeLocator.get(i).getText().trim().equalsIgnoreCase(upc)) {
				return true;
			}
		}

		return false;
	}

	public String[] getNamesOfTheItems() {

		String[] str = new String[items.size()];
		for (int i = 0; i < items.size(); i++) {
			str[i] = items.get(i).getText().trim();
		}
		return str;
	}

	@Step("Verify names of the products")
	public ProductsListPageObjects verifyNamesOfTheProducts(String[] nameOfTheItems) {
		for (int i = 0; i < items.size(); i++) {
			Assert.assertEquals(items.get(i).getText().trim(), nameOfTheItems[i]);
		}
		return this;
	}

	@Step("Verify whether breadcrumb contains {0}")
	public ProductsListPageObjects verifyBrandBreadCrumb(String nameOfTheBrand) {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.replace("/", "")
						.trim()
						.contains(nameOfTheBrand),
				"Breadcrumb does not contain the brand that is clicked. It is "
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim());
		return this;
	}

	@Step("Verify whether title contains {0}")
	public ProductsListPageObjects verifyTitleOfTheBrand(String nameOfTheBrand, String productName) throws Exception {
		Thread.sleep(2500);
		Assert.assertTrue(getDriver().getTitle().trim().contains(nameOfTheBrand),
				"The title does not contain the brand that was clicked. The title is " + getDriver().getTitle().trim()
						+ "." + "Asserting with data : " + nameOfTheBrand + " | " + productName + ".");
		Assert.assertTrue(getDriver().getTitle().trim().contains(" | " + productName),
				"The title does not contain the product name.");
		Assert.assertFalse(getDriver().getTitle().trim().startsWith("|"), "The title does start with | .");
		return this;

	}

	@Step("Verify number of items in the page")
	public ProductsListPageObjects verifyNumberOfItemsInThePage(String numberOfItemsAssociatedWithTheAttribute)
			throws InterruptedException {
		Thread.sleep(800);
		waiting.waitForVisibilityOfElements(allProductsLocator, 2);
		Assert.assertEquals(allProductsLocator.size(), Integer.parseInt(numberOfItemsAssociatedWithTheAttribute),
				"Number of products is not equal to the number of products that the attribute was associated to.");
		return this;
	}

	@Step("Get number of items")
	public String[] getItemIdsForSKUMode(int numberOfItems) {
		String itemIds[] = new String[numberOfItems];
		for (int i = 0; i <= numberOfItems - 1; i++) {
			itemIds[i] = partNumberValueInPLPLocator.get(i).getText().trim();

		}
		return itemIds;
	}

	@Step("Get specific PN")
	public String getSpecificPartNumber(int specificProduct) throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitTillPageLoads();
		waiting.waitForVisibilityOfElements(partNumberValueInPLPLocator, 15);
		log.info("part number :--->  " + partNumberValueInPLPLocator.get(specificProduct - 1).getText().trim());
		return partNumberValueInPLPLocator.get(specificProduct - 1).getText().trim();
	}

	@Step("Get specific short description")
	public String getSpecificShortDescription(int specificProduct) {
		waiting.waitForVisibilityOfElements(shortDescriptionLocator, 15);
		log.info("Item short description :--->  "
				+ shortDescriptionLocator.get(specificProduct - 1).getText().replace("More...", "").trim());
		return shortDescriptionLocator.get(specificProduct - 1).getText().replace("More...", "").trim();
	}

	@Step("Verify price precision in Item List/Grid page")
	public ProductsListPageObjects verifyPricePrecisionInProductListPage(String pnNumber, String pricePrecision) {

		WebElement specificItemPrice = getDriver()
				.findElement(By.xpath("//span[contains(@id,'" + pnNumber + "')][@class]"));

		Assert.assertEquals(specificItemPrice.getText().replace("$", "").replace("/ EACH", "").trim().split("\\.")[1].length(),
				Integer.parseInt(pricePrecision), "Price precision in not matched in Product List/Grid page");

		log.info("Verify Price precision in Product List/Grid page");
		return this;
	}

	@Step("Get PN's")
	public List<String> getPartNumbers() {
		return partNumberValueInPLPLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
	}

	@Step("Get specific PN of call for price item :{0}")
	public String getSpecificPartNumberOfCallForPriceItem(int specificProduct) {
		waiting.waitForVisibilityOfElements(partNumberValueOfCallForPriceItem, 15);
		return partNumberValueOfCallForPriceItem.get(specificProduct - 1).getText().trim();
	}

	@Step("Verify Product List page")
	public void verifyPLP(String searchText, String productName) throws Exception {
		verifyTitle(searchText, productName);
		verifyBreadCrumb(searchText);
		verifyListView();// by default it should be list view
		verifyDisplayOfViewButton();
		verifyDisplayOfSortBy();
		verifyDisplayOfSearchWithin();
		verifyDisplayOfFilterPanel();
	}

	@Step("Verify Product details in product list page")
	public ProductsListPageObjects verifyProductDetailsInListPage() {
		waiting.waitForVisibilityOfElements(itemTitleInSKUModeLocator, 15);
		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(utils.isElementDisplayed(itemImageInSKUModeLocator),
				"Image placeholder is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(itemTitleInSKUModeLocator), "Item Title not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(shortDescriptionLocator), "Short description is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(manufacturerNameInSKUModeLocator),
				"Manufacturer label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(manufacturerValueInSKUModeLocator),
				"Manufacturer value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(brandNameInSKUModeLocator), "Brand label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(brandValueInSKUModeLocator), "Brand Value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(partNumberLabelInSKUModeLocator),
				"Part Number label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(partNumberValueInPLPLocator),
				"Part Number value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(manufacturerPartNumberLabelInSKUModeLocator),
				"MPN label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(manufacturerPartNumberLabelInSKUModeLocator),
				"MPN label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(upcLabelInSKUModeLocator), "UPC label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(upcValueInSKUModeLocator), "UPC value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(availabilityLabelInSKUModeLocator),
				"Availability label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(availabilityValueInSKUModeLocator),
				"Availability Value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(minOrderQtyLabelInSKUModeLocator),
				"Min order qty label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(minOrderQtyValueInSKUModeLocator),
				"Min order qty value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(qtyIntervalLabelInSKUModeLocator),
				"Qty interval label is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(qtyIntervalValueInSKUModeLocator),
				"Qty interval value is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(itemsPriceLocator), "Item price is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(qtyTextBoxesLocator), "Qty textbox is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(addToCartButton), "Add To Cart is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(addToProductGropLocators),
				"Add To Product Group is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(selectItemCheckboxLocators),
				"Select Item checkbox is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(addToCompareCheckboxesInSKUModeLocator),
				"Compare checkbox is not displayed");

		softAssert.assertAll();

		log.info("Verified Product details in product list page");
		return this;
	}

	@Step("Verify Product details in product mode")
	public ProductsListPageObjects verifyProductDetailsInListPage_ProductMode(String viewMode) {
		waiting.waitForVisibilityOfElements(itemTitleInSKUModeLocator, 15);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(utils.isElementDisplayed(itemTitleInSKUModeLocator), "Item Title not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(itemImageInSKUModeLocator),
				"Image placeholder is not displayed");

		softAssert.assertTrue(utils.isElementDisplayed(shortDescriptionPDLocator), "Short description is not displayed");

		if (viewMode.equalsIgnoreCase("listview"))
			softAssert.assertTrue(utils.isElementDisplayed(moreChoicesButtonLocator),
					"View more choice button is not displayed");

		if (viewMode.equalsIgnoreCase("gridview"))
			softAssert.assertTrue(utils.isElementDisplayed(moreChoicesButtonLocator_GridView),
					"View more choice button is not displayed");

		softAssert.assertAll();

		log.info("Verified Product details in product mode");
		return this;
	}

	@Step("Verify Display Of View Results Per Page")
	public ProductsListPageObjects verifyDisplayOfViewResultsPerPage() {
		Assert.assertTrue(itemsPerPageLocator.isDisplayed(), "Change View Button is not displayed");
		return this;
	}

	@Step("Click on add items option")
	public ProductsListPageObjects clickOnAddItemsOptions() {
		addItemsOptionsLocator.click();
		return this;
	}

	@Step("Select add item options: {0}")
	public ProductsListPageObjects selectAddItemsOptions(String option) {
		getDriver()
				.findElement(By.xpath(
						"//dl[@id='bulkAction']/dd/descendant::li/descendant::span[contains(text(),'" + option + "')]"))
				.click();
		return this;
	}

	@Step("Verify item brand in PLP")
	public ProductsListPageObjects verifyItemBrandInPLP(String searchTextForBrand) {
		waiting.waitForVisibilityOfElements(brandValueLocator, 8);
		Assert.assertEquals(brandValueLocator.get(0).getText(), searchTextForBrand, "Invalid brand search");
		return this;
	}

	@Step("Click on add to product group from bulk option")
	public ProductsListPageObjects clickOnAddToProductGroupFromBulkOption() throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", bulkOptionAddToProductGroup);
		Thread.sleep(600);
		return this;
	}

	// functions with loggers

	@Step("Verify breadcrumb to have {0}")
	public ProductsListPageObjects verifyBreadCrumb(String lastBreadcrumb) {
		logger = Logger.getLogger("verifyBreadCrumb");
		Assert.assertEquals(breadCrumbs.get(breadCrumbs.size() - 1).getText().toUpperCase().replace("|", "").trim(),
				lastBreadcrumb.toUpperCase().trim());

		logger.info("Breadcrumb of the page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("/", "").trim() + "] and " + "["
				+ lastBreadcrumb.toUpperCase().trim() + "]");
		return this;
	}

	@Step("Verify Level two Category Bread Crumb.")
	public ProductsListPageObjects verifyLevelTwoCategoryBreadCrumb(String level2Category) {
		log = Logger.getLogger("verifyLevelTwoCategoryBreadCrumb");

		waiting.waitForVisibilityOfElements(breadCrumbs, 10);
		Assert.assertEquals(breadCrumbs.get(breadCrumbs.size() - 2).getText().trim(), level2Category.trim(),
				"Level Two Category Bread Crumb is not :" + level2Category.trim() + ".It is "
						+ breadCrumbs.get(breadCrumbs.size() - 2).getText().trim());
		log.info("Level Two Category Bread Crumb  has been verified" + " with [Actual] and [Expected] value as " + "["
				+ breadCrumbs.get(breadCrumbs.size() - 1).getText().trim() + "] and " + "[" + level2Category.trim()
				+ "]");
		return this;
	}

	@Step("Verify grid view")
	public ProductsListPageObjects verifyGridView() throws Exception {
		logger = Logger.getLogger("verifyGridView");
		for (int i = 0; i < listOfProductsLocator.size(); i++) {
			Assert.assertTrue(listOfProductsLocator.get(i).getAttribute("class").equals("gridView"),
					"class name is not grid view. Class name is " + listOfProductsLocator.get(i).getAttribute("class")
							+ ". This is for the " + i + " product.");

		}
		logger.info("Grid view functionality has been verified");
		return this;
	}

	@Step("Verify list view")
	public ProductsListPageObjects verifyListView() {
		logger = Logger.getLogger("verifyListView");
		waiting.waitForVisibilityOfElements(listOfProductsLocator, 10);
		for (int i = 0; i < listOfProductsLocator.size(); i++) {
			Assert.assertTrue(listOfProductsLocator.get(i).getAttribute("class").equals("listView"),
					"class name is not list view");
		}
		logger.info("verified  list view");
		return this;
	}

	@Step("Verify display of view button")
	public ProductsListPageObjects verifyDisplayOfViewButton() {
		logger = Logger.getLogger("verifyDisplayOfViewButton");
		Assert.assertTrue(changeViewButtonLocatorListView.isDisplayed(), "Sort By Dropdown is not displayed");
		logger.info("View button has been verified");
		return this;
	}

	@Step("Verify display of sort by")
	public ProductsListPageObjects verifyDisplayOfSortBy() {
		logger = Logger.getLogger("verifyDisplayOfSortBy");
		Assert.assertTrue(sortByDropdownLocator.isDisplayed(), "Sort By Dropdown is not displayed");
		logger.info("verified sort by dropdown");
		return this;
	}

	@Step("Verify display of search with in")
	public ProductsListPageObjects verifyDisplayOfSearchWithin() {
		logger = Logger.getLogger("verifyDisplayOfSearchWithin");
		Assert.assertTrue(filterSearchTextboxLocator.isDisplayed(), "Filter Search Textbox is not displayed");
		logger.info("Filter Search Textbox is  displayed");
		return this;
	}

	@Step("Verify display of Filter panel")
	public ProductsListPageObjects verifyDisplayOfFilterPanel() {
		logger = Logger.getLogger("verifyDisplayOfFilterPanel");
		Assert.assertTrue(filterPanelLocator.isDisplayed(), "Filter Search Textbox is not displayed");
		logger.info("Filter Panel is displayed");
		return this;
	}

	@Step("Select and Verify Items per page")
	public ProductsListPageObjects selectAndVerifyItemsPerPage(String[] noOfItems) throws InterruptedException {
		Thread.sleep(1000);
		for (int i = 0; i < noOfItems.length; i++) {
			getDriver().findElement(By.xpath("//a[contains(@onclick," + noOfItems[i] + ")]/span")).click();
			logger.info("clicked on Items per page:" + noOfItems[i]);
			Thread.sleep(1000);
			Assert.assertTrue(itemTitleInSKUModeLocator.size() <= Integer.parseInt(noOfItems[i]),
					"Number of items per page is not matched with the selected option:" + noOfItems[i]);
		}
		log.info("verified Items per page");
		return this;
	}

	@Step("Verify select item checkbox before login")
	public ProductsListPageObjects verifySelectItemCheckboxBeforeLogin(int specificItem) {
		logger = Logger.getLogger("verifySelectItemCheckboxBeforeLogin");
		Assert.assertEquals(selectCheckboxLocator.get(0).getAttribute("data-addtocartflag").trim(), "Y",
				"Select checkbox is not disabled");
		logger.info("Selected Ttem checkbox before login has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + selectCheckboxLocator.get(0).getAttribute("data-addtocartflag").trim() + "] and " + "[" + "Y"
				+ "]");
		return this;
	}

	public boolean verifyNoOfItemsDisplayedIsLessThanOrEqualToTheNumberOfItemsSelected(int showItemsPerPage) {
		logger = Logger.getLogger("verifyNoOfItemsDisplayedIsLessThanOrEqualToTheNumberOfItemsSelected");
		return listOfProductsLocator.size() <= showItemsPerPage;
	}

	@Step("Verify title contains {0}")
	public ProductsListPageObjects verifyTitle(String lastBreadcrumb, String productName) throws Exception {
		logger = Logger.getLogger("verifyTitle");
		Assert.assertEquals(getDriver().getTitle().trim(), lastBreadcrumb + " | " + productName);

		logger.info("Title of the page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + lastBreadcrumb + " | " + productName + "]");
		return this;
	}

	@Step("Click on change view")
	public ProductsListPageObjects clickOnChangeView() throws Exception {
		logger = Logger.getLogger("clickOnChangeView");
		Thread.sleep(1000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", changeViewButtonLocatorListView);
		Thread.sleep(1500);
		logger.info("clicked on ChangeView icon");
		return this;
	}

	@Step("Set to List view")
	public ProductsListPageObjects changeToListView() throws Exception {
		logger = Logger.getLogger("clickOnChangeView");
		Thread.sleep(1000);
		if (changeViewButtonLocatorListView.isDisplayed())
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", changeViewButtonLocatorListView);
		logger.info("defaulted to list view");
		return this;
	}

	@Step("Click on change view before login")
	public ProductsListPageObjects clickOnChangeViewBeforeLogin() throws InterruptedException {

		waiting.waitForVisibilityOfElement(By.xpath("//div//a[@id='gridView']"), 10);

		WebElement changeViewLocator = getDriver().findElement(By.xpath("//div//a[@id='gridView']"));

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", changeViewLocator);

		Thread.sleep(1000);
		return this;
	}

	@Step("Click to change view")
	public ProductsListPageObjects clickOnChangeViewInListPage() throws InterruptedException {

		logger = Logger.getLogger("clickOnChangeView");
		waiting.waitForVisibilityOfElement(changeViewButtonLocatorGridView, 10);
		changeViewButtonLocatorGridView.click();
		Thread.sleep(1000);
		logger.info("clicked on ChangeView ico");
		return this;

	}

	@Step("Verify Item Category in list page")
	public ProductsListPageObjects verifyItemCategoryInPLP(String searchText) {
		waiting.waitForVisibilityOfElements(shortDescriptionLocator, 8);
		Assert.assertTrue(
				shortDescriptionLocator.get(0).getText().trim().toLowerCase().contains(searchText.trim().toLowerCase()),
				"Invalid Item search");
		return this;
	}

	@Step("Click on select Item Checkbox in Product List/Grid Page")
	public ProductsListPageObjects clickOnSelectItemCheckbox(int numberOfClick) throws InterruptedException {

		logger = Logger.getLogger("clickOnSelectItemCheckbox");

		for (int i = 0; i <= numberOfClick; i++) {
			waiting.waitForVisibilityOfElements(selectItemCheckboxLocators, 10);

			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					selectItemCheckboxLocators.get(i));
			Thread.sleep(1200);
		}

		logger.info("clicked on select Item Checkbox in Product List/Grid Page");

		return this;

	}

	@Step("Select an item Add to cart/product group checkbox Under Product Mode:{0}")
	public ProductsListPageObjects clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(
			int specificCheckbox) throws Exception {

		waiting.waitForVisibilityOfElements(selectItemCheckboxLocatorsInProductMode, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				selectItemCheckboxLocatorsInProductMode.get(specificCheckbox - 1));

		Thread.sleep(5000);
		return this;
	}

	@Step("Click on specific select check box {0}")
	public ProductsListPageObjects clickOnSpecificSelectCheckbox(int number) throws InterruptedException {
		waiting.waitForVisibilityOfElements(selectItemCheckboxLocators, 5);
		selectItemCheckboxLocators.get(number - 1).click();
		Thread.sleep(600);
		return this;
	}

	@Step("Click on Bulk Option DropDown in Product List/Grid Page")
	public ProductsListPageObjects clickOnBulkOptionsInProductListPage() throws InterruptedException {
		waiting.waitForVisibilityOfElement(bulkOptionLocator, 10);
		logger = Logger.getLogger("clickOnBulkOptionsInProductListPage");

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", bulkOptionLocator);
		Thread.sleep(1200);

		logger.info("clicked on Bulk Option DropDown in Product List/Grid Page");
		return this;
	}

	@Step("Click on Add Item To Cart Link Under Bulk Option  in Product List/Grid Page")
	public ProductsListPageObjects clickOnAddItemToCartFromBulkOptionInListPage() throws InterruptedException {

		waiting.waitForVisibilityOfElement(addItemToCartLocator, 10);

		logger = Logger.getLogger("clickOnAddItemToCartFromBulkOptionInListPage");

		addItemToCartLocator.click();
		Thread.sleep(2000);

		logger.info("clicked on Add Item To Cart Link Under Bulk Option  in Product List/Grid Page");
		return this;
	}

	@Step("Verify display of Text Fields In List And Grid Page")
	public ProductsListPageObjects verifyTextFieldsDisplayedInListGridPage() {
		SoftAssert softAssert = new SoftAssert();
		logger = Logger.getLogger("verifyTextFieldsDisplayedInListGridPage");
		softAssert.assertTrue(partNumberLabelInSKUModeLocator.get(0).isDisplayed(),
				"Part Number Label is not displayed");

		logger.info("Part Number Label is displayed");

		softAssert.assertTrue(manufacturerPartNumberLabelInSKUModeLocator.get(0).isDisplayed(),
				"Manufacturer Part Number Label is not displayed");

		logger.info(" Manufacturer Part Number Label is displayed");

		softAssert.assertTrue(availabilityLabelInSKUModeLocator.get(0).isDisplayed(),
				"Availability Label is not displayed");

		logger.info("Availability Label is displayed");

		softAssert.assertTrue(minOrderQtyLabelInSKUModeLocator.get(0).isDisplayed(),
				"Min Order Qty Label is not displayed");

		logger.info("Min Order Qty Label is displayed");

		softAssert.assertTrue(qtyIntervalLabelInSKUModeLocator.get(0).isDisplayed(),
				"Qty Interval Label is not displayed");

		logger.info("Qty Interval Label is displayed");

		softAssert.assertTrue(itemImageInSKUModeLocator.get(0).isDisplayed(), "Item Image is not displayed");

		logger.info("Item Image is displayed");

		softAssert.assertTrue(itemTitleInSKUModeLocator.get(0).isDisplayed(), "Item Title is not displayed");

		logger.info("Item Title is displayed");

		softAssert.assertTrue(itemShortDescriptionInSKUModeLocator.get(0).isDisplayed(),
				"Item Short Description is not displayed");

		logger.info("Item Short Description is displayed");

		softAssert.assertTrue(manufacturerNameInSKUModeLocator.get(0).isDisplayed(),
				"Manufacturer name is not displayed");

		logger.info("Manufacturer name is displayed");

		softAssert.assertTrue(brandNameInSKUModeLocator.get(0).isDisplayed(), "Brand name is not displayed");

		logger.info("Brand name is displayed");

		softAssert.assertTrue(qtyTextBoxesLocator.get(0).isDisplayed(), "Qty Text Box is not displayed");

		logger.info("Qty Text Box is displayed");

		softAssert.assertTrue(myProductGroupsLocator.get(0).isDisplayed(), "My Product Group Link Is not displayed");
		softAssert.assertAll();

		logger.info("My Product Group Link Is displayed");

		return this;

	}

	@Step("Select product group from list {0}")
	public ProductsListPageObjects selectProductGroupFromList(String productGroupName) throws Exception {
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//a[@data-groupname='" + productGroupName.trim() + "']")).click();
		return this;
	}

	@Step("Get Qty of specific item {0}")
	public String getQuantityOfSpecificItem(int specificItem) {
		waiting.waitForVisibilityOfElements(quantityIntervalValueLocator, 5);
		return quantityIntervalValueLocator.get(specificItem - 1).getText().trim();
	}

	@Step("Get Qty Interval of item {0}")
	public String getQuantityIntervalOfSpecificItem(int specificItem) {
		waiting.waitForVisibilityOfElements(qtyTextBoxesLocator, 5);
		return qtyTextBoxesLocator.get(specificItem - 1).getAttribute("value").trim();
	}

	@Step("Get MPN of specific item {0}")
	public String getSpecificMPN(int specificProduct) {
		waiting.waitForVisibilityOfElements(mpnValueInSKUModeLocator, 5);
		return mpnValueInSKUModeLocator.get(specificProduct - 1).getText().trim();
	}

	@Step("Verify Displays of Bulk options drop down with Add to Cart and Add to Product Group")
	public ProductsListPageObjects verifyDisplayOfBulkOptionDropDown(String bulkOptionsDropDownValues)
			throws InterruptedException {

		logger = Logger.getLogger("verifyDisplayOfBulkOptionDropDown");

		String[] expectedBulkOptionsDropDownValues = bulkOptionsDropDownValues.split(",");

		waiting.waitForVisibilityOfElement(bulkOptionLocator, 10);

		softAssertion.assertTrue(bulkOptionLocator.isDisplayed(), "Bulk Option DropDown is not displayed");

		bulkOptionLocator.click();
		Thread.sleep(500);

		logger.info("Clicked on Bulk Options.");

		List<WebElement> actualBulkOptionsDropDownValues = getDriver()
				.findElements(By.xpath("//div[@class='bulkActionBtn']//span[@class='text']"));

		for (int i = 0; i < actualBulkOptionsDropDownValues.size(); i++) {

			softAssertion.assertEquals(actualBulkOptionsDropDownValues.get(i).getText().trim(),
					expectedBulkOptionsDropDownValues[i].trim(),
					"Bulk Options Dropdown values are not " + expectedBulkOptionsDropDownValues[i].trim() + ". It is :"
							+ actualBulkOptionsDropDownValues.get(i).getText().trim());

			logger.info("Bulk Options Dropdown values has been verified" + " with [Actual] and [Expected] value as "
					+ "[" + actualBulkOptionsDropDownValues.get(i).getText().trim() + "] and " + "["
					+ expectedBulkOptionsDropDownValues[i].trim() + "]");
		}
		softAssertion.assertAll();
		return this;
	}

	@Step("Verify display of bulk option dropdown before login")
	public ProductsListPageObjects verifyDisplayOfBulkOptionDropDownBeforeLogin(String bulkOptionsDropDownValues)
			throws InterruptedException {

		String[] expectedBulkOptionsDropDownValues = bulkOptionsDropDownValues.split(",");
		waiting.waitForVisibilityOfElement(bulkOptionLocator, 10);

		Assert.assertTrue(bulkOptionLocator.isDisplayed(), "Bulk Option DropDown is not displayed");

		bulkOptionLocator.click();
		Thread.sleep(500);

		List<WebElement> actualBulkOptionsDropDownValues = getDriver()
				.findElements(By.xpath("//div[@class='bulkActionBtn']//span[@class='text']"));

		for (int i = 0; i < actualBulkOptionsDropDownValues.size(); i++) {

			Assert.assertEquals(actualBulkOptionsDropDownValues.get(i).getText().trim(),
					expectedBulkOptionsDropDownValues[i].trim(),
					"Bulk Options Dropdown values are not " + expectedBulkOptionsDropDownValues[i].trim() + ". It is :"
							+ actualBulkOptionsDropDownValues.get(i).getText().trim());
		}

		return this;
	}

	@Step("Verify display of View All Choices Button For Product Mode Items In Product List Page")
	public ProductsListPageObjects verifyDisplayOfViewOfChoicesButtonsInProductListGridPage() {

		logger = Logger.getLogger("verifyDisplayOfViewOfChoicesButtonsInProductListGridPage");

		waiting.waitForVisibilityOfElements(moreChoicesButtonLocator, 10);
		for (int i = 0; i < moreChoicesButtonLocator.size(); i++) {

			Assert.assertTrue(moreChoicesButtonLocator.get(i).isDisplayed(),
					"View All Choice Buttons are not displayed in product list/grid page.");

			logger.info("View All Choice Buttons has been displayed in product list/grid page.");
		}

		return this;
	}

	@Step("Verify display of View All Choice Button For Product Mode Items")
	public ProductsListPageObjects verifyOfToggleButtonToViewProductModeItemInfo() {
		log = Logger.getLogger("verifyOfToggleButtonToViewProductModeItemInfo");
		waiting.waitForVisibilityOfElements(toggleButtonToViewProductModeItemInformationLocator, 10);
		for (int i = 0; i < toggleButtonToViewProductModeItemInformationLocator.size(); i++) {
			Assert.assertTrue(toggleButtonToViewProductModeItemInformationLocator.get(i).isDisplayed(),
					"Toggle Buttons are not displayed in product list/grid page.");
			log.info("Toggle Buttons have been displayed in product list/grid page.");
		}
		return this;
	}

	@Step("Click on Toggle Button link Under Product Mode Items : {0}")
	public ProductsListPageObjects clickOnToggleButtonOfProductModeItem(int specificToggleButton)
			throws InterruptedException {
		log = Logger.getLogger("clickOnToggleButtonOfProductModeItem");
		waiting.waitForVisibilityOfElements(toggleButtonToViewProductModeItemInformationLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				toggleButtonToViewProductModeItemInformationLocator.get(specificToggleButton - 1));
		Thread.sleep(1500);

		log.info("clicked on Specific Toggle Button link Under Product Mode Items : " + specificToggleButton);
		return this;
	}

	@Step("Click on Child Item Under Product Mode Items : {0}")
	public ProductsListPageObjects clickOnChildItemOfProductMode(int specificChildItem) throws InterruptedException {
		log = Logger.getLogger("clickOnChildItemOfProductMode");
		waiting.waitForVisibilityOfElements(childItemsLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				childItemsLocator.get(specificChildItem - 1));
		Thread.sleep(1200);
		log.info("clicked on Child Item Under Product Mode Items");
		return this;
	}

	@Step(" Verify Of Displays items which are assigned to that particular product with below details:{0} After clicking on Toggle Button")
	public ProductsListPageObjects verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButton(
			String productModeItemsFields) {
		String[] expectedProductModeItemsFields = productModeItemsFields.split(",");
		for (int i = 1; i < itemFieldsLocators.size(); i++) {
			if (i < 6) {
				Assert.assertEquals(itemFieldsLocators.get(i).getText().trim(),
						expectedProductModeItemsFields[i].trim());
			} else {
				Assert.assertEquals(itemFieldsLocators.get(i).getAttribute("title").trim(),
						expectedProductModeItemsFields[i].trim());
			}
		}
		return this;
	}

	@Step("Verify display item and fields in Product mode")
	public ProductsListPageObjects verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion3Page(
			String productModeItemsFields) {

		String[] expectedProductModeItemsFields = productModeItemsFields.split(",");

		for (int i = 1; i < itemFieldsLocators.size(); i++) {

			if (i < 7) {

				Assert.assertEquals(itemFieldsLocators.get(i).getText().trim(),
						expectedProductModeItemsFields[i].trim());
			}

			else {

				Assert.assertEquals(itemFieldsLocators.get(i).getAttribute("title").trim(),
						expectedProductModeItemsFields[i].trim());
			}
		}

		return this;

	}

	@Step("Verify display item and fields in Product mode")
	public ProductsListPageObjects verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion4Page(
			String productModeItemsFields) {
		String[] expectedProductModeItemsFields = productModeItemsFields.split(",");

		for (int i = 1; i < itemFieldsLocators.size(); i++) {

			if (i < 7) {

				Assert.assertEquals(itemFieldsLocators.get(i).getText().trim(),
						expectedProductModeItemsFields[i].trim());
			}

			else {

				Assert.assertEquals(itemFieldsLocators.get(i).getAttribute("title").trim(),
						expectedProductModeItemsFields[i].trim());
			}
		}

		return this;

	}

	@Step(" Verify Of Displays items which are assigned to that particular product with below details:{0} After clicking on View All Choices Button")
	public ProductsListPageObjects verifyDisplayItemsAndFieldsAfterClickingOnViewAllChoiceButton(
			String productModeItemsFields) {
		String[] expectedProductModeItemsFields = productModeItemsFields.split(",");
		for (int i = 1; i < itemFieldsLocators.size(); i++) {
			if (i < 6) {
				Assert.assertEquals(itemFieldsLocators.get(i).getText().trim(),
						expectedProductModeItemsFields[i].trim());
			} else {
				Assert.assertEquals(itemFieldsLocators.get(i).getAttribute("title").trim(),
						expectedProductModeItemsFields[i].trim());
			}
		}
		return this;
	}

	@Step("Verify display item and fields in Product mode")
	public ProductsListPageObjects verifyDisplayItemsAndFieldsAfterClickingOnViewAllChoiceButtonInProductVersion3Page(
			String productModeItemsFields) {

		String[] expectedProductModeItemsFields = productModeItemsFields.split(",");

		for (int i = 1; i < itemFieldsLocators.size(); i++) {

			if (i < 7) {

				Assert.assertEquals(itemFieldsLocators.get(i).getText().trim(),
						expectedProductModeItemsFields[i].trim());
			}

			else {

				Assert.assertEquals(itemFieldsLocators.get(i).getAttribute("title").trim(),
						expectedProductModeItemsFields[i].trim());
			}
		}

		return this;
	}

	@Step("Click on View All Choices Button link Under Product Mode Items : {0} In Product list Page")
	public ProductsListPageObjects clickOnViewAllChoiceButton(int specificViewChoiceButton)
			throws InterruptedException {
		waiting.waitForVisibilityOfElements(moreChoicesButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				moreChoicesButtonLocator.get(specificViewChoiceButton - 1));
		Thread.sleep(1200);
		return this;
	}

	@Step("Click on Add Item To Cart Link Under Bulk Option  in Product List/Grid Page")
	public ProductsListPageObjects clickOnAddItemToGroupFromBulkOptionInListPage() throws InterruptedException {

		waiting.waitForVisibilityOfElement(bulkOptionAddToProductGroup, 10);

		logger = Logger.getLogger("clickOnAddItemToCartFromBulkOptionInListPage");

		bulkOptionAddToProductGroup.click();
		Thread.sleep(2000);

		logger.info("clicked on Add Item To Cart Link Under Bulk Option  in Product List/Grid Page");
		return this;
	}

	public ProductsListPageObjects scrollThePageTillProductMode() {

		utils.scrollThePageForListOfElements(moreChoicesButtonLocator, 0);
		return this;
	}

	@Step("Scroll The Page Till Toggle Button To View Product Mode Items.")
	public ProductsListPageObjects scrollThePageTillToggleButtonToViewProductModeItems() {
		log = Logger.getLogger("scrollThePageTillToggleButtonToViewProductModeItems");
		utils.scrollThePageForListOfElements(toggleButtonToViewProductModeItemInformationLocator, 0);
		log.info("Scroll The Page Till Toggle Button To View Product Mode Items.");
		return this;
	}

	public String getCompareItemsCount() {
		waiting.waitForVisibilityOfElement(compareItemsCount, 10);
		String noOfCount = compareItemsCount.getText().trim();
		return noOfCount;
	}

	@Step("Verify Compare item(s) count will be incresed {0}")
	public ProductsListPageObjects verifyWhetherCompareItemIncreased(String compareItemCount) {

		int newCount = Integer.parseInt(compareItemsCount.getText().trim());

		Assert.assertTrue(Integer.parseInt(compareItemCount) < newCount, "Number of item is not increased");
		return this;

	}

	@Step("Verify newly created product group")
	public ProductsListPageObjects verifyDisplayOfCreatedProductGroup() {
		waiting.waitForVisibilityOfElements(createdProductGroupLocator, 10);
		for (int i = 0; i < createdProductGroupLocator.size(); i++) {
			Assert.assertTrue(createdProductGroupLocator.get(i).isDisplayed(), "Created Product List is not displayed");
		}
		return this;
	}

	@Step("Verify text box to enter new product group name")
	public ProductsListPageObjects verifyTextBoxToEnterNewProductGroupName() {
		waiting.waitForVisibilityOfElement(textBoxLocatorToEnterNewgroupName, 10);
		Assert.assertTrue(textBoxLocatorToEnterNewgroupName.isDisplayed(),
				"Text Box is not displayed to enter new product group name");
		return this;
	}

	@Step("Click on {0}st/nd/rd/th compare checkboxes")
	public ProductsListPageObjects clickOnSpecificAddToCompareCheckboxInSKUMode(int specificCheckboxInSKUMode)
			throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCompareCheckboxesInSKUModeLocator.get(specificCheckboxInSKUMode - 1));
		log.info("selected the item :" + specificCheckboxInSKUMode);
		return this;
	}

	@Step("Click on {0}st/nd/rd/th Add To Cart button")
	public ProductsListPageObjects clickOnSpecificAddToCartButtonInSKUMode(int specificButtonInSKUMode)
			throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCartButton.get(specificButtonInSKUMode - 1));
		log.info("Clicked on Add to Cart of the item" + specificButtonInSKUMode);
		Thread.sleep(3500);
		return this;
	}

	@Step("Enter {1} to the item: {0}")
	public ProductsListPageObjects inputQtyForSpecificItemInSKUMode(int specificItemQty, String value)
			throws Exception {
		qtyTextBoxesLocator.get(specificItemQty - 1).clear();
		qtyTextBoxesLocator.get(specificItemQty - 1).sendKeys(value);
		return this;
	}

	@Step("Click on {0}st/nd/rd/th compare checkboxes")
	public ProductsListPageObjects clickOnSpecificAddToCompareCheckboxCallForPrice(int specificCheckbox) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCompareCheckboxCallForPrice.get(specificCheckbox - 1));
		return this;
	}

	@Step("Click and Verify GridView or List View")
	public ProductsListPageObjects verifyGridOrListView() throws Exception {
		logger = Logger.getLogger("verifyGridView and List View");
		if (utils.isElementDisplayed(changeViewButtonLocatorGridView)) {
			Assert.assertEquals(listViewTooltip.get(0).getAttribute("title").trim(), "List View");
			changeViewButtonLocatorListView.click();
			verifyGridView();
		} else if (utils.isElementDisplayed(changeViewButtonLocatorListView)) {
			verifyListView();
		}
		return this;
	}

	@Step("Verify Sort By dropdown functionality")
	public ProductsListPageObjects selectAndVerifySortByOptions(String[] sortByOpts) throws InterruptedException {

		waiting.waitForVisibilityOfElement(sortByDropdownLocator, 10);

		List<String> MPNs_Before, MPNs_Asc, MPNs_Desc;

		List<String> PNs_Before, PNs_Asc, PNs_Desc;

		List<String> UPCs_Before, UPCs_Asc, UPCs_Desc;

		for (int i = 0; i < sortByOpts.length; i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByDropdownLocator);
			// sortByDropdownLocator.click();
			Thread.sleep(500);

			switch (sortByOpts[i].trim()) {
			case "MPN (Asc)":
				getDriver().findElement(By.xpath("//span[contains(text(),'MPN (Asc)')]")).click();
				Thread.sleep(1000);
				MPNs_Before = mpnValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				MPNs_Asc = mpnValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				Collections.sort(MPNs_Asc);
				Assert.assertEquals(MPNs_Before, MPNs_Asc, "Sort by MPN (Asc) functionality failed");
				break;

			case "MPN (Desc)":
				getDriver().findElement(By.xpath("//span[contains(text(),'MPN (Desc)')]")).click();
				Thread.sleep(1000);
				MPNs_Before = mpnValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				MPNs_Desc = mpnValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				Collections.sort(MPNs_Desc, Collections.reverseOrder());
				Assert.assertEquals(MPNs_Before, MPNs_Desc, "Sort by MPN (Desc) functionality failed");
				break;

			case "UPC (Asc)":
				getDriver().findElement(By.xpath("//span[contains(text(),'UPC (Asc)')]")).click();
				Thread.sleep(1000);

				UPCs_Before = upcValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				UPCs_Asc = upcValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());

				Collections.sort(UPCs_Asc);
				Assert.assertEquals(UPCs_Before, UPCs_Asc, "Sort by UPC (Asc) functionality failed");
				break;

			case "UPC (Desc)":
				getDriver().findElement(By.xpath("//span[contains(text(),'UPC (Desc)')]")).click();
				Thread.sleep(1000);

				UPCs_Before = upcValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				UPCs_Desc = upcValueInSKUModeLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				Collections.sort(UPCs_Desc, Collections.reverseOrder());
				Assert.assertEquals(UPCs_Before, UPCs_Desc, "Sort by UPC (Asc) functionality failed");
				break;

			case "Part# (Asc)":
				getDriver().findElement(By.xpath("//span[contains(text(),'Part# (Asc)')]")).click();
				Thread.sleep(1000);
				PNs_Before = partNumberValueInPLPLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				PNs_Asc = partNumberValueInPLPLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				Collections.sort(PNs_Asc);
				Assert.assertEquals(PNs_Before, PNs_Asc, "Sort by Part# (Asc) functionality failed");
				break;

			case "Part# (Desc)":
				getDriver().findElement(By.xpath("//span[contains(text(),'Part# (Desc)')]")).click();
				Thread.sleep(1000);
				PNs_Before = partNumberValueInPLPLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				PNs_Desc = partNumberValueInPLPLocator.stream().map(s -> s.getText()).collect(Collectors.toList());
				Collections.sort(PNs_Desc, Collections.reverseOrder());
				Assert.assertEquals(PNs_Before, PNs_Desc, "Sort by Part# (Desc) functionality failed");
				break;
			default:
				Assert.fail("Invalid sort by option");
				break;
			}
			Thread.sleep(800);
		}
		log.info("Verified Sort By dropdown functionality");
		return this;
	}

	/*
	 * public ProductsListPageObjects filterAndVerifySearchByCategories(String[]
	 * categories) throws InterruptedException {
	 * waiting.waitForVisibilityOfElement(categorySearchTextbox, 10); int
	 * itemsCountInFilterCategory = 0; for (String category : categories) {
	 * categorySearchTextbox.sendKeys(category);
	 * 
	 * WebElement ele = getDriver() .findElement(By.xpath(
	 * "//div[@id='col_Category']//li[contains(@style,'list-item;')]//span[1]"));
	 * 
	 * // fetch items count from category itemsCountInFilterCategory += Integer
	 * .parseInt(ele.getText().substring(ele.getText().indexOf("(") + 1,
	 * ele.getText().indexOf(")")));
	 * 
	 * ele.click();
	 * 
	 * Thread.sleep(800); }
	 * 
	 * // Verify search result count matches with items count in category
	 * waiting.waitForVisibilityOfElement(searchResultsHeaderLocator, 10); int
	 * resultcount = Integer.parseInt(searchResultsHeaderLocator.getText()
	 * .replaceAll(searchResultsHeaderLocator.getText()
	 * .substring(searchResultsHeaderLocator.getText().indexOf("result")), "")
	 * .trim()); Assert.assertEquals(resultcount, itemsCountInFilterCategory,
	 * "Result count mismatch");
	 * 
	 * return this; }
	 */
	@Step("Verify search by Categories by filter")
	public ProductsListPageObjects filterAndVerifySearchByCategories(String[] categories) throws InterruptedException {
		waiting.waitForVisibilityOfElement(categorySearchTextbox, 10);
		int itemsCountInFilterCategory = 0;
		for (String category : categories) {
			categorySearchTextbox.sendKeys(category);

			WebElement ele = getDriver()
					.findElement(By.xpath("//div[@id='col_Category']//span[contains(text(),'" + category + "')]"));

			// fetch items count from category
			itemsCountInFilterCategory += Integer
					.parseInt(ele.getText().substring(ele.getText().indexOf("(") + 1, ele.getText().indexOf(")")));

			ele.click();
			if (utils.isElementDisplayed(categoryFilterButtonLocator))
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", categoryFilterButtonLocator);
			Thread.sleep(1500);
		}

		// Verify search result count matches with items count in category
		waiting.waitForVisibilityOfElement(searchResultsHeaderLocator, 10);
		int resultcount = Integer.parseInt(searchResultsHeaderLocator.getText()
				.replaceAll(searchResultsHeaderLocator.getText()
						.substring(searchResultsHeaderLocator.getText().indexOf("result")), "")
				.trim());
		Assert.assertEquals(resultcount, itemsCountInFilterCategory, "Result count mismatch");

		return this;
	}

	@Step("Verify search by brand by filter")
	public ProductsListPageObjects filterAndVerifySearchByBrands(String[] brands) throws InterruptedException {
		waiting.waitForVisibilityOfElement(categorySearchTextbox, 10);
		int itemsCountInFilterBrand = 0;
		for (String brand : brands) {
			brandSearchTextbox.sendKeys(brand);
			Thread.sleep(3500);
			WebElement ele = getDriver().findElement(By.xpath(
					"//div[@data-target='#col_Brands']/following-sibling::div//input[contains(@name,'multiFilter')]/following-sibling::span[contains(text(),'"
							+ brand.trim() + "') and not(contains(@class,'hidden'))]"));

			// fetch items count from brand
			itemsCountInFilterBrand += Integer
					.parseInt(ele.getText().substring(ele.getText().indexOf("(") + 1, ele.getText().indexOf(")")));

			ele.click();

			if (utils.isElementDisplayed(brandFilterButtonLocator))
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", brandFilterButtonLocator);

			Thread.sleep(1500);
		}

		// Verify search result count matches with items count in category
		waiting.waitForVisibilityOfElement(searchResultsHeaderLocator, 10);
		int resultcount = Integer.parseInt(searchResultsHeaderLocator.getText()
				.replaceAll(searchResultsHeaderLocator.getText()
						.substring(searchResultsHeaderLocator.getText().indexOf("result")), "")
				.trim());
		Assert.assertEquals(resultcount, itemsCountInFilterBrand, "Result count mismatch");
		return this;
	}

	@Step("Verify Item Price before and after login:{0}")
	public ProductsListPageObjects verifyItemPriceAfterLogin(int specificItemPrice) {
		waiting.waitForVisibilityOfElements(itemsPriceLocator, 10);
		Assert.assertTrue(itemsPriceLocator.get(specificItemPrice - 1).isDisplayed(), "Item Price is not displayed.");
		return this;

	}

	@Step("Verify pricing should be displayed in three decimal places:{0},{1}")
	public ProductsListPageObjects verifyPricingDisplayInThreeDecimalPlaces(int specificItemPrice, int decimalPlaces) {
		waiting.waitForVisibilityOfElements(itemsPriceLocator, 10);
		String price = itemsPriceLocator.get(specificItemPrice - 1).getText().replace("$", "");
		Assert.assertEquals(price.split("\\.")[1].length(), decimalPlaces,
				"item pricing should not displayed in three decimal places");
		return this;

	}

	@Step("Verify item availability in list/grid page.")
	public ProductsListPageObjects verifyItemsAvailabilityInListAndGridPage() {
		waiting.waitForVisibilityOfElements(itemsAvailabilityLocator, 10);
		Assert.assertTrue(itemsAvailabilityLocator.get(0).isDisplayed(), "Item Availability is not displayed.");
		return this;
	}

	@Step("Verify item availability in list/grid page before login.")
	public ProductsListPageObjects verifyItemsAvailabilityInListAndGridPageBeforeLogin(int specificItem,
			String itemAvailabilityBeforeLogin) {
		waiting.waitForVisibilityOfElements(itemAvailabilityLocatorBeforeLogin, 10);
		Assert.assertEquals(itemAvailabilityLocatorBeforeLogin.get(specificItem - 1).getText().trim(),
				itemAvailabilityBeforeLogin.trim());
		log.info("Item availability before login is tested.");
		return this;

	}

	@Step("Verify Refine Section In List Page. ")
	public ProductsListPageObjects verifyRefineResultSectionInListPage() {
		waiting.waitForVisibilityOfElement(filterSectionLocator, 10);
		softAssert.assertTrue(utils.isElementDisplayed(filterCategoryHeadingLocator),
				"Category Filter is not displayed in Refine Result Section.");
		softAssert.assertTrue(utils.isElementDisplayed(filterBrandsHeadingLocator),
				"Brand Filter is not displayed in Refine Result Section.");
		softAssert.assertTrue(utils.isElementDisplayed(filterManufacturerHeadingLocator),
				"Manufacturer Filter is not displayed in Refine Result Section.");
		softAssert.assertAll();
		return this;
	}

	public String getNumberOfItemsOfCategory(int specificCategory) throws InterruptedException {
		Thread.sleep(2500);
		waiting.waitForVisibilityOfElement(categoryFilterLocator.get(0), 10);
		String value = categoryFilterLocator.get(specificCategory - 1).getText();
		return value.replaceAll("[^0-9]", "");
	}

	public String getNumberOfItemsOfBrand(int specificBrand) {
		waiting.waitTillPageLoads();
		waiting.waitForVisibilityOfElement(brandsFilterLocator.get(0), 10);
		String value = brandsFilterLocator.get(specificBrand - 1).getText();
		return value.replaceAll("[^0-9]", "");

	}

	public String getNumberOfItemsOfSpecificManufacturer(int specificManufacturer) {
		waiting.waitTillPageLoads();
		waiting.waitForVisibilityOfElement(manufacturerFilterLocator.get(0), 10);
		String value = manufacturerFilterLocator.get(specificManufacturer - 1).getText();
		return value.replaceAll("[^0-9]", "");
	}

	@Step("Click On Specific Checkbox Of Category Filter:{0}")
	public ProductsListPageObjects clickOnSpecificCheckBoxOfCategory(int specificCategory) throws InterruptedException {
		// waiting.waitForVisibilityOfElements(categoryFilterLocator, 10);
		Thread.sleep(1200);
		categoryFilterLocator.get(specificCategory - 1).click();
		/*
		 * ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		 * categoryFilterLocator.get(specificCategory - 1));
		 */
		if(utils.isElementDisplayed(categoryFilterButtonLocator)) {
			categoryFilterButtonLocator.click();
		}
		Thread.sleep(1200);
		return this;
	}

	@Step("Click On Specific Checkbox Of Brand Filter:{0}")
	public ProductsListPageObjects clickOnSpecificCheckBoxOfBrand(int specificBrand) throws InterruptedException {
		waiting.waitForVisibilityOfElement(brandsFilterCheckboxLocator.get(0), 10);
		brandsFilterCheckboxLocator.get(specificBrand - 1).click();
		Thread.sleep(1200);
		if(utils.isElementDisplayed(brandFilterButtonLocator)) {
			brandFilterButtonLocator.click();
		}
		return this;
	}

	@Step("Click On Specific Checkbox Of Manufacturer Filter:{0}")
	public ProductsListPageObjects clickOnSpecificCheckBoxOfManufacturer(int specificManufacturer)
			throws InterruptedException {
		waiting.waitForVisibilityOfElement(manufacturerFilterCheckboxLocator.get(0), 10);
		manufacturerFilterCheckboxLocator.get(specificManufacturer - 1).click();
		if(utils.isElementDisplayed(manufacturerFilterButtonLocator)) {
			manufacturerFilterButtonLocator.click();
		}
		Thread.sleep(1200);
		return this;
	}

	@Step("Verify Refine Result Functionality Of Category,Brand and Manufacturer:{0}")
	public ProductsListPageObjects verifyRefineResultFunctionality(int expectedNumberOfItems) {
		waiting.waitForVisibilityOfElement(searchResult, 10);
		// String numberOfFilterItems = searchResult.getText().replaceAll("[^0-9]", "");
		String[] numberOfFilterItems = searchResult.getText().split("results");
		Assert.assertEquals(Integer.parseInt(numberOfFilterItems[0].trim()), expectedNumberOfItems,
				"Refine Result Functionality is not working.");
		return this;
	}

	@Step("Click On Clear All Link")
	public ProductsListPageObjects clickOnClearAllLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(clearAllLinkLocator, 10);
		clearAllLinkLocator.click();
		Thread.sleep(1200);
		return this;
	}

	@Step("Verify Clear All Link Function.")
	public ProductsListPageObjects verifyClearAllLinkFunction() {
		Assert.assertTrue(utils.isElementNotDisplayed(clearAllSectionLocator));
		return this;
	}

	@Step("Click On Branch Availability.")
	public ProductsListPageObjects clickOnBranchAvailability(int specificBranch) throws InterruptedException {
		waiting.waitForVisibilityOfElements(branchAvailabilityLinkLocator, 10);
		branchAvailabilityLinkLocator.get(specificBranch - 1).click();
		Thread.sleep(1200);
		return this;

	}

	@Step("Verify of all branch availability display.")
	public ProductsListPageObjects verifyBranchAvailability() {
		waiting.waitForVisibilityOfElement(branchAvailabilityPopUpLocator, 10);
		softAssert.assertTrue(utils.isElementDisplayed(branchAvailabilityPopUpNameLocator));
		softAssert.assertTrue(utils.isElementDisplayed(branchAvailabilityHeaderLocator));
		softAssert.assertTrue(utils.isElementDisplayed(branchAvailableHeaderLocator));
		softAssert.assertAll();
		return this;
	}

	@Step("Verify grid view tooltip")
	public ProductsListPageObjects verifyGridViewTooltip() throws Throwable {
		waiting.waitForVisibilityOfElement(changeViewButtonLocatorGridView, 10);
		Thread.sleep(1000);
		Assert.assertEquals(changeViewButtonLocatorGridView.getAttribute("title").trim(), "Grid View",
				"Invalid grid view tooltip");
		log.info("Verified grid view tooltip");
		return this;
	}

	/*
	 * @Step("Verify list view tooltip") public ProductsListPageObjects
	 * verifyListViewTooltip() {
	 * waiting.waitForVisibilityOfElement(changeViewButtonLocatorListView, 10);
	 * Assert.assertEquals(changeViewButtonLocatorListView.getAttribute("title").
	 * trim(), "List View", "Invalid list view tooltip");
	 * log.info("Verified list view tooltip"); return this; }
	 */

	@Step("Verify list view tooltip")
	public ProductsListPageObjects verifyListViewTooltip() {
		waiting.waitForVisibilityOfElement(changeViewButtonLocatorGridView, 10);
		Assert.assertEquals(changeViewButtonLocatorGridView.getAttribute("title").trim(), "Grid View",
				"Invalid list view tooltip");
		log.info("Verified list view tooltip");
		return this;
	}

	@Step("Verify default pagination options")
	public ProductsListPageObjects verifyDefaultPagination() {
		waiting.waitForVisibilityOfElements(paginationLocator, 10);
		By nxt = By.xpath("//div[@class='pagebarUTH']/a[text()='Next']");
		Assert.assertTrue(utils.isElementDisplayed(getDriver().findElement(nxt)), "Previous button is not displayed");
		for (int i = 1; i < 6; i++) {
			Assert.assertEquals(paginationLocator.get(i - 1).getText().trim(), String.valueOf(i),
					"Invalid default pagination values");
		}
		log.info("Verified Default Pagination values");
		return this;
	}

	@Step("Verify pagination options after clicking on Next button")
	public ProductsListPageObjects verifyPaginationAfterClickingOnNextButton() {
		SoftAssert softAssert = new SoftAssert();
		waiting.waitForVisibilityOfElements(paginationLocator, 10);
		By prev = By.xpath("//div[@class='pagebarUTH']/a[text()='Previous']");
		By nxt = By.xpath("//div[@class='pagebarUTH']/a[text()='Next']");
		softAssert.assertTrue(utils.isElementDisplayed(getDriver().findElement(prev)),
				"Previous button is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(getDriver().findElement(nxt)), "Next button is not displayed");
		for (int i = 6; i < 11; i++) {
			softAssert.assertEquals(paginationLocator.get(i - 1).getText().trim(), String.valueOf(i),
					"Invalid default pagination values");
		}
		softAssert.assertAll();
		log.info("Verified Pagination values after selecting Next page option");
		return this;
	}

	@Step("Verify selected page")
	public ProductsListPageObjects verifySelectedPage(String currentPage) throws InterruptedException {
		waiting.waitTillPageLoads();
		Thread.sleep(2500);
		waiting.waitForVisibilityOfElement(currentPageLocator, 15);
		Assert.assertEquals(currentPageLocator.getText().trim(), currentPage.trim(), "Page selected is not displayed");
		log.info("Verified Page selected");
		return this;
	}

	@Step("Click on Next button")
	public ProductsListPageObjects clickOnNextPageButton() {
		waiting.waitForVisibilityOfElement(nextPageLocator, 8);
		nextPageLocator.click();
		log.info("Clicked on Next Page button");
		return this;
	}

	@Step("Click on Previous button")
	public ProductsListPageObjects clickOnPreviousButton() {
		waiting.waitForVisibilityOfElement(previousPageLocator, 8);
		previousPageLocator.click();
		log.info("Clicked on Previous Page button");
		return this;
	}

	@Step("Verify clear filter functionality")
	public ProductsListPageObjects clearAndVerifyFilteredCategory(String category) throws InterruptedException {
		try {
			WebElement cate = getDriver()
					.findElement(By.xpath("//span[text()='" + category + "']/a[@title='Remove This Item']"));
			Thread.sleep(1000);
			cate.click();
			Thread.sleep(1500);
			Assert.assertTrue(utils.isElementNotDisplayed(cate));
			log.info("Verified clear filter functionality");
		} catch (StaleElementReferenceException e) {
		}
		return this;
	}
	// ================check ===============================================

	@Step("Select shipping address")
	public ProductsListPageObjects selectViewInProductListAndGrid() throws Exception {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkForViewOfThePage());
			Thread.sleep(1500);
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			selectViewInProductListAndGrid();
		}
		return this;
	}

	public boolean checkForViewOfThePage() {
		try {
			productListPage().verifyViewInPage();
			log.info("Selected address");
			return true;
		} catch (Exception e) {
			return true;
		}
	}

	@Step("Verify use this address button locator")
	public ProductsListPageObjects verifyViewInPage() throws Exception {

		waiting.waitForVisibilityOfElements(checkForGridViewLocator, 5);
		// ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		// gridIconLocator);
		gridIconLocator.click();
		Thread.sleep(1200);
		return this;
	}

	@Step("Verify Short Description is {0}")
	public ProductsListPageObjects verifyShortDescInProductDetailsPage(String shortDesc) {
		mainOperations.waitTillPageLoads();
		for (int i = 0; i < shortDescriptionLocator.size(); i++) {
			softAssert.assertTrue(mainOperations.getText(shortDescriptionLocator.get(i)).contains(shortDesc),
					"Description value didnt match");
		}
		softAssert.assertAll();
		log.info("Verified Short Description " + shortDesc);
		return this;
	}

	@Step("Verify Short Description is {0}")
	public ProductsListPageObjects verifyShortDescInProductDetailsPageForOneOrMoreWords(String shortDesc) {
		mainOperations.waitTillPageLoads();
		String[] searchWords = shortDesc.split(",");
		for (int i = 0; i < shortDescriptionLocator.size(); i++) {
			if (mainOperations.getText(shortDescriptionLocator.get(i)).equalsIgnoreCase(searchWords[0])
					|| mainOperations.getText(shortDescriptionLocator.get(i)).equalsIgnoreCase(searchWords[1])
					|| mainOperations.getText(shortDescriptionLocator.get(i)).equalsIgnoreCase(searchWords[2])) {
				softAssert.assertTrue(true,	"Description value didnt match");
				log.info("Tested " + shortDescriptionLocator.get(i));
			}
		}
		softAssert.assertAll();
		log.info("Verified Short Description " + shortDesc);
		return this;
	}

}