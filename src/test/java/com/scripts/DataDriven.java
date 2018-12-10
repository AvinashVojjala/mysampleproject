package com.scripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.base.TestBase;
import com.pageobjects.GooglePO;

public class DataDriven extends TestBase {

	@BeforeTest
	public void TestSetup() {
		// selecting the browser type and loading the browser
		initialization();
	}

	@Test
	public void readData() throws Exception {

		GooglePO google = new GooglePO();
		google.googleSearch(readDataFromExcelFile(0, 1, 0));

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\com\\properties\\testdata.xlsx");
		FileOutputStream fos = null;
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Search Data");
		XSSFRow row = null;
		XSSFCell cell = null;
		XSSFFont font = workbook.createFont();
		XSSFCellStyle style = workbook.createCellStyle();

		row = sheet.getRow(1);

		cell = row.createCell(1);

		font.setFontName("Comic Sans MS");
		font.setFontHeight(12.0);
		font.setBold(true);
		font.setColor(HSSFColor.WHITE.index);

		style.setFont(font);
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		cell.setCellValue("PASS");

		fos = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\com\\properties\\testdata.xlsx");
		workbook.write(fos);
		fos.close();

	}

	@AfterTest
	public void exit() throws IOException {

		driver.quit();
	}

}