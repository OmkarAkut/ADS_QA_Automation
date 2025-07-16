package webdriver.scripts.regression.opps2019calculations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** OPPS 2019 Regression Test Suite - last Update 12-17-19. */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        Opps2019JPackagingC.class,
        Opps2019JPackagingD.class,
        Opps2019JPackagingAndSiEqualsRAds1330.class,
        Opps2019DeviceOffsets.class,
        Opps2019RadioPharm.class,
        Opps2019SkinSubstitutesAds1335.class,
        Opps2019ContrastAgents.class,
        Opps2019UpdateModifierPoLogic.class,

})

public class Opps2019CalculationsTestSuite {
}