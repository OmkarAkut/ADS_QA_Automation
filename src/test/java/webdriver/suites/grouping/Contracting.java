package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.ContractingAXE;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	ContractingAXE.class,
	})
public class Contracting {

}
