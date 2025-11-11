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
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChargeableActivityGroup extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozChargeableAcyivityGroup= "Chargeable Activity Groups";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name = "Name" + currentDateTime;
	static String[] filter= {"Name","Is","Equal To",name};
	static String azName = "Chargeable Activity Group";
	static ContractingMap contract;
	String[] columns = {"0000117  ROOM CHARGE MED/SURG  <None>  U  2115  150  Marina Charge Master 2" ,"0001A  TB Test  <None>  H  3232  150  Marina Charge Master 2"};
	String[] columnsEdit = { "0000TB  Quantity Field Expansion #2  FC  H  <All>  123  SD"};
	String[] validateAgesBeforeEdit= {"0000117  ROOM CHARGE MED/SURG" ,"0001A  TB Test"};
	String[] validateAgesAfterEdit=  {"0000117  ROOM CHARGE MED/SURG" ,"0001A  TB Test","0000TB  Quantity Field Expansion #2"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ChargeableActivityGroup", "webdriver.scripts.datamaintenance.maintaindata",
				"ChargeableActivityGroup");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contract=BuildMap.getInstance(driver,ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozChargeableAcyivityGroup);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveAndCreateNewGroup() throws Throwable {
		try {
			addNewScenario(validateAgesBeforeEdit,name,aTozChargeableAcyivityGroup,columns);
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			driverDelay();
			doClick(DataMaintenanceMap.getCancelCloseButton());
			if(DataMaintenanceMap.getdynamicWindowCancelCloseBtnlist().size()>0) {
				doClick(DataMaintenanceMap.getdynamicWindowCancelCloseBtnlist().get(0));
			}
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveAndCreateNewGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveAndCreateNewGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveCloseGroup() throws Throwable {
		try {
			addNewScenario(validateAgesAfterEdit,name,aTozChargeableAcyivityGroup,columns);
			doClick(DataMaintenanceMap.getsaveButton());
			doClick(DataMaintenanceMap.getsaveCloseButton());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test02ValidateSaveCloseGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveCloseGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditSaveGroup() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons(azName, "Select");
			driverDelay();
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsEdit);
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Apply']")));
			assertElements(validateAgesAfterEdit,azName);
			doClick(DataMaintenanceMap.getsaveCloseButton());
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03ValidateEditSaveGroup");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditSaveGroup", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
