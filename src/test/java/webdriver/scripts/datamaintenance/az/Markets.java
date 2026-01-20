package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Markets extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozMarkets = "Markets";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String entityCode="0001 PRIVATE PAY PENDING ";
	static String[] marketsFilter= {"Name","Is","Equal To",name};
	static String updatedName="UpdatedName"+currentDateTime;
	static String[] marketsUpdatedFilter= {"Name","Is","Equal To",updatedName};
	//Issue : ADS-23702
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Markets", "webdriver.scripts.datamaintenance.az",
				"Markets");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozMarkets);
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
			keyInInputByName("name", name, "Market");
			keyInInputByName("code", code, "Market");
			doClickButtons("Market", "Select");
			selectFormItem(entityCode, "");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(marketsFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditMarkets() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			keyInInputByName("name", updatedName, "Market");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			doClick(DataMaintenanceMap.getazClearFilterBtn());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(marketsUpdatedFilter);
			assertTextIsDisplayed(updatedName);
			ExtentReport.logPass("PASS", "test02ValidateEditMarkets");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditMarkets", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03DeleteMarket() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteMarket");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteMarket", driver, e);
			fail(e.getMessage());
		}
	}


	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
