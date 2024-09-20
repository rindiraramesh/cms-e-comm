package pageobjects;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utilities.TestDataPropertyFile;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class SocialMediaObjects extends PageInitializer {

	Logger log = Logger.getLogger(HomePageObjects.class);
	
	TestDataPropertyFile data = new TestDataPropertyFile();

	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods util = new UtilityMethods(getDriver());
	
	SoftAssert soft = new SoftAssert();
	
	@FindBy(css = ".socilaIcons>a:first-child")
	private WebElement socialMedia1;
	
	@FindBy(css = ".socilaIcons>a:nth-child(2)")
	private WebElement socialMedia2;
	
	@FindBy(css = ".socilaIcons>a:nth-child(3)")
	private WebElement socialMedia3;
	
	@FindBy(css = ".socilaIcons>a:nth-child(4)")
	private WebElement socialMedia4;
	
	
	@Step("verifies the social media section")
	public SocialMediaObjects verifySocialMediasection() throws Throwable {
		String[] urls = data.getSocialMediaData().split(">");
		Set<String> allTabs;
		Iterator<String> itr;
		String main;
		soft.assertTrue(util.isElementDisplayed(socialMedia1), "Social media link 1 didn't displayed :");
		log.info("Social media link 1 presence is tested");
		socialMedia1.click();
		Thread.sleep(3000);
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		allTabs = getDriver().getWindowHandles();
		itr = allTabs.iterator();
		main=itr.next();
		String socialTab1=itr.next();
		getDriver().switchTo().window(socialTab1);
		soft.assertEquals(getDriver().getCurrentUrl(), urls[0],"Navigated to different link");
		getDriver().close();
		getDriver().switchTo().window(main);
		
		soft.assertTrue(util.isElementDisplayed(socialMedia2), "Social media link 2 didn't displayed :");
		log.info("Social media link 2 presence is tested");
		socialMedia2.click();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		allTabs = getDriver().getWindowHandles();
		itr = allTabs.iterator();
		main=itr.next();
		Thread.sleep(3000);
		String socialTab2=itr.next();
		getDriver().switchTo().window(socialTab2);
		soft.assertEquals(getDriver().getCurrentUrl(), urls[1],"Navigated to different link");
		getDriver().close();
		getDriver().switchTo().window(main);
		
		
		soft.assertTrue(util.isElementDisplayed(socialMedia3), "Social media link 3 didn't displayed :");
		log.info("Social media link 3 presence is tested");
		socialMedia3.click();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		allTabs = getDriver().getWindowHandles();
		itr = allTabs.iterator();
		main=itr.next();
		Thread.sleep(3000);
		String socialTab3=itr.next();
		getDriver().switchTo().window(socialTab3);
		soft.assertEquals(getDriver().getCurrentUrl(), urls[2],"Navigated to different link");
		getDriver().close();
		getDriver().switchTo().window(main);
		
		soft.assertTrue(util.isElementDisplayed(socialMedia4), "Social media link 4 didn't displayed :");
		log.info("Social media link 4 presence is tested");
		socialMedia4.click();
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		allTabs = getDriver().getWindowHandles();
		itr = allTabs.iterator();
		main=itr.next();
		Thread.sleep(3000);
		String socialTab4=itr.next();
		getDriver().switchTo().window(socialTab4);
		soft.assertEquals(getDriver().getCurrentUrl(), urls[3],"Navigated to different link");
		getDriver().close();
		getDriver().switchTo().window(main);
		
		soft.assertAll();
		return this;
	}
	
}
