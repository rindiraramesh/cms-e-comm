package testmethods;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import utilities.TestDataPropertyFile;

public class HeaderModule extends PageInitializer {

	Logger log = Logger.getLogger(HeaderModule.class.getName());

	LoginModuleTest loginModule = new LoginModuleTest();

	TestDataPropertyFile data = new TestDataPropertyFile();

	@Feature("Header Module")
	@Test(groups = { "regression" })
	@Description("Verify home page title header section")
	public void verifyTitleOfThePage(ITestContext context) throws InterruptedException {
		try {
			log.info("-----------verifyTitleOfThePage test Started------------------");
			headerObjects().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyTitleOfThePage failed" + t.getMessage());
			Assert.fail("verifyTitleOfThePage failed" + t.getMessage());
		} finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------------verifyTitleOfThePage test completed---------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "Smoke Test", "regression" })
	@Description("This is a test case verifies header section details")
	public void verifyHeaderDetails() throws InterruptedException {
		log.info("-----------------verifyHeaderDetails test started ----------------------");
		try {
			headerObjects().verifyHeaderSectionDetails();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyHeaderDetails failed" + t.getMessage());
			Assert.fail("verifyHeaderDetails failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifyHeaderDetails test completed-----------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" })
	@Description("Verify Logo display in header section")
	public void verifyLogo(ITestContext context) throws Exception {
		log.info("---------------verifyLogo test started---------------------------");
		try {
			headerObjects().verifyPositionOfLogo();
			log.info("Logo position in home page is verified :");

			homePage().searchText(data.getSearchTextForBrand()).clickOnSearch().clickOnLogo();
			headerObjects().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());

			homePage().searchText(data.getSearchTextForBrand()).clickOnSearch();
			productListPage().clickOnChangeView();
			homePage().clickOnLogo();
			headerObjects().verifyHomePageTitle(context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyLogo failed" + t.getMessage());
			Assert.fail("verifyLogo failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("------------------verifyLogo test completed --------------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "Regression" })
	@Description("Verify cart count displayed in header section")
	public void verifyCartCountonHeader() throws Exception {
		log.info("---------------verifyCartCountonHeader test started---------------------");
		try {
			loginModule.loginAsASuperUser();
			myCartPage().clearCart();
			homePage().searchText(data.getSearchTextForPN())
					.clickOnSearch()
					.productDetailsPage()
					.clickOnAddToCartButton()
					.myCartPage()
					.clickOnViewCartInMyCartPopup()
					.verifyNoOfItemsInMyCart("1");

		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifyCartCountonHeader failed" + t.getMessage());
			Assert.fail("verifyCartCountonHeader failed" + t.getMessage());
		}

		finally {
			myCartPage().clearCart();
			homePage().clickOnLogout();
			homePage().clickOnLogo();
		}
		log.info("----------------verifyCartCountonHeader test completed---------------------");
	}

	/*
	 * @Feature("Header Module")
	 * 
	 * @Test(groups = { "regression" },enabled=false)
	 * 
	 * @Description("Verify Events in header section") public void
	 * verifytopNavigationEvents(ITestContext context) throws Exception { log.
	 * info("----------------verifytopNavigation Events test started-----------------------------"
	 * ); try { headerObjects().clickOnEventsMenu()
	 * .verifyEventsTitle(context.getCurrentXmlTest().getParameter("Company").trim()
	 * ) .verifyEventsPageDetails(); }
	 * 
	 * catch (Throwable t) { captureScreenshot("step", getDriver());
	 * t.printStackTrace(); log.info("verifytopNavigationEvents failed" +
	 * t.getMessage()); Assert.fail("verifytopNavigationEvents failed" +
	 * t.getMessage()); }
	 * 
	 * finally { homePage().clickOnLogo(); } log.
	 * info("-------------------verifytopNavigation Events test completed----------------------"
	 * ); }
	 */
	@Feature("Header Module")
	@Test(groups = { "regression" })
	@Description("This is a test case which verifies top Navigation Locations in header section")
	public void verifytopNavigationLocation(ITestContext context) throws Exception {
		log.info("---------------verifytopNavigation Locations test started---------------");
		try {
			headerObjects().clickOnLocationsMenu()
					.verifyLocationsTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyLocationPageDetails();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationLocation failed" + t.getMessage());
			Assert.fail("verifytopNavigationLocation failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifytopNavigation Locations test completed----------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" },enabled=false)
	@Description("This is a test case which verifies top Navigation Promotions in header section")
	public void verifytopNavigationPromotions(ITestContext context) throws Exception {
		log.info("------------verifytopNavigation Promotions test started--------------");
		try {
			headerObjects().clickOnPromotionsMenu()
					.verifyPromotionsTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyPromotionsMenuLinks(data.getPromotionsHeaderMenuLinks())
					.verifyPromotionsMenuLinksTitle(data.getPromotionsHeaderMenuLinksTitle(),
							context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationPromotions failed" + t.getMessage());
			Assert.fail("verifytopNavigationPromotions failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifytopNavigation Promotions test completed----------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" },enabled=false)
	@Description("This is a test case which verifies top Navigation Services in header section")
	public void verifytopNavigationServices(ITestContext context) throws Exception {
		log.info("------------------verifytopNavigation Services test started----------------------");
		try {
			headerObjects().clickOnServicesMenu()
					.verifyServicesTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyServicesMenuLinks(data.getServicesHeaderMenuLinks())
					.verifyServicesMenuLinksTitle(data.getServicesHeaderMenuLinksTitle(),
							context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationServices failed" + t.getMessage());
			Assert.fail("verifytopNavigationServices failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("----------------verifytopNavigation Services test completed---------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" })
	@Description("This is a test case which verifies top Navigation About Us in header section")
	public void verifytopNavigationAboutUs(ITestContext context) throws Exception {
		log.info("-----------------verifytopNavigation About Us test started--------------------");
		try {
			headerObjects().clickOnAboutUsMenu()
					.verifyAboutUsTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyAboutUsMenuLinks(data.getAboutUsHeaderMenuLinks())
					.verifyAboutUsMenuLinksTitle(data.getAboutUsHeaderMenuLinksTitle(),
							context.getCurrentXmlTest().getParameter("Company").trim());
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationAboutUs failed" + t.getMessage());
			Assert.fail("verifytopNavigationAboutUs failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifytopNavigation About Us test completed-------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" },enabled=false)
	@Description("Verify top Navigation Menufacturers in header section")
	public void verifytopNavigationMenufacturers(ITestContext context) throws Exception {
		log.info("---------------verifytopNavigation Menufacturers test started-------------");
		try {
			headerObjects().clickOnManufacturersMenu()
					.verifyShopByManufacturersTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyManufacturersMenu();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationMenufacturers failed" + t.getMessage());
			Assert.fail("verifytopNavigationMenufacturers failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("--------------verifytopNavigation Menufacturers test completed-------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" })
	@Description("Verify top Navigation Brands in header section")
	public void verifytopNavigationBrands(ITestContext context) throws Exception {
		log.info("----------------verifytopNavigation Brands test started---------------------");
		try {
			headerObjects().clickOnBrandsMenu()
					.verifyShopByBrandsTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyBrandsMenu();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationBrands failed" + t.getMessage());
			Assert.fail("verifytopNavigationBrands failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-------------------verifytopNavigation Brands test completed-------------------");
	}

	@Feature("Header Module")
	@Test(groups = { "regression" })
	@Description("This is a test case which verifies top Navigation ShopProducts in header section")
	public void verifytopNavigationShopProducts(ITestContext context) throws Exception {
		log.info("--------------------verifytopNavigation ShopProducts test started--------------------");
		try {
			headerObjects().clickOnShopProductsMenu()
					.verifyProductsTitle(context.getCurrentXmlTest().getParameter("Company").trim())
					.verifyShopProductsMenu();
		}

		catch (Throwable t) {
			captureScreenshot("step", getDriver());
			t.printStackTrace();
			log.info("verifytopNavigationBrands failed" + t.getMessage());
			Assert.fail("verifytopNavigationBrands failed" + t.getMessage());
		}

		finally {
			homePage().clickOnLogo();
		}
		log.info("-----------------verifytopNavigation ShopProducts test completed---------------------------");
	}
}
