package utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import mainclass.MainClassWebDriver;

public class MainOperations extends MainClassWebDriver {

	Logger log = Logger.getLogger(MainOperations.class);

	File uploadExe = new File("src/test/resources/upload.exe");

	@Step("Window file upload :{0}")
	public MainOperations windowFileUpload(String uploadContent) throws Exception {
		Runtime.getRuntime().exec(uploadExe.getAbsolutePath() + " " + uploadContent);
		return this;
	}

	@Step("Window file upload :{0}")
	public MainOperations windowFileUploadUsingRobot(String uploadContent) throws Exception {
		StringSelection stringSelection = new StringSelection(uploadContent);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		log.info("File uploaded using robot class");
		return this;
	}

	@Step("Enter text")
	public MainOperations sendKeys(WebElement webElement, String text) {
		webElement.sendKeys(text.trim());
		log.info("Enter text :" + text.trim());
		return this;
	}

	@Step("Wait till page loads")
	public void waitTillPageLoads() {
		new WebDriverWait(getDriver(), 60)
				.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
						.equals("complete"));
	}

	@Step("Enter Key")
	public MainOperations sendKeys(WebElement webElement, Keys key) {
		waitForVisibilityOfElement(webElement, 25);
		webElement.sendKeys(key);
		return this;
	}

	@Step("Clear text")
	public MainOperations clearText(WebElement webElement) {
		waitForVisibilityOfElement(webElement, 10);
		webElement.clear();
		log.info("clear text");
		return this;
	}

	@Step("Click on element")
	public MainOperations click(WebElement webElement) {
		waitForElementToBeClickable(webElement, 25);
		log.info("Clicking on webelement : [" + webElement.toString().split(">")[1]);
		webElement.click();
		return this;
	}

	// new double click functionality
	@Step("double Click on element")
	public MainOperations doubleclick(WebElement webElement) {
		waitForElementToBeClickable(webElement, 25);
		log.info("double clicking on webelement : [" + webElement.toString().split(">")[1]);
		Actions actions = new Actions(getDriver());
		actions.doubleClick(webElement).perform();
		return this;
	}

	@Step("Click on element using index")
	public MainOperations click(List<WebElement> webElement, int index) {
		waitForVisibilityOfElements(webElement, 10);
		webElement.get(index).click();
		log.info("Clicked on webelement using index");
		return this;
	}

	@Step("Click on element using JS")
	public MainOperations clickByJavaScript(WebElement webElement) {
		// log.info("Clicked on element using JS : [" +
		// webElement.toString().split(">")[1]);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", webElement);
		return this;
	}

	@Step("scroll down using JS")
	public MainOperations scrollDown(String pixels) {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,'" + pixels + "')", "");
		log.info("scroll down using JS");
		return this;
	}

	@Step("scroll up using JS")
	public MainOperations scrollUp(String pixels) {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,'" + pixels + "')", "");
		log.info("scroll down using JS");
		return this;
	}

	@Step("Move to the bottom of the page")
	public MainOperations scrollThePageToBottom() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		log.info("Move to the bottom of the page");
		return this;
	}

	@Step("Move to the top of the page")
	public MainOperations scrollThePageToTop() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		log.info("Move to the top of the page");
		return this;
	}

	@Step("scroll till element")
	public MainOperations scrollTillWebElement(WebElement webElement) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
		log.info("scroll till element");
		return this;
	}

	@Step("Navigating to url")
	public MainOperations navigateToURL(String url) {
		getDriver().navigate().to(url);
		log.info("Navigating to url");
		return this;
	}

	@Step("Get current page url")
	public String getURL() {
		String url = getDriver().getCurrentUrl();
		log.info("Get Current page url");
		return url;
	}

	@Step("Navigate it to forward")
	public MainOperations navigateForward() {
		getDriver().navigate().forward();
		log.info("Navigate it to forward");
		return this;
	}

	@Step("Navigate it to backward")
	public MainOperations navigateBackward() {
		getDriver().navigate().back();
		log.info("Navigate it to backward");
		return this;
	}

	// newly created refresh functionality
	@Step("refresh the page")
	public MainOperations refresh() {
		getDriver().navigate().refresh();
		log.info("page is refreshed");
		return this;
	}

	@Step("delayed")
	public MainOperations delay(long millSeconds) throws InterruptedException {
		Thread.sleep(millSeconds);
		log.info("delayed seconds :" + millSeconds);
		return this;
	}

	@Step("waiting for alert")
	public MainOperations waitForAlertToBePresent(int time) {
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.alertIsPresent());
		log.info("waiting for alert");
		return this;
	}

	@Step("Moved to webelement")
	public MainOperations moveToElement(By byLocator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(byLocator)).build().perform();
		log.info("Moved to webelement");
		return this;
	}

	@Step("Moved to webelement")
	public MainOperations moveToElement(WebElement webElement) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(webElement).build().perform();
		log.info("Moved to webelement");
		return this;
	}

	@Step("Moved to weblement and click")
	public MainOperations moveToElementAndClick(By byLocator) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(getDriver().findElement(byLocator)).click().build().perform();
		log.info("Moved to webelement and click");
		return this;
	}

	@Step("Move to webelement and click")
	public MainOperations moveToElementAndClick(WebElement webElement) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(webElement).click().build().perform();
		log.info("Moved to webelement and click");
		return this;
	}

	@Step("Accept alert ")
	public MainOperations acceptAlert() {
		waitForAlertToBePresent(5);
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
		log.info("Accepted alert");
		return this;
	}

	@Step("Dismiss alert")
	public MainOperations dismissAlert() {
		waitForAlertToBePresent(5);
		Alert alert = getDriver().switchTo().alert();
		alert.dismiss();
		log.info("Dismissed alert");
		return this;
	}

	@Step("Select using Visible text")
	public MainOperations selectElementByVisibleText(WebElement webElement, String text) {
		Select select = new Select(webElement);
		select.selectByVisibleText(text);
		log.info("Select using Visible text :" + text);
		return this;
	}

	@Step("Options displayed")
	public List<WebElement> getAllTheOptions(WebElement webElement) {
		Select select = new Select(webElement);
		List<WebElement> options = select.getOptions();
		log.info("All options :" + select.getOptions());
		return options;
	}

	@Step("Select using Webelement")
	public MainOperations selectElementByIndex(WebElement webElement, int index) {
		Select select = new Select(webElement);
		select.selectByIndex(index);
		log.info("Select using Webelement index :" + index);
		return this;
	}

	@Step("Get text from Webelement")
	public String getText(WebElement webElement) {
		log.info("Get text from Webelement :" + webElement.getText());
		waitForVisibilityOfElement(webElement, 25);
		return webElement.getText();
	}

	// Newly Created
	@Step("Get Attribue value from Webelement")
	public String getAttribute(WebElement webElement, String attribute) {
		log.info("Get Attribue value from Webelement :" + webElement.getAttribute(attribute));
		return webElement.getAttribute(attribute);
	}

	@Step("Get text using index")
	public String getText(List<WebElement> webElement, int index) {
		log.info("Get text using index of Webelement :" + webElement.get(index).getText());
		return webElement.get(index).getText();
	}

	@Step("Get page title")
	public String getTitle() {
		log.info("Get page title");
		return getDriver().getTitle();

	}

	@Step("Getting alert text")
	public String getAlertText() {
		waitForAlertToBePresent(20);
		log.info("Getting alert text");
		return getDriver().switchTo().alert().getText();
	}

	@Step("To check whether element is displayed")
	public boolean isElementDisplayed(WebElement element) {
		try {
			if (element.isDisplayed()) {
				log.info("Element is displayed : [" + element.toString().split(">")[1]);
				return true;
			} else {
				log.info("Element is not displayed: " + element);
				return false;
			}
		} catch (NoSuchElementException e) {
			log.info("Element is not displayed: " + element);
			return false;
		}
	}

	@Step("To check whether element is not displayed")
	public boolean isElementNotDisplayed(WebElement element) {
		try {
			if (element.isDisplayed()) {
				log.info("Element is displayed : [" + element.toString().split(">")[1]);
				return false;
			} else {
				log.info("Element is not displayed : [" + element.toString().split(">")[1]);
				return true;
			}
		} catch (NoSuchElementException e) {
			log.info("Element is not displayed: " + element);
			return false;
		}
	}

	@Step("Verify alert text")
	public boolean verifyAlertText(String expectedAlertText) throws Exception {
		boolean result = getAlertText().replace("\n", "").trim().equalsIgnoreCase(expectedAlertText);
		acceptAlert();
		return result;
	}

	// new method switching control from parent to new tab
	@Step("Switching control from parent to new tab")
	public MainOperations SwitchToNewTab(int tab) throws Exception {
		ArrayList<String> Newtabs = new ArrayList<String>(getDriver().getWindowHandles());
		log.info("Available tabs :" + Newtabs);
		getDriver().switchTo().window(Newtabs.get(tab));
		return this;
	}

	// new method switching back control to parent tab
	@Step("Switching control back to parent tab")
	public MainOperations SwitchBackToParentTab() throws Exception {
		ArrayList<String> Newtabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(Newtabs.get(0));
		return this;
	}

	// close the current tab
	@Step("Close the current tab")
	public MainOperations closeCurrentTab() throws Exception {
		getDriver().close();
		log.info("Close current tab");
		return this;
	}

	// new method
	@Step("hitting enter button")
	public MainOperations HitEnterButton(WebElement element) throws Exception {
		element.sendKeys(Keys.ENTER);
		return this;
	}

	public void waitForVisibilityOfElement(WebElement element, int time) {
		log.info("wait for visibility of the element.");
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForVisibilityOfElement(By element, int time) {
		log.info("wait for visibility of the element : [" + element.toString().split(">")[1]);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitForVisibilityOfElements(List<WebElement> element, int time) {
		log.info("wait for visibility of the elements : [" + element.toString().split(">")[1]);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void waitForVisibilityOfElements(By element, int time) {
		log.info("wait for visibility of the elements : [" + element.toString().split(">")[1]);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}

	public void waitForElementToBeClickable(WebElement element, int time) {
		log.info("wait for element to be clickable : [" + element.toString().split(">")[1]);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeClickable(By element, int time) {
		log.info("wait for element to be clickable : [" + element.toString().split(">")[1]);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void implicitWaitForSeconds(long seconds) {
		log.info("implicit waiting ...");
		getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void waitForVisibilityOfElementText(int time, String text, WebElement element) {
		log.info("wait for visiblity of the element text :" + text);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public void waitForAttributeValueContains(WebElement webElement, String attribute, String value, int time) {
		log.info("wait for attribute value contains in the element :" + attribute);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.attributeContains(webElement, attribute, value));
	}

	public void waitForStalenessOfElement(WebElement webElement, int time) {
		new WebDriverWait(getDriver(), time)
				.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(webElement)));
	}

	public void waitForTextInElement(WebElement webElement, String attribute, String text, int time) {
		log.info("wait for text in the element :" + text);
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.textToBePresentInElement(webElement, text));

	}

	@Step("waiting for iframe")
	public MainOperations waitForiframeToBePresent(int time) {
		new WebDriverWait(getDriver(), time).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		log.info("waiting for iframe");
		return this;
	}

	@Step("Switched to iframe")
	public MainOperations switchToiframe() {
		waitForiframeToBePresent(5);
		getDriver().switchTo().activeElement();
		log.info("switched to iframe");
		return this;
	}

	@Step("Switched to iframe")
	public MainOperations switchToParentframe() {
		waitForiframeToBePresent(5);
		getDriver().switchTo().parentFrame();
		log.info("switched to iframe");
		return this;
	}

	@Step("Switched to iframe")
	public MainOperations switchToWindow() {
		getDriver().switchTo().defaultContent();
		log.info("switched to iframe");
		return this;
	}

	@Step("Switched to iframe using name {0}")
	public MainOperations switchToiframeByName(String nameOfFrame) {
		getDriver().switchTo().frame(nameOfFrame);
		log.info("switched to iframe");
		return this;
	}
	
	@Step("Reload frame")
	public MainOperations reloadframe() {
		((JavascriptExecutor) getDriver()).executeScript("document.getElementsByTagName('iframe')[0].contentWindow.location.reload();");
		log.info("Reload frame");
		return this;
	}

	@Step("Drag and drop is performed")
	public MainOperations dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(getDriver());
		actions.dragAndDrop(source, target).build().perform();
		log.info("Drag and drop is performed");
		return this;
	}

	@Step("custom wait")
	public boolean customWaitforVisibility(By ele, int time) throws InterruptedException {

		for (int i = 0; i < time; i++) {

			try {
				getDriver().findElement(ele).isDisplayed();
				return true;
			} catch (Exception e) {
				Thread.sleep(100);
			}
		}

		return false;
	}

	

}
