package testmethods;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import pageobjects.CommonPageObjects;
import utilities.TestDataPropertyFile;
import utilities.TestNGDataProvider;

public class CompareModuleTest extends PageInitializer {

	Logger log = Logger.getLogger(CompareModuleTest.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModuleTest = new LoginModuleTest();

	CommonPageObjects commonPageObjects = new CommonPageObjects();

	@Feature("Compare Module")
	@Test(groups = { "CompareModule", "regression", })
	@Description("Verify compare page details")
	public void verifyComparePage(ITestContext context) throws Exception {
		log.info("------------------verifyComparePage Started--------------------------");
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

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyComparePage-Failed " + t.getMessage());
			Assert.fail("verifyComparePage-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------verifyComparePage completed--------------------------");
	}

	@Feature("Compare Module")
	@Test(groups = { "CompareModule", "regression" })
	@Description("Verify the alert text when no items, one item and more than 5 items are selected.")
	public void verifyAlertTexts() throws Exception {
		log.info("------------------verifyAlertTexts Started--------------------------");
		try {
			// alert text when no item is selected
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnCompareLink()
					.commonOperations()
					.verifyAlertTextPopUp(data.getSelectForThanOneItemToCompareText())
					.clickOnOkButtonInAlertPopUp();

			getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			// alert text when one item is selected
			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnCompareLink()
					.commonOperations()
					.verifyAlertTextPopUp(data.getSelectForThanOneItemToCompareText())
					.clickOnOkButtonInAlertPopUp();

			getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			// alert text when more than 5 items are selected
			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(3)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(4)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(5)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(6)
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertTextForMoreThanFiveItems())
					.clickOnOkButtonInAlertPopUp();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyAlertTexts-Failed " + t.getMessage());
			Assert.fail("verifyAlertTexts-Failed " + t.getMessage());
		} finally {
			productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------verifyAlertTexts completed--------------------------");
	}

	@Feature("Compare Module")
	@Test(groups = { "CompareModule", "regression" })
	@Description("Verify the compare link text.")
	public void verifyCompareLinkText() throws Exception {
		log.info("------------------verifyCompareLinkText Started--------------------------");
		try {
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.verifyCompareText(2)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.verifyCompareText(1);
			productListPage().clickOnClearCompareButton().commonOperations().clickOnOkButtonInAlertPopUp();
			productListPage().verifyCompareText(0);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyCompareLinkText-Failed " + t.getMessage());
			Assert.fail("verifyCompareLinkText-Failed " + t.getMessage());
		} finally {
			productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------verifyCompareLinkText completed--------------------------");
	}

	@Feature("Compare Module")
	@Description("Verify compare page highlight functionality")
	@Test(groups = { "CompareModule", "regression", })
	public void verifyHighlightFunctionality() throws Exception {
		log.info("------------------verifyHighlightFunctionality Started--------------------------");
		try {
			// Highlight similar
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.clickOnCompareLink()
					.comparePage()
					.clickOnHighLightSimilar()
					.verifyColourOfHighlightSimilarButton(data.getColourOfHighlightSimilarButtonAfterClicking())
					.verifyHighlightSimilarFunctionality();

			// Highlight different
			comparePage().clickOnHighlightDifferent()
					.verifyColourOfHighlightDifferentButton(data.getColourOfHighlightSimilarButtonAfterClicking())
					.verifyHighLightDifferentFunctionality();

			// Highlight off
			String colourOfHighlightDifferentAttributes = comparePage().clickOnHighlightDifferent()
					.verifyColourOfHighlightDifferentButton(data.getColourOfHighlightSimilarButtonAfterClicking())
					.getColourOfHighlightDifferentAttributes();
			comparePage().clickOnHighlightOffButton().verifyHighlightOff(colourOfHighlightDifferentAttributes);
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyHighlightFunctionality-Failed " + t.getMessage());
			Assert.fail("verifyHighlightFunctionality-Failed " + t.getMessage());
		}

		finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------verifyHighlightFunctionality completed--------------------------");
	}

	@Feature("Compare Module")
	@Test(groups = { "CompareModule", "regression", })
	@Description("Verify comparision of products that are from different category.")
	public void compareProductsFromDifferentCategory() throws Exception {
		log.info("------------------compareProductsFromDifferentCategory Started--------------------------");
		try {
			String partNumbers1 = homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1);

			String partNumbers2 = homePage().searchText(data.getSearchTextForCategory())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnCompareLink()
					.comparePage()
					.verifyPartNumbers(partNumbers1, partNumbers2);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("compareProductsFromDifferentCategory-Failed " + t.getMessage());
			Assert.fail("compareProductsFromDifferentCategory-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------compareProductsFromDifferentCategory completed--------------------------");
	}

	@Feature("Compare Module")
	@Test(groups = { "CompareModule","regression" }, dataProvider = "MultipleTestData", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify comparision of products that are from different brands.")
	public void compareItemsFromDifferentBrands(String brand1, String brand2) throws Exception {
		log.info("------------------compareItemsFromDifferentBrands Started--------------------------");
		try {

			String partNumbers1 = homePage().searchText(brand1).clickOnSearch().productListPage().getSpecificPartNumber(1);
			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1);

			String partNumbers2 = homePage().searchText(brand2).clickOnSearch().productListPage().getSpecificPartNumber(1);

			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnCompareLink()
					.comparePage()
					.verifyPartNumbers(partNumbers1, partNumbers2);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("compareItemsFromDifferentBrands-Failed " + t.getMessage());
			Assert.fail("compareItemsFromDifferentBrands-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------compareItemsFromDifferentBrands completed--------------------------");
	}

	@Feature("Compare Module")
	@Description("Verify error scenarios in compare page")
	@Test(groups = { "CompareModule", "regression" })
	public void compareItemsFromProductMode(ITestContext context) throws Exception {
		log.info("------------------compareItemsFromProductMode Started--------------------------");
		try {
			homePage().searchText(data.getSearchTextForProductMode())
					.clickOnSearch()
					.productListPage()
					.clickOnViewAllChoiceButton(1)
					.clickOnCompareCheckboxesUnderMoreChoices(2)
					.clickOnCompareLink()
					.comparePage()
					.verifyComparePageFields(data.getComparePageName(),
							context.getCurrentXmlTest().getParameter("Company"));
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("compareItemsFromProductMode-Failed " + t.getMessage());
			Assert.fail("compareItemsFromProductMode-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------compareItemsFromProductMode completed--------------------------");
	}

	@Feature("Compare Module")
	// Test Data not available
	@Test(enabled = false, groups = { "CompareModule",
			"regression" }, dataProvider = "MultipleTestData", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify add to cart button is disabled when compared item doesn't have price")
	public void verifyDisableAddToCartButton(String searchText, ITestContext context) throws Exception {
		log.info("------------------verifyDisableAddToCartButton Started--------------------------");
		try {
			loginModuleTest.loginAsASuperUser();

			String pn = homePage().searchText(searchText)
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumberOfCallForPriceItem(1);

			productListPage().clickOnSpecificAddToCompareCheckboxCallForPrice(1)
					.clickOnSpecificAddToCompareCheckboxCallForPrice(2)
					.clickOnCompareLink()
					.comparePage()
					.verifyComparePageFields(data.getComparePageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.checkDisableOfAddToCartButton(pn);

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyDisableAddToCartButton-Failed " + t.getMessage());
			Assert.fail("verifyDisableAddToCartButton-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnClearCompareButton()
					.homePage()
					.clickOnLogout();
		}
		log.info("------------------verifyDisableAddToCartButton completed--------------------------");
	}

	@Feature("Compare Module")
	@Test(groups = { "CompareModule", "regression", "Smoke Test" })
	@Description("Verify adding an item to cart from compare page.")
	public void addingAnItemToCartFromComparePage() throws Exception {
		log.info("------------------addingAnItemToCartFromComparePage Started--------------------------");
		try {
			loginModuleTest.loginAsASuperUser();
			myCartPage().clearCart();
			String pn = homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.getSpecificPartNumber(1);
			productListPage().clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.clickOnCompareLink()
					.comparePage()
					.clickOnSpecificAddToCartButton(pn)
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.verifyPartNumberInMyCart(pn);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("addingAnItemToCartFromComparePage-Failed " + t.getMessage());
			Assert.fail("addingAnItemToCartFromComparePage-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			myCartPage().clearCart();
			homePage().clickOnLogout();
			getDriver().navigate().refresh();
		}
		log.info("------------------addingAnItemToCartFromComparePage completed--------------------------");
	}

	@Feature("Compare Module")
	@Description("Verify deleting an item from compare page")
	@Test(groups = { "CompareModule", "regression" })
	public void comparePageRemoveItem() throws Exception {
		log.info("------------------comparePageRemoveItem Started--------------------------");
		try {
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(3)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(4)
					.clickOnCompareLink();

			String pn = comparePage().getSpecificPartNumber(1);

			comparePage().clickOnSpecficRemoveCheckbox(1).clickOnRemoveItem().verifyItemIsRemovedFromCompare(pn);
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("comparePageRemoveItem-Failed " + t.getMessage());
			Assert.fail("comparePageRemoveItem-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText()).clickOnSearch().productListPage().clickOnClearCompareButton();
			homePage().clickOnLogo();
		}
		log.info("------------------comparePageRemoveItem completed--------------------------");

	}

	@Feature("Compare Module")
	@Description("Verify error scenarios in compare page")
	@Test(groups = { "CompareModule", "regression" })
	public void comparePageErrorScenarios() throws Exception {
		try {
			log.info("------------------comparePageErrorScenarios Started--------------------------");

			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnSpecificAddToCompareCheckboxInSKUMode(1)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(2)
					.clickOnSpecificAddToCompareCheckboxInSKUMode(3)
					.clickOnCompareLink();

			// verify alert text when no items are selected
			comparePage().clickOnRemoveItem()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertTextWhenNoItemsAreSelectedInComparePage())
					.clickOnOkButtonInAlertPopUp();

			getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			// verify alert text when all items are selected
			comparePage().clickOnSpecficRemoveCheckbox(1)
					.clickOnSpecficRemoveCheckbox(2)
					.clickOnSpecficRemoveCheckbox(3)
					.clickOnRemoveItem()
					.commonOperations()
					.verifyAlertTextPopUp(data.getAlertTextWhenAllItemsAreSelectedInComparePage())
					.clickOnOkButtonInAlertPopUp();

		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("comparePageErrorScenarios-Failed " + t.getMessage());
			Assert.fail("comparePageErrorScenarios-Failed " + t.getMessage());
		} finally {
			homePage().searchText(data.getSearchText())
					.clickOnSearch()
					.productListPage()
					.clickOnClearCompareButton()
					.homePage()
					.clickOnLogo();
		}
		log.info("------------------comparePageErrorScenarios completed--------------------------");
	}

}