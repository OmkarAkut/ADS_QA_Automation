package webdriver.globalscripts.securitytests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SecurityRolesTestMainPageLevelStatic.class,
	SecurityRolesTestMainTabLevelStatic.class,

})
public class SecurityTestsSuite {

}
