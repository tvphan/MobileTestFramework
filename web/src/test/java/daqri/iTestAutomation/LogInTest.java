package daqri.iTestAutomation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LogInTest {
	
		private WebDriver driver;
		String serverurl = "http://itest.daqri.com";
		String path = "/users/sign_in";
		
		@Before
		public void setUp () throws Exception{
			driver = new FirefoxDriver();
			driver.navigate().to(serverurl+path);
		}
		
		@After
		public void tearDown() throws Exception{
			driver.quit();
		}
		@Test()
		public void failedLogInTest() throws Exception{
		        Assert.assertTrue("daqri",
		                            driver.getTitle().startsWith("daqri"));
		        driver.findElement(By.id("email_signin")).sendKeys("fakeuser");
		        driver.findElement(By.id("password")).sendKeys("fakepassword");
		        driver.findElement(By.id("sign-in-btn")).click();

		        Assert.assertEquals("Invalid email or password.",
	                    driver.findElement(By.id("flash_")).getText());
	     	}
		
		@Test()
		public void validLogInTest() throws Exception{
			
			driver.findElement(By.id("email_signin")).sendKeys("superuser@daqri.com");
	        	driver.findElement(By.id("password")).sendKeys("toaster");
	        	driver.findElement(By.id("sign-in-btn")).click();
	        	Assert.assertEquals("Industrial 4D Studio",
	                driver.getTitle());
		}
		
		@Test()
		public void rememberMeTest() throws Exception{
			
			driver.findElement(By.id("email_signin")).sendKeys("superuser@daqri.com");
	        	driver.findElement(By.id("password")).sendKeys("toaster");
	        	driver.findElement(By.id("user_remember_me")).click();
	        	driver.findElement(By.id("sign-in-btn")).click();
	        	Assert.assertEquals("Industrial 4D Studio",
	                driver.getTitle());
	   
	        	/*driver.findElement(By.id("header-menu-list")).findElement(By.id("logout")).click();
	       	 	Assert.assertEquals("Signed out successfully.",
	                driver.findElement(By.id("flash_")).getText());
	        
	        	Assert.assertEquals("superuser@daqri.com", 
	        		driver.findElement(By.id("email_signin")).getText()); */

		}
		
		@Test()
		public void ForgotPasswdTest() throws Exception{

			driver.findElement(By.id("show-forgot-password")).click();
			Assert.assertEquals("", 
					driver.findElement(By.id("request-password-reset-btn")).getText());
			Assert.assertEquals(true,
					   driver.findElement(By.id("forgot-password")).getText().contains("FORGOT PASSWORD"));

		}


}
