package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;
public class VerifyFilterAscSchemes extends CalculationHelper{
	private static ContractingMap modelMap;
	 private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	static final String ContractModelName = "ADS-1320 Contract Model D";
	static String ascScheme = "ASESC-2995 Commercial ASC Scheme";
	static String filter[]= {"ASC Scheme Name","Is","Equal To",ascScheme};
	/** Regression: Automated test script for ADS-6447*/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("VerifyFilterAscSchemes",
				"webdriver.scripts.contracting",
				"VerifyFilterAscSchemes");
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
	@Test
	public void test01VerifyFilterByAscSchemes() throws Throwable {
		try {
			doSearchForContractModel(ContractModelName);
			tableDoubleClickCellFirstColumn(ContractModelName);
			driverDelay(1200);
			doClickTreeItem("Build Structure Elements");
			driverDelay(300);
			doClickTreeItemWithCheckbox("ASC Schemes");
			doClick(ContractingMap.getASCFilterButton());
			doFilterSetFilterParameters("ASC Scheme Name", "Is", "Equal To", ascScheme);
			doClick(dialog.getFilterDialogButtonAdd());
		    waitForAjaxExtJs();
		    doClick(ContractingMap.getASCFilterEditButton());
		    doFilterSetFilterParameters("ASC Scheme Name", "Is", "Equal To", "Test");
		    doClick(ContractingMap.getASCFilterRemoveButton());
		    Thread.sleep(1000);
		    //Shilpa added below lines for 11.2 on 11.17.2023 becoz if we update xpath assertElementIsDisabled might affect other code
		    if(ContractingMap.getASCFilterAddButton().getAttribute("class").contains("disabled")) {
		    	assertTrue(printout);
		    }
		    else {
		    	fail();
		    }
//		   assertElementIsDisabled(ContractingMap.getASCFilterAddButton(),printout);
		    doFilterCreate(filter);
		    assertElementIsDisplayedWithXpath("//div[text()='"+ascScheme+"']");
		    doClosePageOnLowerBar("ADS-1320 Contract...");
//		    doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test01VerifyFilterByAscSchemes");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyFilterByAscSchemes", driver, e);
			fail(e.getMessage());
		} 
		finally{
			doClosePageOnLowerBar("Model Library");

		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
	
}
