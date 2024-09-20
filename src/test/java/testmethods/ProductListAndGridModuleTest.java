package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class ProductListAndGridModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ProductListAndGridModuleTest.class);

	LoginModuleTest login = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	public void navigateToPLP(String searchText) throws Exception {
		homePage().clickOnLogo();
		homePage().searchText(searchText).clickOnSearch();
		productListPage().changeToListView();
	}

	@Feature("Product List/Grid")
	@Test(groups = { "ProductListAndGrid", "regression", "Smoke Test" })
	@Description("Verify Product detail fields in list page")
	public void verifyProductDetailsInListPage() throws Exception {
		log.info("---------------- verifyProductDetailsInListPage Started---------------- ");
		try {
			navigateToPLP(data.getSearchText());
			productListPage().verifyProductDetailsInListPage();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyProductDetailsInListPage failed :" + t.getMessage());
			Assert.fail("verifyProductDetailsInListPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyProductDetailsInListPage Completed---------------- ");

	}

	@Feature("Product List/Grid")
	@Test(groups = { "ProductListAndGrid", "regression", })
	@Description("Verifies Product detail fields in list page product mode like PN,UPC,Add to cart button etc.")
	public void verifyProductDetailsInListPage_ProductMode() throws Exception {
		log.info("----------------verifyProductDetailsInListPage_ProductMode Started---------------- ");
		try {
			navigateToPLP(data.getSearchTextForProductMode());
			productListPage().verifyProductDetailsInListPage_ProductMode("listview")
					.clickOnChangeView()
					.verifyProductDetailsInListPage_ProductMode("gridview");
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyProductDetailsInListPage_ProductMode failed :" + t.getMessage());
			Assert.fail("verifyProductDetailsInListPage_ProductMode failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyProductDetailsInListPage_ProductMode Completed---------------- ");

	}

	// Getting NP, check
	@Feature("Product List/Grid")
	@Test(enabled = false, groups = { "ProductListAndGrid", "regression" })
	@Description("verifying sort By dropdown values")
	public void verifySortByDropdownOptions() throws Exception {
		log.info("----------------verifySortByDropdownOptions Started---------------- ");
		try {
			navigateToPLP(data.getSearchText());
			productListPage().verifySortByDropdown(data.getSortByOptionsInPLPAll().split(";"));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifySortByDropdownOptions failed :" + t.getMessage());
			Assert.fail("verifySortByDropdownOptions failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifySortByDropdownOptions Completed---------------- ");

	}

	@Feature("Product Sort")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verifies product sort functionality in item list page by PN,MPN,UPC")
	public void verifySortByFuncionality() throws Exception {
		log.info("----------------verifySortByFuncionality Started---------------- ");
		try {
			String[] sortByOpts = data.getSortByOptionsInPLP().split(";");
			navigateToPLP(data.getSearchText());
			productListPage().selectAndVerifySortByOptions(sortByOpts);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifySortByFuncionality failed :" + t.getMessage());
			Assert.fail("verifySortByFuncionality failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifySortByFuncionality Completed---------------- ");

	}

	@Feature("Product List/Grid")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verifies show items per page functionality")
	private void verifyShowItemsPerPage() throws Exception {
		log.info("---------------- verifyShowItemsPerPage Started---------------- ");
		try {
			navigateToPLP(data.getSearchText());
			productListPage().selectAndVerifyItemsPerPage(data.getItemsPerPage().split(";"));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyShowItemsPerPage failed :" + t.getMessage());
			Assert.fail("verifyShowItemsPerPage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyShowItemsPerPage Completed---------------- ");

	}

	@Feature("Product List/Grid")
	@Test(enabled = true, groups = { "ProductListAndGrid", "regression", "Smoke Test" })
	@Description("Verifies List and Grid views of items")
	private void verifyListGridViewOption() throws Throwable {
		log.info("----------------verifyListGridViewOption Started---------------- ");
		try {
			navigateToPLP(data.getSearchText());
			productListPage().verifyListView()
					.verifyListViewTooltip()
					.clickOnChangeView()
					.verifyGridView()
					.clickOnChangeView()
					.verifyGridViewTooltip();
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyListGridViewOption failed :" + t.getMessage());
			Assert.fail("verifyListGridViewOption failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyListGridViewOption Completed---------------- ");

	}

	@Feature("Product List/Grid")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verifies Pagination functionality")
	private void verifyPaginationFunctionality() throws Exception {
		log.info("----------------verifyPaginationFunctionality Started---------------- ");
		try {
			// navigateToPLP(data.getSearchText());
			navigateToPLP("11");
			productListPage().verifyDefaultPagination()
					.clickOnSpecificPage(2)
					.verifySelectedPage("2")
					.clickOnNextPageButton()
					.verifyPaginationAfterClickingOnNextButton()
					.clickOnPreviousButton()
					.verifyDefaultPagination();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyPaginationFunctionality failed :" + t.getMessage());
			Assert.fail("verifyPaginationFunctionality failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyPaginationFunctionality Completed---------------- ");

	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression", })
	@Description("Verifies product filter functionality in item list page by selecting multiple categories")
	public void verifyFilterByCategories() throws Exception {
		log.info("----------------verifyFilterByCategories Started---------------- ");
		try {
			navigateToPLP(data.getGeneralSearchText());
			productListPage().filterAndVerifySearchByCategories(data.getCategoriesListForFilter().split(";"));

			productListPage()

					.clearAndVerifyFilteredCategory(data.getCategoriesListForFilter().split(";")[0]);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFilterByCategories failed :" + t.getMessage());
			Assert.fail("verifyFilterByCategories failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFilterByCategories Completed---------------- ");
	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression", })
	@Description("Verifies product filter functionality in item list page by selecting multiple brands")
	public void verifyFilterByBrands() throws Exception {
		log.info("----------------verifyFilterByBrands Started---------------- ");
		try {
			navigateToPLP(data.getGeneralSearchText());

			productListPage().filterAndVerifySearchByBrands(data.getBrandsListForFilter().split(";"));
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFilterByBrands failed :" + t.getMessage());
			Assert.fail("verifyFilterByBrands failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFilterByBrands Completed---------------- ");

	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verifies the display of Refine Section in item list Page.")
	public void verifyRefineSectionInListPage(ITestContext context) throws Exception {
		log.info("----------------verifyRefineSectionInListPage Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			navigateToPLP("11");
					productListPage()
					.verifyRefineResultSectionInListPage();
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyRefineSectionInListPage failed :" + t.getMessage());
			Assert.fail("verifyRefineSectionInListPage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyRefineSectionInListPage Completed---------------- ");

	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verify the functionality of Refine Results through category.")
	public void verifyRefineResultThroughCategory(ITestContext context) throws Exception {
		log.info("---------------- verifyRefineResultThroughCategory Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			navigateToPLP("11");
			String expectedNumberOfItems = productListPage()
					.getNumberOfItemsOfCategory(1);
			productListPage().clickOnSpecificCheckBoxOfCategory(1);
			Thread.sleep(1200);
			productListPage().verifyRefineResultFunctionality(Integer.parseInt(expectedNumberOfItems));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyRefineResultThroughCategory failed :" + t.getMessage());
			Assert.fail("verifyRefineResultThroughCategory failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyRefineResultThroughCategory Completed---------------- ");
	}

	//No data available in 973 stage
	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression" }, enabled=false)
	@Description("Verify the functionality of Refine Results through category.")
	public void verifyRefineResultOfMultipleFilterThroughCategory(ITestContext context) throws Exception {
		log.info("---------------- verifyRefineResultOfMultipleFilterThroughCategory Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			String expectedNumberOfItems1 = homePage()

					.searchText("11")
					.clickOnSearch()
					.productListPage()
					.getNumberOfItemsOfCategory(1);

			String expectedNumberOfItems2 = productListPage().getNumberOfItemsOfCategory(3);

			int totalNumberOfItems = Integer.parseInt(expectedNumberOfItems1)
					+ Integer.parseInt(expectedNumberOfItems2);

			productListPage()

					.clickOnSpecificCheckBoxOfCategory(1);
			Thread.sleep(1200);

			productListPage().clickOnSpecificCheckBoxOfCategory(2);

			Thread.sleep(1200);

			productListPage()

					.verifyRefineResultFunctionality(totalNumberOfItems);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyRefineResultOfMultipleFilterThroughCategory failed :" + t.getMessage());
			Assert.fail("verifyRefineResultOfMultipleFilterThroughCategory failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyRefineResultOfMultipleFilterThroughCategory Completed---------------- ");
	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verify the functionality of Refine Results through brands.")
	public void verifyRefineResultThroughBrand(ITestContext context) throws Exception {
		log.info("---------------- verifyRefineResultThroughBrand Started---------------- ");
		try {
			
			navigateToPLP("11");
			String expectedNumberOfItems = productListPage()
					.getNumberOfItemsOfBrand(1);
			productListPage().clickOnSpecificCheckBoxOfBrand(1);
			Thread.sleep(1200);
			productListPage().verifyRefineResultFunctionality(Integer.parseInt(expectedNumberOfItems));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyRefineResultThroughBrand failed :" + t.getMessage());
			Assert.fail("verifyRefineResultThroughBrand failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyRefineResultThroughBrand Completed---------------- ");
	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression" })
	@Description("Verify the functionality of Refine Results through Manufacturer.")
	public void verifyRefineResultThroughManufacturer(ITestContext context) throws Exception {
		log.info("----------------verifyRefineResultThroughManufacturer Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			navigateToPLP("11");
			String expectedNumberOfItems = productListPage()
					.getNumberOfItemsOfSpecificManufacturer(1);
			productListPage()

					.clickOnSpecificCheckBoxOfManufacturer(1);
			Thread.sleep(1200);
			productListPage()

					.verifyRefineResultFunctionality(Integer.parseInt(expectedNumberOfItems));
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyRefineResultThroughManufacturer failed :" + t.getMessage());
			Assert.fail("verifyRefineResultThroughManufacturer failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyRefineResultThroughManufacturer Completed---------------- ");
	}

	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression", "Smoke Test" })
	@Description("Verification of clear all functionality")
	public void verifyClearAllFunctionality(ITestContext context) throws Exception {
		log.info("----------------verifyClearAllFunctionality Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			//homePage().mouseHoverOnShopProductLink().clickOnRequiredLevel1CategoryLinkFromShopProductMenu(6);
			navigateToPLP("11");
			productListPage().clickOnSpecificCheckBoxOfCategory(2);
			Thread.sleep(1200);
			productListPage().clickOnClearAllLink().verifyClearAllLinkFunction();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyClearAllFunctionality failed :" + t.getMessage());
			Assert.fail("verifyClearAllFunctionality failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyClearAllFunctionality Completed---------------- ");

	}

}
