package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class SalesPersonModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(SalesPersonModuleTest.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	@Feature("Sales Person Module")
	@Description("Verifying user with sales admin role can login as any user in the site.")
	@Test(groups = { "SalesPersonModule", "regression" })
	public void verifyUserWithSalesAdminRole() throws Exception {
		log.info("-------------- verifyUserWithSalesRepRole Started -----------------");

		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName(data.getSalesAdminCredentialForSalesPersonModule())
					.enterPassword(data.getPasswordForWhichCartIsShared())
					.clickOnLoginButton();
			Thread.sleep(1200);
			homePage().verifyTableHeaderOfEcommSalesAdminAndRepRole(data.getTableHeaderOfSalesAdminRole())
					.verifyListOfCustomerForSalesAdminAndRep()
					.selectCustomerFromList()
					.verifyUserUnderTheSelectedCustomer(data.getTableHeaderOfUserList());
			String userFirstname = homePage().getSpecificUserFirstName(2);
			homePage().selectSpecificUser(1).verifySalesAdminWelcomeMessage().verifyUserWelcomeMessage(userFirstname);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserWithSalesRepRole failed :" + t.getMessage());
			Assert.fail("verifyUserWithSalesRepRole failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-------------- verifyUserWithSalesRepRole Completed -----------------");
	}

	@Feature("Sales Person Module")
	@Description("Verifying user with sales Rep role can login as any user in the site.")
	@Test(groups = { "SalesPersonModule", "regression" })
	public void verifyUserWithSalesRepRole() throws Exception {
		log.info("-------------- verifyUserWithSalesRepRole Started -----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName(data.getSalesRepCredentialForSalesPersonModule())
					.enterPassword(data.getPasswordForSalesRepCredential())
					.clickOnLoginButton();
			Thread.sleep(1200);
			String userFirstname = homePage().selectCustomerFromList()
					.verifyUserUnderTheSelectedCustomer(data.getTableHeaderOfUserList())
					.getSpecificUserFirstName(1);
			homePage().selectSpecificUser(1).verifySalesAdminWelcomeMessage().verifyUserWelcomeMessage(userFirstname);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserWithSalesRepRole failed :" + t.getMessage());
			Assert.fail("verifyUserWithSalesRepRole failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-------------- verifyUserWithSalesRepRole Completed -----------------");
	}

	@Feature("Sales Person Module")
	@Description("Verify Message  when no users are assigne to selected customer for  sales rep.")
	@Test(groups = { "SalesPersonModule", "regression" })
	public void verifyMessageWhenNoCustomerAssignToSalesRep(ITestContext context) throws Exception {
		log.info("-------------- verifyMessageWhenNoCustomerAssignToSalesRep Started -----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName(data.getSalesRepCredentialForNoCustomerAssign())
					.enterPassword(data.getPasswordForWhichCartIsShared())
					.clickOnLoginButton();
			homePage().verifyNoCustomerAssignMessage(data.getMessageForNoCustomerAssignToSalesRep())
					.clickOnExitButton();
			homePage().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyMessageWhenNoCustomerAssignToSalesRep failed :" + t.getMessage());
			Assert.fail("verifyMessageWhenNoCustomerAssignToSalesRep failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-------------- verifyMessageWhenNoCustomerAssignToSalesRep Completed -----------------");
	}

}
