package testmethods;

import java.util.List;

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
import utility.UtilityMethods;

public class SaveCartModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(SaveCartModuleTest.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	String searchText = data.getSearchTextForPN();

	String savedCartName = data.getSaveCartName();

	String savedCartPageName = data.getSavedCartPageName();

	public void saveCartDelete(String scName) throws Exception {
		homePage().clickOnMySaveCart()
				.searchSaveCart(scName)
				.deleteSaveCart(scName)
				.commonOperations()
				.clickOnOkButtonInAlertPopUp();
				/*.saveCartPage()
				.searchSaveCart(scName)
				.verifyDeletionOfSaveCart(scName);*/
	}
	
	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
		myCartPage().clearCart();
		homePage().clickOnLogo();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		saveCartDelete(savedCartName);
		homePage().clickOnLogout();
		logger.info("clicked on Logout link");
	}

	
	@Feature("Save Cart Module")
	@Description("Verify Saved Cart functionality and contents of saved cart page")
	@Test(groups = { "Smoke Test", "regression" })
	public void a2_createAndVerifySavedCartPage(ITestContext context) throws Exception {
		log.info("-------------------a2_createAndVerifySavedCartPage Started-------------------");
		try {
			// loginModule.loginAsASuperUser();
	
			String partNumber = homePage().searchText(searchText).clickOnSearch().productDetailsPage().getPartNumber();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					// .clickOnViewCartInMyCartPopup()
					.clickOnViewCartInMyCartPopupForSaveCart()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart(savedCartName)
					.hitEnterForSaveCartCreation();
			myCartPage().verifySaveCartCreationMessage(savedCartName);
			homePage().clickOnMySaveCart()
					.verifyPageName(savedCartPageName)
					.verifybreadCrumbs(savedCartPageName)
					.verifySaveCartTitle(savedCartPageName, context.getCurrentXmlTest().getParameter("Company"))
					.clickOnTheCreatedSaveCart(savedCartName)
					.verifyPageNameSaveCartLandingPageName(savedCartName)
					.verifybreadCrumbs(savedCartName)
					.verifyMySavedCartPage()
					.verifyItemsInSavedCart(partNumber.trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("a2_createAndVerifySavedCartPage failed:" + t.getMessage());
			Assert.fail("a2_createAndVerifySavedCartPage-Failed " + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
		}
		log.info("-------------------a2_createAndVerifySavedCartPage Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify error scenarios of creating a New Saved Cart")
	@Test(groups = {
			"regression" }, dataProvider = "Saved Cart", dataProviderClass = TestNGDataProvider.class)
	public void createSavedCartErrorScenarios(String emptyNameAlertText, String splCharAlertText) throws Exception {
		log.info("------------------- createSavedCartErrorScenarios Started-------------------");
		try {
			// loginModule.loginAsASuperUser();

			homePage().searchText(searchText)
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					// .clickOnViewCartInMyCartPopup()
					.clickOnViewCartInMyCartPopupForSaveCart()
					.myCartPage()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart("")
					.hitEnterForSaveCartCreation()
					.commonOperations()
					.verifyAlertTextPopUp(emptyNameAlertText)
					.clickOnOkButtonInAlertPopUp();

			myCartPage().enterNameOfSaveCart("!@#$%")
					.hitEnterForSaveCartCreation()
					.commonOperations()
					.verifyAlertTextPopUp(splCharAlertText)
					.clickOnOkButtonInAlertPopUp();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("createSavedCartErrorScenarios failed:" + t.getMessage());
			Assert.fail("createSavedCartErrorScenarios-Failed " + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
		}
		log.info("-------------------createSavedCartErrorScenarios Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify adding item to existing Saved Cart")
	@Test(groups = { "Smoke Test", "regression" })
	public void addItemToExistingSavedCart() throws Exception {
		log.info("-------------------addItemToExistingSavedCart Started-------------------");

		try {
			// loginModule.loginAsASuperUser();
			String mpn = homePage().searchText(data.getSearchTextForMPN())
					.clickOnSearch()
					.productDetailsPage()
					.getMPN();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					// .clickOnViewCartInMyCartPopup()
					.clickOnViewCartInMyCartPopupForSaveCart()
					.clickOnSaveCartButton()
					.clickOnTheCreatedCartFromTheSaveCartDropdownList(savedCartName);
			homePage().clickOnMySaveCart().clickOnTheCreatedSaveCart(savedCartName).verifyItemsInSavedCartByMPN(mpn);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("addItemToExistingSavedCart failed:" + t.getMessage());
			Assert.fail("addItemToExistingSavedCart-Failed " + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
		}
		log.info("-------------------addItemToExistingSavedCart Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify Saved Cart name edit functionality")
	@Test(groups = { "regression" }, dataProvider = "Saved Cart", dataProviderClass = TestNGDataProvider.class)
	public void editSaveCartNameScenarios(String newSCName, String editSCName, String editNameSuccessMsg,
			String noChangeAlertMsg, String scNameExistsMsg, String blankSCNameAlertMsg, String scNameMoreThan25chars)
			throws Exception {
		try {
			log.info("-------------------editSaveCartNameScenarios Started-------------------");

			// loginModule.loginAsASuperUser();
			homePage().searchText(data.getSearchTextForMPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					// .clickOnViewCartInMyCartPopup()
					.clickOnViewCartInMyCartPopupForSaveCart()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart(newSCName)
					.hitEnterForSaveCartCreation();

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.myCartPage()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart("Automation3")
					.hitEnterForSaveCartCreation();

			homePage().clickOnMySaveCart().searchSaveCart("Automation3").clickOnTheCreatedSaveCart("Automation3");

			// edit saved cart name
			saveCartPage().clickOnEditCartName()
					.enterEditCartName(editSCName)
					.clickOnSaveButton()
					.commonOperations()
					.verifyAlertTextPopUp(editNameSuccessMsg)
					.clickOnOkButtonInAlertPopUp()
					.saveCartPage()
					.verifyPageNameSaveCartLandingPageName(editSCName);

			// edit saved cart name with existing group name and verify alert message
			saveCartPage().clickOnEditCartName()
					.enterEditCartName(newSCName)
					.clickOnSaveButton()
					.commonOperations()
					.verifyAlertTextPopUp(scNameExistsMsg)
					.clickOnOkButtonInAlertPopUp()
					.saveCartPage()
					.clickOnCancelButtonOfEditCartName();

			// edit group name with no change in group name
			saveCartPage().clickOnEditCartName()
					.clickOnSaveButton()
					.commonOperations()
					.verifyAlertTextPopUp(noChangeAlertMsg)
					.clickOnOkButtonInAlertPopUp();

			// alert text when group name is not provided
			saveCartPage().clickOnEditCartName()
					.enterEditCartName("")
					.clickOnSaveButton()
					.commonOperations()
					.verifyAlertTextPopUp(blankSCNameAlertMsg)
					.clickOnOkButtonInAlertPopUp()
					.saveCartPage()
					.clickOnCancelButtonOfEditCartName();

			// edit group name with more than 25 characters
			saveCartPage().clickOnEditCartName()
					.enterEditCartName(scNameMoreThan25chars)
					.clickOnSaveButton()
					.commonOperations()
					.clickOnOkButtonInAlertPopUp()
					.saveCartPage()
					.verifyPageNameSaveCartLandingPageName(scNameMoreThan25chars.substring(0, 25));
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("editSaveCartNameScenarios failed:" + t.getMessage());
			Assert.fail("editSaveCartNameScenarios-Failed " + t.getMessage());
		}

		finally {

			saveCartDelete(newSCName);
			saveCartDelete(scNameMoreThan25chars.substring(0, 25));
			myCartPage().clearCart();
		}
		log.info("-------------------editSaveCartNameScenarios Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify expand and collapse option in Saved Cart")
	@Test(groups = { "regression" })
	public void verifyExpandCollapseOption() throws Exception {
		try {
			log.info("-------------------verifyExpandCollapseOption Started-------------------");

			// loginModule.loginAsASuperUser();
			homePage().clickOnMySaveCart().searchSaveCart(savedCartName).clickOnTheCreatedSaveCart(savedCartName);

			saveCartPage().selectView("Collapse View")
					.verifyCollapseView()
					.selectView("Expand View")
					.verifyExpandView();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyExpandCollapseOption failed:" + t.getMessage());
			Assert.fail("verifyExpandCollapseOption-Failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyExpandCollapseOption Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify bulk options functionality in Saved Cart")
	@Test(groups = { "regression" })
	public void verifyBulkOptionFunctionalityInSC() throws Exception {
		try {
			log.info("-------------------verifyBulkOptionFunctionalityInSC Started-------------------");

			// loginModule.loginAsASuperUser();

			String partNumber1 = homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(3);
			String partNumber2 = productListPage().getSpecificPartNumber(4);
			String partNumber3 = productListPage().getSpecificPartNumber(5);
			String[] partNumbers = { partNumber1, partNumber2, partNumber3 };
			productListPage().clickOnSpecificSelectCheckbox(3)
					.clickOnSpecificSelectCheckbox(4)
					.clickOnSpecificSelectCheckbox(5)
					.clickOnBulkOptionsInProductListPage()
					.clickOnAddItemToCartFromBulkOptionInListPage()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart(savedCartName)
					.hitEnterForSaveCartCreation();
					//.clickOnTheCreatedCartFromTheSaveCartDropdownList(savedCartName);

			myCartPage().clearCart();

			homePage().clickOnMySaveCart().clickOnTheCreatedSaveCart(savedCartName).verifyItemsInSavedCart(partNumbers);

			// bulk option update quantity functionality
			String qty = "15";
			String priceOfAItem = saveCartPage().getPriceOfSpecificItem(partNumber1);
			String updateExtPrice = String.valueOf(Float.parseFloat(qty) * Float.parseFloat(priceOfAItem));
			saveCartPage().enterQuantityForSpecificItem(qty, partNumber1)
					.clickOnSpecificCheckbox(partNumber1)
					.selectBulkActionsDropdown("Update Selected Items")
					.verifyUpdatedExtPrice(updateExtPrice, partNumber1);

			// bulk option add item to cart
			saveCartPage().clickOnSpecificCheckbox(partNumber2)
					.selectBulkActionsDropdown("Add Selected Items to Cart")
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.verifyPartNumberInMyCart(partNumber2);
			myCartPage().clearCart();

			// delete item from save cart
			homePage().clickOnMySaveCart()
					.clickOnTheCreatedSaveCart(savedCartName)
					.clickOnSpecificCheckbox(partNumber3)
					.selectBulkActionsDropdown("Delete Selected Items")
					.commonOperations()
					.clickOnOkButtonInAlertPopUp()
					.saveCartPage()
					.verifyItemIsDeletedFromSavedCart(partNumber3);

			// bulk option update quantity as zero
			/*saveCartPage().enterQuantityForSpecificItem("0", partNumber1)
					.clickOnSpecificCheckbox(partNumber1)
					.verifyAlertText(partNumber1);

			Thread.sleep(1200);
			// bulk option update quantity as blank
			saveCartPage().enterQuantityForSpecificItem("", partNumber1)
					.clickOnSpecificCheckbox(partNumber1)
					.verifyAlertText(partNumber1);*/
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyBulkOptionFunctionalityInSC failed:" + t.getMessage());
			Assert.fail("verifyBulkOptionFunctionalityInSC-Failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyBulkOptionFunctionalityInSC Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify bulk options alert message when no item(s) are selected")
	@Test(groups = { "regression" }, dataProvider = "Saved Cart", dataProviderClass = TestNGDataProvider.class)
	public void verifyBulkOptionInSC_ES(String updateAlertMsg, String addToCartAlertMsg, String deleteAlertMsg)
			throws Exception {
		try {
			log.info("-------------------verifyBulkOptionInSC_ES Started-------------------");

			// loginModule.loginAsASuperUser();
			homePage().clickOnMySaveCart().searchSaveCart(savedCartName).clickOnTheCreatedSaveCart(savedCartName);
			saveCartPage().clickOnSelectAllCheckBox() // to un check if item is checked from previous script
					.clickOnSelectAllCheckBox()
					.selectBulkActionsDropdown("Update Selected Items")
					.commonOperations()
					.verifyAlertTextPopUp(updateAlertMsg)
					.clickOnOkButtonInAlertPopUp();

			saveCartPage().selectBulkActionsDropdown("Add Selected Items to Cart")
					.commonOperations()
					.verifyAlertTextPopUp(addToCartAlertMsg)
					.clickOnOkButtonInAlertPopUp();

			saveCartPage().selectBulkActionsDropdown("Delete Selected Items")
					.commonOperations()
					.verifyAlertTextPopUp(deleteAlertMsg)
					.clickOnOkButtonInAlertPopUp();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyBulkOptionInSC_ES failed:" + t.getMessage());
			Assert.fail("verifyBulkOptionInSC_ES-Failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyBulkOptionInSC_ES Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("Verify item search functionlity")
	@Test(groups = { "regression" })
	public void verifyItemSearchInSC() throws Exception {
		try {
			log.info("-------------------verifyItemSearchInSC Started-------------------");

			// loginModule.loginAsASuperUser();
			homePage()

					.clickOnMySaveCart()
					.searchSaveCart(savedCartName)

					.clickOnTheCreatedSaveCart(savedCartName);
			String partNumberToSearch = saveCartPage().getPartNumberOfSpecificItem(1);

			// valid search
			saveCartPage().searchTextInSavedCart(partNumberToSearch)
					.clickOnGoButton()
					.verifyItemsInSavedCart(partNumberToSearch)
					.clickOnClearSearchButton();

			// Invalid search
			saveCartPage().searchTextInSavedCart("asdfasdf")
					.clickOnGoButton()
					.verifyNoResultsFoundMsg()
					.clickOnClearSearchButton();

			// No search text
			saveCartPage().clickOnGoButton()
					.commonOperations()
					.verifyAlertTextPopUp("Enter keyword for search")
					.clickOnOkButtonInAlertPopUp();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemSearchInSC failed:" + t.getMessage());
			Assert.fail("verifyItemSearchInSC-Failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifyItemSearchInSC Completed-------------------");

	}

	@Feature("Save Cart Module")
	@Description("This is a test case which verifies item sort functionality in saved cart")
	@Test(groups = { "regression" })
	public void verifySortByInSavedCart() throws Exception {
		try {
			log.info("-------------------verifySortByInSavedCart Started-------------------");

			// loginModule.loginAsASuperUser();
			String[] sortByOptions = data.getSortByOptionsInPG().split(",");
			
			

			homePage().clickOnMySaveCart().searchSaveCart(savedCartName).clickOnTheCreatedSaveCart(savedCartName);

			List<String> partNumbers = saveCartPage().getPartNumbers();
			List<String> manufacturerPartNumbers = saveCartPage().getManufacturerPartNumbers();

			for (int i = 0; i < sortByOptions.length; i++) {
				Thread.sleep(800);
				productGroupsPage().verifySortByOption(manufacturerPartNumbers, partNumbers, sortByOptions[i]);
			}
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySortByInSavedCart failed:" + t.getMessage());
			Assert.fail("verifySortByInSavedCart-Failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("------------------- verifySortByInSavedCart Completed-------------------");
	}

}
