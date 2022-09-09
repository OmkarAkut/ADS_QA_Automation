package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import webdriver.helpers.PageTestHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelpLinksDataMaintenancePageAds2526 extends PageTestHelper {

  String[] folders = {
          "Activities", "Benefit Plans",
          "Classifications", "Consumers", "Departments",
          "Employer Groups", "Encounters", "Entities",
          "Groups", "Miscellaneous", "Practitioners"
  };

   /** Automates ADS-2526.  Verifies that the help link on each page of the application links to the
   * user help guide and the appropriate help page in the guide displays correctly.
 * @throws Exception 
   */
  @BeforeClass
  public static void setupScript() throws Exception {
	  ExtentReport.reportCreate("HelpLinksDataMaintenancePageAds2526","webdriver.globalscripts.help", "HelpLinksDataMaintenancePageAds2526");
    System.out.println("TEST CLASS: " + HelpLinksDataMaintenancePageAds2526.class.getSimpleName());
    loginUser(Users.ApplicationAdministrator1);
    goToPage("Maintain Data");
  }

  @Test
  public void test01MaintainDataPageActivitiesSectionHelpLinks() throws Throwable {
    WebElement helpLink;
    int testCount = 0;
    boolean testFailed;
    int testFailures = 0;

    try {
     Thread.sleep(3000);
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//div[text()='General']/img[contains(@class, 'x-tree-icon-parent')]/preceding-sibling::img")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();

      for (String folder : folders) {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[text()='" + folder + "']/img[contains(@class, 'x-tree-expander')]")).click();
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        Thread.sleep(3000);
        WebElement list = driver.findElement(By.xpath("//div[text()='" + folder + "']/ancestor::table/tbody"));
        List<WebElement> elements = list.findElements(By.xpath("//tr[contains(@class, 'x-grid-tree-node-leaf')]/td/div"));
        System.out.println("Folder Under Test: " + folder + " - Size: " + elements.size());

        for (WebElement element : elements) {
          testCount++;
          element.click();
          waitForSpinnerToEnd();
          waitForAjaxExtJs();
          if (element.getText().equals("User Defined Fields And Relations")){
            driver.findElement(By.xpath("//div[contains(@id,'warningwindow')]/descendant::button/span[text()='OK']")).click();
          }
          String text = element.getText();
          String expectedPartialText = element.getText().substring(0, 3);
         
         
          
          System.out.println("TEST: "+ folder +" > "+ text);
          
          
                              
//NOTE Page Under Test: User Defined Fields And Relations - this one launches a security dialog, which needs to be addressed in the test
          try {
            if (text.equals("Charge Item Service Classification Schemes")) {
              helpLink = driver.findElement(By.xpath("//span[text()='" + text + "']/parent::div[contains(@id,'chargeitemmasterlist')]/following-sibling::div/a[text()='Help']"));
              
            } else if (text.equals("Charge Item Service Classification Schemes")) {
            	
              helpLink = driver.findElement(By.xpath("//span[text()='Charge Item Service Classification Schemes']/../following-sibling::div[contains(@class,'x-box-item')]/a[text()='Help']"));
              //firstPass = false;
            } else {
              helpLink = driver.findElement(By.xpath("//*[text()='" + text + "']/following-sibling::div[contains(@class,'x-box-item')]/div/a[text()='Help']"));
            }
            //*[text()='Charge Masters']/following-sibling::div[contains(@class,'x-box-item')]/div/a[text()='Help']
            testFailed = testHelpLinkContains(helpLink, expectedPartialText, printout);
            if (testFailed == true) {
              testFailures++;
              testFailed = false;
            }
          } catch (Throwable e) {
        	 
            continue;
           
          }
        }
        driver.findElement(By.xpath("//div[text()='" + folder + "']/img[contains(@class, 'x-tree-expander')]")).click(); //closes expanded folder
        waitForAjaxExtJs();
      }
      System.out.println("Total tests run: " + testCount);
  	
 

      if (testFailures > 0) {
        fail("There are test failures");
      }
      
      
      ExtentReport.logPass("PASS", "test01MaintainDataPageActivitiesSectionHelpLinks");
    } catch (Exception|AssertionError e) {
    	
    	ExtentReport.logFail("FAIL","test01MaintainDataPageActivitiesSectionHelpLinks", driver,e);
    	fail(e.getMessage());
      //e.printStackTrace();
     // fail("Help Link Page Test Failed");
    }
  }
  
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

  
  
  
  
  
  
  
  
  
  
}
