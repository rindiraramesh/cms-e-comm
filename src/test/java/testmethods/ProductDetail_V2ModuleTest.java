package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ProductDetail_V2ModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ProductDetail_V2ModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	/**
	 * Configure product mode in CIMM Navigate to 'MANAGE
	 * SITE>ECOMMERCE_STD_TEMPLATE_V2 ->CMS ->Site Builder in CIMM Set Site Section
	 * as:productdetail_V2.html under Layout_Layout2 Set Site Section
	 * as:productmode_V2.html under Layout_Layout3
	 */

	@Feature("ProductDetail_V2")
	@Test(groups = { "ProductDetail_V2Module",
			"regression" }, dataProvider = "ProductDetail_V2", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV2PageAfterLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyDetailV2PageAfterLogin Started---------------- ");
		try {

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);

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
			// productListPage().clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1);
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
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItems();
			productDetailsPage().verifyDifferentiatorSetForProductVersionTwoThreeAndFour()
					.verifyOfDisplayOfItemsFieldsInProductDetailPage(productModeItemsFields)
					.clickOnBulkOptionsInProductDetailPage()
					.clickOnAddItemToCartFromBulkOptionInProductDetailsPage();
			myCartPage().verifyOfViewCartButtonInMyCartPopUp().clickOnCloseButtonInMyCartPopUpOfProductListGridPage();
			productDetailsPage().clickOnSpecificAddToCartAndAddToProductGroupCheckboxInDetailPage(1)
					.clickOnBulkOptionsInProductDetailPage()
					.clickOnAddItemToGroupFromBulkOptionInProductDetailPage();
			productGroupsPage().verifyDisplayOfProductGroupPopUp().clickOnCloseButtonOfProductGroupPopUp();
			productDetailsPage().clickOnSpecificItemInformationLinkInProductDetailPage(1)
					.verifyItemInformationTabInProductDetailPage(itemInformationTabUnderViewMoreChoices)
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfSendLink()
					.verifyDisplayOfShareLink();
			String compareItemsCountInProductDetailPage = productDetailsPage()
					.getCompareItemsCountInProductDetailPage();
			productDetailsPage().clickOnSpecificComapreCheckInProductDetailPage(2)
					.verifyWhetherCompareItemIncreasedInProductDetailPage(compareItemsCountInProductDetailPage);
			productDetailsPage().clickOnCancelComapreLink();
			commonOperations().verifyAlertTextPopUp(data.getAlertMessageWhenClickedOnCancelCompareLink())
					.clickOnOkButtonInAlertPopUp();
			String attributeName = productDetailsPage().getAttributeNameOfProductDetailVersion2();
			productDetailsPage().clickOnDifferentiatorOfProductDetailVersion2Page()
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 6")
					.verificationOfDifferentiatorDropDown("SZ 6")
					.clickOnDifferentiatorOfProductDetailVersion2Page()
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 7")
					.verifyDisplayofRespectiveItemsAfterClickingOnDifferentiator(attributeName)
					.clickOnClearAllLinkForFilteredAttributeItems()
					.verifyClearLinkFunctionality(numberOfItems);
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDetailV2PageAfterLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV2PageAfterLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productGridPage().clickOnChangeView();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV2PageAfterLogin Completed---------------- ");

	}

	@Feature("ProductDetail_V2")
	@Test(groups = { "ProductDetail_V2Module",
			"regression" }, dataProvider = "ProductDetail_V2", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV2PageBeforeLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyDetailV2PageBeforeLogin Started---------------- ");

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
					// .clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1)
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
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItems();
			productDetailsPage().verifyDifferentiatorSetForProductVersionTwoThreeAndFour()
					.verifyOfDisplayOfItemsFieldsInProductDetailPage(productModeItemsFields)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxInDetailPage(1)
					.clickOnBulkOptionsInProductDetailPage()
					.clickOnAddItemToCartFromBulkOptionInProductDetailsPage();
			myCartPage().verifyOfViewCartButtonInMyCartPopUp().clickOnCloseButtonInMyCartPopUpOfProductListGridPage();
			productDetailsPage().clickOnSpecificItemInformationLinkInProductDetailPage(1)
					.verifyItemInformationTabInProductDetailPage(itemInformationTabUnderViewMoreChoices)

					.verifyDisplayOfPrintLink()
					.verifyDisplayOfSendLink()
					.verifyDisplayOfShareLink();
			String compareItemsCountInProductDetailPage = productDetailsPage()
					.getCompareItemsCountInProductDetailPage();
			productDetailsPage().clickOnSpecificComapreCheckInProductDetailPage(2)
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
					.verifyClearLinkFunctionality(numberOfItems);
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDetailV2PageBeforeLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV2PageBeforeLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV2PageBeforeLogin Completed---------------- ");

	}

}
