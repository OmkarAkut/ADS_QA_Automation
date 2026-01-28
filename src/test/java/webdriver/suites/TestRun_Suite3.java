package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.pagetests.PagetestsSuite;
import webdriver.scripts.costing.CostingTestSuite;
import webdriver.scripts.costing.costingmodels.CostingModelsTestSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus.UcqcRvusSuite;
import webdriver.scripts.datamaintenance.maintaindata.MaintaindataTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CostingTestSuite.class,
	MaintaindataTestSuite.class,
	PagetestsSuite.class,
	CostingModelsTestSuite.class,
	UcqcRvusSuite.class,

	
	
})

public class TestRun_Suite3 {

}










