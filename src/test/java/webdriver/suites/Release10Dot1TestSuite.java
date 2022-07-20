package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.contracting.contractmodels.ContractModelsTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    ContractModelsTestSuite.class,

})

public class Release10Dot1TestSuite {
}
