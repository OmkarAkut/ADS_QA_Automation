package webdriver.scripts.costing.unitcostquickcalculation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.UcqcCalculationSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage.UcqcMainPageSuite;
import webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus.UcqcRvusSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    UcqcCalculationSuite.class,
    UcqcMainPageSuite.class,
    UcqcRvusSuite.class,

})

public class UnitCostQuickCalculationTestSuite {
}
