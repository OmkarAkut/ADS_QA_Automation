package webdriver.scripts.contracting;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
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
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityLevelSecurityContracting extends GoHelper{
	private static ContractingMap modelMap;
	private static CostingMap costing;
	private static String entityName="150 Marina Medical Center ";
	static String structureElement="Contract Types";
	static String contractModel="#fz Med IPPS Testing";
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String[] entityList= {"150"};
	/** Regression: Automated test script for ADS-5794 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EntityLevelSecurityContracting",
				"webdriver.scripts.contracting",
				"EntityLevelSecurityContracting");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			costing=BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("RestrictedEntityAndDept1");
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
	public void test01VerifyAddProviderCancelOption() throws Throwable {
		try {
			doClick(modelMap.getNewContractModelButton());
			waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
			assertElementIsDisplayed(ContractingMap.getNewContractModelPopUp());// assert contract model pop up
			doClick(ContractingMap.getContractModelNameInput());
			doClick(ContractingMap.getContractModelAddProviderBtn());
			waitForElementToBeVisible(ContractingMap.getContractModelAddProviderPopup());
			assertElementIsDisplayed(ContractingMap.getContractModelAddProviderPopup());
			doClick(costing.getAddProviderCancelButton());
			doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test01VerifyAddProviderCancelOption");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyAddProviderCancelOption", driver, e);
			fail(e.getMessage());
		}
}
	@Test
	public void test02VerifyDefinitionElementsforContractModel() throws Throwable {
		try {
			doSearchForContractModel(contractModel);
			tableDoubleClickCellFirstColumn(contractModel);
			doClickTreeItemWithCheckbox("General Information - Published Contract");
//			Omkar 29/5/2023 : xpath changes for 11.2
//			doClick("//button/span[text()='Definition Elements']");
//			doClick("//div[contains(@class,'x-tool')]/img[contains(@class, 'tool-expand-bottom')]");
			doClick("//span[text()='Definition Elements']");
			doClick("//div[contains(@class, 'x-title-text x-title-text-default x-title-item')][text()='Providers']/parent::div/following-sibling::div");
			if(!(costing.getProviderEntityList().size()==1)) {
				assertFalse(false);
			}
			ContractModelsHelper.CompareListToArray(costing.getProviderEntityList(), entityList);
			ExtentReport.logPass("PASS", "test02VerifyDefinitionElementsforContractModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyDefinitionElementsforContractModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03VeryThereisOneEntityUnderSelectedColumn() throws Throwable{
			try {
//				Omkar 29/5/2023 : xpath changes for 11.2
//				doClick("//button/span[text()='Add Providers']");
				doClick("//span[text()='Add Providers']");
				waitForAjaxExtJs();
				ContractModelsHelper.assertColumnsToDisplayColumnIsSelected(entityName);
				doClick(costing.getAddProviderCancelButton());
				doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
				ExtentReport.logPass("PASS",
						"test03VeryThereisOneEntityUnderSelectedColumn");

			}
			catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL",
						"test03VeryThereisOneEntityUnderSelectedColumn", driver, e);
				fail(e.getMessage());

			
	}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
