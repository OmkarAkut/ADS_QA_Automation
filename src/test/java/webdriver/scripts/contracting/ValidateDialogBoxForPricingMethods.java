package webdriver.scripts.contracting;

import static org.junit.Assert.fail;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateDialogBoxForPricingMethods extends GoHelper{
	private static ContractingMap modelMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String contractModelName = "Contract Model" + currentDateTime;
	static String serviceName = "SERVICE " + currentDateTime;
	static String serviceModelExist;
	static String serviceModelNew;
	String[] columnsToSelect = {"150  Marina Medical Center" };
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
	 static String[] priceMethodOptionPerDiem  = {
			 "Custom Formula",
			 "Departmental Per Diem",
			 "Departmental Per Diem By Day of Stay",
			 "Percent of Charge",
			 "Requires Manual Intervention",
			 
	 };
	 static String[] priceMethodOptionCharge  = {
			 "Custom Formula",
			 "Non Covered Charge",
			 "Percent of Charge",
			 "Rate Per Procedure",
			 "Requires Manual Intervention",
			 
	 };
	 static String perDiemServiceModel="000 OP Per Diem RC 0921 (PD)";
	 static String perChargeServiceModel="ASESC-1633 Charge Default";
	 static String[] filterPerDiemService = { "Name", "Is", "Equal To", perDiemServiceModel };
	 static String[] filterChargeLevelService = { "Name", "Is", "Equal To", perChargeServiceModel };
	/** Support Issues: Automated test script for ADS-12500,ADS-12501 ,ADS-12502*/
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
	public void validateEditPriceDialogBox(String option,String servicemodel, String serviceType) throws Throwable {
	    
		webdriverClick(driver.findElement(By.name("pricemethodoption")));
		waitForAjaxExtJs();
		webdriverClick(driver.findElement(By
				.xpath("//div[contains(@class,'x-boundlist-list-ct')]/ul/li[text()='" + option + "']")));
		Thread.sleep(200);
		// shilpa 01.08.2022 added above steps
		navigateFeeForServicePaymentTermsPagePricingMethodSectionClickEditButtonToOpenEditDialog();
		if(option.equalsIgnoreCase("Custom Formula")) {
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(text(),'New Price for "+servicemodel+" "+"["+serviceType+"]"+"')]")));
			doClick(ContractingMap.geteditPriceCloseIcon());

		}
		else {
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(text(),'Edit Price for "+servicemodel+" "+"["+serviceType+"]"+"')]")));
			doClick(ContractingMap.geteditPriceCancelCloseBtn());
		}
		if(!ContractingMap.geteditPriceWarningCancelCloseButton().isEmpty() && ContractingMap.geteditPriceWarningCancelCloseButton().get(0).isDisplayed() ) {
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
		}else {
			
		}
	}

	@Test
	public void test01ValidatePricingMethodDialogforExistingContractModel_ADS_12500() throws Throwable {
		try {
			doClick(ContractingMap.getopenTaskList());
			contractModelsHelper.navigateFeeForServicePaymentTerms();
			doClick(ContractingMap.getContractFeeForServicePaymentServiceModelHeaderText());
			serviceModelExist = driver.findElement(By.xpath("(//div[contains(@class,'x-tree-view')])[3]//span"))
					.getText();
			if(serviceModelExist.equals("Root")) {
				System.out.println("Element is not displayed or not present");
				ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
				String serviceModelAddNew = driver.findElement(By.xpath("(//div[contains(@id,'treepanel')]//table//div/span)[2]"))
						.getText();
				doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
				doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelAddNew + "']");
				for (String option : priceMethodOption) {
					validateEditPriceDialogBox(option, serviceModelAddNew, "Encounter");
				}
			}
			else {
				doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodHeaderText());
				doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='" + serviceModelExist + "']");
				System.out.println("Element is displayed");
				for (String option : priceMethodOption) {
					validateEditPriceDialogBox(option, serviceModelExist, "Encounter");
				}
			}
			doClick(ContractingMap.getCloseContractBtn());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01ValidatePricingMethodDialogforExistingContractModel_ADS_12500");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidatePricingMethodDialogforExistingContractModel_ADS_12500", driver,
					e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test02ValidatePricingMetodDialogBoxForNewContractModel_ADS_12500() throws Throwable {
		try {
			model.createContractModel(contractModelName);
			ValidateDragDropAddNewServiceUnderPricing.searchContractModelOpenTaskList(contractModelName);
			 serviceModelNew=driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')]//table//div)[1]")).getText();
			 ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			doClick(ContractingMap.getContractFeeForServicePaymentPricingMethodExpandBtn());
			 for(String option:priceMethodOption) {
				   doClick(ContractingMap.getpricingMethodServiceModel());
			    	validateEditPriceDialogBox(option,serviceModelNew,"Encounter");
			       }
			ExtentReport.logPass("PASS", "test02ValidatePricingMetodDialogBoxForNewContractModel_ADS_12500");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidatePricingMetodDialogBoxForNewContractModel_ADS_12500", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test03ValidatePricingMetodDialogBoxForPerDiemServices_ADS_12501() throws Throwable {
		try {
			
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filterPerDiemService);
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			for(String option:priceMethodOptionPerDiem) {
				   doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='"+perDiemServiceModel+"']");
			    	validateEditPriceDialogBox(option,perDiemServiceModel,"Patient Day");
			       }
			
			ExtentReport.logPass("PASS", "test03ValidatePricingMetodDialogBoxForPerDiemServices_ADS_12501");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidatePricingMetodDialogBoxForPerDiemServices_ADS_12501", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
	public void test04ValidatePricingMetodDialogBoxForPerChargeLevelServices_ADS_12502() throws Throwable {
		try {
			doClick(ContractingMap.getContractFeeForServicePaymentClearFilter());
			contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filterChargeLevelService);
			ValidateDragDropAddNewServiceUnderPricing.dragAndDropServiceForNewContractModel();
			for(String option:priceMethodOptionCharge) {
				   doClick("(//div[contains(@class,'x-tree-view')])[4]//span[text()='"+perChargeServiceModel+"']");
			    	validateEditPriceDialogBox(option,perChargeServiceModel,"Charge Item");
			       }
			doClick(ContractingMap.getCloseContractBtn()); 
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			model.test02DeleteContractModel_ADS6435_ADS6412();
			ExtentReport.logPass("PASS", "test04ValidatePricingMetodDialogBoxForPerChargeLevelServices_ADS_12502");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04ValidatePricingMetodDialogBoxForPerChargeLevelServices_ADS_12502", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
