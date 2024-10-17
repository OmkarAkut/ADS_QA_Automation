package webdriver.scripts.datamaintenance.utilities;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UtilitiesHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilitiesReportCheck
        extends UtilitiesHelper {

  static DataMaintenanceMap dm;
  static StatusMap status;
  final String reportName = "Encounters With No Charges Report";
  String[] codes = {
      "2S1  Office", "2S3  Hospital"
  };
  String startDate = "01/01/2019";
  String endDate = "06/30/2019";
  String currentDate = javaGetCurrentDate("MM/dd/yyyy");
  static final String expectedUtilityName = "Encounters With No Charges Report";
  static final String expectedUtilityStatus = "Completed";
  static final String expectedUtilityLogStatus = "Completed";
  static final String expectedUtilityDownload = "Download";
  String durationPatternMatch = "^\\d\\d:\\d\\d:\\d\\d$";
  
  /*
  Omkar 7/8/2023 : xpath changes for 11.2
  static String utilityStatusUtilityNameXpath = "//table/tbody/tr[2]/td[5]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusStatusXpath = "//table/tbody/tr[2]/td[6]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusLogStatusXpath = "//table/tbody/tr[2]/td[7]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusDownloadXpath = "//table/tbody/tr[2]/td[8]/div[contains(@class, 'grid-cell')]/a";
  static String utilityStatusStartTimeXpath = "//table/tbody/tr[2]/td[9]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusEndTimeXpath = "//table/tbody/tr[2]/td[10]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusDurationXpath = "//table/tbody/tr[2]/td[11]/div[contains(@class, 'grid-cell')]";
	*/
  static String utilityStatusUtilityNameXpath = "(//table/tbody/tr[1]/td)[2]/div";
  static String utilityStatusStatusXpath = "(//table/tbody/tr[1]/td)[4]/div";//Shilpa updated xpath 10.17.2024
  static String utilityStatusLogStatusXpath = "(//table/tbody/tr[1]/td)[4]/div";
  static String utilityStatusDownloadXpath = "(//table/tbody/tr[1]/td)[5]/div/a";
  static String utilityStatusStartTimeXpath = "(//table/tbody/tr[1]/td)[6]/div";
  static String utilityStatusEndTimeXpath = "(//table/tbody/tr[1]/td)[7]/div";
  static String utilityStatusDurationXpath = "(//table/tbody/tr[1]/td)[8]/div";
  
  /** Test script that verifies Encounters With No Charges Report. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UtilitiesReportCheck","webdriver.scripts.datamaintenance.utilities", "UtilitiesReportCheck");

    try {
		status = BuildMap.getInstance(driver, StatusMap.class);
		dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println(
		        "Test Class: "
		        + UtilitiesReportCheck.class.getSimpleName());
//		Login.loginUser("ApplicationAdministrator1");
		//Shilpa: update user 10.10.2024
				loginUser(Users.AutomationTesterAdmin);
		goToPage("Utilities");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01VerifyReportDefaultConfiguration() throws Throwable {
	  //Shilpa 11.08.2022 updated xpath
//    waitForPresenceOfElement("//div[contains(@id, 'datamaintenanceutilities')]" +
//            "/descendant::button/*[text()='Encounters With No Charges Report']"
//    );
	  try {
//		waitForPresenceOfElement("//div[contains(@class,'itemWrap x-item-selected')][text()='Encounters With No Charges Report']"
//		);
	  //Shilpa updated xpath 10.10.2024
	  waitForPresenceOfElement("//div[contains(@class,'itemWrap')][text()='Encounters With No Charges Report']"
    ); 
  
		doClick(dm.getUtilitiesPageEncountersWithNoChargesReport());
//    waitForPresenceOfElement("//div[contains(@id, 'datamaintenanceutilities')]" +
//            "/descendant::button/*[text()='Encounters With No Charges Report']"
//    );
		
//		Omkar 7/8/2023 : xpath changed for 11.2
//		waitForPresenceOfElement("//span[contains(@id,'header_hd')]//following::input[contains(@id,'datefield')][1]"
//		);
		waitForPresenceOfElement("(//input[contains(@id,'datefield')])[1]"
				);
		String startDate = driver.findElement(By.name("startDate1")).getAttribute("value");
			assertEquals(currentDate, startDate);
		
		String endDate = driver.findElement(By.name("endDate1")).getAttribute("value");
	
			assertEquals(currentDate, endDate);
			assertButtonIsActive("Select");
			assertButtonIsInactive("Run");

			ExtentReport.logPass("PASS", "CurrentDate and StartDate Matches : PASS");

			ExtentReport.logPass("PASS", "CurrentDate and EndDate Matches : PASS");
			ExtentReport.logPass("PASS", "Select Button is Active : PASS");
			ExtentReport.logPass("PASS", "Run Button is Active : PASS");

		ExtentReport.logPass("PASS", "test01VerifyReportDefaultConfiguration : PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01VerifyReportDefaultConfiguration",driver,e);
		fail(e.getMessage());
	}

  }

  @Test
  public void test02RunReportAndVerifyCompletedStatus() throws InterruptedException,Throwable {
    try {
		waitForAjaxExtJs();
		runUtilityReport(startDate, endDate, codes);
		ExtentReport.logPass("PASS", "test02RunReportAndVerifyCompletedStatus : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02RunReportAndVerifyCompletedStatus",driver,e);
		fail(e.getMessage());
	}

  }

  @Test
  public void test04aVerifyUtilityStatusPageTableValueForUtilityNameColumn() throws InterruptedException,Throwable {
    try {
		waitForAjaxExtJs();
		String utilityNameText = getWebElement(utilityStatusUtilityNameXpath).getText();
			assertEquals(expectedUtilityName, utilityNameText);
			ExtentReport.logPass("PASS", "test04aVerifyUtilityStatusPageTableValueForUtilityNameColumn : PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04aVerifyUtilityStatusPageTableValueForUtilityNameColumn : FAIL",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04bVerifyUtilityStatusPageTableValueForStatusColumn() throws Throwable {
   
    try {
    	 String utilityStatusStatusText = getWebElement(utilityStatusStatusXpath).getText();
		assertEquals(expectedUtilityStatus, utilityStatusStatusText);
		ExtentReport.logPass("PASS", "test04bVerifyUtilityStatusPageTableValueForStatusColumn : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04bVerifyUtilityStatusPageTableValueForStatusColumn : FAIL",driver,e);
		fail(e.getMessage());

	}
  }

  @Test
  public void test04cVerifyUtilityStatusPageTableValueForLogStatusColumn() throws Throwable {
    try {
        String utilityStatusLogStatusText = getWebElement(utilityStatusLogStatusXpath).getText();

		assertEquals(expectedUtilityLogStatus, utilityStatusLogStatusText);
		ExtentReport.logPass("PASS", "test04cVerifyUtilityStatusPageTableValueForLogStatusColumn : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04cVerifyUtilityStatusPageTableValueForLogStatusColumn : FAIL",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04dVerifyUtilityStatusPageTableValueForDownloadColumn() throws Throwable {
    try {
        String utilityStatusDownloadText = getWebElement(utilityStatusDownloadXpath).getText();

		assertEquals(expectedUtilityDownload, utilityStatusDownloadText);
        String utilityStatusDownloadLinkFormat = getWebElement(utilityStatusDownloadXpath).getAttribute("href");

		assertThat(utilityStatusDownloadLinkFormat, containsString("/services/utilityStatus/downLoad/"));

		ExtentReport.logPass("PASS", "utilityStatusDownloadText : PASS");
		ExtentReport.logPass("PASS", "test04dVerifyUtilityStatusPageTableValueForDownloadColumn : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04dVerifyUtilityStatusPageTableValueForDownloadColumn : FAIL",driver,e);
		fail(e.getMessage());
	}

   
  }

  @Test
  public void test04eVerifyUtilityStatusPageTableValueForStartTimeColumn() throws Throwable {
    try {
        String utilityStatusStartTimeText = getWebElement(utilityStatusStartTimeXpath).getText();

		assertThat(utilityStatusStartTimeText, containsString(currentDate));
		ExtentReport.logPass("PASS", "test04eVerifyUtilityStatusPageTableValueForStartTimeColumn : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04eVerifyUtilityStatusPageTableValueForStartTimeColumn : FAIL",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04fVerifyUtilityStatusPageTableValueForEndTimeColumn() throws Throwable {
    try {
        String utilityStatusEndTimeText = getWebElement(utilityStatusEndTimeXpath).getText();

		assertThat(utilityStatusEndTimeText, containsString(currentDate));
		ExtentReport.logPass("PASS", "test04fVerifyUtilityStatusPageTableValueForEndTimeColumn : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04fVerifyUtilityStatusPageTableValueForEndTimeColumn : FAIL",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04gVerifyUtilityStatusPageTableValueForDurationColumn() throws Throwable {
    try {
        String utilityStatusDurationText = getWebElement(utilityStatusDurationXpath).getText();

		assertTrue(utilityStatusDurationText.matches(durationPatternMatch));
		ExtentReport.logPass("PASS", "test04gVerifyUtilityStatusPageTableValueForDurationColumn : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04gVerifyUtilityStatusPageTableValueForDurationColumn : FAIL",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test05VerifyUtilityStatusPageSearch() throws Throwable {
    try {
    	/*
    	Omkar 8/8/2023 : xpath changes for 11.2
		waitForPresenceOfElement(
		        "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::table/descendant::input[@name='searchText']"
		); */
		waitForPresenceOfElement(
		        "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/..//input[@name='searchText']"
		);
		
			status.getUtilityStatusPageFormFieldSearch().sendKeys(currentDate);
			status.getUtilityStatusPageButtonSearchGlass().click();
		
		waitForSpinnerToEnd();
			String utilityStatusSearchStartTime = getWebElement(utilityStatusStartTimeXpath).getText();
			assertThat(utilityStatusSearchStartTime, containsString(currentDate));
			ExtentReport.logPass("PASS", "test05VerifyUtilityStatusPageSearch : PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test05VerifyUtilityStatusPageSearch : FAIL",driver,e);
		fail(e.getMessage());
	}

	
  }

  @Test
  public void test06DeleteUtilityStatusFirstRowAndConfirm() throws InterruptedException,Throwable {
    
    try {
    	deleteUtilityStatusPageMyStatusFirstRow();
		assertElementIsDisplayed(status.getUtilityStatusPageFormFieldSearch());
		ExtentReport.logPass("PASS", "test06DeleteUtilityStatusFirstRowAndConfirm : PASS");
		driver.findElements(By.xpath("//table/tbody/tr/td[11]/div[contains(@class, 'grid-cell')]"));
		ExtentReport.logPass("PASS", "test06DeleteUtilityStatusFirstRowAndConfirm");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06DeleteUtilityStatusFirstRowAndConfirm : FAIL",driver,e);
		fail(e.getMessage());

	}
  }

  public void runUtilityReport(String startDate, String endDate, String[] codes) throws Throwable,InterruptedException {
		waitForAjaxExtJs();
		populateReportFields(startDate, endDate, codes);
		waitForAjaxExtJs();
//		Omkar 7/8/2023 : xpath changes for 11.2
//		waitForElementToBeVisible(driver.findElement(By.xpath("//button/span[text()='Run']")));
//		driver.findElement(By.xpath("//button/span[text()='Run']")).click();
		waitForElementToBeVisible(driver.findElement(By.xpath("//span[text()='Run']")));
		driver.findElement(By.xpath("//span[text()='Run']")).click();
		waitForSpinnerToEnd();
		waitForUtilityFirstRowDownloadLinkToBecomeActive();
	
  }

  private void populateReportFields(String startDate, String endDate, String[] codes)
          throws Throwable {
    waitForElementToBeVisible(driver.findElement(By.name("startDate1")));
   
		driver.findElement(By.name("startDate1")).clear();
		driver.findElement(By.name("startDate1")).sendKeys(startDate);
		driver.findElement(By.name("endDate1")).clear();
		driver.findElement(By.name("endDate1")).sendKeys(endDate);
//		Omkar 7/8/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//button/span[text()='Select']")).click();
		driver.findElement(By.xpath("//span[text()='Select']")).click();
	    selectItemsOnSelector(codes);
    try {
//    	Omkar 7/8/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//button/span[text()='Apply']")).click();
		driver.findElement(By.xpath("//span[text()='Apply']")).click();
	} catch (Exception e) {
		ExtentReport.logFail("FAIL","Apply Button not found",driver,e);
	}
  }

  public void assertButtonIsInactive(String buttonText) throws Throwable {
    try {
      waitForAjaxExtJs();
//  	Omkar 7/8/2023 : xpath changes for 11.2
      
//      boolean isInactive = driver.findElement(By.xpath(
//              "//button/*[text()='"+buttonText+"']/ancestor::button[@disabled]"))
//              .isDisplayed()
//              ;
      boolean isInactive = driver.findElement(By.xpath(
              "//span[text()='"+buttonText+"']/ancestor::a[@aria-disabled='true']"))
              	 .isDisplayed();
      try {
		assertTrue(isInactive);
	} catch (Exception e) {
		ExtentReport.logPass("PASS","assertButtonIsInactive");
	}
    } catch(Throwable e) {
      try {
		fail("FAIL: "+buttonText+" button is active.");
	} catch (Exception e1) {
		ExtentReport.logPass("PASS","assertButtonIsInactive");

	}
    }
  }

  public void assertButtonIsActive(String buttonText) {
    boolean isInactive = false;
    try {
      waitForAjaxExtJs();
//      Omkar 7/8/2023 : xpath changes for 11.2
      
//      isInactive = driver.findElement(By.xpath(
//              "//button/*[text()='" + buttonText + "']/ancestor::button[@disabled]"))
//              .isDisplayed()
//      ;
      boolean isActive = driver.findElement(By.xpath(
              "//span[text()='"+buttonText+"']/ancestor::a[@aria-disabled='false']"))
              	 .isDisplayed();
      if (isActive) {
        try {
			fail("FAIL: " + buttonText + " button is inactive.");
		} catch (AssertionError e) {
			ExtentReport.logPass("PASS", "assertButtonIsActive");

		}
      }
    } catch (Throwable e) {
      try {
		assertFalse(isInactive);
	} catch (AssertionError e1) {
		ExtentReport.logPass("PASS", "assertButtonIsActive");

	}
    }
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
