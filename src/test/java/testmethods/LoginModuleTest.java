package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import utilities.TestDataPropertyFile;
import utility.MSExcelMethods;

public class LoginModuleTest extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	String loginFilePath = "src/test/resources/TestDataFiles/Login.xlsx";

	Logger log = Logger.getLogger(LoginModuleTest.class);

	MSExcelMethods excelMethods = new MSExcelMethods();

	public void loginAsASuperUser() throws Exception {
		logger = Logger.getLogger("loginAsASuperUser");
		homePage().clickLoginLink()
				.loginDropDown()
				.enterUserName()
				.enterPassword()
				.clickOnLoginButton()
				.homePage()
				.selectShippingAddress()
				.verifyWelcomeMsgAfterLogin();
				//.clickacceptcookies(loginFilePath);
	}

	
	
	public void login(String userName, String password) throws Exception {
		homePage().clickLoginLink()
				.loginDropDown()
				.enterUserName(userName)
				.enterPassword(password)
				.clickOnLoginButton();
		homePage().selectShippingAddress().verifyWelcomeMsgAfterLogin();

	}

	public void loginForAccountFinancialInfoModule(String userName, String password) throws Exception {
		homePage().clickLoginLink()
				.loginDropDown()
				.enterUserName(userName)
				.enterPassword(password)
				// .clickOnLoginButton()
				.clickOnLoginButtonForAccountFinancialInfoModule()
				.homePage()
				.selectShippingAddress()
				.verifyWelcomeMsgAfterLogin();
	}

	public void loginForAddNewCreditCardModule(String userName, String password) throws Exception {
		homePage().clickLoginLinkForNewCreditCart()
				.loginPage()
				.enterUserName(userName)
				.enterPassword(password)
				.clickOnLoginButtonForAddNewCreditCard()
				.homePage()
				.selectShippingAddressForCreditCardModule();
	}

	@Feature("Login Module")
	@Test(groups = { "regression" })
	@Description("Verify login dropdown fields")
	@TmsLink("TestCase101")
	public void loginDropdownContentVerify() throws Exception {
		log.info("------------loginDropdownContentVerify started----------------------------");
		try {
			homePage().clickLoginLink().loginDropDown().verifyLoginDropDown();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginDropdownContentVerify failed " + t.getMessage());
			Assert.fail("loginDropdownContentVerify-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
			log.info("-------------------loginDropdownContentVerify Completed------------------");
		}
	}

	@Feature("Login Module")
	@Test(groups = { "Smoke Test", "regression" })
	@Description("Verify login scenario - TC_Login_001")
	public void loginDropdownValidTest() throws Exception {
		log.info("-----------------loginDropdownValidTest Started---------------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName()
					.enterPassword()
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginDropdownValidTest failed " + t.getMessage());
			Assert.fail("loginDropdownValidTest-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();
		}
		log.info("-------------------------------loginDropdownValidTest Completed----------------------");
	}

	@Feature("Login Module")
	@Test(groups = { "regression" })
	@Description("Verify login by pressing ENTER key")
	public void loginDropdownValidHitEnter() throws Exception {
		log.info("---------------------loginDropdownValidHitEnter Started----------------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName()
					.enterPassword()
					.hitEnter()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginDropdownValidHitEnter failed " + t.getMessage());
			Assert.fail("loginDropdownValidHitEnter-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();
		}
		log.info("----------------------loginDropdownValidHitEnter Completed----------------------------");
	}

	@Feature("Login Module")
	@Test(groups = { "regression" })
	@Description("Verify error scenarios while login - TC_Login_002, TC_Login_003")
	public void loginDropdown_ErrorScenarios() throws Exception {
		log.info("----------------------------loginDropdown_ErrorScenarios Started---------------------");

		try {
			String[][] loginTestData = excelMethods.readDataFromExcel(loginFilePath, "loginDropdown_ErrorScenarios");
			int j = 0;

			homePage().clickLoginLink();

			for (int i = 0; i < loginTestData.length; i++) {
				loginDropDown().enterUserName(loginTestData[i][j++])
						.enterPassword(loginTestData[i][j++])
						.clickOnLoginButton()
						.verifyErrorMessages(loginTestData[i][j++]);
				j = 0;
				Thread.sleep(500);
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginDropdown_ErrorScenarios failed " + t.getMessage());
			Assert.fail("loginDropdown_ErrorScenarios-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------------------loginDropdown_ErrorScenarios Completed---------------------");
	}

	@Feature("Login Module")
	@Test(groups = { "regression" })
	@Description("Verify remember me functionality -TC_Login_004,TC_Login_005")
	public void loginDropdownRememberMe() throws Exception {
		log.info("----------------------------loginDropdownRememberMe Started---------------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName()
					.enterPassword()
					.clickOnRememberMe()
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin()
					.clickOnLogout();

			homePage().clickLoginLink()
					.loginDropDown()
					.verifyAutofillOfUserNameAndPassword(data.getUserName(), data.getPassword())
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin()
					.clickOnLogout();

			homePage().clickLoginLink().loginDropDown().clickOnRememberMe().clickOnLoginButton();

			homePage().selectShippingAddress()
					.verifyWelcomeMsgAfterLogin()
					.clickOnLogout()
					.homePage()
					.clickLoginLink()
					.verifyEmptyUserNameAndPasswordTextbox();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginDropdownRememberMe failed " + t.getMessage());
			Assert.fail("loginDropdownRememberMe-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------------loginDropdownRememberMe Completed---------------------");
	}

	@Feature("Login Module")
	@Description("Verify signup link in login dropdown")
	@Test(groups = { "regression" })
	public void verifySignUpLinkInLoginDropdown() throws Exception {
		log.info("--------------------verifySignUpLinkInLoginDropdown Started----------------------");
		try {
			homePage().clickLoginLink().loginDropDown().clickOnSignUpLink().verifyPageName();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySignUpLinkInLoginDropdown failed " + t.getMessage());
			Assert.fail("verifySignUpLinkInLoginDropdown-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("--------------------verifySignUpLinkInLoginDropdown Completed----------------------");
	}

	@Feature("Login Module")
	@Description("Verify forget password link in Login dropdown")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyForgotPasswordLinkInLoginDropdown() throws Exception {
		log.info("--------------------verifyForgotPasswordLinkInLoginDropdown Started----------------------");
		try {
			homePage().clickLoginLink().loginDropDown().clickOnForgotYourPassword().verifyPageName();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyForgotPasswordLinkInLoginDropdown failed " + t.getMessage());
			Assert.fail("verifyForgotPasswordLinkInLoginDropdown-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("--------------------verifyForgotPasswordLinkInLoginDropdown Completed----------------------");
	}

	@Feature("Login Module")
	@Description("Verify login popup content")
	@Test(groups = { "LoginModule", "regression" })
	public void loginPopUpContentVerify() throws Exception {
		log.info("--------------------loginPopUpContentVerify Started----------------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.verifyLoginPopUp();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginPopUpContentVerify failed " + t.getMessage());
			Assert.fail("loginPopUpContentVerify-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("--------------------loginPopUpContentVerify Completed----------------------");
	}

	@Feature("Login Module")
	@Description("Verify login functionality")
	@Test(groups = { "Smoke Test", "regression" })
	public void loginPopUpValidTest() throws Exception {
		log.info("--------------------loginPopUpValidTest Started----------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.enterUserName()
					.enterPassword()
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginPopUpValidTest failed " + t.getMessage());
			Assert.fail("loginPopUpValidTest-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();
		}
		log.info("--------------------loginPopUpValidTest Completed----------------");
	}

	@Feature("Login Module")
	@Description("Verify login functionality using Enter")
	@Test(groups = { "regression" })
	public void loginPopUpValidHitEnter() throws Exception {
		log.info("--------------------loginPopUpValidHitEnter Started----------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.enterUserName()
					.enterPassword()
					.hitEnter()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginPopUpValidHitEnter failed " + t.getMessage());
			Assert.fail("loginPopUpValidHitEnter-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogout();
		}
		log.info("--------------------loginPopUpValidHitEnter Completed----------------");

	}

	@Feature("Login Module")
	@Description("Verify error scenarios in Login pop up")
	@Test(groups = { "regression" })
	public void loginPopUp_ErrorScenarios() throws InterruptedException, Exception {
		log.info("--------------------loginPopUp_ErrorScenarios Started----------------");
		try {
			String[][] loginTestData = excelMethods.readDataFromExcel(loginFilePath, "loginPopUp_ErrorScenarios");
			int j = 0;
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin();

			for (int i = 0; i < loginTestData.length; i++) {
				loginPopUp().enterUserName(loginTestData[i][j++])
						.enterPassword(loginTestData[i][j++])
						.clickOnLoginButton()
						.verifyErrorMessages(loginTestData[i][j++]);
				j = 0;
				Thread.sleep(500);
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginPopUp_ErrorScenarios failed " + t.getMessage());
			Assert.fail("loginPopUp_ErrorScenarios-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("--------------------loginPopUp_ErrorScenarios Completed----------------");
	}

	@Feature("Login Module")
	@Description("Verify remember me functionality")
	@Test(groups = { "LoginModule", "regression" })
	public void rememberMe_LoginPopUp() throws Exception {
		log.info("------------------------rememberMe_LoginPopUp Started---------------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.enterUserName()
					.enterPassword()
					.clickOnRememberMe()
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin()
					.clickOnLogout();

			homePage().clickLoginLink()
					.loginDropDown()
					.verifyAutofillOfUserNameAndPassword(data.getUserName(), data.getPassword())
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin()
					.clickOnLogout();

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.clickOnRememberMe()
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin()
					.clickOnLogout();

			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.verifyEmptyUserNameAndPasswordTextbox();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("rememberMe_LoginPopUp failed " + t.getMessage());
			Assert.fail("rememberMe_LoginPopUp-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("------------------------rememberMe_LoginPopUp Completed---------------------");
	}

	@Feature("Login Module")
	@Description("Verify signup link in login popup clicking CPN")
	@Test(groups = { "regression" })
	public void verifySignUpLinkInLoginPopup() throws Exception {
		log.info("-----------------verifySignUpLinkInLoginPopup started-------------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.clickOnSignUpLink()
					.verifyPageName();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySignUpLinkInLoginPopup failed " + t.getMessage());
			Assert.fail("verifySignUpLinkInLoginPopup-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifySignUpLinkInLoginPopup Completed-------------------");
	}

	@Feature("Login Module")
	@Description("Verify forget password link in Login popup after clicking CPN link")
	@Test(groups = { "regression" })
	public void verifyForgotPasswordLinkInLoginPopup() throws Exception {
		log.info("-----------------verifyForgotPasswordLinkInLoginPopup started-------------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumberBeforeLogin()
					.loginPopUp()
					.clickOnForgotYourPassword()
					.verifyPageName();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyForgotPasswordLinkInLoginPopup failed " + t.getMessage());
			Assert.fail("verifyForgotPasswordLinkInLoginPopup-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyForgotPasswordLinkInLoginPopup Completed-------------------");
	}

	// This feature is not there in template
	@Feature("Login Module")
	@Description("Verify login pop while checkout of the item")
	@Test(enabled = true, groups = { "Smoke Test", "regression" })
	public void loginPageValidTest() throws Exception {
		log.info("-----------------------loginPageValidTest Started------------------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButtonBeforeLogin()
					.myCartPage()
					.clickOnViewCartInMyCartPopupBeforeLogin()
					.clickOnCheckoutInMyCartPage()
					.loginPage()
					.enterUserName(data.getUserName())
					.enterPassword(data.getPassword())
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("loginPageValidTest failed " + t.getMessage());
			Assert.fail("loginPageValidTest-Failed " + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogout();
		}
		log.info("-----------------------loginPageValidTest Completed------------------------");
	}
}
