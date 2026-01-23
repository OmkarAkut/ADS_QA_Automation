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
	static String currentDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="NAME"+currentDateTime;
	static String consumer="000000-00-00 BOWERMAN ATTY, AINSLEY <Open> - <Open>";
	static String careDeliveryFacility="0000 PRIVATE PAY";
	static String[] encounterFilter= {"Encounter ID","Is","Equal To",name};
	static String entity="0000 PRIVATE PAY";
	static String icd10Principal="0020 Typhoid fever";
	static String icd10admitting="0020 Typhoid fever";
	static String[] apgs= {"APG19988 0002"};
	static String deductible="26.25";
	static String coInsuranceAmount="27.15";
	static String coPayment="28.10";
	static String coInsurancePercent="29.18";
	static String currentContractualAmount="5.12";
	static String upCurrentContractualAmount="6.15";
	static String apcQuantity="120";
	static String upApcQuantity="130";
	static String eapgQuantity="140";
	static String upeapgQuantity="150";
	static String billCycleNumber;
	static String chargesQty="59";
	static String paymentAmt="25.89";
	static String upPaymentAmt="87.67";
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
			keyInInputByName("encounterId", name, "Encounter");
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
			keyInInputByName("quantity", apcQuantity, "APC");
			doClickButtons("APC", "Continue");
			assertTextIsDisplayed("APC012025");
			assertTextIsDisplayed("0701 0701 Sr89 strontium");
			assertTextIsDisplayed("K Nonpass-Through Drugs and Nonimplantable Biologicals");
			assertTextIsDisplayed(apcQuantity);
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.getapcEditBtn());
			keyInInputByName("quantity", upApcQuantity, "APC");
			doClickButtons("APC", "Continue");
			assertTextIsDisplayed("APC012025");
			assertTextIsDisplayed("0701 0701 Sr89 strontium");
			assertTextIsDisplayed("K Nonpass-Through Drugs and Nonimplantable Biologicals");
			assertTextIsDisplayed(upApcQuantity);
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
			keyInInputByName("quantity", eapgQuantity, "New EAPG");
			doClickButtons("EAPG", "Continue");
			assertTextIsDisplayed("ASESC2920EAPGSC");
			assertTextIsDisplayed("0001 PHOTOCHEMOTHERAPY");
			assertTextIsDisplayed("0101 ALL INCL R&B");
			assertTextIsDisplayed(eapgQuantity);
