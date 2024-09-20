package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class PageFooterObjects extends PageInitializer {

	Logger log = Logger.getLogger(PageFooterObjects.class.getName());

	SoftAssert soft = new SoftAssert();

	UtilityMethods util = new UtilityMethods(getDriver());

	WaitingMethods wait = new WaitingMethods(getDriver());

	TestDataPropertyFile data = new TestDataPropertyFile();

	@FindBy(css = ".cimm_logo>a>img")
	public WebElement logoLocator;

	@FindBy(xpath = "//ul[@id='footerMenu1']/li[1]/a")
	public WebElement menu1Link1Locator;

	@FindBy(xpath = "//ul[@id='footerMenu1']/li[2]/a")
	public WebElement menu1Link2Locator;

	@FindBy(xpath = "//ul[@id='footerMenu1']/li[3]/a")
	public WebElement menu1Link3Locator;

	@FindBy(xpath = "//ul[@id='footerMenu1']/li[4]/a")
	public WebElement menu1Link4Locator;

	@FindBy(xpath = "//ul[@id='footerMenu1']/li[5]/a")
	public WebElement menu1Link5Locator;

	@FindBy(xpath = "//ul[@id='footerMenu2']/li[1]/a")
	public WebElement menu2Link1Locator;

	@FindBy(xpath = "//ul[@id='footerMenu2']/li[2]/a")
	public WebElement menu2Link2Locator;

	@FindBy(xpath = "//ul[@id='footerMenu2']/li[3]/a")
	public WebElement menu2Link3Locator;

	@FindBy(xpath = "//ul[@id='footerMenu2']/li[4]/a")
	public WebElement menu2Link4Locator;

	@FindBy(xpath = "//ul[@id='footerMenu2']/li[5]/a")
	public WebElement menu2Link5Locator;

	@FindBy(xpath = "//ul[@id='footerMenu3']/li[1]/a")
	public WebElement menu3Link1Locator;

	@FindBy(xpath = "//ul[@id='footerMenu3']/li[2]/a")
	public WebElement menu3Link2Locator;

	@FindBy(xpath = "//ul[@id='footerMenu3']/li[3]/a")
	public WebElement menu3Link3Locator;

	@FindBy(xpath = "//ul[@id='footerMenu3']/li[4]/a")
	public WebElement menu3Link4Locator;

	@FindBy(xpath = "//p[@class='cimm_footer-copyright']")
	public WebElement copyRightLocator;

	@FindBy(xpath = "//a[normalize-space(text())='Email Subscription']")
	public WebElement EmailSubscriptionLocator;

	@FindBy(xpath = "//ul[@id='footerMenu3']/li[1]")
	public WebElement signUpLocator;

	@FindBy(xpath = "//ul[@id='footerMenu3']/li[2]")
	public WebElement signUpButtonLocator;

	@FindBy(css = "#footerMenu1>li>a")
	public List<WebElement> allLinksWEBelowMenu1Actual;

	@FindBy(css = "#footerMenu2>li>a")
	public List<WebElement> allLinksWEBelowMenu2Actual;

	@FindBy(css = "#footerMenu3>li>a")
	public List<WebElement> allLinksWEBelowMenu3Actual;

	@FindBy(css = "#footerMenu4>li>a")
	public List<WebElement> addressWEActual;

	@Step("verifies the links below Menu1")
	public PageFooterObjects verifyMenu1Links() {
		// data from TestData.properties--->footerMenu1Links
		if (allLinksWEBelowMenu1Actual.size() > 0) {
			String[] allLinksWEBelowMenu1Expected = data.getfooterMenu1Links().split(">");
			String[] allLinksBelowMenu1Actual = new String[allLinksWEBelowMenu1Actual.size()];
			for (int i = 0; i < allLinksWEBelowMenu1Actual.size(); i++) {
				allLinksBelowMenu1Actual[i] = allLinksWEBelowMenu1Actual.get(i).getText();
				soft.assertEquals(allLinksBelowMenu1Actual[i], allLinksWEBelowMenu1Expected[i],
						"Menu 1 links not matched ");
				log.info("verified the menu1 links :" + allLinksWEBelowMenu1Expected[i]);
			}
		}
		soft.assertAll();
		return this;
	}

	@Step("verifies the links below Menu2")
	public PageFooterObjects verifyMenu2Links() {
		// data from TestData.properties--->footerMenu2Links
		if (allLinksWEBelowMenu2Actual.size() > 0) {
			String[] allLinksWEBelowMenu2Expected = data.getfooterMenu2Links().split(">");
			String[] allLinksBelowMenu2Actual = new String[allLinksWEBelowMenu2Actual.size()];
			for (int i = 0; i < allLinksWEBelowMenu2Actual.size(); i++) {
				allLinksBelowMenu2Actual[i] = allLinksWEBelowMenu2Actual.get(i).getText();
				soft.assertEquals(allLinksBelowMenu2Actual[i], allLinksWEBelowMenu2Expected[i],
						"Menu 2 links not matched");
				log.info("verified the menu2 links :" + allLinksWEBelowMenu2Expected[i]);
			}
		}
		soft.assertAll();
		return this;
	}

	@Step("verifies the links below Menu3")
	public PageFooterObjects verifyMenu3Links() {
		// data from TestData.properties--->footerServicesLinks
		if (allLinksWEBelowMenu3Actual.size() > 0) {
			String[] allLinksWEBelowMenu3Expected = data.getfooterMenu3Links().split(">");
			String[] allLinksBelowMenu3Actual = new String[allLinksWEBelowMenu3Actual.size()];
			for (int i = 0; i < allLinksWEBelowMenu3Actual.size(); i++) {
				allLinksBelowMenu3Actual[i] = allLinksWEBelowMenu3Actual.get(i).getText();
				soft.assertEquals(allLinksBelowMenu3Actual[i], allLinksWEBelowMenu3Expected[i],
						"Menu 3 links not matched");
				log.info("verified the menu3 links :" + allLinksWEBelowMenu3Expected[i]);
			}
		}
		soft.assertAll();
		return this;
	}

	@Step("verifies the address below contact Us")
	public PageFooterObjects verifyContactUs() {
		// data from TestData.properties--->footerAddress
		String[] addressExpected = data.getfooterAddress().split(">");
		String[] addressActual = new String[addressWEActual.size()];
		for (int i = 0; i < addressWEActual.size(); i++) {
			addressActual[i] = addressWEActual.get(i).getText();
			soft.assertEquals(addressActual[i], addressExpected[i], "Address is not matched :");
			log.info("verified the Contact Us details: " + addressExpected[i]);
		}
		soft.assertAll();
		return this;
	}

	@Step("verifies the links by clicking below footer Menu 2 ")
	public PageFooterObjects verifyTitlesOfLinksBelowMenu2(String productName) {
		// data from TestData.properties--->footerMenu2LinksTitle
		String[] allTitlesOfLinksBelowMenu2 = data.getfooterMenu2LinksTitle().split(">");

		menu2Link1Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu2[0] + "| " + productName,
				"First link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		/*menu2Link2Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu2[1] + "| " + productName,
				"Second link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu2Link3Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu2[2] + "| " + productName,
				"Third link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu2Link4Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu2[3] + "| " + productName,
				"Fourth link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu2Link5Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu2[4] + "| " + productName,
				"Fifth link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();*/

		soft.assertAll();
		return this;
	}

	@Step("verifies the links by clicking below footer Menu 3 ")
	public PageFooterObjects verifyTitlesOfLinksBelowMenu3(String productName) {
		// data from TestData.properties--->footerMenu3LinksTitle
		String[] allTitlesOfLinksBelowMenu3 = data.getfooterMenu3LinksTitle().split(">");

		logoLocator.click();
		menu3Link1Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu3[0] + "| " + productName,
				"First link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu3Link2Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu3[1] + "| " + productName,
				"Second link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu3Link3Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu3[2] + "| " + productName,
				"Third link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu3Link4Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu3[3] + "| " + productName,
				"Fourth link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		soft.assertAll();
		return this;
	}

	@Step("verifies the links by clicking below footer Menu 1 ")
	public PageFooterObjects verifyTitlesOfLinksBelowMenu1(String productName) {
		// data from TestData.properties--->footerMenu1LinksTitle
		String[] allTitlesOfLinksBelowMenu1 = data.getfooterMenu1LinksTitle().split(">");

		logoLocator.click();
		menu1Link1Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu1[0] + "| " + productName,
				"First link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu1Link2Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu1[1] + "| " + productName,
				"Second link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu1Link3Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu1[2] + "| " + productName,
				"Third link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu1Link4Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu1[3] + "| " + productName,
				"Fourth link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		menu1Link5Locator.click();
		soft.assertEquals(getDriver().getTitle(), allTitlesOfLinksBelowMenu1[4] + "| " + productName,
				"Fifth link title didnt matched :");
		log.info("verified the link :" + getDriver().getTitle());
		logoLocator.click();

		soft.assertAll();
		return this;
	}

	@Step("verifies the copy right text")
	public PageFooterObjects verifyCopyRightstext(String copyText) {
		Assert.assertEquals(copyRightLocator.getText().trim(), copyText,
				"Copyright information didnt matched :");
		log.info("Copy right text is verified ");
		return this;
	}

	@Step("verifies the email subscription section")
	public PageFooterObjects verifyEmailSubscriptionsection() {
		soft.assertTrue(util.isElementDisplayed(EmailSubscriptionLocator), "Email Subscription didnt displayed :");
		log.info("Email Subscription presence is tested");

		soft.assertTrue(util.isElementDisplayed(signUpLocator), "Sign up field didnt displayed :");
		log.info("Sign up presence is tested");

		soft.assertTrue(util.isElementDisplayed(signUpButtonLocator), "Sign up Button didnt displayed :");
		log.info("sign up presence presence is tested");

		soft.assertAll();
		return this;
	}

}
