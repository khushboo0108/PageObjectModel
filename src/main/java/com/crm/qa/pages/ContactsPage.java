package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(id = "first_name")
	WebElement firstName;
	
	@FindBy(id = "surname")
	WebElement lastName;
	
	@FindBy(name = "client_lookup")
	WebElement companyName;
	
	@FindBy(xpath = "//form[@id='contactForm']//input[@type = 'submit' and @value = 'Save' ]")
	WebElement saveBtn;
	
	
	
	//@FindBy(xpath = "//a[contains(text(),'hhh iiii')]//parent::td[@class='datalistrow']//preceding-sibling::td[1]/input[@name='contact_id']")
	//WebElement contactsLaSbel;

  //Initializing the page object model	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}
	
	public void selectContacts(String name) {
		 driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td[@class='datalistrow']"
		 		+ "//preceding-sibling::td[1]/input[@name='contact_id']")).click();;
	}
	
	public void createNewContact(String title, String fName, String lName, String cName) {
		Select s = new Select(driver.findElement(By.name("title")));
		s.selectByVisibleText(title);
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		companyName.sendKeys(cName);
		saveBtn.click();
	}
	

}
