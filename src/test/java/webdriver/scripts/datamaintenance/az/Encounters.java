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
import webdriver.maps.CimMap;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Encounters extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozEncounters = "Encounters";
	public static DialogsMap dialog;
	public static ContractingMap contractMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String consumer="000000-00-00 BOWERMAN ATTY, AINSLEY <Open> - <Open>";
	static String careDeliveryFacility="0000 PRIVATE PAY";
	static String[] activityStatsMasterFilter= {"Name","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String icd10Principal="0020 Typhoid fever";
	static String icd10admitting="0020 Typhoid fever";
	static String[] apgs= {"APG19988 0002"};
	static String deductible="10";
	static String coInsuranceAmount="20";
	static String coPayment="30";
	static String coInsurancePercent="40";
	static String currentContractualAmount="5.12";
	static String upCurrentContractualAmount="6.15";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Encounters", "webdriver.scripts.datamaintenance.az",
				"Encounters");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			contractMap= BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozEncounters);
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
			keyInInputByName("encounterId", code, "Encounter");
			doClick(DataMaintenanceMap.getconsumerSelectBtn());
			driverDelay(10000);
			selectFormItem(consumer, "");
			doClick(DataMaintenanceMap.getencounterCareDeliverySelectBtn());
			waitForDisplayedSpinnerToEnd();
			selectFormItem(careDeliveryFacility, "");
			clickCheckboxByName("encounterTypeCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getencounterTypeDrp(), "1S1 Office");
			clickCheckboxByName("admissionSourceCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getadmissionTypeDrp(), "1SM2 Admin Source 200");
			clickCheckboxByName("dischargeStatusCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getdischargeStatusTypeDrp(), "01 DISCHARGED HOME OR SELF CARE (ROUTINE)");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddDiagnoses() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdiagnosisTab());
			doClick(DataMaintenanceMap.geticd10ProcDiagnosisSelectBtn());
			waitForElementToBeVisible(DataMaintenanceMap.geticd10ProcDiagnosisWindow());
			assertElementIsDisplayed(DataMaintenanceMap.geticd10ProcDiagnosisWindow());
			selectFormItem(icd10Principal, "");
			doClick(DataMaintenanceMap.geticd10ProcAdmitDiagnosisSelectBtn());
			waitForElementToBeVisible(DataMaintenanceMap.geticd10ProcAdmitDiagnosisWindow());
			assertElementIsDisplayed(DataMaintenanceMap.geticd10ProcAdmitDiagnosisWindow());
			doClick("//div[text()='Add ICD10 Admitting Diagnosis']//following::div[text()='"+icd10admitting+"']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			assertElementIsDisplayedWithXpath("(//span[text()='ICD10 Principal Diagnosis']//following::div[text()='"+icd10Principal+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='ICD10 Principal Diagnosis']//following::div[text()='"+icd10admitting+"'])[2]");
			clickCheckboxByName("pOAPrinc10");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//span[text()='ICD10 Principal Diagnosis']//following::ul/li[text()='N - No']/..)[1]")), "Y - Yes");
			clickCheckboxByName("pOAAdmit10");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("(//span[text()='ICD10 Principal Diagnosis']//following::ul/li[text()='N - No']/..)[2]")), "Y - Yes");

			ExtentReport.logPass("PASS", "test02AddDiagnoses");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddDiagnoses", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AddNewICD10Diagnoses() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New ICD10 Secondary Diagnosis");
			doClick(DataMaintenanceMap.geticd10DiagnosesSelectBtn());
			waitForFormDialog("Add ICD10 Diagnosis");
			doDoubleClick("//div[text()='Add ICD10 Diagnosis']//following::div[text()='"+icd10Principal+"']");
			expandPanel("User Defined Fields");
			doClickButtons("New ICD10 Secondary Diagnosis", "Continue");
			assertTextIsDisplayed("0020");
			assertTextIsDisplayed("Typhoid fever");
			collapsePanel("ICD10 Diagnoses");
			
			ExtentReport.logPass("PASS", "test03AddNewICD10Diagnoses");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddNewICD10Diagnoses", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04AddNewICD9Diagnoses() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New ICD9 Secondary Diagnosis");
			doClick(DataMaintenanceMap.geticd9DiagnosesSelectBtn());
			waitForFormDialog("Add ICD9 Diagnosis");
			doDoubleClick("//div[text()='Add ICD9 Diagnosis']//following::div[text()='"+icd10Principal+"']");
			expandPanel("User Defined Fields");
			doClickButtons("New ICD9 Secondary Diagnosis", "Continue");
			assertElementIsDisplayedWithXpath("//h1[text()='ICD9 Secondary Diagnoses']//following::div[text()='0020']");
			assertElementIsDisplayedWithXpath("//h1[text()='ICD9 Secondary Diagnoses']//following::div[text()='Typhoid fever']");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test03AddNewICD9Diagnoses");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AddNewICD9Diagnoses", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05AddProcedures() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getproceduresTab());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Procedure");
			clickCheckboxByName("icd9cmMasterCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='New Procedure']//following::ul/li[text()='1833ICDPROC']/..")), "1833ICDPROC");
