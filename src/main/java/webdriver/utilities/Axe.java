package webdriver.utilities;

import com.deque.axe.AXE;

import java.net.URL;

import static org.junit.Assert.fail;
import static webdriver.globalstatic.SetupStatic.releaseVersion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;

public class Axe {

  private static final Logger logger = LogManager.getLogger();

  //WCAG Compliance Standards - select one below or create a new configuration
  //static final String wcagStandard = "'wcag2a', 'wcag2aa', 'wcag2aaa', 'section508', 'best-practice'";
  //static final String wcagStandard = "'wcag2a', 'wcag2aa', 'best-practice'";
//  static final String wcagStandard = "'wcag2a', 'wcag2aa'";//Shilpa commented on request 11.2 5.15.2024
  	String wcagStandard ="'wcag2a'";
  @Rule
  private static final URL scriptUrl = Axe.class.getResource("/axe.min.js");

  public void runAxeTestOnPage(WebDriver driver, String methodName, boolean createReport) {  //creates JSON results file - no log4j files created
    try {
      JSONObject responseJson = new AXE.Builder(driver, scriptUrl)
              //.options("{runOnly: {type: 'tag', values: ['wcag21a', 'wcag21aa']}}") //this configuration works
              //.options("{absolutePaths: true}")
              .options("{runOnly: [" + wcagStandard + "]}")
              .skipFrames()
              .analyze()
      ;
      JSONArray violations = responseJson.getJSONArray("violations");  //return violations only
      logger.info("Test Page: " + methodName);
      if (violations.length() == 0) {
        logger.info("No Violations Found");
      } else {
        logger.info(AXE.report(violations));
        if (createReport) {
          AXE.writeResults(methodName, violations);
        }
      }
    } catch (Exception e) {
    	fail("Failed in runAxeTestOnPage");
    	
    }
  }

  public void runAxeTestOfPageJsonReport(WebDriver driver, String methodName) {  //creates JSON results file - no log4j files created
    try {
      JSONObject responseJson = new AXE.Builder(driver, scriptUrl).options("{runOnly: ["+wcagStandard+"]}") //sets wcag standard.skipFrames()
              .analyze()
      ;
      JSONArray violations = responseJson.getJSONArray("violations");  //sets axe to return only violations
      if (violations.length() == 0) {
        //assertTrue("No violations found", true);
      } else {
        AXE.writeResults(methodName, violations);
        //assertTrue(AXE.report(violations), false);
      }
    } catch (Exception e) {
    	 fail("Failed in runAxeTestOfPageJsonReport");
    }
  }

  /** Method to run Axe core on the page under test - this one requires passing a logger object. */
  public void runAxeTestOfPageLog4jReport(WebDriver driver, Logger logger, String methodName) {
    logger.info("ADS Release Version: " + releaseVersion);
    logger.info("WCAG Compliance Standard: " + wcagStandard);
    try {
      JSONObject responseJson = new AXE.Builder(driver, scriptUrl)
              .options("{runOnly: [" + wcagStandard + "]}") //sets wcag standard
              .skipFrames()
              .analyze();
      JSONArray violations = responseJson.getJSONArray("violations");  //sets axe to return only violations - i.e., a violations filter
      if (violations.length() == 0) {
        logger.info("Test Page: " + methodName);
        logger.info("No Violations Found");
      } else {
        logger.info("Test Page: " + methodName);
        logger.info(AXE.report(violations));  //prints filtered results to console (i.e., violations)
      }
    } catch (Exception e) {
    	 fail("Failed in runAxeTestOfPageLog4jReport");
    }
  }

  /** Method to run Axe core on the page under test - this one does not require passing a logger object. */
  public void runAxeAccessibilityTestOfPage(WebDriver driver, String methodName) {
    try {
    	
      JSONObject responseJson = new AXE.Builder(driver, scriptUrl).options("{runOnly: [" + wcagStandard + "]}") //sets wcag standard
              //.skipFrames()  //if active, will not look in iframes; if commented out, will look in iframes
              .analyze();
      JSONArray violations = responseJson.getJSONArray("violations");  //sets axe to return only violations - i.e., a violations filter
      if (violations.length() == 0) {
        logger.info("Test Page: " + methodName);
        logger.info("No Violations Found");
      } else {
        logger.info("Test Page: " + methodName);
        logger.info(AXE.report(violations));  //prints filtered results to console (i.e., violations)
      }
    } catch (Exception e) {
    	
      fail("Failed in runAxeAccessibilityTestOfPage");
    }
  }
}
