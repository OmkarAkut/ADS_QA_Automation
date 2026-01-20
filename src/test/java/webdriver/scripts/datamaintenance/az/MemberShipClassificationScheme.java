package webdriver.scripts.datamaintenance.az;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.AzHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberShipClassificationScheme extends AzHelper{
	static DataMaintenanceMap dmMap;
	final static String aTozMemberShipClassificationScheme = "Membership Classification Schemes";
	public static DialogsMap dialog;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String name="Name"+currentDateTime;
	static String[] memberShipClassificaionFilter= {"Name","Is","Equal To",name};
	static String memberName="Scheme"+name;
	static String orderIndex="3";
	static String updatedOrderIndex="4";
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("MemberShipClassificationScheme", "webdriver.scripts.datamaintenance.az",
				"MemberShipClassificationScheme");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozMemberShipClassificationScheme);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ValidateSaveCreateNewButton() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazNewBtn());
			keyInInputByName("name", name, "Membership Classification Scheme");
			doClick(DataMaintenanceMap.getazSaveCreateNewBtn());
			doClick(DataMaintenanceMap.getCancelCloseButton());
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(memberShipClassificaionFilter);
			assertTextIsDisplayed(name);
			ExtentReport.logPass("PASS", "test01ValidateSaveCreateNewButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateSaveCreateNewButton", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02AddMembershipClassification() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazEditBtn());
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageNewBtn());
			keyInInputByName("memClassName", memberName, "Membership Classification");
			keyInInputByName("orderIndex", orderIndex, "Membership Classification");
			keyInInputByName("description", "Benefit Plan Code","Membership Classification");
			keyInInputByName("value", "BP", "Membership Classification");
			doClickButtons("Membership Classification", "Add");
			String validateCode=driver.findElement(By.xpath("(//div[text()='Membership Classification']//following::table//td/div[text()])[2]")).getText();
			assertEqualsString("[Benefit Plan Code] is equal to 'BP'", validateCode);
			doClick(DataMaintenanceMap.getfieldEditBtn());
			keyInInputByName("value", "BP1", "Membership Classification");
			doClickButtons("Membership Classification", "Update");
			String validateCodeAfterEdit=driver.findElement(By.xpath("(//div[text()='Membership Classification']//following::table//td/div[text()])[2]")).getText();
			assertEqualsString("[Benefit Plan Code] is equal to 'BP1'", validateCodeAfterEdit);
			doClick(DataMaintenanceMap.getfieldRemoveBtn());
			keyInInputByName("description", "Benefit Plan Code","Membership Classification");
			keyInInputByName("value", "BP", "Membership Classification");
			doClickButtons("Membership Classification", "Add");
			doClick(DataMaintenanceMap.getfieldRemoveBtn());
			doClickButtons("Membership Classification", "Save");
			doClickButtons("Membership Classification", "Save & Close");
			assertTextIsDisplayed(memberName);
			assertTextIsDisplayed(orderIndex);
			ExtentReport.logPass("PASS", "test02AddMembershipClassification");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AddMembershipClassification", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03EditMembershipClassification() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageEditBtn());
			keyInInputByName("orderIndex", updatedOrderIndex, "Membership Classification");
			doClickButtons("Membership Classification", "Save");
			doClickButtons("Membership Classification", "Save & Close");
			assertTextIsDisplayed(updatedOrderIndex);
			ExtentReport.logPass("PASS", "test03EditMembershipClassification");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03EditMembershipClassification", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04DeleteMembershipClassificatiion() throws Throwable {
		try {
			CimHelper.checkElements(DataMaintenanceMap.getazInnerPageDeleteBtn());
			doClick(DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test04DeleteMembershipClassificatiion");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04DeleteMembershipClassificatiion", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05DeleteMembershipClassificatiionScheme() throws Throwable {
		try {
			deleteScenario(DataMaintenanceMap.getazDeleteBtn(), DataMaintenanceMap.getwarningDeleteBtn());
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test05DeleteMembershipClassificatiionScheme");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05DeleteMembershipClassificatiionScheme", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
