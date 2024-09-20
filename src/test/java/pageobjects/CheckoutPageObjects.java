package pageobjects;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class CheckoutPageObjects extends PageInitializer {

	Logger log = Logger.getLogger(CheckoutPageObjects.class);

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	public TestDataPropertyFile data = new TestDataPropertyFile();

	@FindBy(xpath = "//li[contains(.,'Checkout')]")
	private WebElement checkoutBreadCrumpLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(text(),'Home')]")
	private WebElement homeBreadCrumbLocator;

	@FindBy(xpath = "//h1[contains(.,'Checkout')]")
	private WebElement checkoutPageNameLocator;

	@FindBy(xpath = "//button[@data-id='shipVia']/following-sibling::div//span[1]")
	private List<WebElement> shipMethodOptionsLocator;

	@FindBy(id = "shipEmail")
	private WebElement emailIdForShippingAddressLocator;

	@FindBy(xpath = "//*[contains(text(),'Billing Address')][@class='active']")
	private WebElement billingAddressTabLocator;

	@FindBy(xpath = "//h3[contains(text(),'Shipping Address')]")
	private WebElement shippingAddressTabLocator;

	@FindBy(xpath = "//*[contains(text(),'Order Review')]")
	private WebElement orderReviewTabLocator;

	@FindBy(xpath = "//div[@id='billAddress']//input[contains(@class,'btns-disable form-control')]")
	private List<WebElement> billingAddressMadatoryFieldsLocator;

	@FindBy(xpath = "//button[@data-id='stateSelect']")
	private WebElement billingAddressStateLocator;

	@FindBy(xpath = "//button[@data-id='countrySelect']")
	private WebElement billingAddressCountryLocator;

	@FindBy(xpath = "//h3[text()='Order Details']")
	private WebElement orderDetailsTabLocator;

	@FindBy(css = "#paymentOptBlock>ul>li:first-child>a")
	private WebElement byPOTabLocator;

	@FindBy(xpath = "//div[contains(@class,'cimm_tableDescSection')]/descendant::a")
	private WebElement productNameLocator;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorMessageLocatorOfOrderDetailsPage;

	/*
	 * @FindBy(xpath = "//h1[@class='cimm_pageTitle']") private WebElement
	 * orderConfirmationPageNameLocator;
	 */

	@FindBy(xpath = "//h1[@class='cimm_page-title']")
	private WebElement orderConfirmationPageNameLocator;

	@FindBy(id = "errorMsg")
	private WebElement errorMessageLocator;

	@FindBy(id = "billPhoneNo")
	private WebElement phoneNumberLocator;

	@FindBy(id = "shipPhoneNo")
	private WebElement shipPhoneNumberLocator;

	@FindBy(id = "fname")
	private WebElement billingCompanyName;

	@FindBy(xpath = "//div[@id='printOrderConfirm']//thead//th")
	private List<WebElement> orderConfirmationFieldsLocator;

	@FindBy(xpath = "//div[@id='SendOrderConfirmationPage']//h4")
	private List<WebElement> orderConfirmationHeadingLocator;

	@FindBy(xpath = "//div[@class='orderStatus']/h3")
	private WebElement orderConfirmationSuccessMessageLocator;

	@FindBy(xpath = "//div[@id='shipAddress']//input[@type='text' and not(@id='shipAddress2')]")
	private List<WebElement> shippingAddressFieldsLocator;

	@FindBy(xpath = "//div[@id='shipAddress']//button")
	private List<WebElement> shipToNameStateCountryLocatorsOfShippingAddressTab;

	@FindBy(xpath = "//a[@class='btnNext']")
	private WebElement nextButtonLocator;

	@FindBy(xpath = "//a[@class='btnPrevious']")
	private WebElement previousButtonLocator;

	@FindBy(xpath = "//strong[normalize-space(text())='Order Number:']/following-sibling::span")
	private WebElement orderNumberLocator;

	// @FindBy(xpath = "//button[@data-id='orderType']")
	@FindBy(xpath = "//input[@id='orderedBy']")
	private WebElement orderTypeLocator;

	@FindBy(xpath = "//button[@data-id='shipVia']")
	private WebElement shipMethodLocator;

	@FindBy(id = "orderedBy")
	private WebElement orderedByLocator;

	@FindBy(id = "poNumberTxt")
	private WebElement purchaseOrderNumberLocator;

	@FindBy(id = "discountCoupons")
	private WebElement couponsCodeLocator;

	@FindBy(id = "shippingInstruction")
	private WebElement shippingInstructionsLocator;

	@FindBy(id = "orderNotes")
	private WebElement orderNotesLocator;

	@FindBy(xpath = "//div[@id='shipVia_chosen']/a")
	private WebElement shipViaLocator;

	@FindBy(xpath = "//input[@id='reqDate']")
	private WebElement requiredByDateLocator;

	@FindBy(xpath = "//form[@id='quickCartHiddenInfo']//th")
	private List<WebElement> orderSummaryHeaderLocator;

	@FindBy(xpath = "//a[@id='wizFinishButtonId']")
	private WebElement submitOrderButtonLocator;

	@FindBy(xpath = "//div[@class='orderSummaryWrap']/h3")
	private WebElement orderSummaryLocator;

	@FindBy(id = "shipCompanyName")
	private WebElement shippingAddressNameLocator;

	@FindBy(id = "shipAddress1")
	private WebElement shippingAddressLane1Locator;

	@FindBy(id = "shipAddress2")
	private WebElement shippingAddressLane2Locator;

	@FindBy(id = "shipCity")
	private WebElement shippingAddressCityLocator;

	@FindBy(id = "shipZipcode")
	private WebElement shippingAddressZipLocator;

	@FindBy(xpath = "//strong[normalize-space(text())='Ship Via:'] /following-sibling::span")
	private WebElement shipMethodOptionLocator;

	@Step("Verify breadcrumb of Checkout Page")
	public CheckoutPageObjects verifyCheckoutBreadCrumb() {
		waiting.waitForVisibilityOfElement(checkoutBreadCrumpLocator, 20);
		Assert.assertTrue(checkoutBreadCrumpLocator.isDisplayed(), "checkout page breadcrump is not displayed");
		log.info("checkout page breadcrump is displayed");
		Assert.assertTrue(homeBreadCrumbLocator.isDisplayed(), "checkout page breadcrump is not displayed");
		log.info("checkout page breadcrump is displayed");
		return this;
	}

	@Step("Verify Checkout Page Name")
	public CheckoutPageObjects verifyCheckoutPageName() {
		waiting.waitTillPageLoads();
		waiting.waitForVisibilityOfElement(checkoutPageNameLocator, 20);
		Assert.assertTrue(checkoutPageNameLocator.isDisplayed(), "checkout page name is not displayed");
		log.info("checkout page name is displayed");
		return this;
	}

	@Step("Verify price precision in Checkout page")
	public CheckoutPageObjects verifyPricePrecisionInCheckoutPage(String pnNumber, String pricePrecesion) {

		WebElement specificItemPrice = getDriver().findElement(
				By.xpath("//strong[@class='formatPrice']"));

		Assert.assertEquals(
				specificItemPrice.getText().replace("$", "").replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1].length(), Integer.parseInt(pricePrecesion),
				"Price precision in not matched in Check out page");

		log.info("Verify Price precision in Checkout page");
		return this;
	}

	@Step("Click on next button")
	public CheckoutPageObjects clickOnNextButton() throws Exception {
		waiting.waitForVisibilityOfElement(nextButtonLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", nextButtonLocator);
		Thread.sleep(3000);

		/*
		 * Actions actions = new Actions(getDriver());
		 * 
		 * actions.moveToElement(nextButtonLocator).click().build().perform();
		 * Thread.sleep(1900);
		 */

		log.info("clicked on next button of checkout page");
		return this;
	}

	@Step("Click on previous Button")
	public CheckoutPageObjects clickOnPreviousButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(previousButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", previousButtonLocator);

		log.info("clicked on previous button of checkout page");
		Thread.sleep(1400);
		return this;

	}

	@Step("Enter email id {0}")
	public CheckoutPageObjects enterEmailId(String userName) throws Exception {
		Thread.sleep(1500);
		emailIdForShippingAddressLocator.clear();
		emailIdForShippingAddressLocator.sendKeys(userName);
		log.info("Entered email id as :" + userName);
		return this;
	}

	@Step("Enter ordered by {0}")
	public CheckoutPageObjects enterOrderedBy(String orderBy) {
		waiting.waitForVisibilityOfElement(orderedByLocator, 10);
		orderedByLocator.clear();
		orderedByLocator.sendKeys(orderBy);
		log.info("Order By :-" + orderBy + "has been entered");
		return this;

	}

	@Step("Enter purchase order number {0}")
	public CheckoutPageObjects enterPurchaseOrderNumber(String purchaseOrder) throws InterruptedException {
		Thread.sleep(20000);
		waiting.waitForVisibilityOfElement(purchaseOrderNumberLocator, 10);
		purchaseOrderNumberLocator.clear();
		purchaseOrderNumberLocator.sendKeys(purchaseOrder);

		/*
		 * purchaseOrderNumberLocator.click();
		 * purchaseOrderNumberLocator.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		 * purchaseOrderNumberLocator.sendKeys(Keys.DELETE);
		 * purchaseOrderNumberLocator.sendKeys(purchaseOrder);
		 */
		Thread.sleep(1500);
		log.info("purchase order number :-" + purchaseOrder + "has been entered");
		return this;
	}

	@Step("Select ship via {0}")
	public CheckoutPageObjects selectShipMethod(String shipVia) throws Exception {
		waiting.waitForVisibilityOfElement(shipMethodLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", shipMethodLocator);
		Thread.sleep(2000);

		log.info("clicked on Ship Method Of Order Details Page");

		getDriver()
				.findElement(By.xpath(
						"//button[@data-id='shipVia']/following-sibling::div//span[text()='" + shipVia + "']/.."))
				.click();

		log.info("Ship Via Option:-" + shipVia + " of Order Type has been selected");
		return this;
	}

	@Step("Verify ship method options:{0}")
	public CheckoutPageObjects verifyShipMethodOptions(String shipMethodOptions) throws InterruptedException {
		String[] expectedShipMethodOptions = shipMethodOptions.split(",");
		waiting.waitForVisibilityOfElement(shipMethodLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", shipMethodLocator);
		Thread.sleep(2000);
		for (int i = 0; i < shipMethodOptionsLocator.size(); i++) {
			Assert.assertEquals(shipMethodOptionsLocator.get(i).getText().trim(), expectedShipMethodOptions[i].trim(),
					"Ship Method Option is not " + expectedShipMethodOptions[i].trim() + ". It is :"
							+ shipMethodOptionsLocator.get(i).getText().trim());
			log.info("Verified ship method option :" + expectedShipMethodOptions[i]);
		}
		return this;
	}

	@Step("Select ship via {0}")
	public CheckoutPageObjects selectShipMethodForErrorScenarios(String shipVia) throws Exception {
		shipViaLocator.click();
		Thread.sleep(2000);
		try {
			getDriver().findElement(By.xpath("//div[@id='shipVia_chosen']/descendant::li[text()='" + shipVia + "']"))
					.click();
			log.info("Selected Ship Via :" + shipVia);
		} catch (NoSuchElementException e) {

		}
		return this;
	}

	@Step("Enter shipping instructions {0}")
	public CheckoutPageObjects enterShippingInstructions(String shippingInstructions) {
		waiting.waitForVisibilityOfElement(shippingInstructionsLocator, 10);

		shippingInstructionsLocator.clear();
		shippingInstructionsLocator.sendKeys(shippingInstructions);

		log.info("Shipping instructions :" + shippingInstructions + "has been entered");
		return this;
	}

	@Step("Enter order notes {0}")
	public CheckoutPageObjects enterOrderNotes(String orderNote) throws InterruptedException {
		waiting.waitForVisibilityOfElement(orderNotesLocator, 10);

		orderNotesLocator.clear();

		orderNotesLocator.sendKeys(orderNote);
		Thread.sleep(500);

		log.info("Order Note :" + orderNote + "has been entered");
		return this;
	}

	@Step("Verify whether name of the product in item details tab is {0}")
	public CheckoutPageObjects verifyNameOfTheProductInItemDetailsTab(String productName) {
		waiting.waitForVisibilityOfElement(productNameLocator, 15);
		Assert.assertEquals(productNameLocator.getText().trim(), productName);
		log.info("Verified product name :" + productName);
		return this;
	}

	@Step("Verify email address required error message is Please Enter Email Address.")
	public CheckoutPageObjects verifyEmailAddressRequiredErrorMessage() {
		Assert.assertEquals(errorMessageLocator.getText().trim(), "Please Enter Email Address.");
		log.info("Verified Email address error message");
		return this;
	}

	@Step("Verify email address required error message is {0}")
	public CheckoutPageObjects verifyErrorMessage(String errorMessage) {
		Assert.assertEquals(errorMessageLocator.getText().replace("\n", "").trim(), errorMessage);
		log.info("Verified error message :" + errorMessage);
		return this;
	}

	@Step("Enter phone number: {0}")
	public CheckoutPageObjects enterPhoneNumber(String phoneNumber) {
		waiting.waitForVisibilityOfElement(phoneNumberLocator, 10);
		phoneNumberLocator.clear();
		phoneNumberLocator.sendKeys(phoneNumber);
		log.info("Phone number is entered :" + phoneNumber);
		return this;
	}

	@Step("Enter shipping phone number: {0}")
	public CheckoutPageObjects enterShippingPhoneNumber(String phoneNumber) {
		waiting.waitForVisibilityOfElement(shipPhoneNumberLocator, 3);
		shipPhoneNumberLocator.clear();
		shipPhoneNumberLocator.sendKeys(phoneNumber);
		log.info("Shipping Phone number is entered :" + phoneNumber);
		return this;
	}

	@Step("Select Date : {0}")
	public CheckoutPageObjects selectRequiredByDate(String requiredByDate) throws InterruptedException {
		waiting.waitForVisibilityOfElement(requiredByDateLocator, 10);
		utilityMethods.scrollThePageToTop();
		requiredByDateLocator.click();
		Thread.sleep(1200);
		log.info("required by date :" + requiredByDate.trim());

		By calendarXpath = By
				.xpath("//table[@class='table-condensed']//td[not(contains(@class,'disabled day'))][text()='"
						+ requiredByDate.trim() + "']");

		getDriver().findElement(calendarXpath).click();

		Thread.sleep(1500);
		log.info("Selected Date : " + requiredByDate);
		return this;
	}

	@Step("Verify Billing Address Tab")
	public CheckoutPageObjects verifyBillingAddressTab() {
		waiting.waitForVisibilityOfElement(billingAddressTabLocator, 10);
		Assert.assertTrue(billingAddressTabLocator.isDisplayed(), "Billing Address Tab is not displayed");
		log.info("Billing Address Tab is displayed");
		return this;

	}

	@Step("Verify Shipping Address Tab")
	public CheckoutPageObjects verifyShippingAddressTab() {
		waiting.waitForVisibilityOfElement(shippingAddressTabLocator, 10);
		Assert.assertTrue(shippingAddressTabLocator.isDisplayed(), "Shipping Address Tab is not displayed");
		log.info("Shipping Address Tab is displayed");
		return this;

	}

	@Step("Verify Order Details Tab")
	public CheckoutPageObjects verifyOrderDetailstab() {
		waiting.waitForVisibilityOfElement(orderDetailsTabLocator, 10);

		Assert.assertTrue(orderDetailsTabLocator.isDisplayed(), "Order Details Tab is not displayed");

		log.info("Order Details Tab is displayed");
		return this;

	}

	@Step("Verify Order Review  Fields: {0}")
	public CheckoutPageObjects verifyOrderSummaryFields(String orderSummaryFields) {

		String[] expectedOrderSummaryFields = orderSummaryFields.split(",");

		waiting.waitForVisibilityOfElements(orderSummaryHeaderLocator, 10);
		for (int i = 0; i < orderSummaryHeaderLocator.size(); i++) {

			Assert.assertEquals(orderSummaryHeaderLocator.get(i).getText().trim(), expectedOrderSummaryFields[i].trim(),

					"Order Summary Fields are not " + expectedOrderSummaryFields[i].trim() + ". It is :"
							+ orderSummaryHeaderLocator.get(i).getText().trim());

			log.info("Order Summary Fields have been verified" + " with [Actual] and [Expected] value as " + "["
					+ orderSummaryHeaderLocator.get(i).getText().trim() + "] and " + "["
					+ expectedOrderSummaryFields[i].trim() + "]");

		}

		Assert.assertTrue(previousButtonLocator.isDisplayed(), "Prevoius button of Order Review is not displayed");

		log.info("Prevoius button of Order Review is displayed");

		Assert.assertTrue(submitOrderButtonLocator.isDisplayed(), "Submit button of Order Review is not displayed");

		log.info("Submit button of Order Review is displayed");

		Assert.assertTrue(orderSummaryLocator.isDisplayed(), "Order Summary Tab is not displayed");

		log.info("Order Summary Tab is displayed");

		return this;

	}

	@Step("Click on Submit Order button in Order Review Page")
	public CheckoutPageObjects clickOnSubmitOrderButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(submitOrderButtonLocator, 10);
		// Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", submitOrderButtonLocator);
		// submitOrderButtonLocator.click();
		Thread.sleep(3000);
		log.info("clicked on Submit Order button in Order Review Page");
		return this;

	}

	@Step("Verify Order Confirmation Page Title")
	public CheckoutPageObjects verifyOrderConfirmationPageTitle(String productName) {
		String actualTitle = getDriver().getTitle().trim();

		Assert.assertEquals(actualTitle, "Order Confirmation" + " | " + productName, "Title is wrong");

		log.info("Title of the Order Confirmation has been verified" + " with [Actual] and [Expected] value as " + "["
				+ actualTitle + "] and " + "[" + "Order Confirmation" + " | " + productName + "]");

		return this;

	}

	@Step("Verify Order Confirmation Page Name")
	public CheckoutPageObjects verifyOrderConfirmationPageName(String orderConfirmationPageName) {
		waiting.waitForVisibilityOfElement(orderConfirmationPageNameLocator, 10);
		Assert.assertEquals(orderConfirmationPageNameLocator.getText().trim().toLowerCase(),
				orderConfirmationPageName.trim().toLowerCase(),
				"Order Confirmation Page Name is not " + orderConfirmationPageName.trim().toLowerCase() + ". It is :"
						+ orderConfirmationPageNameLocator.getText().trim().toLowerCase());

		log.info("Order Confirmation Page Name has been verified" + " with [Actual] and [Expected] value as " + "["
				+ orderConfirmationPageNameLocator.getText().trim().toLowerCase() + "] and " + "["
				+ orderConfirmationPageName.trim().toLowerCase() + "]");

		return this;

	}

	@Step("Verify breadcrumb of Order Confirmation Page as {0}")
	public CheckoutPageObjects verifyOrderConfirmationBreadCrumb(String orderConfirmationPageName) {

		String lastBreadCrump = productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.trim();

		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);

		Assert.assertTrue(lastBreadCrump.equalsIgnoreCase(orderConfirmationPageName.trim()), "Invalid Breadcrumb");

		log.info("Order Confirmation Page BreadCrumb has been verified");
		return this;

	}

	@Step("Verify Order Confirmation Fields: {0},{1}")
	public CheckoutPageObjects verifyOrderConfirmationTableHeaderFields(String orderConfirmationFields,
			String orderConfirmationHeaderFields) {

		String[] expectedOrderConfirmationFields = orderConfirmationFields.split(",");

		waiting.waitForVisibilityOfElements(orderConfirmationFieldsLocator, 10);

		for (int i = 0; i < orderConfirmationFieldsLocator.size(); i++) {

			Assert.assertEquals(orderConfirmationFieldsLocator.get(i).getText().trim(),
					expectedOrderConfirmationFields[i].trim(),
					"Order Confirmation Fields are not " + expectedOrderConfirmationFields[i].trim() + ". It is :"
							+ orderConfirmationFieldsLocator.get(i).getText().trim());

			log.info("Order COnfirmation Fields have been verified" + " with [Actual] and [Expected] value as " + "["
					+ orderConfirmationFieldsLocator.get(i).getText().trim() + "] and " + "["
					+ expectedOrderConfirmationFields[i].trim() + "]");

		}

		String[] expectedorderConfirmationHeaderFields = orderConfirmationHeaderFields.split(",");

		for (int i = 0; i < orderConfirmationHeadingLocator.size(); i++) {

			Assert.assertEquals(orderConfirmationHeadingLocator.get(i).getText().trim(),
					expectedorderConfirmationHeaderFields[i].trim(),
					"Order Confirmation Fields are not " + expectedorderConfirmationHeaderFields[i].trim() + ". It is :"
							+ orderConfirmationHeadingLocator.get(i).getText().trim());

			log.info("Order COnfirmation Fields have been verified" + " with [Actual] and [Expected] value as " + "["
					+ orderConfirmationHeadingLocator.get(i).getText().trim() + "] and " + "["
					+ expectedorderConfirmationHeaderFields[i].trim() + "]");
		}

		return this;

	}

	@Step("Verification of Selected Ship method display in Order confirmation page:{0}")
	public CheckoutPageObjects verifyShipMethodDisplayInOrderConfirmationPage(String shipMethod) {
		waiting.waitForVisibilityOfElement(shipMethodOptionLocator, 10);
		Assert.assertEquals(shipMethodOptionLocator.getText().trim(), shipMethod.trim(),
				"Ship Method Option in Order Confirmation Page Name is not " + shipMethod.trim() + ". It is :"
						+ shipMethodOptionLocator.getText().trim());
		log.info("Verified Selected ship method display in order confirmation page :" + shipMethod);
		return this;
	}

	@Step("Verify Order Confirmation Success Message: {0}")
	public CheckoutPageObjects verifyOrderConfirmationSucessMessage(String orderConfiramtionSucessMessage) {
		waiting.waitForVisibilityOfElement(orderConfirmationSuccessMessageLocator, 10);
		Assert.assertEquals(orderConfirmationSuccessMessageLocator.getText().trim(),
				orderConfiramtionSucessMessage.trim(),
				"Order Confirmation Sucess Message is  not " + orderConfiramtionSucessMessage.trim() + ". It is :"
						+ orderConfirmationSuccessMessageLocator.getText().trim());

		log.info("Order COnfirmation Sucess Message have been verified" + " with [Actual] and [Expected] value as "
				+ "[" + orderConfirmationSuccessMessageLocator.getText().trim() + "] and " + "["
				+ orderConfiramtionSucessMessage.trim() + "]");

		return this;

	}

	@Step("Verify price precision in Order Confirmation page")
	public CheckoutPageObjects verifyPricePrecisionInConfirmationPage(String pnNumber, String pricePrecision) {

		WebElement specificItemPrice = getDriver().findElement(By.xpath("//p[contains(text(),'" + pnNumber
				+ "')]/ancestor::td//following-sibling::td[contains(@data-th,'Your')]"));

		Assert.assertEquals(specificItemPrice.getText().replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1], Integer.parseInt(pricePrecision),
				"Price precision in not matched in Order Confirmation page");

		log.info("Verify Price precision in Order Confirmation page :" + pricePrecision);
		return this;
	}

	@Step("Get order Number")
	public String getOrderNumber() {
		waiting.waitForVisibilityOfElement(orderNumberLocator, 10);
		String orderNumber = orderNumberLocator.getText().trim();
		log.info("Get the orderNumber :" + orderNumber);
		return orderNumber;
	}

	@Step("Verify Title of the Checkout Page: {0}")
	public CheckoutPageObjects verifyTitleOfCheckoutPage(String productName) {
		String actualTitle = getDriver().getTitle().trim();
		Assert.assertEquals(actualTitle, "Checkout" + " | " + productName, "Title is wrong");
		log.info("Title of the checkout page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ actualTitle + "] and " + "[" + "Checkout" + " | " + productName + "]");
		return this;
	}

	@Step("Verify Mandatory Fields Of Billing Address is prefilled with previous data : {0}")
	public CheckoutPageObjects verifyMandatoryFieldsOfBillingAddressPreFilledWithData(String mandatoryFieldsData,
			String billingAddressStateData, String billingAddressCountryData) {

		String[] expectedMandatoryFieldsData = mandatoryFieldsData.split(">");
		SoftAssert softAssert = new SoftAssert();

		for (int i = 0; i < billingAddressMadatoryFieldsLocator.size(); i++) {

			softAssert.assertEquals(billingAddressMadatoryFieldsLocator.get(i).getAttribute("value"),
					expectedMandatoryFieldsData[i].trim(),
					"Mandatory Fields Data of Billing Address is not" + expectedMandatoryFieldsData[i].trim()
							+ ". It is :" + billingAddressMadatoryFieldsLocator.get(i).getAttribute("value"));

			log.info("Mandatory Fields Of Billing Address has been verified"
					+ " with previous data [Actual] and [Expected] value as " + "["
					+ billingAddressMadatoryFieldsLocator.get(i).getAttribute("value") + "] and " + "["
					+ expectedMandatoryFieldsData[i].trim() + "]");

		}

		softAssert.assertEquals(billingAddressStateLocator.getAttribute("title").trim(), billingAddressStateData,
				"State Field Data of Billing Address is not" + billingAddressStateData + ". It is :"
						+ billingAddressStateLocator.getAttribute("title").trim());

		log.info("State Field Data of Billing Address has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + billingAddressStateLocator.getAttribute("title").trim() + "] and " + "[" + "New York" + "]");

		softAssert.assertEquals(billingAddressCountryLocator.getAttribute("title").trim(), billingAddressCountryData,

				"Country Field Data of Billing Address is not" + billingAddressCountryData + ". It is :"
						+ billingAddressCountryLocator.getAttribute("title").trim());

		log.info("Country Field Data of Billing Address has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + billingAddressCountryLocator.getAttribute("title").trim() + "] and " + "[" + "United States"
				+ "]");

		softAssert.assertAll();
		log.info("Verified Mandatory Fields Of Billing Address is prefilled with previous data");
		return this;
	}

	@Step("Verify Shipping Address Tab is Active after clicking on next button")
	public CheckoutPageObjects verifyWhetherShippingAddressTabIsActive() {

		Assert.assertEquals(shippingAddressTabLocator.getAttribute("class"), "active",
				"Shipping Address Tab is not active");
		log.info("Shipping Address Tab has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressTabLocator.getAttribute("class") + "] and " + "[" + "active" + "]");
		return this;

	}

	@Step("Verify Fields Of Shipping Address is prefilled with previous data : {0}")
	public CheckoutPageObjects verifyFieldsOfShippingAddressPreFilledWithData(String shippingAddressFieldsData,
			String shipToNameStateCountryFieldsDataOfShippingAddress) {

		String[] expectedShippingAddressFieldsData = shippingAddressFieldsData.split(">");

		String[] expectedShipToNameStateCountryFieldsDataOfShippingAddress = shipToNameStateCountryFieldsDataOfShippingAddress
				.split(">");

		SoftAssert softAssert = new SoftAssert();

		for (int i = 0; i < shippingAddressFieldsLocator.size(); i++) {

			softAssert.assertEquals(shippingAddressFieldsLocator.get(i).getAttribute("value").trim(),
					expectedShippingAddressFieldsData[i].trim(),
					"Shipping Address Fields Data is not" + expectedShippingAddressFieldsData[i].trim() + ". It is :"
							+ shippingAddressFieldsLocator.get(i).getAttribute("value"));

			log.info("Shipping Address Fields Data has been verified"
					+ " with prefilled data [Actual] and [Expected] value as " + "["
					+ shippingAddressFieldsLocator.get(i).getAttribute("value") + "] and " + "["
					+ expectedShippingAddressFieldsData[i].trim() + "]");

		}

		for (int i = 0; i < shipToNameStateCountryLocatorsOfShippingAddressTab.size(); i++) {

			softAssert.assertEquals(shipToNameStateCountryLocatorsOfShippingAddressTab.get(i).getAttribute("title"),
					expectedShipToNameStateCountryFieldsDataOfShippingAddress[i].trim(),
					"ShipToName,State and Country fields of Shipping Address are not"
							+ expectedShipToNameStateCountryFieldsDataOfShippingAddress[i].trim() + ". It is :"
							+ shipToNameStateCountryLocatorsOfShippingAddressTab.get(i).getAttribute("title"));

			log.info("ShipToName,State and Country fields of Shipping Address have been verified"
					+ " with prefilled data [Actual] and [Expected] value as " + "["
					+ shipToNameStateCountryLocatorsOfShippingAddressTab.get(i).getAttribute("title") + "] and " + "["
					+ expectedShipToNameStateCountryFieldsDataOfShippingAddress[i].trim() + "]");

		}

		softAssert.assertTrue(previousButtonLocator.isEnabled(), "Prevoius button of Shipping Address is not enabled");

		log.info("Prevoius button of Shipping Address is enabled");

		softAssert.assertTrue(nextButtonLocator.isEnabled(), "Nex button of Shipping Address is not enabled");

		log.info("Nex button of Shipping Address is enabled");

		softAssert.assertAll();

		log.info("Verified Fields Of Shipping Address is prefilled with previous data");
		return this;

	}

	@Step("Verify Order Details Tab is Active after clicking on next button")
	public CheckoutPageObjects verifyWhetherOrderDetailsTabIsActive() {

		Assert.assertEquals(orderDetailsTabLocator.getAttribute("class"), "active", "Order Details Tab is not active");

		log.info("Order Details Tab has been verified" + " with [Actual] and [Expected] value as " + "["
				+ orderDetailsTabLocator.getAttribute("class") + "] and " + "[" + "active" + "]");

		waiting.waitForVisibilityOfElement(byPOTabLocator, 10);

		Assert.assertEquals(byPOTabLocator.getAttribute("class"), "active", "by PO# Tab is not active");
		return this;

	}

	@Step("Verify Order Details Fields")
	public CheckoutPageObjects verifyOrderDetailsFields() {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(utilityMethods.isElementDisplayed(orderTypeLocator), "Order Type is not displayed");
		log.info("Order Type is displayed");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(orderedByLocator), "Ordered By is not displayed");
		log.info("Ordered By is displayed");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(purchaseOrderNumberLocator),
				"Purchase Order Number is not displayed");
		log.info("Purchase Order Number is displayed");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(requiredByDateLocator),
				"Required By Date is not displayed");
		log.info("Required By Date is displayed");
		/*softAssert.assertTrue(utilityMethods.isElementDisplayed(couponsCodeLocator), "Coupons Code is not displayed");
		log.info("Coupons Code is displayed");*/
		softAssert.assertTrue(utilityMethods.isElementDisplayed(shippingInstructionsLocator),
				"Shipping Instructions is not displayed");
		log.info("Shipping Instructions is displayed");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(orderNotesLocator), "order Notes is not displayed");
		log.info("order Notes is displayed");
		softAssert.assertAll();

		log.info("Order details fields are verified");
		return this;
	}

	@Step("Select order type {0}")
	public CheckoutPageObjects selectOrderType(String orderType) throws Exception {

		waiting.waitForVisibilityOfElement(orderTypeLocator, 10);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", orderTypeLocator);

		Thread.sleep(800);

		log.info("clicked on Order Type Locator of Order Details page");

		switch (orderType) {
		case "Credit card Payment":

			getDriver().findElement(By.xpath(
					"//button[@data-id='orderType']/following-sibling::div//span[text()=' Credit card Payment ']/.."))
					.click();

			log.info("Credit card Payment Option of Order Type has been selected");

			break;
		case "Purchase Order":
			getDriver().findElement(By
					.xpath("//button[@data-id='orderType']/following-sibling::div//span[text()=' Purchase Order ']/.."))
					.click();

			log.info("Purchase Order Option of Order Type has been selected");
			break;
		}
		return this;

	}

	@Step("Verify Mandatory Fields Of Order Details Page is prefilled with previous data : {0},{1},{2},{3},{4}")
	public CheckoutPageObjects verifyPreviouslyFilledDataOfAllMandatoryFieldsInOrderDetailsPage(String shipMethod,
			String orderBy, String purchaseOrderNumber, String shippingInstruction, String orderNotes) {

		Assert.assertEquals(shipMethodLocator.getAttribute("title"), shipMethod,
				"Ship Method Option Fields Data Of Order Details Page  is not prefilled with " + shipMethod
						+ ". It is :" + shipMethodLocator.getAttribute("title"));
		log.info("Ship Method Field Data Of Order Details Page has been verified"
				+ " with prefilled data [Actual] and [Expected] value as " + "["
				+ shipMethodLocator.getAttribute("title") + "] and " + "[" + shipMethod + "]");
		Assert.assertEquals(orderedByLocator.getAttribute("value"), orderBy,
				"Ordered By Fields Data Of Order Details Page  is not prefilled with " + orderBy + ". It is :"
						+ orderedByLocator.getAttribute("value"));
		log.info("Order By Field Data Of Order Details Page has been verified"
				+ " with prefilled data [Actual] and [Expected] value as " + "["
				+ orderedByLocator.getAttribute("value") + "] and " + "[" + orderBy + "]");
		Assert.assertEquals(purchaseOrderNumberLocator.getAttribute("value"), purchaseOrderNumber,
				"Purchase Order Number Fields Data Of Order Details Page  is not prefilled with " + purchaseOrderNumber
						+ ". It is :" + purchaseOrderNumberLocator.getAttribute("value"));
		log.info("Purchase Order Number Field Data Of Order Details Page has been verified"
				+ " with prefilled data [Actual] and [Expected] value as " + "["
				+ purchaseOrderNumberLocator.getAttribute("value") + "] and " + "[" + purchaseOrderNumber + "]");
		Assert.assertEquals(shippingInstructionsLocator.getAttribute("value"), shippingInstruction,
				"Shipping Instructions Fields Data Of Order Details Page  is not prefilled with " + shippingInstruction
						+ ". It is :" + shippingInstructionsLocator.getAttribute("value"));
		log.info("Shipping Instructions Field Data Of Order Details Page has been verified"
				+ " with prefilled data [Actual] and [Expected] value as " + "["
				+ shippingInstructionsLocator.getAttribute("value") + "] and " + "[" + shippingInstruction + "]");
		Assert.assertEquals(orderNotesLocator.getAttribute("value"), orderNotes,
				"Order Note Fields Data Of Order Details Page  is not prefilled with " + orderNotes + ". It is :"
						+ orderNotesLocator.getAttribute("value"));
		log.info("Order Notes Field Data Of Order Details Page has been verified"
				+ " with prefilled data [Actual] and [Expected] value as " + "["
				+ orderNotesLocator.getAttribute("value") + "] and " + "[" + orderNotes + "]");

		return this;

	}

	@Step("Select Required By Date of Order Details Page {0},{1}")
	public CheckoutPageObjects selectDate(String date, String monthYear) throws Exception {

		requiredByDateLocator.click();
		Thread.sleep(1000);

		WebElement monthYearLocator = getDriver().findElement(By.xpath("(//th[@class='datepicker-switch'])[1]"));
		WebElement next = getDriver().findElement(By.xpath("(//th[@class='next'])[1]"));
		// my[0]->Month,my[1]->Year
		String[] my = monthYear.split(" ");

		Calendar cal = Calendar.getInstance();

		if (Integer.parseInt(my[1]) >= cal.get(Calendar.YEAR) && Integer.parseInt(date) >= cal.get(Calendar.DATE)) {
			while (!(monthYearLocator.getText().trim().equalsIgnoreCase(monthYear))) {
				next.click();
			}
		} else
			Assert.assertFalse(true, "Invalid required by date");

		Thread.sleep(1000);

		getDriver().findElement(By.xpath("//td[@class='day' and text()='" + date + "']")).click();

		log.info("Required By date:-" + date + monthYear + " has been selected");

		return this;
	}

	@Step("Verify error message if mandatory fields leave blank in order details page : {0}")
	public CheckoutPageObjects verifyErrorMessgaeIfMandatoryFieldsLeaveBlankInOrderDetailsPage(String errorMessage) {
		waiting.waitForVisibilityOfElement(errorMessageLocatorOfOrderDetailsPage, 10);

		Assert.assertEquals(errorMessageLocatorOfOrderDetailsPage.getText().trim().replace("×", "").replace("\n", ""),
				errorMessage.trim(), "Error Message is not " + errorMessage.trim() + ". It is :"
						+ errorMessageLocatorOfOrderDetailsPage.getText().trim().replace("×", "").replace("\n", ""));
		log.info("Error Message Of Order Details Page has been verified" + " with [Actual] and [Expected] value as "
				+ "[" + errorMessageLocatorOfOrderDetailsPage.getText().trim().replace("×", "").replace("\n", "")
				+ "] and " + "[" + errorMessage.trim() + "]");

		return this;

	}

	@Step("Verify changed shipping address fields in checkout page of Shipping Address section")
	public CheckoutPageObjects verifyChangedShippingAddressFields() {
		SoftAssert softAssert = new SoftAssert();

		String[] checkOutShippingAddressData = data.checkOutShippingAddressData().split(">");
		softAssert.assertEquals(shippingAddressNameLocator.getAttribute("value").trim(),
				checkOutShippingAddressData[0]);
		log.info("Name field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressNameLocator.getAttribute("value").trim().replace("×", "").replace("\n", "") + "] and "
				+ "[" + checkOutShippingAddressData[0] + "]");
		softAssert.assertEquals(shippingAddressLane1Locator.getAttribute("value").trim(),
				checkOutShippingAddressData[1]);
		log.info("Address1  field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressLane1Locator.getAttribute("value").trim().trim().replace("×", "").replace("\n", "")
				+ "] and " + "[" + checkOutShippingAddressData[1] + "]");
		softAssert.assertEquals(shippingAddressLane2Locator.getAttribute("value").trim(),
				checkOutShippingAddressData[2]);
		log.info("Address2  field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressLane2Locator.getAttribute("value").trim().trim().replace("×", "").replace("\n", "")
				+ "] and " + "[" + checkOutShippingAddressData[2] + "]");
		softAssert.assertEquals(shippingAddressCityLocator.getAttribute("value").trim(),
				checkOutShippingAddressData[3]);
		log.info("City field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressCityLocator.getAttribute("value").trim().trim().replace("×", "").replace("\n", "")
				+ "] and " + "[" + checkOutShippingAddressData[3] + "]");
		softAssert.assertEquals(shippingAddressZipLocator.getAttribute("value").trim(), checkOutShippingAddressData[4]);
		log.info("Zip/Postal Code field has been verified" + " with [Actual] and [Expected] value as " + "["
				+ shippingAddressZipLocator.getAttribute("value").trim().trim().replace("×", "").replace("\n", "")
				+ "] and " + "[" + checkOutShippingAddressData[4] + "]");
		softAssert.assertAll();
		log.info("Shipping address fields are verified");
		return this;

	}

	
	public CheckoutPageObjects scrollThePage() throws InterruptedException {
		utilityMethods.scrollTillWebElement(checkoutPageNameLocator);
		Thread.sleep(500);
		return this;
	}

}
