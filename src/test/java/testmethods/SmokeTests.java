package testmethods;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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
import utility.CSVFileOperations;
import utility.MSExcelMethods;
import utility.UtilityMethods;

public class SmokeTests extends PageInitializer {

	Logger log = Logger.getLogger(SmokeTests.class);

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	MSExcelMethods excelMethods = new MSExcelMethods();

	String searchText = data.getSearchTextForPN();

	String savedCartName = data.getSaveCartName();

	String savedCartPageName = data.getSavedCartPageName();

	String productGroupName = data.getProductGroupName();

	String updatedGroupName = "TestProductGroup102";

	CSVFileOperations csvFileOperations = new CSVFileOperations();

	String qopCSVFilePath = System.getProperty("user.dir") + "/src/test/resources/TestDataFiles/QOP/copyPaste.csv";

	String qopFileUpload = System.getProperty("user.dir") + "/src/test/resources/TestDataFiles/QOP/fileUpload.xlsx";

	/*
	 * String qopFileUpload = System.getProperty("user.dir") + File.separator +
	 * "src" + File.separator + "test" + File.separator + "resources" +
	 * File.separator + "TestData" + File.separator + "QOP" + File.separator +
	 * "fileUpload.xlsx";
	 */

	String qopCSVFilePathCombineSeperateRemove = System.getProperty("user.dir")
			+ "/src/test/resources/TestDataFiles/QOP/copyPasteCombineSeperateRemove.csv";

	public List<String> getPartNumbersFromSpeedEntryData(String[] partNumberQuantityData) {
		List<String> partNumber = new ArrayList<>();
		for (int i = 0; i < partNumberQuantityData.length; i++) {
			String[] pn = partNumberQuantityData[i].split(":");
			partNumber.add(pn[0]);
			log.info("Part number for spread entry :" + pn[i]);
		}
		return partNumber;
	}

	public List<String> getPartNumbersFromFileUploadData(String[][] partNumber) {
		List<String> pn = new ArrayList<>();
		for (int i = 0; i < partNumber.length; i++) {
			pn.add(partNumber[i][0]);
		}
		log.info("Part number for File Upload:" + pn);
		return pn;
	}

	public List<String> getPartNumbersFromCSVFile(String filePath) throws IOException {
		List<String[]> testdata = csvFileOperations.readDataFromCSV(filePath);
		List<String> partNumber = new ArrayList<>();
		for (String[] row : testdata) {
			partNumber.add(row[0]);
		}
		log.info("Part number for CSV File :" + partNumber);
		return partNumber;
	}
	
	Date date = new Date();

	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	String date1 = Integer.toString(localDate.getDayOfMonth());
	
