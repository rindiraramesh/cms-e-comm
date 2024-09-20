package testmethods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import utility.CSVFileOperations;
import utility.MSExcelMethods;

public class QuickOrderPadModuleTest extends PageInitializer {

	TestDataPropertyFile data = new TestDataPropertyFile();

	LoginModuleTest loginModule = new LoginModuleTest();

	ProductGroupModuleTest productGroupModuleTest = new ProductGroupModuleTest();

	MSExcelMethods excelMethods = new MSExcelMethods();

	CSVFileOperations csvFileOperations = new CSVFileOperations();

	Logger log = Logger.getLogger(QuickOrderPadModuleTest.class);

	String qopCSVFilePath = "src/test/resources/TestDataFiles/QOP/copyPaste.csv";

	String qopCSVFilePathCombineSeperateRemove = "src/test/resources/TestDataFiles/QOP/copyPasteCombineSeperateRemove.csv";

	String qopCSVFilePath_MoreThan30Rows = "src/test/resources/TestDataFiles/QOP/copyPasteMoreThan30Rows.csv";

	String qopCSVFilePath_LessMinQty = "src/test/resources/TestDataFiles/QOP/copyPasteLessMinQty.csv";

	String qopFileUpload = "src/test/resources/TestDataFiles/QOP/fileUpload.xlsx";

	String qopFileUpload_MoreThan30Rows = "src/test/resources/TestDataFiles/QOP/fileUpload_MoreThan30Rows.xlsx";

	String qopFileUpload_LessMinQty = "src/test/resources/TestDataFiles/QOP/fileUploadLessMinQty.xlsx";

	String qopFileUploadCombineSeperateRemove = "src/test/resources/TestDataFiles/QOP/fileUploadCombineSeperateRemove.xlsx";

	String productGroupName = data.getProductGroupName();

	public void clearCart() throws Exception {
		myCartPage().clearCart();
	}

	public List<String> getPartNumbersFromSpeedEntryData(String[] partNumberQuantityData) {
		List<String> partNumber = new ArrayList<>();
		for (int i = 0; i < partNumberQuantityData.length; i++) {
			String[] pn = partNumberQuantityData[i].split(":");
			partNumber.add(pn[0]);
		}
		return partNumber;
	}

	public List<String> getQuantityFromSpeedEntryData(String[] partNumberQuantityData) {
		List<String> qty = new ArrayList<>();
		for (int i = 0; i < partNumberQuantityData.length; i++) {
			String[] pn = partNumberQuantityData[i].split(":");
			qty.add(pn[1]);
		}
		return qty;
	}

	public int getTotalQuantityFromSpeedEntryData(String[] partNumberQuantityData) {
		int totalQty = 0;
		for (int i = 0; i < partNumberQuantityData.length; i++) {
			totalQty += Integer.parseInt(partNumberQuantityData[i].split(":")[1]);
		}
		return totalQty;
	}

	public List<String> getPartNumbersFromFileUploadData(String[][] partNumber) {
		List<String> pn = new ArrayList<>();
		for (int i = 0; i < partNumber.length; i++) {
			pn.add(partNumber[i][0]);
		}
		return pn;
	}

	public int getTotalQtyFromFileUpload() throws IOException {
		String[][] data = excelMethods.readDataFromExcel(qopFileUploadCombineSeperateRemove, "Sheet1");
		int totalQty = 0;
		for (int i = 0; i < data.length; i++) {
			totalQty += Integer.parseInt(data[i][1]);
		}
		return totalQty;
	}

	public int getTotalQtyFromCSVFile(String filePath) throws IOException {
		List<String[]> testdata = csvFileOperations.readDataFromCSV(filePath);
		int qtySum = 0;
		for (String[] row : testdata) {
			qtySum += Integer.parseInt(row[1]);
		}
		return qtySum;
	}

	public List<String> getQtyFromCSVFile(String filePath, int specificRow) throws IOException {
		List<String[]> testdata = csvFileOperations.readDataFromCSV(filePath);
		List<String> qtys = new ArrayList<>();
		for (String[] row : testdata) {
			qtys.add(row[specificRow]);
		}
		return qtys;
	}

