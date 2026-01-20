package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.assertTrue;
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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GLDataScenarios extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLDataScenarios = "GL Data Scenarios";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String fiscalYear="**FY16";
	static String[] glDataScenariosFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String[] depts= {"0001 0001 Department"};
	static String[] glAccount= {"1510000  IP REVENUE  OO"};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLDataScenarios", "webdriver.scripts.datamaintenance.az",
				"GLDataScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLDataScenarios);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void addGroupDataPoints() throws Throwable, Throwable {
		keyInInputWithDoubleClick(driver.findElement(By.xpath("((//div[text()='GL Data'])[1]//following::div//table[1]//td[2]/div)[2]")), generateRandomNumber());
		driver.findElement(By.xpath("(//div[text()='GL Data'])[1]//following::div//table[1]//td[1]/div")).click();
		doClickButtons("GL Data", "Undo");
		assertElementText(driver.findElement(By.xpath("((//div[text()='GL Data'])[1]//following::div//table[1]//td[2]/div)[2]")), " ", printout);
		for(int i=2;i<=driver.findElements(By.xpath("(//div[text()='GL Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div")).size();i++) {
			keyInInputWithDoubleClick(driver.findElement(By.xpath("((//div[text()='GL Data']//following::div[contains(@class,'x-grid-scrollbar-clipper ')])[2]//table[1]//td/div)["+i+"]")),generateRandomNumber());
		}
		String total=driver.findElement(By.xpath("(//div[text()='GL Data'])[1]//following::div//table[1]//td[1]/div")).getText();
		int tot=Integer.parseInt(total);
		if(tot>0) {
			assertTrue(printout);
		}
		else {fail();}
		doClickButtons("GL Data", "Save Data");
		//Delete
		for(WebElement data:DataMaintenanceMap.getgroupDataGrid()) {
			try {
				if(DataMaintenanceMap.getgroupDataGrid().size()>0) {
					data.click();
					doClickButtons("GL Data", "Delete");
					doClickButtons("Delete", "Delete");
					
				}
			} catch (Exception e) {
				
			}
		}
	}
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("description", name, "GL Data Scenario");
			clickCheckboxByName("timePeriodName");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglFiscalYearDrp(), fiscalYear);
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(glDataScenariosFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateAddGLData() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			doClickButtons("GL Data", "Add");
			waitForFormDialog("Add GL Data");
			doClick(DataMaintenanceMap.getglEntitiesSelectBtn());
			waitForFormDialog("Add Entity");
			selectFormItem(entity, "");
			doClick(DataMaintenanceMap.getglDeptSelectBtn());
			waitForFormDialog("Select Department");
			ContractModelsHelper.selectMultipleColumnsToDisplay(depts);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getglGlAcctSelectBtn());
			waitForFormDialog("Select GL Account");
			ContractModelsHelper.selectMultipleColumnsToDisplay(glAccount);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClickButtons("Add GL Data", "Add");
			addGroupDataPoints();
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02ValidateAddGLData");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateAddGLData", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateDeleteGLDataScenario() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03ValidateDeleteGLDataScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateDeleteGLDataScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
