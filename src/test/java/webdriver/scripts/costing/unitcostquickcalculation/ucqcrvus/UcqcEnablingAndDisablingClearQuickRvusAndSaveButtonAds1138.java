package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
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
    //Shilpa 19.09.2022 updated below line
//    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};
    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110", "Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception,Throwable{
    	ExtentReport.reportCreate("UcqcEnablingAndDisablingClearQuickRvusAndSaveButtonAds1138", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "UcqcEnablingAndDisablingClearQuickRvusAndSaveButtonAds1138");
       try {
		act = new Actions(driver);
		   ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		   System.out.println("Test Class: " + UcqcEnablingAndDisablingClearQuickRvusAndSaveButtonAds1138.class.getSimpleName());
		   Login.loginUser("CostingDepartmentManager1");
		   goToPage("Unit Cost Quick Calculation");
		   doMaximizeWindow();
		   ExtentReport.logPass("PASS", "setupScript");
   	} catch (Exception|AssertionError e) {
   		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
   		fail(e.getMessage());
   	}
    }

//default page state tests
    @Test
    public void test01aUcqcPageVerifyDefaultStateOfClearQuickRvusAndSaveButtonAsDisabled() throws Throwable{
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
            ExtentReport.logPass("PASS", "test01aUcqcPageVerifyDefaultStateOfClearQuickRvusAndSaveButtonAsDisabled");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test01aUcqcPageVerifyDefaultStateOfClearQuickRvusAndSaveButtonAsDisabled", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test01bUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingOneRequiredField() throws Throwable{
        try {
            waitForAjaxExtJs();
            doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"QA Cost Model");
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
            ExtentReport.logPass("PASS", "test01aUcqcPageVerifyDefaultStateOfClearQuickRvusAndSaveButtonAsDisabled");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test01aUcqcPageVerifyDefaultStateOfClearQuickRvusAndSaveButtonAsDisabled", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test01cUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingAllRequiredFields() throws Throwable{
        try {
            waitForAjaxExtJs();
            ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
            ExtentReport.logPass("PASS", "test01cUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingAllRequiredFields");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test01cUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingAllRequiredFields", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test01dUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton() throws Throwable{
        try {
            doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
            ExtentReport.logPass("PASS", "test01dUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test01dUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton", driver, e);
       		fail(e.getMessage());
       	}
    }
//button is enabled tests
    @Test
    public void test02aUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButtonAndClickCell() throws Throwable{
        try {
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02aUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButtonAndClickCell");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02aUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButtonAndClickCell", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test02bUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingModifierColumnHeader() throws Throwable{
        try {
            ucqcGridClickHeader("Modifier", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02bUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingModifierColumnHeader");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02bUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingModifierColumnHeader", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test02cUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingHidePageElement() throws Throwable{
        try {
            ucqcMap.getUnitCostQuickCalculationButtonHide().click();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02cUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingHidePageElement");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02cUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingHidePageElement", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test02dUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentCellInSameColumn() throws Throwable{
        try {
            ucqcGridClickInCell("1100031","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02dUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentCellInSameColumn");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02dUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentCellInSameColumn", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test02eUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInDifferentQuickRvuColumn() throws Throwable{
        try {
            ucqcGridClickInCell("1100049","Quick Employee Benefits RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02eUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInDifferentQuickRvuColumn");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02eUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInDifferentQuickRvuColumn", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test02fUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingSameColumnHeaderAsInitialCell() throws Throwable{
        try {
        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",         	driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='Quick Salaries and Wages RVU']"))
);//Shilpa 19.09.2022 added this to make element visible
        	Thread.sleep(500); 
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02fUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingSameColumnHeaderAsInitialCell");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02fUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingSameColumnHeaderAsInitialCell", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test02gUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentQuickRvuColumnHeaderThanInitialCell() throws Throwable{
        try {
        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",         	driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='Quick Employee Benefits RVU']"))
        			);//Shilpa 19.09.2022 added this to make element visible
        	driverDelay(500);
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02gUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentQuickRvuColumnHeaderThanInitialCell");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02gUcqcPageVerifyEnabledStateOfClearQuickRvusAndSaveButtonAfterClickingDifferentQuickRvuColumnHeaderThanInitialCell", driver, e);
       		fail(e.getMessage());
       	}
    }

//button disables
    @Test
    public void test03aUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInModifierColumn() throws Throwable{
        try {
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout); //click in initial cell
            waitForAjaxExtJs();
            ucqcGridClickInCell("1100023","Modifier", printout);
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test03aUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInModifierColumn");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test03aUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingCellInModifierColumn", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test03bUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingNonQuickRvuCell() throws Throwable{
        try {
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout); //click in initial cell
            waitForAjaxExtJs();
            ucqcGridClickInCell("1100023","Salaries and Wages Cost", printout);
            waitForAjaxExtJs();
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test03bUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingNonQuickRvuCell");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test03bUcqcPageVerifyDisabledStateOfClearQuickRvusAndSaveButtonAfterClickingNonQuickRvuCell", driver, e);
       		fail(e.getMessage());
       	}
    }

//multi select tests
    @Test
    public void test04aUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuCellsInSameColumn() throws Throwable{
        try {
            ucqcGridClickInCell("1100031","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ucqcGridClickInCell("1100049","Quick Salaries and Wages RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test04aUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuCellsInSameColumn");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test04aUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuCellsInSameColumn", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test04bUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingAnotherQuickRvuColumnHeader() throws Throwable{
        try {
        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",         	driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='Quick Employee Benefits RVU']"))
        			);//Shilpa 19.09.2022 added this to make element visible
        	driverDelay(1000);
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test04bUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingAnotherQuickRvuColumnHeader");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test04bUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingAnotherQuickRvuColumnHeader", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test04cUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuColumnHeaders() throws Throwable{
        try {
        	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='Quick Salaries and Wages RVU']"))
        			);//Shilpa 19.09.2022 added this to make element visible
        	driverDelay(500);
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='Quick Employee Benefits RVU']"))
        			);//Shilpa 19.09.2022 added this to make element visible
        	driverDelay(500);
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test04cUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuColumnHeaders");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test04cUcqcPageVerifyEnabledClearQuickRvusAndSaveButtonAfterSelectingMultipleQuickRvuColumnHeaders", driver, e);
       		fail(e.getMessage());
       	}
    }
    @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
