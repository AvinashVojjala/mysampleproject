package com.reports;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;
import com.reports.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	public static final String projectPath = System.getProperty("user.dir") + File.separator;

	private static ExtentReports extentReporter;
	public static final String propertyFilePath = projectPath + "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "properties" + File.separator;
	
	 //extentConfig path
    public static final String extentReport= projectPath + "target"+File.separator+"ExtentReport" ;
    //extentConfig path
    public static final String extentConfig= projectPath + "src" + File.separator + "main" + File.separator+ "resources" + File.separator + "properties" + File.separator+"extentConfig.xml";


	public synchronized static ExtentReports getReporter() {
		if (extentReporter == null) {
			// Set HTML reporting file location
			File file = new File(extentReport);
			if (!file.exists())
				file.mkdir();
			extentReporter = new ExtentReports(file.getAbsolutePath() + File.separator + "ExtentReportResult.html",
					false);
			
			//copy CareLogo into target to put in image
			String src = propertyFilePath+"carelogo.jpg";
			String dest = extentReport+ File.separator + "carelogo.jpg";
			try {
				Files.copy(new File(src), new File(dest));
			} catch (IOException e) {
				System.out.println("Couldn't copied the file");
			}
		}
		return extentReporter;
	}

	/**
	 * Create extent reporter HTML and adding system information
	 * 
	 */
	public static ExtentReports initExtentReporter() {
		extentReporter = ExtentManager.getReporter();
		extentReporter.addSystemInfo("Stack Name", "WebTest");
		extentReporter.loadConfig(new File(extentConfig));
		return extentReporter;
	}

}