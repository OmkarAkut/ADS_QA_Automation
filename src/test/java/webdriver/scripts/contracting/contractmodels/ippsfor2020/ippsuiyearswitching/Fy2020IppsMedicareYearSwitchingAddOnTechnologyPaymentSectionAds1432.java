package webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
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
public class Fy2020IppsMedicareYearSwitchingAddOnTechnologyPaymentSectionAds1432 extends ContractModelsHelper {

  static String section = "Add On Technology Payment";
  private static EditContractingModelMap editModelMap;
  private static final String contractModel = "AFT IPPS 2020 - Saving";
  private static final String serviceModel = "OPPS 2019";
  String newMedicareYear = "Oct 1, 2019 - Sept 30, 2020";
  String previousMedicareYear = "Oct 1, 2018 - Sept 30, 2019";

  //Expected Values
  String previouslySelectedService = "# 03 Encounter";
  String serviceMaximum = "867.55";

  /** Automates test ticket ADS-1432 - Add-On Tech Payment Section. Dev Story ADS-1514.
   * Verifies that ui functions properly when Medicare year range is switched
   * between the previous ui (pre 2019 years) and the new updated ui
   * (2020 and beyond).  This test starts with the new year, then switches to old years,
   * then switches back to the new years ui.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + Fy2020IppsMedicareYearSwitchingAddOnTechnologyPaymentSectionAds1432.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection(section);
  }

  @Test
  public void test01AssertPreviouslySelectedServiceIsDisplayed() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'" + previouslySelectedService + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test02AssertPreviouslySelectedServiceMaximumIsDisplayed() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-numbercolumn')]/div[contains(text(),'" + serviceMaximum + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test05ChangeMedicareYearToPreviousYear() throws InterruptedException {
    waitForAjaxExtJs();
    navigateOpenNewSection("General");
    doChangeMedicareYearTo(previousMedicareYear);
    List<WebElement> op = getListOfSections(section);
    //List<WebElement> op = driver.findElements(By.xpath("//*[contains(@id,'customaccordianpanel')][text()='"+section+"']/parent::div"));
    navigateOpenNewSection(op.get(1));
  }

  @Test
  public void test11AssertPreviouslySelectedServiceIsDisplayedAfterClickingContinueAndCloseButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath(
              "//div[9]/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td[1]/div[contains(text(),'" + previouslySelectedService + "')]"))
              .isDisplayed()
      ;
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test12AssertPreviouslySelectedServiceMaximumIsDisplayedAfterClickingContinueAndCloseButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath(
              "//div[9]/div[2]/div[2]/div[2]/div/table/tbody/tr[2]/td[2]/div[contains(text(),'" + serviceMaximum + "')]"))
              .isDisplayed()
      ;
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test15ChangeMedicareYearToNewYear() throws InterruptedException {
    waitForAjaxExtJs();
    doChangeMedicareYearTo(newMedicareYear);
  }

  @Test
  public void test16AssertPreviouslySelectedServiceIsDisplayedAfterClickingSaveButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-gridcolumn')]/div[contains(text(),'" + previouslySelectedService + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

  @Test
  public void test17AssertPreviouslySelectedServiceMaximumIsDisplayedAfterClickingSaveButton() {
    boolean isDisplayed;
    try {
      isDisplayed = driver.findElement(By.xpath("//*[contains(text(),'Service')]/ancestor::div[contains(@class, 'x-grid-header-ct')]/following-sibling::div/descendant::td[contains(@class,'cell-numbercolumn')]/div[contains(text(),'" + serviceMaximum + "')]")).isDisplayed();
      assertThat(isDisplayed, equalTo(true));
    } catch (Throwable e) {
      fail("Previously Selected Service Is Not Displayed");
    }
  }

}
