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
import utility.MSExcelMethods;

public class ProductDetail_SKUModuleTest extends PageInitializer {

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	MSExcelMethods excelMethods = new MSExcelMethods();

	Logger log = Logger.getLogger(ProductDetail_SKUModuleTest.class);

	@Feature("ProductDetail_SKU Module")
	@Description("Verify navigation from product mode list page to SKU mode item detail page.")
	@Test(enabled = false, groups = { "ProductDetail_SKUModule", "regression" })
	public void verifySKUItemDtailPageThroughCategoryNavigation(ITestContext context) throws Exception {
		try {
			log.info("-------------- verifySKUItemDtailPageThroughCategoryNavigation Started--------------------");
			/*
			 * homePage().mouseHoverOnShopProductLink() .clickOnFirstLevel1CategoryLink()
			 * .productListPage() .verifyOfToggleButtonToViewProductModeItemInfo();
			 */
			homePage().searchText(data.getSearchTextForProductMode()).clickOnSearch();
			productListPage().scrollThePageTillToggleButtonToViewProductModeItems()
					.clickOnToggleButtonOfProductModeItem(1)
					.clickOnChildItemOfProductMode(1);
			productDetailsPage().verifyProductDetailPage().verifyItemPriceAfterLogin().verifyDefaultQtyValue("10");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySKUItemDtailPageThroughCategoryNavigation failed :" + t.getMessage());
			Assert.fail("verifySKUItemDtailPageThroughCategoryNavigation failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------- verifySKUItemDtailPageThroughCategoryNavigation Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify item detail page with item having price and availability.")
	@Test(groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyItemDetailWithPriceAndAvailability(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyItemDetailWithPriceAndAvailability Started--------------------");

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyItemPriceNextToPriceLabel()
					.verifyItemsAvailabilityInDetailPage();
			String brandName = productDetailsPage().getBrandName();
			String mpn = productDetailsPage().getMPN();
			productDetailsPage()

					.verifyItemTitleShouldBeCombinationOfBrandAndMPN(brandName + " " + mpn+ " " +data.getSearchTextForPN());
			// .verifyPDPPageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemDetailWithPriceAndAvailability failed :" + t.getMessage());
			Assert.fail("verifyItemDetailWithPriceAndAvailability failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyItemDetailWithPriceAndAvailability Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify sku mode detail page breadcrumbs links.")
	@Test(groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyProductDetailBreadcrumbsFunctionality(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyProductDetailBreadcrumbsFunctionality Started--------------------");

			String categoryName = homePage().searchText(data.getSearchTextForMPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyDetailPageBreadcrumbs(data.getProductDetailPageBreadCrumb().trim())
					.getCategoryNameFromBreadCrumb(2);
			productDetailsPage().clickOnBreadCrumb(categoryName);
			// productListPage().verifyLevelTwoCategoryBreadCrumb(categoryName);
			productListPage().verifyLevelTwoCategoryBreadCrumb("Kitchen Appliances");
			getDriver().navigate().back();
			productDetailsPage().clickOnBreadCrumb("Home");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyProductDetailBreadcrumbsFunctionality failed :" + t.getMessage());
			Assert.fail("verifyProductDetailBreadcrumbsFunctionality failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyProductDetailBreadcrumbsFunctionality Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	// test data is not available
	@Description("Verify  item detail page with item having no price and availability")
	@Test(enabled = false, groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyItemDetailWithoutPriceAndAvailability(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyItemDetailWithoutPriceAndAvailability Started--------------------");

			homePage().searchText(data.getSearchTextForPNNotHavingPrice())
					.clickOnSearch()
					.productDetailsPage()
					.verifyAddToCartButtonDisableForNoPriceItem()
					.verifyCallForPrice("Call for Price");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemDetailWithoutPriceAndAvailability failed :" + t.getMessage());
			Assert.fail("verifyItemDetailWithoutPriceAndAvailability failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyItemDetailWithoutPriceAndAvailability Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify Alert Message When Same Item Added Twice.")
	@Test(groups = { "ProductDetail_SKUModule", "regression", "Smoke Test" })
	public void verifyAlertMessageWhenSameItemAddedTwice(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyAlertMessageWhenSameItemAddedTwice Started--------------------");

			loginModule.loginAsASuperUser();

			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton();
			myCartPage().clickOnCloseButtonInMyCartPopUp();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verifyDisplayOfCombineButtonInMyCartPopUp()
					.verifyDisplayOfSeperateButtonInMyCartPopUp()
					.verifyDisplayOfCancleButtonInMyCartPopUp();

			commonOperations().verifyAlertTextPopUp(data.getAlertMessageWhenSameItemsAddedTwice());
			myCartPage().clickOnCombineButtonInMyCartPopUp().clickOnViewCartInMyCartPopup();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyAlertMessageWhenSameItemAddedTwice failed :" + t.getMessage());
			Assert.fail("verifyAlertMessageWhenSameItemAddedTwice failed :" + t.getMessage());

		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyAlertMessageWhenSameItemAddedTwice Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify the functionality of Send this page")
	@Test(groups = { "ProductDetail_SKUModule",
			"regression" }, dataProvider = "ProductDetail_SKU", dataProviderClass = TestNGDataProvider.class)
	public void verifySendThisPage(String mandatoryFieldsOfShareThisPage, ITestContext context) throws Exception {
		try {
			log.info("--------------verifySendThisPage Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			String partNumber = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyDisplayOfSendLink()
					.getPartNumber();
			productDetailsPage().clickOnSendThisPageLink();
			sharePage().verifyShareThisPageBreadCrumb(data.getShareThisPageBreadCrumb())
					.verifyMandatoryFieldOfShareThisPage(mandatoryFieldsOfShareThisPage);
			sharePage().verifyItemsInShareThisPage(partNumber);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySendThisPage failed :" + t.getMessage());
			Assert.fail("verifySendThisPage failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifySendThisPage Completed -------------------");
	}

	/*
	 * @Feature("ProductDetail_SKU Module") // need to ask
	 * 
	 * @Description("Verify Alert Message When Same Item Added Twice." )
	 * 
	 * @Test(groups = { "ProductDetail_SKUModule", "regression" }, dataProvider =
	 * "ProductDetail_SKU", dataProviderClass = TestNGDataProvider.class) public
	 * void verifySend(String mandatoryFieldsOfShareThisPage, ITestContext context)
	 * throws Exception { try {
	 * 
	 * homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter(
	 * "Company").trim());
	 * 
	 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
	 * data.getPasswordForWhichCartIsShared());
	 * 
	 * Thread.sleep(1200); String partNumber =
	 * homePage().searchText(data.getSearchTextForPN()) .clickOnSearch()
	 * .productDetailsPage() .verifyDisplayOfSendLink() .getPartNumber();
	 * productDetailsPage().clickOnSendThisPageLink();
	 * 
	 * sharePage().enterFriendName("deepak")
	 * .enterFriendEmailAddress("priya.singh@unilogcorp.com")
	 * .enterFromName("Priya") .enterFromEmailAddress("deepak.hegde@unilogcorp.com")
	 * .enterSubject("For testing") .clickOnSend();
	 * 
	 * } finally { homePage().clickOnLogout(); homePage().clickOnLogo(); }
	 * 
	 * }
	 */

	@Feature("ProductDetail_SKU Module")
	@Description("Verify Mandatory fields error message for send this page")
	@Test(groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyErrorMessageSendThisPage(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyErrorMessageSendThisPage Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/ProductDetail_SKU.xlsx",
					"verifyErrorMessageSendThisPage");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnSendThisPageLink();
			int j = 0;
			for (int i = 0; i < testData.length; i++) {
				sharePage().enterFriendName(testData[i][j++])
						.enterFriendEmailAddress(testData[i][j++])
						.enterFromName(testData[i][j++])
						.enterFromEmailAddress(testData[i][j++])
						.enterSubject(testData[i][j++])
						.clickOnSend()
						.verifyErrorMessage(testData[i][j++]);
				j = 0;
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageSendThisPage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageSendThisPage failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyErrorMessageSendThisPage Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify Send this page before login.")
	@Test(groups = { "ProductDetail_SKUModule",
			"regression" }, dataProvider = "ProductDetail_SKU", dataProviderClass = TestNGDataProvider.class)
	public void verifySendThisPageBeforeLogin(String mandatoryFieldsOfShareThisPage, ITestContext context)
			throws Exception {

		try {
			log.info("--------------verifySendThisPageBeforeLogin Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfMinimumOrderQuantity()
					.verifyDisplayOfMPN()
					.verifyDisplayOfNameOfTheProduct()
					.verifyDisplayOfPartNumber()
					.verifyDisplayOfSendLink();
			String partNumber = productDetailsPage().getPartNumber();
			productDetailsPage().clickOnSendThisPageLink();
			sharePage().verifyShareThisPageBreadCrumb(data.getShareThisPageBreadCrumb())
					.verifyMandatoryFieldOfShareThisPage(mandatoryFieldsOfShareThisPage);
					//.verifyTextBoxToEnterCaptha();
			sharePage().verifyItemsInShareThisPage(partNumber);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySendThisPageBeforeLogin failed :" + t.getMessage());
			Assert.fail("verifySendThisPageBeforeLogin failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifySendThisPageBeforeLogin Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify the functionality of 'Write review' Link.")
	@Test(groups = { "ProductDetail_SKUModule",  "Smoke Test",
			"regression" }, dataProvider = "ProductDetail_SKU", dataProviderClass = TestNGDataProvider.class)
	public void verifyTheFunctOfWriteReviewLink(String title, String review, ITestContext context) throws Exception {
		try {
			log.info("--------------verifyTheFunctOfWriteReviewLink Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnWriteReviewLink()
					.verifyWriteReviewPopUp()
					.selectRatingStar(5)
					.enterTitle(title)
					.enterReview(review)
					.clickOnSubmitReviewButton();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyTheFunctOfWriteReviewLink failed :" + t.getMessage());
			Assert.fail("verifyTheFunctOfWriteReviewLink failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyTheFunctOfWriteReviewLink Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify 'Write review' window alert messages")
	@Test(groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyErrorMessageForWriteReviewLink(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyErrorMessageForWriteReviewLink Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnWriteReviewLink()
					.clickOnSubmitReviewButton();
			commonOperations().verifyAlertTextPopUp(data.getErrorMessageWithoutEnteringMandotryFieldOfReviewLink())
					.clickOnOkButtonInAlertPopUp();
			productDetailsPage().clickOnReviewCloseButton();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageForWriteReviewLink failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageForWriteReviewLink failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyErrorMessageForWriteReviewLink Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify Qty field in item detail page.")
	@Test(groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyUpdatedQtyField(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyUpdatedQtyField Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.enterQuanityInProductDetailsPage("30")
					.clickOnAddToCartButton();
			myCartPage().clickOnViewCartInMyCartPopup().verifyQuantitiesInShoppingCartForMultipleItems("30");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUpdatedQtyField failed :" + t.getMessage());
			Assert.fail("verifyUpdatedQtyField failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyUpdatedQtyField Completed -------------------");
	}

	//Need to check login again
	@Feature("ProductDetail_SKU Module")
	@Description("Verify the alert message showing for customer part number")
	@Test(groups = { "ProductDetail_SKUModule", "regression" }, enabled=false)
	public void verifyErrorMessageForCPN(ITestContext context) throws Exception {
		try {
			log.info("--------------verifyErrorMessageForCPN Started--------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumber()
					.clickOnAddButton();
			commonOperations().verifyAlertTextPopUp(data.getErrorMessageForUpdationOfCPNWithoutSelectingCPN())
					.clickOnOkButtonInAlertPopUp();
			/*getDriver().navigate().refresh();
			productDetailsPage().clickOnAddOrRemoveCustomerPartNumber().enterCPN("@#$%").clickOnAddButton();
			commonOperations().verifyAlertTextPopUp(data.getErrorMessageForSpecailCharacter())
					.clickOnOkButtonInAlertPopUp();*/
			getDriver().navigate().refresh();
			productDetailsPage().clickOnAddOrRemoveCustomerPartNumber()
					.enterCPN(data.getSearchTextForCPN())
					.clickOnAddButton();
			getDriver().navigate().refresh();
			productDetailsPage().clickOnAddOrRemoveCustomerPartNumber()
					.clickOnUpdateButtonOfAddOrRemoveCustomerPartNumber();
			commonOperations().verifyAlertTextPopUp(data.getErrorMessageForUpdationOfCPNWithoutSelectingCPN())
					.clickOnOkButtonInAlertPopUp();
			productDetailsPage().clickOnRemoveButtonOfAddOrRemoveCustomerPartNumber();
			commonOperations().verifyAlertTextPopUp(data.getErrorMessageForRemovalOfCPNWithoutSelectingCPN())
					.clickOnOkButtonInAlertPopUp();
			productDetailsPage().enterCPN("@#$%").clickOnUpdateButtonOfAddOrRemoveCustomerPartNumber();
			commonOperations().verifyAlertTextPopUp(data.getErrorMessageForSpecailCharacter())
					.clickOnOkButtonInAlertPopUp();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageForCPN failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageForCPN failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyErrorMessageForCPN Completed -------------------");
	}

	@Feature("ProductDetail_SKU Module")
	@Description("Verify the functionality of image magnifier.")
	@Test(groups = { "ProductDetail_SKUModule", "regression" })
	public void verifyImageMaginifierIconFunctionality() throws Exception {
		try {
			log.info("--------------verifyImageMaginifierIconFunctionality Started--------------------");

			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			productDetailsPage().clickOnEnlargeIcon().verifyImageMagnifierIconFuncationality();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyImageMaginifierIconFunctionality failed :" + t.getMessage());
			Assert.fail("verifyImageMaginifierIconFunctionality failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyImageMaginifierIconFunctionality Completed -------------------");
	}
}
