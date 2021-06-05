package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.relevantcodes.extentreports.ExtentReports;

public class TestBase {

	public static WebDriver driver = null;
	public static Properties prop;

// protected Driver browser;
//	protected DriverHelper browser;

	protected static ExtentReports extentReport;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/com/properties" + "/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//selecting the browser type and loading the browser
	public static void initialization() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\avinash.vojjala\\Downloads\\softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "D:/Automation/myproject/tools/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		/*
		 * e_driver = new EventFiringWebDriver(driver); // Now create object of
		 * EventListerHandler to register it with EventFiringWebDriver eventListener =
		 * new WebEventListener(); e_driver.register(eventListener); driver = e_driver;
		 */

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		/*
		 * driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,
		 * TimeUnit.SECONDS);
		 * driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,
		 * TimeUnit.SECONDS);
		 */
		driver.get(prop.getProperty("githuburl"));
	}

	/*
	 * public void log(LogStatus logStatus, String message, boolean takeScreenShot)
	 * { if (logStatus == LogStatus.INFO || logStatus == LogStatus.PASS) {
	 * logger.info(message); if (!takeScreenShot) extentTest.log(logStatus,
	 * message); else extentTest.log(logStatus, message,
	 * extentTest.addScreenCapture(browser.takeScreenShotForExtent())); } else if
	 * (logStatus == LogStatus.FAIL || logStatus == LogStatus.ERROR || logStatus ==
	 * LogStatus.WARNING) { logger.warn(message); if (!takeScreenShot)
	 * extentTest.log(logStatus, message); else extentTest.log(logStatus, message,
	 * extentTest.addScreenCapture(browser.takeScreenShotForExtent())); } }
	 */

	public String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../ErrImages/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		// FileHandler.copyFile();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	public static String readDataFromExcelFile(int sheetno, int rownum, int colnum) throws Exception {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\properties\\testdata.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		XSSFWorkbook hssfWorkbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = hssfWorkbook.getSheetAt(sheetno);
		XSSFCell cell = sheet.getRow(rownum).getCell(colnum);
		DataFormatter df = new DataFormatter();
		String data = df.formatCellValue(cell);
		return data;
	}

	public static void readDataFromExcelFileAllRecords() throws IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\properties\\Book1.xlsx");
		FileInputStream FileInput = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(FileInput);
		Sheet sh = wb.getSheet("Logindetails");
		int rowCount = sh.getLastRowNum();
		// System.out.println(rowCount);
		int columnCount = 0;

		Iterator<Row> iterator = sh.iterator();
		Row nextRow = iterator.next();
		columnCount = nextRow.getLastCellNum();
		int uidColNum = 0;
		int pwdColNum = 0;

		for (int j = 0; j < columnCount; j++) {
			String uidText = sh.getRow(0).getCell(j).toString();
			if (uidText.equals("uid")) {
				uidColNum = j;
				break;
			}
		}
		for (int jj = 0; jj < columnCount; jj++) {
			String pwdText = sh.getRow(0).getCell(jj).toString();
			if (pwdText.equals("pwd")) {
				pwdColNum = jj;
				break;
			}
		}
		for (int i = 1; i <= rowCount; i++) {
			driver.findElement(By.id("email")).sendKeys(sh.getRow(i).getCell(uidColNum).getStringCellValue());
			driver.findElement(By.name("password")).sendKeys(sh.getRow(i).getCell(pwdColNum).getStringCellValue());
		}

	}
}
