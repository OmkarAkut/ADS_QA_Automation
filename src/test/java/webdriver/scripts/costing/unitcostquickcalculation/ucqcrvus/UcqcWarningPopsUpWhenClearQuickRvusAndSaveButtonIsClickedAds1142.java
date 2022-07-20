package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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
    String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};

    @BeforeClass
    public static void setupScript() throws Exception {
       act = new Actions(driver);
       ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
       System.out.println("Test Class: " + UcqcWarningPopsUpWhenClearQuickRvusAndSaveButtonIsClickedAds1142.class.getSimpleName());
       Login.loginUser("CostingDepartmentManager1");
       goToPage("Unit Cost Quick Calculation");
       doMaximizeWindow();
    }

    @Test
    public void test02TestSetup() {
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
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test03aVerifyClickingClearQuickRvuAndSaveTriggersWarningMessage()
            throws InterruptedException {
        doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
        assertClearQuickRvusAndSaveDialog();
    }

    @Test
    public void test03bVerifyClearQuickRvuAndSaveWarningDialogMessageContent() {
        warningText = driver.findElement(By.xpath(
                "//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::div[contains(@id,'panel') and contains(@id,'body')]/div")).getText();
        assertEquals(expectedWarningText, warningText);
    }

    @Test
    public void test04ClearQuickRvuAndSaveDialogCancelButtonClosesDialog()
            throws InterruptedException {
        driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
        assertDialogClosesProperly();
    }

    @Test
    public void test05ClearQuickRvuAndSaveDialogTriggeredWhenAllValuesOfQuickRvuColumnAreSelected() {
        try {
            waitForAjaxExtJs();
            ucqcGridClickHeader("Quick Salaries and Wages RVU", printout);
            doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
            waitForAjaxExtJs();
            assertClearQuickRvusAndSaveDialog();
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test06aClearQuickRvuAndSaveDialogCancelXClosesDialog() throws InterruptedException {
        driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
        waitForAjaxExtJs();
        assertElementIsNotDisplayed(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']"));
    }

    @Ignore
    @Test
    public void test06bAssertCellValueRemainsUnchanged() throws InterruptedException {
        driver.findElement(By.xpath("//table/tbody/tr[@class='x-grid-row']/td/div[text()='1']")).click();
        currentCellValue = getNewCellValue("1100023","Quick Salaries and Wages RVU");
        assertEquals(initialCellValue, currentCellValue);
    }

    @Test
    public void test07UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuCellsInSameColumnWithControlButton(){
        try {
            ucqcGridClickInCell("1100031","Quick Salaries and Wages RVU", printout);
            waitForAjaxExtJs();
            act.keyDown(Keys.CONTROL).perform();
            ucqcGridClickInCell("1100049","Quick Salaries and Wages RVU", printout);
            act.keyUp(Keys.CONTROL).perform();
            doClick(ucqcMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
            assertClearQuickRvusAndSaveDialog();
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test08ClearQuickRvuAndSaveDialogCancelButtonClosesDialog()
            throws InterruptedException {
        driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
        assertDialogClosesProperly();
    }

    @Test
    public void test09UcqcPageVerifyClearQuickRvuAndSaveDialogTriggeredAfterSelectingMultipleQuickRvuColumnHeaders() {
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
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    public void test10ClearQuickRvuAndSaveDialogCancelButtonClosesDialog() {
        try {
            driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")).click();
            waitForAjaxExtJs();
            assertElementIsNotDisplayed(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']"));
        } catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Ignore
    @Test
    public void test11AssertValueIsUnchanged() throws InterruptedException {
        driver.findElement(By.xpath("//table/tbody/tr[@class='x-grid-row']/td/div[text()='1']")).click();
        currentCellValue = getNewCellValue("1100023","Quick Salaries and Wages RVU");
        assertEquals(initialCellValue, currentCellValue);
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
        warningText = driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::div[contains(@id,'panel') and contains(@id,'body')]/div")).getText();
        assertEquals(expectedWarningText, warningText);
        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Cancel']")), printout);
        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::span[text()='Clear and Save']")), printout);
        assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id, 'warningwindow') and text()='Warning']/ancestor::div/descendant::img")), printout);
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
}
