package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.examples.files.FilesTemplate;
import webdriver.examples.spreadsheets.SpreadsheetsTestSuite;
import webdriver.globalscripts.accessibilitytests.AccessibilitytestsSuite;
import webdriver.globalscripts.checktests.ChecktestsSuite;
import webdriver.globalscripts.help.HelpSuite;
import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.globalscripts.securitytests.SecurityTestsSuite;
import webdriver.globalscripts.smoketests.SmoketestsSuite;
import webdriver.scripts.contracting.ClearFilterbuttonModels;
import webdriver.scripts.contracting.ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate;
import webdriver.scripts.contracting.ContractingSuite;
import webdriver.scripts.contracting.CopyPasteButtons;
import webdriver.scripts.contracting.CreateANewContractModel;
import webdriver.scripts.contracting.CreatingaNewContractingFolder;
import webdriver.scripts.contracting.EditRiskLimiterScreen;
import webdriver.scripts.contracting.FilterbuttonModels;
import webdriver.scripts.contracting.GeneratePsychCombinedComorbidityFactor;
import webdriver.scripts.contracting.ValidateContractingModeExportImportButton;
import webdriver.scripts.contracting.contractmodels.ContractModelsSuite;
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
import webdriver.scripts.security.SecurityTestSuite;
import webdriver.scripts.security.ucqcroles.UcqcRoleBasedTestSuite;
import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;
import webdriver.templates.TemplatesTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
//	FilesTemplate.class,
//	SpreadsheetsTestSuite.class,
//	AccessibilitytestsSuite.class,
//	ChecktestsSuite.class,
//	HelpSuite.class,
//	PagetestsSuite.class,
//	SecurityTestsSuite.class,
//	ContractingSuite.class,
//	ContractModelsSuite.class,
//	CostingTestSuite.class,
//	CostingCalculationsTestSuite.class,
//	CostingModelsTestSuite.class,
//	UnitCostQuickCalculationSuite.class,
//	UcqcCalculationTestSuite.class,
//	UcqcMainpageTestSuite.class,
//	UcqcRvusSuite.class,
//	MaintaindataTestSuite.class,
//	EpisodesTestSuite.class,
//	GeneralCalculationsTestSuite.class,
//	ReportingTestSuite.class,
//	UcqcRoleBasedTestSuite.class,
//	CalculationStatusSuite.class,
//	TemplatesTestSuite.class,
//	UtilitiesTestSuite.class,
//	SecurityTestSuite.class,
	ClearFilterbuttonModels.class,
	ContractingModelDefinePaymentTermsFeeForServicePaymentTermsCalculate.class,
	CopyPasteButtons.class,
	CreatingaNewContractingFolder.class,
	EditRiskLimiterScreen.class,
	FilterbuttonModels.class,
	GeneratePsychCombinedComorbidityFactor.class,
	ValidateContractingModeExportImportButton.class,

})

public class TestRun_Suite {

}










