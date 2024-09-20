package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestNGDataProvider;

public class ShopByBrandsModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ShopByBrandsModuleTest.class);

	@Feature("Shop By Brands Module")
	@Test(groups = { "ShopByBrands", "regression",
			"Smoke Test" }, dataProvider = "ShopByBrands", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify Brands List and available Items")
	public void verifyBrandsListAndItem(ITestContext context,String shopByBrandBreadcrumb, String productName,
			String specificAlphabetToClickInShopByBrandsPage, String searchText, String brandItemName)
			throws Throwable {
		log.info("---------------verifyBrandsListAndItem Started----------------------");
		try {

			homePage().shopByBrandsPage()
					.hoverOverBrandsLink()
					.verifyBrandsDropdown()
					.clickOnBrandsLinkInHeader()
					.verifyTitleOfShopByBrand(shopByBrandBreadcrumb, context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyShopByBreadcrumb(shopByBrandBreadcrumb)
					.clickOnSpecificAlphabet(specificAlphabetToClickInShopByBrandsPage)
					.searchText(searchText)
					.verifyDisplayOfSearchText(searchText)
					.hoverOverBrandsLink()
					.clickItemUnderBrands(searchText)
					.verifyItemSelectedViaBrands(searchText);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyBrandsListAndItem failed :" + t.getMessage());
			Assert.fail("verifyBrandsListAndItem failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("---------------verifyBrandsListAndItem Completed----------------------");
	}

}
