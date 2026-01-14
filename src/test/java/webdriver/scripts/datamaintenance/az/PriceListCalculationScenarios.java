package webdriver.scripts.datamaintenance.az;

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
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceListCalculationScenarios extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozActivityStatisticMaster = "Price List Calculation Scenarios";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String priceCode = currentDateCode.replaceAll("\\W", "");
	static String priceName="Name"+currentDateTime;
	static String deptHierarchy="ASESC-1832 Hospital Hierarchy";
	static String sourcePriceList="200FY02 Southgate Price List FY02";
	static String newPriceList=priceCode+" "+priceName;
	static String validateNewPriceList=priceCode+" "+" "+priceName;
	static String newPriceListDropdown=priceCode+" "+" "+priceName;
	static String[] priceListCalcFilter= {"Name","Is","Equal To",name};
	static String[] priceListFilter= {"Name","Is","Equal To",priceName};
	static String entity="0000 PRIVATE PAY";
	static String priceChange="20.00";
	static String dollar="20";
	static String[] columns= {"1521103 2018 U NONE PUMP RENTAL CLEANING FEE"};
	static String[] careDelivery= {"0000 PRIVATE PAY"};
	static String[] encounter= {"1S1 Office "};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PriceListCalculationScenarios", "webdriver.scripts.datamaintenance.maintaindata",
				"PriceListCalculationScenarios");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozActivityStatisticMaster);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test01ValidateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Price List Calculation Scenario");
			clickCheckboxByName("deptHierId");
			selectDropdown(deptHierarchy, "Price List Calculation Scenario");
			clickCheckboxByName("sourcePriceListId");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getpriceListDrp(), sourcePriceList);
			doClick(DataMaintenanceMap.getpriceItemNewBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02AddPriceList() throws Throwable {
		try {
			keyInInputByName("code", priceCode, "Price List");
			keyInInputText(priceName, driver.findElement(By.xpath("(//input[@name='name'])[2]")));
//			keyInInputByName("name", priceName, "Price List");
			clickCheckboxByName("chargeMasterCode");
			selectDropdown("200 Southgate Charge Master", "Price List");
			doClick(DataMaintenanceMap.getpriceListSaveBtn());
			ExtentReport.logPass("PASS", "test02AddPriceList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddPriceList", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03SelectPriceChange() throws Throwable {
		try {
			clickCheckboxByName("priceListId");
			System.out.println(newPriceListDropdown);
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//div[text()='Price List Calculation Scenario']//following::ul/li[text()='"+newPriceListDropdown+"']/..)")), newPriceList);
			doClick(DataMaintenanceMap.getpriceListNewBtn());
			doClick(DataMaintenanceMap.getpriceChangeDeptGrp());
			selectFormItem("*ALLDEPTS ALLDEPTS", "");
			keyInInputByName("factorChange", priceChange, "Price Change");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("None");
			assertTextIsDisplayed("Percent");
			assertTextIsDisplayed(priceChange);
			
			
			ExtentReport.logPass("PASS", "test03SelectPriceList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SelectPriceList", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04EditPriceChange() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			waitForFormDialog("*ALLDEPTS");
			doClickButton("Chargeable Activity");
			doClick(DataMaintenanceMap.getpriceChangeChargeActivity());
			ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClickButton("Dollar Change");
			keyInInputByName("factorChange", dollar, "Price Change");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("Chargeable Activity");
			assertTextIsDisplayed("Dollar");
			assertTextIsDisplayed(dollar+".00");
			clickCheckboxByName("shareLog");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(priceListCalcFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(deptHierarchy);
			assertElementIsDisplayedWithXpath("//*[text()='"+name+"']//following::div[text()='"+validateNewPriceList+"']");
			assertTextIsDisplayed("Price List");
			doClick(DataMaintenanceMap.getazEditBtn());
			ExtentReport.logPass("PASS", "test04EditPriceItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04EditPriceItem", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeletePriceChange() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04DeletePriceChange");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeletePriceChange", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06DeletePriceListCalcScenario() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeletePriceListCalcScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeletePriceListCalcScenario", driver, e);
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void test07AddPriceListCalcScenario_AvgEncounterCharges() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Price List Calculation Scenario");
			clickCheckboxByName("deptHierId");
			selectDropdown(deptHierarchy, "Price List Calculation Scenario");
			doClickButton("Average Encounter Charges");
			doClickButton("Care Delivery Facilities");
			doClick(DataMaintenanceMap.getcareDeliveryFacilitySelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(careDelivery);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClickButton("Encounter Types");
			doClick(DataMaintenanceMap.getencountertypeSelectBtn());
			ContractModelsHelper.selectMultipleColumnsToDisplay(encounter);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			clickCheckboxByName("useDischargeTimePeriod");
			clickCheckboxByName("priceListId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("((//div[text()='Price List Calculation Scenario']//following::ul/li[text()='"+newPriceListDropdown+"']/..))[2]")), newPriceList);
			
			ExtentReport.logPass("PASS", "test07AddPriceListCalcScenario_AvgEncounterCharges");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07AddPriceListCalcScenario_AvgEncounterCharges", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08EditPriceListCalcScenario_AvgEncounterCharges() throws Throwable {
		try {
			
			doClick(DataMaintenanceMap.getpriceListNewBtn());
			doClick(DataMaintenanceMap.getpriceChangeDeptGrp());
			selectFormItem("*ALLDEPTS ALLDEPTS", "");
			keyInInputByName("factorChange", priceChange, "Price Change");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			assertTextIsDisplayed("*ALLDEPTS");
			assertTextIsDisplayed("None");
			assertTextIsDisplayed("Percent");
			assertTextIsDisplayed(priceChange);
			clickCheckboxByName("shareLog");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(priceListFilter);
			doClick(DataMaintenanceMap.getazEditBtn());
			test05DeletePriceChange();
			test06DeletePriceListCalcScenario();
			ExtentReport.logPass("PASS", "test08EditPriceListCalcScenario_AvgEncounterCharges");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08EditPriceListCalcScenario_AvgEncounterCharges", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09DeletePriceList() throws Throwable {
		try {
			selectMaintainDataAtoZ("Price Lists");
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(priceListFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test09DeletePriceList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeletePriceList", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
