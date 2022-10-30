package com.patient.qa.util;

import com.patient.qa.pages.LoginPage;

public class TestUtil extends LoginPage {
	
	
	public void selectCountryAndLanguage(String country, String language)
	{
		if(!isDialogBoxVisible())
			clickLanguageText();
			selectCountryLangDrpDwn(country,language);
		clickSubmitBtn();		
	}
}
