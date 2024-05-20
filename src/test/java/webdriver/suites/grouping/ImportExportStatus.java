package webdriver.suites.grouping;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.systemmaintenance.UIValidateImportStatuPage;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	UIValidateImportStatuPage.class,
})
public class ImportExportStatus {

}
