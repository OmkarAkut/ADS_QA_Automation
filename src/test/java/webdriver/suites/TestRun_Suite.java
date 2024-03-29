package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.deployment.SmokeTest;
import webdriver.globalscripts.accessibilitytests.TestSuiteAXE;
import webdriver.globalscripts.checktests.ChecktestsSuite;
import webdriver.globalscripts.help.HelpSuite;
import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.globalscripts.securitytests.SecurityTestsSuite;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.costing.CostingTestSuite;
import webdriver.scripts.costing.costingcalculations.CostingCalculationsTestSuite;
import webdriver.scripts.costing.costingmodels.CostingModelsTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.UcqcCalculationTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.UcqcMainpageTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus.UcqcRvusSuite;
import webdriver.scripts.datamaintenance.maintaindata.MaintaindataTestSuite;
import webdriver.scripts.datamaintenance.utilities.UtilitiesTestSuite;
import webdriver.scripts.episodes.EpisodesTestSuite;
import webdriver.scripts.regression.generalcalculations.GeneralCalculationsTestSuite;
import webdriver.scripts.reporting.ReportingTestSuite;
import webdriver.scripts.security.ucqcroles.UcqcRoleBasedTestSuite;
import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;
import webdriver.scripts.systemmaintenance.SystemMaintenanceTestSuite;





@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	SystemMaintenanceTestSuite.class,
	EpisodesTestSuite.class,
	ReportingTestSuite.class,
	ContractingSuite.class,
	UtilitiesTestSuite.class,
	MaintaindataTestSuite.class,
	TestSuiteAXE.class,
	PagetestsSuite.class,
	HelpSuite.class,
	SecurityTestsSuite.class,
	ChecktestsSuite.class,
	CostingTestSuite.class,
	CostingCalculationsTestSuite.class,
	CostingModelsTestSuite.class,
	CalculationStatusSuite.class,
////	UnitCostQuickCalculationSuite.class,
////	UcqcCalculationTestSuite.class,
	UcqcMainpageTestSuite.class,
	UcqcRvusSuite.class,
	UcqcRoleBasedTestSuite.class,
	GeneralCalculationsTestSuite.class,
	ReportingTestSuite.class,

})

public class TestRun_Suite {

}










