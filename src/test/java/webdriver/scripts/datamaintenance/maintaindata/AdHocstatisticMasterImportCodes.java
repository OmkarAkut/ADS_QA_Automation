package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.RVUExportImportFunction;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdHocstatisticMasterImportCodes extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static CostingMap costing;
	static ContractingMap contracting;
	final static String aTozPageAdHocstatsMaster="Ad Hoc Statistic Masters";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String adHocStatsName="AdHocMaster"+currentDateTime;
	static String[] filter= {"Name","Is","Equal To",adHocStatsName};
	/** Customer Issue: Automated test script for ADS-18798 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AdHocstatisticMasterImportCodes",
				"webdriver.scripts.datamaintenance.maintaindata", "AdHocstatisticMasterImportCodes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contracting=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageAdHocstatsMaster);
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01CreateAdHocStatsMaster_ADS_18798() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputText(code, DataMaintenanceMap.getinputCode());
			keyInInputText(adHocStatsName, DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.getazSaveBtn());
			
			
			ExtentReport.logPass("PASS", "test01CreateAdHocStatsMaster_ADS_18798");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateAdHocStatsMaster_ADS_18798", driver, e);
			
		} 
	}
	@Test
	public void test02ImportCodes_ADS_18798() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getadHocImportBtn());
			waitForPageTitle("Import Data");
			assertTextIsDisplayed("Import Data");
			doactionClick(driver.findElement(By.xpath("(//div[contains(@id,'importwindow')]//span[text()='Select'])[2]")));
			driverDelay();
			//Shilpa: updated file import using Robot class, due to security issues with Autoit
			fileImport(System.getProperty("user.dir") + "\\TestFiles\\AdHocCodes.txt");
			RVUExportImportFunction.selectFileLocAndaddFileName(costing.getRvuImportButton());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test02ImportCodes_ADS_18798");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ImportCodes_ADS_18798", driver, e);
			
		} 
	}
	@Test
	public void test03ValidateCodesImported_ADS_18798() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getazEditBtn());
			assertHasElements(driver.findElements(By.xpath("//div[contains(@id,'masterdetailgrid')]//td[1]/div")));
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateCodesImported_ADS_18798");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateCodesImported_ADS_18798", driver, e);
			
		} 
	}
	@Test
	public void test04DeleteAdHocStatsMaster_ADS_18798() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is not data available to display.");
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test04DeleteAdHocStatsMaster_ADS_18798");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteAdHocStatsMaster_ADS_18798", driver, e);
			
		} 
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
