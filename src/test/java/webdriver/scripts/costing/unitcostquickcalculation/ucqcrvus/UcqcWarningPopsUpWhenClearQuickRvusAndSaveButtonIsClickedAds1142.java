package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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

/** Zephyr ticket ADS-1142 (Dev Story ADS-555). Last Updated 06-21-19. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcWarningPopsUpWhenClearQuickRvusAndSaveButtonIsClickedAds1142 extends UcqcHelper {

    private static CostingMap ucqcMap;
    private static Actions act;
    String expectedWarningText = "Decision Support will clear the Quick RVUs you selected. " +
            "Click Clear and Save to remove the Quick RVU from the highlighted cells; or, " +
            "click Cancel to return to the previous screen without clearing the RVUs.";
    static String initialCellValue = null;
    String currentCellValue = null;
    String warningText = null;
    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center",
    		//"2110  ICU",
    		"2110", //venkat update department filed value 21.09.2022
    		"Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception,Throwable {
    	ExtentReport.reportCreate("UcqcWarningPopsUpWhenClearQuickRvusAndSaveButtonIsClickedAds1142", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "UcqcWarningPopsUpWhenClearQuickRvusAndSaveButtonIsClickedAds1142");
       try {
		act = new Actions(driver);
		   ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		   System.out.println("Test Class: " + UcqcWarningPopsUpWhenClearQuickRvusAndSaveButtonIsClickedAds1142.class.getSimpleName());
		   Login.loginUser("CostingDepartmentManager1");
		   goToPage("Unit Cost Quick Calculation");
		   doMaximizeWindow();
		   ExtentReport.logPass("PASS", "setupScript");
   	} catch (Exception|AssertionError e) {
   		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
   		fail(e.getMessage());
   	}
    }

    @Test
    public void test02TestSetup() throws Throwable {
        try {
            waitForAjaxExtJs();
            ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
            assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
            doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
            waitForAjaxExtJs();
            initialCellValue = getCellValue("1100023","Quick Salaries and Wages RVU");
            ucqcGridClickInCell("1100023","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(), printout);
            ExtentReport.logPass("PASS", "test02TestSetup");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test02TestSetup", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test03aVerifyClickingClearQuickRvuAndSaveTriggersWarningMessage()
            throws Throwable {
        try {
			doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
			assertClearQuickRvusAndSaveDialog();
			ExtentReport.logPass("PASS", "test03aVerifyClickingClearQuickRvuAndSaveTriggersWarningMessage");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test03aVerifyClickingClearQuickRvuAndSaveTriggersWarningMessage", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test03bVerifyClearQuickRvuAndSaveWarningDialogMessageContent() throws Throwable {
        try {
			warningText = driver.findElement(By.xpath(
			        "//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::div[contains(@id,'panel') and contains(@id,'body')]/div")).getText();
			assertEquals(expectedWarningText, warningText);
			ExtentReport.logPass("PASS", "test03bVerifyClearQuickRvuAndSaveWarningDialogMessageContent");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test03bVerifyClearQuickRvuAndSaveWarningDialogMessageContent", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test04ClearQuickRvuAndSaveDialogCancelButtonClosesDialog()
            throws Throwable {
        try {
			driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
			assertDialogClosesProperly();
			ExtentReport.logPass("PASS", "test04ClearQuickRvuAndSaveDialogCancelButtonClosesDialog");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test04ClearQuickRvuAndSaveDialogCancelButtonClosesDialog", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test05ClearQuickRvuAndSaveDialogTriggeredWhenAllValuesOfQuickRvuColumnAreSelected() throws Throwable {
        try {
            waitForAjaxExtJs();
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
            waitForAjaxExtJs();
            assertClearQuickRvusAndSaveDialog();
            ExtentReport.logPass("PASS", "test05ClearQuickRvuAndSaveDialogTriggeredWhenAllValuesOfQuickRvuColumnAreSelected");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test05ClearQuickRvuAndSaveDialogTriggeredWhenAllValuesOfQuickRvuColumnAreSelected", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test06aClearQuickRvuAndSaveDialogCancelXClosesDialog() throws Throwable {
        try {
			driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
			waitForAjaxExtJs();
			assertElementIsNotDisplayed(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']"));
			 ExtentReport.logPass("PASS", "test06aClearQuickRvuAndSaveDialogCancelXClosesDialog");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test06aClearQuickRvuAndSaveDialogCancelXClosesDialog", driver, e);
       		fail(e.getMessage());
       	}
    }

  
    @Test
    public void test06bAssertCellValueRemainsUnchanged() throws InterruptedException ,Throwable{
        try {
        	/*Omkar 29/1/2024 : xpath changes for 11.2
        	//Shilpa 28.102022 below jscript has been added to scoll
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",driver.findElement(By.xpath("//table/tbody/tr[@class='x-grid-row']/td/div[text()='1']")));
		    Thread.sleep(3000);
			driver.findElement(By.xpath("//table/tbody/tr[@class='x-grid-row']/td/div[text()='1']")).click();
			*/
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",driver.findElement(By.xpath("//table/tbody/tr[@class='  x-grid-row']/td/div[text()='1']")));
		    Thread.sleep(3000);
			driver.findElement(By.xpath("//table/tbody/tr[@class='  x-grid-row']/td/div[text()='1']")).click();
			currentCellValue = getNewCellValue("1100023","Quick Salaries and Wages RVU");
			assertEquals(initialCellValue, currentCellValue);
			 ExtentReport.logPass("PASS", "test06bAssertCellValueRemainsUnchanged");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test06bAssertCellValueRemainsUnchanged", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test07UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuCellsInSameColumnWithControlButton() throws Throwable{
        try {
            ucqcGridClickInCell("1100031","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ucqcGridClickInCell("1100049","Quick Salaries and Wages RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
            assertClearQuickRvusAndSaveDialog();
            ExtentReport.logPass("PASS", "test07UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuCellsInSameColumnWithControlButton");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test07UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuCellsInSameColumnWithControlButton", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test08ClearQuickRvuAndSaveDialogCancelButtonClosesDialog()
            throws Throwable {
        try {
			driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
			assertDialogClosesProperly();
			  ExtentReport.logPass("PASS", "test08ClearQuickRvuAndSaveDialogCancelButtonClosesDialog");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test08ClearQuickRvuAndSaveDialogCancelButtonClosesDialog", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test09UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuColumnHeaders() throws Throwable {
        try {
            waitForAjaxExtJs();
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ucqcGridClickInCell("1100023","Quick Employee Benefits RVU", printout);
            ucqcGridClickHeader("Quick Employee Benefits RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            waitForAjaxExtJs();
            doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
            assertClearQuickRvusAndSaveDialog();
            ExtentReport.logPass("PASS", "test09UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuColumnHeaders");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test09UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuColumnHeaders", driver, e);
       		fail(e.getMessage());
       	}
    }

    @Test
    public void test10ClearQuickRvuAndSaveDialogCancelButtonClosesDialog() throws Throwable {
        try {
            driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
            waitForAjaxExtJs();
            assertElementIsNotDisplayed(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']"));
            ExtentReport.logPass("PASS", "test10ClearQuickRvuAndSaveDialogCancelButtonClosesDialog");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test10ClearQuickRvuAndSaveDialogCancelButtonClosesDialog", driver, e);
       		fail(e.getMessage());
       	}
    }

    
    @Test
    public void test11AssertValueIsUnchanged() throws InterruptedException,Throwable {
        try {
        	/*Omkar 29/1/2024 : xpath changes for 11.2
        	//Shilpa 28.10.2022
        	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",driver.findElement(By.xpath("//table/tbody/tr[@class='x-grid-row']/td/div[text()='1']")));
		    Thread.sleep(3000);
			driver.findElement(By.xpath("//table/tbody/tr[@class='x-grid-row']/td/div[text()='1']")).click();
			*/
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",driver.findElement(By.xpath("//table/tbody/tr[@class='  x-grid-row']/td/div[text()='1']")));
		    Thread.sleep(3000);
			driver.findElement(By.xpath("//table/tbody/tr[@class='  x-grid-row']/td/div[text()='1']")).click();
			currentCellValue = getNewCellValue("1100023","Quick Salaries and Wages RVU");
			assertEquals(initialCellValue, currentCellValue);
			 ExtentReport.logPass("PASS", "test11AssertValueIsUnchanged");
       	} catch (Exception|AssertionError e) {
       		ExtentReport.logFail("FAIL", "test11AssertValueIsUnchanged", driver, e);
       		fail(e.getMessage());
       	}
    }

    private void assertDialogClosesProperly() throws InterruptedException {
        waitForAjaxExtJs();
        assertElementIsNotDisplayed(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']"));
        currentCellValue = getNewCellValue("1100023","Quick Salaries and Wages RVU");
        assertEquals(initialCellValue, currentCellValue);
    }

    private void assertClearQuickRvusAndSaveDialog() throws InterruptedException {
    	
        waitForAjaxExtJs();
        String warning = driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']")).getText();
        assertEquals("Warning", warning);
//        Omkar 29/1/2024 : xpath changes for 11.2
//        warningText = driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::div[contains(@id,'panel') and contains(@id,'body')]/div")).getText();
        warningText = driver.findElement(By.xpath("(//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::div[contains(@id,'panel') and contains(@id,'body')]//div)[4]")).getText();
        assertEquals(expectedWarningText, warningText);
        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")), printout);
        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Clear and Save']")), printout);
//        Omkar 29/1/2024 : xpath changes for 11.2
//        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::img")), printout);
        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::div[contains(@class,'x-tool-after-title')]")), printout);
    }

    private String getNewCellValue (String chargeCode, String headerName) throws InterruptedException {
        String columnID;
        String columnValue = null;
        try {
            System.out.println(chargeCode);
            System.out.println(headerName);
            String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
            System.out.println("Row Number: " + row);
            columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
            int columnIDDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
            columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIDDigits + "')]/div")).getText();
            System.out.println("Value: " + columnValue);
        } catch (Throwable e) {
            e.getMessage();
        }
        return columnValue;
    }
    @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
