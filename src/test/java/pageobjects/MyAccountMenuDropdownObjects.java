package pageobjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import initializer.PageInitializer;
import io.qameta.allure.Step;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class MyAccountMenuDropdownObjects extends PageInitializer {
	WaitingMethods waiting = new WaitingMethods(getDriver());

	UtilityMethods move = new UtilityMethods(getDriver());

	@FindBy(xpath = "//a[@data-target='myAccountMenu']")
	private WebElement MyAccountMenu;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/Dashboard']")
	private WebElement myAccountLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/ChangePassword']")
	private WebElement changePasswordLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li:nth-child(3)>a")
	private WebElement changeShippingAddressLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/EditContactInfo']")
	private WebElement editContactLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href^='OpenOrderSale']")
	private WebElement openOrdersLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/OrderHistory']")
	private WebElement OrderHistoryLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/SavedGroups/Products']")
	private WebElement myProductGroupLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/SavedGroups/Cart']")
	private WebElement mySavedCartLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href*='Quote']")
	private WebElement requestforQuoteLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[href='/QuickCartStandard']")
	private WebElement quickOrderPadLocator;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a[onclick='doLogOffScript();']")
	private WebElement logoutButton;

	@FindBy(css = ".myAccountMenu.dropdown-menu>li>a")
	public List<WebElement> allOptionsMyMenuLocator;

	@Step("Click on MyAccountMenu")
	public MyAccountMenuDropdownObjects clickOnMyAccountMenu() throws InterruptedException {
		logger = Logger.getLogger("clickMyAccountMenuLink");
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", MyAccountMenu);
		logger.info("clicked on MyAccountMenu link");
		return MyAccountMenuDropdown();
	}

	@Step("Click on Change Shipping Address link")
	public SelectShippingToProceedObjects clickOnChangeShippingAddressLink() throws Throwable {
		logger = Logger.getLogger("clickChangeShippingAddressLink");
		Thread.sleep(1500);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", changeShippingAddressLocator);
		logger.info("clicked on ChangeShippingAddress link");
		return SelectShippingToProceed();
	}

	@Step("Click on MyAccount link")
	public MyAccountsPageObjects clickOnMyAccountLink() {
		logger = Logger.getLogger("clickMyAccountLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myAccountLocator);
		logger.info("clicked on MyAccount link");
		return myAccountsPage();
	}

	@Step("Click on OpenOrders link")
	public MyAccountsPageObjects clickOpenOrdersLink() {
		logger = Logger.getLogger("clickOpenOrdersLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", openOrdersLocator);
		logger.info("clicked on Open Orders link");
		return myAccountsPage();
	}

	@Step("Click on Order Histort link")
	public MyAccountsPageObjects clickOrderHistoryLink() {
		logger = Logger.getLogger("clickOrderHistoryLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", OrderHistoryLocator);
		logger.info("clicked on Order History link");
		return myAccountsPage();
	}

	@Step("Click on My Product Groups link")
	public MyAccountsPageObjects clickMyProductGroupLink() {
		logger = Logger.getLogger("clickMyProductGroupLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", myProductGroupLocator);
		logger.info("clicked on My Product Group link");
		return myAccountsPage();
	}

	@Step("Click on Quick Order pad link")
	public MyAccountsPageObjects clickQuickOrederPadLink() {
		logger = Logger.getLogger("clickQuickOrderPadLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", quickOrderPadLocator);
		logger.info("clicked on Quick order pad link");
		return myAccountsPage();
	}

	@Step("Click on My Saved Cart link")
	public MyAccountsPageObjects clickMySavedCartLink() {
		logger = Logger.getLogger("clickMySavedCartLink");
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", mySavedCartLocator);
		logger.info("clicked on My Saved Cart link");
		return myAccountsPage();
	}

	@Step("Click on Request For Quote link")
	public RequestForQuotePageObjects clickOnRequestForQuoteLink() {
		logger = Logger.getLogger("clickOnRequestForQuoteLink");
		requestforQuoteLocator.click();
		//((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", requestforQuoteLocator);
		waiting.waitTillPageLoads();
		logger.info("clicked on Request for Quote link");
		return requestQuotePage();
	}

	@Step("Click on clickOnLogout")
	public MyAccountMenuDropdownObjects clickOnLogout() throws InterruptedException {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", logoutButton);
		return this;
	}
}
