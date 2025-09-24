package webdriver.scripts.costing;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12901*/
public class ActivityStatisticMasterEditStatsSave extends GoHelper{
	static CostingMap costing;
	static String master="1833ACTSTATS";
	static String[] filter = { "Code", "Is", "Equal To", master };
	static String deptGroup="*ALLDEPTS ALLDEPTS";
	static String modifier="ALL 0ALL 0ALL Charge associated with ALL Dept";
	static String chargeCodeName="0ALL  0ALL Charge associated with ALL Dept";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ActivityStatisticMasterEditStatsSave", "webdriver.scripts.datamaintenance.maintaindata",
				"ActivityStatisticMasterEditStatsSave");
		try {
	
			costing = BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("CostAnalyst1");
			goToPage("Costing Data Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01AddNewMapping_ADS_12901() throws Throwable {
		try {
			doClick(CostingMap.getcostingTreeExpand());
			doClick(CostingMap.getcodesHierarchiesExpand());
			doClick(CostingMap.getstatisticsExpand());
			doClick(CostingMap.getstatisticsActivityMaster());
			doClick(CostingMap.getstatisticsActivityMasterFilterBtn());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(master);
			doClick(CostingMap.getactivityStatisticsEditBtn());
			waitForElementToBeVisible(CostingMap.getmappingsNewBtn());
			doClick(CostingMap.getmappingsNewBtn());
			doClick(costing.getUnitCostQuickCalculationDropdownEntity());
			doDropdownSelectUsingOptionTextWithelement(CostingMap.getdeptGroupDropdownOptions(), "0000 PRIVATE PAY");
			doClick(CostingMap.getdeptGroupSelectBtn());
			doClick("//div[text()='"+deptGroup+"']");
			doClick(costing.getUnitCostQuickCalculationDepartmentButtonApply());
			doClick(CostingMap.getmodifierSelectBtn());
			doClick("//div[contains(text(),'"+modifier+"')]");
			doClick(costing.getUnitCostQuickCalculationDepartmentButtonApply());
			doClick(CostingMap.getnewMappingSaveCloseBtn());
			assertTextIsDisplayed(chargeCodeName);
			doClick(CostingMap.getmappingsEditBtn());
			keyInInputText("10", driver.findElement(By.name("convFactor")));
			doClick(CostingMap.getmappingSaveBtn());
			doClick(CostingMap.getmappingSaveCloseBtn());
			ExtentReport.logPass("PASS", "test01AddNewMapping_ADS_12901");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddNewMapping_ADS_12901", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01DeleteMapping_ADS_12901() throws Throwable {
		try {
			doClick("//div[text()='0ALL  0ALL Charge associated with ALL Dept']");
			doClick(CostingMap.getmappingsDeleteBtn());
			doClick(costing.warningMessageDeleteBtn());
			doClick(CostingMap.getactivityStatsCancelClose());
			doClick(CostingMap.getactivityStatsMasterCancelClose());
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test01DeleteMapping_ADS_12901");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01DeleteMapping_ADS_12901", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
