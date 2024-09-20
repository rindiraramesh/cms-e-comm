package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class PricingModuleTest extends PageInitializer {
	Logger log = Logger.getLogger(PricingModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	@Feature("Pricing Module")
	@Description("Verify customer specific price in list/grid page after login.")
	@Test(groups = { "PricingModule", "regression", "Smoke Test" })
	public void verifyItemPriceAfterLoginInListAndGridPage(ITestContext context) throws Exception {
		log.info("-------------- verifyItemPriceAfterLoginInListAndGridPage Started--------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().verifyItemPriceAfterLogin(1);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());
			Assert.fail("verifyItemPriceAfterLoginInListAndGridPage failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyItemPriceAfterLoginInListAndGridPage Completed -------------------");

	}

	@Feature("Pricing Module")
	@Description("Verify customer specific price in list/grid page before login.")
	@Test(groups = { "PricingModule", "regression" })
	public void verifyItemPriceBeforeLoginInListAndGridPage(ITestContext context) throws Exception {
		log.info("-------------- verifyItemPriceBeforeLoginInListAndGridPage Started--------------------");
		try {

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			Thread.sleep(1200);
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().verifyItemPriceAfterLogin(1);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemPriceBeforeLoginInListAndGridPage failed :" + t.getMessage());
			Assert.fail("verifySKUItemDtailPageThroughCategoryNavigation failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyItemPriceBeforeLoginInListAndGridPage Completed -------------------");

	}

	@Feature("Pricing Module")
	@Description("Verify customer specific price in detail page after login.")
	@Test(groups = { "PricingModule", "regression", "Smoke Test" })
	public void verifyItemPriceAfterLoginInDetailPage(ITestContext context) throws Exception {
		log.info("-------------- verifyItemPriceAfterLoginInDetailPage Started--------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyItemPriceAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemPriceAfterLoginInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyItemPriceAfterLoginInDetailPage failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyItemPriceAfterLoginInDetailPage Completed -------------------");

	}

	@Feature("Pricing Module")
	@Description("Verify pricing should be displayed in three decimal places")
	@Test(groups = { "PricingModule", "regression" })
	public void verifyPricingDecimalPlaces(ITestContext context) throws Exception {
		log.info("-------------- verifyPricingDecimalPlaces Started--------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
					data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.verifyPricingDisplayInThreeDecimalPlaces(1, 2);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyPricingDecimalPlaces failed :" + t.getMessage());
			Assert.fail("verifyPricingDecimalPlaces failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyPricingDecimalPlaces Completed -------------------");

	}

}
