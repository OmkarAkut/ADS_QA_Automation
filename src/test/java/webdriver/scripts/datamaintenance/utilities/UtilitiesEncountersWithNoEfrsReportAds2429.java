package webdriver.scripts.datamaintenance.utilities;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.datamaintenance.datamaintenancehelpers.UtilitiesPageHelper;
import webdriver.users.Users;
import webdriver.utilities.FileUtility;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilitiesEncountersWithNoEfrsReportAds2429 extends UtilitiesPageHelper {

  static DataMaintenanceMap dm;
  final String reportName = "Encounters With No EFRs Report";
  static FileUtility filer = new FileUtility();
  static String directoryPath;
  static String zipPath = null;
  String[] codes = {
      "2S1  Office", "2S3  Hospital", "3S1  Office", "4S4  Extended Stay"
  };
  String startDate = "01/01/2019";
  String endDate = "06/30/2019";
  String currentDate = javaGetCurrentDate("MM/dd/yyyy");
  String nameDate = javaGetCurrentDate("MMddyyyy");
  List<String> expectedZipDirectoryName = Arrays.asList(
      "eolheiser_" + reportName + "_" + nameDate,
      ".zip"
  );

  /** Test script that verifies Encounters With Zero Charge Balance Report. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UtilitiesEncountersWithNoEfrsReportAds2429","webdriver.scripts.datamaintenance.utilities" ,"UtilitiesEncountersWithNoEfrsReportAds2429");
    try {
		System.out.println(browser);
		failIfHeadless(browser);
		directoryPath = setupSavedFilesDirectoryPath();
		dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println(
		        "Test Class: "
		        + UtilitiesEncountersWithNoEfrsReportAds2429.class.getSimpleName());
		loginUser(Users.ApplicationAdministrator1);
		goToPage("Utilities");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01SetParametersAndRunReport() throws Throwable {
    try {
      waitForAjaxExtJs();
      Thread.sleep(1000);
      doClick(dm.getUtilitiesPageEncountersWithNoEfrsReport());
      runReport(startDate, endDate, codes);
		ExtentReport.logPass("PASS", "test01SetParametersAndRunReport : PASS");

    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01SetParametersAndRunReport",driver,e);
		fail(e.getMessage());
    		
	} 
    
  }

  @Test
  public void test02VerifyEncountersWithNoChargesReportSummaryVersion()
          throws Throwable {
    try {
		zipPath = filer.getFilesFromDirectoryAndReturnTargetFilePath(
		        directoryPath,
		        expectedZipDirectoryName.get(0),
		        "zip"
		);
		System.out.println("Zip Path: " + zipPath);
		List<String> report = filer.convertFileInZipDirectoryToList(
		        zipPath,
		        "summary"
		);
		for (String line : report) {
		  System.out.println(line);
		}
			assertThat(report.size(), equalTo(24));
			assertListOfStringsContainsExpectedStrings(report, encountersWithNoChargesReport);
			ExtentReport.logPass("PASS", "Report Size : PASS");
			ExtentReport.logPass("PASS", "test02VerifyEncountersWithNoChargesReportSummaryVersion : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02VerifyEncountersWithNoChargesReportSummaryVersion",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03VerifyZipDirectoryName() throws Throwable {
   
    try {
    	 List<String> zipName = Arrays.asList(zipPath);
		assertListOfStringsContainsExpectedStrings(zipName, expectedZipDirectoryName);
		ExtentReport.logPass("PASS", "test03VerifyZipDirectoryName : PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03VerifyZipDirectoryName",driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04VerifyEncountersWithNoChargesReportDetailVersion() throws IOException,Throwable {
    try {
		List<String> report = filer.convertFileInZipDirectoryToList(
		        zipPath,
		        "detail"
		);
		for (String line : report) {
		  System.out.println(line);
		}
			assertThat(report.size(), equalTo(26));
			assertListOfStringsContainsExpectedStrings(report, encountersWithNoChargesReport);
			ExtentReport.logPass("PASS", "Report size : PASS");
			ExtentReport.logPass("PASS", "test04VerifyEncountersWithNoChargesReportDetailVersion : PASS");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04VerifyEncountersWithNoChargesReportDetailVersion",driver,e);
		fail(e.getMessage());
	}
  }

  List<String> encountersWithNoChargesReport = Arrays.asList(
      "======================================================",
      "Encounters with No EFRs Report",
      "======================================================",
      "Started at: " + currentDate,
      "Report Parameters:",
      "Discharge Date start range (mm/dd/yyyy): " + startDate,
      "Discharge Date end range (mm/dd/yyyy): " + endDate,
      "Encounter Type selection 1: 2S1",
      "Encounter Type selection 2: 2S3",
      "Encounter Type selection 3: 3S1",
      "Encounter Type selection 4: 4S4",
      "Encounter Type selection 5:",
      "Encounter Type selection 6:",
      "Encounter Type selection 7:",
      "Encounter Type selection 8:",
      "Encounter Type selection 9:",
      "Encounter Type selection 10:",
      "0 Encounters with No Encounter Financial Records",
      "Finished at: " + currentDate,
      "Total elapsed time (hh:mm:ss): 00:00:00"
  );

  List<String> expectedSummaryReportName = Arrays.asList(
          "automationappadmin1_Encounters With Zero Charge Balance Report_",
          nameDate,
          "_summary.log"
  );

  List<String> expectedDetailReportName = Arrays.asList(
          "automationappadmin1_Encounters With Zero Charge Balance Report_",
          nameDate,
          "_detail.log"
  );
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
}
