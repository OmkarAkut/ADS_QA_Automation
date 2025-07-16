package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import webdriver.scripts.contracting.contractmodels.ContractModelsHelper;
import webdriver.maps.DialogsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Fy2020IppsCostOutlierPaymentSectionServicesDialogAds1526 extends ContractModelsHelper {

  private static ModelLibraryMap modelMap;
  private static DialogsMap dialogsMap;
  private static final String contractModel = "AFT IPPS 2020";
  private static final String serviceModel = "OPPS 2019";

  String availableService1 = "# 02 Charge Item Svc";
  String availableService2 = "# CI PC Adv Service";

  /** Automates test ticket ADS-1526.  Dev Story ADS-1320 - Cost Outlier Payment Section.
   *  Focus is only on the Services Dialog, Filtering and Select All functionality).
   *  01-No selected Services displayed by Default (no change) (in pane near Select button on start page)
   *  02-Select button active (no changes)
   *   DEFAULTS
   *
   *   03-Select, Remove, and All buttons disabled by Default (no change)
   *   04-Apply and Cancel always enabled (no change)
   *   05-Select All checkbox unchecked by default
   *   06-All charge-level services listed in Available list and none selected by Default (no change)
   *
   *   07a-When Select All checked, Available list is emptied and <ALL> in grey appears in Selected list which is disabled
   *   07b-If then unchecked, returns to default state with all discharge status codes in Available list
   *
   *   08-Applied Filter should show expected results
   *   09-If any Filter is applied, Select All disables
   *
   *   10-Multi-Select of Charge-Level Services (in Available pane)*
   *   11- Apply closes popup and displays all selected services in the list below Select button
   *   12- Cancel closes popup and maintains same list of discharge status codes previously selected
   *   13- X closes popup and maintains same list of discharge status codes previously selected
   * **/
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    dialogsMap = BuildMap.getInstance(driver, DialogsMap.class);
    System.out.println("Test Class: " + Fy2020IppsCostOutlierPaymentSectionServicesDialogAds1526.class.getSimpleName());
    evolveLoginStaticUser(Users.ContractAnalyst1);
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Cost Outlier Payment");
  }

  @Test
  public void test01AssertSelectedServicesDisplayPaneIsEmptyByDefault() {
    boolean isDisplayed = false;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/div")).isDisplayed();
      fail("Selected services pane shouldn't be populated by default");
    } catch (Throwable e) {
      assertTrue("Selected services pane shouldn't be populated by default",isDisplayed == false);
    }
  }

  @Test
  public void test02AssertSelectButtonIsEnabledByDefault() {
    try {
      boolean isEnabled = driver.findElement(By.xpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::span[text()='Select']")).isEnabled();
      assertThat(isEnabled, equalTo(true));
    } catch (Throwable e) {
      fail("Select button not enabled by default");
    }
  }

  @Test
  public void test03VerifySelectAndRemoveAndAllButtonsAreDisabledByDefault() throws InterruptedException {
    Thread.sleep(1000);
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::span[text()='Select']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(2000);
    try {
      driver.findElement(By.xpath("//span[text()='Select']/parent::button[@disabled]"));
      driver.findElement(By.xpath("//span[text()='Remove']/parent::button[@disabled]"));
      driver.findElement(By.xpath("//span[text()='All']/parent::button[@disabled]"));
    } catch (Throwable e) {
      fail("Buttons not disabled by default");
    }
  }

  @Test
  public void test04VerifyApplyAndCancelButtonsAreEnabledByDefault() {
    assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Apply')]")).isEnabled());
    assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Cancel')]")).isEnabled());
  }

  @Test
  public void test05AssertSelectAllCheckboxIsNotCheckedByDefault() {
    String selectAllStatus = driver.findElement(By.xpath("//*[contains(text(),'Select All')]/ancestor::table[contains(@class,'checkBox')]")).getAttribute("class");
    assertThat(selectAllStatus, not(containsString("checked")));
  }

  @Test
  public void test06aVerifyServicesDialogAvailableListIsPopulatedByDefault() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    assertAvailablePaneValueIsGreaterThan(0);
  }

  @Test
  public void test06bVerifyServicesDialogSelectedListIsNotPopulatedByDefault() throws InterruptedException {
    assertSelectedPaneValue("0");
  }

  @Test
  public void test07aClickSelectAllCheckboxAndAssertAllDisplaysInSelectedPane() throws InterruptedException {
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
  public void test07bUncheckSelectAllCheckboxAndAssertAvailablePaneRepopulatesWithCodesListAndAllNoLongerDisplays() throws InterruptedException {
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
  public void test08VerifyAbilityToFilterAvailableCodesList() throws InterruptedException {
      try {
        doClick(driver.findElement(By.xpath("//span[text()='Filter']/following-sibling::span[contains(@class,'icon-expand')]")));
        waitForAjaxExtJs();
        Thread.sleep(1000);
        doDropdownSelectUsingOptionText(driver.findElement(By.xpath("//*[contains(@id,'filter')]/descendant::label[text()='Field']/../following-sibling::table/descendant::input")), "Name");
        dialogsMap.getFilterDialogFormFieldValue().sendKeys("# 02 Charge Item Svc");
        doClick(driver.findElement(By.xpath("//*[text()='Add']")));
        assertFilterNumberOfExpectedMatches(1);
        doClick(dialogsMap.getFilterDialogButtonApplyFilter());
        waitForSpinnerToEnd();
        waitForAjaxExtJs();
        Thread.sleep(1000);
        assertThatElementIsDisplayed(driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'# 02 Charge Item Svc')]")));
      } catch (Throwable e) {
        fail(e.getMessage());
      }
  }

  @Test
  public void test09AssertSelectAllCheckboxDisabledWhenFilterIsApplied() {
    try {
      String selectAllStatus = driver.findElement(By.xpath("//*[contains(text(),'Select All')]/ancestor::table[contains(@class,'checkBox')]")).getAttribute("class");
      assertThat(selectAllStatus, containsString("disabled"));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test10aClickRemoveAllAppliedFiltersAndApplyAndAssertFilterResultsValueIsGreaterThan1() throws InterruptedException {
    try {
      doClick(driver.findElement(By.xpath("//button/*[contains(text(),'Remove All')]")));
      waitForSpinnerToEnd();
      doClick(dialogsMap.getFilterDialogButtonApplyFilter());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertTrue(getFilterNumberOfExpectedMatches() > 1);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test10bAfterFiltersAreRemovedAssertAvailableListIsGreaterThan1() throws InterruptedException {
    try {
      assertAvailablePaneValueIsGreaterThan(1);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test11VerifyAbilityToMultiSelectCodesInAvailablePane() throws InterruptedException {
    try {
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      String initialPane1 = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'" + availableService1 + "')]/..")).getAttribute("class");
      initialPane1 = getNumbersFromStringWithRegex(initialPane1);
      String initialPane2 = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'" + availableService2 + "')]/..")).getAttribute("class");
      initialPane2 = getNumbersFromStringWithRegex(initialPane2);

      Actions act = new Actions(driver);
      act.keyDown(Keys.CONTROL).perform();
      driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'" + availableService1 + "')]")).click();
      driver.findElement(By.xpath("//label[contains(text(),'Item(s) Available of')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'" + availableService2 + "')]")).click();
      act.keyUp(Keys.CONTROL).perform();

      driver.findElement(By.xpath("//em/button/span[text()='Remove' and contains(@id,'button')]/../../parent::div/preceding-sibling::div/following-sibling::div/descendant::span[text()='Select']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      Thread.sleep(2000);

      String newPane1 = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'" + availableService1 + "')]/..")).getAttribute("class");
      newPane1 = getNumbersFromStringWithRegex(newPane1);
      String newPane2 = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]/ancestor::div[contains(@class, 'x-box-item')]/following-sibling::div/descendant::div[contains(text(),'" + availableService2 + "')]/..")).getAttribute("class");
      newPane2 = getNumbersFromStringWithRegex(newPane2);
      if (printout) {
      System.out.println("initialPane1 " + initialPane1);
      System.out.println("initialPane2 " + initialPane2);
      System.out.println("newPane1 " + newPane1);
      System.out.println("newPane2 " + newPane2);
      }
      assertThat(initialPane1, not(equalTo(newPane1)));
      assertThat(initialPane2, not(equalTo(newPane2)));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test12ClickApplyAndAssertSelectedCodesDisplayInTextField() throws InterruptedException {
    try {
      driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Apply')]")).click();
      verifyServiceIsDisplayedInTextfieldOnMainPage();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test13ClickCancelButtonAndAssertSelectedCodesDisplayInTextField() throws InterruptedException {
    try {
      driver.findElement(By.xpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::span[text()='Select']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      String selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
      assertThat(selectedList, equalTo("2"));
      driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Cancel')]")).click();
      verifyServiceIsDisplayedInTextfieldOnMainPage();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test14ClickXToCloseDialogAndAssertSelectedCodesDisplayInTextField() throws InterruptedException {
    try {
      driver.findElement(By.xpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::span[text()='Select']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      String selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
      assertThat(selectedList, equalTo("2"));
      driver.findElement(By.xpath("//div[contains(@class,'multiSelectorPopup')]/descendant::img[@class = 'x-tool-close']")).click();
      verifyServiceIsDisplayedInTextfieldOnMainPage();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test15ClearSelectedServicesAndVerifyTheyDoNotDisplayInTextFieldOnMainPage() throws InterruptedException {
    try {
      driver.findElement(By.xpath("//label[contains(text(),'Services/Charges excluded from Outlier Calculations')]/../descendant::span[text()='Select']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//*[@class='x-btn-icon icn-doubleLArr']/../*[contains(text(), 'All')]")));
      waitForSpinnerToEnd();
      String selectedList = driver.findElement(By.xpath("//label[contains(text(),'Item(s) Selected')]")).getText().substring(0,1);
      assertThat(selectedList, equalTo("0"));
      driver.findElement(By.xpath("//div[contains(@class,'selectBtnPad')]/descendant::span[contains(text(),'Apply')]")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      boolean isDisplayed = false;
      try {
        isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/div")).isDisplayed();
        fail("Selected services pane shouldn't be populated");
      } catch (Throwable e) {
        assertTrue("Selected services pane shouldn't be populated",isDisplayed == false);
      }
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  private void verifyServiceIsDisplayedInTextfieldOnMainPage() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    try {
      boolean isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/div[contains(text(),'" + availableService1 + "')]")).isDisplayed();
      assertTrue(isDisplayed == true);
    } catch (Throwable e) {
      fail("Selected Codes Not Displayed in textfield on main page");
    }
  }
}