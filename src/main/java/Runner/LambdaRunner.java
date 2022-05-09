package Runner;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Reusablecomponents.WebDriverHelper;
import Uistore.LandingpageUI;
import Utility.Extentreports;
import Utility.Readproperty;

public class LambdaRunner {
	static Readproperty rp = new Readproperty();
//	static WebDriver driver = null;
	WebDriverHelper helper = null;
	Extentreports er;
	LandingpageUI lp;
	private RemoteWebDriver driver;
    private String Status = "failed";
    
    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "pooja.adi2309" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "EFstIA2MPcBqFqH2NUiHuLRK87rhW1uyKuXA8CMO5E24MaQmYz" : System.getenv("LT_ACCESS_KEY");
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        // Configure your capabilities here
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "92.0");
        caps.setCapability("resolution", "1024x768");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
        lp = new LandingpageUI(driver);
        
        
    }
	
//	@Test
//	public void setdriver() {
//		String browser = rp.getdriver();
//		if (browser.equalsIgnoreCase("chrome")) {
//			System.out.println(System.getProperty("user.dir"));
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
//			 driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
//			helper = new WebDriverHelper(driver);
//			
//
//		}
//	}
	
	//Sets up the URl in the browser
	
//	@Test
//	public void openwebsite() {
//		String url = rp.getUrl();
//		helper.openwebsite(url);
//		lp = new LandingpageUI(driver);
//		
//	}
	
	@Test
	public void Testcase() throws InterruptedException {
		er = new Extentreports(driver);
		er.startTest("Simple Form Demo");
		driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		String url = driver.getCurrentUrl();
		System.out.println(url);
		Assert.assertEquals(url, "https://www.lambdatest.com/selenium-playground/simple-form-demo");
		String welcome = "Welcome to LambdaTest";
//		driver.findElement(By.id("user-message")).sendKeys(welcome);
		lp.entermessage.sendKeys(welcome);
//		driver.findElement(By.id("showInput")).click();
		lp.btngetcheckedvalue.click();
		Assert.assertEquals(driver.findElement(By.id("message")).getText(), welcome);
		er.endTest();
		
		er.startTest("Slider");

		driver.get("https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo");

		WebElement slider = lp.getslider();
		for(int i=0; i<80; i++) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}
		Assert.assertEquals(slider.getAttribute("value"), "95");
		System.out.println("Value slider: "+slider.getAttribute("value"));
		er.endTest();
		Status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");
        

        driver.get("https://www.lambdatest.com/selenium-playground/input-form-demo");
		lp.inputformdemolink.click();
		lp.formsubmit.click();
		lp.name.sendKeys("John");
		lp.email.sendKeys("exampleemail@xyz.com");
		lp.password.sendKeys("password123");
		lp.company.sendKeys("ABCD");
		lp.websitename.sendKeys(url);
		
		Select city = new Select(driver.findElement(By.xpath("//*[@name='country']")));
		city.selectByIndex(3);
		Thread.sleep(3000);
		lp.city.sendKeys("City");
		lp.add1.sendKeys("Address Line 1");
		lp.add2.sendKeys("Address Line 2");
		lp.state.sendKeys("State");
		lp.zip.sendKeys("435522");
		Thread.sleep(3000);
		lp.formsubmit.click();
		Thread.sleep(3000);
		Assert.assertEquals(lp.thanksmsg.getText(), "Thanks for contacting us, we will get back to you shortly.");
		er.endTest();
		
	}
	
//	@Test
//	public void TestcaseTwo() throws InterruptedException {
//		er.startTest("Slider");
//		String url = rp.getUrl();
//		helper.openwebsite("https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo");
////		lp.draganddropsliderlink.click();
//		WebElement slider = lp.getslider();
//		for(int i=0; i<80; i++) {
//			slider.sendKeys(Keys.ARROW_RIGHT);
//		}
//		Assert.assertEquals(slider.getAttribute("value"), "95");
//		System.out.println("Value slider: "+slider.getAttribute("value"));
//		er.endTest();
//		Status = "passed";
//        Thread.sleep(150);
//
//        System.out.println("TestFinished");
//	}
//	
//	/**
//	 * @throws InterruptedException 
//	 * 
//	 */
//	@Test
//	public void TestcaseThree() throws InterruptedException {
//		er.startTest("Form Fill and Submit");
//		String url = rp.getUrl();
//		helper.openwebsite("https://www.lambdatest.com/selenium-playground/input-form-demo");
////		lp.inputformdemolink.click();
////		lp.formsubmit.click();
//		lp.name.sendKeys("John");
//		lp.email.sendKeys("exampleemail@xyz.com");
//		lp.password.sendKeys("password123");
//		lp.company.sendKeys("ABCD");
//		lp.websitename.sendKeys(url);
//		
//		Select city = new Select(driver.findElement(By.xpath("//*[@name='country']")));
//		city.selectByIndex(3);
//		Thread.sleep(3000);
//		lp.city.sendKeys("City");
//		lp.add1.sendKeys("Address Line 1");
//		lp.add2.sendKeys("Address Line 2");
//		lp.state.sendKeys("State");
//		lp.zip.sendKeys("435522");
//		Thread.sleep(3000);
//		lp.formsubmit.click();
//		Thread.sleep(3000);
//		Assert.assertEquals(lp.thanksmsg.getText(), "Thanks for contacting us, we will get back to you shortly.");
//		er.endTest();
//	}
//	
	
//	//Closure
//	@AfterTest
//	public void close() {
//		
//		driver.close();
//	}
	
	 @AfterMethod
	    public void tearDown() {
	        driver.executeScript("lambda-status=" + Status);
	        driver.quit();
	    }


}
