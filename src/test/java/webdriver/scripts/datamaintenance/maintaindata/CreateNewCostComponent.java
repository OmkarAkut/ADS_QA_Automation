package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateNewCostComponent extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static ContractingMap contractMap;
	final static String aTozPageCostCompMaster="Cost Component Masters";
	static String costComponentMaster="00 Test CCM D";
	static String[] filter = { "Name", "Is", "Equal To", costComponentMaster };
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costComponent="Component" +currentDateTime;
	static String costComponentType="Direct Expense";
	/** Regression: Automated test script for ADS-8879*/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateDeleteCostMethodDeptMasterDischargeStatus",
				"webdriver.scripts.datamaintenance.maintaindata", "CreateDeleteCostMethodDeptMasterDischargeStatus");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageCostCompMaster);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}	
	@Test
	public void test01AddNewCostComponent_ADS_8879() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getCostComponentFilterButton());
			waitForElementToBeVisible(DataMaintenanceMap.getcostComponentFilterWindow());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costComponentMaster);
			doClick(DataMaintenanceMap.getcostComponentNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getcostComponentContinueBtn());
			doClick(DataMaintenanceMap.getcostComponentContinueBtn());
			keyInInputText(costComponent, DataMaintenanceMap.getcostCompInputName());
			doClick(DataMaintenanceMap.getcomponentTypeBtn());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getcomponentTypeOptions(), costComponentType);
			keyInInputText(generateRandomNumber(), DataMaintenanceMap.getorderRVUIndex());
			doClick(DataMaintenanceMap.getcomponentSave());
			doClick(DataMaintenanceMap.getcomponentSaveClose());
			assertTextIsDisplayed(costComponent);
			ExtentReport.logPass("PASS", "test01AddNewCostComponent_ADS_8879");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddNewCostComponent_ADS_8879", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02DeleteNewCostComponent_ADS_8879() throws Throwable {
		try {
			doClick("//*[text()='"+costComponent+"']");
			doClick(DataMaintenanceMap.getcostComponentDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsNotDisplayed("//*[contains(text(),"+costComponent+"]");
			doClick(DataMaintenanceMap.getcomponentMasterSaveClose());
			doClosePageOnLowerBar("Maintain Data");
			ExtentReport.logPass("PASS", "test02DeleteNewCostComponent_ADS_8879");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02DeleteNewCostComponent_ADS_8879", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
	
}
