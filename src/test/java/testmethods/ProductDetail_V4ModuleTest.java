package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ProductDetail_V4ModuleTest extends PageInitializer {
	Logger log = Logger.getLogger(ProductDetail_V4ModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	/**
	 * Configure product mode in CIMM Navigate to 'MANAGE
	 * SITE>ECOMMERCE_STD_TEMPLATE_V2 ->CMS ->Site Builder in CIMM Set Site Section
	 * as:productdetail_V4.html under Layout_Layout2 Set Site Section
	 * as:productmode_V4.html under Layout_Layout3
	 */

	@Feature("ProductDetail_V4")
	@Test(groups = { "ProductDetail_V3Module",
			"regression" }, dataProvider = "ProductDetail_V4", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV4PageAfterLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyDetailV4PageAfterLogin Started---------------- ");
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
					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion4Page(
							productModeItemsFields)
					.clickOnToggleButtonOfProductModeItem(1)
					.clickOnViewAllChoiceButton(1)
					.verifyDisplayOfItemAndItsFieldsAfterClickingOnToggleButtonInProductVersion4Page(
							productModeItemsFields);
			productListPage().clickOnAddToCartButtonAtItemLevel(1);
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productListPage().clickOnAddToProductGroupButtonAtItemLevel(1)
					.verifyDisplayOfCreatedProductGroup()
					.verifyTextBoxToEnterNewProductGroupName()
					.enterGroupName(data.getMyProductGroupName())
					.hitEnter();
			String compareItemsCount = productListPage().getCompareItemsCount();
			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoicesInProductVersion3Page(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount);
			productListPage().clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItemsInProductVersion3();
			productDetailsPage().verifyDifferentiatorSetForProductVersionTwoThreeAndFour()
					.verifyOfDisplayOfItemsFieldsInProductDetailPageForVersion4(productModeItemsFields);
			productDetailsPage().clickOnAddToProductGroupButtonAtItemLevelInProductDetailPage(1)
					.enterGroupName(data.getMyProductGroupName())
					.verifyDisplayOfCreatedProductGroupInDetailPage()
					.verifyTextBoxToEnterNewProductGroupNameInDetailPage()
					.hitEnter();
			productDetailsPage().clickOnSpecificItemInformationLinkInProductDetailPage(1)
					.verifyItemInformationTabInProductDetailPage(itemInformationTabUnderViewMoreChoices)
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfSendLink()
					.verifyDisplayOfShareLink();
			/* productDetailsPage().verifyDisplayOfRecentlyViewedItems(); */
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
			log.info("verifyDetailV4PageAfterLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV4PageAfterLogin-Failed " + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogout();
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productGridPage().clickOnChangeView();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV4PageAfterLogin Completed---------------- ");

	}

	@Feature("ProductDetail_V4")
	@Test(groups = { "ProductDetail_V3Module",
			"regression" }, dataProvider = "ProductDetail_V4", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV4PageBeforeLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyDetailV4PageBeforeLogin Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			myCartPage().clearCart();
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
			productListPage().clickOnAddToCartButtonAtItemLevel(1);
			myCartPage().verificationOfAddToCartButtonFunctionalityAtItemLevel()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPageAtItemLevel();
			productListPage()
					// .clickOnAddToProductGroupButtonAtItemLevel(1)
					.clickOnAddToProductGroupButtonAtItemLevelBeforeLogin(1);
			loginPopUp().verifyLoginPopUp().clickCloseButton();
			String compareItemsCount = productListPage().getCompareItemsCount();
			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoicesInProductVersion3Page(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount);
			productListPage().clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItemsInProductVersion3();
			productDetailsPage().verifyDifferentiatorSetForProductVersionTwoThreeAndFour()
					.verifyOfDisplayOfItemsFieldsInProductDetailPageForVersion4(productModeItemsFields);
			productDetailsPage().clickOnAddToProductButtonAtItemLevelInProductDetailPageBeforeLogin(1);
			loginPopUp().verifyLoginPopUp().clickCloseButton();
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
			log.info("verifyDetailV4PageBeforeLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV4PageBeforeLogin-Failed " + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV4PageBeforeLogin Completed---------------- ");

	}
}
