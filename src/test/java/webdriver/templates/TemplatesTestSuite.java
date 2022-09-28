package webdriver.templates;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import webdriver.scripts.status.calculationstatus.CalculationStatusSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
		IppsAndOppsCalculations.class,
		JavaTemplate.class,
		JdbcTemplate.class,
		ParameterizedTestTemplate.class,
		StaticFrameworkWithUserRolesLoginParameterizedTestTemplate.class,
		StaticUserRolesParameterizedScriptSingleTest.class,

})




public class TemplatesTestSuite {

}
