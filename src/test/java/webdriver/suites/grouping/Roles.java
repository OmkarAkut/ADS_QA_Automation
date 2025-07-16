package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.systemmaintenance.EditExistingRoleSetup;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EditExistingRoleSetup.class,
})
public class Roles {

}
