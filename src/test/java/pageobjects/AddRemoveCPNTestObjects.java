package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class AddRemoveCPNTestObjects extends PageInitializer {
	Logger log = Logger.getLogger(AddRemoveCPNTestObjects.class);

	WaitingMethods waitingMethods = new WaitingMethods(getDriver());

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	@FindBy(xpath = "//a[contains(@data-function,'customer')]")
	public WebElement addRemoveCPNLoctor;

	@FindBy(id = "newCustomerPartNumber")
	public WebElement newCustomerPartNumberInputLoctor;

	@FindBy(id = "add")
	public WebElement newCustomerPartNumberAddButtonLoctor;

	@FindBy(id = "update")
	public WebElement newCustomerPartNumberUpdateButtonLoctor;

	@FindBy(id = "remove")
	public WebElement newCustomerPartNumberRemoveButtonLoctor;

	@FindBy(xpath = "//*[@id='customerPartNumSubmit']//li")
	public WebElement successMessageLocator;

	@FindBy(className = "bootbox-body")
	public WebElement errorMessageLocator;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement errorPopUpOkLocator;

	@FindBy(className = "customCheckBoxRight")
	public WebElement addedCPNLocator;

	@FindBy(className = "customCheckBoxRight")
	public List<WebElement> removeAllCPNLocator;

	@FindBy(xpath = "//*[@id='yourPrices']/strong")
	public WebElement yourPriceLocator;

	@FindBy(className = "CPN_Sel")
	public List<WebElement> allCPNLocator;

	@Step("Click on Add Remove CPN link")
	public AddRemoveCPNTestObjects clickOnAddRemoveCPNLink() {
		waitingMethods.waitForVisibilityOfElement(addRemoveCPNLoctor, 10);
		addRemoveCPNLoctor.click();
		log.info("Clicked on :" + addRemoveCPNLoctor.getText());
		return this;
	}

	@Step("Enter : {0}")
	public AddRemoveCPNTestObjects enterNewCustomerPartNumber(String newCPN) throws Exception {
		waitingMethods.waitForVisibilityOfElement(newCustomerPartNumberInputLoctor, 10);
		newCustomerPartNumberInputLoctor.click();
		newCustomerPartNumberInputLoctor.sendKeys(newCPN);
		log.info("Entered " + newCPN);
		Thread.sleep(1500);
		return this;
	}

	@Step("Verify after clicking CPN link")
	public AddRemoveCPNTestObjects verifyfieldsonClickingCustomerPartNumber() {
		waitingMethods.waitForVisibilityOfElement(newCustomerPartNumberInputLoctor, 10);
		softAssert.assertTrue(utilityMethods.isElementDisplayed(newCustomerPartNumberInputLoctor),
				"new CPN input field is not display");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(newCustomerPartNumberAddButtonLoctor),
				"Add button field is not display");
		softAssert.assertAll();
		return this;
	}

	@Step("Click on Add button")
	public AddRemoveCPNTestObjects clickAddButton() {
		waitingMethods.waitForVisibilityOfElement(newCustomerPartNumberAddButtonLoctor, 10);
		newCustomerPartNumberAddButtonLoctor.click();
		log.info("Clicked on Add Button ");
		return this;
	}

	@Step("Click on Remove button")
	public AddRemoveCPNTestObjects clickRemoveButton() {
		waitingMethods.waitForVisibilityOfElement(newCustomerPartNumberRemoveButtonLoctor, 10);
		newCustomerPartNumberRemoveButtonLoctor.click();
		log.info("Clicked on Remove Button ");
		return this;
	}

	@Step("Click on Update button")
	public AddRemoveCPNTestObjects clickUpdateButton() {
		waitingMethods.waitForVisibilityOfElement(newCustomerPartNumberUpdateButtonLoctor, 10);
		newCustomerPartNumberUpdateButtonLoctor.click();
		log.info("Clicked on Remove Button ");
		return this;
	}

	@Step("Verify the success message")
	public AddRemoveCPNTestObjects verifySuccessMessage() throws Throwable {
		waitingMethods.waitForVisibilityOfElementText(5, "Customer", successMessageLocator);
		Assert.assertTrue(utilityMethods.isElementDisplayed(successMessageLocator), "Success message is not display");
		log.info("Success Message is verified");
		Thread.sleep(1500);
		return this;
	}

	@Step("Verify the error message")
	public AddRemoveCPNTestObjects verifyDuplicateMessage() {
		waitingMethods.waitForVisibilityOfElementText(5, "Customer", errorMessageLocator);
		Assert.assertTrue(utilityMethods.isElementDisplayed(errorMessageLocator),
				"Customer Part Number already exists.");
		log.info("Error Message is verified");
		errorPopUpOkLocator.click();
		log.info("Clicked ok Button");
		return this;
	}

	@Step("Verify added cpn in list")
	public AddRemoveCPNTestObjects verifyAddedCPN(String cpn) throws Exception {
		for (WebElement cpnList : allCPNLocator) {
			if (cpnList.getText().trim().contentEquals(cpn))
				Assert.assertTrue(cpnList.getText().trim().contentEquals(cpn));
		}
		return this;
	}

	@Step("Select CPN ")
	public AddRemoveCPNTestObjects selectCPN(String cpn) throws Exception {
		getDriver().findElement(By.xpath("//span[@class='CPN_Sel'][contains(text(),'" + cpn + "')]")).click();
		log.info("Selected CPN");
		return this;
	}

	@Step("removing existing CPN's for an item")
	public AddRemoveCPNTestObjects removeAllCPN() throws Exception {
		clickOnAddRemoveCPNLink();
		Thread.sleep(1500);
		if (removeAllCPNLocator.size() != 0) {
			for (int row = 0; row < removeAllCPNLocator.size(); row++) {
				removeAllCPNLocator.get(row).click();
				log.info("Selected :" + removeAllCPNLocator.get(row).getAttribute("value"));
			}
			newCustomerPartNumberRemoveButtonLoctor.click();
			log.info("Clicked on :" + newCustomerPartNumberRemoveButtonLoctor.getText());
			Thread.sleep(2500);
			getDriver().navigate().refresh();
		} else {
			getDriver().navigate().refresh();
		}
		return this;
	}
}
