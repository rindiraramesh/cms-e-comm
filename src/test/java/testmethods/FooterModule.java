package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class FooterModule extends PageInitializer {

	Logger log = Logger.getLogger(FooterModule.class);

	@Feature("Footer Module")
	@Test(groups = { "regression" }, enabled = false)
	@Description("This is a test case verifies all the links of the page in footer section")
	public void verifyAllFooterLinks() throws InterruptedException {
		log.info("-----------------verifyAllFooterLinks Started-----------------");
		try {
			footerObjects().verifyMenu1Links().verifyMenu2Links().verifyMenu3Links().verifyContactUs();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyAllFooterLinks failed " + t.getMessage());
			Assert.fail("verifyAllFooterLinks failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("----------------- Completed-----------------");
	}

	@Feature("Footer Module")
	@Test(groups = { "regression" }, enabled = false)
	@Description("This is a test case verifies title of all links below Menu1/About Us in footer section")
	public void verifyAllLinksBelowMenu1InPageFooter(ITestContext context) throws Throwable {
		log.info("-----------------verifyAllLinksBelowMenu1InPageFooter Started-----------------");

		try {
			footerObjects().verifyTitlesOfLinksBelowMenu1(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyAllLinksBelowMenu1InPageFooter failed " + t.getMessage());
			Assert.fail("verifyAllLinksBelowMenu1InPageFooter failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyAllLinksBelowMenu1InPageFooter Completed-----------------");
	}

	@Feature("Footer Module")
	@Test(groups = { "regression" }, enabled = false)
	@Description("This is a test case verifies title of all links below Menu1/About Us in footer section")
	public void verifyAllLinksBelowMenu2InPageFooter(ITestContext context) throws InterruptedException {
		log.info("-----------------verifyAllLinksBelowMenu2InPageFooter Started-----------------");

		try {
			footerObjects().verifyTitlesOfLinksBelowMenu2(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyAllLinksBelowMenu2InPageFooter failed " + t.getMessage());
			Assert.fail("verifyAllLinksBelowMenu2InPageFooter failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyAllLinksBelowMenu2InPageFooter Completed-----------------");
	}

	@Feature("Footer Module")
	@Test(groups = { "regression" }, enabled = false)
	@Description("This is a test case verifies title of all links below Services in footer section")
	public void verifyAllLinksBelowMenu3InPageFooter(ITestContext context) throws InterruptedException {
		log.info("-----------------verifyAllLinksBelowMenu3InPageFooter Started-----------------");
		try {
			footerObjects().verifyTitlesOfLinksBelowMenu3(context.getCurrentXmlTest().getParameter("Company").trim());
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyAllLinksBelowMenu3InPageFooter failed " + t.getMessage());
			Assert.fail("verifyAllLinksBelowMenu3InPageFooter failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyAllLinksBelowMenu3InPageFooter Completed-----------------");
	}

	@Feature("Footer Module")
	@Test(groups = { "regression" })
	@Description("This is a test case to verify copy right text in footer section")
	public void verifyCopyRightsPageInPageFooter(ITestContext context) throws InterruptedException {
		log.info("----------------- verifyCopyRightsPageInPageFooter Started-----------------");
		try {
			footerObjects().verifyCopyRightstext(
					"Copyright © 2022 " + context.getCurrentXmlTest().getParameter("Company").trim()
							+ ". All Rights Reserved. B2B eCommerce platform by Unilog. Do not sell my info");
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyCopyRightsPageInPageFooter failed " + t.getMessage());
			Assert.fail("verifyCopyRightsPageInPageFooter failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyCopyRightsPageInPageFooter Completed-----------------");
	}

	@Feature("Footer Module")
	@Test(groups = { "regression", "Smoke Test" })
	@Description("This is a test case to verify email subscription section in footer")
	public void verifyEmailSubscriptionsectionInPageFooter() throws InterruptedException {
		log.info("-----------------verifyEmailSubscriptionsectionInPageFooter Started-----------------");
		try {
			footerObjects().verifyEmailSubscriptionsection();
		} catch (Throwable t) {
			t.printStackTrace();
			captureScreenshot("step", getDriver());
			log.info("verifyEmailSubscriptionsectionInPageFooter failed " + t.getMessage());
			Assert.fail("verifyEmailSubscriptionsectionInPageFooter failed :" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifyEmailSubscriptionsectionInPageFooter Completed-----------------");
	}

}
