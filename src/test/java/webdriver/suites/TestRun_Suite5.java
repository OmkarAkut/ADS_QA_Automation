package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.costing.costingcalculations.CostingCalculationsTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.UcqcCalculationTestSuite;
import webdriver.scripts.datamaintenance.az.AZSuite;
import webdriver.scripts.reporting.ReportingTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AZSuite.class,
	ReportingTestSuite.class,
	CostingCalculationsTestSuite.class,
	UcqcCalculationTestSuite.class,


})

public class TestRun_Suite5 {

}










