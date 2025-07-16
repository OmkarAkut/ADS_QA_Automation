package webdriver.globalscripts.checktests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	DatabaseCheck.class,
	SecurityRoleCheck.class,
})
public class ChecktestsSuite {

}
