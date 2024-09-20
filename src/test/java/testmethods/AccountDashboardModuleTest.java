package testmethods;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

public class AccountDashboardModuleTest extends PageInitializer {

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	ProductGroupModuleTest productModule = new ProductGroupModuleTest();

	SaveCartModuleTest saveCartModule = new SaveCartModuleTest();

	Logger logger = Logger.getLogger(AccountDashboardModuleTest.class);

	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogout();
	}

	@Feature("Account Dashboard")
	@Description("Verify My Account Dropdown values for super user.")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verifyMyAccountDropdown(ITestContext context) throws Exception {

		try {
			logger.info("verifyMyAccountDropdown execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			// loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().verifyMyAccountDropdown(data.getSuperUserAccountDropDown());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyMyAccountDropdown failed :" + t.getMessage());
			Assert.fail("verifyMyAccountDropdown failed :" + t.getMessage());
		} finally {

			homePage().clickOnLogo();
		}
		logger.info("verifyMyAccountDropdown execution has been completed successfully");
	}

	@Feature("Account Dashboard")
	@Description("Verify My Account page, Profile Tab.")
	@Test(groups = { "AccountDashboardModule", "regression",
			"Smoke Test" }, dataProvider = "AccountDashboard", dataProviderClass = TestNGDataProvider.class)
	public void verifyMyAccountPageProfileTab(String accountDetailLabels, ITestContext context) throws Exception {

		try {
			logger.info("verifyMyAccountPageProfileTab execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			// loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.verifyMyAccountPage()
					.verifyProfileTab(accountDetailLabels);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyMyAccountPageProfileTab failed :" + t.getMessage());
			Assert.fail("verifyMyAccountPageProfileTab failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}

		logger.info("verifyMyAccountPageProfileTab execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify Profile Tab Contact Info")
	@Test(groups = { "AccountDashboardModule", "regression", "Smoke Test" })
	public void verifyProfileTabContactInfo(ITestContext context) throws Exception {

		try {
			logger.info("VerifyProfileTabContactInfo execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnEditContactInfo();
			editContactInfoPage()
					.verifyEditContactInfoPageTitle(data.getEditContactPageName() + " | "
							+ context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyEditContactInfoPageName(data.getEditContactPageName());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyProfileTabContactInfo failed :" + t.getMessage());
			Assert.fail("verifyProfileTabContactInfo failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("VerifyProfileTabContactInfo execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	// recently added
	@Description("Verify Error Message Without Entering Mandatory Fields.")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verifyErrorMessageWithoutEnterMandatoryFieldsInEditContactPage(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyErrorMessageWithoutEnterMandatoryFieldsInEditContactPage");
			logger.info("verifyErrorMessageWithoutEnterMandatoryFieldsInEditContactPage execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnEditContactInfo();
			editContactInfoPage().enterFirstName("")
					.enterLastName("")
					.enterAddress1("")
					.enterCity("")
					//.selectState("Select State")
					.enterZipCode("")
					.enterPhoneNumber("")
					.clickOnUpdateButton()
					.verifyErrorMessageWhenMandatoryFieldBlank(
							data.getErrorMessageIfMandatoryFieldsLeaveBlankInEditContactPage());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyErrorMessageWithoutEnterMandatoryFieldsInEditContactPage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageWithoutEnterMandatoryFieldsInEditContactPage failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info(
				"verifyErrorMessageWithoutEnterMandatoryFieldsInEditContactPage execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify Image Upload.")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verifyImageUpload(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyImageUpload");
			logger.info("verifyImageUpload execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount();
			/*
			 * .myAccountsPage() .uploadFile(data.getImagePath().trim()) .clickOnUpload()
			 * .verifyFileUpload(data.getImagePath().trim()) .clickOnClearButton()
			 * .verifyClearButtonFunctionality();
			 */
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyImageUpload failed :" + t.getMessage());
			Assert.fail("verifyImageUpload failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyImageUpload execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify 'Last Login' details")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verificationOfLastLoginDetails(ITestContext context) throws Exception {

		try {
			logger = Logger.getLogger("verificationOfLastLoginDetails");
			logger.info("verificationOfLastLoginDetails execution has started");
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, YYYY");
			String expectedSystemDate = formatter.format(date);
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().verifyOfLastLogin(
					expectedSystemDate);
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verificationOfLastLoginDetails failed :" + t.getMessage());
			Assert.fail("verificationOfLastLoginDetails failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verificationOfLastLoginDetails execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify Error Message wihout entering data in madatory fields Billing Address")
	@Test(groups = { "AccountDashboardModule",
			"regression" }, dataProvider = "AccountDashboard", dataProviderClass = TestNGDataProvider.class)
	public void verifyErrorMessageBillAdd(String state, String errorMessage, ITestContext context) throws Exception {

		try {
			logger = Logger.getLogger("verifyErrorMessageBillAdd");
			logger.info("verifyErrorMessageBillAdd execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnBillingAddressEditIcon();
			//userInformationPage().clickOnEditButtonUnderBillingAddress();
			/*
			 * editContactInfoPage().enterCustomerName("") .enterAddress1("")
			 * .enterAddress2("") .enterCity("") //.enterStateInBillingAddressPage(state)
			 * .enterZipCode("") .enterPhoneNumber("") .clickOnUpdateButton()
			 * .verifyErrorMessageWhenMandatoryFieldBlank(errorMessage);
			 */
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyErrorMessageBillAdd failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageBillAdd failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyErrorMessageBillAdd execution has been completed successfully");
	}

	//Needs to update
	@Feature("Account Dashboard Module")
	@Description("Verify Error Message wihout entering data in madatory fields Shipping Address")
	@Test(enabled=false,groups = { "AccountDashboardModule",
			"regression" }, dataProvider = "AccountDashboard", dataProviderClass = TestNGDataProvider.class)
	public void verifyErrorMessageOfShiAddr(String state, String errorMessage, ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyErrorMessageOfShiAddr");
			logger.info("verifyErrorMessageOfShiAddr execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnShippingAddressEditIcon();
			userInformationPage().clickOnSpecificEditButtonUnderShippingAddress(1);
			editContactInfoPage().enterCustomerName("")
					.enterAddress1("")
					.enterAddress2("")
					.enterCity("")
					.enterStateInBillingAddressPage(state)
					.enterZipCode("")
					.enterPhoneNumberInShippingAddressPage("")
					.enterEmailInShippingAddressPage("")
					.clickOnUpdateButton()
					.verifyErrorMessageWhenMandatoryFieldBlank(errorMessage);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyErrorMessageOfShiAddr failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageOfShiAddr failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyErrorMessageOfShiAddr execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify contents present in the 'ADDRESS' tab of 'My Account' page.")
	@Test(groups = { "AccountDashboardModule", "regression", })
	public void verifyContentOfAddressTab(ITestContext context) throws Exception {

		try {

			logger = Logger.getLogger("verifyAddTabEditButtoOfBilling");
			logger.info("verifyAddTabEditButtoOfBilling execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.verifyAddressPage(data.getShipAddressListHeader());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyContentOfAddressTab failed :" + t.getMessage());
			Assert.fail("verifyContentOfAddressTab failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();

		}
		logger.info("verifyAddTabEditButtoOfBilling execution has been completed successfully");
	}

	//Needs to update
	@Feature("Account Dashboard Module")
	@Description("Verify Contact information updated success message when clicked on update button of Billing Address.")
	@Test(groups = { "AccountDashboardModule", "regression" },enabled=false)
	public void verifyUpdateButtonFunctionalityOfBillingAddress(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyAddTabEditButtoOfBilling");
			logger.info("verifyAddTabEditButtoOfBilling execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnBillingAddressEditIcon();
			userInformationPage()
					.verifyUserInformationPageTitle(data.getUserInformationPageName() + " | "
							+ context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyUserInformationPageName(data.getUserInformationPageName());
			userInformationPage().clickOnEditButtonUnderBillingAddress();
			editContactInfoPage()
					.verifyEditContactInfoPageTitle(data.getEditContactPageName() + " | "
							+ context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyBillingAddressPageName();
			editContactInfoPage().clickOnUpdateButton().verifyBillingAddressAndShippingAddressUpdationSuccessMessage(
					data.getContactInformationUpdatedMessageOfBillingAddress());
			Thread.sleep(1200);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyUpdateButtonFunctionalityOfBillingAddress failed :" + t.getMessage());
			Assert.fail("verifyUpdateButtonFunctionalityOfBillingAddress failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyAddTabEditButtoOfBilling execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify Contact information updated success message when clicked on update button of Shipping Address.")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verifyUpdateButtonFunctionalityOfShippingAddress(ITestContext context) throws Exception {

		try {
			logger = Logger.getLogger("verifyEditShippingAddress");
			logger.info("verifyEditShippingAddress execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnShippingAddressEditIcon();
			//userInformationPage().clickOnSpecificEditButtonUnderShippingAddress(1);
			//editContactInfoPage().clickOnUpdateButton().verifyBillingAddressAndShippingAddressUpdationSuccessMessage(
//					data.getContactInformationUpdatedMessageOfShippingAddress());

			Thread.sleep(1200);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyUpdateButtonFunctionalityOfShippingAddress failed :" + t.getMessage());
			Assert.fail("verifyUpdateButtonFunctionalityOfShippingAddress failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyEditShippingAddress execution has been completed successfully");
	}

	//Needs to update
	@Feature("Account Dashboard Module")
	@Description("Verify Shipping Address's edit Button and functionality of 'refresh shipping information' icon")
	@Test(groups = { "AccountDashboardModule", "regression" }, enabled=false)
	public void zverifyTheFunctionalityOfRefreshShippingInformationIcon(ITestContext context) throws Exception {

		try {

			logger = Logger.getLogger("verifyEditShippingAddress");
			logger.info("verifyEditShippingAddress execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnRefreshShippingInformationIcon();
			// verify alert pop up success message once clicked on refresh shipping
			// information' icon
			Thread.sleep(3500);
			commonOperations().verifyAlertTextPopUp(data.getAlertMessageWhenClickOnRefreshShippingIcon())
					.clickOnOkButtonInAlertPopUp();
			/*homePage().clickOnLogo();
			SelectShippingToProceed().selectingShippingAddress1();
			Thread.sleep(2000);*/
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyTheFunctionalityOfRefreshShippingInformationIcon failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfRefreshShippingInformationIcon failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyEditShippingAddress execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify 'Search' Option in 'Address' tab of 'My Account' page")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verificationOfSearchOptionInAddressTabForValidData(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifySearchInAddressTab");
			logger.info("verifySearchInAddressTab execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnAddressTab();
			String addressName = myAccountsPage().getAddressName(1);
			myAccountsPage().sendTextToSearchFunction(addressName)
					.verifyValidSearchResultOfShippingAddress(addressName);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verificationOfSearchOptionInAddressTabForValidData failed :" + t.getMessage());
			Assert.fail("verificationOfSearchOptionInAddressTabForValidData failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifySearchInAddressTab execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify 'Search' Option in 'Address' tab of 'My Account' page")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verificationOfSearchOptionInAddressTabForInValidData(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifySearchInAddressTab");
			logger.info("verifySearchInAddressTab execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnAddressTab();
			myAccountsPage().sendTextToSearchFunction(data.getInvalidDataForSearch())
					.verifyInValidSearchResultOfShippingAddress();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verificationOfSearchOptionInAddressTabForInValidData failed :" + t.getMessage());
			Assert.fail("verificationOfSearchOptionInAddressTabForInValidData failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifySearchInAddressTab execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify 'Show' drop down in Address Tab")
	@Test(groups = { "AccountDashboardModule", "regression" })
	public void verifyShowDropDownFunctionality(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyShowDropDownFunctionality");
			logger.info("verifyShowDropDownFunctionality execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnItemPerPage()
					.clickOnItemPerPageOption("10")
					.verifyDisplayOfItems(10)
					.clickOnItemPerPage()
					.clickOnItemPerPageOption("25")
					.verifyDisplayOfItems(25)
					.clickOnItemPerPage()
					.clickOnItemPerPageOption("50")
					.verifyDisplayOfItems(50)
					.clickOnItemPerPageOption("100")
					.verifyDisplayOfItems(100);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyShowDropDownFunctionality failed :" + t.getMessage());
			Assert.fail("verifyShowDropDownFunctionality failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyShowDropDownFunctionality execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify My Product Group Tab and Product Group present under it.")
	@Test(groups = { "AccountDashboardModule", "regression", })
	public void a_verifyMyProductGroupFunctionalityUnderGroupsTab(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyProductFunctioGroupsTab");
			logger.info("verifyProductFunctioGroupsTab execution has started");
			//homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName(data.getProductGroupName())
					.hitEnter();
			// .verifyMyProductCreationSuccessMsg(data.getProductGroupName());
			homePage()

					.clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnMyProductGroupsTab()
					.verifyMyProductGroupsTab();
					//.clickProductGroupUnderMyAccountTab(data.getProductGroupName());
			productGroupsPage()
					//.clickOnTheGroupCreated(data.getProductGroupName())
					.verifyPageName(data.getProductGroupName())
					.verifyBreadCrumb(data.getProductGroupName())
					.verifyPageTitle(data.getProductGroupName(), context.getCurrentXmlTest().getParameter("Company"));

			//productModule.deleteProductGroup(data.getProductGroupName());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyMyProductGroupFunctionalityUnderGroupsTab failed :" + t.getMessage());
			Assert.fail("verifyMyProductGroupFunctionalityUnderGroupsTab failed :" + t.getMessage());
		}

		finally {

			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyProductFunctioGroupsTab execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify My Product Group Tab and Product Group present under it.")
	@Test(enabled = false, groups = { "AccountDashboardModule", "regression" })
	public void b_verifyViewMoreButtonFunctionalityUnderMyProductGroups(ITestContext context) throws Exception

	{
		try {
			logger = Logger.getLogger("verifyProductFunctioGroupsTab");
			logger.info("verifyProductFunctioGroupsTab execution has started");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnMyProductGroupsTab()
					.clickOnViewMoreUnderMyProductGroup()

					.productGroupsPage()
					.verifyPageTitle(data.getProductGroupPageName(),
							context.getCurrentXmlTest().getParameter("Company"));

		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyViewMoreButtonFunctionalityUnderMyProductGroups failed :" + t.getMessage());
			Assert.fail("verifyViewMoreButtonFunctionalityUnderMyProductGroups failed :" + t.getMessage());
		}

		finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyProductFunctioGroupsTab execution has been completed successfully");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify save cart present under group tab.")
	@Test(groups = { "AccountDashboardModule", "regression", })
	public void c_verifySaveCartFunctionalityUnderGroupsTab(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyProductFunctioGroupsTab");
			logger.info("verifyProductFunctioGroupsTab execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			myCartPage().clearCart();
			String prodName = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.getProductName();
			// .getPartNumber();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.myCartPage()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart(data.getSaveCartName())
					.hitEnterForSaveCartCreation();
			getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			myCartPage().verifySaveCartCreationMessage(data.getSaveCartName());
			homePage()

					.clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnMyProductGroupsTab()
					.clickOnSaveCartUnderMyAccountTab(data.getSaveCartName());

			saveCartPage()
					// .clickOnTheCreatedSaveCart(data.getSaveCartName())
					.verifyPageNameSaveCartLandingPageName(data.getSaveCartName())
					.verifybreadCrumbs(data.getSaveCartName())
					.verifyMySavedCartPage()
					.verifyWhetherProductsAddedAreDisplayedInTheSaveCart(prodName);

		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifySaveCartFunctionalityUnderGroupsTab failed :" + t.getMessage());
			Assert.fail("verifySaveCartFunctionalityUnderGroupsTab failed :" + t.getMessage());
		}

		finally {

			saveCartModule.saveCartDelete(data.getSaveCartName());
			myCartPage().clearCart();
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifySaveCartFunctioGroupsTab execution has been completed successfully.");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify the functionality of View more button present in 'Save Cart' section.")
	@Test(enabled = false, groups = { "AccountDashboardModule", "regression" })
	public void d_verifyViewMoreButtonFunctionalityUnderSaveCart(ITestContext context) throws Exception {
		try {

			logger = Logger.getLogger("verifyViewMoreButtonOfSaveCart");
			logger.info("verifyViewMoreButtonOfSaveCart execution has started.");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnMyProductGroupsTab();
			myAccountsPage().clickOnViewMoreUnderMySavedCart();
			saveCartPage().verifyPageName(data.getSavedCartPageName()).verifySaveCartTitle(data.getSavedCartPageName(),
					context.getCurrentXmlTest().getParameter("Company"));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyViewMoreButtonFunctionalityUnderSaveCart failed :" + t.getMessage());
			Assert.fail("verifyViewMoreButtonFunctionalityUnderSaveCart failed :" + t.getMessage());
		}

		finally {

			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}

		logger.info("verifyViewMoreButtonOfSaveCart execution has been completed successfully.");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify Order Tab Contents.")
	@Test(groups = { "AccountDashboardModule", "regression", }, enabled=false)
	public void verifyOrdersTabContent(ITestContext context) throws Exception

	{
		try {
			logger = Logger.getLogger("verifyOrdersTabAndOpenOrder");
			logger.info("verifyOrdersTabAndOpenOrder execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnOrdersTab()
					.verifyOrdersTabPageContent(data.getOrderPageOpenOrdersHeaders().split(","),
							data.getOrderPageCompletedOrdersHeaders().split(","));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyOrdersTabContent failed :" + t.getMessage());
			Assert.fail("verifyOrdersTabContent failed :" + t.getMessage());
		}

		finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyOrdersTabAndOpenOrder execution has been completed successfully.");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify Order Tab and Click on 'View Order'on any of the Order under 'Open Orders'")
	@Test(groups = { "AccountDashboardModule", "regression" }, enabled=false)
	public void verifyTheFunctionalityOfViewOrderButtonOfOpenOrders(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyOrdersTabAndOpenOrder");
			logger.info("verifyOrdersTabAndOpenOrder execution has started");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnOrdersTab()
					.clickOnViewOrderButtonOfOpenOrder(1)
					.orderDetailsPage()
					.verifyOrderDetailPageName(data.getOrderDetailPageName())
					.verifyOrderDetailBreadCrumbForOpenOrderAndCompletedOrder(
							data.getOrderDetailBreadCrumbForOpenOrder());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyRfQpagefieldsPresence failed :" + t.getMessage());
			Assert.fail("verifyRfQpagefieldsPresence failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyOrdersTabAndOpenOrder execution has been completed successfully.");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify View Order Button Under Completed Orders")
	@Test(groups = { "AccountDashboardModule", "regression" },enabled=false)
	public void verifyTheFunctionalityOfViewOrderButtonOfCompletedOrders(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyViewOrderOfCompletedOrder");
			logger.info("verifyViewOrderOfCompletedOrder execution has started");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnOrdersTab()
					.clickOnViewOrderButtonOfCompletedOrder(1)
					.orderDetailsPage()
					.verifyOrderDetailPageName(data.getOrderDetailPageName())
					.verifyOrderDetailBreadCrumbForOpenOrderAndCompletedOrder(
							data.getOrderDetailBreadCrumbForCompletedOrder());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyTheFunctionalityOfViewOrderButtonOfOpenOrders failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfViewOrderButtonOfOpenOrders failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyViewOrderOfCompletedOrder execution has been completed successfully.");
	}

	@Feature("Account Dashboard Module")
	@Description("Verify 'Order Number' functionality under open orders.")
	@Test(groups = { "AccountDashboardModule", "regression" },enabled=false)
	public void verifyTheFunctionalityOfOrderNumberOfOpenOrder(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyOrderNoFunOfOpenOrder");
			logger.info("verifyOrderNoFunOfOpenOrder execution has started");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnOrdersTab();
			myAccountsPage().clickOnSpecificOrderNumber(1);
			orderDetailsPage().verifyOrderDetailPageName(data.getOrderDetailPageName())
					.verifyOrderDetailBreadCrumbForOpenOrderAndCompletedOrder(
							data.getOrderDetailBreadCrumbForOpenOrder());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyTheFunctionalityOfOrderNumberOfOpenOrder failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfOrderNumberOfOpenOrder failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyOrderNoFunOfOpenOrder execution has been completed successfully.");
	}

	// Test this feature in SD/MS project
	@Feature("Account Dashboard Module")
	@Description("Verify the functionality of View All button Under Open Orders")
	@Test(enabled = false, groups = { "AccountDashboardModule", "regression" })
	public void verifyTheFunctionalityOfViewAllButtonOfOpenOrder(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyViewAllButtonOfOpenOrder");
			logger.info("verifyViewAllButtonOfOpenOrder execution has started.");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnOrdersTab();
			myAccountsPage().clickOnViewAllButtonOfOpenOrders();
			openOrdersPage().verifyOpenOrdersPageName()
					.verifyOpenOrdersTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyTheFunctionalityOfViewAllButtonOfOpenOrder failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfViewAllButtonOfOpenOrder failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyViewAllButtonOfOpenOrder execution has been completed successfully.");
	}

	// Test this feature in SD/MS project
	@Feature("Account Dashboard Module")
	@Description("Verify the functionality of View All button Under Completed Orders")
	@Test(enabled = false, groups = { "AccountDashboardModule", "regression" })
	public void verifyTheFunctionalityOfViewAllButtonOfCompltedOrder(ITestContext context) throws Exception {
		try {
			logger = Logger.getLogger("verifyViewAllButtonOfCompltedOrder");
			logger.info("verifyViewAllButtonOfCompltedOrder execution has started.");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			/*
			 * loginModule.login(data.getSuperUserCredentialForUserManagementModule(),
			 * data.getPasswordForWhichCartIsShared());
			 */
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnMyAccount().myAccountsPage().clickOnOrdersTab();
			myAccountsPage().clickOnViewAllButtonOfCompletedOrders();
			completedOrdersPage().verifyCompletedOrdersPagename()
					.verifyCompleteOrdersTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyTheFunctionalityOfViewAllButtonOfCompltedOrder failed :" + t.getMessage());
			Assert.fail("verifyTheFunctionalityOfViewAllButtonOfCompltedOrder failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("verifyViewAllButtonOfCompltedOrder execution has been completed successfully.");
	}

}
