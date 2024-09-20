package testmethods;

import java.util.Arrays;

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

public class ProductGroupModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(ProductGroupModuleTest.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	String productGroupName = data.getProductGroupName();

	String updatedGroupName = "TestProductGroup102";

	public void deleteProductGroup(String productGroupName) throws Exception {
		homePage().navigateToMyProductGroups()
				.searchProductGroup(productGroupName)
				.deleteProductGroup(productGroupName)
				.commonOperations()
				.clickOnOkButtonInAlertPopUp()
				.productGroupsPage()
				.searchProductGroup(productGroupName)
				.verifyWhetherGroupIsDeleted(productGroupName);
	}

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

	@Feature("My Product Group Module")
	@Description("Verify Product group page and fields present in it")
	@Test(groups = { "Smoke Test", "regression" })
	public void verifyMyProductGroupPage(ITestContext context) throws Exception {
		log.info("--------------------------verifyMyProductGroupPage Started----------------------");

		try {
			
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName(productGroupName)
					.hitEnter()
					.verifyMyProductCreationSuccessMsg(productGroupName)
					.homePage()
					.navigateToMyProductGroups()
					.productGroupsPage()
					.verifyPageTitle(data.getProductGroupPageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.verifyPageNameOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.verifyBreadCrumbOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.clickOnTheGroupCreated(productGroupName)
					.verifyPageName(productGroupName)
					.verifyBreadCrumb(productGroupName)
					.verifyPageTitle(productGroupName, context.getCurrentXmlTest().getParameter("Company"))
					.verifyMyProductGroupFields()
					.verifyItemsInProductGroup(data.getSearchTextForPN());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyMyProductGroupPage-Failed " + t.getMessage());
			Assert.fail("verifyMyProductGroupPage-Failed " + t.getMessage());
		}

		finally {
			deleteProductGroup(productGroupName);
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------verifyMyProductGroupPage Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify creation of Product group from product list page")
	@Test(groups = { "Smoke Test", "regression" })
	public void createPGFromListPage(ITestContext context) throws Exception {
		log.info("--------------------------createPGFromListPage Started----------------------");
		try {
			
			String partNumber = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			productListPage().clickOnSpecificMyProductGroupButton(1)
					.enterGroupName(productGroupName)
					.hitEnter()
					// .verifyMyProductCreationSuccessMsg(productGroupName, 1)
					.homePage()
					.navigateToMyProductGroups()
					.verifyPageTitle(data.getProductGroupPageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.verifyPageNameOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.verifyBreadCrumbOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.clickOnTheGroupCreated(productGroupName)
					.verifyPageName(productGroupName)
					.verifyBreadCrumb(productGroupName)
					.verifyPageTitle(productGroupName, context.getCurrentXmlTest().getParameter("Company"))
					.verifyMyProductGroupFields()
					.verifyItemsInProductGroup(partNumber);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("createPGFromListPage-Failed " + t.getMessage());
			Assert.fail("createPGFromListPage-Failed " + t.getMessage());
		}

		finally {
			deleteProductGroup(productGroupName);
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------createPGFromListPage Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify creation of Product group from Bulk option")
	@Test(groups = { "regression", "Smoke Test" })
	public void createPGFromListPageBulkOption(ITestContext context) throws Exception {
		log.info("--------------------------createPGFromListPageBulkOption Started----------------------");
		try {
			
			String partNumber1 = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);

			String partNumber2 = productListPage().getSpecificPartNumber(2);

			String partNumber3 = productListPage().getSpecificPartNumber(3);

			String[] partNumbers = { partNumber1, partNumber2, partNumber3 };

			productListPage().clickOnSpecificSelectCheckbox(1)
					.clickOnSpecificSelectCheckbox(2)
					.clickOnSpecificSelectCheckbox(3)

					.clickOnAddToProductGroupFromBulkOption()
					.myProductGroupPopUp()
					.verifyItemsSelectedInPopUp(partNumbers)
					.enterProductGroupName("Automation1")
					.clickOnAddNewGroup()
					.clickOnAddButton();
			homePage()

					.navigateToMyProductGroups()

					.clickOnTheGroupCreated("Automation1")

					.verifyItemsInProductGroup(partNumbers);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("createPGFromListPageBulkOption-Failed " + t.getMessage());
			Assert.fail("createPGFromListPageBulkOption-Failed " + t.getMessage());
		}

		finally {
			deleteProductGroup("Automation1");
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------createPGFromListPageBulkOption Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify alert message when product group name is not provided during creation of Product group")
	@Test(groups = { "regression" })
	public void productGroup_errorScenario() throws Exception {
		log.info("--------------------------productGroup_errorScenario Started----------------------");
		try {
			

			// error message in detail page
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName("")
					.hitEnter()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForProductGroup())
					.clickOnOkButtonInAlertPopUp();

			// error message in detail page for Special characters
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName("Bond & Unilog")
					.hitEnter()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForSpecialCharactersInProductGroup())
					.clickOnOkButtonInAlertPopUp();

			// error message in list page
			homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificMyProductGroupButton(1)
					.enterGroupName("")
					.hitEnter()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForProductGroup())
					.clickOnOkButtonInAlertPopUp();

			// error message in list page for Special characters
			homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificMyProductGroupButton(1)
					.enterGroupName("Bond & Unilog")
					.hitEnter()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertMessageForSpecialCharactersInProductGroup())
					.clickOnOkButtonInAlertPopUp();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("productGroup_errorScenario-Failed " + t.getMessage());
			Assert.fail("productGroup_errorScenario-Failed " + t.getMessage());
		}

		finally {
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------productGroup_errorScenario Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify addition of item to existing product group")
	@Test(groups = { "regression" })
	public void c_addProductToExistingPG() throws Exception {
		log.info("--------------------------addProductToExistingPG Started----------------------");
		try {
			
			String partNumber1 = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);

			String partNumber2 = productListPage().getSpecificPartNumber(2);

			String[] partNumbers = { partNumber1, partNumber2 };

			String qty = productListPage().getQuantityOfSpecificItem(1);

			// Create new product group and add item to it
			productListPage().clickOnSpecificMyProductGroupButton(1).enterGroupName(productGroupName).hitEnter();

			getDriver().navigate().refresh();
			Thread.sleep(1500);

			// Add another item and select the product group(from the list) created above
			productListPage().clickOnSpecificMyProductGroupButton(2).selectProductGroupFromList(productGroupName);

			getDriver().navigate().refresh();
			Thread.sleep(1500);
			// Add same item to which quantity has to be verified in product group
			productListPage().clickOnSpecificMyProductGroupButton(1).selectProductGroupFromList(productGroupName);

			// Verify items in product group and quantity of an item
			homePage().navigateToMyProductGroups()
					.clickOnTheGroupCreated(productGroupName)
					.verifyItemsInProductGroup(partNumbers)
					.verifyQuantityOfSpecificItemInPG(Integer.parseInt(qty), partNumber1);

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("addProductToExistingPG-Failed " + t.getMessage());
			Assert.fail("addProductToExistingPG-Failed " + t.getMessage());
		} finally {
			deleteProductGroup(productGroupName);
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------addProductToExistingPG Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify edit group name functionalites")
	@Test(groups = { "regression" }, dataProvider = "ProductGroup", dataProviderClass = TestNGDataProvider.class)
	public void verifyEditGroupName(String pgName1, String searchText1, String pgName2, String searchText2,
			String editPGName, String editNameSuccessMsg, String noChangeAlertMsg, String pgNameExistsMsg,
			String blankPGNameAlertMsg, String pgNameMoreThan25chars) throws Exception {
		log.info("--------------------------verifyEditGroupName Started----------------------");
		try {
			
			homePage().searchText(searchText1)
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName(pgName1)
					.hitEnter()
					// .verifyMyProductCreationSuccessMsg(pgName1)
					.homePage()
					.navigateToMyProductGroups()
					.clickOnTheGroupCreated(pgName1)
					.verifyItemsInProductGroup(searchText1);

			homePage().searchText(searchText2)
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName(pgName2)
					.hitEnter()
					// .verifyMyProductCreationSuccessMsg(pgName2)
					.homePage()
					.navigateToMyProductGroups()
					.clickOnTheGroupCreated(pgName2)
					.verifyItemsInProductGroup(searchText2);

			// edit group name
			productGroupsPage().clickOnEditButton()
					.enterEditGroupName(editPGName)
					.clickOnSave()
					.commonOperations()
					.verifyAlertTextPopUp(editNameSuccessMsg)
					.clickOnOkButtonInAlertPopUp()
					.productGroupsPage()
					.verifyUpdatedGroupName(editPGName);

			// edit group name with existing group name and verify alert message
			productGroupsPage().clickOnEditButton()
					.enterEditGroupName(pgName1)
					.clickOnSave()
					.commonOperations()
					.verifyAlertTextPopUp(pgNameExistsMsg)
					.clickOnOkButtonInAlertPopUp()
					.productGroupsPage()
					.clickOnCancelButtonOfEditPGName();

			// edit group name with no change in group name
			productGroupsPage().clickOnEditButton()
					.clickOnSave()
					.commonOperations()
					.verifyAlertTextPopUp(noChangeAlertMsg)
					.clickOnOkButtonInAlertPopUp();

			// edit group name with more than 25 characters
			productGroupsPage().clickOnEditButton()
					.enterEditGroupName(pgNameMoreThan25chars)
					.clickOnSave()
					.commonOperations()
					.verifyAlertTextPopUp(editNameSuccessMsg)
					.clickOnOkButtonInAlertPopUp()
					.productGroupsPage()
					.verifyPageName(pgNameMoreThan25chars.substring(0, 25));

			// alert text when group name is not provided
			productGroupsPage().clickOnEditButton()
					.enterEditGroupName("")
					.clickOnSave()
					.commonOperations()
					.verifyAlertTextPopUp(blankPGNameAlertMsg)
					.clickOnOkButtonInAlertPopUp();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyEditGroupName-Failed " + t.getMessage());
			Assert.fail("verifyEditGroupName-Failed " + t.getMessage());
		} finally {
			deleteProductGroup(pgName1);
			deleteProductGroup(pgNameMoreThan25chars.substring(0, 25));
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------verifyEditGroupName Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify item search functionality in product group")
	@Test(groups = { "regression" })
	public void verifySearchItemInPG() throws Exception {
		log.info("--------------------------verifySearchItemInPG Started----------------------");
		try {
			
			String partNumber1 = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			String partNumber2 = productListPage().getSpecificPartNumber(2);
			String partNumber3 = productListPage().getSpecificPartNumber(3);
			String partNumber4 = productListPage().getSpecificPartNumber(4);
			String partNumber5 = productListPage().getSpecificPartNumber(5);

			String[] partNumbers = { partNumber1, partNumber2, partNumber3, partNumber4, partNumber5 };

			productListPage().clickOnSpecificSelectCheckbox(1)
					.clickOnSpecificSelectCheckbox(2)
					.clickOnSpecificSelectCheckbox(3)
					.clickOnSpecificSelectCheckbox(4)
					.clickOnSpecificSelectCheckbox(5)
					.clickOnAddToProductGroupFromBulkOption()
					.myProductGroupPopUp()
					.verifyItemsSelectedInPopUp(partNumbers)
					.enterProductGroupName("Automation3")
					.clickOnAddNewGroup()
					.clickOnAddButton();
			homePage().navigateToMyProductGroups().clickOnTheGroupCreated("Automation3").verifyItemsInProductGroup(
					partNumbers);

			// search item in product group
			productGroupsPage().enterSearchText(partNumber2)
					.clickOnSearchButton()
					.verifyItemsInProductGroup(partNumber2)
					.clickOnClearSearchButton();

			Thread.sleep(1500);

			// perform invalid search
			productGroupsPage().enterSearchText("asdfasdfasdf").clickOnSearchButton().verifyErrorMsg();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySearchItemInPG-Failed " + t.getMessage());
			Assert.fail("verifySearchItemInPG-Failed " + t.getMessage());
		}

		finally {
			deleteProductGroup("Automation3");
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------verifySearchItemInPG Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify expand and collapse options in product group")
	@Test(groups = { "regression" })
	public void verifyExpandCollapseOption() throws Exception {
		log.info("--------------------------verifyExpandCollapseOption Started----------------------");
		try {
			
			String partNumber1 = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);

			String partNumber2 = productListPage().getSpecificPartNumber(2);

			String[] partNumbers = { partNumber1, partNumber2 };

			productListPage().clickOnSpecificMyProductGroupButton(1).enterGroupName(productGroupName).hitEnter();

			getDriver().navigate().refresh();
			Thread.sleep(1500);
			productListPage().clickOnSpecificMyProductGroupButton(2).selectProductGroupFromList(productGroupName);

			homePage().navigateToMyProductGroups()
					.clickOnTheGroupCreated(productGroupName)
					.verifyItemsInProductGroup(partNumbers)
					.selectView("Collapse View")
					.verifyCollapseView()
					.selectView("Expand View")
					.verifyExpandView();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyExpandCollapseOption-Failed " + t.getMessage());
			Assert.fail("verifyExpandCollapseOption-Failed " + t.getMessage());
		} finally {
			deleteProductGroup(productGroupName);
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------verifyExpandCollapseOption Started----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify item sort functionality in product group")
	@Test(groups = { "regression" })
	public void verifySortByInPG() throws Exception {
		log.info("--------------------------verifySortByInPG Started----------------------");
		try {
			
			String[] sortByOptions = data.getSortByOptionsInPG().split(",");

			String partNumber1 = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			String partNumber2 = productListPage().getSpecificPartNumber(2);
			String partNumber3 = productListPage().getSpecificPartNumber(3);
			String partNumber4 = productListPage().getSpecificPartNumber(4);
			String partNumber5 = productListPage().getSpecificPartNumber(5);

			String[] partNumbers = { partNumber1, partNumber2, partNumber3, partNumber4, partNumber5 };

			String mpn1 = productListPage().getSpecificMPN(1);
			String mpn2 = productListPage().getSpecificMPN(2);
			String mpn3 = productListPage().getSpecificMPN(3);
			String mpn4 = productListPage().getSpecificMPN(4);
			String mpn5 = productListPage().getSpecificMPN(5);

			String[] mpn = { mpn1, mpn2, mpn3, mpn4, mpn5 };

			productListPage().clickOnSpecificSelectCheckbox(1)
					.clickOnSpecificSelectCheckbox(2)
					.clickOnSpecificSelectCheckbox(3)
					.clickOnSpecificSelectCheckbox(4)
					.clickOnSpecificSelectCheckbox(5)
					.clickOnAddToProductGroupFromBulkOption()
					.myProductGroupPopUp()
					.verifyItemsSelectedInPopUp(partNumbers)
					.enterProductGroupName("Automation4")
					.clickOnAddNewGroup()
					.clickOnAddButton();

			homePage().navigateToMyProductGroups().clickOnTheGroupCreated("Automation4").verifyItemsInProductGroup(
					partNumbers);

			for (int i = 0; i < sortByOptions.length; i++) {
				Thread.sleep(800);
				productGroupsPage().verifySortByOption(Arrays.asList(mpn), Arrays.asList(partNumbers),
						sortByOptions[i]);
			}

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySortByInPG-Failed " + t.getMessage());
			Assert.fail("verifySortByInPG-Failed " + t.getMessage());
		}

		finally {
			deleteProductGroup("Automation4");
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------verifySortByInPG Completed----------------------");

	}

	@Feature("My Product Group Module")
	@Description("Verify bulk option functionalities in product group")
	@Test(groups = { "regression" })
	public void verifyBulkOptionFunctionalityInPG() throws Exception {
		log.info("--------------------------verifyBulkOptionFunctionalityInPG Started----------------------");

		try {
			

			String partNumber1 = homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			String partNumber2 = productListPage().getSpecificPartNumber(2);
			String partNumber3 = productListPage().getSpecificPartNumber(3);
			String[] partNumbers = { partNumber1, partNumber2, partNumber3 };

			productListPage().clickOnSpecificSelectCheckbox(1)
					.clickOnSpecificSelectCheckbox(2)
					.clickOnSpecificSelectCheckbox(3)
					.clickOnAddToProductGroupFromBulkOption()
					.myProductGroupPopUp()
					.verifyItemsSelectedInPopUp(partNumbers)
					.enterProductGroupName("Automation2")
					.clickOnAddNewGroup()
					.clickOnAddButton();

			homePage().navigateToMyProductGroups().clickOnTheGroupCreated("Automation2").verifyItemsInProductGroup(
					partNumbers);

			// bulk option update quantity functionality
			String qty = "3";
			String priceOfAItem = productGroupsPage().getPriceOfSpecificItem(partNumber1);
			String updateExtPrice = String.valueOf(Float.parseFloat(qty) * Float.parseFloat(priceOfAItem));
			Thread.sleep(2000);
			productGroupsPage().enterQuantityForSpecificItem(qty, partNumber1)
					.clickOnSpecificCheckbox(partNumber1)
					.selectBulkActionsDropdown("Update Selected Items")
					.verifyUpdatedExtPrice(updateExtPrice, partNumber1);

			// bulk option update quantity as zero
			productGroupsPage().enterQuantityForSpecificItem("0", partNumber1).clickOnSpecificCheckbox(partNumber1);
			// .verifyAlertText(partNumber1);
			commonOperations().verifyJavaScriptTextPopUp(partNumber1);

			// add item to cart
			productGroupsPage().clickOnSpecificCheckbox(partNumber2)
					.selectBulkActionsDropdown("Add Selected Items to Cart")
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.verifyPartNumberInMyCart(partNumber2);

			// delete item from product group
			homePage().navigateToMyProductGroups()
					.clickOnTheGroupCreated("Automation2")
					.clickOnSpecificCheckbox(partNumber1)
					.selectBulkActionsDropdown("Delete Selected Items")
					.commonOperations()
					.clickOnOkButtonInAlertPopUp()
					.productGroupsPage()
					.verifyItemIsDeletedFromPG(partNumber1);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyBulkOptionFunctionalityInPG-Failed " + t.getMessage());
			Assert.fail("verifyBulkOptionFunctionalityInPG-Failed " + t.getMessage());
		}

		finally {
			deleteProductGroup("Automation2");
			
			homePage().clickOnLogo();
		}
		log.info("--------------------------verifyBulkOptionFunctionalityInPG Completed----------------------");
	}

	@Feature("My Product Group Module")
	@Description("Verify Before Login functionalities in detail and list page")
	@Test(groups = { "regression" })
	public void a_verifyPGCreationBeforeLogin() throws Exception {
		log.info("--------------------z_verifyPGCreationBeforeLogin Started---------------------");
		try {
			homePage().clickOnLogout();
			// add to product group creation verification in detail page
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.loginPopUp()
					.clickOnSignUpLink()
					.verifyPageName();

			// add to product group creation verification in list page

			homePage().searchText(data.getSearchText2())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificMyProductGroupButtonBeforeLogin(1)
					.loginPopUp()
					.clickOnSignUpLink()
					.verifyPageName();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("z_verifyPGCreationBeforeLogin failed :" + t.getMessage());
			Assert.fail("z_verifyPGCreationBeforeLogin failed :" + t.getMessage());
		}finally {
			loginModule.loginAsASuperUser();
			homePage().clickOnLogo();
		}
		log.info("-----------------------a_verifyPGCreationBeforeLogin Completed-----------------------");
	}

}