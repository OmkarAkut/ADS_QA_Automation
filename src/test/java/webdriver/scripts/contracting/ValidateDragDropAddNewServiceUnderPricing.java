package webdriver.scripts.contracting;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.CimHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateDragDropAddNewServiceUnderPricing extends CalculationHelper{
	private static ContractingMap modelMap;
	static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	 static String contractModelName = "Contract Model" + currentDateTime;
	static String serviceName = "SERVICE " + currentDateTime;
	String[] columnsToSelect = {"150  Marina Medical Center" };
	static String[] filterService = { "Name", "Is", "Equal To", serviceName };
	final static String aTozPage="Services";
	static DataMaintenanceMap dmMap;
	static CostingMap costing;
	CreateANewContractModel model=new CreateANewContractModel();
			;
	/** Support Issues: Automated test script for ADS-12496,ADS-12497 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateDragDropAddNewServiceUnderPricing",
				"webdriver.scripts.contracting",
				"ValidateDragDropAddNewServiceUnderPricing");
		try {
			
			
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			costing=BuildMap.getInstance(driver, CostingMap.class);
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
	
	//drag and drop the service to Service Model pane
	public static void dragAndDropServiceForNewContractModel() throws Throwable {
		WebElement sourceBefore = driver.findElement(By.xpath(
				"(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[1]"));
		WebElement targetBefore = driver.findElement(By.xpath("(//div[@class='x-grid-item-container'])[5]"));
		CimHelper.dragAndDrop(sourceBefore, targetBefore);
//		driverDelay();
	}
	//Search Contract Model and Open Task List then Open Fee For Payment Terms
	public static void searchContractModelOpenTaskList(String modelName) throws Throwable {
		doSearchForContractModel(modelName);
		driverDelay(200);
		tableDoubleClickCellFirstColumn(modelName);
		contractModelsHelper.navigateFeeForServicePaymentTerms();
		doClick(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
		waitForAjaxExtJs();
	}
	
	@Test
	public void test01dragBackService_12496() throws Throwable {
		try {
			model.createContractModel(contractModelName);
			searchContractModelOpenTaskList(contractModelName);
			String existServiceName=driver.findElement(By.xpath("(//label[contains(text(),'Services ')]//following::div[contains(@class,'x-grid-cell-inner ')])[1]")).getText();
			dragAndDropServiceForNewContractModel();
			driverDelay();
			WebElement sourceAfter = driver.findElement(By.xpath(
					"(//div[contains(@class,'glAccountsHierarchyGrid ')])[1]//span[contains(text(),'"+existServiceName+"')]"));
			System.out.println(sourceAfter);
			WebElement targetAfter = driver.findElement(By.xpath("(//div[contains(@class,'glAccountsGrid ')]//following::div[@class='x-grid-item-container']//table)[1]"));
			System.out.println(targetAfter);
			CimHelper.dragAndDrop(sourceAfter, targetAfter);
			assertElementIsDisplayedWithXpath("(//label[contains(text(),'Services ')]//following::div[text()='"+existServiceName+"'])");
			ExtentReport.logPass("PASS", "test01dragBackService_12496");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01dragBackService_12496", driver, e);
			fail(e.getMessage());

		}
	}
	@Test
		public void test02addNewService_12497() throws Throwable {
			try {
				
				doClick(ContractingMap.getNewServiceBtn());
				keyInInputText(serviceName,ContractingMap.getServiceNameImput());
				doClick(ContractingMap.getServiceSaveCloseBtn());
				doClick(ContractingMap.getReturnBtn());
				doClick(ContractingMap.getContractFeeForServicePaymentFilter());	
				doClick(ContractingMap.getfilterServiceDrp());
				doDropdownSelectUsingOptionTextOnly(ContractingMap.getfilterServiceDrpOption(), "Name");
				doClick(ContractingMap.getfilterServiceOperatorDrp());
				doDropdownSelectUsingOptionTextOnly(ContractingMap.getfilterServiceOpDrpOption(), "Is");
				
				doClick(ContractingMap.getfilterServiceConditionDrp());
				doDropdownSelectUsingOptionTextOnly(ContractingMap.getfilterServiceConditionDrpOption(), "Equal To");
//				doFilterSetFilterParameters("Name", "Is", "Equal To", serviceName);
				driver.findElement(By.name("valuefield")).sendKeys(serviceName);
				doClick("//div[contains(@id,'filter')]//span[text() = 'Add']");
				doClick(ContractingMap.getFilterDialogButtonApplyFilter());
//				contractModelsHelper.navigateFeeForServicePaymentTermsPageServiceModel(filterService);
				WebElement source = driver.findElement(By.xpath(
						"(//div[text()='"+serviceName+"'])"));
				WebElement target = ContractingMap.getserviceModelNode();
				CimHelper.dragAndDrop(source, target);
				
				ExtentReport.logPass("PASS", "test02addNewService_12497");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02addNewService_12497", driver, e);
				fail(e.getMessage());

			}
		}
		
		@Test
		public void test03validateNewServiceUnderPricingMethod_12497() throws Throwable {
			try {
				contractModelsHelper
				.navigateCloseOpenSection(ContractingMap.getContractFeeForServicePaymentFilterServiceModel());
				navigateFeeForServicePaymentTermsPagePricingMethodSectionSelectServiceModel(serviceName);
				waitForAjaxExtJs();
				assertElementIsDisplayed("//div[text()='Pricing Method']//following::span[text()='"+serviceName+"']");
				doClick(ContractingMap.getContractFeeForServicePaymentSave());
				//Remove below steps once ADS-23071 is fixed
				doClick("//span[text()='OK']");
				doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
				doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());//till here
				doClick(ContractingMap.getCloseContractBtn()); 
				model.test02DeleteContractModel_ADS6435_ADS6412();
				ExtentReport.logPass("PASS", "test03validateNewServiceUnderPricingMethod_12497");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03validateNewServiceUnderPricingMethod_12497", driver, e);
				fail(e.getMessage());

			}
		}
		@Test
		public void test04deleteService() throws Throwable {
			try {
				goToPage("Maintain Data");
				waitForSpinnerToEnd();
				waitForAjaxExtJs();
				selectMaintainDataAtoZ(aTozPage);
				doClick(ContractingMap.getServiceFilterBtn());
				

				doFilterCreate(filterService);
				doClick(ContractingMap.getServiceDeleteBtn());
				waitForElementToBeVisible(ContractingMap.getContractModelDeleteButtonInPopUp());
				doClick(ContractingMap.getContractModelDeleteButtonInPopUp());
				waitForElementToBeVisible(driver.findElement(By.xpath("//*[text()='There is no data available to display.']")));
				assertTextIsDisplayed("There is no data available to display.");
				doClosePageOnLowerBar("Maintain Data");
				ExtentReport.logPass("PASS", "test04deleteService");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test04deleteService", driver, e);
				fail(e.getMessage());

			}
		}
		@AfterClass
		public static void endtest() throws Exception {
			ExtentReport.report.flush();
		}
}
