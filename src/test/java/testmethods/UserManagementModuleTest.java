package testmethods;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;
import utility.MSExcelMethods;

public class UserManagementModuleTest extends PageInitializer {

	Logger logger = Logger.getLogger(UserManagementModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	MSExcelMethods excelMethods = new MSExcelMethods();

	Date date = new Date();

	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	String date1 = Integer.toString(localDate.getDayOfMonth());

	@Feature("User Management")
	@Description("Verify User Management Page and link available for super user")
	@Test(groups = { "UserManagementModule", "regression", "Smoke Test" })
	public void verifyUserManagementPage(ITestContext context) throws Exception {
		try {
			logger.info("--------------verifyUserManagementPage execution has started----------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown()
					.verifyMyAccountDropdown(data.getSuperUserAccountDropDown())
					.clickOnUserManagementLink();
			userManagementPage().verifyUserManagementPage(data.getUserManagementTableHeader());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyUserManagementPage failed :" + t.getMessage());
			Assert.fail("verifyUserManagementPage failed :" + t.getMessage());
		} finally {

			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("----------verifyUserManagementPage execution has been completed successfully--------------");
	}

	@Feature("User Management")
	@Description("Verify add new user fields")
	@Test(groups = { "UserManagementModule", "regression", })
	public void verifyUserManagementPageOnceClickedOnAddNewUserButton(ITestContext context) throws Exception {
		try {

			logger.info("-------------verifyUserManagementPageOnceClickedOnAddNewUserButton Started---------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnAddNewUserButton()
					.verifyAddNewUserFields(data.getMandatoryAndNonMandatoryFieldsOfAddNewUserPage());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyUserManagementPageOnceClickedOnAddNewUserButton failed :" + t.getMessage());
			Assert.fail("verifyUserManagementPageOnceClickedOnAddNewUserButton failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info(
				"---------------verifyUserManagementPageOnceClickedOnAddNewUserButton Completed -------------------");
	}

	@Feature("User Management")
	@Description("Verify Adding new user (General user)")
	@Test(groups = { "UserManagementModule", "regression",
			"Smoke Test" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void a_verificationOfAddingNewUser(String firstname, String lastName, String userRole, String address1,
			String address2, String city, String state, String zipCode, String phoneNumber, String faxNumber,
			ITestContext context) throws Exception {

		try {

			logger.info("-------------a_verificationOfAddingNewUser Started------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnAddNewUserButton()
					.enterEmailToCreateNewUser(data.getNewlyCreatedUser())
					.enterFirstName(firstname)
					.enterLastName(lastName)
					.enterPassword(data.getPasswordForWhichCartIsShared())
					.enterConfirmPassword(data.getPasswordForWhichCartIsShared())
					.clickOnRoleAssignmentDropDown()
					.selectRoleOfTheUser(userRole)
					.enterAddress1(address1)
					.enterAddress2(address2)
					.enterCity(city)
					.clickOnStateDropDown()
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phoneNumber)
					.enterFaxNumber(faxNumber)
					.clickOnSubmitButton()
					.verifyUserCreatedSuccessMessage(data.getNewlyCreatedUser())
					.clickOnBackToUserListButton()
					.verifyBackToUserListButtonFunctionality(data.getUserManagementTableHeader());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("a_verificationOfAddingNewUser failed :" + t.getMessage());
			Assert.fail("a_verificationOfAddingNewUser failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("---------------a_verificationOfAddingNewUser Completed ---------------------");
	}

	@Feature("User Management")
	@Description("Verify general user submitting cart item for approval")
	@Test(groups = { "UserManagementModule", "regression" })
	public void b_VerifyOfSubmitCartApproval(ITestContext context) throws Exception {
		try {

			logger.info("---------------b_VerifyOfSubmitCartApproval Started------------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.login(data.getNewlyCreatedUser(), data.getPasswordForWhichCartIsShared());
			Thread.sleep(1200);
			myCartPage().clearCart();
			homePage().clickOnMyAccountMenuDropdown().verifyMyAccountDropdown(data.getGeneralUserAccountDropDown());
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			String partNumber = productDetailsPage().getPartNumber();
			String shortDescription = productDetailsPage().getShortDescription();
			productDetailsPage().clickOnAddToCartButton();
			myCartPage().verifyPartNumberInMyCartPopUp(partNumber)
					.verifyShortDescriptionInMyCartPopUp(shortDescription)
					.clickOnViewCartInMyCartPopup();
			myCartPage()
					.verifyButtonsAvailableForSuperUserAndGeneralUserInMyCart(
							data.getGeneralUserAvailableButtonInShoppingCartPage())
					.clickOnSubmitCartForApproval();
			// verify cart submitting success message
			commonOperations().verifyAlertTextPopUp(data.getCartApprovalSuccessMessage()).clickOnOkButtonInAlertPopUp();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("b_VerifyOfSubmitCartApproval failed :" + t.getMessage());
			Assert.fail("b_VerifyOfSubmitCartApproval failed :" + t.getMessage());

		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("-------------------b_VerifyOfSubmitCartApproval Completed --------------------");
	}

	@Feature("User Management")
	@Description("Verify Approving the item submitted by general user")
	@Test(groups = { "UserManagementModule",
			"regression" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void c_verificationOfApprovingItem(String purchaseOrderNumber, String shipMethod, String orderBy,
			String shippingInstruction, String orderNotes, ITestContext context) throws Exception {

		try {
			logger.info("-------------c_verificationOfApprovingItem Started--------------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnApprovalCartListLink();
			approvalCartListPage()
					.verifyApprovalCartListPage(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyDisplayOfGeneralUserMailIdWithDateAndTime(data.getNewlyCreatedUser())
					.clickOnLatestGeneralUserAccountLinkWhileSubmmitedCartForApproval(data.getNewlyCreatedUser())
					.verifyRejectCartButtonInApprovalCartPage()
					.verifyApproveCartButtonInApprovalCartPage();

			approvalCartListPage().enterQuantity("15", 1)
					.clickOnSpecificCheckbox(1)
					.verifyUpdateSelectedItemsButtonInApprovalCartPage()
					.clickOnUpdateSelectedItemsButton()
					.verifyFunctionalityOfUpdateSelectedItemsButton("15", 1)
					.clickOnApproveCartButton();

			checkoutPage().verifyCheckoutBreadCrumb()
					.verifyCheckoutPageName()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.clickOnNextButton();
			Thread.sleep(2600);
			checkoutPage()

					.enterPurchaseOrderNumber(purchaseOrderNumber)
					.selectShipMethod(shipMethod)
					.enterOrderedBy(orderBy)
					.selectRequiredByDate(date1)
					.enterShippingInstructions(shippingInstruction)
					.enterOrderNotes(orderNotes);
			checkoutPage()

					.clickOnSubmitOrderButton();

			Thread.sleep(1400);
			checkoutPage()

					.verifyOrderConfirmationPageTitle(context.getCurrentXmlTest().getParameter("Company"));
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("c_verificationOfApprovingItem failed :" + t.getMessage());
			Assert.fail("c_verificationOfApprovingItem failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("-------------c_verificationOfApprovingItem Completed -------------------");
	}

	@Feature("User Management")
	@Description("Verify Rejecting the item")
	@Test(groups = { "UserManagementModule",
			"regression" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void d_verificationOfRejectingCart(String reasonCartReject, String cartRejectedMessage, ITestContext context)
			throws Exception {

		b_VerifyOfSubmitCartApproval(context);

		try {
			logger.info("----------------d_verificationOfRejectingCart Started----------------");
			loginModule.loginAsASuperUser();

			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown().clickOnApprovalCartListLink();

			approvalCartListPage()
					.verifyApprovalCartListPage(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyDisplayOfGeneralUserMailIdWithDateAndTime(data.getNewlyCreatedUser())
					.clickOnLatestGeneralUserAccountLinkWhileSubmmitedCartForApproval(data.getNewlyCreatedUser())
					.verifyRejectCartButtonInApprovalCartPage()
					.clickOnRejectCartButton()
					.enterReasonToRejectCart(reasonCartReject)
					.clickOnSubmitButtonOfRejectedCartPopUp();

			// verify cart Rejecting success message

			commonOperations().verifyAlertTextPopUp(cartRejectedMessage.trim()).clickOnOkButtonInAlertPopUp();

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("d_verificationOfRejectingCart failed :" + t.getMessage());
			Assert.fail("d_verificationOfRejectingCart failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("--------------d_verificationOfRejectingCart Completed ------------------");
	}

	@Feature("User Management")
	@Description("Verify Adding new user (APA user)")
	@Test(groups = { "UserManagementModule", "regression",
			"Smoke Test" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void e_creationOfAPAUser(String firstname, String lastName, String userRole, String address1,
			String address2, String city, String state, String zipCode, String phoneNumber, String faxNumber,
			ITestContext context) throws Exception {
		try {
			logger.info("--------------e_creationOfAPAUser Started----------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnAddNewUserButton()
					.enterEmailToCreateNewUser(data.getNewlyCreatedAPAUser())
					.enterFirstName(firstname)
					.enterLastName(lastName)
					.enterPassword(data.getPasswordForWhichCartIsShared())
					.enterConfirmPassword(data.getPasswordForWhichCartIsShared())
					.clickOnRoleAssignmentDropDown()
					.selectRoleOfTheUser(userRole)
					.enterAddress1(address1)
					.enterAddress2(address2)
					.enterCity(city)
					.clickOnStateDropDown()
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phoneNumber)
					.enterFaxNumber(faxNumber)
					.clickOnSubmitButton()
					.verifyUserCreatedSuccessMessage(data.getNewlyCreatedAPAUser())
					.clickOnBackToUserListButton()
					.verifyBackToUserListButtonFunctionality(data.getUserManagementTableHeader());
			String agentName = userManagementPage().getAgentName(data.getNewlyCreatedAPAUser());

			userManagementPage().enterUserNameToSearch(data.getNewlyCreatedUser())
					.clickOnSpecificEditButtonOfAgnet(data.getNewlyCreatedUser())
					.clickOnSpecificPurchaseAgentListRadioButton(agentName)
					.clickOnUpdateButton();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("e_creationOfAPAUser failed :" + t.getMessage());
			Assert.fail("e_creationOfAPAUser failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("---------------e_creationOfAPAUser Completed--------------------");
	}

	@Feature("User Management")
	@Description("Verify APA user can Approve an item")
	@Test(groups = { "UserManagementModule",
			"regression" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void f_verificationOfApprovingItem(String purchaseOrderNumber, String shipMethod, String orderBy,
			String shippingInstruction, String orderNotes, ITestContext context) throws Exception {

		b_VerifyOfSubmitCartApproval(context);

		try {

			logger.info("----------------f_verificationOfApprovingItem Started-----------------");

			loginModule.login(data.getNewlyCreatedAPAUser(), data.getPasswordForWhichCartIsShared());

			Thread.sleep(1200);
			myCartPage().clearCart();
			homePage()

					.clickOnMyAccountMenuDropdown()
					.verifyMyAccountDropdown(data.getExpectedApaUserAccountDropdown())
					.clickOnApprovalCartListLink();

			approvalCartListPage()
					.verifyApprovalCartListPage(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyDisplayOfGeneralUserMailIdWithDateAndTime(data.getNewlyCreatedUser())
					.clickOnLatestGeneralUserAccountLinkWhileSubmmitedCartForApproval(data.getNewlyCreatedUser())
					.verifyRejectCartButtonInApprovalCartPage()
					.clickOnApproveCartButton();

			checkoutPage().verifyCheckoutBreadCrumb()
					.verifyCheckoutPageName()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.clickOnNextButton();
			Thread.sleep(2600);

			checkoutPage().enterPurchaseOrderNumber(purchaseOrderNumber)

					.selectShipMethod(shipMethod)
					.enterOrderedBy(orderBy)
					.selectRequiredByDate(date1)
					.enterShippingInstructions(shippingInstruction)
					.enterOrderNotes(orderNotes);

			checkoutPage().clickOnSubmitOrderButton();
			Thread.sleep(1600);

			checkoutPage().verifyOrderConfirmationPageTitle(context.getCurrentXmlTest().getParameter("Company"));

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("f_verificationOfApprovingItem failed :" + t.getMessage());
			Assert.fail("f_verificationOfApprovingItem failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("----------------f_verificationOfApprovingItem Completed------------------");

	}

	@Feature("User Management")
	@Description("Verify Adding new user (Super user)")
	@Test(groups = { "UserManagementModule", "regression",
			"Smoke Test" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void g_creationOfSuperUser(String firstname, String lastName, String userRole, String address1,
			String address2, String city, String state, String zipCode, String phoneNumber, String faxNumber,
			ITestContext context) throws Exception {

		try {

			logger.info("-----------------g_creationOfSuperUser Started---------------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnAddNewUserButton()
					.enterEmailToCreateNewUser(data.getNewlyCreatedSuperUser())
					.enterFirstName(firstname)
					.enterLastName(lastName)
					.enterPassword(data.getPasswordForWhichCartIsShared())
					.enterConfirmPassword(data.getPasswordForWhichCartIsShared())
					.clickOnRoleAssignmentDropDown()
					.selectRoleOfTheUser(userRole)
					.enterAddress1(address1)
					.enterAddress2(address2)
					.enterCity(city)
					.clickOnStateDropDown()
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phoneNumber)
					.enterFaxNumber(faxNumber)
					.clickOnSubmitButton()
					.verifyUserCreatedSuccessMessage(data.getNewlyCreatedSuperUser())
					.clickOnBackToUserListButton()
					.verifyBackToUserListButtonFunctionality(data.getUserManagementTableHeader());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("g_creationOfSuperUser failed :" + t.getMessage());
			Assert.fail("g_creationOfSuperUser failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("---------------g_creationOfSuperUser Completed ----------------------");
	}

	@Feature("User Management")
	@Description("Verify 'Edit' functionality and assigned role to super user and APA user in user list page.")
	@Test(groups = { "UserManagementModule", "regression" })
	public void h_verifyEditFunctionality(ITestContext context) throws Exception {

		try {

			logger.info("------------------h_verifyEditFunctionality Started-------------------");

			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/UserManagement.xlsx",
					"h_verifyEditFunctionality");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			loginModule.loginAsASuperUser();

			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			int j = 0;
			for (int i = 0; i < testData.length; i++) {

				userManagementPage().enterUserNameToSearch(data.getNewlyCreatedUser())
						.clickOnSpecificEditButtonOfAgnet(data.getNewlyCreatedUser())
						.verifyUserInformationSectionWhenClickedOnEditButton()
						.verifyUserPrivilegesSectionWhenClickedOnEditButton()
						.verifyPurchasingAgentShipTo()
						.verifyBackToUserListButton()

						.selectRoleOfTheUserWhenClickedOnEditButton(testData[i][j])
						.selectSpecificShipToAddressCheckbox(1, i)
						.clickOnUpdateButton();
				Thread.sleep(1200);

				userManagementPage().verifyRoleAssignedMessage(testData[i][j]);

				userManagementPage().clickOnBackToUserListButtonAfterClickingOnEditUserButton();

				j = 0;
			}
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("h_verifyEditFunctionality failed :" + t.getMessage());
			Assert.fail("h_verifyEditFunctionality failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("-----------------h_verifyEditFunctionality Completed -------------------");
	}

	@Feature("User Management")
	@Description("Verify 'Edit' functionality and assigned role to General user in user list page.")
	@Test(groups = { "UserManagementModule", "regression" })
	public void i_verifyEditFunctionalityForGeneralUser(ITestContext context) throws Exception {
		try {
			logger.info("--------------------i_verifyEditFunctionalityForGeneralUser Started----------------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().enterUserNameToSearch(data.getNewlyCreatedUser())
					.clickOnSpecificEditButtonOfAgnet(data.getNewlyCreatedUser())
					.verifyUserInformationSectionWhenClickedOnEditButton()
					.verifyUserPrivilegesSectionWhenClickedOnEditButton()
					.verifyPurchasingAgentShipTo()
					.verifyBackToUserListButton()
					.selectRoleOfTheUserWhenClickedOnEditButton("General User")
					.selectSpecificShipToAddressCheckbox(1, 1)
					.clickOnSpecificPurchaseAgentListRadioButton(1)
					.clickOnUpdateButton();
			Thread.sleep(1200);
			userManagementPage().verifyRoleAssignedMessageForGeneralUser("General User");
			userManagementPage().clickOnBackToUserListButtonAfterClickingOnEditUserButton();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("i_verifyEditFunctionalityForGeneralUser failed :" + t.getMessage());
			Assert.fail("i_verifyEditFunctionalityForGeneralUser failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("---------------i_verifyEditFunctionalityForGeneralUser Completed------------------");
	}

	@Feature("User Management")
	@Description("Verify search functionality for valid and invalid data.")
	@Test(groups = { "UserManagementModule", "regression" })
	public void j_verifyOfSearchFunctionalityForValidAndInvalidData(ITestContext context) throws Exception {

		try {

			logger.info("----------j_verifyOfSearchFunctionalityForValidAndInvalidData Started--------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			loginModule.loginAsASuperUser();

			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();

			userManagementPage()

					.enterUserNameToSearch(data.getNewlyCreatedUser())
					.verifyValidsearchResult(data.getNewlyCreatedUser());

			userManagementPage().enterUserNameToSearch(data.getInvalidDataForSearch().trim())
					.verifyErrorMessageForInvalidSearchOfUser();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("j_verifyOfSearchFunctionalityForValidAndInvalidData failed :" + t.getMessage());
			Assert.fail("j_verifyOfSearchFunctionalityForValidAndInvalidData failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("------------------j_verifyOfSearchFunctionalityForValidAndInvalidData Completed --------------");
	}

	@Feature("User Management")
	@Description("Verify error message without changing the role")
	@Test(groups = { "UserManagementModule", "regression" })
	public void k_veriyErrorMessageWithoutUpdatingRoleOfTheUser(ITestContext context) throws Exception {

		try {
			logger.info("-----------k_veriyErrorMessageWithoutUpdatingRoleOfTheUser Started-----------------");

			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();

			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().enterUserNameToSearch(data.getNewlyCreatedUser())
					.clickOnSpecificEditButtonOfAgnet(data.getNewlyCreatedUser())

					.verifyUserInformationSectionWhenClickedOnEditButton()
					.verifyUserPrivilegesSectionWhenClickedOnEditButton()
					.verifyPurchasingAgentShipTo()
					.verifyBackToUserListButton()
					.clickOnUpdateButton();

			Thread.sleep(1200);
			userManagementPage()
					.verifyErrorMessageWithoutUpdatingRole(data.getErrorMessageWithoutUpdatingRoleOfTheUser().trim());

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("k_veriyErrorMessageWithoutUpdatingRoleOfTheUser failed :" + t.getMessage());
			Assert.fail("k_veriyErrorMessageWithoutUpdatingRoleOfTheUser failed :" + t.getMessage());

		}

		finally {

			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("--------------k_veriyErrorMessageWithoutUpdatingRoleOfTheUser Completed------------------");
	}

	@Feature("User Management")
	@Description("Verify the functionality of Action Button in user list page")
	@Test(groups = { "UserManagementModule",
			"regression" }, dataProvider = "UserManagement", dataProviderClass = TestNGDataProvider.class)
	public void l_verifyOfActionFunctionality(String alertMessageWhenClickedOnDisableButton,
			String alertMessageWhenClickedOnEnabledButton, ITestContext context) throws Exception {
		try {
			logger.info("----------------l_verifyOfActionFunctionality Started-------------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnSpecificDisableButton(1);
			// verified alert message when clicked on disable button
			commonOperations().verifyAlertTextPopUp(alertMessageWhenClickedOnDisableButton.trim())
					.clickOnCancelButtonInAlertPopUp();
			userManagementPage().verifyAgentStatusAfterClickOnCancelButtonOfDisableButtonAlertPopUp(1);
			userManagementPage().clickOnSpecificDisableButton(1);
			// verified alert message when clicked on disable button
			commonOperations().verifyAlertTextPopUp(alertMessageWhenClickedOnDisableButton.trim())
					.clickOnOkButtonInAlertPopUp();
			Thread.sleep(1200);
			userManagementPage().verifyAgentStatusAfterClickOnOkButtonOfDisableButtonAlertPopUp(1);
			userManagementPage().clickOnSpecificEnabledButton(1);
			// verified alert message when clicked on enabled button
			commonOperations().verifyAlertTextPopUp(alertMessageWhenClickedOnEnabledButton.trim())
					.clickOnCancelButtonInAlertPopUp();
			userManagementPage().verifyAgentStatusAfterClickOnCancelButtonOfEnabledButtonAlertPopUp(1);
			Thread.sleep(1200);
			userManagementPage().clickOnSpecificEnabledButton(1);
			// verified alert message when clicked on enabled button
			commonOperations().verifyAlertTextPopUp(alertMessageWhenClickedOnEnabledButton.trim())
					.clickOnOkButtonInAlertPopUp();
			Thread.sleep(1200);
			userManagementPage().verifyAgentStatusAfterClickOnOKButtonOfEnabledButtonAlertPopUp(1);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("l_verifyOfActionFunctionality failed :" + t.getMessage());
			Assert.fail("l_verifyOfActionFunctionality failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("----------l_verifyOfActionFunctionality Completed --------------------");
	}

	@Feature("User Management")
	@Description("Verify error message while Creating a new general user")
	@Test(groups = { "UserManagementModule", "regression" })
	public void verifyErrorMessageWithoutMandatoryFields(ITestContext context) throws Exception {

		try {
			logger.info("----------- verifyErrorMessageWithoutMandatoryFields Started---------------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnAddNewUserButton().clickOnSubmitButton();
			userManagementPage().verifyErrorMessageIfMandatoryFieldsLeaveBlankInUserManagementPage(
					data.getErrorMessageIfMandatoryFieldsLeaveBlank());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyErrorMessageWithoutMandatoryFields failed :" + t.getMessage());
			Assert.fail("verifyErrorMessageWithoutMandatoryFields failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info("-------------verifyErrorMessageWithoutMandatoryFields Completed -----------------");
	}

	@Feature("User Management")
	@Description("Verify error message by leaving few mandatory fields blank and click on 'Use entity address' checkbox.")
	@Test(groups = { "UserManagementModule", "regression" })
	public void verifyErrorMessageWithoutMandotryFieldsAndClickOnUseEntityAddresscheckbox(ITestContext context)
			throws Exception {
		try {

			logger.info(
					"---------------verifyErrorMessageWithoutMandotryFieldsAndClickOnUseEntityAddresscheckbox Started---------");
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().clickOnMyAccountMenuDropdown().clickOnUserManagementLink();
			userManagementPage().clickOnAddNewUserButton();
			userManagementPage().clickOnUseEntityAddress()
					.clickOnSubmitButton()
					.verifyErrorMessageIfMandatoryFieldsLeaveBlankInUserManagementPage(
							data.getErrorMessageIffewMandatoryFieldsLeaveBlank());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyErrorMessageWithoutMandotryFieldsAndClickOnUseEntityAddresscheckbox failed :"
					+ t.getMessage());
			Assert.fail("verifyErrorMessageWithoutMandotryFieldsAndClickOnUseEntityAddresscheckbox failed :"
					+ t.getMessage());

		}

		finally {

			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		logger.info(
				"-----------verifyErrorMessageWithoutMandotryFieldsAndClickOnUseEntityAddresscheckbox Completed ---------");
	}

}