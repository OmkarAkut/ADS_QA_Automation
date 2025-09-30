package webdriver.scripts.datamaintenance.utilities;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.datamaintenance.datamaintenancehelpers.UtilitiesPageHelper;
import webdriver.users.Users;
import webdriver.utilities.FileUtility;

public class ZeroChargeBalanceUtility extends UtilitiesPageHelper {
	static DataMaintenanceMap dm;
	final String reportName = "Delete Encounters With Zero Charge Balances";
	static FileUtility filer = new FileUtility();
	 static String directoryPath;
	  static String zipPath = null;
	  String startDate = "07/01/2011";
	  String endDate = "07/31/2011";
	  String currentDate = javaGetCurrentDate("MM/dd/yyyy");
	  String nameDate = javaGetCurrentDate("MMddyyyy");
	 
	  List<String> expectedZipDirectoryName = Arrays.asList(
		      "aadmin_Encounters With Zero Charge Balance Report_" + nameDate,
		      ".zip"
		  );
	  //Customer issue ADS-21374
	  @BeforeClass
	  public static void setupScript() throws Throwable {
		  ExtentReport.reportCreate("ZeroChargeBalanceUtility","webdriver.scripts.datamaintenance.utilities", "ZeroChargeBalanceUtility");

	    try {
			directoryPath = setupSavedFilesDirectoryPath();
			dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			System.out.println(
			        "Test Class: "
			        + UtilitiesEncountersWithZeroChargeBalanceReportAds2428.class.getSimpleName());
			loginUser(Users.AutomationTesterAdmin);
			goToPage("Utilities");
			ExtentReport.logPass("PASS", "setupScript");
		}  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	  }
	  @Test
	  public void test01SetParametersAndRunReport_ADS_21374() throws Throwable {
	    try {
	      waitForAjaxExtJs();
	      doClick(dm.getgetUtilitiesPageDeleteEncountersWithZeroChargeBalanceReport());
	      driverDelay();
	      driver.findElement(By.name("chkmultiSelect1")).click();
	      deleteReport(startDate, endDate);
	      ExtentReport.logPass("PASS", "test01SetParametersAndRunReport_ADS_21374 : PASS");
	    } catch (Exception|AssertionError e) {
	    	ExtentReport.logFail("FAIL", "test01SetParametersAndRunReport_ADS_21374", driver, e);
			fail(e.getMessage());
			
		
	    }
	  }
}
