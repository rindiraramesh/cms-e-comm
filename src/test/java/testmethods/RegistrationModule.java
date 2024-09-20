package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class RegistrationModule extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	Logger log = Logger.getLogger(RegistrationModule.class);

	// On Account user registration

	@Feature("Registration")
	@Description("Verify on account registration page elements")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyOnAccountUserRegistrationPage() throws Exception {
		log.info("-------------- verifyOnAccountUserRegistrationPage Started -----------------");
		try {
			
			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.onAccountRegistrationPage()
					.verifyOnAccountRegistrationTab();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyOnAccountUserRegistrationPage failed :" + t.getMessage());
			Assert.fail("verifyOnAccountUserRegistrationPage-Failed " + t.getMessage());
		}
		log.info("-------------- verifyOnAccountUserRegistrationPage Completed -----------------");

	}

	@Feature("Registration")
	@Description("Verify On account Registration functionality")
	@Test(enabled = false, groups = {
			"regression" }, dataProvider = "Registration", dataProviderClass = TestNGDataProvider.class)
	public void verifyOnAccountRegistration(String accNo, String firstName, String lastName, String email,
			String password) throws Exception {
		log.info("-------------- verifyOnAccountUserRegistrationPage Started -----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.onAccountRegistrationPage()
					.enterAccountNumber(accNo)
					.enterFirstName(firstName)
					.enterLastName(lastName)
					.enterEmailId(email)
					.enterPassword(password)
					.enterConfirmPassword(password)
					.clickOnIAccept()
					.verifySuccessMsg();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyOnAccountUserRegistrationPage failed :" + t.getMessage());
			Assert.fail("verifyOnAccountRegistration-Failed " + t.getMessage());
		}
		log.info("-------------- verifyOnAccountUserRegistrationPage Completed -----------------");

	}

	@Feature("Registration")
	@Description("Verify On account registration error scenarios")
	@Test(groups = { "regression" }, dataProvider = "Registration", dataProviderClass = TestNGDataProvider.class)
	public void verifyOnAccountES(String accNo, String firstName, String lastName, String email, String password,
			String confirmPassword, String errorMsg) throws Exception {
		log.info("-------------- verifyOnAccountES Started -----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.onAccountRegistrationPage()
					.enterAccountNumber(accNo)
					.enterFirstName(firstName)
					.enterLastName(lastName)
					.enterEmailId(email)
					.enterPassword(password)
					.enterConfirmPassword(confirmPassword)
					.clickOnIAccept()
					.verifyErrorMsg(errorMsg);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyOnAccountES failed :" + t.getMessage());
			Assert.fail("verifyOnAccountES-Failed " + t.getMessage());
		}
		log.info("-------------- verifyOnAccountES Completed -----------------");

	}

	// Commercial user registration

	@Feature("Registration")
	@Description("Verify new commercial account Registration page elements")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyCommercialUserRegistrationPage() throws Exception {
		log.info("-------------- verifyCommercialUserRegistrationPage Started -----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.commnercialRegistrationPage()
					.clickOnNewCommercialAccount()
					.verifyRegistrationPageName(data.getRegistrationPageName())
					.verifyBreadCrumb(data.getRegistrationPageName())
					.verifyCommercialUserRegistrationTab();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserWithSalesRepRole failed :" + t.getMessage());
			Assert.fail("verifyCommercialUserRegistrationPage-Failed " + t.getMessage());
		}
		log.info("-------------- verifyCommercialUserRegistrationPage Completed -----------------");

	}

	@Feature("Registration")
	@Description("Verify New commercial account Registration functionality")
	@Test(enabled = false, groups = {
			"regression" }, dataProvider = "Registration", dataProviderClass = TestNGDataProvider.class)
	public void verifyUserRegistration(String firstName, String lastName, String company, String email, String password,
			String address1, String address2, String city, String state, String zipCode, String phone)
			throws Exception {
		log.info("-------------- verifyUserRegistration Started -----------------");
		try {

			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.commnercialRegistrationPage()
					.clickOnNewCommercialAccount();

			Thread.sleep(1000);

			commnercialRegistrationPage().enterFirstName(firstName)
					.enterLastName(lastName)
					.enterCompanyName(company)
					.enterEmailId(email)
					.enterPassword(password)
					.enterConfirmPassword(password)
					.enterAddress1(address1)
					.enterAddress2(address2)
					.enterCity(city)
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phone)
					.clickOnIAccept()
					.verifySuccessMsg();

			/*
			 * Thread.sleep(1000); homePage() .clickLoginLink() .loginDropDown()
			 * .enterUserName(email) .enterPassword(password) .clickOnLoginButton()
			 * .homePage() .verifyWelcomeMsgAfterLogin();
			 */
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserRegistration failed :" + t.getMessage());
			Assert.fail("verifyUserRegistration-Failed " + t.getMessage());
		}
		log.info("-------------- verifyUserRegistration Completed -----------------");

	}

	@Feature("Registration")
	@Description("Verify New commercial account registration error scenarios")
	@Test(groups = { "regression" }, dataProvider = "Registration", dataProviderClass = TestNGDataProvider.class)
	public void verifyErrorScenarios(String firstName, String lastName, String company, String email, String password,
			String confirmPassword, String address1, String city, String state, String zipCode, String phone,
			String errorMessage) throws Exception {
		log.info("-------------- verifyErrorScenarios Started -----------------");
		try {
			
			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.commnercialRegistrationPage()
					.clickOnNewCommercialAccount();
			Thread.sleep(1000);

			commnercialRegistrationPage().enterFirstName(firstName)
					.enterLastName(lastName)
					.enterCompanyName(company)
					.enterEmailId(email)
					.enterPassword(password)
					.enterConfirmPassword(confirmPassword)
					.enterAddress1(address1)
					.enterCity(city)
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phone)
					.clickOnIAccept()
					.verifyErrorMsg(errorMessage);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorScenarios failed :" + t.getMessage());
			Assert.fail("verifyErrorScenarios-Failed " + t.getMessage());
		}
		log.info("-------------- verifyErrorScenarios Completed -----------------");

	}

	// Retail user registration

	@Feature("Registration")
	@Description("Verify retail user registration page elements")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyRetailUserRegistrationPage() throws Exception {
		log.info("-------------- verifyRetailUserRegistrationPage Started -----------------");
		try {

			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.retailUserRegistrationPage()
					.clickOnRetailUserTab()
					.verifyNewRetailCustomerTab();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyRetailUserRegistrationPage failed :" + t.getMessage());
			Assert.fail("verifyRetailUserRegistrationPage-Failed " + t.getMessage());
		}
		log.info("-------------- verifyRetailUserRegistrationPage Completed -----------------");

	}

	@Feature("Registration")
	@Description("Verify New retail user account Registration functionality")
	@Test(enabled = false, groups = {
			"regression" }, dataProvider = "Registration", dataProviderClass = TestNGDataProvider.class)
	public void verifyUserRegistrationRU(String firstName, String lastName, String company, String email,
			String password, String address1, String address2, String city, String state, String zipCode, String phone)
			throws Exception {
		log.info("-------------- verifyUserRegistrationRU Started -----------------");
		try {

			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.retailUserRegistrationPage()
					.clickOnRetailUserTab();

			Thread.sleep(1000);

			retailUserRegistrationPage().enterFirstName(firstName)
					.enterLastName(lastName)
					.enterCompanyName(company)
					.enterEmailId(email)
					.enterPassword(password)
					.enterConfirmPassword(password)
					.enterAddress1(address1)
					.enterAddress2(address2)
					.enterCity(city)
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phone)
					.clickOnIAccept()
					.verifySuccessMsg();

			// Thread.sleep(1000);

			/*
			 * homePage() .clickLoginLink() .loginDropDown() .enterUserName(email)
			 * .enterPassword(password) .clickOnLoginButton() .homePage()
			 * .verifyWelcomeMsgAfterLogin();
			 */
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserRegistrationRU failed :" + t.getMessage());
			Assert.fail("verifyUserRegistrationRU-Failed " + t.getMessage());
		}
		log.info("-------------- verifyUserRegistrationRU Completed -----------------");

	}

	@Feature("Registration")
	@Description("Verify Error scenarios while regestring new retail user account Registration")
	@Test(groups = { "regression" }, dataProvider = "Registration", dataProviderClass = TestNGDataProvider.class)
	public void verifyUserRegistrationRU_ES(String firstName, String lastName, String company, String email,
			String password, String confirmPassword, String address1, String city, String state, String zipCode,
			String phone, String errorMsg) throws Exception {
		log.info("-------------- verifyUserRegistrationRU_ES Started -----------------");
		try {

			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.retailUserRegistrationPage()
					.clickOnRetailUserTab();

			Thread.sleep(1000);

			retailUserRegistrationPage().enterFirstName(firstName)
					.enterLastName(lastName)
					.enterCompanyName(company)
					.enterEmailId(email)
					.enterPassword(password)
					.enterConfirmPassword(confirmPassword)
					.enterAddress1(address1)
					.enterCity(city)
					.selectState(state)
					.enterZipCode(zipCode)
					.enterPhoneNumber(phone)
					.clickOnIAccept()
					.verifyErrorMsg(errorMsg);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserRegistrationRU_ES failed :" + t.getMessage());
			Assert.fail("verifyUserRegistrationRU_ES-Failed " + t.getMessage());
		}
		log.info("-------------- verifyUserRegistrationRU_ES Completed -----------------");
	}

}