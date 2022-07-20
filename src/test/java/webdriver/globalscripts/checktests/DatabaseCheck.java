package webdriver.globalscripts.checktests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import webdriver.globalstatic.SetupStatic;
import webdriver.utilities.JavaDataBaseConnectivity;

public class DatabaseCheck extends SetupStatic {

  final String sqlQuery = "SELECT objectid FROM Costmodelscenario2 WHERE objectid=1";
  private JavaDataBaseConnectivity jdbc = new JavaDataBaseConnectivity(setupDb(setTestEnvironment()));

  @Test
  public void runJdbcConnectionTest() throws ClassNotFoundException {
    jdbc.setSqlQuery(sqlQuery);
    int count = jdbc.jdbcGetRowCountFromDb(jdbc.getSqlQuery());
    System.out.println("Db Row Count: " + count);
    assertEquals(1, count);
  }

}
