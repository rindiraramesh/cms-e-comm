package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class ClearanceModule extends PageInitializer {

	Logger log = Logger.getLogger(ClearanceModule.class);

	@Feature("Clearance Module")
	@Description("This test Case verifies Clearance link presence")
	@Test(groups = { "Clearance Module", "regression", "Smoke Test" })
	public void verifyClearanceLinkPresence() throws InterruptedException {
		log.info("-------------verifyClearanceLinkPresence Started------------------");
		try {
			homePage().clearancePage().verifyClearanceLinkBelowShopProductsMenu();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyClearanceLinkPresence failed :" + t.getMessage());
			Assert.fail("verifyClearanceLinkPresence failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("------------- verifyClearanceLinkPresence Completed------------------");
	}

	@Feature("Clearance Module")
	@Description("This test Case verifies Clearance items display after click on link")
	@Test(groups = { "Clearance Module", "regression" })
	public void verifyClearanceItemsDisplayedOnClicking() throws InterruptedException {
		log.info("-------------verifyClearanceItemsDisplayedOnClicking Started------------------");
		try {
			homePage().clearancePage().verifyClearanceItemsAfterClickingClearance();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyClearanceItemsDisplayedOnClicking failed :" + t.getMessage());
			Assert.fail("verifyClearanceItemsDisplayedOnClicking failed :" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------verifyClearanceItemsDisplayedOnClicking Completed------------------");
	}

}
