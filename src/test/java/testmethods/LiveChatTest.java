package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class LiveChatTest extends PageInitializer {

	Logger log = Logger.getLogger(LiveChatTest.class);

	@Feature("Live Chat")
	@Test(groups = { "regression" }, enabled = true)
	@Description("Verify contact us window fields on clicking Live chat")
	public void verifyFieldsDisplayedInContactUsOnClickingLiveChat() throws Throwable {
		log.info("--------------verifyFieldsDisplayedInContactUsOnClickingLiveChat started------------");
		try {
			liveChatObjects().clickOnLiveChatLink().focusOnContactUsWindow().verifyContactUsFields();

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			logger.info("verifyFieldsDisplayedInContactUS failed:" + e.getMessage());
			Assert.fail("verifyFieldsDisplayedInContactUS failed:" + e.getMessage());
		}
		log.info("--------------verifyFieldsDisplayedInContactUsOnClickingLiveChat Completed------------");
	}

	@Feature("Live Chat")
	@Test(groups = { "regression" }, enabled = true)
	@Description("Verify error message in Contact Us window.")
	public void verifyErrorMessageInContactUsWindow() throws Throwable {
		log.info("--------------verifyErrorMessageInContactUsWindow Started------------");

		try {
			liveChatObjects().clickOnLiveChatLink()
					.focusOnContactUsWindow()
					.clickOnSubmitButtonContactUsWindow()
					.verifyErrorMessageInContactUsWindow("Complete the required field");

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			logger.info("verifyErrorMessageInContactUsWindow failed:" + e.getMessage());
			Assert.fail("verifyErrorMessageInContactUsWindow failed:" + e.getMessage());
		}
		log.info("--------------verifyErrorMessageInContactUsWindow Completed------------");
	}

	@Feature("Live Chat")
	@Test(groups = { "regression" }, enabled = true)
	@Description("Verify Thank you message after clicking Submit button.")
	public void verifyThankYouAfterSubmitInContactUsWindow() throws Throwable {
		log.info("--------------verifyThankYouAfterSubmitInContactUsWindow Started------------");

		try {
			liveChatObjects().clickOnLiveChatLink()
					.focusOnContactUsWindow()
					.enterFromName("Unilog")
					.enterCustomerNumber("Unilog Customer Number")
					.enterEmail("test.automation@unilogcorp.com")
					.enterMessage("Unilog Automation testing")
					.clickOnSubmitButtonContactUsWindow()
					.verifyThankyouMsg();

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			logger.info("verifyThankYouAfterSubmitInContactUsWindow failed:" + e.getMessage());
			Assert.fail("verifyThankYouAfterSubmitInContactUsWindow failed:" + e.getMessage());
		}
		log.info("--------------verifyThankYouAfterSubmitInContactUsWindow Completed------------");
	}

	@Feature("Live Chat")
	@Test(groups = { "regression" }, enabled = true)
	@Description("Verify Thank you message after clciking Cancel button.")
	public void verifyThankYouAfterCancelInContactUsWindow() throws Throwable {
		log.info("--------------verifyThankYouAfterCancelInContactUsWindow Started------------");

		try {
			liveChatObjects().clickOnLiveChatLink()
					.focusOnContactUsWindow()
					.enterFromName("Unilog")
					.enterCustomerNumber("Unilog Customer Number")
					.enterEmail("test.automation@unilogcorp.com")
					.enterMessage("Unilog Automation testing")
					.clickOnCancelButtonContactUsWindow()
					.verifyThankyouMsg();

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			logger.info("verifyThankYouAfterCancelInContactUsWindow failed:" + e.getMessage());
			Assert.fail("verifyThankYouAfterCancelInContactUsWindow failed:" + e.getMessage());
		}
		log.info("--------------verifyThankYouAfterCancelInContactUsWindow Completed------------");
	}

	@Feature("Live Chat")
	@Test(groups = { "regression" }, enabled = true)
	@Description("Verify contact us window fields on clicking Leave message")
	public void verifyFieldsDisplayedInContactUsByClickingLeaveMessage() throws Throwable {
		log.info("--------------verifyFieldsDisplayedInContactUsByClickingLeaveMessage Started------------");

		try {
			liveChatObjects().clickOnLeaveMessageLink().focusOnContactUsWindow().verifyContactUsFields();

		} catch (Throwable e) {
			captureScreenshot("step", getDriver());
			e.printStackTrace();
			logger.info("verifyFieldsDisplayedInContactUsByClickingLeaveMessage failed:" + e.getMessage());
			Assert.fail("verifyFieldsDisplayedInContactUsByClickingLeaveMessage failed:" + e.getMessage());
		}
		log.info("--------------verifyFieldsDisplayedInContactUsByClickingLeaveMessage Completed------------");
	}
}
