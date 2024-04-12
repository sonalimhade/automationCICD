package SDETProject1.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SDETProject1.TestComponent.BaseTest;
import SDETProject1.TestComponent.Retry;
import SDETProject1.pageobjects.CartPage;
import SDETProject1.pageobjects.CheckoutPage;
import SDETProject1.pageobjects.ConfirmationPage;
import SDETProject1.pageobjects.LandingPage;
import SDETProject1.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() {

		String ProductName = "ZARA COAT 3";
		

		landingpage.loginApplication("test25@gmail.com", "Test@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}
	
	
	@Test
	public void productErrorValidation() {

		String ProductName = "ZARA COAT 3";
		

		ProductCatalogue productcatalogue = landingpage.loginApplication("testgo@gmail.com", "Yuhu@2024");

		List<WebElement> products = productcatalogue.getProductList();

		productcatalogue.addProductToCart(ProductName);

		CartPage cartpage = productcatalogue.goToCart();

		boolean match = cartpage.verifyProductDisplay("ZARA COAT 4");

		Assert.assertFalse(match);
		

	}

}
