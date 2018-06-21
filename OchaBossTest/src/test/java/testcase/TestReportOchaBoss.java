package testcase;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
// import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.testautomationguru.ocular.Ocular;

import drivermanager.DriverManager;
import drivermanager.DriverManagerFactory;
import drivermanager.DriverType;
import extentreports.ExtentManager;
import page.HomePage;
import page.LoginPage;
import utils.Utils;

public class TestReportOchaBoss {

	DriverManager driverManager;
	WebDriver driver;
	String baseUrl;

	ExtentReports report;
	ExtentTest test2;

	HomePage hp;
	LoginPage lp;
	
	@BeforeSuite
	public void OcularConfiguration() {
		Ocular.config()
		      .snapshotPath(Paths.get("snap"))
		      .resultPath(Paths.get("result"));
	}
	
	@BeforeTest
	public void beforeTest() {
		driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = driverManager.getDriver();

		report = ExtentManager.GetExtent();
		
		test2 = report.createTest("OchaBoss check report page", "Verify daily sales chart");

		baseUrl = "https://boss.ocha.vn:83/";


		lp = new LoginPage(driver);

		test2.log(Status.INFO, "Browser started...");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1920, 1080));
		test2.log(Status.INFO, "Browser zoomed in to 1920*1080");
		driver.get(baseUrl);
		test2.log(Status.INFO, "Launching URL...");
	}

	@Test
	public void checkReport() {

		hp = lp.login("01655942566", "123456");
		test2.log(Status.INFO, "Logged in...");

		
		hp.clickOnUserInfo();
		test2.log(Status.INFO, "Click on user info avatar...");

		hp.assertUserName("Hoàng Minh Hiển");
		test2.log(Status.INFO, "Verify username done.");

		hp.selectFirstBrand();
		test2.log(Status.INFO, "Select the first brand...");

		test2.log(Status.INFO, "Select the first brand...");
		Assert.assertTrue(hp.verifyDailySales());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
            String screenShot = Utils.takeScreenshot(driver,result.getMethod().getMethodName());
	        try {
				test2.log(Status.FAIL,"Screenshot from : " + screenShot).addScreenCaptureFromPath(screenShot);
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