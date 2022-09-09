package webdriver.templates;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.examples.spreadsheets.SpreadsheetsTestSuite;
import webdriver.globalscripts.accessibilitytests.AxeAccessibilityTestSuite;
import webdriver.globalscripts.checktests.ChecktestsSuite;
import webdriver.globalscripts.help.HelpSuite;
import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.globalscripts.securitytests.SecurityTestsSuite;
import webdriver.globalscripts.smoketests.SmoketestsSuite;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.contracting.contractmodels.ContractModelsSuite;
import webdriver.scripts.costing.CostingTestSuite;
import webdriver.scripts.costing.costingmodels.CostingModelsTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationTestSuite;
import webdriver.scripts.datamaintenance.maintaindata.MaintaindataTestSuite;
import webdriver.scripts.datamaintenance.utilities.UtilitiesTestSuite;
import webdriver.scripts.episodes.EpisodesTestSuite;
import webdriver.scripts.regression.generalcalculations.GeneralCalculationsTestSuite;
import webdriver.scripts.reporting.ResportingTestSuite;
import webdriver.scripts.security.SecurityTestSuite;
import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	CalculationStatusSuite.class,
//	SecurityTestSuite.class,
//	ResportingTestSuite.class,
//	GeneralCalculationsTestSuite.class,
//	EpisodesTestSuite.class,
//	UtilitiesTestSuite.class,
//	MaintaindataTestSuite.class,
//	UnitCostQuickCalculationTestSuite.class,
//	CostingModelsTestSuite.class,
//	CostingTestSuite.class,
//	ContractModelsSuite.class,
//	ContractingSuite.class,
//	SmoketestsSuite.class,
//	SecurityTestsSuite.class,
//	PagetestsSuite.class,
//	HelpSuite.class,
//	ChecktestsSuite.class,
//	AxeAccessibilityTestSuite.class,
//	SpreadsheetsTestSuite.class,

})




public class TemplatesTestSuite {

}
