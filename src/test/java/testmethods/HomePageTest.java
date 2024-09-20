package testmethods;

import org.testng.annotations.Test;

import initializer.PageInitializer;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;

public class HomePageTest extends PageInitializer {

	@Feature("Home Page")
	@Description("This is a test case which verifies recently viewed tab for the first time launch")
	@Test(groups = { "Home Page test", "regression", })
	public void a_verifyRecentlyViewedItemstab() throws InterruptedException {
		try {
			homePage()

					.verifyRecentlyViewedItemForFirstLaunch();
		} finally {
			homePage().clickOnLogo();
		}
	}

	// This feature is not present in template
	@Feature("Home Page")
	@Description("This is a test case which verifies recently viewed tab functionality")
	@Test(enabled = false, groups = { "Home Page test", "regression" })
	public void e_verifyRecentlyViewedItemsfunctionality() throws Exception {
		try {
			homePage()

					.verifyRecentlyViewedItemDisplayFunctionality();
		} finally {
			homePage().clickOnLogo();
		}
	}

	// This feature is not present in template
	@Feature("Home Page")
	@Description("This is a test case which verifies recently viewed tab items with play buttons")
	@Test(enabled = false, groups = { "Home Page test", "regression" })
	public void verifyRecentlyViewedItemsDisplayWithPlayButtons() throws Exception {
		try {
			homePage().verifyRecentlyViewedItemDisplay().verifyRecentlyViewedAllItemDisplayWithPlayButtons();
		} finally {
			homePage().clickOnLogo();
		}
	}

	@Feature("Home Page")
	// working fine
	@Description("This is a test case which verifies featured product tab presence")
	@Test(enabled=false,groups = { "Home Page test", "regression" })
	public void d_verifyFeaturedProductsTabLocation() throws InterruptedException {
		try {
			homePage().verifyfeaturedProductsItemDisplayContent();
		} finally {
			homePage().clickOnLogo();
		}
	}

	@Feature("Home Page")
	// working fine
	@Description("This is a test case which verifies featured product tab items using Play buttons")
	@Test(groups = { "Home Page test", "regression" })
	public void i_verifyFeaturedProductsTabwithPlayButtons() throws Exception {
		try {
			homePage().verifyfeaturedProductsItemDisplayWithPlayButtons();
		} finally {
			homePage().clickOnLogo();
		}
	}

	@Feature("Home Page")
	// released in 7.5.2.1 version
	@Description("This is a test case which verifies multiple featured product tab presence")
	@Test(groups = { "Home Page test", "regression" })
	public void f_verifyMultipleFeaturedProductsTabLocation() throws Exception {
		/*
		 * try { homePage().verifyMultiplefeaturedProductsTab(); }
		 */

		// finally {

		homePage().clickOnLogo();
		// }
	}

	@Feature("Home Page")
	// released in 7.5.2.1 version
	@Description("This is a test case which verifies multiple featured product tab items using Play buttons")
	@Test(groups = { "Home Page test", "regression" })
	public void j_verifyMultipleFeaturedProductsTabWithPlayButtons() throws Exception {
		/*
		 * try { homePage().verifyMultipleFeaturedProductsItemDisplayWithPlayButtons();
		 * }
		 */

		// finally {

		homePage().clickOnLogo();
		// }
	}

	@Feature("Home Page")
	// working fine
	@Description("This is a test case which verifies featured brands tab presence")
	@Issue("Not Able To Add Featured Brands.")
	@Test(groups = { "Home Page test", "Regression", })
	public void b_verifyFeaturedBrandsTabLocation() throws InterruptedException {
		try {
			homePage().verifyFeturedBrandsTabisDisplayed();
		} finally {
			homePage().clickOnLogo();
		}
	}

	@Feature("Home Page")
	// working fine
	@Description("This is a test case which verifies featured product tab items using play buttons")
	@Test(groups = { "Home Page test", "regression" })
	public void g_verifyFeaturedBrandsTabwithPlayButtons() throws Exception {
		try {
			homePage().verifyfeaturedBrandsDisplayWithPlayButtons();
		} finally {
			homePage().clickOnLogo();
		}
	}

	@Feature("Home Page")
	@Description("This is a test case which verifies featured manufacturers tab presence")
	@Issue("Not Able To Add Featured Manufacturers.")
	@Test(enabled=false,groups = { "Home Page test", "regression" })
	public void c_verifyFeaturedManufacturersTabLocation() throws InterruptedException {
		try {
			homePage().verifyFeturedManufacturersTabisDisplayed();
		} finally {
			homePage().clickOnLogo();
		}
	}

	@Feature("Home Page")
	@Description("This is a test case which verifies featured manufacturers tab items using play buttons")
	@Issue("Not Able To Add Featured Manufacturers.")
	@Test(groups = { "Home Page test", "regression" }, enabled=false)
	public void h_verifyFeaturedManufacturersTabwithPlayButtons() throws Exception {
		try {
			homePage()

					.verifyfeaturedManufacturersDisplayWithPlayButtons();
		} finally {
			homePage().clickOnLogo();
		}
	}

}
