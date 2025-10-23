package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
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
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Customer Issues: Automated test script for ADS-23379*/
public class ValidationOfZipCodeGroups extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static CostingMap costing;
	private static DialogsMap dialog;
	final static String aTozZipCodePage= "Zip Code Groups";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String zipCodeGroup="Zip code "+currentDateTime;
	static String zipCodeGroup1="Zip code1 "+currentDateTime;
	static String zipCodeGroup2="Zip code2"+currentDateTime;
	static String[] zipCodeGroups= {zipCodeGroup1,zipCodeGroup2};
	static String[] filterNew = { "Name", "Is", "Equal To", zipCodeGroup };
	static String[] filterContains = { "Name", "Is", "Contains", zipCodeGroup };
	static String[] filterStartsWith = { "Name", "Is", "Starts With", zipCodeGroup };
	static String[] filterEndsWith = { "Name", "Is", "Ends With", zipCodeGroup };
	static String[] zipCodes = {"01081","01247","01581","01602"};
	static String[] addZipCodes = {"01081","01247","01581","01602","02126"};
	static String[] zipCodeRangeLowZipCode= {"01081","01247","01581"};
	static String[] zipCodeRangeHighZipCode= {"01081","01247","01602"};
	static List<String> zipCodeList=new ArrayList<>();
	static List<String> zipLowCodeList=new ArrayList<>();
	static List<String> zipHighCodeList=new ArrayList<>();
	static String enterZipcode;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidationOfZipCodeGroups", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidationOfZipCodeGroups");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozZipCodePage);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void compareZipCodes(String[] codes,List<String> codeList) {
		if(codeList.equals(Arrays.asList(codes))) {assertTrue(printout);codeList.clear();}
		else {codeList.clear();fail();}
		
	}
	
	@Test
	public void test01AddZipCodeGroup_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputText(zipCodeGroup, DataMaintenanceMap.getaddName());
			doClick(DataMaintenanceMap.getselectZipCodeBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getselectZipCodeWindow());
			for(String code:zipCodes) {
				doClick("//li[text()='"+code+"']");
				doClick(DataMaintenanceMap.getSelectZipCodeSelectBtn());
			}
			doClick(DataMaintenanceMap.getselectZipCodeApplyBtn());
			ExtentReport.logPass("PASS", "test01AddZipCodeGroup_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AddZipCodeGroup_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02AssertZipCodes_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getselectShowCodesBtn());
			driverDelay(300);
			for(WebElement code:DataMaintenanceMap.getlowZipCodeGrid()) {
				zipCodeList.add(code.getText());
				
			}
			compareZipCodes(zipCodes,zipCodeList);
			ExtentReport.logPass("PASS", "test01AssertZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AssertZipCodesRanges_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getselectShowRangesBtn());
			driverDelay(300);
			for(WebElement code:DataMaintenanceMap.gethighZipCodeGrid()) {
				zipHighCodeList.add(code.getText());
				
			}
			compareZipCodes(zipCodeRangeHighZipCode,zipHighCodeList);
			for(WebElement code:DataMaintenanceMap.getlowZipCodeGrid()) {
				zipLowCodeList.add(code.getText());
				
			}
			compareZipCodes(zipCodeRangeLowZipCode,zipHighCodeList);
			ExtentReport.logPass("PASS", "test01AssertZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04SaveZipCodes_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getselectZipCodeSaveBtn());
			doClick(DataMaintenanceMap.getselectZipCodeSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterNew);
			assertTextIsDisplayed(zipCodeGroup);
			ExtentReport.logPass("PASS", "test01AssertZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteZipCodes_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test01AssertZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test06SaveCreateNewZipCodes_ADS_23379() throws Throwable {
		try {
			test01AddZipCodeGroup_ADS_23379();
			doClick(DataMaintenanceMap.getselectZipCodeSaveNewBtn());
			doClick(DataMaintenanceMap.getselectZipCodeCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterNew);
			assertTextIsDisplayed(zipCodeGroup);
			ExtentReport.logPass("PASS", "test06SaveCreateNewZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06SaveCreateNewZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07EditZipCodes_ADS_23379() throws Throwable {
		try {
			tableDoubleClickCellFirstColumn(zipCodeGroup);
			driver.findElement(By.name("formulaString")).clear();
			enterZipcode=String.join(",", addZipCodes);
			driver.findElement(By.name("formulaString")).sendKeys(enterZipcode);
			doClick(DataMaintenanceMap.getapplyZipCodeBtn());
			DataMaintenanceMap.getselectShowRangesBtn().click();
			driverDelay(300);
			for(WebElement code:DataMaintenanceMap.gethighZipCodeGrid()) {
				zipCodeList.add(code.getText());
				
			}
			compareZipCodes(addZipCodes,zipCodeList);
			DataMaintenanceMap.getselectShowCodesBtn().click();
			driverDelay(300);
			for(WebElement code:DataMaintenanceMap.getlowZipCodeGrid()) {
				zipCodeList.add(code.getText());
				
			}
			compareZipCodes(addZipCodes,zipCodeList);
			
			ExtentReport.logPass("PASS", "test07EditZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07EditZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08ValidateEditedZipCodes_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getselectZipCodeBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getselectZipCodeWindow());
			assertTextIsDisplayed("5  Item(s) Selected");
			for(WebElement selectZipCode:DataMaintenanceMap.getselectedZipCodes()) {
				zipCodeList.add(selectZipCode.getText());
			}
			compareZipCodes(addZipCodes,zipCodeList);
			doClick(DataMaintenanceMap.getselectZipCodeCancelBtn());
			doClick(DataMaintenanceMap.getselectZipCodeSaveCloseBtn());
			ExtentReport.logPass("PASS", "test07EditZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07EditZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void test09ValidateStartsWithFilterZipCodes_ADS_23379() throws Throwable {
		try {
			test01AddZipCodeGroup_ADS_23379();
			doClick(DataMaintenanceMap.getselectZipCodeSaveNewBtn());
			doClick(DataMaintenanceMap.getselectZipCodeCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterStartsWith);
			assertTextIsDisplayed(zipCodeGroup);
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test09ValidateStartsWithFilterZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ValidateStartsWithFilterZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10ValidateEndsWithWithFilterZipCodes_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterEndsWith);
			assertTextIsDisplayed(zipCodeGroup);
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test10ValidateEndsWithWithFilterZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ValidateEndsWithWithFilterZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11ValidateContainsFilterZipCodes_ADS_23379() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filterContains);
			assertTextIsDisplayed(zipCodeGroup);
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test11ValidateContainsFilterZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11ValidateContainsFilterZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12ValidateIsOneOfFilterZipCodes_ADS_23379() throws Throwable {
		try {
			for(String codeGroup:zipCodeGroups) {
				doClick(DataMaintenanceMap.getazNewBtn());
				keyInInputText(codeGroup, DataMaintenanceMap.getaddName());
				doClick(DataMaintenanceMap.getselectZipCodeBtn());
				waitForElementToBeVisible(DataMaintenanceMap.getselectZipCodeWindow());
				for(String code:zipCodes) {
					doClick("//li[text()='"+code+"']");
					doClick(DataMaintenanceMap.getSelectZipCodeSelectBtn());
				}
				doClick(DataMaintenanceMap.getselectZipCodeApplyBtn());
				doClick(DataMaintenanceMap.getselectZipCodeSaveNewBtn());
				doClick(DataMaintenanceMap.getselectZipCodeCancelCloseBtn());
			}
			doClick(DataMaintenanceMap.getazFilterBtn());
			driverDelay(300);
			for(String filter:zipCodeGroups) {
				doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(), dialog.getFilterDialogDropdownField(),
						"Name");
				doDropdownSelectUsingOptionTextServices(dialog.getFilterNameOperator(),
						dialog.getFilterDialogDropdownOperator(), "Is");
				doDropdownSelectUsingOptionTextServices(dialog.getFilterNameCondition(),
						dialog.getFilterDialogDropdownCondition(), "One Of");
				doClick(dialog.getFilterDialogFormFieldValueOneOf());
				dialog.getFilterDialogFormFieldValueOneOf().sendKeys(filter);
				doClick(costing.getRvuContainerAddValueButton());
			}
			
			doClick(dialog.getFilterDialogButtonAdd());
			waitForAjaxExtJs();
			driverDelay(300);
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();
			assertTextIsDisplayed(zipCodeGroup1);
			assertTextIsDisplayed(zipCodeGroup2);
			ExtentReport.logPass("PASS", "test07EditZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07EditZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test13DeleteZipCodes_ADS_23379() throws Throwable {
		try {
			for(WebElement code:DataMaintenanceMap.getzipCodeGroups()) {
				code.click();
				doClick(DataMaintenanceMap.getazDeleteBtn());
				doClick(DataMaintenanceMap.getwarningDeleteBtn());
			}
			
			ExtentReport.logPass("PASS", "test13DeleteZipCodes_ADS_23379");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test13DeleteZipCodes_ADS_23379", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
