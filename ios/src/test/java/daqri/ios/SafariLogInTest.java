package daqri.ios;

import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.URL;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SafariLogInTest {
	private WebDriver driver;
	private static final String url = "http://127.0.0.1:4723/wd/hub";
	String serverurl = "http://itest.daqri.com";
	//String path = "/users/sign_in";
	
	@Before
	public void setUp() throws Exception {
		// Set up Safari client talk to IOS simulator
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "iPad Air");
		capabilities.setCapability("platformName", "ios");
		capabilities.setBrowserName("safari");
		driver = new IOSDriver(new URL(url), capabilities);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	@Test
	public void invalidLogInTest() throws Exception {
		// test fake user and pass
		driver.get(serverurl);
		Thread.sleep(5000);
		Assert.assertEquals("daqri", driver.getTitle());
		System.out.println(driver.getPageSource());
		Assert.assertTrue("daqri",
                driver.getTitle().startsWith("daqri"));
		driver.findElement(By.id("email_signin")).sendKeys("fakeuser");
		Thread.sleep(5000);
		driver.findElement(By.id("password")).sendKeys("fakepassword");
		Thread.sleep(5000);
		driver.findElement(By.id("sign-in-btn")).click();
		Thread.sleep(5000);
		Assert.assertEquals("Invalid email or password.",
				driver.findElement(By.id("flash_")).getText());
		
	}
	
	@Test()
	public void validLogInTest(){
		driver.get(serverurl);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("daqri", driver.getTitle());
		driver.findElement(By.id("email_signin")).sendKeys("superuser@daqri.com");
        	driver.findElement(By.id("password")).sendKeys("toaster");
        	driver.findElement(By.id("sign-in-btn")).click();
        	Assert.assertEquals("daqri", driver.getTitle());
        	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        	driver.getPageSource();
	}
}
