package pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ComparePageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods utilityMethods = new UtilityMethods(getDriver());

	Logger log = Logger.getLogger(ComparePageObjects.class);

	@FindBy(xpath = "//h1")
	private WebElement pageName;

	@FindBy(xpath = "//div[@class='cimm_bodyContentWrap']/descendant::table")
	private WebElement compareTable;

	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> compareBreadCrumb;

	@FindBy(id = "showSimilar")
	private WebElement highLightSimilarButtonLocator;

	@FindBy(xpath = "//td[@class='similar active']")
	private WebElement similarAttributeLocator;

	@FindBy(id = "showDifferent")
	private WebElement highLightDifferentLocator;

	@FindBy(css = "td[class='different active']")
	private WebElement differentAttributeLocator;

	@FindBy(xpath = "//a[contains(text(),'Highlight Off')]")
	private WebElement highlightOffButton;

	@FindBy(xpath = "//td/descendant::a[@class='log-addTocart-btn addToCart']")
	private List<WebElement> addToCartButtonLocator;

	@FindBy(xpath = "(//b[contains(.,'Details')]/ancestor::td/following-sibling::td/b/descendant::span)[1]")
	private WebElement getFirstProductName;

	@FindBy(xpath = "//strong[normalize-space(text())='Part#']/../following-sibling::td//span[contains(@id,'partNumber')]")
	private List<WebElement> partNumberValuesLocator;

	@FindBy(xpath = "//a[contains(text(),'Add To Cart') and not(contains(@class,'disable'))]")
	private List<WebElement> addToCartButtonsWhichAreNotDisabledLocator;

	@FindBy(xpath = "//a[contains(@class,'disable')]")
	private List<WebElement> addToCartButtonsWhichAreDisabledLocator;

	@FindBy(xpath = "//label[@class='customCheckBox2']")
	private List<WebElement> removeCheckboxesLocator;

	@FindBy(xpath = "//a[@onclick='removeItems();']")
	private WebElement removeItemLocator;

	@FindBy(xpath = "//a[@class='comprProdTitle']/span")
	private List<WebElement> productNameLocator;

	@FindBy(xpath = "//table/descendant::a/img")
	private List<WebElement> imagesLocator;

	@Step("Verify display of compare table")
	public ComparePageObjects verifyDisplayOfCompareTable() {
		Assert.assertTrue(compareTable.isDisplayed(), "compare table is not displayed");
		log.info("verifyDisplayOfCompareTable-completed");
		return this;
	}

	@Step("Click on highlight similar button")
	public ComparePageObjects clickOnHighLightSimilar() {
		waiting.waitForVisibilityOfElement(highLightSimilarButtonLocator, 20);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", highLightSimilarButtonLocator);
		// highLightSimilarButtonLocator.click();
		log.info("Clicked on HighLight Similar button");
		return this;
	}

	@Step("Verify activation of similar properties")
	public ComparePageObjects verifyActivationOfSimilarProperties() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		Assert.assertTrue(similarAttributeLocator.isDisplayed(), "similar attribute is not enabled");
		Assert.assertEquals(similarAttributeLocator.getCssValue("background-color").trim(), "rgba(253, 253, 207, 1)");
		log.info("Verify similar proprties enabled");
		return this;
	}

	@Step("Click on highlight different button")
	public ComparePageObjects clickOnHighlightDifferent() {
		waiting.waitForVisibilityOfElement(highLightDifferentLocator, 20);
		highLightDifferentLocator.click();
		log.info("Clicked on HighLight Different button");
		return this;
	}

	@Step("Verify activation of different properties")
	public ComparePageObjects verifyActivationOfDifferentProperties() {
		waiting.waitForVisibilityOfElement(differentAttributeLocator, 10);
		Assert.assertTrue(differentAttributeLocator.isDisplayed(), "different attribute is not enabled");
		Assert.assertEquals(differentAttributeLocator.getCssValue("background-color").trim(), "rgba(250, 232, 226, 1)");
		log.info("Verify different proprties enabled");
		return this;
	}

	@Step("Click on highlight off button")
	public ComparePageObjects clickOnHighlightOffButton() {
		highlightOffButton.click();
		log.info("Clicked on HighLight Off button");
		return this;
	}

	@Step("Verify color of highlight similar text")
	public ComparePageObjects verifyColourOfHighlightSimilarButton(String colourOfHighlightSimilarButton) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(highLightSimilarButtonLocator.getCssValue("background-color").trim(),
				colourOfHighlightSimilarButton);
		log.info("verifyColourOfHighlightSimilarButton-completed");
		return this;
	}

	@Step("Verify color of show different button")
	public ComparePageObjects verifyColourOfShowDifferentButton() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Assert.assertEquals(highLightDifferentLocator.getCssValue("background-color").trim(), "rgba(207, 139, 45, 1)");
		log.info("verifyColourOfShowDifferentButton-completed");
		return this;
	}

	public boolean verifyActivationOfDifferentPropertiesIsPresent() {
		try {
			Assert.assertTrue(differentAttributeLocator.isDisplayed(), "different attribute is still enabled");
			log.info("verifyActivationOfDifferentPropertiesIsPresent-completed");
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}

	}

	@Step("Verify disable of add to cart button")
	public ComparePageObjects checkDisableOfAddToCartButton(String partNumber) {
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		getDriver().findElement(By.xpath("//a[contains(@id,'" + partNumber + "')]")).getAttribute("class").contains(
				"btns-disable");
		log.info("checkDisableOfAddToCartButton-completed");
		return this;
	}

	@Step("Verify whether thw products are {0} {1} {2}")
	public ComparePageObjects verifyWhetherAllTheProductsAreDisplayed(String productTitle1, String productTitle2,
			String productTitle3) {
		String productTitle1Name = "//td[contains(.,'Details')]/following-sibling::td/descendant::span[contains(.,'"
				+ productTitle1 + "')]";
		String productTitle2Name = "//td[contains(.,'Details')]/following-sibling::td/descendant::span[contains(.,'"
				+ productTitle2 + "')]";
		String productTitle3Name = "//td[contains(.,'Details')]/following-sibling::td/descendant::span[contains(.,'"
				+ productTitle3 + "')]";
		Assert.assertTrue(getDriver().findElement(By.xpath(productTitle1Name)).isDisplayed(),
				"First product is not getting displayed. Getting productTitle : " + productTitle1);
		Assert.assertTrue(getDriver().findElement(By.xpath(productTitle2Name)).isDisplayed(),
				"Second product is not getting displayed. Getting productTitle : " + productTitle2);
		Assert.assertTrue(getDriver().findElement(By.xpath(productTitle3Name)).isDisplayed(),
				"Third product is not getting displayed. Getting productTitle : " + productTitle3);
		log.info("Verify all products are displayed");
		return this;
	}

	@Step("Verify part numbers")
	public ComparePageObjects verifyPartNumbers(String[] partNumbers) {
		waiting.waitForVisibilityOfElements(partNumberValuesLocator, 10);
		Assert.assertTrue(assertPartNumbers(partNumbers),
				"Part Number that was there in list page is not coming in compare page.");
		log.info("verifyPartNumbers-completed");
		return this;
	}

	@Step("Verify similarities between items")
	public ComparePageObjects verifyHighlightSimilarFunctionality() {
		List<WebElement> firstColumn = getDriver().findElements(By.xpath("//td[@class='similar active'][1]"));
		List<WebElement> secondColumn = getDriver().findElements(By.xpath("//td[@class='similar active'][2]"));

		for (int i = 0; i < firstColumn.size(); i++) {
			Assert.assertEquals(firstColumn.get(i).getText().trim(), secondColumn.get(i).getText().trim());
		}
		log.info("verifyHighlightSimilarFunctionality-completed");
		return this;
	}

	@Step("Verify color of highlight different")
	public ComparePageObjects verifyColourOfHighlightDifferentButton(String colourOfHightlightDifferentButton) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Assert.assertEquals(highLightDifferentLocator.getCssValue("background-color").trim(),
				colourOfHightlightDifferentButton);
		log.info("verifyColourOfHighlightDifferentButton-completed");
		return this;

	}

	@Step("Verify highlight different items")
	public ComparePageObjects verifyHighLightDifferentFunctionality() {
		List<WebElement> firstColumn = getDriver().findElements(By.xpath("//td[@class='different active'][1]"));
		List<WebElement> secondColumn = getDriver().findElements(By.xpath("//td[@class='different active'][2]"));

		for (int i = 0; i < firstColumn.size(); i++) {
			Assert.assertNotEquals(firstColumn.get(i).getText().trim(), secondColumn.get(i).getText().trim());
		}
		log.info("verifyHighLightDifferentFunctionality-completed");
		return this;

	}

	public String getColourOfHighlightDifferentAttributes() {
		return differentAttributeLocator.getCssValue("background-color").trim();
	}

	public boolean assertHighlightOff(String colourOfHighlightDifferentAttributes) {
		try {
			return !differentAttributeLocator.getCssValue("background-color").trim().equals(
					colourOfHighlightDifferentAttributes);
		} catch (NoSuchElementException e) {
			return true;
		}

	}

	@Step("Verify highlight off functionality")
	public ComparePageObjects verifyHighlightOff(String colourOfHighlightDifferentAttributes) {
		Assert.assertTrue(assertHighlightOff(colourOfHighlightDifferentAttributes));
		log.info("verifyHighlightOff-completed");
		return this;
	}

	@Step("Verify page title,breadcrumb and other fields")
	public ComparePageObjects verifyComparePageFields(String comparePageName, String companyName) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(getDriver().getTitle().trim(), comparePageName + " | " + companyName);
		log.info("Page title tested");
		softAssert.assertEquals(pageName.getText().trim(), comparePageName);
		log.info("Page name tested");
		softAssert.assertEquals(compareBreadCrumb.get(compareBreadCrumb.size() - 1).getText().trim(), comparePageName);
		log.info("Bread crum us verified");
		softAssert.assertTrue(highLightSimilarButtonLocator.isDisplayed(),
				"High light similar button is not displayed");
		softAssert.assertTrue(highLightDifferentLocator.isDisplayed(), "High light different button is not displayed");
		softAssert.assertTrue(highlightOffButton.isDisplayed(), "High light off button is not displayed");
		softAssert.assertTrue(removeItemLocator.isDisplayed(), "Remove item button is not displayed");
		log.info("Buttons verified");
		log.info("verifyComparePageFields-completed");
		return this;
	}

	@Step("Verify part numbers")
	public boolean assertPartNumbers(String[] partNumbers) {
		for (int i = 0; i < partNumbers.length;) {
			for (int j = 0; j < partNumberValuesLocator.size(); j++)
				if (partNumbers[i].equals(partNumberValuesLocator.get(j).getText().trim()))
					return true;
			break;

		}
		log.info("Part number verified");
		return false;

	}

	@Step("Verify part numbers:{0},{1}")
	public ComparePageObjects verifyPartNumbers(String partNumbers1, String partNumbers2) {
		String[] partNumbers = { partNumbers1, partNumbers2 };
		waiting.waitForVisibilityOfElements(partNumberValuesLocator, 10);
		Assert.assertTrue(assertPartNumbers(partNumbers),
				"Part Number that was there in list page is not coming in compare page.");
		log.info("verifyPartNumbers-completed");
		return this;

	}

	@Step("Click on {0}st/nd/rd/th add to cart button")
	public ComparePageObjects clickOnSpecificAddToCartButton(int specificAddToCartButton) {
		addToCartButtonLocator.get(specificAddToCartButton - 1).click();
		log.info("Clicked on Add to Cart button");
		return this;
	}

	@Step("Click on {0}st/nd/rd/th add to cart button")
	public ComparePageObjects clickOnSpecificAddToCartButton(String partNumber) throws Exception {
		Thread.sleep(2000);
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		getDriver().findElement(By.xpath("//a[contains(@id,'" + partNumber + "')]")).click();
		log.info("Clicked on Add to Cart button");
		return this;
	}

	@Step("Click on {0}st/nd/rd/th remove checkbox")
	public ComparePageObjects clickOnSpecficRemoveCheckbox(int specificRemoveCheckbox) {
		removeCheckboxesLocator.get(specificRemoveCheckbox - 1).click();
		log.info("Clicked on Remove checkbox");
		return this;
	}

	@Step("Click on remove link")
	public ComparePageObjects clickOnRemoveItem() {
		waiting.waitForVisibilityOfElement(removeItemLocator, 10);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", removeItemLocator);
		log.info("Clicked on Remove item button");
		return this;
	}

	@Step("Verify number of checkboxes")
	public ComparePageObjects verifyNumberOfRemoveCheckboxes(int expectedNumberOfCheckboxes) {
		Assert.assertEquals(removeCheckboxesLocator.size(), expectedNumberOfCheckboxes);
		log.info("verifyNumberOfRemoveCheckboxes-completed");
		return this;
	}

	@Step("Verify product names")
	public ComparePageObjects verifyProductNames(String searchBrand) {
		for (WebElement productName : productNameLocator) {
			Assert.assertTrue(productName.getText().trim().contains(searchBrand),
					"Product name does not contain the brand name. The name of the product is "
							+ productName.getText().trim());
		}
		log.info("Product names verified");
		return this;
	}

	@Step("Get product name")
	public String getProductName(int specificProductName) {
		return productNameLocator.get(specificProductName - 1).getText().trim();
	}

	@Step("Click on {0}st/nd/rd/th image")
	public ProductsDetailsPageObjects clickOnSpecficImage(int specificImage) {
		imagesLocator.get(specificImage - 1).click();
		log.info("Clicked on Image of product");
		return productDetailsPage();
	}

	@Step("Get specific PN")
	public String getSpecificPartNumber(int specificPN) {
		return partNumberValuesLocator.get(specificPN - 1).getText();
	}

	@Step("Verify item is removed for compare")
	public ComparePageObjects verifyItemIsRemovedFromCompare(String partNumber) {
		getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		By pn = By.xpath("//span[normalize-space(text())='" + partNumber + "']");
		Assert.assertTrue(utilityMethods.isElementNotDisplayed(pn), "Item not deleted");
		log.info("verifyItemIsRemovedFromCompare-completed");
		return this;
	}

}
