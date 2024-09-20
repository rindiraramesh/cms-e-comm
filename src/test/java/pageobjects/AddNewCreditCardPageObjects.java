package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class AddNewCreditCardPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(AddNewCreditCardPageObjects.class);

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	@FindBy(xpath = "//div[@class='cimm_formLabel']/label")
	private List<WebElement> creditCardLabelLocator;

	@FindBy(id = "addPcard")
	private WebElement addNewCreditCardButtonLocator;

	@FindBy(xpath = "//ul[@class='cimm_breadcrumbs']//a[@href='/Welcome']")
	private WebElement homeIconBreadCrumbLocator;

	@FindBy(xpath = "//ul[@class='cimm_breadcrumbs']/li")
	public List<WebElement> breadCrumbs;

	@FindBy(id = "nickName")
	private WebElement nickNameTextboxLocator;

	/*
	 * @FindBy(xpath =
	 * "//div[@class='cimm_formTbx']//em[contains(text(),'Please')]") private
	 * List<WebElement> errorMessageLocator;
	 */

	@FindBy(xpath = "//em[@class='required' and not(text())='']")
	private List<WebElement> errorMessageLocator;

	@FindBy(id = "cardHolder")
	private WebElement cardHolderTextboxLocator;

	@FindBy(id = "cardAddress")
	private WebElement streetAddressTextboxLocator;

	@FindBy(id = "postalCode")
	private WebElement postalCodeTextboxLocator;

	@FindBy(xpath = "//table[@id='cardInfo']//th")
	private List<WebElement> tableHeaderLocator;

	@Step("verify Add New Credit Card page")
	public AddNewCreditCardPageObjects verifyAddNewCreditCardPage(String creditCardLabelName) throws Exception {
		log = Logger.getLogger("verifyAddNewCreditCardPage");
		waiting.waitForVisibilityOfElements(creditCardLabelLocator, 10);
		String[] expectedCreditCardLabelName = creditCardLabelName.split(",");
		for (int i = 0; i < creditCardLabelLocator.size(); i++) {
			Assert.assertEquals(creditCardLabelLocator.get(i).getText().replace("*", "").trim().toLowerCase(),
					expectedCreditCardLabelName[i].trim().toLowerCase(),
					"Add New Credit Card Page Textbox Label is not :"
							+ expectedCreditCardLabelName[i].trim().toLowerCase() + ". It is :"
							+ creditCardLabelLocator.get(i).getText().replace("*", "").trim().toLowerCase());

			log.info("Add New Credit Card Page Textbox Label has been verified"
					+ " with [Actual] and [Expected] value as " + "["
					+ creditCardLabelLocator.get(i).getText().replace("*", "").trim().toLowerCase() + "] and " + "["
					+ expectedCreditCardLabelName[i].trim().toLowerCase() + "]");
		}
		Assert.assertTrue(addNewCreditCardButtonLocator.isDisplayed(), "Add New Credit Card Button is not displayed.");
		log.info("Add New Credit Card Button is displayed.");
		return this;
	}

	@Step("Verify Add New Credit Card Page Title:{0}")
	public AddNewCreditCardPageObjects verifyAddNewCreditCardPageTitle(String productName) {
		log = Logger.getLogger("verifyAddNewCreditCardPageTitle");
		String addNewCreditCardPageTitle = getDriver().getTitle().trim();
		Assert.assertEquals(addNewCreditCardPageTitle, "Add New Credit Card" + " - " + productName, "Title is wrong");
		log.info("Title of the Add New Credit Page  has been verified" + " with [Actual] and [Expected] value as " + "["
				+ addNewCreditCardPageTitle + "] and " + "[" + "Add New Credit Card" + " - " + productName + "]");
		return this;
	}

	@Step("Verify Error Message when mandatory fields leave blank :{0}")
	public AddNewCreditCardPageObjects verifyErrorMessage(String errorMessage) {
		log = Logger.getLogger("verifyErrorMessage");
		waiting.waitForVisibilityOfElements(errorMessageLocator, 10);
		for (int i = 0; i < errorMessageLocator.size(); i++) {
			Assert.assertEquals(errorMessageLocator.get(i).getText().trim(), errorMessage.split("\n")[i],
					"Error Message without entering of the mandatory fields is not : "
							+ errorMessageLocator.get(i).getText().trim() + ". It is : "
							+ errorMessage.split("\n")[i].trim());
			log.info("Error Message has been verified" + " with [Actual] and [Expected] value as " + "["
					+ errorMessageLocator.get(i).getText().trim() + "] and " + "[" + errorMessage.split("\n")[i] + "]");
		}
		return this;

	}

	@Step("Click On Add New Credit Card Button.")
	public AddNewCreditCardPageObjects clickOnAddNewCreditCardButton() throws InterruptedException {
		log = Logger.getLogger("clickOnAddNewCreditCardButton");
		waiting.waitForVisibilityOfElement(addNewCreditCardButtonLocator, 10);
		addNewCreditCardButtonLocator.click();
		Thread.sleep(1200);
		log.info("Clicked On Add New Credit Card Button.");
		return this;
	}

	@Step("Enter Nick Name :{0}")
	public AddNewCreditCardPageObjects enterNickName(String nickName) {
		log = Logger.getLogger("enterNickName");
		waiting.waitForVisibilityOfElement(nickNameTextboxLocator, 10);
		nickNameTextboxLocator.clear();
		nickNameTextboxLocator.sendKeys(nickName);
		log.info("Entered Nick Name : " + nickName);
		return this;
	}

	@Step("Enter Card Holder Name :{0}")
	public AddNewCreditCardPageObjects enterCardHolderName(String cardHolderName) {
		log = Logger.getLogger("enterCardHolderName");
		waiting.waitForVisibilityOfElement(cardHolderTextboxLocator, 10);
		cardHolderTextboxLocator.clear();
		cardHolderTextboxLocator.sendKeys(cardHolderName);
		log.info("Entered Card Holder Name as  : " + cardHolderName);
		return this;
	}

	@Step("Enter Street Address :{0}")
	public AddNewCreditCardPageObjects enterStreetAddress(String streetAddress) {
		log = Logger.getLogger("enterStreetAddress");
		waiting.waitForVisibilityOfElement(streetAddressTextboxLocator, 10);
		streetAddressTextboxLocator.clear();
		streetAddressTextboxLocator.sendKeys(streetAddress);
		log.info("Entered Street Address  as  : " + streetAddress);
		return this;
	}

	@Step("Enter Postal Code :{0}")
	public AddNewCreditCardPageObjects enterPostalCode(String postalCode) {
		log = Logger.getLogger("enterPostalCode");
		waiting.waitForVisibilityOfElement(postalCodeTextboxLocator, 10);
		postalCodeTextboxLocator.clear();
		postalCodeTextboxLocator.sendKeys(postalCode);
		log.info("Entered Postal Code   as  : " + postalCode);
		return this;
	}

	@Step("Verify Card Info Table Header:{0}")
	public AddNewCreditCardPageObjects verifyCardInfoTableHeader(String tableHeader) {
		log = Logger.getLogger("verifyCardInfoTableHeader");
		waiting.waitForVisibilityOfElements(tableHeaderLocator, 10);
		String[] expectedTableHeader = tableHeader.split(",");
		for (int i = 0; i < tableHeaderLocator.size(); i++) {
			Assert.assertEquals(tableHeaderLocator.get(i).getText().trim().toLowerCase(),
					expectedTableHeader[i].trim().toLowerCase(),
					"Card Info Table Header is not :" + expectedTableHeader[i].trim().toLowerCase() + ". It is :"
							+ tableHeaderLocator.get(i).getText().trim().toLowerCase());
			logger.info("Card Info Table Header has been verified" + " with [Actual] and [Expected] value as " + "["
					+ tableHeaderLocator.get(i).getText().trim().toLowerCase() + "] and " + "["
					+ expectedTableHeader[i].trim().toLowerCase() + "]");

		}
		return this;
	}

	@Step("Verify Newly Created Credit Card:{0}")
	public AddNewCreditCardPageObjects verifyNewlyCreatedCreditCard(String cardHolderName) {
		log = Logger.getLogger("verifyNewlyCreatedCreditCard");
		WebElement expectedCardHolderName = getDriver()
				.findElement(By.xpath("//td[@data-th='Name on Card']/span[text()='" + cardHolderName + "']"));
		waiting.waitForVisibilityOfElement(expectedCardHolderName, 10);
		Assert.assertTrue(testUtilityMethods.isElementDisplayed(expectedCardHolderName),
				"Credit Card is not created with above test data.");
		log.info("verified Newly Created Credit Card with detail : " + cardHolderName);
		return this;
	}

	@Step("Click On delete button of Credit card.:{0}")
	public AddNewCreditCardPageObjects clickOnDeleteButtonOfCard(String cardHolderName) throws InterruptedException {
		log = Logger.getLogger("clickOnDeleteButtonOfCard");
		WebElement deleteButton = getDriver()
				.findElement(By.xpath("//span[text()='" + cardHolderName.trim() + "']/../following-sibling::td//a"));
		waiting.waitForVisibilityOfElement(deleteButton, 10);
		deleteButton.click();
		Thread.sleep(1200);
		log.info("Clicked On delete button of Credit card for : " + cardHolderName);
		return this;
	}

	@Step("Verify Deletion Of Credit Card:{0}")
	public AddNewCreditCardPageObjects verifyDeletionOfCreditCard(String cardHolderName) {
		log = Logger.getLogger("verifyDeletionOfCreditCard");
		Assert.assertTrue(assertDeletionOfCreditCard(cardHolderName), "Cart is not deleted yet.");
		log.info("Verified deleted Credit Card:" + cardHolderName);
		return this;
	}

	public boolean assertDeletionOfCreditCard(String cardHolderName) {
		try {
			Assert.assertFalse(getDriver()
					.findElement(By.xpath("//td[@data-th='Name on Card']/span[text()='" + cardHolderName.trim() + "']"))
					.isDisplayed());
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	@Step("Verify breadcrumb of Add New Credit Card Page as {0}")
	public AddNewCreditCardPageObjects verifyTheBreadCrumbs(String breadCrumb) {
		log = Logger.getLogger("verifyTheBreadCrumbs");
		waiting.waitForVisibilityOfElement(homeIconBreadCrumbLocator, 10);
		Assert.assertTrue(homeIconBreadCrumbLocator.isDisplayed(), "Home Icon Bread Crumb is not displayed.");
		log.info("Home Icon Bread Crumb is displayed in Add New Credit Card Page.");
		String lastBreadCrump = breadCrumbs.get(breadCrumbs.size() - 1).getText().trim();
		Assert.assertTrue(lastBreadCrump.equalsIgnoreCase(breadCrumb.trim()), "Invalid Breadcrumb");
		log.info("Bread Crumb of the Add New Credit Card  page has been verified"
				+ " with [Actual] and [Expected] value as " + "["
				+ breadCrumbs.get(breadCrumbs.size() - 1).getText().trim() + "] and " + "[" + breadCrumb.trim() + "]");
		return this;
	}

}
