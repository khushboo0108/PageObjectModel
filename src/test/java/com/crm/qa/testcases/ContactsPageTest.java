package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
	 loginPage = new LoginPage();
	 testUtil = new TestUtil();
	 contactsPage = new ContactsPage();
	 homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	 testUtil.switchToFrame();
	// contactsPage=homePage.clickOnContactsLink();
	}
	
	@Test(priority = 1)
	public void verifyContactsLabelTest() {
		boolean flag = contactsPage.verifyContactsLabel();
		Assert.assertTrue(flag, "Contact label not matched");
	}
	
	@Test(priority = 2)
	public void selectContactTest() {
	contactsPage.selectContacts("hhh iiii");
	}
	
	@Test(priority = 3)
	public void selectMultipleContactTest() {
	contactsPage.selectContacts("hhh iiii");
	contactsPage.selectContacts("sdsds sdsdsdsd");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String title,String fname, String lname,String company) {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title,fname,lname,company);
	}
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}
