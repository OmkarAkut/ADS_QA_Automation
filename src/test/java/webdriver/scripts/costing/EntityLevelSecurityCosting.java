package webdriver.scripts.costing;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-5793 **/
public class EntityLevelSecurityCosting extends CalculationHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	final static String aTozPage = "Entities";
	static String costModel = "0-MarinaCostModel";
	static String costCompMaster = "*FZ Small Hierarchy Test";
	static String[] filter = { "Name", "Is", "Equal To", costModel };

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EntityLevelSecurityCosting", "webdriver.scripts.costing",
				"EntityLevelSecurityCosting");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			Login.loginUser("AutomationTesterAdmin");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5793
	@Test
	public void test01VerifyHomePageIsShown_5793() throws Throwable {
		try {
			try {
				if (costing.getIgreeButton().isDisplayed()) {
					doClick(costing.getIgreeButton());
					assertElementIsDisplayedWithXpath("//div[contains(@class,'landingWrapper')]");
				} else {
					assertElementIsDisplayedWithXpath("//div[contains(@class,'landingWrapper')]");

				}
			} catch (Exception e) {
				assertElementIsDisplayedWithXpath("//div[contains(@class,'landingWrapper')]");

			}
			ExtentReport.logPass("PASS", "test01VerifyHomePageIsShown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyHomePageIsShown", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyDataMaintenanceTab_5793() throws Throwable {
		try {
			goToPage("Maintain Data");
			waitForDisplayedSpinnerToEnd();
			selectMaintainDataAtoZ(aTozPage);
			assertElementIsDisplayed(costing.getEntitiesPage());
			if (CostingMap.getEntitiesPageElementList().size() >= 1) {
				assertTrue(printout);
			} else {
				fail();
			}
			ExtentReport.logPass("PASS", "test02VerifyDataMaintenanceTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyDataMaintenanceTab", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Maintain Data");
		}
	}

	@Test
	public void test03VerifyEntityUnderGeneralInformationCost_5793() throws Throwable {
		try {
			goToPage("Costing Models");
			waitForDisplayedSpinnerToEnd();
			doSearchForModel("");
			doClick(modelMap.getModelLibraryButtonFilter());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeItem("Assign Unit Costs");
			waitForMainPageTitle("General Information - Cost");
			doClickTreeItem("General Information - Cost");
			assertElementIsDisplayedWithXpath("//li[text()='150 Marina Medical Center']");
			doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test03VerifyCostingModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyCostingModel", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test04VerifyEntityCostComponentMaster_5793() throws Throwable {
		try {
			doClickTreeItem("Assign Unit Costs");
			waitForMainPageTitle("Cost Component Masters");
			doClickTreeItem("Cost Component Masters");
			tableDoubleClickCellFirstColumn(costCompMaster);
			assertElementIsDisplayedWithXpath("//li[text()='150 Marina Medical Center']");
			doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test03VerifyCostingModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyCostingModel", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Costing Models");
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
