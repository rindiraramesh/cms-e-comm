package pageobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class CategoryPageObjects extends PageInitializer {

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(CategoryPageObjects.class);

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	WaitingMethods waitingMethods = new WaitingMethods(getDriver());

	@FindBy(xpath = "//a[contains(text(),'roducts')]")
	public WebElement shopProductsLinkLocator;

	@FindBy(xpath = "//div[@id='categoryMenu']//a")
	public List<WebElement> shopProductsLevel1LinksLocator;

	@FindBy(xpath = "//div[@id='col_Category']//li")
	public List<WebElement> shopProductsLevel2LinksLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumbLocator;

	@FindBy(css = "#categoryMenu>div>div>ul>li>a")
	public List<WebElement> allDisplayedLeftMenuShopProducts;

	@FindBy(css = "#brandsMenu>div>div>ul>li>a")
	public List<WebElement> allDisplayedLeftMenuBrands;

	@FindBy(xpath = "//a[@href='/products']/../following-sibling::ul/li/span/a")
	public List<WebElement> allDisplayedMenuShopProducts;

	@FindBy(xpath = "//div[@id='normalHead']//a[normalize-space(text())='Manufacturers']")
	public WebElement manufacturersLinkLocator;

	@FindBy(xpath = "//*[@id='normalHead']/nav/div/ul/li[3]/div/p/a")
	public WebElement viewAllManufacturersLinkLocator;

	@FindBy(css = ".grid>div>ul>li>a")
	public List<WebElement> allDisplayedMenufacturers;

	@FindBy(xpath = "//ul[contains(@class,'navList_manufacturersMenu')]//a")
	public List<WebElement> allDisplayedMenufacturersLeftMenuLocator;

	@FindBy(css = ".row.brandListCont>div>div>div>div")
	public List<WebElement> allDisplayedMenuBrands;

	@FindBy(css = "#brandsMenu>div>div.slimScrollBar")
	public WebElement verticalBrandsSliderLocator;

	@FindBy(xpath = "//a[normalize-space(text())='Brands']")
	public WebElement brandsLinkLocator;

	@FindBy(xpath = "//*[@id='normalHead']/nav/div/ul/li[2]/div/p/a")
	public WebElement viewAllBrandsLinkLocator;

	@FindBy(xpath = "//li/a[text()='Faucets']")
	public WebElement level1CategorySelectedFromLeftMenuLocator;

	@FindBy(xpath = "//h3[contains(@class,'cimm_page-title')]")
	public WebElement pageTitleHeaderLocator;

	@FindBy(xpath = "//p[contains(text(),'result')]")
	public WebElement resultsHeaderLocator;

	@FindBy(xpath = "//div[contains(@class,'category')]//div//h4/a")
	public List<WebElement> levelsCategoryLocator;

	@FindBy(xpath = "//*[@id='brandsMenu']//a")
	public List<WebElement> brandLeftMenuLinksLocator;

	@FindBy(xpath = "//*[@id='productListBlock']//strong[contains(text(),'rand')]/following-sibling::span")
	public List<WebElement> itemsDisplayedForBrand;

	@FindBy(xpath = "//*[@id='manufacturersMenu']//a")
	public List<WebElement> manufacturersLeftMenuLinksLocator;

	@FindBy(xpath = "//*[@id='productListBlock']//strong[contains(text(),'fact')]/following-sibling::span")
	public List<WebElement> itemsDisplayedFormanufacturers;

	@FindBy(xpath = "//*[@id='leftMenu']/div/div/div[4]/ul/li[5]/a")
	public WebElement openOrdersLeftMenuLocator;

	@FindBy(css = "#openOrderTable>tbody>tr")
	public List<WebElement> openOrderTabelLocator;

	@FindBy(xpath = ".//*[@id='col_Category']/ul/li[1]/label")
	public WebElement selectedLeftMenuCheckboxLocator;

	@FindBy(css = "#productListBlock>li>ul>li:nth-child(2)>h3>a")
	public List<WebElement> CategoryItemsDisplayedLocator;

	@Step("Verify bread crumb of Category page :{0}")
	public CategoryPageObjects verifyBreadCrumbOfCategoryPage(String breadCrumbs) {
		String[] expectedBreadCrumbs = breadCrumbs.split(",");
		waitingMethods.waitForVisibilityOfElements(breadCrumbLocator, 10);
		for (int i = 0; i < breadCrumbLocator.size(); i++) {
			Assert.assertEquals(breadCrumbLocator.get(i).getText().trim(), expectedBreadCrumbs[i].trim(),
					"Bread Crumb of Category Page is not : " + expectedBreadCrumbs[i].trim() + " . It is : "
							+ breadCrumbLocator.get(i).getText().trim());
		}
		log.info("Bread crum is verified");
		return this;
	}

	@Step("Click on Shop Products menu")
	public CategoryPageObjects clickOnShopProducts() throws Exception {
		waitingMethods.waitForVisibilityOfElement(shopProductsLinkLocator, 5);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", shopProductsLinkLocator);
		log.info("Clicked on shop products menu");
		return this;
	}

	@Step("Verify  details of Shop Products displayed in left menu with expected list of shop products")
	public CategoryPageObjects verifyLeftMenuCategoryPage_ShopProducts() throws Throwable {
		utilityMethods.moveToElement(shopProductsLinkLocator);
		/*
		 * List<String> allDisplayProductsLinksMenu =
		 * allDisplayedMenuShopProducts.stream() .map(WebElement::getText)
		 * .collect(Collectors.toList());
		 */

		List<String> allDisplayProductsLinksMenu = allDisplayedMenuShopProducts.stream()
				.map(s -> s.getText().toLowerCase())
				.collect(Collectors.toList());

		Collections.sort(allDisplayProductsLinksMenu);
		log.info("Displayed all products in menu is stored in list and sorted");
		clickOnShopProducts();
		List<String> allDisplayedProductCategoryLeftMenu = new ArrayList<String>();

		for (WebElement valueBM : allDisplayedLeftMenuShopProducts) {
			allDisplayedProductCategoryLeftMenu
					.add(((String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerHTML;",
							valueBM)).replace("amp;", "").trim().toLowerCase());
		}

		Collections.sort(allDisplayedProductCategoryLeftMenu);
		log.info("Displayed all products in left menu is stored in list and sorted");
		Assert.assertEquals(allDisplayProductsLinksMenu, allDisplayedProductCategoryLeftMenu);
		log.info("Verified details of Shop Products displayed in left menu with expected list of shop products");
		return this;

	}

	@Step("Verify details of brands displayed in left menu with expected list of brands")
	public CategoryPageObjects verifyLeftMenuCategoryPage_Brands() throws Throwable {
		utilityMethods.moveToElement(brandsLinkLocator);
		utilityMethods.moveToElementAndClick(viewAllBrandsLinkLocator);
		log.info("Clicked on View All brands link");
		/*
		 * List<String> allDisplayBrandsAvailiable =
		 * allDisplayedMenuBrands.stream().map(WebElement::getText).collect(
		 * Collectors.toList());
		 */

		/*
		 * List<String> rightClickOptions = speedEntryRightClickOptionsLocator.stream()
		 * .map(s -> s.getAttribute("innerText")) .collect(Collectors.toList());
		 */

		List<String> allDisplayBrandsAvailiable = allDisplayedMenuBrands.stream()
				.map(s -> s.getText().replace("&", "").trim())
				.collect(Collectors.toList());

		Collections.sort(allDisplayBrandsAvailiable);
		log.info("Displayed all available brands is stored in list and sorted");
		clickOnShopProducts();

		List<String> allDisplayedBrandsLeftMenu = new ArrayList<String>();
		for (WebElement valueBM : allDisplayedLeftMenuBrands) {
			allDisplayedBrandsLeftMenu
					.add(((String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerHTML;",
							valueBM)).replace("amp;", "").replace("&", "").trim());
		}
		Collections.sort(allDisplayedBrandsLeftMenu);
		log.info("Displayed all brands in left menu is stored in list and sorted");
		Assert.assertEquals(allDisplayBrandsAvailiable, allDisplayedBrandsLeftMenu);
		log.info("Verified details of brands displayed in left menu with expected list of brands");
		return this;
	}

	@Step("Verify details of Manufacturers displayed in left menu with expected list of Manufacturers")
	public CategoryPageObjects verifyLeftMenuCategoryPage_Manufacturers() throws Exception {

		utilityMethods.moveToElement(manufacturersLinkLocator);
		utilityMethods.moveToElementAndClick(viewAllManufacturersLinkLocator);
		log.info("Clicked in All manufacturers link");
		List<String> allDisplayManufacturers = allDisplayedMenufacturers.stream().map(WebElement::getText).collect(
				Collectors.toList());
		Collections.sort(allDisplayManufacturers);
		log.info("Displayed all Manufacturers is stored in list and sorted");
		clickOnShopProducts();
		List<String> allDisplayedManuLeftMenu = new ArrayList<String>();
		for (WebElement valueBM : allDisplayedMenufacturersLeftMenuLocator) {
			allDisplayedManuLeftMenu.add(((String) ((JavascriptExecutor) getDriver())
					.executeScript("return arguments[0].innerHTML;", valueBM)).replace("amp;", "").trim());
		}
		Collections.sort(allDisplayedManuLeftMenu);
		log.info("Displayed all Manufacturers in left menu is stored in list and sorted");
		Assert.assertEquals(allDisplayManufacturers, allDisplayedManuLeftMenu);
		log.info("Verified details of Manufacturers displayed in left menu with expected list of Manufacturers");
		return this;
	}

	@Step("Click on Shop Products category level 1")
	public CategoryPageObjects clickOnShopProductsLevel1FromLeftMenu() throws Exception {
		waitingMethods.waitForVisibilityOfElement(shopProductsLevel1LinksLocator.get(0), 5);
		Level1Product = shopProductsLevel1LinksLocator.get(0).getText().trim();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				shopProductsLevel1LinksLocator.get(0));
		log.info("Clicked on 1st Shop Product category level 1");
		return this;
	}

	public String Level1Product = null;

	@Step("Verify category levels from left menu")
	public CategoryPageObjects verifyProductsCategoryLevels() {

		softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), Level1Product, "Navigated to different page");

		log.info("Navigation is verified For the level 1");

		String Level2Product = shopProductsLevel2LinksLocator.get(0).getText().trim();

		shopProductsLevel2LinksLocator.get(0).click();

		log.info("Clicked on level 2 category :");

		softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), Level2Product, "Navigated to different page");

		/*
		 * String productExpected =
		 * level3CategorySelectedThruLeftMenuLocator.getText().replace(" (10)", "");
		 * 
		 * level3CategorySelectedThruLeftMenuLocator.click();
		 * log.info("Clicked on level 3 category :" + productExpected);
		 */

		/*
		 * softAssert.assertTrue(level3CategorySelectedThruLeftMenuHeaderLocator.getText
		 * ().toLowerCase().contains( productExpected.toLowerCase()),
		 * "Navigated to different page 2");
		 */
		// log.info("Navigation is verified based on the page header");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify List of items displayed after clicking the brand link from left menu")
	public CategoryPageObjects verifyListOfItemsDisplayedForBrandFromLeftMenu() {

		String expectedBrand = brandLeftMenuLinksLocator.get(0).getText().trim();
		log.info(expectedBrand);
		utilityMethods.moveToElementAndClick(brandLeftMenuLinksLocator.get(0));
		for (WebElement brand : itemsDisplayedForBrand) {
			// System.out.println("Actual"+brand.getText().trim());
			softAssert.assertEquals(brand.getText().trim(), expectedBrand, "Different brand is displayed");
		}
		log.info("Verified the List of items displayed contains the same brand from left menu");
		softAssert.assertAll();
		return this;
	}

	@Step("Verify List of items display with same manufacturer name clicked from left menu")
	public CategoryPageObjects verifyListOfItemsDisplayedForManufacturersFromLeftMenu() {

		String expectedManufacturers = manufacturersLeftMenuLinksLocator.get(0).getText().trim();
		log.info("Manufacturer link from Left menu =" + expectedManufacturers);

		utilityMethods.moveToElementAndClick(manufacturersLeftMenuLinksLocator.get(0));
		for (WebElement Manufacturer : itemsDisplayedFormanufacturers) {
			// System.out.println("Actual" + brand.getText().trim().substring(0, 6));
			softAssert.assertEquals(Manufacturer.getText().trim(), expectedManufacturers,
					"Manufacturer name displayed is different for the items");
		}
		softAssert.assertAll();
		log.info("Verified List of items display with same manufacturer name clicked from left menu");
		return this;
	}

	@Step("Click on Open orders link in Category page")
	public OpenOrdersPageObjects clickOpenOrdersLinkInLeftMenu() {
		waitingMethods.waitForVisibilityOfElement(openOrdersLeftMenuLocator, 5);
		utilityMethods.moveToElementAndClick(openOrdersLeftMenuLocator);
		log.info("Click on Open orders link in Category page");
		return openOrdersPage();
	}

	@Step("Verify multipal level categories")
	public CategoryPageObjects verifyMultipalLevelCategories() {

		try {

			softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), Level1Product.toUpperCase(),
					"Navigated to different page");

			log.info("Navigation is verified For the level 1 :" + Level1Product);

			if (levelsCategoryLocator.get(0).isDisplayed()) {

				String levelsCategory = levelsCategoryLocator.get(0).getText();

				levelsCategoryLocator.get(0).click();

				try {
					if (pageTitleHeaderLocator.isDisplayed()) {
						softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), levelsCategory,
								"Navigated to different page");
					}
				} catch (Exception e) {
					softAssert.assertTrue(resultsHeaderLocator.getText().trim().toLowerCase().contains(
							levelsCategory.toLowerCase().trim()), "Navigated to different page");
				}

				log.info("Navigation is verified For the level 2 :" + levelsCategory);

			}

			if (levelsCategoryLocator.get(0).isDisplayed()) {

				String levelsCategory = levelsCategoryLocator.get(0).getText();

				levelsCategoryLocator.get(0).click();
				try {
					if (pageTitleHeaderLocator.isDisplayed()) {
						softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), levelsCategory,
								"Navigated to different page");
					}
				} catch (Exception e) {
					softAssert.assertTrue(resultsHeaderLocator.getText().trim().toLowerCase().contains(
							levelsCategory.toLowerCase().trim()), "Navigated to different page");
				}

				log.info("Navigation is verified For the level 3 :" + levelsCategory);

			}

			if (levelsCategoryLocator.get(0).isDisplayed()) {

				String levelsCategory = levelsCategoryLocator.get(0).getText();

				levelsCategoryLocator.get(0).click();

				try {
					if (pageTitleHeaderLocator.isDisplayed()) {
						softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), levelsCategory,
								"Navigated to different page");
					}
				} catch (Exception e) {
					softAssert.assertTrue(resultsHeaderLocator.getText().trim().toLowerCase().contains(
							levelsCategory.toLowerCase().trim()), "Navigated to different page");
				}

				log.info("Navigation is verified For the level 4 :" + levelsCategory);

			}

			if (levelsCategoryLocator.get(0).isDisplayed()) {

				String levelsCategory = levelsCategoryLocator.get(0).getText();

				levelsCategoryLocator.get(0).click();
				try {
					if (pageTitleHeaderLocator.isDisplayed()) {
						softAssert.assertEquals(pageTitleHeaderLocator.getText().trim(), levelsCategory,
								"Navigated to different page");
					}
				} catch (Exception e) {
					softAssert.assertTrue(resultsHeaderLocator.getText().trim().toLowerCase().contains(
							levelsCategory.toLowerCase().trim()), "Navigated to different page");
				}

				log.info("Navigation is verified For the level 5 :" + levelsCategory);

			}
		} catch (Exception e) {

		}
		softAssert.assertAll();
		return this;
	}

}
