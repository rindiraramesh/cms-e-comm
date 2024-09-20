package initializer;

import org.openqa.selenium.support.PageFactory;

import mainclass.MainClassWebDriver;
import pageobjects.AccountFinancialInfoPageObjects;
import pageobjects.AddNewCreditCardPageObjects;
import pageobjects.AddRemoveCPNTestObjects;
import pageobjects.AdvanceSearchPageObjects;
import pageobjects.ApprovalCartListPageObjects;
import pageobjects.CategoryPageObjects;
import pageobjects.ChangePasswordPageObjects;
import pageobjects.CheckoutPageObjects;
import pageobjects.ClearanceModuleObects;
import pageobjects.CommonPageObjects;
import pageobjects.ComparePageObjects;
import pageobjects.CompletedOrdersPageObjects;
import pageobjects.ConfigureProcumentSystemPagePageObjects;
import pageobjects.ContactUsPageObjects;
import pageobjects.EditContactInfoPageObjects;
import pageobjects.ForgotPasswordPageObjects;
import pageobjects.HomePageObjects;
import pageobjects.LocationsPageObjects;
import pageobjects.LoginDropDownPageObjects;
import pageobjects.LoginPageObjects;
import pageobjects.LoginPopUpPageObjects;
import pageobjects.MyAccountMenuDropdownObjects;
import pageobjects.MyAccountsPageObjects;
import pageobjects.MyCartPageObjects;
import pageobjects.MyProductGroupPopUp;
import pageobjects.MyProductGroupsPageObjects;
import pageobjects.NewCardAuthenticationPageObjects;
import pageobjects.OpenOrdersPageObjects;
import pageobjects.OrderDetailsPageObjects;
import pageobjects.OrderHistoryPageObjects;
import pageobjects.PageFooterObjects;
import pageobjects.PageHeaderObjects;
import pageobjects.ProductPageObjects;
import pageobjects.ProductsDetailsPageObjects;
import pageobjects.ProductsListPageObjects;
import pageobjects.QuickOrderPadPreviewPageObjects;
import pageobjects.QuickOrderPageObjects;
import pageobjects.RegistrationANPageObjects;
import pageobjects.RegistrationCAPageObjects;
import pageobjects.RegistrationRCPageObjects;
import pageobjects.RequestForQuotePageObjects;
import pageobjects.SaveCartPageObjects;
import pageobjects.SelectShippingToProceedObjects;
import pageobjects.SendThisPageObjects;
import pageobjects.SharePageObjects;
import pageobjects.SharePopUpPageObjects;
import pageobjects.ShopByBrandsPageObjects;
import pageobjects.ShopByManufacturersPageObjects;
import pageobjects.SocialMediaObjects;
import pageobjects.TestPageObjects;
import pageobjects.UserInformationPageObjects;
import pageobjects.UserManagementPageObjects;
import pageobjects.liveChatObjects;
import pageobjects.productGridPageObjects;

public class PageInitializer extends MainClassWebDriver {

	public TestPageObjects testPage() {
		TestPageObjects testPage = PageFactory.initElements(getDriver(), TestPageObjects.class);
		return testPage;
	}

	public HomePageObjects homePage() {
		HomePageObjects homePage = PageFactory.initElements(getDriver(), HomePageObjects.class);
		return homePage;
	}

	public PageHeaderObjects headerObjects() {
		PageHeaderObjects header = PageFactory.initElements(getDriver(), PageHeaderObjects.class);
		return header;
	}

	public PageFooterObjects footerObjects() {
		PageFooterObjects footer = PageFactory.initElements(getDriver(), PageFooterObjects.class);
		return footer;
	}

	public MyAccountMenuDropdownObjects MyAccountMenuDropdown() {
		MyAccountMenuDropdownObjects MyAccountMenuDropdown = PageFactory.initElements(getDriver(),
				MyAccountMenuDropdownObjects.class);
		return MyAccountMenuDropdown;
	}

	public SelectShippingToProceedObjects SelectShippingToProceed() {
		SelectShippingToProceedObjects SelectShippingToProceed = PageFactory.initElements(getDriver(),
				SelectShippingToProceedObjects.class);
		return SelectShippingToProceed;
	}

	public CommonPageObjects commonOperations() {
		CommonPageObjects commonOperations = PageFactory.initElements(getDriver(), CommonPageObjects.class);
		return commonOperations;
	}

	public LoginDropDownPageObjects loginDropDown() {
		LoginDropDownPageObjects loginDropDown = PageFactory.initElements(getDriver(), LoginDropDownPageObjects.class);
		return loginDropDown;
	}

	public RegistrationCAPageObjects commnercialRegistrationPage() {
		RegistrationCAPageObjects registrationPage = PageFactory.initElements(getDriver(),
				RegistrationCAPageObjects.class);
		return registrationPage;
	}

	public RegistrationANPageObjects onAccountRegistrationPage() {
		RegistrationANPageObjects onAccountRegistrationPage = PageFactory.initElements(getDriver(),
				RegistrationANPageObjects.class);
		return onAccountRegistrationPage;
	}

