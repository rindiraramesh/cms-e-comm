package pageobjects;

import java.util.Calendar;

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
import utility.WaitingMethods;

public class TestPageObjects extends PageInitializer {

	Logger logger;

	WaitingMethods waiting = new WaitingMethods(getDriver());

	@FindBy(xpath = "//input[@id='txtSearch']")
	private WebElement searchBox;

	@FindBy(xpath = "//input[@id='txtSearch']")
	private WebElement searchButton;

	@FindBy(xpath = "//ul[contains(@class,'myAccountMenu')]//a[@href='/RequestForQuote']")
	private WebElement rfq;

	@FindBy(xpath = "//input[@name='reqDate']")
	private WebElement rfq_date;

	@Step("Verify title as {0}")
	public void assertTitle(String title) throws InterruptedException {
		logger = Logger.getLogger("Check Page Title");
		logger.info("Assert Title:" + title);
		Assert.assertEquals(getDriver().getTitle().trim(), title.trim(), "Invalid title");
		Thread.sleep(3000);
	}

	@Step("Verify search button is displayed")
	public void assertSearchButtonIsDisplayed() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertTrue(getDriver().findElement(By.xpath("//button[@id='performSearchBtn']")).isDisplayed(),
				"Search button is not displayed");
	}

	@Step("Search Text:{0}")
	public void searchText(String text) throws InterruptedException {
		Thread.sleep(1000);
		searchBox.sendKeys(text);
		searchBox.sendKeys(Keys.ENTER);
	}

	public void navigateCategories() {
		WebElement productLinkLocator = getDriver()
				.findElement(By.xpath("//div[@id='normalHead']//a[@href='/products']"));
		Actions actions = new Actions(getDriver());
		actions.moveToElement(productLinkLocator).build().perform();
		/*
		 * Action moveOverHome =
		 * builder.click().moveToElement(productLinkLocator).build();
		 * moveOverHome.perform();
		 */
	}

	public TestPageObjects navigateToRFQ() {
		waiting.waitForVisibilityOfElement(rfq, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", rfq);
		return this;
	}

	public TestPageObjects clickOnDate() {
		waiting.waitForVisibilityOfElement(rfq_date, 10);
		rfq_date.click();
		return this;
	}

	public TestPageObjects selectDate(String date, String monthYear) throws InterruptedException {

		try {
			// my[0]->Month,my[1]->Year
			String[] my = monthYear.split(" ");
			Calendar cal = Calendar.getInstance();
			if (Integer.parseInt(my[1]) >= cal.get(Calendar.YEAR)) {

				waiting.waitForVisibilityOfElement(rfq_date, 15);
				rfq_date.click();
				Thread.sleep(1200);

				WebElement monthYearLocator = getDriver()
						.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]"));
				WebElement next = getDriver().findElement(By.xpath("(//th[@class='next'])[1]"));
				int counter = 0;
				while (!(monthYearLocator.getText().trim().equalsIgnoreCase(monthYear))) {
					next.click();
					counter++;
					if (counter > 20) {
						/*
						 * System.out.println("Loop Break"); break;
						 */
						Assert.assertTrue(false, "Invalid Date");
					}
				}

				WebElement dt = getDriver()
						.findElement(By.xpath("//td[text()='" + date + "' and (@class='day' or @class='active day')]"));

				if (!(dt.getAttribute("class").equalsIgnoreCase("old disabled day")
						|| dt.getAttribute("class").equalsIgnoreCase("disabled day")))
					dt.click();
			} else {
				Assert.assertTrue(false, "Invalid Year");
			}
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "Invalid Date");
		}

		return this;
	}

}