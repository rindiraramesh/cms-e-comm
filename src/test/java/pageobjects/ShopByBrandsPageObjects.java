package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
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

public class ShopByBrandsPageObjects extends PageInitializer {

	Actions action = new Actions(getDriver());

	Logger log = Logger.getLogger(ShopByBrandsPageObjects.class);

	@FindBy(xpath = "(//h4[contains(text(),'Brands')]/following-sibling::span/ancestor::dt/following-sibling::dd)[1]/descendant::li/a")
	public List<WebElement> allBrandsUnderBrandsDropdown;

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utils = new UtilityMethods(getDriver());

	@FindBy(xpath = "//h1[contains(text(),'Shop By Brands')]")
	private WebElement shopByBrandsHeading;

	@FindBy(xpath = "//div[contains(@class,'cimm_brandAtoZletters')]/descendant::li/a")
	private List<WebElement> listOfAlphabets;

	@FindBy(xpath = "//div[@id='displayBrand']/descendant::li/a")
	private List<WebElement> listOfBrandsUnderEveryAlphabet;

	@FindBy(xpath = "//ul[@class='cimm_breadcrumbs']/descendant::i[contains(@class,'home')]")
	private WebElement homeIconInBreadcrumbLocator;

	@FindBy(xpath = "//ul[@class='cimm_breadcrumbs']/descendant::h2[contains(text(),'Shop By Brands')]")
	private WebElement shopByBrandsBreadcrumbLocator;

	@FindBy(xpath = "//ul[@class='nav navbar-nav']//a[normalize-space(text())='Brands']")
	private WebElement brandsLink;

	@FindBy(xpath = "//div[contains(@class,'shopbybrand dropdown-menu')]//li")
	private List<WebElement> brandsDropdownLinks;

	@FindBy(xpath = "//li//a[@href='/brands' and text()='View All Brands']")
	private WebElement viewAllBrandsLink;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	public List<WebElement> breadCrumbs;

	@FindBy(xpath = "//div[@class='col-md-12 col-xs-12']//a")
	private WebElement alphabetSearchText;

	@FindBy(xpath = "//input[@id='inputSearch']")
	private WebElement searchByBrandsTextbox;

	@FindBy(xpath = "//div[@class='grid']/descendant::a[contains(text(),'Honeywell Safety')]")
	private WebElement brandsSearchText;

	@Step("Verify shop by brands page name")
	public ShopByBrandsPageObjects verifyShopByBrandsPageName() {
		waiting.waitForVisibilityOfElement(shopByBrandsHeading, 10);
		Assert.assertTrue(shopByBrandsHeading.isDisplayed(), "shop by brands heading is not displayed.");
		log.info("Verified Shop By Brands Page Name");
		return this;
	}

	@Step("Verify shop by brand breadcrump")
	public ShopByBrandsPageObjects verifyShopByBreadcrumb(String shopByBrandBreadcrumb) throws Exception {
		waiting.waitForVisibilityOfElements(breadCrumbs, 10);
		Assert.assertTrue(
				breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("|", "").trim().equals(shopByBrandBreadcrumb),
				"Breadcrump is not Shop By Brands. It is "
						+ breadCrumbs.get(breadCrumbs.size() - 1).getText().replace("|", "").trim());
		log.info("Verified Shop By Brands Breadcrumb");
		return this;
	}

	@Step("Verify page title of shop by brand to have {0}")
	public ShopByBrandsPageObjects verifyTitleOfShopByBrand(String shopByBrandBreadcrumb, String productName)
			throws Exception {
		Assert.assertTrue(
				getDriver().getTitle().trim().equalsIgnoreCase(shopByBrandBreadcrumb + " | " + productName.trim()),
				"Title is " + getDriver().getTitle().trim() + " Asserting with data " + shopByBrandBreadcrumb + " | "
						+ productName.trim());
		log.info("Verified the page title of clicked brand");
		return this;
	}

