package testmethods;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestNGDataProvider;

public class ShopByManufacturerModuleTest extends PageInitializer {

	@Feature("Shop By Manufacturer Module")
	@Test(groups = { "ShopByManufacturers", "regression",
			"Smoke Test" }, dataProvider = "ShopByManufacturers", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify Manufacturers List and available Items")
	public void verifyManufacturersListAndItem(ITestContext context,String shopByManufacturersBreadcrumb, String productName,
			String expectedSearchTextboxPlaceholder, String shopByManufacturerBreadcrump,
			String specificAlphabetToClickInShopByManufacturersPage, String locatorValue, String searchText,
			String manufacturerItemName) throws Exception {
		logger.info("---------------verifyManufacturersListAndItem Started----------------------");
		try {
			homePage().clickOnLogo();
			homePage().shopByManufacturersPage()
					.hoverOverManufacturersLink()
					.verifyManufacturersDropdown()
					.clickOnManufacturerLinkInHeader()
					.verifyTitleOfShopByManufacturers(shopByManufacturersBreadcrumb, context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyShopByBreadcrump(shopByManufacturerBreadcrump)
					.clickOnSpecificAlphabet(specificAlphabetToClickInShopByManufacturersPage)
					.searchText(searchText)
					.verifyDisplayOfSearchText(searchText)
					.hoverOverManufacturersLink()
					.clickItemUnderManufacturer(manufacturerItemName)
					.verifyItemSelectedViaManufacturers(manufacturerItemName);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyManufacturersListAndItem failed:" + t.getMessage());
			Assert.fail("verifyManufacturersListAndItem failed:" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		logger.info("---------------verifyManufacturersListAndItem Completed----------------------");
	}

}
