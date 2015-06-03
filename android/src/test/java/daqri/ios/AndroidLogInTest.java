package daqri.iTestAutomation;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidLogInTest {
	  private AppiumDriver driver;
	  private static final String url = "http://127.0.0.1:4723/wd/hub";
	  
	  @Before
	  public void setUp() throws Exception {
		  // Set up Android client talk to Android simulator
		  final DesiredCapabilities capabilities = new DesiredCapabilities();
		  capabilities.setCapability("deviceName", "Android Emulator");
		  capabilities.setCapability("appPackage", "com.android.settings");
		  capabilities.setCapability("appActivity", ".Settings");
		  driver = new AndroidDriver(new URL(url), capabilities);
	  }

	  @After
	  public void tearDown() throws Exception {
		  driver.quit();
	  }


}
