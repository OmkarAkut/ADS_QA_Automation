package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateChangesToTheDepartmentHierarchy extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPageContractBatches = "Department Hierarchies";
	static String deptHierarchyName="ADS-12686 DEPT Hierarchy Test";
	static String[] filter = { "Hierarchy Name", "Is", "Equal To", deptHierarchyName };
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String newDeptHierarchy = "Dept  " + currentDateTime;
	static String[] filterNewDept = { "Hierarchy Name", "Is", "Equal To", newDeptHierarchy };
	static String deptMaster="FDEPTMASTER FDEPARTMENTMASTER";
	static String deptCode;
	List<String> deptCodeList=new ArrayList<>();
	/** Support Issues: Automated test script for ADS-12686 ,ADS-12687**/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateChangesToTheDepartmentHierarchy", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateChangesToTheDepartmentHierarchy");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageContractBatches);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateSaveDeptHierarchy_12686() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdepthierarchyFilterBtn());
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getdepthierarchyEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdepthierarchyForm());
			WebElement source=driver.findElement(By.xpath("//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container']//div[text()='120 EMERGENCY DEPARTMENT']"));
			WebElement target=driver.findElement(By.xpath("//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container']//span[text()='*ALLDEPT All Departments']"));
			CimHelper.dragAndDrop(source, target);
			driverDelay();
			doClick(DataMaintenanceMap.getdepthierarchySaveBtn());
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			doClick(DataMaintenanceMap.getdepthierarchyEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getdepthierarchyForm());
			doClick(DataMaintenanceMap.getdeptNode());
			assertElementIsDisplayedWithXpath("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='120 EMERGENCY DEPARTMENT']");
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02Removehierarchy_12686() throws Throwable {
		try {
			WebElement source=driver.findElement(By.xpath("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='120 EMERGENCY DEPARTMENT']"));
			WebElement target=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')])[1]"));
			CimHelper.dragAndDrop(source, target);
			driverDelay(800);
			doClick(DataMaintenanceMap.getdepthierarchySaveBtn());
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03AddNewHierarchy_12687() throws Throwable {
		try {
			String input;
			doClick(DataMaintenanceMap.getdepthierarchyNewBtn());
			keyInInputText(newDeptHierarchy, DataMaintenanceMap.getaddName());
			driver.findElement(By.xpath("//input[@name='masterCode']/..")).click();
			driverDelay(100);
			doDropdownSelectUsingOptionTextWithelement(DataMaintenanceMap.getdeptMasterDrpdwnOptions(),
					deptMaster);
			driverDelay(100);
			doClick(DataMaintenanceMap.getdeptMasterNewGroup());
			waitForElementToBeVisible(DataMaintenanceMap.getdeptMasterCode());
			for(int i=0;i<2;i++) {
				deptCode=generateRandomNumber();
				keyInInputText(deptCode, DataMaintenanceMap.getdeptMasterCode());
				keyInInputText("Description", DataMaintenanceMap.getdeptMasterDesc());
				 input="*"+deptCode+" Description";
				deptCodeList.add(input);
				doClick(DataMaintenanceMap.getdeptMasterSaveCreateNew());
				
				
			}
			doClick(DataMaintenanceMap.getdeptMasterCancelClose());
			dragDropTheDeptsToHierarchy(deptCodeList);

			doClick("(//div[contains(@class,'glAccountsHierarchyGrid ')]//div[@class='x-grid-item-container']//span)[2]");
			WebElement sourceHierarchy=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsHierarchyGrid ')]//div[@class='x-grid-item-container']//span)[2]"));
			WebElement targetHierarchy=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')])[1]"));
			CimHelper.dragAndDrop(sourceHierarchy, targetHierarchy);
			driverDelay(800);
			for(String str:deptCodeList) {
				doClick("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='"+str+"']");
				doClick(DataMaintenanceMap.getdeptMasterDeleteGroup());
				doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			}
			ExtentReport.logPass("PASS", "test03AddNewHierarchy_12687");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddNewHierarchy_12687", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteGroups_12687() throws Throwable {
		try {
			for(String str:deptCodeList) {
				doClick("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='"+str+"']");
				doClick(DataMaintenanceMap.getdeptMasterDeleteGroup());
				doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			}
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			ExtentReport.logPass("PASS", "test05DeleteGroups_12687");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteGroups_12687", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05SaveNewHiearchy_12687() throws Throwable {
		try {
			WebElement sourceGroup=driver.findElement(By.xpath("((//div[contains(@class,'glAccountsGrid ')])[1]//following::div[contains(text(),'*')])[1]"));
			WebElement targetGroup=driver.findElement(By.xpath("(//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container'])[2]"));
			CimHelper.dragAndDrop(sourceGroup, targetGroup);
			driverDelay(200);
			WebElement source=driver.findElement(By.xpath("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='FDEPARTMENT1 FDEPARTMENT1']"));
			WebElement target=driver.findElement(By.xpath("(//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container'])[2]"));
			CimHelper.dragAndDrop(source, target);
			driverDelay(200);
			doClick(DataMaintenanceMap.getdepthierarchySaveBtn());
			doClick(DataMaintenanceMap.getdepthierarchySaveCloseBtn());
			ExtentReport.logPass("PASS", "test05DeleteGroups_12687");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteGroups_12687", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeleteHierarchy_12687() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdepthierarchyClearFilterBtn());
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getdepthierarchyFilterBtn());
			doFilterCreate(filterNewDept);
			doClick(DataMaintenanceMap.getdepthierarchyDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteHierarchy_12687");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteHierarchy_12687", driver, e);
			fail(e.getMessage());
		}
	}
	public void dragDropTheDeptsToHierarchy(List<String> list) throws Throwable {
		for(String str:list) {
			doClick("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='"+str+"']");
			WebElement source=driver.findElement(By.xpath("//div[contains(@class,'glAccountsGrid ')]//following::div[text()='"+str+"']"));
			WebElement target=driver.findElement(By.xpath("(//div[contains(@id,'accounthierarchyselector')]//div[@class='x-grid-item-container'])[2]"));
			CimHelper.dragAndDrop(source, target);
			driverDelay(800);
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
