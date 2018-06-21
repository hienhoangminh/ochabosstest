package test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginToOchaBoss {

	public static void main(String[] args) {
		WebDriver driver;
		// System.setProperty("webdriver.chrome.driver", "/Users/hoanghien/drivers/chromedriver");
		
		// driver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "/Users/hoanghien/Desktop/automation-demo/OchaBossTest/drivers/geckodriver");
		driver = new FirefoxDriver();

		driver.get("https://boss.ocha.vn:83/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement phoneNumber = driver.findElement(By.name("mobile_number"));
		phoneNumber.sendKeys("01655942566");
		
		WebElement sendOtpCode = driver.findElement(By.cssSelector("button[type='submit']"));
		sendOtpCode.click();
		
		WebElement otpCode = new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(By.name("otp_code")));
		otpCode.sendKeys("123456");
		
		WebElement buttonLogin = new WebDriverWait(driver, 15).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
		
	    buttonLogin.click();

	    Actions action = new Actions(driver);
	    WebElement userInfo = new WebDriverWait(driver, 15).
	    		until(ExpectedConditions.visibilityOfElementLocated(By.
	    				cssSelector(".control-group.user-info")));
	    action.moveToElement(userInfo).click().perform();
	    
	    try {
			Thread.sleep(9000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    WebElement userName = driver.findElement(By.cssSelector("#user-info-dropdown-id .owner-name"));
	    		
	    //JavascriptExecutor js = (JavascriptExecutor) driver;
		//String text = (String) js.executeScript("return document.querySelector('#user-info-dropdown-id .owner-name').textContent;");
		System.out.println(userName.getText());
	    
//	    WebElement logoutButton = new WebDriverWait(driver, 15).
//	    		until(ExpectedConditions.visibilityOfElementLocated(By.
//	    				xpath("//button[@data-target='#logout-modal-id']")));
//	    logoutButton.click();
//	    
//	    WebElement confirmLogoutButton = new WebDriverWait(driver, 15).
//	    		until(ExpectedConditions.visibilityOfElementLocated(By.
//	    				xpath("//button[@data-dismiss='modal'][contains(.,'Đăng xuất')]")));
//	    confirmLogoutButton.click();
//	    
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
		driver.quit();
	}
	
}
