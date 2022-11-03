package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.ValidateTotalChangeValueCalculationStaticAds1278;

/** ADS-1181 (Overwrite RVU Maintenance popup with selections and buttons) (dev story ADS-741).
 * This script confirms the ability to choose cost components and the destination effective
 * month start on the Overwrite RVU Maintenance Pop Up for a copied Cost Model Scenario.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OverwriteRvuMaintenancePopupAds1181 extends UcqcHelper {

  private static CostingMap overwriteRvuMaint;
  String expectedRvuMaintenanceWarningMessage = "Decision Support will overwrite RVUs on the RVU Maintenance screen with the quick RVUs you selected. Charge codes that do not have results for the cost model scenario do not appear on this screen; RVUs associated with those codes will retain their original value. Click Overwrite to continue and replace the RVUs; or, click Cancel to return to the previous screen without overwriting the RVUs.";
  String[] costComponents = {
    "Salaries and Wages",
    "Employee Benefits",
    "Medical Supplies",
    "Non-Medical Supplies",
    "Equip Repair & Maint",
    "Direct Depreciation",
    "Purchased Services",
    "Professional Fees",
    "Other Expenses",
    "Direct Overhead",
    "Hospital Overhead",
    "Corporate Overhead",
    "Depreciation",
    "Tech"
  };

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("OverwriteRvuMaintenancePopupAds1181", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "OverwriteRvuMaintenancePopupAds1181");
    try {
		overwriteRvuMaint = BuildMap.getInstance(driver, CostingMap.class);
		doMaximizeWindow();
		System.out.println("Test Class: " + ValidateTotalChangeValueCalculationStaticAds1278.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test00VerifyUcqcGridDisplaysOverwriteRvuMaintenancePopup() throws Throwable {
    try {
      waitForAjaxExtJs();
      //Shilpa 19.09.2022 updated below line
//      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change_UCQC", "150 Marina Medical Center", "3145  ENDOSCOPY", "Jan 2005 to Jan 2005");
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change_UCQC", "150 Marina Medical Center", "3145", "Jan 2005 to Jan 2005");

      doClick(overwriteRvuMaint.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      Thread.sleep(1100);
      assertElementIsDisplayed(overwriteRvuMaint.getUnitCostQuickCalculationHeaderQuickSalariesAndWagesRVU(), printout);
      ExtentReport.logPass("PASS", "test00VerifyUcqcGridDisplaysOverwriteRvuMaintenancePopup");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test00VerifyUcqcGridDisplaysOverwriteRvuMaintenancePopup", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01ClickOverwriteRvuMaintenanceButtonAndAssertThatDestinationEffectiveMonthStartHasMonthAndYearDropdownMenus() throws Throwable {
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
		/**Confirm that Destination Effective Month Start selections are provided in a Month drop down and Year drop down.*/
		WebElement[] destinationEffectiveMonthStart = {
		  overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth(),
		  overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear()
		};
		assertElementsAreDisplayed(destinationEffectiveMonthStart, printout);
		ExtentReport.logPass("PASS", "test01ClickOverwriteRvuMaintenanceButtonAndAssertThatDestinationEffectiveMonthStartHasMonthAndYearDropdownMenus");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickOverwriteRvuMaintenanceButtonAndAssertThatDestinationEffectiveMonthStartHasMonthAndYearDropdownMenus", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02ConfirmMonthDropdownIncludesAllTwelveMonthsInAscendingOrderAndFormattedMmm() throws InterruptedException,Throwable {
    /**Confirm that Month drop down includes all 12 months in ascending month chronological order starting with January and the format in Mmm*/
    final List<String> expectedMonthNames = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    try {
		waitForAjaxExtJs();
		System.out.println("Locating Month menu");
		System.out.println(driver.findElement(By.xpath("//*[text()='Overwrite Options']")).getText());
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth());
		WebElement monthMenu = driver.findElement(By.xpath("//*[@name='selectedMonth']/ancestor::div/following-sibling::div/div/ul/li[text()='Jan']/.."));
		List<WebElement> actualMonths = monthMenu.findElements(By.tagName("li"));
		List<String> actualMonthNames = new ArrayList<>();
		for (WebElement month : actualMonths) {
		  System.out.println("Actual month listed: " + month.getText());
		  actualMonthNames.add(month.getText());
		}
		System.out.println(actualMonthNames.size());
		assertEquals(expectedMonthNames, actualMonthNames);
		validateMonthFormat(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth(), printout);
		ExtentReport.logPass("PASS", "test02ConfirmMonthDropdownIncludesAllTwelveMonthsInAscendingOrderAndFormattedMmm");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test02ConfirmMonthDropdownIncludesAllTwelveMonthsInAscendingOrderAndFormattedMmm", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03ConfirmDefaultMonthDisplayedIsCurrentMonth() throws InterruptedException,Throwable {

    /**Confirm that the default month displayed is the current server month*/
    /**Confirm that Year drop down will include years going back 20 years from current year and 2 years forward in ascending year chronological order*/
	    try {
			compareServerMonthAndDestinationEffectiveMonth();

  doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear());

   String yearXpath = "//*[@name='selectedYear']/ancestor::div/following-sibling::div/div/ul/li/../following::*/ul";
   WebElement yearMenu = driver.findElement(By.xpath(yearXpath));
   List<WebElement> actualYears = yearMenu.findElements(By.tagName("li"));
   List<String> actualYearNames = new ArrayList<>();
   for (WebElement year : actualYears) {
     System.out.println("Actual year listed: " + year.getText());
     actualYearNames.add(year.getText());
   }
   System.out.println(actualYearNames.size());

   int firstYear = Integer.parseInt(actualYearNames.get(0));
   int expectedCurrentYear = firstYear + 20;
   System.out.println("The first year in the dropdown is: " + actualYearNames.get(0));

   System.out.println("The expected current year is the first year in the dropdown list plus 20 years: " + expectedCurrentYear);
   int serverYear = getServerYear();
   if (serverYear == expectedCurrentYear) {
     System.out.println("The Destination Effective Month Start year dropdown includes years that are 20 years prior to the current year.");
   } else {
     System.out.println("The Destination Effective Month Start year dropdown does not include years that are 20 years prior to the current year.");
     fail();
   }

   int actualLastYear = Integer.parseInt(actualYearNames.get(22));
   int expectedLastYear = serverYear + 2;
   System.out.println("The expected last year in the dropdown list is the current year plus 2 years: " + expectedLastYear);
   if (expectedLastYear == actualLastYear) {
     System.out.println("The Destination Effective Month Start year dropdown includes years that are 2 years following the current year.");
   } else {
     System.out.println("The Destination Effective Month Start year dropdown does not include years that are 2 years following the current year.");
     fail();
   }
   ExtentReport.logPass("PASS", "test03ConfirmDefaultMonthDisplayedIsCurrentMonth");
	    } catch (Exception|AssertionError e) {
	    	ExtentReport.logFail("FAIL", "test03ConfirmDefaultMonthDisplayedIsCurrentMonth", driver, e);
			fail(e.getMessage());
		}
  }

  @Test
  public void test04ConfirmThatYearFormatIsYyyyAndDefaultYearIsCurrentYear() throws InterruptedException,Throwable {
    /**Confirm that the year format is YYYY and the default year is the current server year*/
    try {
		compareServerYearAndDestinationEffectiveYear();
		validateYearFormat(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear(), printout);
		 ExtentReport.logPass("PASS", "test04ConfirmThatYearFormatIsYyyyAndDefaultYearIsCurrentYear");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test04ConfirmThatYearFormatIsYyyyAndDefaultYearIsCurrentYear", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test05ConfirmThatCostComponentsSectionContainsLabelSourceAndDestinationCostComponents() throws InterruptedException,Throwable {
    /**Confirm that the cost components section is displayed with a label stating, "Source and Destination Cost Components"*/
    try {
		assertElementText(driver.findElement(By.xpath("//label[contains(@class,'x-form-item-label')][text()='Source and Destination Cost Components']")), "Source and Destination Cost Components *", printout);
		ExtentReport.logPass("PASS", "test05ConfirmThatCostComponentsSectionContainsLabelSourceAndDestinationCostComponents");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test05ConfirmThatCostComponentsSectionContainsLabelSourceAndDestinationCostComponents", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test06ConfirmSelectAllAndRemoveAllbuttonsAreAlwaysEnabled() throws Exception,Throwable {
    /**Confirm that the "Select All" and "Remove All" buttons are always enabled*/
    try {
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonSelectAll(), printout);
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonRemoveAll(), printout);
		ExtentReport.logPass("PASS", "test06ConfirmSelectAllAndRemoveAllbuttonsAreAlwaysEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test06ConfirmSelectAllAndRemoveAllbuttonsAreAlwaysEnabled", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test07ConfirmThatCostComponentsSectionContainsLabelSourceAndDestinationCostComponents() throws Exception,Throwable {
    /**Confirm that by default, all cost components are unchecked, Overwrite RVUs button is disabled
     and the Cancel & Close button is enabled*/
    try {
		String[] costComponents = {"Salaries and Wages", "Employee Benefits", "Medical Supplies", "Non-Medical Supplies", "Equip Repair & Maint", "Direct Depreciation","Purchased Services","Professional Fees","Other Expenses","Direct Overhead","Hospital Overhead","Corporate Overhead","Depreciation","Tech"};
		for (String costComponent : costComponents) {
		  verifyCheckBox(costComponent, printout);
		}
		assertElementIsDisabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs(),printout);
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose(),printout);
		ExtentReport.logPass("PASS", "test07ConfirmThatCostComponentsSectionContainsLabelSourceAndDestinationCostComponents");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test07ConfirmThatCostComponentsSectionContainsLabelSourceAndDestinationCostComponents", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test09ConfirmThatwhenSelectAllIsClickedAllCostComponentsAreCheckedAndOverwriteRvusAndCancelAndClosebuttonsRemainEnabled() throws Exception,Throwable {
    /**Confirm that when "Select All" is clicked all of the Cost Components are checked and Overwrite RVUs and Cancel & Close buttons remain enabled*/
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonSelectAll());
		for (String costComponent : costComponents) {
		  verifyCheckBox(costComponent, printout);
		}
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs(),printout);
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose(),printout);
	
		ExtentReport.logPass("PASS", "test09ConfirmThatwhenSelectAllIsClickedAllCostComponentsAreCheckedAndOverwriteRvusAndCancelAndClosebuttonsRemainEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test09ConfirmThatwhenSelectAllIsClickedAllCostComponentsAreCheckedAndOverwriteRvusAndCancelAndClosebuttonsRemainEnabled", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test10ConfirmWhenRemoveAllbuttonIsClickedAllCostComponentsAreUncheckedAndAllPreviouslyUncheckedCostComponentsRemainUnchecked() throws Exception,Throwable {
    /**Confirm that when "Remove All" button is clicked all cost components are unchecked, and all previously unchecked cost components remain unchecked*/
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonRemoveAll());
		for (String costComponent : costComponents) {
		  verifyCheckBox(costComponent, printout);
		}
		ExtentReport.logPass("PASS", "test10ConfirmWhenRemoveAllbuttonIsClickedAllCostComponentsAreUncheckedAndAllPreviouslyUncheckedCostComponentsRemainUnchecked");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test10ConfirmWhenRemoveAllbuttonIsClickedAllCostComponentsAreUncheckedAndAllPreviouslyUncheckedCostComponentsRemainUnchecked", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test11ConfirmThatWhenRemoveAllIsClickedThatOverwriteRvusButtonIsDisabledAndCancelAndCloseButtonIsEnabled() throws Exception,Throwable {
    /**Confirm that when "Remove All" is clicked that the Overwrite RVUs is disabled and Cancel & Close button  is enabled*/
    try {
		assertElementIsDisabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs(),printout);
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose(),printout);
		ExtentReport.logPass("PASS", "test11ConfirmThatWhenRemoveAllIsClickedThatOverwriteRvusButtonIsDisabledAndCancelAndCloseButtonIsEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test11ConfirmThatWhenRemoveAllIsClickedThatOverwriteRvusButtonIsDisabledAndCancelAndCloseButtonIsEnabled", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test12CheckTheSalaryAndWagesCostComponentAndConfirmThatTheOverwriteRvusButtonAndTheCancelAndCloseButtonAreNowEnabled() throws Exception,Throwable {
    /**Check the "Salary and Wages" Cost Component and confirm that the Overwrite RVUs button and the Cancel & Close button are now enabled*/
    try {
		selectCostComponent("Salaries and Wages",printout);
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs(),printout);
		assertElementIsEnabled(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose(),printout);
		ExtentReport.logPass("PASS", "test12CheckTheSalaryAndWagesCostComponentAndConfirmThatTheOverwriteRvusButtonAndTheCancelAndCloseButtonAreNowEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test12CheckTheSalaryAndWagesCostComponentAndConfirmThatTheOverwriteRvusButtonAndTheCancelAndCloseButtonAreNowEnabled", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test13aClickTheOverwriteRvusButtonAndConfirmWarningMessageIsDisplayed() throws Exception,Throwable{
    /**Click the Overwrite RVUs button and confirm a warning message is displayed stating, "﻿Decision Support will overwrite RVUs on the RVU Maintenance screen with the quick RVUs you selected.
     **/
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
		assertElementText(driver.findElement(By.xpath("//div[contains(@class,'errorMsg')]")),expectedRvuMaintenanceWarningMessage,printout);
		waitForAjaxExtJs();
		ExtentReport.logPass("PASS", "test13aClickTheOverwriteRvusButtonAndConfirmWarningMessageIsDisplayed");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test13aClickTheOverwriteRvusButtonAndConfirmWarningMessageIsDisplayed", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test13bClickCancelButtonToReturnToPreviousScreenWithoutOverwritingRvus() throws Exception,Throwable {
    //click Cancel to return to the previous screen without overwriting the RVUs."*/
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel());
		//assertElementText(driver.findElement(By.xpath("//div[contains(@class,'errorMsg')]")),expectedRvuMaintenanceWarningMessage,printout);
		waitForAjaxExtJs();
		//Shilpa: 28.10.2022 not required below line
//		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose());
		ExtentReport.logPass("PASS", "test13bClickCancelButtonToReturnToPreviousScreenWithoutOverwritingRvus");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test13bClickCancelButtonToReturnToPreviousScreenWithoutOverwritingRvus", driver, e);
		fail(e.getMessage());
	}
  }

 
  @Test
  public void test14aClickTheOverwriteRvusButtonAndConfirmWarningMessageIsDisplayed() throws Exception,Throwable {
    /**Click the Overwrite RVUs button and confirm a warning message is displayed stating, "﻿Decision Support will overwrite RVUs on the RVU Maintenance screen with the quick RVUs you selected.
     **/
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
		assertElementText(driver.findElement(By.xpath("//div[contains(@class,'errorMsg')]")),expectedRvuMaintenanceWarningMessage,printout);
		waitForAjaxExtJs();
		ExtentReport.logPass("PASS", "test14aClickTheOverwriteRvusButtonAndConfirmWarningMessageIsDisplayed");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test14aClickTheOverwriteRvusButtonAndConfirmWarningMessageIsDisplayed", driver, e);
		fail(e.getMessage());
	}
  }

 
  @Test
  public void test14bClickOverwriteToContinueAndReplaceTheRvus() throws Exception,Throwable {
    //Click Overwrite to continue and replace the RVUs
    /**Click the Overwrite button*/
    try {
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
		waitForSpinnerToEnd();
		Thread.sleep(1200);
		/**Click the Overwrite RVU Maintenance button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
		/**Check the "Employees Benefits" Cost Component*/
		waitForAjaxExtJs();
		selectCostComponent("Employee Benefits",printout);
		/**Click the Cancel & Close button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose());
		/**Click the Overwrite RVU Maintenance button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
		waitForAjaxExtJs();
		/**Check the "Employee Benefits" Cost Component*/
		selectCostComponent("Employee Benefits",printout);
		/**Click the Overwrite RVUs button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
		waitForAjaxExtJs();
		/**On the Warning pop up click the Cancel button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel());
		/**Click the Overwrite RVUs button on the Overwrite RVU Maintenance popup*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
		waitForAjaxExtJs();
		/**Click "x" to close the warning dialog*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonClose());
		waitForAjaxExtJs();
		/**Click the Overwrite RVUs button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
		waitForAjaxExtJs();
		/**Click the Overwrite button*/
		doClick(overwriteRvuMaint.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
		ucqcWaitForSpinnerToEnd();
		ExtentReport.logPass("PASS", "test14bClickOverwriteToContinueAndReplaceTheRvus");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test14bClickOverwriteToContinueAndReplaceTheRvus", driver, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
