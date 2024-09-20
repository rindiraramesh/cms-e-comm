package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ProductDetail_V3ModuleTest extends PageInitializer {
	Logger log = Logger.getLogger(ProductDetail_V3ModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	/**
	 * Configure product mode in CIMM Navigate to 'MANAGE
	 * SITE>ECOMMERCE_STD_TEMPLATE_V2 ->CMS ->Site Builder in CIMM Set Site Section
	 * as:productdetail_V3.html under Layout_Layout2 Set Site Section
	 * as:productmode_V3.html under Layout_Layout3
	 */

	@Feature("ProductDetail_V3")
	@Test(groups = { "ProductDetail_V3Module",
			"regression" }, dataProvider = "ProductDetail_V3", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV3PageAfterLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String productModeItemsFieldsInDetailPage, String itemInformationTabUnderViewMoreChoices,
			ITestContext context) throws Exception {
		log.info("----------------verifyDetailV3PageAfterLogin Started---------------- ");
		try {

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			myCartPage().clearCart();
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
							productModeItemsFields)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1)
					.clickOnBulkOptionsInProductListPage()
					.clickOnAddItemToCartFromBulkOptionInListPage()
					.myCartPage()
					.verifyOfViewCartButtonInMyCartPopUp()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPage();
			/*
			 * productListPage().
			 * clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1);
			 */
			productListPage().clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(2);
			productListPage().clickOnBulkOptionsInProductListPage().clickOnAddItemToGroupFromBulkOptionInListPage();
			productGroupsPage().verifyDisplayOfProductGroupPopUp().clickOnCloseButtonOfProductGroupPopUp();
			String compareItemsCount = productListPage().getCompareItemsCount();
			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoicesInProductVersion3Page(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount)
					// .clickOnAddToCartButtonAtItemLevel(2);
					.clickOnAddToCartButtonAtItemLevel(3);
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productListPage().clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItemsInProductVersion3();
			productDetailsPage().verifyDifferentiatorSetForProductVersionTwoThreeAndFour()
					.verifyOfDisplayOfItemsFieldsInProductDetailPageForVersion3(productModeItemsFieldsInDetailPage);
			productDetailsPage()
					// .clickOnAddToCartButtonAtItemLevelInProductDetailPage(3);
					.clickOnAddToCartButtonAtItemLevelInProductDetailPage(4);
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productDetailsPage().clickOnSpecificItemInformationLinkInProductDetailPage(1)
					.verifyItemInformationTabInProductDetailPage(itemInformationTabUnderViewMoreChoices)
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfSendLink()
					.verifyDisplayOfShareLink();
			String compareItemsCountInProductDetailPage = productDetailsPage()
					.getCompareItemsCountInProductDetailPage();
			productDetailsPage().clickOnSpecificComapreCheckInProductDetailPageForVersion3(2)
					.verifyWhetherCompareItemIncreasedInProductDetailPage(compareItemsCountInProductDetailPage);
			productDetailsPage().clickOnCancelComapreLink();
			commonOperations().verifyAlertTextPopUp(data.getAlertMessageWhenClickedOnCancelCompareLink())
					.clickOnOkButtonInAlertPopUp();
			String attributeName = productDetailsPage().getAttributeNameOfProductDetailVersion2();
			productDetailsPage().clickOnDifferentiatorOfProductDetailVersion2Page()
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 6")
					.clickOnDifferentiatorOfProductDetailVersion2Page()
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 7")
					.verifyDisplayofRespectiveItemsAfterClickingOnDifferentiator(attributeName)
					.clickOnClearAllLinkForFilteredAttributeItems()
					.verifyClearLinkFunctionalityInProductVersion3(numberOfItems);
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDetailV3PageAfterLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV3PageAfterLogin-Failed " + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogout();
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productGridPage().clickOnChangeView();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV3PageAfterLogin Completed---------------- ");
	}

	@Feature("ProductDetail_V3")
	@Test(groups = { "ProductDetail_V3Module",
			"regression" }, dataProvider = "ProductDetail_V3", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV3PageBeforLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String productModeItemsFieldsInDetailPage, String itemInformationTabUnderViewMoreChoices,
			ITestContext context) throws Exception {
		log.info("----------------verifyDetailV3PageBeforLogin Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			myCartPage().clearCart();
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
							productModeItemsFields)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1)
					.clickOnBulkOptionsInProductListPage()
					.clickOnAddItemToCartFromBulkOptionInListPage()
					.myCartPage()
					.verifyOfViewCartButtonInMyCartPopUp()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPage();
			String compareItemsCount = productListPage().getCompareItemsCount();
			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoicesInProductVersion3Page(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount)
					.clickOnAddToCartButtonAtItemLevel(2);
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productListPage().clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItemsInProductVersion3();
			productDetailsPage().verifyDifferentiatorSetForProductVersionTwoThreeAndFour()
					.verifyOfDisplayOfItemsFieldsInProductDetailPageForVersion3(productModeItemsFieldsInDetailPage);
			productDetailsPage().clickOnAddToCartButtonAtItemLevelInProductDetailPage(3);
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productDetailsPage().clickOnSpecificItemInformationLinkInProductDetailPage(1)
					.verifyItemInformationTabInProductDetailPage(itemInformationTabUnderViewMoreChoices)
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfSendLink()
					.verifyDisplayOfShareLink();
			String compareItemsCountInProductDetailPage = productDetailsPage()
					.getCompareItemsCountInProductDetailPage();
			productDetailsPage().clickOnSpecificComapreCheckInProductDetailPageForVersion3(2)
					.verifyWhetherCompareItemIncreasedInProductDetailPage(compareItemsCountInProductDetailPage);
			productDetailsPage().clickOnCancelComapreLink();
			commonOperations().verifyAlertTextPopUp(data.getAlertMessageWhenClickedOnCancelCompareLink())
					.clickOnOkButtonInAlertPopUp();
			String attributeName = productDetailsPage().getAttributeNameOfProductDetailVersion2();
			productDetailsPage().clickOnDifferentiatorOfProductDetailVersion2Page()
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 6")
					.clickOnDifferentiatorOfProductDetailVersion2Page()
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 7")
					.verifyDisplayofRespectiveItemsAfterClickingOnDifferentiator(attributeName)
					.clickOnClearAllLinkForFilteredAttributeItems()
					.verifyClearLinkFunctionalityInProductVersion3(numberOfItems);
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDetailV3PageBeforLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV3PageBeforLogin-Failed " + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV3PageBeforLogin Completed---------------- ");

	}

}
