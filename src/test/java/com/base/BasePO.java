package com.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePO extends TestBase {

	static int defaultWaitTime = Integer.parseInt(prop.getProperty("defaultWaitTime"));
	static int iternationWaitTime =Integer.parseInt(prop.getProperty("iternationWaitTime"));
	static WebDriverWait wait = new WebDriverWait(driver, defaultWaitTime);

	// Wait for a particular element to visible
	public static void waitUntilElementVisibility(WebElement element) throws Exception {

wait.until(ExpectedConditions.visibilityOf(element));		
		for (int i = 0; i <= 20; i++) {
			try {
				element.isDisplayed();
				break;

			} catch (Exception e) {
				if (i == 40) {
					throw e;

				} else {
					Thread.sleep(1000);
				}
			}
		}
	}

	// wait for a particular element to be visible and click it
	public static void clickElement(WebElement element) throws Exception{

		wait.until(ExpectedConditions.visibilityOf(element));		

			for (int i = 0; i <= 40; i++) {
				try {
					element.click();
					break;

				} catch (Exception e) {
					if (i == 40) {
						throw e;

					} else {
						Thread.sleep(1000);
					}
				}
			}
		}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
