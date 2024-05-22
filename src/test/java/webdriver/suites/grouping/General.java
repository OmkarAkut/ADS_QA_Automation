package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.globalscripts.help.HelpLinksMainPagesAds2524;
import webdriver.globalscripts.help.ValidateHelpSectionOnDashboard;
import webdriver.globalscripts.pagetests.AllPageTestsStatic;
import webdriver.globalscripts.pagetests.BuildVerificationTestScript;
import webdriver.globalscripts.pagetests.ChangePasswordDialogTest;
import webdriver.globalscripts.pagetests.GlobalHeaderPageTest;
import webdriver.globalscripts.securitytests.SecurityRolesTestMainPageLevelStatic;
import webdriver.globalscripts.securitytests.SecurityRolesTestMainTabLevelStatic;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	HelpLinksMainPagesAds2524.class,
	ValidateHelpSectionOnDashboard.class,
	SecurityRolesTestMainPageLevelStatic.class,
	SecurityRolesTestMainTabLevelStatic.class,
	AllPageTestsStatic.class,
	BuildVerificationTestScript.class,
	ChangePasswordDialogTest.class,
	GlobalHeaderPageTest.class,

})
public class General {

}
