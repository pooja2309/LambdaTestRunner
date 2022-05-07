package Runner;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Reusablecomponents.WebDriverHelper;
import Uistore.LandingpageUI;
import Utility.Extentreports;
import Utility.Readproperty;

public class LambdaRunner {
	static Readproperty rp = new Readproperty();
	static WebDriver driver = null;
	WebDriverHelper helper = null;
	Extentreports er;
	LandingpageUI lp;
	
	
	@Test
	public void setdriver() {
		String browser = rp.getdriver();
		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			helper = new WebDriverHelper(driver);
			

		}
	}
	
	//Sets up the URl in the browser
	
	@Test(dependsOnMethods = { "setdriver" })
	public void openwebsite() {
		String url = rp.getUrl();
		helper.openwebsite(url);
		lp = new LandingpageUI(driver);
		
	}
	
	@Test(dependsOnMethods = ("openwebsite"))
	public void TestcaseOne() {
		er = new Extentreports(driver);
		er.startTest("Simple Form Demo");
		lp.simpleformdemo.click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		Assert.assertEquals(url, "https://www.lambdatest.com/selenium-playground/simple-form-demo");
		String welcome = "Welcome to LambdaTest";
		lp.entermessage.sendKeys(welcome);
		lp.btngetcheckedvalue.click();
		Assert.assertEquals(lp.message.getText(), welcome);
		er.endTest();
		
	}
	
	@Test(dependsOnMethods = ("TestcaseOne"))
	public void TestcaseTwo() {
		er.startTest("Slider");
		String url = rp.getUrl();
		helper.openwebsite(url);
		lp.draganddropsliderlink.click();
		WebElement slider = lp.getslider();
		for(int i=0; i<80; i++) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}
		Assert.assertEquals(slider.getAttribute("value"), "95");
		System.out.println("Value slider: "+slider.getAttribute("value"));
		er.endTest();
	}
	
	/**
	 * @throws InterruptedException 
	 * 
	 */
	@Test(dependsOnMethods = ("TestcaseTwo"))
	public void TestcaseThree() throws InterruptedException {
		er.startTest("Form Fill and Submit");
		String url = rp.getUrl();
		helper.openwebsite(url);
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
	
	
	//Closure
	@AfterTest
	public void close() {
		
		driver.close();
	}

}
