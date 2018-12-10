package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePO;

public class GooglePO extends BasePO {
	
	public GooglePO() {
		PageFactory.initElements(driver, this);
	}
	
	//google text box
	@FindBy(xpath="//input[@type='text']")
	WebElement inputtext;
	
	//google search button
	@FindBy(xpath="//input[text()='Google Search']")
	WebElement search;
	
	
public void googleSearch(String searchtext) {
	inputtext.sendKeys(searchtext);
	inputtext.submit();
	
	
}
}
