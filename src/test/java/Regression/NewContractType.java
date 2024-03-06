package Regression;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class NewContractType extends CalculationHelper{
	private static ContractingMap modelMap;
	static final String ContractModelName = "ADS-1320 Contract Model D";
	static String structureElement="Contract Types";
	static String currentDateTime = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String contractCode = currentDateTime.replaceAll("\\W", "");
	static String filter[]= {"Code","Is","Equal To",contractCode};
	/** Regression: Automated test script for ADS-6446 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("NewContractType",
				"webdriver.scripts.contracting",
				"NewContractType");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6446
	@Test
	public void test01CreateNewContractType_6446() throws Throwable {
		try {
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			doClickTreeItem("Build Structure Elements");
			driverDelay(300);
			doClickTreeItemWithCheckbox("Contract Types");
			driverDelay(300);
			doClick(ContractingMap.getContractTypeNewButton());
			waitForElementToBeVisible(ContractingMap.getMedicareCode());
			ContractModelsHelper.keyInValues(ContractingMap.getMedicareCode(), contractCode);
			doClick(modelMap.getNewFolderNameSave());
			doClick(ContractingMap.getContractTypeFilterButton());
			driverDelay(100);
			doFilterCreate(filter);
			assertElementIsDisplayedWithXpath("//div[text()='"+contractCode+"']");
			 doClick("//span[text()='ADS-1320 Contract...']//following::span[@class='x-tab-close-btn']");
			ExtentReport.logPass("PASS", "test01CreateNewContractType");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractType", driver, e);
			fail(e.getMessage());
		} 
		finally{
			doClosePageOnLowerBar("Contract Models");

		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
