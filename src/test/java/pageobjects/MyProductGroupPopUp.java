package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class MyProductGroupPopUp extends PageInitializer {

	UtilityMethods testUtilityMethods = new UtilityMethods(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();

	Actions action = new Actions(getDriver());

	WaitingMethods waiting = new WaitingMethods(getDriver());

	@FindBy(xpath = "//input[@id='newProductGroupName']")
	private WebElement productGroupTextboxLocator;

	@FindBy(xpath = "//button[@onclick='BulkAction.addNewProductGroup()']")
	private WebElement addNewGroupButton;

	@FindBy(xpath = "//div[@id='multipleProductGroupContent']//a[normalize-space(text())='Add']")
	private WebElement addButtonLocator;

	@FindBy(xpath = "//li[@class='hintCorrect']")
	private WebElement successMessageLocator;

	@FindBy(xpath = "//div[@id='multipleProductGroupContent']")
	private WebElement productGroupPopUpLocator;

	@FindBy(xpath = "//div[@id='multipleProductGroupContent']//a[normalize-space(text())='Cancel']")
	private WebElement cancelButton;

	public MyProductGroupPopUp enterProductGroupName(String productGroupName) {
		waiting.waitForVisibilityOfElement(productGroupTextboxLocator, 20);
		productGroupTextboxLocator.click();
		productGroupTextboxLocator.clear();
		productGroupTextboxLocator.sendKeys(productGroupName);
		return this;
	}

	public MyProductGroupPopUp clickOnAddNewGroup() throws InterruptedException {
		Thread.sleep(1000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addNewGroupButton);
		Thread.sleep(500);
		return this;
	}

	public MyProductGroupPopUp clickOnAddButton() throws InterruptedException {
		waiting.waitForVisibilityOfElement(addButtonLocator, 5);
		// addButtonLocator.click();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", addButtonLocator);
		Thread.sleep(600);
		return this;
	}

	public MyProductGroupPopUp clickOnCancelButton() {
		waiting.waitForVisibilityOfElement(cancelButton, 5);
		cancelButton.click();
		return this;
	}

	public MyProductGroupPopUp verifySuccessMsg() {
		waiting.waitForVisibilityOfElement(successMessageLocator, 5);
		Assert.assertTrue(successMessageLocator.isDisplayed());
		return this;
	}

	public MyProductGroupPopUp clickOnTheCreatedProductGroup(String myProductGroupName) {
		getDriver().findElement(By.xpath("//a[text()='" + myProductGroupName + "']")).click();
		return this;
	}

	public MyProductGroupPopUp clickOnTheGroupCreatedInTheList(String myProductGroupName) throws InterruptedException {
		Thread.sleep(2000);
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					getDriver().findElement(By.xpath("//label[text()='" + myProductGroupName + "']")));
		} catch (NoSuchElementException e) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					getDriver().findElement(By.xpath("//span[text()='" + myProductGroupName + "']")));

		}
		return this;
	}

	public MyProductGroupPopUp verifyCheckboxAssociatedWithTheProductGroupIsSelected() {

		return this;
	}

	@Step("verify items added to product group")
	public MyProductGroupPopUp verifyItemsSelectedInPopUp(String[] partNumbers) throws Exception {
		Thread.sleep(1500);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(productGroupPopUpLocator).build().perform();
		List<WebElement> item = getDriver().findElements(By.xpath("//strong[contains(text(),'Part#')]/ancestor::p"));
		for (int i = 0; i < partNumbers.length; i++) {
			Assert.assertTrue(item.get(i).getText().contains(partNumbers[i]),
					"Item added to product group is not present");
		}
		return this;
	}

	@Step("verify items added to product group")
	public MyProductGroupPopUp verifyItemsSelectedInPopUp(List<String> partNumbers) throws Exception {
		Thread.sleep(4000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(productGroupPopUpLocator).build().perform();
		List<WebElement> item = getDriver().findElements(By.xpath("//strong[contains(text(),'Part#')]/ancestor::p[text()]"));
		for (int i = 0; i < partNumbers.size(); i++) {
			logger.info("Item displayed is :"+item.get(i).getText());
			Assert.assertTrue(item.get(i).getText().contains(partNumbers.get(i)),
					"Item added to product group is not present");
		}
		return this;
	}

	@Step("verify items added to product group")
	public MyProductGroupPopUp verifyItemsSelectedInPopUp(String partNumbers) throws Exception {
		Thread.sleep(1000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(productGroupPopUpLocator).build().perform();
		WebElement item = getDriver()
				.findElement(By.xpath("//div[@class='itemsList']//strong[contains(text(),'Part#:')]/ancestor::p"));
		Assert.assertTrue(item.getText().contains(partNumbers), "Item added to product group is not present");
		return this;
	}

}
