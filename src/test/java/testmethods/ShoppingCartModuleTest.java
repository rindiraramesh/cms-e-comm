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
import utility.MSExcelMethods;

public class ShoppingCartModuleTest extends PageInitializer {
	Logger log = Logger.getLogger(ShoppingCartModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	MSExcelMethods excelMethods = new MSExcelMethods();

	SaveCartModuleTest saveCartModule = new SaveCartModuleTest();
	
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

	@Feature("Shopping Cart")
	@Description("Verify Shopping Cart Functionality.")
	@Test(groups = { "ShoppingCartModule", "regression",
			"Smoke Test" }, dataProvider = "Shopping Cart", dataProviderClass = TestNGDataProvider.class)
	public void verifyShoppingCartPage(String itemHeaders, String buttonsAvailable, String productsPageBreadCrumb,
			String mandatoryFieldsOfSendThisPage, String sendThisPageBreadCrumb, String saveCartPlaceHolder,
			ITestContext context) throws Exception {
		log.info("----------------verifyShoppingCartPage Started-----------------------");
		try {
			String myCartBreadcrumb = data.getMyCartBreadcrumb();
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextForPN())
					.clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.clickOnViewCartInMyCartPopup()
					.verifyDisplayOfCartSection()
					.verifyItemHeadersAvailableInMyCartPage(itemHeaders)
					.verifyButtonsAvailableForSuperUserAndGeneralUserInMyCart(buttonsAvailable)
					.verifyMyCartBreadcrumb(myCartBreadcrumb);
			String partNumber = myCartPage().getPartNumberOfSpecificItem(1);
			myCartPage().clickOnImageIfTheProduct().verifyPartNumberInProductDetailsPage(partNumber);
			myCartPage().navigateToShoppingCart();
			/*
			 * myCartPage().enterQuantityForSpecificItem("0",
			 * data.getSearchTextForPN()).clickOnUpdateButton(); Thread.sleep(1200);
			 */
			// Verify Alert Message For Quantity less than or equal to Zero
			/*
			 * commonOperations().verifyAlertTextPopUp(data.
			 * getAlertMessageForQuantityLessThanOrEqualToZero_CartPage())
			 * .clickOnOkButtonInAlertPopUp();
			 */
			myCartPage().clickOnEmptyCartButton();
			Thread.sleep(1200);
			// verify Alert Message When Clicked On Empty Cart Button
			commonOperations().verifyAlertTextPopUp(data.getAlertMessageForDeletionOfItem())
					.verifyOkButtonInAlertPopUp()
					.verifyCancelButtonInAlertPopUp()
					.clickOnCancelButtonInAlertPopUp();
			Thread.sleep(1200);
			// myCartPage().clickOnContinueShopping().productsPage().verifyBreadcrumb(productsPageBreadCrumb);
			myCartPage().clickOnCartIcon()
					.clickOnShareThisLinkInShoppingCart()
					.sendThisPage()
					.verifySendThisPageBreadCrumb(sendThisPageBreadCrumb)
					.verifyPageNameOfSendThisPage()
					.verifyMandatoryFieldOfSendThisPage(mandatoryFieldsOfSendThisPage)
					.verifyItemsInSendThisPage(partNumber);
			myCartPage().clickOnCartIcon().clickOnCheckoutInMyCartPage().verifyCheckoutBreadCrumb();
			myCartPage().clickOnCartIcon()
					.clickOnSaveCartButton()
					.verifyBlankSpaceToCreateNewSaveCart(saveCartPlaceHolder)
					.verifyPreviouslyCreatedSavedCart()
					.enterNameOfSaveCart(data.getSaveCartName())
					.hitEnterForSaveCartCreation();
			Thread.sleep(1000);
			myCartPage().verifySaveCartCreationMessage(data.getSaveCartName())
					.clickOnTheConfirmationMessage(data.getSaveCartName())
					.saveCartPage()
					.verifybreadCrumbs(data.getSaveCartName())
					.verifyPageName(data.getSaveCartName());

			myCartPage().clickOnCartIcon()
					.clickOnSaveCartButton()
					.clickOnTheCreatedCartFromTheSaveCartDropdownList(data.getSaveCartName())
					.verifySaveCartCreationMessage(data.getSaveCartName())
					.clickOnTheConfirmationMessage(data.getSaveCartName());
			saveCartPage().verifyItemsInSavedCart(partNumber);
			saveCartModule.saveCartDelete(data.getSaveCartName());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyShoppingCartPage failed :" + t.getMessage());
			Assert.fail("verifyShoppingCartPage failed :" + t.getMessage());

		}

		finally {
			myCartPage().clearCart();
					}
		log.info("---------------verifyShoppingCartPage completed----------------");
	}

