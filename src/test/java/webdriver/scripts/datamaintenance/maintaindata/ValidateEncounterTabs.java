package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateEncounterTabs extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	private static String encounterId="502057159E";
	final static String aTozPageEncounters = "Encounters";
	static String[] tabs= {"Totals","Diagnoses","Procedures","DRGs","APCs & APGs","Services","Financial Records","Charges","Payments","UDFs & UDRs"};
	/** Support Issues: Automated test script for ADS-12929**/
	static String expTotalActualCharges="143,631.28";
	static String expTotalActualCashPayments="69,846.42";
	static String expTotalActualAdjustments="5,928.77";
	static String balanceAmount="67,856.09";
	static String expTotalNonCoveredCharges="553.00";
	static String expPayment="142,853.51";
	static  String expDirectFixedCost="3,827.54";
	static String expDirectVariableCost="18,565.65";
	static String expIndirectFixedCost="7,606.32";
	static String expIndirecVariableCost="0.00";
	static String expTotalCosts="29,999.51";
	static String balanceStatus="Debit";
	static String expIcdProc="0014 INJECTION OXAZOLIDINONE2";
	static String exphcpcsProc="4556 Test CPT";
	static String expUdf="PRIV Private Room";
	static String eapgScheme="ASESC2918EAPG";
	static String eapg="0002 SUPERFICIAL NEEDLE BIOPSY AND ASPIRATION";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateEncounterTabs", "webdriver.scripts.datamaintenance.maintaindata",
				"ValidateEncounterTabs");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageEncounters);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ValidateSaveInAllEncounterTabs_12929() throws Throwable {
		try {
			doSearchForModel(encounterId);
			tableDoubleClickCellFirstColumn(encounterId);
			for(String tab:tabs) {
				doClick("//span[text()='"+tab+"']/../../..");
				waitForDisplayedSpinnerToEnd();
				doClick(DataMaintenanceMap.getsaveButton());
			}
			ExtentReport.logPass("PASS", "test01ValidateSaveForContractBatches_12620");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveForContractBatches_12620", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateTotalTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.gettotalsTabs());
			assertElementTextContains(DataMaintenanceMap.gettotalActualCharges(), expTotalActualCharges, printout);
			assertElementTextContains(DataMaintenanceMap.gettotalActualCashPay(), expTotalActualCashPayments, printout);
			assertElementTextContains(DataMaintenanceMap.gettotalActualAdjust(), expTotalActualAdjustments, printout);
			assertElementTextContains(DataMaintenanceMap.getbalanceStatus(), balanceStatus, printout);
			assertElementTextContains(DataMaintenanceMap.getbalanceAmount(), balanceAmount, printout);
			assertElementTextContains(DataMaintenanceMap.gettotalnonCoveredCharges(), expTotalNonCoveredCharges, printout);
			assertElementTextContains(DataMaintenanceMap.getexpPayment(), expPayment, printout);
			assertElementTextContains(DataMaintenanceMap.getdirectFixedCost(), expDirectFixedCost, printout);
			assertElementTextContains(DataMaintenanceMap.getdirectVarialeCost(), expDirectVariableCost, printout);
			assertElementTextContains(DataMaintenanceMap.getindirectFixedCost(), expIndirectFixedCost, printout);
			assertElementTextContains(DataMaintenanceMap.getindirectVariableCost(), expIndirecVariableCost, printout);
			assertElementTextContains(DataMaintenanceMap.gettotalCosts(), expTotalCosts, printout);
			ExtentReport.logPass("PASS", "test02ValidateTotalTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateTotalTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateProcedureTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getproceduresTab());
			expIcdProc=DataMaintenanceMap.geticdProcedure().getText();
			exphcpcsProc=DataMaintenanceMap.gethcpcsProcedure().getText();
			expUdf=DataMaintenanceMap.getudf().getText();
			doClick(DataMaintenanceMap.getprocEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getprocEditWindow());
			assertElementTextContains(DataMaintenanceMap.geticdProcedureInput(), expIcdProc, printout);
			assertElementTextContains(DataMaintenanceMap.gethcpcsProcedureInput(), exphcpcsProc, printout);
			assertElementTextContains(DataMaintenanceMap.getudfInput(), expUdf, printout);
			doClick(DataMaintenanceMap.getcancelCloseBtn());
			ExtentReport.logPass("PASS", "test03ValidateProcedureTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateProcedureTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateDiagnosisTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getdiagnosisTab());
			doClick(DataMaintenanceMap.geticd10ProcDiagnosisSelectBtn());
			waitForElementToBeVisible(DataMaintenanceMap.geticd10ProcDiagnosisWindow());
			assertElementIsDisplayed(DataMaintenanceMap.geticd10ProcDiagnosisWindow());
			doClick(DataMaintenanceMap.getdialogFormCancelCloseBtn());
			doClick(DataMaintenanceMap.geticd10ProcAdmitDiagnosisSelectBtn());
			waitForElementToBeVisible(DataMaintenanceMap.geticd10ProcAdmitDiagnosisWindow());
			assertElementIsDisplayed(DataMaintenanceMap.geticd10ProcAdmitDiagnosisWindow());
			doClick(DataMaintenanceMap.getdialogFormCancelCloseBtn());
			ExtentReport.logPass("PASS", "test04ValidateDiagnosisTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidateDiagnosisTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05ValidateEAPGTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getapcapgTab());
			navigateOpenNewSection(DataMaintenanceMap.geteapgSection());
			doClick(DataMaintenanceMap.geteapgEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.geteapgWindow());
			assertElementIsDisplayed(DataMaintenanceMap.geteapgWindow());
			assertElementValueAttribute(DataMaintenanceMap.geteapgSchemeField(), eapgScheme, printout);
			assertElementTextContains(DataMaintenanceMap.geteapgSchemeText(),eapg,printout);
			doClick(DataMaintenanceMap.getcancelCloseBtn());
			ExtentReport.logPass("PASS", "test05ValidateEAPGTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ValidateEAPGTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06ValidateAccordianHeadersExpandCollapseUnderChargesTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getchargesTab());
			doClick(DataMaintenanceMap.getchargesTabEditBtn());
			navigateOpenNewSection(DataMaintenanceMap.getgeneralSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getgeneralAccordianAccordian(), "Collapse panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getpractSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getpractitionersAccordian(), "Collapse panel", "aria-label",printout);
			navigateOpenNewSection(DataMaintenanceMap.getmappingsSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getmappingsAccordian(), "Collapse panel", "aria-label",printout);
			navigateOpenNewSection(DataMaintenanceMap.getrbrvsSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getrbrvsAccordian(), "Collapse panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getchargeudfSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getchargeUDfAccordianAccordian(), "Collapse panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getchargeudfSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getchargeUDfAccordianAccordian(), "Expand panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getrbrvsSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getrbrvsAccordian(), "Expand panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getmappingsSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getmappingsAccordian(), "Expand panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getpractSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getpractitionersAccordian(), "Expand panel", "aria-label",printout);
		    navigateOpenNewSection(DataMaintenanceMap.getgeneralSection());
		    assertThatAttributeValueIsEqual(DataMaintenanceMap.getgeneralAccordianAccordian(), "Expand panel", "aria-label",printout);
			doClick(DataMaintenanceMap.getcancelCloseBtn());
		    ExtentReport.logPass("PASS", "test06ValidateAccordianHeadersExpandCollapseUnderChargesTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ValidateAccordianHeadersExpandCollapseUnderChargesTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06ValidateEditChargesTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getchargesTabEditBtn());
			assertElementIsDisplayed(DataMaintenanceMap.getgeneralAccordianAccordian());
			doClick(DataMaintenanceMap.getcancelCloseBtn());
			 ExtentReport.logPass("PASS", "test06ValidateEditChargesTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ValidateEditChargesTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07ValidateNewChargesTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getchargesTabNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getnewChargeDeptSelectBtn());
			doClick(DataMaintenanceMap.getnewChargeDeptSelectBtn());
			doClick("(//div[text()='Add Department']//following::div[contains(@id,'dynamicGrid')]//following::td/div)[3]");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			doClick(DataMaintenanceMap.getnewChargeCodeSelectBtn());
			doClick("(//div[text()='Add Charge Code']//following::div[contains(@id,'dynamicselect')]//following::td/div)[2]");
			doClick(DataMaintenanceMap.getapplyBtnInPopUp());
			keyInInputText("10", driver.findElement(By.name("quantity")));
			try {
				if(DataMaintenanceMap.getactualChargeContinue().getAttribute("class").contains("disabled")){
					fail();
				}
			} catch (Exception e) {
				assertTrue(printout);
				System.out.println("Continue Button is enabled");
			}
			
			doClick(DataMaintenanceMap.getcancelCloseBtn());
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			ExtentReport.logPass("PASS", "test06ValidateEditChargesTab_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ValidateEditChargesTab_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08ValidateDiagnosisTabSelectBtn_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getchargesTabNewBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getnewChargeDeptSelectBtn());
		    navigateOpenNewSection(DataMaintenanceMap.getgeneralSection());
		    navigateOpenNewSection(DataMaintenanceMap.getdiagnosesSection());
			for(int i=1;i<=16;i++) {
				doClick("((//span[text()='ICD10 Diagnosis 1']//following::span[text()='Select']))["+i+"]");
				waitForPresenceOfElement("(//input[@name='carrierfield']//following::table//div)[2]");
				doClick("(//input[@name='carrierfield']//following::table//div)[2]");
				doClick(DataMaintenanceMap.getapplyBtnInPopUp());
				
			}
			ExtentReport.logPass("PASS", "test08ValidateDiagnosisTabSelectBtn_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ValidateDiagnosisTabSelectBtn_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09ValidateFinancialRecordsEFR_Contractual_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getfinancialRecordsTab());
			doClick(DataMaintenanceMap.getfinancialRecordsEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getfinancialRecordsEFR());
			if(DataMaintenanceMap.getfinancialRecordsEFR().getAttribute("readonly").contains("readonly")) {
				assertTrue(printout);
			}else {fail();}
			navigateOpenNewSection(DataMaintenanceMap.getcontractAllowanceSection());
			assertElementIsDisplayed(DataMaintenanceMap.getcontractualSelectBtn());
			doClick(DataMaintenanceMap.getcontractualSelectBtn());
			assertTextIsDisplayed("Add Current Allowance Benefit Plan");
			doClick(DataMaintenanceMap.getdialogFormCancelCloseBtn());
			navigateOpenNewSection(DataMaintenanceMap.getbillingSectionSection());
			doClick(DataMaintenanceMap.getbillTypeComboBox());
			assertElementIsDisplayedWithXpath("//input[@name='billTypeCode']//following::ul[contains(@id,'dynamiccombo')]");
			doClick(DataMaintenanceMap.getcancelCloseBtn());
			ExtentReport.logPass("PASS", "test09ValidateFinancialRecordsEFR_Contractual_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ValidateFinancialRecordsEFR_Contractual_12929", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10ValidatePaymentsTab_12929() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getpatmentsTab());
			doClick(DataMaintenanceMap.getpaymentsNewBtn());
			assertTextIsDisplayed("New Payment");
			doClick(DataMaintenanceMap.getpayorEntityCode());
			driverDelay();
			doDropdownSelectUsingOptionTextWithelement(
					driver.findElement(By.xpath("(//input[@name='payorEntityCode']//following::ul[contains(@id,'dynamiccombo')]/li)[1]")),
					"0000  PRIVATE PAY");
			keyInInputText("20",DataMaintenanceMap.getpaymentAmount());
			doClick(DataMaintenanceMap.getpayeeEntityCode());
			driverDelay();
			doDropdownSelectUsingOptionTextWithelement(
					driver.findElement(By.xpath("(//input[@name='payeeEntityCode']//following::ul[contains(@id,'dynamiccombo')]/li)[1]")),
					"0000  PRIVATE PAY");
			doClick(DataMaintenanceMap.gettransTypeCode());
			driverDelay();
			doDropdownSelectUsingOptionTextWithelement(
					driver.findElement(By.xpath("(//input[@name='transTypeCode']//following::ul[contains(@id,'dynamiccombo')]/li)[1]")),
					"100  FGr");
			doClick(DataMaintenanceMap.getdynamicWinContinueBtn());
			assertTextIsDisplayed("100  FGr");
			doClick("//*[text()='100  FGr']");
			doClick(DataMaintenanceMap.getpaymentsDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getencounterCancelCloseBtn());
			doClick(DataMaintenanceMap.getpsychWarningCancelCloseBtn());
			ExtentReport.logPass("PASS", "test09ValidateFinancialRecordsEFR_Contractual_12929");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ValidateFinancialRecordsEFR_Contractual_12929", driver, e);
			fail(e.getMessage());
		}
	}
}
