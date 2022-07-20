package webdriver.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.globalscripts.help.HelpLinksTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        HelpLinksTestSuite.class,

})

public class GeneralBuildTestSuite {
}