package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.costing.EditCostModelCalculationScenarios;
import webdriver.scripts.costing.EncCostCalcScenarioSelectedCostModelScenariosdisplayed;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EditCostModelCalculationScenarios.class,
	EncCostCalcScenarioSelectedCostModelScenariosdisplayed.class,

})
public class CostModelScenarioCalculation {

}
