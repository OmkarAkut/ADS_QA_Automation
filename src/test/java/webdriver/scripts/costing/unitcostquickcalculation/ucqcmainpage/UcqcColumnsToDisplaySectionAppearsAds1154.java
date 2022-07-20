package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** This script verifies basic functionality of Columns to Display section on ucqc page.
 * ADS-1154 (Dev story: ads-297)
 * Last Updated: 06/21/19
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcColumnsToDisplaySectionAppearsAds1154 extends UcqcHelper {

    static CostingMap costingMap;
    String costModel = "QA Cost Model";
    String[] setRequiredFields = {"Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005"};

    @BeforeClass
    public static void setupScript() throws Exception {
        costingMap = BuildMap.getInstance(driver, CostingMap.class);
        System.out.println("Test Class: " + UcqcColumnsToDisplaySectionAppearsAds1154.class.getSimpleName());
        Login.loginUser("CostingDepartmentManager1");
        doMaximizeWindow();
        goToPage("Unit Cost Quick Calculation");
    }

    @Test
    public void test01VerifyColumnsToDisplayIsRequired() {
        try {
            waitForAjaxExtJs();
            Thread.sleep(1000);
            assertAsteriskIsDisplayed("Columns to Display");
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test02VerifyDefaultStatusOfAllCheckboxAndSelectButton() {
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            assertAllCheckboxIsDisabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test03aSetCostModelAndVerifyAllCheckboxIsSelectedAndEnabled() {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test03bSetCostModelToNoneAndVerifyAllCheckboxIsSelectedAndDisabled() {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "<None>");
            waitForAjaxExtJs();
            assertAllCheckboxIsDisabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test03cSetNewCostModelAndVerifyAllCheckboxIsSelectedAndEnabled() {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "QA Marina");
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test04UncheckAllCheckboxAndVerifySelectedButtonIsEnabled() {
        try {
            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsNotChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test05CheckAllCheckboxAndVerifySelectedButtonIsDisabled() {
        try {
            waitForAjaxExtJs();
            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertAllCheckboxIsEnabled(printout);
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test06SetRequiredFieldsAndEnsureAllCheckboxIsCheckedAndVerifyApplySelectionsButtonIsEnabled() {
        try {
            resetRequiredFields();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test07SetCostModelToNoneAndEnsureAllCheckboxIsUnchanged() {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test08SetCostModelScenarioToNoneAndEnsureAllCheckboxIsUnchanged() {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test09SetEntityToNoneAndEnsureAllCheckboxIsUnchanged() {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(), costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test10SetDepartmentToNoneAndEnsureAllCheckboxIsUnchanged() {
        try {
            resetRequiredFields();
            updateDepartment("<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test11SetResultsStoredForToNoneAndEnsureAllCheckboxIsUnchanged() {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }


    private void resetRequiredFields() throws Exception {
        ucqcPopulateRequiredFieldsToDisplayGrid(setRequiredFields);  //("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");
        waitForAjaxExtJs();
        assertColumnsToDisplayAllCheckBoxIsChecked();
    }

    private void assertAllCheckboxIsEnabled(boolean printout) throws Exception {
        String classText;
        try {
            waitForAjaxExtJs();
            classText = driver.findElement(By.xpath("//label[text()='All']/ancestor::*[contains(@class,'x-form-cb')]")).getAttribute("class");
            boolean isEnabled = !(classText.contains("disabled"));
            if (printout) {
                System.out.println("Element class text: " + classText);
                System.out.println("IsEnabled: " + isEnabled);
            }
            assertTrue(isEnabled);
        } catch (Throwable e) {
            fail("element not found");
        }
    }

    private void assertAllCheckboxIsDisabled(boolean printout) throws Exception {
        String classText = null;
        try {
            waitForAjaxExtJs();
            classText = driver.findElement(By.xpath("//label[text()='All']/ancestor::*[contains(@class,'x-form-cb')]")).getAttribute("class");
            boolean isEnabled = classText.contains("disabled");
            if (printout) {
                System.out.println("Element class text: " + classText);
                System.out.println("IsEnabled: " + isEnabled);
            }
            assertTrue(isEnabled);
        } catch (Throwable e) {
            fail("element not found");
        }
    }

    private void assertColumnsToDisplayAllCheckBoxIsNotChecked() throws Exception {
        String columnsToDisplayCheckBox = null;
        try {
            waitForAjaxExtJs();
            columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
        } catch(Throwable e) {
            System.out.println("Element Not Found");
            fail("element not found");
        }
        boolean isNotChecked = columnsToDisplayCheckBox.contains("dirty");
        if (printout){
            System.out.println("Element class text: " + columnsToDisplayCheckBox);
            System.out.println("IsNotChecked: " + isNotChecked);
        }
        try {
            assertTrue(isNotChecked);
        } catch(Throwable e){
            System.out.println("TEST FAILED: Element is Checked");
            throw new Exception();
        }
    }

    private void assertColumnsToDisplayAllCheckBoxIsChecked() throws Exception {
        String columnsToDisplayCheckBox = null;
        try {
            waitForAjaxExtJs();
            columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
        } catch(Throwable e) {
            System.out.println("Element Not Found");
            fail("element not found");
        }
        boolean isChecked = columnsToDisplayCheckBox.contains("checked");
        if (printout){
            System.out.println("Element class text: " + columnsToDisplayCheckBox);
            System.out.println("IsChecked: " + isChecked);
        }
        try {
            assertTrue(isChecked);
        } catch(Throwable e){
            System.out.println("TEST FAILED: Element is not checked");
            throw new Exception();
        }
    }
}


