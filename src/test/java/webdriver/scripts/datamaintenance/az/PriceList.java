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
public class PriceList extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozPriceList = "Price Lists";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String currentDateCode = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String code = currentDateCode.replaceAll("\\W", "");
	static String name="Name"+currentDateTime;
	static String chargeMaster="350 Island Park Charge Master";
	static String[] chargeMasterFilter= {"Name","Is","Equal To",name};
	static String chargeableActivity="1111  9660002  U  NONE  PRE-ADMIT";
	static String charges[]=chargeableActivity.split("  ");
	static String price="5.15";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("PriceList", "webdriver.scripts.datamaintenance.maintaindata",
				"PriceList");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPriceList);
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
			addNewScenario(code,name,"Charge Master",DataMaintenanceMap.getinputCode(),
					DataMaintenanceMap.getaddName(),chargeMaster,
					DataMaintenanceMap.getchargeMasterPriceListDropdown(),
					DataMaintenanceMap.getchargeMasterMasterDropdownList(),"Save & Create New");
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(chargeMasterFilter);
			assertTextIsDisplayed(name);
			assertTextIsDisplayed(code);
			ExtentReport.logPass("PASS", "test01ValidateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateEditPriceList() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			doClickButtons("New Price Item", "Select");
			driverDelay();
			selectFormItem(chargeableActivity, "");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCreateNewBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageCancelCloseBtn());
			for(String assertText:charges) {
				System.out.println(assertText);
				assertTextIsDisplayed(assertText);
			}
			assertTextIsDisplayed("0.00");
			ExtentReport.logPass("PASS", "test02ValidateEditPriceList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateEditPriceList", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateEditPriceItem() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("price", price, "Price Item");
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageSaveCloseBtn());
			assertTextIsDisplayed(price);
			ExtentReport.logPass("PASS", "test03ValidateEditPriceItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateEditPriceItem", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeletePriceItem() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			doClick(DataMaintenanceMap.getazSaveBtn());
			doClick(DataMaintenanceMap.getazSaveCloseBtn());
			ExtentReport.logPass("PASS", "test04DeletePriceItem");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeletePriceItem", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeletePriceList() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeletePriceList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeletePriceList", driver, e);
			fail(e.getMessage());
		}
	}
	
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
