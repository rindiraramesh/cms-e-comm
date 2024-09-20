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
import io.qameta.allure.Issue;
import utilities.TestDataPropertyFile;

public class RequestForQuoteModule extends PageInitializer {

	Logger log = Logger.getLogger(RequestForQuoteModule.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogout();
	}

	@Feature("Request For Quote Module")
	@Test(enabled=false,groups = { "regression", "Smoke Test" })
	@Description("Verify RFQ page fields")
	public void verifyRfQpagefieldsPresence() throws Exception {
		log.info("-----------------verifyRfQpagefieldsPresence Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu().clickOnRequestForQuoteLink().verifyDisplayOfFields(
					data.getReqForQuoteTabelheader());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyRfQpagefieldsPresence failed :" + t.getMessage());
			Assert.fail("verifyRfQpagefieldsPresence failed :" + t.getMessage());
		}

		finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyRfQpagefieldsPresence Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression", "Smoke Test" })
	@Description("Verify RFQ page details")
	public void verifyRfQpageDetails(ITestContext context) throws Exception {
		log.info("-----------------verifyRfQpageDetails Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.verifyReqForQuoteBreadCrum(data.getBreadCrumReqForQuote())
					.verifyReqForQuotePageName()
					.verifyReqForQuoteTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyReqForQuoteUrl(context.getCurrentXmlTest().getParameter("URL").trim());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyRfQpageDetails failed :" + t.getMessage());
			Assert.fail("verifyRfQpageDetails failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyRfQpageDetails Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression" })
	@Description("Verify RFQ page pre populated mandatory fields")
	public void verifyRfQpagePrePopulatedMandatoryFields() throws Exception {
		log.info("-----------------verifyRfQpagePrePopulatedMandatoryFields Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu().clickOnRequestForQuoteLink().verifyMadatoryFieldsPopulated();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyRfQpagePrePopulatedMandatoryFields failed :" + t.getMessage());
			Assert.fail("verifyRfQpagePrePopulatedMandatoryFields failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyRfQpagePrePopulatedMandatoryFields Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression" })
	@Description("Verify RFQ page mandatory fields error message")
	public void verifyRfQpageMandatoryFieldsErrorMessages() throws Exception {
		log.info("-----------------verifyRfQpageMandatoryFieldsErrorMessages Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.enterPartNumber(data.getSearchTextForPN())
					.enterQty("1")
					.enterShortDescription("Automation Test")
					.enterUOM("EA")
					.updateMadatoryFields()
					.clearFirstName()
					.enterProductInfoAndQty(data.getSearchTextForPN())
					.clickSubmitQuoteRequestButton()
					.verifyErrorMessageMandatoryFields("Please Enter Name.")
					.enterFirstName("Test Automation")
					.clearPhone()
					.clickSubmitQuoteRequestButton()
					.verifyErrorMessageMandatoryFields("Please Enter Phone Number.")
					.enterPhone("5213697845")
					.clearEmail()
					.clickSubmitQuoteRequestButton()
					.verifyErrorMessageMandatoryFields("Please Enter Contact Email Address.")
					.enterEmail("test.automation@unilogcorp.com");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyRfQpageMandatoryFieldsErrorMessages failed :" + t.getMessage());
			Assert.fail("verifyRfQpageMandatoryFieldsErrorMessages failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyRfQpageMandatoryFieldsErrorMessages Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression" })
	@Description("Verify error message when no product information entered in tabel")
	public void verifyErrorMessagesWhenNoProductInformationEntered() throws Exception {
		log.info("-----------------verifyErrorMessagesWhenNoProductInformationEntered Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.updateMadatoryFields()
					.clickSubmitQuoteRequestButton()
					.verifyErrorForNoProductInformation(data.getErrorMessageWhenNoProductInformationRFQ())
					.updateMadatoryFields()
					.enterQty("2")
					.clickSubmitQuoteRequestButton()
					.verifyErrorForNoProductInformationAndQtyEntererd("Please Enter Atleast One Product Information.")
					.updateMadatoryFields()
					.enterQty("0")
					.enterPartNumber(data.getSearchTextForPN())
					.enterShortDescription("Test")
					.enterUOM("EA")
					.clickSubmitQuoteRequestButton()
					.verifyErrorForProductInformationEnteredAndQtyNotEntererd("Please Enter Valid Quantity for Item(s) from Line# 1.");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyErrorMessagesWhenNoProductInformationEntered failed :" + t.getMessage());
			Assert.fail("verifyErrorMessagesWhenNoProductInformationEntered failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyErrorMessagesWhenNoProductInformationEntered Completed--------------------");

	}

	// This is not working in template
	@Feature("Request For Quote Module")
	@Test(enabled = false, groups = { "regression", "Smoke Test" })
	@Description("Verify success message after quote submission")
	public void verifySuccessMessageAfterQuoteSubmission() throws Exception {
		log.info("-----------------verifySuccessMessageAfterQuoteSubmission Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.updateMadatoryFields()
					.enterPartNumber(data.getSearchTextForPN())
					.enterQty("1")
					.enterShortDescription("Automation Test")
					.clickSubmitQuoteRequestButton()
					/*.verifyRfQSubmissionMessage()
					.updateMadatoryFields()
					.enterManuPartNumber(data.getSearchTextForMPN())
					.clickSubmitQuoteRequestButton()
					.verifyRfQSubmissionMessage()
					.updateMadatoryFields()
					.enterBrandMfrName(data.getSearchTextForBrand())
					.clickSubmitQuoteRequestButton()
					.verifyRfQSubmissionMessage()*/;
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifySuccessMessageAfterQuoteSubmission failed :" + t.getMessage());
			Assert.fail("verifySuccessMessageAfterQuoteSubmission failed :" + t.getMessage());
		}

		finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifySuccessMessageAfterQuoteSubmission Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression" })
	@Description("Verify adding and removing rows in RFQ page table")
	public void verifyAddingRemovingRowInRequestForQuoteTabel() throws Exception {
		log.info("----------------- verifyAddingRemovingRowInRequestForQuoteTabel Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.verifyAddRowInRequestForQuoteTabel();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyAddingRemovingRowInRequestForQuoteTabel failed :" + t.getMessage());
			Assert.fail("verifyAddingRemovingRowInRequestForQuoteTabel failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyAddingRemovingRowInRequestForQuoteTabel Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression", "Smoke Test" })
	@Description("Verify Required date field")
	public void averifyRequiredByDateFieldFunction() throws Exception {
		log.info("-----------------averifyRequiredByDateFieldFunction Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu().clickOnRequestForQuoteLink().verifyRequiredByDateField();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("averifyRequiredByDateFieldFunction failed :" + t.getMessage());
			Assert.fail("averifyRequiredByDateFieldFunction failed :" + t.getMessage());
		}

		finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------averifyRequiredByDateFieldFunction Completed--------------------");

	}

	@Feature("Request For Quote Module")
	@Test(groups = { "regression" })
	@Description("Verify Qty field ")
	@Issue("Known issue")
	public void verifyQtyFieldValidation() throws Exception {
		log.info("----------------- verifyQtyFieldValidation Started--------------------");
		try {
			// loginModule.loginAsASuperUser();
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.verifyQtyFieldWithSpecialCharacters(data.getInputValuesForQtyfield());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyQtyFieldValidation failed :" + t.getMessage());
			Assert.fail("verifyQtyFieldValidation failed :" + t.getMessage());
		} finally {
			// homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyQtyFieldValidation Completed--------------------");
	}
}
