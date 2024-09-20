package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class ChangePasswordPageObjects extends PageInitializer {

	WaitingMethods waiting = new WaitingMethods(getDriver());

	Logger log = Logger.getLogger(ChangePasswordPageObjects.class);

	@FindBy(xpath = "//span[contains(text(),'User Name')]/following-sibling::b")
	private WebElement userNameLabelLocator;

	@FindBy(id = "oldPassword")
	private WebElement oldPasswordLocator;

	@FindBy(id = "newPassword")
	private WebElement newPasswordLocator;

	@FindBy(id = "confirmPassword")
	private WebElement confirmPasswordLocator;

	@FindBy(id = "updateBtn")
	private WebElement saveButtonLocator;

	@FindBy(id = "message")
	private WebElement successMsgLocator;

	@FindBy(xpath = "//button[text()='Clear All']")
	private WebElement cancelButtonLocator;

	@FindBy(xpath = "//h1[contains(text(),'Change Password')]")
	private WebElement changePasswordHeadingLocator;

	@FindBy(xpath = "//ul[@class='breadcrumb']/descendant::li[contains(text(),'Change Password')]")
	private WebElement changePasswordBreadcrumbLocator;

	@FindBy(xpath = "//span[@class='required']/ancestor::li/input")
	private List<WebElement> mandatoryFieldsLocator;

	@FindBy(xpath = "//div[@class='cimm_caption']")
	private WebElement pleaseNoteLocator;

	@FindBy(xpath = "//form[@id='changePassword']/descendant::div[contains(@class,'alert')]")
	private WebElement errorMsgLocator;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement successMesssageLocator;
	
	@FindBy(xpath = "//ul[@class='breadcrumb']/li")
	private List<WebElement> breadCrumbLocator;

	@Step("Verify username label {0}")
	public ChangePasswordPageObjects verifyUserNamePrefill(String userNameForPasswordChange) {
		Assert.assertEquals(userNameLabelLocator.getText().trim(), userNameForPasswordChange,
				"User name is not prefilled");
		log.info("VerifyUserNamePrefill-completed");
		return this;
	}

	@Step("Verify change password page")
	public ChangePasswordPageObjects verifyChangePasswordPage(String pleaseNoteText, String productName, String breadCrumbs)
			throws Exception {
		SoftAssert soft = new SoftAssert();
		String[] expectedBreadCrumbs = breadCrumbs.split(",");
		soft.assertTrue(oldPasswordLocator.isDisplayed(), "Old Password text box is not displayed.");
		soft.assertTrue(newPasswordLocator.isDisplayed(), "New Password text box is not displayed.");
		soft.assertTrue(saveButtonLocator.isDisplayed(), "Save Button is not displayed.");
		soft.assertTrue(cancelButtonLocator.isDisplayed(), "Cancel Button is not displayed.");
		soft.assertTrue(changePasswordHeadingLocator.isDisplayed(), "Change Password Heading is not displayed.");
		soft.assertTrue(changePasswordBreadcrumbLocator.isDisplayed(), "Change Password Breadcrumb is not displayed.");
		soft.assertEquals(pleaseNoteLocator.getText().trim(), pleaseNoteText, "Please note is not displayed.");
		soft.assertEquals(getDriver().getTitle().trim(), "Change Password | " + productName);
		for (int i = 0; i < breadCrumbLocator.size(); i++) {
			soft.assertEquals(breadCrumbLocator.get(i).getText().trim(), expectedBreadCrumbs[i].trim(),
					"Bread Crumb of Order History Page is not : " + expectedBreadCrumbs[i].trim() + " . It is : "
							+ breadCrumbLocator.get(i).getText().trim());
		}
		soft.assertAll();
		log.info("VerifyChangePasswordPage-completed");
		return this;
	}

	@Step("Verify mandatory fields")
	public ChangePasswordPageObjects verifyMandatoryFields(String changePasswordMandatoryFields[]) {
		for (int i = 0; i < mandatoryFieldsLocator.size(); i++) {
			Assert.assertEquals(mandatoryFieldsLocator.get(i).getAttribute("id"), changePasswordMandatoryFields[i],
					"Mandatory field is not " + changePasswordMandatoryFields[i]);
		}
		log.info("verifyMandatoryFields-completed");
		return this;
	}

	@Step("Enter old password:{0}")
	public ChangePasswordPageObjects enterOldPassword(String oldPassword) {
		oldPasswordLocator.clear();
		oldPasswordLocator.sendKeys(oldPassword);
		log.info("Entered old password");
		return this;
	}

	@Step("Enter new password:{0}")
	public ChangePasswordPageObjects enterNewPassword(String newPassword) {
		newPasswordLocator.clear();
		newPasswordLocator.sendKeys(newPassword);
		log.info("Entered new password");
		return this;
	}

	@Step("Enter confirm password:{0}")
	public ChangePasswordPageObjects enterConfirmPassword(String confirmPassword) {
		confirmPasswordLocator.clear();
		confirmPasswordLocator.sendKeys(confirmPassword);
		log.info("Entered confirm password");
		return this;
	}

	@Step("Verify whether error msg is:{0}")
	public ChangePasswordPageObjects verifyErrorMessages(String expectedErrorMessage) {
		waiting.waitForVisibilityOfElement(errorMsgLocator, 10);
		new UtilityMethods(getDriver()).scrollThePageToTop();
		Assert.assertEquals(errorMsgLocator.getText().replace("×", "").trim(), expectedErrorMessage,
				"Error message is wrong.!");
		log.info("verifyErrorMessages-completed");
		return this;
	}

	@Step("Click on save button")
	public ChangePasswordPageObjects clickOnSaveButton() {
		saveButtonLocator.click();
		log.info("Clicked on Save button");
		return this;
	}

	@Step("Verify tab order from top to bottom")
	public ChangePasswordPageObjects verifyTabFocusTopToBottom(String oldPasswordId, String newPasswordId,
			String confirmPasswordId, String saveButtonId, String cancelButtonText) {
		SoftAssert soft = new SoftAssert();
		oldPasswordLocator.click();
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), oldPasswordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), newPasswordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), confirmPasswordId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), saveButtonId);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		soft.assertEquals(getDriver().switchTo().activeElement().getText().trim(), cancelButtonText);
		soft.assertAll();
		log.info("verifyTabFocusTopToBottom-completed");
		return this;
	}

	public ChangePasswordPageObjects hitTopToToBottom(int numberOfTimesToHitTab) {
		oldPasswordLocator.click();
		for (int i = 0; i < numberOfTimesToHitTab; i++) {
			getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		}
		log.info("hitTopToToBottom-completed");
		return this;
	}

	@Step("Verify tab order from bottom to top")
	public ChangePasswordPageObjects verifyTabFocusBottomToTop(String cancelButtonText, String saveButtonId,
			String confirmPasswordId, String newPasswordId, String oldPasswordId) {
		SoftAssert soft = new SoftAssert();
		cancelButtonLocator.click();
		String tabBehind = Keys.chord(Keys.SHIFT, Keys.TAB);
		soft.assertEquals(getDriver().switchTo().activeElement().getText().trim(), cancelButtonText);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), saveButtonId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), confirmPasswordId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), newPasswordId);
		getDriver().switchTo().activeElement().sendKeys(tabBehind);
		soft.assertEquals(getDriver().switchTo().activeElement().getAttribute("id"), oldPasswordId);
		soft.assertAll();
		log.info("verifyTabFocusBottomToTop-completed");
		return this;
	}

	@Step("Verify success message:{0}")
	public ChangePasswordPageObjects verifySuccessMsg(String passwordChangeSuccessMsg) {
		waiting.waitForVisibilityOfElement(successMesssageLocator, 8);
		Assert.assertEquals(successMesssageLocator.getText().trim(), passwordChangeSuccessMsg,
				"Success message is wrong.");
		log.info("verifySuccessMsg-completed");

		return this;

	}

	@Step("Verify Change Password Page Title:{0}")
	public ChangePasswordPageObjects verifyChangePasswordPageTitle(String productName) {
		String changePasswordPageTitle = getDriver().getTitle().trim();
		Assert.assertEquals(changePasswordPageTitle, "Change Password" + " | " + productName, "Title is wrong");
		log.info("verifyChangePasswordPageTitle-completed");
		return this;
	}
}