package Regression;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;


public class ValidateBenifitPlansInfoForMultipleUnpublishedContracts extends CalculationHelper {

	private static ContractingMap modelMap;
	private static String ContractModelA = "CM ADS1327 Contract A";
	private static String ContractModelB = "CM ADS1327 Contract B";
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();

	/** Regression: Automated test script for ADS-6080, some steps need to be updated here  */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateBenifitPlansInfoForMultipleUnpublishedContracts",
				"webdriver.scripts.contracting", "ValidateBenifitPlansInfoForMultipleUnpublishedContracts");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");

			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			doSearchForContractModel(ContractModelA);
			tableDoubleClickCellFirstColumn(ContractModelA);
			driverDelay(300);
			doClickTreeItem("Model Contract");
			waitForMainPageTitle("General Information - Unpublished Contract");
			doClickTreeItemWithCheckbox("General Information - Unpublished Contract");
//			Omkar 26/6/2023 : xpath changes for 11.2
//			ContractModelsHelper.toggleBetweenTheDockBar("//span[text()='Model Library']//parent::button");
			ContractModelsHelper.toggleBetweenTheDockBar("//span[text()='Model Library']");
			doSearchForContractModel(ContractModelB);
			tableDoubleClickCellFirstColumn(ContractModelB);
			driverDelay(300);
//			Omkar 26/6/2023 : xpath changes for 11.2
//			doClick("(//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='Model Contract'])[2]");
			doClick("(//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Model Contract'])[2]");
			driverDelay(300);
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("(//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='General Information - Unpublished Contract'])[2]");
			doClick("(//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='General Information - Unpublished Contract'])[2]");
			driverDelay(300);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01MoveToDefinitionElementsTabInMultipleContracts() throws Throwable {
		try {
//			Omkar 7/7/2023 : xpath changes for 11.2
//			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[1]");
			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[1]");
			doClick(ContractingMap.getDefinitionElementC1());			
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("(//span[contains(text(),'Benefit Plans')]//following::img)[1]");
//			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[2]");
			doClick("(//div[contains(text(),'Benefit Plans')]//following::div)[1]");
			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[2]");
			doClick(ContractingMap.getDefinitionElementC2());
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("(//span[contains(text(),'Benefit Plans')]//following::img[1])[2]");
			doClick("(//div[contains(text(),'Benefit Plans')]//following::div[1])[2]");
			ExtentReport.logPass("PASS", "test02MoveToDefinitionElementsTabInMultipleContracts");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02MoveToDefinitionElementsTabInMultipleContracts", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02RemoveBenefitPlanInMultipleContracts() throws Throwable {
		try {
//			Omkar 7/7/2023 : xpath changes for 11.2
//			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[1]");
			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[1]");
			ContractModelsHelper.scrollToView(ContractingMap.getDefinitionElementAddBtn());
			doClick(ContractingMap.getDefinitionElementAddBtn());
			//Need to add benifit plans here
			waitForMainPageTitle("Add Benefit Plans");
			doClick(modelMap.getRemoveItem());
//			Omkar 7/7/2023 : xpath changes for 11.2
//			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[2]");
			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[2]");
			ContractModelsHelper.scrollToView(ContractingMap.getDefinitionElementAddBtnC2());
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("((//span[contains(@id,'contracttaskfolder') and (contains(text(),'" + ContractModelB
//					+ "'))])//following::div[contains(@class,'calcSceneCls')]//following::span[text()='Add'])[1]");
			doClick("((//div[contains(@id,'contracttaskfolder') and (contains(text(),'" + ContractModelB
					+ "'))])//following::div[contains(@class,'calcSceneCls')]//following::span[text()='Add'])[1]");
			waitForMainPageTitle("Add Benefit Plans");
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("((//div[contains(@id,'dynamicwindow')])//div[contains(@class,'multiSelPneCls')]//following::span[text()='Remove']//parent::button)[2]");
			doClick("((//div[contains(@id,'dynamicwindow')])//div[contains(@class,'multiSelPneCls')]//following::span[text()='Remove'])[2]");
			ExtentReport.logPass("PASS", "test03RemoveBenefitPlanInMultipleContracts");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03RemoveBenefitPlanInMultipleContracts", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ApplyBenefitPlanInMultipleContracts() throws Throwable {
//		Omkar 7/7/2023 : xpath changes for 11.2
//		ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[1]");
				ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[1]");
		try {
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("//span[text()='Apply']//parent::button");
			doClick("//span[text()='Apply']");
//			Omkar 7/7/2023 : xpath changes for 11.2
//			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[2]");
//			doClick("//span[text()='Apply']//parent::button");
			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[2]");
			doClick("//span[text()='Apply']");
//			doClick("(//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close'])[2]");
			
			//Shilpa update xpath for 11.2 on 11.16.2023
			doClick("(//div[contains(@class,'footer')]//span[text()='Cancel & Close'])[2]");
//			doClick("//div[@class='x-window-bodyWrap']//span[text()='Cancel & Close']"); // warning window
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("(//*[contains(@id,'tab') and contains(text(),'CM ADS1327...')]/../../following-sibling::a)[2]");
			doClick("(//span[contains(text(),'CM ADS1327...')]/parent::span/parent::span/following-sibling::span)[2]"); // close the tab
//			Omkar 7/7/2023 : xpath changes for 11.2
//			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...']//parent::button)[1]");
			ContractModelsHelper.toggleBetweenTheDockBar("(//span[text()='CM ADS1327...'])[1]");
//			doClick("(//div[contains(@class,'x-toolbar x-docked x-toolbar-footer')]//span[text()='Cancel & Close'])[1]");
			//Shilpa update xpath for 11.2 on 11.16.2023
			doClick("(//div[contains(@class,'footer')]//span[text()='Cancel & Close'])[1]");
//			doClick("//div[@class='x-window-bodyWrap']//span[text()='Cancel & Close']"); // warning window
//			Omkar 7/7/2023 : xpath changes for 11.2
//			doClick("(//*[contains(@id,'tab') and contains(text(),'CM ADS1327...')]/../../following-sibling::a)[1]");
			doClick("(//span[contains(text(),'CM ADS1327...')]/parent::span/parent::span/following-sibling::span)[1]"); // close the tab
//			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());  not needed
			doClosePageOnLowerBar("Contract Models");
			ExtentReport.logPass("PASS", "test04ApplyBenefitPlanInMultipleContracts");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ApplyBenefitPlanInMultipleContracts", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
