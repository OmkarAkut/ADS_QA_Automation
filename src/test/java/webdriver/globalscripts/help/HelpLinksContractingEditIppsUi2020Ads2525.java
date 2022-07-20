package webdriver.globalscripts.help;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.helpers.PageTestHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelpLinksContractingEditIppsUi2020Ads2525 extends PageTestHelper {

  private static ContractingMap contractingMap;
  private static GeneralElementsMap generalElement;
  private static ModelLibraryMap modelLibrary;
  private static final String contractModel = "AFT IPPS 2020 Defaults - DoNotModify";
  private static final String serviceModel = "OPPS 2019";

  /** This scripts verifies that the help link on each page of the application links to the
   * user help guide and the appropriate help page in the guide displays correctly.
   */
  @BeforeClass
  public static void setupScript() {
    contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
    generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
    System.out.println("TEST CLASS: " + HelpLinksContractingEditIppsUi2020Ads2525.class.getSimpleName());
    loginUser(Users.ApplicationAdministrator1);
  }

  @Test
  public void testContractingTabContractModelsPage01HelpLinkTest() {
    try {
      goToPage("Contract Models");
      waitForSpinnerToEnd();
      testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'mlfd.htm')]")), "Model Library", printout);
      doClosePageOnLowerBar("Model Library");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testContractingTabContractModelsPage02FeeForServicePaymentTermsPageHelpLink() {
    try {
      goToPage("Contract Models");
      waitForSpinnerToEnd();
      navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
      testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'cnffsfd.htm')]")), "Fee for Service Payment Terms", printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testContractingTabContractModelsPage03EditPriceDialogHelpLink() {
    try {
      navigateToFeeForServicePaymentTermsPagePricingMethodSectionEditPriceDialog(serviceModel);
      waitForAjaxExtJs();
      Thread.sleep(1000);
      testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'cnpmippsfd.htm')]")), "Medicare Inpatient PPS Pricing Method", printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testContractingTabContractModelsPage04DischargeStatusCodeForTransfersDialogHelpLink() {
    try {
      Thread.sleep(1000);
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//*[text()='Select' and contains(@id, 'abutton')]")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      Thread.sleep(1000);
      testHelpLink(driver.findElement(By.xpath("//*[contains(@onclick, 'selectorfd.htm')]")), "Selector", printout);
      driver.findElement(By.xpath("//div[contains(@id,'dynamicwindow')]/descendant::*[contains(@class,'x-tool-close')]")).click();
      doClosePageOnLowerBar("AFT IPPS 2020...");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
