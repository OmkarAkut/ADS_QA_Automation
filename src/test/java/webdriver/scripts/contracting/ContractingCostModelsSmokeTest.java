package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContractingCostModelsSmokeTest extends ContractModelsHelper {

  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Criteria Text";
  private static final String serviceModel = "OPPS 2019";
  private static List<WebElement> costMethods;

  /**
   * Automates test ticket ADS-1207-General Section. Dev Story ADS-1405.
   * Verifies text in Criteria box displays proper data for Medicare years 2020 (new ui)
   * and 2019 and before (previous ui) after changing the year.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + ContractingCostModelsSmokeTest.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
    navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
  }

  @Test
  public void test01Verify() throws InterruptedException {

    waitForAjaxExtJs();
    //navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
    costMethods = getCostMethodList();

    for (WebElement costMethod : costMethods) {
      System.out.println("1Cost Method: " + costMethod.getText());
      if (costMethod.getText().equals("<None>")) {
        continue;
      }
      System.out.println("2Cost Method: " + costMethod.getText());
      //waitForPresenceOfElement("//*[@name = 'pricemethodoption']");
      //webdriverClick(driver.findElement(By.name("pricemethodoption")));
      //waitForAjaxExtJs();
      ////costMethod.click();
      System.out.println("3Cost Method: " + costMethod.getText());
      //driver.findElement(By.xpath("//*[text()='Edit']")).click();
      //navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
      System.out.println("4Cost Method: " + costMethod.getText());
      assertTrue(driver.findElement(By.xpath("//span[contains(@id, 'header') and contains(text(), '" + costMethod.getText() + "')]")).isDisplayed());
      driver.findElement(By.xpath("//*[contains(@id, 'feeschedule') and contains(@id, 'header-inner')]/descendant::*[@class = 'x-tool-close']")).click();
    }
  }
    //    setDropdownMenuValue(
//            driver.findElement(By.name("pricemethodoption")),
//            driver.findElement(By.xpath("//body/div[18]/div/ul")),
//            "AP DRG Fee Schedule",
//            true
//    );

  private List<WebElement> getCostMethodList() throws InterruptedException {
    waitForPresenceOfElement("//*[@name = 'pricemethodoption']");
    waitForAjaxExtJs();
    webdriverClick(driver.findElement(By.name("pricemethodoption")));
    waitForAjaxExtJs();
    WebElement optionsUl;
    if (driver.findElement(By.xpath("//ul/descendant::li[text()='AP DRG Fee Schedule']/..")) != null) {
      optionsUl = driver.findElement(By.xpath("//ul/descendant::li[text()='AP DRG Fee Schedule']/.."));
    } else {
      optionsUl = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
    }
    return optionsUl.findElements(By.tagName("li"));
  }

  public void setDropdownMenuValue(WebElement triggerDropdown, WebElement menuOptionsUl, String value, boolean printout) throws InterruptedException {
    waitForAjaxExtJs();
    webdriverClick(triggerDropdown);
    waitForAjaxExtJs();
    WebElement optionsUl;
    if (menuOptionsUl != null) {
      optionsUl = menuOptionsUl;
    } else {
      optionsUl = driver.findElement(By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'listEl')]/ul"));
    }
    if (printout) {
      System.out.println("menuOptionsUl: " + menuOptionsUl);
    }
    List<WebElement> options = optionsUl.findElements(By.tagName("li"));
    for(WebElement option : options) {
      if(option.getText().equals(value)) {
        if (printout) {
          System.out.println("Menu option set to: " + option);
        }
        option.click();
        break;
      }
    }
  }
}
