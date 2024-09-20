package pageobjects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.WaitingMethods;

public class ProductPageObjects extends PageInitializer {
	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Actions action = new Actions(getDriver());

	SoftAssert soft = new SoftAssert();

	Logger log = Logger.getLogger(ProductPageObjects.class);

	@FindBy(xpath = "//h4[contains(text(),'Category')]/following-sibling::span")
	private WebElement categoryToggleButton;

	@FindBy(xpath = "//dt[contains(text(),'Category')]/following-sibling::dd[1]/descendant::a[@class='active']")
	private List<WebElement> categoriesList;

	@FindBy(xpath = "//div[@class='cimm_productCategory']/descendant::h2")
	private List<WebElement> categoryNamesInThePage;

	@FindBy(xpath = "//div[@class='cimm_productCategory']/descendant::div[@class='cimm_categoryImg']")
	private List<WebElement> categoryNamesInPageImagesLink;

	@FindBy(xpath = "//div[@id='categoryMenu']//a")
	private List<WebElement> categoryItemsInLeftMenu;

	@FindBy(xpath = "//h2")
	private WebElement pageName;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Out')]")
	private WebElement logOutLinkLocatorInLeftMenu;

	@FindBy(xpath = "//div[@class='slick-track']/descendant::img")
	private List<WebElement> bannerImages;

	@FindBy(xpath = "//div[@data-target='#manufacturersMenu']/following-sibling::div[@id='manufacturersMenu']")
	private WebElement manufacturerNameLocatorInLeftmenu;

	@FindBy(xpath = "//div[@data-target='#categoryMenu']/following-sibling::div[@id='categoryMenu']")
	private WebElement shopProdutsLocatorInLeftmenu;

	@FindBy(xpath = "//div[@data-target='#brandsMenu']/following-sibling::div[@id='brandsMenu']")
	private WebElement brandNameLocatorInLeftmenu;

