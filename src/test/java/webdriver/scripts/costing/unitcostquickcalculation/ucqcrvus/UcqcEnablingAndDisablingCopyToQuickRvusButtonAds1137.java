package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** Zephyr ticket ADS-1137.  Updated 06-21-19. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcEnablingAndDisablingCopyToQuickRvusButtonAds1137 extends UcqcHelper {

    boolean printout = false;
    private static CostingMap ucqcMap;
    //shilpa 19.09.2022 updated below line
//    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};
    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110", "Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception,Throwable {
    	ExtentReport.reportCreate("UcqcEnablingAndDisablingCopyToQuickRvusButtonAds1137", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "UcqcEnablingAndDisablingCopyToQuickRvusButtonAds1137");
       try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		   System.out.println("Test Class: " + UcqcEnablingAndDisablingCopyToQuickRvusButtonAds1137.class.getSimpleName());
		   Login.loginUser("CostingDepartmentManager1");
		   goToPage("Unit Cost Quick Calculation");
		   doMaximizeWindow();
		   ExtentReport.logPass("PASS", "setupScript");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
    }

    @Test
    public void test01aUcqcPageVerifyDefaultStateOfCopyToQuickRvusButtonAsDisabled() throws Throwable{
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test01aUcqcPageVerifyDefaultStateOfCopyToQuickRvusButtonAsDisabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test01aUcqcPageVerifyDefaultStateOfCopyToQuickRvusButtonAsDisabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test01bUcqcPageVerifyEnabledStateOfCopyToQuickRvusButtonAfterSelectingRequiredFields() throws Throwable{
        try {
            waitForAjaxExtJs();
            ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test01bUcqcPageVerifyEnabledStateOfCopyToQuickRvusButtonAfterSelectingRequiredFields");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test01bUcqcPageVerifyEnabledStateOfCopyToQuickRvusButtonAfterSelectingRequiredFields", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test02UcqcPageUndoCostModelScenarioAndCopyToQuickRvusButtonBecomesDisabled() throws Throwable{
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test02UcqcPageUndoCostModelScenarioAndCopyToQuickRvusButtonBecomesDisabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test02UcqcPageUndoCostModelScenarioAndCopyToQuickRvusButtonBecomesDisabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test03UcqcPageUndoEntityFieldAndCopyToQuickRvusButtonBecomesDisabled() throws Throwable{
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test03UcqcPageUndoEntityFieldAndCopyToQuickRvusButtonBecomesDisabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test03UcqcPageUndoEntityFieldAndCopyToQuickRvusButtonBecomesDisabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test04UcqcPageUndoDepartmentFieldAndCopyToQuickRvusButtonBecomesDisabled() throws Throwable{
        try {
            resetRequiredFields();
            selectDepartment("<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test04UcqcPageUndoDepartmentFieldAndCopyToQuickRvusButtonBecomesDisabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test04UcqcPageUndoDepartmentFieldAndCopyToQuickRvusButtonBecomesDisabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test05UcqcPageUndoResultsStoredForFieldAndCopyToQuickRvusButtonBecomesDisabled() throws Throwable{
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test05UcqcPageUndoResultsStoredForFieldAndCopyToQuickRvusButtonBecomesDisabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test05UcqcPageUndoResultsStoredForFieldAndCopyToQuickRvusButtonBecomesDisabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test06UcqcPageModifyAnyQuickRvuButDontSaveAndCopyToQuickRvusButtonBecomesDisabled() throws Throwable{
        try {
            resetRequiredFields();
            ucqcUpdateGridCellValue("1100023","Quick Salaries and Wages RVU","10",printout);
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test06UcqcPageModifyAnyQuickRvuButDontSaveAndCopyToQuickRvusButtonBecomesDisabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test06UcqcPageModifyAnyQuickRvuButDontSaveAndCopyToQuickRvusButtonBecomesDisabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test07UcqcPageModifyAnyQuickRvuDontSaveThenUndoAndCopyToQuickRvusButtonBecomesEnabled() throws Throwable{
        try {
            ucqcMap.getUnitCostQuickCalculationButtonUndo().click();
            waitForSpinnerToEnd();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test07UcqcPageModifyAnyQuickRvuDontSaveThenUndoAndCopyToQuickRvusButtonBecomesEnabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test07UcqcPageModifyAnyQuickRvuDontSaveThenUndoAndCopyToQuickRvusButtonBecomesEnabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    @Test
    public void test08UcqcPageModifyAnyQuickRvuAndSaveAndCopyToQuickRvusButtonBecomesEnabled() throws Throwable{
        try {
            ucqcUpdateGridCellValue("1100023","Quick Salaries and Wages RVU","12",printout);
            waitForAjaxExtJs();
            doClick(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
            ucqcWaitForSpinnerToEnd();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
            ExtentReport.logPass("PASS", "test08UcqcPageModifyAnyQuickRvuAndSaveAndCopyToQuickRvusButtonBecomesEnabled");
    	}  catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test08UcqcPageModifyAnyQuickRvuAndSaveAndCopyToQuickRvusButtonBecomesEnabled", driver, e);
    		fail(e.getMessage());

    	}
    }

    private void resetRequiredFields() throws Exception {
        ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
        doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
        waitForAjaxExtJs();
        assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
    }
    @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
