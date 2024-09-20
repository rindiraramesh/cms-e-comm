package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class LeftMenuModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(LeftMenuModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

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

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Sign In Link and Shop product menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void verifySignInLinkAndLevelOneCategory(ITestContext context) throws Exception {
		log.info("-------------------verifySignInLinkAndLevelOneCategory  Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			headerObjects().verifyShopProductsMenu();
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifySignInLinkAndLevelOneCategory failed :" + t.getMessage());
			Assert.fail("verifySignInLinkAndLevelOneCategory failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("-------------------verifySignInLinkAndLevelOneCategory  Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Left Menu Fields and Level2 Category.")
	@Test(groups = { "LeftMenuModule", "regression", })
	public void verifyLeftMenuAndLevel2Category(ITestContext context) throws Exception {
		log.info("------------------- verifyLeftMenuAndLevel2Category Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();

			productsPage().verifyShopProductsInTheLeftMenu()
					.verifyBrandInTheLeftMenu()
					.verifyManufacturersInTheLeftMenu()
					.verifyMyAccountInTheLeftMenu();

			String firstCatgeory = productsPage().clickOnFirstCategoryInTheLeftPanel();

			productListPage().verifyLevelTwoCategoryBreadCrumb(firstCatgeory);
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyLeftMenuAndLevel2Category failed :" + t.getMessage());
			Assert.fail("verifyLeftMenuAndLevel2Category failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyLeftMenuAndLevel2Category Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Brand Functionality Of Left Menu")
	@Test(groups = { "LeftMenuModule", "regression", })
	public void verifyBrandsFunctionality(ITestContext context) throws Exception {
		log.info("------------------- verifyBrandsFunctionality Started-------------------------");
		try {

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();

			productsPage().verifyBrandInTheLeftMenu().clickOnSpecificBrandFromLeftMenu(data.getSearchTextForBrand1());

			productListPage().verifyItemBrandInPLP(data.getSearchTextForBrand1());

		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyBrandsFunctionality failed :" + t.getMessage());
			Assert.fail("verifyBrandsFunctionality failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyBrandsFunctionality Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify My Account section in the left menu")
	@Test(groups = { "LeftMenuModule", "regression", "Smoke Test" })
	public void verifyMyAccountSection(ITestContext context) throws Exception {
		log.info("------------------- verifyMyAccountSection Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();
			productsPage().verifyMyAccountInTheLeftMenu()
					.verifyMyAccountSectionInTheLeftMenu(data.getSuperUserAccountDropDown());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyMyAccountSection failed :" + t.getMessage());
			Assert.fail("verifyMyAccountSection failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyMyAccountSection Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify My Account Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void verifyMyAccountLinkInMyAccountSection(ITestContext context) throws Exception {
		log.info("------------------- verifyMyAccountLinkInMyAccountSection Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();
			productsPage().clickOnMyAccountLinkInLeftMenu();
			myAccountsPage().verifyPageName();
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyMyAccountLinkInMyAccountSection failed :" + t.getMessage());
			Assert.fail("verifyMyAccountLinkInMyAccountSection failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyMyAccountLinkInMyAccountSection Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Change Password Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void verifyChangePasswordLinkInMyAccountSection(ITestContext context) throws Exception {
		log.info("------------------- verifyChangePasswordLinkInMyAccountSection Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnProductsLink();
			productsPage().clickOnChangePasswordLinkInLeftMenu();
			changePasswordPage()
					.verifyChangePasswordPageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyChangePasswordLinkInMyAccountSection failed :" + t.getMessage());
			Assert.fail("verifyChangePasswordLinkInMyAccountSection failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyChangePasswordLinkInMyAccountSection Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Edit Contact Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression",
			"Smoke Test" }, dataProvider = "LeftMenu", dataProviderClass = TestNGDataProvider.class)
	public void verifyEditContactInMyAccount(String editContactPageName, ITestContext context) throws Exception {
		log.info("------------------- verifyEditContactInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnProductsLink();
			productsPage().clickOnEditContactLinkInLeftMenu();
			editContactInfoPage()
					.verifyEditContactInfoPageTitle(
							editContactPageName + " | " + context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyEditContactInfoBreadcrumb(editContactPageName);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyEditContactInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyEditContactInMyAccount failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyEditContactInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Open Order Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule",
			"regression" }, dataProvider = "LeftMenu", dataProviderClass = TestNGDataProvider.class)
	public void verifyOpenOrderInMyAccount(String breadCrumbs, String headers, ITestContext context) throws Exception {
		log.info("------------------- verifyOpenOrderInMyAccount Started-------------------------");
		try {
			String[] expectedHeaders = headers.split(",");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();
			productsPage().clickOnOpenOrderLinkInLeftMenu();
			openOrdersPage().verifyOpenOrdersTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyBreadCrumbOfOpenOrderPage(breadCrumbs)
					.verifyOpenOrdersHeaders(expectedHeaders);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyOpenOrderInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyOpenOrderInMyAccount failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyOpenOrderInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Order History Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule",
			"regression" }, dataProvider = "LeftMenu", dataProviderClass = TestNGDataProvider.class)
	public void verifyOrderHistoryInMyAccount(String breadCrumbs, String headers, ITestContext context)
			throws Exception {
		log.info("------------------- verifyOrderHistoryInMyAccount Started-------------------------");
		try {
			String[] expectedHeaders = headers.split(",");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();
			productsPage().clickOnOrderHistorylinkInLeftMenu();
			orderHistoryPage().verifyBreadCrumbOfOrderHistoryPage(data.getBreadCrumbOfCompletedOrderPage())
					.verifyOrderHistoryTableHeaders(expectedHeaders)
					.verifyOrderHistoryPageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyOrderHistoryInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyOrderHistoryInMyAccount failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyOrderHistoryInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify My Product Group Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression", })
	public void verifyMyProductGropLinkInMyAccount(ITestContext context) throws Exception {
		log.info("------------------- verifyMyProductGropLinkInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();
			productsPage().clickOnMyProductGroupLinkInTheLeftMenu();
			productGroupsPage()
					.verifyPageTitle(data.getProductGroupPageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.verifyBreadCrumbOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.verifyTableHeaderOfMyProductGroupLandingPage(data.getTableHeaderOfMyProductGroup());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyMyProductGropLinkInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyMyProductGropLinkInMyAccount failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyMyProductGropLinkInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify My Saved Cart Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression", })
	public void verifyMySaveCartLinkInMyAccount(ITestContext context) throws Exception {
		log.info("------------------- verifyMySaveCartLinkInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnProductsLink();
			productsPage().clickOnMySaveCartLinkInLeftMenu();
			saveCartPage().verifybreadCrumbs(data.getSavedCartPageName())
					.verifySaveCartTitle(data.getSavedCartPageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.verifyTableHeaderOfMySaveCartLandingPage(data.getTableHeaderOfMySavedCart());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyMySaveCartLinkInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyMySaveCartLinkInMyAccount failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyMySaveCartLinkInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Request For Quote Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void verifyRequestForQuoteInMyAccount(ITestContext context) throws Exception {
		log.info("------------------- verifyRequestForQuoteInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnProductsLink();
			productsPage().clickOnRequestForQuoteLinkInLeftMenu();
			requestQuotePage().verifyReqForQuoteTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyReqForQuoteBreadCrum(data.getBreadCrumReqForQuote());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyRequestForQuoteInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyRequestForQuoteInMyAccount failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyRequestForQuoteInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Quick Order Pad Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void verifyQuickOrderPadLinkInMyAccount(ITestContext context) throws Exception {
		log.info("------------------- verifyQuickOrderPadLinkInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnProductsLink();
			productsPage().clickOnQuickOrderPadLinkInLeftMenu();
			quickOrderPadPage().verifyQuickOrderPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyQuickOrderPadLinkInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyQuickOrderPadLinkInMyAccount failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyQuickOrderPadLinkInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Approval Cart List Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void verifyApprovalCartListInMyAccount(ITestContext context) throws Exception {
		log.info("------------------- verifyApprovalCartListInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnProductsLink();
			productsPage().clickOnApprovalCartListInLeftMenu();
			approvalCartListPage()
					.verifyApprovalCartListPage(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyApprovalCartListInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyApprovalCartListInMyAccount failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
			log.info("------------------- verifyApprovalCartListInMyAccount Completed-------------------------");
		}
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify User Management Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule",
			"regression" }, dataProvider = "LeftMenu", dataProviderClass = TestNGDataProvider.class)
	public void verifyUserManagementInMyAccount(String userManagementHeader, String userManagementPageTitle,
			ITestContext context) throws Exception {
		log.info("------------------- verifyUserManagementInMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnProductsLink();
			productsPage().clickOnUserManagementLinkInLeftMenu();
			userManagementPage().verifyUserManagementPage(userManagementHeader)
					.verifyUserManagementPageBreadCrumb(userManagementPageTitle)
					.verifyUserManagementPageTitle(userManagementPageTitle,
							context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyUserManagementInMyAccount failed :" + t.getMessage());
			Assert.fail("verifyUserManagementInMyAccount failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyUserManagementInMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify Configure Procument System Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule",
			"regression" }, dataProvider = "LeftMenu", dataProviderClass = TestNGDataProvider.class)
	public void verifyConfigProcureSysMyAccount(String pageTitle, String bredCrums, ITestContext context)
			throws Exception {
		log.info("------------------- verifyConfigProcureSysMyAccount Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			Thread.sleep(1200);
			homePage().clickOnProductsLink();
			productsPage().clickOnConfigureProcurementSystemLinkInLeftMenu();
			configureProcumentSystemPage()
					.verifyConfigureProcurementSystemTitle(
							pageTitle + " | " + context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyConfigureProcurementSystemBreadcrumb(bredCrums);
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyConfigProcureSysMyAccount failed :" + t.getMessage());
			Assert.fail("verifyConfigProcureSysMyAccount failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("------------------- verifyConfigProcureSysMyAccount Completed-------------------------");
	}

	@Feature("Left Menu Module")
	@Description("This test Case is to verify LogOut Link Of Left Menu.")
	@Test(groups = { "LeftMenuModule", "regression" })
	public void z_verifyLogOutLinkInMyAccountOfLeftMenu(ITestContext context) throws Exception {
		log.info("------------------- z_verifyLogOutLinkInMyAccountOfLeftMenu Started-------------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			Thread.sleep(1200);
			homePage().clickOnProductsLink();

			productsPage().clickOnLogoutLinkInLeftMenu();
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("z_verifyLogOutLinkInMyAccountOfLeftMenu failed :" + t.getMessage());
			Assert.fail("z_verifyLogOutLinkInMyAccountOfLeftMenu failed :" + t.getMessage());

		}finally {
			homePage().clickOnLogo();
		}
		log.info("------------------- z_verifyLogOutLinkInMyAccountOfLeftMenu Completed-------------------------");
	}
}
