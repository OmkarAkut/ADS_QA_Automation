package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class CreateUCQCLogMakeUCQCProcessAvailableCalculationPage  extends UcqcHelper{
	private static CostingMap costingMap;
	 static ContractingMap contractingMap;
	  static final String[] requiredFields = {
	    "Marina",
	    "*DM4 FZL 150/3520 w ALL dept chg co_UCQC",
	    "150 Marina Medical Center",
	    "3520",
	    "Apr 2004 to Apr 2004"
	  };
	  @BeforeClass
	  public static void setupScript() throws Throwable {
		  ExtentReport.reportCreate("CreateUCQCLogMakeUCQCProcessAvailableCalculationPage","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "CreateUCQCLogMakeUCQCProcessAvailableCalculationPage");
		   
		  
	    try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractingMap=BuildMap.getInstance(driver, ContractingMap.class);
			System.out.println("Test Class: " + CostChangeColumnsPopulateAfterCalculateAds1230.class.getSimpleName());
			Login.loginUser("CostingDepartmentManager1");
			webdriverMaximizeWindow();
			goToPage("Unit Cost Quick Calculation");
			waitForAjaxExtJs();
			ucqcDisplayChargeCodeGrid(requiredFields);
			 ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","setupScript", driver,e);
	    	fail(e.getMessage());
		} 
	    
	   
	    
	    
	  }
	  @Test
	    public void test01ChangeValues() throws Throwable {
//			WebElement changeCell = ucqcGetCellValueAsWebElement("0ALL", "Salaries and Wages Change", printout);
		      ucqcUpdateGridCellValue("6200133","Quick Salaries and Wages RVU","15",printout);
		    doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
		    doClick(costingMap.getUnitCostQuickCalculationButtonCalculate()); 
		    waitForDisplayedSpinnerToEnd();
		    goToPage("Calculation Status");
		    CalculationHelper.waitForFirstRowCalculationBarToReach100Percent();
		    driverDelay();
		    doClick(costingMap.getRvuDownloadSharedLocDropdown());
		    doClick(contractingMap.getContractModelExportFileSharedLocOption());
			ContractModelsHelper.keyInValues(costingMap.getRvuFileNameInput(), "Test");
			doClick(ContractingMap.getContractFeeForServicePaymentWarningPopUpContinueButton());
			CalculationHelper calculationHelper = new CalculationHelper();
			calculationHelper.calculationStatusPageOpenViewDialog();
			
			calculationHelper.closeViewDialog();
	  }
	  @AfterClass
		public static void endtest() throws Exception {
		  	doClosePageOnLowerBar("Calculation Status");
			doClosePageOnLowerBar("RVU Maintenance");
			ExtentReport.report.flush();

		}
}
