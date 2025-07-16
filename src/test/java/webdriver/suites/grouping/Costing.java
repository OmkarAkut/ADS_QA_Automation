package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.CostingAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CostingAXE.class,
})
public class Costing {

}
