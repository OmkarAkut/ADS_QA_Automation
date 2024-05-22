package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.systemmaintenance.ValidateSecuritySettingsGeneralSettingspagesintheSystemMaintenance;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ValidateSecuritySettingsGeneralSettingspagesintheSystemMaintenance.class,
})
public class SecuritySettings {

}
