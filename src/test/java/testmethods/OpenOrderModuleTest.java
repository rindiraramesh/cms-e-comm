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

public class OpenOrderModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(OpenOrderModuleTest.class);

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

	@Feature("Open Order Module")
	@Description("Verification of open orders page")
	@Test(groups = { "OpenOrderModule", "regression", "Smoke Test" })
	public void verifyOpenOrderPage(ITestContext context) throws Exception {
		log.info("-------------------verifyOpenOrderPage Started-------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().clickOnMyAccountMenuDropdown()
					.verifyMyAccountDropdown(data.getSuperUserAccountDropDown())
					.clickOnOpenOrders()
					.openOrdersPage()
					.verifyOpenOrdersPageName()
					.verifyOpenOrdersHeaders(data.getExpectedOpenOrderTableHeader().split(","))
					.verifyOpenOrdersTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyBreadCrumbOfOpenOrderPage(data.getBreadCrumbOfOpenOrderPage());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyOpenOrderPage failed :" + t.getMessage());
			Assert.fail("verifyOpenOrderPage failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyOpenOrderPage Completed -------------------");
	}
	
	//No test data, so disabled
	@Feature("Open Order Module")
	@Description("Verify Open order search functionality for valid data")
	@Test(groups = { "OpenOrderModule", "regression", "Smoke Test" }, enabled=false)
	public void verifyOpenOrderSearchFunctionalityForValidData(ITestContext context) throws Exception {
		log.info("-------------------verifyOpenOrderSearchFunctionalityForValidData Started-------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders();
			String orderNumber = openOrdersPage().getOrderNumber(1);

			openOrdersPage().sendTextToSearchBox(orderNumber).verifyTextDisplayForOrderNo(orderNumber);

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyOpenOrderSearchFunctionalityForValidData failed :" + t.getMessage());
			Assert.fail("verifyOpenOrderSearchFunctionalityForValidData failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyOpenOrderSearchFunctionalityForValidData Completed -------------------");
	}

	@Feature("Open Order Module")
	@Description("Verify Open order search functionality for invalid data")
	@Test(groups = { "OpenOrderModule", "regression" })
	public void verifyOpenOrderSearchFunctionalityForInValidData(ITestContext context) throws Exception {
		log.info("-------------------verifyOpenOrderSearchFunctionalityForInValidData Started-------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders();
			openOrdersPage()

					.sendTextToSearchBox(data.getInvalidDataForSearch());
			openOrdersPage().verifySearchResultForInvalidData();
		}

		catch (Throwable t)

		{
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyOpenOrderSearchFunctionalityForInValidData failed :" + t.getMessage());
			Assert.fail("verifyOpenOrderSearchFunctionalityForInValidData failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyOpenOrderSearchFunctionalityForInValidData Completed -------------------");
	}

	@Feature("Open Order Module")
	@Description("Verification of 'Show' drop down in Open Order Page")
	@Test(groups = { "OpenOrderModule", "regression" })
	public void verifyShowDropDownFunctionality(ITestContext context) throws Exception {
		log.info("-------------------verifyShowDropDownFunctionality Started-------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders();
			openOrdersPage()

					.selectContentPerPageOption("10")

					.verifyDisplayOfContent("10")

					.selectContentPerPageOption("25")

					.verifyDisplayOfContent("25")

					.selectContentPerPageOption("50")

					.verifyDisplayOfContent("50")

					.selectContentPerPageOption("100")

					.verifyDisplayOfContent("100");
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyShowDropDownFunctionality failed :" + t.getMessage());
			Assert.fail("verifyShowDropDownFunctionality failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyShowDropDownFunctionality Completed -------------------");
	}

	@Feature("Open Order Module")
	@Description("Verify the pagination of open order page action for next button.")
	@Test(groups = { "OpenOrderModule", "regression" })
	public void verifyNextButtonFunctionalityOfPagination(ITestContext context) throws Exception {
		log.info("-------------------verifyNextButtonFunctionalityOfPagination Started-------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders();
			// openOrdersPage().verifyNextButtonOfPagination();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyNextButtonFunctionalityOfPagination failed :" + t.getMessage());
			Assert.fail("verifyNextButtonFunctionalityOfPagination failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyNextButtonFunctionalityOfPagination Completed -------------------");
	}

	@Feature("Open Order Module")
	@Description("Verify the pagination of open order page action for Previous button.")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verifyPreviousButtonFunctionalityOfPagination(ITestContext context) throws Exception {
		log.info("-------------------verifyPreviousButtonFunctionalityOfPagination Started-------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders();
			openOrdersPage().verifyPreviousButtonOfPagination();
		}

		catch (Throwable t)

		{
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyPreviousButtonFunctionalityOfPagination failed :" + t.getMessage());
			Assert.fail("verifyPreviousButtonFunctionalityOfPagination failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyPreviousButtonFunctionalityOfPagination Completed -------------------");
	}
}
