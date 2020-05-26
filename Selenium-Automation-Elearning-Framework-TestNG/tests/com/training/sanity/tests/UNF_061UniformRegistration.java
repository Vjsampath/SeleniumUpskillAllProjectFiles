package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.UniformHomePOM;
import com.training.pom.UniformRegisterAccountPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class UNF_061UniformRegistration {

	private WebDriver driver;
	private String baseUrl;
	private UniformHomePOM UniformHomePOM;
	private UniformRegisterAccountPOM UniformRegisterAccountPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		UniformHomePOM = new UniformHomePOM(driver);
		UniformRegisterAccountPOM = new UniformRegisterAccountPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test(dataProvider = "excelreginputs", dataProviderClass = LoginDataProviders.class)
	public void myAccounRegistration(String firstName,String lastName,String email,String telephone,String add1,String add2,String city,
			String postalcode, String country,String region,String password,String passwordConfirm) throws InterruptedException {
		
	//Action for mouseHover My Account icon	
		WebElement weMyAccount1 = driver
				.findElement(By.xpath("//li[@class='dropdown myaccount']//span[@class='caret']"));
		Actions Act1 = new Actions(driver);
		Action mouseOverMyAcc1 = Act1.moveToElement(weMyAccount1).build();
		mouseOverMyAcc1.perform();
		
		String expectedPageHeader = "REGISTER ACCOUNT";
		String expectedAccSuccessCreatedMsg = "Congratulations! Your new account has been successfully created!";
		

		//Click Register to open Registration form
		UniformHomePOM.myAccountDropdownBtn();
		UniformHomePOM.clickmyAccountRegister();
	
		
		// Check for Register Form Header
//		Assert.assertTrue("Page Header Does Not Match ", expectedPageHeader.equals(UniformRegisterAccountPOM.UniformRegisterPageHeader()));
//		screenShot.captureScreenShot("UFM_001MyAccountReg Page Header is Register Account");
		
		
		// Enter of all mandatory field of Form
		//Personal Details

		
		UniformRegisterAccountPOM.sendfirstName(firstName);
		UniformRegisterAccountPOM.sendlastName(lastName);
		UniformRegisterAccountPOM.sendemail(email);
		UniformRegisterAccountPOM.sendtelephone(telephone);
	
		//Address
		UniformRegisterAccountPOM.sendaddress1(add1);
		UniformRegisterAccountPOM.sendaddress2(add2);
		UniformRegisterAccountPOM.sendcity(city);
		UniformRegisterAccountPOM.sendpostcode(postalcode);
		UniformRegisterAccountPOM.selectcountry(country);
		UniformRegisterAccountPOM.selectzone(region);
		
		//Password
		UniformRegisterAccountPOM.sendPassword(password);
		UniformRegisterAccountPOM.sendconfirmPwd(passwordConfirm);
		
		//Newsletter
		UniformRegisterAccountPOM.checkrbnewsSubscribeNo();
		UniformRegisterAccountPOM.checkcbPrivPolicy();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		UniformRegisterAccountPOM.clickContinueBtn();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']//p[contains(text(),'Congratulations! Your new account has been success')]")));
		String successAccCreatedMsg = driver.findElement(By.xpath("//div[@id='content']//p[contains(text(),'Congratulations! Your new account has been success')]")).getText();

		System.out.println("success Account Created message " + successAccCreatedMsg);

		Assert.assertEquals(expectedAccSuccessCreatedMsg, successAccCreatedMsg);
		System.out.println("Assert Acct Creation Pass");
		screenShot.captureScreenShot("UNF_061UniformReg success message");
		
	}
}
