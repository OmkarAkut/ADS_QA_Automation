package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.systemmaintenance.EditAnExistingUserSetup;
import webdriver.scripts.systemmaintenance.ValidateFilterbuttonontheSystemMaintenanceUserspage;
import webdriver.scripts.systemmaintenance.ValidateImportExportbuttonsontheSystemMaintenance;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EditAnExistingUserSetup.class,
	ValidateFilterbuttonontheSystemMaintenanceUserspage.class,
	ValidateImportExportbuttonsontheSystemMaintenance.class,

})
public class Users {

}
