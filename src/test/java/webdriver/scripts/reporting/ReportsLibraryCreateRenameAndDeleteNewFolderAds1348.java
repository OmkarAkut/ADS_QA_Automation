package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginStatic;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryCreateRenameAndDeleteNewFolderAds1348 extends LoginStatic {

  //static GeneralElementsMap generalMap;
  static String folderName = "New Folderdelete" + javaGetRandomNumber(1000, printout);

  /** Regression Test for Test Ticket ADS-1348. **/
  @BeforeClass
  public static void setupScript() throws InterruptedException,Throwable {
    //generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
		ExtentReport.reportCreate("ReportsLibraryCreateRenameAndDeleteNewFolderAds1348", "webdriver.scripts.reporting", "ReportsLibraryCreateRenameAndDeleteNewFolderAds1348");

    try {
		System.out.println("Test Class: " + ReportsLibraryCreateRenameAndDeleteNewFolderAds1348.class.getSimpleName());
		evolveLoginStaticUser(Users.AutomationTester1);
		goToPage("Report Library");
		waitForAjaxExtJs();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
		ExtentReport.logPass("PASS", "setupScript");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);

		      fail(e.getMessage());
		    }
  }

  @AfterClass
  public static void teardownScript() {
    driver.switchTo().parentFrame();
    ExtentReport.report.flush();
  }


@Test
  public void test01aCreateNewReportsFolderAndConfirmRenameAndDeleteButtonsAreEnabled() throws Exception,Throwable {
		driverDelay(5000);;//Shilpa 16.09.2022 
     try {
		driver.findElement(By.xpath("//div[@role='treeitem']/div[contains(text(),'Folders')]")).click();
		driver.findElement(By.xpath("//div/button[text()='New Folder']")).click();
		driver.findElement(By.xpath("//td[@style='vertical-align: middle;']//div[@class='gwt-TreeItem']//div[@class='gwtQuery-droppable']//img")).click();
		Boolean isEnabled = driver.findElement(By.xpath("//div/button[text()='Rename Folder']")).isEnabled();
		Assert.assertTrue("Rename button is enabled", isEnabled);
		ExtentReport.logPass("PASS", "test01aCreateNewReportsFolderAndConfirmRenameAndDeleteButtonsAreEnabled");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01aCreateNewReportsFolderAndConfirmRenameAndDeleteButtonsAreEnabled", driver, e);

	      fail(e.getMessage());
	    }
    //assertElementIsEnabled(driver.findElement(By.xpath("//div/button[text()='Rename Folder']")), printout);
    //assertElementIsEnabled(driver.findElement(By.xpath("//div/button[text()='Delete Folder']")), printout);
  }

  @Test
  public void test02RenameNewReportsFolder() throws Throwable {
    try {
    	driverDelay(5000);;//venkat 03.10.2022 
      driver.findElement(By.xpath("//div[contains(@class,'droppable')][contains(text(),'New Folder')]")).click();
      driverPause();//shilpa 16.09.2022
      driver.findElement(By.xpath("//div/button[text()='Rename Folder']")).click();
      driverPause();//shilpa 16.09.2022
      driver.findElement(
              By.xpath("//div[text()='Rename Folder']/ancestor::tr/following-sibling::tr/descendant::input"))
              .sendKeys(Keys.chord(Keys.CONTROL, "a"));//shilpa 16.09.2022 select existing text and sendkeys
      ;
      driver.findElement(
              By.xpath("//div[text()='Rename Folder']/ancestor::tr/following-sibling::tr/descendant::input"))
              .sendKeys(folderName);
      ;
     
      driver.findElement(
              By.xpath("//div[text()='Rename Folder']/ancestor::tr/following-sibling::tr/descendant::td/button[text()='OK']"))
              .click()
      ;
    driverDelay();//driverwait Shilpa 16.09.2022
      assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@class,'droppable')][contains(text(),'"+folderName+"')]")), printout);
      ExtentReport.logPass("PASS", "test02RenameNewReportsFolder");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02RenameNewReportsFolder", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test03CancelDeletingNewReportsFolder() throws Throwable {
    try {
    	driverDelay(5000);;//venkat 03.10.2022 
      driver.findElement(By.xpath("//div/button[text()='Delete Folder']")).click();
      driverDelay(2000);
      driver.findElement(By.xpath("//div[text()='Delete Folder']/ancestor::tr/following-sibling::tr/descendant::td/button[text()='Cancel']"))
              .click();
      driverDelay(2000);
      assertElementIsDisplayed(driver.findElement(By.xpath(("//div[contains(@class,'droppable')][contains(text(),'"+folderName+"')]"))),
              printout
      );
      ExtentReport.logPass("PASS", "test03CancelDeletingNewReportsFolder");

     	} catch(Exception|AssertionError e) {
     		ExtentReport.logFail("FAIL", "test03CancelDeletingNewReportsFolder", driver, e);

     	      fail(e.getMessage());
     	    }
  }

  @Ignore
  @Test
  public void test04DeleteNewReportsFolder() throws Throwable {
    try {
      driver.findElement(By.xpath("//div/button[text()='Delete Folder']")).click();
      assertElementIsDisplayed(
              driver.findElement(By.xpath("//div[text()='Delete Folder']/ancestor::tr/following-sibling::tr/descendant::div[text()='"+folderName+" will be deleted. Do you want to continue?']")),
              printout);
      driver.findElement(By.xpath("//div[text()='Delete Folder']/ancestor::tr/following-sibling::tr/descendant::td/button[text()='Delete']")).click();
      assertElementIsNotDisplayed(driver.findElement(By.xpath("//div[contains(@class, 'droppable')][contains(text(),'"+folderName+"')]")));
      ExtentReport.logPass("PASS", "test04DeleteNewReportsFolder");

 	} catch(Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test04DeleteNewReportsFolder", driver, e);

 	      fail(e.getMessage());
 	    }
  }
}
