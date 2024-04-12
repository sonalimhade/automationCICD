package SDETProject1.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SDETProject1.TestComponent.BaseTest;
import SDETProject1.pageobjects.CartPage;
import SDETProject1.pageobjects.CheckoutPage;
import SDETProject1.pageobjects.ConfirmationPage;
import SDETProject1.pageobjects.LandingPage;
import SDETProject1.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;
	public CartPage cartpage;
	public CheckoutPage checkoutpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and (.+)$")
	public void Logged_with_username_and_Password(String username, String Password)
	{
		productcatalogue = landingpage.loginApplication(username, Password);
	}
	
	@When ("^I add (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		List<WebElement> products = productcatalogue.getProductList();

		productcatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String productName)
	{
		cartpage = productcatalogue.goToCart();

		boolean match = cartpage.verifyProductDisplay(productName);

		Assert.assertTrue(match);

		checkoutpage = cartpage.goToCheckout();

		checkoutpage.selectCountry("india");

		confirmationpage= checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_dispalyed_on_confirmation(String string)
	{
		String ConfirmMSG = confirmationpage.getConfirmationMSG();
		Assert.assertTrue(ConfirmMSG.equalsIgnoreCase(string));
		driver.close();
	}
	
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void message_is_displayed(String str1) throws Throwable
	{
		Assert.assertEquals(str1, landingpage.getErrorMessage());
		driver.close();
	}
}
