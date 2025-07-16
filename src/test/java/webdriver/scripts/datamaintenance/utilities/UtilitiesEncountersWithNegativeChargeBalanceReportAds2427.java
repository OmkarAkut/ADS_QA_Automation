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
public class UtilitiesEncountersWithNegativeChargeBalanceReportAds2427 extends UtilitiesPageHelper {
	static String directoryPath;
	static DataMaintenanceMap dm;
	final String reportName = "Encounters With Negative Charge Balance Report";
	static FileUtility filer = new FileUtility();
	static String zipPath = null;
	String[] codes = { "2S1  Office", "2S3  Hospital", "3S1  Office", "4S4  Extended Stay" };
	String startDate = "01/01/2019";
	String endDate = "06/30/2019";
	String currentDate = javaGetCurrentDate("MM/dd/yyyy");
	String nameDate = javaGetCurrentDate("MMddyyyy");
	//Shilpa updated file name 11.08.2022
//	List<String> expectedZipDirectoryName = Arrays
//			.asList("eolheiser_Encounters With Negative Charge Balance Report_" + nameDate, ".zip");
	//Shilpa : updated filepath name as per added user 10.10.2024
	List<String> expectedZipDirectoryName = Arrays
			.asList("aadmin_Encounters With Negative Charge Balance Report_" + nameDate, ".zip");
	/**
	 * Test script that verifies Encounters With Negative Charge Balance Report.
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("UtilitiesEncountersWithNegativeChargeBalanceReportAds2427","webdriver.scripts.datamaintenance.utilities"
				,"UtilitiesEncountersWithNegativeChargeBalanceReportAds2427");
		try {
			directoryPath = setupSavedFilesDirectoryPath();
			dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			System.out.println(
					"Test Class: " + UtilitiesEncountersWithNegativeChargeBalanceReportAds2427.class.getSimpleName());
			// loginUser(Users.ApplicationAdministrator1);
			//Shilpa : updated user to AutomationTesterAdmin
			loginUser(Users.AutomationTesterAdmin);
			goToPage("Utilities");
			waitForAjaxExtJs();
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
			doClick(dm.getUtilitiesPageEncountersWithNegativeChargeBalanceReport());
			driverDelay();
			runReport(startDate, endDate, codes);
			ExtentReport.logPass("PASS", "test01SetParametersAndRunReport : PASS");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SetParametersAndRunReport", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyEncountersWithNoChargesReportSummaryVersion() throws Throwable {
		try {
			System.out.println(directoryPath);
			zipPath = filer.getFilesFromDirectoryAndReturnTargetFilePath(directoryPath, expectedZipDirectoryName.get(0),
					"zip");
			System.out.println("Zip Path: " + zipPath);
			List<String> report = filer.convertFileInZipDirectoryToList(zipPath, "summary");
			for (String line : report) {
				System.out.println(line);
			}
				assertThat(report.size(), equalTo(24));
				assertListOfStringsContainsExpectedStrings(report, encountersWithNegativeChargeBalanceReport);

				ExtentReport.logPass("PASS", "Report Size : PASS");
				ExtentReport.logPass("PASS", "test02VerifyEncountersWithNoChargesReportSummaryVersion : PASS");
			
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
				ExtentReport.logPass("PASS", "test03VerifyZipDirectoryName");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyZipDirectoryName", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04VerifyEncountersWithNoChargesReportDetailVersion() throws IOException,Throwable {
		try {
			List<String> report = filer.convertFileInZipDirectoryToList(zipPath, "detail");
			for (String line : report) {
				System.out.println(line);
			}
				assertThat(report.size(), equalTo(26));
				assertListOfStringsContainsExpectedStrings(report, encountersWithNegativeChargeBalanceReport);
				ExtentReport.logPass("PASS", "Report Size : PASS");

				ExtentReport.logPass("PASS", "test04VerifyEncountersWithNoChargesReportDetailVersion : PASS");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyEncountersWithNoChargesReportDetailVersion", driver, e);
			fail(e.getMessage());
		}
	}

	List<String> encountersWithNegativeChargeBalanceReport = Arrays.asList(
			"======================================================", "Encounters with Negative Charge Balance Report",
			"======================================================", "Started at: " + currentDate,
			"Report Parameters:", "Discharge Date start range (mm/dd/yyyy): " + startDate,
			"Discharge Date end range (mm/dd/yyyy): " + endDate, "Encounter Type selection 1: 2S1",
			"Encounter Type selection 2: 2S3", "Encounter Type selection 3: 3S1", "Encounter Type selection 4: 4S4",
			"Encounter Type selection 5:", "Encounter Type selection 6:", "Encounter Type selection 7:",
			"Encounter Type selection 8:", "Encounter Type selection 9:", "Encounter Type selection 10:",
			"0 Encounters with Negative Charge Balance", "Finished at: " + currentDate,
			"Total elapsed time (hh:mm:ss): 00:00:00");

	List<String> expectedSummaryReportName = Arrays
			.asList("automationappadmin1_Encounters With Negative Charge Balance Report_", nameDate, "_summary.log");

	List<String> expectedDetailReportName = Arrays
			.asList("automationappadmin1_Encounters With Negative Charge Balance Report_", nameDate, "_detail.log");

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
