package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Utility {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String filepath;
	public String sheetname;
	public static ExtentReports extents;
	public static ExtentTest test;

	public static void LunchURL(String url, String browser) {

		// cross browser Test
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();

		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public static void closingBrowser() {

		driver.close();
	}

	public static void readFromPropFile(String filepath) throws IOException {

		FileReader file = new FileReader(filepath);
		prop = new Properties();
		prop.load(file);
	}
	public static String[][] readExcel(String sheetName) throws IOException {
        String filePath = "C:\\Users\\siva\\eclipse-workspace\\MainProject\\src\\test\\resources\\HotelBooking.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook book = new XSSFWorkbook(fis);
        XSSFSheet sheet = book.getSheet(sheetName);

        int rownum = sheet.getLastRowNum();
        int colnum = sheet.getRow(0).getLastCellNum();

        String[][] data = new String[rownum][colnum];

        DecimalFormat df = new DecimalFormat("#"); // To convert numeric values properly

        for (int i = 1; i <= rownum; i++) {  // Start from 1 to skip headers
            XSSFRow row = sheet.getRow(i);
            if (row == null) continue; // Skip empty rows

            for (int j = 0; j < colnum; j++) {
                XSSFCell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);

                switch (cell.getCellType()) {
                    case STRING:
                        data[i - 1][j] = cell.getStringCellValue().trim();
                        break;
                    case NUMERIC:
                        data[i - 1][j] = df.format(cell.getNumericCellValue()); // Convert to String
                        break;
                    case BOOLEAN:
                        data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case BLANK:
                        data[i - 1][j] = "";  // Assign empty string for blank cells
                        break;
                    default:
                        data[i - 1][j] = cell.toString(); // Fallback conversion
                        break;
                }

                // Debugging output
                System.out.println("Row " + i + ", Column " + j + ": " + data[i - 1][j]);
            }
        }

        book.close();
        fis.close(); // Ensure file is properly closed
        return data;
    }


	public static String screenshot(String name) throws IOException {

		String path = "C:\\Users\\siva\\eclipse-workspace\\MainProject\\screenshots\\" + name + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}

}



