package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class PageHeaderObjects extends PageInitializer {

	Logger log = Logger.getLogger(PageHeaderObjects.class.getName());

	SoftAssert softAssert = new SoftAssert();

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waitingMethods = new WaitingMethods(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();

	@FindBy(css = ".cimm_logo>a>img")
	public WebElement logoLocator;

	@FindBy(id = "txtSearch")
	public WebElement searchFieldLocator;

	@FindAll({ @FindBy(xpath = "//a[contains(@data-target,'login')]"), @FindBy(css = ".dropdown-toggle") })
	public WebElement myAccountLinkLocator;

	@FindBy(xpath = "//li/a[@href='/cart']/span[1]")
	public WebElement cartLocator;

	@FindBy(xpath = "//li/a[@href='/cart']/span[2]")
	public WebElement cartCountLocator;

	@FindBy(xpath = "//a[contains(text(),'Shop Products')]")
	public WebElement shopProductsLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'Brands')])[1]")
	public WebElement brandsLinkLocator;

	@FindBy(css = ".row.brandListCont>div>div>h2")
	public List<WebElement> brandsAlpabaticLocator;

	@FindBy(css = ".row.brandListCont>div>div>div>div>a")
	public List<WebElement> allBrandsDisplayedLocator;

	@FindBy(xpath = "//p/a[@href='/brands']")
	public WebElement viewAllBrandsLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'Manuf')])[1]")
	public WebElement manufacturersLinkLocator;

	@FindBy(css = ".grid>div>h2")
	public List<WebElement> manufacturersAlphabatLinkLocator;

	@FindBy(css = ".grid>div>ul>li>a")
	public List<WebElement> manufacturersLinksBelowAlpabetLocator;

	@FindBy(xpath = "//p/a[@href='/manufacturers']")
	public WebElement viewAllManufacturersLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'Service')])[1]")
	public WebElement servicesLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'Service')])[1]/../following-sibling::ul//a")
	public List<WebElement> servicesMenuLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'Promotion')])[1]")
	public WebElement promotionsLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'Promotion')])[1]/../following-sibling::ul//a")
	public List<WebElement> promotionsMenuLinkLocator;

	// @FindBy(xpath = "(//a[contains(text(),'EVENT')])[1]")
	@FindBy(xpath = "//a[contains(text(),'Events')]")
	public WebElement eventsLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'LOCATIONS')])[1]")
	public WebElement locationsLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'ABOUT US')])[1]")
	public WebElement aboutUsLinkLocator;

	@FindBy(xpath = "(//a[contains(text(),'About')])[1]/../following-sibling::ul/li/span/a")
	public List<WebElement> aboutUsMenuLinkLocator;

	@FindBy(css = "#backToTop>a")
	public WebElement topNavigationLocator;

	@FindBy(id = "performSearchBtn")
	public WebElement searchButtonLocator;

	@FindBy(css = "a#gridView")
	public WebElement gridViewLocator;

	@FindBy(css = "a#listView")
	public WebElement listViewLocator;

	@FindBy(xpath = "//*[@id='sitePartNo']/span")
	public WebElement pnLocator;

	@FindBy(id = "selectedZipCode")
	public WebElement EnterZipCodeLocator;

	@FindBy(css = "div[class='inputWrap']>div>button")
	public WebElement withInDropdownLocator;

	@FindBy(id = "findNowButton")
	public WebElement findNowLocator;

	@FindBy(css = "#list>li:nth-child(3)>div")
	public WebElement branchLinkLocator;

	@FindBy(css = "#list>li:nth-child(2)>div")
	public WebElement headQuartersLinkLocator;

	@FindBy(css = "#list>li:nth-child(1)>a>div")
	public WebElement allLocationsLinkLocator;

	@FindBy(xpath = "//*[@id='eventCalendarInline']/div[contains(@class,'eventsCalendar')]")
	public WebElement calenderLocation;

	@FindBy(id = "day")
	public WebElement dayLinkLocator;

	@FindBy(id = "week")
	public WebElement weekLinkLocator;

	@FindBy(id = "month")
	public WebElement monthLinkLocator;

	@FindBy(css = ".calendarFilter>div:first-child")
	public WebElement eventMessageLocator;

	@FindBy(xpath = "//div[@class='cimm_category']//h4/a")
	public List<WebElement> allDisplayProductsCategory;

	@FindBy(xpath = "//a[@href='/products']/../following-sibling::ul/li/span/a")
	public List<WebElement> allDisplayProducts;

	@Step("Verify Home page title :{0}")
	public PageHeaderObjects verifyHomePageTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Shop Electrical, Plumbing, & HVAC Supplies" + " | " + productName, "Title is wrong");
		logger.info("Home Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Shop Electrical, Plumbing, & HVAC Supplies" + " | " + productName + "]");
		return this;
	}

	@Step("Verify the header section details")
	public PageHeaderObjects verifyHeaderSectionDetails() {
		log.info("verifyHeaderSectionDetails started");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(logoLocator), "Logo is not displayed :");
		log.info("Logo is verified");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(searchFieldLocator), "Search field is not displayed :");
		log.info("Search field is verified");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(myAccountLinkLocator),
				"My Account link is not displayed :");
		log.info("My Account link  is verified");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(cartLocator), "Cart link is not displayed :");
		log.info("Cart link is verified");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(shopProductsLinkLocator),
				"Shop Products menu link is not displayed :");
		log.info("Shop Products menu link  is verified");

		
		/*
		 * softAssert.assertTrue(utilityMethods.isElementDisplayed(
		 * manufacturersLinkLocator), "Manufactureres menu Link is not displayed :");
		 * log.info("Manufactureres menu Link is verified");
		 */
		 

		softAssert.assertTrue(utilityMethods.isElementDisplayed(brandsLinkLocator),
				"Brands menu link is not displayed :");
		log.info("Brands menu link is verified");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(servicesLinkLocator),
				"Services Link is not displayed :");
		log.info("Services Link is verified");

		
		/*
		 * softAssert.assertTrue(utilityMethods.isElementDisplayed(promotionsLinkLocator
		 * ), "Promotions menu link is not displayed :");
		 * log.info("Promotions menu link is verified");
		 */

		
		/*
		 * softAssert.assertTrue(utilityMethods.isElementDisplayed(eventsLinkLocator),
		 * "Events Link is not displayed :"); log.info("Events Link is verified");
		 */

		softAssert.assertTrue(utilityMethods.isElementDisplayed(locationsLinkLocator),
				"Location menu link is not displayed :");
		log.info("Location menu link is verified");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(aboutUsLinkLocator),
				"About Us link is not displayed :");
		log.info("About Us link  is verified");

		utilityMethods.scrollThePageToBottom();
		waitingMethods.waitForVisibilityOfElement(topNavigationLocator, 10);
		softAssert.assertTrue(utilityMethods.isElementDisplayed(topNavigationLocator),
				"topNavigator link is not displayed :");
		log.info("topNavigator is verified ");

		softAssert.assertAll();
		log.info("verifyHeaderSectionDetails ended");

		return this;
	}

	@Step("Verify the logo position in header section")
	public PageHeaderObjects verifyPositionOfLogo() {

		Assert.assertTrue(utilityMethods.isElementDisplayed(logoLocator), "Logo is not displayed :");
		log.info("Position Of Logo is verified");
		return this;
	}

	@Step("Click On Locations")
	public PageHeaderObjects clickOnLocationsMenu() {
		waitingMethods.waitForVisibilityOfElement(locationsLinkLocator, 5);
		locationsLinkLocator.click();
		log.info("clicked on Locations link");
		return this;
	}

	@Step("Verify Locations page title :{0}")
	public PageHeaderObjects verifyLocationsTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Locations" + " | " + productName, "Title is wrong");
		log.info("Locations Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Locations" + " | " + productName + "]");
		return this;
	}

	/*
	 * @Step("Click On Events") public PageHeaderObjects clickOnEventsMenu() {
	 * waitingMethods.waitForVisibilityOfElement(eventsLinkLocator, 5);
	 * eventsLinkLocator.click(); log.info("clicked on Events link"); return this; }
	 */

	public PageHeaderObjects verifyEventsTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Events" + " | " + productName, "Title is wrong");
		log.info("Events Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Events" + " | " + productName + "]");
		return this;
	}

	@Step("Click On Promotions menu")
	public PageHeaderObjects clickOnPromotionsMenu() {
		waitingMethods.waitForVisibilityOfElement(promotionsLinkLocator, 5);
		promotionsLinkLocator.click();
		log.info("clicked on Promotions link");
		return this;
	}

	@Step("Verify Shop By Promotions page title :{0}")
	public PageHeaderObjects verifyPromotionsTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Solution" + " | " + productName, "Title is wrong");
		log.info("Promotions Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Promotions" + " | " + productName + "]");
		return this;
	}

	@Step("Verify Links below Promotions page")
	public PageHeaderObjects verifyPromotionsMenuLinks(String linkNames) {
		String[] linkExpected = linkNames.split(",");
		utilityMethods.moveToElement(promotionsLinkLocator);
		for (int i = 0; i < promotionsMenuLinkLocator.size(); i++) {
			softAssert.assertEquals(promotionsMenuLinkLocator.get(i).getText().trim(), linkExpected[i],
					"Expected link is :" + linkExpected[i] + "Link displayed :"
							+ promotionsMenuLinkLocator.get(i).getText());
			log.info("verified for the link :" + linkExpected[i]);
		}
		softAssert.assertAll();
		log.info("Links below Promotions page is verified");
		return this;
	}

	@Step("Verify Links Title below Promotions menu")
	public PageHeaderObjects verifyPromotionsMenuLinksTitle(String linkNames, String productName) throws Throwable {
		String[] linkExpected = linkNames.split(",");
		for (int i = 0; i < promotionsMenuLinkLocator.size(); i++) {
			utilityMethods.moveToElement(promotionsLinkLocator);
			promotionsMenuLinkLocator.get(i).click();
			softAssert.assertEquals(getDriver().getTitle().trim(), linkExpected[i].trim() + " | " + productName,
					"Title is wrong");
			log.info(linkExpected[i] + "Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
					+ getDriver().getTitle().trim() + "] and " + "[" + linkExpected[i].trim() + " | " + productName
					+ "]");
			log.info("verified title for the link :" + linkExpected[i]);

			homePage().clickOnLogo();
		}
		softAssert.assertAll();
		log.info("Links below Promotions page is verified");
		return this;
	}

	@Step("Click On Services menu")
	public PageHeaderObjects clickOnServicesMenu() {
		waitingMethods.waitForVisibilityOfElement(servicesLinkLocator, 5);
		servicesLinkLocator.click();
		log.info("clicked on Services link");
		return this;
	}

	@Step("Verify Shop By Services page title :{0}")
	public PageHeaderObjects verifyServicesTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Services" + " | " + productName, "Title is wrong");
		log.info("Services Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Services" + " | " + productName + "]");
		return this;
	}

	@Step("Verify Links below Services page: {0}")
	public PageHeaderObjects verifyServicesMenuLinks(String linkNames) {
		String[] linkExpected = linkNames.split(",");
		utilityMethods.moveToElement(servicesLinkLocator);
		for (int i = 0; i < servicesMenuLinkLocator.size(); i++) {
			softAssert.assertEquals(servicesMenuLinkLocator.get(i).getText().trim(), linkExpected[i],
					"Expected link is :" + linkExpected[i] + "Link displayed :"
							+ servicesMenuLinkLocator.get(i).getText());
			log.info("verified for the link :" + linkExpected[i]);
		}
		softAssert.assertAll();
		log.info("Links below Services page is verified");
		return this;
	}

	@Step("Verify Links Title below Services menu")
	public PageHeaderObjects verifyServicesMenuLinksTitle(String linkNames, String productName) throws Throwable {
		String[] linkExpected = linkNames.split(",");
		for (int i = 0; i < servicesMenuLinkLocator.size(); i++) {
			utilityMethods.moveToElement(servicesLinkLocator);
			servicesMenuLinkLocator.get(i).click();
			softAssert.assertEquals(getDriver().getTitle().trim(), linkExpected[i].trim() + " | " + productName,
					"Title is wrong");
			log.info(linkExpected[i] + "Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
					+ getDriver().getTitle().trim() + "] and " + "[" + linkExpected[i].trim() + " | " + productName
					+ "]");
			log.info("verified title for the link :" + linkExpected[i]);

			homePage().clickOnLogo();
		}
		softAssert.assertAll();
		log.info("Links below Services page is verified");
		return this;
	}

	@Step("Verify Links below About Us page")
	public PageHeaderObjects verifyAboutUsMenuLinks(String linkNames) {
		String[] linkExpected = linkNames.split(",");
		utilityMethods.moveToElement(aboutUsLinkLocator);
		for (int i = 0; i < aboutUsMenuLinkLocator.size(); i++) {
			softAssert.assertEquals(aboutUsMenuLinkLocator.get(i).getText().trim(), linkExpected[i],
					"Expected link is :" + linkExpected[i] + "Link displayed :"
							+ aboutUsMenuLinkLocator.get(i).getText());
			log.info("verified for the link :" + linkExpected[i]);
		}
		softAssert.assertAll();
		log.info("Links below About Us page is verified");
		return this;
	}

	@Step("Verify Links below About Us page")
	public PageHeaderObjects verifyAboutUsMenuLinksTitle(String linkNames, String productName) throws Throwable {
		String[] linkExpected = linkNames.split(",");
		for (int i = 0; i < aboutUsMenuLinkLocator.size(); i++) {
			utilityMethods.moveToElement(aboutUsLinkLocator);
			aboutUsMenuLinkLocator.get(i).click();
			softAssert.assertEquals(getDriver().getTitle().trim(), linkExpected[i] + "| " + productName,
					"Title is wrong");
			log.info(linkExpected[i] + "Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
					+ getDriver().getTitle().trim() + "] and " + "[" + linkExpected[i] + "| " + productName + "]");
			log.info("verified title for the link :" + linkExpected[i]);

			homePage().clickOnLogo();
		}
		softAssert.assertAll();
		log.info("Links below About Us page is verified");
		return this;
	}

	@Step("Verify Location page details")
	public PageHeaderObjects verifyLocationPageDetails() throws InterruptedException {

		waitingMethods.waitForElementToBeClickable(withInDropdownLocator, 5);
		softAssert.assertTrue(utilityMethods.isElementDisplayed(EnterZipCodeLocator),
				"Zip code field is not displayed :");
		log.info("Enter Zipcode element presence tested");

		softAssert.assertTrue(utilityMethods.isElementDisplayed(withInDropdownLocator),
				"DropDown field is not displayed :");
		log.info("With in dropdown element presence tested");

		utilityMethods.moveToElementAndClick(withInDropdownLocator);

		// Not able to find text within dropdown when we run in headless mode.

		/*
		 * List<WebElement> allDropDownValues =
		 * getDriver().findElements(By.xpath("//ul[@role='listbox']//span[1]"));
		 * List<String> allDropDownValuesExpected = new ArrayList<String>();
		 * allDropDownValuesExpected.add("50 miles");
		 * allDropDownValuesExpected.add("10 miles");
		 * allDropDownValuesExpected.add("20 miles");
		 * allDropDownValuesExpected.add("30 miles");
		 * allDropDownValuesExpected.add("40 miles");
		 * allDropDownValuesExpected.add("100 miles"); List<String>
		 * allDropDownValuesActual = new ArrayList<String>(); for (WebElement value :
		 * allDropDownValues) { Thread.sleep(250);
		 * allDropDownValuesActual.add(value.getText());
		 * log.info("Actual values displayed :" + value.getText()); }
		 * softAssert.assertEquals(allDropDownValuesActual, allDropDownValuesExpected,
		 * "Drop down values didnt matched :");
		 * log.info("all dropdown values are verified");
		 * utilityMethods.moveToElementAndClick(withInDropdownLocator);
		 */
		/*
		 * branchLinkLocator.click(); log.info("clicked on branch link");
		 * allLocationsLinkLocator.click(); log.info("clicked on all location link");
		 * headQuartersLinkLocator.click(); log.info("clicked on head quarters link");
		 * // data from TestData.properties---> pageHeaderData,position 3
		 * EnterZipCodeLocator.sendKeys(testData[3]); log.info("zipcode is entered");
		 * findNowLocator.click(); log.info("clicked on find now link");
		 */
		softAssert.assertAll();
		log.info("Loction Page verification completed ");
		return this;
	}

	@Step("Verify events page Details ")
	public PageHeaderObjects verifyEventsPageDetails() throws Exception {

		softAssert.assertTrue(utilityMethods.isElementDisplayed(calenderLocation), "Calender is not displayed :");
		log.info("calender presence is tested");

		dayLinkLocator.click();
		log.info("clicked on day link");

		softAssert.assertTrue(
				eventMessageLocator.getText().trim().toLowerCase().endsWith("displaying events for all audiences"),
				"Event message is not displayed :");
		log.info("verified the events for the day");

		weekLinkLocator.click();
		Thread.sleep(500);

		log.info("clicked on week link");
		// System.out.println(eventMessageLocator.getText());
		softAssert.assertTrue(eventMessageLocator.getText().trim().toLowerCase().startsWith("current week "),
				"Event in Current week is not displayed :");
		log.info("verified the events for the current week");

		monthLinkLocator.click();
		log.info("clicked on month link");
		Thread.sleep(500);
		// System.out.println(eventMessageLocator.getText());
		softAssert.assertTrue(eventMessageLocator.getText().trim().toLowerCase().startsWith("month"),
				"Event in month is not displayed :");
		log.info("verified the events for the month");

		softAssert.assertAll();
		log.info("Events page details verification completed ");
		return this;
	}

	@Step("Click On About Us menu")
	public PageHeaderObjects clickOnAboutUsMenu() {
		waitingMethods.waitForVisibilityOfElement(aboutUsLinkLocator, 5);
		aboutUsLinkLocator.click();
		log.info("clicked on About Us link");
		return this;
	}

	@Step("Verify Shop By About Us page title :{0}")
	public PageHeaderObjects verifyAboutUsTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "About Us" + " | " + productName, "Title is wrong");
		log.info("About Us Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "About Us" + " | " + productName + "]");
		return this;
	}

	@Step("Click On Manufacturers menu")
	public PageHeaderObjects clickOnManufacturersMenu() {
		waitingMethods.waitForVisibilityOfElement(manufacturersLinkLocator, 5);
		manufacturersLinkLocator.click();
		log.info("clicked on Manufacturers link");
		return this;
	}

	@Step("Verify Shop By Manufacturers page title :{0}")
	public PageHeaderObjects verifyShopByManufacturersTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Shop By Manufacturers" + " | " + productName,
				"Title is wrong");
		log.info("Shop By Manufacturers Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Shop By Manufacturers" + " | " + productName + "]");
		return this;
	}

	@Step("Verify manufacturers menu")
	public PageHeaderObjects verifyManufacturersMenu() {

		List<String> alphabatManufacturerList = new ArrayList<String>();
		for (WebElement valueB : manufacturersAlphabatLinkLocator) {
			alphabatManufacturerList.add(valueB.getText());
			// System.out.println(valueB.getText());
		}

		List<String> manufacturersLinksDisplayed = new ArrayList<String>();
		for (WebElement valueBM : manufacturersLinksBelowAlpabetLocator) {
			manufacturersLinksDisplayed.add(valueBM.getText().substring(0, 1));
			// System.out.println(valueBM.getText().substring(0, 1));
		}

		for (int i = 0; i < alphabatManufacturerList.size(); i++) {
			for (int j = 0; j < manufacturersLinksDisplayed.size(); j++) {
				if (alphabatManufacturerList.get(i).contains(manufacturersLinksDisplayed.get(j))) {
					softAssert.assertEquals(alphabatManufacturerList.get(i), manufacturersLinksDisplayed.get(j),
							"Manufacturers list didnt matched :");
					// System.out.printf(allDisplayBrandsAvilable.get(i),
					// allDisplayBrandsByManufacturersAvilable.get(j));
				}
			}
		}
		softAssert.assertAll();
		log.info("verified the Manufacturers names associated with Manufacturers name starting character");
		return this;
	}

	@Step("Click On Brands menu")
	public PageHeaderObjects clickOnBrandsMenu() {
		waitingMethods.waitForVisibilityOfElement(brandsLinkLocator, 5);
		brandsLinkLocator.click();
		log.info("clicked on Brands link");
		return this;
	}

	@Step("Verify Shop By Brands page title :{0}")
	public PageHeaderObjects verifyShopByBrandsTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Shop By Brands" + " | " + productName, "Title is wrong");
		log.info("Shop By Brands Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Shop By Brands" + " | " + productName + "]");
		return this;
	}

	@Step("Verify brands menu")
	public PageHeaderObjects verifyBrandsMenu() {

		List<String> allBradAlphabetList = new ArrayList<String>();
		for (WebElement valueB : brandsAlpabaticLocator) {
			allBradAlphabetList.add(valueB.getText());
			// System.out.println(valueB.getText());
		}

		List<String> allDisplayBrandsByManufacturersAvilable = new ArrayList<String>();
		for (WebElement valueBM : allBrandsDisplayedLocator) {
			allDisplayBrandsByManufacturersAvilable.add(valueBM.getText().substring(0, 1));
			// System.out.println(valueBM.getText().substring(0, 1));
		}

		for (int i = 0; i < allBradAlphabetList.size(); i++) {
			for (int j = 0; j < allDisplayBrandsByManufacturersAvilable.size(); j++) {
				if (allBradAlphabetList.get(i).contains(allDisplayBrandsByManufacturersAvilable.get(j))) {
					softAssert.assertEquals(allBradAlphabetList.get(i), allDisplayBrandsByManufacturersAvilable.get(j),
							"Brands list didnt matched :");
				}
			}
		}
		softAssert.assertAll();
		log.info("verified the Brands names associated with Brand name starting character");
		return this;
	}

	@Step("Click On Shop products menu")
	public PageHeaderObjects clickOnShopProductsMenu() {
		waitingMethods.waitForVisibilityOfElement(shopProductsLinkLocator, 5);
		shopProductsLinkLocator.click();
		log.info("clicked on shop products link");
		return this;
	}

	@Step("Verify Product page title :{0}")
	public PageHeaderObjects verifyProductsTitle(String productName) throws InterruptedException {
		Thread.sleep(3000);
		Assert.assertEquals(getDriver().getTitle().trim(), "Products" + " | " + productName, "Title is wrong");
		log.info("Product Page Title has been verified" + " with [Actual] and [Expected] value as " + "["
				+ getDriver().getTitle().trim() + "] and " + "[" + "Products" + " | " + productName + "]");
		return this;
	}

	@Step("Verify shop products menu")
	public PageHeaderObjects verifyShopProductsMenu() {

		waitingMethods.waitForVisibilityOfElement(shopProductsLinkLocator, 5);
		shopProductsLinkLocator.click();
		utilityMethods.moveToElement(shopProductsLinkLocator);
		List<String> allDisplayProductsLinksAvilable = new ArrayList<String>();
		for (WebElement valueP : allDisplayProducts) {
			allDisplayProductsLinksAvilable.add(valueP.getText().toLowerCase().trim().substring(0, 3));
			// System.out.println(valueP.getText().toLowerCase().trim().substring(0, 4));
		}
		List<String> allDisplayedProductCategory = new ArrayList<String>();
		for (WebElement valueBM : allDisplayProductsCategory) {
			allDisplayedProductCategory.add(valueBM.getText().toLowerCase().trim().substring(0, 3));
			// System.out.println(valueBM.getText().toLowerCase().trim().substring(0, 4));
		}

		// Assert.assertEquals(allDisplayProductsLinksAvilable,
		// allDisplayedProductCategory,"Product Category list didnt matched :");
		Assert.assertTrue(allDisplayedProductCategory.containsAll(allDisplayProductsLinksAvilable),
				"Product Category list didnt matched :");

		log.info("verified all products from menu is displayed in Products category page");

		return this;
	}

}
