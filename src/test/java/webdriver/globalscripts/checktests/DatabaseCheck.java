package webdriver.globalscripts.checktests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ExtentReport.ExtentReport;
import webdriver.core.Driver;
import webdriver.globalstatic.SetupStatic;
import webdriver.utilities.JavaDataBaseConnectivity;

public class DatabaseCheck extends SetupStatic {

  final String sqlQuery = "SELECT objectid FROM Costmodelscenario2 WHERE objectid=1";
  private JavaDataBaseConnectivity jdbc = new JavaDataBaseConnectivity(setupDb(setTestEnvironment()));

  @Test
  public void runJdbcConnectionTest() throws ClassNotFoundException ,Throwable{
	  ExtentReport.reportCreate("runJdbcConnectionTest", "webdriver.globalscripts.checktests","runJdbcConnectionTest");

    try {
		jdbc.setSqlQuery(sqlQuery);
		int count = jdbc.jdbcGetRowCountFromDb(jdbc.getSqlQuery());
		System.out.println("Db Row Count: " + count);
		assertEquals(1, count);
		ExtentReport.logPass("PASS", "runJdbcConnectionTest");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "runJdbcConnectionTest", Driver.driver, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
  
}
