package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** Zephyr ticket ADS-1137.  Updated 06-21-19. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcEnablingAndDisablingCopyToQuickRvusButtonAds1137 extends UcqcHelper {

    boolean printout = false;
    private static CostingMap ucqcMap;
    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception {
       ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
       System.out.println("Test Class: " + UcqcEnablingAndDisablingCopyToQuickRvusButtonAds1137.class.getSimpleName());
       Login.loginUser("CostingDepartmentManager1");
       goToPage("Unit Cost Quick Calculation");
       doMaximizeWindow();
    }

    @Test
    public void test01aUcqcPageVerifyDefaultStateOfCopyToQuickRvusButtonAsDisabled(){
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test01bUcqcPageVerifyEnabledStateOfCopyToQuickRvusButtonAfterSelectingRequiredFields(){
        try {
            waitForAjaxExtJs();
            ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02UcqcPageUndoCostModelScenarioAndCopyToQuickRvusButtonBecomesDisabled(){
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test03UcqcPageUndoEntityFieldAndCopyToQuickRvusButtonBecomesDisabled(){
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04UcqcPageUndoDepartmentFieldAndCopyToQuickRvusButtonBecomesDisabled(){
        try {
            resetRequiredFields();
            selectDepartment("<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test05UcqcPageUndoResultsStoredForFieldAndCopyToQuickRvusButtonBecomesDisabled(){
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test06UcqcPageModifyAnyQuickRvuButDontSaveAndCopyToQuickRvusButtonBecomesDisabled(){
        try {
            resetRequiredFields();
            ucqcUpdateGridCellValue("1100023","Quick Salaries and Wages RVU","10",printout);
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test07UcqcPageModifyAnyQuickRvuDontSaveThenUndoAndCopyToQuickRvusButtonBecomesEnabled(){
        try {
            ucqcMap.getUnitCostQuickCalculationButtonUndo().click();
            waitForSpinnerToEnd();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test08UcqcPageModifyAnyQuickRvuAndSaveAndCopyToQuickRvusButtonBecomesEnabled(){
        try {
            ucqcUpdateGridCellValue("1100023","Quick Salaries and Wages RVU","12",printout);
            waitForAjaxExtJs();
            doClick(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
            ucqcWaitForSpinnerToEnd();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    private void resetRequiredFields() throws Exception {
        ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
        doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
        waitForAjaxExtJs();
        assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
    }
}
