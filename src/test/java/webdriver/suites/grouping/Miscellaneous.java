package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.MiscellaneousAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	MiscellaneousAXE.class,
})
public class Miscellaneous {

}
