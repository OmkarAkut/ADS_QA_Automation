package webdriver.globalstatic;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.helperstatic.AssertStatic;

public class BeforeAfterRolesTesting extends AssertStatic {

    long timerStart;
    public WebDriver driver;

    @Before
    public void setup() throws Exception {
    	  System.out.println("kjsncafldjflrsejg");
        System.out.println("START FRAMEWORK");

        //sets browser driver
//        setsBrowserDriver(setBrowser());

        //creates driver object
        driver = setDriver(setBrowser());

        //start login timer
        timerStart = setupStartTimer();

        //navigates to url under test
        getURL(setupTestEnvironmentUrl(setTestEnvironment()));
        if(browser.toLowerCase().equals("ie") || browser.toLowerCase().equals("internetexplorer")){
            clickThroughIeCertificateScreens();
        }

    }

    @After
    public void teardown(){
    	 @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            //Log Out
//            waitForElementToBeVisible(driver.findElement(By.id("component-1022")));
//            WebElement logOut = driver.findElement(By.id("component-1022"));
//            logOut.click();
        	waitForElementToBeVisible(driver.findElement(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")));
        	//Shilpa updated 1.22.2025
//        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")));
            driver.findElement(By.xpath("//*[contains(@class, 'logout') and text() = 'Log Out']")).click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='username-inputEl']")));
            System.out.println("Logout successful");

        } catch (Throwable e) {
            System.out.println("WARNING: Logout was not successful");
        } finally {
            closesDriverObject();
        }
    }

    public void getURL(String url) {
        driver.get(url);
    }

}