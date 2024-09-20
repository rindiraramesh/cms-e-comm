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
import utility.MSExcelMethods;

public class SendThisPageModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(SendThisPageModuleTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	MSExcelMethods excelMethods = new MSExcelMethods();

	@Feature("Send This Page Module")
	@Description("Verify Send this page after login.")
	@Test(groups = { "SendThisPageModule", "regression",
			 "Smoke Test" }, dataProvider = "SendThisPage", dataProviderClass = TestNGDataProvider.class)
	public void verifySendThisPage(String mandatoryFieldsOfShareThisPage, ITestContext context) throws Exception {
		log.info("-------------- verifySendThisPage Started -----------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			loginModule.loginAsASuperUser();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfNameOfTheProduct()
					.verifyDisplayOfPartNumber()
					.verifyDisplayOfShareLink()
					.verifyDisplayOfSendLink();
			String partNumber = productDetailsPage().getPartNumber();
			productDetailsPage().clickOnSendThisPageLink();

			sharePage().verifyShareThisPageBreadCrumb(data.getShareThisPageBreadCrumb())
					.verifyMandatoryFieldOfShareThisPage(mandatoryFieldsOfShareThisPage);
			sharePage().verifyItemsInShareThisPage(partNumber);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("VerifySendThisPage failed :" + t.getMessage());
			Assert.fail("VerifySendThisPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("--------------verifySendThisPage Completed -----------------");
	}

	@Feature("Send This Page Module")
	@Description("Verify Send this page before login.")
	@Test(groups = { "SendThisPageModule", "regression",
			 }, dataProvider = "SendThisPage", dataProviderClass = TestNGDataProvider.class)
	public void verifySendThisPageBeforeLogin(String mandatoryFieldsOfShareThisPage, ITestContext context)
			throws Exception {
		log.info("--------------verifySendThisPageBeforeLogin Started -----------------");

		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfNameOfTheProduct()
					.verifyDisplayOfPartNumber()
					.verifyDisplayOfShareLink()
					.verifyDisplayOfSendLink();
			String partNumber = productDetailsPage().getPartNumber();
			productDetailsPage().clickOnSendThisPageLink();
			sharePage().verifyShareThisPageBreadCrumb(data.getShareThisPageBreadCrumb())
					.verifyMandatoryFieldOfShareThisPage(mandatoryFieldsOfShareThisPage);
					//.verifyTextBoxToEnterCaptha();
			sharePage().verifyItemsInShareThisPage(partNumber);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySendThisPageBeforeLogin failed :" + t.getMessage());
			Assert.fail("verifySendThisPageBeforeLogin failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("--------------verifySendThisPageBeforeLogin Completed -----------------");
	}

	@Feature("Send This Page Module")
	@Description("Verify alert messages without entering mandatory fields after login.")
	@Test(groups = { "SendThisPageModule", "regression" })
	public void verifyErrorMessage(ITestContext context) throws Exception {
		log.info("--------------verifyErrorMessage Started -----------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/SendThisPage.xlsx",
					"verifyErrorMessage");
			loginModule.loginAsASuperUser();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnSendThisPageLink();
			int j = 0;
			for (int i = 0; i < testData.length; i++) {
				sharePage().enterFriendName(testData[i][j++])
						.enterFriendEmailAddress(testData[i][j++])
						.enterFromName(testData[i][j++])
						.enterFromEmailAddress(testData[i][j++])
						.enterSubject(testData[i][j++])
						.clickOnSend()
						.verifyErrorMessage(testData[i][j++]);
				j = 0;
			}
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyErrorMessage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessage failed :" + t.getMessage());

		}

		finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-------------- verifyErrorMessage Completed -----------------");

	}

	@Feature("Send This Page Module")
	@Description("Verify alert messages without entering mandatory fields before login.")
	@Test(groups = { "SendThisPageModule", "regression" })
	public void verifyErrorBeforLoginMessage(ITestContext context) throws Exception {
		log.info("-------------- verifyErrorBeforLoginMessage Started -----------------");
		try {
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/SendThisPage.xlsx",
					"verifyErrorBeforLoginMessage");

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnSendThisPageLink();

			int j = 0;
			for (int i = 0; i < testData.length; i++) {
				sharePage().enterFriendName(testData[i][j++])
						.enterFriendEmailAddress(testData[i][j++])
						.enterFromName(testData[i][j++])
						.enterFromEmailAddress(testData[i][j++])
						.enterSubject(testData[i][j++])
						.clickOnSend()
						.verifyErrorMessage(testData[i][j++]);
				j = 0;
			}
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyErrorBeforLoginMessage failed :" + t.getMessage());
			Assert.fail("verifyErrorBeforLoginMessage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------- verifyErrorBeforLoginMessage Completed -----------------");

	}

}
