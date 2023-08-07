package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.DoHelper;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;

//Omkar 7/8/2023 : Unable to scroll to created folder. See comment below in the code

public class CreatingaNewContractingFolder extends GoHelper {

	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractFolderName = "Folder" + currentDateTime;
	static String renameFolderName="Test";
	/** Regression: Automated test script for ADS-6410,ADS-6411 */

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreatingaNewContractingFolder",
				"webdriver.scripts.contracting", "CreatingaNewContractingFolder");
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
/**Test - UI Validation [Contracting] �Creating a New Contracting Folder�.**/
	@Test
	public void test01CreateNewContractFolder() throws Throwable {
		try {
			doClick(modelMap.getNewContractFolderBtn());
			waitForElementToBeVisible(modelMap.getNewFolderPopUp());
			doClick(modelMap.getNewFolderNameInput());
			modelMap.getNewFolderNameInput().sendKeys(contractFolderName);
			driverDelay(500);
			doClick(ContractingMap.getNewFolderNameSave());
			waitForAjaxExtJs();
			waitForSpinnerToEnd();
			doClick(modelMap.getContractingTreeExpand());
			driverDelay(500);
			DoHelper.scrollToView("//div[text()='" + contractFolderName + "']");  //unable to scroll to this folder in 11.2 
			assertTextIsDisplayed(contractFolderName);
			doClick("//div[text()='" + contractFolderName + "']");
			doClick(modelMap.getDeleteContractFolderBtn());
			waitForElementToBeVisible(modelMap.getContractModelDeleteButtonInPopUp());
			doClick(modelMap.getContractModelDeleteButtonInPopUp());
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test01CreateNewContractFolder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateNewContractFolder", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
