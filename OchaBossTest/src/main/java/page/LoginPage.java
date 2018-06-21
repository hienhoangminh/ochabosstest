package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.awaitility.Awaitility.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class LoginPage {

	private static final Logger log = LogManager.getLogger(LoginPage.class.getName());

	private WebDriver driver;

	@FindBy(name = "mobile_number")
	private WebElement phoneNumber;

	@FindBy(css = "button[type='submit']")
	private WebElement btnSubmit;

	@FindBy(css = "input[name='otp_code']")
	private WebElement inputOtp;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputPhoneNumber(String phoneNumber) {
		try {
			this.phoneNumber.sendKeys(phoneNumber);
			log.info("Input " + phoneNumber + " into phone number field");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void clickSendOtpCode() {
		try {
			await("Wait for button Login to be enabled").atMost(20, TimeUnit.SECONDS).until(this.btnSubmit::getText,
					is("Gửi mã xác minh"));
			this.btnSubmit.click();
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public void inputOTPCode(String otpCode) {
		try {
			this.inputOtp.sendKeys(otpCode);
			log.info("Input " + otpCode + " into OTP field");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public HomePage clickLoginButton() {
		try {
			await("Wait for button Login to be enabled").atMost(20, TimeUnit.SECONDS).until(this.btnSubmit::getText,
					is("Đăng nhập"));
			this.btnSubmit.click();
			log.info("Clicking login...");
			return new HomePage(driver);
		} catch (Exception e) {
			log.error("Element not found ! Please check your locator again!");
			return null;
		}
	}

	public HomePage login(String phoneNumber, String otpCode) {
		inputPhoneNumber(phoneNumber);
		clickSendOtpCode();
		inputOTPCode(otpCode);
		return clickLoginButton();
	}

}
