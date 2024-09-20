package utility;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import mainclass.MainClassWebDriver;

public class UtilityMethods extends MainClassWebDriver {

	WebDriver driver;

	WaitingMethods waitingMethods;

	Logger log = Logger.getLogger(UtilityMethods.class);

	public UtilityMethods(WebDriver driver) {
		this.driver = driver;
	}

	// To accept alert
	public void alertOK() {
		waitingMethods = new WaitingMethods(driver);
		waitingMethods.waitForAlertToBePresent(5);
		Alert alert = driver.switchTo().alert();
		log.info("Accept alert");
		alert.accept();
	}

	// To dismiss alert
	public void alertCancel() {
		waitingMethods = new WaitingMethods(driver);
		waitingMethods.waitForAlertToBePresent(5);
		Alert alert = driver.switchTo().alert();
		log.info("Cancel the alert");
		alert.dismiss();
	}

	// To fetch alert text
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText().trim();
		log.info("Alert text is:" + alertText);
		return alertText;
	}

	// Verify alert text
	public boolean verifyAlertText(String expectedAlertText) throws Exception {
		boolean result = getAlertText().replace("\n", "").trim().equalsIgnoreCase(expectedAlertText);
		alertOK();
		log.info("Alert text is verified :" + expectedAlertText);
		return result;
	}

	// To select value from dropdown by visible text
	public void selectElementByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		log.info("Selected " + text + " from drop down");
	}

	// To select value from dropdown by index
	public void selectElementByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Selected by index :" + index);
	}

	// To check Alert is displayed
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	// To check whether element is displayed
	public boolean isElementDisplayed(WebElement element) {
		try {
			if (element.isDisplayed()) {
				log.info("Element is displayed");
				return true;
			} else {
				log.info("Element is not displayed");
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// To check whether element is displayed
	public boolean isElementDisplayed(List<WebElement> element) {
		try {
			if (element.size() == 0)
				return false;

			for (int i = 0; i < element.size(); i++) {
				if (element.get(i).isDisplayed()) {
					log.info("Element is displayed");
					continue;
				} else {
					log.info("Element is not displayed");
					return false;
				}
			}
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// To check whether element is displayed by accepting string as argument
	public boolean isElementDisplayed(String element) {
		try {
			if (getDriver().findElement(By.xpath(element)).isDisplayed()) {
				log.info("Element is displayed");
				return true;
			} else {
				log.info("Element is not displayed");
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// To check whether element is displayed
	public boolean isElementDisplayed(By locator) {
		try {
			if (getDriver().findElement(locator).isDisplayed()) {
				log.info("Element is displayed");
				return true;
			} else {
				log.info("Element is not displayed");
				return false;
			}
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// To check whether element is not displayed
	public boolean isElementNotDisplayed(WebElement element) {
		try {
			if (element.isDisplayed()) {
				log.info("Element is not displayed");
				return false;
			}

			else {
				log.info("Element is displayed");
				return true;
			}
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	// To check whether element is not displayed
	public boolean isElementNotDisplayed(By locator) {
		try {
			if (getDriver().findElement(locator).isDisplayed()) {
				log.info("Element is not displayed");
				return false;
			} else {
				log.info("Element is displayed");
				return true;
			}
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	// Move to a element
	public void moveToElement(By locator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(locator)).build().perform();
		log.info("Moved to the element");
	}

	// Move to a element
	public void moveToElement(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).build().perform();
		log.info("Moved to the element");
	}

	// Move to a element and click
	public void moveToElementAndClick(By locator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(locator)).click().build().perform();
		log.info("Moved to element and clicked");
	}

	// Move to a element and click
	public void moveToElementAndClick(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).click().build().perform();
		log.info("Moved to element and clicked");
	}

	public void scrollThePageForListOfElements(List<WebElement> locatorValue, int indexValue) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();",
				locatorValue.get(indexValue));
		log.info("Scroll the page for list of elements");
	}

	// Move to the bottom of the page
	public void scrollThePageToBottom() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		log.info("Scroll to the bottom of the page");
	}

	// Move to the top of the page
	public void scrollThePageToTop() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		log.info("Scroll to the top of the page");
	}

	// scroll till element
	public void scrollTillWebElement(WebElement webElement) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
		log.info("Scroll till web element");
	}

	// Drag and drop
	public void dragAndDropElement(WebElement source, WebElement target) {
		Actions actions = new Actions(getDriver());
		actions.dragAndDrop(source, target).build().perform();
		log.info("Drag and dropped");
	}

	// Drag and drop
	public void dragAndDropElement(WebElement source, int xOrdinate, int yOrdinate) {
		Actions actions = new Actions(getDriver());
		actions.dragAndDropBy(source, xOrdinate, yOrdinate).build().perform();
		log.info("Drag and dropped");
	}

}
