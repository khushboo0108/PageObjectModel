package com.crm.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase {
	

	@FindBy(xpath="//td[contains(text(),'User: khushboo sinha')]")
	@CacheLookup // it is used for better performance of script. 
	//It holds all the values in a cache and instead of hitting browser for 100 times, 
	//the elements will be pick up from cache memory. But if the page is refreshed or dom is changed, 
	//then you will get StaleElementException. So use it only when you think the element 
	//will not get changed.
	WebElement userNameLabel;
	
	@FindBy(xpath ="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath ="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath ="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[text()='New Contact']")
	WebElement newContactLink;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
		
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed(); 
	}
	
	public void clickOnNewContactLink() {
		Actions a = new Actions(driver);
		a.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
}
