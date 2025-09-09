package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateDialogBoxForPricingMethods extends GoHelper{
	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName;
	static String serviceName = "SERVICE " + currentDateTime;
	String[] columnsToSelect = {"150  Marina Medical Center" };
	static String[] filterService = { "Name", "Is", "Equal To", serviceName };
	CreateANewContractModel model=new CreateANewContractModel();
	ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	 static String[] priceMethodOption = {
			 "AP DRG Fee Schedule",
			 "APG Fee Schedule",
			 "APR DRG Fee Schedule",
			 "Case Rate",
			 "Case Rate Based on Length of Stay",
			 "Chargeable Activity Fee Schedule",
			 "Commercial DRG",
			 "Custom Formula",
			 "EAPG Fee Schedule",
			 "Encounter Procedure Fee Schedule",
			 "HCFA DRG Fee Schedule",
			 "HCPCS Code Fee Schedule",
			 "Level of Care",
			 "Medicare Inpatient PPS",
			 "Medicare/Commercial ASC",
			 "Medicare/Commercial Outpatient PPS",
			 "Medicare/Commercial RBRVS",
			 "MSDRG Fee Schedule",
			 "Per Diem",
			 "Percent of Charge",
			 "Requires Manual Intervention",
			 "Revenue Code Fee Schedule",
			 "TRICARE DRG Fee Schedule",
	 };
	/** Regression: Automated test script for ADS-12500 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateDialogBoxForPricingMethods",
				"webdriver.scripts.contracting",
				"ValidateDialogBoxForPricingMethods");
		try {
			
			
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contract Models");
			waitForDisplayedSpinnerToEnd();
			assertThatString(modelMap.getContractModelHeader(), "Contracting Model Library", printout);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	public void validateEditPriceDialogBox(String option) throws Throwable {
	    String serviceModel=ContractingMap.getpricingMethodServiceModel().getText();
	    
		webdriverClick(driver.findElement(By.name("pricemethodoption")));
		waitForAjaxExtJs();
		webdriverClick(driver.findElement(By
				.xpath("//div[contains(@class,'x-boundlist-list-ct')]/ul/li[text()='" + option + "']")));
		Thread.sleep(200);
		// shilpa 01.08.2022 added above steps
		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
		if(option.equalsIgnoreCase("Custom Formula")) {
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(text(),'New Price for "+serviceModel+" "+"[Encounter]"+"')]")));
			doClick(ContractingMap.geteditPriceCloseIcon());

		}
		else {
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(text(),'Edit Price for "+serviceModel+" "+"[Encounter]"+"')]")));
			doClick(ContractingMap.geteditPriceCancelCloseBtn());
		}
		if(!ContractingMap.geteditPriceWarningCancelCloseButton().isEmpty() && ContractingMap.geteditPriceWarningCancelCloseButton().get(0).isDisplayed() ) {
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
		}else {
			
		}
	}
//	@Test
	public void test01ValidatePricingMethodDialogforExistingContractModel_ADS_12500() throws Throwable {
		try {
			doClick("//span[text()='Open Task List']");
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelHeaderText());
			if (!ContractingMap.getserviceModel().isEmpty() && ContractingMap.getserviceModel().get(0).isDisplayed()) {
				doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
			    doClick(ContractingMap.getpricingMethodServiceModel());
			    System.out.println("Element is displayed");
			    for(String option:priceMethodOption) {
			    	validateEditPriceDialogBox(option);
			       }
			    
			} else {
			    System.out.println("Element is not displayed or not present");
			    WebElement sourceBefore = driver.findElement(By.xpath(
						"(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[1]"));
				WebElement targetBefore = driver.findElement(By.xpath("(//div[@class='x-grid-item-container'])[5]"));
				CimHelper.dragAndDrop(sourceBefore, targetBefore);
				 for(String option:priceMethodOption) {
				    	validateEditPriceDialogBox(option);
				    }
				 
			}
			doClick(ContractingMap.getCloseContractBtn()); 
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());

			ExtentReport.logPass("PASS", "test01ValidatePricingMethodDialogforExistingContractModel_ADS_12500");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidatePricingMethodDialogforExistingContractModel_ADS_12500", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test02ValidatePricingMetodDialogBoxForNewContractModel_ADS_12500() throws Throwable {
		try {
			model.test01CreateNewContractModel_6413();
			contractModelName=CreateANewContractModel.contractModelName;
			doSearchForContractModel(contractModelName);
			driverDelay(200);
			tableDoubleClickCellFirstColumn(contractModelName);
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			doClick(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
			waitForAjaxExtJs();
			WebElement sourceBefore = driver.findElement(By.xpath(
					"(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[1]"));
			WebElement targetBefore = driver.findElement(By.xpath("(//div[@class='x-grid-item-container'])[5]"));
			CimHelper.dragAndDrop(sourceBefore, targetBefore);
//			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelExpandBtn());
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodExpandBtn());
//			doClick(ContractingMap.getpricingMethodServiceModel());
			
			 for(String option:priceMethodOption) {
				   doClick(ContractingMap.getpricingMethodServiceModel());
			    	validateEditPriceDialogBox(option);
			       }
			 doClick(ContractingMap.getCloseContractBtn()); 
			 doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidatePricingMetodDialogBox_12500");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidatePricingMetodDialogBox_12500", driver, e);
			fail(e.getMessage());

		}
	}
}
