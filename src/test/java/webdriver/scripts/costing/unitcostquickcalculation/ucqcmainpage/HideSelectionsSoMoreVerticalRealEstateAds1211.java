package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HideSelectionsSoMoreVerticalRealEstateAds1211 extends UcqcHelper {

  private static CostingMap costingMap;
  //static String[] requiredFields = {"Marina","*TB MHFY05 Before Vol Change","150 Marina Medical Center","2016  CHILDREN","Apr 2004 to Mar 2005"};

  /** Test ticket: ADS-1211 (dev story ADS-279).
   * This script tests the hide and show selections button on ucqc page. */
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + HideSelectionsSoMoreVerticalRealEstateAds1211.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
  }

  @Test
  public void test01ConfirmHideButtonIsDisplayedAndEnabledByDefault() {
    try {
      waitForAjaxExtJs();
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonHide());
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonHide(), printout);
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02aClickHideButtonAndAssertFieldsAreNotDisplayed() throws InterruptedException {
    doClick(costingMap.getUnitCostQuickCalculationButtonHide());
    waitForAjaxExtJs();
    String isDisplay = driver.findElement(By.xpath("//div[contains(@class,'benefitPlanListPanel')]")).getAttribute("style");
    System.out.println(isDisplay);
    assertThat(isDisplay, containsString("display: none"));
  }

  @Test
  public void test02bAfterHideButtonIsClickedAssertButtonsAreDisplayed() {
    try {
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonApplySelections());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonUndo());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCalculate());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03AssertShowButtonIsDisplayed() {
    try {
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonShow());
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ClickShowButtonAndAssertThatFieldsAreDisplayed() {
    try {
      doClick(costingMap.getUnitCostQuickCalculationButtonShow());
      waitForAjaxExtJs();
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationDropdownCostModel());
      assertThatElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@class,'benefitPlanListPanel')]")));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05AssertHideButtonIsDisplayed() {
    try {
      waitForAjaxExtJs();
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonHide(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
