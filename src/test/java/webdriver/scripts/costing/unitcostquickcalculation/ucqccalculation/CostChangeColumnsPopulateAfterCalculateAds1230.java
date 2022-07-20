package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostChangeColumnsPopulateAfterCalculateAds1230 extends UcqcHelper {

  private static CostingMap costingMap;
  static final String[] requiredFields = {
    "QA Marina",
    "ADS-302 LG By Month",
    "150 Marina Medical Center",
    "Q302  QA Dept for ADS-302",
    "Apr 2004 to Apr 2004"
  };
  final String[] costComponent = {
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

  final String[] chargeCodes = {
    "1100452",
    "1120021",
    "1406701",
  };

  /** Test ticket: ADS-1230 (Dev Story ADS-673).
  This script tests that the difference in cost between the newly calculated cost from UCQC by cost component
  as displayed in CC Quick Cost columns and previously calculated cost from the selected CMS as displayed in CC Cost column.
  The calculation formula is: CC Change =  Quick CC Cost - CC Cost */
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + CostChangeColumnsPopulateAfterCalculateAds1230.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    webdriverMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @Test
  public void test01ConfirmCalculatedValuesPopulateInCostComponentColumns() {
    for (String chargeCode : chargeCodes) {
      for (String cc : costComponent) {
        String changeValue = ucqcGetChargeCodeGridCellValue(chargeCode, cc + " Change", printout);
        try {
          assertThat(changeValue, containsString("."));
        } catch (Throwable e) {
          //for empty change cells, check that at least one calculating cell is empty
          assertTrue(ucqcGetChargeCodeGridCellValue(chargeCode, cc + " Cost", printout).equals(" ")
            || ucqcGetChargeCodeGridCellValue(chargeCode, "Quick " + cc + " Cost", printout).equals(" "));
        }
      }
    }
  }

  @Test
  public void test02VerifyChangeColumnValuesAreTwoDecimalPlaces() {
    for (String chargeCode : chargeCodes) {
      for (String cc : costComponent) {
        String changeValue = ucqcGetChargeCodeGridCellValue(chargeCode, cc + " Change", printout);
        if (changeValue.equals(" ")){
          continue;
        } else {
          assertThatValueHasRequiredDecimalPlaces(changeValue, 2, printout);
        }
      }
    }
  }

  @Ignore
  @Test
  public void test06AssertChangeColumnAllowsNegativeValues() throws InterruptedException {
    final String[] requiredFieldsNegativeCase = {
            "QA Marina",
            "ADS-302 LG In Total",
            "150 Marina Medical Center",
            "Q302  QA Dept for ADS-302",
            "Apr 2004 to Mar 2005"
    };
    ucqcDisplayChargeCodeGrid(requiredFieldsNegativeCase);
    String cellValue = ucqcGetChargeCodeGridCellValue("1120021", "Salaries and Wages Change", printout);
    assertValueIsNegative(cellValue);
  }

  @Test
  public void test04AssertChangeColumnValueIsLeftAligned() {
    WebElement changeCell = ucqcGetCellValueAsWebElement("1120021", "Salaries and Wages Change", printout);
    assertThatElementAlignment(changeCell.getAttribute("style"), "left", printout);
  }

  @Test
  public void test05ConfirmChangeColumnCalculationIsCorrect() {
    for (String chargeCode : chargeCodes) {
      try {
        for (String cc : costComponent) {
          if (ucqcGetChargeCodeGridCellValue(chargeCode, cc + " Change", printout).equals(" ")) {
            assertTrue(ucqcGetChargeCodeGridCellValue(chargeCode, cc + " Cost", printout).equals(" ")
              || ucqcGetChargeCodeGridCellValue(chargeCode, "Quick " + cc + " Cost", printout).equals(" "));
          } else {
            assertCCChangeTotalCalculation(chargeCode, cc + " Change", cc + " Cost", "Quick " + cc + " Cost");
          }
        }
      } catch (Throwable e) {
        fail(e.getMessage());
      }
    }
  }
}
