package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestNGDataProvider;

public class AccountFinancialInfoModuleTest extends PageInitializer {

	LoginModuleTest loginModule = new LoginModuleTest();

	Logger log = Logger.getLogger(AccountFinancialInfoModuleTest.class);

	@Feature("Account Financial Info Module")
	@Test(groups = { "AccountFinancialInfoModule",
			"regression" }, dataProvider = "MultipleTestData", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify Account Financial Info page.")
	public void verifyAccountInfoPage(String accountBalanceTableHeader, String openBalanceTableHeader,
			ITestContext context) throws Exception {
		log.info("-----------------------verifyAccountInfoPage execution has started---------------------");
		try {

			log = Logger.getLogger("verifyAccountInfoPage");
			log.info("verifyAccountInfoPage execution has started");
			loginModule.loginAsASuperUser();
			// loginModule.loginForAccountFinancialInfoModule("Harisha.mr@unilogcorp.com",
			// "Test12345");
			Thread.sleep(1200);

			homePage().clickOnMyAccountMenuDropdown()
					.clickOnAccountInquiryLink()
					.accountFinancialInfoPage()
					.verifyAccountFinancialInfoPageName()
					.verifyAccountFinancialInfoPageTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyAccountBalanceTabAndTableHeader(accountBalanceTableHeader)
					.verifyOpenBalanceTabAndTableHeader(openBalanceTableHeader);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAccountInfoPage failed :" + t.getMessage());
			Assert.fail("verifyAccountInfoPage failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogout();
		}
		log.info("-----------verifyAccountInfoPage execution has been completed successfully-----------------");
	}
}
