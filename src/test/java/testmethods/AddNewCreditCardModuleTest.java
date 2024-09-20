package testmethods;

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
import utility.MSExcelMethods;

public class AddNewCreditCardModuleTest extends PageInitializer {
	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	MSExcelMethods excelMethods = new MSExcelMethods();

	Logger log = Logger.getLogger(AddNewCreditCardModuleTest.class);
	
	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
		homePage().clickOnLogo();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogoutForAddNewCreditCard();
		logger.info("clicked on Logout link");
	}

	@Feature("Add New Credit Card Module")
	@Test(groups = { "AddNewCreditCardModule",
			"regression" }, dataProvider = "AddNewCreditCard", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify Add new Credit card page")
	public void verifyAddNewCreditCardPage(String creditCardLabelName, String breadCrumb, ITestContext context)
			throws Exception {
		log.info("---------------verifyAddNewCreditCardPage execution has started--------------");
		try {
			log = Logger.getLogger("verifyAddNewCreditCardPage");
			log.info("verifyAddNewCreditCardPage execution has started");
			// loginModule.loginForAddNewCreditCardModule("unilogsuperuser16@gmail.com",
			// "test1234");
			Thread.sleep(1200);
			productsPage().clickOnManageCreditCardLinkInLeftMenu();
			addNewCreditCardPage()
					.verifyAddNewCreditCardPageTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyAddNewCreditCardPage(creditCardLabelName)
					.verifyTheBreadCrumbs(breadCrumb);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAddNewCreditCardPage failed :" + t.getMessage());
			Assert.fail("verifyAddNewCreditCardPage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info(
				"---------------verifyAddNewCreditCardPage execution has been completed successfully--------------------");
	}

	@Feature("Add New Credit Card Module")
	@Test(groups = { "AddNewCreditCardModule", "regression" })
	@Description("To verify error message while adding Add new credit card")
	public void verifyErrorMessage() throws Exception {
		log.info("-------------------verifyErrorMessage execution has started--------------");
		try {
			log = Logger.getLogger("verifyErrorMessage");
			log.info("verifyErrorMessage execution has started");
			String[][] testData = excelMethods.readDataFromExcel("src/test/resources/TestDataFiles/AddNewCreditCard.xlsx",
					"verifyErrorMessage");
			// loginModule.loginForAddNewCreditCardModule("unilogsuperuser16@gmail.com",
			// "test1234");
			productsPage().clickOnManageCreditCardLinkInLeftMenu();
			int j = 0;
			for (int i = 0; i < testData.length; i++) {
				addNewCreditCardPage().enterNickName(testData[i][j++])
						.enterCardHolderName(testData[i][j++])
						.enterStreetAddress(testData[i][j++])
						.enterPostalCode(testData[i][j++])
						.clickOnAddNewCreditCardButton()
						.verifyErrorMessage(testData[i][j++]);
				j = 0;
			}
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyErrorMessage failed :" + t.getMessage());
			Assert.fail("verifyErrorMessage failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-------------verifyErrorMessage execution has been completed successfully---------------");
	}

	@Feature("Add New Credit Card Module")
	@Test(groups = { "AddNewCreditCardModule",
			"regression" }, dataProvider = "AddNewCreditCard", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify Product detail fields in list page like PN,UPC,Add to cart button etc.")
	public void verifyCreationAndDeletionOfCard(String nickName, String cardHolderName, String address,
			String postalCode, String cardNumber, String month, String year, String successMessage, String tableHeader,
			String detetionMessageOfCreditCard, ITestContext context) throws Exception {
		log.info("---------------verifyCreationAndDeletionOfCard execution has started-------------------");
		try {

			log = Logger.getLogger("verifyCreationAndDeletionOfCard");
			log.info("verifyCreationAndDeletionOfCard execution has started");
			// loginModule.loginForAddNewCreditCardModule("unilogsuperuser16@gmail.com",
			// "test1234");
			Thread.sleep(1200);
			productsPage().clickOnManageCreditCardLinkInLeftMenu();
			addNewCreditCardPage().enterNickName(nickName)
					.enterCardHolderName(cardHolderName)
					.enterStreetAddress(address)
					.enterPostalCode(postalCode)
					.clickOnAddNewCreditCardButton();
			Thread.sleep(1200);

			newCardAuthenticationPage()
					.verifyNewCardAuthenticationPageTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.switchInsideFrame()
					.enterCardNumber(cardNumber)
					.selectExpirationMonth(month)
					.selectExpirationYear(year)
					.clickOnAddToCardButton()
					.verifyNewlyAddedCreditCardSuccessMessage(successMessage)
					.swichBackToMainWindow();
			productsPage().clickOnManageCreditCardLinkInLeftMenu();
			addNewCreditCardPage().verifyCardInfoTableHeader(tableHeader)
					.verifyNewlyCreatedCreditCard(cardHolderName)
					.clickOnDeleteButtonOfCard(cardHolderName);
			commonOperations().verifyAlertTextPopUpForAddNewCreditCard(detetionMessageOfCreditCard)
					.clickOnOkButtonInAlertPopUpForAddNewCreditCard();
			addNewCreditCardPage().verifyDeletionOfCreditCard(cardHolderName);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyCreationAndDeletionOfCard failed :" + t.getMessage());
			Assert.fail("verifyCreationAndDeletionOfCard failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info(
				"------------------verifyCreationAndDeletionOfCard execution has been completed successfully---------------");
	}

	@Feature("Add New Credit Card Module")
	@Test(groups = { "AddNewCreditCardModule",
			"regression" }, dataProvider = "AddNewCreditCard", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify alert message when Invalid credit card expire date is entered")
	public void verifyAlertMessForInvalidCard(String nickName, String cardHolderName, String address, String postalCode,
			String cardNumber, String month, String year, String errorMessage, ITestContext context) throws Exception {
		log.info("--------------------verifyAlertMessForInvalidCard execution has started-----------------------");
		try {
			log = Logger.getLogger("verifyAlertMessForInvalidCard");
			log.info("verifyAlertMessForInvalidCard execution has started");
			// loginModule.loginForAddNewCreditCardModule("unilogsuperuser16@gmail.com",
			// "test1234");
			Thread.sleep(1200);
			productsPage().clickOnManageCreditCardLinkInLeftMenu();
			addNewCreditCardPage().enterNickName(nickName)
					.enterCardHolderName(cardHolderName)
					.enterStreetAddress(address)
					.enterPostalCode(postalCode)
					.clickOnAddNewCreditCardButton();
			Thread.sleep(1200);

			newCardAuthenticationPage()
					.verifyNewCardAuthenticationPageTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.switchInsideFrame()
					.enterCardNumber(cardNumber)
					.selectExpirationMonth(month)
					.selectExpirationYear(year)
					.clickOnAddToCardButton()
					.verifyErrorMessageForInvalidCreditCard(errorMessage)
					.swichBackToMainWindow();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAlertMessForInvalidCard failed :" + t.getMessage());
			Assert.fail("verifyAlertMessForInvalidCard failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info(
				"-------------------verifyAlertMessForInvalidCard execution has been completed successfully------------");
	}
}
