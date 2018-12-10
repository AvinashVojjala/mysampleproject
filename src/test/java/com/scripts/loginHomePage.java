package com.scripts;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.base.TestBase;
import com.pageobjects.HomePagePO;

public class loginHomePage extends TestBase {
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
		HomePagePO po = new HomePagePO();
		po.signIn();
	}

	/*
	 * @AfterSuite public void tearDownSuite() { try { // close suite
	 * extentReport.flush(); //extentReport.close(); } catch (Exception e) {
	 * //logger.warn("Error in closing extent reporter"); e.printStackTrace(); } }
	 */
	@AfterMethod
	public void tearDown() throws IOException {
		capture(driver);		
		driver.quit();
		/*
		 * try { // close suite extentReport.flush(); //extentReport.close(); } catch
		 * (Exception e) { //logger.warn("Error in closing extent reporter");
		 * e.printStackTrace(); }
		 */
	}

}
