package testmethods;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class SmokeTests2 extends PageInitializer {

	Logger log = Logger.getLogger(SmokeTests2.class);

	TestDataPropertyFile data = new TestDataPropertyFile();
	
	@Feature("Compare Module")
	@Test(groups = { "CompareModule", "regression" })
	@Description("Verify compare page")
	public void verifyComparePage(ITestContext context) throws Exception {
		log.info("---------------verifyComparePage Started----------------");
		try {
			String pn1 = homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			String pn2 = productListPage().getSpecificPartNumber(2);
			String partNumbers[] = { pn1, pn2 };

			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.clickOnCompareLink()
					.comparePage()
					.verifyComparePageFields(data.getComparePageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.verifyPartNumbers(partNumbers);

			String pn_comparePage = comparePage().getSpecificPartNumber(1);
			comparePage().clickOnSpecficImage(1).verifyPartNumberInProductDetailsPage(pn_comparePage);

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifyComparePage failed:" + e.getMessage());
			Assert.fail("verifyComparePage failed:" + e.getMessage());
		} finally {
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnClearCompareButton()
					.homePage()
					.clickOnLogo();
		}
		log.info("---------------verifyComparePage Completed----------------");
	}

	@Feature("General Search")
	@Description("Verify Item Search By Part Number")
	@Test(groups = { "regression" })
	public void verifySearchByPartNumber() throws Exception {
		log.info("--------------- verifySearchByPartNumber Started----------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextForPN());
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifySearchByPartNumber failed:" + e.getMessage());
			Assert.fail("verifySearchByPartNumber failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("---------------verifySearchByPartNumber Completed----------------");
	}

	@Feature("General Search")
	@Description("Verify the narrow search for manufacturer part number.")
	@Test(groups = { "regression" })
	public void verifySearchFunctionality_narrowSearch_MPN() throws Throwable {
		log.info("---------------verifySearchFunctionality_narrowSearch_MPN Started----------------");
		try {
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.enterSearchTextInNarrowFilterTextbox(data.getSearchTextNarrowFilterTextboxForMPN())
					.clickOnNarrowSearchOrFilterSearchButton()
					.productDetailsPage()
					.verifyManufacturerPartNumberInProductDetailsPage(data.getSearchTextNarrowFilterTextboxForMPN());
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifySearchFunctionality_narrowSearch_MPN failed:" + e.getMessage());
			Assert.fail("verifySearchFunctionality_narrowSearch_MPN failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("---------------verifySearchFunctionality_narrowSearch_MPN Completed----------------");
	}

	@Feature("Login Module")
	@Test(groups = { "smoke" })
	@Description("Verify login function")
	public void loginDropdownValidTest() throws Exception {
		log.info("---------------loginDropdownValidTest Started----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.enterUserName()
					.enterPassword()
					.clickOnLoginButton()
					.homePage()
					.selectShippingAddress()
					.verifyWelcomeMsgAfterLogin();
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("loginDropdownValidTest failed:" + e.getMessage());
			Assert.fail("loginDropdownValidTest failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("---------------loginDropdownValidTest Completed----------------");
	}

	@Feature("Login Module")
	@Test(groups = { "smoke", "regression" })
	@Description("Verify login pop up function")
	public void loginPopUpValidTest() throws Exception {
		log.info("---------------loginPopUpValidTest Started----------------");
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
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("loginPopUpValidTest failed:" + e.getMessage());
			Assert.fail("loginPopUpValidTest failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("---------------loginPopUpValidTest Completed----------------");
	}

	@Feature("Registration")
	@Description("Verify new commercial account Registration page elements")
	@Test(groups = { "regression" })
	public void verifyCommercialUserRegistrationPage() throws Exception {
		log.info("--------------- verifyCommercialUserRegistrationPage Started----------------");
		try {
			homePage().clickLoginLink()
					.loginDropDown()
					.clickOnRegisterHere()
					.commnercialRegistrationPage()
					.clickOnNewCommercialAccount()
					.verifyRegistrationPageName(data.getRegistrationPageName())
					.verifyBreadCrumb(data.getRegistrationPageName())
					.verifyCommercialUserRegistrationTab();
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifyCommercialUserRegistrationPage failed:" + e.getMessage());
			Assert.fail("verifyCommercialUserRegistrationPage failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("---------------verifyCommercialUserRegistrationPage Completed----------------");
	}
	
	@Feature("Category filter")
	@Test(groups = { "ProductListAndGrid", "regression", "Smoke Test" })
	@Description("Verification of clear all functionality")
	public void verifyClearAllFunctionality(ITestContext context) throws Exception {
		log.info("----------------verifyClearAllFunctionality Started---------------- ");
		try {
			homePage().searchText("11").clickOnSearch();
			productListPage().changeToListView();
			productListPage().clickOnSpecificCheckBoxOfCategory(2);
			Thread.sleep(1200);
			productListPage().clickOnClearAllLink().verifyClearAllLinkFunction();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyClearAllFunctionality failed :" + t.getMessage());
			Assert.fail("verifyClearAllFunctionality failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyClearAllFunctionality Completed---------------- ");

	}

}