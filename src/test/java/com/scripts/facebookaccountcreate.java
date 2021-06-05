package com.scripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.base.TestBase;

public class facebookaccountcreate extends TestBase {
	public static Logger logger = LoggerFactory.getLogger(loginHomePage.class);
	@BeforeSuite
	public void report() {
		// extentReport = ExtentManager.initExtentReporter();

	}

	@BeforeMethod
	public void setUp() {
		initialization(); 
	}

	@Test
	public void testLogin() throws Exception {
		driver.get("https://www.facebook.com/");   
        driver.findElement(By.xpath("id('u_0_1')")).sendKeys("Tomprakash");
        driver.findElement(By.xpath("id('u_0_3')")).sendKeys("Sahu");
        driver.findElement(By.xpath("id('u_0_6')")).sendKeys("7869084527");
        driver.findElement(By.xpath("id('u_0_9')")).sendKeys("7869084527");
        driver.findElement(By.xpath("id('u_0_b')")).sendKeys("cs083595");
        Select date = new Select(driver.findElement(By.xpath("id('day')")));
        date.selectByVisibleText("24");
        Select month = new Select(driver.findElement(By.xpath("id('month')")));
        month.selectByVisibleText("Dec");
        Select year = new Select(driver.findElement(By.xpath("id('year')")));
        year.selectByVisibleText("1989");
        driver.findElement(By.className("_58mt")).click();
        driver.findElement(By.cssSelector("[id=u_0_j]")).click();
        driver.findElement(By.cssSelector("[id=u_0_n]")).click();
    
	}

	
	@AfterMethod
	public void tearDown() throws IOException {
		capture(driver);		
		driver.quit();
		
	}

}
