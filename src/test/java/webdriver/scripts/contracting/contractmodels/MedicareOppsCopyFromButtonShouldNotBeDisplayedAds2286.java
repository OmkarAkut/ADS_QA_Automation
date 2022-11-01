package webdriver.scripts.contracting.contractmodels;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286 extends ContractModelsHelper {

  private static final String contractModel = "ADS1353 Copy From Button";
  private static final String serviceModel = "OPPS 2020";
  //private static EditContractingModelMap editModelMap;

  /** Dev Story ADS-2286. **/
  @BeforeClass
  public static void setupScript() throws InterruptedException,Throwable {
    //editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
	  ExtentReport.reportCreate("MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286", "webdriver.scripts.contracting.contractmodels", "MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286");
    try {
		System.out.println("Test Class: " + MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286.class.getSimpleName());
		evolveLoginStaticUser(Users.ContractAnalyst1);
		navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
		navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
		Thread.sleep(1000);
		doDropdownSelectUsingOptionText(driver.findElement(By.xpath("//td/div[contains(@class, 'x-form-trigger-last')]")),
		        driver.findElement(By.xpath("//div[contains(@class, 'x-boundlist-list-ct') and contains(@id, 'listEl')]/ul")),"Medicare/Commercial Outpatient PPS"
		);
		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
		ExtentReport.logPass("PASS", "setupScript");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("PASS", "setupScript", driver, e);
		fail(e.getMessage());
    }
  }

  @Test
  public void test01AssertCopyFromButtonIsNotDisplayedForMedicareOppsPricingMethodAds2286() throws InterruptedException,Throwable {
    try {
		assertElementIsNotDisplayed("//button/span[@id='copyFromButtonId-btnInnerEl'][text()='Copy From']");
		doClosePageOnLowerBar("ADS1353 Copy From...");
		ExtentReport.logPass("PASS", "test01AssertCopyFromButtonIsNotDisplayedForMedicareOppsPricingMethodAds2286");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("PASS", "test01AssertCopyFromButtonIsNotDisplayedForMedicareOppsPricingMethodAds2286", driver, e);
		fail(e.getMessage());
    }
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
