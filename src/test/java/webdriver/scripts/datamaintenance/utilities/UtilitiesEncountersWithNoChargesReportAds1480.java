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
public class UtilitiesEncountersWithNoChargesReportAds1480
        extends UtilitiesPageHelper {

  static DataMaintenanceMap dm;
  final String reportName = "Encounters With No Charges Report";
  static FileUtility utility = new FileUtility();
  static String directoryPath;
  static String zipPath = null;
  String[] codes = {
      "2S1  Office", "2S3  Hospital", "3S1  Office", "4S4  Extended Stay"
  };
  String startDate = "01/01/2019";
  String endDate = "06/30/2019";
  String currentDate = javaGetCurrentDate("MM/dd/yyyy");
  String nameDate = javaGetCurrentDate("MMddyyyy");
  List<String> summaryReport;
  List<String> expectedZipDirectoryName = Arrays.asList(
          "eolheiser_Encounters With No Charges Report_" + nameDate,
          ".zip"
  );

  /** Test script that verifies Encounters With No Charges Report. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
		 ExtentReport.reportCreate("UtilitiesEncountersWithNoChargesReportAds1480","webdriver.scripts.datamaintenance.utilities", "UtilitiesEncountersWithNoChargesReportAds1480");

    try {
		directoryPath = setupSavedFilesDirectoryPath();
		dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println(
		        "Test Class: "
		        + UtilitiesEncountersWithNoChargesReportAds1480.class.getSimpleName());
		loginUser(Users.ApplicationAdministrator1);
		goToPage("Utilities");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01SetParametersAndRunReport() throws Throwable {
    try {
      waitForAjaxExtJs();
      doClick(dm.getUtilitiesPageEncountersWithNoChargesReport());
      runReport(startDate, endDate, codes);
		ExtentReport.logPass("PASS", "test01SetParametersAndRunReport : PASS");

    } catch (Exception e) {
		ExtentReport.logFail("FAIL", "test01SetParametersAndRunReport", driver, e);
		fail(e.getMessage());

	} 
    
  }

  @Test
  public void test02VerifyEncountersWithNoChargesReportSummaryVersion() throws IOException,Throwable {
	  try {
		System.out.println(directoryPath);
		zipPath = utility.getFilesFromDirectoryAndReturnTargetFilePath(
		        directoryPath,
		        expectedZipDirectoryName.get(0),
		        "zip"
		);
		System.out.println("Zip Path: " + zipPath);
		summaryReport = utility.convertFileInZipDirectoryToList(
		        zipPath,
		        "summary"
		);
		if (printout) {
		  for (String line : summaryReport) {
		    System.out.println(line);
		  }
		}
			assertThat(summaryReport.size(), equalTo(24));
			assertListOfStringsContainsExpectedStrings(summaryReport, encountersWithNoChargesReport);
			ExtentReport.logPass("PASS", "Summary Report Size  : PASS");
			ExtentReport.logPass("PASS", "test02VerifyEncountersWithNoChargesReportSummaryVersion  : PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02VerifyEncountersWithNoChargesReportSummaryVersion", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03VerifyZipDirectoryName() throws Throwable {
    try {
		List<String> zipName = Arrays.asList(zipPath);
			assertListOfStringsContainsExpectedStrings(zipName, expectedZipDirectoryName);
			ExtentReport.logPass("PASS", " test03VerifyZipDirectoryName  : PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03VerifyZipDirectoryName", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04VerifyEncountersWithNoChargesReportDetailVersion() throws Throwable {
    try {
		List<String> detailReport = utility.convertFileInZipDirectoryToList(
		        zipPath,
		        "detail"
		);
		if (printout) {
		  for (String line : detailReport) {
		    System.out.println(line);
		  }
		}
			assertThat(detailReport.size(), equalTo(26));
			assertListOfStringsContainsExpectedStrings(detailReport, encountersWithNoChargesReport);
			ExtentReport.logPass("PASS", " Detail Report Size  : PASS");

			ExtentReport.logPass("PASS", " test04VerifyEncountersWithNoChargesReportDetailVersion : PASS");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04VerifyEncountersWithNoChargesReportDetailVersion",driver,e);
		fail(e.getMessage());
	}
  }

  List<String> encountersWithNoChargesReport = Arrays.asList(
      "======================================================",
      "Encounter with No Charges Report",
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
      "0 Encounters with No Charge Items",
      "Finished at: " + currentDate,
      "Total elapsed time (hh:mm:ss): 00:00:00"
  );



  List<String> expectedSummaryReportName = Arrays.asList(
          "automationappadmin1_Encounters With No Charges Report_",
          nameDate,
          "_summary.log"
  );

  List<String> expectedDetailReportName = Arrays.asList(
          "automationappadmin1_Encounters With No Charges Report_",
          nameDate,
          "_detail.log"
  );
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
