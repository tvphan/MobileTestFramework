package daqri.iTestAutomation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IOSLogInTest {
	private IOSDriver driver;

    @Before
    public void setUp() throws Exception {
        // set up iOS client talk to iOS simulator
        
    	File appDir = new File(System.getProperty("user.dir"));
    	File app = new File(appDir, "DAQRI-sim.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("deviceName", "iPad Air");
        capabilities.setCapability("app", app.getAbsolutePath());
        //System.out.println ("here is the app: " + app.getAbsolutePath());
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
    }

    @After
    public void tearDown() throws Exception {
    	Thread.sleep(5000);
        driver.quit();
    }
    
    @Test
    public void invalidLogInTest() throws Exception {
    	WebElement text = driver.findElement(By.xpath("//UIATextField[1]"));
    	Assert.assertTrue("email", text.isEnabled());
        Assert.assertTrue(driver.findElement(By.id("password")).isEnabled());
        WebElement button = driver.findElement(By.id("Button"));
        text.sendKeys("fakeuser");
        driver.findElement(By.id("password")).sendKeys("fakepassword");
        button.click();
        Assert.assertNotNull(driver.getErrorHandler());
        //Assert.assertTrue(driver.getWindowHandle().contains ("Invalid email & password combination"));
    	
    }
    
    @Test
    public void validLogInTest() throws Exception {
    	driver.findElementById("email").sendKeys("superuser@daqri.com");
    	driver.findElementById("password").sendKeys("toaster");
    	driver.findElementById("Button").click();

        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
          System.out.println(contextName);
          if (contextName.contains("WEBVIEW")){
            driver.context(contextName);
          }
        }
    	//driver.findElementsByCssSelector("Projects");
    }
}
