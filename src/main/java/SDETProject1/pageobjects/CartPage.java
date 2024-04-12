package SDETProject1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SDETProject1.abstractcomponent.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	By ProductsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By ToastMsg = By.id("toast-container");
	
	public boolean verifyProductDisplay(String ProductName)
	{
		boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutBtn.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}
	
	
	 

}