	@FindBy(xpath = "//div[normalize-space(text())='Your Account']/following-sibling::ul[@class='myAccountLeftMenu']")
	private WebElement myAccountLocatorInLeftmenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a")
	private List<WebElement> myAcountSectionLocator;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Account')]")
	private WebElement myAccountLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Password')]")
	private WebElement changePasswordLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(text(),'Home')]")
	public WebElement homeIconInBreadcrumbLocator;

	@FindBy(xpath = "//div[@class='cimm_productCategory']/descendant::a")
	private List<WebElement> linkForEveryCategoryLocator;

	@FindBy(xpath = "//ul[@id='accountList']//a[@id='addnewcre']")
	private WebElement addNewCreditCardLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Pad')]")
	private WebElement quickOrderPadLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(@href,'pprov')]")
	private WebElement approvalCartListLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Management')]")
	private WebElement userManagementLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Quote')]")
	private WebElement requestForQuoteLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Saved')]")
	private WebElement mySaveCartLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Product')]")
	private WebElement myProductGroupLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'History')]")
	private WebElement orderHistoryLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Open')]")
	private WebElement openOrderLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Contact')]")
	private WebElement editContactInfoLinkLocatorInLeftMenu;

	@FindBy(xpath = "//ul[@class='myAccountLeftMenu']/li/a[contains(text(),'Configure')]")
	private WebElement configureProcumentSystemLinkLocatorInLeftMenu;

	@FindBy(xpath = "//select[@id='sortBy']/option")
	private List<WebElement> sortByValues;

	@FindBy(xpath = "//button[@data-id='sortBy']/following-sibling::select")
	private WebElement sortByLocator;

	@Step("Click on category toggle button")
	public ProductPageObjects clickOnCategoryToggleButton() {
		waiting.waitForVisibilityOfElement(categoryToggleButton, 15);
		categoryToggleButton.click();
		log.info("Clicked on category toggle button");
		return this;
	}

	@Step("Verify names of the categories in the page")
	public ProductPageObjects verifyNamesOfAllTheCategoriesInListAndInPage() {

		waiting.waitForVisibilityOfElements(categoriesList, 15);
		for (int i = 0; i < categoriesList.size(); i++) {
			Assert.assertTrue(
					(categoriesList.get(i).getText().replace(".", "").trim())
							.equalsIgnoreCase(categoryNamesInThePage.get(i).getText().trim()),
					"category name is not the same as it was in the dropdown. The name in the category list is : "
							+ categoriesList.get(i).getText().trim() + " and the name in the category page is : "
							+ categoryNamesInThePage.get(i).getText().trim() + ".");
			log.info("Verified names of the categories in the page :" + categoryNamesInThePage.get(i).getText().trim());
		}
		return this;
	}

	@Step("Verify breadcrumbs to have {0}")
	public ProductPageObjects verifyBreadcrumb(String productsPageBreadCrumb) {
		Assert.assertTrue(homeIconInBreadcrumbLocator.isDisplayed(), "Home icon is not displayed in the breadcrumb.");

		log.info("Home icon is  displayed in the breadcrumb.");

		Assert.assertEquals(productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.replace("|", "")
				.trim(), productsPageBreadCrumb);

		log.info("Breadcrumb of the page has been verified" + " with [Actual] and [Expected] value as " + "["
				+ productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
						.getText()
						.replace("|", "")
						.trim()
				+ "] and " + "[" + productsPageBreadCrumb + "]");

		return this;
	}

	@Step("Verify page name to have {0}")
	public ProductPageObjects verifyPagename(String productsPageBreadCrump) throws Exception {
		Assert.assertEquals(pageName.getText().trim().toLowerCase(), productsPageBreadCrump.toLowerCase());
		log.info("Verified page name :" + pageName.getText().trim());
		return this;
	}

	@Step("Click on {0} st/nd/rd specific category")
	public ProductPageObjects clickOnSpecificCategory(String getSpecificCategory) {
		waiting.waitForVisibilityOfElement(By.xpath("//h2[text()[normalize-space() = '" + getSpecificCategory + "']]"),
				5);
		getDriver().findElement(By.xpath("//h2[text()[normalize-space() = '" + getSpecificCategory + "']]")).click();
		log.info("Clicked on :" + getSpecificCategory);
		return this;
	}

	@Step("Verify second breadcrumb")
	public ProductPageObjects verifySecondBreadcrumb(String getSpecificCategory) {
		Assert.assertEquals(productDetailsPage().breadCrumbs.get(2).getText().replace("/", "").trim(),
				getSpecificCategory);
		log.info("Verified second bread crumb");
		return this;
	}

	@Step("Get the last bread crumb")
	public String getLastBreadCrumb() {
		String lastBreadcrump = (productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 1)
				.getText()
				.replace("/", "")
				.trim());
		log.info("Last bread crumb displayed is: " + lastBreadcrump);
		return lastBreadcrump;
	}

	@Step("Get the last but one bread crumb")
	public String getLastButOneBreadCrumb() {
		String lastButOneBreadcrump = (productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 2)
				.getText()
				.replace("/", "")
				.trim());
		log.info("Last but one bread crumb is: " + lastButOneBreadcrump);
		return lastButOneBreadcrump;
	}

	@Step("Verify page title to have {0}")
	public ProductPageObjects verifyPageTitle(String lastButOneBreadcrumb, String lastBreadcrumb, String productName)
			throws Exception {
		Thread.sleep(1500);
		String title = lastBreadcrumb + " | " + productName;
		Assert.assertEquals(getDriver().getTitle().trim(), title);
		log.info("Verified page title");
		return this;
	}

	@Step("Verify last breadcrumb to have {0}")
	public ProductPageObjects verifyLastButOneBreadcrump(String getSpecificCategory) {
		Assert.assertEquals(productDetailsPage().breadCrumbs.get(productDetailsPage().breadCrumbs.size() - 2)
				.getText()
				.replace("/", "")
				.trim(), getSpecificCategory);
		log.info("Verified last but one bread crumb");
		return this;
	}

	@Step("Click on {0} category image")
	public ProductPageObjects clickOnSpecificCategoryImage(String getSpecificCategory) {
		String clickOnSpecificCategoryImage = "//div[@class='cimm_productCategory']/descendant::h5[contains(text(),'"
				+ getSpecificCategory + "')]/preceding-sibling::div[@class='cimm_categoryImg']";
		getDriver().findElement(By.xpath(clickOnSpecificCategoryImage)).click();
		log.info("Clicked on Image of: " + getSpecificCategory);
		return this;
	}

	@Step("Verify banner images")
	public ProductPageObjects verifyBannerImages(String expectedBannerImages) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String bannerImagesInLevelOne[] = expectedBannerImages.split(",");
		for (int i = 0; i < bannerImages.size(); i++) {
			Assert.assertTrue(bannerImages.get(i).getAttribute("src").trim().equals(bannerImagesInLevelOne[i]));
		}
		log.info("Verified banner images");
		return this;
	}

	@Step("Click on :{0} ")
	public ProductPageObjects clickOnSpecificCategoryUnderTheProductsLink(String categoryNameToSearch) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				getDriver().findElement(By.xpath("//a[contains(text(),'" + categoryNameToSearch + "')]")));
		log.info("Clicked on: " + categoryNameToSearch);
		return this;
	}

	@Step("Get the categories name")
	public String[] getNamesOfTheCategories() {
		String[] categoryNames = new String[categoryNamesInThePage.size()];
		for (int i = 0; i < categoryNamesInThePage.size(); i++) {
			categoryNames[i] = categoryNamesInThePage.get(i).getText().trim();
		}
		log.info("Get categories name");
		return categoryNames;
	}

	@Step("Get the product names")
	public String[] getProductNames() {
		String[] productName = new String[linkForEveryCategoryLocator.size()];
		for (int i = 0; i < linkForEveryCategoryLocator.size(); i++) {
			productName[i] = categoryNamesInThePage.get(i).getText().trim();
		}
		log.info("Get product names");
		return productName;
	}

	@Step("Click on first Category Name from Left Menu")
	public String clickOnFirstCategoryInTheLeftPanel() throws InterruptedException {
		String firstCategory = categoryItemsInLeftMenu.get(0).getText().trim();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", categoryItemsInLeftMenu.get(0));
		Thread.sleep(1200);
		log.info("Clicked on first category name :" + firstCategory);
		return firstCategory;
	}

	@Step("Verify discription: {0}")
	public ProductPageObjects verifyCategoryDescription(String categoryDescription) {
		Assert.assertTrue(false, "category description is not getting updated in ecommerce.");
		log.info("Verified description :" + categoryDescription);
		return this;
	}

	@Step("Verify names of all categories")
	public ProductPageObjects verifyNamesOfAllCategories(String[] nameOfTheItems) {
		for (int i = 0; i < categoryNamesInThePage.size(); i++) {
			Assert.assertEquals(categoryNamesInThePage.get(i).getText().trim(), nameOfTheItems[i]);
			log.info("Verified categories :" + nameOfTheItems[i]);
		}
		return this;
	}

	@Step("Verify Shop Product Link In The Left Menu.")
	public ProductPageObjects verifyShopProductsInTheLeftMenu() {
		waiting.waitForVisibilityOfElement(shopProdutsLocatorInLeftmenu, 10);
		Assert.assertTrue(shopProdutsLocatorInLeftmenu.isDisplayed(),
				"Shop Product Is not Displayed In The Left Menu.");
		log.info("Verified Shop Product Link In The Left Menu.");
		return this;
	}

	@Step("Verify Brand Link In The Left Menu.")
	public ProductPageObjects verifyBrandInTheLeftMenu() {
		waiting.waitForVisibilityOfElement(brandNameLocatorInLeftmenu, 10);
		Assert.assertTrue(brandNameLocatorInLeftmenu.isDisplayed(), "Brand Name Is not Displayed In The Left Menu.");
		log.info("Verified Brand Link In The Left Menu.");
		return this;
	}

	@Step("Verify Manufacturer Link In The Left Menu.")
	public ProductPageObjects verifyManufacturersInTheLeftMenu() {
		waiting.waitForVisibilityOfElement(manufacturerNameLocatorInLeftmenu, 10);
		Assert.assertTrue(manufacturerNameLocatorInLeftmenu.isDisplayed(),
				"Manufacturers Name Is not Displayed In The Left Menu.");
		log.info("Verified Manufacturer Link In The Left Menu");
		return this;
	}

	@Step("Verify My Account Link In The Left Menu.")
	public ProductPageObjects verifyMyAccountInTheLeftMenu() {
		waiting.waitForVisibilityOfElement(myAccountLocatorInLeftmenu, 10);
		Assert.assertTrue(myAccountLocatorInLeftmenu.isDisplayed(), "My Account is not Displayed In The Left Menu.");
		log.info("Verified My Account Link In The Left Menu");
		return this;
	}

	@Step("Verify My Account Section In Left Menu:{0}")
	public ProductPageObjects verifyMyAccountSectionInTheLeftMenu(String myAccountSection) throws InterruptedException {
		Thread.sleep(1500);
		String[] expectedMyAccountSection = myAccountSection.split(">");
		String[] expectedMyAccountSection1 = expectedMyAccountSection[0].split(",");
		String[] expectedMyAccountSection2 = expectedMyAccountSection[1].split(",");
		waiting.waitForVisibilityOfElements(myAcountSectionLocator, 10);
		try {
			for (int i = 0; i < myAcountSectionLocator.size(); i++) {
				Assert.assertEquals(myAcountSectionLocator.get(i).getText().trim(), expectedMyAccountSection1[i],
						"My Account Drop Down is not :" + expectedMyAccountSection1[i] + ". It is :"
								+ myAcountSectionLocator.get(i).getText().trim());
				log.info("Verified My Account Section In Left Menu: " + expectedMyAccountSection1[i]);
			}
		} catch (Exception | AssertionError e) {
			try {
				for (int i = 0; i < myAcountSectionLocator.size(); i++) {
					Assert.assertEquals(myAcountSectionLocator.get(i).getText().trim(), expectedMyAccountSection2[i],
							"My Account Drop Down is not :" + expectedMyAccountSection2[i] + ". It is :"
									+ myAcountSectionLocator.get(i).getText().trim());
					log.info("Verified My Account Section In Left Menu with 2nd List: " + expectedMyAccountSection2[i]);

				}
			} catch (Exception | AssertionError f) {
				Assert.assertFalse(true,
						"For logged in User My Account DropDown Link value is differnt from expected.");
			}

		}

		return this;
	}

	@Step("Click On My Account Link Of Left Menu.")
	public ProductPageObjects clickOnMyAccountLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(myAccountLinkLocatorInLeftMenu, 10);
		myAccountLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On My Account Link Of Left Menu");
		return this;
	}

	@Step("Click On Change Password Link Of Left Menu.")
	public ProductPageObjects clickOnChangePasswordLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(changePasswordLinkLocatorInLeftMenu, 10);
		changePasswordLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Change Password Of Left Menu");
		return this;
	}

	@Step("Click On Manage credit card Link Of Left Menu.")
	public ProductPageObjects clickOnManageCreditCardLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(addNewCreditCardLinkLocatorInLeftMenu, 10);
		addNewCreditCardLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On manage credit card Link Of Left Menu");
		return this;
	}

	@Step("Click On Edit Contact link Of Left Menu.")
	public ProductPageObjects clickOnEditContactLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(editContactInfoLinkLocatorInLeftMenu, 10);
		editContactInfoLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Edit Contact link Of Left Menu");
		return this;
	}

	@Step("Click On Open Order link Of Left Menu.")
	public ProductPageObjects clickOnOpenOrderLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(openOrderLinkLocatorInLeftMenu, 10);
		openOrderLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Open Order link Of Left Menu.");
		return this;
	}

	@Step("Click On Order History link Of Left Menu.")
	public ProductPageObjects clickOnOrderHistorylinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(orderHistoryLinkLocatorInLeftMenu, 10);
		orderHistoryLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Order History link Of Left Menu.");
		return this;
	}

	@Step("Click On My Product Group link Of Left Menu.")
	public ProductPageObjects clickOnMyProductGroupLinkInTheLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(myProductGroupLinkLocatorInLeftMenu, 10);
		myProductGroupLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On My Product Group link Of Left Menu.");
		return this;
	}

	@Step("Click On My Saved Cart link Of Left Menu.")
	public ProductPageObjects clickOnMySaveCartLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(mySaveCartLinkLocatorInLeftMenu, 10);
		mySaveCartLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On My Saved Cart link Of Left Menu.");
		return this;
	}

	@Step("Click On User Management link Of Left Menu.")
	public ProductPageObjects clickOnUserManagementLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(userManagementLinkLocatorInLeftMenu, 10);
		userManagementLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On User Management link Of Left Menu.");
		return this;
	}

	@Step("Click On Request For Quote link Of Left Menu.")
	public ProductPageObjects clickOnRequestForQuoteLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(requestForQuoteLinkLocatorInLeftMenu, 10);
		requestForQuoteLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Request For Quote link Of Left Menu.");
		return this;

	}

	@Step("Click On Configure Procurement System link Of Left Menu.")
	public ProductPageObjects clickOnConfigureProcurementSystemLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(configureProcumentSystemLinkLocatorInLeftMenu, 10);
		configureProcumentSystemLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Configure Procurement System link Of Left Menu.");
		return this;

	}

	@Step("Click On Specific Brand Name Of Left Menu:{0}")
	public ProductPageObjects clickOnSpecificBrandFromLeftMenu(String searchTextForBrand) throws InterruptedException {
		Thread.sleep(2500);
		WebElement brandName = getDriver().findElement(
				By.xpath("//div[@id='brandsMenu']//a[normalize-space(text())='" + searchTextForBrand + "']"));
		((JavascriptExecutor)getDriver()).executeScript("arguments[0].click();", brandName);
		//brandName.click();
		Thread.sleep(1200);
		log.info("Clicked On Specific Brand Name Of Left Menu: " + searchTextForBrand);
		return this;

	}

	@Step("Click On Quick Order Pad link Of Left Menu.")
	public ProductPageObjects clickOnQuickOrderPadLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(quickOrderPadLinkLocatorInLeftMenu, 10);
		quickOrderPadLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Quick Order Pad link Of Left Menu");
		return this;
	}

	@Step("Click On Approval Cart List link Of Left Menu.")
	public ProductPageObjects clickOnApprovalCartListInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(approvalCartListLinkLocatorInLeftMenu, 10);
		approvalCartListLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Approval Cart List link Of Left Menu");
		return this;
	}

	@Step("Click On Logout link Of Left Menu.")
	public ProductPageObjects clickOnLogoutLinkInLeftMenu() throws InterruptedException {
		waiting.waitForVisibilityOfElement(logOutLinkLocatorInLeftMenu, 10);
		logOutLinkLocatorInLeftMenu.click();
		Thread.sleep(1200);
		log.info("Clicked On Logout link Of Left Menu");
		return this;

	}

	@Step("Verify sort by dropdown is displayed.")
	public ProductPageObjects verifySortByDrpdownValues(String[] expectedSortByDropdownOptions) {
		for (int i = 1; i < sortByValues.size(); i++) {
			String elementValue = sortByValues.get(i).getText();
			soft.assertEquals(elementValue, expectedSortByDropdownOptions, "sort By dropdown values are available");
			log.info("Verified sort by dropdown is displayed: " + elementValue);
		}
		return this;
	}

	@Step("Select option in Drop down: {0}")
	public ProductPageObjects selectOptionInDropdown(String sortByOption) throws InterruptedException {

		// waiting.waitForVisibilityOfElement(sortByLocator, 10); Thread.sleep(2000);
		try {
			Select select = new Select(sortByLocator);
			select.selectByVisibleText(sortByOption);
			log.info("Selected option in Drop down: " + sortByOption);
		} catch (NoSuchElementException e) {
			log.info("option value not find in dropdown");
		}

		return this;
	}

	@Step("Verify sorting by option: {0}")
	public ProductPageObjects sortingByOptions(String dropdownOption) throws InterruptedException {
		List<WebElement> allListItems = getDriver()
				.findElements(By.xpath("//li[@class='listView']/descendant::strong[normalize-space(text())='"
						+ dropdownOption + "'] /following-sibling::span"));
		// List<WebElement> Anchortags=Row.findElements(By.tagName("a"));

		// Linked List to Store ProductNames
		// LinkedList productNames = new LinkedList();
		LinkedList<String> productNames = new LinkedList<String>();

		int size = allListItems.size();
		log.info("size of all list items is" + size);
		for (WebElement temp : allListItems) {
			String sTemp = temp.getText();
			log.info(sTemp);
			// Important
			// to remove extra spaces and to Lowercase Every product name**
			productNames.add(sTemp.toLowerCase().trim());
		}
		log.info(productNames);

		// Check SortedOr NOt?

		Boolean num = checkAscendingOrder(productNames);
		log.info("boolean value" + num);
		if (num == true) {

			log.info("Products names are Sorted in ascending Order");
		} else {
			log.info("Products names are Sorted in decsending Order");
		}
		log.info("New List:--" + productNames);
		log.info("Size is " + size);
		Thread.sleep(2000);
		return this;
	}

	@Step("Verify ascending order")
	public static boolean checkAscendingOrder(LinkedList<String> Names) {
		String previous = "";

		for (String current : Names) {
			if (current.compareTo(previous) < 0) {
				return false;
			}
			previous = current;
		}
		return true;
	}

	@Step("Verify values in drop down")
	public ProductPageObjects verify_Values_In_Dropdown(String[] expectedSortByDropdownOptions) {
		// boolean bValue = false;
		List<String> list = new ArrayList<String>();
		for (String strValue : expectedSortByDropdownOptions) {
			boolean bflag = false;
			for (WebElement element : sortByValues) {
				String elementValue = element.getText();
				if (strValue.equals(elementValue)) {
					bflag = true;
				}
			}
			if (!bflag)
				list.add(strValue);
		}

		if (list.size() > 0) {
			for (String strList : list) {
				log.info("Value not present in dropdown: " + strList);
			}
			// Assign false if any of the value not found in dropdown
		} else {
			// Assign true if all values found in dropdown
			log.info("All value(s) found in dropdown");
		}
		return this;
	}

}
