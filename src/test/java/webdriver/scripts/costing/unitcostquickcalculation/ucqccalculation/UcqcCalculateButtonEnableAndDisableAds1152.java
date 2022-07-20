package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.interactions.Actions;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/**Zephyr ticket ADS-1152 (Dev Story ADS-364).  Last Updated 06-21-19. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCalculateButtonEnableAndDisableAds1152 extends UcqcHelper {

    boolean printout = false;
    private static CostingMap ucqcMap;
    private static Actions act;
    private String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception {
       act = new Actions(driver);
       ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
       System.out.println("Test Class: " + UcqcCalculateButtonEnableAndDisableAds1152.class.getSimpleName());
       Login.loginUser("CostingDepartmentManager1");
       goToPage("Unit Cost Quick Calculation");
       doMaximizeWindow();
    }

    @Test
    public void test02UcqcPageVerifyDefaultStateOfCalculateButtonAsDisabled(){
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test03UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFields(){
        try {
            waitForAjaxExtJs();
            ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton(){
        try {
            doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
            waitForSpinnerToEnd();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterUpdatingGridCell(){
        try {
            String cellValue = getCellValue("1100023","Quick Salaries and Wages RVU");
            ucqcUpdateGridCellValue("1100023","Quick Salaries and Wages RVU", cellValue, printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs(), printout);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test05UcqcPageVerifyEnabledStateOfCalculateButtonAfterSavingGridCellUpdate(){
        try {
            doClick(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
            waitForSpinnerToEnd();
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs(), printout);
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }
}
