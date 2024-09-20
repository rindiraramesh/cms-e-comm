package testmethods;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class GeneralSearch extends PageInitializer {

	Logger logger = Logger.getLogger(GeneralSearch.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModuleTest = new LoginModuleTest();

	@Feature("Narrow Search")
	@Description("Verify the placeholder and search button of the search field.")
	@Test(groups = { "regression" })
	public void verifySearchPlaceHolderAndGoButton() throws InterruptedException {
		try {
			logger.info("----------verifySearchPlaceHolderAndGoButton Started------------");
			homePage().verifyPlaceHolderOfSearchTextbox(data.getExpectedSearchTexboxPlaceholder())
					.verifyDisplayOfSearchTextboxAndButton();

			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchPlaceHolderAndGoButton failed " + t.getMessage());
			Assert.fail("verifySearchPlaceHolderAndGoButton-Failed " + t.getMessage());
		}
		logger.info("----------verifySearchPlaceHolderAndGoButton Completed------------");
	}

	@Feature("General Search")
	@Description("Verify Item Search By Part Number")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifySearchByPartNumber() throws Exception {
		try {
			logger.info("-------verifySearchByPartNumber Started---------------------");
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextForPN());

			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchByPartNumber failed " + t.getMessage());
			Assert.fail("verifySearchByPartNumber-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchByPartNumber Completed---------------------");
	}

	@Feature("General Search")
	@Description("Verify Item Search By Manufacturer Part Number")
	@Test(groups = { "regression", })
	public void verifySearchByMPN() throws Exception {
		try {
			logger.info("------------verifySearchByMPN Started----------------------");
			homePage().searchText(data.getSearchTextForMPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyManufacturerPartNumberInProductDetailsPage(data.getSearchTextForMPN());

			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchByMPN failed " + t.getMessage());
			Assert.fail("verifySearchByMPN-Failed " + t.getMessage());
		}
		logger.info("------------verifySearchByMPN Completed----------------------");
	}

	@Feature("General Search")
	@Description("Verify searching of for UPC.")
	@Test(groups = { "regression", })
	public void verifySearchByUPC() throws Exception {
		try {
			logger.info("-------------verifySearchByUPC Started----------------------");
			homePage().searchText(data.getSearchTextForUPC())
					.clickOnSearch()
					.productDetailsPage()
					.verifyUPCInProductDetailsPage(data.getSearchTextForUPC());

			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchByUPC failed " + t.getMessage());
			Assert.fail("verifySearchByUPC-Failed " + t.getMessage());
		}
		logger.info("-------------verifySearchByUPC Completed----------------------");
	}

	@Feature("General Search")
	@Description("Verify Item Search By Customer Part Number")
	@Test(groups = { "regression" })
	public void verifySearchByCPN() throws Exception {
		try {
			logger.info("-------------verifySearchByCPN Started-------------");
			loginModuleTest.loginAsASuperUser();
			Thread.sleep(1200);
			homePage().searchText(data.getSearchTextForCPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyCPNInProductDetailsPage(data.getSearchTextForCPN());

		} catch (TimeoutException | NoSuchElementException e) {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumber()
					.enterCPN(data.getSearchTextForCPN());
			homePage().searchText(data.getSearchTextForCPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyCPNInProductDetailsPage(data.getSearchTextForCPN());
		} finally {
			homePage().clickOnLogout();
		}
		logger.info("-------------verifySearchByCPN Completed-------------");
	}

	@Feature("General Search")
	@Description("Verify Item Search By Brand")
	@Test(groups = { "regression" })
	public void verifySearchByBrand() throws Exception {
		logger.info("-------verifySearchByBrand Started------------------");
		
		

		Thread.sleep(1000);
		
		homePage()
				.searchText(data.getSearchTextForBrand1())

				.clickOnSearch()

				.productListPage()

				.verifyItemBrandInPLP(data.getSearchTextForBrand1());

		logger.info("-------verifySearchByBrand Completed------------------");
		homePage().clickOnLogo();
	}

	@Feature("General Search")
	@Description("Verify Item Search By Brand using auto suggest option")
	@Test(groups = { "regression" },enabled=false)
	public void verifySearchByBrand_AutoSuggest() throws Exception {
		try {
			logger.info("-------verifySearchByBrand_AutoSuggest Started------------------");
			homePage().searchText(data.getSearchTextForBrand())
					.selectBrandFromAutoSuggest(data.getSearchTextForBrand())
					.productListPage()
					.verifyItemBrandInPLP(data.getSearchTextForBrand());

			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchByBrand_AutoSuggest failed " + t.getMessage());
			Assert.fail("verifySearchByBrand_AutoSuggest-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchByBrand_AutoSuggest Completed------------------");
	}

	@Feature("General Search")
	@Description("Verify Item Search By Category using auto suggest 'More Result' option")
	@Test(enabled = false, groups = { "regression" })
	public void verifySearchByCategory_AutoSuggest() throws Exception {
		try {
			logger.info("-------verifySearchByCategory_AutoSuggest Started------------------");
			homePage()

					/* .searchText(data.getSearchText()) */

					.searchText("wire")

					.homePage()
					.selectMoreResultOptionFromAutoSuggest()
					.productListPage()
					.verifyItemCategoryInPLP(data.getSearchText());
			homePage().clickOnLogo();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchByCategory_AutoSuggest failed " + t.getMessage());
			Assert.fail("verifySearchByCategory_AutoSuggest-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchByCategory_AutoSuggest Completed------------------");
	}

	@Feature("General Search")
	@Description("Verify the alert message when clicking on go without providing any searchtext.")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyAlertMessage_NoSearchText() throws Exception {
		try {
			logger.info("-------verifyAlertMessage_NoSearchText Started------------------");
			homePage().clickOnSearch().verifyAlertMessage(data.getAlertMessageForSearch());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAlertMessage_NoSearchText failed " + t.getMessage());
			Assert.fail("verifyAlertMessage_NoSearchText-Failed " + t.getMessage());
		}
		logger.info("-------verifyAlertMessage_NoSearchText Completed------------------");
	}

	@Feature("General Search")
	@Description("Verify the text message that is displayed when search input is invalid.")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyInvalidSearch() throws Exception {
		logger.info("-------verifyInvalidSearch Started------------------");
		homePage().searchText("asflksjdflkjdslkf").clickOnSearch().verifyMessageForInvalidSearchData();
		logger.info("-------verifyInvalidSearch Completed------------------");
		homePage().clickOnLogo();
	}

	@Feature("Narrow Search")
	@Description("Verify the placeholder and search button of the narrow search field.")
	@Test(groups = { "regression" })
	public void verifySearchPlaceHolderAndSearchButton_NarrowSearch() throws Exception {
		try {
			logger.info("-------verifySearchPlaceHolderAndSearchButton_NarrowSearch Started------------------");
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().verifyDisplayOfSearchWithin();
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchPlaceHolderAndSearchButton_NarrowSearch failed " + t.getMessage());
			Assert.fail("verifySearchPlaceHolderAndSearchButton_NarrowSearch-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchPlaceHolderAndSearchButton_NarrowSearch Completed------------------");

	}

	@Feature("Narrow Search")
	@Description("Verify the narrow search for part number.")
	@Test(groups = { "regression" })
	public void verifySearchFunctionality_narrowSearch_partNumber() throws Throwable {
		try {
			logger.info("-------verifySearchFunctionality_narrowSearch_partNumber Started------------------");

			homePage().searchText(data.getSearchText1())
					.clickOnSearch()
					.productListPage()
					.enterSearchTextInNarrowFilterTextbox(data.getSearchTextNarrowFilterTextboxForPartNumber())
					.clickOnNarrowSearchOrFilterSearchButton()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextNarrowFilterTextboxForPartNumber());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchFunctionality_narrowSearch_partNumber failed " + t.getMessage());
			Assert.fail("verifySearchFunctionality_narrowSearch_partNumber-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchFunctionality_narrowSearch_partNumber Completed------------------");

	}

	@Feature("Narrow Search")
	@Description("Verify the narrow search for manufacturer part number.")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifySearchFunctionality_narrowSearch_MPN() throws Throwable {
		try {
			logger.info("-------verifySearchFunctionality_narrowSearch_MPN Started------------------");

			homePage().searchText(data.getSearchText1())
					.clickOnSearch()
					.productListPage()
					.enterSearchTextInNarrowFilterTextbox(data.getSearchTextNarrowFilterTextboxForMPN())
					.clickOnNarrowSearchOrFilterSearchButton()
					.productDetailsPage()
					.verifyManufacturerPartNumberInProductDetailsPage(data.getSearchTextNarrowFilterTextboxForMPN());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchFunctionality_narrowSearch_MPN failed " + t.getMessage());
			Assert.fail("verifySearchFunctionality_narrowSearch_MPN-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchFunctionality_narrowSearch_MPN Completed------------------");

	}

	@Feature("Narrow Search")
	@Description("Verify the narrow search for UPC.")
	@Test(groups = { "regression" })
	public void verifySearchFunctionality_narrowSearch_UPC() throws Throwable {
		try {
			logger.info("-------verifySearchFunctionality_narrowSearch_UPC Started------------------");

			homePage().searchText(data.getSearchText1())
					.clickOnSearch()
					.productListPage()
					.enterSearchTextInNarrowFilterTextbox(data.getSearchTextNarrowFilterTextboxForUPC())
					.clickOnNarrowSearchOrFilterSearchButton()
					.productDetailsPage()
					.verifyUPCInProductDetailsPage(data.getSearchTextNarrowFilterTextboxForUPC());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchFunctionality_narrowSearch_UPC failed " + t.getMessage());
			Assert.fail("verifySearchFunctionality_narrowSearch_UPC-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchFunctionality_narrowSearch_UPC Completed------------------");

	}

	@Feature("Narrow Search")
	@Description("Verify the narrow search for invalid item.")
	@Test(groups = { "regression" })
	public void verifySearchFunctionality_narrowSearch_Invalid() throws Throwable {
		try {
			logger.info("-------verifySearchFunctionality_narrowSearch_Invalid Started------------------");

			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.enterSearchTextInNarrowFilterTextbox("asdfasdfasdf")
					.clickOnNarrowSearchOrFilterSearchButton()
					.homePage()
					.verifyMessageForInvalidNarrowSearchData(data.getSearchText());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifySearchFunctionality_narrowSearch_Invalid failed " + t.getMessage());
			Assert.fail("verifySearchFunctionality_narrowSearch_Invalid-Failed " + t.getMessage());
		}
		logger.info("-------verifySearchFunctionality_narrowSearch_Invalid Started------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search page elements - TC_Advsearch_001")
	@Test(groups = { "regression", "Smoke Test" })
	public void verifyAdvanceSearchPage(ITestContext context) throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchPage Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.verifyAdvanceSearchPage(context.getCurrentXmlTest().getParameter("Company").trim(), data.getAdvanceSearchBreadcrumb());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchPage failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchPage-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchPage Started------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search by UPC - TC_Advsearch_004")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByUPC() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByUPC Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectUPC()
					.enterSearchText(data.getSearchTextForUPC())
					.clickOnSearchButton()
					.productDetailsPage()
					.verifyUPCInProductDetailsPage(data.getSearchTextForUPC());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByUPC failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByUPC-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByUPC Completed------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search for invalid UPC - TC_Advsearch_005")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByInvalidUPC() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByInvalidUPC Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectUPC()
					.enterSearchText("asdfsadfasd")
					.clickOnSearchButton()
					.homePage()
					.verifyMessageForInvalidSearchData();
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByInvalidUPC failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByInvalidUPC-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByInvalidUPC Completed------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search by MPN - TC_Advsearch_006")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByMPN() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByMPN Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectMPN()
					.enterSearchText(data.getSearchTextForMPN())
					.clickOnSearchButton()
					.productDetailsPage()
					.verifyManufacturerPartNumberInProductDetailsPage(data.getSearchTextForMPN());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByMPN failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByMPN-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByMPN Completed------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search for invalid MPN - TC_Advsearch_007")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByInvalidMPN() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByInvalidMPN Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectMPN()
					.enterSearchText("asdfsadfasd")
					.clickOnSearchButton()
					.homePage()
					.verifyMessageForInvalidSearchData();
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByInvalidMPN failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByInvalidMPN-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByInvalidMPN Completed------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search by CPN - TC_Advsearch_002")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByCPN() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByCPN Started------------------");

			loginModuleTest.loginAsASuperUser();
			Thread.sleep(1500);
			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectCPN()
					.enterSearchText(data.getSearchTextForCPN())
					.clickOnSearchButton()
					.productDetailsPage()
					.verifyCPNInProductDetailsPage(data.getSearchTextForCPN());
		} catch (TimeoutException | NoSuchElementException e) {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddOrRemoveCustomerPartNumber()
					.enterCPN(data.getSearchTextForCPN());
			homePage().searchText(data.getSearchTextForCPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyCPNInProductDetailsPage(data.getSearchTextForCPN());
		} finally {
			homePage().clickOnLogout();
		}
		logger.info("-------verifyAdvanceSearchByCPN Completed------------------");

	}

	@Feature("Advance Search")
	@Description("Verify advance search for invalid CPN - TC_Advsearch_003")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByInvalidCPN() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByInvalidCPN Started------------------");

			loginModuleTest.loginAsASuperUser();
			Thread.sleep(1500);
			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectCPN()
					.enterSearchText("asdfsadfasd")
					.clickOnSearchButton()
					.homePage()
					.verifyMessageForInvalidSearchData();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByInvalidCPN failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByInvalidCPN-Failed " + t.getMessage());
		}finally {
			homePage().clickOnLogout();
			
		}
		logger.info("-------verifyAdvanceSearchByInvalidCPN Completed------------------");

	}

	@Feature("Advance Search")
	@Description("Verify the alert message when clicking on search button without providing any search text.")
	@Test(groups = { "regression" })
	public void verifyAlertMessage_NoSearchText_AdvSearch() throws Exception {
		try {
			logger.info("-------verifyAlertMessage_NoSearchText_AdvSearch Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectCPN()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch())
					.selectUPC()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch())
					.selectMPN()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch())
					.selectDescription()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch())
					.selectDescription_AllWords()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch())
					.selectDescription_ExactWords()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch())
					.selectDescription_OneOrMore()
					.clickOnSearchButton()
					.verifyAlertMessage(data.getAlertMessageForSearch());
			homePage().clickOnLogo();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAlertMessage_NoSearchText_AdvSearch failed " + t.getMessage());
			Assert.fail("verifyAlertMessage_NoSearchText_AdvSearch-Failed " + t.getMessage());
		}
		logger.info("-------verifyAlertMessage_NoSearchText_AdvSearch Completed------------------");
	}
	
	@Feature("Advance Search")
	@Description("Verify using Description when \"All of these words\" is selected - TC_Advsearch_008")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByAllOfTheseWords() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByAllOfTheseWords Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectDescription()
					.selectDescription_AllWords()
					.enterSearchText("0123936 Whirlpool Dishwasher 90 deg 3/4 FHT x 3/8 Comp LF 1 BG")
					.clickOnSearchButton();
			productDetailsPage().verifyShortDescInProductDetailsPage("0123936 Whirlpool Dishwasher 90 deg 3/4 FHT x 3/8 Comp LF 1 BG");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByAllOfTheseWords failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByAllOfTheseWords-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByAllOfTheseWords Completed------------------");
	}
	
	@Feature("Advance Search")
	@Description("Verify using Description when \"Exact Wording of Phrase\" is selected - TC_Advsearch_009")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByExactWords() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByExactWords Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectDescription()
					.selectDescription_ExactWords()
					.enterSearchText("Kitchen Faucet")
					.clickOnSearchButton();
			productListPage().verifyShortDescInProductDetailsPage("Kitchen Faucet");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByExactWords failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByExactWords-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByExactWords Completed------------------");
	}
	
	@Feature("Advance Search")
	@Description("Verify using Description when \"One or more of these words\" is selected - TC_Advsearch_010")
	@Test(groups = { "regression" })
	public void verifyAdvanceSearchByOneOrMoreWords() throws Exception {
		try {
			logger.info("-------verifyAdvanceSearchByOneOrMoreWords Started------------------");

			homePage().searchText("asdfasdf")
					.clickOnSearch()
					.advanceSearchPage()
					.clickOnAdvanceSearchLink()
					.selectDescription()
					.selectDescription_OneOrMore()
					.enterSearchText("Sleeve test Industrial")
					.clickOnSearchButton();
			productListPage().verifyShortDescInProductDetailsPageForOneOrMoreWords("Sleeve, test, Industrial");
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			logger.info("verifyAdvanceSearchByOneOrMoreWords failed " + t.getMessage());
			Assert.fail("verifyAdvanceSearchByOneOrMoreWords-Failed " + t.getMessage());
		}
		logger.info("-------verifyAdvanceSearchByOneOrMoreWords Completed------------------");
	}


}
