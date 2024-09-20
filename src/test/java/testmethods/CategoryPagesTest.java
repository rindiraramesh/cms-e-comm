package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class CategoryPagesTest extends PageInitializer {

	Logger log = Logger.getLogger(CategoryPagesTest.class);

	LoginModuleTest login = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	@Feature("Category Page")
	@Description("Verify bread crum details of Category page")
	@Test(groups = { "Category Page", "regression", })
	public void verifyBreadcrumbsOfCategoryPages() throws InterruptedException {
		log.info("-----------------verifyBreadcrumbsOfCategoryPages Started------------------");

		try {
			homePage().categoryPage().clickOnShopProducts().verifyBreadCrumbOfCategoryPage(
					data.getBreadCrumForcategoryPage());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyBreadcrumbsOfCategoryPages failed :" + t.getMessage());
			Assert.fail("verifyBreadcrumbsOfCategoryPages failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyBreadcrumbsOfCategoryPages Completed------------------");
	}

	//Need to review and update the logic as per new implementation 
	@Feature("Category Page")
	@Description("Verify LeftMenu details of Category page")
	@Test(groups = { "Category Page", "regression" }, enabled=false)
	public void verifyLeftMenuOnCategoryPages() throws Exception {
		log.info("-----------------verifyLeftMenuOnCategoryPages Started------------------");
		try {
			homePage().categoryPage()
					.clickOnShopProducts()
					.verifyLeftMenuCategoryPage_ShopProducts()
					.verifyLeftMenuCategoryPage_Brands()
					.verifyLeftMenuCategoryPage_Manufacturers();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLeftMenuOnCategoryPages failed :" + t.getMessage());
			Assert.fail("verifyLeftMenuOnCategoryPages failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyLeftMenuOnCategoryPages Completed------------------");
	}

	@Feature("Category Page")
	@Description("Verify Category details item selected from left menu")
	@Test(groups = { "Category Page", "regression", "Smoke Test" }, enabled=false)
	public void verifyLeftMenuProductCategoryPages() throws InterruptedException {
		log.info("-----------------verifyLeftMenuProductLFCategoryPages Started------------------");
		try {
			homePage().categoryPage()
					.clickOnShopProducts()
					.clickOnShopProductsLevel1FromLeftMenu()
					.verifyProductsCategoryLevels();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLeftMenuProductLFCategoryPages failed :" + t.getMessage());
			Assert.fail("verifyLeftMenuProductLFCategoryPages failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyLeftMenuProductLFCategoryPages Completed------------------");
	}

	@Feature("Category Page")
	@Description("Verify List of items displayed for brands from LeftMenu")
	@Test(groups = { "Category Page", "regression", })
	public void verifyLeftMenuBrandPages() throws InterruptedException {
		log.info("-----------------verifyLeftMenuBrandPages Started------------------");
		try {
			homePage().categoryPage().clickOnShopProducts().verifyListOfItemsDisplayedForBrandFromLeftMenu();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLeftMenuBrandPages failed :" + t.getMessage());
			Assert.fail("verifyLeftMenuBrandPages failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyLeftMenuBrandPages Completed------------------");
	}

	@Feature("Category Page")
	@Description("Verify List of items displayed for Manufacturers from LeftMenu")
	@Test(groups = { "Category Page", "regression", })
	public void verifyLeftMenuManufacturersPages() throws InterruptedException {
		log.info("-----------------verifyLeftMenuManufacturersPages Started------------------");
		try {
			homePage().categoryPage().clickOnShopProducts().verifyListOfItemsDisplayedForManufacturersFromLeftMenu();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLeftMenuManufacturersPages failed :" + t.getMessage());
			Assert.fail("verifyLeftMenuManufacturersPages failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyLeftMenuManufacturersPages Completed------------------");
	}

	@Feature("Category Page")
	@Description("Verify List of items displayed for L1 and L2 category pages")
	@Test(groups = { "Category Page", "regression" }, enabled=false)
	public void verifyLeftMenuLMultipalLevelCategoryPages() throws InterruptedException {
		log.info("-----------------verifyLeftMenuLMultipalLevelCategoryPages Started------------------");
		try {
			homePage().categoryPage()
					.clickOnShopProducts()
					.clickOnShopProductsLevel1FromLeftMenu()
					.verifyMultipalLevelCategories();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyLeftMenuLMultipalLevelCategoryPages failed :" + t.getMessage());
			Assert.fail("verifyLeftMenuLMultipalLevelCategoryPages failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyLeftMenuLMultipalLevelCategoryPages Completed------------------");
	}

}
