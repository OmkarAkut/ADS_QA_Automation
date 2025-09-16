package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

public class EditConsumerHistoryRecord extends CalculationHelper{

	static DataMaintenanceMap dmMap;
	final static String aTozPageAscSchemes = "Consumers";
	static String consumerNumber="--00--";
	static String[] filter = { "Consumer Number", "Is", "Equal To", consumerNumber };
	/** Support Issues: Automated test script for ADS-12604 **/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EditConsumerHistoryRecord", "webdriver.scripts.datamaintenance.maintaindata",
				"EditConsumerHistoryRecord");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageAscSchemes);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01EditConsumerHistory_12604() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getConsumerFilterBtn());
			waitForPresenceOfElement("//div[contains(@id,'filterwindow')]//following::h1[text()='Consumers']");
			doFilterCreate(filter);
			waitForDisplayedSpinnerToEnd();
			doClick(DataMaintenanceMap.getconsumerEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getconsumerPanel());
			doClick(DataMaintenanceMap.getconsumerPanel());
			doClick(DataMaintenanceMap.getconsumerHistoryEditBtn());
			waitForElementToBeVisible(DataMaintenanceMap.getconsumerHistoryForm());
			String consumerNum="Consumers" + generateRandomNumber();
			keyInInputText(consumerNum,driver.findElement(By.name("firstName")));
			doClick(DataMaintenanceMap.getconsumerHistoryContinueBtn());
			doClick(DataMaintenanceMap.getconsumerPanelSaveClose());
			waitForDisplayedSpinnerToEnd();
			waitForPresenceOfElement("//*[contains(text(),'"+consumerNum+"')]");
			assertElementIsDisplayedWithXpath("//*[contains(text(),'"+consumerNum+"')]");
			ExtentReport.logPass("PASS", "test01EditConsumerHistory_12604");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01EditConsumerHistory_12604", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
	
}
