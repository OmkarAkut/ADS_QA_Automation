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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GLAccountHierarchies extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozGLAccountHierarchies = "GL Account Hierarchies";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] deptHierarchy= {"Hierarchy Name","Is","Equal To",name};
	static String glAcctMaster="MARINA Marina Master";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("GLAccountHierarchies", "webdriver.scripts.datamaintenance.maintaindata",
				"GLAccountHierarchies");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozGLAccountHierarchies);
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
			keyInInputByName("name", name, "GL Account Hierarchy");
			clickCheckboxByName("masterCode");
			doDropdownSelectUsingOptionTextOnly(DataMaintenanceMap.getglAcctMasterDrp(), glAcctMaster);
			driverDelay();
			CimHelper.dragAndDrop(driver.findElement(By.xpath("(//label[contains(text(),'GL Accounts / GL Account Groups')]//following::div//table//td/div)[1]")), driver.findElement(By.xpath("(//label[contains(text(),'Hierarchy ')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div)")));
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditDeptHierarchy() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(deptHierarchy);
			doClick(DataMaintenanceMap.getazEditBtn());
			driverDelay();
			CimHelper.dragAndDrop(driver.findElement(By.xpath("(//label[contains(text(),'GL Accounts / GL Account Groups')]//following::div//table//td/div)[2]")), driver.findElement(By.xpath("(//label[contains(text(),'Hierarchy ')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div)[2]")));
			assertElementIsDisplayedWithXpath("(//label[contains(text(),'Hierarchy ')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div)[3]");
			doClickButton("Undo");
			assertElementIsNotDisplayed("(//label[contains(text(),'Hierarchy ')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div)[3]");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			ExtentReport.logPass("PASS", "test02ValidateEditDeptHierarchy");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditDeptHierarchy", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteDeptHierarchy() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getwarningDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test03DeleteDeptHierarchy");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteDeptHierarchy", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
