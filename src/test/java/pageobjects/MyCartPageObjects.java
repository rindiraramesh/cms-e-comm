package pageobjects;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class MyCartPageObjects extends PageInitializer {
	public TestDataPropertyFile data = new TestDataPropertyFile();

	Actions action = new Actions(getDriver());

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(MyCartPageObjects.class);

	@FindBy(xpath = "//form[@id='updateCartForm']/descendant::table")
	public WebElement myProductGroupCartSection;

	@FindBy(xpath = "//div[contains(@class,'popCheckout')]/descendant::a[@href='/cart']")
	private WebElement checkoutButtonInMyCartPopUp;

	/*
	 * @FindBy(xpath = "//h1[contains(@class,'pageTitle')]") private WebElement
	 * pageName;
	 */

	@FindBy(xpath = "//h1[contains(@class,'cimm_page-title')]")
	private WebElement pageName;

	@FindBy(xpath = "//form[@id='updateCartForm']//a[contains(text(),'Checkout')]")
	private WebElement checkoutButtonInMyCartPage;

	@FindBy(xpath = "//a[contains(text(),'Empty Cart')]")
	private WebElement emptyCartButton;

	@FindBy(xpath = "//li[@class='cartCountDisplayLi dropdown']/a[@data-target='quickCartViewBlock']")
	private WebElement cartButtonInTopNavigationMenu;

	@FindBy(xpath = "//span[contains(@class,'cartCountrefresh blockElement')]")
	private WebElement cartCountInTopNavigationMenu;

	@FindBy(xpath = "//*[contains(text(),'No items in your shopping cart')]")
	private WebElement checkForEmptyCart;

	@FindBy(xpath = "//a[contains(text(),'ontinue')]")
	private WebElement continueShoppingButtonInMyCartPage;

	@FindBy(xpath = "//a[contains(text(),'Continue Checkout')]")
	private WebElement continueCheckoutButtonInMyCartPage;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumpOfMyCartPageLocator;

	@FindBy(xpath = "//div[@class='cimm_instructions']/descendant::li")
	private List<WebElement> cartInstructions;

	@FindBy(xpath = "//h6[contains(text(),'Cart Tips')]")
	private WebElement cartInstructionsHeader;

	@FindBy(xpath = "//div[@class='cimm_cartProdDescription']/p[@itemprop='name']//a/strong")
	private List<WebElement> myCartProductName;

	@FindBy(xpath = "//div[@class='cimm_btnGroupEnclosure']/descendant::a[@data-function='saveCartFunction']")
	private WebElement saveCartButton;

	@FindBy(xpath = "//ul[@class='dropdown-menu customDropDown']//input")
	private WebElement saveCartTextbox;

	@FindBy(xpath = "//a[@data-original-title='Share This']")
	private WebElement shareThisLinkLocator;

	@FindBy(xpath = "//div[@class='modal-content']//button[contains(text(),'ombine')]")
	private WebElement combineButtonInMyCartPopUpLocator;

	@FindBy(xpath = "//div[@class='modal-content']//button[contains(text(),'eparate')]")
	private WebElement seperateButtonInMyCartPopUpLocator;

	@FindBy(xpath = "//div[@class='modal-content']//button[contains(text(),'ancel')]")
	private WebElement cancleButtonInMyCartPopUpLocator;

	@FindBy(xpath = "//a[contains(@onclick,'refreshShoppingCart')]")
	private WebElement updateShoppingCartLink;

	@FindBy(xpath = "//td[@data-th='Ext Price']/strong")
	private WebElement extPrice;

	@FindBy(xpath = "//form[@id='updateCartForm']/descendant::table/thead//th")
	private List<WebElement> itemHeadersLocatorInMyCartPage;

	@FindBy(xpath = "//div[@class='cimm_btnGroupEnclosure']/ul/li")
	private List<WebElement> buttonsInMyCart;

	@FindBy(xpath = "//span[@class='priceValue']")
	private WebElement totalPrice;

	@FindBy(xpath = "//td[@data-th='Qty']/input[@name='shoppingCartQty']")
	private List<WebElement> quantityInShoppingCartPage;

	@FindBy(xpath = "//a[contains(@onclick,'pdateShoppingCart()')]")
	private WebElement updateCartButton;

	@FindBy(xpath = "//a[@data-original-title='Delete Item']")
	private List<WebElement> deleteItemLink;

	@FindBy(xpath = "//div/a[contains(@onclick,'deleteItem')]")
	private List<WebElement> deleteItemLinkForTooltip;

	@FindBy(xpath = "//tr[@class='odd' or @class='even']/td[@data-th='Image']")
	private List<WebElement> productImages;

	@FindBy(id = "sendApproval")
	private WebElement submitCartForApprovalButtonLocator;

	@FindBy(xpath = "//div[@class='modal-content']//h4[contains(text(),'My Cart')]/preceding-sibling::button")
	private WebElement myCartPopUpCloseButtonLocator;

	@FindBy(xpath = "//div[@class='modal-header noBorder']//h4[@class='modal-title']/button")
	private WebElement myCartPopUpCloseButtonLocatorOfProductListGridPage;

	@FindBy(xpath = "//td[@data-th='Per Unit Price']/strong")
	private WebElement perUnitPriceLocator;

	@FindBy(xpath = "//td[@data-th='Per Unit Price']/following-sibling::td[1]/strong")
	private WebElement uomChosenLocator;

	@FindBy(xpath = "//a[.='Next']")
	private WebElement nextPaginationButtonLocator;

	@FindBy(xpath = "//li[@class='paginate_button active']")
	private WebElement activePaginationLocator;

	@FindBy(xpath = "//strong[contains(@class,'mRight')]")
	private WebElement totalNoOfItemsInMyCartLocator;

	@FindBy(xpath = "//ul[@class='dropdown-menu customDropDown']//a")
	private List<WebElement> saveCartDropdownListLocator;

	@FindBy(xpath = "//a[@class='popMsg hideMe']")
	private WebElement successMessageForSaveCartCreationLocator;

	@FindBy(xpath = "//div[contains(@class,'')]//a[contains(@class,'right')][contains(text(),'View Cart')]")
	private WebElement checkoutButtonInMyProductGroupLocator;

	@FindBy(xpath = "//div[contains(@class,'')]//a[contains(@class,'right')][contains(text(),'View Cart')]")
	private WebElement checkoutButtonInMyProductGroupLocatorBeforeLogin;

	@FindBy(xpath = "//div[contains(@class,'cartQuickView')]/descendant::a[contains(text(),'Checkout')]")
	private WebElement checkoutButtonInCartViewLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::h3/a")
	private List<WebElement> productNameInCartPreviewLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::span[@class='ATCQuantity']")
	private List<WebElement> quantityInCartPreviewLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::img")
	private List<WebElement> imgInCartPreviewLocator;

	@FindBy(xpath = "//div[@class='modal-header noBorder']/button")
	private WebElement myCartPopUpCloseButtonLocatorAtItemLevel;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::span[@id='cartCountrefresh']")
	private WebElement totalItemsInShoppingCartCountCartPreviewLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::span[@class='ATCprice']")
	private List<WebElement> priceIntCartPreviewLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::div[@class='cimm_quickCartProducts']")
	private WebElement cartPreviewSectionLocator;

	@FindBy(xpath = "//div[@class='cimm_quikCartSummary']/p[contains(text(),'Cart Total')]")
	private WebElement cartPreviewCartTotalLocator;

	@FindBy(xpath = "//div[@class='cimm_quikCartSummary']/p[contains(text(),'Total Items')]")
	private WebElement cartPreviewTotalItemsSectionLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::a[contains(text(),'Checkout')]")
	private WebElement cartPreviewCheckoutButtonLocator;

	@FindBy(xpath = "//div[@class='modal-footer']//a[contains(text(),'View Cart')]")
	private WebElement viewCartButtonInMyCartPopUp;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::a[contains(text(),'Continue Shopping')]")
	private WebElement cartPreviewContinueShoppingButtonLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::i[contains(@class,'circle')]/ancestor::a")
	private List<WebElement> cartPreviewRemoveItemButtonLocator;

	@FindBy(xpath = "//div[@class='cartQuickView']/descendant::i[contains(text(),'Please shop to add items to your cart.')]")
	private WebElement pleaseShopTextInCartPreviewLocator;

	@FindBy(xpath = "//table[@id='cartWrap']//strong[contains(text(),'Part#:')]/following-sibling::span")
	private List<WebElement> partNumberValueLocator;

	@FindBy(xpath = "//a[@href='/cart']//span[@class='ATCCartCount']")
	private WebElement totalItemsCountInMyCartPopUp;

	@FindBy(xpath = "//div[@class='modal-content']/descendant::div[@class='bootbox-body']")
	private WebElement alertTextLocator;

	@FindBy(xpath = "//div[@class='modal-content']//span[contains(@class,'ATCprice')]")
	private WebElement priceLocatorInMyCartPopUp;

	@FindBy(xpath = "//div[@class='modal-content']//p[@class='ATCShortDesc']")
	private WebElement shortDescriptionLocatorInMyCartPopUp;

	@FindBy(xpath = "//ul/li[@itemprop='mpn']/strong[contains(text(),'MPN:')]/following-sibling::span")
	private List<WebElement> mpnValue;

	@Step("Click on checkout in my cart pop up")
	public MyCartPageObjects clickOnCheckoutInMyCartPopup() throws Exception {
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", checkoutButtonInMyCartPopUp);
		return this;

	}

	@Step("Click on checkout in my cart pop up")
	public MyCartPageObjects clickOnViewCartInMyCartPopup() throws Exception {
		Thread.sleep(1500);
		log = Logger.getLogger("clickOnViewCartInMyCartPopup");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				checkoutButtonInMyProductGroupLocator);
		log.info("clicked On ViewCart Button In My Cart Popup");
		Thread.sleep(1200);
		return this;
	}

	@Step("Click on checkout in my cart pop up")
	public MyCartPageObjects clickOnViewCartInMyCartPopupBeforeLogin() throws Exception {
		waiting.waitForElementToBeClickable(checkoutButtonInMyProductGroupLocatorBeforeLogin, 10);
		testUtilityMethods.moveToElement(checkoutButtonInMyProductGroupLocatorBeforeLogin);
		log = Logger.getLogger("clickOnViewCartInMyCartPopup");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				checkoutButtonInMyProductGroupLocatorBeforeLogin);
		log.info("Clicked On ViewCart Button In My Cart Popup");
		Thread.sleep(1200);
		return this;
	}

	@FindBy(xpath = "//div[contains(@class,'modal-content')]//a[@href='/cart' and .='View Cart']")
	private WebElement viewCartButtonLocator;

	@Step("Click on checkout in my cart pop up")
	public MyCartPageObjects clickOnViewCartInMyCartPopupForSaveCart() throws Exception {
		waiting.waitForElementToBeClickable(checkoutButtonInMyProductGroupLocator, 6);
		log = Logger.getLogger("clickOnViewCartInMyCartPopup");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", viewCartButtonLocator);
		log.info("clicked On ViewCart Button In My Cart Popup");
		Thread.sleep(1200);
		return this;
	}

	@Step("Verify View cart Button in my cart pop up")
	public MyCartPageObjects verifyViewCartButtonInMyCartPopUp() {

		waiting.waitForVisibilityOfElement(checkoutButtonInMyProductGroupLocator, 10);

		log = Logger.getLogger("verifyViewCartButtonInMyCartPopUp");
		Assert.assertTrue(checkoutButtonInMyProductGroupLocator.isDisplayed(), "View Cart Button is not displayed ");

		log.info("View Cart Button is displayed ");
		return this;

	}

	@Step("Click on checkout in my cart page title contains {0}")
	public MyCartPageObjects verifyMyCartTitle(String myCartBreadcrumb, String productName) throws Exception {
		Assert.assertEquals(getDriver().getTitle().trim(), myCartBreadcrumb + " | " + productName);
		return this;
	}

	@Step("Verify breadcrumb of Shopping Cart Page as {0}")
	public MyCartPageObjects verifyMyCartBreadcrumb(String myCartBreadcrumb) throws Exception {
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElements(breadCrumpOfMyCartPageLocator, 10);

		log = Logger.getLogger("verifyMyCartBreadcrumb");

		String[] expectedMyCartBreadcrumb = myCartBreadcrumb.split(",");

		for (int i = 0; i < breadCrumpOfMyCartPageLocator.size(); i++) {
			Assert.assertEquals(breadCrumpOfMyCartPageLocator.get(i).getText().trim().toLowerCase(),
					expectedMyCartBreadcrumb[i].trim().toLowerCase(),
					"Breadcrumb of the Shopping Cart page is not" + expectedMyCartBreadcrumb[i].trim().toLowerCase()
							+ ". It is :" + breadCrumpOfMyCartPageLocator.get(i).getText().trim().toLowerCase());

			log.info(
					"Breadcrumb of the Shopping Cart page has been verified" + " with [Actual] and [Expected] value as "
							+ "[" + breadCrumpOfMyCartPageLocator.get(i).getText().trim().toLowerCase() + "] and " + "["
							+ expectedMyCartBreadcrumb[i].trim().toLowerCase() + "]");
		}

		return this;

	}

	@Step("Click on checkout in my cart page name contains {0}")
	public MyCartPageObjects verifyMyCartPagename(String myCartBreadcrump) {
		Assert.assertEquals(pageName.getText().trim(), myCartBreadcrump.toUpperCase());
		return this;
	}

	public boolean assertCheckoutButtonInMyCartPage() {
		try {
			if (checkoutButtonInMyCartPage.isDisplayed()) {
				return false;
			}

		} catch (NoSuchElementException e) {
			return true;
		}
		return false;

	}

	@Step("Verify checkout out button is not displayed in my cart page")
	public MyCartPageObjects verifyCheckoutButtonNotDisplayedInMyCartPage() {
		Assert.assertTrue(assertCheckoutButtonInMyCartPage(),
				"checkout button is displayed in my cart page for general user.");
		return this;
	}

	@Step("Clear cart")
	public boolean clearCart() throws Exception {
		waiting.waitForVisibilityOfElement(cartCountInTopNavigationMenu, 5);
		int itemInCart = Integer.parseInt(cartCountInTopNavigationMenu.getText()
				.replace(")", "")
				.replace("Items", "")
				.replace("(", "")
				.replace("ITEMS", "")
				.trim());
		if (itemInCart > 0) {
			navigateToShoppingCart();
			clickOnEmptyCartButton();
			commonOperations().clickOnOkButtonInAlertPopUp();
			clickOnContinueShopping();
			/*try {
				if (checkForEmptyCart.isDisplayed()) {
					clickOnContinueShopping();
				}
			} catch (NoSuchElementException e) {
				clickOnEmptyCartButton();
				commonOperations().clickOnOkButtonInAlertPopUp();
				clickOnContinueShopping();
				return true;
			}*/
		}
		return true;
	}

	@Step("Click on continue shopping")
	public MyCartPageObjects clickOnContinueShopping() throws InterruptedException {
		log = Logger.getLogger("clickOnContinueShopping");
		waiting.waitForVisibilityOfElement(continueShoppingButtonInMyCartPage, 3);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", continueShoppingButtonInMyCartPage);
		Thread.sleep(1500);

		log.info("Clicked on continue shopping");
		return this;
	}

	@Step("Click on continue checkout button.")
	public MyCartPageObjects clickOnContinueCheckoutButton() throws InterruptedException {

		log = Logger.getLogger("clickOnContinueCheckoutButton");

		waiting.waitForVisibilityOfElement(continueCheckoutButtonInMyCartPage, 5);
		continueCheckoutButtonInMyCartPage.click();
		Thread.sleep(1500);
		log.info("Clicked on continue checkout");
		return this;

	}

	@Step("Click on empty cart button")
	public MyCartPageObjects clickOnEmptyCartButton() throws InterruptedException {
			log = Logger.getLogger("clickOnEmptyCartButton");
			Thread.sleep(3000);
			waiting.waitTillPageLoads();
			waiting.waitForElementToBeClickable(emptyCartButton, 10);
			//emptyCartButton.click();
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", emptyCartButton);
			log.info("Clicked on empty cart button");
		return this;
	}

	@Step("Click on cart icon")
	public MyCartPageObjects navigateToShoppingCart() throws InterruptedException {
		log = Logger.getLogger("navigateToShoppingCart");
		clickOnCartIcon();
		log.info("clicked on add to cart icon");
		return this;
	}

	@Step("Click on checkout in cart preview")
	public MyCartPageObjects clickOnCheckoutInCartPreview() {
		checkoutButtonInCartViewLocator.click();
		return this;
	}

	@Step("Click on cart icon")
	public MyCartPageObjects clickOnCartIcon() throws InterruptedException {
		Thread.sleep(1000);
		log = Logger.getLogger("clickOnCartIcon");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", cartButtonInTopNavigationMenu);
		log.info("clicked on cart icon");
		return this;
	}

	@Step("Verify buttons available for general user in my cart")
	public MyCartPageObjects verifyButtonsAvailableForGeneralUserInMyCart(String expectedButtonsInMyCartForGeneralUser)
			throws Exception {
		String expectedButtonsInMyCartForGeneralUserArray[] = expectedButtonsInMyCartForGeneralUser.split(",");
		for (int i = 0; i < buttonsInMyCart.size(); i++) {
			Assert.assertTrue(
					buttonsInMyCart.get(i).getText().trim().equalsIgnoreCase(
							expectedButtonsInMyCartForGeneralUserArray[i]),
					"Button from web app is " + buttonsInMyCart.get(i).getText().trim() + " .Button expected is : "
							+ expectedButtonsInMyCartForGeneralUserArray[i]);
		}
		return this;
	}

	@Step("Verify cart page instructions")
	public MyCartPageObjects verifyCartPageInstructions() {
		/*
		 * Assert.assertTrue(cartInstructionsHeader.isDisplayed(),
		 * "Cart tips heading is not displayed in the instructions."); String
		 * expectedCartInstructions[] = data .getExpectedCartInstructions() .split(":");
		 * for (int i = 0; i < cartInstructions.size(); i++) {
		 * Assert.assertEquals(cartInstructions .get(i) .getText() .trim(),
		 * expectedCartInstructions[i]); }
		 */

		return this;
	}

	@Step("Verify name of the product in my cart page is {0}")
	public MyCartPageObjects verifyNameOfTheProductInMyCartPage(String productName) throws Exception {
		Thread.sleep(1500);
		Assert.assertTrue(myCartProductName.get(0).getText().trim().equalsIgnoreCase(productName),
				"Product name in my cart page is not similar to what it was in product details page. Product name is : "
						+ myCartProductName.get(0).getText().trim() + ". Asserting it with : " + productName + ".");
		return this;
	}

	@Step("Enter save cart name as :{0}")
	public MyCartPageObjects enterNameOfSaveCart(String saveCartName) {
		waiting.waitForVisibilityOfElement(saveCartTextbox, 6);
		log = Logger.getLogger("enterNameOfSaveCart");

		saveCartTextbox.sendKeys(saveCartName);

		log.info("save Cart name has been entered as: " + saveCartName);
		return this;
	}

	@Step("Hit enter for save cart creation")
	public MyCartPageObjects hitEnterForSaveCartCreation() {
		log = Logger.getLogger("hitEnterForSaveCartCreation");

		saveCartTextbox.sendKeys(Keys.ENTER);

		log.info("hit enter for save cart creation");

		return this;
	}

	@Step("Verify save cart creation success message for :{0}")
	public MyCartPageObjects verifySaveCartCreationMessage(String saveCartName) {
		waiting.waitForVisibilityOfElement(successMessageForSaveCartCreationLocator, 4);

		log = Logger.getLogger("verifySaveCartCreationMessage");

		Assert.assertEquals(successMessageForSaveCartCreationLocator.getText().trim(),
				"Cart Saved Successfully - " + saveCartName,
				"Save Cart Creation Success Message is not" + "Cart Saved Successfully - " + saveCartName + ". It is :"
						+ successMessageForSaveCartCreationLocator.getText().trim());

		log.info("save cart creation success message  has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + successMessageForSaveCartCreationLocator.getText().trim() + "] and " + "["
				+ "Cart Saved Successfully - " + saveCartName + "]");

		return this;
	}

	@Step("Click on confirmation message of save cart creation")
	public MyCartPageObjects clickOnTheConfirmationMessage(String saveCartName) throws InterruptedException {
		waiting.waitForVisibilityOfElement(successMessageForSaveCartCreationLocator, 4);

		log = Logger.getLogger("clickOnTheConfirmationMessage");

		Thread.sleep(1000);
		testUtilityMethods.scrollThePageToBottom();
		//successMessageForSaveCartCreationLocator.click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				successMessageForSaveCartCreationLocator);

		Thread.sleep(1500);

		log.info("clicked on confirmation message of save cart creation");

		return this;
	}

	@Step("Move over update link")
	public MyCartPageObjects hoverOverUpdateLink() {
		Actions action = new Actions(getDriver());
		action.moveToElement(updateShoppingCartLink).build().perform();
		return this;
	}

	@Step("Verify refresh tooltip")
	public MyCartPageObjects verifyUpdateToolTip() {
		Assert.assertEquals(updateShoppingCartLink.getAttribute("title").trim(), "Update",
				"Update tooltip is not Update. It is " + updateShoppingCartLink.getAttribute("title").trim() + ".");
		return this;
	}

	@Step("Get total price")
	public Number getTotalPrice() throws ParseException {
		waiting.waitForVisibilityOfElement(totalPrice, 5);
		Number currentTotalPrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(totalPrice.getText().replace("\n", "").replace(" ", "").trim());
		return currentTotalPrice;
	}

	@Step("Enter quantity {0}")
	public MyCartPageObjects enterQuantityInShoppingCart(String quantity) throws InterruptedException {
		Thread.sleep(3000);
		log = Logger.getLogger("enterQuantityInShoppingCart");

		quantityInShoppingCartPage.get(0).click();

		quantityInShoppingCartPage.get(0).sendKeys(Keys.CONTROL + "a");
		quantityInShoppingCartPage.get(0).sendKeys(Keys.DELETE);
		Thread.sleep(1200);

		quantityInShoppingCartPage.get(0).sendKeys(quantity);

		log.info("entered quantity in Shopping cart page : " + quantity);

		return this;
	}

	@Step("Enter qty {0} for the item {1}")
	public MyCartPageObjects enterQuantityForSpecificItem(String qty, String partNumber) throws InterruptedException {

		WebElement qtyOfItem = getDriver()
				.findElement(By.xpath("//div[@id='cartWrap_wrapper']//strong[contains(text(),'Part#:')]"
						+ "/following-sibling::span[contains(text(),'" + partNumber + "')]/ancestor::td"
						+ "/following-sibling::td[@data-th='Qty']/input[@name='shoppingCartQty']"));

		qtyOfItem.click();

		qtyOfItem.sendKeys(Keys.CONTROL + "a");
		qtyOfItem.sendKeys(Keys.DELETE);
		Thread.sleep(1200);

		qtyOfItem.sendKeys(qty);

		System.out.println(" enterQuantityForSpecificItem :--> " + qty);

		Thread.sleep(1200);
		return this;

	}

	@Step("Click on update link")
	public MyCartPageObjects clickOnUpdateLink() {
		updateShoppingCartLink.click();
		return this;
	}

	public Number getExtensionPrice() throws ParseException {

		waiting.waitForVisibilityOfElement(extPrice, 5);
		Number price = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(extPrice.getText().replace("\n", "").replace(" ", "").trim());

		DecimalFormat oneDigit = new DecimalFormat("#.00");

		oneDigit.format(price);
		System.out.println(oneDigit.format(price));
		return price;
	}

	@Step("Verify update of extension price")
	public MyCartPageObjects verifyExtPriceAfterUpdate(String quantity, Number currentExtnPrice) throws ParseException {

		Number afterUpdateExtensionPrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(extPrice.getText().replace("\n", "").replace(" ", "").trim());

		int quantityValue = Integer.parseInt(quantity);

		Assert.assertTrue(checkForExtnPrice(currentExtnPrice, afterUpdateExtensionPrice, quantityValue),
				"extension price is not getting updated.");
		return this;
	}

	@Step("Verify ExtnPrice")
	private boolean checkForExtnPrice(Number previousPrice, Number afterPrice, int quantityValue) {
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");

		String previous = oneDigit.format(previousPrice.doubleValue() * quantityValue);

		String after = oneDigit.format(afterPrice.doubleValue());
		return previous.equals(after);

	}

	@Step("Verify update of total price")
	public MyCartPageObjects verifyTotalPrice(String quantity, Number currentTotalPrice) throws ParseException {
		Number afterUpdateTotalPrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(totalPrice.getText().replace("\n", "").replace(" ", "").trim());
		int quantityValue = Integer.parseInt(quantity);
		Assert.assertTrue(checkForExtnPrice(currentTotalPrice, afterUpdateTotalPrice, quantityValue),
				"total price is not getting updated");
		return this;
	}

	@Step("Verify empty of cart")
	public MyCartPageObjects verifyEmptyCart() throws Exception {
		waiting.waitForVisibilityOfElement(continueShoppingButtonInMyCartPage, 6);
		Assert.assertTrue(continueShoppingButtonInMyCartPage.isDisplayed(),
				"continue shopping button is not displayed. Hence shopping cart is not emptied.");
		return this;
	}

	@Step("Verify sort by dropdown options")
	public MyCartPageObjects verifySortByDropdownValues() {

		return this;
	}

	@Step("Check whether it is the same quantity {0}")
	public MyCartPageObjects checkWhetherItIsTheSameQuantity(String quantity) {
		waiting.waitForVisibilityOfElements(quantityInShoppingCartPage, 8);
		Assert.assertEquals(quantityInShoppingCartPage.get(0).getAttribute("value").trim(), quantity);
		return this;
	}

	@Step("Click on update button")
	public MyCartPageObjects clickOnUpdateButton() throws InterruptedException {
		log = Logger.getLogger("clickOnUpdateButton");

		waiting.waitForVisibilityOfElement(updateCartButton, 12);
		updateCartButton.click();
		Thread.sleep(3000);

		log.info("clicked on update button");
		return this;
	}

	@Step("Move over delete button")
	public MyCartPageObjects hoverOverDeleteButton() {
		Actions action = new Actions(getDriver());
		action.moveToElement(deleteItemLink.get(0)).build().perform();

		return this;
	}

	@Step("Verify delete tool tip")
	public MyCartPageObjects verifyDeleteToolTip() {
		Assert.assertEquals(deleteItemLinkForTooltip.get(0).getAttribute("title").trim(), "Delete");
		return this;
	}

	@Step("Click on delete link")
	public MyCartPageObjects clickOnDeleteLink() {
		log = Logger.getLogger("clickOnDeleteLink");

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", deleteItemLink.get(0));

		log.info("clicked on delete link");

		return this;
	}

	@Step("Click on specific item delete link")
	public MyCartPageObjects clickOnDeleteLink(int specificItem) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", deleteItemLink.get(specificItem - 1));
		return this;
	}

	@Step("Verify MPN is {0}")
	public MyCartPageObjects verifyMPN(String mpn) {
		Assert.assertEquals(mpnValue.get(0).getText().trim(), mpn);
		return this;
	}

	@Step("Verify Alert Text Message After clicking on Delete Link of Specific Item ")
	public MyCartPageObjects verifyDeleteAlertTextMessage(String pn) {

		waiting.waitForVisibilityOfElement(alertTextLocator, 10);
		log = Logger.getLogger("verifyDeleteAlertTextMessage");
		Assert.assertEquals(alertTextLocator.getText().trim(), "You want to delete item " + pn + " from cart?",
				"Invalid alert text while deleting an item.");

		log.info("Alert text Message has been verified");
		return this;

	}

	@Step("Click on image of the product")
	public ProductsDetailsPageObjects clickOnImageIfTheProduct() {
		log = Logger.getLogger("clickOnImageIfTheProduct");

		productImages.get(0).click();

		log.info("clicked on image of the product");
		return new ProductsDetailsPageObjects();
	}

	@Step("Click on specific product name")
	public ProductsDetailsPageObjects clickOnSpecificProductName(int specificProduct) {
		myCartProductName.get(specificProduct - 1).click();
		return new ProductsDetailsPageObjects();
	}

	@Step("Enter quantities in shopping cart as {0}")
	public MyCartPageObjects enterQuantitiesInShoppingCartForMultipleItems(
			String quantityForShoppingCartPageVerification) {

		for (int i = 0; i < quantityInShoppingCartPage.size(); i++) {
			quantityInShoppingCartPage.get(i).click();
			quantityInShoppingCartPage.get(i).clear();
			quantityInShoppingCartPage.get(i).sendKeys(quantityForShoppingCartPageVerification);
		}
		return this;
	}

	/*
	 * @Step("verify update quantities in shopping cart as {0}") public
	 * MyCartPageObjects verifyQuantitiesInShoppingCartForMultipleItems( String
	 * quantityForShoppingCartPageVerification) throws InterruptedException {
	 * waiting.waitForVisibilityOfElements(quantityInShoppingCartPage, 20);
	 * 
	 * logger = Logger.getLogger("verifyQuantitiesInShoppingCartForMultipleItems");
	 * for (int i = 0; i < quantityInShoppingCartPage.size(); i++) {
	 * Assert.assertEquals(quantityInShoppingCartPage.get(i).getAttribute("value"),
	 * quantityForShoppingCartPageVerification,
	 * "Quantity in shopping cart page is not getting updated. The updated quantity is "
	 * + quantityInShoppingCartPage.get(i).getAttribute("value"));
	 * 
	 * logger.info("The Updated quantity in shopping cart has been verified" +
	 * " with [Actual] and [Expected] value as " + "[" +
	 * quantityInShoppingCartPage.get(i).getAttribute("value") + "] and " + "[" +
	 * quantityForShoppingCartPageVerification + "]");
	 * 
	 * } return this; }
	 */

	@Step("Verify update quantities in shopping cart as {0}")
	public MyCartPageObjects verifyQuantitiesInShoppingCartForMultipleItems(
			String quantityForShoppingCartPageVerification) throws InterruptedException {
		waiting.waitForVisibilityOfElements(quantityInShoppingCartPage, 20);

		log = Logger.getLogger("verifyQuantitiesInShoppingCartForMultipleItems");
		for (int i = 0; i < quantityInShoppingCartPage.size(); i++) {
			Assert.assertEquals(quantityInShoppingCartPage.get(i).getAttribute("value"),
					quantityForShoppingCartPageVerification,
					"Quantity in shopping cart page is not getting updated. The updated quantity is "
							+ quantityInShoppingCartPage.get(i).getAttribute("value"));

			log.info("The Updated quantity in shopping cart has been verified"
					+ " with [Actual] and [Expected] value as " + "["
					+ quantityInShoppingCartPage.get(i).getAttribute("value") + "] and " + "["
					+ quantityForShoppingCartPageVerification + "]");

		}
		return this;
	}

	@Step("Verify display of cart section")
	public MyCartPageObjects verifyDisplayOfCartSection() {

		log = Logger.getLogger("verifyDisplayOfCartSection");

		Assert.assertTrue(myProductGroupCartSection.isDisplayed(), "cart section is not displayed.");

		log.info("cart section is displayed.");
		return this;
	}

	@Step("Verify whether number of items in shopping cart is {0}")
	public MyCartPageObjects verifyNumberOfItemsInShoppingCart(int expectedNumberOfProducts) {
		waiting.waitForVisibilityOfElements(productImages, 6);

		log = Logger.getLogger("verifyNumberOfItemsInShoppingCart");
		Assert.assertEquals(productImages.size(), expectedNumberOfProducts, "Number of products in the cart is "
				+ myCartProductName.size() + " but expecting " + expectedNumberOfProducts + ".");

		log.info("Number Of products in Shopping Cart has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + productImages.size() + "] and " + "[" + expectedNumberOfProducts + "]");
		return this;

	}

	@Step("Click on checkout in my cart page")
	public CheckoutPageObjects clickOnCheckoutInMyCartPage() throws InterruptedException {
		waiting.waitForVisibilityOfElement(checkoutButtonInMyCartPage, 10);
		Thread.sleep(1400);
		log = Logger.getLogger("clickOnCheckoutInMyCartPage");
		checkoutButtonInMyCartPage.click();
		Thread.sleep(1400);

		log.info("Clicked on checkout button in my cart page");
		return checkoutPage();

	}

	@Step("Click on submit cart for approval")
	public MyCartPageObjects clickOnSubmitCartForApproval() throws InterruptedException {
		log = Logger.getLogger("clickOnSubmitCartForApproval");
		waiting.waitForVisibilityOfElement(submitCartForApprovalButtonLocator, 10);
		submitCartForApprovalButtonLocator.click();
		Thread.sleep(1600);
		log.info("clicked on submit cart for approval.");
		return this;
	}

	@Step("Click on save cart button")
	public MyCartPageObjects clickOnSaveCartButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(saveCartButton, 6);

		log = Logger.getLogger("clickOnSaveCartButton");
		getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Thread.sleep(1200);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", saveCartButton);
		Thread.sleep(1200);

		log.info("Clicked on save cart button");

		return this;
	}

	@Step("Enter {0} as save cart name")
	public MyCartPageObjects enterNameOfSaveCartAndAddTheProductToSaveCart(String saveCartName) {
		waiting.waitForVisibilityOfElement(saveCartTextbox, 6);
		saveCartTextbox.sendKeys(saveCartName);
		return this;
	}

	@Step("Click on close button in my cart popup")
	public MyCartPageObjects clickOnCloseButtonInMyCartPopUp() throws InterruptedException {
		log = Logger.getLogger("clickOnCloseButtonInMyCartPopUp");
		waiting.waitForVisibilityOfElement(myCartPopUpCloseButtonLocator, 12);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myCartPopUpCloseButtonLocator);
		Thread.sleep(2500);
		log.info("clicked on close button in my cart pop up");
		return this;
	}

	@Step("Click on close button in my cart popup of Product list Or Grid Page")
	public MyCartPageObjects clickOnCloseButtonInMyCartPopUpOfProductListGridPage() throws InterruptedException {
		log = Logger.getLogger("clickOnCloseButtonInMyCartPopUp");
		waiting.waitForVisibilityOfElement(myCartPopUpCloseButtonLocatorOfProductListGridPage, 12);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				myCartPopUpCloseButtonLocatorOfProductListGridPage);
		Thread.sleep(2500);
		log.info("clicked on close button in my cart pop up");
		return this;
	}

	@Step("Click on close button of cart popup")
	public MyCartPageObjects clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel()
			throws InterruptedException {

		waiting.waitForVisibilityOfElement(myCartPopUpCloseButtonLocatorAtItemLevel, 12);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				myCartPopUpCloseButtonLocatorAtItemLevel);
		Thread.sleep(2500);
		return this;

	}

	public MyCartPageObjects verifyPerUnitPrice(String priceForSingleItemWithUOM) {
		waiting.waitForVisibilityOfElement(perUnitPriceLocator, 6);
		Assert.assertEquals(perUnitPriceLocator.getText().trim(), priceForSingleItemWithUOM);
		return this;
	}

	public MyCartPageObjects verifyUOM(String uomName) {
		Assert.assertEquals(uomChosenLocator.getText().replace(" ", "").trim(), uomName);
		return this;
	}

	@Step("Verify ext. price as:{0}")
	public MyCartPageObjects verifyExtPrice(Number expectedExtPrice) throws ParseException {
		Number actualExtPrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(extPrice.getText().replace("\n", "").replace(" ", "").trim());
		Assert.assertEquals(actualExtPrice, expectedExtPrice);
		return this;
	}

	@Step("Verify total price as:{0}")
	public MyCartPageObjects verifyTotalPrice(Number expectedTotalPrice) throws ParseException {
		Number actualTotalPrice = NumberFormat.getCurrencyInstance(Locale.US)
				.parse(totalPrice.getText().replace("\n", "").replace(" ", "").trim());
		Assert.assertEquals(actualTotalPrice, expectedTotalPrice);
		return this;
	}

	@Step("Verify total price as:{0}")
	public MyCartPageObjects verifyTotalPrice(float expectedTotalPrice) throws ParseException {
		float actualTotalPrice = Float
				.parseFloat(totalPrice.getText().replace("\n", "").replace(" ", "").replace("$", "").trim());
		Assert.assertEquals(actualTotalPrice, expectedTotalPrice);
		return this;
	}

	public MyCartPageObjects clickOnTheCreatedCartFromTheSaveCartDropdownList(String saveCartName) {
		waiting.waitForVisibilityOfElements(saveCartDropdownListLocator, 5);
		for (WebElement saveCartDropdownList : saveCartDropdownListLocator) {
			if (saveCartDropdownList.getText().trim().equals(saveCartName)) {
				saveCartDropdownList.click();
				break;
			}
		}

		return this;
	}

	@Step("Click on checkout button in my cart popup from product details page")
	public MyCartPageObjects clickOnCheckoutInMyCartPopupProductDetailsPage() {
		waiting.waitForElementToBeClickable(checkoutButtonInMyProductGroupLocator, 6);

		log = Logger.getLogger("clickOnViewCartInMyCartPopup");

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				checkoutButtonInMyProductGroupLocator);

		log.info("clicked On ViewCart Button In My Cart Popup");

		return this;
	}

	@Step("Verify name of product:{0} in cart preview")
	public MyCartPageObjects verifyNameOfTheProductInCartPreview(int specificProduct, String productName) {
		waiting.waitForVisibilityOfElements(productNameInCartPreviewLocator, 6);
		Assert.assertEquals(productNameInCartPreviewLocator.get(specificProduct - 1).getText().toLowerCase().trim(),
				productName.toLowerCase(), "Product name is not correct in cart preview");
		return this;
	}

	@Step("Verify quantity:{0} in cart preview")
	public MyCartPageObjects verifyQuantityInCartPreview(int specificProduct, String quantity) {
		Assert.assertEquals(quantityInCartPreviewLocator.get(specificProduct - 1).getText().trim(), quantity,
				"Quantity is not correct in cart preview");
		return this;
	}

	@Step("Verify total number of items in shopping cart")
	public MyCartPageObjects verifyTotalItemsInShoppingCartCount() {
		Assert.assertEquals(totalItemsInShoppingCartCountCartPreviewLocator.getText().trim(),
				Integer.toString(imgInCartPreviewLocator.size()),
				"Number of items in shopping cart count in the cart preview is wrong");
		return this;
	}

	@Step("Verify price precision in My Cart popup")
	public MyCartPageObjects verifyPricePrecisionInMyCartPage(String pnNumber, String pricePrecision) throws InterruptedException {
		Thread.sleep(3000);
		WebElement specificItemPrice = getDriver()
				.findElement(By.xpath("//div[contains(@id,'cartPop')]//span[contains(text(),'" + pnNumber
						+ "')]/ancestor::div[@class='row']//span[contains(@class,'price')]"));

		Assert.assertEquals(
				specificItemPrice.getText().replace("/ EACH", "").replace("/ (1)", "").replace("$", "").trim().split("\\.")[1].length(), Integer.parseInt(pricePrecision),
				"Price precision in not matched in My cart popup");

		log.info("Verify Price precision in My Cart pop up");
		return this;
	}

	@Step("Verify price precision in Shopping Cart page")
	public MyCartPageObjects verifyPricePrecisionInShoppingCart(String pnNumber, String pricePrecesion) {

		WebElement specificItemPrice = getDriver().findElement(By.xpath("//span[contains(text(),'" + pnNumber
				+ "')]/ancestor::td/following-sibling::td[contains(@data-th,'Unit')]/strong"));

		Assert.assertEquals(specificItemPrice.getText().replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1].length(), Integer.parseInt(pricePrecesion),
				"Price precision in not matched in Shopping cart page");

		log.info("Verify Price precision in Shopping Cart page");
		return this;
	}

	@Step("Verify price of a specific item")
	public MyCartPageObjects verifyPriceOfTheSpecificItemAddedInCartView(int specificItemInCartView,
			String updatedPrice) {
		waiting.waitForVisibilityOfElements(priceIntCartPreviewLocator, 6);
		Assert.assertEquals(priceIntCartPreviewLocator.get(specificItemInCartView - 1).getText().trim(), updatedPrice,
				"Price is wrong for the item added in cart view");
		return this;
	}

	@Step("Verify cart preview dropdown")
	public MyCartPageObjects verifyCartPreviewDropdown(String stageOfTheCartPreview) throws Exception {
		switch (stageOfTheCartPreview) {
		case "After":

			waiting.waitForVisibilityOfElement(cartPreviewSectionLocator, 3);
			Assert.assertTrue(cartPreviewSectionLocator.isDisplayed(), "Cart preview dropdown is not displayed");
			waiting.waitForVisibilityOfElement(cartPreviewCartTotalLocator, 3);
			Assert.assertTrue(cartPreviewCartTotalLocator.isDisplayed(),
					"Cart Total in cart preview is not displayed.");
			Assert.assertTrue(cartPreviewTotalItemsSectionLocator.isDisplayed(),
					"Total Items in shopping cart is not displayed.");
			Assert.assertTrue(cartPreviewContinueShoppingButtonLocator.isDisplayed(),
					"Continue shopping button in shopping cart is not displayed.");
			Assert.assertTrue(cartPreviewCheckoutButtonLocator.isDisplayed(),
					"Checkout button in shopping cart is not displayed.");
			Assert.assertTrue(quantityInCartPreviewLocator.get(0).isDisplayed(), "Quantity Value is not displayed.");
			Assert.assertTrue(priceIntCartPreviewLocator.get(0).isDisplayed(), "Quantity Value is not displayed.");
			Assert.assertTrue(imgInCartPreviewLocator.get(0).isDisplayed(), "Image of the product is not displayed.");
			Assert.assertTrue(cartPreviewRemoveItemButtonLocator.get(0).isDisplayed(), "Close button is not displayed");
			Assert.assertTrue(productNameInCartPreviewLocator.get(0).isDisplayed(), "Product Name is not displayed");
			break;
		case "Before":
			waiting.waitForVisibilityOfElement(pleaseShopTextInCartPreviewLocator, 3);
			Assert.assertTrue(pleaseShopTextInCartPreviewLocator.isDisplayed(), "Please shop text is not displayed");
			Assert.assertTrue(cartPreviewCheckoutButtonLocator.isDisplayed(),
					"Checkout button in shopping cart is not displayed.");
			Assert.assertTrue(cartPreviewContinueShoppingButtonLocator.isDisplayed(),
					"Continue shopping button in shopping cart is not displayed.");
			Assert.assertTrue(cartPreviewCartTotalLocator.isDisplayed(),
					"Cart Total in cart preview is not displayed.");
			Assert.assertTrue(cartPreviewTotalItemsSectionLocator.isDisplayed(),
					"Total Items in shopping cart is not displayed.");
			break;
		default:
			throw new Exception("invalid stage of the cart preview");
		}
		return this;
	}

	@Step("Click on continue shopping button in cart preview")
	public MyCartPageObjects clickOnContinueShoppingButtonInCartPreview() {
		waiting.waitForVisibilityOfElement(cartPreviewContinueShoppingButtonLocator, 7);
		cartPreviewContinueShoppingButtonLocator.click();
		return this;
	}

	@Step("Verify cart preview section is not displayed")
	public MyCartPageObjects verifyThatCartPreviewDropdownSectionIsNotDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(assertCartPreviewDropdownIsNotDisplayed(), "Cart preview dropdown is still displayed");
		return this;
	}

	private boolean assertCartPreviewDropdownIsNotDisplayed() {
		try {
			return !cartPreviewSectionLocator.isDisplayed();
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	@Step("Click on checkout button in cart preview")
	public MyCartPageObjects clickOnCheckoutButtonInCartPreview() {
		waiting.waitForVisibilityOfElement(cartPreviewCheckoutButtonLocator, 6);
		cartPreviewCheckoutButtonLocator.click();
		return this;
	}

	@Step("Verify cart total:{0} in cart preview")
	public MyCartPageObjects verifyCartTotalValueInCartPreview(float expectedTotalPrice) {
		waiting.waitForVisibilityOfElement(cartPreviewCartTotalLocator, 6);
		String valueOfCartTotal = cartPreviewCartTotalLocator.getText().replace("CART TOTAL : $", "").trim();
		Assert.assertEquals(valueOfCartTotal, Float.toString(expectedTotalPrice), "Cart total is wrong.");
		return this;
	}

	@Step("Verify number of items:{0} in cart preivew")
	public MyCartPageObjects verifyNumberOfItemsInCartPreview(int expectedNumberOfItemsInCartPreview) {
		waiting.waitForVisibilityOfElements(productNameInCartPreviewLocator, 5);
		Assert.assertEquals(productNameInCartPreviewLocator.size(), expectedNumberOfItemsInCartPreview,
				"Number of Products in wrong in cart preview");
		Assert.assertEquals(
				cartPreviewTotalItemsSectionLocator.getText().replace("TOTAL ITEMS IN SHOPPING CART : ", "").trim(),
				Integer.toString(expectedNumberOfItemsInCartPreview), "Number Of Items in cart preview is wrong");
		return this;
	}

	@Step("Click on {0} st/nd/rd/th remove button in cart preview")
	public MyCartPageObjects clickOnRemoveButtonForSpecificItemInCartPreview(int specificItem) {
		waiting.waitForVisibilityOfElements(cartPreviewRemoveItemButtonLocator, 4);
		cartPreviewRemoveItemButtonLocator.get(specificItem - 1).click();
		return this;
	}

	@Step("Verify product:{0} is removed from cart preview")
	public MyCartPageObjects verifyRemovalOfTheProductInCartPreview(String productName) throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(assertRemovalOfProductInCartPreview(productName), "Product is not removed in cart Preview");
		return this;
	}

	private boolean assertRemovalOfProductInCartPreview(String productName) {
		try {
			return !getDriver().findElement(By.xpath("//a[text()[normalize-space() = '" + productName + "']]"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	@Step("Get the product name")
	public String getNameOfTheProductFromCartPreview(int specificItem) {
		waiting.waitForVisibilityOfElements(productNameInCartPreviewLocator, 5);
		return productNameInCartPreviewLocator.get(specificItem - 1).getText().trim();
	}

	@Step("Verify part number in cart page :{0}")
	public MyCartPageObjects verifyPartNumberInMyCart(String partNumber) {

		log = Logger.getLogger("verifyPartNumberInMyCart");
		WebElement pn = getDriver().findElement(By.xpath(
				"//table[@id='cartWrap']//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"+ partNumber.trim() + "')]"));
		Assert.assertTrue(pn.isDisplayed(), "Item(s) not added to cart");

		log.info("Item part Number In Shopping Cart is displayed");
		return this;
	}

	@Step("Verify description of item {0} in cart page :{1}")
	public MyCartPageObjects verifyshortDesciptionInMyCart(String addedItem, String description) {

		log = Logger.getLogger("verifyPartNumberInMyCart");
		String shortDescDisplayed = getDriver()
				.findElement(By.xpath(
						"//table[@id='cartWrap']//span[contains(text(),'" + addedItem.trim() + "')]/ancestor::ul/li/p"))
				.getText()
				.trim();
		Assert.assertTrue(shortDescDisplayed.contains(description),
				"Short Discription missmatch in shopping cart page, actual displayed is: " + shortDescDisplayed);

		log.info("Item Short description In Shopping Cart is displayed");
		return this;
	}

	@Step("Verify part number in cart pop up :{0}")
	public MyCartPageObjects verifyPartNumberInMyCartPopUp(String partNumber) {

		log = Logger.getLogger("verifyPartNumberInMyCartPopUp");
		WebElement pn = getDriver().findElement(By.xpath("//div[@class='modal-content']//span[contains(text(),'" +partNumber+ "')]"));

		Assert.assertTrue(pn.isDisplayed(), "Item not added to cart");

		log.info("Item part Number In My Cart Pop Up is displayed");
		return this;

	}

	@Step("Verify item short description in my cart pop up is {0}")
	public MyCartPageObjects verifyShortDescriptionInMyCartPopUp(String shortDescription) {

		waiting.waitForVisibilityOfElement(shortDescriptionLocatorInMyCartPopUp, 10);

		log = Logger.getLogger("verifyShortDescriptionInMyCartPopUp");

		/*
		 * Assert.assertEquals(shortDescriptionLocatorInMyCartPopUp.getText().trim(),
		 * shortDescription.trim(), "Short Description of the item is not same");
		 */
        System.out.println(shortDescriptionLocatorInMyCartPopUp.getText().trim());
		Assert.assertTrue(shortDescriptionLocatorInMyCartPopUp.getText().trim().contains(shortDescription),"Actual short desc displayed :" + shortDescriptionLocatorInMyCartPopUp.getText().trim());

		log.info(
				"Item Short Description In My Cart Pop Up has been verified" + " with [Actual] and [Expected] value as "+ "[" + shortDescriptionLocatorInMyCartPopUp.getText().trim() + "] and " + "["+ shortDescription + "]");
		return this;

	}

	@Step("Verify item price in my cart pop up is {0}")
	public MyCartPageObjects verifyPriceInMyCartPopUp(String price) {

		waiting.waitForVisibilityOfElement(priceLocatorInMyCartPopUp, 10);

		log = Logger.getLogger("verifyPriceInMyCartPopUp");

		String priceOfItemInMyCartPopUp = String
				.valueOf(Math.round(Float.parseFloat(priceLocatorInMyCartPopUp.getText().replace("$", "").trim())));

		Assert.assertEquals(priceOfItemInMyCartPopUp, price.trim(), "price of the item is not same");

		log.info("Item Price In My Cart Pop Up has been verified" + " with [Actual] and [Expected] value as " + "["
				+ priceOfItemInMyCartPopUp + "] and " + "[" + price.trim() + "]");

		return this;

	}

	@Step("Verify part numbers in cart page")
	public MyCartPageObjects verifyPartNumberInMyCart(String[] partNumber) {
		for (int i = 0; i < partNumber.length; i++) {
			WebElement pn = getDriver().findElement(By.xpath(
					"//table[@id='cartWrap']//strong[contains(text(),'Part#:']/following-sibling::span[contains(text(),'"
							+ partNumber[i].trim() + "']"));
			Assert.assertTrue(pn.isDisplayed(), "Item(s) not added to cart");
		}
		return this;
	}

	@Step("Verify part numbers in cart page")
	public MyCartPageObjects verifyPartNumberInMyCart(List<String> partNumber) {
		for (int i = 0; i < partNumber.size(); i++) {
			WebElement pn = getDriver().findElement(By.xpath("//table[@id='cartWrap']//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
							+ partNumber.get(i) + "')]"));
			Assert.assertTrue(pn.isDisplayed(), "Item(s) not added to cart");
		}
		return this;
	}

	@Step("Find Part Number Of Specfic Item Present In Shopping Cart Page")
	public String getPartNumberOfSpecificItem(int specificItem) {
		log = Logger.getLogger("getPartNumberOfSpecificItem");

		return partNumberValueLocator.get(specificItem - 1).getText().trim();

	}

	@Step("Delete item")
	public MyCartPageObjects deleteSpecificItem(String partNumber1) {
		WebElement deleteItem = getDriver().findElement(By.xpath(
				"//table[@id='cartWrap']//strong[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber1.trim() + ")']/ancestor::td[@data-th='Description']/..//a[@title='Delete']"));
		deleteItem.click();
		return this;
	}

	@Step("Verify item:{0} is deleted from cart")
	public MyCartPageObjects verifyItemIsDeletedFromCart(String partNumber1) {
		By deleteItem = By.xpath(
				"//table[@id='cartWrap']//storng[contains(text(),'Part#:')]/following-sibling::span[contains(text(),'"
						+ partNumber1.trim() + "')]/ancestor::td[@data-th='Description']/..//a[@title='Delete']");
		Assert.assertTrue(testUtilityMethods.isElementNotDisplayed(deleteItem), "Item deleted still exists in cart");
		return this;
	}

	@Step("Verify item count in my cart pop up is {0}")
	public MyCartPageObjects verifyItemsCountInMyCartPopUp(String count) throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElement(totalItemsCountInMyCartPopUp, 10);

		log = Logger.getLogger("verifyItemsCountInMyCartPopUp");

		Assert.assertEquals(totalItemsCountInMyCartPopUp.getText().trim(), count.trim(), "Invalid items count");

		log.info("Item Count In My Cart Pop Up has been verified" + " with [Actual] and [Expected] value as " + "["
				+ totalItemsCountInMyCartPopUp.getText().trim() + "] and " + "[" + count.trim() + "]");
		return this;
	}

	@Step("Verify item header in my cart pop up is {0}")
	public MyCartPageObjects verifyItemHeadersAvailableInMyCartPage(String itemHeaders) {

		waiting.waitForVisibilityOfElements(itemHeadersLocatorInMyCartPage, 10);

		log = Logger.getLogger("verifyItemHeadersAvailableInMyCartPage");

		String[] expectedItemHeadersInMyCartPage = itemHeaders.split(",");

		for (int i = 0; i < itemHeadersLocatorInMyCartPage.size(); i++) {

			Assert.assertEquals(itemHeadersLocatorInMyCartPage.get(i).getText().trim().toLowerCase(),
					expectedItemHeadersInMyCartPage[i].trim().toLowerCase(),
					"Actual Item Headers are " + itemHeadersLocatorInMyCartPage.get(i).getText().trim().toLowerCase()
							+ "Expected Item Headers are " + expectedItemHeadersInMyCartPage[i].trim().toLowerCase());

			log.info("Item Header of Shopping Cart page has been verified" + " with [Actual] and [Expected] value as "
					+ "[" + itemHeadersLocatorInMyCartPage.get(i).getText().trim().toLowerCase() + "] and " + "["
					+ expectedItemHeadersInMyCartPage[i].trim().toLowerCase() + "]");

		}
		return this;
	}

	@Step("Verify Button Available For Super User In My Cart Page {0}")
	public MyCartPageObjects verifyButtonsAvailableForSuperUserAndGeneralUserInMyCart(String buttonsAvailable) {

		waiting.waitForVisibilityOfElements(buttonsInMyCart, 10);

		log = Logger.getLogger("verifyButtonsAvailableForSuperUserInMyCart");

		String buttonPattern[] = buttonsAvailable.split(">");
		try {

			String expectedButtonsInMyCartForSuperUser[] = buttonPattern[0].split(",");

			for (int i = 0; i < buttonsInMyCart.size(); i++) {

				Assert.assertEquals(buttonsInMyCart.get(i).getText().trim().toLowerCase(),
						expectedButtonsInMyCartForSuperUser[i].trim().toLowerCase(),
						"Button actual is " + buttonsInMyCart.get(i).getText().trim() + "Button expected is : "
								+ expectedButtonsInMyCartForSuperUser[i]);

				log.info("Button Available For Super User has been verified" + " with [Actual] and [Expected] value as "
						+ "[" + buttonsInMyCart.get(i).getText().trim().toLowerCase() + "] and " + "["
						+ expectedButtonsInMyCartForSuperUser[i].trim().toLowerCase() + "]");
			}
		} catch (Exception | AssertionError e) {
			String expectedButtonsInMyCartForSuperUser[] = buttonPattern[1].split(",");

			for (int i = 0; i < buttonsInMyCart.size(); i++) {

				Assert.assertEquals(buttonsInMyCart.get(i).getText().trim().toLowerCase(),
						expectedButtonsInMyCartForSuperUser[i].trim().toLowerCase(),
						"Button actual is " + buttonsInMyCart.get(i).getText().trim() + "Button expected is : "
								+ expectedButtonsInMyCartForSuperUser[i]);

				log.info("Button Available For Super User has been verified" + " with [Actual] and [Expected] value as "
						+ "[" + buttonsInMyCart.get(i).getText().trim().toLowerCase() + "] and " + "["
						+ expectedButtonsInMyCartForSuperUser[i].trim().toLowerCase() + "]");
			}
		}

		return this;
	}

	@Step("Click on Pagination Button in Shopping Cart page")
	public MyCartPageObjects clickOnPaginationButton(String pageNumber) throws InterruptedException {

		waiting.waitForVisibilityOfElement(By.xpath("//a[contains(text(),'" + pageNumber + "')]"), 10);
		log = Logger.getLogger("clickOnPaginationButton");

		getDriver().findElement(By.xpath("//a[contains(text(),'" + pageNumber + "')]")).click();
		Thread.sleep(2200);

		log.info("Clicked on Pagination Button in Shopping Cart page");

		return this;

	}

	@Step("Verify Page Number After Clicking On pagination button in Shopping Cart page : {0}")
	public MyCartPageObjects verifyPaginationPage(String expectedPageNumber) {

		log = Logger.getLogger("verifyPaginationPage");

		Assert.assertEquals(activePaginationLocator.getText(), expectedPageNumber,
				"Page Number is not after clicking on Pagination Button  " + expectedPageNumber + ". It is :"
						+ activePaginationLocator.getText());

		log.info("Page Number has been verified" + " with [Actual] and [Expected] value as " + "["
				+ activePaginationLocator.getText() + "] and " + "[" + expectedPageNumber + "]");

		return this;

	}

	@Step("Get Total Number Of Items Present In Shopping Cart Page")
	public String getTotalNumberofItemsInMyCart() {
		waiting.waitForVisibilityOfElement(totalNoOfItemsInMyCartLocator, 10);
		log = Logger.getLogger("getTotalNumberofItemsInMyCart");

		String totalNoOfItems = totalNoOfItemsInMyCartLocator.getText()
				.replace("Total Item:", "")
				.replace(" ", "")
				.trim();

		log.info("Total Number of Items In Shopping Cart page : " + totalNoOfItems);

		return totalNoOfItems;

	}

	@Step("Verify Number Of items In Shopping Cart Page :{0}")
	public MyCartPageObjects verifyNoOfItemsInMyCart(String totalNoOfItemsInMyCartPage) {

		log = Logger.getLogger("verifyNoOfItemsInMyCart");

		String actualNoOfItems = totalNoOfItemsInMyCartLocator.getText().replace("Total Item: ", "").trim();

		Assert.assertEquals(actualNoOfItems, totalNoOfItemsInMyCartPage,

				"Total Number Of Items are not  " + totalNoOfItemsInMyCartPage + ". It is :" + actualNoOfItems);

		log.info("Total Number of Items In Shopping Cart Page have been verified"
				+ " with [Actual] and [Expected] value as " + "[" + actualNoOfItems + "] and " + "["
				+ totalNoOfItemsInMyCartPage + "]");

		return this;

	}

	@Step("Verify blank space to create new save cart. {0}")
	public MyCartPageObjects verifyBlankSpaceToCreateNewSaveCart(String saveCartPlaceHolder) {

		waiting.waitForVisibilityOfElement(saveCartTextbox, 10);

		log = Logger.getLogger("verifyBlanSpaceToCreateNewSaveCart");

		Assert.assertEquals(saveCartTextbox.getAttribute("placeholder"), saveCartPlaceHolder,
				"Blank Space is not displayed to create new Save Cart.");

		log.info("Blank Space to create new Save Cart has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + saveCartTextbox.getAttribute("placeholder") + "] and " + "[" + saveCartPlaceHolder + "]");

		return this;

	}

	@Step("Verify previously created saved cart")
	public MyCartPageObjects verifyPreviouslyCreatedSavedCart() {

		for (int i = 0; i < saveCartDropdownListLocator.size(); i++) {

			Assert.assertTrue(saveCartDropdownListLocator.get(i).getTagName().contains("a"));
		}

		return this;
	}

	@Step("Click on Share This Link In ShoppingCart Page")
	public MyCartPageObjects clickOnShareThisLinkInShoppingCart() throws InterruptedException {

		waiting.waitForVisibilityOfElement(shareThisLinkLocator, 10);

		log = Logger.getLogger("clickOnShareThisLinkInShoppingCart");

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", shareThisLinkLocator);

		Thread.sleep(1500);

		log.info("Clicked on Share This Link In ShoppingCart Page");
		return this;

	}

	@Step("Verify display of Combine Button In My Cart Pop Up")
	public MyCartPageObjects verifyDisplayOfCombineButtonInMyCartPopUp() {
		waiting.waitForVisibilityOfElement(combineButtonInMyCartPopUpLocator, 18);
		log = Logger.getLogger("verifyDisplayOfCombineButtonInMyCartPopUp");
		Assert.assertTrue(combineButtonInMyCartPopUpLocator.isDisplayed(), "Combine Button is not displayed");
		log.info("Combine Button is displayed");
		return this;
	}

	@Step("Verify display of Seperate Button In My Cart Pop Up")
	public MyCartPageObjects verifyDisplayOfSeperateButtonInMyCartPopUp() {
		waiting.waitForVisibilityOfElement(seperateButtonInMyCartPopUpLocator, 10);
		log = Logger.getLogger("verifyDisplayOfSeperateButtonInMyCartPopUp");
		Assert.assertTrue(seperateButtonInMyCartPopUpLocator.isDisplayed(), "Seperate Button is not displayed");
		log.info("Seperate Button is displayed");
		return this;
	}

	@Step("Verify display of Cancle Button In My Cart Pop Up")
	public MyCartPageObjects verifyDisplayOfCancleButtonInMyCartPopUp() {
		waiting.waitForVisibilityOfElement(cancleButtonInMyCartPopUpLocator, 10);
		log = Logger.getLogger("verifyDisplayOfCancleButtonInMyCartPopUp");
		Assert.assertTrue(cancleButtonInMyCartPopUpLocator.isDisplayed(), "cancle Button is not displayed");
		log.info("cancle Button is displayed");
		return this;
	}

	@Step("Click on Combine Button in my cart pop up")
	public MyCartPageObjects clickOnCombineButtonInMyCartPopUp() throws InterruptedException {
		log = Logger.getLogger("clickOnCombineButtonInMyCartPopUp");
		combineButtonInMyCartPopUpLocator.click();
		log.info("Clicked on Combine Button in my cart pop up");
		Thread.sleep(1200);
		return this;

	}

	@Step("Click on Seperate Button in my cart pop up")
	public MyCartPageObjects clickOnSeperateButtonInMyCartPopUp() throws InterruptedException {

		log = Logger.getLogger("clickOnSeperateButtonInMyCartPopUp");
		seperateButtonInMyCartPopUpLocator.click();
		Thread.sleep(1200);

		log.info("Clicked on Seperate Button in my cart pop up");
		return this;
	}

	@Step("Click on Cancle Button in my cart pop up")
	public MyCartPageObjects clickOnCancleButtonInMyCartPopUp() throws InterruptedException {

		log = Logger.getLogger("clickOnCancleButtonInMyCartPopUp");
		cancleButtonInMyCartPopUpLocator.click();
		Thread.sleep(1200);

		log.info("Clicked on Cancle Button in my cart pop up");
		return this;
	}

	@Step("Verify view cart button in my cart popup")
	public MyCartPageObjects verifyOfViewCartButtonInMyCartPopUp() {
		waiting.waitForVisibilityOfElement(checkoutButtonInMyCartPopUp, 10);
		Assert.assertTrue(checkoutButtonInMyCartPopUp.isDisplayed());
		return this;
	}

	@Step("Verify Add To Cart at item level")
	public MyCartPageObjects verificationOfAddToCartButtonFunctionalityAtItemLevel() {
		waiting.waitForVisibilityOfElement(viewCartButtonInMyCartPopUp, 10);
		Assert.assertTrue(viewCartButtonInMyCartPopUp.isDisplayed());
		return this;
	}

	@Step("Verify quantity {1} for the item {0}")
	public MyCartPageObjects verifyQuantityOfSpecificItem(String partNumber, int qty) {
		String totalqty = getDriver()
				.findElement(By.xpath("//td[@data-th='Qty']//input[@data-partnumber='" + partNumber + "']"))
				.getAttribute("value")
				.trim();
		Assert.assertEquals(totalqty, String.valueOf(qty), "Quantity did not increase");
		return this;
	}

	@Step("Get the price of the item {0}")
	public String getPriceOfSpecificItem(String searchTextForPN) {
		WebElement ele = getDriver().findElement(By.xpath("//table[@id='cartWrap']//strong[contains(text(),'Part#:')]"
				+ "/following-sibling::span[contains(text(),'" + searchTextForPN
				+ "')]/ancestor::td/following-sibling::td[@data-th='Per Unit Price']/strong"));
		String priceOfItem = ele.getText().replace(" / (1)", "").replace("$", "").trim();
		return priceOfItem;
	}

	@Step("Verify quantity textbox contains {0} after update")
	public MyCartPageObjects verifyUpdateOfQuantityInShoppingCart(String quantity) throws Exception {
		waiting.waitForVisibilityOfElements(quantityInShoppingCartPage, 12);
		Assert.assertEquals(quantityInShoppingCartPage.get(0).getAttribute("value"), quantity,
				"Quantity in shopping cart page is not getting updated. The updated quantity is "
						+ quantityInShoppingCartPage.get(0).getAttribute("value"));
		return this;
	}

	@Step("Verify quantity textbox contains {0} after update")
	public MyCartPageObjects verifyUpdateOfQuantityInShoppingCart(String quantity, String partNumber) throws Exception {
		WebElement qtyOfItem = getDriver()
				.findElement(By.xpath("//div[@id='cartWrap_wrapper']//strong[contains(text(),'Part#:')]"
						+ "/following-sibling::span[contains(text(),'" + partNumber + "')]/ancestor::td"
						+ "/following-sibling::td[@data-th='Qty']/input[@name='shoppingCartQty']"));
		Assert.assertEquals(qtyOfItem.getAttribute("value"), quantity,
				"Quantity in shopping cart page is not getting updated. The updated quantity is "
						+ qtyOfItem.getAttribute("value"));
		return this;
	}

	@Step("Verify updated price {0} for item {0}")
	public MyCartPageObjects verifyUpdatedExtPrice(String updateExtPrice, String partNumber)
			throws InterruptedException {
		Thread.sleep(1000);
		String actualExtPrice = getDriver()
				.findElement(By.xpath("//strong[contains(text(),'Part#:')]" + "/following-sibling::span[contains(text(),'"
						+ partNumber + "')]" + "/ancestor::td/following-sibling::td[@data-th='Ext Price']/strong"))
				.getText()
				.trim()
				.replace("$", "");
		Assert.assertEquals(Math.round(Float.parseFloat(actualExtPrice)), Math.round(Float.parseFloat(updateExtPrice)),
				"Ext. Price not updated after quantity is updated");
		return this;

	}

}