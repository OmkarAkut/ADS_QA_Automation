package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

    //GeneralBuildTestSuite.class,
    Release10Dot0TestSuite.class,
    Release10Dot1TestSuite.class,

})

public class RegressionTestSuite {
}
