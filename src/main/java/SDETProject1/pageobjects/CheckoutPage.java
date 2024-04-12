package SDETProject1.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SDETProject1.abstractcomponent.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath=".//input[@placeholder='Select Country']")
	WebElement countryDrp;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountryResult;
	
	@FindBy(css=".btnn")
	WebElement submitBtn;
	
	By countrySearchResults = By.cssSelector(".ta-results");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By ToastMsg = By.id("toast-container");
	
	public void selectCountry(String searchKey)
	{
		selectDynamicDropdown(countryDrp, searchKey);
		
		waitForElementToAppear(countrySearchResults);
		
		selectCountryResult.click();
		
		
	}
	
	public ConfirmationPage submitOrder()
	{
		submitBtn.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
	
	
	
	
	 

}
