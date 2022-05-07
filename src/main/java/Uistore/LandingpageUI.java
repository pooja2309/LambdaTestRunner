package Uistore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingpageUI {
	public WebDriver driver;

	public LandingpageUI(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (xpath = "//*[@href='https://www.lambdatest.com/selenium-playground/simple-form-demo\']")
	public WebElement simpleformdemo;
	
	@FindBy(id = "user-message")
	public WebElement entermessage;
	
	@FindBy(id = "showInput")
	public WebElement btngetcheckedvalue;
	
	@FindBy(id = "message")
	public WebElement message;

	@FindBy(xpath = "//*[@href='https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo']")
	public WebElement draganddropsliderlink;
		
	@FindBy(xpath = "//input[@type='range' and @value='15']")
	public WebElement slider15;
	
	public WebElement getslider()
	{
		return slider15;
	}
	
	@FindBy(xpath = "//*[@href='https://www.lambdatest.com/selenium-playground/input-form-demo']")
	public WebElement inputformdemolink;
	
	@FindBy(css = "button[type=submit]")
	public WebElement formsubmit;
	
	
	@FindBy(id = "name")
	public WebElement name;
	
	@FindBy(id = "inputEmail4")
	public WebElement email;
	
	@FindBy(id = "inputPassword4")
	public WebElement password;
	
	@FindBy(id = "company")
	public WebElement company;
	
	@FindBy(id = "websitename")
	public WebElement websitename;
	
	@FindBy(id = "inputCity")
	public WebElement city;
	
	@FindBy(id = "inputAddress1")
	public WebElement add1;
	
	@FindBy(id = "inputAddress2")
	public WebElement add2;
	
	@FindBy(id = "inputState")
	public WebElement state;
	
	@FindBy(id = "inputZip")
	public WebElement zip;
	
	@FindBy(xpath = "//*[contains(text(),'Choose an option')]")
	public WebElement dropdowncity;
		
	@FindBy(xpath = "//*[contains(text(),'Thanks for contacting us, we will get back to you shortly.')]")
	public WebElement thanksmsg;
	
	
	
	
}
