package daqri.iTestAutomation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverTest {
	private WebDriver driver;
	String serverurl = "http://itest.daqri.com";
	String path = "/users/sign_in";
	
	@Before
	public void setUp () throws Exception{ 
		System.setProperty("webdriver.chrome.driver", 
				"/Users/tuon-vanphan/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get(serverurl+path);
	}
	
	@After
	public void tearDown() throws Exception{
		driver.close();
		driver.quit();
	}

	@Test
	public void viewProjectTest() throws Exception {
        	Assert.assertTrue("daqri",
                            driver.getTitle().startsWith("daqri"));
        	driver.findElement(By.id("email_signin")).sendKeys("fakeuser");
        	driver.findElement(By.id("password")).sendKeys("fakepassword");
        	driver.findElement(By.id("sign-in-btn")).click();

        	Assert.assertEquals("Invalid email or password.",
                driver.findElement(By.id("flash_")).getText());
        	Thread.sleep(5000);

     }
}
