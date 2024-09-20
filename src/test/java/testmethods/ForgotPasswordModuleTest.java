package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class ForgotPasswordModuleTest extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	Logger logger = Logger.getLogger(ForgotPasswordModuleTest.class);

	@Feature("Forgot Password")
	@Description("Verify forgot password page")
	@Test(groups = { "ForgotPasswordModule", "regression", "Smoke Test" })
	public void verifyFPPage() throws Exception {
		logger.info("-------------verifyFPPage-Started--------- ");

		try {

			homePage().clickLoginLink().clickOnForgotYourPassword().verifyForgotPasswordPage();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyFPPage-Failed " + t.getMessage());
			Assert.fail("verifyFPPage-Failed " + t.getMessage());
		}
		logger.info("-------------verifyFPPage-Completed--------- ");
	}

	@Feature("Forgot Password")
	@Description("Verify forgot password error scenarios")
	@Test(groups = { "ForgotPasswordModule",
			"regression" }, dataProvider = "ForgotPassword", dataProviderClass = TestNGDataProvider.class)
	public void forgotPassword_ES(String emailId, String expectedErrorMessage) throws Exception {
		logger.info("-------------forgotPassword_ES-Started--------- ");
		try {

			homePage().clickLoginLink()
					.clickOnForgotYourPassword()
					.enterEmailId(emailId)
					.clickOnGetNewPassword()
					.verifyErrorMessage(expectedErrorMessage);

		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("forgotPassword_ES-Failed " + t.getMessage());
			Assert.fail("forgotPassword_ES-Failed " + t.getMessage());
		}
		logger.info("-------------forgotPassword_ES-Completed--------- ");

	}

	@Feature("Forgot Password")
	@Test(groups = { "ForgotPasswordModule",
			"regression" }, dataProvider = "ForgotPassword", dataProviderClass = TestNGDataProvider.class)
	@Description("Verification of forgot Password functionality by entering Non-Existing user email id - TC_ForgotPassword_002")
	public void forgotPassword_InvalidAccount(String emailId, String expectedErrorMessage) throws Exception {
		logger.info("-------------forgotPassword_InvalidAccount-Started--------- ");

		try {
			homePage().clickLoginLink()
					.clickOnForgotYourPassword()
					.enterEmailId(emailId)
					.clickOnGetNewPassword()
					.verifyErrorMessage_InvalidAccount(expectedErrorMessage);

		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("forgotPassword_InvalidAccount-Failed " + t.getMessage());
			Assert.fail("forgotPassword_InvalidAccount-Failed " + t.getMessage());
		}
		logger.info("-------------forgotPassword_InvalidAccount-Completed--------- ");

	}

	@Feature("Forgot Password")
	@Description("Verification of forgot Password functionality by entering Existing user email id. - TC_ForgotPassword_001")
	@Test(enabled=false,groups = { "ForgotPasswordModule", "regression",
			"Smoke Test" }, dataProvider = "ForgotPassword", dataProviderClass = TestNGDataProvider.class)
	public void forgotPasswordPositiveScenario(String emailId, String expectedSuccessMessage) throws Exception {
		logger.info("-------------forgotPasswordPositiveScenario-Started--------- ");

		try {
			homePage().clickLoginLink()
					.clickOnForgotYourPassword()
					.enterEmailId(emailId)
					.clickOnGetNewPassword()
					.verifySuccessMessage(emailId, expectedSuccessMessage);

		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("forgotPasswordPositiveScenario-Failed " + t.getMessage());
			Assert.fail("forgotPasswordPositiveScenario-Failed " + t.getMessage());
		}
		logger.info("-------------forgotPasswordPositiveScenario-Completed--------- ");
	}

	@Feature("Forgot Password")
	@Description("Verify bread crumbs")
	@Test(groups = { "ForgotPasswordModule", "regression" })
	public void verifyBreadcrumbAndHomeLinkFunctionality(ITestContext context) throws Exception {
		logger.info("-------------verifyBreadcrumbAndHomeLinkFunctionality-Started--------- ");
		try {
			homePage().clickLoginLink()
					.clickOnForgotYourPassword()
					.verifyBreadCrumbsOfForgotPage(data.getForgotPageBreadCrumb())
					.clickOnHomeLinkBreadCrumb()
					.verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company"));

		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			logger.info("verifyBreadcrumbAndHomeLinkFunctionality-Failed " + t.getMessage());
			Assert.fail("verifyBreadcrumbAndHomeLinkFunctionality-Failed " + t.getMessage());
		}
		logger.info("-------------verifyBreadcrumbAndHomeLinkFunctionality-Completed--------- ");
	}

}