	@Step("Click on every alphabet and verify the first letter of all the options displayed.")
	public ShopByBrandsPageObjects clickOnEveryAlphabetAndCheckTheFirstLetterOfTheRespectiveBrands() throws Exception {
		try {
			waiting.waitForVisibilityOfElements(listOfAlphabets, 10);
			for (int i = 0; i < listOfAlphabets.size(); i++) {

				((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", listOfAlphabets.get(i));
				Thread.sleep(4000);

				{
					for (int j = 0; j < listOfBrandsUnderEveryAlphabet.size(); j++) {
						waiting.waitForVisibilityOfElements(listOfBrandsUnderEveryAlphabet, 20);
						Assert.assertTrue(
								listOfBrandsUnderEveryAlphabet.get(j).getText().trim().startsWith(
										listOfAlphabets.get(i).getText().trim()),
								"Brand does not start with aphabet chosen. Brand name is - "
										+ listOfBrandsUnderEveryAlphabet.get(j).getText().trim()
										+ " and alphabet chosen is " + listOfAlphabets.get(i).getText().trim() + ".");
					}
				}
			}
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			clickOnEveryAlphabetAndCheckTheFirstLetterOfTheRespectiveBrands();
		}
		return this;
	}

	@Step("Click on brands toggle button")
	public ShopByBrandsPageObjects clickOnBrandsToggleButton() {
		productListPage().filterBrandsDropdownToggleButtonLocator.click();
		log.info("Clicked on Brands toggle button");
		return this;
	}

	@Step("Click on {0} st/nd/rd brand")
	public ShopByBrandsPageObjects clickOnSpecificBrand(int specificBrand) throws Exception {
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				allBrandsUnderBrandsDropdown.get(specificBrand - 1));
		log.info("Clicked on specific brand");
		return this;
	}

	public String getNameOfTheSpecificBrand(int specificBrand) {
		String nameOfTheBrandString = allBrandsUnderBrandsDropdown.get(specificBrand - 1).getAttribute("href");
		String nameOfTheBrandSubString = nameOfTheBrandString.substring(nameOfTheBrandString.lastIndexOf("/") + 1);
		return nameOfTheBrandSubString;
	}

	public String getNameOfTheSpecificBrand(String specificBrandname) {
		String nameOfTheBrandString = getDriver().findElement(
				By.xpath("//dt[contains(text(),'Brands')]/following-sibling::dd/descendant::a[contains(@href,'"
						+ specificBrandname + "')]"))
				.getAttribute("href");
		String nameOfTheBrandsubString = nameOfTheBrandString.substring(nameOfTheBrandString.lastIndexOf("/") + 1);
		return nameOfTheBrandsubString;
	}

	@Step("Click on {0} st/nd/rd brand")
	public ShopByBrandsPageObjects clickOnSpecificBrand(String specificBrandname) throws Exception {
		Thread.sleep(2000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				getDriver().findElement(
						By.xpath("//dt[contains(text(),'Brands')]/following-sibling::dd/descendant::a[contains(@href,'"
								+ specificBrandname + "')]")));
		log.info("Clicked on Brand:" + specificBrandname);
		return this;
	}

	@Step("Click on specific alphabet :{0}")
	public ShopByBrandsPageObjects clickOnSpecificAlphabet(String specificAlphabetToClickInShopByBrandsPage)
			throws Throwable {
		getDriver().findElement(By.xpath("//a[text()='" + specificAlphabetToClickInShopByBrandsPage + "']")).click();
		log.info("Clicked on the specific alphabet :" + specificAlphabetToClickInShopByBrandsPage);
		Thread.sleep(250);
		log.info("Clicked on Alphabet:" + specificAlphabetToClickInShopByBrandsPage);
		return this;

	}

	@Step("Click on specific brand {0}")
	public ShopByBrandsPageObjects clickOnSpecificBrandUnderAlphabets(String specificBrandname)
			throws InterruptedException {
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver().findElement(
				By.xpath("//div[@id='displayBrand']/descendant::li/a[text()='" + specificBrandname + "']")));
		log.info("Clicked on Brand:" + specificBrandname);
		return this;
	}

	public ShopByBrandsPageObjects clickOnHomeIconInBreadcrumb() {
		homeIconInBreadcrumbLocator.click();
		log.info("Clicked on Home icon in breadcrumb");
		return this;
	}

	public ShopByBrandsPageObjects verifyDisplayOfHomeIconInBreadcrumb() {
		Assert.assertTrue(homeIconInBreadcrumbLocator.isDisplayed(), "Home Icon is not displayed in the breadcrumb.");
		return this;
	}

	public ShopByBrandsPageObjects clickOnShopByBrandsCrumb() {
		shopByBrandsBreadcrumbLocator.click();
		return this;
	}

	@Step("Hover over brands link")
	public ShopByBrandsPageObjects hoverOverBrandsLink() throws InterruptedException {
		try {
			Thread.sleep(3000);
			waiting.waitForVisibilityOfElement(brandsLink, 8);
			action.moveToElement(brandsLink).build().perform();
			log.info("hoverOver the brands link");
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			hoverOverBrandsLink();
		}
		Thread.sleep(2000);
		log.info("hover over brands link");
		return this;
	}

