package drivermanager;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public class FirefoxDriverManager extends DriverManager {

	private GeckoDriverService gkService;
	
	@Override
	protected void startService() {
	    System.setProperty("webdriver.gecko.driver","drivers//geckodriver");

		 if (null == gkService) {
	            try {
	            	gkService = new GeckoDriverService.Builder()
	                    .usingDriverExecutable(new File("drivers//geckodriver"))
	                    .usingAnyFreePort()
	                    .build();
	            	gkService.start();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	}

	@Override
	protected void stopService() {
		 if (null != gkService && gkService.isRunning())
			 gkService.stop();
	}

	@Override
	protected void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("test-type");
        driver = new FirefoxDriver(options);
	}

}
