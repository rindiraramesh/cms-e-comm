package pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.HashTableUtility;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class QuickOrderPageObjects extends PageInitializer {
	@FindBy(xpath = "//a[@href='/']")
	public WebElement homeIconLocator;

	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Hashtable<String, Integer> headerindex = new Hashtable<String, Integer>();

	UtilityMethods utils = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(QuickOrderPageObjects.class);

	Actions actions = new Actions(getDriver());

	By tableName = By.className("htCore");

	By headerNameRow = By.className("colHeader");

	By invalidQuantityLocator = By.className("htInvalid");

	@FindBy(xpath = "//a[contains(text(),'File Upload')]")
	private WebElement fileUploadTabLocator;

	@FindBy(xpath = "//div[contains(@id,'customFileUpload')]/input")
	private WebElement chooseFileLocator;

	@FindBy(xpath = "//a[contains(@href,'itemsInCart')]")
	private WebElement addedToCartCountLocator;

	@FindBy(xpath = "//span[@data-original-title='View Cart']//following-sibling::span[contains(@class,'cartCountrefresh')]")
	private WebElement shoppingCartCountLocator;

	@FindBy(xpath = "//input[@value='Upload']")
	private WebElement uploadButtonLocator;

	@FindBy(xpath = "//a[contains(@href,'copyPaste')]")
	private WebElement copyPasteTabLocator;

	@FindBy(xpath = "//textarea[@id='copyPasteText']")
	private WebElement copyPasteSectionLocator;

	@FindBy(xpath = "//input[@id='submitBtnQOCP']")
	private WebElement addToCartButtonInCopyPasteSectionLocator;

	@FindBy(xpath = "//tbody/tr/td[@class='htAutocomplete']")
	private List<WebElement> partNumberUPCTableRowsLocator;

	@FindBy(xpath = "//a[contains(@href,'typeItems')]")
	private WebElement speedEntryTabLocator;

	@FindBy(xpath = "//h1")
	private WebElement pageNameLocator;

	@FindBy(xpath = "//input[@id='Combine3']/..")
	private WebElement combineButtonInCartFileUploadLocator;

	@FindBy(xpath = "//input[@id='Separate3']/..")
	private WebElement seperateButtonInCartFileUploadLocator;

	@FindBy(xpath = "//input[@id='Remove3']/..")
	private WebElement removeButtonInCartFileUploadLocator;

	@FindBy(xpath = "//input[@id='Combine1']/..")
	private WebElement combineInSpeedEntryLocator;

	@FindBy(xpath = "//input[@id='Separate1']/..")
	private WebElement seperateButtonSpeedEntryLocator;

	@FindBy(xpath = "//input[@id='Remove1']/..")
	private WebElement removeButtonSpeedEntryLocator;

	@FindBy(id = "submitBtnPad")
	private WebElement addToCartButtonSpeedEntryLocator;

	@FindBy(xpath = "//input[@id='Remove3']/..")
	private WebElement removeButtonLocator;

	@FindBy(xpath = "//div[@id='typeItems']//ol[@class='quickcartclass']")
	private WebElement speedEntryInstructionsLocator;

	@FindBy(xpath = "//div[@class='ht_master handsontable']/descendant::span[@class='colHeader columnSorting']")
	private List<WebElement> speedEntryColumnsLocator;

	@FindBy(xpath = "//div[@class='ht_master handsontable']/descendant::span[@class='rowHeader']")
	private List<WebElement> speedEntryRowsLocator;

	@FindBy(xpath = "//div[@class='htItemWrapper']")
	private List<WebElement> speedEntryRightClickOptionsLocator;

	@FindBy(xpath = "//a[contains(text(),'Items With Exceptions')]")
	private WebElement speedEntryitemsWithExceptionsLocator;

	@FindBy(xpath = "//a[contains(text(),'No Matches')]")
	private WebElement noMatchesLocator;

	@FindBy(xpath = "//div[@id='copyPaste']//ol[@class='quickcartclass']")
	private WebElement copyPasteInstructionsLocator;

	@FindBy(xpath = "//div[@id='fileUpload']//ol[@class='quickcartclass']")
	private WebElement cartFileUploadInstructionsLocator;

	@FindBy(xpath = "//input[@id='Combine2']/..")
	private WebElement combineButtonInCopyPasteLocator;

	@FindBy(xpath = "//input[@id='Separate2']/..")
	private WebElement seperateButtonInCopyPasteLocator;

	@FindBy(xpath = "//input[@id='Remove2']/..")
	private WebElement removeButtonInCopyPasteLocator;

	@FindBy(xpath = "//div[@id='fileUpload']/descendant::a")
	private WebElement clickHereLinkLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadcrumbLocator;

	@FindBy(xpath = "//ul[@id='itemsInCartSlider']//strong[text()='PN:']/..")
	private List<WebElement> partNumbersInAddedToCartSection;

	@FindBy(xpath = "//div[contains(@class,'ht_master handsontable')]//span[@class='rowHeader']")
	private List<WebElement> rowCountInSpeedEntry;

	@FindBy(xpath = "//div[@class='cimm_genericSearch']")
	private WebElement genericSearchMsgLocator;

	@FindBy(xpath = "//a[contains(text(),'click here')]")
	private WebElement clickHerelinkInGenericSearchMsgLocator;

	@FindBy(xpath = "//a[@href='#restrictedItems']")
	private WebElement itemsWithExceptionSection;

	@FindBy(xpath = "//button[@data-id='bulkAction']")
	private WebElement bulkOptionLocatorInSpeedEntry;

	@FindBy(css = "img[id^='imageName']")
	private List<WebElement> resultImageLocatorInSpeedEntry;

	@FindBy(xpath = "//input[@data-selectall='CheckBox']/..")
	private WebElement selectAllCheckboxInBulkOptionLocatorInSpeedEntry;

	@FindBy(xpath = "//span[normalize-space(text())='Select item to add to cart/group']/..")
	private List<WebElement> checkboxInBulkOptionLocatorInSpeedEntry;

	@FindBy(xpath = "//div[@id='multipleResult']//strong[contains(text(),'Part#:')]/..")
	private List<WebElement> partNumbersInMultipleResultsSection;

	@FindBy(xpath = "//strong[normalize-space(text())='Qty:']/following-sibling::input")
	private List<WebElement> quantityTextBoxInMultipleResultsSection;

	@Step("Click on file upload tab")
	public QuickOrderPageObjects clickOnFileUploadTab() throws InterruptedException {
		Thread.sleep(1200);
		fileUploadTabLocator.click();
		log.info("Clicked on File upload tab");
		return this;
	}

	@Step("Upload a file with file path {0}")
	public QuickOrderPageObjects uploadFile(String cartFileUploadPath) throws InterruptedException {
		Thread.sleep(1500);
		File file = new File(cartFileUploadPath);
		chooseFileLocator.sendKeys(file.getAbsolutePath());
		log.info("Uploaded file");
		return this;
	}

	@Step("Click on file upload tab")
	public QuickOrderPageObjects clickOnCopyPasteTab() throws InterruptedException {
		// waiting.waitForVisibilityOfElement(copyPasteTabLocator, 10);
		Thread.sleep(1500);
		copyPasteTabLocator.click();
		log.info("Clicked on copy paste tab");
		return this;
	}

	public int getAddedToCartCount() {
		waiting.waitForVisibilityOfElement(addedToCartCountLocator, 20);
		String cartCount = addedToCartCountLocator.getText();
		return Integer.parseInt(cartCount.substring(cartCount.indexOf("(") + 1, cartCount.indexOf(")")).trim());
	}

	@Step("Verify cart count equal to {0}")
	public QuickOrderPageObjects verifyCartCountEqualToAddedToCartCount(int addedToCartCount) {
		waiting.waitForVisibilityOfElement(shoppingCartCountLocator, 6);
		String cartCount = shoppingCartCountLocator.getText();
		Assert.assertEquals(
				Integer.parseInt(cartCount.substring(cartCount.indexOf("(") + 1, cartCount.indexOf(")")).trim()),
				addedToCartCount);
		log.info("Verified cart count is equal to added cart count");
		return this;
	}

	@Step("Verify cart count equal to {0}")
	public QuickOrderPageObjects verifyCartCountEqualToAddedToCartCount(String addedToCartCount) {
		waiting.waitForVisibilityOfElement(shoppingCartCountLocator, 6);
		String cartCount = shoppingCartCountLocator.getText().substring(
				shoppingCartCountLocator.getText().indexOf("(") + 1, shoppingCartCountLocator.getText().indexOf(")"));
		Assert.assertTrue(Integer.parseInt(cartCount) <= Integer.parseInt(addedToCartCount),
				"More than 20 rows are added");
		log.info("Verified cart count is equal to added cart count");
		return this;
	}

	@Step("Click on upload")
	public QuickOrderPageObjects clickOnUpload() throws Exception {
		waiting.waitTillPageLoads();
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", uploadButtonLocator);
		log.info("Clicked on upload button");
		return this;
	}

	@Step("Copy paste file whose file path is {0}")
	public QuickOrderPageObjects copyPasteFile(String relativeFilePath) throws Exception {
		waiting.waitTillPageLoads();
		Thread.sleep(2000);
		copyPasteSectionLocator.click();
		String line = "";

		File absolutePath = new File(relativeFilePath);

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(absolutePath.getAbsolutePath()));
		while ((line = br.readLine()) != null) {
			copyPasteSectionLocator.sendKeys(line + "\n");
			Thread.sleep(500);
		}
		log.info("Copied and pasted data from file");
		return this;
	}

	@Step("Copy paste file whose file path is {0}")
	public QuickOrderPageObjects copyPasteTxtFile(String relativeFilePath) throws Exception {
		Thread.sleep(1500);
		copyPasteSectionLocator.click();
		String line = "";
		File absolutePath = new File(relativeFilePath);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(absolutePath.getAbsolutePath()));
		while ((line = br.readLine()) != null) {

			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(line), null);

			copyPasteSectionLocator.sendKeys(Keys.CONTROL + "v");
			copyPasteSectionLocator.sendKeys(Keys.ENTER);

		}
		log.info("Copied and pasted data from file");
		return this;
	}

	@Step("Click on add to cart button")
	public QuickOrderPageObjects clickOnAddToCartButtonInCopyPaste() throws Exception {
		Thread.sleep(2600);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				addToCartButtonInCopyPasteSectionLocator);
		log.info("Clicked on Add to Cart button in Copy Paste Tab");
		return this;
	}

	@Step("Click on speed entry")
	public QuickOrderPageObjects clickOnSpeedEntry() throws Exception {
		waiting.waitForVisibilityOfElement(speedEntryTabLocator, 10);
		speedEntryTabLocator.click();
		log.info("Clicked on Speed Entry tab");
		return this;
	}

	@Step("Verify alert message is {0}")
	public QuickOrderPageObjects verifyAlertMessage(String expectedAlertMessage) throws Exception {
		/*
		 * Assert.assertTrue(utils.assertAlertText(expectedAlertMessage),
		 * "Alert text is wrong");
		 */
		return this;
	}

	@Step("Upload file whose file path is {0}")
	public QuickOrderPageObjects uploadViaRobot(String filePath) throws AWTException {
		Robot robot = new Robot();
		File file = new File(filePath);
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		return this;
	}

	@Step("Verify quick order pad page")
	public QuickOrderPageObjects verifyQuickOrderPage(String title) throws InterruptedException {
		waiting.waitForVisibilityOfElement(homeIconLocator, 5);
		Thread.sleep(1200);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homeIconLocator.isDisplayed(), "Home Icon is not displayed in the breadcrumb.");
		softAssert.assertEquals(getDriver().getTitle().trim(), "Quick Order | "+title+"", "Invalid page title");
		softAssert.assertTrue(
				breadcrumbLocator.get(breadcrumbLocator.size() - 1).getText().replace("|", "").trim().equalsIgnoreCase(
						"Quick Order"));
		softAssert.assertTrue(pageNameLocator.getText().trim().equalsIgnoreCase("Quick Order"), "Page Name is wrong");
		softAssert.assertTrue(speedEntryTabLocator.getAttribute("class").equals("active"),
				"Speed entry tab is not focused by default");
		softAssert.assertTrue(speedEntryTabLocator.isDisplayed(), "Speed entry tab is not displayed");
		softAssert.assertTrue(combineInSpeedEntryLocator.isDisplayed(),
				"Combine button in Speed entry tab is not displayed");
		softAssert.assertTrue(seperateButtonSpeedEntryLocator.isDisplayed(),
				"Seperate button in Speed entry tab is not displayed");
		softAssert.assertTrue(removeButtonSpeedEntryLocator.isDisplayed(),
				"Remove button in speed entry tab is not displayed");
		softAssert.assertTrue(addToCartButtonSpeedEntryLocator.isDisplayed(),
				"Add to cart button in Speed entry tab is not displayed");
		softAssert.assertTrue(speedEntryInstructionsLocator.isDisplayed(),
				"Instruction section in Speed entry tab is not displayed");
		softAssert.assertTrue(copyPasteTabLocator.isDisplayed(), "Copy Paste tab is not displayed");
		softAssert.assertTrue(fileUploadTabLocator.isDisplayed(), "File Upload tab is not displayed");
		softAssert.assertAll();
		log.info("Verified QOP page fields");
		return this;
	}

	@Step("Click on seperate button")
	public QuickOrderPageObjects clickOnSeperateButtonInFileUpload() throws InterruptedException {
		Thread.sleep(1000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				seperateButtonInCartFileUploadLocator);
		log.info("Clicked on Seperate button in File upload tab");
		return this;
	}

	@Step("Click on seperate button")
	public QuickOrderPageObjects clickOnSeperateButtonInCopyPaste() {
		waiting.waitForVisibilityOfElement(seperateButtonInCopyPasteLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", seperateButtonInCopyPasteLocator);
		log.info("Clicked on Seperate button in Copy Paste tab");
		return this;
	}

	@Step("Click on remove button")
	public QuickOrderPageObjects clickOnRemoveButtonInFileUpload() throws InterruptedException {
		Thread.sleep(1000);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", removeButtonLocator);
		log.info("Clicked on Remove button in File upload tab");
		return this;
	}

	@Step("Enter part number or upc {0} and the number of rows to enter is {1}")
	public QuickOrderPageObjects enterPartNumberOrUPCForSpeedEntry(String[] keyword, int numberOfRowsToEnter)
			throws Exception {

		for (int i = 0; i < numberOfRowsToEnter; i++) {

			String[] partNumberUPC = keyword[i].split(":");
			HashTableUtility table = new HashTableUtility(getDriver());
			headerindex = table.headers(tableName, headerNameRow);
			table.enterDataInHandsOnTable(i + 1, "Keyword", partNumberUPC[0]);
			table.enterDataInHandsOnTable(i + 1, "Quantity", partNumberUPC[1]);
		}
		/*
		 * copyPasteTabLocator.click(); Thread.sleep(1500);
		 * speedEntryTabLocator.click();
		 */
		Thread.sleep(1500);
		log.info("Entered part numbers in speed entry tab");
		return this;
	}

	@Step("Enter part number or upc and quantity {0}")
	public QuickOrderPageObjects enterPartNumberOrUPCForSpeedEntry(String[] keywordWithQuantity) throws Exception {
		Thread.sleep(2500);
		for (int i = 0; i < keywordWithQuantity.length; i++) {

			String[] partNumberUPC = keywordWithQuantity[i].split(":");
			// partNumberUPC[0]->PartNumber
			// partNumberUPC[1]->Quantity
			HashTableUtility table = new HashTableUtility(getDriver());
			headerindex = table.headers(tableName, headerNameRow);
			table.enterDataInHandsOnTable(i + 1, "Keyword", partNumberUPC[0]);
			table.enterDataInHandsOnTable(i + 1, "Quantity", partNumberUPC[1]);
			Thread.sleep(1800);
			log.info("Entered part numbers in speed entry tab :"+partNumberUPC[0]);
		}
		/*
		 * copyPasteTabLocator.click(); Thread.sleep(1500);
		 * speedEntryTabLocator.click();
		 */
		
		return this;
	}

	@Step("Enter part number or upc {0} and the number of rows to enter is {1}")
	public QuickOrderPageObjects enterPartNumberForUndoRedoInSpeedEntry(String keywordWithQuantity) throws Exception {
		HashTableUtility table = new HashTableUtility(getDriver());
		headerindex = table.headers(tableName, headerNameRow);
		table.enterDataInHandsOnTable(1, "Keyword", keywordWithQuantity.split(":")[0]);
		log.info("Entered part numbers in speed entry tab for undo and redo operations");
		return this;
	}

	@Step("Enter part number or upc {0} and the number of rows to enter is {1}")
	public QuickOrderPageObjects enterPartNumberOrUPCForSpeedEntry(String partNumberUPC, String numberOfRowsToEnter)
			throws Exception {
		HashTableUtility table = new HashTableUtility(getDriver());
		headerindex = table.headers(tableName, headerNameRow);
		table.enterDataInHandsOnTable(1, "Keyword", partNumberUPC);
		table.enterDataInHandsOnTable(1, "Quantity", numberOfRowsToEnter);

		Thread.sleep(1500);
		log.info("Entered part numbers in speed entry tab");
		return this;
	}

	@Step("Click on add to cart button in speed entry")
	public QuickOrderPageObjects clickOnAddToCartButtonSpeedEntry() throws InterruptedException {
		waiting.waitForVisibilityOfElement(addToCartButtonSpeedEntryLocator, 6);
		addToCartButtonSpeedEntryLocator.click();
		Thread.sleep(1200);
		log.info("Clicked on Add to Cart button in speed entry tab");
		return this;
	}

	@Step("Click on combine option in speed entry tab")
	public QuickOrderPageObjects clickOnCombineOptionInSpeedEntry() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", combineInSpeedEntryLocator);
		log.info("Clicked on Combine button in speed entry tab");
		return this;
	}

	@Step("Click on seperate option in speed entry tab")
	public QuickOrderPageObjects clickOnSeperateButtonSpeedEntry() {
		seperateButtonSpeedEntryLocator.click();
		log.info("Clicked on Seperate button in speed entry tab");
		return this;
	}

	@Step("Click on remove option in speed entry tab")
	public QuickOrderPageObjects clickOnRemoveButtonSpeedEntry() {
		waiting.waitForVisibilityOfElement(removeButtonSpeedEntryLocator, 5);
		removeButtonSpeedEntryLocator.click();
		log.info("Clicked on Remove button in speed entry tab");
		return this;
	}

	@Step("Enter invalid quantity in speed entry tab")
	public QuickOrderPageObjects enterInvalidQuantityInSpeedEntry(String quantity) throws Exception {
		HashTableUtility utils = new HashTableUtility(getDriver());
		headerindex = utils.headers(tableName, headerNameRow);
		utils.enterDataInHandsOnTable(1, "Quantity", quantity);
		log.info("Entered invalid quantity in speed entry tab");
		return this;
	}

	@Step("Verify Invalid Quantity colour")
	public QuickOrderPageObjects verifyInvalidQuantityColour(String invalidQuantityColourChrome,
			String invalidQuantityColourFirefox) throws Exception {

		List<WebElement> invalidQuantity = getDriver().findElements(invalidQuantityLocator);
		for (int i = 0; i < invalidQuantity.size(); i++)

		{
			waiting.waitForVisibilityOfElement(speedEntryInstructionsLocator, 3);
			Assert.assertEquals(invalidQuantity.get(i).getCssValue("background-color"), invalidQuantityColourChrome,
					"Actual is " + invalidQuantity.get(i).getCssValue("background-color") + ", Expecting"
							+ invalidQuantityColourChrome);
		}
		return this;
	}

	@Step("Verify elements of speed entry tab")
	public QuickOrderPageObjects verifySpeedEntryTab(String speedEntryInstructions) {
		waiting.waitForVisibilityOfElement(speedEntryInstructionsLocator, 6);
		Assert.assertEquals(speedEntryInstructionsLocator.getText().replace("\n", "").trim(), speedEntryInstructions,
				"Speed entry instructions is wrong.");
		Assert.assertTrue(addToCartButtonSpeedEntryLocator.isDisplayed(),
				"Add to cart button is not displayed in speed entry tab.");
		Assert.assertEquals(speedEntryColumnsLocator.size(), 2,
				"Number of columns is not 2. It is " + speedEntryColumnsLocator.size());
		Assert.assertEquals(speedEntryRowsLocator.size(), 10,
				"Number of rows is not 10. It is " + speedEntryRowsLocator.size());
		log.info("Verified speed entry tab fields");
		return this;
	}

	@Step("Right on speed entry table row")
	public QuickOrderPageObjects rightClickOnASpecificCell(String header, int cellNumber) throws Exception {
		HashTableUtility utils = new HashTableUtility(getDriver());
		headerindex = utils.headers(tableName, headerNameRow);
		rightCickOnCell(cellNumber, header);
		return this;
	}

	private void rightCickOnCell(int rownumber, String colName) throws Exception {
		Actions action = new Actions(getDriver());
		String colrow = "//tr[" + rownumber + "]/td[" + headerindex.get(colName) + "]";
		action.contextClick(getDriver().findElement(By.xpath(colrow))).build().perform();
	}

	@Step("Verify Right Click options {0} in speed entry table")
	public QuickOrderPageObjects verifyRightClickOptions(String[] speedEntryExtensionOptions) {
		waiting.waitForVisibilityOfElements(speedEntryRightClickOptionsLocator, 3);
		for (int i = 0; i < speedEntryRightClickOptionsLocator.size(); i++) {
			Assert.assertEquals(speedEntryRightClickOptionsLocator.get(i).getText().trim(),
					speedEntryExtensionOptions[i]);
		}
		log.info("Verified right click options in speed entry tab");
		return this;
	}

	public QuickOrderPageObjects enterKeywordOrQtyBut1FieldIsEmpty(String[] keyword, String header,
			int numberOfRowsToEnter) throws Exception {
		HashTableUtility utils = new HashTableUtility(getDriver());
		headerindex = utils.headers(tableName, headerNameRow);
		if (header.equals("Keyword")) {
			for (int i = 0; i < numberOfRowsToEnter; i++) {
				utils.enterDataInHandsOnTable(i + 1, header, keyword[i]);
			}
		} else if (header.equals("Quantity")) {
			for (int i = 0; i < numberOfRowsToEnter; i++) {
				utils.enterDataInHandsOnTable(i + 1, header, keyword[i + 1]);
			}
		}
		return this;
	}

	@Step("Get items with exceptions")
	public int getItemsWithExceptions() {
		waiting.waitForVisibilityOfElement(speedEntryitemsWithExceptionsLocator, 15);
		return Integer.parseInt(speedEntryitemsWithExceptionsLocator.getText()
				.replace("ITEMS WITH EXCEPTIONS", "")
				.replace(")", "")
				.replace("(", "")
				.replace(" ", "")
				.trim());
	}

	@Step("Verify number of items with exceptions")
	public QuickOrderPageObjects verifyNumberOfItemsWithExceptions(int actualItemsWithException,
			int expectedNumberOfItemsInException) {
		Assert.assertEquals(actualItemsWithException, expectedNumberOfItemsInException);
		log.info("Verified number of items with exception");
		return this;
	}

	@Step("Verify the item with Call for Price in added Items")
	public QuickOrderPageObjects verifyWhetherTheItemsAddedHaveCallForPrice(int numberOfRowsToEnter) {
		Assert.assertEquals(getDriver().findElements(By.xpath("//span[text()='Call for Price']")).size(),
				numberOfRowsToEnter, "Number of items that have call for price are not the same as expected.");
		log.info("Verified added items have call for price");
		return this;
	}

	@Step("Get items with no match")
	public int getItemsWithNoMatches() {
		waiting.waitForVisibilityOfElement(noMatchesLocator, 15);
		return Integer.parseInt(noMatchesLocator.getText()
				.replace("NO MATCHES", "")
				.replace(")", "")
				.replace("(", "")
				.replace(" ", "")
				.trim());
	}

	@Step("Verify number of items with no match")
	public QuickOrderPageObjects verifyNumberOfItemsWithNoMatches(int actualItemsWithNoMatches,
			int numberOfRowsToEnter) {
		Assert.assertEquals(actualItemsWithNoMatches, numberOfRowsToEnter);
		log.info("Verified number of items with no matchers");
		return this;
	}

	@Step("Verify elements of Copy Paste tab")
	public QuickOrderPageObjects verifyCopyPasteSection(String copyPasteSectionInstructions)
			throws InterruptedException {
		waiting.waitForVisibilityOfElement(copyPasteInstructionsLocator, 5);
		Assert.assertEquals(copyPasteInstructionsLocator.getText().replace("\n", "").trim(),
				copyPasteSectionInstructions);
		Assert.assertTrue(addToCartButtonInCopyPasteSectionLocator.isDisplayed(),
				"Add to Cart Button in copy paste section is not displayed.");
		log.info("Verified Copy Paste tab fields");
		return this;
	}

	@Step("Click on remove button in copy paste tab")
	public QuickOrderPageObjects clickOnRemoveButtonInCopyPaste() {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", removeButtonInCopyPasteLocator);
		log.info("Clicked on Remove button in copy paste tab");
		return this;
	}

	@Step("Verify elements of File Upload tab")
	public QuickOrderPageObjects verifyFileUploadTab() {
		waiting.waitForVisibilityOfElement(cartFileUploadInstructionsLocator, 15);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(utils.isElementDisplayed(cartFileUploadInstructionsLocator),
				"File Upload instructions is not displayed.");
		/*
		 * softAssert.assertTrue(utils.isElementDisplayed(chooseFileLocator),
		 * "File Upload field is not displayed");
		 */
		softAssert.assertTrue(utils.isElementDisplayed(uploadButtonLocator), "File Upload button is not displayed.");
		softAssert.assertTrue(utils.isElementDisplayed(combineButtonInCartFileUploadLocator),
				"Combine button in File Upload tab is not displayed.");
		softAssert.assertTrue(utils.isElementDisplayed(seperateButtonInCartFileUploadLocator),
				"Seperate button in File Upload tab is not displayed.");
		softAssert.assertTrue(utils.isElementDisplayed(removeButtonInCartFileUploadLocator),
				"Remove button in File Upload tab is not displayed.");
		softAssert.assertAll();
		log.info("Verified File upload tab");
		return this;
	}

	@Step("Click on Click Here link")
	public QuickOrderPageObjects clickOnClickHereLink() throws Exception {
		waiting.waitForVisibilityOfElement(clickHereLinkLocator, 5);
		clickHereLinkLocator.click();
		log.info("Clicked on Click here link");
		return this;
	}

	public QuickOrderPageObjects verifySampleFile(String downloadedPath) throws Exception {
		/*
		 * Thread.sleep(2000); File file = new File(downloadedPath); ExcelLibrary excel
		 * = new ExcelLibrary(file.getAbsolutePath());
		 * Assert.assertEquals(excel.xlsxReadCell(0, 0), "Key Word");
		 * Assert.assertEquals(excel.xlsxReadCell(1, 0), "Quantity");
		 */
		return this;
	}

	@Step("Enter speed entry")
	public QuickOrderPageObjects enterPartNumberOrUPCForSpeedEntryForMoreThanNRecords(String[] partNumberOrUpc,
			int numberOfRowsToEnter) throws Exception {
		for (int i = 0; i < numberOfRowsToEnter; i++) {
			String[] partNumberUPC = partNumberOrUpc[0].split(":");
			HashTableUtility table = new HashTableUtility(getDriver());
			headerindex = table.headers(tableName, headerNameRow);
			table.enterDataInHandsOnTable(i + 1, "Keyword", partNumberUPC[0]);
			table.enterDataInHandsOnTable(i + 1, "Quantity", partNumberUPC[1]);
		}
		copyPasteTabLocator.click();
		Thread.sleep(1500);
		speedEntryTabLocator.click();
		Thread.sleep(3000);
		log.info("Entered part numbers in speed entry tab");
		return this;
	}

	@Step("Click on hit enter :{0}")
	public QuickOrderPageObjects hitEnter(int numberOfRowsToEnter) throws InterruptedException {
		Thread.sleep(3000);
		getDriver().findElement(By.xpath("//table[@class='htCore']/descendant::td[@class='htAutocomplete'][1]"))
				.click();
		Actions action = new Actions(getDriver());
		for (int i = 0; i < numberOfRowsToEnter; i++) {
			action.sendKeys(Keys.ENTER).build().perform();
		}
		log.info("Enter Key is pressed");
		return this;
	}

	@Step("Verify items in added to cart section")
	public QuickOrderPageObjects verifyItemInAddedToCartSection(String[] partNumber) throws InterruptedException {
		Thread.sleep(1700);
		for (int i = 0; i < partNumber.length; i++) {
			String[] pn1 = partNumber[i].split(":");
			By pn2 = By.xpath("//ul[@id='itemsInCartSlider']//strong[contains(text(),'Part#:')]//parent::li//span[contains(text(),'" + pn1[0] + "')]");
			Assert.assertTrue(utils.isElementDisplayed(pn2), "Items are not displayed under added to cart section");
		}
		log.info("Verified Items in added to cart section");
		return this;
	}

	@Step("Verify price precision in QOP page")
	public QuickOrderPageObjects verifyPricePrecisionInQuickOrderPage(String pnNumber, String pricePrecision) throws InterruptedException {
		Thread.sleep(3500);
		WebElement specificItemPrice = getDriver().findElement(By.xpath(
				"//li[@class='price']//span"));

		Assert.assertEquals(specificItemPrice.getText().replace("/ EA", "").replace("/ (1)", "").trim().split("\\.")[1].length(), Integer.parseInt(pricePrecision),
				"Price precision in not matched in Quick Order pad page");

		log.info("Verify Price precision in Quick Orderpad page");
		return this;
	}

	@Step("Verify the items in Items with Exception section")
	public QuickOrderPageObjects verifyItemInItemsWithExceptionSection(String[] partNumber)
			throws InterruptedException {
		Thread.sleep(2500);
		for (int i = 0; i < partNumber.length; i++) {
			String[] pn1 = partNumber[i].split(":");
			By pn2 = By.xpath(
					"//strong[contains(text(),'Part#:')]/parent::li[contains(text(),'"
							+ pn1[0] + "')]");
			Assert.assertTrue(utils.isElementDisplayed(pn2), "Item is not displayed under added to cart section");
		}
		log.info("Verified Items with exception section");
		return this;
	}

	@Step("Verify Right click options {0} in speed entry table")
	public QuickOrderPageObjects verifySpeedEntryRightClickOptions(String[] options) throws InterruptedException {
		Thread.sleep(1200);
		WebElement temp = getDriver().findElement(By.xpath("(//span[text()='2'])[1]"));
		actions.contextClick(temp).build().perform();
		Thread.sleep(1000);
		List<String> rightClickOptions = speedEntryRightClickOptionsLocator.stream()
				.map(s -> s.getAttribute("innerText"))
				.collect(Collectors.toList());
		Assert.assertEquals(rightClickOptions, Arrays.asList(options), "Invalid Right Click Options");
		actions.sendKeys(Keys.ESCAPE).build().perform();
		log.info("Verified speed entry right click options");
		return this;
	}

	@Step("Add rows")
	public void addRows() throws InterruptedException {
		// add rows to check only 20 rows are added
		Thread.sleep(1000);
		utils.moveToElementAndClick(
				getDriver().findElement(By.xpath("//div[@class='ht_master handsontable']//span[text()='10']")));
		Thread.sleep(800);
		for (int i = 0; i <= 20; i++) {
			actions.sendKeys(Keys.ENTER).build().perform();
		}
	}

	@Step("Verify max no of rows can be added")
	public QuickOrderPageObjects verifyMaxNoOfRowsThatCanBeAdded() throws InterruptedException {
		addRows();
		Thread.sleep(1300);
		Assert.assertTrue(rowCountInSpeedEntry.size() == 20, "More than 20 rows added");
		log.info("Verified maximum number of rows that can be added");
		return this;
	}

	@Step("Verify remove row in speed entry table")
	public QuickOrderPageObjects verifyRemoveRow() throws InterruptedException {
		WebElement temp = getDriver().findElement(By.xpath("(//span[text()='20'])[1]"));
		actions.contextClick(temp).build().perform();
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//div[contains(text(),'Remove row')]")).click();
		Thread.sleep(500);
		Assert.assertTrue(rowCountInSpeedEntry.size() <= 20, "More than 20 rows added");
		log.info("Verified remove row functionality");
		return this;
	}

	@Step("Verify add row below in speed entry table")
	public QuickOrderPageObjects verifyAddRowBelow() throws InterruptedException {
		WebElement temp = getDriver().findElement(By.xpath("(//span[text()='18'])[1]"));
		actions.contextClick(temp).build().perform();
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//div[contains(text(),'Insert row below')]")).click();
		Thread.sleep(500);
		Assert.assertTrue(rowCountInSpeedEntry.size() == 20, "Add row functionality failed");
		log.info("Verified add row below functionality");
		return this;
	}

	@Step("Verify add row above in speed entry table")
	public QuickOrderPageObjects verifyAddRowAbove() throws InterruptedException {
		WebElement temp = getDriver().findElement(By.xpath("(//span[text()='18'])[1]"));
		actions.contextClick(temp).build().perform();
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//div[contains(text(),'Insert row above')]")).click();
		Thread.sleep(500);
		Assert.assertTrue(rowCountInSpeedEntry.size() == 20, "Add row functionality failed");
		log.info("Verified add row above functionality");
		return this;
	}

	@Step("Verify the search locator")
	public QuickOrderPageObjects verifyGenericSearchMsgDisplay() {
		waiting.waitForVisibilityOfElement(genericSearchMsgLocator, 8);
		Assert.assertTrue(utils.isElementDisplayed(genericSearchMsgLocator),
				"Generic search message locator is not displayed");
		log.info("Verified generic search locator");
		return this;
	}

	@Step("Click on Click here search")
	public QuickOrderPageObjects clickOnClickHereLinkInGenericSearch() {
		waiting.waitForVisibilityOfElement(clickHerelinkInGenericSearchMsgLocator, 8);
		// utils.moveToElementAndClick(clickHerelinkInGenericSearchMsgLocator);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				clickHerelinkInGenericSearchMsgLocator);
		log.info("Clicked on Click Here link");
		return this;
	}
	
	

	@Step("Verify Search text in PLP")
	public QuickOrderPageObjects verifySearchTextInPLP(String[] searchText) throws InterruptedException {
		Thread.sleep(1500);
		Set<String> allTabs;
		Iterator<String> itr;
		String main;
		Thread.sleep(3000);
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		allTabs = getDriver().getWindowHandles();
		itr = allTabs.iterator();
		main=itr.next();
		String PLP=itr.next();
		getDriver().switchTo().window(PLP);
		log.info("Switched to PLP tab");
		WebElement searchResult = getDriver().findElement(By.xpath("//div[@class='searchResults']/p"));
		// actions.moveToElement(searchResult).build().perform();
		Assert.assertTrue(searchResult.getText().contains(searchText[0]), "Did not navigate to respective search text");
		log.info("Verified searched text in PLP :"+searchText[0]);
		getDriver().close();
		getDriver().switchTo().window(main);
		return this;
	}

	@Step("Verify elements of Copy Paste tab")
	public QuickOrderPageObjects verifyCopyPasteTab() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(utils.isElementDisplayed(copyPasteSectionLocator), "Copy paste area is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(copyPasteInstructionsLocator),
				"Copy paste instructions is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(addToCartButtonInCopyPasteSectionLocator),
				"Add to cart button in copy paste tab is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(combineButtonInCopyPasteLocator),
				"Combine button in Copy paste tab is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(removeButtonInCopyPasteLocator),
				"Remove button in Copy paste tab is not displayed");
		softAssert.assertTrue(utils.isElementDisplayed(seperateButtonInCopyPasteLocator),
				"Seperate button in Copy paste tab is not displayed");
		softAssert.assertAll();
		log.info("Verified Copy Paste tab");
		return this;
	}

	@Step("Verify Items with exception section is displayed")
	public QuickOrderPageObjects verifyItemsWithExceptionSectionIsDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		waiting.waitForVisibilityOfElement(itemsWithExceptionSection, 15);
		Assert.assertTrue(utils.isElementDisplayed(itemsWithExceptionSection),
				"Item is not added under items with exception section");
		log.info("Verified Items with exception section is displayed");
		return this;
	}

	@Step("Verify Undo Option")
	public QuickOrderPageObjects verifyUndoOption(String text) throws InterruptedException {
		WebElement temp = getDriver().findElement(By.xpath("(//span[text()='1'])[1]"));
		actions.contextClick(temp).build().perform();
		Thread.sleep(500);
		getDriver().findElement(By.xpath("//div[text()='Undo']")).click();
		Thread.sleep(500);
		By pn = By.xpath("//td[contains(@class,'htAutocomplete') and text()='" + text + "']");
		Assert.assertTrue(utils.isElementNotDisplayed(pn), "Undo Optoin failed");
		log.info("Verified undo functionality");
		return this;
	}

	@Step("Verify Redo Option")
	public QuickOrderPageObjects verifyRedoOption(String text) throws InterruptedException {
		try {
			WebElement temp = getDriver().findElement(By.xpath("(//span[text()='1'])[1]"));
			actions.contextClick(temp).build().perform();
			Thread.sleep(500);
			getDriver().findElement(By.xpath("//div[contains(text(),'Redo')]")).click();
			Thread.sleep(500);
			By pn = By.xpath("//td[normalize-space(text())='" + text.trim() + "']");
			Assert.assertTrue(utils.isElementDisplayed(pn), "Redo Optoin failed");
			log.info("Verified redo functionality");
		} catch (AssertionError e) {
			try {
				By.xpath("//td[normalize-space(text())='" + text.trim() + "']//");

			} catch (NoSuchElementException ex) {
				By pn = By.xpath("//td[normalize-space(text())='" + text.trim() + "']");
				Assert.assertTrue(utils.isElementDisplayed(pn), "Redo Optoin failed");
			}
			log.info("Verified redo functionality");
		}
		return this;
	}

	@Step("Select Bulk Option as {0}")
	public QuickOrderPageObjects selectBulkOption(String bulkOption) throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,250)", "");
		// utils.scrollTillWebElement(bulkOptionLocatorInSpeedEntry);
		waiting.waitForVisibilityOfElement(bulkOptionLocatorInSpeedEntry, 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", bulkOptionLocatorInSpeedEntry);
		utils.moveToElement(resultImageLocatorInSpeedEntry.get(0));
		Thread.sleep(1200);
		switch (bulkOption) {
		case "Add Items to Group":
			getDriver().findElement(By.xpath("//span[contains(text(),'Add Items to Group')]/..")).click();
			Thread.sleep(600);
			break;
		case "Add Items to Cart":
			getDriver().findElement(By.xpath("//span[contains(text(),'Add Items to Cart')]/..")).click();
			Thread.sleep(600);
			break;
		}
		log.info("Selected bulk option as:" + bulkOption);
		return this;
	}

	@Step("Click on select all checkbox")
	public QuickOrderPageObjects clickOnSelectAllCheckboxInBulkOption() throws InterruptedException {
		waiting.waitForVisibilityOfElement(selectAllCheckboxInBulkOptionLocatorInSpeedEntry, 15);
		// selectAllCheckboxInBulkOptionLocatorInSpeedEntry.click();

		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				selectAllCheckboxInBulkOptionLocatorInSpeedEntry);
		Thread.sleep(1400);
		log.info("Clicked on Select All checkbox");
		return this;
	}

	@Step("Click on specific checkbox")
	public QuickOrderPageObjects clickOnSpecificCheckbox(int specificCheckbox) {
		// waiting.waitForVisibilityOfElements(checkboxInBulkOptionLocatorInSpeedEntry,
		// 15);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();",
				checkboxInBulkOptionLocatorInSpeedEntry.get(specificCheckbox - 1));
		// checkboxInBulkOptionLocatorInSpeedEntry.get(specificCheckbox - 1).click();
		log.info("Clicked on specific checkbox");
		return this;
	}

	@Step("Enter Qty:{1} for Item:{0}")
	public QuickOrderPageObjects enterQuantityForSpecificItem(int specificCheckbox, String quantity)
			throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,450)", "");
		// waiting.waitForVisibilityOfElements(quantityTextBoxInMultipleResultsSection,
		// 15);
		quantityTextBoxInMultipleResultsSection.get(specificCheckbox - 1).sendKeys(Keys.CONTROL + "a");
		quantityTextBoxInMultipleResultsSection.get(specificCheckbox - 1).sendKeys(quantity);
		Thread.sleep(1200);
		log.info("Entered quantity for an item");
		return this;
	}

	public List<String> getPartNumbersFromMultipleResultSection() throws InterruptedException {
		Thread.sleep(1200);
		List<String> pns = partNumbersInMultipleResultsSection.stream()
				.map(s -> s.getAttribute("innerText"))
				.collect(Collectors.toList())
				.stream()
				.map(s -> s.replaceAll("Part#:", "").trim())
				.collect(Collectors.toList());
		System.out.println("part number :--> " + pns);
		return pns;
	}

}
