package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.LogInPageAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LogInPageAXE.class,
})
public class LogInPage {

}
