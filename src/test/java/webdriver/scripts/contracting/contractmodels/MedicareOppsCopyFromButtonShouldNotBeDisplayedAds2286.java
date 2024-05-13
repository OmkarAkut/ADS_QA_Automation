package webdriver.scripts.contracting.contractmodels;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.helpers.ContractModelsHelper;
import ExtentReport.ExtentReport;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286 extends ContractModelsHelper {
	   private static ContractingMap modelMap;
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName = "Contract Model" + currentDateTime;
    private static final String contractModel = contractModelName;
    private static final String serviceModel = "OPPS 2020";
    String[] columns = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String[] columnsToSelect = { "100  Pacific Hospital", "151  Copy of Marina Medical Center", "150  Marina Medical Center" };
	String[] columnsToRemove = { "100  Pacific Hospital", "151  Copy of Marina Medical Center" };
	String addProvider = "150 Marina Medical Center";
	private static final String serviceName = "OPPS 2020";
	static String[] filter = { "Name", "Is", "Equal To", serviceName };
  //private static EditContractingModelMap editModelMap;

  /** Dev Story ADS-2286. **/
  /** Regression Test Case ADS-6083 incomplete due to scroll issue*/
  @BeforeClass
  public static void setupScript() throws InterruptedException,Throwable {
    //editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
	  ExtentReport.reportCreate("MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286", "webdriver.scripts.contracting.contractmodels", "MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286");
    try {
    	modelMap = BuildMap.getInstance(driver, ContractingMap.class);
		System.out.println("Test Class: " + MedicareOppsCopyFromButtonShouldNotBeDisplayedAds2286.class.getSimpleName());
		evolveLoginStaticUser(Users.ContractAnalyst1);
//		navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
//		navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
//		Thread.sleep(4000);
//		doDropdownSelectUsingOptionText(driver.findElement(By.xpath("//input[contains(@id,'dynamiccombo')]/parent::td")),
//		        driver.findElement(By.xpath("//div[contains(@class, 'x-boundlist-list-ct') and contains(@id, 'listEl')]/ul")),"Medicare/Commercial Outpatient PPS"
//		);
//		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		Thread.sleep(4000);
		ExtentReport.logPass("PASS", "setupScript");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("PASS", "setupScript", driver, e);
		fail(e.getMessage());
    }
  }

  @Test
  public void test01AssertCopyFromButtonIsNotDisplayedForMedicareOppsPricingMethodAds2286_ADS_6083() throws InterruptedException,Throwable {
    try {
    	doClick(modelMap.getNewContractModelButton());
		waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
		assertElementIsDisplayed(ContractingMap.getNewContractModelPopUp());// assert contract model pop up
		doClick(ContractingMap.getContractModelNameInput());
		ContractingMap.getContractModelNameInput().sendKeys(contractModelName);
		doClick(ContractingMap.getContractModelAddProviderBtn());
		waitForElementToBeVisible(ContractingMap.getContractModelAddProviderPopup());
		ContractModelsHelper.selectMultipleColumnsToDisplay(columnsToSelect);
		contractModelsHelper.removeMultipleColumnsToDisplay(columnsToRemove);
		doClick(modelMap.getApplySelections());
		waitForElementToBeVisible(ContractingMap.getNewContractModelPopUp());
		doClick(modelMap.getSaveContractModel());
//		goToPage("Contract Models");
//		doSearchForContractModel(contractModelName);
//		driverDelay(2000);
//		tableDoubleClickCellFirstColumn(contractModelName);
		navigateToContractModelsPageFeeForServicePaymentTermsPage(contractModel);
//		navigateToFeeForServicePaymentTermsPagePricingMethodSectionEditPriceDialog(serviceModel);
		doClick("//div[text()='Service Model']");
		contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filter);
		//Below steps fail due to drag and drop
		ContractModelsHelper.dragAndDropElement((driver.findElement(By.xpath("(//table[@class='x-grid-item x-grid-item-selected'])[4]"))),ContractingMap.getselectDropServiceModelPanel());
		navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceModel);
		Thread.sleep(4000);
		assertElementIsDisplayedWithXpath("//div[contains(@id,'medicarecommercialoutpatientpps')]//following::span[text()='Copy From']");
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
