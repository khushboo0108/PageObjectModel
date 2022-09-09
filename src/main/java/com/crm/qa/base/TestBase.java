package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	static WebEventListener eventListener;
	//@SuppressWarnings("deprecation")
	static
	EventFiringWebDriver e_driver;
	
		public TestBase() {
			
			try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:/Users/khsinha/eclipse-workspace/FreeCRMTest/src/main/java/com/crm/qa/"
					+ "config/config.properties");
			prop.load(ip);
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();			
			}
		}
		
	@SuppressWarnings("deprecation")
	public static void initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/Users/khsinha/OneDrive - Capgemini/Documents/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C:/Users/khsinha/OneDrive - Capgemini/Documents/geckodriver");
			driver = new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		//Now create object with event listener handler to register with the EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);//if app is taking too much time then again we need to change the vale of wait.So its better to create a Util class and add these in that class
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
}
