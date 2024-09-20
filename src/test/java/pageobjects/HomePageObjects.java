package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.MainOperations;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class HomePageObjects extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	Actions action = new Actions(getDriver());
	
	MainOperations mainOperations = new MainOperations();



	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	String faviconURL = "//link[@href='/ASSETS/WEB_THEMES//ECOMMERCE_STD_TEMPLATE_V2/images/favicon.ico']";

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(HomePageObjects.class);

	@FindBy(xpath = "(//a[normalize-space(text())='Log Out'])[1]")
	public WebElement logoutButton;

	@FindBy(xpath = "//div[@class='cimm_headerTopLinks']//a[contains(text(),'Logout')]")
	public WebElement logoutButtonForAddNewCreditCard;

	@FindBy(id = "pLoginErr")
	private WebElement errorMsgLocator;

	@FindBy(how = How.XPATH, using = "//a[text()='Parts']")
	private WebElement partsLinkLocator;

	@FindBy(xpath = "//a[@data-target='loginDropDown']")
	private WebElement loginLinkLocator;

	@FindBy(xpath = "//input[@id='txtSearch']")
	private WebElement searchTextbox;

	@FindBy(xpath = "//div[@class='cimm_headerTopLinks']//a[contains(text(),'Login')]")
	private WebElement loginLinkLocatorForNewCreditCard;

	@FindBy(xpath = "//li[@class='ui-menu-item']")
	private List<WebElement> searchResultsLocator;

	@FindBy(xpath = "//button[@id='performSearchBtn']")
	private WebElement searchButton;

	@FindBy(xpath = "//a[text()='Exit']")
	private WebElement exitButtonLocator;

	@FindBy(xpath = "//table[@id='usersList']//th")
	private List<WebElement> headerLocatorOfUserListTable;

	@FindBy(xpath = "//div[@class='modal-body']/h3")
	private WebElement messageLocatorForNoCustomerAssignRole;

	@FindBy(xpath = "//ul[@class='dropdown-menu myAccountMenu']//a[@href='/Dashboard']")
	private WebElement myAccountLink;

	@FindBy(xpath = "//a[contains(text(),'My Account')]")
	private WebElement myAccountInHeader;

	@FindBy(xpath = "//img[@itemprop='logo']")
	private WebElement logo;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']//a[@href='/SavedGroups/ApproveCart']")
	private WebElement approvalCartListLinkLocator;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']//a[@href='/UserManagement']")
	private WebElement userManagementLinkLocator;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']//a[@href='/QuickCartStandard']")
	private WebElement quickOrderPadLink;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']/li/a")
	private List<WebElement> myAcountDropdownLinksLocator;

	@FindBy(xpath = "//ul[@id='featuredBrands']")
	private WebElement featuredBrandsList;

	@FindBy(xpath = "//ul[@id='featuredManufacturers']")
	private WebElement featuredManufacturers;

	@FindBy(xpath = "//span[@class='foot_copy halfBlokWrap']")
	private WebElement copyRightsOfUnilog;

	@FindBy(xpath = "//div[@class='slick-list draggable']/div/li/a/img")
	private List<WebElement> carouselImages;

	@FindBy(xpath = "//table[@id='customersList']//th")
	private List<WebElement> tableHeaderLocatorsForSalesAdminAndRep;

	@FindBy(className = "cartCountDisplayLi")
	private WebElement cartCountLink;

	@FindBy(className = "cartTotalDisplayLi")
	private WebElement cartTotalLink;

	@FindBy(xpath = "//a[contains(.,'Sign Up')]")
	private WebElement signUpLink;

	@FindBy(css = "a[href='/SavedGroups/Products']")
	private WebElement myProductGroupsLinkInUserDropdown;

	@FindBy(css = "ul[class='clearAfter']>li>a[href='/Brands']")
	private WebElement brandsLink;

	@FindBy(css = "ul[class='clearAfter']>li>a[href='/Manufacturers']")
	private WebElement manufacturersLink;

	@FindBy(xpath = "//div[@id='normalHead']//a[@href='/products']")
	private WebElement shopProductLinkLocator;

	@FindBy(xpath = "//div[@class='cimm_header_mid']//b[contains(text(),'Sales Admin:')]")
	private WebElement salesAdminWelcomeMessageLocator;

	@FindBy(xpath = "//th[text()='First Name']/ancestor::thead/following-sibling::tbody//td[2]")
	private WebElement usersFirstNameLocators;

	@FindBy(xpath = "//table[@id='usersList']//tbody/tr/td/button")
	private List<WebElement> selectButtonLocatorsForUserList;

	@FindBy(xpath = "//span[@data-automation='welcome']")
	private WebElement userWelcomeMessageLocator;

	@FindBy(xpath = "//table[@id='customersList']//tbody/tr/td/button")
	private List<WebElement> selectButtonLocators;

	@FindBy(xpath = "//table[@id='customersList']//tbody/tr")
	private List<WebElement> listOfCustomerLocators;

	@FindBy(xpath = "//h3[contains(text(),'Shop By Brands')]")
	private WebElement shopByBrandsHeading;

	@FindBy(xpath = "//p[contains(text(),'Use the Shop By Brands to choose the particular brand related product list.')]")
	private WebElement brandsDropdownInstructions;

	@FindBy(xpath = "//li[@id='brandLink']/descendant::li/a")
	private List<WebElement> brandDropdownLinks;

	@FindBy(xpath = "//li[@id='manufacturerLink']/descendant::li/a")
	private List<WebElement> manufacturersDropdownLinks;

	@FindBy(xpath = "//div[@class='cimm_shopByBrand']/descendant::a[@href='/Brands']")
	private WebElement viewAllBrandsLink;

	@FindBy(xpath = "//div[@class='cimm_shopByManufacturer']/descendant::a[@href='/Manufacturers']")
	private WebElement viewAllManufacturersLink;

	@FindBy(xpath = "//a[@href='Products']")
	private WebElement productsLink;

	@FindBy(xpath = "//ul[@class='cimm_signWrap']/descendant::a[@href='/SavedGroups/Cart']")
	private WebElement mySaveCartLink;

	@FindBy(xpath = "//a[@id='selectButton']")
	private WebElement useSelectedAddressButton;

	@FindBy(xpath = "//a[@id='productId']")
	private List<WebElement> shipToNameInSelectShippingPopUp;

	@FindBy(xpath = "//span[text()='Select Ship To']/..")
	private List<WebElement> shipToNameInSelectShippingPopUpForCreditCardModule;

	@FindBy(xpath = "//ul[@class='cimm_signWrap cimm_signWrapSpace']/descendant::a[contains(text(),'Sign Up')]")
	private WebElement signUpLinkLocator;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='Services']")
	private WebElement servicesLink;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']//a[@href='/OrderHistory']")
	private WebElement orderHistoryLink;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='Industries Served']")
	private WebElement industriesServedLink;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='Events']")
	private WebElement eventsLink;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='About Us']")
	private WebElement aboutUsLink;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='Contact Us']")
	private WebElement contactUsLink;

	@FindBy(xpath = "//h2[@class='cimm_pageTitle']")
	private WebElement pageNameLocator;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']//a[contains(@href,'OpenOrder')]")
	private WebElement openOrdersLinkLocator;

	@FindBy(xpath = "//div[@class='searchZero']//p[contains(text(),'We found 0 results')]")
	private WebElement invalidSearchResultLocator;
	
	@FindBy(xpath = "//div[@class='searchZero']//*[contains(text(),'We found 0 results')]")
	private WebElement invalidNarrowSearchResultLocator;

	@FindBy(xpath = "//div[@class='ac_results']/descendant::strong")
	private List<WebElement> autoCompleteResultsLocator;

	@FindBy(xpath = "//ul[@class='cimm_myAccountMenu']/descendant::a[contains(@href,'ManagePurchaseAgent')]")
	private WebElement managePurchasingAgentLinkLocator;

	@FindBy(xpath = "//ul[@class='cimm_myAccountMenu']/descendant::a[contains(text(),'Request for Quote')]")
	private WebElement requestForQuoteLinkLocator;

	@FindBy(css = "a[href='/EditContactInfo']")
	private WebElement editContactLinkLocator;

	@FindBy(css = "a[href='/DisablePurchaseAgent'")
	private WebElement disablePurchaseAgentLinkLocator;

	@FindBy(xpath = "//ul[contains(@class,'myAccountMenu')]/descendant::a[contains(text(),'Approval Cart List')]")
	private WebElement approveCartListLinkLocator;

	@FindBy(xpath = "//div[contains(@class,'footerCol')]/descendant::a")
	private List<WebElement> footerLinksLocator;

	@FindBy(xpath = "//a[text()='Home']")
	private WebElement homeLinkLocator;

	@FindBy(xpath = "//a[@data-target='myAccountMenu']")
	public WebElement userAccountDropdown;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']/li/a[contains(@href,'OpenOrder')]")
	public WebElement openOrderLink;

	@FindBy(xpath = "//ul[@class='myAccountMenu dropdown-menu']/li/a[contains(@href,'/AccountInquiryUnit.action')]")
	public WebElement accountInquiryLinkLocator;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='Divisions']")
	private WebElement divisionsLinkLocator;

	@FindBy(xpath = "//ul[contains(@class,'headerNavBar')]/descendant::a[text()='Locations']")
	private WebElement locationsLinkLocator;

	//@FindBy(xpath = "//ul[@class='cimm_signWrap']//a[@href='/Cart']")
	@FindBy(xpath="//ul[@class='cimm_signWrap']//a[contains(@data-target,'art')]")
	private WebElement cartIconLocator;

	@FindBy(xpath = "//ul[contains(@class,'header')]/descendant::a[contains(text(),'Divisions')]")
	private WebElement divisionsLinkInHeaderLocator;

	@FindBy(xpath = "//ul[contains(@class,'header')]/descendant::a[contains(text(),'Contact Us')]")
	private WebElement contactUsInHeaderLocator;

	@FindBy(xpath = "//ul[contains(@class,'myAccountMenu')]/descendant::a[text()='Change Password']")
	private WebElement changePasswordLinkLocator;

	@FindBy(xpath = "//a[text()='Products']/following-sibling::ul/li/a")
	private List<WebElement> levelOneCategoriesUnderProductsLinkLocator;

	@FindBy(xpath = "//ul[@class='cimm_myAccountMenu']/descendant::a[text()='Edit Contact']")
	private WebElement editContactInfoInUserAccountDropdownLocator;

	@FindBy(xpath = "//div[@class='ac_results']/descendant::li[not(contains(@class,'catheader')) and not(contains(@class,'suggest'))]/descendant::strong")
	private List<WebElement> autoSuggestListHighlightedCharactersLocator;

	@FindBy(xpath = "//div[@class='userProfile']/p")
	private WebElement userNameLabel;

	@FindBy(xpath = "//div[@class='errMsg']/span")
	private WebElement errorMsgLocator_LoginPage;

	@FindBy(xpath = "//b[normalize-space(text())='Welcome:']")
	private WebElement welcomeMsgLocator;
	
	

	@FindBy(xpath = "//div[@data-widget-name='FeaturedProductGroups']/div[1]/h2/following-sibling::ul//li[@role='option']")
	public List<WebElement> multipleFeaturedProductsTabItemLocator;

	@FindBy(xpath = "//div[@data-widget-name='FeaturedProductGroups']/div[1]/h2/following-sibling::ul/button[@aria-label='Next']")
	public WebElement multipleFeaturedProductsTabPlayButtonRightLocator;

	@FindBy(xpath = "//div[@data-widget-name='FeaturedProductGroups']/div[1]/h2/following-sibling::ul/button[@aria-label='Previous']")
	public WebElement multipleFeaturedProductsTabPlayButtonLeftLocator;

	@FindBy(xpath = "//div[@data-widget-name='FeaturedProductGroups']")
	public WebElement multipleFeatureProductGroupLocator;

	@FindBy(xpath = "//a[normalize-space(text())='Shop Products']")
	private WebElement productsLinkLocator;

	@FindBy(xpath = "//li[@class='dropdown']//a[normalize-space(text())='Manufacturers']")
	private WebElement manufacturersLinkLocator;

	@FindBy(xpath = "//div[@class='ac_results']")
	private WebElement autoSuggestBlock;

	@FindBy(xpath = "//a[normalize-space(text())='More Results']")
	private WebElement showMoreOptionsAutoSuggest;

	@FindBy(xpath = "//label[normalize-space(text())='Recently Viewed Items']/..")
	public WebElement recentlyViewedTabItemLocator;

	@FindBy(xpath = "//*[@id='recentlyViewedContent']/div/div/li/div/div[2]//a")
	public List<WebElement> recentlyViewedItemLocator;

	@FindBy(xpath = "//*[@id='recentlyViewedContent']/button[2]")
	public WebElement recentlyViewedPlayButtonRightLocator;

	@FindBy(xpath = "//*[@id='recentlyViewedContent']/button[1]")
	public WebElement recentlyViewedPlayButtonLeftLocator;

	@FindBy(xpath = "//*[@id='cimm_static_block']/div[1]/div[4]/div/div/div")
	public WebElement featuredProductsTabLocator;

	// @FindBy(xpath = "//div[@data-select='widget' and @data-widget-name='Featured
	// Product']/div/ul/div/div/li[@role='option']")
	@FindBy(xpath = "//ul[contains(@class,'featuredProductGroupList_2')]//li[@role='option']")
	public List<WebElement> featuredProductsTabItemLocator;

	// @FindBy(xpath = "//div[@data-select='widget' and @data-widget-name='Featured
	// Product']/div/ul/button[@aria-label='Next']")
	@FindBy(xpath = "//ul[contains(@class,'featuredProductGroupList_2')]//button[@aria-label='Next']")
	public WebElement featuredProductsTabPlayButtonRightLocator;

	// @FindBy(xpath = "//div[@data-select='widget' and @data-widget-name='Featured
	// Product']/div/ul/button[@aria-label='Previous']")
	@FindBy(xpath = "//ul[contains(@class,'featuredProductGroupList_2')]//button[@aria-label='Previous']")
	public WebElement featuredProductsTabPlayButtonLeftLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Brands']/../following-sibling::ul")
	public WebElement featuredBrandsTabLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Brands']/../following-sibling::ul//li//img")
	public List<WebElement> featuredBrandsTabItemLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Brands']/../following-sibling::ul//button[normalize-space(text())='Next']")
	public WebElement featuredBrandsTabPlayButtonRightLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Brands']/../following-sibling::ul//button[normalize-space(text())='Previous']")
	public WebElement featuredBrandsTabPlayButtonLeftLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Manufacturers']/../following-sibling::ul")
	public WebElement featuredManufacturersTabLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Manufacturers']/../following-sibling::ul//li//img")
	public List<WebElement> featuredManufacturersTabItemLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Manufacturers']/../following-sibling::ul//button[normalize-space(text())='Next']")
	public WebElement featuredManufacturersTabPlayButtonRightLocator;

	@FindBy(xpath = "//label[normalize-space(text())='Featured Manufacturers']/../following-sibling::ul//button[normalize-space(text())='Previous']")
	public WebElement featuredManufacturersTabPlayButtonLeftLocator;

	@FindBy(xpath = "//ul[@class='cimm_signWrap']/descendant::a[@href='/ChangePassword']")
	private WebElement changePasswordLink;

	// @FindBy(xpath = "//a[@href='/products']/../following-sibling::ul/li/span/a")
	@FindBy(xpath = "//ul[contains(@class,'navbar-nav')]//ul[contains(@class,'menu')]/li[@class='sec']/span/a")
	private List<WebElement> Level1ProductCategoryHeaderMenuLinks;

	public void verifyAllLocatorsInHomePage() {

		softAssert.assertTrue(utilityMethods.isElementDisplayed(logo));
		softAssert.assertTrue(utilityMethods.isElementDisplayed(loginLinkLocator));
		softAssert.assertAll();

	}

	@Step("Navigate to my product groups page")
	public MyProductGroupsPageObjects navigateToMyProductGroups() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myProductGroupsLinkInUserDropdown);
		log.info("Navigate to my product groups page");
		return productGroupsPage();
	}

	
	
	@Step("Click On Shop Product Link.")
	public HomePageObjects clickOnProductsLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(productsLinkLocator, 8);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", productsLinkLocator);
		log.info("Clicked On Shop Product Link");
		Thread.sleep(1200);
		return this;
	}

	public boolean checkForUseThisAddress() {
		try {
			homePage().verifyUseThisAddressButtonIsDisplayedAndIfDisplayedClickIt();
			return true;
		} catch (Exception e) {
			return true;
		}
	}

	public boolean checkForUseThisAddressForAddNewCreditCardModule() {
		try {
			homePage().verifyUseThisAddressButtonIsDisplayedAndIfDisplayedClickItForAddNewCreditCardModule();
			log.info("Selected address");
			return true;
		} catch (Exception e) {
			return true;
		}
	}

	@Step("Verify welcome msg for logged in user")
	public HomePageObjects verifyWelcomeMsgAfterLogin() throws Exception {
		Thread.sleep(5000);
		waiting.waitTillPageLoads();
		Assert.assertTrue(welcomeMsgLocator.isDisplayed(), "User login unsuccessful");
		log.info("User is successfully logged in");
		return this;
	}
	


	@Step("Verify welcome msg for user")
	public HomePageObjects verifyWelcomeMsg() throws Exception {
		try {
			Assert.assertTrue(checkForUseThisAddress());
			waiting.waitForVisibilityOfElement(userAccountDropdown, 3);
			Assert.assertTrue(userAccountDropdown.isDisplayed(), "user dropdown is not displayed");
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			verifyWelcomeMsg();
		}
		log.info("Welcome message is verified");
		return this;

	}

	@Step("Verify error message {0}")
	public HomePageObjects assertForErrorMessages(String expectedMsg) {
		waiting.waitForVisibilityOfElement(errorMsgLocator, 10);
		Assert.assertEquals(errorMsgLocator.getText().trim(), expectedMsg,
				errorMsgLocator.getText().trim() + " is displayed");
		log.info("Verified error message " + expectedMsg);
		return this;
	}

	@Step("Click on Logout Button")
	public HomePageObjects clickOnLogout() throws InterruptedException {
		Thread.sleep(1300);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logoutButton);
		log.info("Clicked on Logout button");
		return this;
	}

	@Step("Click on clickOnLogout of Add New Credit Card Module.")
	public HomePageObjects clickOnLogoutForAddNewCreditCard() throws InterruptedException {
		log = Logger.getLogger("verifyMyAccountDropdown");
		Thread.sleep(1300);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logoutButtonForAddNewCreditCard);
		log.info("Clicked on clickOnLogout of Add New Credit Card Module.");
		return this;
	}

	@Step("Verify welcome message has {0} as the username")
	public HomePageObjects verifyWelcomeMsg(String expectedMsg) {
		checkForUseThisAddress();
		try {
			waiting.waitForVisibilityOfElement(userAccountDropdown, 20);
			Assert.assertEquals(userAccountDropdown.getText().trim(), expectedMsg);
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			verifyWelcomeMsg(expectedMsg);
		}
		return this;
	}

	@Step("Hover over brands link")
	public HomePageObjects hoverOverBrandsLink() {
		try {
			action.moveToElement(brandsLink).build().perform();
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			hoverOverBrandsLink();
		}
		log.info("Hover over brands link");
		return this;
	}

	@Step("Click on the {0} st/nd/3rd brand")
	public HomePageObjects clickOnASpecificBrand(int specificBrand) {
		brandDropdownLinks.get(specificBrand - 1).click();
		log.info("Clicked on link" + specificBrand);
		return this;
	}

	@Step("Click on the {0} st/nd/3rd manufacturer")
	public HomePageObjects clickOnASpecificManufacturer(int specificManufacturer) {
		manufacturersDropdownLinks.get(specificManufacturer - 1).click();
		log.info("Clicked on Manufacturer link " + specificManufacturer);
		return this;
	}

	@Step("Get link name: {0}")
	public String getSpecificBrandLinkName(int specificBrand) {
		waiting.waitForVisibilityOfElements(brandDropdownLinks, 10);
		String brandName = brandDropdownLinks.get(specificBrand - 1).getText().trim();
		log.info("Link name is :" + brandName);
		return brandName;
	}

	@Step("Get Manufacturer name: {0}")
	public String getSpecificManufacturersLinkName(int specificManufacturer) {
		waiting.waitForVisibilityOfElements(manufacturersDropdownLinks, 10);
		String brandName = manufacturersDropdownLinks.get(specificManufacturer - 1).getText().trim();
		log.info("Link name is :" + brandName);
		return brandName;
	}

	@Step("Hover over manufacturers link")
	public HomePageObjects hoverOverManufacturersLink() {
		try {
			action.moveToElement(manufacturersLink).build().perform();
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			hoverOverManufacturersLink();
		}
		log.info("Hover over manufacturers link");
		return this;
	}

	@Step("Verify manufacturers dropdown")
	public HomePageObjects verifyManufacturersDropdown() {
		waiting.waitForVisibilityOfElements(manufacturersDropdownLinks, 10);
		for (WebElement manufacturerLinkDropdown : manufacturersDropdownLinks) {
			Assert.assertTrue(manufacturerLinkDropdown.isDisplayed(),
					"Manufacturers dropdown Links are not displayed.");
		}
		Assert.assertTrue(viewAllManufacturersLink.isDisplayed(), "View All Manufacturers link is not displayed.");
		log.info("Verified manufacturers dropdown");
		return this;
	}

	@Step("Verify use this address button locator")
	public HomePageObjects verifyUseThisAddressButtonIsDisplayedAndIfDisplayedClickIt() throws Exception {
		waiting.waitForVisibilityOfElements(shipToNameInSelectShippingPopUp, 3);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				shipToNameInSelectShippingPopUp.get(0));
		log.info("Selected the address");
		/*
		 * waiting.waitForVisibilityOfElement(useSelectedAddressButton, 2);
		 * ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		 * useSelectedAddressButton);
		 * log.info("Clicked on the Selected Address button");
		 */
		return this;
	}

	@Step("Verify use this address button locator for add new credit card module.")
	public HomePageObjects verifyUseThisAddressButtonIsDisplayedAndIfDisplayedClickItForAddNewCreditCardModule()
			throws Exception {
		waiting.waitForVisibilityOfElements(shipToNameInSelectShippingPopUpForCreditCardModule, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				shipToNameInSelectShippingPopUpForCreditCardModule.get(0));
		Thread.sleep(1800);
		log.info("Selected the address");
		return this;
	}

	@Step("Verify placeholder of search textbox")
	public HomePageObjects verifyPlaceHolderOfSearchTextbox(String expectedSearchTextboxPlaceholder) {
		waiting.waitForVisibilityOfElement(searchTextbox, 8);
		Assert.assertEquals(searchTextbox.getAttribute("value").trim(), expectedSearchTextboxPlaceholder);
		log.info("Verified placeholder of search textbox");
		return this;
	}

	@Step("Verify display of search textbox and search button")
	public HomePageObjects verifyDisplayOfSearchTextboxAndButton() {
		softAssert.assertTrue(searchTextbox.isDisplayed(), "Search Textbox is not displayed");
		softAssert.assertTrue(searchButton.isDisplayed(), "Search Button is not displayed");
		softAssert.assertAll();
		log.info("Verified display of search textbox and search button");
		return this;

	}

	@Step("Verify whether the invalid search message contains We found 0 results for  {0} . You may try some other search ")
	public HomePageObjects verifyMessageForInvalidSearchData(String searchTextForInvalidTestData) {
		waiting.waitForVisibilityOfElement(invalidSearchResultLocator, 10);
		Assert.assertTrue(utilityMethods.isElementDisplayed(invalidSearchResultLocator), "Search is successful");
		log.info("Verified the invalid search message contains We found 0 results for  " + searchTextForInvalidTestData
				+ " . You may try some other search");
		return this;
	}

	@Step("Verify invalid search text result")
	public HomePageObjects verifyMessageForInvalidSearchData() {
		waiting.waitForVisibilityOfElement(invalidSearchResultLocator, 10);
		Assert.assertTrue(utilityMethods.isElementDisplayed(invalidSearchResultLocator), "Search is successful");
		log.info("Verified invalid search text result");
		return this;
	}
	
	@Step("Verify invalid narrow search text result")
	public HomePageObjects verifyMessageForInvalidNarrowSearchData(String searchTextForInvalidTestData) {
		waiting.waitForVisibilityOfElement(invalidNarrowSearchResultLocator, 10);
		Assert.assertTrue(utilityMethods.isElementDisplayed(invalidNarrowSearchResultLocator), "Search is successful");
		log.info("Verified the invalid search message contains We found 0 results for  " + searchTextForInvalidTestData
				+ " . You may try some other search");
		return this;
	}

	@Step("Hover over products link")
	public HomePageObjects hoverProductsLink() {
		action.moveToElement(productsLink).build().perform();
		log.info("Hover over products link");
		return this;
	}

	@Step("Verify 1st level category")
	public HomePageObjects verifyFirstLevelCategories(String[] productNames) throws InterruptedException {
		Thread.sleep(2000);
		for (int i = 0; i < levelOneCategoriesUnderProductsLinkLocator.size(); i++) {

			Assert.assertTrue(
					levelOneCategoriesUnderProductsLinkLocator.get(i)
							.getText()
							.toLowerCase()
							.replace(".", "")
							.trim()
							.contains(productNames[i].replace(".", "").toLowerCase()),
					"Expected : " + productNames[i].toLowerCase() + " Actual : "
							+ levelOneCategoriesUnderProductsLinkLocator.get(i)
									.getText()
									.toLowerCase()
									.replace(".", "")
									.trim());
		}
		log.info("Verified 1st level category");
		return this;
	}

	@Step("Verify My Account DropDown :{0}")
	public HomePageObjects verifyMyAccountDropdown(String expectedDropdownLinks) throws Exception {
		log = Logger.getLogger("verifyMyAccountDropdown");
		Thread.sleep(1500);
		String[] expectedDropdownLinksAll = expectedDropdownLinks.split(">");
		String[] expectedDropdownLinks1 = expectedDropdownLinksAll[0].split(",");
		String[] expectedDropdownLinks2 = expectedDropdownLinksAll[1].split(",");
		waiting.waitForVisibilityOfElements(myAcountDropdownLinksLocator, 10);
		try {
			for (int i = 0; i < myAcountDropdownLinksLocator.size(); i++) {

				Assert.assertEquals(myAcountDropdownLinksLocator.get(i).getText().toLowerCase().trim(),
						expectedDropdownLinks1[i].toLowerCase().trim(),
						"My Account Drop Down is not :" + expectedDropdownLinks1[i].toLowerCase().trim() + ". It is :"
								+ myAcountDropdownLinksLocator.get(i).getText().toLowerCase().trim());
				log.info("My Account Dropdown value has been verified" + " with [Actual] and [Expected] value as " + "["
						+ myAcountDropdownLinksLocator.get(i).getText().trim() + "] and " + "["
						+ expectedDropdownLinks1[i].trim() + "]");
			}
		} catch (Exception | AssertionError e) {
			try {
				for (int i = 0; i < myAcountDropdownLinksLocator.size(); i++) {

					Assert.assertEquals(myAcountDropdownLinksLocator.get(i).getText().toLowerCase().trim(),
							expectedDropdownLinks2[i].toLowerCase().trim(),
							"My Account Drop Down is not :" + expectedDropdownLinks2[i].toLowerCase().trim()
									+ ". It is :" + myAcountDropdownLinksLocator.get(i).getText().toLowerCase().trim());
					log.info("My Account Dropdown value has been verified" + " with [Actual] and [Expected] value as "
							+ "[" + myAcountDropdownLinksLocator.get(i).getText().trim() + "] and " + "["
							+ expectedDropdownLinks2[i].trim() + "]");
				}
			} catch (Exception | AssertionError f) {
				f.printStackTrace();
				Assert.fail(f.getMessage());
			}

		}

		return this;
	}

	@Step("Verify Table Header For Ecomm Sales Admin and Sales Rep:{0}")
	public HomePageObjects verifyTableHeaderOfEcommSalesAdminAndRepRole(String tableHeaderValuesOfSalesAdminAndRep) {
		log = Logger.getLogger("verifyTableHeaderOfEcommSalesAdminAndRepRole");
		waiting.waitForVisibilityOfElements(tableHeaderLocatorsForSalesAdminAndRep, 10);
		String[] expectedTableHeaderValuesOfSalesAdminAndRep = tableHeaderValuesOfSalesAdminAndRep.split(",");
		for (int i = 0; i < tableHeaderLocatorsForSalesAdminAndRep.size() - 1; i++) {
			Assert.assertEquals(tableHeaderLocatorsForSalesAdminAndRep.get(i).getText().trim(),
					expectedTableHeaderValuesOfSalesAdminAndRep[i].trim(),
					"Table Header of Sales Admin User and Sales Rep Uesr are not :"
							+ expectedTableHeaderValuesOfSalesAdminAndRep[i].trim() + ". It is :"
							+ tableHeaderLocatorsForSalesAdminAndRep.get(i).getText().trim());
			log.info("Table Header of Sales Admin User and Sales Rep Uesr have been verified"
					+ " with [Actual] and [Expected] value as " + "["
					+ tableHeaderLocatorsForSalesAdminAndRep.get(i).getText().trim() + "] and " + "["
					+ expectedTableHeaderValuesOfSalesAdminAndRep[i].trim() + "]");
		}
		return this;
	}

	@Step("Verify Message When No Users are assign to the Sales Rep :{0}")
	public HomePageObjects verifyNoCustomerAssignMessage(String message) {
		log = Logger.getLogger("verifyNoCustomerAssignMessage");
		waiting.waitForVisibilityOfElement(messageLocatorForNoCustomerAssignRole, 10);
		Assert.assertEquals(messageLocatorForNoCustomerAssignRole.getText().trim(), message.trim(),
				"Message  when no users are assigne to selected customer is not :" + message.trim() + ". It is :"
						+ messageLocatorForNoCustomerAssignRole.getText().trim());
		log.info("Message  when no users are assigne to selected customer has been verified"
				+ " with [Actual] and [Expected] value as " + "["
				+ messageLocatorForNoCustomerAssignRole.getText().trim() + "] and " + "[" + message.trim() + "]");
		return this;
	}

	@Step("Click On Exit Button")
	public HomePageObjects clickOnExitButton() throws InterruptedException {
		log = Logger.getLogger("clickOnExitButton");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", exitButtonLocator);
		Thread.sleep(1200);
		log.info("Clicked On Exit Button.");
		return this;
	}

	@Step("Verify Table Header For Selected User:{0}")
	public HomePageObjects verifyUserUnderTheSelectedCustomer(String tableHeaderValuesOfUserList) {
		log = Logger.getLogger("verifyUserUnderTheSelectedCustomer");
		waiting.waitForVisibilityOfElements(headerLocatorOfUserListTable, 10);
		String[] expectedTableHeaderValuesOfUserList = tableHeaderValuesOfUserList.split(",");
		for (int i = 0; i < headerLocatorOfUserListTable.size() - 1; i++) {
			Assert.assertEquals(headerLocatorOfUserListTable.get(i).getText(),
					expectedTableHeaderValuesOfUserList[i].trim(),
					"Table Header of User List is not :" + expectedTableHeaderValuesOfUserList[i].trim() + ". It is :"
							+ headerLocatorOfUserListTable.get(i).getText().trim());
			log.info("Table Header of User List has been verified" + " with [Actual] and [Expected] value as " + "["
					+ headerLocatorOfUserListTable.get(i).getText() + "] and " + "["
					+ expectedTableHeaderValuesOfUserList[i].trim() + "]");
		}
		return this;
	}

	/*
	 * public String getSpecificUserFirstName(int specificUserFirstName) { String
	 * UserFirstName = usersFirstNameLocators.get(specificUserFirstName -
	 * 1).getText().trim(); return UserFirstName; }
	 */

	public String getSpecificUserFirstName(int specificUserFirstName) {
		String UserFirstName = usersFirstNameLocators.getText().trim();
		return UserFirstName;
	}

	@Step("Verify List Of Customers For Sales Admin And Rep.")
	public HomePageObjects verifyListOfCustomerForSalesAdminAndRep() {
		log = Logger.getLogger("verifyListOfCustomerForSalesAdminAndRep");
		waiting.waitForVisibilityOfElements(listOfCustomerLocators, 10);
		Assert.assertTrue(listOfCustomerLocators.size() >= 1,
				"List of customer is not available for the sales admin and rep user.");
		log.info("Verified List Of Customers For Sales Admin And Rep.");
		return this;
	}

	@Step("Select Customer From List Of Customers")
	public HomePageObjects selectCustomerFromList() {
		log = Logger.getLogger("selectCustomerFromList");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", selectButtonLocators.get(1));
		log.info("Selected the address");
		return this;
	}

	@Step("Select specific User :{0}")
	public HomePageObjects selectSpecificUser(int specificUser) {
		log = Logger.getLogger("selectSpecificUser");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				selectButtonLocatorsForUserList.get(specificUser - 1));
		log.info("Selected specific User :" + specificUser);
		return this;
	}

	@Step("Verify Sales Admin Welcome Message")
	public HomePageObjects verifySalesAdminWelcomeMessage() {
		log = Logger.getLogger("verifySalesAdminWelcomeMessage");
		waiting.waitForVisibilityOfElement(salesAdminWelcomeMessageLocator, 10);
		Assert.assertTrue(salesAdminWelcomeMessageLocator.isDisplayed(),
				"Welcome Message of Sales Admin is not displayed.");
		log.info("Verified Sales Admin Welcome Message.");
		return this;
	}

	@Step("Verify User Welcome Message :{0}")
	public HomePageObjects verifyUserWelcomeMessage(String userName) {
		log = Logger.getLogger("verifyUserWelcomeMessage");
		waiting.waitForVisibilityOfElement(userWelcomeMessageLocator, 10);
		Assert.assertTrue(userWelcomeMessageLocator.getText().toLowerCase().contains(userName.toLowerCase()),
				"Welcome Message of User is not displayed.");
		log.info("Verified User Welcome Message." + userName);
		return this;

	}

	/*
	 * @Step("Select shipping address") public HomePageObjects
	 * selectShippingAddress() throws Exception { try { Thread.sleep(1500);
	 * Assert.assertTrue(checkForUseThisAddress()); } catch
	 * (StaleElementReferenceException e) { getDriver().navigate().refresh();
	 * selectShippingAddress(); } return this; }
	 */

	@Step("Select shipping address")
	public HomePageObjects selectShippingAddress() throws Exception {
		try {
			Thread.sleep(3500);
			waiting.waitForElementToBeClickable(shipToNameInSelectShippingPopUp.get(0), 5);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					shipToNameInSelectShippingPopUp.get(0));
			log.info("Selected the Shop Now option");
		} catch (Exception e) {
			homePage().verifyWelcomeMsgAfterLogin();
		}
		return this;
	}

	@Step("Select shipping address for add new credit card module.")
	public HomePageObjects selectShippingAddressForCreditCardModule() throws InterruptedException {

		try {
			Thread.sleep(3000);
			Assert.assertTrue(checkForUseThisAddressForAddNewCreditCardModule());
			Thread.sleep(1500);
		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			selectShippingAddressForCreditCardModule();
		}
		log.info("Shipping address is selected");
		return this;

	}

	@Step("Verify error message:{0} in login page")
	public HomePageObjects assertForErrorMessages_LoginPage(String expectedMsg) {
		waiting.waitForVisibilityOfElement(errorMsgLocator_LoginPage, 5);
		Assert.assertEquals(errorMsgLocator_LoginPage.getText().trim(), expectedMsg.trim(), "Invalid Error Message");
		log.info("Verified error message :" + expectedMsg);
		return this;
	}

	@Step("Click on Quick Order pag link")
	public HomePageObjects clickOnQuickOrderPadLinkInHeader() throws InterruptedException {
		waiting.waitForVisibilityOfElement(quickOrderPadLink, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", quickOrderPadLink);
		log.info("Clicked on Quick Order pag link");
		return this;
	}

	@Step("Verify whether alert message is {0}")
	public HomePageObjects verifyAlertMessage(String alertText) throws Exception {
		Thread.sleep(800);
		WebElement alert = getDriver()
				.findElement(By.xpath("//div[normalize-space(text())='" + alertText.trim() + "']"));
		Assert.assertTrue(utilityMethods.isElementDisplayed(alert), "Alert pop up not displayed");
		log.info("Alert message is verified");
		return this;
	}

	@Step("Select shipping address")
	public HomePageObjects selectShippingAddressFromPopUp() throws Exception {
		try {
			Thread.sleep(3000);
			Assert.assertTrue(selectShippingAddressFromTheList());
			Thread.sleep(1500);

		} catch (StaleElementReferenceException e) {
			getDriver().navigate().refresh();
			selectShippingAddress();
		}
		log.info("Shipping address is selected");
		return this;
	}

	@Step("Verify use this address button locator")
	public boolean selectShippingAddressFromTheList() throws Exception {
		/* waiting.explicitWaitVisibilityOfElement(selectShippingPopUp, 5); */
		/*
		 * if (selectShippingPopUp.isDisplayed()) {
		 * waiting.waitForVisibilityOfElement(selectShippingPopUp, 5);
		 * waiting.waitForVisibilityOfElement(useSelectedAddressLocator, 10);
		 * ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
		 * useSelectedAddressLocator); Thread.sleep(2400); } else { return true; }
		 */
		return true;
	}

	@Step("Select brand from auto suggestion")
	public HomePageObjects selectBrandFromAutoSuggest(String searchTextForBrand) throws InterruptedException {
		waiting.waitForVisibilityOfElement(autoSuggestBlock, 20);
		Thread.sleep(1000);
		getDriver().findElement(By.xpath("//li[@data-value='" + searchTextForBrand + "']")).click();
		log.info("Selected brand from auto suggestion");
		return this;
	}

	@Step("Click on login link")
	public LoginDropDownPageObjects clickLoginLink() throws Exception {
		logger = Logger.getLogger("clickLoginLink");
		waiting.waitTillPageLoads();
		waiting.waitForElementToBeClickable(loginLinkLocator, 30);
		utilityMethods.scrollThePageToTop();
		loginLinkLocator.click();
		logger.info("Clicked on login link");
		return loginDropDown();
	}

	/*
	 * @Step("Click on login link") public LoginDropDownPageObjects
	 * clickLoginLinkForAccountFinancialInfo() throws Exception { logger =
	 * Logger.getLogger("clickLoginLink");
	 * waiting.waitForElementToBeClickable(loginLinkLocator, 18);
	 * loginLinkLocator.click(); logger.info("Clicked on login link"); return
	 * loginDropDown(); }
	 */

	@Step("Click on login link for add new credit card module.")
	public LoginPageObjects clickLoginLinkForNewCreditCart() {
		log = Logger.getLogger("clickLoginLinkForNewCreditCart");
		waiting.waitForVisibilityOfElement(loginLinkLocatorForNewCreditCard, 18);
		loginLinkLocatorForNewCreditCard.click();
		log.info("Clicked on login link");
		return loginPage();
	}

	@Step("Enter search text:{0}")
	public HomePageObjects searchText(String searchText) throws Exception {
		// waiting.waitForVisibilityOfElement(searchTextbox, 30);

		log = Logger.getLogger("searchText");
		Thread.sleep(1500);
		utilityMethods.moveToElementAndClick(searchTextbox);
		searchTextbox.clear();
		searchTextbox.sendKeys(searchText);
		log.info("Search Text has been entered :" + searchText);
		return this;
	}

	@Step("Click on search button")
	public HomePageObjects clickOnSearch() throws InterruptedException {
		waiting.waitForVisibilityOfElement(searchButton, 25);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", searchButton);
		Thread.sleep(2400);
		waiting.waitTillPageLoads();
		log.info("Clicked on search button");
		return this;
	}

	@Step("Verify User Name:{0} After Login")
	public HomePageObjects verifyUserNameAfterLogin(String userName) throws Exception {
		waiting.waitForVisibilityOfElement(welcomeMsgLocator, 10);
		Assert.assertTrue(utilityMethods.isElementDisplayed(welcomeMsgLocator), "User is not logged in");
		log.info("user name has been verified:" + userName + "after login");
		return this;
	}

	@Step("Verify Home Page Title")
	public HomePageObjects verifyHomePageTitle(String productName) throws InterruptedException {
		Thread.sleep(1500);
		String HomePageTitle = getDriver().getTitle();

		Assert.assertEquals(HomePageTitle, "Shop Electrical, Plumbing, & HVAC Supplies" + " | " + productName, "Title is wrong");

		log.info("Title of the home page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ HomePageTitle + "] and " + "[" + "Shop Electrical, Plumbing, & HVAC Supplies" + " | " + productName + "]");
		return this;
	}

	@Step("Select show more option")
	public HomePageObjects selectMoreResultOptionFromAutoSuggest() {
		waiting.waitForVisibilityOfElement(showMoreOptionsAutoSuggest, 10);
		showMoreOptionsAutoSuggest.click();
		log.info("Selected show more option");
		return this;
	}

	@Step("Click on Logo")
	public HomePageObjects clickOnLogo() throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logo);
		Thread.sleep(1400);
		log.info("Clicked on Logo");
		return this;
	}

	@Step("Click On My Account Tab")
	public HomePageObjects clickOnMyAccountMenuDropdown() throws InterruptedException {
		waiting.waitForVisibilityOfElement(userAccountDropdown, 20);
		log = Logger.getLogger("clickOnUserAccountDropdown");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", userAccountDropdown);
		Thread.sleep(1200);
		log.info("Clicked On My Account Tab");
		return this;

	}

	@Step("Click on my save cart")
	public SaveCartPageObjects clickOnMySaveCart() throws InterruptedException {
		Thread.sleep(1200);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", mySaveCartLink);
		log.info("Clicked on my Saved cart link");
		return saveCartPage();
	}

	@Step("Click On Open Order Link Under My Account Tab")
	public HomePageObjects clickOnOpenOrderLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(openOrderLink, 5);

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", openOrderLink);
		Thread.sleep(1400);

		log.info("Clicked On Open Order Link Under My Account Tab");
		return this;

	}

	@Step("Click On Account Inquiry Link Under My Account Tab")
	public HomePageObjects clickOnAccountInquiryLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(accountInquiryLinkLocator, 5);
		log = Logger.getLogger("clickOnAccountInquiryLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", accountInquiryLinkLocator);
		Thread.sleep(1400);
		log.info("Clicked On Account Inquiry Link Under My Account Tab");
		return this;
	}

	@Step("Click On User Management Link Under My Account Tab")
	public HomePageObjects clickOnUserManagementLink() throws InterruptedException {
		log = Logger.getLogger("clickOnUserManagementLink");
		waiting.waitForVisibilityOfElement(userManagementLinkLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", userManagementLinkLocator);
		Thread.sleep(1500);
		log.info("Clicked On User Management Link Under My Account Tab.");
		return this;
	}

	@Step("Click On Approval Cart List Link Under My Account Tab")
	public HomePageObjects clickOnApprovalCartListLink() throws InterruptedException {
		log = Logger.getLogger("clickOnApprovalCartListLink");
		waiting.waitForVisibilityOfElement(approvalCartListLinkLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", approvalCartListLinkLocator);
		Thread.sleep(1200);
		log.info("Clicked On Approval Cart List Link Under My Account Tab.");
		return this;
	}

	@Step("Click on View Cart icon")
	public HomePageObjects clickOnViewCartIcon() throws InterruptedException {
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", cartIconLocator);
		log.info("Clicked on View Cart icon");
		return this;
	}

	@Step("Click on 'My Account' from Dropdown Menu")
	public MyAccountsPageObjects clickOnMyAccount() {

		waiting.waitForVisibilityOfElement(myAccountLink, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myAccountLink);
		log.info("Clicked on 'My Account' from Dropdown Menu");
		return myAccountsPage();

	}

	@Step("Verify recently viewed item section for first launch")
	public HomePageObjects verifyRecentlyViewedItemForFirstLaunch() {

		utilityMethods.scrollThePageToBottom();
		Assert.assertFalse(utilityMethods.isElementDisplayed(recentlyViewedTabItemLocator),
				"Recently viewed section is displayed");
		log.info("Recently viewed tab display is verified");
		return this;
	}

	@Step("Verify recently viewed item display")
	public HomePageObjects verifyRecentlyViewedItemDisplay() throws Exception {
		// Search Text data from TestData.properties ---->recentlyViewedSearchHomePage
		String[] searchText = data.getSearchTextforRecentlyViewedTest().split(">");
		for (int i = 0; i < searchText.length; i++) {
			searchText(searchText[i]).clickOnSearch();
			log.info("Searched for :" + searchText[i]);
		}
		clickOnLogo();
		utilityMethods.scrollThePageToBottom();
		log.info("Scrolled to the bottom of the page");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(recentlyViewedTabItemLocator),
				"Recently viewed section is not displayed");
		log.info("Verified recently viewed section");
		for (int j = 0; j < recentlyViewedItemLocator.size(); j++) {
			for (int i = 0; i < searchText.length; i++) {
				if (searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title"))) {
					// System.out.println("actual:"+recentlyViewedItemLocator.get(j).getAttribute("title")
					// +"Expected"+searchText[i]);
					softAssert.assertTrue(
							searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title")),
							"recently viewed item is not present");
				}
			}
		}
		log.info("Verified recently viewed items");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify recently viewed item display using play buttons, left and right")
	public HomePageObjects verifyRecentlyViewedAllItemDisplayWithPlayButtons() throws Exception {
		// Search Text data from TestData.properties ---->recentlyViewedSearchHomePage
		String[] searchText = data.getSearchTextforRecentlyViewedTest().split(">");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView()",
				recentlyViewedPlayButtonRightLocator);
		log.info("Scrolled till right play button");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", recentlyViewedPlayButtonRightLocator);
		log.info("Clicked on the right play button");
		for (int j = 0; j < recentlyViewedItemLocator.size(); j++) {
			for (int i = 0; i < searchText.length; i++) {
				if (searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title"))) {
					// System.out.println("actual:"+recentlyViewedItemLocator.get(j).getAttribute("title")
					// +"Expected"+searchText[i]);
					softAssert.assertTrue(
							searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title")),
							"recently viewed item is not present");
				}
			}
		}
		log.info("Verified right play button functionality");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", recentlyViewedPlayButtonLeftLocator);
		log.info("Clicked on the Left play button");
		for (int j = 0; j < recentlyViewedItemLocator.size(); j++) {
			for (int i = 0; i < searchText.length; i++) {
				if (searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title"))) {
					// System.out.println("actual:"+recentlyViewedItemLocator.get(j).getAttribute("title")
					// +"Expected"+searchText[i]);
					softAssert.assertTrue(
							searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title")),
							"recently viewed item is not present");
				}
			}
		}
		log.info("Verified left play button functionality");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify recently viewed item display functionality")
	public HomePageObjects verifyRecentlyViewedItemDisplayFunctionality() throws Exception {
		getDriver().manage().deleteAllCookies();
		log.info("Deleted all cookies");

		String[] searchText = data.getSearchTextforRecentlyViewedTest().split(">");

		for (int i = 0; i < 2; i++) {
			searchText(searchText[i]).clickOnSearch();

			getDriver().navigate().refresh();
		}
		clickOnLogo();
		utilityMethods.scrollThePageToBottom();
		softAssert.assertTrue(utilityMethods.isElementDisplayed(recentlyViewedTabItemLocator),
				"Recently viewed section is not displayed");

		log.info("Verified the presence of recently viewed tab locator");

		for (int j = 0; j < recentlyViewedItemLocator.size(); j++) {
			for (int i = 0; i < searchText.length; i++) {
				if (searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title"))) {

					softAssert.assertTrue(
							searchText[i].contains(recentlyViewedItemLocator.get(j).getAttribute("title")),
							"recently viewed item is not present");
				}
			}
		}
		log.info("Verified recntly viewed functionlity");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify featured products display location")
	public HomePageObjects verifyfeaturedProductsItemDisplayLocation() {

		softAssert.assertTrue(utilityMethods.isElementDisplayed(featuredProductsTabLocator),
				"Featured products tab is not displayed");
		log.info("Verified featured products tab presence");
		softAssert.assertEquals(utilityMethods.isElementDisplayed(featuredProductsTabLocator),
				"featured product tab tab is not displayed");
		log.info("Verified featured products tab location");
		softAssert.assertAll();
		return this;
	}

	/*
	 * @Step("Verify featured products display content") public HomePageObjects
	 * verifyfeaturedProductsItemDisplayContent() { ArrayList<String> displayedItems
	 * = new ArrayList<String>(); for (int i = 0; i <
	 * featuredProductsTabItemLocator.size(); i++) { if
	 * (featuredProductsTabItemLocator.get(i).getText().isEmpty()) ; else
	 * displayedItems.add(featuredProductsTabItemLocator.get(i).getText().toString()
	 * ); } Assert.assertEquals(displayedItems.size(), 5);
	 * log.info("Verified featured products display content"); return this; }
	 */

	@Step("Verify featured products display content")
	public HomePageObjects verifyfeaturedProductsItemDisplayContent() {
		ArrayList<String> displayedItems = new ArrayList<String>();
		for (int i = 0; i < featuredProductsTabItemLocator.size(); i++) {
			if (featuredProductsTabItemLocator.get(i).getText().isEmpty())
				;
			else
				displayedItems.add(featuredProductsTabItemLocator.get(i).getText().toString());
		}
		Assert.assertEquals(displayedItems.size(), 5);
		log.info("Verified featured products display content");
		return this;
	}

	@Step("Verify featured products display content using play buttons, left and right")
	public HomePageObjects verifyfeaturedProductsItemDisplayWithPlayButtons() throws Exception {
		ArrayList<String> displayedItems = new ArrayList<String>();

		for (int i = 0; i < featuredProductsTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					featuredProductsTabPlayButtonRightLocator);
			log.info("Clicked on right play button");
			Thread.sleep(250);
			if (featuredProductsTabItemLocator.get(i).getText().isEmpty())
				;
			else
				displayedItems.add(featuredProductsTabItemLocator.get(i).getText().toString());
		}

		softAssert.assertNotNull(displayedItems.size(), "Items not displayed after clicking rightplay button");
		log.info("Verified right play button functionality");
		for (int i = 0; i < featuredProductsTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					featuredProductsTabPlayButtonLeftLocator);
			log.info("Clicked on left play button");
			Thread.sleep(250);
			if (featuredProductsTabItemLocator.get(i).getText().isEmpty())
				;
			else
				displayedItems.add(featuredProductsTabItemLocator.get(i).getText().toString());
		}

		softAssert.assertNotNull(displayedItems.size(), "Items not displayed after clicking leftplay button");
		log.info("Verified left play button functionality");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify Multiple featured products.")
	public HomePageObjects verifyMultiplefeaturedProductsTab() throws Exception {

		waiting.waitForVisibilityOfElement(multipleFeatureProductGroupLocator, 10);

		softAssert.assertTrue(utilityMethods.isElementDisplayed(multipleFeatureProductGroupLocator),
				"Multiple Feature Product section is not displayed");

		softAssert.assertAll();
		log.info("Verified Multiple featured products");
		return this;
	}

	@Step("Verify featured products display content using play buttons, left and right")
	public HomePageObjects verifyMultipleFeaturedProductsItemDisplayWithPlayButtons() throws Exception {
		ArrayList<String> displayedItems = new ArrayList<String>();

		for (int i = 0; i < multipleFeaturedProductsTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					multipleFeaturedProductsTabPlayButtonRightLocator);
			log.info("Clicked on right play button");
			Thread.sleep(250);
			if (multipleFeaturedProductsTabItemLocator.get(i).getText().isEmpty())
				;
			else
				displayedItems.add(multipleFeaturedProductsTabItemLocator.get(i).getText().toString());
		}

		softAssert.assertNotNull(displayedItems.size(), "Items not displayed after clicking rightplay button");
		log.info("Verified right play button functionality");
		for (int i = 0; i < multipleFeaturedProductsTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					multipleFeaturedProductsTabPlayButtonLeftLocator);
			log.info("Clicked on left play button");
			Thread.sleep(250);
			if (multipleFeaturedProductsTabItemLocator.get(i).getText().isEmpty())
				;
			else
				displayedItems.add(multipleFeaturedProductsTabItemLocator.get(i).getText().toString());
		}

		softAssert.assertNotNull(displayedItems.size(), "Items not displayed after clicking leftplay button");
		log.info("Verified left play button functionality");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify featured brands display location")
	public HomePageObjects verifyFeturedBrandsTabisDisplayed() {
		utilityMethods.scrollThePageToBottom();
		softAssert.assertTrue(utilityMethods.isElementDisplayed(featuredBrandsTabLocator),
				"Featured Brands section is not displayed");
		log.info("Verified featured Brands tab presence");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify featured brands display content using play buttons, left and right")
	public HomePageObjects verifyfeaturedBrandsDisplayWithPlayButtons() throws Exception {
		ArrayList<String> displayedBrands = new ArrayList<String>();
		// System.out.println(featuredBrandsTabItemLocator.size());
		// ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView()",
		// featuredBrandsTabPlayButtonRightLocator);
		Thread.sleep(250);
		for (int i = 0; i < featuredBrandsTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					featuredBrandsTabPlayButtonRightLocator);
			log.info("Clicked on right play button");
			Thread.sleep(250);
			if (featuredBrandsTabItemLocator.get(i).getAttribute("alt").isEmpty())
				;
			else
				displayedBrands.add(featuredBrandsTabItemLocator.get(i).getText().toString());
			// System.out.println(featuredBrandsTabItemLocator.get(i).getAttribute("alt").toString());
		}
		softAssert.assertNotNull(displayedBrands.size(), "Brands not displayed after clicking rightplay button");
		log.info("Verified right play button functionality");
		for (int i = 0; i < featuredBrandsTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					featuredBrandsTabPlayButtonLeftLocator);
			log.info("Clicked on Left play button");
			Thread.sleep(250);
			if (featuredBrandsTabItemLocator.get(i).getAttribute("alt").isEmpty())
				;
			else
				displayedBrands.add(featuredBrandsTabItemLocator.get(i).getText().toString());
			// System.out.println(featuredBrandsTabItemLocator.get(i).getAttribute("alt").toString());
		}
		softAssert.assertNotNull(displayedBrands.size(), "Brands not displayed after clicking leftplay button");
		log.info("Verified left play button functionality");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify featured manufacturers display location")
	public HomePageObjects verifyFeturedManufacturersTabisDisplayed() {
		utilityMethods.scrollThePageToBottom();
		log.info("Scrolled to the page bottom");
		softAssert.assertTrue(utilityMethods.isElementDisplayed(featuredManufacturersTabLocator),
				"Featured Manufacturers section is not displayed");
		log.info("Verified featured Manufacturers tab presence");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify featured manufacturers display content using play buttons, left and right")
	public HomePageObjects verifyfeaturedManufacturersDisplayWithPlayButtons() throws Exception {

		ArrayList<String> displayedManufacturers = new ArrayList<String>();
		// System.out.println(featuredManufacturersTabItemLocator.size());
		// ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView()",
		// featuredManufacturersTabPlayButtonRightLocator);

		for (int i = 0; i < featuredManufacturersTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					featuredManufacturersTabPlayButtonRightLocator);
			log.info("Clicked on right play button");
			Thread.sleep(250);
			if (featuredManufacturersTabItemLocator.get(i).getAttribute("alt").isEmpty()) {

				;
			}

			else {

				displayedManufacturers.add(featuredManufacturersTabItemLocator.get(i).getText().toString());

			}
		}

		softAssert.assertNotNull(displayedManufacturers.size(),
				"Manufacturers not displayed after clicking rightplay button");
		log.info("Verified right play button functionality");
		for (int i = 0; i < featuredManufacturersTabItemLocator.size(); i++) {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
					featuredManufacturersTabPlayButtonLeftLocator);
			log.info("Clicked on left play button");
			Thread.sleep(250);
			if (featuredManufacturersTabItemLocator.get(i).getAttribute("alt").isEmpty())
				;
			else
				displayedManufacturers.add(featuredManufacturersTabItemLocator.get(i).getText().toString());
			// System.out.println(featuredManufacturersTabItemLocator.get(i).getAttribute("alt").toString());
		}

		softAssert.assertNotNull(displayedManufacturers.size(),
				"Manufacturers not displayed after clicking leftplay button");
		log.info("Verified left play button functionality");
		softAssert.assertAll();
		return this;
	}

	@Step("Click on Open Orders")
	public OpenOrdersPageObjects clickOnOpenOrders() {
		waiting.waitForVisibilityOfElement(openOrdersLinkLocator, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", openOrdersLinkLocator);
		log.info("Clicked on Open Orders");
		return openOrdersPage();
	}

	@Step("Click on Order History Link.")
	public OrderHistoryPageObjects clickOnOrderHistory() {
		waiting.waitForVisibilityOfElement(orderHistoryLink, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", orderHistoryLink);
		log.info("Clicked on Order History Link");
		return orderHistoryPage();

	}

	@Step("Click on Change Password link in My Account menu")
	public HomePageObjects clickOnChangePasswordLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(changePasswordLink, 3);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", changePasswordLink);
		log.info("Clicked on Change Password link in My Account menu");
		Thread.sleep(1500);
		return this;
	}

	@Step("Click on Quick Order Pad link.")
	public QuickOrderPageObjects clickOnQuickOrderPadLink() throws InterruptedException {
		waiting.waitTillPageLoads();
		clickOnMyAccountMenuDropdown();
		quickOrderPadLink.click();
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", quickOrderPadLink);
		log.info("Click on Quick Order pad link");
		return quickOrderPadPage();
	}

	@Step("Mouse Hover On Shop Product Link.")
	public HomePageObjects mouseHoverOnShopProductLink() throws InterruptedException {
		waiting.waitForVisibilityOfElement(shopProductLinkLocator, 10);
		utilityMethods.moveToElement(shopProductLinkLocator);
		Thread.sleep(1200);
		log.info("Mouse Hover On Shop Product Link.");
		return this;
	}

	@Step("Click on first Level1 category count {0}")
	public HomePageObjects clickOnRequiredLevel1CategoryLinkFromShopProductMenu(int a) throws InterruptedException {
		String firstLink = Level1ProductCategoryHeaderMenuLinks.get(a - 0).getText();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				Level1ProductCategoryHeaderMenuLinks.get(a - 0));
		Thread.sleep(1200);
		log.info("Clicked on Level1 category: " + firstLink);
		return this;

	}

}
