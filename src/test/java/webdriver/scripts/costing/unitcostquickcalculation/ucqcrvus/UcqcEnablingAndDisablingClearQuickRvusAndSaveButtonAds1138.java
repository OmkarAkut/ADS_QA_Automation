package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** Zephyr ticket ADS-1138 (Dev Story ADS-554). Last Updated 06-21-19. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcEnablingAndDisablingClearQuickRvusAndSaveButtonAds1138 extends UcqcHelper {

    boolean printout = false;
    private static CostingMap ucqcMap;
    private static Actions act;
    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception {
       act = new Actions(driver);
       ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
       System.out.println("Test Class: " + UcqcEnablingAndDisablingClearQuickRvusAndSaveButtonAds1138.class.getSimpleName());
       Login.loginUser("CostingDepartmentManager1");
       goToPage("Unit Cost Quick Calculation");
       doMaximizeWindow();
    }

//default page state tests
    @Test
    public void test01aUcqcPageVerifyDefaultStateOfClearQuickRvusAndSaveButtonAsDisabled(){
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test01bUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingOneRequiredField(){
        try {
            waitForAjaxExtJs();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"QA Cost Model");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test01cUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingAllRequiredFields(){
        try {
            waitForAjaxExtJs();
            ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test01dUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton(){
        try {
            doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }
//button is enabled tests
    @Test
    public void test02aUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButtonAndClickCell(){
        try {
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02bUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingModifierColumnHeader(){
        try {
            ucqcGridClickHeader("Modifier", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02cUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingHidePageElement(){
        try {
            ucqcMap.getUnitCostQuickCalculationButtonHide().click();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02dUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentCellInSameColumn(){
        try {
            ucqcGridClickInCell("1100031","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02eUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInDifferentQuickRvuColumn(){
        try {
            ucqcGridClickInCell("1100049","Quick Employee Benefits RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02fUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingSameColumnHeaderAsInitialCell(){
        try {
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test02gUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentQuickRvuColumnHeaderThanInitialCell(){
        try {
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

//button disables
    @Test
    public void test03aUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInModifierColumn(){
        try {
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout); //click in initial cell
            waitForAjaxExtJs();
            ucqcGridClickInCell("1100023","Modifier", printout);
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test03bUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingNonQuickRvuCell(){
        try {
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout); //click in initial cell
            waitForAjaxExtJs();
            ucqcGridClickInCell("1100023","Salaries and Wages Cost", printout);
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

//multi select tests
    @Test
    public void test04aUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuCellsInSameColumn(){
        try {
            ucqcGridClickInCell("1100031","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ucqcGridClickInCell("1100049","Quick Salaries and Wages RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04bUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingAnotherQuickRvuColumnHeader(){
        try {
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test04cUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuColumnHeaders(){
        try {
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }
}
