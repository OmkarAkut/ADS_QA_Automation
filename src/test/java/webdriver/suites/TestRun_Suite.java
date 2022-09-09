package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.examples.spreadsheets.SpreadsheetsTestSuite;
import webdriver.globalscripts.accessibilitytests.AxeAccessibilityTestSuite;
import webdriver.globalscripts.checktests.ChecktestsSuite;
import webdriver.globalscripts.help.HelpLinksTestSuite;
import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.globalscripts.securitytests.SecurityTestsSuite;
import webdriver.globalscripts.smoketests.SmoketestsSuite;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.contracting.contractmodels.ContractModelsSuite;
import webdriver.scripts.costing.CostingTestSuite;
import webdriver.scripts.costing.costingcalculations.CostingCalculationsTestSuite;
import webdriver.scripts.costing.costingmodels.CostingModelsTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.UcqcCalculationTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.UcqcMainpageTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus.UcqcRvusSuite;
import webdriver.scripts.datamaintenance.maintaindata.MaintaindataTestSuite;
import webdriver.scripts.episodes.EpisodesTestSuite;
import webdriver.scripts.regression.generalcalculations.GeneralCalculationsTestSuite;
import webdriver.scripts.reporting.ResportingTestSuite;
import webdriver.scripts.security.SecurityTestSuite;
import webdriver.scripts.security.ucqcroles.UcqcRoleBasedTestSuite;
import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;
import webdriver.templates.TemplatesTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

	SpreadsheetsTestSuite.class,
	AxeAccessibilityTestSuite.class,
	ChecktestsSuite.class,
	//HelpLinksTestSuite.class,
	PagetestsSuite.class,
	SecurityTestsSuite.class,
	SmoketestsSuite.class,
	ContractingSuite.class,
	ContractModelsSuite.class,
	CostingTestSuite.class,
	CostingCalculationsTestSuite.class,
	CostingModelsTestSuite.class,
	//UnitCostQuickCalculationTestSuite.class,
	//UcqcCalculationTestSuite.class,
	//UcqcMainpageTestSuite.class,
	//UcqcRvusSuite.class,
	MaintaindataTestSuite.class,
	EpisodesTestSuite.class,
//	GeneralCalculationsTestSuite.class,
	//ResportingTestSuite.class,
	SecurityTestSuite.class,
	//UcqcRoleBasedTestSuite.class,
	CalculationStatusSuite.class,
	TemplatesTestSuite.class,

})

public class TestRun_Suite {

}










