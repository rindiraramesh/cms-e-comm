package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;

public class ClearanceModuleObects extends PageInitializer {

	UtilityMethods utilityMenthods = new UtilityMethods(getDriver());

	SoftAssert softAssert = new SoftAssert();

	Logger log = Logger.getLogger(ClearanceModuleObects.class);

	@FindBy(xpath = "//a[@href='/products']")
	public WebElement shopProductsLinkLocator;

	@FindBy(xpath = "//a[@href='/products']/../following-sibling::ul//a[text()='Clearance']")
	public WebElement clearanceLinkLocator;

	@FindBy(xpath = "//*[@class='productTitle']")
	public List<WebElement> clearanceItemsLocator;

	@Step("Verify clearance link in shop products menu")
	public ClearanceModuleObects verifyClearanceLinkBelowShopProductsMenu() {

		utilityMenthods.moveToElement(shopProductsLinkLocator);
		Assert.assertTrue(utilityMenthods.isElementDisplayed(clearanceLinkLocator), "Clearance Link is not displayed");
		log.info("Clearance Link display is verified");
		return this;
	}

	@Step("Verify clearance items after clicking clearance link")
	public ClearanceModuleObects verifyClearanceItemsAfterClickingClearance() {

		utilityMenthods.moveToElement(shopProductsLinkLocator);
		clearanceLinkLocator.click();
		log.info("Clearance Link is clicked");
		softAssert.assertEquals(getDriver().getTitle().trim().substring(0, 9), "Clearance",
				"Navigated to other than Clearance page ");
		log.info("Clearance page title display is verified");
		for (WebElement clearance : clearanceItemsLocator) {
			softAssert.assertTrue(utilityMenthods.isElementDisplayed(clearance), "Clearance is not displayed");
			log.info("Clearance items display :" + clearance.getText().trim());
		}
		log.info("Clearance items display is verified");
		return this;
	}

}
