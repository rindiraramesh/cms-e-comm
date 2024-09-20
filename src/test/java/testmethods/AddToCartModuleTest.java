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
import utility.MSExcelMethods;

public class AddToCartModuleTest extends PageInitializer {

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	MSExcelMethods excelMethods = new MSExcelMethods();

	Logger log = Logger.getLogger(AddToCartModuleTest.class);

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

	@Feature("Add To Cart")
	@Description("Verify add to cart functionality from details page.")
	@Test(groups = { "AddToCartModule", "regression", "Smoke Test" })
	public void verifyAddToCartFuncatinalityInDetailPage(ITestContext context) throws Exception {

		try {
			log.info("---------verifyAddToCartFuncatinalityInDetailPage execution has started-------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			String partNumber = productDetailsPage().getPartNumber();
			//String shortdescription=productDetailsPage().getShortDescription();
			String price = productDetailsPage().getPrice();

			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.verifyPartNumberInMyCartPopUp(partNumber)
					//.verifyShortDescriptionInMyCartPopUp(shortdescription)
					.verifyPriceInMyCartPopUp(price)
					.verifyViewCartButtonInMyCartPopUp()
					.clickOnCloseButtonInMyCartPopUp();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyAddToCartFuncatinalityInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyAddToCartFuncatinalityInDetailPage failed :" + t.getMessage());

		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("---------verifyAddToCartFuncatinalityInDetailPage execution has completed-------");

	}

	@Feature("Add To Cart")
	@Description("Verify Combine button functionality  when same item is added twice from Details page in my shopping cart.")
	@Test(groups = { "AddToCartModule", "regression", "Smoke Test" })
	public void verifyCombineFunctionalityAddToCartInDetailPage(ITestContext context) throws Exception {
		log.info("---------verifyCombineFunctionalityAddToCartInDetailPage execution has started-------");

		try {
			log = Logger.getLogger("combineFunctionalityAddToCart");
			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			String partNumber = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.getPartNumber();
			int qty = productDetailsPage().getQuantity();
			int combineQty = qty * 2;

			productDetailsPage().clickOnAddToCartButton();
			myCartPage().clickOnCloseButtonInMyCartPopUp();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verifyDisplayOfCombineButtonInMyCartPopUp()
					.clickOnCombineButtonInMyCartPopUp()
					.clickOnViewCartInMyCartPopup()
					.verifyPartNumberInMyCart(partNumber)
					.verifyNumberOfItemsInShoppingCart(1)
					.verifyQuantitiesInShoppingCartForMultipleItems(Integer.toString(combineQty));

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyCombineFunctionalityAddToCartInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyCombineFunctionalityAddToCartInDetailPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("---------verifyCombineFunctionalityAddToCartInDetailPage execution has completed-------");
	}

	@Feature("Add To Cart")
	@Description("Verify Seperate button functionality  when same item is added twice from Details page in my shopping cart.")
	@Test(groups = { "AddToCartModule", "regression" })
	public void verifySeperateFunctionalityAddToCartInDetailPage(ITestContext context) throws Exception {

		try {
			log.info("--------------verifySeperateFunctionalityAddToCartInDetailPage execution has started----------");

			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			String partNumber = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.getPartNumber();
			int qty = productDetailsPage().getQuantity();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().clickOnCloseButtonInMyCartPopUp();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verifyDisplayOfSeperateButtonInMyCartPopUp()
					.clickOnSeperateButtonInMyCartPopUp()
					.clickOnViewCartInMyCartPopup()
					.verifyPartNumberInMyCart(partNumber)
					.verifyNumberOfItemsInShoppingCart(2)
					.verifyQuantitiesInShoppingCartForMultipleItems(Integer.toString(qty));

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySeperateFunctionalityAddToCartInDetailPage failed :" + t.getMessage());
			Assert.fail("verifySeperateFunctionalityAddToCartInDetailPage failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}

		log.info(
				"-------------verifySeperateFunctionalityAddToCartInDetailPage execution has been completed successfully----------");

	}

	@Feature("Add To Cart")
	@Description("Verify Cancel button functionality  when same item is added twice from Details page in my shopping cart.")
	@Test(groups = { "AddToCartModule", "regression" })
	public void verifyCancelFunctionalityAddToCartInDetailPage(ITestContext context) throws Exception {

		log.info("--------------verifyCancelFunctionalityAddToCartInDetailPage execution has started-----------");
		try {

			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			String partNumber = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.getPartNumber();
			int qty = productDetailsPage().getQuantity();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().clickOnCloseButtonInMyCartPopUp();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verifyDisplayOfCancleButtonInMyCartPopUp().clickOnCancleButtonInMyCartPopUp();
			myCartPage().clickOnCartIcon()
					.verifyPartNumberInMyCart(partNumber)
					.verifyNumberOfItemsInShoppingCart(1)
					.verifyQuantitiesInShoppingCartForMultipleItems(Integer.toString(qty));

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyCancelFunctionalityAddToCartInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyCancelFunctionalityAddToCartInDetailPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info(
				"-----------verifyCancelFunctionalityAddToCartInDetailPage execution has been completed successfully---------");

	}

	@Feature("Add To Cart")
	@Description("Verify error msg when qty is entered is less than minimun qty in Details page.")
	@Test(groups = { "AddToCartModule", "regression" },enabled = false)
	public void verifyErrorMessageIfQtyIsLessThanMinOrderInDetailPage() throws Exception {

		try {
			log.info(
					"----------verifyErrorMessageIfQtyIsLessThanMinOrderInDetailPage execution has started-----------");

			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			productDetailsPage().enterQuanityInProductDetailsPage("5")
					.clickOnAddToCartButton()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForMinOrderQuantity())
					.clickOnOkButtonInAlertPopUp();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageIfQtyIsLessThanMinOrder failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageIfQtyIsLessThanMinOrder failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info(
				"------------verifyErrorMessageIfQtyIsLessThanMinOrderInDetailPage execution has been completed successfully--------");

	}

	@Feature("Add To Cart")
	@Description("Verify error msg when qty is entered is 0 or less.")
	@Test(groups = { "AddToCartModule", "regression" })
	public void verifyErrorMessageIfQtyIsZeorOrLessInDetailPage() throws Exception {

		try {
			log.info("----------verifyErrorMessageIfQtyIsZeorOrLessInDetailPage execution has started-------------");

			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			productDetailsPage().enterQuanityInProductDetailsPage("0")
					.clickOnAddToCartButton()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForQuantityLessThanOrEqualToZero())
					.clickOnOkButtonInAlertPopUp();
			
			/*
			 * productDetailsPage().enterQuanityInProductDetailsPage("-5")
			 * .clickOnAddToCartButton() .commonOperations()
			 * .verifyAlertTextPopUp(data.getAlertMessageForMinOrderQuantity())
			 * .clickOnOkButtonInAlertPopUp();
			 */
			 
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageIfQtyIsZeorOrLessInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageIfQtyIsZeorOrLessInDetailPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("----------verifyErrorMessageIfQtyIsZeorOrLessInDetailPage execution has Completed-------------");

	}

	@Feature("Add To Cart")
	@Description("Verify add to cart functionality in list/grid page.")
	@Test(groups = { "AddToCartModule", "regression", "Smoke Test" })
	public void verifiyAddToCartFunctionalityFromPLPage(ITestContext context) throws Exception {

		try {
			log.info("---------verifiyAddToCartFunctionalityFromPLPage execution has started---------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchText()).clickOnSearch();
			String addedItem = productListPage().getSpecificPartNumber(1);
			String shortDescription = productListPage().getSpecificShortDescription(1);
			
Thread.sleep(5000);
			productListPage().inputQtyForSpecificItemInSKUMode(1, "10")
					.clickOnSpecificAddToCartButtonInSKUMode(1)
					.myCartPage()
					.verifyPartNumberInMyCartPopUp(addedItem)
					.verifyShortDescriptionInMyCartPopUp(shortDescription)
					.clickOnViewCartInMyCartPopup()
					.verifyPartNumberInMyCart(addedItem)
					.verifyshortDesciptionInMyCart(addedItem, shortDescription);
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifiyAddToCartFunctionalityFromPLPage failed :" + t.getMessage());
			Assert.fail("verifiyAddToCartFunctionalityFromPLPage failed :" + t.getMessage());

		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info(
				"------------------verifiyAddToCartFunctionalityFromPLPage execution has been completed successfully-----------");

	}

	@Feature("Add To Cart")
	@Description("Verify error msg when qty is entered is less than minimun qty in Product List page.")
	@Test(groups = { "AddToCartModule", "regression" },enabled = false)
	public void verifyErrorMessageIfQtyIsLessThanMinOrderInPLPage() throws Exception {

		try {
			log.info("----------verifyErrorMessageIfQtyIsLessThanMinOrderInPLPage execution has started-----------");

			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchText()).clickOnSearch();
			productListPage().inputQtyForSpecificItemInSKUMode(1, "5")
					.clickOnSpecificAddToCartButtonInSKUMode(1)
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForMinOrderQuantityInPL())
					.clickOnOkButtonInAlertPopUp();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageIfQtyIsLessThanMinOrderInPLPage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageIfQtyIsLessThanMinOrderInPLPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info(
				"------------verifyErrorMessageIfQtyIsLessThanMinOrderInPLPage execution has been completed successfully--------");

	}

	@Feature("Add To Cart")
	@Description("Verify error msg when qty entered is 0 or less in Product List page")
	@Test(groups = { "AddToCartModule", "regression" })
	public void verifyErrorMessageIfQtyIsZeorOrLessInPLPage() throws Exception {

		try {
			log.info("----------verifyErrorMessageIfQtyIsZeorOrLessInPLPage execution has started-------------");

			// loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchText()).clickOnSearch();
			Thread.sleep(5000);
			productListPage().inputQtyForSpecificItemInSKUMode(1, "0")
			
					.clickOnSpecificAddToCartButtonInSKUMode(1)
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForQuantityLessThanOrEqualToZero())
					.clickOnOkButtonInAlertPopUp();
			/*
			 * productListPage().inputQtyForSpecificItemInSKUMode(1, "-5")
			 * .clickOnSpecificAddToCartButtonInSKUMode(1) .commonOperations()
			 * .verifyAlertTextPopUp(data.getAlertMessageForMinOrderQuantityInPL())
			 * .clickOnOkButtonInAlertPopUp();
			 */
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessageIfQtyIsZeorOrLessInPLPage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageIfQtyIsZeorOrLessInPLPage failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("----------verifyErrorMessageIfQtyIsZeorOrLessInPLPage execution has Completed-------------");

	}

}
