package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ProductDetail_V1ModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ProductDetail_V1ModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	/**
	 * Configure product mode in CIMM Navigate to 'MANAGE
	 * SITE>ECOMMERCE_STD_TEMPLATE_V2 ->CMS ->Site Builder in CIMM Set Site Section
	 * as:productdetail.html under Layout_Layout2 Set Site Section
	 * as:productmode.html under Layout_Layout3
	 */

	@Feature("ProductDetail_V1")
	@Description("This Test Case Verify product mode design for version One from chosen template from Cimm After Login")
	@Test(groups = { "ProductDetail_V1Module", "regression",
			"Smoke Test" }, dataProvider = "ProductDetail_V1", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV1PageAfterLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyDetailV1PageAfterLogin Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			
			Thread.sleep(1200);

			homePage().searchText(data.getGeneralSearchText1()).clickOnSearch();

			productListPage()

					.verifyDisplayOfBulkOptionDropDown(bulkOptionsDropDownValues)

					.verifyDisplayOfViewOfChoicesButtonsInProductListGridPage()
					.verifyOfToggleButtonToViewProductModeItemInfo();
			productListPage()

					.scrollThePageTillProductMode()

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
			/*
			 * productListPage()
			 * .clickOnSpecificAddToCartAndAddToProductGroupCheckboxUnderMoreChoices(1);
			 */
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
			productDetailsPage().verifyDifferentiatorSetForProduct()
					.verifyOfDisplayOfItemsFieldsInProductDetailPage(productModeItemsFields)
					// .clickOnSpecificAddToCartAndAddToProductGroupCheckboxInDetailPage(2)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxInDetailPage(3)
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
			String attributeName = productDetailsPage().getAttributeName();
			productDetailsPage().clickOnSpecificCheckBoxOfDifferentiator("SZ 6")
					.verificationOfDifferentiatorDropDown("SZ 6")
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 9")
					.verifyDisplayofRespectiveItemsAfterClickingOnDifferentiator(attributeName)
					.clickOnClearAllLinkForFilteredAttributeItems()
					.verifyClearLinkFunctionality(numberOfItems);
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDetailV1PageAfterLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV1PageAfterLogin-Failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productGridPage().clickOnChangeView();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV1PageAfterLogin Completed---------------- ");
	}

	@Feature("ProductDetail_V1")
	@Test(groups = { "ProductDetail_V1Module", "regression",
			"Smoke Test" }, dataProvider = "ProductDetail_V1", dataProviderClass = TestNGDataProvider.class)
	public void verifyDetailV1PageBeforeLogin(String bulkOptionsDropDownValues, String productModeItemsFields,
			String itemInformationTabUnderViewMoreChoices, ITestContext context) throws Exception {
		log.info("----------------verifyDetailV1PageBeforeLogin Started---------------- ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().searchText(data.getGeneralSearchText()).clickOnSearch();
			productListPage().verifyDisplayOfBulkOptionDropDownBeforeLogin(bulkOptionsDropDownValues)
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
			String compareItemsCount = productListPage().getCompareItemsCount();
			productListPage().clickOnSpecificComapreCheckboxUnderViewMoreChoices(1)
					.verifyWhetherCompareItemIncreased(compareItemsCount)
					.clickOnSpecificItemInformationLinkUnderViewMoreChoices(1)
					.verifyItemInformationTabUnderViewMoreChoices(itemInformationTabUnderViewMoreChoices)
					.clickOnChangeView();
			productGridPage().verifyDisplayOfViewOfChoicesButtonsInProductGridPage()
					.clickOnViewAllChoiceButtonInProductGridPage(1);
			String numberOfItems = productDetailsPage().getNumberOfProductChoiceItems();
			productDetailsPage().verifyDifferentiatorSetForProduct()
					.verifyOfDisplayOfItemsFieldsInProductDetailPage(productModeItemsFields)
					.clickOnSpecificAddToCartAndAddToProductGroupCheckboxInDetailPage(2)
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
			String attributeName = productDetailsPage().getAttributeName();
			productDetailsPage().clickOnSpecificCheckBoxOfDifferentiator("SZ 6")
					.verificationOfDifferentiatorDropDown("SZ 6")
					.clickOnSpecificCheckBoxOfDifferentiator("SZ 9")
					.verifyDisplayofRespectiveItemsAfterClickingOnDifferentiator(attributeName)
					.clickOnClearAllLinkForFilteredAttributeItems()
					.verifyClearLinkFunctionality(numberOfItems);
			productDetailsPage().clickOnSendThisPageLink();
			sendThisPage().verifyTitleOfSendThisPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDetailV1PageBeforeLogin failed " + t.getMessage());
			Assert.fail("verifyDetailV1PageBeforeLogin-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyDetailV1PageBeforeLogin Completed---------------- ");
	}

}