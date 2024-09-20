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

public class OrderHistoryModuleTest extends PageInitializer {
	Logger log = Logger.getLogger(OrderHistoryModuleTest.class);

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

	@Feature("Order History Module")
	@Description("Verify Order History page.")
	@Test(groups = { "OrderHistoryModule", "regression", "Smoke Test" })
	public void verifyOrderHistoryPage(ITestContext context) throws Exception {
		log.info("-------------- verifyOrderHistoryPage Started--------------------");
		try {

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnOrderHistory()
					.verifyOrderHistoryPage()
					.verifyBreadCrumbOfOrderHistoryPage(data.getBreadCrumbOfCompletedOrderPage())
					.verifyOrderHistoryTableHeaders(data.getExpectedOrderHistoryTableHeader().split(","));
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyOrderHistoryPage failed :" + t.getMessage());
			Assert.fail("verifyOrderHistoryPage failed :" + t.getMessage());
		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyOrderHistoryPage Completed -------------------");

	}

	@Feature("Order History Module")
	@Description("Verify 'Show' drop down in Order History Page.")
	@Test(groups = { "OrderHistoryModule", "regression" })
	public void verifyShowDropDownFunctionality(ITestContext context) throws Exception {
		log.info("-------------- verifyShowDropDownFunctionality Started--------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnOrderHistory();
			orderHistoryPage().selectContentPerPageOption("10")
					.verifyDisplayOfContent("10")
					.selectContentPerPageOption("25")
					.verifyDisplayOfContent("25")
					.selectContentPerPageOption("50")
					.verifyDisplayOfContent("50")
					.selectContentPerPageOption("100")
					.verifyDisplayOfContent("100");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyShowDropDownFunctionality failed :" + t.getMessage());
			Assert.fail("verifyShowDropDownFunctionality failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyShowDropDownFunctionality Completed -------------------");

	}

	@Feature("Order History Module")
	@Description("Verify Order History search functionality for valid data")
	@Test(groups = { "OrderHistoryModule", "regression", "Smoke Test" })
	public void verifyOfSearchByOrderNumberPOAndkeyword(ITestContext context) throws Exception {
		log.info("-------------- verifyOfSearchByOrderNumberPOAndkeyword Started--------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown().clickOnOrderHistory();
			String keyword = orderHistoryPage().getKeywords(1);
			orderHistoryPage().sendTextToSearchBox(keyword).verifyTextDisplayForKeyword(keyword);

		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyOfSearchByOrderNumberPOAndkeyword failed :" + t.getMessage());
			Assert.fail("verifyOfSearchByOrderNumberPOAndkeyword failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyOfSearchByOrderNumberPOAndkeyword Completed -------------------");
	}

	@Feature("Order History Module")
	@Description("Verify sorting functionality for Order#")
	@Test(groups = { "OrderHistoryModule", "regression", "Smoke Test" })
	public void verifySortingInOrderHistoryTable(ITestContext context) throws Exception {
		log.info("-------------- verifySortingInOrderHistoryTable Started--------------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnOrderHistory()
					.clickOnOrderColumnSorting()
					.verifyAccendingSort()
					.clickOnOrderColumnSorting()
					.verifyDesendingSort();
			getDriver().navigate().refresh();
			/*orderHistoryPage().clickOnDebitColumnSorting()
					.verifyAccendingSort()
					.clickOnDebitColumnSorting()
					.verifyDesendingSort();*/

		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifySortingInOrderHistoryTable failed :" + t.getMessage());
			Assert.fail("verifySortingInOrderHistoryTable failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("----------------- verifySortingInOrderHistoryTable Completed -------------------");

	}

}
