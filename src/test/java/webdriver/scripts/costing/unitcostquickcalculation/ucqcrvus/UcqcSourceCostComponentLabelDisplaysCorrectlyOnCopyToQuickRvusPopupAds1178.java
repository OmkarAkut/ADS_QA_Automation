package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcSourceCostComponentLabelDisplaysCorrectlyOnCopyToQuickRvusPopupAds1178 extends UcqcHelper {

  private static CostingMap ucqcMap;
  private String optionText = "Copy Quick RVUs from Source Cost Component to Others";
  private String expectedLabelText = "Source Cost Component";

  /**
   * Zephyr ticket ADS-1178 (Dev Story ADS-699).  Last Updated 08-14-19
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + UcqcSourceCostComponentLabelDisplaysCorrectlyOnCopyToQuickRvusPopupAds1178.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
  }

  @Test
  public void testUcqcSourceCostComponentLabelDisplaysCorrectlyOnCopyToQuickRvusPopup() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid("QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005");
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
      doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      doClick(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//*[@name='copyType']")));
      doDropdownSelectUsingOptionTextRequiresInitialElementClick(driver.findElement(By.xpath("//*[@name='copyType']/ancestor::table/following::ul")), optionText, printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//label[text()='" + expectedLabelText + "']")), printout);
      doClick(driver.findElement(By.xpath("//span[contains(@id,'button') and contains (text(),'Cancel & Close')]")));
      waitForAjaxExtJs();
      doClosePageOnLowerBar("Unit Cost Quick...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  private static void doDropdownSelectUsingOptionTextRequiresInitialElementClick(WebElement listElement, String optionText, boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    List<WebElement> menu = listElement.findElements(By.tagName("li"));  //list element is typically ul tag that must contain li options
    for (WebElement option : menu) {
      if (printout) {
        System.out.println("Try option: " + option);
      }
      if (option.getText().equals(optionText)) {
        if (printout) {
          System.out.println("Selected Option Text: " + option);
        }
        option.click();
        break;
      }
    }
  }
}

