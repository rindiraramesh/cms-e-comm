package utilities;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mainclass.MainClassWebDriver;

public class HashTableUtility extends MainClassWebDriver {
	WebDriver driver;

	Hashtable<String, Integer> headerindex = new Hashtable<String, Integer>();

	public HashTableUtility(WebDriver driver) {
		this.driver = driver;
	}

	public Hashtable<String, Integer> headers(By table, By header) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(table));
		WebElement tablename = driver.findElement(table);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(header));
		List<WebElement> colummheader = tablename.findElements(header);
		for (int i = 0; i < colummheader.size(); i++) {
			if (i == 0) {
				continue;
			}
			headerindex.put(colummheader.get(i).getText(), i);
		}
		return headerindex;
	}

	// Method to enter the data on the respective row and column based on column
	// name
	public void enterDataInHandsOnTable(int rownumber, String colName, String dataToEnter) throws Exception {
		Actions action = new Actions(driver);

		String colrow = "//tr[" + rownumber + "]/td[" + headerindex.get(colName) + "]";

		action.click(driver.findElement(By.xpath(colrow))).sendKeys(dataToEnter).sendKeys(Keys.TAB).build().perform();
		/*	        driver.findElement(By.xpath(colrow)).sendKeys(Keys.TAB);
		        driver.findElement(By.xpath(colrow)).sendKeys(dataToEnter);*/
	}

	// Method to enter the data on the respective row and column based on column
	// name
	public void enterDataInHandsOnTableEnterKeyPressed(int rownumber, String colName, String dataToEnter)
			throws Exception {
		Actions action = new Actions(driver);

		String colrow = "//tr[" + rownumber + "]/td[" + headerindex.get(colName) + "]";

		action.click(driver.findElement(By.xpath(colrow))).sendKeys(dataToEnter).sendKeys(Keys.ENTER).build().perform();
		/*	        driver.findElement(By.xpath(colrow)).sendKeys(Keys.TAB);
		        driver.findElement(By.xpath(colrow)).sendKeys(dataToEnter);*/
	}
}
