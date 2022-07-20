package webdriver.scripts.contracting.contractmodels;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286 extends ContractModelsHelper {

  private static final String contractModel = "ADS1353 Copy From Button";
  private static final String serviceModel = "OPPS 2020";
  //private static EditContractingModelMap editModelMap;

  /** Dev Story ADS-2286. **/
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    //editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    System.out.println("Test Class: " + MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286.class.getSimpleName());
    evolveLoginStaticUser(Users.ContractAnalyst1);
    navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
    navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
//    doDropdownSelectUsingOptionText(
//            driver.findElement(By.xpath("//td/div[contains(@class, 'x-form-trigger-last')]")),
//            driver.findElement(By.xpath("//div[contains(@class, 'x-boundlist-list-ct') and contains(@id, 'listEl')]/ul")),
//            "Medicare/Commercial Outpatient PPS"
//    );
    navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
  }

  @Test
  public void test01AssertCopyFromButtonIsNotDisplayedForMedicareOppsPricingMethodAds2286() throws InterruptedException {
    assertElementIsNotDisplayed("//button/span[@id='copyFromButtonId-btnInnerEl'][text()='Copy From']");
    doClosePageOnLowerBar("ADS1353 Copy From...");
  }
}
