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

public class ReOrderCartModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ReOrderCartModuleTest.class);

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

	@Feature("Re-Order Module")
	@Description("Verify open orders page")
	@Test(groups = { "OpenOrderModule", "regression", })
	public void verifyOpenOrderPage(ITestContext context) throws Exception {
		log.info("-----------------verifyOpenOrderPage Started--------------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnOpenOrders()
					.openOrdersPage()
					.verifyOpenOrdersPageName()
					.verifyOpenOrdersHeaders(data.getExpectedOpenOrderTableHeader().split(","));

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyOpenOrderPage failed :" + t.getMessage());
			Assert.fail("verifyOpenOrderPage failed :" + t.getMessage());

		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyOpenOrderPage Completed--------------------");

	}

	@Feature("Re-Order Module")
	@Description("Verify Order Detail Page By Clicking On Order Number and My Account Link functionality in Breadcrumb.")
	@Test(groups = { "OrderDetailModule", "regression",
			"Smoke Test" }, dataProvider = "OrderDetail", dataProviderClass = TestNGDataProvider.class)
	public void verifyOrderDetailPageByOrderNo(String orderDetailHeader, String reOderItemDescriptionHeader,
			String priceHeader, ITestContext context) throws Exception {
		log.info("-----------------verifyOrderDetailPageByOrderNo Started--------------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders().openOrdersPage();
			String orderNumber = openOrdersPage().getOrderNumber(1);
			openOrdersPage().clickOnOrderNumber(orderNumber);
			orderDetailsPage().verifyOrderDetailPageName(data.getOrderDetailPageName())
					.verifyOrderDetailPage(orderDetailHeader, reOderItemDescriptionHeader, priceHeader);

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyOrderDetailPageByOrderNo failed :" + t.getMessage());
			Assert.fail("verifyOrderDetailPageByOrderNo failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyOrderDetailPageByOrderNo Completed--------------------");

	}

	@Feature("Re-Order Module")
	@Description("Verify Order Detail Page By Clicking On View Order Link and Reorder Button functionality.")
	@Test(groups = { "OrderDetailModule", "regression" })
	public void verifyTheFunctionalityOfViewOrderLink(ITestContext context) throws Exception {
		log.info("-----------------verifyTheFunctionalityOfViewOrderLink Started--------------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders().openOrdersPage().clickOnSpecificViewOrderLink(
					1);
			orderDetailsPage().verifyOrderDetailPageName(data.getOrderDetailPageName());

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyTheFunctionalityOfViewOrderLink failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfViewOrderLink failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyTheFunctionalityOfViewOrderLink Completed--------------------");

	}

	@Feature("Re-Order Module")
	@Description("Verify Order Detail Page By Clicking On View Order Link and Reorder Button functionality.")
	@Test(groups = { "OrderDetailModule", "regression", "Smoke Test" })
	public void verifyTheFunctionalityOfReorderButton(ITestContext context) throws Exception {
		log.info("-----------------verifyTheFunctionalityOfReorderButton Started--------------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders().openOrdersPage().clickOnSpecificViewOrderLink(
					1);

			orderDetailsPage().clickonSelectAllCheckbox().clickOnReorderButton();
			myCartPage().verifyMyCartPagename(data.getShoppingCartPageName());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyTheFunctionalityOfReorderButton failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfReorderButton failed :" + t.getMessage());

		}

		finally {
			myCartPage().clearCart();
			
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyTheFunctionalityOfReorderButton Completed--------------------");

	}

	@Feature("Re-Order Module")
	@Description("Verify Order Detail Page By Clicking On View Order Link and Reorder Button functionality.")
	@Test(groups = { "OrderDetailModule", "regression" })
	public void verifyAlertMessageWithoutSelectingCheckbox(ITestContext context) throws Exception {
		log.info("-----------------verifyAlertMessageWithoutSelectingCheckbox Started--------------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			homePage().clickOnMyAccountMenuDropdown().clickOnOpenOrders().openOrdersPage().clickOnSpecificViewOrderLink(
					1);
			orderDetailsPage().clickOnReorderButton();

			// Verify Alert Message When click on reorder button without selecting checkbox
			commonOperations()

					.verifyAlertTextPopUp(data.getAlertMessageWithoutClickingOnSelectAllCheckbox())
					.clickOnOkButtonInAlertPopUp();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyAlertMessageWithoutSelectingCheckbox failed :" + t.getMessage());
			Assert.fail("verifyAlertMessageWithoutSelectingCheckbox failed :" + t.getMessage());

		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyAlertMessageWithoutSelectingCheckbox Completed--------------------");

	}
}
