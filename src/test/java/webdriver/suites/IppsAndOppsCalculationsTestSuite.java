package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.regression.ipps2019calculations.Ipps2019CalculationsTestSuite;
import webdriver.scripts.regression.ipps2020calculations.Ipps2020CalculationsTestSuite;
import webdriver.scripts.regression.ipps2021calculations.Ipps2021CalculationsTestSuite;
import webdriver.scripts.regression.opps2019calculations.Opps2019CalculationsTestSuite;
import webdriver.scripts.regression.opps2020calculations.Opps2020CalculationsTestSuite;

//last update 12-11-20
@RunWith(Suite.class)
@Suite.SuiteClasses({

        Ipps2019CalculationsTestSuite.class,
        Ipps2020CalculationsTestSuite.class,
        Opps2019CalculationsTestSuite.class,
        Opps2020CalculationsTestSuite.class,
        Ipps2021CalculationsTestSuite.class,   //need to update scripts to run in edge env

})

public class IppsAndOppsCalculationsTestSuite {
}