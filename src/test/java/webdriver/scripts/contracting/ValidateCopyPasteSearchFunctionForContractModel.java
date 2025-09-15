package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Support Issues: Automated test script for ADS-14434*/
public class ValidateCopyPasteSearchFunctionForContractModel extends GoHelper{
	private static ContractingMap modelMap;
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String searchString="zero";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModel = searchString + currentDateTime;
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("ValidateCopyPasteSearchFunctionForContractModel", "webdriver.scripts.contracting",
				"ValidateCopyPasteSearchFunctionForContractModel");
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
	public void test01CopyPasteContractModel_14434() throws Throwable {
		try {
			doSearchForContractModel(searchString);
			doClick(modelMap.getContractModelButtonCopy());
			doClick(modelMap.getContractModelButtonPaste());
			waitForElementToBeVisible(modelMap.getContractModelPastePopup());
			keyInInputText(contractModel, ContractingMap.getInputName());
			doClick(modelMap.getContractModelSaveCopy());
			assertTextIsDisplayed(contractModel);
			doClick("//*[text()='" + contractModel + "']");
			doClick(ContractingMap.getContractModelDeleteButton());
			doClick(ContractingMap.getContractModelDeleteButtonInPopUp());
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test01CopyPasteContractModel_14434");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CopyPasteContractModel_14434", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
