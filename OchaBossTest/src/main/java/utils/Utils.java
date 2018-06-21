package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utils {

	public static String takeScreenshot(WebDriver driver,String fileName) {
		String extentReportImage = fileName + "_" + System.currentTimeMillis() + ".png";
		String path = System.getProperty("user.dir") + "/screenshot/";
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile method
			FileUtils.copyFile(src, new File(path + extentReportImage));
			return path + extentReportImage;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "";
		}

	}
}
