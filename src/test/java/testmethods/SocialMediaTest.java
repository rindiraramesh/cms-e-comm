package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class SocialMediaTest extends PageInitializer {

	Logger log = Logger.getLogger(SocialMediaTest.class);

	@Feature("Social media")
	@Test(groups = { "regression" }, enabled = true)
	@Description("This is a test case to verify Social media")
	public void verifySocialMediaInPageFooter() throws Throwable {
		log.info("----------------verifySocialMediaInPageFooter Started------------------");

		try {
			socialMediaObjects().verifySocialMediasection();
		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			log.info("verifySocialMediaInPageFooter failed:" + e.getMessage());
			Assert.fail("verifySocialMediaInPageFooter failed:" + e.getMessage());
		}
		log.info("----------------verifySocialMediaInPageFooter Completed------------------");
	}

}
