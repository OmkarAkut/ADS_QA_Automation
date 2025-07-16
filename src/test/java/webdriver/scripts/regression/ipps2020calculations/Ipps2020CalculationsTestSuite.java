package webdriver.scripts.regression.ipps2020calculations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** IPPS 2020 Calculations Regression Test Suite - last Update 12-4-19 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        Ipps2020TransfersAds1638.class,
        Ipps2020Transfers0282Ads1639.class,
        Ipps2020DeviceCreditAds1647.class,
        Ipps2020ExcludedServicesAds1651.class,
        Ipps2020AddOnTechAds1653.class,
        Ipps2020PsychComorbidityAds2153.class,

})

public class Ipps2020CalculationsTestSuite {
}