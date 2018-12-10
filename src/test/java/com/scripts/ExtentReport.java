package com.scripts;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport extends TestBase {
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
		initialization(); 
	}

	@Test
	public void extentReportsDemo() throws IOException {

		if (driver.getTitle().equals("Google")) {
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		} else {
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			//String path=capture(driver);
		}
	}

	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}




