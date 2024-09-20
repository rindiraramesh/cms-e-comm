package testmethods;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import utility.WaitingMethods;

public class ChangePasswordTest extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	LoginModuleTest loginModule = new LoginModuleTest();

	MSExcelMethods excelMethods = new MSExcelMethods();

	TestDataPropertyFile data = new TestDataPropertyFile();

	String changePasswordFilePath = "src/test/resources/TestDataFiles/ChangePassword.xlsx";

	Logger logger = Logger.getLogger(ChangePasswordTest.class);

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

	@Feature("Change Password module")
	@Test(enabled = false, groups = { "ChangePassword", "regression",
			"Smoke Test" }, dataProvider = "ChangePassword", dataProviderClass = TestNGDataProvider.class)
	@Description("Verification of Change password functionality for logged user with valid values - TC_CP_003,TC_CP_008")
	public void verifyChangePasswordModule(String userName, String passWord, String oldPassword, String newPassword,
			String confirmPassword, String passwordChangeSuccessMsg) throws Exception {
		try {
			logger.info("-----------------verifyChangePasswordModule started----------------");
			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().enterOldPassword(oldPassword)
					.enterNewPassword(newPassword)
					.enterConfirmPassword(confirmPassword)
					.clickOnSaveButton()
					.verifySuccessMsg(passwordChangeSuccessMsg);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyChangePasswordModule-Failed " + t.getMessage());
			Assert.fail("verifyChangePasswordModule-Failed " + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			Thread.sleep(1200);
			loginModule.login(userName, newPassword);
			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().enterOldPassword(newPassword)
					.enterNewPassword(oldPassword)
					.enterConfirmPassword(oldPassword)
					.clickOnSaveButton();
			// .verifySuccessMsg(passwordChangeSuccessMsg);
			// homePage().clickOnLogout();
		}
		logger.info("-----------------verifyChangePasswordModule Completed----------------");
	}

	@Feature("Change Password module")
	@Test(groups = { "ChangePassword", "regression",
			"Smoke Test" }, dataProvider = "ChangePassword", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify the Change password fields and page - TC_CP_001")
	public void verificationOfChangePwdPage(ITestContext context, String pleaseNoteText, String productName,
			String breadcrum) throws Exception {
		try {
			logger.info("-----------------verificationOfChangePwdPage started----------------");
			// login.login(data.getUserName(), data.getPassword());
			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().verifyChangePasswordPage(pleaseNoteText,
					context.getCurrentXmlTest().getParameter("Company").trim(), breadcrum);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verificationOfChangePwdPage-Failed " + t.getMessage());
			Assert.fail("verificationOfChangePwdPage-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("-----------------verificationOfChangePwdPage Completed----------------");
	}

	@Feature("Change Password module")
	@Test(groups = { "ChangePassword",
			"regression" }, dataProvider = "ChangePassword", dataProviderClass = TestNGDataProvider.class)
	@Description("This testcase to verify tab order from Top to Bottom")
	public void verifyChangePwdTabOrderT2B(String userName, String password, String oldPasswordId, String newPasswordId,
			String confirmPasswordId, String saveButtonId, String cancelButtonText) throws Exception {
		try {
			logger.info("-----------------verifyChangePwdTabOrderT2B started----------------");

			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().verifyTabFocusTopToBottom(oldPasswordId, newPasswordId, confirmPasswordId,
					saveButtonId, cancelButtonText);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyChangePwdTabOrderT2B-Failed " + t.getMessage());
			Assert.fail("verifyChangePwdTabOrderT2B-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("-----------------verifyChangePwdTabOrderT2B Completed----------------");
	}

	@Feature("Change Password module")
	@Test(groups = { "ChangePassword",
			"regression" }, dataProvider = "ChangePassword", dataProviderClass = TestNGDataProvider.class)
	@Description("This testcase to verify tab order from Bottom to Top")
	public void verifyChangePwdTabOrderB2T(String userName, String password, String cancelButtonText,
			String saveButtonId, String confirmPasswordId, String newPasswordId, String oldPasswordId)
			throws Exception {
		try {
			logger.info("-----------------verifyChangePwdTabOrderB2T started----------------");

			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().verifyTabFocusBottomToTop(cancelButtonText, saveButtonId, confirmPasswordId,
					newPasswordId, oldPasswordId);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyChangePwdTabOrderB2T-Failed " + t.getMessage());
			Assert.fail("verifyChangePwdTabOrderB2T-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("-----------------verifyChangePwdTabOrderB2T Completed----------------");
	}

	@Feature("Change Password module")
	@Test(groups = { "regression" })
	@Description(" This test case to verify the error messages -TC_CP_004,TC_CP_006,TC_CP_006")
	public void verifyChangePasswordErrorMsg() throws Exception {
		try {
			logger.info("-----------------verifyChangePasswordErrorMsg started----------------");
			String[][] changePasswordData = excelMethods.readDataFromExcel(changePasswordFilePath,
					"verifyChangePasswordErrorMsg");
			int j = 0;
			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();

			for (int i = 0; i < changePasswordData.length; i++) {
				changePasswordPage().enterOldPassword(changePasswordData[i][j++])
						.enterNewPassword(changePasswordData[i][j++])
						.enterConfirmPassword(changePasswordData[i][j++])
						.clickOnSaveButton()
						.verifyErrorMessages(changePasswordData[i][j++]);
				j = 0;
				Thread.sleep(1000);
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyChangePasswordErrorMsg-Failed " + t.getMessage());
			Assert.fail("verifyChangePasswordErrorMsg-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("-----------------verifyChangePasswordErrorMsg Completed----------------");
	}

	@Feature("Change Password module")
	@Test(groups = { "ChangePassword",
			"regression" }, dataProvider = "ChangePassword", dataProviderClass = TestNGDataProvider.class)
	@Description("This testcase to to verify the functionality of Change Password when invalid old password is entered")
	public void verifyErrMsgForInvalidOldPwd(String oldPassword, String newPassword, String confirmPassword,
			String expectedErrorMessage) throws Exception {
		try {
			logger.info("-----------------verifyErrMsgForInvalidOldPwd started----------------");
			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().enterOldPassword(oldPassword)
					.enterNewPassword(newPassword)
					.enterConfirmPassword(confirmPassword)
					.clickOnSaveButton();
			Thread.sleep(1200);
			Assert.assertEquals(getDriver()
					.findElement(By.xpath("//div[@class='cimm_mainContentEnclosure']//div[contains(@class,'alert')]"))
					.getText()
					.trim(), expectedErrorMessage, "Error message is wrong.!");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyErrMsgForInvalidOldPwd-Failed " + t.getMessage());
			Assert.fail("verifyErrMsgForInvalidOldPwd-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		logger.info("-----------------verifyErrMsgForInvalidOldPwd completed----------------");
	}

}
