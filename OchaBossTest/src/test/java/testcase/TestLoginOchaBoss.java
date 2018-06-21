package testcase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

// import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import drivermanager.DriverManager;
import drivermanager.DriverManagerFactory;
import drivermanager.DriverType;
import extentreports.ExtentManager;
import page.HomePage;
import page.LoginPage;
import utils.Utils;

public class TestLoginOchaBoss {

	DriverManager driverManager;
	WebDriver driver;
	String baseUrl;

	ExtentReports report;
	ExtentTest test1;

	HomePage hp;
	LoginPage lp;
	
	@BeforeTest
	public void beforeTest() {
		//driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
		driverManager = DriverManagerFactory.getManager(DriverType.FIREFOX);

	}

	@BeforeMethod
	public void beforeMethod() {
		driver = driverManager.getDriver();

		report = ExtentManager.GetExtent();
		
		test1 = report.createTest("OchaBoss Login", "Verify Login sucessfully");

		baseUrl = "https://boss.ocha.vn:83/";


		lp = new LoginPage(driver);

		test1.log(Status.INFO, "Browser started...");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		test1.log(Status.INFO, "Browser zoomed in to 1920*1080");
		driver.get(baseUrl);
		test1.log(Status.INFO, "Launching URL...");
	}

	@Test
	public void loginOchaBoss() {

		hp = lp.login("01655942566", "123456");
		test1.log(Status.INFO, "Logged in...");

		
		hp.clickOnUserInfo();
		test1.log(Status.INFO, "Click on user info avatar...");

		hp.assertUserName("Hoàng Minh Hiển");
		test1.log(Status.INFO, "Verify user name...");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
            String screenShot = Utils.takeScreenshot(driver,result.getMethod().getMethodName());
	        try {
				test1.log(Status.FAIL,"Screenshot from : " + screenShot).addScreenCaptureFromPath(screenShot);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		report.flush();
	}
	
	@AfterTest
	public void afterTest() {
		driverManager.quitDriver();      
	}

}