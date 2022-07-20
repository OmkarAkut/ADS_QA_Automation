package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcNavigateOnLineHelpAds1428
        extends UcqcHelper {

  static String firstHandle;
  static CostingMap ucqcMap;
  static String[] ucqcRequiredFields = {
      "Marina",
      "*CM1 TB MHFY05 After Vol Change_UCQC",
      "150 Marina Medical Center",
      "3145  ENDOSCOPY",
      "Jan 2005 to Jan 2005"
  };

  /**
   Zephyr Test Ticket: ADS-1428 (no dev story-regression only).
   This script tests that the Help links function properly across the UCQC pages.
  */
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println(
        "Test Class: " + UcqcNavigateOnLineHelpAds1428.class.getSimpleName()
    );
    loginStaticUser(Users.CostingDepartmentManager1);
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(ucqcRequiredFields);
  }

  @AfterClass
  public static void teardownScript() {
    driver.switchTo().window(firstHandle);
  }

  @Test
  public void test01VerifyHelpLinkForCopyToQuickRvuDialog() {
    try {
      doClick(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
      waitForAjaxExtJs();
      assertHelpLink(
              getWebElement("//*[contains(@onclick, 'csucqccpfd.htm')]"),
              "Copy To Quick RVUs",
              printout
      );
      doClickButton("Cancel & Close");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02VerifyHelpLinkForOverwriteRvuMaintenanceDialog() throws InterruptedException {
    doClick(ucqcMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
    waitForAjaxExtJs();
    assertHelpLink(
            getWebElement("//*[contains(@onclick,'csucqcovfd.htm')]"),
            "Overwrite RVU Maintenance",
            printout
    );
    doClickButton("Cancel & Close");
  }

  //NOTE: This test is also in ADS-1113.
  @Test
  public void test03VerifyHelpLinkForColumnsToDisplayDialog() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    ucqcMap.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll().click();
    ucqcMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect().click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    assertHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'selectorfd.htm')]")),
            "Selector", printout);
    ucqcMap.getUnitCostQuickCalculationColumnsToDisplayModalCancel().click();
  }

  @Test
  public void test04VerifyOnlineHelpPagesForUcqcSection() throws Exception {
    //Thread.sleep(500);
    waitForAjaxExtJs();
    firstHandle = webSwitchToNewWindow(
        ucqcMap.getUnitCostQuickCalculationPageHelpLink(),
        printout
    );
    assertHelpPageHeader("Unit Cost Quick Calculation", printout);
  }

  @Test
  public void test07aVerifyOnlineHelpPagesForUcqcSectionProceduresDisplayAndUpdateValues()
          throws InterruptedException {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Costing")));
    doClick(getWebElement(getHelpTocXpath("Procedures")));
    driverDelay();
    doClick(getWebElement(getHelpTocXpath("Unit Cost Quick Calculation")));
    driverWait();
    doClick(getWebElement(getHelpTocXpath("Display and Update Values")));
    assertHelpPageHeader(
            "Display and Update Values on the Unit Cost Quick Calculation Screen",
            printout
    );
  }

  @Test
  public void test07bVerifyOnlineHelpPagesForUcqcSectionProceduresCopyDataToQuickRvus() {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Copy Data to Quick RVUs")));
    assertHelpPageHeader(
            "Copy Data to Quick RVUs",
            printout
    );
  }

  @Test
  public void test07cVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus() {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Clear Quick RVUs")));
    assertHelpPageHeader(
            "Clear Quick RVUs",
            printout
    );
  }

  @Test
  public void test07dVerifyOnlineHelpPagesForUcqcSectionProceduresCalculateQuickCosts() {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Calculate Quick Costs")));
    assertHelpPageHeader(
            "Calculate Quick Costs",
            printout
    );
  }

  @Test
  public void test07eVerifyOnlineHelpPagesForUcqcSectionProceduresClearQuickRvus() {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Calculate Quick Costs")));
    assertHelpPageHeader(
            "Calculate Quick Costs",
            printout
    );
  }

  @Test
  public void test07fVerifyOnlineHelpPagesForUcqcSectionProceduresOverwriteRvusWithDataFromQuickRvus() {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Overwrite RVUs with Data from Quick RVUs")));
    assertHelpPageHeader(
            "Overwrite RVUs with Data from Quick RVUs",
            printout
    );
  }

  @Test
  public void test08aVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsUcqc()
          throws InterruptedException {
    driver.switchTo().parentFrame();
    doClick(getWebElement(getHelpTocXpath("Field Descriptions")));
    driverDelay(2000);
    doClick(getWebElement("//li[28]/div/span/a[text()='Unit Cost Quick Calculation']"));
    driverDelay(3000);
    doClick(getWebElement("//li[28]/ul/li[1]/div/span/a[text()='Unit Cost Quick Calculation']"));
    driverDelay(2000);
    assertHelpPageHeader(
            "Unit Cost Quick Calculation",
            printout
    );
  }

  @Test
  public void test08bVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsCopyToQuickRvus() {
    driver.switchTo().parentFrame();
    doClick(getWebElement("//li[28]/ul/li[2]/div/span/a[text()='Copy To Quick RVUs']"));
    assertHelpPageHeader(
            "Copy To Quick RVUs",
            printout
    );
  }

  @Test
  public void test08cVerifyOnlineHelpPagesForUcqcSectionFieldDescriptionsOverwriteRvuMaintenance() {
    driver.switchTo().parentFrame();
    doClick(getWebElement("//li[28]/ul/li[3]/div/span/a[text()='Overwrite RVU Maintenance']"));
    assertHelpPageHeader(
            "Overwrite RVU Maintenance",
            printout
    );
  }

  public String getHelpTocXpath(String sectionName) {
    return "//*[@id='toc']/descendant::a[text()='" + sectionName + "']";
  }

}
