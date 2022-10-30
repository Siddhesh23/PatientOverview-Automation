package com.patient.qa.base;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	
	protected static WebDriver driver;
	static Properties prop;
	static String projectPath;
	
	public TestBase()
	{
		
		try {
			prop = new Properties();
			projectPath= System.getProperty("user.dir");
			FileInputStream ip = new FileInputStream(projectPath+"/src/main/java/com/patient/qa/config/config.properties");
			prop.load(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void launchPage()
	{
		String browserName= prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(prop.getProperty("baseURL"));
		System.out.println("Launching page: "+driver.getTitle());
	}
	
	public String[] getCountryVals()
	{
		return (prop.getProperty("country")).split(",");		
	}
	
	public String[] getLangVals()
	{
		return (prop.getProperty("language")).split(",");		
	}
	
	
	

}
