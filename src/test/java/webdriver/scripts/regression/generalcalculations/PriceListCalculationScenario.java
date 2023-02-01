package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriceListCalculationScenario extends CalculationHelper {
		static DataMaintenanceMap dmMap;
		static ContractingMap contractMap;
		final static String batch="v10.2 REGRESSION PL Calc - Avg Enc Chgs1";
		final static String aTozPage = "Price Lists";
		final static String aTozPage1="Price List Calculation Scenarios";
		static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		static String priceList = "PRICE" + currentDateTime;
		static String priceListName=currentDateTime.replaceAll("\\W", "")+" "+priceList;
		static String chargeMaster="150 Marina Charge Master 2";
		static String[] filterPriceScenario= {"Name","Is","Equal To","v10.2 REGRESSION PL Calc - Avg Enc Chgs1"};
		static String[] filterPriceList= {"Name","Is","Equal To",priceList};
		/** Regression: Test script for ADS-6103 */
	  @BeforeClass
	  public static void setupScript() throws Exception,Throwable {
		  ExtentReport.reportCreate("GeneralCalculationsChargeItemServiceClassificationSchemeAds2342", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsChargeItemServiceClassificationSchemeAds2342");
	    try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
			fail(e.getMessage());
		} 
	  }
	  
	  @Test
	  public void test01AddNewPriceList() throws Throwable {
		  try {
			doClick(DataMaintenanceMap.getLoadDataNewButton());
			  ContractingMap.getMedicareCode().sendKeys(currentDateTime.replaceAll("\\W", ""));
			 ContractingMap.getInputName().sendKeys(priceList);
			 doDropdownSelectUsingOptionText(DataMaintenanceMap.getChargeMaster(), chargeMaster);
			 doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			  ExtentReport.logPass("PASS", "test01AddNewPriceList");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01AddNewPriceList", driver, e);
				fail(e.getMessage());
			}
	  }
	  
	  @Test
	  public void test02ChangeResultsDestinationPriceLists() throws Throwable {
		  try {
			selectMaintainDataAtoZ(aTozPage1);
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			driverDelay(100);
			doFilterCreate(filterPriceScenario);
			tableDoubleClickCellFirstColumn(batch);
			System.out.println(currentDateTime.replaceAll("\\W", "")+" "+priceList);
			doDropdownSelectUsingOptionText(DataMaintenanceMap.getPriceListMaster(), currentDateTime.replaceAll("\\W", "")+" "+priceList);
			ContractModelsHelper.scrollToView(DataMaintenanceMap.getLogLoc());
			DataMaintenanceMap.getLogLoc().clear();
			DataMaintenanceMap.getLogLoc().sendKeys(priceListName);
			doClick(ContractingMap.getSaveBenefitPlan());
			ExtentReport.logPass("PASS", "test02ChangeResultsDestinationPriceLists");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test02ChangeResultsDestinationPriceLists", driver, e);
				fail(e.getMessage());
			}
	  }
	  
	  @Test
	  public void test03ValidateCalculationStatus() throws Throwable {
		  try {
			  doClick(DataMaintenanceMap.getCalculateButton());
			  waitForPageTitle("Calculation Status");
			 
			  waitForFirstRowCalculationBarToReach100Percent();
			  calculationStatusPageOpenViewDialog();
			  driverDelay(5000);
		      confirmCalculationStatusDetailsContains("Total number of price items inserted/updated: 6443");
		      confirmCalculationStatusDetailsContains("Total number of charge items processed: 4185640");

		      closeViewDialog();
		      deleteMyCalculationStatusFirstRow();
		     
		      doClosePageOnLowerBar("Calculation Status");
		      doClick(ContractingMap.getContractFeeForServicePaymentSave());
			  ExtentReport.logPass("PASS", "test03ValidateCalculationStatus");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03ValidateCalculationStatus", driver, e);
				fail(e.getMessage());
			}
	  }
	  
	  @Test
	  public void test04OpenPriceList() throws Throwable {
		  try {
			selectMaintainDataAtoZ(aTozPage);
			doClick(DataMaintenanceMap.getLoadDataFilterButton());
			doFilterCreate(filterPriceList);
			tableDoubleClickCellFirstColumn(priceList);
			 ExtentReport.logPass("PASS", "test03ValidateCalculationStatus");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test03ValidateCalculationStatus", driver, e);
				fail(e.getMessage());
			}
	  }
}