	public RegistrationRCPageObjects retailUserRegistrationPage() {
		RegistrationRCPageObjects registrationRCPageObjects = PageFactory.initElements(getDriver(),
				RegistrationRCPageObjects.class);
		return registrationRCPageObjects;
	}

	public ProductsDetailsPageObjects productDetailsPage() {
		ProductsDetailsPageObjects productDetailsPage = PageFactory.initElements(getDriver(),
				ProductsDetailsPageObjects.class);
		return productDetailsPage;
	}

	public MyAccountsPageObjects myAccountsPage() {
		MyAccountsPageObjects myAccountsPage = PageFactory.initElements(getDriver(), MyAccountsPageObjects.class);
		return myAccountsPage;
	}

	public UserInformationPageObjects userInformationPage() {
		UserInformationPageObjects userInformationPage = PageFactory.initElements(getDriver(),
				UserInformationPageObjects.class);
		return userInformationPage;
	}

	public ProductsListPageObjects productListPage() {
		ProductsListPageObjects productListPage = PageFactory.initElements(getDriver(), ProductsListPageObjects.class);
		return productListPage;
	}

	public productGridPageObjects productGridPage() {
		productGridPageObjects productGridPage = PageFactory.initElements(getDriver(), productGridPageObjects.class);
		return productGridPage;
	}

	public CompletedOrdersPageObjects completedOrdersPage() {
		CompletedOrdersPageObjects completedOrdersPage = PageFactory.initElements(getDriver(),
				CompletedOrdersPageObjects.class);
		return completedOrdersPage;
	}

	public OpenOrdersPageObjects openOrdersPage() {
		OpenOrdersPageObjects openOrdersPage = PageFactory.initElements(getDriver(), OpenOrdersPageObjects.class);
		return openOrdersPage;
	}

	public AccountFinancialInfoPageObjects accountFinancialInfoPage() {
		AccountFinancialInfoPageObjects accountFinancialInfoPage = PageFactory.initElements(getDriver(),
				AccountFinancialInfoPageObjects.class);
		return accountFinancialInfoPage;
	}

	public MyCartPageObjects myCartPage() {
		MyCartPageObjects shoppingCartPage = PageFactory.initElements(getDriver(), MyCartPageObjects.class);
		return shoppingCartPage;
	}

	public ForgotPasswordPageObjects forgotPasswordPage() {
		ForgotPasswordPageObjects forgotPasswordPage = PageFactory.initElements(getDriver(),
				ForgotPasswordPageObjects.class);
		return forgotPasswordPage;
	}

	public ChangePasswordPageObjects changePasswordPage() {
		ChangePasswordPageObjects changePasswordPage = PageFactory.initElements(getDriver(),
				ChangePasswordPageObjects.class);
		return changePasswordPage;
	}

	public AddNewCreditCardPageObjects addNewCreditCardPage() {
		AddNewCreditCardPageObjects addNewCreditCardPage = PageFactory.initElements(getDriver(),
				AddNewCreditCardPageObjects.class);
		return addNewCreditCardPage;
	}

	public NewCardAuthenticationPageObjects newCardAuthenticationPage() {
		NewCardAuthenticationPageObjects ewCardAuthenticationPage = PageFactory.initElements(getDriver(),
				NewCardAuthenticationPageObjects.class);
		return ewCardAuthenticationPage;
	}

	public LoginPopUpPageObjects loginPopUp() {
		LoginPopUpPageObjects loginPopUp = PageFactory.initElements(getDriver(), LoginPopUpPageObjects.class);
		return loginPopUp;
	}

	public LoginPageObjects loginPage() {
		LoginPageObjects loginPage = PageFactory.initElements(getDriver(), LoginPageObjects.class);
		return loginPage;
	}

	public ComparePageObjects comparePage() {
		ComparePageObjects comparePage = PageFactory.initElements(getDriver(), ComparePageObjects.class);
		return comparePage;
	}

	public MyProductGroupsPageObjects productGroupsPage() {
		MyProductGroupsPageObjects myProductGroupsPage = PageFactory.initElements(getDriver(),
				MyProductGroupsPageObjects.class);
		return myProductGroupsPage;
	}

	public ShopByBrandsPageObjects shopByBrandsPage() {
		ShopByBrandsPageObjects shopByBrandsPage = PageFactory.initElements(getDriver(), ShopByBrandsPageObjects.class);
		return shopByBrandsPage;
	}

	public ShopByManufacturersPageObjects shopByManufacturersPage() {
		ShopByManufacturersPageObjects shopByManufacturersPage = PageFactory.initElements(getDriver(),
				ShopByManufacturersPageObjects.class);
		return shopByManufacturersPage;
	}

	public SharePopUpPageObjects sharePopUp() {
		SharePopUpPageObjects sharePopUp = PageFactory.initElements(getDriver(), SharePopUpPageObjects.class);
		return sharePopUp;
	}

	public ProductPageObjects productsPage() {
		ProductPageObjects productsPage = PageFactory.initElements(getDriver(), ProductPageObjects.class);
		return productsPage;
	}

