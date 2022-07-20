package webdriver.scripts.contracting.contractmodels.ippsfor2020;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020criteriatext.Fy2020IppsCriteriaTextTestSuite;
import webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020uiupdates.Fy2020IppsUiUpdatesTestSuite;
import webdriver.scripts.contracting.contractmodels.ippsfor2020.ipps2020saveedits.Fy2020IppsSavePricingTestSuite;
import webdriver.scripts.contracting.contractmodels.ippsfor2020.ippsuiyearswitching.Fy2020IppsMedicareYearSwitchingTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        Fy2020IppsUiUpdatesTestSuite.class,
        Fy2020IppsSavePricingTestSuite.class,
        Fy2020IppsMedicareYearSwitchingTestSuite.class,
        Fy2020IppsCriteriaTextTestSuite.class,

})

public class Fy2020IppsTestSuite {
}
