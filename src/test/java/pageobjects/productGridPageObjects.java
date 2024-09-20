package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.WaitingMethods;

public class productGridPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	SoftAssert soft = new SoftAssert();

	@FindBy(xpath = "//div[@class='itemCountGrid']/a")
	private List<WebElement> moreChoicesButtonLocator;

	@FindBy(xpath = "//div//a[@id='listView']")
	private WebElement changeViewButtonLocator;

	// shilpa
	@FindBy(xpath = "//div[@class='pagebarUTH']")
	private WebElement paginationLocator;

	@FindBy(xpath = "//a[text()='Next']")
	private WebElement nextPaginationButtonLocator;

	@FindBy(xpath = "//a[text()='Previous']")
	private WebElement previousButtonLocator;

	@Step("Verify display of View All Choices Button For Product Mode Items In Product Grid Page")
	public productGridPageObjects verifyDisplayOfViewOfChoicesButtonsInProductGridPage() {

		waiting.waitForVisibilityOfElements(moreChoicesButtonLocator, 10);
		for (int i = 0; i < moreChoicesButtonLocator.size(); i++) {
			Assert.assertTrue(moreChoicesButtonLocator.get(i).isDisplayed(),
					"View All Choice Buttons are not displayed in product grid page.");
		}

		return this;

	}

	@Step("click on View All Choices Button link Under Product Mode Items : {0} In Product Grid Page")
	public productGridPageObjects clickOnViewAllChoiceButtonInProductGridPage(int specificProductMode)
			throws InterruptedException {

		waiting.waitForVisibilityOfElements(moreChoicesButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				moreChoicesButtonLocator.get(specificProductMode - 1));
		Thread.sleep(1400);

		return this;

	}

	@Step("click on change view")
	public productGridPageObjects clickOnChangeView() throws Exception {
		logger = Logger.getLogger("clickOnChangeView");
		waiting.waitForVisibilityOfElement(changeViewButtonLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", changeViewButtonLocator);

		Thread.sleep(1000);
		logger.info("clicked on ChangeView ico");
		return this;
	}

	public String getNumberOfItemsUnderViewMoreChoices(int specificViewMoreChoiceProduct) {

		waiting.waitForVisibilityOfElements(moreChoicesButtonLocator, 10);

		String noOfItemsUnderViewMoreChoices = moreChoicesButtonLocator.get(specificViewMoreChoiceProduct - 1)
				.getText()
				.replaceAll(" View All ", "")
				.replaceAll(" Choices", "")
				.trim();

		return noOfItemsUnderViewMoreChoices;

	}

	// shilpa
	@Step("click on change view")
	public productGridPageObjects verifyPagination(String pageNumber, String nextpageNumber) {
		soft.assertTrue(paginationLocator.isDisplayed(), "Pagination is not displayed.");
		// waiting.waitForVisibilityOfElement(By.xpath("//a[text()='" + pageNumber +
		// "']"), 10);
		logger = Logger.getLogger("clickOnPaginationButton");
		getDriver().findElement(By.xpath("//a[text()='" + pageNumber + "']")).click();
		soft.assertTrue(getDriver().findElement(By.xpath("//span[@class='this-page' and text()='" + pageNumber + "']"))
				.isDisplayed(), "Pagination is not displayed.");
		if (nextPaginationButtonLocator.isDisplayed()) {
			nextPaginationButtonLocator.click();
			soft.assertTrue(
					getDriver().findElement(By.xpath("//span[@class='this-page' and text()='" + nextpageNumber + "']"))
							.isDisplayed(),
					"Pagination is not displayed.");
			soft.assertTrue(previousButtonLocator.isDisplayed(), "Pagination is not displayed.");
			previousButtonLocator.click();
		}

		return this;
	}

}