	public SaveCartPageObjects saveCartPage() {
		SaveCartPageObjects saveCartPage = PageFactory.initElements(getDriver(), SaveCartPageObjects.class);
		return saveCartPage;
	}

	public EditContactInfoPageObjects editContactInfoPage() {
		EditContactInfoPageObjects editContactInfoPage = PageFactory.initElements(getDriver(),
				EditContactInfoPageObjects.class);
		return editContactInfoPage;
	}

	public QuickOrderPageObjects quickOrderPadPage() {
		QuickOrderPageObjects quickOrderPadPage = PageFactory.initElements(getDriver(), QuickOrderPageObjects.class);
		return quickOrderPadPage;
	}

	public CheckoutPageObjects checkoutPage() {
		CheckoutPageObjects checkoutPage = PageFactory.initElements(getDriver(), CheckoutPageObjects.class);
		return checkoutPage;
	}

	public SharePageObjects sharePage() {
		SharePageObjects sharePage = PageFactory.initElements(getDriver(), SharePageObjects.class);
		return sharePage;
	}

	public SendThisPageObjects sendThisPage() {
		SendThisPageObjects sendThisPage = PageFactory.initElements(getDriver(), SendThisPageObjects.class);
		return sendThisPage;
	}

	public ContactUsPageObjects contactUsPage() {
		ContactUsPageObjects contactUsPage = PageFactory.initElements(getDriver(), ContactUsPageObjects.class);
		return contactUsPage;
	}

	public MyProductGroupPopUp myProductGroupPopUp() {
		MyProductGroupPopUp myProductGroupPopUp = PageFactory.initElements(getDriver(), MyProductGroupPopUp.class);
		return myProductGroupPopUp;
	}

	public QuickOrderPadPreviewPageObjects quickOrderPadPreviewPage() {
		QuickOrderPadPreviewPageObjects quickOrderPadPreviewPage = PageFactory.initElements(getDriver(),
				QuickOrderPadPreviewPageObjects.class);
		return quickOrderPadPreviewPage;
	}

	public AdvanceSearchPageObjects advanceSearchPage() {
		AdvanceSearchPageObjects advanceSearchPage = PageFactory.initElements(getDriver(),
				AdvanceSearchPageObjects.class);
		return advanceSearchPage;
	}

	public UserManagementPageObjects userManagementPage() {

		UserManagementPageObjects userManagementPage = PageFactory.initElements(getDriver(),
				UserManagementPageObjects.class);
		return userManagementPage;
	}

	public ApprovalCartListPageObjects approvalCartListPage() {
		ApprovalCartListPageObjects approvalCartListPage = PageFactory.initElements(getDriver(),
				ApprovalCartListPageObjects.class);
		return approvalCartListPage;
	}

	public OrderDetailsPageObjects orderDetailsPage() {
		OrderDetailsPageObjects orderDetailsPage = PageFactory.initElements(getDriver(), OrderDetailsPageObjects.class);
		return orderDetailsPage;
	}

	public RequestForQuotePageObjects requestQuotePage() {
		RequestForQuotePageObjects requestForQuotePage = PageFactory.initElements(getDriver(),
				RequestForQuotePageObjects.class);
		return requestForQuotePage;
	}

	public ConfigureProcumentSystemPagePageObjects configureProcumentSystemPage() {
		ConfigureProcumentSystemPagePageObjects configureProcumentSystemPage = PageFactory.initElements(getDriver(),
				ConfigureProcumentSystemPagePageObjects.class);
		return configureProcumentSystemPage;
	}

	public OrderHistoryPageObjects orderHistoryPage() {
		OrderHistoryPageObjects orderHistoryPage = PageFactory.initElements(getDriver(), OrderHistoryPageObjects.class);
		return orderHistoryPage;
	}

	public LocationsPageObjects locationsPage() {
		LocationsPageObjects locationsPage = PageFactory.initElements(getDriver(), LocationsPageObjects.class);
		return locationsPage;
	}

	public CategoryPageObjects categoryPage() {
		CategoryPageObjects categoryPage = PageFactory.initElements(getDriver(), CategoryPageObjects.class);
		return categoryPage;
	}

	public ClearanceModuleObects clearancePage() {
		ClearanceModuleObects clearancePage = PageFactory.initElements(getDriver(), ClearanceModuleObects.class);
		return clearancePage;
	}

	public AddRemoveCPNTestObjects addRemoveCPN() {
		AddRemoveCPNTestObjects addRemoveCPN = PageFactory.initElements(getDriver(), AddRemoveCPNTestObjects.class);
		return addRemoveCPN;
	}

	public SocialMediaObjects socialMediaObjects() {
		SocialMediaObjects socialMediaObjects = PageFactory.initElements(getDriver(), SocialMediaObjects.class);
		return socialMediaObjects;
	}

	public liveChatObjects liveChatObjects() {
		liveChatObjects liveChatObjects = PageFactory.initElements(getDriver(), liveChatObjects.class);
		return liveChatObjects;
	}
}
