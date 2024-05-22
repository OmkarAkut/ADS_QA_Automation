package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.accessibilitytests.EpisodesAXE;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EpisodesAXE.class,
})
public class Episodes {

}
