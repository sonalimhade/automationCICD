package SDETProject1.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SDETProject1.TestComponent.BaseTest;
import SDETProject1.pageobjects.CartPage;
import SDETProject1.pageobjects.CheckoutPage;
import SDETProject1.pageobjects.ConfirmationPage;
import SDETProject1.pageobjects.LandingPage;
import SDETProject1.pageobjects.OrdersPage;
import SDETProject1.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	public String ProductName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) {

		
		

		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("Password"));

		List<WebElement> products = productcatalogue.getProductList();

		productcatalogue.addProductToCart(input.get("Product"));

		CartPage cartpage = productcatalogue.goToCart();

		boolean match = cartpage.verifyProductDisplay(input.get("Product"));

		Assert.assertTrue(match);

		CheckoutPage checkoutpage = cartpage.goToCheckout();

		checkoutpage.selectCountry("india");

		ConfirmationPage confirmationpage = checkoutpage.submitOrder();

		String ConfirmMSG = confirmationpage.getConfirmationMSG();
		Assert.assertTrue(ConfirmMSG.equalsIgnoreCase("Thankyou for the order."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productcatalogue = landingpage.loginApplication("test254@gmail.com", "Test@123");
		OrdersPage orderspage = productcatalogue.goToOrders();
		Assert.assertTrue(orderspage.verifyOrdersDisplay(ProductName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data = getDataOnHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\SDETProject1\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		//return new Object[][] {{"test254@gmail.com", "Test@123","ZARA COAT 3"},{"testgo@gmail.com", "Yuhu@2024","ADIDAS ORIGINAL"}};
	}
	
	
	
	/*@DataProvider
	public Object[][] getData()
	{
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "test254@gmail.com");
		map.put("Password", "Test@123");
		map.put("Product", "ZARA COAT 3");
		
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "testgo@gmail.com");
		map1.put("Password", "Yuhu@2024");
		map1.put("Product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
		
		//return new Object[][] {{"test254@gmail.com", "Test@123","ZARA COAT 3"},{"testgo@gmail.com", "Yuhu@2024","ADIDAS ORIGINAL"}};
	}*/

}
