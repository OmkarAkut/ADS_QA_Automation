package webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.scripts.contracting.contractmodels.ContractModelsHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IppsUi2020AreaWageIndexFunctionalityTestAds1623 extends ContractModelsHelper {

  private static final String serviceModel = "OPPS 2019";
  private static final String contractModel = "AFT IPPS 2020";

  /**
   * Automates test ticket ADS-1623. Sections of Dev Story ADS-1321 - Operating Payment section.
   * Automates Quality Data Submitter and Meaningful EHR User checkbox combinations
   * as they relate to autopopulating standard default values for the National Labor Rate and National Non-Labor Rate fields.
   **/
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + IppsUi2020AreaWageIndexFunctionalityTestAds1623.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    navigateToContractModelsPageFeeForServicePaymentTermsPageEditPriceEncounterDialog(contractModel, serviceModel);
    navigateCloseGeneralSectionOpenNewSection("Operating Payment");
  }

  @Test
  public void test00ExpandOperatingPaymentSectionAndAssertProperFieldLabelsAreDisplayed() throws InterruptedException {
    waitForAjaxExtJs();
    assertElementIsDisplayedWithXpath("//label[text()='Area Wage Index']");
    assertElementIsDisplayedWithXpath("//label[text()='National Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[text()='National Non-Labor Rate']");
    assertElementIsDisplayedWithXpath("//label[text()='Quality Data Submitter']");
    assertElementIsDisplayedWithXpath("//label[text()='Meaningful EHR User']");
    assertElementIsDisplayedWithXpath("//label[text()='Worst-Performing Quartile for HAC ']");
  }

  @Test
  public void test10AreaWageIndexIs2AndBothBoxesCheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    doUpdateFormFieldValue(driver.findElement(By.name("areaWageIndex")), "2", printout);
    driver.findElement(By.xpath("//label[text()='Area Wage Index']")).click();
    String expectedText = "2.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test11AreaWageIndexIs2AndFirstBoxIsCheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Meaningful EHR User']/preceding-sibling::input[contains(@class,'checkbox')]")).click();
    String expectedText = "2.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test12AreaWageIndexIs2AndBothBoxesAreUncheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Quality Data Submitter']/preceding-sibling::input[contains(@class,'checkbox')]")).click();
    String expectedText = "2.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test13AreaWageIndexIs2AndSecondBoxIsCheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Meaningful EHR User']/preceding-sibling::input[contains(@class,'checkbox')]")).click();
    String expectedText = "2.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test20AreaWageIndexIs1AndBothBoxesCheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    doUpdateFormFieldValue(driver.findElement(By.name("areaWageIndex")), "1", printout);
    driver.findElement(By.xpath("//label[text()='Area Wage Index']")).click();
    String expectedText = "1.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test21AreaWageIndexIs2AndFirstBoxIsCheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Meaningful EHR User']/preceding-sibling::input[contains(@class,'checkbox')]")).click();
    String expectedText = "1.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test22AreaWageIndexIs2AndBothBoxesAreUncheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Quality Data Submitter']/preceding-sibling::input[contains(@class,'checkbox')]")).click();
    String expectedText = "1.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  @Test
  public void test23AreaWageIndexIs2AndSecondBoxIsCheckedAssertLaborRates() throws InterruptedException {
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//label[text()='Meaningful EHR User']/preceding-sibling::input[contains(@class,'checkbox')]")).click();
    String expectedText = "1.0000";
    assertFieldDefaultValue(driver.findElement(By.name("areaWageIndex")), expectedText);
    verifyLaborRateValues();
  }

  private boolean meuCheckboxStatus() {
    boolean meuCheckbox;
    String meuStatus = driver.findElement(By.xpath("//label[text()='Meaningful EHR User']/ancestor::table")).getAttribute("class");
    if (meuStatus.contains("checked")) {
      meuCheckbox = true;
    } else {
      meuCheckbox = false;
    }
    return meuCheckbox;
  }

  private boolean qdsCheckboxStatus() {
    boolean qdsCheckbox;
    String qdsStatus = driver.findElement(By.xpath("//label[text()='Quality Data Submitter']/ancestor::table")).getAttribute("class");
    if (qdsStatus.contains("checked")) {
      qdsCheckbox = true;
    } else {
      qdsCheckbox = false;
    }
    return qdsCheckbox;
  }

  private void verifyLaborRateValues() {
    if (Double.parseDouble(driver.findElement(By.name("areaWageIndex")).getAttribute("value")) <= 1) {
      if (qdsCheckboxStatus() == true && meuCheckboxStatus() == true) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3596.70", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "2204.43", printout);
      } else if (qdsCheckboxStatus() == true && meuCheckboxStatus() == false) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3517.82", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "2156.09", printout);
      } else if (qdsCheckboxStatus() == false && meuCheckboxStatus() == true) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3570.41", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "2188.32", printout);
      } else if (qdsCheckboxStatus() == false && meuCheckboxStatus() == false) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3491.54", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "2139.97", printout);
      } else fail("Invalid Test Conditions");

    } else if (Double.parseDouble(driver.findElement(By.name("areaWageIndex")).getAttribute("value")) > 1) {
      if (qdsCheckboxStatus() == true && meuCheckboxStatus() == true) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3962.17", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "1838.96", printout);
      } else if (qdsCheckboxStatus() == true && meuCheckboxStatus() == false) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3875.28", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "1798.63", printout);
      } else if (qdsCheckboxStatus() == false && meuCheckboxStatus() == true) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3933.21", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "1825.52", printout);
      } else if (qdsCheckboxStatus() == false && meuCheckboxStatus() == false) {
        System.out.println("QDS: " + qdsCheckboxStatus());
        System.out.println("MEU: " + meuCheckboxStatus());
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperlaborRate")), "3846.32", printout);
        assertFormFieldExpectedValue(driver.findElement(By.name("nationalOperNonLaborRate")), "1785.19", printout);
      } else fail("Invalid Test Conditions");

    } else {
        fail("Invalid Test Data");
    }
  }
}