package webdriver.globalstatic;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import webdriver.helperstatic.AssertStatic;

public class BeforeAfterRolesTesting extends AssertStatic {

    long timerStart;
    public WebDriver driver;

    @Before
    public void setup() throws Exception {
    	  System.out.println("kjsncafldjflrsejg");
        System.out.println("START FRAMEWORK");

        //sets browser driver
        setsBrowserDriver(setBrowser());

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
        try {
            //Log Out
            waitForElementToBeVisible(driver.findElement(By.id("component-1022")));
            WebElement logOut = driver.findElement(By.id("component-1022"));
            logOut.click();
            Thread.sleep(1000);
            waitUntilElementIsVisible(driver.findElement(By.id("username-inputEl")));
            System.out.println("You have logged out successfully.");

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