//			clickCheckboxByName("hcpcsCodeMasterCode");
//			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='New Procedure']//following::ul/li[text()='1833ICDPROC']/..")), "1833ICDPROC");
			doClick(DataMaintenanceMap.geticdSelectBtn());
			selectFormItem("024I  CODE 024I", "");
			expandPanel("User Defined Fields");
			doClickButtons("New Procedure", "Continue");
			assertTextIsDisplayed("Principal");
			assertTextIsDisplayed("1833ICDPROC");
			assertTextIsDisplayed("024I CODE 024I");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClickButton("Secondary");
			doClickButtons("Procedure", "Continue");
			assertTextIsDisplayed("Secondary");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test05AddProcedures");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AddProcedures", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06AddDRGS() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdrgsTab());
			clickCheckboxByName("msdrg1ClsfSchemeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='MSDRGs']//following::ul/li[text()='2010MSDRG2']/..")), "2010MSDRG3");
			doClick("(//label[text()='DRG Scheme']//following::span[text()='Select'])[1]");
			selectFormItem("0011 liver transplant &/or intestinal transplant", "");
			assertTextIsDisplayed("0011 liver transplant &/or intestinal transplant");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test05AddProcedures");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AddProcedures", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07AddAPCS() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getapcsTab());
			expandPanel("Ambulatory Payment Classifications (APCs)");
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClick(DataMaintenanceMap.getapcNewBtn());
			clickCheckboxByName("apcClassificationSchemeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='New APC']//following::ul/li[text()='APC012008']/..")), "APC012025");
			doClick(DataMaintenanceMap.getapcSelectBtn());
			waitForFormDialog("Add APC");
			selectFormItem("0701 Sr89 strontium", "");
			keyInInputByName("quantity", "10", "APC");
			doClickButtons("APC", "Continue");
			assertTextIsDisplayed("APC012025");
			assertTextIsDisplayed("0701 0701 Sr89 strontium");
			assertTextIsDisplayed("K Nonpass-Through Drugs and Nonimplantable Biologicals");
			assertTextIsDisplayed("10");
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.getapcEditBtn());
			keyInInputByName("quantity", "20", "APC");
			doClickButtons("APC", "Continue");
			assertTextIsDisplayed("APC012025");
			assertTextIsDisplayed("0701 0701 Sr89 strontium");
			assertTextIsDisplayed("K Nonpass-Through Drugs and Nonimplantable Biologicals");
			assertTextIsDisplayed("20");
			collapsePanel("Ambulatory Payment Classifications (APCs)");
			ExtentReport.logPass("PASS", "test05AddProcedures");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05AddProcedures", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08AddEAPGS() throws Throwable {
		try {
			expandPanel("Enhanced Ambulatory Patient Groups (EAPGs)");
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClick(DataMaintenanceMap.geteapgNewBtn());
			clickCheckboxByName("eapgClassSchemeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='New EAPG']//following::ul/li[text()='ASESC2920EAPGSC']/..")), "ASESC2920EAPGSC");
			doClick(DataMaintenanceMap.geteapgSelectBtn());
			waitForFormDialog("Add EAPG");
			selectFormItem("0001 PHOTOCHEMOTHERAPY", "");
			doClick(DataMaintenanceMap.getrevenueCodeEncSelectBtn());
			selectFormItem("UB92  0101  ALL INCL R&B", "");
			expandPanel("Other Fields");
			doClickButtons("EAPG", "Continue");
			assertTextIsDisplayed("ASESC2918EAPG");
			assertTextIsDisplayed("0001 PHOTOCHEMOTHERAPY");
			assertTextIsDisplayed("0101 ALL INCL R&B");
			assertTextIsDisplayed("1");
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.geteapgEditBtn());
			keyInInputByName("quantity", "5", "EAPG");
			doClickButtons("EAPG", "Continue");
			assertTextIsDisplayed("5");
			collapsePanel("Enhanced Ambulatory Patient Groups (EAPGs)");
			ExtentReport.logPass("PASS", "test08AddEAPGS");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08AddEAPGS", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09AddAPGS() throws Throwable {
		try {
			expandPanel("Ambulatory Patient Groups (APGs)");
			scrollToView(DataMaintenanceMap.getapgSelectBtn());
			doClick(DataMaintenanceMap.getapgSelectBtn());
			waitForFormDialog("Add APGs");
			ContractModelsHelper.selectMultipleColumnsToDisplay(apgs);
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			assertTextIsDisplayed("APG19988");
			assertTextIsDisplayed("0002");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test09AddAPGS");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09AddAPGS", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10AddServices() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getservicesTab());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Service");
			clickCheckboxByName("serviceClassSchemeId");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Service']//following::ul/li[text()='Inpatients by DRG 001 to 254']/..")), "Inpatients by DRG 255 to 508");
			doClick(DataMaintenanceMap.getserviceSelectBtn());
			waitForFormDialog("New Service");
			selectFormItem("# Inpatient", "");
			doClickButtons("Service", "Continue");
			assertTextIsDisplayed("Inpatients by DRG 255 to 508");
			assertTextIsDisplayed("# Inpatient");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test10AddServices");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10AddServices", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test11AddFinancialRecords() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getfinancialRecordsTab());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Encounter Financial Record");
			keyInInputByName("financialRecNum", code, "New Encounter Financial Record");
			doClick(DataMaintenanceMap.getcostAccountingSelectBtn());
			waitForFormDialog("Add Cost Accounting Entity");
			selectFormItem("0000 PRIVATE PAY", "");
			//Contract/Financial
			expandPanel("Contract/Financial");
			doClick(DataMaintenanceMap.getsellerOfServicesSelectBtn());
			waitForFormDialog("Add Seller Of Services");
			selectFormItem("0000 PRIVATE PAY", "");
			doClick(DataMaintenanceMap.getprimaryBenefitPlanSelectBtn());
			waitForFormDialog("Add Primary Benefit Plan");
			selectFormItem("573 DPTest (222 QAMASTERS) ", "");
			//Contractual Allowance
			expandPanel("Contractual Allowance");
			keyInInputByName("currentContractualAmount",currentContractualAmount, "New Encounter Financial Record");
			doClick(DataMaintenanceMap.getcontractualBenefitPlanSelectBtn());
			waitForFormDialog("Add Current Allowance Benefit Plan");
			selectFormItem("573 DPTest (222 QAMASTERS) ", "");
			//Billing
			expandPanel("Billing");
			clickCheckboxByName("billTypeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Billing']//following::ul/li[text()='1SM1 Final Bill Type']/..")), "1SM1 Final Bill Type");
			keyInInputByName("billCycleNumber", code, "New Encounter Financial Record");
			//Financial Responsibility
			expandPanel("Financial Responsibility");
			keyInInputByName("deductible",deductible, "New Encounter Financial Record");
			keyInInputByName("coInsuranceAmount", coInsuranceAmount, "New Encounter Financial Record");
			keyInInputByName("coPayment", coPayment, "New Encounter Financial Record");
			keyInInputByName("coInsurancePercent", coInsurancePercent, "New Encounter Financial Record");
			doClickButtons("New Encounter Financial Record", "Continue");
			assertElementIsDisplayedWithXpath("(//span[text()='Cost Accounting Entity']//following::div[text()='0000  PRIVATE PAY'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Seller of Services']//following::div[text()='0000  PRIVATE PAY'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Primary Benefit Plan']//following::div[text()='573 DPTest'])[1]");
			expandPanel("Contractual Allowance");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='573 DPTest'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='"+currentContractualAmount+"'])[1]");
			expandPanel("Billing");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='573 DPTest'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Bill Type']//following::div[text()='1SM1  Final Bill Type'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Bill Cycle Number']//following::div[text()='"+code+"'])[1]");
			expandPanel("Financial Responsibility");
			assertElementIsDisplayedWithXpath("(//span[text()='Deductible']//following::div[text()='"+deductible+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Coinsurance Amount']//following::div[text()='"+coInsuranceAmount+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Copayment']//following::div[text()='"+coPayment+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Coinsurance Percent']//following::div[text()='"+coInsurancePercent+"'])[1]");
			expandPanel("RBRVS RVU Totals");
			
			//Edit Financial Record Number
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			expandPanel("Contractual Allowance");
			keyInInputByName("currentContractualAmount",upCurrentContractualAmount, "New Encounter Financial Record");
			doClickButtons("Edit "+code+"", "Continue");
			expandPanel("Contractual Allowance");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='"+upCurrentContractualAmount+"'])[1]");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test11AddFinancialRecords");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11AddFinancialRecords", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
