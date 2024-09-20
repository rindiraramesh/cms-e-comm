package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class AccountSetup extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	Logger log = Logger.getLogger(AccountSetup.class);

	String userName = "test.automation@unilogcorp.com";
	String passWord = "Test@1234";

	// Commercial user registration

	@Test
	public void accountSetup() throws Throwable {
		verifyUserRegistration();
		checkLogin();
		addShippingAddress();
		homePage().clickOnLogout();
	}

	public void verifyUserRegistration() throws Exception {
		log.info("-------------- verifyUserRegistration Started -----------------");
		try {

			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.commnercialRegistrationPage()
					.clickOnNewCommercialAccount();

			commnercialRegistrationPage().enterFirstName("Test")
					.enterLastName("Automation")
					.enterCompanyName("UnilogQA")
					.enterEmailId(userName)
					.enterPassword(passWord)
					.enterConfirmPassword(passWord)
					.enterAddress1("78")
					.enterAddress2("2nd street")
					.enterCity("Wayne")
					.selectState("Ohio")
					.enterZipCode("19087")
					.enterPhoneNumber("5123587822")
					.clickOnIAccept()
					.verifySuccessMsg();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUserRegistration failed :" + t.getMessage());
			Assert.fail("verifyUserRegistration-Failed " + t.getMessage());
		}
		log.info("-------------- verifyUserRegistration Completed -----------------");

	}

	public void checkLogin() throws Throwable {
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName(userName)
					.enterPassword(passWord)
					.clickOnLoginButton()
					.homePage()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("checkLogin failed :" + t.getMessage());
			Assert.fail("checkLogin-Failed " + t.getMessage());
		}
	}

	public void addShippingAddress() throws Throwable {
		try {
			homePage().clickOnMyAccountMenuDropdown()
					.clickOnMyAccount()
					.myAccountsPage()
					.clickOnAddressTab()
					.clickOnShippingAddressEditIcon();
			userInformationPage().clickOnAddShippingAddressButton();
			editContactInfoPage().enterFirstName_AddNewShippingAddress("Test")
					.enterLastName_AddNewShippingAddress("Automation")
					.enterAddress1_AddNewShippingAddress("1983")
					.enterAddress2_AddNewShippingAddress("2nd St")
					.enterCity_AddNewShippingAddress("Cincinnati")
					.enterState_AddNewShippingAddress("Alaska")
					.enterZipCode_AddNewShippingAddress("45202")
					.enterPhoneNumber_AddNewShippingAddress("15253258796")
					.enterEmail_AddNewShippingAddress(userName)
					.clickOnUpdateButton();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("addShippingAddress failed :" + t.getMessage());
			Assert.fail("addShippingAddress-Failed " + t.getMessage());
		}
	}

	// On Account user registration

	@Feature("Registration")
	@Description("Verify on account registration page elements")
	@Test(groups = { "setup" }, enabled = false)
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

	// Retail user registration

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
}