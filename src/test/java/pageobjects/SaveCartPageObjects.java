package pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class SaveCartPageObjects extends PageInitializer {

	@FindBy(xpath = "//h1")
	public WebElement pageName;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	public List<WebElement> breadCrumbs;

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(SaveCartPageObjects.class);

	@FindBy(xpath = "//h3[contains(text(),'My Saved Carts')]/ancestor::div[@class='accountDash cimm_halfBlockColumns']/descendant::ul")
	private List<WebElement> mySavedCartListFromAccounts;

	@FindBy(xpath = "//div[@class='cimm_listEnclosure']/ul")
	private List<WebElement> mySavedCartList;

	@FindBy(xpath = "//span[contains(text(),'Save Cart')]/ancestor::dt/following-sibling::dd/ul")
	private List<WebElement> mySavedCartListFromSaveIcon;

	@FindBy(xpath = "//button[@data-original-title='Delete Cart']")
	private WebElement deleteSaveCartButton;

	@FindBy(xpath = "//button[@data-id='bulkActionSelect']")
	private WebElement bulkOptionsClickLocator;

	@FindBy(xpath = "//dl[@id='bulkOptions']/dd/descendant::li/descendant::span")
	private List<WebElement> bulkOptionsValuesLocator;

	@FindBy(xpath = "//div[@class='cimm_BreadCrumbs']//a[@href='/SavedGroups/Cart']")
	private WebElement mySavedCartCrumbLocator;

	@FindBy(xpath = "//div[@class='cimm_BreadCrumbs']//a[@href='Dashboard']")
	private WebElement myAccountCrumbLocator;

	@FindBy(xpath = "//div[@class='cimm_BreadCrumbs']//a[@href='/welcome']")
	private WebElement homeBreadCrumbLinkLocator;

	@FindBy(xpath = "//input[@id='searchKey']")
	private WebElement searchTextBoxLocator;

	@FindBy(xpath = "//tr/td[@data-th='Select']/label")
	private List<WebElement> selectCheckboxLocator;

	@FindBy(xpath = "//button[@id='searchBtn']")
	private WebElement searchBtnLocator;

	@FindBy(xpath = "//input[@id='clearsearchBtn']")
	private WebElement clearSearchBtnLocator;

	@FindBy(xpath = "//button[@data-original-title='Edit Cart Name']")
	private WebElement editCartNameLocator;

	@FindBy(xpath = "//button[@data-original-title='Delete Cart']")
	private WebElement deleteCartLocator;

	@FindBy(xpath = "//button[@data-target='#sharePop']")
	private WebElement shareLocator;

	@FindBy(xpath = "//button[@data-id='sortByBrand']")
	private WebElement sortByLocator;

	@FindBy(xpath = "//button[@data-id='resultPage']")
	private WebElement itemsPerPageLocator;

	@FindBy(xpath = "//span[contains(text(),'Collapse View')]")
	private WebElement collapseViewLocator;

	@FindBy(xpath = "//td[@data-th='Image']")
	private WebElement itemImageLocator;

	@FindBy(xpath = "//div[@class='cimm_tableDescSection left']")
	private WebElement itemDescriptionLocator;

	@FindBy(xpath = "//input[@name='shoppingCartQty']")
	private List<WebElement> quantityTextBoxLocator;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement serachTextBoxLocator;

	@FindBy(xpath = "//span[contains(@class,'priceSpan')]/ancestor::td[@data-th='Your Price']")
	private WebElement yourPriceLocator;

	@FindBy(xpath = "//span[contains(@class,'priceSpan')]/ancestor::td[@data-th='Ext Price']")
	private WebElement extPriceLocator;

	@FindBy(xpath = "//td[@data-th='Ext Price']/span")
	private List<WebElement> extensionPriceInPGLocator;

	@FindBy(xpath = "//label[@class='customCheckBox']")
	private WebElement selectAllLocator;

	@FindBy(xpath = "//form[@id='productGroupForm']//h5[@class='cimm_itemTitle']/a")
	private WebElement productNameInSavedCartLocator;

	@FindBy(xpath = "//ul[@id='bulkActionClick']/descendant::span[contains(text(),'Update Selected Items')]")
	private WebElement updateSelectItemsLocator;

	@FindBy(xpath = "//p[contains(text(),'Added Successfully.')]")
	private List<WebElement> addedSuccessfullyMsgLocator;

	@FindBy(xpath = "//input[@id='editedName']")
	private WebElement editCartNameTextBoxLocator;

	@FindBy(xpath = "//button[@id='groupNameSaveBtn']")
	private WebElement editCartNameSaveButtonLocator;

	@FindBy(xpath = "//button[@onclick='cancelEditGroup();']")
	private WebElement editCartNameCancelButtonLocator;

	@FindBy(xpath = "//td[text()='No results found']")
	private WebElement noResultsFoundMsg;

	@FindBy(xpath = "//div[@class='expndCollapseViews']/descendant::li")
	private List<WebElement> changeViewOptionsLocator;

	@FindBy(xpath = "//li[@class='hideForCollapse']/p")
	private WebElement descriptionLocator;

	@FindBy(xpath = "//div[@class='chosen-drop']/descendant::li[text()='Part Number (Asc)']")
	private WebElement partNumberAscLocator;

	@FindBy(xpath = "//div[@class='chosen-drop']/descendant::li[text()='Part Number (Desc)']")
	private WebElement partNumberDescLocator;

	@FindBy(xpath = "//div[@class='chosen-drop']/descendant::li[text()='Manf Part# (Asc)']")
	private WebElement manfPartNoAscLocator;

	@FindBy(xpath = "//div[@class='chosen-drop']/descendant::li[text()='Manf Part# (Desc)']")
	private WebElement manfPartNoDescLocator;

	@FindBy(xpath = "//div[@id='resultPage_chosen']/div/ul/li")
	private List<WebElement> itemPerPageOptionsLocator;

	@FindBy(xpath = "//table[@class='cimm_siteTable cimm_pgTable rwd-table rwd-Tab']/descendant::tbody/tr")
	private List<WebElement> verifyItemsPerPage;

	@FindBy(xpath = "//div[contains(@class,'cimm_tableDescSection')]/descendant::a")
	private List<WebElement> productNamesLocator;

	@FindBy(xpath = "//div[@class='addToCartHeaderContent']/descendant::a[@class='closeBtn']")
	private WebElement closeButtonPopUpLocator;

	@FindBy(xpath = "//input[@value='Combine']")
	private List<WebElement> combineButtonInCartPopUpLocator;

	@FindBy(xpath = "//p[@class='alreadyExist']")
	private List<WebElement> itemAlreadyExistsMessageLocator;

	@FindBy(xpath = "(//div[contains(@class,'popCheckout')]/descendant::a[contains(text(),'Checkout')])[2]")
	private WebElement checkoutButtonInMyCartLocator;

	@FindBy(xpath = "//input[@value='Seperate']")
	private List<WebElement> seperateButtonInCartPopUpLocator;

	@FindBy(xpath = "//div[@class='mulAddtoCartStatus']/descendant::input[@value='Cancel']")
	private List<WebElement> cancelButtonInCartPopUpLocator;

	@FindBy(xpath = "//div[@id='sortByBrand_chosen']/descendant::li")
	private List<WebElement> sortByOptionsTextLocator;

	@FindBy(xpath = "//form[@id='productGroupForm']/descendant::table/thead//th")
	private List<WebElement> itemHeadersLocatorsInSaveCartPage;

	@FindBy(xpath = "//button[@data-id='views']")
	private WebElement chooseViewLocator;

	@FindBy(xpath = "//td[@data-th='Item Description']")
	private List<WebElement> itemsInSaveCart;

	@FindBy(xpath = "//h1")
	private WebElement landingPageName;

	@FindBy(xpath = "//li[contains(@class,'Collapse')]/p")
	private List<WebElement> shortDescriptionLocator;

	@FindBy(xpath = "//td[@data-th='Item Description']//strong[normalize-space(text())='Part#:']/following-sibling::span")
	private List<WebElement> partNumberValueLocator;

	@FindBy(xpath = "//td[@data-th='Item Description']//strong[contains(text(),'MPN:')]/following-sibling::span")
	private List<WebElement> manufacturerPartNumberLocator;

	@FindBy(xpath = "//div[contains(text(),'No Saved Cart Available')]")
	private WebElement noSCAvailableLocator;

	@Step("Click on mysavedcart ")
	public SaveCartPageObjects clickOnMySavedCartCrumb() {
		mySavedCartCrumbLocator.click();
		log.info("Clicked on saved cart breadcrumb");
		return this;
	}

	@Step("Click on myAccount ")
	public SaveCartPageObjects clickOnMyAccountCrumb() {
		myAccountCrumbLocator.click();
		log.info("Clicked on My Account breadcrumb");
		return this;
	}

	@Step("Verify page name ")
	public SaveCartPageObjects verifyPageName() {
		assertTrue(pageName.isDisplayed(), "My Cart page name is not displayed.");
		log.info("Verified page name");
		return this;
	}

	@Step("Click on saved cart named : {0} ")
	public SaveCartPageObjects clickOnTheCreatedSaveCart(String saveCartName) throws Exception {
		try {
			waiting.waitForVisibilityOfElement(By.xpath("//a[contains(text(),'" + saveCartName + "')]"), 10);
			getDriver().findElement(By.xpath("//a[contains(text(),'" + saveCartName + "')]")).click();
		} catch (NoSuchElementException | TimeoutException e) {
			/*
			 * ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
			 * myAccountsPage().savedCartViewMoreLocator);
			 */
			getDriver().findElement(By.xpath("//a[contains(text(),'" + saveCartName + "')]")).click();
		}
		log.info("Clicked on the Saved Cart created:" + saveCartName);
		return this;
	}

	@Step("Verify breadcrumb is {0} ")
	public SaveCartPageObjects verifybreadCrumbs(String mySaveCartBreadCrumb) {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);

		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.trim()
						.equalsIgnoreCase(mySaveCartBreadCrumb.trim()),

				"Breadcrumb is not " + mySaveCartBreadCrumb + ". It is :"
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.trim());
		log.info("Verify breadcrumbs");
		return this;
	}

	@Step("Verify Product Name Added In My Saved Cart : {0} ")
	public SaveCartPageObjects verifyWhetherProductsAddedAreDisplayedInTheSaveCart(String products) {
		waiting.waitForVisibilityOfElement(productNameInSavedCartLocator, 10);
		Assert.assertEquals(productNameInSavedCartLocator.getText().trim().toLowerCase(), products.trim().toLowerCase(),
				"Product Name is " + products.trim().toLowerCase() + "It is :"
						+ productNameInSavedCartLocator.getText().trim().toLowerCase());
		log.info("verified Whether Products Added Are Displayed In The SaveCart");
		return this;
	}

	@Step("Verify price precision in My Save Cart page")
	public SaveCartPageObjects verifyPricePrecisionInSaveCart(String pnNumber, String pricePrecesion) {

		WebElement specificItemPrice = getDriver().findElement(By.xpath("//span[contains(text(),'" + pnNumber
				+ "')]/ancestor::td/following-sibling::td[contains(@data-th,'Your')]/span"));

		Assert.assertEquals(specificItemPrice.getText().replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1].length(), Integer.parseInt(pricePrecesion),
				"Price precision in not matched in My Save Cart page");

		log.info("Verify Price precision in Save Cart page");
		return this;
	}

	@Step("Verify saved Cart list ")
	public SaveCartPageObjects verifySavedCartList() {
		waiting.waitForVisibilityOfElements(mySavedCartList, 20);
		Assert.assertEquals(mySavedCartList.listIterator(), mySavedCartList.listIterator());
		log.info("Verify Saved cart list");
		return this;
	}

	@Step("Verify saved Cart list From My Accounts ")
	public SaveCartPageObjects verifySavedCartListFromMyAccounts() {
		waiting.waitForVisibilityOfElements(mySavedCartListFromAccounts, 20);
		Assert.assertEquals(mySavedCartListFromAccounts.listIterator(), mySavedCartList.listIterator());
		log.info("Verified SavedCart List From My Accounts");
		return this;
	}

	@Step("Verify saved Cart list From My Accounts ")
	public SaveCartPageObjects verifySavedCartListFromSaveIcon() {
		waiting.waitForVisibilityOfElements(mySavedCartListFromSaveIcon, 20);
		Assert.assertEquals(mySavedCartListFromSaveIcon.listIterator(), mySavedCartList.listIterator());
		log.info("Verifed Saved Cart List From Save Icon");
		return this;
	}

	@Step("Verify page name  is {0} ")
	public SaveCartPageObjects verifyPageName(String saveCartBreadcrumb) throws InterruptedException {
		waiting.waitTillPageLoads();
		Thread.sleep(2500);
		waiting.waitForVisibilityOfElement(pageName, 5);
		Assert.assertTrue(pageName.getText().trim().equalsIgnoreCase(saveCartBreadcrumb),
				"Page name is not " + saveCartBreadcrumb);
		log.info("Verified Page name");
		return this;
	}

	@Step("Verify page name  is {0} ")
	public SaveCartPageObjects verifyPageNameSaveCartLandingPageName(String saveCartBreadcrumb) {
		waiting.waitForVisibilityOfElement(landingPageName, 10);
		Assert.assertTrue(landingPageName.getText().trim().equalsIgnoreCase(saveCartBreadcrumb),
				"Page name is not " + saveCartBreadcrumb);
		log.info("Verified Page name");
		return this;
	}

	@Step("Verify cart title contains saved groups ")
	public SaveCartPageObjects verifySaveCartTitle(String productName) throws Exception {
		Assert.assertEquals(getDriver().getTitle().trim(), "Saved Groups" + " | " + productName.trim());
		log.info("Verified Saved Cart page title");
		return this;
	}

	@Step("Verify cart title contains saved groups ")
	public SaveCartPageObjects verifySaveCartTitle(String pageName, String productName) throws Exception {
		Assert.assertEquals(getDriver().getTitle().trim(), pageName + " | " + productName.trim());
		log.info("Verified Saved Cart page title");
		return this;
	}

	@Step("Click on delete save cart ")
	public SaveCartPageObjects deleteSaveCart() {
		waiting.waitForVisibilityOfElement(deleteSaveCartButton, 15);
		deleteSaveCartButton.click();
		log.info("Clicked on Delete Save Cart Icon");
		return this;
	}

	@Step("Click on delete save cart ")
	public SaveCartPageObjects deleteSaveCart(String saveCartName) throws Exception {
		Thread.sleep(1500);
		WebElement deleteIcon = getDriver().findElement(By.xpath("//a[contains(text(),'" + saveCartName
				+ "')]/ancestor::td[@data-th='Cart Name']/following-sibling::td[@data-th='Action']/button[@data-original-title='Delete Saved Cart']"));
		deleteIcon.click();
		Thread.sleep(1500);
		log.info("Deleted Saved Cart:" + saveCartName);
		return this;
	}

	@Step("Verify deletion of save cart is {0} ")
	public SaveCartPageObjects verifyDeletionOfSaveCart(String saveCartName) {
		Assert.assertTrue(assertDeletionOfSaveCart(saveCartName), "Cart is not deleted yet.");
		log.info("Verified deleted Saved Cart:" + saveCartName);
		return this;
	}

	@Step("Verify Save cart is deleted")
	public boolean assertDeletionOfSaveCart(String saveCartName) {
		try {
			Assert.assertFalse(
					getDriver().findElement(By.xpath("//a[contains(text(),'" + saveCartName + "')]")).isDisplayed());
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	@Step("Verify title ")
	public SaveCartPageObjects verifyTitleAfterClickingOnTheCartCreated(String saveCart, String productName)
			throws Exception {
		Assert.assertEquals(getDriver().getTitle().trim(), saveCart + " | " + productName.trim());
		log.info("Verified Saved Cart page title");
		return this;
	}

	@Step("Select {0} from bulk actions dropdown")
	public SaveCartPageObjects selectBulkActionsDropdown(String bulkOption) throws Exception {
		// bulkOptionsClickLocator.click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", bulkOptionsClickLocator);
		Thread.sleep(1200);
		switch (bulkOption) {
		case "Delete Selected Items":
			getDriver().findElement(By.xpath("//span[contains(text(),'Delete Selected Items')]")).click();
			Thread.sleep(1400);
			break;
		case "Update Selected Items":
			getDriver().findElement(By.xpath("//span[contains(text(),'Update Selected Items')]")).click();
			Thread.sleep(1400);
			break;
		case "Add Selected Items to Cart":
			utils.scrollThePageToTop();
			getDriver().findElement(By.xpath("//span[contains(text(),'Add Selected Items to Cart')]")).click();
			Thread.sleep(1400);
			break;
		}
		log.info("Selected Bulk Option as:" + bulkOption);
		return this;
	}

	@Step("Verify whether alert text is {0}")
	public SaveCartPageObjects verifyAlertText(String partNumber) throws Exception {
		try {
			waiting.waitForAlertToBePresent(5);
			String expText = "Min Order Quantity is 10. For PN: " + partNumber
					+ ". To Continue with Min Order Qty click \"Ok\".To cancel this item click \"Cancel\"";
			Assert.assertTrue(utils.verifyAlertText(expText), "Alert text is invalid.");
		} catch (NoAlertPresentException | AssertionError e) {
			getDriver().navigate().refresh();
			Thread.sleep(1000);
			clickOnSpecificCheckbox(partNumber);
			Thread.sleep(1000);
			enterQuantityForSpecificItem("", partNumber);
			clickOnSpecificCheckbox(partNumber);
			String expText = "Invalid Qty. For PN: " + partNumber
					+ ". To Continue with Min Order Qty click \"Ok\".To cancel this item click \"Cancel\"";
			Assert.assertTrue(utils.verifyAlertText(expText), "Alert text is invalid.");
		}
		log.info("Verified Alert Text");
		return this;
	}

	@Step("Verify Successfully message")
	public SaveCartPageObjects verifyNumberAddedSuccessfullyMsg(int expectedNumberOfAddedSuccessfullyMessages)
			throws Exception {
		waiting.waitForVisibilityOfElements(addedSuccessfullyMsgLocator, 5);
		Assert.assertEquals(addedSuccessfullyMsgLocator.size(), expectedNumberOfAddedSuccessfullyMessages);
		return this;

	}

	@Step("Click on the {0}st/nd/rd checkbox")
	public SaveCartPageObjects clickOnSpecificCheckbox(String partNumber) {
	
		WebElement specificCheckboxByPN = getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Select']/label"));
		specificCheckboxByPN.click();
		log.info("Clicked on checkbox of item with PN:" + partNumber);
		return this;
	}

	@Step("Click on the {0}st/nd/rd checkbox")
	public SaveCartPageObjects clickOnSpecificCheckbox(int specificCheckbox) {
		waiting.waitForVisibilityOfElements(selectCheckboxLocator, 10);
		selectCheckboxLocator.get(specificCheckbox - 1).click();
		log.info("Clicked on checkbox of an item");
		return this;
	}

	@Step("Verify whether breadcrumb is {0} ")
	public SaveCartPageObjects verifyBreadCrumbOfMySavedCart(String mySavedCart) {
		Assert.assertTrue(saveCartPage().breadCrumbs.get(saveCartPage().breadCrumbs.size() - 1)
				.getText()
				.replace("/", "")
				.trim()
				.equalsIgnoreCase(mySavedCart));
		log.info("Verified breadcrumb of saved cart");
		return this;

	}

	@Step("Verify bread crumb after creation")
	public SaveCartPageObjects verifyBreadCrumbAfterCreation(String saveCartName, String mySaveCartBreadCrumb,
			String myAccountBreadcrumb) {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);
		Assert.assertTrue(homeBreadCrumbLinkLocator.isDisplayed(),
				"home link is not displayed in the breadcrumb navigation");
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 3)
						.getText()
						.replace("/", "")
						.trim()
						.equalsIgnoreCase(myAccountBreadcrumb.trim()),
				"Breadcrump is not " + myAccountBreadcrumb + ". It is :"
						+ saveCartPage().breadCrumbs.get(saveCartPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim());
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 2)
						.getText()
						.replace("/", "")
						.trim()
						.equalsIgnoreCase(mySaveCartBreadCrumb.trim()),
				"Breadcrump is not " + mySaveCartBreadCrumb + ". It is :"
						+ saveCartPage().breadCrumbs.get(saveCartPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim());
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.replace("/", "")
						.trim()
						.equalsIgnoreCase(saveCartName.trim()),
				"Breadcrump is not " + saveCartName + ". It is :"
						+ saveCartPage().breadCrumbs.get(saveCartPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim());
		log.info("Verified breadcrumb of saved cart");
		return this;
	}

	@Step("Verify my saved cart page")
	public SaveCartPageObjects verifyMySavedCartPage() throws Exception {
		SoftAssert softAssert = new SoftAssert();
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		softAssert.assertTrue(sortByLocator.isDisplayed(), "sortBy is not displayed.");
		softAssert.assertTrue(collapseViewLocator.isDisplayed(), "collapseView is not displayed.");
		softAssert.assertTrue(itemsPerPageLocator.isDisplayed(), "itemsPerPage is not displayed.");
		softAssert.assertTrue(landingPageName.isDisplayed(), "My Saved Cart page name is not displayed.");
		softAssert.assertTrue(bulkOptionsClickLocator.isDisplayed(), "Bulk Options is not displayed.");
		softAssert.assertTrue(searchTextBoxLocator.isDisplayed(), "Search Box is not displayed.");
		softAssert.assertTrue(searchBtnLocator.isDisplayed(), "Search Button is not displayed.");
		softAssert.assertTrue(clearSearchBtnLocator.isDisplayed(), "clearSearchBtn is not displayed.");
		softAssert.assertTrue(editCartNameLocator.isDisplayed(), "editCartName is not displayed.");
		softAssert.assertTrue(deleteCartLocator.isDisplayed(), "deleteCart is not displayed.");
		softAssert.assertTrue(shareLocator.isDisplayed(), "share is not displayed.");
		softAssert.assertTrue(itemImageLocator.isDisplayed(), "itemImage is not displayed.");
		softAssert.assertTrue(quantityTextBoxLocator.get(0).isDisplayed(), "quantity is not displayed.");
		softAssert.assertTrue(yourPriceLocator.isDisplayed(), "yourPrice is not displayed.");
		softAssert.assertTrue(extPriceLocator.isDisplayed(), "extPrice is not displayed.");
		softAssert.assertTrue(selectAllLocator.isDisplayed(), "selectAll is not displayed.");
		log.info("Verified Saved Cart page fields");
		return this;
	}

	@Step("Click on selectAll checkbox ")
	public SaveCartPageObjects clickOnSelectAllCheckBox() {
		waiting.waitForVisibilityOfElement(selectAllLocator, 15);
		selectAllLocator.click();
		log.info("Click on Select All checkbox");
		return this;
	}

	@Step("Enter {0} as Quantity")
	public SaveCartPageObjects enterQuantityForSpecificItem(int specificItem, String quantity) {
		waiting.waitForVisibilityOfElement(quantityTextBoxLocator.get(0), 6);
		quantityTextBoxLocator.get(specificItem - 1).clear();
		quantityTextBoxLocator.get(specificItem - 1).sendKeys(quantity);
		log.info("Entered quantity of an item");
		return this;
	}

	@Step("Enter quantity for Item:{0}")
	public SaveCartPageObjects enterQuantityForSpecificItem(String quantity, String partNumber)
			throws InterruptedException {
		WebElement qtyOfItem = getDriver().findElement(
				By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'" + partNumber
						+ "')]/ancestor::td/following-sibling::td[@data-th='Qty']/input[@name='shoppingCartQty']"));
		qtyOfItem.click();
		qtyOfItem.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		qtyOfItem.sendKeys(Keys.BACK_SPACE);
		qtyOfItem.sendKeys(quantity);
		log.info("Entered quantity for item with PN:" + partNumber);
		return this;
	}

	@Step("Click On Edit Cart Name")
	public SaveCartPageObjects clickOnEditCartName() throws Exception {
		waiting.waitForVisibilityOfElement(editCartNameLocator, 15);
		editCartNameLocator.click();
		Thread.sleep(600);
		log.info("Clicked on Edit Saved Cart button");
		return this;
	}

	@Step("Verify Edit Cart Name Elements")
	public SaveCartPageObjects verifyEditCartNameElements() throws Exception {
		Assert.assertTrue(editCartNameTextBoxLocator.isDisplayed(), "enterEditCartName not displayed.");
		Assert.assertTrue(editCartNameSaveButtonLocator.isDisplayed(), "editCartNameSaveIcon not displayed.");
		Assert.assertTrue(editCartNameCancelButtonLocator.isDisplayed(), "editCartNameCancelIcon not displayed.");
		log.info("Verified Edited Saved Cart name");
		return this;
	}

	@Step("Send Text in Edit Cart name Text Box")
	public SaveCartPageObjects enterEditCartName(String editCartName) throws Exception {
		waiting.waitForVisibilityOfElement(editCartNameTextBoxLocator, 15);
		editCartNameTextBoxLocator.clear();
		editCartNameTextBoxLocator.sendKeys(editCartName);
		log.info("Entered Saved Cart updated name");
		return this;
	}

	@Step("Edit group name as {0}")
	public SaveCartPageObjects enterNameForEditSCNameScenarios(String flag) {
		waiting.waitForVisibilityOfElement(editCartNameTextBoxLocator, 5);
		if (!flag.equals("No Change"))
			editCartNameTextBoxLocator.clear();
		return this;
	}

	@Step("Click On Save Button of Edit Cart Name")
	public SaveCartPageObjects clickOnSaveButton() throws Exception {
		waiting.waitForVisibilityOfElement(editCartNameSaveButtonLocator, 15);
		editCartNameSaveButtonLocator.click();
		Thread.sleep(600);
		log.info("Clicked on Save button in Saved Cart name edit mode");
		return this;
	}

	@Step("Click On Cancel Icon of Edit Cart Name")
	public SaveCartPageObjects clickOnCancelButtonOfEditCartName() throws Exception {
		waiting.waitForVisibilityOfElement(editCartNameCancelButtonLocator, 15);
		editCartNameCancelButtonLocator.click();
		Thread.sleep(600);
		log.info("Clicked on Cancel button in Saved Cart name edit mode");
		return this;
	}

	@Step("Verify Edit Cart Name Elements are not displayed when Cancel Icon is clicked")
	public SaveCartPageObjects verifyWhetherEditCartNameElementsAreNotDisplayedAfterCancel() throws Exception {
		Assert.assertFalse(editCartNameTextBoxLocator.isDisplayed(), "EnterEditCartName is displayed.");
		Assert.assertFalse(editCartNameSaveButtonLocator.isDisplayed(), "editCartNameSaveIcon is displayed.");
		Assert.assertFalse(editCartNameCancelButtonLocator.isDisplayed(), "editCartNameCancelIcon is displayed.");
		log.info("Verified Whether Edit Cart Name Elements Are Not Displayed After Cancel");
		return this;
	}

	@Step("Enter search text in saved cart")
	public SaveCartPageObjects searchTextInSavedCart(String text) {
		waiting.waitForVisibilityOfElement(searchTextBoxLocator, 6);
		searchTextBoxLocator.clear();
		searchTextBoxLocator.sendKeys(text);
		log.info("Searched item in saved cart");
		return this;
	}

	@Step("Click On Go Button in Saved Cart")
	public SaveCartPageObjects clickOnGoButton() throws Exception {
		waiting.waitForVisibilityOfElement(searchBtnLocator, 15);
		searchBtnLocator.click();
		log.info("Clicked on Go button");
		return this;
	}

	@Step("Verify the item displayed when searched in saved cart ")
	public SaveCartPageObjects verifySearchedItemIsDisplayed(String searchText) throws Exception {
		Assert.assertTrue(
				getDriver().findElement(By.xpath("//p[contains(text(),'" + searchText + "')]")).isDisplayed());
		log.info("Verified searched item is displayed in saved cart");
		return this;
	}

	@Step("Verify the item is not displayed for invalid search in saved cart ")
	public SaveCartPageObjects verifyNoResultsFoundMsg() throws Exception {
		waiting.waitForVisibilityOfElement(noResultsFoundMsg, 5);
		Assert.assertTrue(utils.isElementDisplayed(noResultsFoundMsg), "Records found");
		log.info("Verified No Results Found Msg");
		return this;
	}

	@Step("Click On Clear Search Button in Saved Cart")
	public SaveCartPageObjects clickOnClearSearchButton() throws Exception {
		waiting.waitForVisibilityOfElement(clearSearchBtnLocator, 15);
		clearSearchBtnLocator.click();
		log.info("Clicked on Clear search button");
		return this;
	}

	@Step("Click on {0}")
	public SaveCartPageObjects clickOnSpecificView(String specificView) throws Exception {
		Thread.sleep(1500);
		for (int i = 0; i < changeViewOptionsLocator.size(); i++) {
			if (changeViewOptionsLocator.get(i).getText().trim().equals(specificView)) {
				changeViewOptionsLocator.get(i).click();
				break;
			}
		}
		log.info("Seleted View:" + specificView);
		return this;
	}

	@Step("Select View:{0}")
	public SaveCartPageObjects selectView(String viewType) throws InterruptedException {
		waiting.waitForVisibilityOfElement(chooseViewLocator, 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", chooseViewLocator);
		// chooseViewLocator.click();
		Thread.sleep(1200);
		switch (viewType) {
		case "Expand View":
			WebElement expand = getDriver().findElement(By.xpath("//span[contains(text(),'Expand View')]"));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", expand);
			break;

		case "Collapse View":
			WebElement collapse = getDriver().findElement(By.xpath("//span[contains(text(),'Collapse View')]"));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", collapse);
			break;
		}
		log.info("Selected View Type as:" + viewType);
		return this;
	}

	@Step("Verify collapse view")
	public SaveCartPageObjects verifyCollapseView() {
		for (int i = 0; i < itemsInSaveCart.size(); i++) {
			Assert.assertTrue(utils.isElementNotDisplayed(shortDescriptionLocator.get(i)), "Not in Collapse View");
		}
		log.info("Verified collapse view");
		return this;
	}

	@Step("Verify expand view")
	public SaveCartPageObjects verifyExpandView() {
		for (int i = 0; i < itemsInSaveCart.size(); i++) {
			Assert.assertTrue(utils.isElementDisplayed(shortDescriptionLocator.get(i)), "Not in Expand View");
		}
		log.info("Verified expand view");
		return this;
	}

	@Step("Verify display of description for expand view ")
	public SaveCartPageObjects verifyDescriptionDisplayForExpandView() throws Exception {
		waiting.waitForVisibilityOfElement(descriptionLocator, 5);
		Assert.assertTrue(descriptionLocator.isDisplayed(), "description locator is not displayed in expand view");
		log.info("Verified visibility Item description in Expand view");
		return this;
	}

	@Step("Verify there is no display of description for collapse view ")
	public SaveCartPageObjects verifyDescriptionDisplayForCollapseView() throws Exception {
		Assert.assertFalse(descriptionLocator.isDisplayed(), "Description is displayed");
		log.info("Verified invisibility Item description in Expand view");
		return this;
	}

	@Step("Verify sort by options")
	public SaveCartPageObjects verifySortByDrodown(String[] expectedSortByDropdownOptions) throws InterruptedException {
		Thread.sleep(3500);
		sortByLocator.click();
		for (int i = 0; i < sortByOptionsTextLocator.size(); i++) {
			Assert.assertEquals(sortByOptionsTextLocator.get(i).getText().trim(), expectedSortByDropdownOptions[i]);
		}
		log.info("Verified Sort by dropdown options");
		return this;
	}

	@Step("Verify Share Cart list")
	public SaveCartPageObjects verifyDisplayOfShareCartLink() {
		waiting.waitForVisibilityOfElement(shareLocator, 6);
		Assert.assertTrue(shareLocator.isDisplayed(), "Share Cart Link is not displayed");
		log.info("Verified share cart link is displayed");
		return this;
	}

	@Step("Verify save cart is shared with save cart name :{0}")
	public SaveCartPageObjects verifyWhetherTheCartIsShared(String saveCartName) {
		Assert.assertTrue(getDriver()
				.findElement(By.xpath("//a[contains(@href,'" + saveCartName + "') and contains(@href,'SharedCart')]"))
				.isDisplayed(), "Cart is which was shared is not displayed");
		log.info("Verified saved cart is a shared cart");
		return this;
	}

	@Step("Clicked on shared cart :{0}")
	public SaveCartPageObjects clickOnSharedCart(String saveCartName) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver()
				.findElement(By.xpath("//a[contains(@href,'" + saveCartName + "') and contains(@href,'SharedCart')]")));
		log.info("Clicked on the shared cart");
		return this;
	}

	@Step("Verify breadcrumb of shared cart :{0}")
	public SaveCartPageObjects verifyCompleteBreadcrumb(String completeBreadcrumb) {
		String[] str = completeBreadcrumb.split(",");
		for (int i = 0; i < breadCrumbs.size() - 1; i++) {
			Assert.assertEquals(breadCrumbs.get(i).getText().replace("|", "").trim(), str[i],

					"Bread Crumb of shared cart is not :" + str[i] + ". It is "
							+ breadCrumbs.get(i).getText().replace("|", "").trim());
		}
		log.info("Verified breadcrumb");
		return this;
	}

	@Step("Click on close button in save cart page my cart pop up")
	public SaveCartPageObjects clickOnCloseButtonInSaveCartPageMyCartPopUp() throws InterruptedException {
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", closeButtonPopUpLocator);
		log.info("Clicked on close button in saved cart pop up");
		return this;
	}

	@Step("Verify Alert message")
	public SaveCartPageObjects verifyItemAlreadyExistsMessageInMyCartPopUpInSaveCart(
			int specificItemAlreadyExistsMessage) {
		waiting.waitForVisibilityOfElements(itemAlreadyExistsMessageLocator, 5);
		Assert.assertTrue(itemAlreadyExistsMessageLocator.get(specificItemAlreadyExistsMessage - 1).isDisplayed(),
				"Item already exists message is not displayed");
		log.info("Verified Item already exists message in my cart pop up");
		return this;
	}

	@Step("Click on specific combine button")
	public SaveCartPageObjects clickOnCombineButton(int specificCombineButton) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				combineButtonInCartPopUpLocator.get(specificCombineButton - 1));
		log.info("Clicked on combine button in my cart pop up");
		return this;
	}

	@Step("Click on checkout button in my cart pop up")
	public MyCartPageObjects clickOnCheckoutButtonInMyCartPopUpInSaveCart() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", checkoutButtonInMyCartLocator);
		log.info("Clicked on checkout button in my cart pop up");
		return myCartPage();
	}

	@Step("Click on specific separate  button")
	public SaveCartPageObjects clickOnSeparateButton(int specificSperateButton) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				seperateButtonInCartPopUpLocator.get(specificSperateButton - 1));
		log.info("Clicked on seperate button in my cart pop up");
		return this;
	}

	@Step("Click on specific cancel button")
	public SaveCartPageObjects clickOnCancelButton(int specificCancelButton) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				cancelButtonInCartPopUpLocator.get(specificCancelButton - 1));
		log.info("Clicked on cancel button in my cart pop up");
		return this;
	}

	@Step("Verify line item is displayed")
	public SaveCartPageObjects verifyWhetherTheLineItemIsNotDisplayed() throws InterruptedException {
		Thread.sleep(1500);
		Assert.assertTrue(checkIfTheLineItemIsNotDisplayed(), "Line item is displayed.");
		return this;
	}

	@Step("Verify line item is displayed")
	private boolean checkIfTheLineItemIsNotDisplayed() {
		return (getDriver().findElements(By.xpath("//div[@class='muladdtoCartItems']")).size()) == 0;
	}

	@Step("Verify item with part number:{0} is present in saved cart")
	public SaveCartPageObjects verifyItemsInSavedCart(String partNumber) {

		By itemInPG = By.xpath(
				"//form[@id='productGroupForm']//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber.trim() + "')]");

		Assert.assertTrue(testUtilityMethods.isElementDisplayed(itemInPG), "Item added is not present in saved cart");

		log.info("Item added is present in saved cart");

		return this;
	}

	@Step("Verify item with MPN:{0} is present in saved cart")
	public SaveCartPageObjects verifyItemsInSavedCartByMPN(String partNumber) {
		By itemInPG = By.xpath(
				"//form[@id='productGroupForm']//strong[contains(text(),'MPN:')]/following-sibling::span[contains(text(),'"
						+ partNumber.trim() + "')]");

		Assert.assertTrue(testUtilityMethods.isElementDisplayed(itemInPG), "Item added is not present in saved cart");

		log.info("Item added is present in saved cart");

		return this;
	}

	@Step("Verify items added to saved cart")
	public SaveCartPageObjects verifyItemsInSavedCart(String[] partNumbers) {
		for (int i = 0; i < partNumbers.length; i++) {
			By itemInPG = By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
					+ partNumbers[i].trim() + "')]");
			Assert.assertTrue(testUtilityMethods.isElementDisplayed(itemInPG),
					"Item added is not present in saved cart");
		}
		log.info("Item added is present in saved cart");
		return this;
	}

	@Step("Verify alert text of bulk options")
	public SaveCartPageObjects verifyAlertTextsForBulkOptions(String[] alertTexts, String[] bulkOptions)
			throws Exception {
		for (int i = 0; i < bulkOptions.length; i++) {
			selectBulkActionsDropdown(bulkOptions[i].trim());
			commonOperations().verifyAlertTextPopUp(alertTexts[i].trim()).clickOnOkButtonInAlertPopUp();
		}
		log.info("Verified Alert Texts For Bulk Options");
		return this;
	}

	@Step("Verify item with part number:{0} is deleted from saved cart")
	public SaveCartPageObjects verifyItemIsDeletedFromSavedCart(String partNumber) {
		By itemInPG = By.xpath(
				"//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'" + partNumber + "')]");
		Assert.assertTrue(testUtilityMethods.isElementNotDisplayed(itemInPG), "Item is not deleted from product group");
		log.info("Verified Item is deleted from saved cart");
		return this;
	}

	@Step("verify quantity of an item in product group")
	public SaveCartPageObjects verifyQuantityOfItemInPG(String quantity, int specificItemQty) {
		Assert.assertEquals(quantityTextBoxLocator.get(specificItemQty - 1).getAttribute("value"), quantity);
		log.info("Verified quantity of item");
		return this;
	}

	@Step("Verify updated extn. price in saved cart")
	public SaveCartPageObjects verifyUpdatedExtPrice(float updatedExtnPrice, int specificItem) {
		float actualExtPrice = Float
				.parseFloat(extensionPriceInPGLocator.get(specificItem - 1).getText().replace("$", "").trim());
		Assert.assertEquals(actualExtPrice, updatedExtnPrice, "Price mismatch");
		log.info("Verified updated quantity of item");
		return this;
	}

	@Step("Verify updated extn. price in saved cart")
	public SaveCartPageObjects verifyUpdatedExtPrice(String updatedExtPrice, String partNumber)
			throws InterruptedException {
		Thread.sleep(3000);
		String actualExtPrice = getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Ext Price']/span"))
				.getText()
				.trim()
				.replace("$", "");
		Assert.assertEquals(Math.round(Float.parseFloat(actualExtPrice)), Math.round(Float.parseFloat(updatedExtPrice)),
				"Ext. Price not updated after quantity is updated");
		log.info("Verified updated quantity of item");
		return this;
	}

	@Step("Verify items headers available in save cart page :{0}")
	public SaveCartPageObjects verifyItemHeadersAvailableInSaveCartPage(String itemHeadersInSaveCartPage) {

		waiting.waitForVisibilityOfElements(itemHeadersLocatorsInSaveCartPage, 10);
		String[] expectedItemHeadersInSaveCartPage = itemHeadersInSaveCartPage.split(",");

		for (int i = 0; i < itemHeadersLocatorsInSaveCartPage.size(); i++) {

			Assert.assertEquals(itemHeadersLocatorsInSaveCartPage.get(i).getText().trim(),
					expectedItemHeadersInSaveCartPage[i].trim(),
					"Item Headers Of save cart Page are not" + expectedItemHeadersInSaveCartPage[i].trim() + ". It is :"
							+ itemHeadersLocatorsInSaveCartPage.get(i).getText().trim());
		}
		log.info("Verified Item header in saved cart");
		return this;
	}

	@Step("Verify specific item price :{0}")
	public String getPriceOfSpecificItem(String partNumber) {
		return getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber + "')]/ancestor::td/following-sibling::td[@data-th='Your Price']/span"))
				.getText()
				.trim()
				.replace("$", "");
	}

	@Step("Click on share cart link")
	public SharePopUpPageObjects clickOnShareCartLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(shareLocator, 6);
		shareLocator.click();
		Thread.sleep(1200);
		log.info("Clicked on Share cart link");
		return sharePopUp();
	}

	@Step("Verify Sorting :{2}")
	public SaveCartPageObjects verifySortByOption(List<String> manufacturerPN, List<String> partNumber,
			String sortOption) {
		waiting.waitForVisibilityOfElements(partNumberValueLocator, 10);
		List<String> pn = partNumberValueLocator.stream().map(WebElement::getText).collect(Collectors.toList());
		List<String> mpn = manufacturerPartNumberLocator.stream().map(WebElement::getText).collect(Collectors.toList());
		WebElement option = getDriver().findElement(By.xpath("//span[contains(text(),'" + sortOption.trim() + "')]"));
		switch (sortOption) {
		case "Part Number (Asc)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByLocator);
			waiting.waitForVisibilityOfElement(sortByLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Collections.sort(partNumber);
			Collections.sort(pn);
			Assert.assertEquals(partNumber, pn);
			break;
		case "Part Number (Desc)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByLocator);
			waiting.waitForVisibilityOfElement(sortByLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Collections.sort(partNumber, Collections.reverseOrder());
			Collections.sort(pn, Collections.reverseOrder());
			Assert.assertEquals(partNumber, pn);
			break;
		case "Manf Part# (Asc)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByLocator);
			waiting.waitForVisibilityOfElement(sortByLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Collections.sort(manufacturerPN);
			Collections.sort(mpn);
			Assert.assertEquals(manufacturerPN, mpn);
			break;
		case "Manf Part# (Desc)":
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sortByLocator);
			waiting.waitForVisibilityOfElement(sortByLocator, 10);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", option);
			Collections.sort(manufacturerPN, Collections.reverseOrder());
			Collections.sort(mpn, Collections.reverseOrder());
			Assert.assertEquals(manufacturerPN, mpn);
			break;
		}
		log.info("Verified Sort By functionality");
		return this;

	}

	@Step("List all PN's")
	public List<String> getPartNumbers() {
		return partNumberValueLocator.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	@Step("List all MPN's")
	public List<String> getManufacturerPartNumbers() {
		return manufacturerPartNumberLocator.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	@Step("Get PN of :{0}")
	public String getPartNumberOfSpecificItem(int specificItem) {
		return partNumberValueLocator.get(specificItem - 1).getText();
	}

	@Step("Search Save Cart :{0}")
	public SaveCartPageObjects searchSaveCart(String scName) {
		try {
			waiting.waitForVisibilityOfElement(serachTextBoxLocator, 10);
			serachTextBoxLocator.clear();
			serachTextBoxLocator.sendKeys(scName);
			log.info("Searched saved cart:" + scName);
		} catch (NoSuchElementException | TimeoutException e) {
			Assert.assertTrue(utils.isElementDisplayed(noSCAvailableLocator));
		}
		return this;
	}

	@Step("Verify Table Header Of My Save Cart Landing Page.")
	public SaveCartPageObjects verifyTableHeaderOfMySaveCartLandingPage(String tableHeaderOfMySaveCart) {
		try {
			waiting.waitForVisibilityOfElements(productGroupsPage().tableHeaderLocator, 10);

			String[] expectedTableHeaderOfMySaveCart = tableHeaderOfMySaveCart.split(",");
			for (int i = 0; i < productGroupsPage().tableHeaderLocator.size(); i++) {
				Assert.assertEquals(productGroupsPage().tableHeaderLocator.get(i).getText().trim(),
						expectedTableHeaderOfMySaveCart[i].trim(),
						"Table Header Of My Saved Cart is not : " + expectedTableHeaderOfMySaveCart[i].trim()
								+ ". It is :" + productGroupsPage().tableHeaderLocator.get(i).getText().trim());
				log.info("Verified header contents of save cart page");
			}
		} catch (TimeoutException e) {
			Assert.assertTrue(false, "Table Header Is Not Available For My Saved Cart for Logged In User.");
		}
		return this;
	}
}