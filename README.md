# Ochabosstest

This project is aimed to demo how apply Selenium WebDriver to automate web testing. Version used is 3.8.0 with 
chrome driver 2.40 and firefox driver 0.16

This project is structure like below:

![structure](http://i66.tinypic.com/k1ueqa.jpg)

 - main:
    -- drivermanager : To manipulate which browser is instantiated based on pre-defined browser name.
    -- extentreports : To instantiate the ExtentReports
    -- page : all the screen that are navigated on application
    -- utils : some useful function
 - test :
    -- testcase : where we declare our automated test.
    
 All dependencies are managed by Maven.
 
 We have sub-folders:
 -- logs : to store log of each run
 -- drivers : to store browser drivers
 -- screenshot : to store the screenshot when the test fails
 -- snap : to store the expected image.
 -- result : to store actual image.
 
 We can run the test locally when right-clicking on testng.xml file or run = command line : mvn test.
    
