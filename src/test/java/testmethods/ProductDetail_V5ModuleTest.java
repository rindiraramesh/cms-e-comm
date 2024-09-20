package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ProductDetail_V5ModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ProductDetail_V5ModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	@Feature("ProductDetail_V5")
	@Test(groups = { "ProductDetail_V2Module",
			"regression" }, dataProvider = "ProductDetail_V5", dataProviderClass = TestNGDataProvider.class)
	public void verifyV5ForProModeV2AferLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyV5ForProModeV2AferLogin Started---------------- ");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();

			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();

			productListPage().verifyDisplayOfBulkOptionDropDown(bulkOptionsDropDownValues)
					.verifyDisplayOfViewOfChoicesButtonsInProductListGridPage()
					.verifyOfToggleButtonToViewProductModeItemInfo();

			productListPage().scrollThePageTillProductMode()
					.clickOnToggleButtonOfProductModeItem(1)
					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButton(productModeItemsFields)
					.clickOnToggleButtonOfProductModeItem(1)
					.clickOnViewAllChoiceButton(1)
					.verifyDisplayItemsAndFieldsAfterClickingOnViewAllChoiceButton(productModeItemsFields)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1)
					.clickOnBulkOptionsInProductListPage()
					.clickOnAddItemToCartFromBulkOptionInListPage()
					.myCartPage()
					.verifyOfViewCartButtonInMyCartPopUp()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPage();

			productListPage().clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(2);

			productListPage().clickOnBulkOptionsInProductListPage().clickOnAddItemToGroupFromBulkOptionInListPage();

			productGroupsPage().verifyDisplayOfProductGroupPopUp().clickOnCloseButtonOfProductGroupPopUp();

			String compareItemsCount = productListPage().getCompareItemsCount();

			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoices(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount)
					.clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();

			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);

			productDetailsPage().verifyDisplayOfAddMyProductGroupButton()
					.verifyDisplayOfAddToCartButton()
					.verifyDifferentiatorSetForProductVersion5();
			productDetailsPage().clickOnDifferentiator()
					.selectSpecificDifferentiatorValueInProductDetailPageForVersion5("SZ 7")
					.verifyDifferentiatorDropdownfunctionality("SZ 7");

			productDetailsPage().clickOnAddToCartButton();

			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();

			productDetailsPage().clickOnMyProductGroupButtonInVersion5()
					.verifyDisplayOfCreatedProductGroupInDetailPage()
					.verifyTextBoxToEnterNewProductGroupNameInDetailPage();
			productDetailsPage().verifyDisplayOfPrintLink().verifyDisplayOfSendLink().verifyDisplayOfShareLink();
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyV5ForProModeV2AferLogin failed " + t.getMessage());
			Assert.fail("verifyV5ForProModeV2AferLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();

			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();

			productGridPage().clickOnChangeView();

			homePage().clickOnLogo();
		}
		log.info("----------------verifyV5ForProModeV2AferLogin Completed---------------- ");

	}

	@Feature("ProductDetail_V5")
	@Test(groups = { "ProductDetail_V2Module",
			"regression" }, dataProvider = "ProductDetail_V5", dataProviderClass = TestNGDataProvider.class)
	public void verifyV5ForProModeV2BeforeLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyV5ForProModeV2BeforeLogin Started---------------- ");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();

			productListPage().verifyDisplayOfBulkOptionDropDown(bulkOptionsDropDownValues)
					.verifyDisplayOfViewOfChoicesButtonsInProductListGridPage()
					.verifyOfToggleButtonToViewProductModeItemInfo();

			productListPage().scrollThePageTillProductMode()
					.clickOnToggleButtonOfProductModeItem(1)
					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButton(productModeItemsFields)
					.clickOnToggleButtonOfProductModeItem(1)
					.clickOnViewAllChoiceButton(1)
					.verifyDisplayItemsAndFieldsAfterClickingOnViewAllChoiceButton(productModeItemsFields)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(2)
					.clickOnBulkOptionsInProductListPage()
					.clickOnAddItemToCartFromBulkOptionInListPage()
					.myCartPage()
					.verifyOfViewCartButtonInMyCartPopUp()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPage();

			String compareItemsCount = productListPage().getCompareItemsCount();
			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoices(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount)
					.clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();

			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			productDetailsPage().verifyDisplayOfAddMyProductGroupButton()
					.verifyDisplayOfAddToCartButton()
					.verifyDifferentiatorSetForProductVersion5();

			productDetailsPage().clickOnDifferentiator()
					.selectSpecificDifferentiatorValueInProductDetailPageForVersion5("SZ 7")
					.verifyDifferentiatorDropdownfunctionality("SZ 7");

			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();

			productDetailsPage().clickOnMyProductGroupButtonInVersion5();
			loginPopUp().verifyLoginPopUp().clickCloseButton();
			productDetailsPage().verifyDisplayOfPrintLink().verifyDisplayOfSendLink().verifyDisplayOfShareLink();
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyV5ForProModeV2BeforeLogin failed " + t.getMessage());
			Assert.fail("verifyV5ForProModeV2BeforeLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyV5ForProModeV2BeforeLogin Completed---------------- ");

	}

	@Feature("ProductDetail_V5")
	@Test(enabled = false, groups = { "ProductDetail_V3Module",
			"regression" }, dataProvider = "ProductDetail_V5", dataProviderClass = TestNGDataProvider.class)
	public void verifyV5ForProModeV3AferLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			ITestContext context) throws Exception {
		log.info("----------------verifyV5ForProModeV3AferLogin Started---------------- ");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productListPage().verifyDisplayOfBulkOptionDropDown(bulkOptionsDropDownValues)
					.verifyDisplayOfViewOfChoicesButtonsInProductListGridPage()
					.verifyOfToggleButtonToViewProductModeItemInfo();
			productListPage().scrollThePageTillProductMode()
					.clickOnToggleButtonOfProductModeItem(1)
					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion3Page(
							productModeItemsFields)

					.clickOnToggleButtonOfProductModeItem(1)
					.clickOnViewAllChoiceButton(1)

					.verifyDisplayItemsAndFieldsAfterClickingOnViewAllChoiceButtonInProductVersion3Page(
							productModeItemsFields);
			productListPage().clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			productDetailsPage().verifyDisplayOfAddMyProductGroupButton()
					.verifyDisplayOfAddToCartButton()
					.verifyDifferentiatorSetForProductVersion5();
			productDetailsPage().clickOnDifferentiator()
					.selectSpecificDifferentiatorValueInProductDetailPageForVersion5("SZ 7")
					.verifyDifferentiatorDropdownfunctionality("SZ 7");
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productDetailsPage().clickOnMyProductGroupButtonInVersion5()
					.verifyDisplayOfCreatedProductGroupInDetailPage()
					.verifyTextBoxToEnterNewProductGroupNameInDetailPage();
			productDetailsPage().verifyDisplayOfPrintLink().verifyDisplayOfSendLink().verifyDisplayOfShareLink();
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyV5ForProModeV3AferLogin failed " + t.getMessage());
			Assert.fail("verifyV5ForProModeV3AferLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();

			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();

			productGridPage().clickOnChangeView();

			homePage().clickOnLogo();
		}
		log.info("----------------verifyV5ForProModeV3AferLogin Completed---------------- ");

	}

	@Feature("ProductDetail_V4")
	@Test(enabled = false, groups = { "ProductDetail_V3Module",
			"regression" }, dataProvider = "ProductDetail_V5", dataProviderClass = TestNGDataProvider.class)
	public void verifyV5ForProModeV4AferLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			ITestContext context) throws Exception {
		log.info("----------------verifyV5ForProModeV4AferLogin Started---------------- ");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productListPage().verifyDisplayOfBulkOptionDropDown(bulkOptionsDropDownValues)
					.verifyDisplayOfViewOfChoicesButtonsInProductListGridPage()
					.verifyOfToggleButtonToViewProductModeItemInfo();
			productListPage().scrollThePageTillProductMode()
					.clickOnToggleButtonOfProductModeItem(1)

					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion4Page(
							productModeItemsFields)

					.clickOnToggleButtonOfProductModeItem(1)
					.clickOnViewAllChoiceButton(1)

					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion4Page(
							productModeItemsFields);
			productListPage()

					.clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			productDetailsPage().verifyDisplayOfAddMyProductGroupButton()
					.verifyDisplayOfAddToCartButton()
					.verifyDifferentiatorSetForProductVersion5();
			productDetailsPage().clickOnDifferentiator()
					.selectSpecificDifferentiatorValueInProductDetailPageForVersion5("SZ 7")
					.verifyDifferentiatorDropdownfunctionality("SZ 7");
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productDetailsPage().clickOnMyProductGroupButtonInVersion5()
					.verifyDisplayOfCreatedProductGroupInDetailPage()
					.verifyTextBoxToEnterNewProductGroupNameInDetailPage();
			productDetailsPage().verifyDisplayOfPrintLink().verifyDisplayOfSendLink().verifyDisplayOfShareLink();
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyV5ForProModeV4AferLogin failed " + t.getMessage());
			Assert.fail("verifyV5ForProModeV4AferLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();

			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();

			productGridPage().clickOnChangeView();

			homePage().clickOnLogo();
		}
		log.info("----------------verifyV5ForProModeV4AferLogin Completed---------------- ");
	}

}