	@BeforeClass
	public void a_login() throws Throwable {
		loginModule.loginAsASuperUser();
		clearCart();
	}

	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogout();
	}
	
	public void clearCart() throws Exception {
		myCartPage().clearCart();
	}
	
	public void deleteProductGroup(String productGroupName) throws Exception {
		homePage().navigateToMyProductGroups()
				.searchProductGroup(productGroupName)
				.deleteProductGroup(productGroupName)
				.commonOperations()
				.clickOnOkButtonInAlertPopUp()
				.productGroupsPage()
				.searchProductGroup(productGroupName)
				.verifyWhetherGroupIsDeleted(productGroupName);
		log.info("Deleted product Group :" + productGroupName);
	}

	public void saveCartDeleteAndVerify(String scName) throws Exception {
		homePage().clickOnMySaveCart()
				.searchSaveCart(scName)
				.deleteSaveCart(scName)
				.commonOperations()
				.clickOnOkButtonInAlertPopUp()
				.saveCartPage()
				.searchSaveCart(scName)
				.verifyDeletionOfSaveCart(scName);
		log.info("Deleted Saved Cart :" + scName);
	}

	@Feature("Change Password module")
	@Test(groups = { "ChangePassword",
			"regression" }, dataProvider = "ChangePassword", dataProviderClass = TestNGDataProvider.class)
	@Description("Verify the Change password fields")
	public void verificationOfChangePwdPage(String pleaseNoteText, String productName, String breadcrum) throws Exception {
		log.info("---------------verificationOfChangePwdPage Started----------------");
		try {
			
			homePage().clickOnMyAccountMenuDropdown().clickOnChangePasswordLink();
			changePasswordPage().verifyChangePasswordPage(pleaseNoteText, productName, breadcrum);
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verificationOfChangePwdPage failed:" + e.getMessage());
			Assert.fail("verificationOfChangePwdPage failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("---------------verificationOfChangePwdPage Completed----------------");
	}

	@Feature("My Product Group Module")
	@Description("Verify Product group page and fields present in it")
	@Test(groups = { "Smoke", "regression" })
	public void verifyMyProductGroupPage(ITestContext context) throws Exception {
		log.info("---------------verifyMyProductGroupPage Started----------------");
		try {
			
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnMyProductGroupButton()
					.enterGroupName(productGroupName)
					.hitEnter()
					// .verifyMyProductCreationSuccessMsg(productGroupName)
					.homePage()
					.navigateToMyProductGroups()
					.productGroupsPage()
					.verifyPageTitle(data.getProductGroupPageName(),
							context.getCurrentXmlTest().getParameter("Company"))
					.verifyPageNameOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.verifyBreadCrumbOfMyProductGroupLandingPage(data.getProductGroupPageName())
					.clickOnTheGroupCreated(productGroupName)
					.verifyPageName(productGroupName)
					.verifyBreadCrumb(productGroupName)
					.verifyPageTitle(productGroupName, context.getCurrentXmlTest().getParameter("Company"))
					.verifyMyProductGroupFields()
					.verifyItemsInProductGroup(data.getSearchTextForPN());
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifyMyProductGroupPage failed:" + e.getMessage());
			Assert.fail("verifyMyProductGroupPage failed:" + e.getMessage());
		} finally {
			try {
				deleteProductGroup(productGroupName);
				homePage().clickOnLogo();
			} catch (NoSuchElementException | TimeoutException e) {
				homePage().clickOnLogo();
			}
		}
		log.info("---------------verifyMyProductGroupPage Completed----------------");
	}

	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression" })
	@Description("Verify add to cart functionality in 'Copy Paste' tab")
	public void copyPasteAddToCart() throws Exception {
		log.info("---------------copyPasteAddToCart Started----------------");
		try {
			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePath)
					.clickOnAddToCartButtonInCopyPaste()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(
					getPartNumbersFromCSVFile(qopCSVFilePathCombineSeperateRemove));
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("copyPasteAddToCart failed:" + e.getMessage());
			Assert.fail("copyPasteAddToCart failed:" + e.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------copyPasteAddToCart Completed----------------");
	}

	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression" })
	@Description("Verify add to cart functionality in 'Speed Entry' tab")
	public void speedEntryAddToCart() throws Exception {
		log.info("---------------speedEntryAddToCart Started----------------");
		try {
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getPartNumberForSpeedEntry().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.verifyItemInAddedToCartSection(data.getPartNumberForSpeedEntry().split(";"));
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(
					getPartNumbersFromSpeedEntryData(data.getPartNumberForSpeedEntry().split(";")));
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("speedEntryAddToCart failed:" + e.getMessage());
			Assert.fail("speedEntryAddToCart failed:" + e.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------speedEntryAddToCart Completed----------------");
	}

	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression" })
	@Description("Verify File Upload Add to cart")
	public void verifyFileUploadAddToCart() throws Exception {
		log.info("---------------verifyFileUploadAddToCart Started----------------");
		try {
			
			String[][] data = excelMethods.readDataFromExcel(qopFileUpload, "Sheet1");
			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnFileUploadTab()
					.uploadFile(qopFileUpload)
					.clickOnUpload()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(
					getPartNumbersFromFileUploadData(data));
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifyFileUploadAddToCart failed:" + e.getMessage());
			Assert.fail("verifyFileUploadAddToCart failed:" + e.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("--------------- verifyFileUploadAddToCart Completed----------------");
	}

	@Feature("Request For Quote Module")
	@Test(groups = { "Regression" })
	@Description("Verify Add and remove feature in RFQ")
	public void verifyAddAndRemoveFunctionInRFQ() throws Exception {
		log.info("---------------verifyAddAndRemoveFunctionInRFQ Started----------------");
		try {
			
			MyAccountMenuDropdown().clickOnMyAccountMenu()
					.clickOnRequestForQuoteLink()
					.verifyAddRowInRequestForQuoteTabel();
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifyAddAndRemoveFunctionInRFQ failed:" + e.getMessage());
			Assert.fail("verifyAddAndRemoveFunctionInRFQ failed:" + e.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("--------------- verifyAddAndRemoveFunctionInRFQ Completed----------------");
	}

	@Feature("Save Cart Module")
	@Description("Verify Creating a New Saved Cart and contents of saved cart page")
	@Test(groups = { "Smoke", "regression" })
	public void createAndVerifySavedCartPage(ITestContext context) throws Exception {
		log.info("--------------- createAndVerifySavedCartPage Started----------------");
		try {
			

			String partNumber = homePage().searchText(searchText).clickOnSearch().productDetailsPage().getPartNumber();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.myCartPage()
					.clickOnSaveCartButton()
					.enterNameOfSaveCart(savedCartName)
					.hitEnterForSaveCartCreation();
			getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			myCartPage().verifySaveCartCreationMessage(savedCartName);
			homePage().clickOnMySaveCart()
					.verifyPageName(savedCartPageName)
					.verifybreadCrumbs(savedCartPageName)
					.verifySaveCartTitle(savedCartPageName, context.getCurrentXmlTest().getParameter("Company"))
					.searchSaveCart(savedCartName)
					.clickOnTheCreatedSaveCart(savedCartName)
					.verifyPageNameSaveCartLandingPageName(savedCartName)
					.verifybreadCrumbs(savedCartName)
					.verifyMySavedCartPage()
					.verifyItemsInSavedCart(partNumber.trim());
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("createAndVerifySavedCartPage failed:" + e.getMessage());
			Assert.fail("createAndVerifySavedCartPage failed:" + e.getMessage());
		} finally {
			try {
				clearCart();
				saveCartDeleteAndVerify(savedCartName);
				homePage().clickOnLogo();
			} catch (NoSuchElementException | TimeoutException e) {
				homePage().clickOnLogo();
			}
		}
		log.info("---------------createAndVerifySavedCartPage Completed----------------");
	}

	@Feature("Add To Cart")
	@Description("Verify items are added to cart from list/grid or from details page.")
	@Test(groups = { "AddToCartModule", "regression" })
	public void verifyAddToCartFuncatinalityInDetailPage(ITestContext context) throws Exception {
		log.info("--------------- verifyAddToCartFuncatinalityInDetailPage Started----------------");
		try {
			homePage().searchText(data.getSearchTextForPN()).clickOnSearch();
			String partNumber = productDetailsPage().getPartNumber();
			String shortDescription = productDetailsPage().getShortDescription();
			String price = productDetailsPage().getPrice();
			productDetailsPage().verifyAddToCartButton()
					.verifyEnabledDisableCartButtonBasedOnPriceOfItem()
					.verifyDisplayOfAddMyProductGroupButton()
					.verifyDisplayOfSendLink()
					.verifyDisplayOfPrintLink()
					.verifyDisplayOfMinimumOrderQuantity()
					.verifyDisplayOfMPN()
					.verifyDisplayOfNameOfTheProduct()
					.verifyDisplayOfPartNumber()
					.verifyDisplayOfMinimumOrderQuantity()
					.verifyDisplayOfUPCText()
					.verifyDisplayOfNameOfTheProduct()
					.verifyDisplayOfBrand()
					.verifyDisplayOfManufacturer()
					.verifyDisplayOfQuantityInterval()
					.verifyDisplayOfQtyTextBox()
					.verifyDisplayOfAddRemoveCPN()
					.verifyDisplayOfImage()
					.verifydisplayOfThumblineImage()
					.verifyDisplayOfDescriptionTab()
					.verifyDisplayOfSpecificationTab();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.verifyPartNumberInMyCartPopUp(partNumber)

					.verifyShortDescriptionInMyCartPopUp(shortDescription)
					.verifyPriceInMyCartPopUp(price)
					.verifyViewCartButtonInMyCartPopUp()
					.clickOnCloseButtonInMyCartPopUp();

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifyAddToCartFuncatinalityInDetailPage failed:" + e.getMessage());
			Assert.fail("verifyAddToCartFuncatinalityInDetailPage failed:" + e.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------verifyAddToCartFuncatinalityInDetailPage Completed----------------");
	}

	@Feature("Shopping Cart")
	@Description("Verify Update Button Funcationality.")
	@Test(groups = { "ShoppingCartModule", "regression", "Smoke Test" })
	public void verifyUpdateCartButtonFuncationality() throws Exception {
		log.info("-------------verifyUpdateCartButtonFuncationality Started------------------");
		try {
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.verifyPartNumberInProductDetailsPage(data.getSearchTextForPN())
					.clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.clickOnViewCartInMyCartPopup();
			String qty = "15";
			String priceOfAItem = myCartPage().getPriceOfSpecificItem(data.getSearchTextForPN());
			String updateExtPrice = String.valueOf(Float.parseFloat(qty) * Float.parseFloat(priceOfAItem));
			myCartPage().enterQuantityForSpecificItem(qty, data.getSearchTextForPN())
					.clickOnUpdateButton()
					.verifyUpdateOfQuantityInShoppingCart(qty, data.getSearchTextForPN())
					.verifyUpdatedExtPrice(updateExtPrice, data.getSearchTextForPN());
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyUpdateCartButtonFuncationality failed :" + t.getMessage());
			Assert.fail("verifyUpdateCartButtonFuncationality failed :" + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("---------------verifyUpdateCartButtonFuncationality Completed-------------------");

	}
	
	@Feature("Pricing Module")
	@Description("Verify customer specific price in detail page after login.")
	@Test(groups = { "PricingModule", "regression", "Smoke Test" })
	public void verifyItemPriceAfterLoginInDetailPage() throws Exception {
		log.info("-------------- verifyItemPriceAfterLoginInDetailPage Started--------------------");
		try {
			homePage().searchText(searchText)
					.clickOnSearch()
					.productDetailsPage()
					.verifyItemPriceAfterLogin();
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyItemPriceAfterLoginInDetailPage failed :" + t.getMessage());
			Assert.fail("verifyItemPriceAfterLoginInDetailPage failed :" + t.getMessage());

		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------- verifyItemPriceAfterLoginInDetailPage Completed -------------------");

	}
	
	@Feature("PO Checkout")
	@Description("Verify whether able to place a PO order.")
	@Test(groups = { "POCheckoutModule", "regression",
			 }, dataProvider = "POCheckout", dataProviderClass = TestNGDataProvider.class)
	public void verifyCheckoutProcessByPO(String purchaseOrderNumber, String shipMethod, String orderBy,
			String shippingInstruction, String orderNotes, String orderSummaryFields, String orderConfirmationPageName,
			String orderConfirmationFields, String orderConfirmationHeaderFields, ITestContext context)
			throws Exception {
		log.info("-----------------verifyCheckoutProcessByPO started--------------");
		try {
			String myCartBreadcrumb = data.getMyCartBreadcrumb();

			String partNumber = homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.getPartNumber();
			productDetailsPage().clickOnAddToCartButton()
					.myCartPage()
					.verifyItemsCountInMyCartPopUp("1")
					.verifyPartNumberInMyCartPopUp(partNumber)
					.clickOnCloseButtonInMyCartPopUp()
					.clickOnCartIcon()
					.verifyMyCartBreadcrumb(myCartBreadcrumb)
					.clickOnCheckoutInMyCartPage()
					.checkoutPage()
					.verifyCheckoutBreadCrumb()
					.verifyCheckoutPageName()
					.verifyTitleOfCheckoutPage(context.getCurrentXmlTest().getParameter("Company"))
					.verifyBillingAddressTab()
					.verifyShippingAddressTab()
					.verifyOrderDetailstab()
					.clickOnNextButton()
					.verifyWhetherShippingAddressTabIsActive()
					.selectShipMethod(shipMethod)
					.clickOnNextButton();
			Thread.sleep(20000);
			checkoutPage().verifyWhetherOrderDetailsTabIsActive()
					.verifyOrderDetailsFields()
					.enterPurchaseOrderNumber(purchaseOrderNumber)
					.enterOrderedBy(orderBy)
					.selectRequiredByDate(date1)
					.enterShippingInstructions(shippingInstruction)
					.enterOrderNotes(orderNotes)
					.clickOnPreviousButton();
			Thread.sleep(1400);
			checkoutPage().verifyShippingAddressTab().verifyWhetherShippingAddressTabIsActive().clickOnNextButton();
			Thread.sleep(20000);
			checkoutPage()
					.verifyPreviouslyFilledDataOfAllMandatoryFieldsInOrderDetailsPage(shipMethod, orderBy,
							purchaseOrderNumber, shippingInstruction, orderNotes)
					.verifyOrderSummaryFields(orderSummaryFields);
		
		} catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyCheckoutProcessByPO failed :" + t.getMessage());
			Assert.fail("verifyCheckoutProcessByPO failed :" + t.getMessage());
		} finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyCheckoutProcessByPO Completed--------------");
	}
}