package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import webdriver.globalscripts.accessibilitytests.StatusAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	StatusAXE.class,
})
public class Status {

}
