package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UniformAdminHomePOM {
	private WebDriver driver; 
	
	public UniformAdminHomePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//i[@class='fa fa-tags fa-fw']")
	private WebElement catorgorylink; 
	
	@FindBy(xpath="//li[@id='catalog']//ul//li//a[contains(text(),'Products')]")
	private WebElement productLink;
	
	@FindBy(xpath="//div[@class='pull-right']//a[@class='btn btn-primary']")
	private WebElement addPlus; 
	
	@FindBy(id="input-name1")
	private WebElement sendProductName;
	
	@FindBy(id="input-meta-title1")
	private WebElement sendMetaTagTitle;
	
	@FindBy(xpath="//a[contains(text(),'Data')]")
	private WebElement dataTab;
	
	@FindBy(id="input-model")
	private WebElement sendModel;

	@FindBy(id="input-price")
	private WebElement sendPrice;
	
	@FindBy(id="input-quantity")
	private WebElement sendquantity;
	
	public void clickProductLink() {
		this.productLink.click(); 
	}
	
	public void clickCatorgorylink() {
		this.catorgorylink.click(); 
	}
	
	public void clickAddPlus() {
		this.addPlus.click(); 
	}
	
	
	public void clickDataTab() {
		this.dataTab.click(); 
	}
	
	public void sendModel(String sendModel) {
		this.sendModel.clear();
		this.sendModel.sendKeys(sendModel);
	}
	
	public void sendPrice(String sendPrice) {
		this.sendPrice.clear();
		this.sendPrice.sendKeys(sendPrice);
	}
	
	public void sendquantity(String sendquantity) {
		this.sendquantity.clear();
		this.sendquantity.sendKeys(sendquantity);
	}
	
	public void sendProductName(String sendProductName) {
		this.sendProductName.clear();
		this.sendProductName.sendKeys(sendProductName);
	}
	
	public void sendMetaTag(String sendMetaTagTitle) {
		this.sendMetaTagTitle.clear(); 
		this.sendMetaTagTitle.sendKeys(sendMetaTagTitle); 
	}
	
	
	@FindBy(xpath="//a[contains(text(),'Links')]")
	private WebElement clickLinkTab;
	
	public void clickLinkTab() {
		this.clickLinkTab.click(); 
	}
	@FindBy(xpath="//div[@class='pull-right']//button[@class='btn btn-primary']")
	private WebElement clickSave;
	
	public void clickSavebtn() {
		this.clickSave.click(); 
	}
	
	@FindBy(xpath="//input[@id='input-category']")
	private WebElement catagory;
	
	public void selectcatagory(String catagory) {
		this.catagory.click();
		this.catagory.sendKeys(catagory);
	//	Select catagoryselected = new Select(this.catagory);
	//	catagoryselected.selectByVisibleText(catagory);
	}
}