	@Feature("Shopping Cart")
	@Description("Verify Update Button Funcationality.")
	@Test(groups = { "ShoppingCartModule", "regression", "Smoke Test" })
	public void verifyUpdateCartButtonFuncationality() throws Exception {
		log.info("-------------verifyUpdateCartButtonFuncationality Started------------------");
		try {
			
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextForPN())
					.clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.clickOnViewCartInMyCartPopup();
			String qty = "15";
			String priceOfAItem = myCartPage().getPriceOfSpecificItem(data.getSearchTextForPN());
			String updateExtPrice = String.valueOf(Float.parseFloat(qty) * Float.parseFloat(priceOfAItem));
			myCartPage().enterQuantityForSpecificItem(qty, data.getSearchTextForPN())
					.clickOnUpdateButton()
					.verifyUpdateOfQuantityInShoppingCart(qty, data.getSearchTextForPN())
					.verifyUpdatedExtPrice(updateExtPrice, data.getSearchTextForPN());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUpdateCartButtonFuncationality failed :" + t.getMessage());
			Assert.fail("verifyUpdateCartButtonFuncationality failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------verifyUpdateCartButtonFuncationality Completed-------------------");

	}

	@Feature("Shopping Cart")
	@Description("Verification Of Pagination Functionality and delete Button Functionality Of Shopping Cart Page.")
	@Test(enabled = false, groups = { "ShoppingCartModule", "regression" })
	public void verificationOfPaginationAndOkCancleButtonFunctionality() throws Exception {
		log.info("-----------verificationOfPaginationAndOkCancleButtonFunctionality Started-----------------");

		try {
			Thread.sleep(1000);
			String totalNoOfItemsInMyCartPage = homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnSelectItemCheckbox(10)
					.clickOnBulkOptionsInProductListPage()
					.clickOnAddItemToCartFromBulkOptionInListPage()
					.myCartPage()
					.clickOnCloseButtonInMyCartPopUpOfProductListGridPage()
					.clickOnCartIcon()
					.getTotalNumberofItemsInMyCart();
			myCartPage().clickOnPaginationButton("2").verifyPaginationPage("2");
			myCartPage().clickOnPaginationButton("1").verifyPaginationPage("1");
			String partNumber = myCartPage().getPartNumberOfSpecificItem(1);
			myCartPage().clickOnDeleteLink().verifyDeleteAlertTextMessage(partNumber);
			commonOperations().verifyCancelButtonInAlertPopUp()
					.verifyOkButtonInAlertPopUp()
					.clickOnCancelButtonInAlertPopUp();
			myCartPage().verifyNoOfItemsInMyCart(totalNoOfItemsInMyCartPage).clickOnDeleteLink();
			commonOperations().clickOnOkButtonInAlertPopUp();
			String noOfItemsAfterClickingOnOkButton = myCartPage().getTotalNumberofItemsInMyCart();
			myCartPage().verifyNoOfItemsInMyCart(noOfItemsAfterClickingOnOkButton);

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verificationOfPaginationAndOkCancleButtonFunctionality failed :" + t.getMessage());
			Assert.fail("verificationOfPaginationAndOkCancleButtonFunctionality failed :" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("-----------verificationOfPaginationAndOkCancleButtonFunctionality Completed-------------------");
	}

	@Feature("Shopping Cart")
	@Description("Verify Error Message When Mandatory Fields Of Send This Page Left Blank")
	@Test(groups = { "ShoppingCartModule", "regression" })
	public void verifyErrorScenarioSendThisPage() throws Exception {
		log.info("---------------verifyErrorScenarioSendThisPage Started--------------------");

		try {
			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/ShoppingCart.xlsx",
					"verifyErrorScenarioSendThisPage");
			
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextForPN())
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnShareThisLinkInShoppingCart();
			sendThisPage().verifyItemsInSendThisPage(data.getSearchTextForPN());
			int j = 0;
			for (int i = 0; i < testData.length; i++) {
				sendThisPage().enterFriendName(testData[i][j++])
						.enterFriendEmailAddress(testData[i][j++])
						.enterFromName(testData[i][j++])
						.enterFromEmailAddress(testData[i][j++])
						.enterSubject(testData[i][j++])
						.clickOnSend()
						.verifyErrorMessage(testData[i][j++]);
				j = 0;
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorScenarioSendThisPage failed :" + t.getMessage());
			Assert.fail("verifyErrorScenarioSendThisPage failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------verifyErrorScenarioSendThisPage Completed--------------------");

	}

	@Feature("Shopping Cart")
	@Description("Verify Error Message When Mandatory Fields Of Send This Page Left Blank")
	@Test(groups = { "ShoppingCartModule", "regression" })
	public void shoppingCart_quantityField_ES() throws Exception {
		log.info("---------------shoppingCart_quantityField_ES Started---------------------");

		try {
			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/ShoppingCart.xlsx",
					"shoppingCart_quantityField_ES");
			myCartPage().clearCart();

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup();
			int j = 0;
			for (int i = 0; i < testData.length; i++) {

				myCartPage().enterQuantityForSpecificItem(testData[i][j++], data.getSearchTextForPN())
						// .clickOnUpdateButton()
						.verifyUpdateOfQuantityInShoppingCart(testData[i][j++], data.getSearchTextForPN());
				j = 0;
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("shoppingCart_quantityField_ES failed :" + t.getMessage());
			Assert.fail("shoppingCart_quantityField_ES failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------shoppingCart_quantityField_ES Completed--------------------");

	}

}