package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberSubgroupAggregates extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozSiteOfServiceTables = "Member Subgroup Aggregates";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] memberSubGroupFilter= {"Employer Group Code","Is","Equal To","1SM1000"};
	static String employerGroup="1, 1SM1000 EmployerGroup1 ";
	static String employerSubGroup="1SM1000SG Sub Group 1000";
	static String memberDestination="1, 3SM1 Member Designation1";
	static String[] memberSubGroupInnerFilter= {"Code","Is","Equal To","1SM1000"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("MemberSubgroupAggregates", "webdriver.scripts.datamaintenance.az",
				"MemberSubgroupAggregates");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozSiteOfServiceTables);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void addMemberSubgroupAggregate() throws Throwable {
		doClick(DataMaintenanceMap.getemployerGrpSelectBtn());
		waitForFormDialog("Add Employer Group");
		doClickButtons("Add Employer Group", "Filter");
		doFilterCreate(memberSubGroupInnerFilter);
		selectFormItem(employerGroup, "");
		doClick(DataMaintenanceMap.getemployerSubGrpSelectBtn());
		waitForFormDialog("Add Employer Subgroup");
		selectFormItem(employerSubGroup, "");
		doClick(DataMaintenanceMap.getmemberDesignationSelectBtn());
		waitForFormDialog("Add Member Designation");
		selectFormItem(memberDestination, "");
		
	}
	@Test
	public void test01ValidateSaveCreateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addMemberSubgroupAggregate();
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(memberSubGroupFilter);
			assertTextIsDisplayed("1, 1SM1000 EmployerGroup1");
			assertTextIsDisplayed("1SM1000SG Sub Group 1000");
			assertTextIsDisplayed("1, 1SM1 Member Designation1 - Entity11");
			doClick(DataMaintenanceMap.getazViewBtn());
			doClickButtons("Member Subgroup Aggregate", "Close");
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveCreateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveCreateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			addMemberSubgroupAggregate();
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(memberSubGroupFilter);
			assertTextIsDisplayed("1, 1SM1000 EmployerGroup1");
			assertTextIsDisplayed("1SM1000SG Sub Group 1000");
			assertTextIsDisplayed("1, 1SM1 Member Designation1 - Entity11");
			ExtentReport.logPass("PASS", "test02ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateDeleteMemberSubgroupButton() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test03ValidateDeleteMemberSubgroupButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateDeleteMemberSubgroupButton", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
