package webdriver.scripts.cim;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-20027 **/
public class CIMaddedtoCustomCostOverheadTaskLists extends GoHelper{
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		
		ExtentReport.reportCreate("CIMaddedtoCustomCostOverheadTaskLists", "webdriver.scripts.cim",
				"CIMaddedtoCustomCostOverheadTaskLists");
		try {
			Login.loginUser("AutomationTesterAdmin");
			goToPage("customize task lists");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01Validate_CIM_Other_Screens_20054() throws Throwable {
		try {
			doClick("//span[text()='Unpublished Contract']/ancestor::a");
			waitForDisplayedSpinnerToEnd();
			assertElementIsNotDisplayed("//div[contains(@class,'x-tree-view nowrapRow')]//span[text()='Cost Integration Manager (CIM)']");
			doClick("//span[text()='Published Contract']/ancestor::a");
			waitForDisplayedSpinnerToEnd();
			assertElementIsNotDisplayed("//div[contains(@class,'x-tree-view nowrapRow')]//span[text()='Cost Integration Manager (CIM)']");
			doClick("//span[text()='Episode']/ancestor::a");
			waitForDisplayedSpinnerToEnd();
			assertElementIsNotDisplayed("//div[contains(@class,'x-tree-view nowrapRow')]//span[text()='Cost Integration Manager (CIM)']");
			ExtentReport.logPass("PASS", "test03Validate_CIM_Other_Screens_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_CIM_Other_Screens_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01Validate_CIM_Under_Cost_Screen_20054() throws Throwable {
		try {
			doClick("//span[text()='Cost']/ancestor::a");
			assertElementTextContainsWithXpathLocator("//div[text()='Cost Integration Manager (CIM)']/preceding::table[1]//div", "Cost Component Variability Masters", printout);
			assertElementTextContainsWithXpathLocator("//div[text()='Cost Integration Manager (CIM)']/following::table[1]//div", "Cost Method Masters", printout);
			ExtentReport.logPass("PASS", "test01Validate_Filter_Conditions_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_Filter_Conditions_20054", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03Validate_Overhead_Screen_20054() throws Throwable {
		try {
			doClick("//span[text()='Overhead']/ancestor::a");
			doClick("//span[text()='Allocate Overhead']/preceding::div[contains(@class,'x-tree-expander')][1]");
			List<WebElement> list = driver.findElements(By.xpath("(//table[contains(@class,'x-grid-item-selected')])[2]//following::table//span[@class='x-tree-node-text ']"));
			System.out.println(list.size());
			for(int i=0;i<=list.size();i++) {
				if(list.get(i).getText().equals("Cost Integration Manager (CIM)")) {
					int j=i;
					int lastElement=j+1;
					if(driver.findElement(By.xpath("((//table[contains(@class,'x-grid-item-selected')])[2]//following::table//span[@class='x-tree-node-text '])["+lastElement+"]")).getText().equals("Cost Integration Manager (CIM)")) {
						System.out.println(driver.findElement(By.xpath("((//table[contains(@class,'x-grid-item-selected')])[2]//following::table//span[@class='x-tree-node-text '])["+lastElement+"]")).getText());
						assertTrue(printout);
						break;
					}
					else {
						fail();
					}
				}
			}
			ExtentReport.logPass("PASS", "test01Validate_Overhead_Screen_20054");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01Validate_Overhead_Screen_20054", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Customize Task Lists");
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
