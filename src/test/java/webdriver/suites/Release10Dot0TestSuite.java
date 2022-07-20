package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationTestSuite;
import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;


@RunWith(Suite.class)
@Suite.SuiteClasses({

    UnitCostQuickCalculationTestSuite.class,
    CalculationStatusSuite.class,

})

public class Release10Dot0TestSuite {
}
