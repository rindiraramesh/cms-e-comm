package utilities;

import utility.PropertyFileMethods;

public class TestDataPropertyFile {

	String filePath = "src/test/resources/TestData.properties";

	String testRailFilePath = "src/test/resources/TestRailData.properties";

	String prerequisitesFilePath = "src/test/resources/prerequisites.properties";

	PropertyFileMethods propertyFileMethods = new PropertyFileMethods();

	public String getSearchText() {
		String searchText = propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchText");
		return searchText;
	}
	public String getSearchText1() {
		String searchText1 = propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchText1");
		return searchText1;
	}
	public String getSearchText2() {
		String searchText2 = propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchText1");
		return searchText2;
	}


	public String getUserName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "username");
	}

	public String getPassword() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "password");
	}

	public String getSearchTextForPN() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForPartNumber");
	}
	

	public String getSearchTextForPNNotHavingPrice() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForPNNotHavingPrice");
	}

	public String getSearchTextForMPN() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForMPN");
	}

	public String getSearchTextForUPC() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForUPC");
	}

	public String getSearchTextForProductMode() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForProductMode");
	}

	public String getAlertMessageForSearch() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertTextForItemSearch");
	}

	public String getSearchTextNarrowFilterTextboxForPartNumber() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextNarrowFilterTextboxForPartNumber");
	}

	public String getSearchTextNarrowFilterTextboxForMPN() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextNarrowFilterTextboxForMPN");
	}

	public String getSearchTextNarrowFilterTextboxForUPC() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextNarrowFilterTextboxForUPC");
	}

	public String getExpectedSearchTexboxPlaceholder() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedSearchTexboxPlaceholder");
	}

	public String getAdvanceSearchBreadcrumb() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "advanceSearchBreadcrumb");
	}

	public String getSearchTextForCPN() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForCPN");
	}

	public String getSearchTextForBrand() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForBrand");
	}
	
	public String getSearchTextForBrand1() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForBrand1");
	}

	public String getItemsPerPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "itemsPerPage");
	}

	public String getUserNameAfterLogin() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "userNameAfterLogin");
	}

	public String getSuccessMessageForItemShare() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "successMessageForItemShare");
	}

	public String getForgotYourPasswordInstructions() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "forgotYourPasswordInstructions");
	}

	public String getForgotPageBreadCrumb() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "forgotPageBreadCrumb");
	}

	public String getRegistrationPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "registrationPageName");
	}

	public String getMyCartBreadcrumb() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "myCartBreadcrumb");
	}

	public String getGeneralSearchKeyword() {

		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "generalSearchKeyword");
	}

	public String getAlertMessageForQuantityLessThanOrEqualToZero() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"alertMessageForQuantityLessThanOrEqualToZero");
	}

	public String getAlertMessageForQuantityLessThanOrEqualToZero_CartPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"alertMessageForQuantityLessThanOrEqualToZeroCartPage");
	}

	public String getAlertMessageForMinOrderQuantity() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageForMinOrderQuantity");
	}

	public String getAlertMessageForMinOrderQuantityInPL() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageForMinOrderQuantityInPL");
	}

	public String getOrderConfirmationSuccessMessage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "orderConfirmationSuccessMessage");
	}

	public String getAlertMessageForDeletionOfItem() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageForDeletionOfItem");
	}

	public String getSaveCartName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "saveCartName");
	}

	public String getSelectForThanOneItemToCompareText() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "selectForThanOneItemToCompareText");
	}

	public String getAlertTextForMoreThanFiveItems() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertTextForMoreThanFiveItems");
	}

	public String getColourOfHighlightSimilarButtonAfterClicking() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"colourOfHighlightSimilarButtonAfterClicking");
	}

	public String getProductGroupName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "productGroupName");
	}

	public String getProductGroupPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "productGroupPageName");
	}

	public String getAlertMessageForProductGroup() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageForProductGroup");
	}

	public String getAlertMessageForSpecialCharactersInProductGroup() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"alertMessageForSpecialCharactersInProductGroup");
	}

	public String getSortByOptionsInPG() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "sortByOptionsInPG");
	}

	public String getSortByOptionsInPLP() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "sortByOptionsInPLP");
	}

	public String getSortByOptionsInPLPAll() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "sortByOptionsInPLPAll");
	}

	public String getBulkOptionsInPG() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "bulkOptionsInPG");
	}

	public String getGeneralSearchText() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "generalSearchText");
	}
	
	public String getGeneralSearchText1() {
		String generalSearchText1 = propertyFileMethods.readTestDataFromPropertyFile(filePath, "generalSearchText1");
		return generalSearchText1;
	}

	public String getAlertMessageWhenClickedOnCancelCompareLink() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageWhenClickedOnCancleCompareLink");
	}

	public String getMyProductGroupName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "myProductGroupName");
	}

	public String getSavedCartPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "savedCartPageName");
	}

	public String getValidKeywordForShareCart() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "validKeywordForShareCart");
	}

	public String getPasswordForWhichCartIsShared() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "passwordForWhichCartIsShared");
	}

	public String getShoppingCartPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "shoppingCartPageName");
	}

	public String getSuperUserCredentialForUserManagementModule() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "superUserCredentialForUserManagementModule");
	}

	public String getSuperUserAccountDropDown() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "superUserAccountDropDown");
	}

	public String getGeneralUserAccountDropDown() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "generalUserAccountDropDown");
	}

	public String getErrorMessageIfMandatoryFieldsLeaveBlank() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "errorMessageIfMandatoryFieldsLeaveBlank");
	}

	public String getErrorMessageIffewMandatoryFieldsLeaveBlank() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "errorMessageIffewMandatoryFieldsLeaveBlank");
	}

	public String getErrorMessageWithoutUpdatingRoleOfTheUser() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"getErrorMessageWithoutUpdatingRoleOfTheUser");
	}

	public String getGeneralUserAvailableButtonInShoppingCartPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"generalUserAvailableButtonInShoppingCartPage");
	}

	public String getEditContactPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "editContactPageName");
	}

	public String getOrderDetailPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "orderDetailPageName");
	}

	public String getOrderDetailBreadCrumbForOpenOrder() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "orderDetailBreadCrumbForOpenOrder");
	}

	public String getOrderDetailBreadCrumbForCompletedOrder() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "orderDetailBreadCrumbForCompletedOrder");
	}

	public String getUserInformationPageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "userInformationPageName");
	}

	public String getUserManagementTableHeader() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "userManagementTableHeader");
	}

	public String getMandatoryAndNonMandatoryFieldsOfAddNewUserPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"mandatoryAndNonMandatoryFieldsOfAddNewUserPage");
	}

	public String getContactInformationUpdatedMessageOfBillingAddress() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"contactInformationUpdatedMessageOfBillingAddress");
	}

	public String getContactInformationUpdatedMessageOfShippingAddress() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"contactInformationUpdatedMessageOfShippingAddress");
	}

	public String getShipAddressListHeader() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "shipAddressListHeader");
	}

	public String getExpectedOpenOrderTableHeader() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedOpenOrderTableHeader");
	}

	public String getExpectedOrderHistoryTableHeader() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedOrderHistoryTableHeader");
	}

	public String getBreadCrumbOfCompletedOrderPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "breadCrumbOfCompletedOrderPage");
	}

	public String getAlertMessageWithoutClickingOnSelectAllCheckbox() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"alertMessageWithoutClickingOnSelectAllCheckbox");
	}

	public String getBreadCrumbOfOpenOrderPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "breadCrumbOfOpenOrderPage");
	}

	public String getInvalidDataForSearch() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "invalidDataForSearch");
	}

	public String getAlertMessageWhenClickOnRefreshShippingIcon() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageWhenClickOnRefreshShippingIcon");
	}

	public String getNewlyCreatedUser() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "newlyCreatedUser");
	}

	public String getNewlyCreatedAPAUser() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "newlyCreatedAPAUser");
	}

	public String getNewlyCreatedSuperUser() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "newlyCreatedSuperUser");
	}

	public String getSharedCartCompleteBreadcrumb() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "sharedCartCompleteBreadcrumb");
	}

	public String getAlertTextOfDeleteSaveCart() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertTextOfDeleteSaveCart");
	}

	public String getSearchTextForCategory() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForCategory");
	}

	public String getComparePageName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "comparePageName");
	}

	public String getAlertTextWhenNoItemsAreSelectedInComparePage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"alertTextWhenNoItemsAreSelectedInComparePage");
	}

	public String getAlertTextWhenAllItemsAreSelectedInComparePage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"alertTextWhenAllItemsAreSelectedInComparePage");
	}

	public String getTestRunID() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TEST_RUN_ID");
	}

	public String getTestRailUserName() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TESTRAIL_USERNAME");
	}

	public String getTestRailPassword() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TESTRAIL_PASSWORD");
	}

	public String getTestRailURL() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "RAILS_ENGINE_URL");
	}

	public String getTestRailPassStatus() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TEST_CASE_PASSED_STATUS");
	}

	public String getTestRailFailStatus() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TEST_CASE_FAILED_STATUS");
	}

	public String getTestRailEnableStatus() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "IS_ENABLED");
	}

	public String getTestRailDataFilePath() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "testRailDataFilePath");
	}

	public String getExpectedSuperUserAccountDropdown() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedSuperUserAccountDropdown");
	}

	public String getExpectedGeneralUserAccountDropdown() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedGeneralUserAccountDropdown");
	}

	public String getCartApprovalSuccessMessage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedCartApprovalSuccessMessage");
	}

	/*
	 * public String getAPAUserName() { return
	 * propertyFileMethods.readTestDataFromPropertyFile(filePath, "aPAUserName"); }
	 */
	/*
	 * public String getApaPassword() { return
	 * propertyFileMethods.readTestDataFromPropertyFile(filePath, "aPAPassword"); }
	 */

	public String getExpectedApaUserAccountDropdown() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "expectedApaUserAccountDropdown");
	}

	public String getAboutUsHeaderMenuLinks() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "aboutUsHeaderMenuLinks");
	}

	public String getAboutUsHeaderMenuLinksTitle() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "aboutUsHeaderMenuLinksTitle");
	}

	public String getPromotionsHeaderMenuLinks() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "promotionsHeaderMenuLinks");
	}

	public String getPromotionsHeaderMenuLinksTitle() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "promotionsHeaderMenuLinksTitle");
	}

	public String getServicesHeaderMenuLinks() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "servicesHeaderMenuLinks");
	}

	public String getServicesHeaderMenuLinksTitle() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "servicesHeaderMenuLinksTitle");
	}

	public String getfooterAddress() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerAddress");
	}

	public String getfooterMenu3Links() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerMenu3Links");
	}

	public String getfooterMenu2Links() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerMenu2Links");
	}

	public String getfooterMenu1Links() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerMenu1Links");
	}

	public String getfooterMenu3LinksTitle() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerMenu3LinksTitle");
	}

	public String getfooterMenu2LinksTitle() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerMenu2LinksTitle");
	}

	public String getfooterMenu1LinksTitle() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "footerMenu1LinksTitle");
	}

	public String getSearchShippingAddress() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "SearchShippingddress");
	}

	public String getReqForQuoteTabelheader() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "ReqForQuoteTabelheader");
	}

	public String getBreadCrumReqForQuote() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "BreadCrumReqForQuote");
	}

	public String getInputValuesForQtyfield() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "inputValuesForQtyfield");
	}

	public String getOrderPageOpenOrdersHeaders() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "orderPageOpenOrdersHeaders");
	}

	public String getOrderPageCompletedOrdersHeaders() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "completedOrderTableHeaders");
	}

	public String getSearchTextforRecentlyViewedTest() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "recentlyViewedSearchHomePage");
	}

	public String getShareThisPageBreadCrumb() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "shareThisPageBreadCrumb");
	}

	public String getPartNumberForSpeedEntry() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "partNumberForSpeedEntry");
	}

	public String getPricePricisonData() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "pricePricisonData");
	}

	public String getQOPRightClickOptions() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "qopRightClickOptions");
	}

	public String getPartNumberForSpeedEntry_LessMinOrderQty() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "partNumberForSpeedEntry_LessMinOrderQty");
	}

	public String getSearchTextForSpeedEntry() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForSpeedEntry");
	}

	public String getTableHeaderOfMyProductGroup() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "tableHeaderOfMyProductGroup");
	}

	public String getTableHeaderOfMySavedCart() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "tableHeaderOfMySavedCart");
	}

	public String getPartNumberForSpeedEntryCombineRemoveSeperate() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"partNumberForSpeedEntryCombineRemoveSeperate");
	}

	public String getSearchTextForSpeedEntry_InvalidQty() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForSpeedEntry_InvalidQty");
	}

	public String getSearchTextForSpeedEntry_BulkOption() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchTextForSpeedEntry_BulkOption");
	}

	public String getErrorMessageWhenNoProductInformationRFQ() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "errorMessageWhenNoProductInformationRFQ");
	}

	public String ChangedShippingAddressData() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "ChangedShippingAddressData");
	}

	public String checkOutShippingAddressData() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "checkOutShippingAddressData");
	}

	public String getBreadCrumForcategoryPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "categoryPageBreadCrumb");
	}

	public String getCategoriesListForFilter() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "categoriesListForFilter");
	}

	public String getBrandsListForFilter() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "brandsListForFilter");
	}

	public String getItemAvailabilityBeforeLogin() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "itemAvailabilityBeforeLogin");
	}

	public String getShipMethodOptions() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "shipMethodOptions");
	}

	public String getErrorMessageIfMandatoryFieldsLeaveBlankInEditContactPage() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"errorMessageIfMandatoryFieldsLeaveBlankInEditContactPage");
	}

	public String getImagePath() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "imagePath");
	}

	public String getProductDetailPageBreadCrumb() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "productDetailPageBreadCrumb");
	}

	public String getAlertMessageWhenSameItemsAddedTwice() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "alertMessageWhenSameItemsAddedTwice");
	}

	public String getErrorMessageWithoutEnteringMandotryFieldOfReviewLink() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"errorMessageWithoutEnteringMandotryFieldOfReviewLink");
	}

	public String getErrorMessageForUpdationOfCPNWithoutSelectingCPN() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"errorMessageForUpdationOfCPNWithoutSelectingCPN");
	}

	public String getErrorMessageForRemovalOfCPNWithoutSelectingCPN() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath,
				"errorMessageForRemovalOfCPNWithoutSelectingCPN");
	}

	public String getErrorMessageForSpecailCharacter() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "errorMessageForSpecailCharacter");
	}

	public String getSocialMediaData() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "SocialMediaData");
	}

	public String getTableHeaderOfSalesAdminRole() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "tableHeaderOfSalesAdminRole");
	}

	public String getTableHeaderOfUserList() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "tableHeaderOfUserList");
	}

	public String getSalesAdminCredentialForSalesPersonModule() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "salesAdminCredentialForSalesPersonModule");
	}

	public String getSalesRepCredentialForSalesPersonModule() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "salesRepCredentialForSalesPersonModule");
	}

	public String getPasswordForSalesRepCredential() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "passwordForSalesRepCredential");
	}

	public String getSalesRepCredentialForNoCustomerAssign() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "salesRepCredentialForNoCustomerAssign");
	}

	public String getMessageForNoCustomerAssignToSalesRep() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "messageForNoCustomerAssignToSalesRep");
	}

	public void updatePrerequisitesConfigFile(String key, String value) {
		propertyFileMethods.writeDataToPropertyFile(prerequisitesFilePath, key, value);
	}

}