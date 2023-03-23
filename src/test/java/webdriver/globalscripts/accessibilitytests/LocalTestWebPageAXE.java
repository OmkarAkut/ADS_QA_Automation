package webdriver.globalscripts.accessibilitytests;

import com.deque.axe.AXE;

import ExtentReport.ExtentReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webdriver.utilities.Axe;

import static org.junit.Assert.fail;

import java.net.URL;

public class LocalTestWebPageAXE {

    public WebDriver driver;
    private Axe ax = new Axe();
    private static final Logger logger = LogManager.getLogger(); //Log4j2Config

    @Rule
    public TestName name = new TestName();
    private final URL scriptUrl = Axe.class.getResource("/axe.min.js");

    @Before
    public void setupScript() throws InterruptedException,Throwable {
    	ExtentReport.reportCreate("LocalAccessibilityScript", "webdriver.globalscripts.accessibilitytests", "LocalAccessibilityScript");
        try {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(System.getProperty("user.dir")+"//TestFiles//Test Web Page.html");
			Thread.sleep(500);
			ExtentReport.logPass("PASS", "setupScript");
    	} catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
    	fail(e.getMessage());
    	}
    }

    @After
    public void teardown() {
    	ExtentReport.report.flush();
        driver.quit();
        
    }

    @Test
    public void testLocalAccessibilityScript() throws InterruptedException,Throwable {
        try {
			runAxe(driver);
			ExtentReport.logPass("PASS", "testLocalAccessibilityScript");
    	} catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "testLocalAccessibilityScript", driver, e);
    	fail(e.getMessage());
    	}
    }

    public void runAxe(WebDriver driver) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .options("{ runOnly: ['wcag2a'] }")
                .analyze();
        JSONArray violations = responseJSON.getJSONArray("violations");  //sets axe to return only violations - i.e., a violations filter
        if (violations.length() == 0) {
            System.out.println("No Violations Found");
        } else {
            System.out.println(AXE.report(violations));  //prints filtered results to console (i.e., wcag2a violations)
        }
    }
}
