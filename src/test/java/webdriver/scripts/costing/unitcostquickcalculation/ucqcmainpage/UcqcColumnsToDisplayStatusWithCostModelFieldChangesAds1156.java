package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** ADS-1156 (Dev story: ads-999).  Last Updated: 06/24/19
 * Verifies All checkbox and Select button enable-disable properly on
 * Columns to Display section on ucqc page.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcColumnsToDisplayStatusWithCostModelFieldChangesAds1156 extends UcqcHelper {

    private static CostingMap costingMap;
    boolean printout = true;
    private String costModel = "QA Cost Model";
    private String newCostModel = "QA Marina";

    @BeforeClass
    public static void setupScript() throws Throwable {
    	ExtentReport.reportCreate("UcqcColumnsToDisplayStatusWithCostModelFieldChangesAds1156","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "UcqcColumnsToDisplayStatusWithCostModelFieldChangesAds1156");
        try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + UcqcColumnsToDisplayStatusWithCostModelFieldChangesAds1156.class.getSimpleName());
			Login.loginUser("CostingDepartmentManager1");
			doMaximizeWindow();
			goToPage("Unit Cost Quick Calculation");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","setupScript", driver,e);
			fail(e.getMessage());
		} 
    }

    @Test
    public void test01VerifyDefaultStatusOfAllCheckboxAndSelectButton() throws Throwable {
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            assertAllCheckboxIsDisabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test01VerifyDefaultStatusOfAllCheckboxAndSelectButton");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test01VerifyDefaultStatusOfAllCheckboxAndSelectButton", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test02SetCostModelAndVerifyAllCheckboxIsSelectedAndEnabledAndSelectButtonDisabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            ExtentReport.logPass("PASS", "test02SetCostModelAndVerifyAllCheckboxIsSelectedAndEnabledAndSelectButtonDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test02SetCostModelAndVerifyAllCheckboxIsSelectedAndEnabledAndSelectButtonDisabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test03UncheckAllCheckboxAndVerifySelectedButtonIsEnabled() throws Throwable {
        try {
            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsNotChecked();
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            ExtentReport.logPass("PASS", "test03UncheckAllCheckboxAndVerifySelectedButtonIsEnabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03UncheckAllCheckboxAndVerifySelectedButtonIsEnabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test04CheckAllCheckboxAndVerifySelectedButtonIsDisabled() throws Throwable {
        try {
            waitForAjaxExtJs();
            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertAllCheckboxIsEnabled(printout);
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
            ExtentReport.logPass("PASS", "test04CheckAllCheckboxAndVerifySelectedButtonIsDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test04CheckAllCheckboxAndVerifySelectedButtonIsDisabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test05SetCostModelToNoneAndEnsureAllCheckboxIsCheckedAndDisabledAndSelectButtonIsDisabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertAllCheckboxIsDisabled(printout);
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            ExtentReport.logPass("PASS", "test05SetCostModelToNoneAndEnsureAllCheckboxIsCheckedAndDisabledAndSelectButtonIsDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test05SetCostModelToNoneAndEnsureAllCheckboxIsCheckedAndDisabledAndSelectButtonIsDisabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test06SetCostModelAndVerifyAllCheckboxIsSelectedAndEnabledAndSelectButtonDisabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            ExtentReport.logPass("PASS", "test06SetCostModelAndVerifyAllCheckboxIsSelectedAndEnabledAndSelectButtonDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test06SetCostModelAndVerifyAllCheckboxIsSelectedAndEnabledAndSelectButtonDisabled", driver,e);
            fail(e.getMessage());
        }
    }

   
    @Test
    public void test07SetCostModelToAnotherCostModelAndEnsureAllCheckboxIsCheckedAndDisabledAndSelectButtonIsDisabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), newCostModel);
            waitForSpinnerToEnd();
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
        //    assertAllCheckboxIsDisabled(printout);
            //venkat updated enabled condition
              assertAllCheckboxIsEnabled(printout);
            
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            ExtentReport.logPass("PASS", "test07SetCostModelToAnotherCostModelAndEnsureAllCheckboxIsCheckedAndDisabledAndSelectButtonIsDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test07SetCostModelToAnotherCostModelAndEnsureAllCheckboxIsCheckedAndDisabledAndSelectButtonIsDisabled", driver,e);
            fail(e.getMessage());
        }
    }

    private void assertAllCheckboxIsEnabled(boolean printout) {
        String classText = null;
        try {
            waitForAjaxExtJs();
            classText = driver.findElement(By.xpath("//label[text()='All']/ancestor::*[contains(@class,'x-form-cb')]")).getAttribute("class");
        } catch(Throwable e) {
            fail("All checkbox not found");
        }
        try {
            waitForAjaxExtJs();
            boolean isDisabled = classText.contains("disabled");
            if (printout) {
                System.out.println("All checkbox class text: " + classText);
                System.out.println("All checkbox isDisabled: " + isDisabled);
            }
            assertFalse(isDisabled);
        } catch (Throwable e) {
            fail("All checkbox enable failure");
        }
    }

    private void assertAllCheckboxIsDisabled(boolean printout) {
        String classText = null;
        try {
            waitForAjaxExtJs();
            classText = driver.findElement(By.xpath("//label[text()='All']/ancestor::*[contains(@class,'x-form-cb')]")).getAttribute("class");
        } catch(Throwable e) {
            fail("All checkbox not found");
        }
        try {
            boolean isDisabled = classText.contains("disabled");
            if (printout) {
                System.out.println("All checkbox class text: " + classText);
                System.out.println("All checkbox isDisabled: " + isDisabled);
            }
            assertTrue(isDisabled);
        } catch (Throwable e) {
            fail("All checkbox disable failure");
        }
    }

    private void assertColumnsToDisplayAllCheckBoxIsNotChecked() throws Exception {
        String columnsToDisplayCheckBox = null;
        try {
            waitForAjaxExtJs();
            columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
        } catch(Throwable e) {
            fail("All checkbox not found");
        }
        boolean isNotChecked = columnsToDisplayCheckBox.contains("dirty");
        if (printout){
            System.out.println("All checkbox class text: " + columnsToDisplayCheckBox);
            System.out.println("All checkbox IsNotChecked: " + isNotChecked);
        }
        try {
            assertTrue(isNotChecked);
        } catch(Throwable e){
            fail("All checkbox checked-unchecked failure");
        }
    }

    private void assertColumnsToDisplayAllCheckBoxIsChecked() throws Exception {
        String columnsToDisplayCheckBox = null;
        try {
            waitForAjaxExtJs();
            columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
        } catch(Throwable e) {
            fail("All checkbox not found");
        }
        boolean isChecked = columnsToDisplayCheckBox.contains("checked");
        if (printout){
            System.out.println("All checkbox class text: " + columnsToDisplayCheckBox);
            System.out.println("All checkbox IsChecked: " + isChecked);
        }
        try {
            assertTrue(isChecked);
        } catch(Throwable e){
            fail("All checkbox checked-unchecked failure");
        }
    }
    @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
}


