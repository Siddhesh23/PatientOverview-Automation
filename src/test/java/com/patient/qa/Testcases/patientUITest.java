package com.patient.qa.Testcases;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.patient.qa.base.TestBase;
import com.patient.qa.pages.LoginPage;
import com.patient.qa.util.TestUtil;

public class patientUITest extends TestBase {

	TestUtil util;
	LoginPage loginPage;

	public patientUITest() {
		super();
	}

	@BeforeMethod
	public void launchPatientOveriewPage() {
		launchPage();
		loginPage = new LoginPage();
		util = new TestUtil();
	}

	@Test
	public void verifyLanguageTest() {
		String[] countryVals = getCountryVals();
		String[] langVals = getLangVals();
		for (int i = 0; i < countryVals.length; i++) {
			util.selectCountryAndLanguage(countryVals[i], langVals[i]);
			if ((countryVals[i]).equals("FR")) {
				Assert.assertTrue("The Patients tab should not be displayed for France",
						loginPage.isPatientsTabPresent());
				Assert.assertTrue("The Professionals tab should not be displayed for France",
						loginPage.isProfessionalsTabPresent());
			} else {
				Assert.assertFalse("The Patients  tab should be displayed for all countries other than France",
						loginPage.isPatientsTabPresent());
				Assert.assertFalse("The Professional tab should be displayed for all countries other than France",
						loginPage.isProfessionalsTabPresent());
			}
		}
	}

	@AfterMethod
	public void closeSessions() {
		driver.quit();
		driver.close();
	}
}