	@Step("Click on Brands link")
	public ShopByBrandsPageObjects clickOnBrandsLink() throws Exception {
		Thread.sleep(3000);
		brandsLink.click();
		log.info("Click ob brands link");
		return this;
	}

	/*
	 * @Step("verify brands dropdown and VIEW ALL BRANDSS link") public
	 * ShopByBrandsPageObjects verifyBrandsDropdown() {
	 * waiting.waitForVisibilityOfElements(brandsDropdownLinks, 10); for (WebElement
	 * brandsLinkDropdown : brandsDropdownLinks) {
	 * Assert.assertTrue(utils.isElementDisplayed(brandsLinkDropdown),
	 * "Brands dropdown Links are not displayed."); }
	 * log.info("Brands all links are verified");
	 * Assert.assertTrue(utils.isElementDisplayed(viewAllBrandsLink),
	 * "View All Manufacturers link is not displayed."); return this; }
	 */

	@Step("Verify brands dropdown and VIEW ALL BRANDSS link")
	public ShopByBrandsPageObjects verifyBrandsDropdown() {
		waiting.waitForVisibilityOfElements(brandsDropdownLinks, 10);
		for (WebElement brandsLinkDropdown : brandsDropdownLinks) {
			Assert.assertTrue(utils.isElementDisplayed(brandsLinkDropdown), "Brands dropdown Links are not displayed.");
		}
		log.info("Brands all links are verified");
		Assert.assertTrue(utils.isElementDisplayed(viewAllBrandsLink), "View All Brands link is not displayed.");
		return this;
	}

	@Step("Click on Brands link/menu")
	public ShopByBrandsPageObjects clickOnBrandsLinkInHeader() throws InterruptedException {
		Thread.sleep(1500);
		waiting.waitForVisibilityOfElement(brandsLink, 5);
		brandsLink.click();
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", brandsLink);
		log.info("Clicked on brands link");
		return this;
	}

	public ShopByBrandsPageObjects clickOnSpecificAlphabetInBrandsPage(
			String specificAlphabetToClickInShopByBrandsPage) {
		getDriver().findElement(By.xpath("//a[text()='" + specificAlphabetToClickInShopByBrandsPage + "']")).click();
		Assert.assertTrue(alphabetSearchText.isDisplayed(), "Specific alphabet search related Text is not displayed");
		log.info("Click on specific alphabet :" + specificAlphabetToClickInShopByBrandsPage);
		return this;
	}

	@Step("Enter search text:{0} in 'Search By Brands' textbox")
	public ShopByBrandsPageObjects searchText(String searchText) throws Exception {
		waiting.waitForVisibilityOfElement(searchByBrandsTextbox, 15);
		Thread.sleep(500);
		searchByBrandsTextbox.clear();
		searchByBrandsTextbox.sendKeys(searchText);
		log.info("Entered search text :" + searchText);
		/*
		 * searchByBrandsTextbox.click(); log.info("clicked on search ");
		 */
		return this;
	}

	@Step("Verify display of search text for brands search")
	public ShopByBrandsPageObjects verifyDisplayOfSearchText(String searchText) throws InterruptedException {
		WebElement foundTextWebElement = getDriver()
				.findElement(By.xpath("//div[contains(@class,'brandListCont')]//a[text()='" + searchText + "']"));
		utils.scrollTillWebElement(foundTextWebElement);
		Assert.assertEquals(foundTextWebElement.getText().trim(), searchText);
		log.info("verified display of search text for brands search");
		return this;
	}

	@Step("Select/Click item under Brands")
	public ShopByBrandsPageObjects clickItemUnderBrands(String searchText) throws Exception {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver().findElement(By.xpath(
				"//div[contains(@class,'shopbybrand')]//a[normalize-space(text())='" + searchText.trim() + "']")));
		log.info("Select/Click item under Brands :" + searchText);
		return this;
	}

	@Step("Verify the item which is selected via Brands dropdown")
	public ShopByBrandsPageObjects verifyItemSelectedViaBrands(String searchText) throws Exception {
		List<WebElement> brand = getDriver()
				.findElements(By.xpath("//strong[contains(text(),'Brand:')]/following-sibling::span[contains(text(),'"
						+ searchText + "')]"));
		Assert.assertTrue(utils.isElementDisplayed(brand.get(0)), "Brand not available");
		log.info("verified the item which is selected via Brands dropdown");
		return this;
	}
}