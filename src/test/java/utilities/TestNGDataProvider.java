package utilities;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import utility.MSExcelMethods;

public class TestNGDataProvider {

	@DataProvider(name = "ReadDataFromExcel")
	public static Object[][] readDataFromExcel(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/TestData.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "Login")
	public static Object[][] login(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/Login.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "Registration")
	public static Object[][] registration(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/Registration.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "PDPAndPLP")
	public static Object[][] pdpAndPLP(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/PDPAndPLP.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "ForgotPassword")
	public static Object[][] forgotPassword(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ForgotPassword.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "POCheckout")
	public static Object[][] poCheckout(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/POCheckout.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "Shopping Cart")
	public static Object[][] shoppingCart(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ShoppingCart.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "Share Cart")
	public static Object[][] shareCart(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ShareCart.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;
	}

	@DataProvider(name = "Saved Cart")
	public static Object[][] savedCart(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/SavedCart.xlsx");
		Object[][] testdata = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return testdata;

	}

	@DataProvider(name = "ProductGroup")
	public static Object[][] productGroup(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductGroup.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductDetail_V1")
	public static Object[][] productDetailVersion1(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductDetailVersion1.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductDetail_V2")
	public static Object[][] productDetailVersion2(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductDetailVersion2.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductDetail_V3")
	public static Object[][] productDetailVersion3(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductDetailVersion3.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductDetail_V4")
	public static Object[][] productDetailVersion4(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductDetailVersion4.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductDetail_V5")
	public static Object[][] productDetailVersion5(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductDetailVersion5.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "UserManagement")
	public static Object[][] userManagementModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/UserManagement.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "AccountDashboard")
	public static Object[][] accountDashboardModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/AccountDashboard.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "AddNewCreditCard")
	public static Object[][] addNewCreditCardModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/AddNewCreditCard.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "MultipleTestData")
	public static Object[][] multipleTestData(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/MultipleTestData.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ShopByManufacturers")
	public static Object[][] shopByManufacturers(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ShopByManufacturers.xlsx");

		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;

	}

	@DataProvider(name = "SendThisPage")
	public static Object[][] sendThisPageModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/SendThisPage.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductDetail_SKU")
	public static Object[][] productDetail_SKUModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductDetail_SKU.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	/*@DataProvider(name = "OpenOrder")
	public static Object[][] openOrderModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/OpenOrder.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}*/

	@DataProvider(name = "OrderDetail")
	public static Object[][] orderDetailModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/OrderDetail.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ReOrderCart")
	public static Object[][] reOrderCartModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ReOrderCart.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "LeftMenu")
	public static Object[][] leftMenuModuleTest(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/LeftMenu.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ShopByBrands")
	public static Object[][] shopByBrands(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ShopByBrands.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ChangePassword")
	public static Object[][] ChangePassword(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ChangePassword.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "Locations")
	public static Object[][] Locations(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/Locations.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}

	@DataProvider(name = "ProductListAndGrid")
	public static Object[][] ProductListAndGrid(Method methodName) throws Exception {
		MSExcelMethods msExcelMethods = new MSExcelMethods();
		File file = new File("src/test/resources/TestDataFiles/ProductListAndGrid.xlsx");
		Object data[][] = msExcelMethods.readDataFromExcel(file.getAbsolutePath(), methodName.getName());
		return data;
	}
}
