package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class AddRemoveCPNTest extends PageInitializer {

	Logger log = Logger.getLogger(AddRemoveCPNTest.class);

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();
	
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
	
	

	@Feature("Add/Remove CPN Test")
	@Description("To verify adding new part number for an item.")
	@Test(groups = { "Add/Remove CPN Test", "regression", "Smoke Test" })
	public void a_verifyAddingNewPartNumber() throws Exception {

		log.info("-------------------a_verifyAddingNewPartNumber started---------------------");
		try {
			
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			//addRemoveCPN().removeAllCPN();
			addRemoveCPN().clickOnAddRemoveCPNLink()
					.verifyfieldsonClickingCustomerPartNumber()
					.enterNewCustomerPartNumber(data.getSearchTextForCPN())
					.clickAddButton()
					.verifySuccessMessage()
					.clickOnAddRemoveCPNLink()
					.verifyAddedCPN(data.getSearchTextForCPN());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("a_verifyAddingNewPartNumber failed " + t.getMessage());
			Assert.fail("a_verifyAddingNewPartNumber failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}

		log.info("---------------a_verifyAddingNewPartNumber completed--------------------");
	}

	@Feature("Add/Remove CPN Test")
	@Description("To verify error message while adding a duplicate part number for an item.")
	@Test(groups = { "Add/Remove CPN Test", "regression" })
	public void b_verifyErrorMessageAddingNewPartNumber() throws Exception {
		log.info("----------------b_verifyErrorMessageAddingNewPartNumber test started------------------");
		try {
			// 
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			addRemoveCPN().clickOnAddRemoveCPNLink()
					.enterNewCustomerPartNumber(data.getSearchTextForCPN())
					.clickAddButton()
					.verifyDuplicateMessage();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("b_verifyErrorMessageAddingNewPartNumber failed " + t.getMessage());
			Assert.fail("b_verifyErrorMessageAddingNewPartNumber failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("------------b_verifyErrorMessageAddingNewPartNumber test completed-----------------");
	}

	@Feature("Add/Remove CPN Test")
	@Description("To verify updating existing part number for an item.")
	@Test(groups = { "Add/Remove CPN Test", "regression" })
	public void c_verifyUpdatingExistingPartNumber() throws Throwable {
		log.info("-----------c_verifyUpdatingExistingPartNumber test started-------------");
		try {
			// 
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			addRemoveCPN().clickOnAddRemoveCPNLink()
					.selectCPN(data.getSearchTextForCPN())
					.enterNewCustomerPartNumber(data.getSearchTextForCPN() + "Updated")
					.clickUpdateButton()
					.verifySuccessMessage();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("c_verifyUpdatingExistingPartNumber failed " + t.getMessage());
			Assert.fail("c_verifyUpdatingExistingPartNumber failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("---------------c_verifyUpdatingExistingPartNumber test Completed-------------");
	}

	@Feature("Add/Remove CPN Test")
	@Description("To verify removing part number for an item.")
	@Test(groups = { "Add/Remove CPN Test", "regression", "Smoke Test" })
	public void d_verifyRemovingExistingPartNumber() throws Exception {
		log.info("--------------d_verifyRemovingExistingPartNumber test started--------------");
		try {
			// 
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			addRemoveCPN().clickOnAddRemoveCPNLink()
					.selectCPN(data.getSearchTextForCPN())
					.clickRemoveButton()
					.verifySuccessMessage();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("d_verifyRemovingExistingPartNumber failed " + t.getMessage());
			Assert.fail("d_verifyRemovingExistingPartNumber failed " + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("------------d_verifyRemovingExistingPartNumber test completed----------");
	}
}
