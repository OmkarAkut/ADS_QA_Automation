package webdriver.scripts.datamaintenance.maintaindata;
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
public class ChargeItemServiceClassificationSchemes extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozChargeItemServiceClassificationSchemes = "Charge Item Service Classification Schemes";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String[] chargeScheme= {"Name","Is","Equal To",name};

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ChargeItemServiceClassificationSchemes", "webdriver.scripts.datamaintenance.maintaindata",
				"ChargeItemServiceClassificationSchemes");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozChargeItemServiceClassificationSchemes);
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
			keyInInputByName("name", name, "Charge Item Service Classification Scheme");
			doClickButtons("Charge Item Service Classification Scheme", "Select");
			selectFormItem("# ASESC-2471", "");
			CimHelper.dragAndDrop(driver.findElement(By.xpath("(//label[contains(text(),'Services')]//following::div[1]//td/div)[1]")), driver.findElement(By.xpath("(//label[contains(text(),'Service Model')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div)")));
			doClick("(//label[text()='Share Log in Selected Shared Location']//preceding-sibling::*[1]/input)");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Create New']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(chargeScheme);
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getmessageboxDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSaveCloseChargeItem() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Charge Item Service Classification Scheme");
			doClickButtons("Charge Item Service Classification Scheme", "Select");
			selectFormItem("# ASESC-2471", "");
			CimHelper.dragAndDrop(driver.findElement(By.xpath("(//label[contains(text(),'Services')]//following::div[1]//td/div)[1]")), driver.findElement(By.xpath("(//label[contains(text(),'Service Model')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div)")));
			doClick("(//label[text()='Share Log in Selected Shared Location']//preceding-sibling::*[1]/input)");
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save']")));
			doClick(DataMaintenanceMap.getCancelCloseButton());
			ExtentReport.logPass("PASS", "test02ValidateSaveCloseChargeItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSaveCloseChargeItem", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditChargeItem() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(chargeScheme);
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.dragAndDrop(driver.findElement(By.xpath("(//label[contains(text(),'Services')]//following::div[1]//td/div)[2]")), driver.findElement(By.xpath("((//label[contains(text(),'Service Model')]//following::div[contains(@class,'glAccountsHierarchyGrid ')]//td/div))[2]")));
			CimHelper.checkElements(driver.findElements(By.xpath("//span[text()='Save & Close']")));
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(),DataMaintenanceMap.getmessageboxDeleteBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			ExtentReport.logPass("PASS", "test03ValidateEditChargeItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditChargeItem", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
