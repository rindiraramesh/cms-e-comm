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
import utility.MSExcelMethods;

public class POCheckoutModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(PricingModuleTest.class);

	Date date = new Date();

	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	String date1 = Integer.toString(localDate.getDayOfMonth());

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	MSExcelMethods excelMethods = new MSExcelMethods();

	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
		myCartPage().clearCart();
		homePage().clickOnLogo();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogout();
		logger.info("clicked on Logout link");
	}

	// To update the
	@Feature("PO Checkout")
	@Description("Verify whether able to place a PO order.")
	@Test(enabled=false,groups = { "POCheckoutModule",
			"regression", }, dataProvider = "POCheckout", dataProviderClass = TestNGDataProvider.class)
	public void verifyCheckoutProcessByPO(String purchaseOrderNumber, String shipMethod, String orderBy,
			String shippingInstruction, String orderNotes, String orderSummaryFields, String orderConfirmationPageName,
			String orderConfirmationFields, String orderConfirmationHeaderFields, ITestContext context)
			throws Exception {
		log.info("-----------------verifyCheckoutProcessByPO started--------------");
		try {

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			String myCartBreadcrumb = data.getMyCartBreadcrumb();

			String partNumber = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.getPartNumber();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.verifyPartNumberInMyCartPopUp(partNumber)
					.clickOnCloseButtonInMyCartPopUp()
					.clickOnCartIcon()
					.verifyMyCartBreadcrumb(myCartBreadcrumb)
					.clickOnCheckoutInMyCartPage()
					.checkoutPage()
					.verifyCheckoutBreadCrumb()
					.verifyCheckoutPageName()
					.verifyTitleOfCheckoutPage(context.getCurrentXmlTest().getParameter("Company"))
					.verifyBillingAddressTab()
					.verifyShippingAddressTab()
					.verifyOrderDetailstab()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.selectShipMethod(shipMethod)
					.clickOnNextButton();
			Thread.sleep(2600);
			checkoutPage().verifyWhetherOrderDetailsTabIsActive()
					.verifyOrderDetailsFields()
					.enterPurchaseOrderNumber(purchaseOrderNumber)
					.enterOrderedBy(orderBy)
					.selectRequiredByDate(date1)
					.enterShippingInstructions(shippingInstruction)
					.enterOrderNotes(orderNotes)
					.clickOnPreviousButton();
			Thread.sleep(1400);
			checkoutPage().verifyShippingAddressTab().verifyWhetherShippingAddressTabIsActive().clickOnNextButton();
			Thread.sleep(20000);
			checkoutPage()
					.verifyPreviouslyFilledDataOfAllMandatoryFieldsInOrderDetailsPage(shipMethod, orderBy,
							purchaseOrderNumber, shippingInstruction, orderNotes)
					.verifyOrderSummaryFields(orderSummaryFields)
					.clickOnSubmitOrderButton();
			Thread.sleep(2500);
			checkoutPage().verifyOrderConfirmationPageTitle(context.getCurrentXmlTest().getParameter("Company"))
					.verifyOrderConfirmationPageName(orderConfirmationPageName)
					.verifyOrderConfirmationBreadCrumb(orderConfirmationPageName)
					.verifyOrderConfirmationTableHeaderFields(orderConfirmationFields, orderConfirmationHeaderFields)
					.verifyOrderConfirmationSucessMessage(data.getOrderConfirmationSuccessMessage());
			String orderNumber = checkoutPage().getOrderNumber();
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnOrderHistory()
					.orderHistoryPage()
					.sendTextToSearchBox(orderNumber)
					.verifyTextDisplayForOrderNo(orderNumber);

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyCheckoutProcessByPO failed :" + t.getMessage());
			Assert.fail("verifyCheckoutProcessByPO failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyCheckoutProcessByPO Completed--------------");
	}

	@Feature("PO Checkout")
	@Description("Verify whether able to place a PO order.")
	@Test(groups = { "POCheckoutModule",
			"regression" }, dataProvider = "POCheckout", dataProviderClass = TestNGDataProvider.class)
	public void a_verifyBillingAddressFields(String billingAddressMandatoryFieldsData, String billingAddressStateData,
			String billingAddressCountryData, ITestContext context) throws Exception {
		log.info("-----------------a_verifyBillingAddressFields started--------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnCheckoutInMyCartPage()
					.checkoutPage()
					.verifyMandatoryFieldsOfBillingAddressPreFilledWithData(billingAddressMandatoryFieldsData,
							billingAddressStateData, billingAddressCountryData);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("a_verifyBillingAddressFields failed :" + t.getMessage());
			Assert.fail("a_verifyBillingAddressFields failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("-----------------a_verifyBillingAddressFields Completed--------------");

	}

	@Feature("PO Checkout")
	@Description("Verify whether able to place a PO order.")
	@Test(groups = { "POCheckoutModule", "Smoke Test",
			"regression" }, dataProvider = "POCheckout", dataProviderClass = TestNGDataProvider.class)
	public void b_verifyShippingAddressFields(String shippingAddressFieldsData,
			String shipToNameStateCountryFieldsDataOfShippingAddress, ITestContext context) throws Exception {
		log.info("-----------------b_verifyShippingAddressFields started--------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnCheckoutInMyCartPage()
					.checkoutPage()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.verifyFieldsOfShippingAddressPreFilledWithData(shippingAddressFieldsData,shipToNameStateCountryFieldsDataOfShippingAddress);

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("b_verifyShippingAddressFields failed :" + t.getMessage());
			Assert.fail("b_verifyShippingAddressFields failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("-----------------b_verifyShippingAddressFields Completed--------------");

	}

	@Feature("PO Checkout")
	@Description("Verify Error Message by Leaving Mandatory fields.")
	@Test(groups = { "POCheckoutModule", "regression" })
	public void verifyErrorScenarioOrderDetails() throws Exception {
		log.info("-----------------verifyErrorScenarioOrderDetails started--------------");
		try {

			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/POCheckout.xlsx",
					"verifyErrorScenarioOrderDetails");
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnCheckoutInMyCartPage()
			.checkoutPage()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.selectShipMethod("CMS Delivery")
					.clickOnNextButton()
					.verifyWhetherOrderDetailsTabIsActive()
					.clickOnSubmitOrderButton()
					.verifyErrorMessgaeIfMandatoryFieldsLeaveBlankInOrderDetailsPage(
							"Please Enter Purchase Order Number.Please Enter Order By.Please Select Required Date.");
			int j = 0;
			for (int i = 0; i < testData.length; i++) {
				checkoutPage().enterPurchaseOrderNumber(testData[i][j++])
						.enterOrderedBy(testData[i][j++])
						.selectRequiredByDate(date1)
						.clickOnSubmitOrderButton()
						.scrollThePage()
						.verifyErrorMessgaeIfMandatoryFieldsLeaveBlankInOrderDetailsPage(testData[i][j++]);
				j = 0;
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorScenarioOrderDetails failed :" + t.getMessage());
			Assert.fail("verifyErrorScenarioOrderDetails failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();

		}
		log.info("-----------------verifyErrorScenarioOrderDetails Completed--------------");
	}

}