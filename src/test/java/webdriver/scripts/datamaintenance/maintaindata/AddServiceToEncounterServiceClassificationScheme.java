package webdriver.scripts.datamaintenance.maintaindata;

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
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-12915 ,ADS-12916*/
public class AddServiceToEncounterServiceClassificationScheme extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEncounterServiceClassificationScheme = "Encounter Service Classification Schemes";
	static String encounterScheme="ADS-12915 Encounter Scheme";
	static String[] filter = { "Name", "Is", "Equal To", encounterScheme };
	String[] services= {" ASESC-2455 Service name with blank front","# CI PC Service"};
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String encounterSchemeNew="EncScheme "+currentDateTime;
	static String[] filterNew = { "Name", "Is", "Equal To", encounterSchemeNew };
	public static DialogsMap dialog;
	private static CostingMap costing;
	private static CimMap cimMap;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("AddServiceToEncounterServiceClassificationScheme", "webdriver.scripts.datamaintenance.maintaindata",
				"AddServiceToEncounterServiceClassificationScheme");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			cimMap = BuildMap.getInstance(driver, CimMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEncounterServiceClassificationScheme);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void doFilterCreateIsOneOf(String[] filterParameters) throws InterruptedException {
		System.out.println(filterParameters[0]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(), dialog.getFilterDialogDropdownField(),
				filterParameters[0]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),
				dialog.getFilterDialogDropdownOperator(), filterParameters[1]);
		doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),
				dialog.getFilterDialogDropdownCondition(), filterParameters[2]);
	}
	@Test
	public void test01OpenForEditEncounterScheme_12915() throws Throwable {
		try {
			doClick(dmMap.getencounterButtonFilter());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(encounterScheme);
			ExtentReport.logPass("PASS", "test01OpenForEditEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenForEditEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02DragDropServiceToEncounterScheme_12915() throws Throwable {
		try {
			
			doClick("//div[text()='Service Model']//following::span[text()='Filter']");
			for(int i=0;i<2;i++) {
				String[] doFilterCreateIsOneOf = { "Name", "Is", "One Of" };
				doFilterCreateIsOneOf(doFilterCreateIsOneOf);
				doClick(dialog.getFilterDialogFormFieldValueOneOf());
				dialog.getFilterDialogFormFieldValueOneOf().sendKeys(services[i]);
				doClick(costing.getRvuContainerAddValueButton());
			}
			addFilter();
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			for(int i=0;i<services.length;i++) {
				System.out.println(services[i]);
				WebElement source=driver.findElement(By.xpath("//div[@class='x-grid-item-container']//div[text()='"+services[i]+"']"));
				WebElement target=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsHierarchyGrid ')]//div[@class='x-grid-item-container'])"));
				CimHelper.dragAndDrop(source, target);
				assertElementIsDisplayedWithXpath("(//div[contains(@class,'glAccountsHierarchyGrid ')]//div[@class='x-grid-item-container'])//following::span[text()='"+services[i]+"']");
				
			}
		doClick(DataMaintenanceMap.getencounterSaveBtn());
		driverDelay();
		ExtentReport.logPass("PASS", "test02DragDropServiceToEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02DragDropServiceToEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02RemoveServiceFromEncounterScheme_12915() throws Throwable {
		try {
			for(int i=0;i<services.length;i++) {
				WebElement source=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsHierarchyGrid ')]//div[@class='x-grid-item-container'])//following::span[text()='"+services[i]+"']"));
				WebElement target=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')])[1]"));
				CimHelper.dragAndDrop(source, target);
				assertElementIsDisplayedWithXpath("//div[@class='x-grid-item-container']//div[text()='"+services[i]+"']");
			}
			doClick(DataMaintenanceMap.getencounterSaveCloseBtn());
			ExtentReport.logPass("PASS", "test01AddServiceToEncounterScheme_12915");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddServiceToEncounterScheme_12915", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03CreateNewEncounterScheme_12916() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getencounterButtonClearFilter());
			doClick(DataMaintenanceMap.getencounterButtonNewBtn());
			keyInInputText(encounterSchemeNew, DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.getencounterPopSelectBtn());
			waitForElementPresence("//div[contains(@id,'dynamicwindow')][text()='Add Population']");
			doClick(DataMaintenanceMap.getencounterAddPopl());
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			test02DragDropServiceToEncounterScheme_12915();
			doClick(costing.shareLog());
			doClick(DataMaintenanceMap.getencounterSaveBtn());
			doClick(DataMaintenanceMap.getencounterSaveCloseBtn());
			ExtentReport.logPass("PASS", "test03CreateNewEncounterScheme_12916");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03CreateNewEncounterScheme_12916", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteNewEncounterScheme_12916() throws Throwable {
		try {
			doClick(dmMap.getencounterButtonFilter());
			doFilterCreate(filterNew);
			doClick(DataMaintenanceMap.getencounterButtonDeleteBtn());
			doClick(DataMaintenanceMap.getmessageboxDeleteBtn());
			ExtentReport.logPass("PASS", "test03CreateNewEncounterScheme_12916");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03CreateNewEncounterScheme_12916", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
