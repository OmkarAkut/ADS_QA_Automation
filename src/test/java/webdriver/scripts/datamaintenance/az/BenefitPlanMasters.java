package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BenefitPlanMasters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozBenefitPlanMasters = "Benefit Plan Masters";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String benifitPlanMaster="Scheme "+currentDateTime;
	static String benifitPlanMasterCode=currentDateTime;
	static String benifitPlanCode="SchemeCode"+code;
	static String[] benefitPlanMasterFilter= {"Name","Is","Equal To",benifitPlanMaster};
	static String benifitPlanList;
	static String productCode="A1"+currentDateCode.replaceAll("\\W", "");;
	static String productName="A1"+currentDateTime;
	static String productLine;
	static String businessLine;
	static String businessCode="B1"+currentDateCode.replaceAll("\\W", "");;
	static String businessName="B1"+currentDateTime;
	static String updatedBenifitName;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("BenefitPlanMasters", "webdriver.scripts.datamaintenance.az",
				"BenefitPlanMasters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozBenefitPlanMasters);
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
			keyInInputByName("name", benifitPlanMaster,"Benefit Plan Master");
			keyInInputByName("code", benifitPlanMasterCode,"Benefit Plan Master");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(benefitPlanMasterFilter);
			assertTextIsDisplayed(benifitPlanMaster);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", benifitPlanMaster,"Benefit Plan Master");
			keyInInputByName("code", benifitPlanMasterCode,"Benefit Plan Master");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(benefitPlanMasterFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test02ValidateSaveButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateSaveCloseButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", benifitPlanMaster,"Benefit Plan Master");
			keyInInputByName("code", benifitPlanMasterCode,"Benefit Plan Master");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(benefitPlanMasterFilter);
			doClick(DataMaintenanceMap.getazPageEditBtn());
			ExtentReport.logPass("PASS", "test03ValidateSaveCloseButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateSaveCloseButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddASCGroups() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			benifitPlanList="Group"+benifitPlanMaster;
			keyInInputByName("name", benifitPlanList, "New Benefit Plan");
			keyInInputByName("code", benifitPlanCode, "New Benefit Plan");
			doClickButtons("New Benefit Plan", "Select");
			selectFormItem("100 Pacific Hospital", "");
			ExtentReport.logPass("PASS", "test04AddASCGroups");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AddASCGroups", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05AddNewProductLine() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getproductLineNewBtn());
			productLine=productCode+" "+productName;
			keyInInputByName("code", productCode, "Product Line");
			keyInInputByName("name", productName, "Product Line");
			doClickButtons("New Benefit Plan", "Save & Create New");
			doClickButtons("New Benefit Plan", "Cancel & Close");
			doClick(DataMaintenanceMap.getProductLine());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getproductLineList(), productLine);
			ExtentReport.logPass("PASS", "test05AddNewProductLine");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AddNewProductLine", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddNewBusinessLine() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getBusinessLineNewBtn());
			businessLine=businessCode+" "+businessName;
			keyInInputByName("code", businessCode, "Line of Business");
			keyInInputByName("name", businessName, "Line of Business");
			doClickButtons("New Benefit Plan", "Save & Create New");
			doClickButtons("New Benefit Plan", "Cancel & Close");
			doClick(DataMaintenanceMap.getlineOfBusiness());
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getlineOfBusinessList(), businessLine);
			doClickButtons("New Benefit Plan", "Save & Create New");
			doClickButtons("New Benefit Plan", "Cancel & Close");
			assertTextIsDisplayed(benifitPlanList);
			ExtentReport.logPass("PASS", "test06AddNewBusinessLine");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06AddNewBusinessLine", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07EditBenefitPlanList() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			updatedBenifitName="Updated"+businessName;
			keyInInputByName("name", updatedBenifitName, benifitPlanList);  
			doClickButtons(benifitPlanList, "Save");
			doClickButtons(updatedBenifitName, "Save & Close");
			assertTextIsDisplayed(updatedBenifitName);
			
			ExtentReport.logPass("PASS", "test07EditBenefitPlanList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07EditBenefitPlanList", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08AddEffectivePeriod() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.geteffectivePeriodNewBtn());
			String startDate=driver.findElement(By.name("startDate")).getAttribute("value");
			String endDate=driver.findElement(By.name("toDate")).getAttribute("value");
			String startDateAfterParse=LocalDate.parse(startDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")).format(DateTimeFormatter.ofPattern("MMM/dd/yyyy"));
			String endDateAfterParse=LocalDate.parse(endDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")).format(DateTimeFormatter.ofPattern("MMM/dd/yyyy"));
			
			keyInInputByName("doctorVisitCopay", "10", "New Benefit Plan Period");
			keyInInputByName("erCopay", "20", "New Benefit Plan Period");
			keyInInputByName("hospitalStayCopay", "30", "New Benefit Plan Period");
			keyInInputByName("deDuctible", "40", "New Benefit Plan Period");
			keyInInputByName("coInsurance", "50", "New Benefit Plan Period");
			doClick(DataMaintenanceMap.geteffectivePeriodSaveCreateNewBtn());
			doClick(DataMaintenanceMap.geteffectivePeriodCancelCloseBtn());
			assertTextIsDisplayed("10.00");
			assertTextIsDisplayed("20.00");
			assertTextIsDisplayed("30.00");
			assertTextIsDisplayed("40.00");
			assertTextIsDisplayed("50.00");
			assertTextIsDisplayed(startDateAfterParse+" "+"-"+" "+endDateAfterParse);
			ExtentReport.logPass("PASS", "test08AddEffectivePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08AddEffectivePeriod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08EditEffectivePeriod() throws Throwable {
		try {
			doClick(DataMaintenanceMap.geteffectivePeriodEditBtn());
			keyInInputByName("doctorVisitCopay", "60", "Edit Benefit Plan Period");
			keyInInputByName("erCopay", "70", "Edit Benefit Plan Period");
			keyInInputByName("hospitalStayCopay", "80", "Edit Benefit Plan Period");
			keyInInputByName("deDuctible", "90", "Edit Benefit Plan Period");
			keyInInputByName("coInsurance", "95", "Edit Benefit Plan Period");
			doClick(DataMaintenanceMap.geteffectivePeriodSaveBtn());
			doClick(DataMaintenanceMap.geteffectivePeriodSaveCloseBtn());
			assertTextIsDisplayed("60.00");
			assertTextIsDisplayed("70.00");
			assertTextIsDisplayed("80.00");
			assertTextIsDisplayed("90.00");
			assertTextIsDisplayed("95.00");
			ExtentReport.logPass("PASS", "test08EditEffectivePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08EditEffectivePeriod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09DeleteEffectivePeriod() throws Throwable {
		try {
			doClick(DataMaintenanceMap.geteffectivePeriodDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClickButtons(updatedBenifitName, "Save & Close");

			ExtentReport.logPass("PASS", "test09DeleteEffectivePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteEffectivePeriod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10DeleteBenifitPlanPeriod() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test09DeleteEffectivePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteEffectivePeriod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11DeleteBenifitPlanMaster() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test09DeleteEffectivePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DeleteEffectivePeriod", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12DeleteProductLine() throws Throwable {
		try {
			selectMaintainDataAtoZ("Product Lines");
			String[] productLineFilter = { "Name", "Is", "Equal To", productName };
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(productLineFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test11DeleteProductLine");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11DeleteProductLine", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test13DeleteLineOfBusiness() throws Throwable {
		try {
			selectMaintainDataAtoZ("Lines Of Business");
			String[] productLineFilter= {"Name","Is","Equal To",businessName};
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(productLineFilter);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test11DeleteProductLine");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11DeleteProductLine", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