	public List<String> getPartNumbersFromCSVFile(String filePath) throws IOException {
		List<String[]> testdata = csvFileOperations.readDataFromCSV(filePath);
		List<String> partNumber = new ArrayList<>();
		for (String[] row : testdata) {
			partNumber.add(row[0]);
			System.out.println(partNumber.add(row[0]));
		}
		return partNumber;
	}

	
	@BeforeClass
	public void a_login() throws Exception {
		loginModule.loginAsASuperUser();
	
		myCartPage().clearCart();
	}
	
	@AfterClass
	public void z_LogOut() throws Throwable {
		homePage().clickOnLogout();
		logger.info("clicked on Logout link");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "regression", "Smoke Test" })
	@Description("Verify Quick Order Pad page elements")
	public void verifyQuickOrderLandingPage(ITestContext context) throws Exception {
		log.info("----------------verifyQuickOrderLandingPage Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.verifyQuickOrderPage(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifySpeedEntryRightClickOptions(data.getQOPRightClickOptions().split(","))
					.verifyMaxNoOfRowsThatCanBeAdded()
					.verifyRemoveRow()
					.verifyAddRowBelow()
					.verifyRemoveRow()
					.verifyAddRowAbove();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyQuickOrderLandingPage failed " + t.getMessage());
			Assert.fail("verifyQuickOrderLandingPage-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyQuickOrderLandingPage Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke Test", "regression" })
	@Description("Verify add to cart functionality in 'Speed Entry' tab")
	public void speedEntryAddToCart() throws Exception {
		log.info("----------------speedEntryAddToCart Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getPartNumberForSpeedEntry().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.verifyItemInAddedToCartSection(data.getPartNumberForSpeedEntry().split(";"));
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(
					getPartNumbersFromSpeedEntryData(data.getPartNumberForSpeedEntry().split(";")));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart Completed---------------- ");
	}

	@Feature("Quick Order Pad Module")
	@Test(enabled = false, groups = { "Smoke", "regression","speed" })
	@Description("Verify add to cart functionality in 'Speed Entry' tab with items with exception")
	public void speedEntryAddToCart_ItemsWithException() throws Exception {
		log.info("----------------speedEntryAddToCart_ItemsWithException Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getPartNumberForSpeedEntry_LessMinOrderQty().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.verifyItemInItemsWithExceptionSection(
							data.getPartNumberForSpeedEntry_LessMinOrderQty().split(";"));
			homePage().clickOnViewCartIcon().myCartPage().verifyEmptyCart();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_ItemsWithException failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_ItemsWithException-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_ItemsWithException Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "regression","speed"  })
	@Description("Verify add to cart functionality in 'Speed Entry' tab with combine functionality")
	public void speedEntryAddToCart_Combine() throws Exception {
		log.info("----------------speedEntryAddToCart_Combine Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(
							data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.verifyItemInAddedToCartSection(data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";"));
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(getPartNumbersFromSpeedEntryData(
							data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")))
					.verifyQuantityOfSpecificItem(
							getPartNumbersFromSpeedEntryData(
									data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")).get(0),
							getTotalQuantityFromSpeedEntryData(
									data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_Combine failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_Combine-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_Combine Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed"  })
	@Description("Verify add to cart functionality in 'Speed Entry' tab with seperate functionality")
	public void speedEntryAddToCart_Seperate() throws Exception {
		log.info("----------------speedEntryAddToCart_Seperate Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(
							data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";"))
					.clickOnSeperateButtonSpeedEntry()
					.clickOnAddToCartButtonSpeedEntry()
					.verifyItemInAddedToCartSection(data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";"));
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(getPartNumbersFromSpeedEntryData(
							data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")))
					.verifyQuantityOfSpecificItem(
							getPartNumbersFromSpeedEntryData(
									data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")).get(0),
							Integer.parseInt(getQuantityFromSpeedEntryData(
									data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")).get(0)));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_Seperate failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_Seperate-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_Seperate Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed"  })
	@Description("Verify add to cart functionality in 'Speed Entry' tab with remove functionality")
	public void speedEntryAddToCart_Remove() throws Exception {
		log.info("----------------speedEntryAddToCart_Remove Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(
							data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";"))
					.clickOnRemoveButtonSpeedEntry()
					.clickOnAddToCartButtonSpeedEntry()
					.verifyItemInAddedToCartSection(data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";"));
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(getPartNumbersFromSpeedEntryData(
							data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")))
					.verifyQuantityOfSpecificItem(
							getPartNumbersFromSpeedEntryData(
									data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")).get(0),
							Integer.parseInt(getQuantityFromSpeedEntryData(
									data.getPartNumberForSpeedEntryCombineRemoveSeperate().split(";")).get(0)));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_Remove failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_Remove-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_Remove Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(enabled = true, groups = { "Smoke", "regression","speed" })
	@Description("Verify add to cart functionality in 'Speed Entry' tab with generic search text")
	public void speedEntryAddToCart_SearchText() throws Exception {
		log.info("----------------speedEntryAddToCart_SearchText Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.verifyGenericSearchMsgDisplay()
					.clickOnClickHereLinkInGenericSearch()
					.verifySearchTextInPLP(data.getSearchTextForSpeedEntry().split(":"));
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_SearchText failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_SearchText-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_SearchText Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression" })
	@Description("Verify Undo and Redo options in 'Speed Entry' tab")
	public void speedEntryAddToCart_UndoRedo() throws Exception {
		log.info("----------------speedEntryAddToCart_UndoRedo Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberForUndoRedoInSpeedEntry(data.getSearchTextForSpeedEntry().split(";")[0])
					.verifyUndoOption(data.getSearchTextForSpeedEntry().split(";")[0])
					.verifyRedoOption(data.getSearchTextForSpeedEntry().split(";")[0]);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_UndoRedo failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_UndoRedo-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_UndoRedo Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed"  })
	@Description("Verify invalid quantity functionality in 'Speed Entry' tab")
	public void speedEntryAddToCart_InvalidQty() throws Exception {
		log.info("----------------speedEntryAddToCart_InvalidQty Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_InvalidQty().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.commonOperations()
					.verifyAlertPopUpContainsText("Enter Valid Quantity to row#");
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_InvalidQty failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_InvalidQty-Failed " + t.getMessage());
		} finally {
			
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_InvalidQty Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke Test", "regression","speed"  })
	@Description("Verify add to cart functionality in 'Speed Entry' through bulk option by selecting all items")
	public void speedEntryAddToCart_BulkOption_SelectAll() throws Exception {
		log.info("----------------speedEntryAddToCart_BulkOption_SelectAll Started---------------- ");
		try {
			List<String> pns = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_BulkOption().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.getPartNumbersFromMultipleResultSection();
			quickOrderPadPage()

					.clickOnSelectAllCheckboxInBulkOption()
					.selectBulkOption("Add Items to Cart")
					.myCartPage()
					.clickOnViewCartInMyCartPopup();
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(pns);
		} 
		
		  catch (Throwable t) { t.printStackTrace(); captureScreenshot("step",
		  getDriver()); log.info("speedEntryAddToCart_BulkOption_SelectAll failed " +
		  t.getMessage());
		  Assert.fail("speedEntryAddToCart_BulkOption_SelectAll-Failed " +
		  t.getMessage()); }
		finally { clearCart(); homePage().clickOnLogo(); }
		 
		log.info("----------------speedEntryAddToCart_BulkOption_SelectAll Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed"  })
	@Description("Verify add to cart functionality in 'Speed Entry' through bulk option by updating quantity")
	public void speedEntryAddToCart_BulkOption_UpdateQty() throws Exception {
		log.info("----------------speedEntryAddToCart_BulkOption_UpdateQty Started---------------- ");
		try {
			int qty = 15;
			// loginModule.loginAsASuperUser();
			List<String> pns = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_BulkOption().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.getPartNumbersFromMultipleResultSection();
			quickOrderPadPage()

					.enterQuantityForSpecificItem(1, String.valueOf(qty))
					.clickOnSpecificCheckbox(1)
					.selectBulkOption("Add Items to Cart")
					.myCartPage()
					.clickOnViewCartInMyCartPopup();

			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(pns.get(0))
					.verifyQuantityOfSpecificItem(pns.get(0), qty);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_BulkOption_UpdateQty failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_BulkOption_UpdateQty-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_BulkOption_UpdateQty Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "regression","speed"  })
	@Description("Verify add to product group functionality in 'Speed Entry' through bulk option by selecting all items")
	public void speedEntryAddToPG_BulkOption_SelectAll() throws Exception {
		log.info("----------------speedEntryAddToPG_BulkOption_SelectAll Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			List<String> pns = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_BulkOption().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.getPartNumbersFromMultipleResultSection();

			List<String> out = pns.stream().map(s -> s.replaceAll("Part#:", "").trim()).collect(Collectors.toList());

			quickOrderPadPage()

					.clickOnSelectAllCheckboxInBulkOption()
					.selectBulkOption("Add Items to Group")
					.myProductGroupPopUp()
					//.verifyItemsSelectedInPopUp(out)
					// .enterProductGroupName(productGroupName)
					.enterProductGroupName("TestGroup1")
					.clickOnAddNewGroup()
					.clickOnAddButton();
			homePage()

					.navigateToMyProductGroups()
					.searchProductGroup("TestGroup1")
					.clickOnTheGroupCreated("TestGroup1")

					.verifyItemsInProductGroup(out);
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToPG_BulkOption_SelectAll failed " + t.getMessage());
			Assert.fail("speedEntryAddToPG_BulkOption_SelectAll-Failed " + t.getMessage());
		}

		finally {
			productGroupModuleTest.deleteProductGroup("TestGroup1");
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToPG_BulkOption_SelectAll Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed"  })
	@Description("Verify add to cart functionality in 'Speed Entry' through bulk option specific item")
	public void speedEntryAddToCart_BulkOption_SpecificItem() throws Exception {
		log.info("----------------speedEntryAddToCart_BulkOption_SpecificItem Started---------------- ");
		try {
			
			List<String> pns = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_BulkOption().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.getPartNumbersFromMultipleResultSection();
			quickOrderPadPage().clickOnSpecificCheckbox(1)

					.selectBulkOption("Add Items to Cart")
					.myCartPage()
					.clickOnViewCartInMyCartPopup();

			homePage()

					.clickOnViewCartIcon()

					.myCartPage()

					.verifyPartNumberInMyCart(pns.get(0).trim());
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToCart_BulkOption_SpecificItem failed " + t.getMessage());
			Assert.fail("speedEntryAddToCart_BulkOption_SpecificItem-Failed " + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToCart_BulkOption_SpecificItem Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed"  })
	@Description("Verify add to product group functionality in 'Speed Entry' through bulk option by selecting specific items")
	public void speedEntryAddToPG_BulkOption_SpecificItem() throws Exception {
		log.info("----------------speedEntryAddToPG_BulkOption_SpecificItem Started---------------- ");
		try {
		
			List<String> pns = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_BulkOption().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.getPartNumbersFromMultipleResultSection();

			quickOrderPadPage().clickOnSpecificCheckbox(1)
					.selectBulkOption("Add Items to Group")
					.myProductGroupPopUp()
					.verifyItemsSelectedInPopUp(pns.get(0).trim())
					.enterProductGroupName(productGroupName)
					.clickOnAddNewGroup()
					.clickOnAddButton();
			homePage()

					.navigateToMyProductGroups()
					.searchProductGroup(productGroupName)

					.clickOnTheGroupCreated(productGroupName)

					.verifyItemsInProductGroup(pns.get(0).trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("speedEntryAddToPG_BulkOption_SpecificItem failed " + t.getMessage());
			Assert.fail("speedEntryAddToPG_BulkOption_SpecificItem-Failed " + t.getMessage());
		}

		finally {
			productGroupModuleTest.deleteProductGroup(productGroupName);
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToPG_BulkOption_SpecificItem Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","speed" })
	@Description("Verify add to product group functionality in 'Speed Entry' through bulk option by updating quantity")
	public void speedEntryAddToPG_BulkOption_UpdateQty() throws Exception {
		log.info("----------------speedEntryAddToPG_BulkOption_UpdateQty Started---------------- ");
		try {
			int qty = 15;
			// loginModule.loginAsASuperUser();
			List<String> pns = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.enterPartNumberOrUPCForSpeedEntry(data.getSearchTextForSpeedEntry_BulkOption().split(";"))
					.clickOnAddToCartButtonSpeedEntry()
					.getPartNumbersFromMultipleResultSection();

			quickOrderPadPage().enterQuantityForSpecificItem(1, String.valueOf(qty))

					.clickOnSpecificCheckbox(1)
					.selectBulkOption("Add Items to Group")
					.myProductGroupPopUp()
					.verifyItemsSelectedInPopUp(pns.get(0).trim())
					// .enterProductGroupName(productGroupName)
					.enterProductGroupName("TestAutomation2")
					.clickOnAddNewGroup()
					.clickOnAddButton();
			homePage()

					.navigateToMyProductGroups()
					.searchProductGroup("TestAutomation2")

					.clickOnTheGroupCreated("TestAutomation2")

					.verifyItemsInProductGroup(pns.get(0).trim())

					.verifyQuantityOfSpecificItemInPG(qty, pns.get(0));

		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("speedEntryAddToPG_BulkOption_UpdateQty failed " + t.getMessage());
			Assert.fail("speedEntryAddToPG_BulkOption_UpdateQty-Failed " + t.getMessage());
		}

		finally {
			productGroupModuleTest.deleteProductGroup("TestAutomation2");
			homePage().clickOnLogo();
		}
		log.info("----------------speedEntryAddToPG_BulkOption_UpdateQty Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke Test", "regression","copy" })
	@Description("Verify 'Copy Paste' tab")
	public void verifyCopyPasteTab() throws Exception {
		log.info("----------------verifyCopyPasteTab Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink().quickOrderPadPage().clickOnCopyPasteTab().verifyCopyPasteTab();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyCopyPasteTab failed " + t.getMessage());
			Assert.fail("verifyCopyPasteTab-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyCopyPasteTab Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke Test", "regression","copy" })
	@Description("Verify add to cart functionality in 'Copy Paste' tab")
	public void copyPasteAddToCart() throws Exception {
		log.info("----------------copyPasteAddToCart Started---------------- ");
		try {

			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePath)
					.clickOnAddToCartButtonInCopyPaste()
					.getAddedToCartCount();
			homePage()

					.clickOnViewCartIcon()

					.myCartPage()

					.verifyPartNumberInMyCart(getPartNumbersFromCSVFile(qopCSVFilePath));

			quickOrderPadPage()

					.verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("copyPasteAddToCart failed " + t.getMessage());
			Assert.fail("copyPasteAddToCart-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------copyPasteAddToCart Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "regression","copy" })
	@Description("Verify Copy Paste Add to cart Combine functionality")
	public void copyPasteAddToCart_Combine() throws Exception {
		log.info("----------------copyPasteAddToCart_Combine Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePathCombineSeperateRemove)
					.clickOnAddToCartButtonInCopyPaste()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(getPartNumbersFromCSVFile(qopCSVFilePathCombineSeperateRemove))
					.verifyQuantityOfSpecificItem(getPartNumbersFromCSVFile(qopCSVFilePathCombineSeperateRemove).get(0),
							getTotalQtyFromCSVFile(qopCSVFilePathCombineSeperateRemove));
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("copyPasteAddToCart_Combine failed " + t.getMessage());
			Assert.fail("copyPasteAddToCart_Combine-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------copyPasteAddToCart_Combine Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","copy" })
	@Description("Verify Copy Paste Add to cart Seperate functionality")
	public void copyPasteAddToCart_Seperate() throws Exception {
		log.info("----------------copyPasteAddToCart_Seperate Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePathCombineSeperateRemove)
					.clickOnSeperateButtonInCopyPaste()
					.clickOnAddToCartButtonInCopyPaste()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(
					getPartNumbersFromCSVFile(qopCSVFilePathCombineSeperateRemove));
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("copyPasteAddToCart_Seperate failed " + t.getMessage());
			Assert.fail("copyPasteAddToCart_Seperate-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------copyPasteAddToCart_Seperate Completed---------------- ");
	}

	//
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","copy" })
	@Description("Verify Copy Paste Add to cart Remove functionality")
	public void copyPasteAddToCart_Remove() throws Exception {
		log.info("----------------copyPasteAddToCart_Remove Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePathCombineSeperateRemove)
					.clickOnRemoveButtonInCopyPaste()
					.clickOnAddToCartButtonInCopyPaste()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(getPartNumbersFromCSVFile(qopCSVFilePathCombineSeperateRemove))
					.verifyQuantityOfSpecificItem(getPartNumbersFromCSVFile(qopCSVFilePathCombineSeperateRemove).get(0),
							Integer.parseInt(getQtyFromCSVFile(qopCSVFilePathCombineSeperateRemove, 1).get(0)));
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("copyPasteAddToCart_Remove failed " + t.getMessage());
			Assert.fail("copyPasteAddToCart_Remove-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------copyPasteAddToCart_Remove Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","copy" })
	@Description("Verify add to cart functionality in 'Copy Paste' tab")
	public void copyPasteAddToCart_MoreThan40Rows() throws Exception {
		log.info("----------------copyPasteAddToCart_MoreThan20Rows Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePath_MoreThan30Rows)
					.clickOnSeperateButtonInCopyPaste()
					.clickOnAddToCartButtonInCopyPaste();
			Thread.sleep(1000);
			homePage().clickOnViewCartIcon();
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount("40");
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("copyPasteAddToCart_MoreThan20Rows failed " + t.getMessage());
			Assert.fail("copyPasteAddToCart_MoreThan20Rows-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------copyPasteAddToCart_MoreThan20Rows Completed---------------- ");
	}

	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","copy" })
	@Description("Verify 'Copy Paste' alert text with no data")
	public void verifyCopyPasteTab_AlertMsgBlankText() throws Exception {
		log.info("----------------verifyCopyPasteTab_AlertMsgBlankText Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.clickOnAddToCartButtonInCopyPaste()
					.commonOperations()
					.verifyAlertTextPopUp("Please enter valid values")
					.clickOnOkButtonInAlertPopUp();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyCopyPasteTab_AlertMsgBlankText failed " + t.getMessage());
			Assert.fail("verifyCopyPasteTab_AlertMsgBlankText-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyCopyPasteTab_AlertMsgBlankText Completed---------------- ");
	}

	@Feature("Quick Order Pad Module")
	@Test(enabled = false, groups = { "Smoke", "regression","copy" })
	@Description("Verify 'Copy Paste' with less minimum order quantity")
	public void verifyCopyPasteTab_LessMinQty() throws Exception {
		log.info("----------------verifyCopyPasteTab_LessMinQty Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnCopyPasteTab()
					.copyPasteFile(qopCSVFilePath_LessMinQty)
					.clickOnAddToCartButtonInCopyPaste()
					.verifyItemsWithExceptionSectionIsDisplayed();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyCopyPasteTab_LessMinQty failed " + t.getMessage());
			Assert.fail("verifyCopyPasteTab_LessMinQty-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyCopyPasteTab_LessMinQty Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke Test", "regression","File"  })
	@Description("Verify File Upload tab")
	public void verifyFileUploadTab() throws Exception {
		log.info("----------------verifyFileUploadTab Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink().quickOrderPadPage().clickOnFileUploadTab().verifyFileUploadTab();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadTab failed " + t.getMessage());
			Assert.fail("verifyFileUploadTab-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFileUploadTab Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke Test", "regression","File"  })
	@Description("Verify File Upload Add to cart")
	public void verifyFileUploadAddToCart() throws Exception {
		log.info("----------------verifyFileUploadAddToCart Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
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
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadAddToCart failed " + t.getMessage());
			Assert.fail("verifyFileUploadAddToCart-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFileUploadAddToCart Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","File"  })
	@Description("Verify File Upload Add to cart More Than 40 Rows")
	public void verifyFileUploadAddToCart_MoreThan40Rows() throws Exception {
		log.info("----------------verifyFileUploadAddToCart_MoreThan40Rows Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnFileUploadTab()
					.uploadFile(qopFileUpload_MoreThan30Rows)
					.clickOnUpload()
					.commonOperations()
					.verifyAlertTextPopUp(
							"Uploaded file contains more than 40 items, maximum upload item count is 40 or less than 40 items.")
					.clickOnOkButtonInAlertPopUp();
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadAddToCart_MoreThan40Rows failed " + t.getMessage());
			Assert.fail("verifyFileUploadAddToCart_MoreThan40Rows-Failed " + t.getMessage());
		}

		finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFileUploadAddToCart_MoreThan20Rows Completed---------------- ");
	}

	@Feature("Quick Order Pad Module")
	@Test(enabled = false, groups = { "Smoke", "regression","File"  })
	@Description("Verify File Upload Add to cart with less minimum order quantity")
	public void verifyFileUploadAddToCart_LessMinQty() throws Exception {
		log.info("----------------verifyFileUploadAddToCart_LessMinQty Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnFileUploadTab()
					.uploadFile(qopFileUpload_LessMinQty)
					.clickOnUpload()
					.verifyItemsWithExceptionSectionIsDisplayed();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadAddToCart_LessMinQty failed " + t.getMessage());
			Assert.fail("verifyFileUploadAddToCart_LessMinQty-Failed " + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFileUploadAddToCart_LessMinQty Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "regression","File"  })
	@Description("Verify File Upload Add to cart Combine functionality")
	public void verifyFileUploadAddToCart_Combine() throws Exception {
		log.info("----------------verifyFileUploadAddToCart_Combine Started---------------- ");
		try {
			
			String[][] data = excelMethods.readDataFromExcel(qopFileUploadCombineSeperateRemove, "Sheet1");

			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnFileUploadTab()
					.uploadFile(qopFileUploadCombineSeperateRemove)
					.clickOnUpload()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(data[0][0])
					.verifyQuantityOfSpecificItem(data[0][0], getTotalQtyFromFileUpload());
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		}

		catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadAddToCart_Combine failed " + t.getMessage());
			Assert.fail("verifyFileUploadAddToCart_Combine-Failed " + t.getMessage());
		}

		finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFileUploadAddToCart_Combine Completed---------------- ");
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","File"  })
	@Description("Verify File Upload Add to cart Seperate functionality")
	public void verifyFileUploadAddToCart_Seperate() throws Exception {
		log.info("----------------verifyFileUploadAddToCart_Seperate Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			String[][] data = excelMethods.readDataFromExcel(qopFileUploadCombineSeperateRemove, "Sheet1");

			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnFileUploadTab()
					.uploadFile(qopFileUploadCombineSeperateRemove)
					.clickOnSeperateButtonInFileUpload()
					.clickOnUpload()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon().myCartPage().verifyPartNumberInMyCart(data[0][0]);
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadAddToCart_Seperate failed " + t.getMessage());
			Assert.fail("verifyFileUploadAddToCart_Seperate-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
			log.info("----------------verifyFileUploadAddToCart_Seperate Completed---------------- ");
		}
	}

	
	//completed
	@Feature("Quick Order Pad Module")
	@Test(groups = { "Smoke", "regression","File" })
	@Description("Verify File Upload Add to cart Remove functionality")
	public void verifyFileUploadAddToCart_Remove() throws Exception {
		log.info("----------------verifyFileUploadAddToCart_Remove Started---------------- ");
		try {
			// loginModule.loginAsASuperUser();
			String[][] data = excelMethods.readDataFromExcel(qopFileUploadCombineSeperateRemove, "Sheet1");
			int addedToCartCount = homePage().clickOnQuickOrderPadLink()
					.quickOrderPadPage()
					.clickOnFileUploadTab()
					.uploadFile(qopFileUploadCombineSeperateRemove)
					.clickOnRemoveButtonInFileUpload()
					.clickOnUpload()
					.getAddedToCartCount();
			homePage().clickOnViewCartIcon()
					.myCartPage()
					.verifyPartNumberInMyCart(data[0][0])
					.verifyQuantityOfSpecificItem(data[0][0], Integer.parseInt(data[0][1]));
			quickOrderPadPage().verifyCartCountEqualToAddedToCartCount(addedToCartCount);
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyFileUploadAddToCart_Remove failed " + t.getMessage());
			Assert.fail("verifyFileUploadAddToCart_Remove-Failed " + t.getMessage());
		} finally {
			clearCart();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyFileUploadAddToCart_Remove Completed---------------- ");
	}

}