//			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			doClick(DataMaintenanceMap.geteapgEditBtn());
			driverDelay(200);
			keyInInputByName("quantity",upeapgQuantity, "EAPG");
			doClickButtons("EAPG", "Continue");
			assertElementIsDisplayedWithXpath("//div[text()='ASESC2920EAPGSC']//following::div[text()='"+upeapgQuantity+"']");
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
			assertElementIsDisplayedWithXpath("//div[text()='Ambulatory Patient Groups (APGs)']//following::div[text()='0002']");
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
//			selectFormItem("0000 PRIVATE PAY", "");
			doClick("//div[text()='Add Cost Accounting Entity']//following::div[text()='0000 PRIVATE PAY']");;
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			ExtentReport.logPass("PASS", "test11AddFinancialRecords");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11AddFinancialRecords", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test12AddDetailsUnderContractFinancial() throws Throwable {
		try {
			//Contract/Financial
			expandPanel("Contract/Financial");
			doClick(DataMaintenanceMap.getsellerOfServicesSelectBtn());
			waitForFormDialog("Add Seller Of Services");
//			selectFormItem("0000 PRIVATE PAY", "");
			doClick("//div[text()='Add Seller Of Services']//following::div[text()='0000  PRIVATE PAY']");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getprimaryBenefitPlanSelectBtn());
			waitForFormDialog("Add Primary Benefit Plan");
			selectFormItem("573 DPTest (222 QAMASTERS) ", "");
			ExtentReport.logPass("PASS", "test12AddDetailsUnderContractFinancial");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12AddDetailsUnderContractFinancial", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test13AddDetailsUnderContractualAllowance() throws Throwable {
		try {
			//Contractual Allowance
			expandPanel("Contractual Allowance");
			keyInInputByName("currentContractualAmount",currentContractualAmount, "New Encounter Financial Record");
			doClick(DataMaintenanceMap.getcontractualBenefitPlanSelectBtn());
			waitForFormDialog("Add Current Allowance Benefit Plan");
			selectFormItem("573 DPTest (222 QAMASTERS) ", "");
			ExtentReport.logPass("PASS", "test12AddDetailsUnderContractFinancial");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test12AddDetailsUnderContractFinancial", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test14AddDetailsUnderBilling() throws Throwable {
		try {
			//Billing
			expandPanel("Billing");
			clickCheckboxByName("billTypeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Billing']//following::ul/li[text()='1SM1 Final Bill Type']/..")), "1SM1 Final Bill Type");
			 billCycleNumber=generate3digitRandomNumber();
			keyInInputByName("billCycleNumber", billCycleNumber, "New Encounter Financial Record");
			ExtentReport.logPass("PASS", "test14AddDetailsUnderBilling");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test14AddDetailsUnderBilling", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test15AddDetailsUnderFinancialResponsibility() throws Throwable {
		try {
			//Financial Responsibility
			expandPanel("Financial Responsibility");
			keyInInputByName("deductible",deductible, "New Encounter Financial Record");
			keyInInputByName("coInsuranceAmount", coInsuranceAmount, "New Encounter Financial Record");
			keyInInputByName("coPayment", coPayment, "New Encounter Financial Record");
			keyInInputByName("coInsurancePercent", coInsurancePercent, "New Encounter Financial Record");
			doClickButtons("New Encounter Financial Record", "Continue");
			ExtentReport.logPass("PASS", "test15AddDetailsUnderFinancialResponsibility");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test15AddDetailsUnderFinancialResponsibility", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test16ValidateFinancialRecordDetails() throws Throwable {
		try {
			assertElementIsDisplayedWithXpath("(//span[text()='Cost Accounting Entity']//following::div[text()='0000  PRIVATE PAY'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Seller of Services']//following::div[text()='0000  PRIVATE PAY'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Primary Benefit Plan']//following::div[text()='573 DPTest'])[1]");
			expandPanel("Contractual Allowance");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='573 DPTest'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='"+currentContractualAmount+"'])[1]");
			expandPanel("Billing");
			assertElementIsDisplayedWithXpath("(//span[text()='Bill Type']//following::div[text()='1SM1  Final Bill Type'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Bill Cycle Number']//following::div[text()='"+billCycleNumber+"'])[1]");
			expandPanel("Financial Responsibility");
			assertElementIsDisplayedWithXpath("(//span[text()='Deductible']//following::div[text()='"+deductible+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Coinsurance Amount']//following::div[text()='"+coInsuranceAmount+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Copayment']//following::div[text()='"+coPayment+"'])[1]");
			assertElementIsDisplayedWithXpath("(//span[text()='Coinsurance Percent']//following::div[text()='"+coInsurancePercent+"'])[1]");
			
			ExtentReport.logPass("PASS", "test16ValidateFinancialRecordDetails");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test16ValidateFinancialRecordDetails", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test17EditFinancialRecordDetails() throws Throwable {
		try {

			//Edit Financial Record Number
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			expandPanel("Contractual Allowance");
			keyInInputByName("currentContractualAmount",upCurrentContractualAmount, "Edit "+code+"");
			doClickButtons("Edit "+code+"", "Continue");
			expandPanel("Contractual Allowance");
			assertElementIsDisplayedWithXpath("(//span[text()='Current Allowance Amount']//following::div[text()='"+upCurrentContractualAmount+"'])[1]");
			doClick(DataMaintenanceMap.getazSaveBtn());
			expandPanel("RBRVS RVU Totals");
			ExtentReport.logPass("PASS", "test17EditFinancialRecordDetails");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test17EditFinancialRecordDetails", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test18AddCharges() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getchargesTab());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Actual Charge");
			doClick(DataMaintenanceMap.getchargesDeptSelectBtn());
			waitForFormDialog("Add Department");
			selectFormItem("0007  President CEO", "");
			doClick(DataMaintenanceMap.getchargesCodeSelectBtn());
			waitForFormDialog("Add Charge Code");
			selectFormItem("0007  D23101  ASESC2310 CODE1  NONE  U", "");
			keyInInputByName("quantity", chargesQty, "New Actual Charge");
			expandPanel("Practitioners");
			expandPanel("Mappings");
			expandPanel("Diagnoses");
			expandPanel("RBRVS RVUs");
			doClickButtons("New Actual Charge", "Continue");
			assertTextIsDisplayed("0007  President CEO");
			assertTextIsDisplayed("D23101  ASESC2310 CODE1");
			assertElementIsDisplayedWithXpath("(//div[text()='"+chargesQty+"'])[2]");
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test18AddCharges");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test18AddCharges", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test19AddPayments() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getpatmentsTab());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Payment");
			keyInInputByName("paymentAmount", paymentAmt, "New Payment");
			clickCheckboxByName("transTypeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='New Payment']//following::ul/li[text()='<None>']/..")), "1SM2 CENTRAL STATES HLTH & LIFE");
			doClickButtons("New Payment", "Continue");
			doClick(DataMaintenanceMap.getazSaveBtn());
			assertElementIsDisplayedWithXpath("//div[text()='1SM2  CENTRAL STATES HLTH & LIFE']");
			assertTextIsDisplayed(paymentAmt);
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("paymentAmount", upPaymentAmt, "General");
			doClickButton("Continue");
			assertTextIsDisplayed(upPaymentAmt);
			doClick(DataMaintenanceMap.getazSaveBtn());
			ExtentReport.logPass("PASS", "test19AddPayments");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test19AddPayments", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test20AddPractitioners() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getpractitionersTab());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			waitForFormDialog("New Other Practitioner Role");
			clickCheckboxByName("roleTypeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Practitioner Role']//following::ul/li[text()='Consulting Physician 1']/..")), "Consulting Physician 2");
			doClick(DataMaintenanceMap.getpractSelectBtn());
			waitForFormDialog("Add Practitioner");
			selectFormItem("00001  Smith, John", "");
			doClickButtons("New Other Practitioner Role", "Continue");
			assertTextIsDisplayed("Consulting Physician 2");
			assertTextIsDisplayed("00001 Smith, John");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			clickCheckboxByName("roleTypeCode");
			doDropdownSelectUsingOptionTextOnly(driver.findElement(By.xpath("//div[text()='Practitioner Role']//following::ul/li[text()='Consulting Physician 1']/..")), "Consulting Physician 3");
			doClickButton("Continue");
			assertTextIsDisplayed("Consulting Physician 3");
			doClick(DataMaintenanceMap.getudfudrTab());
			assertTextIsDisplayed("User-Defined Fields and Relations - Encounter");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(encounterFilter);
			assertTextIsDisplayed(name);
			doClick(DataMaintenanceMap.getazEditBtn());
			ExtentReport.logPass("PASS", "test20AddPractitioners");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test20AddPractitioners", driver, e);
			fail(e.getMessage());
		}
	}
	public void deleteDataFromTab(String tabName) throws Throwable {
		doClick("//span[text()='"+tabName+"']/../../..");
		CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
		doClick(DataMaintenanceMap.getwarningDeleteBtn());
		assertTextIsDisplayed("There is no data available to display.");
	}
	@Test
	public void test21DeleteDiagnoses() throws Throwable {
		try {
			deleteDataFromTab("Diagnoses");
			deleteDataFromTab("Procedures");
			deleteDataFromTab("Services");
			deleteDataFromTab("Services");
			deleteDataFromTab("Payments");
			ExtentReport.logPass("PASS", "test21DeletePractitioners");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test21DeletePractitioners", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test22DeleteFinancialRecords() throws Throwable {
		try {
			deleteDataFromTab("Financial Records");
			doClick(DataMaintenanceMap.getchargesTab());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test21DeletePractitioners");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test21DeletePractitioners", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test23DeleteAPC() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getapcapgTab());
			expandPanel("Ambulatory Payment Classifications (APCs)");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsDisplayedWithXpath("(//div[text()='Ambulatory Payment Classifications (APCs)']//following::*[text()='There is no data available to display.'])[1]");
			collapsePanel("Ambulatory Payment Classifications (APCs)");
			ExtentReport.logPass("PASS", "test23DeleteAPC");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test23DeleteAPC", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test24DeleteEAPG() throws Throwable {
		try {
			expandPanel("Enhanced Ambulatory Patient Groups (EAPGs)");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertElementIsDisplayedWithXpath("(//div[text()='Enhanced Ambulatory Patient Groups (EAPGs)']//following::*[text()='There is no data available to display.'])[1]");
			collapsePanel("Enhanced Ambulatory Patient Groups (EAPGs)");
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test24DeleteEAPG");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test24DeleteEAPG", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test25DeleteEncounter() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			ExtentReport.logPass("PASS", "test24DeleteEAPG");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test24DeleteEAPG", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
