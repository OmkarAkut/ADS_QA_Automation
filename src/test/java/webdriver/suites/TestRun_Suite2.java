package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.ae.TestSuiteForAE;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.UcqcMainpageTestSuite;
import webdriver.scripts.regression.generalcalculations.GeneralCalculationsTestSuite;
import webdriver.scripts.systemmaintenance.SystemMaintenanceTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestSuiteForAE.class,
	SystemMaintenanceTestSuite.class,
	GeneralCalculationsTestSuite.class,
	UnitCostQuickCalculationSuite.class,
	UcqcMainpageTestSuite.class,

	
	
	
})

public class TestRun_Suite2 {

}










