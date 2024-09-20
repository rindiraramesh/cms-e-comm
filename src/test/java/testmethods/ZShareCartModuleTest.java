package testmethods;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import junit.framework.Assert;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ZShareCartModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ZShareCartModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	SaveCartModuleTest saveCart = new SaveCartModuleTest();

	String saveCartNameNew = "AutomationSaveCart";
	
	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
		homePage().clickOnLogo();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogout();
		logger.info("clicked on Logout link");
	}

	@Feature("Share Cart")
	@Description("Verify share cart functionality")
	@Test(groups = { "ShareCartModule",
			"regression" }, dataProvider = "Share Cart", dataProviderClass = TestNGDataProvider.class)
	public void a_VerfiyShareCartFunctionality(String itemHeaders, String buttonsAvailable, String saveCartPlaceHolder,
			String itemHeadersInSaveCartPage, String sharePopUpHeading, String userNameTextBoxLabel,
			String shareCartFields, ITestContext context) throws Exception {
		log.info("----------------a_VerfiyShareCartFunctionality Started------------------");
		try {
			String myCartBreadcrumb = data.getMyCartBreadcrumb();
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			Thread.sleep(1200);
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			String partNumber = productDetailsPage().getPartNumber();
			String shortDescription = productDetailsPage().getShortDescription();
			String productName = productDetailsPage().getProductName();
			String mpn = productDetailsPage().getMPN();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verifyPartNumberInMyCartPopUp(partNumber)
					.verifyItemsCountInMyCartPopUp("1")
					.verifyShortDescriptionInMyCartPopUp(shortDescription)
					.clickOnViewCartInMyCartPopup();
			myCartPage().verifyPartNumberInMyCart(partNumber)
					.verifyMPN(mpn)
					.verifyNameOfTheProductInMyCartPage(productName)
					.verifyMyCartBreadcrumb(myCartBreadcrumb)
					.verifyItemHeadersAvailableInMyCartPage(itemHeaders)
					.verifyButtonsAvailableForSuperUserAndGeneralUserInMyCart(buttonsAvailable);
			myCartPage().clickOnSaveCartButton()
					.verifyBlankSpaceToCreateNewSaveCart(saveCartPlaceHolder)
					.enterNameOfSaveCartAndAddTheProductToSaveCart(saveCartNameNew)
					.hitEnterForSaveCartCreation();
			Thread.sleep(1000);
			myCartPage().verifySaveCartCreationMessage(saveCartNameNew).clickOnTheConfirmationMessage(saveCartNameNew);
			saveCartPage().verifybreadCrumbs(saveCartNameNew).verifyPageName(saveCartNameNew);
			saveCartPage().verifyWhetherProductsAddedAreDisplayedInTheSaveCart(productName)
					.verifyItemHeadersAvailableInSaveCartPage(itemHeadersInSaveCartPage)
					.clickOnShareCartLink()
					.verifySharePopUp(sharePopUpHeading, userNameTextBoxLabel)
					.enterUserNameOrEmail(data.getValidKeywordForShareCart())
					.clickOnSearchButton()
					.verifySearchResult(data.getValidKeywordForShareCart())
					.verifyShareCartFields(shareCartFields)
					.verifResetButton()
					.verifyShareButton()
					.clickOnTheSpecificCheckbox(data.getValidKeywordForShareCart(), 1)
					.verifyWhetherCheckboxesAreClicked()
					.clickOnShareButton()
					.verifySavedCartSharedSucessMessage();
			getDriver().navigate().refresh();
			Thread.sleep(1200);
			homePage().clickOnLogout();
			loginModule.login(data.getValidKeywordForShareCart(), data.getPasswordForWhichCartIsShared());
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMySaveCart()
					.verifyWhetherTheCartIsShared(saveCartNameNew)
					.clickOnSharedCart(saveCartNameNew)
					.verifyPageName(saveCartNameNew)
					.verifybreadCrumbs(saveCartNameNew)
					.verifyCompleteBreadcrumb(data.getSharedCartCompleteBreadcrumb());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("a_VerfiyShareCartFunctionality failed :" + t.getMessage());
			Assert.fail("a_VerfiyShareCartFunctionality failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------a_VerfiyShareCartFunctionality Completed------------------");
	}

	@Feature("Share Cart")
	@Description("Verify error message in share cart ")
	@Test(groups = { "ShareCartModule",
			"regression" }, dataProvider = "Share Cart", dataProviderClass = TestNGDataProvider.class)
	public void verfiyNegativeShareCartScenario(String invalidEmailId, String alertTextMessage, ITestContext context)
			throws Exception {
		log.info("----------------verfiyNegativeShareCartScenario Started------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			Thread.sleep(1200);
			homePage().clickOnMySaveCart()
					.searchSaveCart(saveCartNameNew)
					.clickOnTheCreatedSaveCart(saveCartNameNew)
					.verifyPageNameSaveCartLandingPageName(saveCartNameNew)
					.clickOnShareCartLink()
					.enterUserNameOrEmail(invalidEmailId)
					.clickOnSearchButton();
			commonOperations().verifyAlertTextPopUp(alertTextMessage).clickOnOkButtonInAlertPopUp();
			sharePopUp().clickOnCloseButton();
			sharePopUp().verifyCancleButtonFunctionality();
			saveCart.saveCartDelete(saveCartNameNew);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verfiyNegativeShareCartScenario failed :" + t.getMessage());
			Assert.fail("verfiyNegativeShareCartScenario failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verfiyNegativeShareCartScenario Completed------------------");
	}

}
