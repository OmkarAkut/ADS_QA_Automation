package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsGeneralSectionDischargeStatusCodeForTransfersDialogAds1510 extends ContractModelsHelper {

  private static ContractingMap contractingMap;
  private static ModelLibraryMap modelMap;
  private static DialogsMap dialogsMap;
  private static final String contractModel = "AFT IPPS 2020";
  private static final String serviceModel = "OPPS 2019";

  /** Automates test ticket ADS-1510. Dev Story ADS-1320 - General Section.
   * Focus is only on the Discharge Status Code For Transfers Dialog,
   * Filtering and Select All functionality).
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    dialogsMap = BuildMap.getInstance(driver, DialogsMap.class);
    System.out.println("Test Class: " + Fy2020IppsGeneralSectionDischargeStatusCodeForTransfersDialogAds1510.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    assertElementTextWithXpath("//span[contains(@id, 'medicareinpatientpps')]", "Edit Price for " + serviceModel + " [Encounter]", printout);
    Thread.sleep(1000);
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
  }

  @Test
  public void test23AssertSelectAllCheckboxIsNotCheckedByDefault() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    String selectAllStatus = driver.findElement(By.xpath("//*[contains(text(),'Select All')]/ancestor::table[contains(@class,'checkBox')]")).getAttribute("class");
    assertThat(selectAllStatus, not(containsString("checked")));
  }

  @Test
  public void test24ClickSelectAllCheckboxAndAssertAllDisplaysInSelectedPane() throws InterruptedException {
    assertAvailablePaneValueIsGreaterThan(0);
    assertSelectedPaneValue("0");
    driver.findElement(By.xpath("//*[contains(text(),'Select All')]/preceding-sibling::input")).click();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    assertAvailablePaneValueIsZero();
    String assertAll = null;
    try {
      assertAll = driver.findElement(By.xpath("//*[contains(@class,'glAccountsGrid')]/descendant::div[text()='<ALL>']")).getText();
    } catch (Throwable e) {
      fail("Element not displayed on page");
    }
    assertThat(assertAll, equalTo("<ALL>"));
  }

  @Test
  public void test25UncheckSelectAllCheckboxAndAssertAvailablePaneRepopulatesWithCodesListAndAllNoLongerDisplays() throws InterruptedException {
    driver.findElement(By.xpath("//*[contains(text(),'Select All')]/preceding-sibling::input")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    assertAvailablePaneValueIsGreaterThan(0);
    assertSelectedPaneValue("0");
    String assertAll = null;  //verify <All> is no longer displayed
    try {
      assertAll = driver.findElement(By.xpath("//*[contains(@class,'glAccountsGrid')]/descendant::div[text()='<ALL>']")).getText();
      fail("<All> still displays in Selected pane");
    } catch (Throwable e) {
      assertNull(assertAll);
    }
  }

  @Test
  public void test26VerifyAbilityToFilterAvailableCodesList() throws InterruptedException {
    doClick(driver.findElement(By.xpath("//span[text()='Filter']/following-sibling::span[contains(@class,'icon-expand')]")));
    doDropdownSelectUsingOptionText(driver.findElement(By.xpath("//*[contains(@id,'filter')]/descendant::label[text()='Field']/../following-sibling::table/descendant::input")), "Code");
    dialogsMap.getFilterDialogFormFieldValue().sendKeys("01");
    doClick(driver.findElement(By.xpath("//*[text()='Add']")));
    waitForSpinnerToEnd();
    String filterResultsRatio = driver.findElement(By.xpath("//*[contains(text(),'Filter to Match These Criteria')]")).getText();
    String[] filterResults = filterResultsRatio.split(" ");
    assertTrue(filterResults[5].contains("1/"));
    doClick(dialogsMap.getFilterDialogButtonApplyFilter());
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    assertThatElementIsDisplayed(driver.findElement(By.xpath("//tbody/tr[contains(@class,'grid-row')]/td/div[contains(text(),'01 DISCHARGE')]/..")));
  }

  @Test
  public void test27AssertSelectAllCheckboxDisabledWhenFilterIsApplied() {
    String selectAllStatus = driver.findElement(By.xpath("//*[contains(text(),'Select All')]/ancestor::table[contains(@class,'checkBox')]")).getAttribute("class");
    assertThat(selectAllStatus, containsString("disabled"));
  }
}
