package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ModelContractPatientFinancialResponsibility extends CalculationHelper{
	private static ContractingMap contractingMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String patientFinRespName = "PatientTerm " + currentDateTime;
	static String patientFinResponsibilityOrder = "1";
	static String PatientFinResponsibilityTerm="Patient Financial Responsibility";
	static final String ContractModelName = "ADS-1320 Contract Model D";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ModelContractPatientFinancialResponsibility", "webdriver.scripts.contracting",
				"ModelContractPatientFinancialResponsibility");
		try {
			contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			assertThatString(contractingMap.getContractModelHeader(), "Contracting Model Library", printout);
			doClick(CostingMap.getContractingName);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	/** Regression: Automated test script for ADS-6467 */
	//ADS-6467
	@Test
	public void test01VerifyNewPatientFinancialResponsibility_6467() throws Throwable {
		try {
			doClickTreeItem("Contracting");
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			assertTextIsDisplayed("Unpublished Contract Task List");
			assertTextIsDisplayed("Build Structure Elements");
			assertTextIsDisplayed("Model Contract");
			assertTextIsDisplayed("Publish Contract");
			assertTextIsDisplayed("Export Contract");
			doClickTreeItem("Model Contract");
			driverDelay(200);
			assertTextIsDisplayed("General Information - Unpublished Contract");
			assertTextIsDisplayed("Define Payment Terms");
			assertElementIsDisplayed(ContractingMap.getAssertCalculateOption());
			doClickTreeItem("Define Payment Terms");
			driverDelay(200);
			doClickTreeItem("Patient Financial Responsibility");
			waitForPageTitle("Patient Financial Responsibility");
//			doClick(driver.findElement(By.xpath("(//span[text()='"+PatientFinResponsibilityTerm+"'])[2]//following::span[text()='New']//parent::button")));
			//Shilpa update xpath 11.2 on 11.15.2023
			doClick(driver.findElement(By.xpath("(//span[text()='"+PatientFinResponsibilityTerm+"'])[2]//following::span[text()='New']//preceding-sibling::span/../..//parent::div")));
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), patientFinRespName);
			ContractModelsHelper.keyInValues(ContractingMap.getOrderInut(), patientFinResponsibilityOrder);
			doClick(contractingMap.getContractModelSaveCopy());
//			try {
//				doClick(contractingMap.getContractModelContinue());
//			}catch(Exception e ) {
//				
//			}
			
//			
//			assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@id,'patientfinancialresponsibilitymaingrid')]//following::div[text()='"+patientFinRespName+"']")));
			//Shilpa update xpath 11.2 on 11.15.2023
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id,'amasterlist')]//following::div[text()='"+patientFinRespName+"']")));

			doClick(contractingMap.getContractModelDeletebtnPatient());
			waitForElementToBeVisible(contractingMap.getContractModelDeleteButtonInPopUp());
			doClick(ContractingMap.getDeleteButtonMesaageBox());
//			doClick("//span[text()='ADS-1320 Contract...']//following::span[@class='x-tab-close-btn']");
			doClick("//span[@class='x-tab-close-btn'][contains(text(),'removable')]");//Shilpa xpath update for 11.2 on 10.31.2024
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test01VerifyNewPatientFinancialResponsibility");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyNewPatientFinancialResponsibility", driver, e);
			fail(e.getMessage());
		}
		
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
