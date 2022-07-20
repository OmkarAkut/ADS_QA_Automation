package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.globalscripts.checktests.SecurityRoleCheck;
import webdriver.globalscripts.help.HelpLinksMainPagesAds2524;
import webdriver.globalscripts.pagetests.GlobalHeaderPageTest;
import webdriver.scripts.status.calculationstatus.CalculationStatusPageSmokeTest;
import webdriver.scripts.datamaintenance.maintaindata.DataMaintenanceAtoZTabPageLoads;

//last update 7-6-21
@RunWith(Suite.class)
@Suite.SuiteClasses({

        GlobalHeaderPageTest.class,
        HelpLinksMainPagesAds2524.class,
        SecurityRoleCheck.class,
        CalculationStatusPageSmokeTest.class,
        DataMaintenanceAtoZTabPageLoads.class,

})

public class DailyBuildTestSuite {
}