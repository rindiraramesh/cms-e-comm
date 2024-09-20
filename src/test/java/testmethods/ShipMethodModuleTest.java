package testmethods;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

public class ShipMethodModuleTest extends PageInitializer {

	Date date = new Date();

	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	String date1 = Integer.toString(localDate.getDayOfMonth());

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	Logger log = Logger.getLogger(ShipMethodModuleTest.class);
	
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

	@Feature("Ship Method")
	@Description("Verification of Ship Method Options.")
	@Test(groups = { "ShipMethodModule", "regression", "Smoke Test" })
	public void verifyShipMethodOptions(ITestContext context) throws Exception {
		log.info("--------------------verifyShipMethodOptions Started------------------ ");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();

			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.clickOnCloseButtonInMyCartPopUp()
					.clickOnCartIcon()
					.clickOnCheckoutInMyCartPage()
					.checkoutPage()
					.clickOnNextButton();
					//.verifyShipMethodOptions(data.getShipMethodOptions());
					/*.verifyWhetherShippingAddressTabIsActive()
					.clickOnNextButton()
					.verifyWhetherOrderDetailsTabIsActive()*/
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyShipMethodOptions failed " + t.getMessage());
			Assert.fail("verifyShipMethodOptions failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("--------------------verifyShipMethodOptions Completed------------------ ");
	}

	// Need to test this with SD/MS
	@Feature("Ship Method")
	@Description("Verification of Selected Ship method display in Order confirmation page.")
	@Test(enabled = false, groups = { "ShipMethodModule",
			"regression" }, dataProvider = "POCheckout", dataProviderClass = TestNGDataProvider.class)
	public void verifyShipMethodInOrderPage(String purchaseOrderNumber, String orderBy, String shipMethod,
			String shippingInstruction, String orderNotes, ITestContext context) throws Exception {
		log.info("--------------------verifyShipMethodInOrderPage Started------------------ ");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			myCartPage().clearCart();

			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();

			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.clickOnCloseButtonInMyCartPopUp()
					.clickOnCartIcon()
					.clickOnCheckoutInMyCartPage()
					.checkoutPage()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.clickOnNextButton();

			checkoutPage()

					.verifyWhetherOrderDetailsTabIsActive()
					.enterPurchaseOrderNumber(purchaseOrderNumber)
					.enterOrderedBy(orderBy)
					.selectShipMethod(shipMethod)
					.selectRequiredByDate(date1)
					.enterShippingInstructions(shippingInstruction)
					.enterOrderNotes(orderNotes)
					.clickOnSubmitOrderButton();

			Thread.sleep(1400);
			checkoutPage().verifyShipMethodDisplayInOrderConfirmationPage(shipMethod);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyShipMethodInOrderPage failed " + t.getMessage());
			Assert.fail("verifyShipMethodInOrderPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("--------------------verifyShipMethodInOrderPage Completed------------------ ");

	}

}
