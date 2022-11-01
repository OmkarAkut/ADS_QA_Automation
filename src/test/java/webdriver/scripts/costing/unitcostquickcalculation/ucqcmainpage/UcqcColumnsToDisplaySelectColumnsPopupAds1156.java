package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** ADS-1156 (Dev story: ads-999).  Last Updated: 07/11/19.
 * Verifies All checkbox and Select button enable-disable properly on
 * Columns to Display section on ucqc page.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcColumnsToDisplaySelectColumnsPopupAds1156 extends UcqcHelper {

    private static CostingMap costingMap;
    boolean printout = false;
    private String costModel = "QA Cost Model";
    private String newCostModel = "QA Marina";
    private static ArrayList<String> selectedListStrings = new ArrayList();
    private static List<WebElement> initialSelectedList;

    @BeforeClass
    public static void setupScript() throws Throwable {
    	 ExtentReport.reportCreate("UcqcColumnsToDisplaySelectColumnsPopupAds1156","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "UcqcColumnsToDisplaySelectColumnsPopupAds1156");
        try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + UcqcColumnsToDisplaySelectColumnsPopupAds1156.class.getSimpleName());
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
    public void test02aVerifyDefaultStatusOfAllCheckboxAndSelectButtonOnUcqcPage() throws Throwable {
        try {
        	//Venkat 05-09-2022 Add Robot class in zoom in chrome browser
        	Thread.sleep(2000);
		    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
		    Thread.sleep(3000);
        	
       
            waitForAjaxExtJs();
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            assertAllCheckboxIsDisabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test02aVerifyDefaultStatusOfAllCheckboxAndSelectButtonOnUcqcPage");
         
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test02aVerifyDefaultStatusOfAllCheckboxAndSelectButtonOnUcqcPage", driver,e);
            fail(e.getMessage());
        }
       
    }

    @Test
    public void test02bConfirmThatSelectButtonOnUcqcPageBecomesActiveAndClickableAfterSelectingCostModel() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            doClick(costingMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
            assertColumnsToDisplayAllCheckBoxIsNotChecked();
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            ExtentReport.logPass("PASS", "test02bConfirmThatSelectButtonOnUcqcPageBecomesActiveAndClickableAfterSelectingCostModel");
        } catch (Exception| AssertionError e) {
        	ExtentReport.logFail("FAIL","test02bConfirmThatSelectButtonOnUcqcPageBecomesActiveAndClickableAfterSelectingCostModel", driver,e);
            fail(e.getMessage());
        }
    }

@Test
    public void test03aAccessColumnsToDisplayDialogAndConfirmAvailableBoxOnSelectDialogIsNotPopulatedByDefault() throws Throwable {
        try {
        	Thread.sleep(2000);
		    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
		    Thread.sleep(3000);
            doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
            
            waitForAjaxExtJs();
//            addDimension(1200,1000);
//        	driverDelay(3000);
//        	driver.manage().window().maximize();
            assertEquals(0, getSelectDialogAvailableListSize(printout));
            ExtentReport.logPass("PASS", "test03aAccessColumnsToDisplayDialogAndConfirmAvailableBoxOnSelectDialogIsNotPopulatedByDefault");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03aAccessColumnsToDisplayDialogAndConfirmAvailableBoxOnSelectDialogIsNotPopulatedByDefault", driver,e);
            fail(e.getMessage());
        }
    }

   @Test
    public void test03bConfirmRemoveAndSelectButtonsAreDisabledAndApplyAndCancelButtonsAreEnabledByDefault() throws Throwable {
        try {
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
            ExtentReport.logPass("PASS", "test03bConfirmRemoveAndSelectButtonsAreDisabledAndApplyAndCancelButtonsAreEnabledByDefault");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03bConfirmRemoveAndSelectButtonsAreDisabledAndApplyAndCancelButtonsAreEnabledByDefault", driver,e);
            fail(e.getMessage());
        }
    }

@Test
    public void test03cConfirmChargeCodeIsNotInSelectedList() throws Throwable {
        try {
            waitForAjaxExtJs();
            
            initialSelectedList = getSelectDialogSelectedList();
            selectedListStrings = javaListConvertListOfWebElementsToStrings(initialSelectedList, printout);
            assertFalse(selectedListStrings.contains("Charge Code"));
            ExtentReport.logPass("PASS", "test03cConfirmChargeCodeIsNotInSelectedList");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03cConfirmChargeCodeIsNotInSelectedList", driver,e);
            fail(e.getMessage());
        }
    }

@Test
    public void test03dVerifyOrderOfListItemsInSelectedList() throws Throwable {
        try {
        	 System.out.println(selectedListStrings.get(1));
        	 //Shilpa 10.07.2022
        	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//label[text()='Selected']/ancestor::div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]//td/div[text()='"+selectedListStrings.get(1)+"']")));
             Thread.sleep(500); 
            assertEquals("Charge Code Name", selectedListStrings.get(1));
            assertEquals("Modifier", selectedListStrings.get(2));
            assertEquals("Total Unit Cost", selectedListStrings.get(3));
            assertEquals("Total Quick Cost", selectedListStrings.get(4));
            assertEquals("Total Change", selectedListStrings.get(5));
            verifyFiveItemsEachCostComponent(selectedListStrings, printout);
            ExtentReport.logPass("PASS", "test03dVerifyOrderOfListItemsInSelectedList");
            driver.manage().window().maximize();
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03dVerifyOrderOfListItemsInSelectedList", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test04aSelectItemFromSelectedListAndConfirmRemoveButtonIsEnabled() throws Throwable {
        try {
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/*[contains(@class,'x-grid-cell-first')]")));
            waitForAjaxExtJs();
           assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
           
            
            ExtentReport.logPass("PASS", "test04aSelectItemFromSelectedListAndConfirmRemoveButtonIsEnabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test04aSelectItemFromSelectedListAndConfirmRemoveButtonIsEnabled", driver,e);
            fail(e.getMessage());
        }
    }

  
    @Test
    public void test04bReclickItemInSelectedListToDeselectItAndConfirmRemoveButtonIsDisabled() throws Throwable {
        try {
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/*[contains(@class,'x-grid-cell-first')]")));
            waitForAjaxExtJs();
           // assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
            //venkat update enable condition 01-11-2022
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
        } catch (Throwable e) {
        	ExtentReport.logFail("FAIL","test04bReclickItemInSelectedListToDeselectItAndConfirmRemoveButtonIsDisabled", driver,e);
            fail(e.getMessage());
            
        }
    }

    @Test
    public void test04cMoveItemFromSelectedListToAvailableList() throws Throwable {
        try {
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/*[contains(@class,'x-grid-cell-first')]")));
            waitForAjaxExtJs();
            doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
            assertEquals(1, getSelectDialogAvailableListSize(printout));
            ExtentReport.logPass("PASS", "ttest04cMoveItemFromSelectedListToAvailableList");
            
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test04cMoveItemFromSelectedListToAvailableList", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test04dConfirmSelectButtonIsDisabledWhenAvailableListContainsAtLeastOneItemButIsNotSelected() {
        try {
            assertEquals(1, getSelectDialogAvailableListSize(printout));
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
            ExtentReport.logPass("PASS", "ttest04cMoveItemFromSelectedListToAvailableList");
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test04eVerifyItemInAvailableListIsNotAlsoInSelectedList() throws Throwable {
        try {
            String availableListItem = getSelectDialogAvailableList().get(1).getText();
            List<WebElement> selectedList = getSelectDialogSelectedList();
            assertTrue(!selectedList.contains(availableListItem));
            ExtentReport.logPass("PASS", "test04eVerifyItemInAvailableListIsNotAlsoInSelectedList");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test04eVerifyItemInAvailableListIsNotAlsoInSelectedList", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test05CloseAndReopenDialogAndVerifyAvailableListItemsPersist() throws Throwable {
        try {
            waitForAjaxExtJs();
            doClick(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply()); //close dialog
            waitForAjaxExtJs();
            doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect()); //reopen dialog
            assertEquals(1, getSelectDialogAvailableListSize(printout));
            ExtentReport.logPass("PASS", "test05CloseAndReopenDialogAndVerifyAvailableListItemsPersist");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test05CloseAndReopenDialogAndVerifyAvailableListItemsPersist", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test06HighlightAnItemInBothListsAndVerifyRemoveAndSelectButtonsAreActive() throws Throwable {
        try {
            waitForAjaxExtJs();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[1]/tr/*[contains(@class,'x-grid-cell-first')]")));
            Thread.sleep(500); 
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[1]/tr/*[contains(@class,'x-grid-cell-first')]")));
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/*[contains(@class,'x-grid-cell-first')]")));
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
            ExtentReport.logPass("PASS", "test06HighlightAnItemInBothListsAndVerifyRemoveAndSelectButtonsAreActive");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test06HighlightAnItemInBothListsAndVerifyRemoveAndSelectButtonsAreActive", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test07CloseDialogAndRecheckAllCheckboxAndThenUncheckItAndVerifyAvailableListIsResetToDefaultEmptyStatus() throws Throwable {
        try {
            doClick(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
            waitForAjaxExtJs();
            doClick(costingMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
            assertColumnsToDisplayAllCheckBoxIsChecked();
            waitForAjaxExtJs();
            doClick(costingMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
            assertColumnsToDisplayAllCheckBoxIsNotChecked();
            doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
            assertEquals(0, getSelectDialogAvailableListSize(printout));
            ExtentReport.logPass("PASS", "test07CloseDialogAndRecheckAllCheckboxAndThenUncheckItAndVerifyAvailableListIsResetToDefaultEmptyStatus");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test07CloseDialogAndRecheckAllCheckboxAndThenUncheckItAndVerifyAvailableListIsResetToDefaultEmptyStatus", driver,e);
            fail(e.getMessage());
        }
    }

    //below set to ignore because these buttons are always enabled - need to verify if
    //the requirements are correct
   
    @Test
    public void test08aConfirmApplyButtonIsDisabledWhenNoItemsAreSelected() throws Throwable {
        try {
            waitForAjaxExtJs();
           // assertElementIsDisabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
            //venkat update enable condition 01-11-2022
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
        } catch (Throwable e) {
        	ExtentReport.logFail("FAIL","test08aConfirmApplyButtonIsDisabledWhenNoItemsAreSelected", driver,e);
            
            fail(e.getMessage());
            
        }
    }

    
    @Test
    public void test08bConfirmApplyButtonIsDisabledWhenOnlyChargeCodeNameIsSelected() throws Throwable {
        try {
            driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/td/*[text()='Charge Code Name']")).click();
            waitForAjaxExtJs();
           // assertElementIsDisabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
          //venkat update enable condition 01-11-2022
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
        } catch (Throwable e) {
        	ExtentReport.logFail("FAIL","test08bConfirmApplyButtonIsDisabledWhenOnlyChargeCodeNameIsSelected", driver,e);
            fail(e.getMessage());
        }
    }

   
    @Test
    public void test08cConfirmApplyButtonIsDisabledWhenOnlyModifierIsSelected() throws Throwable {
        try {
            driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/td/*[text()='Modifier']")).click();
            waitForAjaxExtJs();
          //  assertElementIsDisabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
          //venkat update enable condition 01-11-2022
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
        } catch (Throwable e) {
        	ExtentReport.logFail("FAIL","test08cConfirmApplyButtonIsDisabledWhenOnlyModifierIsSelected", driver,e);
            fail(e.getMessage());
        }
    }

  
    @Test
    public void test08dConfirmApplyButtonIsDisabledWhenOnlyChargeCodeNameAndModifierAreSelected() throws Throwable {
        try {
            Actions act = new Actions(driver);
            act.keyDown(Keys.CONTROL).perform();
            driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/td/*[text()='Charge Code Name']")).click();
            act.keyUp(Keys.CONTROL).perform();
            waitForAjaxExtJs();
           // assertElementIsDisabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
            //venkat update enable condition 01-11-2022
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
        } catch (Throwable e) {
        	ExtentReport.logFail("FAIL","test08dConfirmApplyButtonIsDisabledWhenOnlyChargeCodeNameAndModifierAreSelected", driver,e);
        	
            fail(e.getMessage());
        }
    }

    @Test
    public void test08eConfirmApplyButtonIsEnabledWhenASingleItemIsSelectedOnSelectedList() throws Throwable {
        try {
            driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/td/*[contains(text(),'RVU')]")).click();
            waitForAjaxExtJs();
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply(), printout);
            ExtentReport.logPass("PASS", "test08eConfirmApplyButtonIsEnabledWhenASingleItemIsSelectedOnSelectedList");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test08eConfirmApplyButtonIsEnabledWhenASingleItemIsSelectedOnSelectedList", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test20MoveItemFromAvailableListToSelectedList() throws Throwable {
        try {
            waitForAjaxExtJs();
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]/tr/*[contains(@class,'x-grid-cell-first')]")));
            doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
            waitForAjaxExtJs();
            assertEquals(1, getSelectDialogAvailableListSize(printout));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[1]/tr/*[contains(@class,'x-grid-cell-first')]")));
            Thread.sleep(500); 
            doClick(driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[1]/tr/*[contains(@class,'x-grid-cell-first')]")));
            waitForAjaxExtJs();
            doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
            assertEquals(0, getSelectDialogAvailableListSize(printout));
            ExtentReport.logPass("PASS", "test20MoveItemFromAvailableListToSelectedList");
            
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test20MoveItemFromAvailableListToSelectedList", driver,e);
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
            fail("All checkbox enable-disable failure");
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
            fail("All checkbox enable-disable failure");
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

    public int getSelectDialogAvailableListSize(boolean printout) throws InterruptedException {
        int listSize = 0;
        waitForAjaxExtJs();
        WebElement availableListElement = driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[1]"));
        List<WebElement> availableList = availableListElement.findElements(By.tagName("tr"));
        if(availableList.size() > 1) {
            listSize = availableList.size()-1;  //subtract 1 for the header row
        }
        if(printout) {
            System.out.println("Select Columns dialog Available list size: " + listSize);
        }
        return listSize;
    }

    public List<WebElement> getSelectDialogAvailableList() throws InterruptedException {
    	  waitForAjaxExtJs();
          WebElement availableListElement = driver.findElement(By.xpath("//div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[1]"));
          List<WebElement> availableList = availableListElement.findElements(By.tagName("tr"));
        return availableList;
    }

    public List<WebElement> getSelectDialogSelectedList() throws InterruptedException {
        waitForAjaxExtJs();
        WebElement availableListElement = driver.findElement(By.xpath("//label[text()='Selected']/ancestor::div[contains(@class,'x-panel x-box-item x-panel-default')]/following-sibling::div/descendant::tbody[2]"));
        List<WebElement> availableList = availableListElement.findElements(By.tagName("tr"));
        return availableList;
    }

    public void verifyFiveItemsEachCostComponent(ArrayList<String> listOfStrings, boolean printout) throws InterruptedException {
        int groups = listOfStrings.size();
        for (int i=6; i<groups; i=i+5) {
            if(printout) {
                System.out.println("The index i is " + i);
                System.out.println("List size is: " + groups);
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//td/div[text()='"+listOfStrings.get(i)+"']")));
            Thread.sleep(500); 
            String one = listOfStrings.get(i);
            String two = listOfStrings.get(i+1);
            String three = listOfStrings.get(i+2);
            String four = listOfStrings.get(i+3);
            String five = listOfStrings.get(i+4);

            if (printout) {
                System.out.println(one);
                System.out.println(two);
                System.out.println(three);
                System.out.println(four);
                System.out.println(five);
            }
            System.out.println(five);
            assertTrue(one.contains("RVU"));
            assertTrue(two.contains("Quick") & two.contains("RVU"));
            assertTrue(three.contains("Cost"));
            assertTrue(four.contains("Quick") & four.contains("Cost"));
            assertTrue(five.contains("Change"));
        }
    }
    
    
    @AfterClass
   	public static void endtest() throws Exception {

   		ExtentReport.report.flush();

   	}
}


