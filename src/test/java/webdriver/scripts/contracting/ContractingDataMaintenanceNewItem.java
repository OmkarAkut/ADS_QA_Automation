package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;


public class ContractingDataMaintenanceNewItem extends CalculationHelper {
	
	private static String aTozPage="Time Periods";
	private static ContractingMap modelMap;
	static DataMaintenanceMap dmMap;
	static String currentDateTime = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String timePeriod = currentDateTime;
	static String timePeriodMonth="Sep";
	static String timePeriodYear="2023";
	static String[] filter= {"Name","Is","Equal To",timePeriod};
	/**Regression test ADS-6443,ADS-6445*/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		try {
			ExtentReport.reportCreate("ContractingDataMaintenanceNewItem",
					"webdriver.scripts.contracting",
					"ContractingDataMaintenanceNewItem");
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForDisplayedSpinnerToEnd();
			doClick(ContractingMap.getContractDataMaintenance());
			selectMaintainDataAtoZ(aTozPage);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test01ContractingDataMaintenanceNewItem() throws Throwable {
		try {
			doClick(ContractingMap.getContractDataMaintenanceNewButton());
			waitUntilElementIsVisible(ContractingMap.getNewTimePeriodPopUp());
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), timePeriod);
			doDropdownSelectUsingOptionText(ContractingMap.getMonthdropdown(), timePeriodMonth);
			doDropdownSelectUsingOptionText(ContractingMap.getYeardropdown(), timePeriodYear);
			doClick(modelMap.getNewFolderNameSave());
			driverDelay(20);
			doClick(ContractingMap.getContractDataMaintenanceFilterButton());
			driverDelay(20);
			doFilterCreate(filter);
			assertElementIsDisplayedWithXpath("//div[text()='"+timePeriod+"']");
			//ADS-6443
			doClick(ContractingMap.getContractDataMaintenanceEditButton());
			assertThatAttributeValue(ContractingMap.getInputName(), timePeriod, printout);
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			assertElementIsDisplayedWithXpath("//div[text()='"+timePeriod+"']");
			ExtentReport.logPass("PASS", "test01ContractingDataMaintenanceNewItem");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ContractingDataMaintenanceNewItem", driver, e);
			fail(e.getMessage());
		} 
		finally {
			doClosePageOnLowerBar("Maintain Data");
		}
	}
}
