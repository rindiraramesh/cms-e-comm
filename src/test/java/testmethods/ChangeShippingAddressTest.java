package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class ChangeShippingAddressTest extends PageInitializer {
	LoginModuleTest loginModule = new LoginModuleTest();

	Logger logger = Logger.getLogger(ChangeShippingAddressTest.class);

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

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies if user changes shipping address application will display in MyAccount page in Shipping address section")
	@Test(groups = { "regression", })
	public void a_verifyChangedShippingAddressinMyAccountAddressTab() throws Exception {
		logger.info("---------a_verifyChangedShippingAddressinMyAccountAddressTabe test is started---------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnChangeShippingAddressLink()
					.selectingShippingAddress2()
					.verifyWelcomeMsgAfterLogin()
					.clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.clickOnAddressTab()
					.verifyShippingAddressDetails();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed VerifyChangedShippingAddressinMyAccountAddressTab test :" + t.getMessage());
			Assert.fail("Failed VerifyChangedShippingAddressinMyAccountAddressTab test :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		logger.info("------------a_verifyChangedShippingAddressinMyAccountAddressTab test is ended-----------------");
	}

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies if user changes shipping address application will display in checkout Shipping address page")
	@Test(groups = { "regression" })
	public void b_verifyChangedShippingAddressinCheckoutAddres() throws Exception {
		logger.info("----------------b_verifyChangedShippingAddressinCheckoutAddres test is started-----------------");
		try {
			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			MyAccountMenuDropdown().clickOnChangeShippingAddressLink()
					.selectingShippingAddress2()
					.verifyWelcomeMsgAfterLogin()
					.searchText(data.getSearchTextForPN())
					.clickOnSearch();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().clickOnViewCartInMyCartPopup()
					.clickOnCheckoutInMyCartPage()
					.clickOnNextButton()
					.verifyChangedShippingAddressFields();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed VerifyChangedShippingAddressinCheckoutAddres test :" + t.getMessage());
			Assert.fail("Failed VerifyChangedShippingAddressinCheckoutAddres test :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		logger.info(
				"----------------------b_verifyChangedShippingAddressinCheckoutAddres test is completed--------------------");
	}

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies if user clicks on Refresh Shipping Information, Select Shipping To Proceed refreshs ")
	@Test(groups = { "regression", "Smoke Test" })
	public void c_verifyRefreshShippingInformationFunctionality() throws Exception {
		logger.info("------------c_verifyRefreshShippingInformationFunctionality test is started-----------------");

		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnChangeShippingAddressLink()
					.clickOnRefreshShippingInformation()
					.verifyPopupOnclickOnRefreshShippingInformation()
					.verifySuccessMessageAfterclickOnRefreshShippingInformation()
					.acceptAlertAfterclickOnRefreshShippingInformation()
					.selectingShippingAddress2()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed VerifyRefreshShippingInformationFunctionality test :" + t.getMessage());
			Assert.fail("Failed VerifyRefreshShippingInformationFunctionality test :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}

		logger.info(
				"-----------------c_verifyRefreshShippingInformationFunctionality test is completed-----------------");

	}

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies if user clicks on Refresh Shipping Information, Select Shipping To Proceed refreshs in MyAccount ")
	@Test(groups = { "regression" })
	public void d_verifyRefreshShippingInformationFunctionalityMyAccount() throws Exception {

		logger.info(
				"----------------d_verifyRefreshShippingInformationFunctionalityMyAccount test is started----------------");

		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountLink()
					.clickOnAddressTab()
					.clickOnRefreshShippingInformation()
					.verifyPopupOnclickOnRefreshShippingInformation()
					.verifySuccessMessageAfterclickOnRefreshShippingInformation()
					.acceptAlertAfterclickOnRefreshShippingInformation()
					.clickOnOrdersTab();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed VerifyRefreshShippingInformationFunctionalityMyAccount test :" + t.getMessage());
			Assert.fail("Failed VerifyRefreshShippingInformationFunctionalityMyAccount test :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}

		logger.info(
				"--------------d_verifyRefreshShippingInformationFunctionalityMyAccount test is completed----------");

	}

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies search result in Select shipping address popup ")
	@Test(groups = { "regression" })
	public void e_verifySearchResultSelectShip() throws Exception {

		logger.info("--------------e_verifySearchResultSelectShip test is started--------------");

		try {
			MyAccountMenuDropdown().clickOnChangeShippingAddressLink()
					.verifySearchAddressWithAllShippingAddress()
					.clearSearchField()
					.selectingShippingAddress1()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed verifySearchResultSelectShip test :" + t.getMessage());
			Assert.fail("Failed verifySearchResultSelectShip test :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("-------------e_verifySearchResultSelectShip test is Completed--------------");

	}

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies all avilable shipping address list in my account ")
	@Test(groups = { "regression" })
	public void f_verifyShipAddressListinMyAccount() throws Exception {

		logger.info("--------------f_verifyShipAddressListinMyAccount started----------------------");

		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountLink()
					.myAccountsPage()
					.clickOnAddressTab()
					.verifyAllShippingAddressWithAllShipAddressListInMyAccount();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed a_verifyShipAddressListinMyAccount test :" + t.getMessage());
			Assert.fail("Failed a_verifyShipAddressListinMyAccount test :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		logger.info("-------------f_verifyShipAddressListinMyAccount Completed-----------------");

	}

	@Feature("ChangeShippingAddress")
	@Description("This test case verifies search result in Select shipping address in My Account page ")
	@Test(groups = { "regression" })
	public void g_verifySearchSelectShipAddressListInMyAccount() throws Exception {

		logger.info("-----------g_verifySearchSelectShipAddressListInMyAccount test is started-------------");

		try {

			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountLink()
					.clickOnAddressTab()
					.verifySearchAddressWithAllShippingAddress()
					.clearSearchField();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("Failed verifySearchSelectShipAddressListInMyAccount test :" + t.getMessage());
			Assert.fail("Failed verifySearchSelectShipAddressListInMyAccount test :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("--------------g_verifySearchSelectShipAddressListInMyAccount test is Completed---------");

	}
}
