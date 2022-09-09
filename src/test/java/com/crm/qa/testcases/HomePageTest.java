package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();
	}
	
	// Test cases should be separated and should be independent to each other
	//before each test case -- launch the browser
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setup() {
		initialization();
	 loginPage = new LoginPage();
	 testUtil = new TestUtil();
	 contactsPage = new ContactsPage();
	 homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority =1)
	public void verifyHomePageTitleTest() {
		String homePageTitle=homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page title not matched"); 
	}
	
	@Test(priority =2)
	public void verifyUserNameTest() {
		
		testUtil.switchToFrame();
		boolean flag = homePage.verifyCorrectUserName();
		Assert.assertTrue(flag, "Username is not correct");
	}
	
	@Test(priority =3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage=homePage.clickOnContactsLink();
	}

	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}
