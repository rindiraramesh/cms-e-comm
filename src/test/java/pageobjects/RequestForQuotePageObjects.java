package pageobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class RequestForQuotePageObjects extends PageInitializer {

	UtilityMethods util = new UtilityMethods(getDriver());

	WaitingMethods wait = new WaitingMethods(getDriver());

	SoftAssert soft = new SoftAssert();

	Logger log = Logger.getLogger(RequestForQuotePageObjects.class);

	@FindBy(id = "rfnpName")
	public WebElement rfqFirstNameLocator;
	
	@FindBy(xpath = "//button[@data-id='shipToAddress']")
	public WebElement rfqAddressLocator;

	/*@FindBy(id = "rfnpName2")
	public WebElement rfqLastNameLocator;*/

	@FindBy(id = "rfnpPhone")
	public WebElement rfqPhoneLocator;

	@FindBy(id = "rfnpEmail")
	public WebElement rfqEmailLocator;

	@FindBy(xpath = ".//button[@value='Add Row']")
	public WebElement rfqAddRowButtonLocator;

	@FindBy(xpath = "//input[@id='reqDate']")
	public WebElement rfqDateLocator;

	@FindBy(id = "orderNotes")
	public WebElement rfqcommentsLocator;

	@FindBy(id = "submitBtn")
	public WebElement rfqSubmitQuoteRequestButtonLocator;

	@FindBy(css = ".cimm_page-title")
	public WebElement rfqPageNameLocator;

	@FindBy(css = ".alert.alert-danger")
	public WebElement rfqErrorMessageLocator;

	@FindBy(css = ".alert.alert-danger")
	public List<WebElement> rfqBreadCrumLocator;

	@FindBy(id = "PN0")
	public WebElement pN0Locator;

	@FindBy(id = "MPN0")
	public WebElement mPN0Locator;

	@FindBy(id = "BN0")
	public WebElement bN00Locator;

	@FindBy(id = "QTY0")
	public WebElement qTY0Locator;
	
	@FindBy(id = "DESC0")
	public WebElement dESC0Locator;
	
	@FindBy(id = "UOM0")
	public WebElement uOM0Locator;

	@FindBy(css = ".modal-header>h4>span")
	public WebElement rfqPopUpHeaderLocator;

	@FindBy(css = ".bootbox-body")
	public WebElement rfqPopUpMessageLocator;

	@FindBy(css = ".modal-footer>button")
	public WebElement rfqPopUpOkButtonLocator;

	@FindBy(xpath = "//*[@id='idOfRowToInsertBefore']/td/button[@value='Add Row']")
	public WebElement addRowButtonLocator;

	@FindBy(xpath = "//*[@id='idOfRowToInsertBefore']/td/button[@value='Remove Row']")
	public WebElement removeRowButtonLocator;

	@FindBy(xpath = "//*[@id='reqForQuoteTableId']/tbody/tr[6]/td/input")
	public WebElement newTabelRowLocator;
	
	@FindBy(xpath = "//input[contains(@id,'MPN')]")
	public List<WebElement> tableRowLocator;

	@Step("Verify RFQ page details ")
	public RequestForQuotePageObjects verifyDisplayOfFields(String reqForQuoteTabelheader) {
		wait.waitForVisibilityOfElement(rfqFirstNameLocator, 10);

		soft.assertTrue(util.isElementDisplayed(rfqFirstNameLocator),"Name issue");
		log.info("FirstName is verified for display");

		/*soft.assertTrue(util.isElementDisplayed(rfqLastNameLocator),"LastName issue");
		log.info("LastName is verified for display");*/

		soft.assertTrue(util.isElementDisplayed(rfqPhoneLocator),"Phone Number issue");
		log.info("Phone Number is verified for display");

		soft.assertTrue(util.isElementDisplayed(rfqEmailLocator),"Email issue");
		log.info("Email is verified for display");

		soft.assertTrue(util.isElementDisplayed(rfqAddRowButtonLocator),"Add Row button issue");
		log.info("Add Row button is verified for display");

		soft.assertTrue(util.isElementDisplayed(rfqDateLocator),"Date issue");
		log.info("Date is verified for display");

		soft.assertTrue(util.isElementDisplayed(rfqcommentsLocator),"Comments issue");
		log.info("Comments is verified for display");

		soft.assertTrue(util.isElementDisplayed(rfqSubmitQuoteRequestButtonLocator),"Submit Quote Request Button issue");
		log.info("Submit Quote Request Button is verified for display");

		List<WebElement> allReqForQuoteTabelHeader = getDriver()
				.findElements(By.xpath("//*[@id='reqForQuoteTableId']//th"));
		String[] tabelexepected = reqForQuoteTabelheader.split(">");
		String[] tabelActual = new String[9];
		for (int i = 0; i < allReqForQuoteTabelHeader.size(); i++) {
			tabelActual[i] = allReqForQuoteTabelHeader.get(i).getText();
			// System.out.println(allReqForQuoteTabelHeader.get(i).getText());
			soft.assertEquals(tabelActual[i], tabelexepected[i], "Table is not as per the expected :");
			log.info("RFQ table header is verified :" + tabelexepected[i]);
		}
		soft.assertAll();
		return this;
	}

	@Step("Verify bread crum details")
	public RequestForQuotePageObjects verifyReqForQuoteBreadCrum(String breadcrum) {
		String[] breadCrumExepected = breadcrum.split(">");
		String[] breadCrumActual = new String[2];
		for (int i = 0; i < rfqBreadCrumLocator.size(); i++) {
			breadCrumActual[i] = rfqBreadCrumLocator.get(i).getText();
			// System.out.println(allReqForQuoteTabelHeader.get(i).getText());
			soft.assertEquals(breadCrumActual[i], breadCrumExepected[i], "Bread crum is not correct");
		}
		soft.assertAll();
		return this;
	}

	@Step("Verify RFQ page title")
	public RequestForQuotePageObjects verifyReqForQuoteTitle(String productName) {

		Assert.assertEquals(getDriver().getTitle(), "Request for Quote" + " | " + productName, "Title is wrong");
		log.info("Title of the RFQ page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle() + "] and " + "[" + "Request for Quote" + " | " + productName + "]");
		return this;
	}

	@Step("Verify RFQ page url")
	public RequestForQuotePageObjects verifyReqForQuoteUrl(String url) {
Assert.assertTrue(getDriver().getCurrentUrl().contains("Quote"), "Url of the page didnt matched :");
		log.info("Url of the page is verified ");
		return this;
	}

	@Step("Verify RFQ page name")
	public RequestForQuotePageObjects verifyReqForQuotePageName() {
		Assert.assertEquals(rfqPageNameLocator.getText().toUpperCase(), "REQUEST FOR QUOTE",
				"Page name didnt matched :");
		log.info("Page name is verified ");
		return this;
	}

	@Step("Enter PN: {0}")
	public RequestForQuotePageObjects enterPartNumber(String PN) throws InterruptedException {
		wait.waitForVisibilityOfElement(pN0Locator, 5);
		pN0Locator.sendKeys(PN);
		Thread.sleep(1500);
		log.info("Entered to PN field " + PN);
		return this;
	}
	
	/*@Step("Enter Qty: {0}")
	public RequestForQuotePageObjects enterQty(String Qty) {
		wait.waitForVisibilityOfElement(qTY0Locator, 5);
		qTY0Locator.sendKeys(Qty);
		log.info("Entered to Qty field " + Qty);
		return this;
	}*/
	
	@Step("Enter Short Description: {0}")
	public RequestForQuotePageObjects enterShortDescription(String desc) {
		wait.waitForVisibilityOfElement(dESC0Locator, 5);
		dESC0Locator.sendKeys(desc);
		log.info("Entered to Short Description field " + desc);
		return this;
	}
	
	@Step("Enter UOM: {0}")
	public RequestForQuotePageObjects enterUOM(String uom) {
		wait.waitForVisibilityOfElement(uOM0Locator, 5);
		uOM0Locator.sendKeys(uom);
		log.info("Entered to UOM field " + uom);
		return this;
	}


	@Step("Clear PN")
	public RequestForQuotePageObjects clearPartNumber() {
		wait.waitForVisibilityOfElement(pN0Locator, 5);
		pN0Locator.clear();
		log.info("Cleared PN field ");
		return this;
	}

	@Step("Enter MPN: {0}")
	public RequestForQuotePageObjects enterManuPartNumber(String MPN) {
		wait.waitForVisibilityOfElement(mPN0Locator, 5);
		mPN0Locator.sendKeys(MPN);
		log.info("Entered to MPN field " + MPN);
		return this;
	}

	@Step("Clear MPN")
	public RequestForQuotePageObjects clearManuPartNumber() {
		wait.waitForVisibilityOfElement(mPN0Locator, 5);
		mPN0Locator.clear();
		log.info("Cleared MPN field ");
		return this;
	}

	@Step("Enter Brand: {0}")
	public RequestForQuotePageObjects enterBrandMfrName(String brand) {
		wait.waitForVisibilityOfElement(bN00Locator, 5);
		bN00Locator.sendKeys(brand);
		log.info("Entered to brand field " + brand);
		return this;
	}

	@Step("Clear brand")
	public RequestForQuotePageObjects clearBrandMfrName() {
		wait.waitForVisibilityOfElement(bN00Locator, 5);
		bN00Locator.clear();
		log.info("Cleared brand field ");
		return this;
	}

	@Step("Enter firstName: {0}")
	public RequestForQuotePageObjects enterFirstName(String firstName) {
		wait.waitForVisibilityOfElement(rfqFirstNameLocator, 5);
		rfqFirstNameLocator.sendKeys(firstName);
		log.info("Entered to First Name field " + firstName);
		return this;
	}

	/*@Step("Enter lastName: {0}")
	public RequestForQuotePageObjects enterLastName(String lastName) {
		wait.waitForVisibilityOfElement(rfqLastNameLocator, 5);
		rfqLastNameLocator.sendKeys(lastName);
		log.info("Entered to lastName field " + lastName);
		return this;
	}*/

	@Step("Enter phone: {0}")
	public RequestForQuotePageObjects enterPhone(String phone) {
		wait.waitForVisibilityOfElement(rfqPhoneLocator, 5);
		rfqPhoneLocator.sendKeys(phone);
		log.info("Entered to phone field " + phone);
		return this;
	}

	@Step("Clear firstName")
	public RequestForQuotePageObjects clearFirstName() {
		wait.waitForVisibilityOfElement(rfqFirstNameLocator, 5);
		rfqFirstNameLocator.clear();
		log.info("Cleared First Name field");
		return this;
	}

	/*@Step("Clear lastName")
	public RequestForQuotePageObjects clearLastName() {
		wait.waitForVisibilityOfElement(rfqLastNameLocator, 5);
		rfqLastNameLocator.clear();
		log.info("Cleared to lastName field");
		return this;
	}*/

	@Step("Clear phone")
	public RequestForQuotePageObjects clearPhone() {
		wait.waitForVisibilityOfElement(rfqPhoneLocator, 5);
		rfqPhoneLocator.clear();
		log.info("Cleared to phone field ");
		return this;
	}

	@Step("Enter email: {0}")
	public RequestForQuotePageObjects enterEmail(String email) {
		wait.waitForVisibilityOfElement(rfqEmailLocator, 5);
		rfqEmailLocator.sendKeys(email);
		log.info("Entered to email field " + email);
		return this;
	}

	@Step("Clear email")
	public RequestForQuotePageObjects clearEmail() {
		wait.waitForVisibilityOfElement(rfqEmailLocator, 5);
		rfqEmailLocator.clear();
		log.info("cleared to email field ");
		return this;
	}
	
	@Step("Enter qty")
	public RequestForQuotePageObjects enterQty(String qty) throws InterruptedException {
		Thread.sleep(1500);
		qTY0Locator.clear();
		qTY0Locator.sendKeys(qty);
		log.info("Entered qty :"+qty);
		return this;
	}
	
	@Step("Enter product info with qty")
	public RequestForQuotePageObjects enterProductInfoAndQty(String productInfo) throws InterruptedException {
		pN0Locator.sendKeys(productInfo);
		Thread.sleep(1500);
		qTY0Locator.clear();
		qTY0Locator.sendKeys("1");
		log.info("Entered product info with qty");
		return this;
	}

	@Step("Click Submit Quote Request button")
	public RequestForQuotePageObjects clickSubmitQuoteRequestButton() throws InterruptedException {
		Thread.sleep(3000);
		wait.waitForVisibilityOfElement(rfqSubmitQuoteRequestButtonLocator, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rfqSubmitQuoteRequestButtonLocator);
		log.info("Clicked on Submit Quote Request Button ");
		return this;
	}

	@Step("Update mandatory fields if required")
	public RequestForQuotePageObjects updateMadatoryFields() {
		wait.waitForVisibilityOfElement(rfqPhoneLocator, 5);
		/*if (rfqLastNameLocator.getAttribute("value").isEmpty())
			enterLastName("Automation");
		log.info("Entered LastName since it was not populated :" + rfqLastNameLocator.getAttribute("value"));*/
		if (rfqFirstNameLocator.getAttribute("value").isEmpty())
			enterFirstName("Test Automation");
		log.info("Entered First Name since it was not populated :" + rfqFirstNameLocator.getAttribute("value"));
		if (rfqPhoneLocator.getAttribute("value").isEmpty())
			enterPhone("5125852358");
		log.info("Entered Phone since it was not populated :" + rfqPhoneLocator.getAttribute("value"));
		if (rfqEmailLocator.getAttribute("value").isEmpty())
			enterEmail("test.automation@unilogcorp.com");
		log.info("Entered Email since it was not populated :" + rfqEmailLocator.getAttribute("value"));
		return this;
	}

	@Step("Verify mandatory fields")
	public RequestForQuotePageObjects verifyMadatoryFieldsPopulated() {
		wait.waitForVisibilityOfElement(rfqFirstNameLocator, 5);

		/*soft.assertFalse(rfqAddressLocator.getAttribute("value").isEmpty(), "Last Name field is not populated");
		log.info("Verified LastName field ");*/

		soft.assertFalse(rfqFirstNameLocator.getAttribute("value").isEmpty(), "First Name field is not populated");
		log.info("Verified FirstName field ");

		/*
		 * soft.assertFalse(rfqPhoneLocator.getAttribute("value").isEmpty(),
		 * "Phone field is not populated"); log.info("Verified Phone field ");
		 */

		soft.assertFalse(rfqEmailLocator.getAttribute("value").isEmpty(), "Email field is not populated");
		log.info("Verified Email field ");

		soft.assertAll();

		return this;
	}

	@Step("Verify error messages for mandatory fields")
	public RequestForQuotePageObjects verifyErrorMessageMandatoryFields(String ErrorMsg) {

		soft.assertEquals(rfqErrorMessageLocator.getText().substring(1).trim(), ErrorMsg,
				"Error message is not matched :");
		log.info("Error message is verified :" + ErrorMsg);

		soft.assertAll();
		return this;
	}

	@Step("Verify error messages when no product information is entered")
	public RequestForQuotePageObjects verifyErrorForNoProductInformation(String errorMsg) {
		Assert.assertEquals(rfqErrorMessageLocator.getText().substring(1).trim(), errorMsg,
				"Error message is not matched when no producr info is entered");
		log.info("Error message is verified when no product information is entered");
		return this;
	}
	
	@Step("Verify error messages when no product information is entered and Qty is entered")
	public RequestForQuotePageObjects verifyErrorForNoProductInformationAndQtyEntererd(String errorMsg) throws InterruptedException {
		Thread.sleep(2500);
		Assert.assertEquals(rfqErrorMessageLocator.getText().substring(1).trim(), errorMsg,
				"Error message is not matched when no product information is entered and Qty is entered");
		log.info("Error message is verified when no product information is entered and Qty is entered");
		return this;
	}
	
	@Step("Verify error messages when product information is entered and Qty is not entered")
	public RequestForQuotePageObjects verifyErrorForProductInformationEnteredAndQtyNotEntererd(String errorMsg) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(rfqErrorMessageLocator.getText().substring(1).trim(), errorMsg,
				"Error message is not matched when product information is entered and Qty is not entered");
		log.info("Error message is verified when product information is entered and Qty is not entered");
		return this;
	}

	@Step("Verify RFQ submission using PN, Brand and MPN")
	public RequestForQuotePageObjects verifyRfQSubmissionMessage() throws Exception {
		wait.waitForVisibilityOfElement(rfqPopUpHeaderLocator, 10);
		soft.assertEquals(rfqPopUpHeaderLocator.getText().trim(), "Success", "header in popup didnt matched :");
		soft.assertEquals(rfqPopUpMessageLocator.getText().trim(), "Request has been sent successfully.",
				"Message didn't matched");
		soft.assertTrue(util.isElementDisplayed(rfqPopUpOkButtonLocator));
		rfqPopUpOkButtonLocator.click();
		log.info("Clicked on ok Button ");
		Thread.sleep(500);
		soft.assertAll();
		return this;
	}

	@Step("Verify adding row and removing row in tabel")
	public RequestForQuotePageObjects verifyAddRowInRequestForQuoteTabel() {
		try {
			Thread.sleep(2500);
			int currentRows = tableRowLocator.size();
			addRowButtonLocator.click();
			log.info("Clicked on Add row Button ");
			Thread.sleep(1500);
			soft.assertTrue(tableRowLocator.size()>currentRows, "new Table row is not displayed :");
			soft.assertTrue(util.isElementDisplayed(removeRowButtonLocator), "remove row button is not displayed :");

			removeRowButtonLocator.click();
			log.info("Clicked on Remove row Button ");
			soft.assertTrue(tableRowLocator.size()==currentRows, "Row is not removed :");

		} catch (Throwable t) {
			log.info("Error while performing adding/removing row in tabel " + t.getMessage());
			soft.fail("Error while performing adding/removing row in tabel");
		}
		soft.assertAll();
		return this;
	}

	@Step("Verify required by date field")
	public RequestForQuotePageObjects verifyRequiredByDateField() throws Exception {
		wait.waitTillPageLoads();
		
		soft.assertNotNull(rfqDateLocator.getAttribute("readonly"), "Date field is not read only :");
		Date date = new Date();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

		// simpleDateFormat.applyPattern("dd");
		simpleDateFormat.applyPattern("dd");

		String day = simpleDateFormat.format(date);

		log.info("day:---> " + day);
		simpleDateFormat.applyPattern("MM");
		String month = simpleDateFormat.format(date);

		log.info("month:---> " + month);
		simpleDateFormat.applyPattern("yyyy");
		String year = simpleDateFormat.format(date);

		log.info("year:---> " + year);
		simpleDateFormat.applyPattern("MM/dd/yyyy");
		String currentDay = simpleDateFormat.format(date);

		log.info("currentDay :---> " + currentDay);

		Thread.sleep(2250);
		rfqDateLocator.click();
		Thread.sleep(500);
		try {

			WebElement Currentdt = getDriver()
					.findElement(By.xpath("//td[text()='" + day + "' and (@class='day' or @class='active day')]"));
			soft.assertTrue(moveToElementAndClick(Currentdt));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rfqDateLocator);
			log.info("Selected the date :" + day);
		} catch (Exception e) {

			simpleDateFormat.applyPattern("d");
			WebElement Currentdt = getDriver()
					.findElement(By.xpath("//td[text()='" + day + "' and (@class='day' or @class='active day')]"));
			soft.assertTrue(moveToElementAndClick(Currentdt));
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rfqDateLocator);
			log.info("Selected the date :" + day);
		}
		util.moveToElementAndClick(rfqDateLocator);
		for (int i = 1; i < Integer.parseInt(day); i++) {

			WebElement previoustdt = getDriver()
					.findElement(By.xpath("//td[text()='" + (i) + "' and (@class='disabled day')]"));
			moveToElementAndClick(previoustdt);
			soft.assertEquals(rfqDateLocator.getAttribute("value"), currentDay, "Able to click on disabled day :");
			rfqDateLocator.click();
			log.info("Tested for previous date :" + i);
		}
		util.moveToElementAndClick(rfqDateLocator);
		for (int i = Integer.parseInt(day); i < 28; i++) {

			WebElement activeNexttdt = getDriver()
					.findElement(By.xpath("//td[text()='" + (i + 1) + "' and (@class='day' or @class='active day')]"));
			soft.assertTrue(moveToElementAndClick(activeNexttdt), "Not able to select active future date's :");
			rfqDateLocator.click();
			log.info("Tested for Next date :" + i);
		}
		soft.assertAll();
		return this;
	}

	public boolean moveToElementAndClick(WebElement element) {

		try {
			Actions actions = new Actions(getDriver());
			actions.moveToElement(element).click().build().perform();
			Thread.sleep(1500);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Step("VerifyQty field in tabel with special characters")
	public RequestForQuotePageObjects verifyQtyFieldWithSpecialCharacters(String qty) throws Exception {
		String[] inputValues = qty.split("a");
		for (int i = 0; i < inputValues.length; i++) {
			qTY0Locator.sendKeys(inputValues[i]);
			log.info("Qty field is tested for :" + inputValues[i]);
			Thread.sleep(250);
			rfqEmailLocator.click();
			Thread.sleep(250);
			soft.assertEquals(qTY0Locator.getAttribute("value"), "", "Able to enter special character :");
			qTY0Locator.clear();
			log.info("Qty filed is cleared ");
		}
		soft.assertAll();
		return this;
	}

}
