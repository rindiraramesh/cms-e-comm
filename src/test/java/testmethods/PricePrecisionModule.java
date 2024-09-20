package testmethods;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class PricePrecisionModule extends PageInitializer {

	Logger log = Logger.getLogger(PricePrecisionModule.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	String precision = "%.2f";

	String orderNumber = null;

	Date date = new Date();

	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	String today = Integer.toString(localDate.getDayOfMonth());

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

	@Feature("Price Precision")
	@Description("Verify price precision in Item details page")
	@Test(groups = { "Price Precision", "regression", "Smoke Test" })
	public void a_verifyPricePrecisionInItemDetailsPage() throws Exception {
		log.info("-----------------a_verifyPricePrecisionInItemDetailsPage started--------------");

		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPricePrecisionInItemDetailsPage(data.getPricePricisonData());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("a_verifyPricePrecisionInItemDetailsPage failed :" + t.getMessage());
			Assert.fail("a_verifyPricePrecisionInItemDetailsPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
			log.info("-----------------a_verifyPricePrecisionInItemDetailsPage Completed--------------");

		}
	}

	@Feature("Price Precision")
	@Description("Verify price precision in item List view page")
	@Test(groups = { "Price Precision", "regression", })
	public void b_verifyPricePrecisionInListViewPage() throws Exception {
		log.info("-----------------b_verifyPricePrecisionInListViewPage started--------------");

		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnLastButOneBreadCrumb()
					.verifyPricePrecisionInProductListPage(data.getSearchTextForPN(), data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("b_verifyPricePrecisionInListViewPage failed :" + t.getMessage());
			Assert.fail("b_verifyPricePrecisionInListViewPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
			log.info("-----------------b_verifyPricePrecisionInListViewPage Completed--------------");

		}
	}

	@Feature("Price Precision")
	@Description("Verify price precision in item grid view page")
	@Test(groups = { "Price Precision", "regression", })
	public void c_verifyPricePrecisionInGridViewPage() throws Exception {
		log.info("-----------------c_verifyPricePrecisionInGridViewPage started--------------");

		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnLastButOneBreadCrumb()
					.clickOnChangeView()
					.verifyPricePrecisionInProductListPage(data.getSearchTextForPN(), data.getPricePricisonData());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("c_verifyPricePrecisionInGridViewPage failed :" + t.getMessage());
			Assert.fail("c_verifyPricePrecisionInGridViewPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
			log.info("-----------------c_verifyPricePrecisionInGridViewPage Completed--------------");

		}
	}

	@Feature("Price Precision")
	@Description("Verify price precision in view cart pop up")
	@Test(groups = { "Price Precision", "regression" })
	public void d_verifyPricePrecisionInMyCartPopUp() throws Exception {
		log.info("-----------------d_verifyPricePrecisionInMyCartPopUp started--------------");

		try {
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.verifyPricePrecisionInMyCartPage(data.getSearchTextForPN(), data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("d_verifyPricePrecisionInMyCartPopUp failed :" + t.getMessage());
			Assert.fail("d_verifyPricePrecisionInMyCartPopUp failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
			log.info("-----------------d_verifyPricePrecisionInViewCartPage Completed--------------");

		}
	}

	@Feature("Price Precision")
	@Description("Verify price precision in Shopping cart page")
	@Test(groups = { "Price Precision", "regression" })
	public void e_verifyPricePrecisionInShoppingCartPage() throws Exception {
		log.info("-----------------e_verifyPricePrecisionInCartPage started--------------");

		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.verifyPricePrecisionInShoppingCart(data.getSearchTextForPN(), data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("e_verifyPricePrecisionInCartPage failed :" + t.getMessage());
			Assert.fail("e_verifyPricePrecisionInCartPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
			log.info("-----------------e_verifyPricePrecisionInCartPage Completed--------------");

		}
	}

	@Feature("Price Precision")
	@Description("Verify price precision in My Saved cart page")
	@Test(groups = { "Price Precision", "regression" })
	public void f_verifyPricePrecisionInMySavedCartPage() throws Exception {
		log.info("-----------------f_verifyPricePrecisionInMySavedCartPage started--------------");

		try {
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart(data.getSaveCartName())
					.hitEnterForSaveCartCreation()
					.clickOnTheConfirmationMessage(data.getSaveCartName());
			saveCartPage().verifyPricePrecisionInSaveCart(data.getSearchTextForPN(), data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("f_verifyPricePrecisionInMySavedCartPage failed :" + t.getMessage());
			Assert.fail("f_verifyPricePrecisionInMySavedCartPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			MyAccountMenuDropdown().clickMySavedCartLink();
			saveCartPage().deleteSaveCart(data.getSaveCartName());
			homePage().clickOnLogo();
		}
		log.info("-----------------f_verifyPricePrecisionInMySavedCartPage Completed--------------");

	}

	@Feature("Price Precision")
	@Description("This test Case verifies price precision in My Saved cart page")
	@Test(groups = { "Price Precision", "regression", })
	public void g_verifyPricePrecisionInCheckOutPage() throws Exception {
		log.info("-----------------g_verifyPricePrecisionInCheckOutPage started--------------");

		try {
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnCheckoutInMyCartPage()
					.verifyPricePrecisionInCheckoutPage(data.getSearchTextForPN(),
							data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("g_verifyPricePrecisionInCheckOutPage failed :" + t.getMessage());
			Assert.fail("g_verifyPricePrecisionInCheckOutPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("-----------------g_verifyPricePrecisionInCheckOutPage Completed--------------");

	}

	// No data in template, so disabled
	@Feature("Price Precision")
	@Description("This test Case verifies price precision in Order Details page")
	@Test(groups = { "Price Precision", "regression", }, enabled = false)
	public void h_verifyPricePrecisionInOrderDetailsPage() throws Exception {
		log.info("-----------------h_verifyPricePrecisionInOrderDetailsPage started--------------");

		try {
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnCheckoutInMyCartPage()
					.clickOnNextButton()
					.enterShippingPhoneNumber("5132534522")
					.enterEmailId("test@unilogcorp.com")
					.clickOnNextButton();
			checkoutPage().verifyWhetherOrderDetailsTabIsActive()
					.enterPurchaseOrderNumber("UnilogTest")
					.enterOrderedBy("Automation")
					.selectShipMethod("BEST WAY")
					.selectRequiredByDate(today)
					.clickOnSubmitOrderButton();
			Thread.sleep(5000);
			checkoutPage().verifyOrderConfirmationSucessMessage(data.getOrderConfirmationSuccessMessage())
					.verifyPricePrecisionInConfirmationPage(data.getSearchTextForPN(), data.getPricePricisonData());
			orderNumber = checkoutPage().getOrderNumber();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("h_verifyPricePrecisionInOrderDetailsPage failed :" + t.getMessage());
			Assert.fail("h_verifyPricePrecisionInOrderDetailsPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
			log.info("-----------------h_verifyPricePrecisionInOrderDetailsPage Closed--------------");

		}
	}

	// Not data in template, so disabled
	@Feature("Price Precision")
	@Description("This test Case verifies price precision in My Saved cart page")
	@Test(groups = { "Price Precision",
			"regression" }, enabled = false, dependsOnMethods = "h_verifyPricePrecisionInOrderDetailsPage")
	public void i_verifyPricePrecisionInOpenOrderDetailsPage() throws Exception {
		log.info("-----------------i_verifyPricePrecisionInOpenOrderDetailsPage started--------------");
		try {

			MyAccountMenuDropdown().clickOpenOrdersLink()
					.openOrdersPage()
					.sendTextToSearchBox(orderNumber)
					.clickOnOrderNumber(orderNumber)
					.verifyPricePrecisionInOpenOrderPage(data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("i_verifyPricePrecisionInOpenOrderDetailsPage failed :" + t.getMessage());
			Assert.fail("i_verifyPricePrecisionInOpenOrderDetailsPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
			log.info("-----------------i_verifyPricePrecisionInOpenOrderDetailsPage Closed--------------");

		}
	}

	@Feature("Price Precision")
	@Description("This test Case verifies price precision in My product groups page")
	@Test(groups = { "Price Precision", "regression" })
	public void j_verifyPricePrecisionInMyProductGroupsPage() throws Exception {
		log.info("-----------------j_verifyPricePrecisionInMyProductGroupsPage started--------------");

		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName(data.getProductGroupName())
					.hitEnter()
					.clickOnTheConfirmationMessage()
					.verifyPricePrecisionInMyProductGroupPage(data.getSearchTextForPN(), data.getPricePricisonData());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("j_verifyPricePrecisionInMyProductGroupsPage failed :" + t.getMessage());
			Assert.fail("j_verifyPricePrecisionInMyProductGroupsPage failed :" + t.getMessage());
		}

		finally {
			MyAccountMenuDropdown().clickMyProductGroupLink();
			productGroupsPage().deleteProductGroup(data.getProductGroupName());
			homePage().clickOnLogo();
		}
		log.info("-----------------j_verifyPricePrecisionInMyProductGroupsPage Closed--------------");
	}

	@Feature("Price Precision")
	@Description("This test Case verifies price precision in QuickOreder page")
	@Test(groups = { "Price Precision", "regression", "Smoke Test" })
	public void k_verifyPricePrecisionInQuickOrderPage() throws Exception {
		log.info("-----------------k_verifyPricePrecisionInQuickOrderPage started--------------");

		try {
			homePage().clickOnQuickOrderPadLink()

					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForPN(), "1")
					.clickOnAddToCartButtonSpeedEntry()
					.verifyPricePrecisionInQuickOrderPage(data.getSearchTextForPN(), data.getPricePricisonData());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("k_verifyPricePrecisionInQuickOrderPage failed :" + t.getMessage());
			Assert.fail("k_verifyPricePrecisionInQuickOrderPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------k_verifyPricePrecisionInQuickOrderPage Closed--------------");
	}

}
