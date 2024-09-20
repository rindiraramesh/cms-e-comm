package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
/*import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;
*/
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ShopByManufacturersPageObjects extends PageInitializer {

	@FindBy(xpath = "(//h4[contains(text(),'Manufacturers')]/following-sibling::span/ancestor::dt/following-sibling::dd)[1]/descendant::li/a")
	public List<WebElement> allManufacturersUnderManufacturersDropdown;

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(ShopByManufacturersPageObjects.class);

	Actions action = new Actions(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	@FindBy(xpath = "//h2[contains(text(),'Shop By Manufacturers')]")
	private WebElement shopByManufacturersName;

	@FindBy(xpath = "//div[contains(@class,'cimm_brandAtoZletters')]/descendant::li/a")
	private List<WebElement> listOfAlphabets;

	@FindBy(xpath = "//div[@id='displayBrand']/descendant::li/a")
	private List<WebElement> listOfManufacturersUnderEveryAlphabet;

	@FindBy(xpath = "//h4[contains(text(),'Manufacturers')]/following-sibling::span")
	private WebElement filterManufacturersDropdownToggleButtonLocator;

	@FindBy(xpath = "//div[@id='normalHead']//a[normalize-space(text())='Manufacturers']")
	private WebElement manufacturersLink;

	@FindBy(xpath = "//div[contains(@class,'shopbymanuf dropdown-menu')]//li")
	private List<WebElement> manufacturersDropdownLinks;

	/*
	 * @FindBy(xpath =
	 * "//li[@class='dropdown']/descendant::a[@href='/manufacturers' and text()='View All Manufacturers']"
	 * ) private WebElement viewAllManufacturersLink;
	 */

	@FindBy(xpath = "//div[contains(@class,'shopbymanuf')]//a[text()='View All Manufacturers']")
	private WebElement viewAllManufacturersLink;

	@FindBy(xpath = "//li[@class='dropdown']/descendant::a[@href='/manufacturers' and text()='View All Manufacturers']")
	private WebElement shopByManufacturersBreadcrumb;

	@FindBy(xpath = "//input[@id='searchGrid']")
	private WebElement searchByManufacturerTextbox;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	public List<WebElement> breadCrumbs;

	@FindBy(xpath = "//input[@id='searchGrid']")
	private WebElement searchByManufacturersTextbox;

	@FindBy(xpath = "//div[@class='grid']/descendant::a[text()='Kimberly-Clark Professional']")
	private WebElement manufacturersSearchText;

	@FindBy(xpath = "//div[@class='grid']//a")
	private WebElement alphabetSearchText;

	@FindBy(xpath = "//div[contains(@class,'cimm_shopByManufacturer dropdown-menu')]//a")
	private WebElement itemAvailableUnderManufacturer;

	@Step("Verify shop by manufacturers page name")
	public ShopByManufacturersPageObjects verifyShopByManufacturersPageName() {
		waiting.waitForVisibilityOfElement(shopByManufacturersName, 10);
		Assert.assertTrue(shopByManufacturersName.isDisplayed(), "shop by manufacturers heading is not displayed.");
		return this;
	}

	@Step("Verify shop by manufacturers breadcrumb contains {0}")
	public ShopByManufacturersPageObjects verifyShopByBreadcrump(String shopByManufacturerBreadcrump) {
		waiting.waitForVisibilityOfElements(breadCrumbs, 10);
		Assert.assertTrue(
				breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("/", "").trim().equals(
						shopByManufacturerBreadcrump),
				"Breadcrump is not " + shopByManufacturerBreadcrump + ". It is "
						+ breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("/", "").trim() + ".");
		log.info("Verified shop by manufacturers breadcrumb contains" + shopByManufacturerBreadcrump);
		return this;
	}

	@Step("Verify title of shop by manufacturers contains {0}")
	public ShopByManufacturersPageObjects verifyTitleOfShopByManufacturers(String shopByManufacturersBreadcrumb,
			String productName) throws Exception {
		waiting.waitTillPageLoads();
		Assert.assertTrue(getDriver().getTitle().trim().equals(shopByManufacturersBreadcrumb + " | " + productName),
				"Title is " + getDriver().getTitle().trim() + " Asserting with data : " + shopByManufacturersBreadcrumb
						+ " | " + productName.trim());
		log.info("Verified title of shop by manufacturers " + shopByManufacturersBreadcrumb);
		return this;
	}

	@Step("Click on every alphabet and check the first letter of the options")
	public ShopByManufacturersPageObjects clickOnEveryAlphabetAndCheckTheFirstLetterOfTheRespectiveManufacturers()
			throws Exception {
		waiting.waitForVisibilityOfElements(listOfAlphabets, 10);
		for (int i = 0; i < listOfAlphabets.size(); i++) {

			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", listOfAlphabets.get(i));

			Thread.sleep(4000);
			{
				for (int j = 0; j < listOfManufacturersUnderEveryAlphabet.size(); j++) {
					waiting.waitForVisibilityOfElements(listOfManufacturersUnderEveryAlphabet, 20);
					Thread.sleep(2000);
					Assert.assertTrue(
							listOfManufacturersUnderEveryAlphabet.get(j).getText().trim().startsWith(
									listOfAlphabets.get(i).getText().trim()),
							"Manufacturer does not start with aphabet chosen. Manufacturer name is - "
									+ listOfManufacturersUnderEveryAlphabet.get(j).getText().trim()
									+ " and alphabet chosen is " + listOfAlphabets.get(i).getText().trim() + ".");
				}
			}
		}
		return this;
	}

	@Step("Click on manufacturers toggle button")
	public ShopByManufacturersPageObjects clickOnManufacturersToggleButton() throws Exception {
		filterManufacturersDropdownToggleButtonLocator.click();
		return this;
	}

	@Step("Click on manufacturer with name {0} ")
	public ShopByManufacturersPageObjects clickOnSpecificManufacturer(String specificManufacturer) throws Exception {
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver().findElement(By.xpath(
				"(//h4[contains(text(),'Manufacturers')]/following-sibling::span/ancestor::dt/following-sibling::dd)[1]/descendant::li/a[contains(@href,'"
						+ specificManufacturer + "')]")));
		return this;
	}

	@Step("Click on {0} st/nd/3rd manufacturer")
	public ShopByManufacturersPageObjects clickOnSpecificManufacturer(int specificManufacturer) throws Exception {
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				allManufacturersUnderManufacturersDropdown.get(specificManufacturer - 1));
		return this;
	}

	public String getNameOfTheSpecificManufacturer(int specificManufacturer) {
		String nameOfTheManufacturerString = allManufacturersUnderManufacturersDropdown.get(specificManufacturer - 1)
				.getAttribute("href");
		String nameOfTheManufacturersubString = nameOfTheManufacturerString
				.substring(nameOfTheManufacturerString.lastIndexOf("/") + 1);
		return nameOfTheManufacturersubString;
	}

	public String getNameOfTheSpecificManufacturer(String specificManufacturer) {
		String nameOfTheManufacturerString = getDriver().findElement(By.xpath(
				"(//h4[contains(text(),'Manufacturers')]/following-sibling::span/ancestor::dt/following-sibling::dd)[1]"
						+ "/descendant::li/a[contains(@href,'" + specificManufacturer + "')]"))
				.getAttribute("href");
		String nameOfTheManufacturersubString = nameOfTheManufacturerString
				.substring(nameOfTheManufacturerString.lastIndexOf("/") + 1);
		return nameOfTheManufacturersubString;
	}

	@Step("Verify manufacturer breadcrumb contains {0}")
	public ShopByManufacturersPageObjects verifyManufacturerBreadCrumb(String nameOfTheManufacturer) {
		waiting.waitForVisibilityOfElements(productDetailsPage().breadCrumbs, 10);
		Assert.assertTrue(
				productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.replace("/", "")
						.trim()
						.contains(nameOfTheManufacturer),
				"Breadcrump does not contain the manufacturer that is clicked. It is "
						+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
								.getText()
								.replace("/", "")
								.trim()
						+ ".");
		return this;
	}

	@Step("Verify manufacturer title contains {0}")
	public ShopByManufacturersPageObjects verifyTitleOfTheManufacturer(String nameOfTheManufacturer, String productName)
			throws Exception {
		Thread.sleep(2500);
		Assert.assertTrue(getDriver().getTitle().trim().contains(nameOfTheManufacturer),
				"The title does not contain the manufacturer that was clicked. The title is "
						+ getDriver().getTitle().trim() + "." + "Asserting with data : " + nameOfTheManufacturer + ".");
		Assert.assertTrue(getDriver().getTitle().trim().contains(" | " + productName),
				"The title does not contain the product name.");
		Assert.assertFalse(getDriver().getTitle().trim().startsWith("|"), "The title does start with | .");
		return this;
	}

	@Step("Hover over manufacturers link")
	public ShopByManufacturersPageObjects hoverOverManufacturersLink() throws InterruptedException {
		try {
			Thread.sleep(1500);
			waiting.waitForVisibilityOfElement(manufacturersLink, 5);
			action.moveToElement(manufacturersLink).build().perform();
			log.info("hover over manufacturers link");

		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			hoverOverManufacturersLink();
		}
		return this;
	}

	@Step("Verify manufacturers dropdown and VIEW ALL MANUFACTURERS link")
	public ShopByManufacturersPageObjects verifyManufacturersDropdown() {
		waiting.waitForVisibilityOfElements(manufacturersDropdownLinks, 10);
		for (WebElement manufacturerLinkDropdown : manufacturersDropdownLinks) {
			Assert.assertTrue(manufacturerLinkDropdown.isDisplayed(),
					"Manufacturers dropdown Links are not displayed.");
		}
		Assert.assertTrue(viewAllManufacturersLink.isDisplayed(), "View All Manufacturers link is not displayed.");
		log.info("Verified manufacturers dropdown and VIEW ALL MANUFACTURERS link");
		return this;
	}

	@Step("Click on MANUFACTURERS link/menu")
	public ShopByManufacturersPageObjects clickOnManufacturerLinkInHeader() throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElement(manufacturersLink, 5);
		manufacturersLink.click();
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", manufacturersLink);
		log.info("Clicked on MANUFACTURERS link/menu");
		return this;
	}

	@Step("Verify placeholder of search by Manufacturer textbox")
	public ShopByManufacturersPageObjects verifyPlaceHolderOfSearchByManufacturerTextbox(
			String expectedSearchTextboxPlaceholder) {
		waiting.waitForVisibilityOfElement(searchByManufacturerTextbox, 8);
		// System.out.println(searchByManufacturerTextbox.getAttribute("value"));
		// Assert.assertEquals(searchByManufacturerTextbox.getAttribute("value").trim(),
		// expectedSearchTextboxPlaceholder);
		System.out.println("placeholder" + searchByManufacturersTextbox.getText());
		System.out.println("placehololder of search text");
		Assert.assertEquals(searchByManufacturerTextbox.getText().trim(), expectedSearchTextboxPlaceholder);
		return this;
	}

	@Step("Click on specific alphabet {0}")
	public ShopByManufacturersPageObjects clickOnSpecificAlphabet(
			String specificAlphabetToClickInShopByManufacturersPage) {
		getDriver().findElement(By.xpath("//a[text()='" + specificAlphabetToClickInShopByManufacturersPage + "']"))
				.click();
		Assert.assertTrue(utils.isElementDisplayed(alphabetSearchText),
				"Specific alphabet search related Text is not displayed");
		log.info("Clicked on Specific Alphabet :" + specificAlphabetToClickInShopByManufacturersPage);
		return this;
	}

	public ShopByManufacturersPageObjects scrollUntilElementVisible(String locatorValue) {

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", locatorValue);
		return this;
	}

	@Step("Enter search text:{0} in 'Search By Manufacturers' textbox")
	public ShopByManufacturersPageObjects searchText(String searchText) throws Exception {
		waiting.waitForVisibilityOfElement(searchByManufacturerTextbox, 15);
		searchByManufacturersTextbox.clear();
		searchByManufacturersTextbox.sendKeys(searchText);
		log.info("Enter search text" + searchText + " in Search By Manufacturers textbox");
		// searchByManufacturersTextbox.click();
		return this;
	}

	@Step("Verify display of search text for Manufacturers search")
	public ShopByManufacturersPageObjects verifyDisplayOfSearchText(String searchText) throws InterruptedException {
		Thread.sleep(1000);
		By name = By.xpath("//div[contains(@id,'displayBrand')]//a[normalize-space(text())='" + searchText + "']");
		Assert.assertTrue(utils.isElementDisplayed(name), "Searched manufacturer not found");
		log.info("Verified display of search text for Manufacturers search");
		return this;
	}

	/*
	 * @Step("Select/Click item under Manufacturers") public
	 * ShopByManufacturersPageObjects clickItemUnderManufacturer(String
	 * manufacturerItemName) throws Exception { ((JavascriptExecutor)
	 * getDriver()).executeScript("arguments[0].click();",
	 * getDriver().findElement(By
	 * .xpath("//div[contains(@class,'shopbymanuf dropdown-menu')]/descendant::li/a[contains(text(),'"
	 * + manufacturerItemName + "')]")));
	 * log.info("Select/Click item under Manufacturers :" + manufacturerItemName);
	 * return this; }
	 */

	@Step("Select/Click item under Manufacturers")
	public ShopByManufacturersPageObjects clickItemUnderManufacturer(String manufacturerItemName) throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver().findElement(By.xpath(
				"//div[contains(@class,'shopbymanuf ')]//a[contains(text(),'" + manufacturerItemName.trim() + "')]")));
		log.info("Select/Click item under Manufacturers :" + manufacturerItemName);
		return this;
	}

	@Step("Verify the item which is selected via Manufacturers dropdown")
	public ShopByManufacturersPageObjects verifyItemSelectedViaManufacturers(String manufacturerItemName)
			throws Exception {
		List<WebElement> manuf = getDriver().findElements(
				By.xpath("//strong[contains(text(),'Manufacturers:')]/following-sibling::span[contains(text(),'"
						+ manufacturerItemName + "')]"));
		Assert.assertTrue(utils.isElementDisplayed(manuf.get(0)), "Brand not available");
		log.info("Verified the item which is selected via Manufacturers dropdown");
		return this;
	}

}