package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import ExtentReport.ExtentReport;
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
    String[] setRequiredFields = {"Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
    	//	"2130  PED ICU",
    		"2130", //venkata update Text data 13.09.2022
//    		"Jan 2005 to Jan 2005"};
//    		"Jan 2005 to Mar 2005"};//Shilpa updated test data for 11.2 on 11.4.2024
			"Jan 2005 to Jan 2005"};//Shilpa updated test data for 11.2 on 01.20.2024
    @BeforeClass
    public static void setupScript() throws Throwable {
    	
    	 ExtentReport.reportCreate("UcqcColumnsToDisplaySectionAppearsAds1154","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "UcqcColumnsToDisplaySectionAppearsAds1154");
    	    
        try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + UcqcColumnsToDisplaySectionAppearsAds1154.class.getSimpleName());
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
    public void test01VerifyColumnsToDisplayIsRequired() throws Throwable {
        try {
            waitForAjaxExtJs();
            Thread.sleep(1000);
           
            
            assertAsteriskIsDisplayed("Columns to Display");
            
            ExtentReport.logPass("PASS", "test01VerifyColumnsToDisplayIsRequired");
            
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test01VerifyColumnsToDisplayIsRequired", driver,e);
            fail(e.getMessage());
        }
        driver.manage().window().maximize();
    }

    @Test
    public void test02VerifyDefaultStatusOfAllCheckboxAndSelectButton() throws Throwable {
        try {
            waitForAjaxExtJs();
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(), printout);
            assertAllCheckboxIsDisabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test02VerifyDefaultStatusOfAllCheckboxAndSelectButton");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test02VerifyDefaultStatusOfAllCheckboxAndSelectButton", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test03aSetCostModelAndVerifyAllCheckboxIsSelectedAndEnabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test03aSetCostModelAndVerifyAllCheckboxIsSelectedAndEnabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03aSetCostModelAndVerifyAllCheckboxIsSelectedAndEnabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test03bSetCostModelToNoneAndVerifyAllCheckboxIsSelectedAndDisabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "<None>");
            waitForAjaxExtJs();
            assertAllCheckboxIsDisabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test03bSetCostModelToNoneAndVerifyAllCheckboxIsSelectedAndDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03bSetCostModelToNoneAndVerifyAllCheckboxIsSelectedAndDisabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test03cSetNewCostModelAndVerifyAllCheckboxIsSelectedAndEnabled() throws Throwable {
        try {
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "QA Marina");
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test03cSetNewCostModelAndVerifyAllCheckboxIsSelectedAndEnabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test03cSetNewCostModelAndVerifyAllCheckboxIsSelectedAndEnabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test04UncheckAllCheckboxAndVerifySelectedButtonIsEnabled() throws Throwable {
        try {
        	
			waitForAjaxExtJs();
//			Omkar 19/2/2024 : xpath changes for 11.2
//            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::span/input")));
            System.out.println("ENter");
            waitForAjaxExtJs();
            assertAllCheckboxIsEnabled(printout);
            assertColumnsToDisplayAllCheckBoxIsNotChecked();
            ExtentReport.logPass("PASS", "test04UncheckAllCheckboxAndVerifySelectedButtonIsEnabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test04UncheckAllCheckboxAndVerifySelectedButtonIsEnabled", driver,e);
            fail(e.getMessage());
        }
     
        
    }

    @Test
    public void test05CheckAllCheckboxAndVerifySelectedButtonIsDisabled() throws Throwable {
        try {
        	
			
            waitForAjaxExtJs();
//            Omkar 19/2/2024 : xpath changes for 11.2
//          doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
            doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::span/input")));
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertAllCheckboxIsEnabled(printout);
            assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
            ExtentReport.logPass("PASS", "test05CheckAllCheckboxAndVerifySelectedButtonIsDisabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test05CheckAllCheckboxAndVerifySelectedButtonIsDisabled", driver,e);
            fail(e.getMessage());
        }
       
    }

    @Test
    public void test06SetRequiredFieldsAndEnsureAllCheckboxIsCheckedAndVerifyApplySelectionsButtonIsEnabled() throws Throwable {
        try {
            resetRequiredFields();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
            ExtentReport.logPass("PASS", "test06SetRequiredFieldsAndEnsureAllCheckboxIsCheckedAndVerifyApplySelectionsButtonIsEnabled");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test06SetRequiredFieldsAndEnsureAllCheckboxIsCheckedAndVerifyApplySelectionsButtonIsEnabled", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test07SetCostModelToNoneAndEnsureAllCheckboxIsUnchanged() throws Throwable {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test07SetCostModelToNoneAndEnsureAllCheckboxIsUnchanged");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test07SetCostModelToNoneAndEnsureAllCheckboxIsUnchanged", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test08SetCostModelScenarioToNoneAndEnsureAllCheckboxIsUnchanged() throws Throwable {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test08SetCostModelScenarioToNoneAndEnsureAllCheckboxIsUnchanged");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test08SetCostModelScenarioToNoneAndEnsureAllCheckboxIsUnchanged", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test09SetEntityToNoneAndEnsureAllCheckboxIsUnchanged() throws Throwable {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(), costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test09SetEntityToNoneAndEnsureAllCheckboxIsUnchanged");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test09SetEntityToNoneAndEnsureAllCheckboxIsUnchanged", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test10SetDepartmentToNoneAndEnsureAllCheckboxIsUnchanged() throws Throwable {
        try {
            resetRequiredFields();
            updateDepartment("<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test10SetDepartmentToNoneAndEnsureAllCheckboxIsUnchanged");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test10SetDepartmentToNoneAndEnsureAllCheckboxIsUnchanged", driver,e);
            fail(e.getMessage());
        }
    }

    @Test
    public void test11SetResultsStoredForToNoneAndEnsureAllCheckboxIsUnchanged() throws Throwable {
        try {
            resetRequiredFields();
            doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(), "<None>");
            waitForAjaxExtJs();
            assertColumnsToDisplayAllCheckBoxIsChecked();
            ExtentReport.logPass("PASS", "test11SetResultsStoredForToNoneAndEnsureAllCheckboxIsUnchanged");
        } catch (Exception|AssertionError e) {
        	ExtentReport.logFail("FAIL","test11SetResultsStoredForToNoneAndEnsureAllCheckboxIsUnchanged", driver,e);
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
//            Omkar 19/2/2024 : xpath changes for 11.2
//            classText = driver.findElement(By.xpath("//label[text()='All']/ancestor::*[contains(@class,'x-form-cb')]")).getAttribute("class");
//            boolean isEnabled = !(classText.contains("disabled"));
            classText = driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::span/input")).getAttribute("aria-disabled");
            boolean isEnabled = !(classText.contains("true"));		
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
//            Omkar 19/2/2024 : xpath changes for 11.2
//          columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
          columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkbox')]")).getAttribute("class");
     
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
//            Omkar 19/2/2024 : xpath changes for 11.2
//            columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
            columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkbox')]")).getAttribute("class");
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
    
    @AfterClass
   	public static void endtest() throws Exception {

   		ExtentReport.report.flush();

   	}
}


