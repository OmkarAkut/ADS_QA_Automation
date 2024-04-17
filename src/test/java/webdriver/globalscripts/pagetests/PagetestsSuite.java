package webdriver.globalscripts.pagetests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	AllPageTestsStatic.class,
	BuildVerificationTestScript.class,
	ChangePasswordDialogTest.class,
	GlobalHeaderPageTest.class,

})
public class PagetestsSuite {

}
