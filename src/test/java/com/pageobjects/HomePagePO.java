package com.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BasePO;

public class HomePagePO extends BasePO

{
	public HomePagePO() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@value='Sign in']")
	WebElement signIn;

	public void signIn() throws Exception {

		waitUntilElementVisibility(username);
		username.sendKeys(prop.getProperty("username"));
		password.sendKeys(prop.getProperty("password"));
		clickElement(signIn);
	}
}
