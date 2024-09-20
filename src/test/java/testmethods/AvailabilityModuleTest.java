package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class AvailabilityModuleTest extends PageInitializer {

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	Logger logger = Logger.getLogger(AvailabilityModuleTest.class);

	@Feature("Availability Module")
	@Description("Verify item availability in list/grid page after login.")
	@Test(groups = { "AvailabilityModule", "regression", "Smoke Test" })
	public void c_verifyItemAvailabilityAfterLoginInListAndGridPage(ITestContext context) throws Exception {
		logger.info("------------------c_verifyItemAvailabilityAfterLoginInListAndGridPage started------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			;
			Thread.sleep(1200);
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.verifyItemsAvailabilityInListAndGridPage();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
			Assert.fail("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("------------------verifyItemPriceAfterLoginInListAndGridPage Completed------------------");
	}

	@Feature("Availability Module")
	@Description("Verify item availability in detail page after login.")
	@Test(groups = { "AvailabilityModule", "regression", "Smoke Test" })
	public void d_verifyItemAvailabilityAfterLoginInDetailPage(ITestContext context) throws Exception {
		logger.info("------------------d_verifyItemAvailabilityAfterLoginInDetailPage started------------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyItemsAvailabilityInDetailPage();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
			Assert.fail("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		logger.info("------------------d_verifyItemAvailabilityAfterLoginInDetailPage Completed------------------");
	}

	@Feature("Availability Module")
	@Description("Verify item availability in detail page before login.")
	@Test(enabled = false, groups = { "AvailabilityModule", "regression", "Smoke Test" })
	public void a_verifyItemAvailabilityBeforeLoginInDetailPage(ITestContext context) throws Exception {
		logger.info("------------------verifyItemAvailabilityBeforeLoginInDetailPage started------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyItemsAvailabilityInDetailPage();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyItemAvailabilityBeforeLoginInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyItemAvailabilityBeforeLoginInDetailPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		logger.info("------------------verifyItemAvailabilityBeforeLoginInDetailPage Completed------------------");

	}

	@Feature("Availability Module")
	@Description("Verify  item availability in detail page after login.")
	@Test(enabled = false, groups = { "AvailabilityModule", "regression" })
	public void b_verifyItemAvailabilityBeforeLogin(ITestContext context) throws Exception {
		logger.info("------------------verifyItemAvailabilityBeforeLogin started------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			Thread.sleep(1200);
			homePage().searchText(data.getGeneralSearchText())
					.clickOnSearch()
					.productListPage()
					.verifyItemsAvailabilityInListAndGridPageBeforeLogin(1, data.getItemAvailabilityBeforeLogin());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
			Assert.fail("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("------------------verifyItemAvailabilityBeforeLogin completed------------------");
	}

	@Feature("Availability Module")
	@Description("Verify all branch availability display.")
	@Test(enabled = false, groups = { "AvailabilityModule", "regression" })
	public void verifyBranchAvailability(ITestContext context) throws Exception {
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productListPage()
					.clickOnBranchAvailability(1)
					.verifyBranchAvailability();
		} catch (Throwable t) {
			Assert.fail("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}

	}

}
