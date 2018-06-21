package test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Report {

	public static void main(String[] args) {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "/Users/hoanghien/drivers/chromedriver");
		
		driver = new ChromeDriver();
		
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

	    try {
			Thread.sleep(9000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    System.out.println("Size of array: " + driver.findElements(By.cssSelector("#branch_select_id .dropdown-content div")).size());
	    
	    WebElement firstBrand = driver.findElement(By.
	    				cssSelector("#branch_select_id .dropdown-content div:nth-of-type(2)"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", firstBrand);
        
	    try {
			Thread.sleep(9000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	     
		driver.quit();
	}
	
}