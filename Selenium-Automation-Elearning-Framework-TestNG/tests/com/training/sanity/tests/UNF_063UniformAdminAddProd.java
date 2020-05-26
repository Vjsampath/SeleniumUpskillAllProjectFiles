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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.training.pom.UniformAdminLoginPOM;
import com.training.pom.UniformAdminHomePOM;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class UNF_063UniformAdminAddProd {

	private WebDriver driver;
	private String baseUrl;
	private UniformAdminHomePOM UniformAdminHomePOM;
	private UniformAdminLoginPOM UniformAdminLoginPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		UniformAdminLoginPOM = new UniformAdminLoginPOM(driver);
		UniformAdminHomePOM = new UniformAdminHomePOM(driver);
		baseUrl = properties.getProperty("baseURL2");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void adminLogin() throws InterruptedException {

		// Login as admin

		UniformAdminLoginPOM.sendUserName("admin");
		UniformAdminLoginPOM.sendPassword("admin@123");
		UniformAdminLoginPOM.clickLoginBtn();
	}

	@Test(dependsOnMethods= {"adminLogin"})
	public void adminAddProd() throws InterruptedException {
		// Action for mouseHover Category icon
		WebElement weCataLink = driver.findElement(By.xpath("//i[@class='fa fa-tags fa-fw']"));
		Actions Act1 = new Actions(driver);
		Action mouseOverCatagoryLink = Act1.moveToElement(weCataLink).click().build();
		mouseOverCatagoryLink.perform();

		UniformAdminHomePOM.clickProductLink();
		UniformAdminHomePOM.clickAddPlus();
		UniformAdminHomePOM.sendProductName("Blazer_Girls(10)");
		UniformAdminHomePOM.sendMetaTag("Blazer for Girls");
		UniformAdminHomePOM.clickDataTab();
		UniformAdminHomePOM.sendModel("BLG-112");
		UniformAdminHomePOM.sendPrice("1111");
		UniformAdminHomePOM.sendquantity("200");

		UniformAdminHomePOM.clickLinkTab();
		UniformAdminHomePOM.selectcatagory("Uniform");
		driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a[contains(text(),'Uniform')]")).click();

		UniformAdminHomePOM.clickSavebtn();

		String Actual = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(Actual.contains("Success"));
		System.out.println("success message");
		screenShot.captureScreenShot("UNF_063UniformAdminAddProd success message");

	}
}
