package pageobjects;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.MainOperations;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ProductsDetailsPageObjects extends PageInitializer {

	Actions action = new Actions(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();
	
	MainOperations mainOperations = new MainOperations();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(ProductsDetailsPageObjects.class);

	@FindBy(xpath = "//input[@data-originalqty='originalQty']")
	private WebElement quantityTextBoxLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	public List<WebElement> breadCrumbs;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li/a")
	public List<WebElement> breadCrumbsLink;

	@FindBy(xpath = "//div[@id='yourPrices']")
	private WebElement yourPrice;

	@FindBy(xpath = "//*[@class='cimm_prodDetailTitle']")
	private WebElement itemTitleLocator;

	@FindBy(xpath = "//a[contains(@id,'enableCart')]")
	private WebElement addToCartButton;

	@FindBy(xpath = "(//a[contains(text(),'Add To Cart')])[2]")
	private WebElement addToCartButtonBeforeLogin;

	@FindBy(xpath = "(//a[contains(text(),'Checkout')])[2]")
	private WebElement checkoutButton;

	@FindBy(xpath = "//span[contains(text(),'MPN')]/following-sibling::span")
	private WebElement mpnValue;

	@FindBy(xpath = "//li[@id='mPartNo']/strong")
	private WebElement mpnLabel;

	@FindBy(xpath = "//a[@data-function='customerPartNumber']")
	private WebElement addRemoveCPNLocator;

	@FindBy(xpath = "//a[@data-target='#loginModal' and contains(text(),'Add / Remove CPN')]")
	private WebElement addRemoveCPNLocatorBeforeLogin;

	@FindBy(xpath = "(//a[contains(@data-function,'product')])[2]")
	private WebElement myProductGroupButton;

	@FindBy(xpath = "//div[contains(@class,'cimm_itemdetail')]//a[text()=' Add To Product Group']")
	private WebElement myProductGroupButtonBeforeLoin;

	@FindBy(xpath = "//ul[@class='resp-tabs-list domtabs']/descendant::span")
	private List<WebElement> productDetailsTabs;

	@FindBy(xpath = "//strong[contains(text(),'Brand Name')]")
	private WebElement brandNameLabel;

	@FindBy(xpath = "//strong[contains(text(),'Manufactur')]")
	private WebElement manufacturerLabel;

	@FindBy(xpath = "//a[@data-original-title='Send this Page']")
	private WebElement sendThisPageLink;

	@FindBy(xpath = "//a[@title='Print this page']")
	private WebElement printThisPageLink;

	@FindBy(xpath = "//li[@id='sitePartNo']/strong")
	private WebElement partNumberLabel;

	@FindBy(xpath = "//li[@id='minOrdQty']/strong")
	private WebElement minimumOrderQuantityLabel;

	@FindBy(xpath = "//li[@id='qtyInt']/strong")
	private WebElement quantityIntervalLabel;

	@FindBy(xpath = "//div[@id='ProdRating']")
	private WebElement proRatingLabel;

	@FindBy(xpath = "//strong[text()='Qty']")
	private WebElement quantityLabel;

	@FindBy(xpath = "//div[@id='yourPrices']/strong")
	private WebElement priceLabel;

	@FindBy(xpath = "//h4[contains(text(),'Manufactur')]/following-sibling::span")
	private WebElement filterManufactureresToggleButtonLocator;

	@FindBy(xpath = "//h4[contains(text(),'Manufactur')]")
	private WebElement filterManufacturersHeading;

	@FindBy(xpath = "//a[@data-original-title='Print this page']")
	private WebElement printLink;

	@FindBy(xpath = "//a[@id='sharePageTrigger']")
	private WebElement shareLink;

	@FindBy(xpath = "//div[contains(@class,'Accordion')]/descendant::a/span[not(contains(text(),'Video'))]")
	private List<WebElement> accordiansLocator;

	@FindBy(xpath = "//form[@id='reviewForm']")
	private WebElement reviewFormLocator;

	@FindBy(xpath = "//div[@id='rateit']")
	private WebElement ratingStarLocator;

	@FindBy(xpath = "//input[@id='title']/preceding-sibling::label")
	private WebElement titleLabelLocator;

	@FindBy(xpath = "//label[@for='Review']")
	private WebElement reviewLabelLocator;

	@FindBy(xpath = "//input[@id='savebtn']")
	private WebElement submitReviewButtonLocator;

	@FindBy(xpath = "//h3//button[@class='close']")
	private WebElement reviewCloseButtonLocator;

	@FindBy(xpath = "//div[@id='rateit']/img[not(@title='Remove Rating!')]")
	private List<WebElement> ratingImageLocator;

	@FindBy(xpath = "//input[@id='title']")
	private WebElement titleTexBoxLocator;

	@FindBy(xpath = "//textarea[@id='comments']")
	private WebElement reviewTextboxLocator;

	@FindBy(xpath = "//div[@id='yourPrices']")
	private WebElement itemsPriceWithPriceLabelLocator;

	@FindAll(value = { @FindBy(xpath = "(//a[@data-function='productGroupDropDown'])[2]"),
			@FindBy(xpath = "//div[contains(@class,'cimm_itemdetail')]//a[text()='Add To Product Group']") })
	private WebElement addToMyProductGroupButton;

	@FindBy(xpath = "//li[@id='shipBranchNames']/b")
	private WebElement shipBranchNameLabel;

	@FindBy(xpath = "//li[@id='upcNo']/strong")
	private WebElement upcLabel;

	@FindBy(xpath = "//li[@id='upcNo']//span")
	private WebElement upcValueLocator;

	@FindBy(xpath = "//div[@class='filterSelOptions']")
	private WebElement differentiatorLocatorForProductDetailVersion5;

	@FindBy(xpath = "//ul[contains(@class,'filterScroll')]")
	private WebElement differentiatorLocator;

	@FindBy(xpath = "//div[@class='toggle_attributeList']")
	private WebElement differentiatorLocatorForProductDetailVersion2;

	@FindBy(xpath = "//span[contains(text(),'Add Items to')]/..")
	private WebElement bulkOptionAddToProductGroupLocatorInProductDetailPage;

	@FindBy(xpath = "//div[@class='select_attributeList']/strong")
	private WebElement attributeValueLocator;

	@FindBy(xpath = "//th[contains(text(),'ropertie')]/ancestor::thead/following-sibling::tbody//strong")
	private List<WebElement> attributeLocators;

	@FindBy(xpath = "//div[@class='compareWrap']/strong")
	private WebElement productChoiceItemLocator;

	@FindBy(xpath = "//a[@class='imgEnlargeIcon']")
	private WebElement enlargeIcon;

	@FindBy(xpath = "//button[@id='update']")
	private WebElement updateButtonLocator;

	@FindBy(xpath = "//button[@id='remove']")
	private WebElement removeButtonLocator;

	@FindBy(xpath = "//a[@id='sharePageTrigger']")
	private WebElement shareThisPageLinkLocator;

	@FindBy(xpath = "//a[@data-target='#ratingModal']")
	private WebElement writeReviewLinkLocator;

	@FindBy(xpath = "//div[@class='cimm_itemdetail-image']/descendant::img")
	private WebElement productImage;

	@FindBy(xpath = "//img[@id='fullResImage']")
	private WebElement fullProductImage;

	/*
	 * @FindBy(xpath = "//div[@class='zoomLens']") private WebElement
	 * fullProductImage;
	 */

	@FindBy(xpath = "//div[contains(@class,'cimm_itemdetail')]//input[@placeholder='Enter New Group Name']")
	private WebElement myProductGroupTextbox;

	@FindBy(xpath = "(//a[@class='popMsg hideMe'])[2]")
	private WebElement successMessageForProdGroupCreationLocator;

	@FindBy(xpath = "(//a[contains(@data-function,'product')]/preceding-sibling::a)[2]")
	private WebElement productGroupCreationMsg;

	@FindBy(xpath = "//ul/descendant::a[contains(text(),'My Product Groups')]")
	private WebElement myProductGroupsUnderGroups;

	@FindBy(xpath = "//h4[contains(text(),'Groups')]/following-sibling::span")
	private WebElement groupsToggleButton;

	@FindBy(xpath = "//input[@id='newCustomerPartNumber']")
	private WebElement customerPartNumberTextbox;

	@FindBy(xpath = "//button[@id='add']")
	private WebElement addButton;

	@FindBy(id = "remove")
	private WebElement removeButton;

	@FindBy(xpath = "//ul[@id='CustomerPartNoListDropDown']/descendant::input[@id='remove']")
	private WebElement cpnRemoveButton;

	@FindBy(xpath = "//dl/descendant::h4")
	private List<WebElement> leftPanelNames;

	@FindBy(xpath = "//dl/descendant::h4/following-sibling::span")
	private List<WebElement> toggleButtons;

	@FindBy(xpath = "//li[@id='mPartNo']/descendant::span")
	private WebElement mpnValueLocator;

	@FindBy(xpath = "//li[@itemprop='brand']//span")
	private WebElement brandNameLocator;

	@FindBy(xpath = "//li[@id='sitePartNo']//span")
	private WebElement partNumberValueLocator;

	@FindBy(xpath = "//span[@id='mulitFilterTrailDetailPage']//li/span[@class='refine-value']")
	private WebElement differentiatorValueLocatorAfterSelecting;

	@FindBy(xpath = "//td[@title='Compare']/descendant::input")
	private List<WebElement> compareCheckboxesUnderMoreChoicesLocator;

	@FindBy(xpath = "//a[contains(@onclick,'compareItems')]")
	private WebElement compareLinkLocator;

	@FindBy(xpath = "//div[contains(text(),'Product Choices')]")
	private WebElement productChoicesLocator;

	@FindBy(xpath = "//div[@id='yourPrices']//span[@class='priceSpan']")
	private WebElement itemsPriceLocator;

	@FindBy(xpath = "//strong[contains(text(),'Availa')]/following-sibling::span[1]")
	private WebElement itemsAvailabilityLocator;

	@FindBy(xpath = "//td[@class='tabelImage details-control']/img")
	private List<WebElement> productChoicesImagesLocator;

	@FindBy(xpath = "//input[@id='newCustomerPartNumber']")
	private WebElement cpnTextboxLocator;

	@FindBy(xpath = "//a[@title='Send this Page']")
	private WebElement shareLocator;

	@FindBy(xpath = "//li[@id='sitePartNo']/descendant::span")
	private List<WebElement> partNumberUnderProductChoicesLocator;

	@FindBy(xpath = "//td[@class='tabelImage details-control']/descendant::span[2]")
	private List<WebElement> partNumberAboveProductImageLocator;

	@FindBy(xpath = "//h1[@itemprop='description']")
	private WebElement shortDescriptionLocator;

	@FindBy(xpath = "//select[@class='multipleUom']")
	private WebElement multipleUOMDropdownLocator;

	@FindBy(xpath = "//div[@id='yourPrices']//span")
	private WebElement priceLocator;

	@FindBy(xpath = "//div[@id='productModeItemContent']")
	private WebElement relatedProductsLocator;

	@FindBy(xpath = "//*[contains(@class,'prodDetailTitle')]")
	private WebElement productNameLocator;

	@FindBy(xpath = "//div[@class='compareWrap clearAfter']/strong")
	private WebElement productChoiceItemLocatorForVersion3;

	@FindBy(xpath = "//span[contains(text(),'Clear All')]/..")
	private WebElement clearAllLocator;

	@FindBy(xpath = "//div[contains(@class,'compare_head')]/strong/a[@onclick='clearCookie();']")
	private WebElement cancleCompareLinkLocator;

	@FindBy(xpath = "//ul[@role='listbox']/li/a/span[@class='text']")
	private List<WebElement> differentiatorValueLocatorForVesrion5;

	@FindBy(xpath = "//button[@class='btn dropdown-toggle btn-default']")
	private WebElement DifferentiatorLocator;

	@FindBy(xpath = "//div[@id='specificationSection']//strong[contains(text(),'Size')]/../following-sibling::td[2]/span")
	private WebElement differentiatorValueLocatorUnderSpecificationTab;

	@FindBy(xpath = "//td[@title='Item Information']")
	private List<WebElement> expandMoreChoicesInProductModeLocator;

	@FindBy(xpath = "//td[@title='Item Information']")
	private WebElement expandChoicesLocator;

	@FindBy(xpath = "//input[@id='popup_combine']")
	private WebElement combineOptionInMyCartPopUpLocator;

	@FindBy(xpath = "//input[@id='popup_separate']")
	private WebElement seperateOptionInMyCartPopUpLocator;

	@FindBy(xpath = "//input[@id='popup_cancel']")
	private WebElement cancelOptionInMyCartPopUpLocator;

	@FindBy(xpath = "//div[@id='popup_content']")
	private WebElement confirmAddToCartOptionLocator;

	@FindBy(xpath = "//a[@data-original-title='Send this Page']")
	private WebElement sendLink;

	@FindBy(xpath = "//span[@id='custPartBlock']")
	private WebElement cpnValueLocator;

	@FindBy(xpath = "//div[@class='cimm_itemdetail-image']")
	private WebElement imageLocator;

	// @FindBy(xpath = "//a[@href='#']//img[@itemprop='image']")
	@FindBy(xpath = "//img[@id='img_0']")
	private WebElement thumbnailImageLocator;

	@FindBy(xpath = "//h3[contains(text(),'DESCRIPTION')]")
	private WebElement descriptionTabLocator;

	@FindBy(xpath = "//h3[@data-hashid='#specificationSection']")
	private WebElement specificationsTabLocator;

	@FindBy(xpath = "//h3[@data-hashid='#featureSection']")
	private WebElement featureTabLocator;

	@FindBy(xpath = "//h3[@ data-hashid='#documentsSection']")
	private WebElement documentsTabLocator;

	@FindBy(xpath = "//h3[label[text()='Recently Viewed Items']]/..")
	private WebElement recentlyViewedItemsLocator;

	@Step("Get Product Name")
	public String getProductName() {
		String productName = itemTitleLocator.getText().trim();
		log.info("Item title is :" + productName);
		return productName;
	}

	@Step("Verify display of quantity label")
	public ProductsDetailsPageObjects verifyDisplayOfQuantity() {
		Assert.assertEquals(quantityLabel.getText().replace(":", "").trim(), "Qty");
		log.info("Verified quantity label");
		return this;
	}

	@Step("Verify display of send link")
	public ProductsDetailsPageObjects verifyDisplayOfSendLink() {
		Assert.assertTrue(sendLink.isDisplayed(), "Send This Page is not displayed");
		log.info("Send This Page is displayed");
		return this;
	}

	@Step("Verify display of share link")
	public ProductsDetailsPageObjects verifyDisplayOfShareLink() {
		Assert.assertTrue(shareLink.isDisplayed());
		log.info("Verified display of share link");
		return this;
	}

	@Step("Verify display of UPC label")
	public ProductsDetailsPageObjects verifyDisplayOfUPCText() {
		Assert.assertEquals(upcLabel.getText().replace(":", "").trim(), "UPC");

		log.info("UPC label has been verified" + " with [Actual] and [Expected] value as " + "["
				+ upcLabel.getText().replace(":", "").trim() + "] and " + "[" + "UPC" + "]");
		return this;
	}

	@Step("Click on enlarge icon")
	public ProductsDetailsPageObjects clickOnEnlargeIcon() throws InterruptedException {

		waiting.waitForVisibilityOfElement(enlargeIcon, 10);
		utils.moveToElementAndClick(enlargeIcon);
		Thread.sleep(1200);
		log.info("Clicked on enlarge icon");
		return this;
	}

	public int getHeightOfTheImage() {
		waiting.waitForVisibilityOfElement(productImage, 20);
		Integer height = Integer.parseInt(productImage.getAttribute("height"));
		int intheight = height.intValue();
		return intheight;
	}

	public int getWidthOfTheImage() {
		Integer width = Integer.parseInt(productImage.getAttribute("width"));
		int intwidth = width.intValue();
		return intwidth;
	}

	@Step("Verify image height and width after enlarge is greater than {0} {1}")
	public ProductsDetailsPageObjects verifyImageHeightAndWidthAfterEnlarge(int height, int width) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Assert.assertTrue(assertImageHeight(height),
				"The enlarged image height is less than the image present in the PDP page.");
		Assert.assertTrue(assertImageWidth(width),
				"The enlarged image width is less than the image present in the PDP page.");
		log.info("Verified image height and width after enlarge");
		return this;

	}

	@Step("Verify The Functionality Of Image Maginifier Icon.")
	public ProductsDetailsPageObjects verifyImageMagnifierIconFuncationality() {
		waiting.waitForVisibilityOfElement(fullProductImage, 10);
		Assert.assertTrue(fullProductImage.isDisplayed(), "Magnifier Icon is not working.");
		log.info("Magnifier Icon is working.");
		return this;
	}

	private boolean assertImageWidth(int height) {
		Integer heightFromWeb = Integer.parseInt(fullProductImage.getAttribute("height").replace("px", "").trim());
		int actualHeight = heightFromWeb.intValue();
		return actualHeight >= height;
	}

	private boolean assertImageHeight(int width) {
		Integer widthFromWeb = Integer.parseInt(fullProductImage.getAttribute("width").replace("px", "").trim());
		int actualWidth = widthFromWeb.intValue();
		return actualWidth >= width;
	}

	@Step("Click on My Product Group button")
	public ProductsDetailsPageObjects clickOnMyProductGroupButton() throws Exception {
		waiting.waitForVisibilityOfElement(myProductGroupButtonBeforeLoin, 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myProductGroupButtonBeforeLoin);
		logger.info("Clicked on My Product Group button.");
		return this;
	}

	@Step("Click On My Product Group Button In Product Detail page for product mode version 5")
	public ProductsDetailsPageObjects clickOnMyProductGroupButtonInVersion5() throws InterruptedException {
		waiting.waitForVisibilityOfElement(addToMyProductGroupButton, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addToMyProductGroupButton);
		Thread.sleep(1200);
		log.info("Clicked On My Product Group Button In Product Detail page for product mode version 5");
		return this;

	}

	@Step("Enter group name {0}")
	public ProductsDetailsPageObjects enterGroupName(String myProductGroupName) {
		waiting.waitForVisibilityOfElement(myProductGroupTextbox, 6);
		myProductGroupTextbox.sendKeys(myProductGroupName);
		logger.info("Enter group name : " + myProductGroupName);
		return this;
	}

	@Step("Press enter")
	public ProductsDetailsPageObjects hitEnter() throws InterruptedException {
		myProductGroupTextbox.sendKeys(Keys.ENTER);
		Thread.sleep(1200);
		logger.info("Press enter.");
		return this;
	}

	@Step("Click on confirmation message of product group creation")
	public MyProductGroupsPageObjects clickOnTheConfirmationMessage() throws InterruptedException {
		waiting.waitForVisibilityOfElement(successMessageForProdGroupCreationLocator, 4);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				successMessageForProdGroupCreationLocator);
		Thread.sleep(1500);
		log.info("Clicked on confirmation message of product group created");
		return productGroupsPage();
	}

	@Step("Verify My Product Group creation success message is {0}")
	public ProductsDetailsPageObjects verifyMyProductCreationSuccessMsg(String myProductGroupName) {
		waiting.waitForVisibilityOfElement(productGroupCreationMsg, 15);
		String expectedMsg = (itemTitleLocator.getText().trim() + " Added To Group - " + myProductGroupName)
				.toLowerCase();
		Assert.assertEquals(productGroupCreationMsg.getText().toLowerCase().trim(), expectedMsg);
		logger.info("My Product Group Creation Success Message has been verified"
				+ " with [Actual] and [Expected] value as " + "["
				+ productGroupCreationMsg.getText().toLowerCase().trim() + "] and " + "[" + expectedMsg + "]");
		return this;
	}

	@Step("Click on add or remove CPN")
	public ProductsDetailsPageObjects clickOnAddOrRemoveCustomerPartNumber() throws Exception {
		waiting.waitTillPageLoads();
		waiting.waitForVisibilityOfElement(addRemoveCPNLocator, 3);
		addRemoveCPNLocator.click();
		log.info("Clicked on add or remove CPN");
		return this;
	}

	@Step("Click on add or remove CPN")
	public ProductsDetailsPageObjects clickOnAddOrRemoveCustomerPartNumberBeforeLogin() throws Exception {
		waiting.waitForVisibilityOfElement(addRemoveCPNLocatorBeforeLogin, 10);
		addRemoveCPNLocatorBeforeLogin.click();
		log.info("Clicked on add or remove CPN");
		return this;
	}

	@Step("Click On Update Button of AddOrTemoveCustomerPartNumber.")
	public ProductsDetailsPageObjects clickOnUpdateButtonOfAddOrRemoveCustomerPartNumber() throws InterruptedException {
		waiting.waitForVisibilityOfElement(updateButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", updateButtonLocator);
		Thread.sleep(1200);
		log.info("Clicked On Update Button of AddOrTemoveCustomerPartNumber");
		return this;
	}

	@Step("Click On Remove Button of AddOrTemoveCustomerPartNumber.")
	public ProductsDetailsPageObjects clickOnRemoveButtonOfAddOrRemoveCustomerPartNumber() throws InterruptedException {
		waiting.waitForVisibilityOfElement(removeButtonLocator, 10);
		removeButtonLocator.click();
		Thread.sleep(1200);
		log.info("Clicked On Remove Button of AddOrTemoveCustomerPartNumber");
		return this;
	}

	@Step("Enter CPN {0}")
	public ProductsDetailsPageObjects enterCPN(String customerPartNumber) throws Exception {
		waiting.waitForVisibilityOfElement(cpnTextboxLocator, 15);
		cpnTextboxLocator.sendKeys(customerPartNumber);
		cpnTextboxLocator.sendKeys(Keys.ENTER);
		log.info("Enter CPN  : " + customerPartNumber);
		return this;
	}

	@Step("Click on add button")
	public ProductsDetailsPageObjects clickOnAddButton() throws InterruptedException {
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addButton);
		log.info("Clicked on add button");
		return this;
	}

	@Step("Click on {0} st/nd/rd checkbox")
	public ProductsDetailsPageObjects clickOnCheckbox(String customerPartNumber) throws Exception {
		Thread.sleep(1700);
		String customerPartNumberCheckbox = "//input[@value='" + customerPartNumber + "']";
		getDriver().findElement(By.xpath(customerPartNumberCheckbox)).click();
		return this;

	}

	@Step("Click on remove button")
	public ProductsDetailsPageObjects clickOnRemove() throws Exception {
		Thread.sleep(1700);
		action.click(removeButton).build().perform();
		Thread.sleep(1700);
		return this;
	}

	public boolean verifyDeletionOfCPN(String customerPartNumber) throws Exception {
		Thread.sleep(1500);
		try {
			Assert.assertTrue(getDriver().findElement(By.xpath("//td[contains(text(),'" + customerPartNumber + "')]"))
					.isDisplayed());
		} catch (NoSuchElementException e) {
			return true;
		}
		return false;
	}

	@Step("Verify pdp filter section when logged in")
	public ProductsDetailsPageObjects verifyPDPFilterSectionWhenLoggedIn() {
		/*
		 * String a[] = data .getFilterNamesInPDPAfterLogin() .split(","); for (int i =
		 * 0; i < leftPanelNames.size(); i++) { Assert.assertEquals(leftPanelNames
		 * .get(i) .getText() .trim() .toLowerCase(), a[i] .trim() .toLowerCase(),
		 * "Getting left Panel name as " + leftPanelNames .get(i) .getText() .trim() +
		 * " but expected " + a[i].trim() + ". "); }
		 */
		return this;
	}

	@Step("Verify pdp filter section toggle buttons")
	public ProductsDetailsPageObjects verifyPDPFilterSectionToggleButtons() {
		Assert.assertEquals(leftPanelNames.size(), toggleButtons.size());
		return this;
	}

	@Step("Click on add to cart button")
	public ProductsDetailsPageObjects clickOnAddToCartButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(addToCartButton, 15);
		log = Logger.getLogger("clickOnAddToCartButton");
		String itemPrice;
		int i = 0;
		do {
			Thread.sleep(2000);
			itemPrice = itemsPriceLocator.getText();
			i++;
			if (i > 3) {
				log.info("Waiting to price load... :" + i);
				break;
			}
			i++;
		} while (!(itemPrice.contains("$")));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addToCartButton);
		Thread.sleep(1500);
		log.info("Clicked on Add To cart Button");
		return this;
	}

	@Step("Click on add to cart button")
	public ProductsDetailsPageObjects clickOnAddToCartButtonBeforeLogin() throws InterruptedException {
		waiting.waitForVisibilityOfElement(addToCartButtonBeforeLogin, 15);
		log = Logger.getLogger("clickOnAddToCartButton");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addToCartButtonBeforeLogin);
		Thread.sleep(1500);
		log.info("Clicked on Add To cart Button");
		return this;
	}

	@Step("Enter quantity {0}")
	public ProductsDetailsPageObjects enterQuanityInProductDetailsPage(String quantity) throws Exception {
		log = Logger.getLogger("enterQuanityInProductDetailsPage");
		/*
		 * quantityTextBoxLocator.click(); quantityTextBoxLocator.clear();
		 * quantityTextBoxLocator.sendKeys(quantity);
		 */
		quantityTextBoxLocator.sendKeys(Keys.BACK_SPACE);
		quantityTextBoxLocator.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		quantityTextBoxLocator.sendKeys(Keys.DELETE);
		Thread.sleep(1200);
		quantityTextBoxLocator.sendKeys(quantity);
		log.info("Entered quantity : " + quantity);
		return this;
	}

	@Step("get MPN of Items")
	public String getMPN() {
		log = Logger.getLogger("getMPN");
		return mpnValueLocator.getText().trim();
	}

	@Step("get Brand of Items")
	public String getBrandName() {
		log = Logger.getLogger("getBrandName");
		return brandNameLocator.getText().trim();
	}

	@Step("Verify Item Title is combination of :{0}")
	public ProductsDetailsPageObjects verifyItemTitleShouldBeCombinationOfBrandAndMPN(String title) {

		log = Logger.getLogger("verifyItemTitleShouldBeCombinationOfBrandAndMPN");
		waiting.waitForVisibilityOfElement(itemTitleLocator, 10);

		Assert.assertEquals(itemTitleLocator.getText().toUpperCase().trim(), title.toUpperCase().trim(),
				"Item Title is not :" + title.toUpperCase().trim() + ". It is :"
						+ itemTitleLocator.getText().toUpperCase().trim());
		log.info("Item Title Combination has been verified" + " with [Actual] and [Expected] value as " + "["
				+ itemTitleLocator.getText().toUpperCase().trim() + "] and " + "[" + title.toUpperCase().trim() + "]");
		return this;
	}

	@Step("Verify product name {0}")
	public ProductsDetailsPageObjects verifyProductName(String productNameFromShoppingCart) {
		Assert.assertTrue(itemTitleLocator.getText().trim().equalsIgnoreCase(productNameFromShoppingCart),
				"Actual : " + itemTitleLocator.getText().trim() + " , Expected : " + productNameFromShoppingCart + ".");
		return this;
	}

	@Step("Verify part number is {0}")
	public ProductsDetailsPageObjects verifyPartNumberInProductDetailsPage(String searchPartNumber) throws Exception {
		logger = Logger.getLogger("verifyPartNumberInProductDetailsPage");
		Assert.assertEquals(getDriver().findElement(By.xpath("//span[contains(text(),'" + searchPartNumber + "')]"))
				.getText()
				.trim(), searchPartNumber);
		Thread.sleep(1500);
		logger.info("Verified part number");
		return this;
	}

	public boolean assertPartNumberUnderProductChoices(String searchPartNumber) throws Exception {

		if (productChoicesLocator.isDisplayed()) {
			for (WebElement partNumberUnderProductChoiceLocator : partNumberUnderProductChoicesLocator) {

				waiting.waitForVisibilityOfElement(partNumberUnderProductChoiceLocator, 10);
				if (partNumberUnderProductChoiceLocator.getText().trim().equals(searchPartNumber)) {
					return true;
				}
			}
		}
		return false;
	}

	@Step("Verify mpn is {0}")
	public ProductsDetailsPageObjects verifyManufacturerPartNumberInProductDetailsPage(String searchTextForMPNTest)
			throws Exception {
		Assert.assertEquals(mpnValueLocator.getText().trim(), searchTextForMPNTest, "MPN is not present");
		return this;
	}

	@Step("Verify UPC is {0}")
	public ProductsDetailsPageObjects verifyUPCInProductDetailsPage(String searchText) throws Exception {
		waiting.waitForVisibilityOfElement(upcValueLocator, 10);
		Assert.assertEquals(upcValueLocator.getText().trim(), searchText);
		return this;
	}

	@Step("Verify CPN is {0}")
	public ProductsDetailsPageObjects verifyCPNInProductDetailsPage(String searchText) {
		waiting.waitForVisibilityOfElement(cpnValueLocator, 10);
		Assert.assertTrue(cpnValueLocator.getText().trim().contains(searchText.trim()), "CPN value not found");
		return this;
	}

	public boolean assertUPC(String searchTextForUPCLabelTest) throws Exception {

		if (productChoicesLocator.isDisplayed()) {
			for (WebElement productChoiceImage : productChoicesImagesLocator) {

				((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", productChoiceImage);
				Thread.sleep(2000);
				waiting.waitForVisibilityOfElement(upcValueLocator, 10);
				if (upcValueLocator.getText().trim().equals(searchTextForUPCLabelTest)) {
					return true;
				}
			}
		}
		return false;
	}

	@Step("Click on compare link")
	public ProductsDetailsPageObjects clickOnCompareLink() {
		compareLinkLocator.click();
		return this;
	}

	@Step("Get Part Number of items")
	public String getPartNumber() {
		logger = Logger.getLogger("getPartNumber");
		logger.info("Part Number of the item is :" + partNumberValueLocator.getText().trim());
		return partNumberValueLocator.getText().trim();

	}

	@Step("Get Quantity of items")
	public int getQuantity() {
		logger = Logger.getLogger("getQuantity");
		waiting.waitForVisibilityOfElement(quantityTextBoxLocator, 10);
		int qty = Integer.parseInt(quantityTextBoxLocator.getAttribute("value").trim());
		return qty;
	}

	@Step("Get Short Description of items")
	public String getShortDescription() {

		logger = Logger.getLogger("getShortDescription");

		logger.info("Short Description of the item is :" + shortDescriptionLocator.getText().trim());

		return shortDescriptionLocator.getText().trim();

	}

	public ProductsDetailsPageObjects verifyUOMDropdown(String[] expectedMultipleUOMs) {
		Select select = new Select(multipleUOMDropdownLocator);
		for (int i = 0; i < expectedMultipleUOMs.length; i++) {
			Assert.assertEquals(select.getOptions().get(i).getText().trim(), expectedMultipleUOMs[i]);
		}
		return this;
	}

	public ProductsDetailsPageObjects selectSpecificUOM(String specificUOM) {
		Select select = new Select(multipleUOMDropdownLocator);
		select.selectByVisibleText(specificUOM);
		return this;
	}

	public Number getPriceForSingleItem() throws ParseException {
		String priceLocatorArray[] = priceLocator.getText().split("/");
		Number price = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(priceLocatorArray[0].replace("\n", "").replace(" ", ""));
		return price;
	}

	public void checkLatestPrice(Number priceForSingleItem, String quantity) throws Exception {
		Thread.sleep(1000);
		String priceLocatorArray[] = priceLocator.getText().split("/");
		Number afterUpdatePrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(priceLocatorArray[0].replace("\n", "").replace(" ", "").trim());
		int quantityValue = Integer.parseInt(quantity);
		Assert.assertTrue(checkForExtnPrice(priceForSingleItem, afterUpdatePrice, quantityValue),
				"extension price is not getting updated.");
	}

	private boolean checkForExtnPrice(Number priceForSingleItem, Number afterUpdatePrice, int quantityValue) {
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		String previous = oneDigit.format(priceForSingleItem.doubleValue() * quantityValue);
		String after = oneDigit.format(afterUpdatePrice.doubleValue());

		return previous.equals(after);
	}

	public ProductsDetailsPageObjects checkUOMChange(String uomName) {
		String priceLocatorArray[] = priceLocator.getText().split("/");
		String actualUOMName[] = priceLocatorArray[1].split("\\(");
		Assert.assertEquals(actualUOMName[0].replace("\n", "").trim(), uomName);
		return this;
	}

	public Number getPriceForSingleItemWhichHasMultipleUOM() throws ParseException {
		waiting.waitForVisibilityOfElement(priceLocator, 5);
		String priceLocatorArray[] = priceLocator.getText().split("/");
		Number price = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(priceLocatorArray[0].replace("\n", "").replace(" ", ""));
		return price;
	}

	@Step("Verify whether breadcrumb contains {0}")
	public ProductsDetailsPageObjects verifyBrandBreadCrumb(String nameOfTheBrand) {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.replace("/", "")
						.trim()
						.contains(nameOfTheBrand),
				"Breadcrump does not contain the brand that is clicked. It is "
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim());
		return this;
	}

	@Step("Verify whether title contains {0}")
	public ProductsDetailsPageObjects verifyTitleOfTheBrand(String nameOfTheBrand, String productName)
			throws Exception {
		Thread.sleep(2500);
		Assert.assertTrue(getDriver().getTitle().trim().contains(nameOfTheBrand),
				"The title does not contain the brand that was clicked. Actual Title is "
						+ getDriver().getTitle().trim() + "." + "But Expected : " + nameOfTheBrand + " | " + productName
						+ ".");
		Assert.assertTrue(getDriver().getTitle().trim().contains(" | " + productName),
				"The title does not contain the product name.");
		Assert.assertFalse(getDriver().getTitle().trim().startsWith("|"), "The title does start with | .");
		return this;

	}

	public String getPrice() {

		logger = Logger.getLogger("getPrice");

		float price = Float.parseFloat(priceLocator.getText().replace("$", "").replace("/ EACH  ", "").trim());

		float quantity = Float.parseFloat(quantityTextBoxLocator.getAttribute("value").trim());

		logger.info("Price of the item : " + String.valueOf(Math.round(price * quantity)));

		return String.valueOf(Math.round(price * quantity));
	}

	public ProductsDetailsPageObjects selectAddToCartOption(String option) throws Exception {
		Thread.sleep(1000);
		waiting.waitForVisibilityOfElement(confirmAddToCartOptionLocator, 10);
		switch (option) {
		case "Combine":
			combineOptionInMyCartPopUpLocator.click();
			break;

		case "Separate":
			seperateOptionInMyCartPopUpLocator.click();
			break;

		case "Cancel":
			cancelOptionInMyCartPopUpLocator.click();
			break;

		default:
			throw new Exception(("Invalid Option"));
		}
		return this;
	}

	@Step("Click On Send This Page Link")
	public SharePageObjects clickOnSendThisPageLink() throws InterruptedException {
		log = Logger.getLogger("clickOnSendThisPageLink");
		waiting.waitForVisibilityOfElement(sendThisPageLink, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", sendThisPageLink);
		log.info("Clicked on send this page");
		Thread.sleep(1200);
		return sharePage();
	}

	@Step("Click On Share This Page Link")
	public ProductsDetailsPageObjects clickOnShareThisPage() throws InterruptedException {
		waiting.waitForVisibilityOfElement(shareThisPageLinkLocator, 10);
		shareThisPageLinkLocator.click();
		Thread.sleep(1200);
		return this;
	}

	@FindBy(xpath = "//a[@data-original-title='Print this page']")
	private WebElement printThisPageLinkLocator;

	public ProductsDetailsPageObjects clickOnPrintTisPage() throws InterruptedException {
		waiting.waitForVisibilityOfElement(printThisPageLinkLocator, 10);
		printThisPageLinkLocator.click();
		Thread.sleep(1200);
		return this;
	}

	@Step("Click On Write Review Link.")
	public ProductsDetailsPageObjects clickOnWriteReviewLink() throws InterruptedException {
		log = Logger.getLogger("clickOnWriteReviewLink");
		waiting.waitForVisibilityOfElement(writeReviewLinkLocator, 10);
		writeReviewLinkLocator.click();
		Thread.sleep(1200);
		log.info("Clicked On Write Review Link.");
		return this;

	}

	@Step("Select Rating for Items:{0}")
	public ProductsDetailsPageObjects selectRatingStar(int specificStar) throws InterruptedException {
		log = Logger.getLogger("selectRatingStar");
		waiting.waitForVisibilityOfElements(ratingImageLocator, 10);
		for (int i = 0; i < specificStar; i++) {
			Actions actions = new Actions(getDriver());
			actions.moveToElement(ratingImageLocator.get(i)).click(ratingImageLocator.get(i)).build().perform();
			Thread.sleep(1200);
		}
		log.info("Select Rating for Items: " + specificStar);
		return this;
	}

	@Step("Enter Title For The Item In Write Review Popup :{0}")
	public ProductsDetailsPageObjects enterTitle(String titleName) {
		log = Logger.getLogger("enterTitle");
		titleTexBoxLocator.clear();
		titleTexBoxLocator.sendKeys(titleName);
		log.info("Enter Title For The Item In Write Review Popup : " + titleName);
		return this;
	}

	@Step("Enter Review For The Item In Write Review Popup :{0}")
	public ProductsDetailsPageObjects enterReview(String review) {
		log = Logger.getLogger("enterReview");
		reviewTextboxLocator.clear();
		reviewTextboxLocator.sendKeys(review);
		log.info("Enter Review For The Item In Write Review Popup : " + review);
		return this;
	}

	@FindBy(xpath = "//div[@class='bootbox-body']")
	private WebElement priya;

	public ProductsDetailsPageObjects verifySuccessMessageForReviewSubmission() throws InterruptedException {
		waiting.waitForVisibilityOfElement(priya, 10);
		String messageText = ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerHTML", priya)
				.toString();

		System.out.println(messageText);
		/*
		 * assertThat(messageText,equals("expected"); shareimprov
		 */
		return this;
	}

	@Step("Click On Submit Review Button.")
	public ProductsDetailsPageObjects clickOnSubmitReviewButton() throws InterruptedException {
		log = Logger.getLogger("clickOnSubmitReviewButton");
		submitReviewButtonLocator.click();
		Thread.sleep(1800);
		log.info("Clicked On Submit Review Button.");
		return this;
	}

	@Step("Verify Write Review Popup fields.")
	public ProductsDetailsPageObjects verifyWriteReviewPopUp() {
		log = Logger.getLogger("verifyWriteReviewPopUp");
		waiting.waitForVisibilityOfElement(reviewFormLocator, 10);
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(utils.isElementDisplayed(reviewFormLocator), "Review Form is not displaying.");
		log.info("Review Form is displaying.");
		soft.assertTrue(utils.isElementDisplayed(ratingStarLocator), "Rating Star is not displaying.");
		log.info("Rating Star is displaying.");
		soft.assertEquals(titleLabelLocator.getText().trim().replace(" *", ""), "Title",
				"Title Label Of Wrire Review is not :" + "Title" + ". It is :"
						+ titleLabelLocator.getText().trim().replace(" *", ""));
		logger.info("Product Title label has been verified" + " with [Actual] and [Expected] value as " + "["
				+ titleLabelLocator.getText().trim().replace(" *", "") + "] and " + "[" + "Title" + "]");
		soft.assertEquals(reviewLabelLocator.getText().trim().replace(" *", ""), "Review",
				"Review Label Of Wrire Review is not :" + "Review" + ". It is :"
						+ reviewLabelLocator.getText().trim().replace(" *", ""));
		logger.info("Review label  has been verified" + " with [Actual] and [Expected] value as " + "["
				+ reviewLabelLocator.getText().trim().replace(" *", "") + "] and " + "[" + "Review" + "]");
		soft.assertTrue(utils.isElementDisplayed(submitReviewButtonLocator), "Submit Review Button is not displaying.");
		log.info("Submit Review Button is displaying.");
		soft.assertTrue(utils.isElementDisplayed(reviewCloseButtonLocator), "Review Close Button is not displaying.");
		log.info("Review Close Button is displaying.");
		soft.assertAll();
		return this;
	}

	@Step("Click On Review Close Button.")
	public ProductsDetailsPageObjects clickOnReviewCloseButton() throws InterruptedException {
		log = Logger.getLogger("clickOnReviewCloseButton");
		waiting.waitForVisibilityOfElement(reviewCloseButtonLocator, 10);
		reviewCloseButtonLocator.click();
		Thread.sleep(1200);
		log.info("Clicked On Review Close Button.");
		return this;
	}

	@FindBy(xpath = "//ol[@class='sharePageWrap sharePageWrapTog']/li")
	private List<WebElement> shareThisPageOptionLocator;

	public ProductsDetailsPageObjects clickOnShareThisPageOption() throws InterruptedException {

		waiting.waitForVisibilityOfElements(shareThisPageOptionLocator, 10);

		for (int i = 0; i < shareThisPageOptionLocator.size(); i++) {
			shareThisPageOptionLocator.get(i).click();
			Thread.sleep(1200);
		}
		return this;

	}

	@Step("Verify whether product name is displayed or not")
	public ProductsDetailsPageObjects verifyDisplayOfNameOfTheProduct() {
		log = Logger.getLogger("verifyDisplayOfNameOfTheProduct");
		Assert.assertTrue(productNameLocator.isDisplayed(), "Name of the product is not displayed");
		log.info("Name of the product is  displayed");
		return this;
	}

	public ProductsDetailsPageObjects verifyDisplayOfAddRemoveCPN() {

		logger = Logger.getLogger("verifyDisplayOfAddRemoveCPN");

		Assert.assertTrue(addRemoveCPNLocator.isDisplayed(), "Add/Remove CPN Link is not displayed");

		logger.info("Add/Remove CPN Link is displayed");
		return this;
	}

	@Step("Verify display of part number label")
	public ProductsDetailsPageObjects verifyDisplayOfPartNumber() {
		log = Logger.getLogger("verifyDisplayOfPartNumber");
		Assert.assertEquals(partNumberLabel.getText().replace(":", "").trim(), "Part#");
		log.info("part number label has been verified" + " with [Actual] and [Expected] value as " + "["
				+ partNumberLabel.getText().replace(":", "").trim() + "] and " + "[" + "Part#" + "]");
		return this;
	}

	@Step("Verify display of MPN Label")
	public ProductsDetailsPageObjects verifyDisplayOfMPN() {
		log = Logger.getLogger("verifyDisplayOfMPN");
		Assert.assertEquals(mpnLabel.getText().replace(":", "").trim(), "MPN");
		log.info("MPN label of the page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ mpnLabel.getText().replace(":", "").trim() + "] and " + "[" + "MPN" + "]");
		return this;
	}

	public ProductsDetailsPageObjects verifyDisplayOfBrand() {
		logger = Logger.getLogger("verifyDisplayOfBrand");
		Assert.assertTrue(brandNameLabel.isDisplayed(), "Brand label is not displayed");
		logger.info("Brand label is displayed");
		return this;

	}

	public ProductsDetailsPageObjects verifyDisplayOfManufacturer() {

		logger = Logger.getLogger("verifyDisplayOfManufacturer");
		Assert.assertTrue(manufacturerLabel.isDisplayed(), "Manufacturer label is not displayed");
		logger.info("Manufacturer label is displayed");
		return this;

	}

	@Step("Verify display of Minimum Order Quantity Label")
	public ProductsDetailsPageObjects verifyDisplayOfMinimumOrderQuantity() {

		log = Logger.getLogger("verifyDisplayOfMinimumOrderQuantity");
		Assert.assertEquals(minimumOrderQuantityLabel.getText().replace(":", "").trim(), "Min. Order Qty");

		log.info("Minimum Order Quantity Label has been verified" + " with [Actual] and [Expected] value as " + "["
				+ minimumOrderQuantityLabel.getText().replace(":", "").trim() + "] and " + "[" + "Min. Order Qty"
				+ "]");
		return this;
	}

	@Step("Verify display of quantity interval")
	public ProductsDetailsPageObjects verifyDisplayOfQuantityInterval() {

		logger = Logger.getLogger("verifyDisplayOfQuantityInterval");
		Assert.assertEquals(quantityIntervalLabel.getText().replace(":", "").trim(), "Qty. Interval");

		logger.info("display of quantity interval has been verified" + " with [Actual] and [Expected] value as " + "["
				+ quantityIntervalLabel.getText().replace(":", "").trim() + "] and " + "[" + "Qty. Interval" + "]");
		return this;
	}

	public ProductsDetailsPageObjects verifyDisplayOfImage() {
		logger = Logger.getLogger("verifyDisplayOfImage");
		waiting.waitForVisibilityOfElement(imageLocator, 10);
		Assert.assertTrue(imageLocator.isDisplayed(), "Image is not displayed");
		logger.info("Image is displayed");
		return this;

	}

	public ProductsDetailsPageObjects verifydisplayOfThumblineImage() {

		logger = Logger.getLogger("verifydisplayOfThumblineImage");

		waiting.waitForVisibilityOfElement(thumbnailImageLocator, 10);

		Assert.assertTrue(thumbnailImageLocator.isDisplayed(), "Thumbnail Image is not displayed");

		logger.info("Thumbnail Image is displayed");
		return this;

	}

	public ProductsDetailsPageObjects verifyDisplayOfDescriptionTab() {

		logger = Logger.getLogger("verifyDisplayOfDescriptionTab");
		waiting.waitForVisibilityOfElement(descriptionTabLocator, 10);

		Assert.assertTrue(descriptionTabLocator.isDisplayed(), "Description Tab is not displayed");

		logger.info("Description Tab is displayed");

		return this;

	}

	public ProductsDetailsPageObjects verifyDisplayOfSpecificationTab() {

		logger = Logger.getLogger("verifyDisplayOfSpecificationTab");

		waiting.waitForVisibilityOfElement(specificationsTabLocator, 10);

		Assert.assertTrue(specificationsTabLocator.isDisplayed(), "Specification Tab is not displayed");

		logger.info("Specification Tab is displayed");

		return this;

	}

	public ProductsDetailsPageObjects verifyDisplayOfRecentlyViewedItems() throws InterruptedException {

		Thread.sleep(1400);

		waiting.waitForVisibilityOfElement(recentlyViewedItemsLocator, 10);
		Assert.assertTrue(recentlyViewedItemsLocator.isDisplayed());

		return this;

	}

	public ProductsDetailsPageObjects verifyDisplayOfQtyTextBox() {
		logger = Logger.getLogger("verifyDisplayOfQtyTextBox");
		Assert.assertTrue(quantityTextBoxLocator.isDisplayed(), "Qty text Box is not displayed");
		logger.info("Qty text Box is displayed");
		return this;

	}

	@Step("Verify default Value Of Qty :{0}")
	public ProductsDetailsPageObjects verifyDefaultQtyValue(String defaultQtyValue) {
		log = Logger.getLogger("verifyDefaultQtyValue");
		waiting.waitForVisibilityOfElement(quantityTextBoxLocator, 10);
		Assert.assertEquals(quantityTextBoxLocator.getAttribute("value").trim(), defaultQtyValue.trim(),
				"Default Qty Value is not :" + defaultQtyValue.trim() + ". It is :"
						+ quantityTextBoxLocator.getAttribute("value").trim());
		log.info("Default Qty Value has been verified" + " with [Actual] and [Expected] value as " + "["
				+ quantityTextBoxLocator.getAttribute("value").trim() + "] and " + "[" + defaultQtyValue.trim() + "]");
		return this;

	}

	@Step("Verify display of your price label")
	public ProductsDetailsPageObjects verifyDisplayOfYourPrice(String loggedInFlag) {

		logger = Logger.getLogger("verifyDisplayOfYourPrice");

		if (loggedInFlag.equals("Logged In")) {
			Assert.assertEquals(priceLabel.getText().replace(":", "").trim(), "Your Price");
		} else {
			Assert.assertEquals(priceLabel.getText().replace(":", "").trim(), "Price");
		}

		return this;
	}

	@Step("Verify PDP product title")
	public ProductsDetailsPageObjects verifyPDPPageTitle(String productName) throws Exception {
		String PDPTitle = getDriver().getTitle();
		waiting.waitForVisibilityOfElement(itemTitleLocator, 10);
		String itemName = itemTitleLocator.getText().trim();
		Assert.assertEquals(PDPTitle.toLowerCase(), itemName.toLowerCase() + " | " + productName.toLowerCase(),
				"Title is wrong");
		log.info("Title of the product detail page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ PDPTitle + "] and " + "[" + itemName + " | " + productName + "]");
		return this;
	}

	@Step("Verify breadcrump")
	public ProductsDetailsPageObjects verifyBreadCrumb() {

		logger = Logger.getLogger("verifyBreadCrumb");

		String itemName = itemTitleLocator.getText().trim();

		String lastBreadCrump = breadCrumbs.get(breadCrumbs.size() - 1).getText().trim();
		Assert.assertEquals(itemName, lastBreadCrump.replace("/", "").trim(),
				"item name and the last breadcrump is not the same. Item name is : " + itemName
						+ " and the last breadcrump is : " + lastBreadCrump);

		logger.info(
				"Breadcrumb of the product detail page has been verified" + " with [Actual] and [Expected] value as "
						+ "[" + itemName + "] and " + "[" + lastBreadCrump.replace("/", "").trim() + "]");
		return this;
	}

	@Step("Click On BreadCrumb link : {0}")
	public ProductsDetailsPageObjects clickOnBreadCrumb(String categoryName) throws InterruptedException {
		log = Logger.getLogger("clickOnBreadCrumb");
		WebElement categoryName1 = getDriver()
				.findElement(By.xpath("//ul[@class='breadcrumb']//*[contains(text(),'" + categoryName.trim() + "')]"));
		waiting.waitForVisibilityOfElement(categoryName1, 10);
		categoryName1.click();
		Thread.sleep(1200);
		log.info("Clicked On BreadCrumb link : " + categoryName);
		return this;

	}

	@Step("get Category Name From Breadcrumbs")
	public String getCategoryNameFromBreadCrumb(int specificCategoryLink) {
		log = Logger.getLogger("getCategoryNameFromBreadCrumb");
		waiting.waitForVisibilityOfElements(breadCrumbs, 10);
		String lastBreadCrump = breadCrumbs.get(breadCrumbs.size() - specificCategoryLink).getText().trim();
		return lastBreadCrump;
	}

	@Step("Click on last but on Breadcrumbs")
	public ProductsListPageObjects clickOnLastButOneBreadCrumb() {
		waiting.waitForVisibilityOfElements(breadCrumbsLink, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				breadCrumbsLink.get(breadCrumbsLink.size() - 1));
		log.info("Clicked on :" + breadCrumbs.get(breadCrumbs.size() - 2).getText().trim());
		return productListPage();
	}

	@Step("Verify Detail Page Breadcrumbs: {0}")
	public ProductsDetailsPageObjects verifyDetailPageBreadcrumbs(String breadCrumb) {
		log = Logger.getLogger("verifyDetailPageBreadcrumbs");
		waiting.waitForVisibilityOfElements(breadCrumbs, 10);
		String[] expectedBreadCrumb = breadCrumb.split(",");
		List<String> rightClickOptions = breadCrumbs.stream().map(s -> s.getText().trim()).collect(Collectors.toList());
		Assert.assertEquals(rightClickOptions, Arrays.asList(expectedBreadCrumb), "Breadcrumbs is not correct.");
		log.info("Breadcrumbs of Product Detail Page has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + rightClickOptions + "] and " + "[" + Arrays.asList(expectedBreadCrumb) + "]");

		return this;

	}

	@Step("Verify display of print link")
	public ProductsDetailsPageObjects verifyDisplayOfPrintLink() {
		log = Logger.getLogger("verifyDisplayOfPrintLink");
		Assert.assertTrue(printLink.isDisplayed(), "Print link button is not displayed");
		log.info("Print link button is displayed");
		return this;
	}

	public ProductsDetailsPageObjects verifyAddToCartButton() {
		log = Logger.getLogger("verifyAddToCartButton");
		Assert.assertTrue(addToCartButton.isDisplayed(), "Add to cart button is not displayed");
		log.info("Add to cart button is displayed");
		return this;
	}

	@Step("Verify Add To Cart Button is disable for no price item.")
	public ProductsDetailsPageObjects verifyAddToCartButtonDisableForNoPriceItem() {
		log = Logger.getLogger("verifyAddToCartButtonDisableForNoPriceItem");
		waiting.waitForVisibilityOfElement(addToCartButton, 10);
		Assert.assertTrue(addToCartButton.getAttribute("class").contains("disable"),
				"Add To Cart button is not disable for non-price item.");
		log.info("Add To Cart Button is disable for non-price item.");
		return this;
	}

	@Step("Verify Call For Price :{0}")
	public ProductsDetailsPageObjects verifyCallForPrice(String priceOfItem) {
		log = Logger.getLogger("verifyCallForPrice");
		Assert.assertTrue(itemsPriceWithPriceLabelLocator.getText().contains(priceOfItem));
		return this;
	}

	/*
	 * @Step("Verify product details page") public ProductsDetailsPageObjects
	 * verifyProductDetailPage() throws InterruptedException { log =
	 * Logger.getLogger("verifyProductDetailPage");
	 * //waiting.waitForVisibilityOfElement(addToCartButton, 5); Thread.sleep(1200);
	 * SoftAssert softAssert = new SoftAssert();
	 * //softAssert.assertTrue(addToCartButton.isDisplayed(),
	 * "Add to cart button is not displayed");
	 * //log.info("Add to cart button is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(addToMyProductGroupButton),
	 * "add to my product group button is not displayed.");
	 * log.info("add to my product group button is displayed.");
	 * softAssert.assertTrue(utils.isElementDisplayed(sendLink),
	 * "Send This Page is not displayed"); log.info("Send This Page is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(printLink),
	 * "Print link button is not displayed");
	 * log.info("Print link button is displayed");
	 * softAssert.assertEquals(minimumOrderQuantityLabel.getText().replace(":",
	 * "").trim(), "Min. Order Qty", "Minimum Order Quantity label is not :" +
	 * "Min. Order Qty" + ". It is :" +
	 * minimumOrderQuantityLabel.getText().replace(":", "").trim());
	 * logger.info("Minimum Order Quantity label has been verified" +
	 * " with [Actual] and [Expected] value as " + "[" +
	 * minimumOrderQuantityLabel.getText().replace(":", "").trim() + "] and " + "["
	 * + "Min. Order Qty" + "]");
	 * softAssert.assertEquals(mpnLabel.getText().replace(":", "").trim(), "MPN",
	 * "Manufacturer Part Number label is not : " + "MPN" + ". It is :" +
	 * mpnLabel.getText().replace(":", "").trim());
	 * logger.info("MPN Label has been verified" +
	 * " with [Actual] and [Expected] value as " + "[" +
	 * mpnLabel.getText().replace(":", "").trim() + "] and " + "[" + "MPN" + "]");
	 * softAssert.assertTrue(utils.isElementDisplayed(productNameLocator),
	 * "Name of the product is not displayed");
	 * log.info("Name of the product is displayed");
	 * softAssert.assertEquals(partNumberLabel.getText().replace(":", "").trim(),
	 * "PN", "Part Number label is not : " + "PN" + ". It is :" +
	 * partNumberLabel.getText().replace(":", "").trim());
	 * softAssert.assertEquals(upcLabel.getText().replace(":", "").trim(), "UPC",
	 * "UPC label is not : " + "UPC" + ". It is :" + upcLabel.getText().replace(":",
	 * "").trim()); softAssert.assertTrue(utils.isElementDisplayed(brandNameLabel),
	 * "Brand label is not displayed"); log.info("Brand label is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(manufacturerLabel),
	 * "Manufacturer label is not displayed");
	 * log.info("Manufacturer label is displayed");
	 * softAssert.assertEquals(quantityIntervalLabel.getText().replace(":",
	 * "").trim(), "Qty. Interval", "Quantity Interval label is not : " +
	 * "Qty. Interval" + ". It is :" + quantityIntervalLabel.getText().replace(":",
	 * "").trim());
	 * softAssert.assertTrue(utils.isElementDisplayed(quantityTextBoxLocator),
	 * "Qty text Box is not displayed"); log.info("Qty text Box is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(addRemoveCPNLocator),
	 * "Add/Remove CPN Link is not displayed");
	 * log.info("Add/Remove CPN Link is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(imageLocator),
	 * "Image is not displayed"); log.info("Image is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(thumbnailImageLocator),
	 * "Thumbnail Image is not displayed");
	 * log.info("Thumbnail Image is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(descriptionTabLocator),
	 * "Description Tab is not displayed");
	 * log.info("Description Tab is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(specificationsTabLocator),
	 * "Specification Tab is not displayed");
	 * log.info("Specification Tab is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(featureTabLocator),
	 * "Feature Tab is not displayed"); log.info("Feature Tab is displayed");
	 * softAssert.assertTrue(utils.isElementDisplayed(documentsTabLocator),
	 * "Document Tab is not displayed"); log.info("Document Tab is displayed");
	 * softAssert.assertAll(); return this; }
	 */

	@Step("Verify product details page")
	public ProductsDetailsPageObjects verifyProductDetailPage() throws InterruptedException {
		log = Logger.getLogger("verifyProductDetailPage");
		// waiting.waitForVisibilityOfElement(addToCartButtonLocatorBeforeLogin, 5);
		Thread.sleep(1200);
		SoftAssert softAssert = new SoftAssert();
		/*
		 * softAssert.assertTrue(addToCartButtonLocatorBeforeLogin.isDisplayed(),
		 * "Add to cart button is not displayed");
		 * log.info("Add to cart button is displayed");
		 */
		softAssert.assertTrue(utils.isElementDisplayed(addToMyProductGroupButton),
				"add to my product group button is not displayed.");
		log.info("add to my product group button is displayed.");
		softAssert.assertTrue(utils.isElementDisplayed(sendLink), "Send This Page is not displayed");
		log.info("Send This Page is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(printLink), "Print link button is not displayed");
		log.info("Print link button is displayed");
		softAssert.assertEquals(minimumOrderQuantityLabel.getText().replace(":", "").trim(), "Min. Order Qty",
				"Minimum Order Quantity label is not :" + "Min. Order Qty" + ". It is :"
						+ minimumOrderQuantityLabel.getText().replace(":", "").trim());
		logger.info("Minimum Order Quantity label has been verified" + " with [Actual] and [Expected] value as " + "["
				+ minimumOrderQuantityLabel.getText().replace(":", "").trim() + "] and " + "[" + "Min. Order Qty"
				+ "]");
		softAssert.assertEquals(mpnLabel.getText().replace(":", "").trim(), "MPN",
				"Manufacturer Part Number label is not : " + "MPN" + ". It is :"
						+ mpnLabel.getText().replace(":", "").trim());
		logger.info("MPN Label has been verified" + " with [Actual] and [Expected] value as " + "["
				+ mpnLabel.getText().replace(":", "").trim() + "] and " + "[" + "MPN" + "]");
		softAssert.assertTrue(utils.isElementDisplayed(productNameLocator), "Name of the product is not displayed");
		log.info("Name of the product is displayed");
		softAssert.assertEquals(partNumberLabel.getText().replace(":", "").trim(), "PN",
				"Part Number label is not : " + "PN" + ". It is :" + partNumberLabel.getText().replace(":", "").trim());
		softAssert.assertEquals(upcLabel.getText().replace(":", "").trim(), "UPC",
				"UPC label is not : " + "UPC" + ". It is :" + upcLabel.getText().replace(":", "").trim());
		softAssert.assertTrue(utils.isElementDisplayed(brandNameLabel), "Brand label is not displayed");
		log.info("Brand label is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(manufacturerLabel), "Manufacturer label is not displayed");
		log.info("Manufacturer label is displayed");
		softAssert.assertEquals(quantityIntervalLabel.getText().replace(":", "").trim(), "Qty. Interval",
				"Quantity Interval label is not : " + "Qty. Interval" + ". It is :"
						+ quantityIntervalLabel.getText().replace(":", "").trim());
		softAssert.assertTrue(utils.isElementDisplayed(quantityTextBoxLocator), "Qty text Box is not displayed");
		log.info("Qty text Box is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(addRemoveCPNLocator), "Add/Remove CPN Link is not displayed");
		log.info("Add/Remove CPN Link is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(imageLocator), "Image is not displayed");
		log.info("Image is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(thumbnailImageLocator), "Thumbnail Image is not displayed");
		log.info("Thumbnail Image is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(descriptionTabLocator), "Description Tab is not displayed");
		log.info("Description Tab is displayed");
		softAssert.assertTrue(utils.isElementDisplayed(specificationsTabLocator), "Specification Tab is not displayed");
		log.info("Specification Tab is displayed");
		/*
		 * softAssert.assertTrue(utils.isElementDisplayed(featureTabLocator),
		 * "Feature Tab is not displayed"); log.info("Feature Tab is displayed");
		 */
		/*
		 * softAssert.assertTrue(utils.isElementDisplayed(documentsTabLocator),
		 * "Document Tab is not displayed"); log.info("Document Tab is displayed");
		 */
		softAssert.assertAll();
		return this;
	}

	@Step("Verify display of Add my product group button")
	public ProductsDetailsPageObjects verifyDisplayOfAddMyProductGroupButton() {
		logger = Logger.getLogger("verifyDisplayOfAddMyProductGroupButton");
		Assert.assertTrue(addToMyProductGroupButton.isDisplayed(), "add to my product group button is not displayed.");
		logger.info("My Product Group button is displayed.");
		return this;
	}

	@Step("Verify display od Add To Cart Button")
	public ProductsDetailsPageObjects verifyDisplayOfAddToCartButton() {
		waiting.waitForVisibilityOfElement(addToCartButton, 10);

		Assert.assertTrue(addToCartButton.isDisplayed(), "add to Cart button is not displayed.");
		return this;
	}

	public ProductsDetailsPageObjects verifyEnabledDisableCartButtonBasedOnPriceOfItem() {

		logger = Logger.getLogger("verifyEnabledDisableCartButtonBasedOnPriceOfItem");

		if (yourPrice.getText().contains("Call"))

		{
			Assert.assertTrue(addToCartButton.getAttribute("class").contains("disable"),
					"Add To Cart Button is enabled");

			logger.info("Add To Cart Button is disabled");

		} else {

			Assert.assertFalse(addToCartButton.getAttribute("class").contains("disable"),
					"Add To cart Button is disabled");

			logger.info("Add To Cart Button is enabled");

		}

		return this;
	}

	@Step("Verify Differentiator set for the product category in detail page")
	public ProductsDetailsPageObjects verifyDifferentiatorSetForProduct() {
		waiting.waitForVisibilityOfElement(differentiatorLocator, 10);
		Assert.assertTrue(differentiatorLocator.isDisplayed());
		return this;

	}

	@Step("Verify Differentiator set for the product category in detail page for product version two,three and four")
	public ProductsDetailsPageObjects verifyDifferentiatorSetForProductVersionTwoThreeAndFour() {

		waiting.waitForVisibilityOfElement(differentiatorLocatorForProductDetailVersion2, 10);
		Assert.assertTrue(differentiatorLocatorForProductDetailVersion2.isDisplayed());
		return this;

	}

	@Step("Verify Differentiator set for the product category in detail page for version five")
	public ProductsDetailsPageObjects verifyDifferentiatorSetForProductVersion5() {

		waiting.waitForVisibilityOfElement(differentiatorLocatorForProductDetailVersion5, 10);
		Assert.assertTrue(differentiatorLocatorForProductDetailVersion5.isDisplayed());
		return this;
	}

	@Step(" Verify Of Displays items which are assigned to that particular product with below details:{0} In Product Detail Page")
	public ProductsDetailsPageObjects verifyOfDisplayOfItemsFieldsInProductDetailPage(
			String itemInformationTabUnderViewMore) {

		productListPage()
				.verifyDisplayItemsAndFieldsAfterClickingOnViewAllChoiceButton(itemInformationTabUnderViewMore);
		return this;

	}

	public ProductsDetailsPageObjects verifyOfDisplayOfItemsFieldsInProductDetailPageForVersion3(
			String productModeItemsFields) {

		String[] expectedProductModeItemsFields = productModeItemsFields.split(",");
		for (int i = 1; i < productListPage().itemFieldsLocators.size(); i++) {

			if (i < 6) {

				Assert.assertEquals(productListPage().itemFieldsLocators.get(i).getText().trim(),
						expectedProductModeItemsFields[i].trim());
			}

			else {

				Assert.assertEquals(productListPage().itemFieldsLocators.get(i).getAttribute("title").trim(),
						expectedProductModeItemsFields[i].trim());
			}
		}

		return this;
	}

	public ProductsDetailsPageObjects verifyOfDisplayOfItemsFieldsInProductDetailPageForVersion4(
			String productModeItemsFieldsInDetailPage) {

		productListPage().verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion4Page(
				productModeItemsFieldsInDetailPage);
		return this;

	}

	@Step("Select an item Add to cart/product group checkbox {0} In Product Detail Page")
	public ProductsDetailsPageObjects clickOnSpecificAddToCartAndAddToProductGroupCheckboxInDetailPage(
			int specificCheckbox) throws InterruptedException {

		waiting.waitForVisibilityOfElements(productListPage().selectItemCheckboxLocatorsInProductMode, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().selectItemCheckboxLocatorsInProductMode.get(specificCheckbox - 1));

		Thread.sleep(5000);

		return this;

	}

	@Step("Click on Bulk Option DropDown in Product Detail Page")
	public ProductsDetailsPageObjects clickOnBulkOptionsInProductDetailPage() throws InterruptedException {

		waiting.waitForVisibilityOfElement(productListPage().bulkOptionLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", productListPage().bulkOptionLocator);
		Thread.sleep(1200);
		return this;

	}

	@Step("Click on Add Item To Cart Link Under Bulk Option  in Product Detail Page")
	public ProductsDetailsPageObjects clickOnAddItemToCartFromBulkOptionInProductDetailsPage()
			throws InterruptedException {
		waiting.waitForVisibilityOfElement(productListPage().addItemToCartLocator, 10);
		logger = Logger.getLogger("clickOnAddItemToCartFromBulkOptionInProductDetailsPage");

		productListPage().addItemToCartLocator.click();
		Thread.sleep(2000);

		logger.info("Clicked on Add Item To Cart Link Under Bulk Option  in Product Details Page");

		return this;

	}

	@Step("Click on Add Item To Cart Link Under Bulk Option  in Product Detail Page")
	public ProductsDetailsPageObjects clickOnAddItemToGroupFromBulkOptionInProductDetailPage()
			throws InterruptedException {
		waiting.waitForVisibilityOfElement(bulkOptionAddToProductGroupLocatorInProductDetailPage, 10);

		logger = Logger.getLogger("clickOnAddItemToCartFromBulkOptionInProductDetailsPage");

		bulkOptionAddToProductGroupLocatorInProductDetailPage.click();
		Thread.sleep(2000);

		logger.info("Clicked on Add Item To Cart Link Under Bulk Option  in Product Details Page");

		return this;

	}

	@Step("Click on'Item info' link for Product Mode Items {0} in Product Detail Page")
	public ProductsDetailsPageObjects clickOnSpecificItemInformationLinkInProductDetailPage(
			int specificItemInformationLink) throws InterruptedException {

		waiting.waitForVisibilityOfElements(productListPage().itemInformationLinkUnderViewMoreChoicesLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().itemInformationLinkUnderViewMoreChoicesLocator.get(specificItemInformationLink - 1));

		Thread.sleep(1800);
		return this;

	}

	@Step("Verification of 'Item information' link {0} of product mode items in Product Detail Page")
	public ProductsDetailsPageObjects verifyItemInformationTabInProductDetailPage(
			String itemInformationTabInProductDetailPage) {

		String[] expecteditemInformationTabInProductDetailPage = itemInformationTabInProductDetailPage.split(",");

		waiting.waitForVisibilityOfElements(productListPage().itemInformationTabLocatorUnderViewMoreChoices, 10);

		for (int i = 0; i < productListPage().itemInformationTabLocatorUnderViewMoreChoices.size(); i++) {

			Assert.assertEquals(productListPage().itemInformationTabLocatorUnderViewMoreChoices.get(i)
					.getText()
					.trim()
					.toLowerCase(), expecteditemInformationTabInProductDetailPage[i].trim().toLowerCase());
		}
		return this;

	}

	public String getCompareItemsCountInProductDetailPage() {

		waiting.waitForVisibilityOfElement(productListPage().compareItemsCount, 10);
		String noOfCount = productListPage().compareItemsCount.getText().trim();
		return noOfCount;

	}

	@Step("Click on {0} st/nd/rd/th compare checkboxes under product mode items in Product Detail Page")
	public ProductsDetailsPageObjects clickOnSpecificComapreCheckInProductDetailPage(
			int specificCheckboxUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(productListPage().addToCompareCheckboxesUnderViewMoreChoicesLocator, 15);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().addToCompareCheckboxesUnderViewMoreChoicesLocator
						.get(specificCheckboxUnderProductMode - 1));

		Thread.sleep(1800);
		return this;

	}

	public ProductsDetailsPageObjects clickOnSpecificComapreCheckInProductDetailPageForVersion3(
			int specificCheckboxUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(
				productListPage().addToCompareCheckboxesUnderViewMoreChoicesLocatorInProductVersion3, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().addToCompareCheckboxesUnderViewMoreChoicesLocatorInProductVersion3
						.get(specificCheckboxUnderProductMode - 1));

		Thread.sleep(1400);

		return this;
	}

	@Step("Verify Compare item(s) count will be incresed {0} in Product Detail Page")
	public ProductsDetailsPageObjects verifyWhetherCompareItemIncreasedInProductDetailPage(
			String compareItemsCountInProductDetailPage) {

		int newCount = Integer.parseInt(productListPage().compareItemsCount.getText().trim());

		Assert.assertTrue(Integer.parseInt(compareItemsCountInProductDetailPage) < newCount,
				"Number of item is not increased");
		return this;

	}

	@Step(" Click on required checkbox of Differentiator :{0}")
	public ProductsDetailsPageObjects clickOnSpecificCheckBoxOfDifferentiator(String differentiatorValue)
			throws InterruptedException {

		/*
		 * public void scrollTillWebElement(WebElement webElement) {
		 * ((JavascriptExecutor)
		 * getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
		 * }
		 */

		/*
		 * WebElement differentiatorCheckbox = getDriver()
		 * .findElement(By.xpath("//em[contains(text())='" + differentiatorValue.trim()
		 * + "']/..//label"));
		 */

		WebElement differentiatorCheckbox = getDriver()
				.findElement(By.xpath("//span[contains(text(),'" + differentiatorValue.trim() + "')]/.."));

		// utils.scrollTillWebElement(differentiatorCheckbox);
		// differentiatorCheckbox.click();

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", differentiatorCheckbox);

		Thread.sleep(2200);

		return this;

	}

	public String getAttributeName() {

		String attributeValue = attributeValueLocator.getText().replace(" :", "").trim();
		System.out.println("getAttributeName :---> " + attributeValue);
		return attributeValue;
	}

	public String getAttributeNameOfProductDetailVersion2() {

		String attributeValue = differentiatorLocatorForProductDetailVersion2.getText().replace(" :", "").trim();

		return attributeValue;
	}

	@Step("Click on Differentiator Drop Down In Product Version Two")
	public ProductsDetailsPageObjects clickOnDifferentiatorOfProductDetailVersion2Page() throws InterruptedException {

		waiting.waitForVisibilityOfElement(differentiatorLocatorForProductDetailVersion2, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				differentiatorLocatorForProductDetailVersion2);
		Thread.sleep(1200);

		return this;
	}

	@Step("Verify Display Of items After selecting Differentiator")
	public ProductsDetailsPageObjects verifyDisplayofRespectiveItemsAfterClickingOnDifferentiator(
			String attributeValue) {

		waiting.waitForVisibilityOfElements(attributeLocators, 10);

		Assert.assertEquals(attributeLocators.get(0).getText().trim(), attributeValue.trim(),
				"Attribute Values are not same");
		return this;

	}

	public String getNumberOfProductChoiceItems() {

		waiting.waitForVisibilityOfElement(productChoiceItemLocator, 10);
		String numberOfItemsInProductChoice = productChoiceItemLocator.getText().trim();
		return numberOfItemsInProductChoice;

	}

	public String getNumberOfProductChoiceItemsInProductVersion3() {

		waiting.waitForVisibilityOfElement(productChoiceItemLocatorForVersion3, 10);
		String numberOfItemsInProductChoice = productChoiceItemLocatorForVersion3.getText().trim();
		return numberOfItemsInProductChoice;

	}

	@Step("Click on 'Clear all' link Under Differentiator")
	public ProductsDetailsPageObjects clickOnClearAllLinkForFilteredAttributeItems() throws InterruptedException {
		waiting.waitForVisibilityOfElement(clearAllLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", clearAllLocator);

		Thread.sleep(1200);
		return this;

	}

	@Step("Selected item will be removed from the list and dispalys remaining items which are assigned to that particular product.")
	public ProductsDetailsPageObjects verifyClearLinkFunctionality(String numberOfItems) {

		waiting.waitForVisibilityOfElement(productChoiceItemLocator, 10);
		Assert.assertEquals(productChoiceItemLocator.getText().trim(), numberOfItems.trim(),
				"Number of Items are same after cliking in clear link");

		return this;

	}

	public ProductsDetailsPageObjects verifyClearLinkFunctionalityInProductVersion3(String numberOfItems) {

		waiting.waitForVisibilityOfElement(productChoiceItemLocatorForVersion3, 10);
		Assert.assertEquals(productChoiceItemLocatorForVersion3.getText().trim(), numberOfItems.trim(),
				"Number of Items are same after cliking in clear link");

		return this;

	}

	@Step("Click on 'Cancel' option Of Compare Link In Product Detail Page")
	public ProductsDetailsPageObjects clickOnCancelComapreLink() throws InterruptedException {

		waiting.waitForVisibilityOfElement(cancleCompareLinkLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", cancleCompareLinkLocator);

		Thread.sleep(1200);
		return this;

	}

	public ProductsDetailsPageObjects clickOnAddToCartButtonAtItemLevelInProductDetailPage(
			int specificAddToCArtButtonUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(productListPage().addToCartButtonAtItemLevelUnderViewMoreChoicesLocator,
				10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().addToCartButtonAtItemLevelUnderViewMoreChoicesLocator
						.get(specificAddToCArtButtonUnderProductMode - 1));

		Thread.sleep(2000);

		return this;
	}

	public ProductsDetailsPageObjects clickOnAddToProductGroupButtonAtItemLevelInProductDetailPage(
			int specificAddToProductGrouptButtonUnderProductMode) throws InterruptedException {

		waiting.waitForVisibilityOfElements(
				productListPage().addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocator
						.get(specificAddToProductGrouptButtonUnderProductMode - 1));
		Thread.sleep(1400);

		return this;

	}

	public ProductsDetailsPageObjects clickOnAddToProductButtonAtItemLevelInProductDetailPageBeforeLogin(
			int specificAddToProductGrouptButtonUnderProductMode) throws InterruptedException {
		waiting.waitForVisibilityOfElements(
				productListPage().addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocatorBeforeLogin, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				productListPage().addToProductGroupButtonAtItemLevelUnderViewMoreChoicesLocatorBeforeLogin
						.get(specificAddToProductGrouptButtonUnderProductMode - 1));
		Thread.sleep(1400);
		return this;
	}

	@Step("Verify display of already created my product group")
	public ProductsDetailsPageObjects verifyDisplayOfCreatedProductGroupInDetailPage() {

		waiting.waitForVisibilityOfElements(productListPage().createdProductGroupLocator, 10);
		for (int i = 0; i < productListPage().createdProductGroupLocator.size(); i++) {
			Assert.assertTrue(productListPage().createdProductGroupLocator.get(i).isDisplayed(),
					"Created Product List is not displayed");
		}
		return this;
	}

	@Step("Verify display of Text Box  to Enter new product group name")
	public ProductsDetailsPageObjects verifyTextBoxToEnterNewProductGroupNameInDetailPage() {
		waiting.waitForVisibilityOfElement(productListPage().textBoxLocatorToEnterNewgroupName, 10);
		Assert.assertTrue(productListPage().textBoxLocatorToEnterNewgroupName.isDisplayed(),
				"Text Box is not displayed to enter new product group name");
		return this;
	}

	@Step("Select required checkbox of Differentiator :{0} in product version five")
	public ProductsDetailsPageObjects selectSpecificDifferentiatorValueInProductDetailPageForVersion5(
			String differentiatorValue) throws InterruptedException {
		waiting.waitForVisibilityOfElement(
				By.xpath("//ul[@role='listbox']/li/a/span[contains(text(),'" + differentiatorValue + "')]"), 10);
		WebElement differentiatorValue1 = getDriver().findElement(
				By.xpath("//ul[@role='listbox']/li/a/span[contains(text(),'" + differentiatorValue.trim() + "')]"));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", differentiatorValue1);
		Thread.sleep(1400);
		return this;
	}

	@Step("Click On Differentiator for product version five")
	public ProductsDetailsPageObjects clickOnDifferentiator() throws InterruptedException {
		Thread.sleep(1200);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", DifferentiatorLocator);
		Thread.sleep(1200);
		return this;
	}

	@Step("Verify Differentiator Dropdown functionality as :{0} in product mode version five")
	public ProductsDetailsPageObjects verifyDifferentiatorDropdownfunctionality(String differentiatorValue) {
		waiting.waitForVisibilityOfElement(differentiatorValueLocatorUnderSpecificationTab, 10);
		Assert.assertEquals(differentiatorValueLocatorUnderSpecificationTab.getText().trim(),
				differentiatorValue.trim());
		return this;
	}

	@Step("Verify display of respective item information after selecting 'Differentiator' drop down value {0}.")
	public ProductsDetailsPageObjects verificationOfDifferentiatorDropDown(String differentiatorValue) {
		waiting.waitForVisibilityOfElement(differentiatorValueLocatorAfterSelecting, 10);
		Assert.assertTrue(differentiatorValueLocatorAfterSelecting.getText().contains(differentiatorValue),
				"testing fail");
		return this;
	}

	@Step("Verify Item Price After Login In Detail Page")
	public ProductsDetailsPageObjects verifyItemPriceAfterLogin() throws Throwable {
		log = Logger.getLogger("verifyItemPriceAfterLogin");
		waiting.waitForVisibilityOfElement(itemsPriceLocator, 10);
		String itemPrice;
		int i = 0;
		do {
			Thread.sleep(2000);
			itemPrice = itemsPriceLocator.getText();
			i++;
			if (i > 30) {
				log.info("Waiting to price load... :" + i);
				break;
			}
			i++;
		} while (!(itemPrice.contains("$")));
		Assert.assertTrue(itemsPriceLocator.getText().contains("$"), "Item Price is not displayed.");
		log.info("Item Price is displayed.");
		return this;
	}

	@Step("Verify price precision in Item Details page: {0}")
	public ProductsDetailsPageObjects verifyPricePrecisionInItemDetailsPage(String pricePrecision) {
		log.info("Precision after decimal :" + itemsPriceLocator.getText().replace("/ EACH", "").trim().split("\\.")[1]);
		Assert.assertEquals(itemsPriceLocator.getText().replace("/ EACH", "").trim().split("\\.")[1].length(),
				Integer.parseInt(pricePrecision), "Price precision in not matched in Product Details page");

		log.info("Verify Price precision in Product Details page");
		return this;
	}

	@Step("Verify  Item price should display next to 'Price' label")
	public ProductsDetailsPageObjects verifyItemPriceNextToPriceLabel() {
		log = Logger.getLogger("verifyItemPriceNextToPriceLabel");
		waiting.waitForVisibilityOfElement(itemsPriceWithPriceLabelLocator, 10);
		Assert.assertTrue(itemsPriceWithPriceLabelLocator.getText().trim().contains("Price"),
				"Item price should not display next to 'Price' label");
		log.info("Item price should display next to 'Price' label");
		return this;
	}

	@Step("Verify item availability in detail page.")
	public ProductsDetailsPageObjects verifyItemsAvailabilityInDetailPage() {
		log = Logger.getLogger("verifyItemsAvailabilityInDetailPage");
		waiting.waitForVisibilityOfElement(itemsAvailabilityLocator, 10);
		Assert.assertTrue(itemsAvailabilityLocator.isDisplayed(), "Item Availability should not displayed.");
		log.info("Item Availability is verified");
		return this;
	}
	
	@Step("Verify Short Description is {0}")
	public ProductsDetailsPageObjects verifyShortDescInProductDetailsPage(String shortDesc) {
		mainOperations.waitTillPageLoads();
		waiting.waitForVisibilityOfElement(shortDescriptionLocator, 10);
		Assert.assertTrue(mainOperations.getText(shortDescriptionLocator).contains(shortDesc), "Description value match");
		log.info("Verified Short Description "+shortDesc);
		return this;
	}

}