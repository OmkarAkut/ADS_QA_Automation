package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcSelectColumnsDialogVerifyOnlineHelpAds1113 extends PageTestHelper {

	private static CostingMap costingMap;

	/**
	 * Automates test ticket Ads-1113. User Help on Ucqc > Select Columns dialog.
	 * 
	 * @throws Throwable
	 */
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("UcqcSelectColumnsDialogVerifyOnlineHelpAds1113", "webdriver.globalscripts.help",
				"UcqcSelectColumnsDialogVerifyOnlineHelpAds1113");

		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("TEST CLASS: " + UcqcSelectColumnsDialogVerifyOnlineHelpAds1113.class.getSimpleName());
			evolveLoginStaticUser(Users.AutomationTester1);
			goToPage("Ucqc");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void testUcqcPageSelectColumnsDialogVerifyOnlineHelpLink() throws Throwable {
		try {
			
			
			
			//Venkat  Add Robot class in zoom in chrome browser and wait condition 13.09.2022
			Thread.sleep(3000);
        	Robot robot = new Robot();
        	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			Thread.sleep(500);//venkat adding key release function 23.09.2022
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			Thread.sleep(500);
        	robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			Thread.sleep(500);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			Thread.sleep(2000);
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),
					costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "QA Marina");
			
			waitForSpinnerToEnd();
			waitForElementToBeVisible(costingMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
			
			costingMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll().click();
			costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect().click();
			testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'selectorfd.htm')]")), "Selector",printout);
			costingMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel().click();
			doClosePageOnLowerBar("Unit Cost Quick...");

			ExtentReport.logPass("PASS", "testUcqcPageSelectColumnsDialogVerifyOnlineHelpLink");
			//Robot robot = new Robot();
		    robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			Thread.sleep(500);//venkat adding key release function 23.09.2022
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_ADD);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ADD);
			Thread.sleep(500);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_ADD);
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testUcqcPageSelectColumnsDialogVerifyOnlineHelpLink", driver, e);
			fail(e.getMessage());
		}
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
