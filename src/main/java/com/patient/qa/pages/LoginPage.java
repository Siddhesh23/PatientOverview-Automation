package com.patient.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.patient.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(xpath="//div[contains(@class,'navbar')]//span[@id='languageText']")
	WebElement languageText;
	
	@FindBy(xpath="//div[@class='modal-dialog']")
	WebElement dialogBox;
	
	@FindBy(id="countryDropdownBtn")
	WebElement countryDrpdwn;
	
	@FindBy(id="languageDropdownBtn")
	WebElement langDrpdwn;
	
	@FindBy(id="modalSubmit")
	WebElement submitBtn;
	
	@FindBy(id="patHeaderLink")
	WebElement patientsTab;
	
	@FindBy(id="proHeaderLink")
	WebElement professionalsTab;
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	//initialize the web elements with driver
	public LoginPage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	
	
	public boolean isDialogBoxVisible()
	{	
		return dialogBox.isDisplayed();
	
}
	
	public boolean isPatientsTabPresent()
	{
		return driver.findElements(By.id("patHeaderLink")).isEmpty();
}	
	
	public boolean isProfessionalsTabPresent()
	{
		return driver.findElements(By.id("proHeaderLink")).isEmpty();
}
	
	public void clickLanguageText()
	{
	driver.navigate().refresh();	
	languageText.click();
	}
	
	public void selectCountryLangDrpDwn(String country,String language)
	{	
	
	Actions actions = new Actions(driver);	
	WebElement countryVal = driver.findElement(By.xpath("//ul[@id='countryDropdown']//a[@value='"+country+"']"));
	WebElement langVal = driver.findElement(By.xpath("//ul[@id='languageDropdown']//a[@value='"+language+"']"));
	 wait.until(ExpectedConditions.elementToBeClickable (By.id("languageDropdownBtn")));
	 actions.moveToElement(langDrpdwn).click().build().perform();
	 
	 for (int i=0; i<=4;i++) {
		 try {			
	 actions.moveToElement(langVal).click().build().perform();
	 break;
		 }
		 catch(Exception e) {
		 System.out.print(e.getMessage());
		 }
	 }
	 
	actions.moveToElement(countryDrpdwn).click().build().perform();
		 for (int i=0; i<=4;i++) {
		 try {
			 
	 actions.moveToElement(countryVal).click().build().perform();
	 break;
		 }
		 catch(Exception e) {
		 System.out.print(e.getMessage());
		 }
	 }
		
	}
	
	public void clickSubmitBtn()
	{
		wait.until(ExpectedConditions.elementToBeClickable (By.id("modalSubmit")));
		submitBtn.click();
	
	}

	
}